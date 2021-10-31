package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class UtilityForm {

	public static Categoria createRegistaFromParams(String descrizioneInputParam, String codiceInputParam) {

		Categoria result = new Categoria(descrizioneInputParam, codiceInputParam);
		return result;
	}

	public static boolean validateCategoriaBean(Categoria categoriaToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(categoriaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(categoriaToBeValidated.getCodice())) {
			return false;
		}
		return true;
	}
	

		public static Annuncio createAnnuncioFromParams(String testoAnnuncioInputParam, String prezzoInputParam, String[] categorieInputParam) {
			Date dataCreatedParam = new Date();
			Integer prezzoParam = Integer.parseInt(prezzoInputParam);
			Annuncio result = new Annuncio(testoAnnuncioInputParam, prezzoParam, dataCreatedParam);
			result.setAperto(true);
			
			return result;
		}

	public static boolean validateAnnuncioBean(Annuncio annuncioToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(annuncioToBeValidated.getTestoAnnuncio())
				|| annuncioToBeValidated.getPrezzo() == null 
				|| annuncioToBeValidated.getPrezzo() < 1
				|| annuncioToBeValidated.getData() == null
				|| annuncioToBeValidated.getUtenteInserimento().getId() == null 
				|| annuncioToBeValidated.getUtenteInserimento().getId() < 1) {
			return false;
		}
		return true;
	}
	
	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome())
				|| StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())
				|| utenteToBeValidated.getStato() == null
				|| utenteToBeValidated.getDateCreated() == null)
				{
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
