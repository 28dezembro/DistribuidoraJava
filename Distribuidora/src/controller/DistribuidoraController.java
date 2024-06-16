package controller;

import java.util.List;
import java.util.Optional;

import model.produto.*;
import model.usuario.*;

public class DistribuidoraController {
    private List<Produto> produtos;
    private List<Usuario> usuarios;

    public DistribuidoraController(List<Produto> produtos, List<Usuario> usuarios) {
        this.produtos = produtos;
        this.usuarios = usuarios;
    }

    private int gerarCodigoProduto(){
        return produtos.stream()
        .mapToInt(p -> p.getCodigo())
        .max()
        .orElse(0) + 1;
    }

    public Optional<Produto> buscarProduto(String nome){
        return produtos.stream()
        .filter(p -> p.getNome().equals(nome))
        .findFirst();
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd){
        if (!buscarProduto(nome).isPresent()) {
            produtos.add(Comida.getComida(nome, categoria, preco, gerarCodigoProduto(), qtd));
        }
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd, boolean alcoolico){
        if (!buscarProduto(nome).isPresent()) {
            produtos.add(Bebida.getBebida(nome, categoria, preco, gerarCodigoProduto(), qtd, alcoolico));
        }
    }

    public void venderProduto(){

    }

    public Optional<Usuario> buscarUsuario(String login){
        return usuarios.stream()
        .filter(u -> u.getLogin().equals(login))
        .findFirst();
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd){
        if (!buscarUsuario(login).isPresent()) {
            usuarios.add(UsuarioComum.getUsuarioComum(nomeCompleto, login, senha, endereco, telefone, ddd));
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha){
        if (!buscarUsuario(login).isPresent()) {
            usuarios.add(UsuarioAdmin.getUsuarioAdmin(nomeCompleto, login, senha));
        }
    }

    @Override
    public String toString() {
        return "DistribuidoraController [produtos=" + produtos + ", usuarios=" + usuarios + "]";
    }

    
    
}
