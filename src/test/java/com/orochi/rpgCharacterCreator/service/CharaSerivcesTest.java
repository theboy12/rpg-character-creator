package com.orochi.rpgCharacterCreator.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;
import com.orochi.rpgCharacterCreator.exceptions.CharacterCreatorException;
import com.orochi.rpgCharacterCreator.persis.repository.CharaRepository;
import com.orochi.rpgCharacterCreator.services.CharaServices;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class CharaSerivcesTest {

	@MockBean
	CharaRepository repo;
	
	@SpyBean
	CharaServices service;
	
	@Test
	//Validate if the character was created successfully(must return exception)
	public void TryCreatingCharacter() {
		
		//cenary
		CharaEntity chara = new CharaEntity();
		
		Throwable erro = Assertions.catchThrowable(() -> service.validate(chara));
		Assertions.assertThat(erro).isInstanceOf(CharacterCreatorException.class).hasMessage("Invalid character name!");
		
		
	}
	
	
	
}
