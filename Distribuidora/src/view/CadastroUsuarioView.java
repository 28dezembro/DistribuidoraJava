package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.DistribuidoraController;

public class CadastroUsuarioView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller) throws Exception {
        String usuario, senha, nomeCompleto, endereco;
        int telefone, ddd;
        boolean menuCadastro = true;

        System.out.println("Bem vindo novo usuário, vamos iniciar o cadastro. Por favor preencha o formulário: ");
        try {
            while (menuCadastro) {
                System.out.println("\nDigite seu nome completo: ");
                nomeCompleto = in.nextLine();
                System.out.println("\nDigite um login para sua conta: ");
                usuario = in.nextLine();
                System.out.println("\nDigite uma senha para sua conta: ");
                senha = in.nextLine();
                System.out.println("\nEm que endereço reside?: ");
                endereco = in.nextLine();
                System.out.println("\nTelefone para contato: ");
                telefone = in.nextInt();
                System.out.println("Qual o DDD do seu telefone?");
                ddd = in.nextInt();
                in.nextLine(); // Limpar o buffer

                try {
                    controller.cadastrarUsuario(nomeCompleto, usuario, senha, endereco, telefone, ddd);
                    System.out.println("\nUsuário cadastrado com sucesso!");
                    menuCadastro = false;
                } catch (Exception e) {
                    throw new Exception("Não foi possível fazer o cadastro, tente novamente" + e.getMessage());
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("Valor inserido incorreto");
            in.nextLine(); // Limpar o buffer
        }
    }

}
