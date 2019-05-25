package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import objects.Book;

public class BookService implements BookServiceI {
	public BookService() {
	}
	
	private EntityManager em = Persistence.createEntityManagerFactory("lib_1").createEntityManager();
	
	public List<Book> findAll() {
        TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
        List<Book> books = query.getResultList();
        return books;
	}
	
	public int generateNewId() {
		TypedQuery<Integer> q = em.createNamedQuery("Book.maxId", Integer.class);
		return q.getSingleResult() + 1;
	}
	
	public Book add(Book b) {
		em.getTransaction().begin();
		em.merge(b);
        em.getTransaction().commit();
		return b;
	}
	
	public void delete(Book b) {
		em.getTransaction().begin();
		em.remove(b);
        em.getTransaction().commit();
	}
}
