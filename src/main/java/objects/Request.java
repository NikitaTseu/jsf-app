package objects;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name = "requests")
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "select r from Request r"),  
    @NamedQuery(name = "Request.findByUser", query = "select r from Request r where r.user.id = :idParam"),
    @NamedQuery(name = "Request.maxId", query = "select MAX(r.id) from Request r")
})
@SuppressWarnings("serial")
public class Request implements Serializable{
	@Id
	@Column(name = "id")
	private int id;
	
	//@Column(name = "user_id")
	@Transient
	private int user_id;
	
	//@Column(name = "book_id")
	@Transient
	private int book_id;
	
	@Column(name = "date1")
	private Date date1;
	
	@Column(name = "date2")
	private Date date2;
	
	@Column(name = "closed")
	private int closed;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	private String status = "";

	public Request(int id, User user, Book book, Date date1, Date date2) {
		super();
		closed = 0;
		this.id = id;
		this.user = user;
		this.book = book;
		this.date1 = date1;
		this.date2 = date2;
	}

	public Request() {
		super();
		this.id = 0;
		this.user_id = 0;
		this.book_id = 0;
		this.date1 = null;
		this.date2 = null;
	}
	
	public void prolong() {
		Date d2 = DateUtils.round(date2, Calendar.DAY_OF_MONTH);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d2);
		cal.add(Calendar.DAY_OF_MONTH, 14);
		date2 = cal.getTime();
		status = getStatus();
	}
	
	public String toString() {
		return "Request #" + this.id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public int getClosed() {
		return closed;
	}
	public void setClosed(int closed) {
		this.closed = closed;
	}

	public String getStatus() {
		if(closed == 1) {
			status = "closed";
		}
		else {
			Date now = new Date();
			now = DateUtils.round(now, Calendar.DAY_OF_MONTH);
			Date d2 = DateUtils.round(date2, Calendar.DAY_OF_MONTH);
			if(d2.before(now)) {
				return "expired";
			}
			else {
				return "open";
			}
		}
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}