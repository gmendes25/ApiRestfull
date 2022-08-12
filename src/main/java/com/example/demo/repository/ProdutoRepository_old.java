package com.example.demo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Produto;

public class ProdutoRepository_old {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId;

    /**
     * Retorna uma lista de produtos
     * @return Lista de produtos */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo Id.
     * @param id do produto que será localizado.
     * @return Retorna um produto, caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos.stream().filter(produto -> produto.getId()== id).findFirst();
    }

    /**
     * Metodo para adicionar produto na lista
     * @param produto produto que será adicionado
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);
        
        return produto;
    }

    /**
     * Metodo para deletar um produto baseado no id
     * @param id Id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualiar um produto
     * @param produto Produto que será atualizado
     * @return Produto após ser atualizado
     */
    public Produto atualizar(Produto produto){
        //Tenho que identificar o produto.
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if(produtoEncontrado == null){
            throw new InputMismatchException("Produto não encontrado");
        }
        //Remover o produto.
        deletar(produto.getId());
        //Adicionar o produto depois de atualizado.
        produtos.add(produto);
        return produto;
    }
}