
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ModeloCarroNaoEncontradoException;
import modelo.ModelosCarro;
import perfis.SingletonPerfis;
import modelo.Carro;
import servico.ModelosCarroAppService;
import servico.UsuariosAppService;

public class PrincipalModelosCarro {
	public static void main(String[] args) {
		String nome;
		
		ModelosCarro umModelosCarro;
	
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		
		ModelosCarroAppService modelosCarroAppService = (ModelosCarroAppService)fabrica.getBean ("modelosCarroAppService");
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
		    System.out.println('\n' + "1. Cadastrar um modelo");
		    System.out.println("2. Alterar um modelo");
		    System.out.println("3. Remover um modelo");
		    System.out.println("4. Listar todos os modelos");
		    System.out.println("5. Listar todos os modelos e seus carros");
		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

		    switch (opcao) {
		    case 1:{
		    	nome = Console.readLine('\n' + "Informe o nome do modelo: ");
		    	umModelosCarro = new ModelosCarro(nome);
		    	
		    	try {
		    		modelosCarroAppService.inclui(umModelosCarro);
		    	} catch(Exception e) {
		    		System.out.println(e.getMessage());
		    	}
		    	break;
		    }
		    case 2:
		    {
		    	int resposta = Console.readInt('\n' + "Digite o número do modelo que você deseja alterar: ");
		    	try {
		    		umModelosCarro = modelosCarroAppService.recuperaUmModelosCarro(resposta);
		    	} catch(Exception e) {
		    		System.out.println("\n" + e.getMessage());
		    		break;
		    	}
		    	System.out.println("\nNúmero: "+ umModelosCarro.getId() +"Nome =" + umModelosCarro.getNome());
		    	String novoNome = Console.readLine(("\nDigite o novo nome"));
		    	umModelosCarro.setNome(novoNome);
		    	try {
		    		modelosCarroAppService.altera(umModelosCarro);
		    	} catch (Exception e) {
		    		System.out.println("\n" + e.getMessage());
		    		break;
		    	}
		    	break;
		    }
		    case 3:{
		    	int resposta = Console.readInt('\n' + "Digite o número do modelo que você deseja remover: ");

				try {
				    umModelosCarro = modelosCarroAppService.recuperaUmModelosCarro(resposta);
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}

				System.out.println('\n' + "Número = " + umModelosCarro.getId() + "    Nome = " + umModelosCarro.getNome());

				String resp = Console.readLine('\n' + "Confirma a remoção do modelo?");

				if (resp.toLowerCase().equals("s")) {
				    try {
					modelosCarroAppService.exclui(umModelosCarro);
					System.out.println('\n' + "Modelo removido com sucesso!");
				    } catch (Exception e) {
					System.out.println('\n' + e.getMessage());
				    }
				} else {
				    System.out.println('\n' + "Modelo não removido.");
				}

				break;
		    }
		    case 4:
		    {
		    	List<ModelosCarro> modelosCarros;
		    	try {
					modelosCarros = modelosCarroAppService.recuperaModelosCarros();
				} catch (ModeloCarroNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
		    	if(modelosCarros.size() != 0) {
		    		System.out.println("");
		    		for(ModelosCarro modelosCarro : modelosCarros) {
		    			System.out.println("ID: " + modelosCarro.getId());
		    			System.out.println("Nome: " + modelosCarro.getNome());
		    		}
		    	}
		    	break;
		    }
		    case 5:
		    {
		    	List<ModelosCarro> modelosCarros = modelosCarroAppService.recuperaModelosCarrosECarros();
		    	if(modelosCarros.size() != 0) {
		    		System.out.println("");
		    		for(ModelosCarro modelosCarro : modelosCarros) {
		    			System.out.println("ID: " + modelosCarro.getId());
		    			System.out.println("Nome: " + modelosCarro.getNome());
		    			List<Carro> carros = modelosCarro.getCarros();
		    			for(Carro carro : carros) {
		    				System.out.println("Placa: "+ carro.getPlaca());
		    				//System.out.println("DATA CADASTRO: " + carro.getDataCriacaoMasc());
		    			}
		    		}
		    		
		    	}else {
				    System.out.println('\n' + "Não há carros cadastrados.");
				}
		    	break;
		    }
		}
		
		
	}
	}
}
