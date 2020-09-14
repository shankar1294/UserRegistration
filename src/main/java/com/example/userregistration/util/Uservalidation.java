package com.example.userregistration.util;

import java.util.Map;

public class Uservalidation {
	
	public void validateRequest(Map<String, String> req) throws Exception {
		if(req.get("firstname") == null || req.get("lastname") == null || req.get("email") == null) {
			throw new Exception("input value cannot be null");
		} else if(req.get("firstname").equals("") || req.get("lastname").equals("") || req.get("email").equals("")) {
			throw new Exception("input value cannot be empty");
		}
		
	}

}
