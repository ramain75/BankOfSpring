package org.bankofspring.web;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

@Component
public class CustomerForm {
	private String name;
	private String email;
	private String description;
	@NotNull (message="Name is mandatory")
	@NotBlank (message="Name is mandatory")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
