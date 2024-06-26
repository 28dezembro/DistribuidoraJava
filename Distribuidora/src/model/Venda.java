package model;

import java.io.Serializable;
import java.util.List;
import model.produto.*;
import model.usuario.UsuarioComum;


public class Venda implements Serializable {
    protected int codigo;
    protected List<Produto> produtos;
    protected UsuarioComum usuario;
    protected float valorVenda;

    public Venda(int codigo, List<Produto> produtos, UsuarioComum usuario, float valorVenda) {
        this.codigo = codigo;
        this.produtos = produtos;
        this.usuario = usuario;
        this.valorVenda = valorVenda;
    }

    public static Venda cadastrarVenda(int codigo, List<Produto> produtos, UsuarioComum usuario, float valorVenda){
        return new Venda(codigo, produtos, usuario, valorVenda);
    }

    public String geraReciboVenda(Venda venda){
        return "////////////////////////////////////////////////////"+
        "\nRecibo do Pedido n°" + venda.getCodigo() +
        "\nComprador: " + venda.getUsuario().getNomeCompleto() + 
        "\nContato: " + "("+venda.getUsuario().getDdd()+")" + venda.getUsuario().getTelefone() +
        "\nEndereço: " + venda.getUsuario().getEndereco() + 
        "\nProdutos : " + venda.getUsuario().imprimeProdutosVenda(venda);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public UsuarioComum getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioComum usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Venda [codigo=" + codigo + ", produtos=" + produtos + ", usuario=" + usuario + ", valorVenda=" + valorVenda + "]";
    }

}
