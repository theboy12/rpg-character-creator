package com.orochi.rpgCharacterCreator.persis.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orochi.rpgCharacterCreator.entities.UserEntity;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserRepositoryTest {
	
	@Autowired
	UserRepository repo;
	
	@Test
	public void SerachingIfEmailAlreadyExist() {
		
		//cenary
		repo.save(userBuild());
		
		//action
		boolean exists = repo.existsByEmail("email@email.com");
		
		//verification
		Assertions.assertThat(exists).isTrue();
		
	}
	
	@Test
	public void SerachingIfEmailNotExist() {
		
		//cenary
		repo.deleteAll();		
				
		//action
		boolean exists = repo.existsByEmail("email@email.com");
				
		//verification
		Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void SerachingIfNicknameAlreadyExist() {
		
		//cenary
		repo.save(userBuild());
		
		
		//action
		boolean exists = repo.existsByNickname("teste");
		
		//verification
		Assertions.assertThat(exists).isTrue();
	}

	@Test
	public void SearchingIfNicknameNotExist() {
		
		//cenary
		repo.deleteAll();		
				
		//action
		boolean exists = repo.existsByNickname("teste");
				
		//verification
		Assertions.assertThat(exists).isFalse();
	}
	
	@Test
	public void mustPersistAUserOnTheDatabase() {
		//cenary
		repo.deleteAll();	
		
		//action
		UserEntity savedUser = repo.save(userBuild());
		
		//verification
		Assertions.assertThat(savedUser.getId()).isNotNull();
			
	}
	
	@Test
	public void mustSearchAUserByEmail() {
		
		//cenary
		repo.deleteAll();	
		repo.save(userBuild());
		
		//verification
		Optional<UserEntity> result = repo.findByEmail("email@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
		
	}
	
	@Test
	public void mustSearchAUserByNickname() {
		
		//cenary
		repo.deleteAll();	
		repo.save(userBuild());
		
		//verification
		Optional<UserEntity> result = repo.findByNickname("teste");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
		
	}
	
	
	//-----------------------------------------------------
	public static UserEntity userBuild() {
		
		return UserEntity.builder().email("email@email.com").nickname("teste").password("teste").build();
	}
}
