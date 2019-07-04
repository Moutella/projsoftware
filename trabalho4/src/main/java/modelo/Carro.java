package modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "Carro.recuperaCarros", query = "select c from Carro c order by c.id"),
	@NamedQuery(name = "Carro.recuperaUmCarroEUsuarioEModelo", query = "select c from Carro c left outer join fetch c.usuario left outer join fetch c.modelo where c.id = ?1"),
	@NamedQuery(name = "Carro.recuperaCarrosUsuariosModelos", query = "select distinct c from Carro c left outer join fetch c.usuario left outer join fetch c.modelo")})
	
@Entity
@Table(name = "CARRO")
public class Carro {
	private Long id;
	private String placa;
	private ModelosCarro modelo;
	private Usuario usuario;
	public Carro() {
		
	}
	public Carro(String placa, ModelosCarro modelo, Usuario usuario) {
		this.placa = placa;
		this.modelo = modelo;
		this.usuario = usuario;
	}
	
	//GETS
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
		return id;
    }
	@Column(nullable = false)
    public String getPlaca() {
		return placa;
    }
	//SETS
		@SuppressWarnings("unused")
	    private void setId(Long id) {
		this.id = id;
	    }
		public void setPlaca(String placa) {
			this.placa=placa;
		}
		    // ASSOCIAÇOES
	// ASSOCIAÇOES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODELO_ID")
    public ModelosCarro getModelo() {
    	return modelo;
    }
    public void setModelo(ModelosCarro modelo) {
    	this.modelo = modelo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID")
    public Usuario getUsuario() {
    	return usuario;
    }
    public void setUsuario(Usuario usuario) {
    	this.usuario = usuario;
    }
}
