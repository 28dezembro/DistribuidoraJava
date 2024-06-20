package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recibo {
    private static final File ARQUIVO = new File("src/recibo/recibo.txt");

    public static void gravar(String texto) throws Exception{
        List<String> textos = new ArrayList<>();

        try {
            ARQUIVO.getParentFile().mkdirs();

            if (ARQUIVO.exists()) textos = ler();
            textos.add(texto);
            
            FileWriter file = new FileWriter(ARQUIVO);
            PrintWriter print = new PrintWriter(file);

            for (String string : textos) {
                print.println(string);
            }
            print.close();
            
        } catch (Exception e) {
            throw new Exception("Erro ao gravar o arquivo");
        }
    }

    public static List<String> ler() throws Exception{
        List<String> textos = new ArrayList<>();
        try {
            Scanner scan = new Scanner(ARQUIVO);
            while (scan.hasNextLine()) {
                textos.add(scan.nextLine());
            }
            scan.close();
            return textos;
        } catch (Exception e) {
            throw new Exception("Não foi possível ler o arquivo " + e.getMessage());
        }
    }
}
