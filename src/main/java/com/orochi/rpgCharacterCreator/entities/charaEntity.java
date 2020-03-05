package com.orochi.rpgCharacterCreator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Table(name = "chara", schema = "rpgcharactercreator")
@Entity

public class charaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "class")
	private String charClass;
	
	@Column(name = "vit")
	private int vit;
	
	@Column(name = "agi")
	private int agi;
	
	@Column(name = "dex")
	private int dex;
	
	@Column(name = "str")
	private int str;
	
	@Column(name = "int")
	private int intelli;
	
	@Column(name = "luk")
	private int luk;
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private UserEntity userID;
	
}
