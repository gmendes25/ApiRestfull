package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.shared.ProdutoDTO;

@Service
public class ProdutoServices {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para obter todos produtos da lista
     * @return Lista de produtos
     */
    public List<ProdutoDTO> obterTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());
    }
    
    /**
     * Metodo que retorna o produto encontrado pelo Id.
     * @param id do produto que será localizado.
     * @return Retorna um produto, caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        //Obter Optional de produto, caso esteja vazio será lançada uma exception.
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new RuntimeException();
        }
        //Convertendo o Optional em ProdutoDTO.
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        //Convertendo o ProdutoDTO para um Optional novamente.
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista
     * @param produto produto que será adicionado
     * @return Retorna o produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
       
        produtoDto.setId(null);
        //Criar um produto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //Converter o ProdutoDTO em Produto
        Produto produto = mapper.map(produtoDto,Produto.class);
        //Salvar o produto no banco de dados
        produto = produtoRepository.save(produto);
        //Obtendo Id atualizado do produto
        produtoDto.setId(produto.getId());
        //Retornando produto atualizado
        return produtoDto;
    }

    /**
     * Metodo para deletar um produto baseado no id
     * @param id Id do produto a ser deletado
     */
    public void deletar(Integer id){
        //Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        //Caso nao exista lançar exception
        if(produto.isEmpty()){
            throw new RuntimeException();
        }
        //Caso produto exista, deletar
        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualiar um produto
     * @param produto Produto que será atualizado
     * @return Produto após ser atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        //Passar o id para o produtoDto
        produtoDto.setId(id);
        //Criar objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        //Converter produtoDto em Produto
        Produto produto = mapper.map(produtoDto,Produto.class);
        //Atualizar o produto no Banco de Dados
        produtoRepository.save(produto);
        return produtoDto;
    }
}
