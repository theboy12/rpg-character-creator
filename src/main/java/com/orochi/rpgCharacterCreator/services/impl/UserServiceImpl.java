package com.orochi.rpgCharacterCreator.services.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orochi.rpgCharacterCreator.entities.UserEntity;
import com.orochi.rpgCharacterCreator.exceptions.LoginErrorException;
import com.orochi.rpgCharacterCreator.exceptions.UserAlreadyExistsException;
import com.orochi.rpgCharacterCreator.persis.repository.UserRepository;
import com.orochi.rpgCharacterCreator.services.UserServices;

@Service
public class UserServiceImpl implements UserServices{


	private UserRepository repoUser;

	@Autowired
	public UserServiceImpl(UserRepository repoUser) {
		super();
		this.repoUser = repoUser;
		
	}

	@Override
	public UserEntity login(String nickname, String password) {
		
		Optional<UserEntity> user = repoUser.findByNickname(nickname);
		
		if(!user.isPresent()) {
			throw new LoginErrorException("Invalid nickname!"); 
		}
		
		if(!user.get().getPassword().equals(password)) {
			throw new LoginErrorException("Invalid password!");
		}
		return user.get();
	}

	@Override
	@Transactional
	public UserEntity signIn(UserEntity user) {
		verifyEmail(user.getEmail());
		verifyNickname(user.getNickname());
		
		
		return repoUser.save(user);
	}

	@Override
	public void verifyEmail(String email) {
		
		boolean exists = repoUser.existsByEmail(email);
		
		if(exists) {
			throw new UserAlreadyExistsException("This e-mail has already been registered!");
		}
		
	}

	@Override
	public void verifyNickname(String nickname) {
		
		boolean exists = repoUser.existsByNickname(nickname);
		
		if(exists) {
			throw new UserAlreadyExistsException("This nickname is already being used!");
		}
		
	}
	
	
	
	
}
