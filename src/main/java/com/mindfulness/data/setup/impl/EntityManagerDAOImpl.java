package com.mindfulness.data.setup.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mindfulness.data.model.MindfulnessDataModel;
import com.mindfulness.data.setup.EntityManagerDAO;

public class EntityManagerDAOImpl implements EntityManagerDAO {

	private EntityManagerFactory entityManagerFactory;

	private void setup() {
		entityManagerFactory = Persistence.createEntityManagerFactory("QuotesWebApp");
	}

	private void close() {
		entityManagerFactory.close();
	}

	public void createQueries() {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<MindfulnessDataModel> dataModel = entityManager
				.createQuery("from MindfulnessDataModel", MindfulnessDataModel.class).getResultList();
		for (MindfulnessDataModel mindfulnessDataModel : dataModel) {
			System.out.println("Quote is: " + mindfulnessDataModel.getQuote());
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		close();

	}

	@Override
	public void makeInserts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void performUpdates() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDeletes() {
		// TODO Auto-generated method stub

	}

}
