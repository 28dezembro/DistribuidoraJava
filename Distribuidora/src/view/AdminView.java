/*menu admin
	alterar usuarios(senha, admin ou comum)
	excluir usuarios
		
	listar produtos(todos os produtos(ativos e nao ativos))
		crud produto
		repor estoque
		ativar e desativar produtos */

package view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.produto.Bebida;
import model.produto.Comida;
import model.produto.Produto;
import model.usuario.Admin;

public class AdminView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado) throws Exception {
        boolean menu = true;
        int adminopcao;
    
        System.out.println("\nLogin feito com sucesso administrador, " + usuarioSelecionado.getNomeCompleto());
        System.out.println("------------Seja Bem-Vindo a distribuidora dos Guri------------");

        while (menu) {
            System.out.println("\nSelecione o que deseja fazer: "
                + "\n1 - Alterar usuário"
                + "\n2 - Excluir usuário"
                + "\n3 - Listar produtos"
                + "\n4 - Menu de produto"
                + "\n5 - Ativar/Desativar produto"
                + "\n0 - Sair");
    
            adminopcao = in.nextInt();
            in.nextLine(); 
    
            switch (adminopcao) {
                case 1:
                    System.out.println("Digite o login do usuário que deseja alterar:");
                    String loginUsuario = in.nextLine();
                    System.out.println("Selecione o que deseja alterar:"
                        + "\n1 - Alterar senha"
                        + "\n2 - Alterar tipo de usuário (admin ou comum)");
                    int opcaoAlterar = in.nextInt();
                    in.nextLine(); 

                    switch (opcaoAlterar) {
                        case 1:
                        System.out.println("Digite a nova senha:");
                        controller.alterarSenhaUsuario(loginUsuario, in.nextLine());
                            break;
                        case 2:
                        System.out.println("Digite o novo tipo (admin ou comum):");
                        String novoTipo = in.nextLine();
                        controller.alterarTipoUsuario(loginUsuario, novoTipo);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Digite o login do usuário que deseja excluir:");
                    String loginExcluir = in.nextLine();
                    controller.excluirUsuario(loginExcluir);
                    break;
                
                case 3:
                System.out.println("\nSelecione o que deseja fazer: "
                + "\n1 - Listar Bebidas"
                + "\n2 - Listar Comidas");
                    int listarCategorias = in.nextInt();
                    in.nextLine(); 

                    switch (listarCategorias) {
                        case 1:
                            List<Bebida> bebidas = controller.listarBebidas();
                            System.out.println("BEBIDAS:");
                            for (Bebida bebida : bebidas) {
                                System.out.println(bebida.getNome() + "\nQuantidade Disponivel: " + bebida.getQtd() + "\nValor: R$" + bebida.getPreco());
                            }
                            break;

                        case 2:
                            List<Comida> comidas = controller.listarComidas();
                            System.out.println("\nCOMIDAS:");
                            for (Comida comida : comidas) {
                                System.out.println(comida.getNome() + " \nQuantidade Disponivel: " + comida.getQtd()+ " \nValor: R$ " + comida.getPreco());
                            }
                            break;
                        case 0:
                            menu = false;
                            break;

                        default:
                            System.out.println("Opção inválida. Por favor, escolha 1 para Bebidas ou 2 para Comidas.");
                            break;
                        }

                    break;
                
                case 4:

                    boolean subMenuProdutos = true;
                    while (subMenuProdutos) {
                        System.out.println("\nSelecione a operação desejada para produtos:"
                                + "\n1 - cadastrar novo produto"
                                + "\n2 - Repor Estoque"
                                + "\n3 - Atualizar informações de um produto"
                                + "\n4 - Excluir um produto"
                                + "\n0 - Voltar");
                
                        int cadProdutos = in.nextInt();
                        in.nextLine();
                
                        switch (cadProdutos) {
                            case 1:
                            while (menu) {
                                System.out.println("\nSelecione o que deseja fazer: "
                                        + "\n1 - Cadastrar nova comida"
                                        + "\n2 - Cadastrar nova bebida"
                                        + "\n0 - Sair");
                    
                                int opcao = in.nextInt();
                                in.nextLine();
                    
                                switch (opcao) {
                                    case 1:
                                        System.out.println("Digite o nome da comida:");
                                        String nomeComida = in.nextLine();
                                        System.out.println("Digite a categoria da comida:");
                                        String categoriaComida = in.nextLine();
                                        System.out.println("Digite o preço da comida:");
                                        float precoComida = in.nextFloat();
                                        System.out.println("Digite a quantidade da comida:");
                                        int qtdComida = in.nextInt();
                                        in.nextLine();
                    
                                        controller.cadastrarProduto(nomeComida, categoriaComida, precoComida, qtdComida);
                                        break;
                                    case 2:
                                        System.out.println("Digite o nome da bebida:");
                                        String nomeBebida = in.nextLine();
                                        System.out.println("Digite a categoria da bebida:");
                                        String categoriaBebida = in.nextLine();
                                        System.out.println("Digite o preço da bebida:");
                                        float precoBebida = in.nextFloat();
                                        System.out.println("Digite a quantidade da bebida:");
                                        int qtdBebida = in.nextInt();
                                        in.nextLine();
                                        System.out.println("É alcoólica? (true/false):");
                                        boolean alcoolica = in.nextBoolean();
                                        in.nextLine(); 
                    
                                        controller.cadastrarProduto(nomeBebida, categoriaBebida, precoBebida, qtdBebida, alcoolica);
                                        break;
                                    case 0:
                                        menu = false;
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Tente novamente.");
                                        break;
                                }
                
                            System.out.println("Sessão encerrada.");
                            }
                    
                                break;
                            case 2:
                                System.out.println("Digite o nome do produto que deseja repor o estoque:");
                                String nomeProduto = in.nextLine();
                                System.out.println("Digite a quantidade a ser adicionada ao estoque:");
                                int quantidade = in.nextInt();
                                in.nextLine();
                                
                                controller.reporEstoque(nomeProduto, quantidade);
                                System.out.println("Estoque de " + nomeProduto + " atualizado com sucesso.");
                                break;
                            case 3:
                                int alterar = in.nextInt();
                                in.nextLine();
                
                                switch (alterar) {
                                case 1:
                                    System.out.println("Digite o nome do produto que deseja alterar:");
                                    String nome = in.nextLine();
                                    
                                    
                                    Produto produtoExistente = controller.buscarProduto(nome).orElse(null);
                                    if (produtoExistente == null) {
                                        System.out.println("Produto não encontrado.");
                                    } else {                             
                                    System.out.println("Selecione o que deseja alterar:"
                                            + "\n1 - Nome"
                                            + "\n2 - Categoria"
                                            + "\n3 - Preço"
                                            + "\n0 - Voltar");
                
                                    int opcaoAlt = in.nextInt();
                                    in.nextLine(); 
                
                                    switch (opcaoAlt) {
                                        case 1:
                                            System.out.println("Digite o novo nome:");
                                            String novoNome = in.nextLine();
                                            controller.alterarNomeProduto(produtoExistente, novoNome);
                                            break;
                                        case 2:
                                            System.out.println("Digite a nova categoria:");
                                            String novaCategoria = in.nextLine();
                                            controller.alterarCategoriaProduto(produtoExistente, novaCategoria);
                                            break;
                                        case 3:
                                            System.out.println("Digite o novo preço:");
                                            float novoPreco = in.nextFloat();
                                            controller.alterarPrecoProduto(produtoExistente, novoPreco);
                                            break;
                                    }
                                break;
                                    }
                                }
                            case 4:
                                System.out.println("Digite o nome do produto que deseja excluir:");
                                String nomeProdutoExcluir = in.nextLine();
   
                                Optional<Produto> produtoOptional = controller.buscarProduto(nomeProdutoExcluir);
                                if (produtoOptional.isPresent()) {
                                    Produto produtoParaExcluir = produtoOptional.get();
                                    controller.excluirProduto(produtoParaExcluir);
                                } else {
                                    System.out.println("Produto não encontrado.");
                                }
                                break;
                            case 0:
                                subMenuProdutos = false;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }
                    break;
                case 5:
                                System.out.println("Digite o nome do produto que deseja ativar ou desativar:");
                                String nome = in.nextLine();
                                System.out.println("Digite 1 para ativar ou 0 para desativar:");
                                int opcao = in.nextInt();
                                boolean ativar = (opcao == 1);
                                in.nextLine();
                            
                                controller.ativarDesativarProduto(nome, ativar);
                                System.out.println("Produto " + nome + " " + (ativar ? "ativado" : "desativado") + " com sucesso.");
                            break;
                case 0:
                    System.out.println("Sessão encerrada.");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }    
    }
}