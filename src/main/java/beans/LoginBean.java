package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	
	@ManagedProperty("#{SessionBean}")
	private SessionBean sessionBean;
	
	UserServiceI service = new UserService();
	
	public LoginBean() {}
	
	public void refresh() {
		login = "";
		pass = "";
		sessionBean.setInvalidLoginFlag(0);
	}
	
	public String register() {
		refresh();
		sessionBean.setUserRegisteredFlag(0);
		return "register";
	}
	
	public String check() {
		user = service.findByLogin(login);
		if(user == null || !pass.equals(user.getPassword())) {
			refresh();
			sessionBean.setUserRegisteredFlag(0);
			sessionBean.setInvalidLoginFlag(1);
			return "login";
		}
		else {
			refresh();
			sessionBean.setUserRegisteredFlag(0);
			if(user.getLibrn() == 1) {
				return "userList";
			}
			else {
				return "startPageUser";
			}
		}
	}
	
	public SessionBean getSessionBean() {
		return sessionBean;
	}
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
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
