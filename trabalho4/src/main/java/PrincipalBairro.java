import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import modelo.Bairro;
import modelo.Usuario;
import perfis.SingletonPerfis;
import servico.BairroAppService;
import servico.UsuariosAppService;
public class PrincipalBairro {
	public static void main(String[] args) {
		String nome;
		
		Bairro umBairro;
		

		
		@SuppressWarnings("resource")
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		BairroAppService bairroAppService = (BairroAppService)fabrica.getBean ("bairroAppService");
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
		    System.out.println('\n' + "1. Cadastrar um bairro");
		    System.out.println("2. Alterar um bairro");
		    System.out.println("3. Remover um bairro");
		    System.out.println("4. Listar todos os bairros e seus moradores");
		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

		    switch (opcao) {
		    case 1:{
		    	nome = Console.readLine('\n' + "Informe o nome do bairro: ");
		    	umBairro = new Bairro(nome);
		    	
		    	try {
		    		bairroAppService.inclui(umBairro);
		    	} catch(Exception e) {
		    		System.out.println(e.getMessage());
		    	}
		    	break;
		    }
		    case 2:
		    {
		    	int resposta = Console.readInt('\n' + "Digite o número do bairro que você deseja alterar: ");
		    	try {
		    		umBairro = bairroAppService.altera(umBairro);(resposta);
		    	} catch(Exception e) {
		    		System.out.println("\n" + e.getMessage());
		    		break;
		    	}
		    	System.out.println("\nNúmero: "+ umBairro.getId() +"Nome =" + umBairro.getNome());
		    	String novoNome = Console.readLine(("\nDigite o novo nome"));
		    	umBairro.setNome(novoNome);
		    	try {
		    		bairroAppService.altera(umBairro);
		    	} catch (Exception e) {
		    		System.out.println("\n" + e.getMessage());
		    		break;
		    	}
		    	break;
		    }
		    case 3:{
		    	int resposta = Console.readInt('\n' + "Digite o número do bairro que você deseja remover: ");

				try {
				    umBairro = bairroAppService.recuperaUmBairro(resposta);
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}

				System.out.println('\n' + "Número = " + umBairro.getId() + "    Nome = " + umBairro.getNome());

				String resp = Console.readLine('\n' + "Confirma a remoção do bairro?");

				if (resp.toLowerCase().equals("s")) {
				    try {
					bairroAppService.exclui(umBairro);
					System.out.println('\n' + "Bairro removido com sucesso!");
				    } catch (Exception e) {
					System.out.println('\n' + e.getMessage());
				    }
				} else {
				    System.out.println('\n' + "Bairro não removido.");
				}

				break;
		    }
		    case 4:
		    {
		    	List<Bairro> bairros = bairroAppService.recuperaBairrosEmoradores();
		    	if(bairros.size() != 0) {
		    		System.out.println("");
		    		for(Bairro bairro : bairros) {
		    			System.out.println("ID: " + bairro.getId());
		    			System.out.println("Nome: " + bairro.getNome());
		    			List<Usuario> usuarios = bairro.getUsuarios();
		    			for(Usuario usuario : usuarios) {
		    				System.out.println("Nome: "+ usuario.getNome());
		    				System.out.println("MAT: "+ usuario.getMatricula());
		    				System.out.println("DATA CADASTRO: " + usuario.getDataCriacaoMasc());
		    			}
		    		}
		    		
		    	}else {
				    System.out.println('\n' + "Não há bairros cadastrados");
				}
		    	break;
		    }
		}
		
		
	}
	}
}
