package com.pharmacy.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.UserBean;
import com.pharmacy.repo.UserRepository;

/**
 * @author PREMKUMAR PAUL
 *
 */

@Service
public class UserService {

	@Autowired
	private final UserRepository userRepo;

	
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
    
	/**
	 * 
	 *
	 * FOR ADD USER 
	 * @param user
	 * 
	 */
	public UserBean addUser(UserBean user) {
		return userRepo.save(user);
	}
	/**
	 * 
	 *
	 * TO GET USER DETAILS
	 */
	public List<UserBean> getUsers() {
		return userRepo.findAll();
	}

	/**
	 * 
	 *
	 * TO GET USER DETAILS BY USERSNAME
	 * @param String username
	 */
	public UserBean getUser(String username) {
		return userRepo.findByUsername(username).get();
	}

	/**
	 *
	 *
	 * TO GET ROLE DETAILS FOR USER
	 * @param String username
	 */
	public Object getRole(String username) {
		return userRepo.getRole(username);
	}

	/**
	 * @author PREMKUMAR PAUL
	 *
	 * TO LOGIN USER VIA USERNAME AND PASSWORD
	 * @param String username
	 * @param String password
	 */
	public Object auth(String username, String password) {
		return userRepo.authenticate(username, password);
	}
	/**
	 * @author PREMKUMAR PAUL
	 *
	 * TO UPDATE USER BY USER ID
	 */
	public UserBean updateUser(UserBean user, int id) {
		user.setId(id);
		return userRepo.save(user);
	}

	/**
	 * 
	 *TO UPDATE USER BY USERNAME AND PASSWORD
	 * @param String username
	 * @param String password
	 */
	public UserBean updateUser(UserBean user, String username, String password) {
		UserBean olduser = (UserBean) userRepo.authenticate(username, password);
		user.setId(olduser.getId());
		return userRepo.save(user);
	}
	/**
	 * 
	 *TO DELETE USER BY USERNAME AND PASSWORD
	 * @param String username
	 * @param String password
	 */

	@Transactional
	public String deleteByUsernameAndPass(String username, String password) {
		int res = userRepo.deleteByUsernameAndPassword(username, password);
		if (res == 1)

			return "SUCCESS";
		else
			return "FAILED";
	}
	/**
	 * 
	 *TO DELETE USER BY USERID 
	 * @param int id
	 * 
	 */
	@Transactional
	public void deleteByUserId(int id) {
		userRepo.deleteById(id);
	}

	public String decodeToken(String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];
		
		return new String(Base64.decodeBase64(payload), StandardCharsets.UTF_8);
	}
	
	
	public Boolean checkMobileNoExist(String phoneNumber) { 
		  
		  return this.userRepo.findByPhoneNumber(phoneNumber)!=null; 
		   
	  } 
	 
	public Boolean checkUsernameExist(String username) { 
		 
		  return this.userRepo.findByUsername(username)!=null; 
		  
	  } 
	 
	public Boolean checkMailExist(String mail) { 
		  
		  return this.userRepo.findByEmail(mail)!=null; 
	  } 
}
