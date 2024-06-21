package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.usuario.Admin;

public class CadastroProdutoView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado) throws Exception {
        boolean menu = true, alcoolicoNovoProduto;
        int opcao, qtdNovoProduto;
        String nomeNovoProduto, categoriaNovoProduto, resposta;
        float precoNovoProduto;

        while (menu) {
            System.out.println(
                    "1 - Cadastrar nova comida"
                            + "\n2 - Cadastrar nova bebida"
                            + "\n0 - Sair");

            try {
                opcao = in.nextInt();
                in.nextLine();// Limpa buffer

                switch (opcao) {
                    case 1:
                        try {
                            System.out.println("Digite o nome da comida:");
                            nomeNovoProduto = in.nextLine();
                            System.out.println("Digite a categoria da comida:");
                            categoriaNovoProduto = in.nextLine();
                            System.out.println("Digite o preço da comida:");
                            precoNovoProduto = in.nextFloat();
                            System.out.println("Digite a quantidade da comida:");
                            qtdNovoProduto = in.nextInt();
                            in.nextLine();// Limpa buffer

                            try {
                                controller.cadastrarProduto(
                                        nomeNovoProduto, categoriaNovoProduto, precoNovoProduto, qtdNovoProduto);
                            } catch (Exception e) {
                                System.err.println("Erro ao cadastrar novo produto: " + e.getMessage());
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Valor inserido inválido para o campo");
                            in.nextLine(); // Consome a entrada inválida
                        }
                        break;

                    case 2:
                        try {
                            System.out.println("Digite o nome da bebida:");
                            nomeNovoProduto = in.nextLine();
                            System.out.println("Digite a categoria da bebida:");
                            categoriaNovoProduto = in.nextLine();
                            System.out.println("Digite o preço da bebida:");
                            precoNovoProduto = in.nextFloat();
                            System.out.println("Digite a quantidade da bebida:");
                            qtdNovoProduto = in.nextInt();
                            in.nextLine();// limpa o buffer
                            System.out.println("É alcoólica? (S/N):");
                            resposta = in.nextLine();
                            if (resposta.equalsIgnoreCase("S")) {
                                alcoolicoNovoProduto = true;
                            } else if (resposta.equalsIgnoreCase("N")) {
                                alcoolicoNovoProduto = false;
                            } else {
                                System.out.println("Valor booleano inválido (S/N)");
                                break;
                            }

                            try {
                                controller.cadastrarProduto(
                                        nomeNovoProduto, categoriaNovoProduto, precoNovoProduto, qtdNovoProduto,
                                        alcoolicoNovoProduto);
                            } catch (Exception e) {
                                System.err.println("Erro ao cadastrar novo produto: " + e.getMessage());
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Valor inserido inválido para o campo");
                            in.nextLine(); // Consome a entrada inválida
                        }
                        ;

                    case 0:
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
