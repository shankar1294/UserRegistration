package com.example.userregistration.controller;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.userregistration.model.User;
import com.example.userregistration.service.UserRegistrationService;
import com.example.userregistration.util.Uservalidation;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserRegistrationController.class)
@WithMockUser
class UserRegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRegistrationService userRegistrationService;

	@Test
	void createUser() throws Exception {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("firstname" , "Shankar");
		mockReq.put("lastname" , "rsn");
		mockReq.put("email" , "rshankar1294@gmail.com");

		User mockresp = new User();
		mockresp.setId(1);
		mockresp.setEmail("rshankar1294@gmail.com");
		mockresp.setFirstname("Shankar");
		mockresp.setLastname("rsn");
		mockresp.setReg_date(null);

		String exampleCourseJson = "{\"firstname\":\"Shankar\",\"lastname\":\"rsn\",\"email\":\"rshankar1294@gmail.com\"}";
		String expected = "{\"id\":1,\"firstname\":\"Shankar\",\"lastname\":\"rsn\",\"email\":\"rshankar1294@gmail.com\",\"reg_date\":null}";

		Mockito.when(
				userRegistrationService.createUser(mockReq)).thenReturn(mockresp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("respones ::"+result.getResponse().getContentAsString());

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}

	@Test
	void updateUser() throws Exception {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("firstname" , "Shankar");
		mockReq.put("lastname" , "rsn");
		mockReq.put("email" , "rshankar1294@gmail.com");

		User mockresp = new User();
		mockresp.setId(1);
		mockresp.setEmail("rshankar1294@gmail.com");
		mockresp.setFirstname("Shankar");
		mockresp.setLastname("rsn");
		mockresp.setReg_date(null);

		String exampleCourseJson = "{\"firstname\":\"Shankar\",\"lastname\":\"rsn\",\"email\":\"rshankar1294@gmail.com\"}";
		String expected = "{\"id\":1,\"firstname\":\"Shankar\",\"lastname\":\"rsn\",\"email\":\"rshankar1294@gmail.com\",\"reg_date\":null}";
		Mockito.when(
				userRegistrationService.updateUser("1", mockReq)).thenReturn(mockresp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user-update/1")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println("respones ::"+result.getResponse().getContentAsString());

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);

	}

	@Test
	public void checkUserValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , "Shankar");
		req.put("lastname" , "rsn");
		req.put("email" , "");
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;
		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
	
	@Test
	public void checklastNameValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , "Shankar");
		req.put("lastname" , "");
		req.put("email" , "ssr");
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;
		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
	
	@Test
	public void checkFirstNameValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , "");
		req.put("lastname" , "dd");
		req.put("email" , "ssr");
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;
		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
	
	@Test
	public void checkEmptyUserValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , "sfs");
		req.put("lastname" , "dddd");
		req.put("email" , null);
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;

		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
	
	@Test
	public void checkEmptylastNameValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , "sfs");
		req.put("lastname" , null);
		req.put("email" , "dddd");
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;

		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
	
	@Test
	public void checkEmptyFirstNameValidation() {

		Map<String, String> req = new HashMap<String, String>();
		req.put("firstname" , null);
		req.put("lastname" , "dddd");
		req.put("email" , "dddd");
		Uservalidation uservalidation = new Uservalidation();
		Throwable e = null;

		try {
			uservalidation.validateRequest(req);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);
	}
}
