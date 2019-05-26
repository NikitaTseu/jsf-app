package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import objects.Request;
import service.RqService;
import service.RqServiceI;

@SuppressWarnings("serial")
@ManagedBean(name="RequestBean")
@SessionScoped
public class RequestBean implements Serializable {
	public RequestBean() {};
	
	@ManagedProperty("#{UserTableBean}")
	private UserTableBean utb;
	
	RqServiceI service;
	List<Request> requests = new ArrayList<>();
	List<Request> selectedRq = new ArrayList<>();
	
	String[] status = {"open", "closed", "expired"};
	
	@PostConstruct
	public void postConstruct () {
		 service = new RqService();
		 requests = service.findAll();
	}
	
	public String loadRqForSelectedUser() {
		int uid = utb.getSelectedUser().getId();
		utb.getSelectedUser().setRequest(service.findByUser(uid));
		return "profile?faces-redirect=true";
	}
	
	public String closeSelected() {
		for(int i=0;i<selectedRq.size();i++) {
			service.updateClosedStatus(selectedRq.get(i));
		}
		selectedRq = new ArrayList<>();
		return "profile?faces-redirect=true";
	}

	public UserTableBean getUtb() {
		return utb;
	}

	public void setUtb(UserTableBean utb) {
		this.utb = utb;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Request> getSelectedRq() {
		return selectedRq;
	}

	public void setSelectedRq(List<Request> selectedRq) {
		this.selectedRq = selectedRq;
	}

	public List<String> getStatus() {
		return Arrays.asList(status);
	}

	public void setStatus(String[] status) {
		this.status = status;
	}
	
	

}
