package com.mindfulness.test.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.mindfulness.data.model.MindfulnessDataModel;
import com.mindfulness.data.setup.EntityManagerDAO;
import com.mindfulness.data.setup.impl.EntityManagerDAOImpl;

public class DatabaseTests {

	private EntityManagerDAO entityManagerDAO = new EntityManagerDAOImpl();

	@Test
	public void shouldConnectToDbAndReturnAnEntityManager() {
		EntityManagerFactory entityManagerFactory;
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
	public void listAllQuotesInTheConsole() {
		List<MindfulnessDataModel> dataModel = new ArrayList<>();
		dataModel = entityManagerDAO.listAllQuotes();
		for (MindfulnessDataModel mindfulnessDataModel : dataModel) {
			System.out.println("Quote is: " + mindfulnessDataModel.getQuote());
		}

	}

	@Test
	public void shouldReturnOneQuoteGivenItsId() {
		int id = 1;
		String quoteExpected = "test";
		String quoteActual = entityManagerDAO.selectOneQuote(id);
		assertEquals(quoteExpected, quoteActual);
	}

	@Test
	public void shouldReturnQuoteGivenAString() {
		String quoteSearched = "te";
		String quoteActual = entityManagerDAO.searchQuoteByName(quoteSearched).get(0).getQuote();
		assertEquals("test", quoteActual);
	}

	@Test
	public void shouldInsertAQuoteSuccessfully() {
		List<MindfulnessDataModel> dataModel = entityManagerDAO.searchQuoteByName("The obstacle");

		if (!(dataModel.isEmpty())) {
			entityManagerDAO.deleteSingleQuote(dataModel.get(0).getId());
		}
		String originalQuote = "The obstacle is the path.";
		int quoteId;
		quoteId = entityManagerDAO.insertAQuote(originalQuote);
		String returnedQuote = entityManagerDAO.selectOneQuote(quoteId);
		assertEquals(returnedQuote, originalQuote);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldDeleteTheQuoteAndThrowExceptionWhenQuoteWithIdIsLookedUp() {
		int id = entityManagerDAO.searchQuoteByName("The obstacle is the path.").get(0).getId();
		entityManagerDAO.deleteSingleQuote(id);
		entityManagerDAO.selectOneQuote(id);

	}

}
