package com.orochi.rpgCharacterCreator.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orochi.rpgCharacterCreator.entities.UserEntity;
import com.orochi.rpgCharacterCreator.persis.repository.UserRepository;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTests {
	
	@Autowired
	UserRepository repo;
	
	@Test
	public void VerifyUserEmail() {
	
		//Scenary
		UserEntity email = UserEntity.builder().email("email@email.com").build();
		repo.save(email);
		
		//Action
		boolean exists1 = repo.existsByEmail("email@email.com");
		
		
		//Test
		Assertions.assertThat(exists1).isTrue();
		
		
	}
	

	@Test
	public void VerifyUserNickname() {
		
		//Scenary
		UserEntity nickname = UserEntity.builder().nickname("nickname").build();
		repo.save(nickname);
		
		//Action
		boolean exists2 = repo.existsByNickname("nickname");	
		
		//Test
		Assertions.assertThat(exists2).isTrue();
		
	}
	

}
