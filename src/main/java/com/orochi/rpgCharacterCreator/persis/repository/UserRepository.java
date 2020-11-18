package com.orochi.rpgCharacterCreator.persis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orochi.rpgCharacterCreator.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	boolean existsByEmail(String email);
	
	boolean existsByNickname(String nickname);

	Optional<UserEntity> findByNickname(String nickname);
	
	Optional<UserEntity> findByEmail(String email);
}
