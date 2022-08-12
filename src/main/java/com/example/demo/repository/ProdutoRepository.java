package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Produto;
//Interface JpaRepository utiliza o primeiro ponto como objeto(PRODUTO), o segundo como chave primária (Id)
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

} 
