package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "select b from Book b"),
    @NamedQuery(name = "Book.findById", query = "select distinct b from Book b where b.id = :idParam"),
    @NamedQuery(name = "Book.maxId", query = "select MAX(b.id) from Book b")
})
@SuppressWarnings("serial")
public class Book implements Serializable {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "isbn")
	private String isbn = "00-0000-00";

	@Column(name = "title")
	private String title = "EMPTY";

	@Column(name = "author")
	private String author = "EMPTY";

	@Column(name = "genre")
	private String genre = "EMPTY";

	@OneToMany(mappedBy = "book")
	private List<Request> request = new ArrayList<Request>();
	
	public Book() {
		this.id = 0;
		this.isbn = "00-0000-00";
		this.title = "EMPTY";
		this.author = "EMPTY";
		this.genre = "EMPTY";
	}

	public Book(int id, String isbn, String title, String author, String genre) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public String toString() {
		return author + " '" + title + "'";
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

}
