package learning.techgenii.querydsljpa.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterCO {

	private String name;

	private String login;

	private Boolean disabled;

	private Set<String> blogPostTitle = new HashSet<>(0);
}
