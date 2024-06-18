package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.produto.Produto;
import model.usuario.UsuarioComum;


public class UsuarioComumView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, UsuarioComum usuarioSelecionado) throws Exception{
        boolean menu = true;
        int opcoes;
        String produtoBuscar;
        Produto produtoAdicionar;
        List<Produto> carrinho = new ArrayList<>();

        System.out.println("Login feito com sucesso, " + usuarioSelecionado.getNomeCompleto());

        while (menu) {
            System.out.println("\nSelecione o que deseja fazer: "
            + "\n1 - Buscar produtos"
            + "\n2 - Visualizar carrinho"
            + "\n3 - Meus pedidos");
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
                                    carrinho.add(controller.adicionarAoCarrinho(produtoAdicionar, in.nextInt()));
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
                    if (carrinho.isEmpty()) {
                        System.out.println("\nNão há nenhum item no seu carrinho");
                        break;
                    }

                    System.out.println("\n");
                    for (Produto produtoCarrinho : carrinho) {
                        System.out.println(produtoCarrinho.imprimeProduto());
                    }

                    System.out.println("Deseja fechar o carrinho e finalizar a venda?(S/N)");
                        if (in.nextLine().equalsIgnoreCase("S")) {
                            controller.finalizarVenda(carrinho, usuarioSelecionado);
                            
                        }
                    break;

                case 3:
                    System.out.println(usuarioSelecionado.getPedidos());
                    break;
                    
                case 4:
                    carrinho.clear();
                    break;

                case 5:
                    
                    break;

                case 6:
                    
                    break;

                default:

                    break;
            }
            
        }

    }
    
}
