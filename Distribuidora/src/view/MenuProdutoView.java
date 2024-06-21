package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.DistribuidoraController;
import model.usuario.Admin;

public class MenuProdutoView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller, Admin usuarioSelecionado) throws Exception {
        boolean menuProduto = true;
        String buscaAlteraProduto;
        int opcaoMenu, opcaoAlteraProduto, quantidadeReporEstoque;

        while (menuProduto) {
            System.out.println("\nSelecione a operação desejada para produtos:"
                    + "\n1 - Cadastrar novo produto"
                    + "\n2 - Repor Estoque"
                    + "\n3 - Atualizar informações de um produto"
                    + "\n4 - Excluir um produto"
                    + "\n5 - Listar estoque"
                    + "\n0 - Voltar");

            try {
                opcaoMenu = in.nextInt();
                in.nextLine();

                switch (opcaoMenu) {
                    case 1:
                        CadastroProdutoView.main(args, in, controller, usuarioSelecionado);
                        break;

                    case 2:
                        System.out.println("Que produto deseja repor o estoque?");
                        buscaAlteraProduto = in.nextLine();

                        if (controller.buscarProduto(buscaAlteraProduto).isPresent()) {
                            System.out.println("Digite a quantidade a ser adicionada ao estoque:");
                            try {
                                quantidadeReporEstoque = in.nextInt();
                                in.nextLine(); // limpa buffer
                                controller.reporEstoque(buscaAlteraProduto, quantidadeReporEstoque);
                                System.out.println("Estoque de " + buscaAlteraProduto + " atualizado com sucesso.");
                            } catch (InputMismatchException e) {
                                System.err.println("Erro: quantidade inválida. Tente novamente.");
                                in.nextLine(); // Consome a entrada inválida
                            } catch (Exception e) {
                                System.err.println("Não foi possível adicionar a quantidade desejada: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Produto não encontrado");
                        }

                        break;

                    case 3:
                        System.out.println("Que produto deseja alterar?");
                        buscaAlteraProduto = in.nextLine();

                        if (controller.buscarProduto(buscaAlteraProduto).isPresent()) {
                            System.out.println("Selecione o que deseja alterar:"
                                    + "\n1 - Nome"
                                    + "\n2 - Categoria"
                                    + "\n3 - Preço"
                                    + "\n0 - Voltar");
                            opcaoAlteraProduto = in.nextInt();
                            in.nextLine();// Limpa o buffer

                            switch (opcaoAlteraProduto) {
                                case 1:
                                    System.out.println("Digite o novo nome:");
                                    try {
                                        controller.alterarNomeProduto(buscaAlteraProduto, in.nextLine());
                                        System.out.println("Nome alterado com sucesso!");
                                    } catch (Exception e) {
                                        System.err.println("Erro ao alterar o nome: " + e.getMessage());
                                    }
                                    break;

                                case 2:
                                    System.out.println("Digite a nova categoria:");
                                    try {
                                        controller.alterarCategoriaProduto(buscaAlteraProduto, in.nextLine());
                                        System.out.println("Categoria alterada com sucesso!");
                                    } catch (Exception e) {
                                        System.err.println("Erro ao alterar a categoria: " + e.getMessage());
                                    }
                                    break;

                                case 3:
                                    System.out.println("Digite o novo preço:");
                                    try {
                                        controller.alterarPrecoProduto(buscaAlteraProduto, in.nextFloat());
                                        System.out.println("Preço alterado com sucesso!");
                                    } catch (InputMismatchException e) {
                                        System.err.println("Valor inserido inválido");
                                    } catch (Exception e) {
                                        System.err.println("Erro ao alterar preço: " + e.getMessage());
                                    }
                                    break;
                            }
                        }else {
                            System.out.println("Produto não encontrado");
                        }
                        break;

                    case 4:
                        System.out.println("Digite o nome do produto que deseja excluir:");
                        buscaAlteraProduto = in.nextLine();
                        if (controller.buscarProduto(buscaAlteraProduto).isPresent()) {
                            System.out.println("Digite DELETAR para deletar o produto: " + buscaAlteraProduto);
                            if (in.nextLine().equals("DELETAR")) {
                                try {
                                    controller.excluirProduto(buscaAlteraProduto);
                                    System.out.println("Produto deletado com sucesso!");
                                } catch (Exception e) {
                                    System.err.println("Erro ao excluir produto:" + e.getMessage());
                                }
                            } else {
                                System.out.println("Operação Cancelada");
                            }
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;

                    case 5:
                        System.out.println(controller.imprimeEstoque());
                        break;

                    case 0:
                        menuProduto = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Valor inserido incorreto");
                in.nextLine(); // Limpar o buffer
            }
        }
    }
}
