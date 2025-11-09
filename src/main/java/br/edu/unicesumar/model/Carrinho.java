package br.edu.unicesumar.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinho_tb1")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_carrinho")
    private int id;


    @OneToMany(
        mappedBy = "carrinho",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public void addItem(ItemCarrinho item) {
        item.setCarrinho(this); // Define o carrinho do item
        this.itens.add(item);    // Adiciona o item à lista
    }

    public void removeItem(ItemCarrinho item) {
        this.itens.remove(item);
    }

}