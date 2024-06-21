package model.produto;

public class Bebida extends Produto {
    protected boolean alcoolico;

    private Bebida(String nome, String categoria, float preco, int codigo, int qtd, boolean alcoolico) {
        super(nome, categoria, preco, codigo, qtd);
        this.alcoolico = alcoolico;
    }

    public static Bebida cadastrarBebida(String nome, String categoria, float preco, int codigo, int qtd, boolean alcoolico){
        return new Bebida(nome, categoria, preco, codigo, qtd, alcoolico);
    }

    @Override
    public Bebida copiarProduto(Produto produto){
        Bebida bebida = (Bebida) produto;
        return new Bebida(bebida.getNome(), bebida.getCategoria(), bebida.getPreco(), bebida.getCodigo(), bebida.getQtd(), bebida.isAlcoolico());
    }

    public boolean isAlcoolico() {
        return alcoolico;
    }

    public void setAlcoolico(boolean alcoolico) {
        this.alcoolico = alcoolico;
    }

    @Override
    public String toString() {
        return "Bebida [ "+ super.toString() + " alcoolico=" + alcoolico + "]";
    }
    
}
