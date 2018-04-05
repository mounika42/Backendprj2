package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.User;

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO {

	public FriendDAOImpl() {

		System.out.println("FriendDAOImpl");
	}

	@Autowired
	private SessionFactory sessionFactory;

	public List<User> suggestedUsers(String email) {
		Session session = sessionFactory.openSession();
		SQLQuery sqlQuery = session.createSQLQuery(
				"select * from user_table where email in " + "(select email from user_table where email!=? " + "minus "
						+ "(select fromId_email from friend where toId_email=?" + "union "
						+ "select toId_email from friend where fromId_email=? ))");
		sqlQuery.setString(0, email);
		sqlQuery.setString(1, email);
		sqlQuery.setString(2, email);
		sqlQuery.addEntity(User.class);
		List<User> suggestedUsersList = sqlQuery.list();
		return suggestedUsersList;

	}

	public void addFriend(Friend friend) {

		Session session = sessionFactory.getCurrentSession();
		session.save(friend);

	}

	public List<Friend> pendingRequests(String email) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where status=? and toId.email=?");
		query.setString(1, email);
		query.setCharacter(0, 'p');
		List<Friend> pendingRequests = query.list();
		return pendingRequests;
	}

	public void acceptRequest(Friend request) {

		Session session = sessionFactory.getCurrentSession();
		request.setStatus('a');
		session.update(request);
	}

	public void deleteRequest(Friend request) {

		Session session = sessionFactory.getCurrentSession();
		session.delete(request);
	}

	public List<Friend> listOfFriends(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select  f.toId from Friend f where f.fromId.email=? and f.status=?");
		query1.setString(0, email);
		query1.setCharacter(1, 'a');
		List<Friend> friendsList1 = query1.list();
		Query query2 = session.createQuery("select f.fromId from Friend f where f.toId.email=? and f.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'a');
		List<Friend> friendsList2 = query2.list();
		friendsList1.addAll(friendsList2);
		return friendsList1;
	}

}
