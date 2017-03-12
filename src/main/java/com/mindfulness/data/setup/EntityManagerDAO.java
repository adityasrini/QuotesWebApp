package com.mindfulness.data.setup;

import com.mindfulness.data.model.MindfulnessDataModel;

import java.util.List;

public interface EntityManagerDAO {
	
	List<MindfulnessDataModel> listAllQuotes();
	
	String selectOneQuote(int id);
	
	List<MindfulnessDataModel> searchQuoteByName(String searchString);
	
	int insertAQuote(String quote);
	
	List<Integer> batchInserts(String[] strings);
	
	int performAnUpdate(int id, String updatedString);
	
	List<Integer> batchUpdates(int[] ids, String[] strings);
	
	void deleteSingleQuote(int id);
	
	List<Integer> batchDeletes(int[] ids);
	
}