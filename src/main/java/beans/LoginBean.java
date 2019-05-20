package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import objects.User;
import service.UserService;
import service.UserServiceI;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {
	
	private String login = "";
	private String pass = "";
	private User user = null;
	private String message="";
	private String messageDetails="";
	
	UserServiceI service = new UserService();
	
	public LoginBean() {}
	
	public void refresh() {
		login = "";
		pass = "";
	}
	
	public String register() {
		refresh();
		return "register";
	}
	
	public String check() {
		user = service.findByLogin(login);
		if(user == null || !pass.equals(user.getPassword())) {
			refresh();
			return "login3";
		}
		else {
			refresh();
			if(user.getLibrn() == 1) {
				return "startPageLibrn";
			}
			else {
				return "startPageUser";
			}
		}
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
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

	public String getMessageDetails() {
		return messageDetails;
	}
	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}
		
}
