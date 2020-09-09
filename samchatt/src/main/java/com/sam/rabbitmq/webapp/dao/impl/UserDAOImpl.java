package com.sam.rabbitmq.webapp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sam.rabbitmq.webapp.dao.UserDAO;
import com.sam.rabbitmq.webapp.entity.User;
import com.sam.rabbitmq.webapp.response.MessageDetails;
import com.sam.rabbitmq.webapp.response.UserChatHistoryDetails;
import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

@Repository
@PropertySource(value = { "classpath:queries.properties" })
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	Environment env;
	
	public void saveUser(Session session, User user) {
		session.save(user);
	}
	public void updateUser(Session session, User user) {
		session.update(user);
	}
	public UserRegistrationTO userLogin(Session session, Long contactNumber, String emailId, String password) {
		SQLQuery query = session.createSQLQuery(env.getProperty("AUTHENTICATE_USER"));
		query.setParameter(0, emailId);
		query.setParameter(1, contactNumber);
		query.setParameter(2, password);
		query.addScalar("userId", LongType.INSTANCE)
				.addScalar("name", StringType.INSTANCE)
				.addScalar("emailId", StringType.INSTANCE)
				.addScalar("mobileNumber", LongType.INSTANCE)
				.addScalar("sessionId", StringType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(UserRegistrationTO.class));
		return (UserRegistrationTO) query.uniqueResult();
	}
	public boolean checkDupMobileNumber(Session session, Long mobileNumber) {
		Query query = session.createQuery(env.getProperty("FETCH_USER_BY_MOBILE_NUMBER_HQL"));
		query.setParameter("mobileNum", mobileNumber);
		User user =  (User) query.uniqueResult();
		return user != null? true: false;
	}
	public boolean checkDupEmailId(Session session, String emailId) {
		Query query = session.createQuery(env.getProperty("FETCH_USER_BY_EMAIL_ID_HQL"));
		query.setParameter("emailId", emailId);
		User user =  (User) query.uniqueResult();
		return user != null? true: false;
	}
	public User getUserById(Session session, int userId) {
		Query query = session.createQuery(env.getProperty("FETCH_USER_BY_ID_HQL"));
		query.setParameter("userId", userId);
		User user =  (User) query.uniqueResult();
		return user;
	}
	public User getUserByMobileNumberOrEmail(Session session, long contactNumber, String emailId) {
		Query query = session.createQuery(env.getProperty("FETCH_USER_BY_MOBILE_NUM_EMAIL_ID_HQL"));
		query.setParameter(0, contactNumber)
				.setParameter(1, emailId);
		User user =  (User) query.uniqueResult();
		return user;
	}
	public User getUserBySessionId(Session session, String sessionId) {
		Query query = session.createQuery(env.getProperty("FETCH_USER_BY_SESSION_ID_HQL"));
		query.setParameter("sessionId", sessionId);
		User user =  (User) query.uniqueResult();
		return user;
	}
	@SuppressWarnings("unchecked")
	public List<UserChatHistoryDetails> fetchUserChatHistoryDetailsByUserId(Session session, Integer userId) {
		SQLQuery query = session.createSQLQuery(env.getProperty("FETCH_USER_CHAT_HISTORY_DETAILS_BY_USER_ID"));
		query.setParameter("userId", userId);
		query.addScalar("name", StringType.INSTANCE)
				.addScalar("mobileNumber", LongType.INSTANCE)
				.addScalar("lastMessageText", StringType.INSTANCE)
				.addScalar("lastMessageDate", StringType.INSTANCE)
				.addScalar("unReadMessagesCount", IntegerType.INSTANCE)
				.addScalar("userId",LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(UserChatHistoryDetails.class));
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<MessageDetails> fetchMessagesByUserIds(Session session, Integer senderId, Integer receiverId, boolean senderMessages) {
		SQLQuery query = session.createSQLQuery(env.getProperty("FETCH_MESSAGES_BY_SENDER_RECEIVER_USER_ID"));
		query.setParameter("sender", senderId);
		query.setParameter("receiver", receiverId);
		query.setBoolean("senderMessages", senderMessages);
		query.addScalar("messageId", IntegerType.INSTANCE)
				.addScalar("messageText", StringType.INSTANCE)
				.addScalar("messageRecivedDate", StringType.INSTANCE)
				.addScalar("messageSentDate", StringType.INSTANCE)
				.addScalar("multiMediaUrl",StringType.INSTANCE)
				.addScalar("senderMessages", BooleanType.INSTANCE)
				.addScalar("readStatus", BooleanType.INSTANCE)
				.addScalar("readStatusCode", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(MessageDetails.class));
		return query.list();
	}
}
