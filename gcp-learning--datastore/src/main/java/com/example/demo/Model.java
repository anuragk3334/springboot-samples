package com.example.demo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Model {
	
	private String id;
	private String description;
	private Date creationDate;
	private String username;
	private String email;
	private String queryType;
	private List<Map<String,Object>> lastsavedState;

}
