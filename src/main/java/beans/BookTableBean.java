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
	
	private BookServiceI service = null;
	private Book selectedBook = null;
	private List<Book> bookList = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct () {
		 service = new BookService();
		 refresh();
	}

	public void onRowSelect(SelectEvent event) {}
	
	public String deleteBook() {
		if(selectedBook != null) {
			service.delete(selectedBook);
			refresh();
		}
		return "bookList";
	}
	
	public void refresh() {
		bookList = service.findAll();
	}
	
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
