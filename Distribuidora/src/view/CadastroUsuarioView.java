package view;

import java.util.Scanner;
import controller.DistribuidoraController;

public class CadastroUsuarioView {
    public static void main(String[] args, Scanner in, DistribuidoraController controller) throws Exception {
        String usuario, senha, nomeCompleto, endereco;
        int telefone, idade;
        boolean menuCadastro = true;

        while (menuCadastro) {
            System.out.println("\nDigite seu nome completo: ");
            nomeCompleto = in.nextLine();
            System.out.println("\nDigite seu login: ");
            usuario = in.nextLine();
            System.out.println("\nDigite sua senha: ");
            senha = in.nextLine();
            System.out.println("\nDigite seu endereço: ");
            endereco = in.nextLine();
            System.out.println("\nDigite seu telefone: ");
            telefone = in.nextInt();
            System.out.println("\nDigite sua idade: ");
            idade = in.nextInt();
            in.nextLine(); // Limpar o buffer
            
            try {
                controller.cadastrarUsuario(nomeCompleto, usuario, senha, endereco, telefone, idade);
                System.out.println("\nUsuário cadastrado com sucesso!");
                menuCadastro = false;
            } catch (Exception e) {
                throw new Exception("Não foi possível fazer o cadastro, tente novamente" + e.getMessage());
            }
            
        }

    }
    
}
