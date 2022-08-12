package com.example.demo.View.model;

public class ProdutoRequest {
     
    //#region Atributos
    
     private String name;
     
     private Integer quantidade;
     
     private Double valor;
     
     private String observacao;
     //#endregion
     
     //#region Getters and Setters
 
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
 
     public String getObservacao() {
         return observacao;
     }
 
     public void setObservacao(String observacao) {
         this.observacao = observacao;
     }
     //#endregion 
}
