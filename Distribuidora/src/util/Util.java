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
    
}
