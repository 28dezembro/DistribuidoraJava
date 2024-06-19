package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Venda;
import model.produto.*;
import model.usuario.*;

public class DistribuidoraController {
    private List<Produto> produtos;
    private List<Usuario> usuarios;
    private List<Venda> vendas;

    public DistribuidoraController(List<Produto> produtos, List<Usuario> usuarios, List<Venda> vendas) {
        this.produtos = produtos;
        this.usuarios = usuarios;
        this.vendas = vendas;
    }

    private int gerarCodigoVenda(){
        return vendas.stream()
        .mapToInt(v -> v.getCodigo())
        .max()
        .orElse(0) + 1;
    }

    private int gerarCodigoProduto(){
        return produtos.stream()
        .mapToInt(p -> p.getCodigo())
        .max()
        .orElse(0) + 1;
    }

    public Optional<Produto> buscarProduto(String nome){
        return produtos.stream()
        .filter(produtoEstoque -> produtoEstoque.getNome().equals(nome))
        .findFirst();
    }

    public Optional<Venda> buscarVenda(int codigo){
        return vendas.stream()
        .filter(vendasEstoque -> String.valueOf(vendasEstoque.getCodigo()).equals(String.valueOf(codigo)))
        .findFirst();
    }

    public Optional<Usuario> buscarUsuario(String login){
        return usuarios.stream()
        .filter(usuarioSistema -> usuarioSistema.getLogin().equals(login))
        .findFirst();
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd){
        if (buscarProduto(nome).isEmpty()) {
            produtos.add(Comida.cadastrarComida(nome, categoria, preco, gerarCodigoProduto(), qtd));
        }
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd, boolean alcoolico){
        if (buscarProduto(nome).isEmpty()) {
            produtos.add(Bebida.cadastrarBebida(nome, categoria, preco, gerarCodigoProduto(), qtd, alcoolico));
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd){
        if (buscarUsuario(login).isEmpty()) {
            usuarios.add(UsuarioComum.cadastrarUsuarioComum(nomeCompleto, login, senha, endereco, telefone, ddd));
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha){
        if (buscarUsuario(login).isEmpty()) {
            usuarios.add(Admin.cadastrarAdmin(nomeCompleto, login, senha));
        }
    }

    private void cadastrarVenda(int codigo, List<Produto> produtos, UsuarioComum usuario, float valorVenda) throws Exception{
        if (buscarVenda(codigo).isEmpty()) {
            try {
                Venda novaVenda = Venda.cadastrarVenda(codigo, produtos, usuario, valorVenda);
                vendas.add(novaVenda);
                usuario.getPedidos().add(novaVenda);
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar a venda" + e.getMessage());
            } 
        }
    }

    private Produto copiarProduto(Produto produto)throws Exception {
        if (produto instanceof Comida) {
            Comida comida = (Comida) produto;
            return comida.copiarProduto(produto);
            
        } else if (produto instanceof Bebida) {
            Bebida bebida = (Bebida) produto;
            return bebida.copiarProduto(produto);
        } else {
            throw new Exception("Produto não identificado");
        }
    }
    
    public void alterarSenhaUsuario(String login, String novaSenha) throws Exception {
        try {
            if (buscarUsuario(login).isPresent()) {
                buscarUsuario(login).get().setSenha(novaSenha);
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível alterar a senha do usuário" + e.getMessage());
        }
    }

    public void alterarTipoUsuario(String login, String novoTipo) {
        Optional<Usuario> usuarioOptional = buscarUsuario(login);
        usuarioOptional.ifPresent(usuario -> {
            if (usuario instanceof Admin) {
                ((Admin) usuario).setAdmin("admin".equals(novoTipo));
            }
        });
    }

    public void excluirUsuario(String login) {
        usuarios.removeIf(u -> u.getLogin().equals(login));
    }
    
    public void reporEstoque(String nomeProduto, int quantidade) {
        buscarProduto(nomeProduto).get().aumentarEstoque(quantidade);
    }
    
    public void ativarDesativarProduto(String nomeProduto, boolean ativar) {
        buscarProduto(nomeProduto).get().setAtivo(ativar);
    }
    
    public Produto adicionarProdutoCarrinho(Produto produto, int quantidade) throws Exception {
        Produto produtoEstoque = buscarProduto(produto.getNome()).get();
        if (produtoEstoque.getQtd() < quantidade) {
            throw new Exception("Quantidade indisponível");
        }
        Produto produtoCarrinho = copiarProduto(produtoEstoque);
        produtoCarrinho.setQtd(quantidade);
        return produtoCarrinho;
    }

    public void removeProdutoCarrinho(Produto produto){

    }
    
    public float calculaTotalVenda(List<Produto> carrinho){
        float valorTotal = 0f;
        for (Produto produto : carrinho) {
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }

    public void finalizarVenda(List<Produto> carrinho, UsuarioComum usuario) throws Exception{
        try {
            //Faz uma copia de todos os produtos do carrinho para cadastrar na venda
            List<Produto> produtosVenda = new ArrayList<>();
            for (Produto produto : carrinho) {
                produtosVenda.add(copiarProduto(produto));
            }

            //Reduz estoque da distribuidora baseado na quantidade de cada produto no carrinho;
            carrinho.stream()
            .forEach(produtoCarrinho -> {
                produtos.stream()
                    .filter(produtoEstoque -> produtoEstoque.getCodigo() == produtoCarrinho.getCodigo())
                    .findFirst()
                    .ifPresent(produtoEstoque -> produtoEstoque.reduzirEstoque(produtoCarrinho.getQtd()));
            });

            //finalmente cadastre a venda
            cadastrarVenda(gerarCodigoVenda(), produtosVenda, usuario, calculaTotalVenda(produtosVenda));
        } catch (Exception e) {
            throw new Exception("Não foi possível finalizar a venda" + e.getMessage());
        }
    }
    
    public List<Bebida> listarBebidas() {
        return produtos.stream()
        .filter(p -> p instanceof Bebida)
        .map(p -> (Bebida) p)
        .collect(Collectors.toList());
    }

    public List<Comida> listarComidas() {
        return produtos.stream()
        .filter(p -> p instanceof Comida)
        .map(p -> (Comida) p)
        .collect(Collectors.toList());
    }

    public void alterarNomeProduto(Produto produto, String novoNome) {
        produto.setNome(novoNome);
        System.out.println("Nome do produto alterado para " + novoNome);
    }

    public void alterarCategoriaProduto(Produto produto, String novaCategoria) {
        produto.setCategoria(novaCategoria);
        System.out.println("Categoria do produto alterada para " + novaCategoria);
    }

    public void alterarPrecoProduto(Produto produto, float novoPreco) {
        produto.setPreco(novoPreco);
        System.out.println("Preço do produto alterado para " + novoPreco);
    }

    public void excluirProduto(Produto produto) {
        
        if (!produtos.contains(produto)) {
            System.out.println("Produto não encontrado.");
            return;
        }
    
        if (produto.getQtd() == 0) {
            produtos.remove(produto);
            System.out.println("Produto " + produto.getNome() + " excluído com sucesso.");
        } else {
            System.out.println("Não é possível excluir o produto " + produto.getNome() + " porque o estoque não está zerado.");
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public String toString() {
        return "DistribuidoraController [produtos=" + produtos + ", usuarios=" + usuarios + "]";
    }
 
}
