package service;

import java.util.List;

import objects.Request;

public interface RqServiceI {
	public List<Request> findAll();
	public int generateNewId();
	public Request add(Request r);
	public Request updateClosedStatus(Request r);
	public Request prolong(Request r);
	public List<Request> findByUser(int user_id);
}