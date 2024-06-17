import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.DistribuidoraController;
import model.produto.*;
import model.usuario.*;
import model.*;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        boolean login = true;
        String usuario, senha;
        Usuario usuarioSelecionado;

        Scanner in = new Scanner(System.in);

        DistribuidoraController controller = new DistribuidoraController(new ArrayList<Produto>(), new ArrayList<Usuario>(), new ArrayList<Venda>());

        //Populando o sistema
        controller.cadastrarProduto("Campo Largo", "Vinho", 14.90f, 1, true);
        controller.cadastrarProduto("Passatempo", "Bolacha", 4.90f, 1);
        
        controller.cadastrarUsuario("André Lucas", "Andre", "123123");
        controller.cadastrarUsuario("André lucas", "Andre", "null", "rua tchurusbago", 998998989, 41); //teste pra ver se inclui usuario duplicado (mesmo login)
        controller.cadastrarUsuario("André lucas", "Lucas", "null", "rua tchurusbago", 998998989, 41);
        //Fim da população

        while (login) {
            System.out.println("\nDigite seu login: ");
            usuario = in.nextLine();

            if (controller.buscarUsuario(usuario).isPresent()) {
                usuarioSelecionado = controller.buscarUsuario(usuario).get();
                System.out.println("\nOlá: " + usuarioSelecionado.getNomeCompleto()
                + "\nDigite sua senha:");
                senha = in.nextLine();

                if (usuarioSelecionado.getSenha().equals(senha)) {
                    try {
                        if (usuarioSelecionado.isAdmin()) {
                            AdminView.main(args, in, controller, (Admin) usuarioSelecionado);
                        }else{
                            UsuarioComumView.main(args, in, controller, (UsuarioComum) usuarioSelecionado);
                        }    
                    } catch (InputMismatchException e) {
                        throw new Exception("Erro ao selecionar usuário" + e.getMessage());
                    }
                }
            }
        }
    }
}
