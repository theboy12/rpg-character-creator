package com.orochi.rpgCharacterCreator.api.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orochi.rpgCharacterCreator.api.dto.UserDTO;
import com.orochi.rpgCharacterCreator.entities.UserEntity;
import com.orochi.rpgCharacterCreator.exceptions.LoginErrorException;
import com.orochi.rpgCharacterCreator.exceptions.UserAlreadyExistsException;
import com.orochi.rpgCharacterCreator.services.UserServices;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	private UserServices service;
	
	public UserResource(UserServices service) {
		this.service = service;
		
	}
	
	@PostMapping("/authentication")
	public ResponseEntity authentication(@RequestBody UserDTO dto) {
		try {
			UserEntity userLogin = service.login(dto.getNickname(), dto.getPassword());
			return ResponseEntity.ok(userLogin);
		}catch(LoginErrorException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
	
	LocalDate date = LocalDate.now();
	
	@PostMapping
	public ResponseEntity save( @RequestBody UserDTO dto) {
		
		UserEntity user = UserEntity
				.builder()
				.nickname(dto.getNickname())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.date(date)
				.build();
		
		try {
			UserEntity savedUser = service.signIn(user);
			return new ResponseEntity(savedUser, HttpStatus.CREATED);
			
		}catch (UserAlreadyExistsException e){
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
		
	}

}
