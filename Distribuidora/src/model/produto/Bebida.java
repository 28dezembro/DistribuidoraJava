package model.produto;

public class Bebida extends Produto {
    protected boolean alcoolico;

    private Bebida(String nome, String categoria, float preco, int codigo, int qtd, boolean alcoolico) {
        super(nome, categoria, preco, codigo, qtd);
        this.alcoolico = alcoolico;
    }

    public static Bebida getBebida(String nome, String categoria, float preco, int codigo, int qtd, boolean alcoolico){
        return new Bebida(nome, categoria, preco, codigo, qtd, alcoolico);
    }

    public boolean isAlcoolico() {
        return alcoolico;
    }

    public void setAlcoolico(boolean alcoolico) {
        this.alcoolico = alcoolico;
    }

    public String produtoDisponivel(){
        return categoria + " " + nome + ":"
        + "\nPreço: " + preco
        + "\nQuantidade Disponível: " + qtd
        + "\nCódigo do Produto: " + codigo;
    }

    @Override
    public String toString() {
        return "Bebida [ "+ super.toString() + " alcoolico=" + alcoolico + "]";
    }
    
}
