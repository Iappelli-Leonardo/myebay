package it.prova.myebay.dao;

import java.util.List;

import it.prova.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	
	public Categoria findByIdFetchingAnnunci(Long id) throws Exception;

	public List<Categoria> findByExample(Categoria example) throws Exception;
}
