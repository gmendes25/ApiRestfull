package com.example.demo.View.model;

public class ProdutoResponse {
    
    //#region Atributes
    private Integer id;
    
    private String name;

    private Integer quantidade;

    private Double valor;
    //#endregion
    
    //#region Getters and Setters
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //#endregion

    //#region Constructor
    
    public ProdutoResponse() {
    }
    //#endregion
}
