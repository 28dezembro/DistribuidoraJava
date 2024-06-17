package view;

import java.util.Scanner;
import controller.DistribuidoraController;
import model.usuario.UsuarioComum;

public class UsuarioComumView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, UsuarioComum usuarioSelecionado) {
        boolean menu = true;
        int opcoes;
        String produto;

        System.out.println("Login feito com sucesso, " + usuarioSelecionado.getNomeCompleto());

        while (menu) {
            System.out.println("Selecione o que deseja fazer: "
            + "\n1 - Buscar produtos");
            opcoes = in.nextInt();
            in.nextLine(); // consome caracter

            switch (opcoes) {
                case 1:
                    System.out.println("Que produto deseja buscar?");
                    produto = in.nextLine();
                    if (controller.buscarProduto(produto).isPresent()) {
                        if (controller.buscarProduto(produto).get().getQtd() > 0) {
                            System.out.println("\n" + controller.buscarProduto(produto).get().produtoDisponivel());
                        }else{
                            System.out.println("Sem estoque do produto no momento.");
                        }
                    }
                    
                    break;

                case 2:
                    
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
