package it.prova.myebay.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO{
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Optional<Categoria> findOne(Long id) throws Exception {
		Categoria result = entityManager.find(Categoria.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categoriaInstance = entityManager.merge(categoriaInstance);

	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(categoriaInstance);
	}

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(categoriaInstance));

	}

	@Override
	public Categoria findByIdFetchingAnnunci(Long id) throws Exception {
		TypedQuery<Categoria> query = entityManager
				.createQuery("select c FROM Categoria c left join fetch c.annunci a where c.id = :idCategoria", Categoria.class);
		query.setParameter("id", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<Categoria> findByExample(Categoria example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Categoria c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" c.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		
		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" c.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(queryBuilder.toString(), Categoria.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}


}
