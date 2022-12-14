package edu.sistemas_distribuidos.terceira_entrega.server.services;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Parametro;

public class EsqueletoGerenciarImovel {
    public static byte[] cadastrarImovel(byte[] objeto){
        Imovel imovel = new Gson().fromJson(new String(objeto), Imovel.class);
        return GerenciarImovel.cadastrarImovel(imovel).getBytes();
    }

    public static byte[] buscarImovel(byte[] objeto){
        Parametro parametro = new Gson().fromJson(new String(objeto), Parametro.class);
        return GerenciarImovel.buscarImovel(parametro).getBytes();
    }

    public static byte[] editarImovel (byte[] objeto){
        Parametro parametro = new Gson().fromJson(new String(objeto), Parametro.class);
        return GerenciarImovel.editarImovel(parametro).getBytes();
    }

    public static byte[] removerImovel (byte[] objeto){
        Parametro parametro = new Gson().fromJson(new String(objeto), Parametro.class);
        return GerenciarImovel.removerImovel(parametro).getBytes();
    }

}
