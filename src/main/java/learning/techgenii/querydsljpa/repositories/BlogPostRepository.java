package learning.techgenii.querydsljpa.repositories;

import learning.techgenii.querydsljpa.domains.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
}
