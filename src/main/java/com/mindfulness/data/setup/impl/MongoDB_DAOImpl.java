package com.mindfulness.data.setup.impl;

import com.mindfulness.data.model.MindfulnessDataModel;
import com.mindfulness.data.setup.EntityManagerDAO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class MongoDB_DAOImpl implements EntityManagerDAO {
	
	private MongoCollection<Document> mongoDBSetup() {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase database = client.getDatabase("Quotes");
		MongoCollection<Document> collection = database.getCollection("quotes");
		
		return collection;
	}
	
	@Override
	public List<MindfulnessDataModel> listAllQuotes() {
		MongoCursor<Document> cursor = mongoDBSetup().find().iterator();
		List<MindfulnessDataModel> mindfulnessDataModels = new ArrayList<>();
		return getsCursorAndAddsToDataModelList(cursor, mindfulnessDataModels);
	}
	
	private List<MindfulnessDataModel> getsCursorAndAddsToDataModelList(MongoCursor<Document> cursor,
	                                                                    List<MindfulnessDataModel> mindfulnessDataModels) {
		while (cursor.hasNext()) {
			Document next = cursor.next();
			MindfulnessDataModel mindfulnessDataModel = new MindfulnessDataModel();
			mindfulnessDataModel.setSecondId((String) next.get("_id.$oid"));
			mindfulnessDataModel.setQuote(next.getString("quote"));
			mindfulnessDataModels.add(mindfulnessDataModel);
		}
		return mindfulnessDataModels;
	}
	
	@Override
	public String selectOneQuote(int id) {
		
		Document document = mongoDBSetup().find().first();
		return document.getString("quote");
		
	}
	
	@Override
	public List<MindfulnessDataModel> searchQuoteByName(String searchString) {
		// TEST THIS FIRST
		MongoCursor<Document> cursor = mongoDBSetup().find(regex("quote", "." + searchString + ".")).iterator();
		List<MindfulnessDataModel> mindfulnessDataModels = new ArrayList<>();
		return getsCursorAndAddsToDataModelList(cursor, mindfulnessDataModels);
	}
	
	@Override
	public int insertAQuote(String quote) {
		mongoDBSetup().insertOne(new Document("quote", quote));
		return 0;
	}
	
	@Override
	public List<Integer> batchInserts(String[] strings) {
		List<Document> documentList = new ArrayList<>();
		for (String string : strings) {
			Document document = new Document("quote", string);
			documentList.add(document);
		}
		
		mongoDBSetup().insertMany(documentList);
		return null;
	}
	
	@Override
	public int performAnUpdate(int id, String updatedString) {
		
		mongoDBSetup().updateOne(eq("_id", id), set("quote", updatedString));
		return 1;
	}
	
	
	@Override
	public List<Integer> batchUpdates(int[] ids, String[] strings) {
		for (int i = 0; i < ids.length; i++) {
			performAnUpdate(ids[i], strings[i]);
			
		}
		return null;
	}
	
	@Override
	public void deleteSingleQuote(int id) {
		mongoDBSetup().deleteOne(eq("_id", id));
		
	}
	
	@Override
	public List<Integer> batchDeletes(int[] ids) {
		for (int id : ids) {
			mongoDBSetup().deleteOne(eq("_id", id));
		}
		return null;
	}
	
}
