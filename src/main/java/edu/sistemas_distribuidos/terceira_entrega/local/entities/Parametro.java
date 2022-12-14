package edu.sistemas_distribuidos.terceira_entrega.local.entities;

public class Parametro {
    private int id;
    private Double preco;

    private Imovel imovel;

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Parametro(int id, Double preco) {
        this.id = id;
        this.preco = preco;
    }

    public Parametro(Double preco) {
        this.preco = preco;
        this.id = 0;
    }

    public Parametro(int id) {
        this.id = id;
        this.preco = 0D;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
