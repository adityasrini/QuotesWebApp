package com.mindfulness.service.impl;

import com.mindfulness.data.model.MindfulnessDataModel;
import com.mindfulness.data.setup.EntityManagerDAO;
import com.mindfulness.data.setup.impl.MongoDB_DAOImpl;
import com.mindfulness.service.QuoteGenerator;

import java.util.List;

public class QuoteGeneratorImpl implements QuoteGenerator {
	public String generateQuote() {
		EntityManagerDAO entityManagerDAO = new MongoDB_DAOImpl();
		return entityManagerDAO.selectOneQuote(1);
	}
	
	public String[] listAllQuotes() {
		EntityManagerDAO entityManagerDAO = new MongoDB_DAOImpl();
		List<MindfulnessDataModel> mindfulnessDataModels = entityManagerDAO.listAllQuotes();
		
		String[] stringArray = new String[mindfulnessDataModels.size()];
		
		for (int i = 0; i < mindfulnessDataModels.size(); i++) {
			stringArray[i] = mindfulnessDataModels.get(i).getQuote();
		}
		
		return stringArray;
		
	}
}