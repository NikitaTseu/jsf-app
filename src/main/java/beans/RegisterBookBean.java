package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import objects.Book;
import service.BookService;
import service.BookServiceI;

@ManagedBean(name = "RegisterBookBean")
@SessionScoped
public class RegisterBookBean {
	
	private int id = 0;
	private String isbn = "";
	private String title = "";
	private String author = "";
	private String genre = "";
	
	private Book book = null;
	
	BookServiceI service = new BookService();
	
	public RegisterBookBean() {}
	
	public void refresh() {
		id = 0;
		isbn = "";
		title = "";
		author = "";
		genre = "";
	}

	
	@ManagedProperty("#{BookTableBean}")
	private BookTableBean tableBean;
	
	public String doIt() {
		book = new Book(service.generateNewId(), isbn, title, author, genre);
		service.add(book);
		refresh();
		tableBean.refresh();
		return "registerBook";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public BookTableBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(BookTableBean tableBean) {
		this.tableBean = tableBean;
	}
	
	
	
}
