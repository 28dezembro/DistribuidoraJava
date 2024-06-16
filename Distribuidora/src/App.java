import java.util.ArrayList;

import controller.DistribuidoraController;
import model.produto.*;
import model.usuario.*;

public class App {
    public static void main(String[] args) throws Exception {
        DistribuidoraController controller = new DistribuidoraController(new ArrayList<Produto>(), new ArrayList<Usuario>());

        controller.cadastrarProduto("Campo Largo", "Vinho", 14.90f, 1, true);
        controller.cadastrarProduto("Passatempo", "Bolacha", 4.90f, 1);
        
        controller.cadastrarUsuario("André Lucas", "Andre", "123123");
        controller.cadastrarUsuario("André lucas", "Andre", "null", "rua tchurusbago", 998998989, 41);
        controller.cadastrarUsuario("André lucas", "Lucas", "null", "rua tchurusbago", 998998989, 41);

        

        System.out.println(controller);


    }
}
