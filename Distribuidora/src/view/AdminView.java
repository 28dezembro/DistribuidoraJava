package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.usuario.Admin;

public class AdminView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado) throws Exception {
        boolean menu = true;
        int adminopcao;

        System.out.println("\nLogin feito com sucesso administrador, " + usuarioSelecionado.getNomeCompleto());
        System.out.println("------------Seja Bem-Vindo a distribuidora dos Guri------------");

        while (menu) {
            System.out.println(
                    "\nSelecione o que deseja fazer: "
                            + "\n1 - Alterar cadastro"
                            + "\n2 - Menu de produto"
                            + "\n3 - Listar todos os pedidos"
                            + "\n0 - Sair");

            try {
                adminopcao = in.nextInt();
                in.nextLine();

                switch (adminopcao) {
                    case 1:
                        AlterarCadastroAdminView.main(args, in, controller, usuarioSelecionado);
                        break;

                    case 2:
                        MenuProdutoView.main(args, in, controller, usuarioSelecionado);
                        break;

                    case 3:
                        System.out.println(controller.imprimePedidos());
                        break;

                    case 0:
                        System.out.println("Sessão encerrada.");
                        menu = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Valor inserido incorreto");
                in.nextLine(); // Limpar o buffer
            }

        }
    }
}