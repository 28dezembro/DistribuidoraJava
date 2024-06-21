package model.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Venda;
import model.produto.Produto;

public class UsuarioComum extends Usuario{
    protected String endereco;
    protected int telefone, ddd;
    protected List<Produto> carrinho;
    protected List<Venda> pedidos;

    public UsuarioComum(String nomeCompleto, String login, String senha, boolean admin, String endereco, int telefone, int ddd, List<Venda> pedidos, List<Produto> carrinho) {
        super(nomeCompleto, login, senha, admin);
        this.endereco = endereco;
        this.telefone = telefone;
        this.ddd = ddd;
        this.pedidos = pedidos;
        this.carrinho = carrinho;
    }

    public static UsuarioComum cadastrarUsuarioComum(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd){
        return new UsuarioComum(nomeCompleto, login, senha, false, endereco, telefone, ddd, new ArrayList<>(), new ArrayList<>());
    }

    public Optional<Produto> buscarProdutoCarrinho(String nome){
        return carrinho.stream()
        .filter(produtoCarrinho -> produtoCarrinho.getNome().equalsIgnoreCase(nome))
        .findFirst();
    }

    public String imprimeCarrinho(){
        String produtos = carrinho.stream()
        .map(produto -> produto.imprimeProduto())
        .collect(Collectors.joining("\n"));

        float valorTotal = 0f;
        for (Produto produto : carrinho) {
            valorTotal += produto.getPreco();
        }

        return produtos + "\nTotal R$" + valorTotal;
    }

    public String imprimeProdutosVenda(Venda venda) {
        float valorTotal = 0f;
        for (Produto produto : venda.getProdutos()) {
            valorTotal += produto.getPreco();
        }
        String produtos = venda.getProdutos().stream()
        .map(produto -> produto.imprimeProduto())
        .collect(Collectors.joining("\n"));

        return produtos + "\nTotal R$" + valorTotal;
    }

    public String imprimePedidos() {
        return pedidos.stream()
        .map(pedido -> pedido.geraReciboVenda(pedido))
        .collect(Collectors.joining("\n\n"));
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

    public List<Venda> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Venda> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "UsuarioComum [endereco=" + endereco + ", telefone=" + telefone + ", ddd=" + ddd + ", carrinho=" + carrinho +"]";
    }

}
