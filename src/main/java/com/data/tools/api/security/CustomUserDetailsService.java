package com.data.tools.api.security;


import com.data.tools.api.exceptions.Exceptions;
import com.data.tools.api.exceptions.GlobalException;
import com.data.tools.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.data.tools.api.entity.User;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Override
	public UesrDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = this.userRepository
				.findByEmail(email).orElseThrow(() -> GlobalException.throwEx(Exceptions.USER_NOT_FOUND, "User not found for the email:" + email));
		
		return new UesrDetailsImpl(user);
	}

}
