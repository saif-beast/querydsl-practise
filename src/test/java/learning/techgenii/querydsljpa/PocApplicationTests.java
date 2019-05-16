package learning.techgenii.querydsljpa;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import learning.techgenii.querydsljpa.controller.UserFilterCO;
import learning.techgenii.querydsljpa.domains.BlogPost;
import learning.techgenii.querydsljpa.domains.User;
import learning.techgenii.querydsljpa.querydsl.QBlogPost;
import learning.techgenii.querydsljpa.querydsl.QUser;
import learning.techgenii.querydsljpa.repositories.BlogPostRepository;
import learning.techgenii.querydsljpa.repositories.UserRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PocApplicationTests {

	@Autowired
	private EntityManager entityManager;

	private static JPAQueryFactory jpaQueryFactory;
	private static QUser user;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogPostRepository blogPostRepository;

	@Before
	public void initialiseJPAQueryFactory() {
		if (jpaQueryFactory == null) {
			jpaQueryFactory = new JPAQueryFactory(entityManager);
			user = QUser.user;
		}

	}

	private static User getUserEntity(String login, String name, Boolean disabled) {
		User user = new User();
		user.setDisabled(disabled);
		user.setLogin(login);
		user.setName(name);
		return user;
	}


	@Test
	public void isJPAQueryFactoryInitialised() {
		assert jpaQueryFactory != null;
	}

	@Test
	public void queryWithNameQueryDSL() {
		userRepository.deleteAll();
		Stream.of(
				new User(null,"saifullah","saif",false,null),
				new User(null,"saifullah","saif",false,null),
				new User(null,"saifullah","saif",false,null)
		).forEach(user -> {
			userRepository.save(user);
		});

		List<User> dbFetchedUsers = this.jpaQueryFactory
				.selectFrom(user)
				.where(user.login.eq("saif"))
				.orderBy(user.login.desc())
				.fetchAll()
				.fetch();

		User user1 = dbFetchedUsers.get(0);



		Assert.notEmpty(dbFetchedUsers,"Fetched User cannot be empty");
		Assert.isTrue(user1.getLogin().equals("saif"),"");
	}

	@Test
	public void groupingAndOrderingOnBlogPost() {
		blogPostRepository.deleteAll();
		QBlogPost qBlogPost = QBlogPost.blogPost;
		Stream.of(
				new BlogPost(null,"QueryDSL Group1","QueryDSL body",null),
				new BlogPost(null,"QueryDSL Group1","QueryDSL body",null),
				new BlogPost(null,"QueryDSL Group2","QueryDSL body",null),
				new BlogPost(null,"QueryDSL Group1","QueryDSL body",null),
				new BlogPost(null,"QueryDSL Group2","QueryDSL body",null)
		).forEach(blogPost -> {
			blogPostRepository.save(blogPost);
		});

		NumberPath<Long> count = Expressions.numberPath(Long.class,"c");
		List<Tuple> tuples = jpaQueryFactory.select(
				qBlogPost.title, qBlogPost.id.count().as(count))
				.from(qBlogPost)
				.groupBy(qBlogPost.title)
				.orderBy(count.desc())
				.fetch();

		//Printed the result from console then used as expectedResult string
		String expectedResult = "[[QueryDSL Group1, 3], [QueryDSL Group2, 2]]";
		Assert.isTrue(expectedResult.equals(tuples.toString()),"Does not match with the expected result");

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void complexQueryWithJoins() {
		userRepository.deleteAll();
		blogPostRepository.deleteAll();

		QBlogPost qBlogPost = QBlogPost.blogPost;
		QUser qUser = QUser.user;

		List<BlogPost> user1_blogPosts = Arrays.asList(
				new BlogPost(null, "QueryDSL Group1", "QueryDSL body 1", null),
				new BlogPost(null, "QueryDSL Group1", "QueryDSL body 2", null),
				new BlogPost(null, "QueryDSL Group1", "QueryDSL body 3", null)

		);

		Set<BlogPost> blogPostSet1 = new HashSet<>(user1_blogPosts);

		blogPostRepository.save(blogPostSet1);

		List<BlogPost> user2_blogPosts = Arrays.asList(
				new BlogPost(null, "QueryDSL Group2", "QueryDSL body", null),
				new BlogPost(null, "QueryDSL Group2", "QueryDSL body", null)

		);

		Set<BlogPost> blogPostSet2 = new HashSet<>(user2_blogPosts);

		blogPostRepository.save(blogPostSet2);

		Stream.of(
				new User(null,"Saifullah","saif",false,blogPostSet1),
				new User(null,"Arjun","arjun",false,blogPostSet2)
		).forEach(user -> {
			userRepository.saveAndFlush(user);
		});

		List<User> users = jpaQueryFactory.selectFrom(qUser)
				.innerJoin(qUser.blogPosts, qBlogPost)
				.on(qBlogPost.title.eq("QueryDSL Group1"))
				.fetch();

		System.out.println(users);


	}

	@AfterClass
	public static void db_cleanUp(){
	}

}
