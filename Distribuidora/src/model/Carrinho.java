package model;

import java.util.List;
import model.produto.*;

public class Carrinho {
    private List<Produto> produtos;

    public Carrinho(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
