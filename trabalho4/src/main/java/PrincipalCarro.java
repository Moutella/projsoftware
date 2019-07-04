import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ModeloCarroNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Carro;
import modelo.ModelosCarro;
import modelo.Usuario;
import perfis.SingletonPerfis;
import servico.CarroAppService;
import servico.ModelosCarroAppService;
import servico.UsuarioAppService;
import servico.UsuariosAppService;

public class PrincipalCarro {
	public static void main(String[] args) {
		String placa;
		
		Usuario umUsuario;
		ModelosCarro umModelo;
		Carro umCarro;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		UsuarioAppService usuarioAppService = (UsuarioAppService)fabrica.getBean ("usuarioAppService");
		CarroAppService carroAppService = (CarroAppService)fabrica.getBean ("carroAppService");
		ModelosCarroAppService modeloAppService = (ModelosCarroAppService)fabrica.getBean ("modelosCarroAppService");
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		UsuariosAppService perfilApp = (UsuariosAppService)fabrica.getBean("usuariosAppService");
		String conta = Console.readLine("\n Digite seu usuário:\n");
		String senha = Console.readLine("\n Digite sua senha:\n");
		List<String> perfisList = perfilApp.recuperaPerfis(conta, senha);
		String[] perfis = new String[perfisList.size()];
		for(int i = 0; i < perfisList.size(); i++) {
			perfis[i] = perfisList.get(i);
		}
		
		singletonPerfis.setPerfis(perfis);
		
		boolean continua = true;
		while(continua) {
			System.out.println('\n' + "O que você deseja fazer?");
		    System.out.println('\n' + "1. Cadastrar um carro");
		    System.out.println("2. Remover um carro");
		    System.out.println("3. Recuperar um carro e seu motorista e modelo");
		    System.out.println("4. Recuperar todos os carros e seus motoristas");
		    System.out.println("5. Sair");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

		    switch (opcao) {
		    case 1:{
		    	placa = Console.readLine("\nDigite a placa do carro");
		    	long modeloId = Console.readInt("\nDigite o id do modelo");
		    	long userId = Console.readInt("\nDigite o id do usuario");
		    	try {
		    		umUsuario = usuarioAppService.recuperaUmUsuario(userId);
		    	} catch(UsuarioNaoEncontradoException e) {
				    System.out.println('\n' + e.getMessage());
		    		break;
		    	}
		    	try {
		    		umModelo = modeloAppService.recuperaUmModelosCarro(modeloId);
		    	} catch(ModeloCarroNaoEncontradoException e) {
				    System.out.println('\n' + e.getMessage());
		    		break;
		    	}
		    	umCarro = new Carro(placa, umModelo, umUsuario);
		    	try {
		    		carroAppService.inclui(umCarro);
		    	} catch(Exception e) {
		    		System.out.println(e.getMessage());
		    	}
		    	break;
		    }
		    case 2:{
		    	int resposta = Console.readInt('\n' + "Digite o número do carro que você deseja remover: ");

				try {
				    umCarro = carroAppService.recuperaUmCarro(resposta);
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}

				System.out.println('\n' + "Número = " + umCarro.getId() + "Placa: " + umCarro.getPlaca());

				String resp = Console.readLine('\n' + "Confirma a remoção do carro?");

				if (resp.equals("s")) {
				    try
				    /* ==> */ {
					carroAppService.exclui(umCarro);
					System.out.println('\n' + "Carro removido com sucesso!");
				    } catch (Exception e) {
					System.out.println(e.getMessage());
				    }
				} else {
				    System.out.println('\n' + "Carro não removido.");
				}
				break;
		    }
		    case 3: {
		    	long numero = Console.readInt('\n' + "Informe o número do carro: ");

				try {
				    umCarro = carroAppService.recuperaUmCarroEUsuarioEModelo(numero);
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				umUsuario = umCarro.getUsuario();
				umModelo = umCarro.getModelo();
				System.out.println('\n' + "Id = " + umCarro.getId() 
				   + "  Placa = " + umCarro.getPlaca() 
				   + "  Usuario = " + umUsuario.getNome()
				   + "  Modelo = " + umModelo.getNome());

				break;
		    }
		    case 4:
		    {
		    	List<Carro> carros = carroAppService.recuperaCarrosUsuariosModelos();
		    	if(carros.size()!=0) {
		    		System.out.println("");
		    		for(Carro carro : carros) {
		    			umUsuario = carro.getUsuario();
						umModelo = carro.getModelo();
						System.out.println('\n' + "Id = " + carro.getId() 
						   + "  Placa = " + carro.getPlaca() 
						   + "  Usuario = " + umUsuario.getNome()
						   + "  Modelo = " + umModelo.getNome());

		    		}
		    	}
		    	break;
		    }
		    case 5:{
		    	continua = false;
		    	break;
		    }
		    default:
				System.out.println('\n' + "Opção inválida!");
			    
		    	
		}
	}
	}
}

