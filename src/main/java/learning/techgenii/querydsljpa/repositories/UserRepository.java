package learning.techgenii.querydsljpa.repositories;

import learning.techgenii.querydsljpa.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
