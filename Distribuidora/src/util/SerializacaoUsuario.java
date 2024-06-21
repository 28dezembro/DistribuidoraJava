package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import model.usuario.Usuario;

public abstract class SerializacaoUsuario {
    private static final File ARQUIVO = new File("src/obj/usuario.ser");

    public static void salvar(List<Usuario> lista) throws Exception{
        try {
            ARQUIVO.getParentFile().mkdirs();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
            oos.writeObject(lista);
            oos.close();

        } catch (Exception e) {
            throw new Exception("Não foi possível salvar o arquivo" + e.getMessage());
        }
    }

    public static List<Usuario> ler() throws Exception{
        try {
            if (ARQUIVO.exists() && ARQUIVO.isFile()) {
                ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(ARQUIVO));

                return (List<Usuario>) ois.readObject();
            }else{
                throw new Exception("Arquivo inválido"); 
            }
            
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo" + e.getMessage());
        }
    }
}
