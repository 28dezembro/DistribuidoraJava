package model.produto;

public class Comida extends Produto {

    private Comida(String nome, String categoria, float preco, int codigo, int qtd) {
        super(nome, categoria, preco, codigo, qtd);
    }

    public static Comida cadastrarComida(String nome, String categoria, float preco, int codigo, int qtd) {
        return new Comida(nome, categoria, preco, codigo, qtd);
    }

    @Override
    public Comida copiarProduto(Produto produto){
        Comida comida = (Comida) produto;
        return new Comida(comida.getNome(), comida.getCategoria(), comida.getPreco(), comida.getCodigo(), comida.getQtd());
    }

    public String imprimeProduto(){
        return categoria + " " + nome + ":" + " R$" + preco;
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
