package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import objects.User;
import service.UserService;
import service.UserServiceI;

@SuppressWarnings("serial")
@ManagedBean(name="UserTableBean")
@SessionScoped
public class UserTableBean implements Serializable {
	
	private User selectedUser = null;
	private List<User> userList = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct () {
		 UserServiceI service = new UserService();
		 userList = service.findAll();
	}
	
	public String loadRqForSelectedUser() {
		
		return "profile";
	}

	public void onRowSelect(SelectEvent event) {}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
