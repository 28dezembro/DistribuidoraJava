package model.usuario;


public class Admin extends Usuario {

    private Admin(String nomeCompleto, String login, String senha, boolean admin) {
        super(nomeCompleto, login, senha, admin);
    }

    public static Admin getAdmin(String nomeCompleto, String login, String senha){
        return new Admin(nomeCompleto, login, senha, true);
    }

    @Override
    public String toString() {
        return "Admin ["+super.toString()+"]";
    }

    
    
}
