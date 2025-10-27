package br.edu.unicesumar.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// ANOTAÇÃO CORRETA para ter ID e ser uma tabela separada
@Entity
@Table(name="endereco_tb1")
public class Endereco {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_endereco")
    private int id; // MANTÉM O ID

    @Column(name="logradouro")
    private String logradouro;

    @Column(name="numero", nullable= false)
    private String numero;

    @Column(name="bairro")
    private String bairro;

    @Column(name="cidade")
    private String cidade;

    @Column(name="estado")
    private String estado;

    @Column(name="cep", nullable= false)
    private String cep;

    public Endereco(){}

    public Endereco(int id, String logradouro, String numero, String bairro, String cidade, String estado, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}