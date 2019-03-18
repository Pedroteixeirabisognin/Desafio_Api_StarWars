package com.b2w.apistarwars.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timestamp;
	private int status;
	private String error;
	private String menssage;
	private String path;


	
}
