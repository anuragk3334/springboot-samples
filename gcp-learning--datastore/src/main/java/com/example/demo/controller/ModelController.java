package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.data.datastore.core.DatastoreTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.EntityQuery.Builder;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import io.swagger.annotations.ApiParam;

@RestController
public class ModelController {
	//https://cloud.google.com/datastore/docs/concepts/queries
	//https://cloud.google.com/appengine/docs/standard/java/datastore/queries
	//https://stackoverflow.com/questions/15942840/add-filter-query-in-google-app-engine/34108997
	//https://www.programcreek.com/java-api-examples/?code=spotify/styx/styx-master/styx-common/src/main/java/com/spotify/styx/storage/DatastoreStorage.java
   @PostMapping("/post")
	public ResponseEntity<Model> create(
			@ApiParam(allowableValues ="domain1,domain2")@RequestHeader(value="domainId") String domain,
			@ApiParam(allowableValues ="tenatnt1,tenatnt2")@RequestHeader(value="tenantId") String tenantId,
			@ApiParam @RequestBody Model model) throws JsonProcessingException {

		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		
		KeyFactory keyFactory = datastore.newKeyFactory().setNamespace("default").
				setKind("("+domain+")"+"("+tenantId+")");
		
		IncompleteKey key=keyFactory.newKey();
		List<Map<String, Object>> lastsavedState = model.getLastsavedState();
		String x =new ObjectMapper().writeValueAsString(lastsavedState);
		FullEntity<IncompleteKey> fullEntity = Entity.newBuilder(key).
		set("queryDescription", model.getDescription()).
		set("username", model.getUsername()).
		set("email", model.getEmail()).
		set("queryType", model.getQueryType()).
		set("lastsavedQuery",x).build();
		datastore.add(fullEntity);
		
		
		
		EntityQuery.Builder kind = Query.newEntityQueryBuilder().setNamespace("default").setKind("("+domain+")"+"("+tenantId+")");
		PropertyFilter filter = PropertyFilter.eq("username", "anurag1");
		kind.setFilter(filter);
		kind.setOrderBy(OrderBy.asc("username"));
		EntityQuery query = kind.build();
		//query.getFilter().
		QueryResults<Entity> results = datastore.run(query);
		Model model1=new Model();
		while(results.hasNext()) {
			Entity next = results.next();
			Long val=next.getKey().getId();
			String string = next.getString("lastsavedQuery");
			List<Map<String,Object>> list=new ObjectMapper().readValue(string, List.class);
			model1.setLastsavedState(list);
			model1.setId(val.toString());
			System.out.println(next);
		}
		
		/*
		 * datastore.add(fullEntity); // The kind for the new entity String kind =
		 * "Task"; // The name/ID for the new entity String name = "sampletask1"; // The
		 * Cloud Datastore key for the new entity Key taskKey =
		 * datastore.newKeyFactory().setKind(kind).newKey(name); String value = new
		 * ObjectMapper().writeValueAsString(model);
		 * 
		 * Entity task = Entity.newBuilder(taskKey).set("description", value).build();
		 * datastore.put(task);
		 * 
		 * System.out.printf("Saved %s: %s%n", task.getKey().getName(),
		 * task.getString("description"));
		 * 
		 * //Retrieve entity Entity retrieved = datastore.get(taskKey);
		 * 
		 * String string = retrieved.getString("description"); Model model1= new
		 * ObjectMapper().readValue(string, Model.class);
		 * System.out.printf("Retrieved %s: %s%n", taskKey.getName(), string);
		 */
			return ResponseEntity.ok().body(model1);
	}

}
