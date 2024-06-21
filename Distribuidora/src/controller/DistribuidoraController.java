package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Venda;
import model.produto.*;
import model.usuario.*;
import util.Recibo;
import util.SerializacaoProduto;
import util.SerializacaoUsuario;
import util.SerializacaoVenda;

public class DistribuidoraController {
    private List<Produto> estoque;
    private List<Usuario> usuarios;
    private List<Venda> vendas;

    public DistribuidoraController(List<Produto> estoque, List<Usuario> usuarios, List<Venda> vendas) {
        this.estoque = estoque;
        this.usuarios = usuarios;
        this.vendas = vendas;
        try {
            carregarDados();
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados, " + e.getMessage());
        }
    }

    //Serializacao
    public void salvarDados() throws Exception{
        SerializacaoProduto.salvar(estoque);
        SerializacaoVenda.salvar(vendas);
        SerializacaoUsuario.salvar(usuarios);
    }

    private void carregarDados() throws Exception{
        estoque = SerializacaoProduto.ler();
        vendas = SerializacaoVenda.ler();
        usuarios = SerializacaoUsuario.ler();
    }

    //Produtos
    private int gerarCodigoProduto(){
        return estoque.stream()
        .mapToInt(p -> p.getCodigo())
        .max()
        .orElse(0) + 1;
    }

    public Optional<Produto> buscarProduto(String nome){
        return estoque.stream()
        .filter(produtoEstoque -> produtoEstoque.getNome().equalsIgnoreCase(nome))
        .findFirst();
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd) throws Exception{
        if (buscarProduto(nome).isEmpty()) {
            try {
                estoque.add(Comida.cadastrarComida(nome, categoria, preco, gerarCodigoProduto(), qtd));
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar o produto" + e.getMessage());
            }
        }else{
            throw new Exception("Produto já existente em estoque");
        }
    }

    public void cadastrarProduto(String nome, String categoria, float preco, int qtd, boolean alcoolico) throws Exception{
        if (buscarProduto(nome).isEmpty()) {
            try {
                estoque.add(Bebida.cadastrarBebida(nome, categoria, preco, gerarCodigoProduto(), qtd, alcoolico));
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar o produto" + e.getMessage());
            }   
        }else{
            throw new Exception("Produto já existente em estoque");
        }
    }

