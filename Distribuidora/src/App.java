import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.produto.*;
import model.usuario.*;
import util.Util;
import model.*;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        boolean login = true;
        String usuario, senha;
        Usuario usuarioSelecionado;
        int opcao;
        
        Scanner in = new Scanner(System.in);
        DistribuidoraController controller = new DistribuidoraController(new ArrayList<Produto>(), new ArrayList<Usuario>(), new ArrayList<Venda>());

        while (login) {
            System.out.println("\nSelecione uma opção:"
                            + "\n1. Faça Login"
                            + "\n2. Crie seu cadastro");

            try {
                opcao = in.nextInt();
                in.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\nDigite seu login: ");
                        usuario = Util.formataString(in.nextLine());

                        if (controller.buscarUsuario(usuario).isPresent()) {
                            usuarioSelecionado = controller.buscarUsuario(usuario).get();
                            System.out.println("\nOlá, " + usuarioSelecionado.getNomeCompleto() + "\nDigite sua senha:");
                            senha = in.nextLine();

                            if (usuarioSelecionado.getSenha().equals(senha)) {
                                try {
                                    if (usuarioSelecionado.isAdmin()) {
                                        AdminView.main(args, in, controller, (Admin) usuarioSelecionado);
                                    } else {
                                        UsuarioComumView.main(args, in, controller, (UsuarioComum) usuarioSelecionado);
                                    }
                                } catch (Exception e) {
                                    System.err.println("Erro ao selecionar usuário: " + e.getMessage());
                                }
                                break;
                            } else {
                                System.out.println("\nSenha incorreta. Tente novamente.");
                            }
                        } else {
                            System.out.println("\nUsuário não encontrado. Tente novamente.");
                        }
                        break;

                    case 2:
                        CadastroUsuarioView.main(args, in, controller);
                        break;

                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Valor inserido incorreto");
                in.nextLine(); // Limpar o buffer
            }

        }
    }
}
