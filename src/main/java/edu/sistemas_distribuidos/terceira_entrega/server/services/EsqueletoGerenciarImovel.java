package edu.sistemas_distribuidos.terceira_entrega.server.services;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;

public class EsqueletoGerenciarImovel {
    public static byte[] cadastrarImovel(byte[] objeto){
        Imovel imovel = new Gson().fromJson(new String(objeto), Imovel.class);
        return GerenciarImovel.cadastrarImovel(imovel).getBytes();
    }

}
