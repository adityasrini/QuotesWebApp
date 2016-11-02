package com.mindfulness.data.setup;

import java.util.List;

import com.mindfulness.data.model.MindfulnessDataModel;

public interface EntityManagerDAO {

	public List<MindfulnessDataModel> listAllQuotes();

	public String selectOneQuote(int id);

	public List<MindfulnessDataModel> searchQuoteByName(String searchString);

	public int insertAQuote(String quote);

	public List<Integer> batchInserts(String[] strings);

	public int performAnUpdate(int id);

	public List<Integer> batchUpdates(String[] strings);

	public void deleteSingleQuote(int id);

	public List<Integer> batchDeletes(int[] ids);

}