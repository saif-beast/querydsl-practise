package learning.techgenii.querydsljpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import learning.techgenii.querydsljpa.domains.User;
import learning.techgenii.querydsljpa.dto.BlogPostDTO;
import learning.techgenii.querydsljpa.dto.UserDTO;
import learning.techgenii.querydsljpa.querydsl.QUser;
import learning.techgenii.querydsljpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController implements CrudController<UserCO,Long>{

    private final UserRepository userRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public UserController(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * Handle validation error response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationError(MethodArgumentNotValidException ex) {
        Map map = new HashMap();
        map.put("errorCode",400);
        map.put("errorMessage",ex.getMessage());
        return ResponseEntity.badRequest().body(map);
    }

    @Override
    public ResponseEntity create(@RequestBody UserCO userCO) throws JsonProcessingException {

        User user = new User(userCO.getId(),userCO.getName(),userCO.getLogin(),userCO.getDisabled(),null);
        userRepository.save(user);
        return ResponseEntity.ok(getUserDTO(user));
    }

    @Override
    public ResponseEntity update(@RequestBody UserCO userCO) {
        return null;
    }

    @Override
    public ResponseEntity get(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @PostMapping("/filter")
    public ResponseEntity filter(@RequestBody UserFilterCO userFilterCO) {
        QUser user = QUser.user;

        List<User> dbFetchedUsers = this.jpaQueryFactory
                .selectFrom(user)
                .where(user.login.contains(userFilterCO.getName()))
                .orderBy(user.login.desc())
                .fetchAll()
                .fetch();

        int ALICE = 0;
        int BOB   = 1;

        List<Integer> resultScore = new ArrayList<>(2);
        resultScore.set(0,0);
        resultScore.set(1,0);

        Integer integer = resultScore.get(ALICE);
        integer++;
        resultScore.set(ALICE,integer);

        return ResponseEntity.
                ok(
                        Optional.ofNullable(dbFetchedUsers).orElse(new ArrayList<>())
                                .stream()
                                .map(this::getUserDTO)
                                .collect(Collectors.toList())
                );
    }

    private UserDTO getUserDTO(User dbFetchedUser) {
        return new UserDTO(
                dbFetchedUser.getId(), dbFetchedUser.getName(), dbFetchedUser.getLogin(), dbFetchedUser.getDisabled(),
                Optional.ofNullable(dbFetchedUser.getBlogPosts()).orElse(new HashSet<>()).stream().map(blogPost ->
                        new BlogPostDTO(blogPost.getId(), blogPost.getTitle(), blogPost.getBody())
                ).collect(Collectors.toSet())
        );
    }
}
