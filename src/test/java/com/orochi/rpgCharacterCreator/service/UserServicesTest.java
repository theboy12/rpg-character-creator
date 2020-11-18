package com.orochi.rpgCharacterCreator.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orochi.rpgCharacterCreator.entities.UserEntity;
import com.orochi.rpgCharacterCreator.exceptions.LoginErrorException;
import com.orochi.rpgCharacterCreator.exceptions.UserAlreadyExistsException;
import com.orochi.rpgCharacterCreator.persis.repository.UserRepository;
import com.orochi.rpgCharacterCreator.services.UserServices;
import com.orochi.rpgCharacterCreator.services.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserServicesTest {
	
	@SpyBean
	UserServiceImpl service;
	
	@MockBean
	UserRepository repo;
	
	@Test(expected = Test.None.class)
	//Must create a new user
	public void mustCreateANewUser() {
		//cenary
		Mockito.doNothing().when(service).verifyEmail(Mockito.anyString());
		Mockito.doNothing().when(service).verifyNickname(Mockito.anyString());
		
		UserEntity user = UserEntity.builder()
				.id(1l)
				.nickname("nickname")
				.email("test@test.com")
				.password("password")
				.build();
		
		Mockito.when(repo.save(Mockito.any(UserEntity.class))).thenReturn(user);
		
		//action
		UserEntity savedUser = service.signIn(new UserEntity());
		
		//verification
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isEqualTo(1l);
		Assertions.assertThat(savedUser.getNickname()).isEqualTo("nickname");
		Assertions.assertThat(savedUser.getEmail()).isEqualTo("test@test.com");
		Assertions.assertThat(savedUser.getPassword()).isEqualTo("password");
		
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	//must fail to save a user with a already existing email
	public void userAlreadyExistsEmail() {
		//cenary
		String email = "test@test.com";
		UserEntity user = UserEntity.builder().email(email).build();
		Mockito.doThrow(UserAlreadyExistsException.class).when(service).verifyEmail(email);
		
		//action
		service.signIn(user);
		
		//verify
		Mockito.verify(repo, Mockito.never()).save(user);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	//must fail to save a user with a already existing username
	public void userAlreadyExistsUsername() {
		//cenary
		String nickname = "test";
		UserEntity user = UserEntity.builder().nickname(nickname).build();
		Mockito.doThrow(UserAlreadyExistsException.class).when(service).verifyNickname(nickname);
		
		//action
		service.signIn(user);
		
		//verify
		Mockito.verify(repo, Mockito.never()).save(user);
	}

	@Test(expected = Test.None.class)
	//Verify if the email already exists(should be false)
	public void verifyingEmail() {
		
		//cenary
		Mockito.when(repo.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//action
		service.verifyEmail("email@email.com");
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	//Verify if the email already exists(should be true)
	public void VerifyingEmailAlreadyExists(){
	
		//cenary
		Mockito.when(repo.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		//action
		service.verifyEmail("test@test.com");
	}
	
	@Test(expected = Test.None.class)
	//Login in after finding the user
	public void loginWithARealUser() {
		
		//cenary
		String nickname = "test";
		String password = "password";
		
		UserEntity user = UserEntity.builder()
				.email("test@test.com")
				.nickname(nickname)
				.password(password)
				.id(1l)
				.build();
		
		Mockito.when(repo.findByNickname(nickname)).thenReturn(Optional.of(user));
		
		//action
		UserEntity userLogged = service.login(nickname, password);
		
		//verification
		Assertions.assertThat(userLogged).isNotNull();
	}
	
	@Test
	//Must fail after trying to login with a non-existent user
	public void userDoNotExists() {
		
		//cenary
		Mockito.when(repo.findByNickname(Mockito.anyString())).thenReturn(Optional.empty());
		
		//action
		Throwable exception = Assertions.catchThrowable(() -> service.login("test", "password"));
		Assertions.assertThat(exception).isInstanceOf(LoginErrorException.class).hasMessage("Invalid nickname!");
		
	}
	
	@Test
	//Must fail to login after typing the wrong password
	public void IncorrectPassword() {
		
		String password = "password";
		
		//cenary
		UserEntity user = UserEntity.builder().email("test@test.com").nickname("test").password(password).build();
		Mockito.when(repo.findByNickname(Mockito.anyString())).thenReturn(Optional.of(user));
		
		//action
		Throwable exception = Assertions.catchThrowable(() -> service.login("test", "test"));
		Assertions.assertThat(exception).isInstanceOf(LoginErrorException.class).hasMessage("Invalid password!");
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	//verify if nickname already exists(should return exception)
	public void verifyingIfUsernameAlreadyExists() {
		
		//cenary
		Mockito.when(repo.existsByNickname(Mockito.anyString())).thenReturn(true);
		
		//action
		service.verifyNickname("test");
	}
	
}
