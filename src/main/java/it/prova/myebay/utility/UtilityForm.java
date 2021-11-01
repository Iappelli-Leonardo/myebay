package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
	

		public static Annuncio createAnnuncioFromParams(String testoAnnuncioInputParam, String prezzoInputParam) {
			Date dataCreatedParam = new Date();
			
			Annuncio result = new Annuncio(testoAnnuncioInputParam, dataCreatedParam);
			
			if (NumberUtils.isCreatable(prezzoInputParam)) {
				result.setPrezzo(Integer.parseInt(prezzoInputParam));
			}
			
			result.setAperto(true);
			
			return result;
			
			
	
		}

	public static boolean validateAnnuncioBean(Annuncio annuncioToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(annuncioToBeValidated.getTestoAnnuncio())
				|| annuncioToBeValidated.getPrezzo() == null 
				|| annuncioToBeValidated.getPrezzo() < 1
				|| annuncioToBeValidated.getData() == null){
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
