package learning.techgenii.querydsljpa.controller;

import lombok.Data;

@Data
public class UserCO {

	private Long id;

	private String name;

	private String login;

	private Boolean disabled;
}
