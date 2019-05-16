package learning.techgenii.querydsljpa.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogPost {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String body;

	@ManyToOne
	private User user;
}
