package modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({
	@NamedQuery(name = "ModelosCarro.recuperaModelosCarros", query = "SELECT u FROM ModelosCarro u ORDER BY u.id"),
	@NamedQuery(name = "ModelosCarro.recuperaModelosCarrosECarros", query = "select distinct m from ModelosCarro m left outer join fetch m.carros order by m.id")
})
@Entity
@Table(name = "MODELOSCARRO")
public class ModelosCarro {
	private Long id;
	private String nome;
	private List<Carro> carros;
	public ModelosCarro() {
		
	}
	public ModelosCarro(String nome) {
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
    @OneToMany(mappedBy = "modelo")
    @OrderBy
    public List<Carro> getCarros() {
    	return carros;
    }

    public void setCarros(List<Carro> carros) {
    	this.carros = carros;
    }
}
