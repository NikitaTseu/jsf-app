package service;

import java.util.List;

import objects.User;

public interface UserServiceI {
	public List<User> findAll();
	public User findByLogin(String loginInput);
	public int generateNewId();
	public User add(User u);
}
