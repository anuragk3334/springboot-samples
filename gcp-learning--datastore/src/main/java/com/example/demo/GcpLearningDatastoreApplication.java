package com.example.demo;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

@SpringBootApplication
public class GcpLearningDatastoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpLearningDatastoreApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { Datastore
	 * datastore = DatastoreOptions.getDefaultInstance().getService();
	 * 
	 * // The kind for the new entity String kind = "Task"; // The name/ID for the
	 * new entity String name = "sampletask1"; // The Cloud Datastore key for the
	 * new entity Key taskKey =
	 * datastore.newKeyFactory().setKind(kind).newKey(name);
	 * 
	 * // Prepares the new entity
	 * 
	 * Entity task = Entity.newBuilder(taskKey) .set("description", "Buy milk")
	 * .build();
	 * 
	 * 
	 * Model model=new Model(); model.setCreationDate(new Date(0));
	 * model.setDescription("My first Query"); model.setEmail("anurag@gmail.com");
	 * //model.setId(id); model.setQueryType("Private");
	 * model.setUsername("Anurag");
	 * 
	 * String value=new ObjectMapper().writeValueAsString(model);
	 * 
	 * Entity task = Entity.newBuilder(taskKey) .set("description", value) .build();
	 * 
	 * 
	 * Entity task = Entity.newBuilder(taskKey) .set("description", "Buy milk")
	 * .build();
	 * 
	 * 
	 * // Saves the entity datastore.put(task);
	 * 
	 * System.out.printf("Saved %s: %s%n", task.getKey().getName(),
	 * task.getString("description"));
	 * 
	 * //Retrieve entity Entity retrieved = datastore.get(taskKey);
	 * 
	 * System.out.printf("Retrieved %s: %s%n", taskKey.getName(),
	 * retrieved.getString("description"));
	 * 
	 * }
	 */

}
