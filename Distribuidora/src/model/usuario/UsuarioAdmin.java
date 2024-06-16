package model.usuario;


public class UsuarioAdmin extends Usuario {

    private UsuarioAdmin(String nomeCompleto, String login, String senha, boolean admin) {
        super(nomeCompleto, login, senha, admin);
    }

    public static UsuarioAdmin getUsuarioAdmin(String nomeCompleto, String login, String senha){
        return new UsuarioAdmin(nomeCompleto, login, senha, true);
    }

    @Override
    public String toString() {
        return "UsuarioAdmin ["+super.toString()+"]";
    }

    
    
}
