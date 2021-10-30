package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {


	public List<Annuncio> listAllElements() throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;
	
	public Annuncio caricaSingoloElementoConAcquisto(Long id) throws Exception;

	public void aggiorna(Annuncio annuncioInstance) throws Exception;

	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception;

	public void rimuovi(Annuncio annuncioInstance) throws Exception;
	
	public List<Annuncio> findByExample(Annuncio example) throws Exception;
	
	public void rimuovi(Long idAnnuncioToRemove) throws Exception;

	//per injection
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
	
}
