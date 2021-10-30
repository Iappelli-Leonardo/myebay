package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.dao.AcquistoDAO;
import it.prova.myebay.model.Acquisto;


public interface AcquistoService {

	public List<Acquisto> listAllElements() throws Exception;

	public Acquisto caricaSingoloElemento(Long id) throws Exception;
	
	public Acquisto caricaSingoloElementoConUtente(Long id) throws Exception;

	public void aggiorna(Acquisto acquistoInstance) throws Exception;

	public void inserisciNuovo(Acquisto acquistoInstance) throws Exception;

	public void rimuovi(Acquisto acquistoInstance) throws Exception;
	
	public List<Acquisto> findByExample(Acquisto example) throws Exception;
	
	public void rimuovi(Long idAcquistoToRemove) throws Exception;

	//per injection
	public void setAcquistoDAO(AcquistoDAO registaDAO);
}
