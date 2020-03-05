package com.orochi.rpgCharacterCreator.persis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orochi.rpgCharacterCreator.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	boolean existsByEmail(String email);
	
	boolean existsByNickname(String nickname);
}
