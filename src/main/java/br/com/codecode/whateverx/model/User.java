package br.com.codecode.whateverx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
 
@Entity
@NamedQuery(name="findAll", query="SELECT u FROM User u")
public class User extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column
	private String email;

	@Column
	private String password;	

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (getId() != null) {
			if (!getId().equals(other.getId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (email != null && !email.trim().isEmpty())
			result += "email: " + email;
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		return result;
	}
}