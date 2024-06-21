package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.Venda;

public class SerializacaoVenda {
    private static final File ARQUIVO = new File("src/obj/venda.ser");

    public static void salvar(List<Venda> lista) throws Exception{
        try {
            ARQUIVO.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo" + e.getMessage());
        }
    }

    public static List<Venda> ler() throws Exception{
        try {
            if (ARQUIVO.exists()) {
                if (ARQUIVO.isFile()) {
                    ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO));
    
                    return (List<Venda>) ois.readObject();
                } else {
                    throw new Exception("O caminho especificado não é um arquivo");
                }
            } else {
                throw new Exception("O arquivo não existe");
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo: " + e.getMessage());
        }
    }
}
