package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.DistribuidoraController;
import model.usuario.*;

public class AlterarCadastroAdminView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado) throws Exception {
        boolean menuAdmin = true, alteraCadastro;
        int opcaoMenu, opcaoAlterar;
        String alteraInformacao, buscarUsuarioAlterar, usuarioExcluir;
        Usuario passaUsuarioAlterar;

        while (menuAdmin) {
            System.out.println(
                    "\n1 - Alterar seu cadastro" +
                            "\n2 - Alterar cadastro de outro usuário" +
                            "\n3 - Deletar um usuário" +
                            "\n0 - Voltar");
            try {
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
                                    System.out.println("Para qual nome gostaria de mudar?(Digite CANCELAR para interromper)");
                                    alteraInformacao = in.nextLine();
                                    if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                                        usuarioSelecionado.setNomeCompleto(alteraInformacao);
                                        System.out.println("Nome alterado com sucesso!");
                                        break;
                                    }
                                    break;

                                case 2:
                                    System.out.println("Para qual login gostaria de mudar?(Digite CANCELAR para interromper)");
                                    alteraInformacao = in.nextLine();
                                    if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                                        usuarioSelecionado.setLogin(alteraInformacao);
                                        System.out.println("Login alterado com sucesso!");
                                        break;
                                    }
                                    break;

                                case 3:
                                    System.out.println("Confirme sua senha: ");
                                    if (in.nextLine().equals(usuarioSelecionado.getSenha())) {
                                        System.out.println("Digite a nova senha:");
                                        controller.alterarSenhaUsuario(usuarioSelecionado.getLogin(), in.nextLine());
                                        System.out.println("Senha alterada com sucesso!");
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

                    case 3:
                        System.out.println("Digite o login do usuário que deseja excluir:");
                        usuarioExcluir = in.nextLine();
                        if (controller.buscarUsuario(usuarioExcluir).isPresent()) {
                            System.out.println("Confirma a deleção do usuario " + usuarioExcluir + "?(S/N)");
                            if (in.nextLine().equalsIgnoreCase("S")) {
                                try {
                                    controller.excluirUsuario(usuarioExcluir);
                                    System.out.println("Usuario deletado com sucesso");
                                } catch (Exception e) {
                                    System.out.println("Erro ao deletar" + e.getMessage());
                                }
                            }
                        } else {
                            System.out.println("Usuário não encontrado");
                        }
                        break;

                    default:
                        menuAdmin = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Valor inserido incorreto");
                in.nextLine(); // Limpar o buffer
            }

        }
    }
}
