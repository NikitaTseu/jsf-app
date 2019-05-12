package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import objects.Book;

@ManagedBean(name = "MainBean")
@SessionScoped
public class MainBean {
	public MainBean() {
	}

	private Book newBook = new Book(-1, "NULL", "NULL");

	private ArrayList<Book> books = new ArrayList<>();

	public String display() {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/lib?useUnicode=true&"
						+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"nikita", "A1s2d3f4")) {
			Statement statement = null;
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM books");
			books.clear();
			while (result.next()) {
				books.add(new Book(result.getInt("id"), result.getString("name"), result.getString("author")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "bookshelf";
	}

	public Book getNewBook() {
		return newBook;
	}

	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String add() {
		newBook.setAuthor("");
		newBook.setName("");
		return "add";
	}
	
	public String addToDB() {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/lib?useUnicode=true&"
						+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"nikita", "A1s2d3f4")) {
			Statement statement = null;
			statement = connection.createStatement();
			statement.executeUpdate("INSERT books(name, author) VALUES ('" + newBook.getName() + "', '" + newBook.getAuthor() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return display();
	}

	public String toMain() {
		return "index";
	}
}
