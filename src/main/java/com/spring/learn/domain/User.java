package com.spring.learn.domain;

//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class User {
	
//	@Size(min=6, message="The <name> must contains more than 6 symbols")
	private String name;
	
//	@Size(min=5, max=10, message="The <password> must be within 5-10 symbols length")
	private String password;
	private boolean admin;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
