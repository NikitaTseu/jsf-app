package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import objects.User;

public class UserService implements UserServiceI {
	public UserService() {
	}
	
	private EntityManager em = Persistence.createEntityManagerFactory("lib_1").createEntityManager();
	
	public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        List<User> users = query.getResultList();
        return users;
	}
	
	public User findByLogin(String loginInput) {
		User user = null;
		try {
	        TypedQuery<User> query = em.createNamedQuery("User.findByLogin", User.class);
	        query.setParameter("loginParam", loginInput);
	        user = query.getSingleResult();
		}
		catch(NoResultException e) {}
        return user;
	}
	
	public int generateNewId() {
		TypedQuery<Integer> q = em.createNamedQuery("User.maxId", Integer.class);
		return q.getSingleResult() + 1;
	}
	
	public User add(User u) {
		em.getTransaction().begin();
		em.merge(u);
        em.getTransaction().commit();
		return u;
	}
}
