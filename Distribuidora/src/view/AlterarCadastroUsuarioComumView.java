package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.DistribuidoraController;
import model.usuario.*;

public class AlterarCadastroUsuarioComumView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, UsuarioComum usuarioSelecionado) throws Exception {
        boolean menu = true;
        int opcaoAlterar;
        String alteraInformacao;

        while (menu) {
            System.out.println(
                    "Selecione o que deseja alterar:"
                            + "\n1 - Alterar Nome"
                            + "\n2 - Alterar Login"
                            + "\n3 - Alterar Senha"
                            + "\n4 - Alterar Endereco"
                            + "\n5 - Alterar Telefone"
                            + "\n6 - Alterar DDD"
                            + "\n0 - Voltar");

            try {
                opcaoAlterar = in.nextInt();
                in.nextLine();

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

                    case 4:
                        System.out.println("Para qual endereço gostaria de mudar?(Digite CANCELAR para interromper)");
                        alteraInformacao = in.nextLine();
                        if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                            usuarioSelecionado.setEndereco(alteraInformacao);
                            System.out.println("Endereço alterado com sucesso!");
                            break;
                        }
                        break;

                    case 5:
                        System.out.println("Para qual telefone gostaria de mudar?(Digite CANCELAR para interromper)");
                        alteraInformacao = in.nextLine();
                        if (alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                            usuarioSelecionado.setTelefone(Integer.parseInt(alteraInformacao));
                            System.out.println("Telefone alterado com sucesso!");
                            break;
                        }
                        break;

                    case 6:
                        System.out.println("Para qual DDD gostaria de mudar?(Digite CANCELAR para interromper)");
                        alteraInformacao = in.nextLine();
                        if (!alteraInformacao.equalsIgnoreCase("CANCELAR")) {
                            usuarioSelecionado.setDdd(Integer.parseInt(alteraInformacao));
                            System.out.println("DDD alterado com sucesso!");
                            break;
                        }
                        break;

                    case 0:
                        menu = false;
                        break;

                    default:
                        System.out.println("Escolha uma opção válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Valor inserido incorreto");
                in.nextLine(); // Limpar o buffer
            }

        }

    }

}
