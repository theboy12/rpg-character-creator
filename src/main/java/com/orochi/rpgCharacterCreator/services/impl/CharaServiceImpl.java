package com.orochi.rpgCharacterCreator.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.transaction.annotation.Transactional;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;
import com.orochi.rpgCharacterCreator.exceptions.validation.CharacterValidation;
import com.orochi.rpgCharacterCreator.persis.repository.CharaRepository;
import com.orochi.rpgCharacterCreator.services.CharaServices;

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
		new CharacterValidation(character);
		return charaRepo.save(character);
	}

	@Override
	@Transactional
	public CharaEntity updateCharacter(CharaEntity character) {
		Objects.requireNonNull(character.getID());
		new CharacterValidation(character);
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

}
