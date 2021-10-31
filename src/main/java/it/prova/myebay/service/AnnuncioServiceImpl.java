package it.prova.myebay.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.dao.CategoriaDAO;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AnnuncioServiceImpl implements AnnuncioService{

	private AnnuncioDAO annuncioDAO;

	private CategoriaDAO categoriaDAO;
	
	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}

	@Override
	public List<Annuncio> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElementoConCategorie(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Annuncio registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.update(registaInstance);

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
	public void inserisciNuovo(Annuncio registaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.insert(registaInstance);

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
	public void rimuovi(Annuncio registaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			annuncioDAO.delete(registaInstance);

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
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idAnnuncioToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);
			Annuncio registaToRemove = annuncioDAO.findOneEager(idAnnuncioToRemove).orElse(null);
			if (registaToRemove == null)
				throw new ElementNotFoundException("Annuncio con id: " + idAnnuncioToRemove + " non trovato.");
			
			annuncioDAO.delete(registaToRemove);
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
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	public void inserisciNuovoConCategorie(Annuncio annuncioInstance, String[] categoriaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);
			categoriaDAO.setEntityManager(entityManager);

			Set<Categoria> categorie = new HashSet<Categoria>();

			for (int i = 0; i < categoriaInstance.length; i++) {
				Long idCategorie = Long.parseLong(categoriaInstance[i]);

				categorie.add(categoriaDAO.findOne(idCategorie).orElse(null));
			}

			annuncioInstance.setCategorie(categorie);

			// eseguo quello che realmente devo fare
			annuncioDAO.insert(annuncioInstance);

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
