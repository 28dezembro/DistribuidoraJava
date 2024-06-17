package model.produto;

public abstract class Produto {
    protected String nome, categoria;
    protected float preco;
    protected int codigo, qtd;

    public Produto(String nome, String categoria, float preco, int codigo, int qtd) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.codigo = codigo;
        this.qtd = qtd;
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

    public String produtoDisponivel(){
        return categoria + " " + nome + ":"
        + "\nPreço: " + preco
        + "\nQuantidade Disponível: " + qtd
        + "\nCódigo do Produto: " + codigo;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", categoria=" + categoria + ", preco=" + preco + ", codigo=" + codigo + ", qtd=" + qtd + "]";
    }

}