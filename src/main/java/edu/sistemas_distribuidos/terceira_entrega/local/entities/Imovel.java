package edu.sistemas_distribuidos.terceira_entrega.local.entities;

public class Imovel {
    private String nomeProprietario;
    private String endereco;
    private double preco;

    private int id;

    public Imovel(String nomeProprietario, String endereco, double preco) {
        this.nomeProprietario = nomeProprietario;
        this.endereco = endereco;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
