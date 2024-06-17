package controller;

import java.util.List;
import java.util.Optional;

import model.Venda;
import model.produto.*;
import model.usuario.*;

public class DistribuidoraController {
    private List<Produto> produtos;
    private List<Usuario> usuarios;
    private List<Venda> vendas;

    public DistribuidoraController(List<Produto> produtos, List<Usuario> usuarios, List<Venda> vendas) {
        this.produtos = produtos;
        this.usuarios = usuarios;
        this.vendas = vendas;
    }

    private int gerarCodigoVenda(){
        return vendas.stream()
        .mapToInt(v -> v.getCodigo())
        .max()
        .orElse(0) + 1;
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

    public Optional<Venda> buscarVenda(int codigo){
        return vendas.stream()
        .filter(p -> String.valueOf(p.getCodigo()).equals(String.valueOf(codigo)))
        .findFirst();
    }

    public Optional<Usuario> buscarUsuario(String login){
        return usuarios.stream()
        .filter(u -> u.getLogin().equals(login))
        .findFirst();
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd){
        if (!buscarProduto(nome).isPresent()) {
            produtos.add(Comida.cadastrarComida(nome, categoria, preco, gerarCodigoProduto(), qtd));
        }
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd, boolean alcoolico){
        if (!buscarProduto(nome).isPresent()) {
            produtos.add(Bebida.cadastrarBebida(nome, categoria, preco, gerarCodigoProduto(), qtd, alcoolico));
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd){
        if (buscarUsuario(login).isEmpty()) {
            usuarios.add(UsuarioComum.cadastrarUsuarioComum(nomeCompleto, login, senha, endereco, telefone, ddd));
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha){
        if (buscarUsuario(login).isEmpty()) {
            usuarios.add(Admin.cadastrarAdmin(nomeCompleto, login, senha));
        }
    }

    private void cadastrarVenda(int codigo, List<Produto> produtos, UsuarioComum usuario, float valorVenda){
        if (buscarVenda(codigo).isEmpty()) {
            vendas.add(Venda.cadastrarVenda(codigo, produtos, usuario, valorVenda));
        }
    }

    public Produto adicionarAoCarrinho(Produto produto, int quantidade) throws Exception {
        Produto produtoEstoque = buscarProduto(produto.getNome()).get();
        if (produtoEstoque.getQtd() < quantidade) {
            throw new Exception("Quantidade indisponível");
        }
        produto.setQtd(quantidade);
        return produto;
    }

    private void reduzirEstoque(List<Produto> carrinho) {
        for (Produto produtoCarrinho : carrinho) {
            for (Produto p : produtos) {
                if (p.equals(produtoCarrinho)) {
                    p.reduzirEstoque(produtoCarrinho.getQtd());
                }
            }
        }
    }

    public void finalizarVenda(List<Produto> carrinho, UsuarioComum usuario){
        float valorTotal = 0f;
        for (Produto produto : carrinho) {
            valorTotal += produto.getPreco();
        }

        cadastrarVenda(gerarCodigoVenda(), carrinho, usuario, valorTotal);
        reduzirEstoque(carrinho);
        usuario.getCarrinho().clear();
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "DistribuidoraController [produtos=" + produtos + ", usuarios=" + usuarios + "]";
    }

    
    
}
