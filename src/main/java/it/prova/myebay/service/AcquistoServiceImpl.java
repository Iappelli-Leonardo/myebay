package it.prova.myebay.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AcquistoServiceImpl implements AcquistoService{
	
	private AcquistoDAO acquistoDAO;
	
	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}

	@Override
	public List<Acquisto> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElementoConUtente(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Acquisto registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			acquistoDAO.update(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Acquisto registaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			acquistoDAO.insert(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Acquisto registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			acquistoDAO.delete(registaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idAcquistoToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			acquistoDAO.setEntityManager(entityManager);
			Acquisto registaToRemove = acquistoDAO.findOneEager(idAcquistoToRemove).orElse(null);
			if (registaToRemove == null)
				throw new ElementNotFoundException("Acquisto con id: " + idAcquistoToRemove + " non trovato.");
			
			acquistoDAO.delete(registaToRemove);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
		
	}


}
