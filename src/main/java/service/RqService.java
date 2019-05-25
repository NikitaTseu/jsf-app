package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import objects.Request;

public class RqService implements RqServiceI {
	public RqService() {
	}
	
	private EntityManager em = Persistence.createEntityManagerFactory("lib_1").createEntityManager();
	
	public List<Request> findAll() {
        TypedQuery<Request> query = em.createNamedQuery("Request.findAll", Request.class);
        List<Request> requests = query.getResultList();
        return requests;
	}
	
	public int generateNewId() {
		TypedQuery<Integer> q = em.createNamedQuery("Request.maxId", Integer.class);
		return q.getSingleResult() + 1;
	}
	
	public Request add(Request r) {
		em.getTransaction().begin();
		em.merge(r);
        em.getTransaction().commit();
		return r;
	}
	
	public List<Request> findByUser(int user_id) {
		List<Request> rq = new ArrayList<>();
		try {
	        TypedQuery<Request> query = em.createNamedQuery("Request.findByUser", Request.class);
	        query.setParameter("idParam", user_id);
	        rq = query.getResultList();
		}
		catch(NoResultException e) {}
        return rq;
	}
}
