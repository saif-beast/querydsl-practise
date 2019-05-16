package learning.techgenii.querydsljpa.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import learning.techgenii.querydsljpa.domains.BlogPost;


/**
 * QBlogPost is a Querydsl query type for BlogPost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlogPost extends EntityPathBase<BlogPost> {

    private static final long serialVersionUID = -1415158227L;

    public static final QBlogPost blogPost = new QBlogPost("blogPost");

    public final StringPath body = createString("body");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QBlogPost(String variable) {
        super(BlogPost.class, forVariable(variable));
    }

    public QBlogPost(Path<? extends BlogPost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlogPost(PathMetadata metadata) {
        super(BlogPost.class, metadata);
    }

}

