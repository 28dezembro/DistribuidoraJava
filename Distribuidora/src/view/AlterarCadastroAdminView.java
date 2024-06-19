package view;

import java.util.Scanner;

import controller.DistribuidoraController;
import model.usuario.*;

public class AlterarCadastroAdminView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado)
            throws Exception {
        boolean menuAdmin = true, alteraCadastro;
        int opcaoMenu, opcaoAlterar;
        String alteraInformacao, buscarUsuarioAlterar;
        Usuario passaUsuarioAlterar;

        while (menuAdmin) {
            System.out.println(
                            "\n1 - Alterar seu cadastro" +
                            "\n2 - Alterar cadastro de outro usuário" +
                            "\n0 - Voltar");
            opcaoMenu = in.nextInt();
            in.nextLine();

            switch (opcaoMenu) {
                case 1:
                    alteraCadastro = true;
                    while (alteraCadastro) {
                        System.out.println(
                                "Selecione o que deseja alterar:"
                                        + "\n1 - Alterar Nome"
                                        + "\n2 - Alterar Login"
                                        + "\n3 - Alterar Senha"
                                        + "\n0 - Voltar");
                        opcaoAlterar = in.nextInt();
                        in.nextLine();// consome caracter

                        switch (opcaoAlterar) {
                            case 1:
                                System.out
                                        .println("Para qual nome gostaria de mudar?(Digite CANCELAR para interromper)");
                                alteraInformacao = in.nextLine();
                                if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                                    usuarioSelecionado.setNomeCompleto(alteraInformacao);
                                    break;
                                }
                                break;

                            case 2:
                                System.out.println(
                                        "Para qual login gostaria de mudar?(Digite CANCELAR para interromper)");
                                alteraInformacao = in.nextLine();
                                if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                                    usuarioSelecionado.setLogin(alteraInformacao);
                                    break;
                                }
                                break;

                            case 3:
                                System.out.println("Confirme sua senha: ");
                                if (in.nextLine().equals(usuarioSelecionado.getSenha())) {
                                    System.out.println("Digite a nova senha:");
                                    controller.alterarSenhaUsuario(usuarioSelecionado.getLogin(), in.nextLine());
                                    break;
                                }
                                System.out.println("Senha incorreta, tente novamente;");
                                break;

                            case 0:
                                alteraCadastro = false;
                                break;

                            default:
                                System.out.println("Escolha uma opção válida.");
                                break;
                        }

                    }
                    break;

                case 2:
                    System.out.println("Digite o login do usuário que gostaria de alterar:");
                    buscarUsuarioAlterar = in.nextLine();
                    if (controller.buscarUsuario(buscarUsuarioAlterar).isPresent()
                            && controller.buscarUsuario(buscarUsuarioAlterar).get() instanceof UsuarioComum) {
                        passaUsuarioAlterar = controller.buscarUsuario(buscarUsuarioAlterar).get();
                        AlterarCadastroUsuarioComumView.main(args, in, controller, (UsuarioComum) passaUsuarioAlterar);
                        break;
                    }
                    System.out.println("Usuario selecionado não existe ou é outro Admin.");
                    break;

                default:
                    menuAdmin = false;
                    break;
            }
        }
    }
}
