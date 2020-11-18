package com.orochi.rpgCharacterCreator.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;
import com.orochi.rpgCharacterCreator.exceptions.CharacterCreatorException;
import com.orochi.rpgCharacterCreator.persis.repository.CharaRepository;
import com.orochi.rpgCharacterCreator.services.CharaServices;

@Service
public class CharaServiceImpl implements CharaServices {

	CharaRepository charaRepo;
	
	@Autowired
	public CharaServiceImpl(CharaRepository charaRepo) {
		super();
		this.charaRepo = charaRepo;
		
	}
	
	@Override
	@Transactional
	public CharaEntity createCharacter(CharaEntity character) {
		validate(character);
		return charaRepo.save(character);
	}

	@Override
	@Transactional
	public CharaEntity updateCharacter(CharaEntity character) {
		Objects.requireNonNull(character.getID());
		validate(character);
		return charaRepo.save(character);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CharaEntity> searchCharacter(CharaEntity characterFilter) {
		
		Example example = Example.of(characterFilter, ExampleMatcher
				.matching()
				.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return charaRepo.findAll(example);
	}

	@Override
	public void deleteCharacter(CharaEntity character) {
		
		Objects.requireNonNull(character.getID());
		charaRepo.delete(character);
		
	}
	
	@Override
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
