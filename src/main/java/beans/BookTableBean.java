package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import objects.Book;
import service.BookService;
import service.BookServiceI;

@SuppressWarnings("serial")
@ManagedBean(name="BookTableBean")
@SessionScoped
public class BookTableBean implements Serializable {
	
	private Book selectedBook = null;
	private List<Book> bookList = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct () {
		 BookServiceI service = new BookService();
		 bookList = service.findAll();
	}

	public void onRowSelect(SelectEvent event) {}
	
	public Book getSelectedBook() {
		return selectedBook;
	}
	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
}
