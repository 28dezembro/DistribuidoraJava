package model;

import java.util.List;
import model.produto.*;
import model.usuario.UsuarioComum;

public class Venda {
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
