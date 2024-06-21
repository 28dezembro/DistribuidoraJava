package model.produto;

import java.io.Serializable;
import model.IOperacoesProduto;
import util.Util;

public abstract class Produto implements IOperacoesProduto, Serializable {
    protected String nome, categoria;
    protected float preco;
    protected int codigo, qtd;

    public Produto(String nome, String categoria, float preco, int codigo, int qtd) {
        this.nome = Util.formataString(nome);
        this.categoria = Util.formataString(categoria);
        this.preco = preco;
        this.codigo = codigo;
        this.qtd = qtd;
    }

    public boolean reduzirEstoque(int quantidade){
        if (qtd > 0) {
            qtd -= quantidade;
            return true;
        }
        return false;
    }

    public void aumentarEstoque(int quantidade) {
        this.qtd += quantidade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String imprimeProduto(){
        return categoria + " " + nome + ":" + " R$" + preco;
    }

    public String produtoDisponivel(){
        return categoria + " " + nome + ":"
        + "\nPreço: " + preco;
    }

    public String imprimeProdutoEstoque(){
        return "Codigo: " + codigo +
            "\nProduto " + nome + ":" +
            "\nCategoria: " + categoria + 
            "\nQuantidade em estoque: " + qtd +
            "\nPreco: R$" + preco +
            "\n///////////////////////////////////";
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", categoria=" + categoria + ", preco=" + preco + ", codigo=" + codigo + ", qtd=" + qtd + "]";
    }

}
