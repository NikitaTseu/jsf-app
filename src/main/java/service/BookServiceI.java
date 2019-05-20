package service;

import java.util.List;

import objects.Book;

public interface BookServiceI {
	public List<Book> findAll();
	public int generateNewId();
	public Book add(Book u);
}
