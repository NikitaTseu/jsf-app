package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.time.DateUtils;

import objects.Book;
import objects.Request;
import service.BookService;
import service.BookServiceI;
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
	BookServiceI bookService;
	
	List<Request> requests = new ArrayList<>();
	List<Request> selectedRq = new ArrayList<>();
	
	String[] status = {"open", "closed", "expired"};
	
	String bookId = "";
	
	@PostConstruct
	public void postConstruct () {
		 service = new RqService();
		 bookService = new BookService();
		 requests = service.findAll();
	}
	
	public String loadRqForSelectedUser() {
		int uid = utb.getSelectedUser().getId();
		utb.getSelectedUser().setRequest(service.findByUser(uid));
		return "profile?faces-redirect=true";
	}
	
	public String closeSelected() {
		for(int i = 0; i < selectedRq.size(); i++) {
			service.updateClosedStatus(selectedRq.get(i));
		}
		selectedRq = new ArrayList<>();
		return "profile?faces-redirect=true";
	}
	
	public String prolongSelected() {
		for(int i = 0; i < selectedRq.size(); i++) {
			service.prolong(selectedRq.get(i));
		}
		selectedRq = new ArrayList<>();
		return "profile?faces-redirect=true";
	}
	
	public void newRequest() {
		Book b = bookService.findById(Integer.parseInt(bookId));
		bookId = "";
		if(b != null) {
			Date now = new Date();
			now = DateUtils.round(now, Calendar.DAY_OF_MONTH);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DAY_OF_MONTH, 14);
			
			Request r = new Request(service.generateNewId(), utb.getSelectedUser(), b, now, cal.getTime());
			service.add(r);
			
			int uid = utb.getSelectedUser().getId();
			utb.getSelectedUser().setRequest(service.findByUser(uid));
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Successful", "Book added") );
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Oooops", "Wrong book ID") );
		}
		//return "profile?faces-redirect=true";
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

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
}
