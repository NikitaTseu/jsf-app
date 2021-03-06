package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "select u from User u"),
    @NamedQuery(name = "User.findByLogin", query = "select distinct u from User u where u.login = :loginParam"),
    @NamedQuery(name = "User.maxId", query = "select MAX(u.id) from User u")
})
@SuppressWarnings("serial")
public class User implements Serializable{
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "librn")
	private int librn = 0;
	
	@Column(name = "name")
	private String name = "EMPTY";
	
	@Column(name = "surname")
	private String surname = "EMPTY";
	
	@Column(name = "login")
	private String login = "EMPTY";
	
	@Column(name = "password")
	private String password = "EMPTY";
	
	@OneToMany(mappedBy = "user")
	private List<Request> request = new ArrayList<Request>();

	public User(int id, int librn, String name, String surname, String login, String password) {
		super();
		this.id = id;
		this.librn = librn;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}
	
	public User() {
		this.id = 0;
		this.librn = 0;
		this.name = "EMPTY";
		this.surname = "EMPTY";
		this.login = "EMPTY";
		this.password = "EMPTY";
	}
	
	public String toString() {
		return this.surname + this.name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLibrn() {
		return librn;
	}
	public void setLibrn(int librn) {
		this.librn = librn;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
