package com.raghav.ecommerce.service;

import com.raghav.ecommerce.exception.UserException;
import com.raghav.ecommerce.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;


}
