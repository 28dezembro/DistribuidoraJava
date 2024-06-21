package util;

public class Util {

    public static String formataString(String string){
        return string.trim().toLowerCase();
    }

    public static String formataTelefone(String string){
        return string.trim().replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\-", "").replaceAll("\\\\D", "");
    }
    
    public static boolean validaEmail(String email){
        return email.contains("@") && email.contains(".") && email.indexOf("@") < email.lastIndexOf(".");
    }

    public static String imprimeStringFormatada(String string) {
        String originalString = "campo largo";
        String formattedString = "";
    
        String[] words = originalString.split(" ");
        for (String word : words) {
            formattedString += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return formattedString.trim();
    }
    
}
