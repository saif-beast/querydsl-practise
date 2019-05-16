package learning.techgenii.querydsljpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;

	private String name;

	private String login;

	private Boolean disabled;

	private Set<BlogPostDTO> blogPosts = new HashSet<>();
}
