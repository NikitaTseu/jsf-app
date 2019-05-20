package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import objects.User;
import service.UserService;
import service.UserServiceI;

@ManagedBean(name = "RegisterBean")
@SessionScoped
public class RegisterBean {
	
	private int id = 0;
	private String name = "";
	private String surname = "";
	private String login = "";
	private String pass1 = "";
	
	private User user = null;
	
	private String message = "";
	
	UserServiceI service = new UserService();
	
	public RegisterBean() {}
	
	public void refresh() {
		id = 0;
		name = "";
		surname = "";
		login = "";
		pass1 = "";
		message = "";
	}

	public String submit() {
		user = new User(service.generateNewId(), 0, name, surname, login, pass1);
		service.add(user);
		refresh();
		return "login2";
	}
	
	public String cancel() {
		refresh();
		return "login";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getPass1() {
		return pass1;
	}
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
