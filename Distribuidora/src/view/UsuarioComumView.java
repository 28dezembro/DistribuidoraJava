package view;

import java.util.List;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.produto.Bebida;
import model.produto.Comida;
import model.produto.Produto;
import model.usuario.UsuarioComum;


public class UsuarioComumView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, UsuarioComum usuarioSelecionado) throws Exception{
        boolean menu = true;
        int opcoes;
        String produtoBuscar, categoria;
        Produto produtoAdicionar;

        System.out.println("\nLogin feito com sucesso usuario, " + usuarioSelecionado.getNomeCompleto());
        System.out.println("------------Seja Bem-Vindo a distribuidora dos Guri------------");

        while (menu) {
            System.out.println("\nSelecione o que deseja fazer: "
            + "\n1 - Buscar produtos"
            + "\n2 - Visualizar carrinho"
            + "\n3 - Meus pedidos"
            + "\n4 - Listar Categoria's"
            + "\n0 - Sair");
            opcoes = in.nextInt();
            in.nextLine(); // consome caracter

            switch (opcoes) {
                case 1:
                    System.out.println("\nQue produto deseja buscar?");
                    produtoBuscar = in.nextLine();

                    if (controller.buscarProduto(produtoBuscar).isPresent()) {
                        produtoAdicionar = controller.buscarProduto(produtoBuscar).get();

                        if (produtoAdicionar.getQtd() > 0) {
                            System.out.println("\n" + produtoAdicionar.imprimeProduto());
                            System.out.println("Deseja adicionar o item ao carrinho?(S/N)");

                            if (in.nextLine().equalsIgnoreCase("S")) {
                                System.out.println("Qual a quantidade desejada? (Quantidade disponível: "+ produtoAdicionar.getQtd() +")");

                                try {
                                    usuarioSelecionado.getCarrinho().add(controller.adicionarAoCarrinho(produtoAdicionar, in.nextInt()));
                                    System.out.println(produtoAdicionar.getNome() + " adicionado ao carrinho com sucesso!");
                                } catch (Exception e) {
                                    System.out.println("\nErro ao adicionar ao carrinho " + e.getMessage());
                                }
                            }
                        }else{
                            System.out.println("Produto não encontrado ou sem estoque no momento.");
                        }
                    }
                break;

                case 2:
                    if (usuarioSelecionado.getCarrinho().isEmpty()) {
                        System.out.println("\nNão há nenhum item no seu carrinho");
                    }

                    System.out.println("\n");
                    for (Produto produto : usuarioSelecionado.getCarrinho()) {
                        System.out.println(produto.imprimeProduto());
                    }

                    System.out.println("Deseja fechar o carrinho e finalizar a venda?(S/N)");
                    if (in.nextLine().equalsIgnoreCase("S")) {
                        controller.finalizarVenda(usuarioSelecionado.getCarrinho(), usuarioSelecionado);
                    }
                    
                break;

                case 3:
                    System.out.println(controller.buscarVenda(1));
                    
                break;
                    
                case 4:
                    System.out.println("Qual Categoria você deseja Listar(Comida ou Bebida)?");
                    categoria = in.nextLine();

                    if (categoria.equalsIgnoreCase("Comida") ) {
                        List<Comida> comidas = controller.listarComidas();
                        System.out.println("\nAqui está as Comidas ativas em estoque:");
                            for (Comida comida : comidas) {
                                if (comida.getQtd() > 0) {
                                    System.out.println("Nome: " + comida.getNome());
                                    System.out.println("Quantidade Disponível: " + comida.getQtd());
                                    System.out.println("Valor: R$ " + comida.getPreco());
                                    System.out.println();
                                }
                            }
                    }else if(categoria.equalsIgnoreCase("Bebida")){
                        List<Bebida> bebidas = controller.listarBebidas();
                        System.out.println("\nAqui está as Bebidas ativas em estoque:");
                            for (Bebida bebida : bebidas) {
                                if (bebida.getQtd() > 0) {
                                    System.out.println("Nome: " + bebida.getNome());
                                    System.out.println("Quantidade Disponível: " + bebida.getQtd());
                                    System.out.println("Valor: R$ " + bebida.getPreco());
                                    System.out.println("Alcoólico: " + bebida.isAlcoolico());
                                    System.out.println();
                                }
                            }
                    }else {
                        System.out.println("Categoria inválida!");
                    }
                break;

                case 0:
                    System.out.println("Sessão encerrada.");
                    menu = false;
                break;

                default:
                    if (opcoes != 1 || opcoes != 2 || opcoes != 3 || opcoes != 4 || opcoes != 0) {
                        System.out.println("Opção Invalida, tente novamente!");
                    }
                break;
            } 
        }      
    }
}
