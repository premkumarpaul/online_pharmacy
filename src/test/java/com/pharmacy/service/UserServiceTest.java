package com.pharmacy.service;

 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.pharmacy.service.UserService;
 
import com.pharmacy.model.UserBean;
import com.pharmacy.repo.UserRepository;

 @SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService service;
	@MockBean
	private UserRepository repository;
	@Test
	 void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream.
				of(new  UserBean("Dilip", "Danile", "yadavdilip9865", "98564","123","user"), new  UserBean("sandeep", "hksingh@", "prem", "9856","123","Admin")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}
	@Test
	 void getUserbyidTest() {
		when(repository.findAll()).thenReturn(Stream.
				of(new  UserBean("Dilip", "Danile", "yadavdilip9865", "98564","123","user")).collect(Collectors.toList()));
		assertEquals(1, service.getUsers().size());
	}
	@Test
	 void getAllUserTest() {
		when(repository.findAll()).thenReturn(Stream.of(new UserBean("Sandeep","prem","sandy@123","98568965","100","Admin"))
				.collect(Collectors.toList()));
		assertEquals(1, service.getUsers().size());
	}
	@Test
	 void deleteUserByIdTest() {
		int id=1;
		UserBean user=new UserBean("Dilip", "Danile", "yadavdilip9865", "98564","123","user");
		service.deleteByUserId(1);
		verify(repository,times(1)).deleteById(1);
	}
	@Test
	 void saveUserTest() {
		UserBean user = new UserBean("Dilip", "Danile", "yadavdilip9865", "98564","123","user");
		when(repository.save(user)).thenReturn(user);
		
		assertEquals(user,service.addUser(user));
	}
	

}