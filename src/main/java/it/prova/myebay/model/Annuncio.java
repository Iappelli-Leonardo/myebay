package it.prova.myebay.model;

import javax.persistence.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "annuncio")
public class Annuncio {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "testoAnnuncio")
	private String testoAnnuncio;
	@Column(name = "prezzo")
	private int prezzo;
	@Column(name = "data")
	private Date data;
	@Column(name = "aperto")
	private boolean aperto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id",nullable = false)
	private Utente utenteIserimento;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "annuncio_categoria", joinColumns = @JoinColumn(name = "annuncio_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"))
	private Set<Categoria> categorie = new HashSet<Categoria>();

	public Annuncio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Annuncio(Long id, String testoAnnuncio, int prezzo, Date data, boolean aperto,
			Utente utenteIserimento) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteIserimento = utenteIserimento;
	}
	
	public Annuncio(String testoAnnuncio, int prezzo, Date data, boolean aperto,
			Utente utenteIserimento) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteIserimento = utenteIserimento;
	}
	
	public Annuncio(String testoAnnuncio, int prezzo, Date data, boolean aperto) {
		super();
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public Utente getUtenteIserimento() {
		return utenteIserimento;
	}

	public void setUtenteIserimento(Utente utenteIserimento) {
		this.utenteIserimento = utenteIserimento;
	}
	
	
	public void addToCategoria(Categoria categoriaInstance) {
		this.categorie.add(categoriaInstance);
		categoriaInstance.getAnnunci().add(this);
	}
	
	public void removeFromCategoria(Categoria categoriaInstance) {
		this.categorie.remove(categoriaInstance);
		categoriaInstance.getAnnunci().remove(this);
	}
}
