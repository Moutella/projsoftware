package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQueries({
	@NamedQuery(name = "Bairro.recuperaBairros", query = "select b from Bairro b order by b.id"),
	@NamedQuery(name = "Bairro.recuperaUmBairroEMoradores", query = "select b from Bairro b left outer join fetch b.usuarios order by b.id asc"),
	@NamedQuery(name = "Bairro.recuperaBairrosEMoradores", query = "select distinct b from Bairro b left outer join fetch b.usuarios order by b.id asc")})
	
	
@Entity
@Table(name = "BAIRRO")
public class Bairro {
	private Long id;
	private String nome;
	private List<Usuario> usuarios;
	public Bairro() {
		
	}
	public Bairro(String nome) {
		this.nome = nome;
	}
	
	
	///GETS
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
	return id;
    }

	@Column(name = "NOME")
	public String getNome() {
		return nome;
	    }

	
	
	///SETS
	public void setId(Long id) {
		this.id = id;
    }

    public void setNome(String nome) {
		this.nome = nome;
    }
    
    //ASSOCS
    @OneToMany(mappedBy = "bairro")
    @OrderBy
    public List<Usuario> getUsuarios() {
	return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
    	this.usuarios = usuarios;
    }

}
