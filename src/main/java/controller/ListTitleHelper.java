/**
 * @author Evan Bunnell - ebunnell
 * CIS175 12737 - Fall 2023
 * Sep 13, 2023
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListTitle;

public class ListTitleHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MediaConsumptionList");

	public void insertTitle(ListTitle li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListTitle> showAllTitles(){
		EntityManager em = emfactory.createEntityManager();
		List<ListTitle> allItems = em.createQuery("SELECT i FROM ListTitle i").getResultList();
		return allItems;
	}

	public void deleteTitle(ListTitle toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTitle> typedQuery = em.createQuery("select li from ListTitle li where li.title = :selectedTitle and li.medium = :selectedMedium"
				+ " and li.location = :selectedLocation",
				ListTitle.class);

		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedMedium", toDelete.getMedium());
		typedQuery.setParameter("selectedLocation", toDelete.getLocation());

		//one result
		typedQuery.setMaxResults(1);

		//get result and save into a new list item
		ListTitle result = typedQuery.getSingleResult();

		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public ListTitle searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListTitle found = em.find(ListTitle.class,	idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateTitle(ListTitle toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param itemTitle
	 * @return
	 */
	public List<ListTitle> searchForItemByTitle(String itemTitle) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTitle> typedQuery = em.createQuery("select li from ListTitle li where li.title = :selectedTitle", ListTitle.class);
		typedQuery.setParameter("selectedTitle", itemTitle);
		List<ListTitle> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param itemMedium
	 * @return
	 */
	public List<ListTitle> searchForItemByMedium(String itemMedium) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTitle> typedQuery = em.createQuery("select li from ListTitle li where li.medium = :selectedMedium", ListTitle.class);
		typedQuery.setParameter("selectedMedium", itemMedium);
		List<ListTitle> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param itemLocation
	 * @return
	 */
	public List<ListTitle> searchForItemByLocation(String itemLocation) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListTitle> typedQuery = em.createQuery("select li from ListTitle li where li.location = :selectedLocation", ListTitle.class);
		typedQuery.setParameter("selectedLocation", itemLocation);
		List<ListTitle> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public void cleanUp() {
		emfactory.close();
	}
}
