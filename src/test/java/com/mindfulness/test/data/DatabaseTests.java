package com.mindfulness.test.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.mindfulness.data.setup.EntityManagerDAO;
import com.mindfulness.data.setup.impl.EntityManagerDAOImpl;

public class DatabaseTests {

	private EntityManagerFactory entityManagerFactory;

	@Test
	public void shouldConnectToDbAndReturnAnEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("QuotesWebApp");
		entityManagerFactory.close();
	}

	// These are db connectivity tests for when the entity manager completely
	// fails
	// @Test
	// public void shouldSuccessfullySetupAndCloseEntityManager() {
	// EntityManagerDAO entityManager = new EntityManagerDAOImpl();
	// entityManager.setup();
	// entityManager.close();
	// }

	@Test
	public void listAllQuotes() {
		EntityManagerDAO entityManagerDAO = new EntityManagerDAOImpl();
		entityManagerDAO.createQueries();

	}

}
