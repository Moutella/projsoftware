package modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
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
