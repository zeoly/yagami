package com.thinkequip.anochat.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Reply implements Serializable {

	private static final long serialVersionUID = 333701557089362792L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	long id;

	@Column(name = "content")
	String content;

	@Column(name = "replyer")
	String replyer;

}
