package com.orochi.rpgCharacterCreator.exceptions.validation;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;
import com.orochi.rpgCharacterCreator.exceptions.CharacterCreatorException;

public class CharacterValidation {

	CharaEntity character;
	
	public CharacterValidation(CharaEntity character) {
		super();
		this.character = character;
	}

	public void validate(CharaEntity character) {
		
		if(character.getName() == null || character.getName().trim().equals("")) 
		{			
			throw new CharacterCreatorException("Invalid character name!");			
		}		
		if(character.getName().length() > 40)
		{
			throw new CharacterCreatorException("This name is too long!");
		}	
		if(character.getCharClass() == null || character.getCharClass().trim().equals("")) 
		{
			throw new CharacterCreatorException("Invalid class name!");
		}
		if(character.getCharClass().length() > 100) 
		{
			throw new CharacterCreatorException("Class name is too long!");
		}
		if(character.getVit() == null || character.getVit() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
		if(character.getAgi() == null || character.getAgi() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
		if(character.getDex() == null || character.getDex() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
		if(character.getStr() == null || character.getStr() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
		if(character.getIntelli() == null || character.getIntelli() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
		if(character.getLuk() == null || character.getLuk() < 0) 
		{
			throw new CharacterCreatorException("Invalid attribute value!");
		}
	}
	
}
