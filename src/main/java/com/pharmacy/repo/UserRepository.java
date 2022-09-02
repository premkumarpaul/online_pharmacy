package com.pharmacy.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharmacy.model.UserBean;
/** THIS INTERFACE IS FOR USER REPOSITORY **/
public interface UserRepository extends  JpaRepository<UserBean,Integer>{

	Optional<UserBean> findByUsername(String username);
	
	public UserBean findByEmail(String email); 
	public UserBean findByPhoneNumber(String phoneNumber); 
	
	@Query("select s.role from UserBean s where username=?1")
	Object getRole(String username);
	
	@Query("select s from UserBean s where username=?1 and password=?2")
	Object authenticate(String username, String password);
	
	Optional<UserBean> findUserBeanById(int id);
	
	int deleteByUsernameAndPassword(String username, String password);
	
	void deleteById(Integer id);
}
