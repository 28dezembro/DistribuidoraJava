import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Optional;

import controller.DistribuidoraController;
import model.produto.*;
import model.usuario.*;
import model.*;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        boolean login = true;
        String usuario, senha, nomeCompleto, endereco;
        int telefone, idade;
        Usuario usuarioSelecionado;

        Scanner in = new Scanner(System.in);

        DistribuidoraController controller = new DistribuidoraController(new ArrayList<Produto>(), new ArrayList<Usuario>(), new ArrayList<Venda>());

        // Populando o sistema
        controller.cadastrarProduto("Campo Largo", "Vinho", 14.90f, 1, true);
        controller.cadastrarProduto("Passatempo", "Bolacha", 4.90f, 1);

        controller.cadastrarUsuario("André Lucas", "a", "a");
        controller.cadastrarUsuario("André lucas", "Andre", "null", "rua tchurusbago", 998998989, 41); // teste para ver se inclui usuario duplicado (mesmo login)
        controller.cadastrarUsuario("Marcão", "Marcos", "123", "rua tchurusbago", 998998989, 41);
        controller.cadastrarUsuario("thierry", "tito", "1234");
        // Fim da população

        while (login) {
            System.out.println("\n\nSelecione uma opção:");
            System.out.println("1. Faça Login");
            System.out.println("2. Crie seu cadastro");
            int opcao = in.nextInt();
            in.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.println("\n\nDigite seu login: ");
                    usuario = in.nextLine();

                    Optional<Usuario> usuarioOptional = controller.buscarUsuario(usuario);
                    if (usuarioOptional.isPresent()) {
                        usuarioSelecionado = usuarioOptional.get();
                        System.out.println("\nOlá, " + usuarioSelecionado.getNomeCompleto()
                                + "\nDigite sua senha:");
                        senha = in.nextLine();

                        if (usuarioSelecionado.getSenha().equals(senha)) {
                            try {
                                if (usuarioSelecionado.isAdmin()) {
                                    AdminView.main(args, in, controller, (Admin) usuarioSelecionado);
                                } else {
                                    UsuarioComumView.main(args, in, controller, (UsuarioComum) usuarioSelecionado);
                                }
                            } catch (InputMismatchException e) {
                                throw new Exception("Erro ao selecionar usuário: " + e.getMessage());
                            }
                        } else {
                            System.out.println("\nSenha incorreta. Tente novamente.");
                        }
                    } else {
                        System.out.println("\nUsuário não encontrado. Tente novamente.");
                    }
                    break;

                case 2:
                    System.out.println("\n\nDigite seu nome completo: ");
                    nomeCompleto = in.nextLine();
                    System.out.println("Digite seu login: ");
                    usuario = in.nextLine();
                    System.out.println("Digite sua senha: ");
                    senha = in.nextLine();
                    System.out.println("Digite seu endereço: ");
                    endereco = in.nextLine();
                    System.out.println("Digite seu telefone: ");
                    telefone = in.nextInt();
                    System.out.println("Digite sua idade: ");
                    idade = in.nextInt();
                    in.nextLine(); // Limpar o buffer

                    controller.cadastrarUsuario(nomeCompleto, usuario, senha, endereco, telefone, idade);
                    System.out.println("\nUsuário cadastrado com sucesso!");
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        }

        in.close();
    }
}
