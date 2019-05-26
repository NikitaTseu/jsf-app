package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	public Book findById(int id) {
		Book book = null;
		try {
	        TypedQuery<Book> query = em.createNamedQuery("Book.findById", Book.class);
	        query.setParameter("idParam", id);
	        book = query.getSingleResult();
		}
		catch(NoResultException e) {}
        return book;
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
