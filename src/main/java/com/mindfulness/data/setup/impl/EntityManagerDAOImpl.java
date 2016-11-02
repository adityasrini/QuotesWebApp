package com.mindfulness.data.setup.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

	public List<MindfulnessDataModel> listAllQuotes() {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<MindfulnessDataModel> dataModel = entityManager
				.createQuery("from MindfulnessDataModel", MindfulnessDataModel.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		close();
		return dataModel;

	}

	@Override
	public String selectOneQuote(int id) {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		TypedQuery<String> query = entityManager
				.createQuery("Select m.quote From MindfulnessDataModel m where m.id = :id", String.class)
				.setParameter("id", id);

		String quote = query.getResultList().get(0);

		entityManager.getTransaction().commit();
		entityManager.close();
		close();
		return quote;
	}

	public List<MindfulnessDataModel> searchQuoteByName(String searchString) {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		TypedQuery<MindfulnessDataModel> query = entityManager
				.createQuery("Select m From MindfulnessDataModel m where m.quote like :searchString",
						MindfulnessDataModel.class)
				.setParameter("searchString", "%" + searchString + "%");

		List<MindfulnessDataModel> dataModel = query.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();
		close();
		return dataModel;
	}

	@Override
	public int insertAQuote(String quote) {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		MindfulnessDataModel dataModel = new MindfulnessDataModel();
		dataModel.setQuote(quote);
		entityManager.persist(dataModel);
		TypedQuery<Integer> query = entityManager
				.createQuery("Select m.id From MindfulnessDataModel m where m.quote Like :quote", Integer.class)
				.setParameter("quote", quote);
		int quoteId = query.getSingleResult().intValue();

		entityManager.getTransaction().commit();
		entityManager.close();
		close();
		return quoteId;
	}

	@Override
	public int performAnUpdate(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteSingleQuote(int id) {
		setup();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.createQuery("delete from MindfulnessDataModel m where m.id = :id").setParameter("id", id)
				.executeUpdate();

		entityManager.getTransaction().commit();
		entityManager.close();
		close();
	}

	@Override
	public List<Integer> batchInserts(String[] strings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> batchUpdates(String[] strings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> batchDeletes(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
