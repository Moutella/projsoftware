import java.util.List;

import corejava.Console;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import modelo.Bairro;
import modelo.Carro;
import modelo.Usuario;
import perfis.SingletonPerfis;
import servico.BairroAppService;
import servico.UsuarioAppService;
import servico.UsuariosAppService;
import util.Util;

public class PrincipalUsuario {
	public static void main(String[] args) {
		String nome;
		Long matricula;
		String dataCadastro;
		
		Usuario umUsuario;
		Bairro umBairro;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		UsuarioAppService usuarioAppService = (UsuarioAppService)fabrica.getBean ("usuarioAppService");
		BairroAppService bairroAppService = (BairroAppService)fabrica.getBean ("bairroAppService");
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
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
		    System.out.println('\n' + "1. Cadastrar um usuario");
		    System.out.println("2. Alterar um usuario");
		    System.out.println("3. Remover um usuario");
		    System.out.println("4. Listar todos os usuarios");
		    System.out.println("5. Listar um usuario e seus carros:");
		    System.out.println("6. Listar todos os usuarios e seus carros:");
		    System.out.println("7. Sair");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

		    switch (opcao) {
		    case 1:
		    {
		    	nome = Console.readLine('\n' + "Informe o nome do usuario: ");
				matricula = Long.parseLong(Console.readLine("Informe a matricula do usuario"));
				long bairroId = Console.readInt("Informe o ID do bairro do usuario: ");
				dataCadastro = Console.readLine("Informe a data de cadastramento do usuario: ");
				try {
					umBairro = bairroAppService.recuperaUmBairro(bairroId);
					 System.out.println("Bairro: " + umBairro.getNome());
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				umUsuario = new Usuario(nome,matricula, Util.strToCalendar(dataCadastro), umBairro);
				try
				/* ==> */ {
				    usuarioAppService.inclui(umUsuario);

				    System.out.println('\n' + "Usuário adicionado com sucesso");
				} catch (Exception e) {
				    System.out.println(e.getMessage());
				}

				break;
		    }
		    case 2:
		    {
		    	int resposta = Console.readInt('\n' + "Digite o número do usuario que você deseja alterar: ");

				try {
				    umUsuario = usuarioAppService.recuperaUmUsuario(resposta);
				    // System.out.println(umUsuario.getLances().size());
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				System.out.println('\n' + "Número = " + umUsuario.getId() + "    Nome = " + umUsuario.getNome()
				+ "    Matricula = " + umUsuario.getMatricula());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Matricula");
				System.out.println("3. Bairro");
				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
				    String novoNome = Console.readLine("Digite o novo nome: ");
				    umUsuario.setNome(novoNome);

				    try {
				    	usuarioAppService.altera(umUsuario);
				    	System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
				    } catch (Exception e) {
				    	System.out.println('\n' + e.getMessage());
				    }
				    break;

				case 2:
				    Long novaMatr = Long.parseLong(Console.readLine("Digite a nova matricula: "));
				    umUsuario.setMatricula(novaMatr);

				    try {
					usuarioAppService.altera(umUsuario);

					System.out.println('\n' + "Alteração de matricula efetuada " + "com sucesso!");
				    } catch (Exception e) {
					System.out.println('\n' + e.getMessage());
				    }

				    break;
				case 3:
					Long novoBairro = Long.parseLong(Console.readLine("Digite o ID do novo bairro"));
					try {
						umBairro = bairroAppService.recuperaUmBairro(novoBairro);
						
					} catch (Exception e) {
					    System.out.println('\n' + e.getMessage());
					    break;
					}
					umUsuario.setBairro(umBairro);
					 try {
							usuarioAppService.altera(umUsuario);

							System.out.println('\n' + "Alteração de matricula efetuada " + "com sucesso!");
						    } catch (Exception e) {
							System.out.println('\n' + e.getMessage());
						    }

				default:
				    System.out.println('\n' + "Opção inválida!");
				}
				break;

		    }
		    case 3:
		    {
		    	int resposta = Console.readInt('\n' + "Digite o número do usuario que você deseja remover: ");

				try {
				    umUsuario = usuarioAppService.recuperaUmUsuario(resposta);
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				try {
					umBairro = bairroAppService.recuperaUmBairro(umUsuario.getBairro().getId());
					
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				System.out.println('\n' + "Número = " + umUsuario.getId() + "    Nome = " + umUsuario.getNome()
					+ "    Matricula = " + umUsuario.getMatricula()); 

				String resp = Console.readLine('\n' + "Confirma a remoção do usuario?");

				if (resp.toLowerCase().equals("s")) {
				    try {
					usuarioAppService.exclui(umUsuario.getId());
					System.out.println('\n' + "Usuario removido com sucesso!");
				    } catch (Exception e) {
					System.out.println('\n' + e.getMessage());
				    }
				} else {
				    System.out.println('\n' + "Usuario não removido.");
				}

				break;
			    
			}

		    case 4:
		    {
		    	List<Usuario> usuarios = usuarioAppService.recuperaUsuarios();
				for(Usuario u : usuarios) {
					System.out.println("\nID = " + u.getId().toString());
					System.out.println("Nome = " + u.getNome());
					System.out.println("matricula = " + u.getMatricula().toString());
					System.out.println("Data Cadastro = " + u.getDataCriacaoMasc());
				}
				break;
		    }
		    case 5:{
		    	int resposta = Console.readInt('\n' + "Digite o número do usuario que você deseja pesquisar: ");

				try {
				    umUsuario = usuarioAppService.recuperaUmUsuarioECarros(resposta);
				    // System.out.println(umUsuario.getLances().size());
				} catch (Exception e) {
				    System.out.println('\n' + e.getMessage());
				    break;
				}
				System.out.println('\n' + "Número = " + umUsuario.getId() + "    Nome = " + umUsuario.getNome()
				+ "    Matricula = " + umUsuario.getMatricula());
				List<Carro> carros = umUsuario.getCarros();
				for(Carro carro : carros) {
					System.out.println("Placa do Carro: " + carro.getPlaca());
				}
		    	break;
		    }
		    case 6:{
		    	List<Usuario> usuarios = usuarioAppService.recuperaUsuariosECarros();
		    	if(usuarios.size() != 0) {
		    		System.out.println("");
		    		for(Usuario usuario : usuarios) {
		    			System.out.println("ID: " + usuario.getId());
		    			System.out.println("Nome: " + usuario.getNome());
		    			
		    			List<Carro> carros = usuario.getCarros();
						for(Carro carro : carros) {
							System.out.println("Placa do Carro: " + carro.getPlaca());
						}
		    		}
		    		
		    	}else {
				    System.out.println('\n' + "Não há produtos cadastrados com esta descrição.");
				}
		    	break;
		    }
		    case 7: {
				continua = false;
				break;
			    }
			    default:	System.out.println('\n' + "Opção inválida!");
		    }
		}
	}
	//case 7:
	//{
		
}
