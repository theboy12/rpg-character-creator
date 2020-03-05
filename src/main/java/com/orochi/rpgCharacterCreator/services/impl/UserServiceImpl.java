package com.orochi.rpgCharacterCreator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.orochi.rpgCharacterCreator.entities.UserEntity;
import com.orochi.rpgCharacterCreator.exceptions.UserAlreadyExistsException;
import com.orochi.rpgCharacterCreator.persis.repository.UserRepository;
import com.orochi.rpgCharacterCreator.services.UserServices;

public class UserServiceImpl implements UserServices{


	UserRepository repoUser;

	@Autowired
	public UserServiceImpl(UserRepository repoUser) {
		super();
		this.repoUser = repoUser;
		
	}

	@Override
	public UserEntity login(String nickname, String password) {
		
		return null;
	}

	@Override
	public UserEntity signIn(UserEntity user) {
		
		return null;
	}

	@Override
	public void verifyEmail(String email) {
		
		boolean exists = repoUser.existsByEmail(email);
		
		if(exists) {
			throw new UserAlreadyExistsException("This e-mail has already been registered!");
		}
		
	}

	@Override
	public void nickname(String nickname) {
		
		boolean exists = repoUser.existsByNickname(nickname);
		
		if(exists) {
			throw new UserAlreadyExistsException("This nickname is already being used!");
		}
		
	}
	
	
	
	
}
