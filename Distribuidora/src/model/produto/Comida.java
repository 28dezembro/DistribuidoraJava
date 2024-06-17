package model.produto;

public class Comida extends Produto {

    private Comida(String nome, String categoria, float preco, int codigo, int qtd) {
        super(nome, categoria, preco, codigo, qtd);
    }

    public static Comida cadastrarComida(String nome, String categoria, float preco, int codigo, int qtd) {
        return new Comida(nome, categoria, preco, codigo, qtd);
    }

    public String produtoDisponivel(){
        return categoria + " " + nome + ":"
        + "\nPreço: " + preco;
    }

    @Override
    public String toString() {
        return "Comida ["+ super.toString()+"]";
    }
    
}
