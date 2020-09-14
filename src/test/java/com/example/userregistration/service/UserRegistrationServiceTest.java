package com.example.userregistration.service;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@RunWith(SpringRunner.class)
class UserRegistrationServiceTest {
	
	@InjectMocks
	private UserRegistrationService userRegistrationService;
	
	@Mock
	private UserRepository repo;
	
	@Test
	void createUser() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("firstname" , "Shankar");
		mockReq.put("lastname" , "rsn");
		mockReq.put("email" , "rshankar1294@gmail.com");
		
		User mockreq = new User();
		mockreq.setEmail("rshankar1294@gmail.com");
		mockreq.setFirstname("Shankar");
		mockreq.setLastname("rsn");
		mockreq.setReg_date(null);	

		Mockito.when(repo.save(mockreq)).thenReturn(new User());
		User user = userRegistrationService.createUser(mockReq);
		assertTrue(user == null);
	}
	
	@Test
	void updateUser() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("firstname" , "Shankar");
		mockReq.put("lastname" , "rsn");
		mockReq.put("email" , "rshankar1294@gmail.com");
		
		User mockreq = new User();
		mockreq.setEmail("rshankar1294@gmail.com");
		mockreq.setFirstname("Shankar");
		mockreq.setLastname("rsn");
		mockreq.setReg_date(null);	
		
		User mockresp = new User();
		mockresp.setId(1);
		mockresp.setEmail("rshankar1294@gmail.com");
		mockresp.setFirstname("Shankar");
		mockresp.setLastname("rsn");
		mockresp.setReg_date(null);
		Optional<User> user = Optional.of(mockresp);
		
		Mockito.when(repo.findById(1)).thenReturn(user);
		Mockito.when(repo.save(mockreq)).thenReturn(new User());	
		User users = userRegistrationService.updateUser("1",mockReq);
		assertTrue(users == null);
	}

}
