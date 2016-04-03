package com.gothere.gothere.models;

import java.util.Date;

/**
 * Created by David on 4/2/2016.
 */
public class Item {
    int id;
    String produto;
    String descricao; //(string),
    Double valor_unitario; //(number),
    Date data_criacao; //(string),
    Date data_edicao; //(string),
    Date data_vigencia;// (string),

    public Item(int id, String produto, String descricao, Double valor_unitario, Date data_criacao, Date data_edicao, Date data_vigencia) {
        this.id = id;
        this.produto = produto;
        this.descricao = descricao;
        this.valor_unitario = valor_unitario;
        this.data_criacao = data_criacao;
        this.data_edicao = data_edicao;
        this.data_vigencia = data_vigencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getData_edicao() {
        return data_edicao;
    }

    public void setData_edicao(Date data_edicao) {
        this.data_edicao = data_edicao;
    }

    public Date getData_vigencia() {
        return data_vigencia;
    }

    public void setData_vigencia(Date data_vigencia) {
        this.data_vigencia = data_vigencia;
    }

    public String toString(){
        return this.id + ": " + this.produto + " - " + this.valor_unitario;
    }
    //fornecedor (string)


}
