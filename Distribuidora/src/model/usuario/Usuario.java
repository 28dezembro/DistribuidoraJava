package model.usuario;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nomeCompleto, login, senha;
    protected boolean admin;

    public Usuario(String nomeCompleto, String login, String senha, boolean admin) {
        this.nomeCompleto = nomeCompleto;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Usuario [nomeCompleto=" + nomeCompleto + ", login=" + login + ", senha=" + senha + ", admin=" + admin + "]";
    }

}
