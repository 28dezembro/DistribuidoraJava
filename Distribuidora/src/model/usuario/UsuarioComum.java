package model.usuario;

import java.util.ArrayList;
import java.util.List;

import model.produto.*;

public class UsuarioComum extends Usuario{
    protected String endereco;
    protected int telefone, ddd;
    protected List<Produto> carrinho;

    public UsuarioComum(String nomeCompleto, String login, String senha, boolean admin, String endereco, int telefone, int ddd, List<Produto> carrinho) {
        super(nomeCompleto, login, senha, admin);
        this.endereco = endereco;
        this.telefone = telefone;
        this.ddd = ddd;
        this.carrinho = carrinho;
    }

    public static UsuarioComum getUsuarioComum(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd){
        return new UsuarioComum(nomeCompleto, login, senha, false, endereco, telefone, ddd, new ArrayList<>());
    }

    public void adicionarAoCarrinho(Produto produto){
        carrinho.add(produto);
    }

    public void removerDoCarrinho(Produto produto){
        carrinho.remove(produto);
    }

    public void limparCarrinho(){
        carrinho.clear();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "UsuarioComum ["+super.toString()+"endereco=" + endereco + ", telefone=" + telefone + ", ddd=" + ddd + ", carrinho=" + carrinho + "]";
    }
    
}
