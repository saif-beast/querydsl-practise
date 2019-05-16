package learning.techgenii.querydsljpa.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String login;

	private Boolean disabled;

	@OneToMany(cascade = CascadeType.PERSIST,mappedBy = "user")
	private Set<BlogPost> blogPosts = new HashSet<>(0);

}
