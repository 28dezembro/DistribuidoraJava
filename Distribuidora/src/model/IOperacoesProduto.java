package model;

import model.produto.Produto;

public interface IOperacoesProduto {
    public abstract Produto copiarProduto(Produto produto) throws Exception;
}
