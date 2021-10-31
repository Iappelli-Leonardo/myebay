package it.prova.myebay.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "dateCreated")
	private Date dateCreated;
	@Column(name = "creditoResiduo")
	private Integer creditoResiduo;

	// se non uso questa annotation viene gestito come un intero
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;

	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);

	public Utente() {
	}

	public Utente(Long id, String username, String password, String nome, String cognome, Date dateCreated,
			Integer creditoResiduo, StatoUtente stato, Set<Ruolo> ruoli) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.creditoResiduo = creditoResiduo;
		this.stato = stato;
		this.ruoli = ruoli;
	}
	public Utente(Long id, String username, String password, String nome, String cognome) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Utente(Long id) {
		super();
		this.id = id;
	}
	
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Utente(String username, String nome, String password, String cognome, Set<Ruolo> ruoli, Integer creditoResiduo) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruoli = ruoli;
		this.creditoResiduo = creditoResiduo;
	}

	public Utente(String username, String password, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}
	public Utente(String username, String password, String nome, String cognome, Date dateCreated, Integer creditoResiduo) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.creditoResiduo = creditoResiduo;
	}
	
	public Utente(String username, String password,String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}
	public Utente(String username,String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	
	public Integer getCreditoResiduo() {
		return creditoResiduo;
	}

	public void setCreditoResiduo(Integer creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}

	public boolean isAdmin() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
				return true;
		}
		return false;
	}
	
	public boolean isUser() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_CLASSIC_USER))
				return true;
		}
		return false;
	}
	
	public boolean isLogged() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ROLE_CLASSIC_USER)||
					ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
				return true;
		}
		return false;
	}

}