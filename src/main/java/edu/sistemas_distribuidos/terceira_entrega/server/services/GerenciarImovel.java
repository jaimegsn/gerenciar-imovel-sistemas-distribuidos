package edu.sistemas_distribuidos.terceira_entrega.server.services;

import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;

public class GerenciarImovel {
    public static String cadastrarImovel(Imovel imovel){
        System.out.println("Servidor: imovel do proprietario " +imovel.getNomeProprietario()+ " Foi cadastrado");
        return "Mensagem enviada pelo servidor: Imovel Cadastrado";
    }
}
