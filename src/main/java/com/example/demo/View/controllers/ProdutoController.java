package com.example.demo.View.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.View.model.ProdutoRequest;
import com.example.demo.View.model.ProdutoResponse;
import com.example.demo.services.ProdutoServices;
import com.example.demo.shared.ProdutoDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoServices produtoServices;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){
        List<ProdutoDTO> produto =  produtoServices.obterTodos();
        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse> resposta = produto.stream().map(produtoDto -> mapper.map(produto,ProdutoResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(resposta,HttpStatus.OK);    
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
        Optional<ProdutoDTO> dto = produtoServices.obterPorId(id);
        
        ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
        
        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);
        produtoDto = produtoServices.adicionar(produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto,ProdutoResponse.class),HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(Integer id){
    produtoServices.deletar(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest ProdutoReq,@PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(ProdutoReq,ProdutoDTO.class);
        produtoDto = produtoServices.atualizar(id, produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto,ProdutoResponse.class),HttpStatus.OK);
    }
}
