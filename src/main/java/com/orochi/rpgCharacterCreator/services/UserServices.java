package com.orochi.rpgCharacterCreator.services;

import com.orochi.rpgCharacterCreator.entities.UserEntity;

public interface UserServices {

	UserEntity login(String nickname, String password);
	
	UserEntity signIn(UserEntity user);
	
	void verifyEmail(String email);
	
	void nickname(String nickname);
}
