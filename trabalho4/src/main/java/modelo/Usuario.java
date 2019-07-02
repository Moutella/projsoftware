package modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import util.Util;
@Entity
@Table(name = "USUARIO")

public class Usuario {
	private Long id;
	private String nome;
	private Long matricula;
	private Bairro bairro;
	private Calendar dataCadastro;
	private List<Carro> carros;
	
	public Usuario() {
		
	}
	public Usuario(String nome, Long matricula, Calendar dataCadastro, Bairro bairro) {
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.matricula = matricula;
		this.bairro = bairro;
	}
	
	// GETS
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
		return id;
    }

	@Column(nullable = false)
    public String getNome() {
		return nome;
    }
	@Column(name = "MATRICULA")
    public Long getMatricula() {
		return matricula;
    }
	
	@Column(name = "DATA_CADASTRO")
    @Temporal(value = TemporalType.DATE)
    public Calendar getDataCadastro() {
		return dataCadastro;
    }
	@Transient
    public String getDataCriacaoMasc() {
    	return Util.calendarToStr(dataCadastro);
    }
	//SETS
	@SuppressWarnings("unused")
    private void setId(Long id) {
	this.id = id;
    }
	public void setNome(String nome) {
		this.nome = nome;
	}
    public void setMatricula(Long matricula) {
	this.matricula = matricula;
    }

    public void setDataCadastro(Calendar dataCadastro) {
	this.dataCadastro = dataCadastro;
    }
    // ASSOCIAÇOES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAIRRO_ID")
    public Bairro getBairro() {
    	return bairro;
    }
    public void setBairro(Bairro bairro) {
    	this.bairro = bairro;
    }
    
    @OneToMany(mappedBy = "usuario")
    @OrderBy
    public List<Carro> getCarros() {
    	return carros;
    }

    public void setCarros(List<Carro> carros) {
    	this.carros=carros;
    }
}