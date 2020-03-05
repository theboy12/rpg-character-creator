package com.orochi.rpgCharacterCreator.persis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orochi.rpgCharacterCreator.entities.charaEntity;

public interface CharaRepository extends JpaRepository<charaEntity, Long>{

}
