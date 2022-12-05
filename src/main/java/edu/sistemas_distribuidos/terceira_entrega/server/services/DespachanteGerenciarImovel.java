package edu.sistemas_distribuidos.terceira_entrega.server.services;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.message.Mensagem;

import java.lang.reflect.Method;

public class DespachanteGerenciarImovel {
    public static byte[] selecionarEsqueleto(Mensagem mensagem) {
        Class<?> objRef = null;
        Method method = null;
        byte[] resposta = null;
        Mensagem retorno = mensagem;
        String pacote = "edu.sistemas_distribuidos.terceira_entrega.server.services.";

        try {
            objRef = Class.forName(pacote + "EsqueletoGerenciar" + mensagem.getRemoteObjectRef());
            method = objRef.getMethod(mensagem.getMethodId(), byte[].class);

            //Definindo parâmetros
            Object[] parametros = new Object[1];
            parametros[0] = mensagem.getArguments();

            // Invocando método que irá retornar um array de bytes
            resposta = (byte[]) method.invoke(objRef, parametros);

            // Trocando atributos
            retorno.setMessageType(1);
            retorno.setArguments(resposta);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(retorno).concat("$$").getBytes();
    }
}
