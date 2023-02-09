package com.sam.rabbitmq.webapp.dao;

import java.util.List;

import org.hibernate.Session;

import com.sam.rabbitmq.webapp.chatt.to.ContactDTO;
import com.sam.rabbitmq.webapp.entity.User;
import com.sam.rabbitmq.webapp.response.MessageDetails;
import com.sam.rabbitmq.webapp.response.UserChatHistoryDetails;
import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

public interface UserDAO {
	void saveUser(Session session, User user);
	void updateUser(Session session, User user);
	UserRegistrationTO userLogin(Session session, Long contactNumber, String emailId, String Password);
	boolean checkDupMobileNumber(Session session, Long mobileNumber);
	boolean checkDupEmailId(Session session, String emailId) ;
	User getUserById(Session session, int userId);
	User getUserByMobileNumberOrEmail(Session session, long contactNumber, String emailId);
	User getUserBySessionId(Session session, String sessionId);
	List<UserChatHistoryDetails> fetchUserChatHistoryDetailsByUserId(Session session, Integer id);
	List<MessageDetails> fetchMessagesByUserIds(Session session, Integer recieverUserID, Integer userId, boolean senderMessages);
	List<ContactDTO> getContacts(Session session, Integer id);
	List<UserChatHistoryDetails> fetchUserChatHistoryDetailsByUserIdAndRecvId(Session session, Integer id,
			Integer recId);
}
