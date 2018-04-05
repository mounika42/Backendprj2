package com.niit.dao;

import java.util.List;

import com.niit.model.Notification;

public interface NotificationDAO {

	List<Notification> getNotificationNotViewed(String email);

	Notification getNotification(int id);

	void updateNotification(int id);
}
