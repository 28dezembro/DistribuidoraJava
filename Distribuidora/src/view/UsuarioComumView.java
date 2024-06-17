package view;

import java.util.Scanner;
import controller.DistribuidoraController;
import model.produto.Produto;
import model.usuario.UsuarioComum;

public class UsuarioComumView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, UsuarioComum usuarioSelecionado) throws Exception{
        boolean menu = true;
        int opcoes, produtoBuscarQtd;
        String produtoBuscar;
        Produto produtoAdicionar;

        System.out.println("Login feito com sucesso, " + usuarioSelecionado.getNomeCompleto());

        while (menu) {
            System.out.println("\nSelecione o que deseja fazer: "
            + "\n1 - Buscar produtos"
            + "\n2 - Visualizar carrinho");
            opcoes = in.nextInt();
            in.nextLine(); // consome caracter

            switch (opcoes) {
                case 1:
                    System.out.println("Que produto deseja buscar?");
                    produtoBuscar = in.nextLine();

                    if (controller.buscarProduto(produtoBuscar).isPresent()) {
                        produtoAdicionar = controller.buscarProduto(produtoBuscar).get();

                        if (produtoAdicionar.getQtd() > 0) {
                            System.out.println("\n" + produtoAdicionar.produtoDisponivel());
                            System.out.println("Deseja Buscar o item ao carrinho?(S/N)");

                            if (in.nextLine().equalsIgnoreCase("S")) {
                                System.out.println("Qual a quantidade desejada? (Quantidade disponível: "+ produtoAdicionar.getQtd() +")");
                                produtoAdicionar.setQtd(in.nextInt());

                                controller.adicionarAoCarrinho(produtoAdicionar);

                            }
                        }else{
                            System.out.println("Sem estoque do produto no momento.");
                        }
                    }
                    
                    break;

                case 2:
                    if (usuarioSelecionado.getCarrinho().isEmpty()) {
                        System.out.println("Não há nenhum item no seu carrinho");
                    }
                    System.out.println(usuarioSelecionado.getCarrinho());
                    
                    break;

                case 3:
                    
                    break;
                    
                case 4:
                    
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