    private Produto copiarProduto(Produto produto) throws Exception {
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

    public void reporEstoque(String nomeProduto, int quantidade) throws Exception {
        try {
            buscarProduto(nomeProduto).get().aumentarEstoque(quantidade);
            salvarDados();
        } catch (Exception e) {
            throw new Exception("Não foi possível repor o estoque do produto" + e.getMessage());
        }
    }

    public List<Bebida> listarBebidas() {
        return estoque.stream()
        .filter(p -> p instanceof Bebida)
        .map(p -> (Bebida) p)
        .collect(Collectors.toList());
    }

    public List<Comida> listarComidas() {
        return estoque.stream()
        .filter(p -> p instanceof Comida)
        .map(p -> (Comida) p)
        .collect(Collectors.toList());
    }

    public void alterarNomeProduto(String nome, String alteraNome) throws Exception {
        buscarProduto(nome).get().setNome(alteraNome);
        salvarDados();
    }

    public void alterarCategoriaProduto(String nome, String alteraCategoria) throws Exception {
        buscarProduto(nome).get().setCategoria(alteraCategoria);
        salvarDados();
    }

    public void alterarPrecoProduto(String nome, float alteraPreco) throws Exception {
        buscarProduto(nome).get().setPreco(alteraPreco);
        salvarDados();
    }

    public void excluirProduto(String nome) throws Exception{
        if (buscarProduto(nome).get().getQtd() == 0) {
            try {
                estoque.remove(buscarProduto(nome).get());
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Erro inesperado ao excluir produto" + e.getMessage());
            }
        } else {
            throw new Exception("Impossível excluir o produto pois seu estoque não está zerado");
        }
    }

    //Usuario
    public Optional<Usuario> buscarUsuario(String login){
        return usuarios.stream()
        .filter(usuarioSistema -> usuarioSistema.getLogin().equalsIgnoreCase(login))
        .findFirst();
    } 

    public void cadastrarUsuario(String nomeCompleto, String login, String senha, String endereco, int telefone, int ddd) throws Exception{
        if (buscarUsuario(login).isEmpty()) {
            try {
                usuarios.add(UsuarioComum.cadastrarUsuarioComum(nomeCompleto, login, senha, endereco, telefone, ddd));
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar o usuário" + e.getMessage());
            }
        }else{
            throw new Exception("Usuário já existe no sistema");
        }
    }

    public void cadastrarUsuario(String nomeCompleto, String login, String senha) throws Exception{
        if (buscarUsuario(login).isEmpty()) {
            try {
                usuarios.add(Admin.cadastrarAdmin(nomeCompleto, login, senha));
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar o usuário" + e.getMessage());
            }
        }else{
            throw new Exception("Usuário já existe no sistema");
        }
    }

    public void alterarSenhaUsuario(String login, String novaSenha) throws Exception {
        try {
            if (buscarUsuario(login).isPresent()) {
                buscarUsuario(login).get().setSenha(novaSenha);
                salvarDados();
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível alterar a senha do usuário" + e.getMessage());
        }
    }

    public void excluirUsuario(String login) throws Exception{
        try {
            usuarios.removeIf(u -> u.getLogin().equals(login));
            salvarDados();
        } catch (Exception e) {
            throw new Exception("Não foi possível remover o usuário: " + e.getMessage());
        }
    }
    
    public Produto adicionarProdutoCarrinho(Produto produto, int quantidade) throws Exception {
        Produto produtoEstoque = buscarProduto(produto.getNome()).get();

        if (produtoEstoque.getQtd() >= quantidade) {
            try {
                Produto produtoCarrinho = copiarProduto(produtoEstoque);
                produtoCarrinho.setQtd(quantidade);
                return produtoCarrinho;
            } catch (Exception e) {
                throw new Exception("Não possível adicionar o produto no carrinho");
            }
        }else{
            throw new Exception("Quantidade indisponível");
        }
    }

    //Venda
    private int gerarCodigoVenda(){
        return vendas.stream()
        .mapToInt(v -> v.getCodigo())
        .max()
        .orElse(0) + 1;
    }

    public Optional<Venda> buscarVenda(int codigo){
        return vendas.stream()
        .filter(vendasEstoque -> String.valueOf(vendasEstoque.getCodigo()).equals(String.valueOf(codigo)))
        .findFirst();
    }

    private Venda cadastrarVenda(int codigo, List<Produto> produtos, UsuarioComum usuario, float valorVenda) throws Exception{
        if (buscarVenda(codigo).isEmpty()) {
            Venda novaVenda = null;
            try {
                novaVenda = Venda.cadastrarVenda(codigo, produtos, usuario, valorVenda);
                vendas.add(novaVenda);
                usuario.getPedidos().add(novaVenda);
                salvarDados();
            } catch (Exception e) {
                throw new Exception("Não foi possível cadastrar a venda" + e.getMessage());
            } 
            return novaVenda;
        } else {
            throw new Exception("Venda já existe no sistema");
        }
    }

    public float calculaTotalVenda(List<Produto> carrinho) throws Exception{
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
                estoque.stream()
                    .filter(produtoEstoque -> produtoEstoque.getCodigo() == produtoCarrinho.getCodigo())
                    .findFirst()
                    .ifPresent(produtoEstoque -> produtoEstoque.reduzirEstoque(produtoCarrinho.getQtd()));
            });

            //finalmente cadastre a venda
            Venda novaVenda = cadastrarVenda(gerarCodigoVenda(), produtosVenda, usuario, calculaTotalVenda(produtosVenda));
            String recibo = novaVenda.geraReciboVenda(novaVenda);
            Recibo.gravar(recibo);
            salvarDados();
        } catch (Exception e) {
            throw new Exception("Não foi possível finalizar a venda" + e.getMessage());
        }
    }
    
    public String imprimeEstoque(){
        return estoque.stream()
        .map(produtoEstoque -> produtoEstoque.imprimeProdutoEstoque())
        .collect(Collectors.joining("\n"));
    }

    public String imprimePedidos(){
        return vendas.stream()
        .map(venda -> venda.geraReciboVenda(venda))
        .collect(Collectors.joining("\n"));
    }

    public List<Produto> getEstoque() {
        return estoque;
    }

    public void setProdutos(List<Produto> estoque) {
        this.estoque = estoque;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "DistribuidoraController [produtos=" + estoque + ", usuarios=" + usuarios + "]";
    }
 
}
