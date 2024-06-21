package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.produto.Produto;


public class SerializacaoProduto {
    private static final File ARQUIVO = new File("src/obj/produto.ser");

    public static void salvar(List<Produto> lista) throws Exception{
        try {
            ARQUIVO.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo" + e.getMessage());
        }
    }

    public static List<Produto> ler() throws Exception{
        try {
            if (ARQUIVO.exists() && ARQUIVO.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO));

                return (List<Produto>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo" + e.getMessage());
        }
    }
}
