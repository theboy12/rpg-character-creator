package com.orochi.rpgCharacterCreator.services;

import java.util.List;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;

public interface CharaServices {

	CharaEntity createCharacter(CharaEntity character);
	
	CharaEntity updateCharacter(CharaEntity character);
	
	List<CharaEntity> searchCharacter(CharaEntity characterFilter);
	
	void deleteCharacter(CharaEntity character);
}
