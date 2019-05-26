package service;

import java.util.List;

import objects.Book;

public interface BookServiceI {
	public List<Book> findAll();
	public Book findById(int id);
	public int generateNewId();
	public Book add(Book b);
	public void delete(Book b);
}
