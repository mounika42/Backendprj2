 package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Users;
@Repository
public class UsersDaoImpl implements UsersDao
{
	
@Autowired
private SessionFactory sessionFactory;

public void registration(Users users) 
{
	Session session=sessionFactory.openSession();

	Transaction tx=session.beginTransaction();
	users.setEnabled(true);
	users.setOnline(false);
	session.save(users);
	tx.commit();
	session.flush();
	session.close();
}
	
	public List<Users> getAllUsers() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from users");
		List<Users> users=query.list();
		session.close();
		return users;
	}
	
	public Users login(Users users)
	{
		System.out.println(users.getUsername()+""+users.getPassword());
		Session session=sessionFactory.openSession();
		System.out.println("hello");
		Query query=session.createQuery("from Users where username=? and password=? and enabled=?");
		System.out.println("hell");
		query.setString(0, users.getUsername()); //for assigning the values to parameter username
		query.setString(1, users.getPassword());
		query.setBoolean(2, true);
		Users validUsers=(Users)query.uniqueResult();
		session.close();
		System.out.println("Dao completed");
		return validUsers;
		
	}
	
	public Users updateUser(Users validUser)
	{
		Session session=sessionFactory.openSession();
		session.update(validUser);
		session.flush();
		session.close();
		return validUser;
	}

	public Users getUserByUsername(int id)
	{
		Session session=sessionFactory.openSession();
		Users users=(Users)session.get(Users.class, id);
		session.close();
		return users;
	}

	
}
