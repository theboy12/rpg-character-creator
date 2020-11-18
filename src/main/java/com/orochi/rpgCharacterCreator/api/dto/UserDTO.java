package com.orochi.rpgCharacterCreator.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
	
	private String email;
	private String nickname;
	private String password;
	
}
