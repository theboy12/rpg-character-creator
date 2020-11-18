package com.orochi.rpgCharacterCreator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Table(name = "chara", schema = "rpgcharactercreator")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CharaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "class")
	private String charClass;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "vit")
	private Integer vit;
	
	@Column(name = "agi")
	private Integer agi;
	
	@Column(name = "dex")
	private Integer dex;
	
	@Column(name = "str")
	private Integer str;
	
	@Column(name = "int")
	private Integer intelli;
	
	@Column(name = "luk")
	private Integer luk;
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private UserEntity userID;
	
}
