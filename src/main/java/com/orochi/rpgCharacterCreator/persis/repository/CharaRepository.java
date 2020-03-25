package com.orochi.rpgCharacterCreator.persis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orochi.rpgCharacterCreator.entities.CharaEntity;

public interface CharaRepository extends JpaRepository<CharaEntity, Long>{

}
