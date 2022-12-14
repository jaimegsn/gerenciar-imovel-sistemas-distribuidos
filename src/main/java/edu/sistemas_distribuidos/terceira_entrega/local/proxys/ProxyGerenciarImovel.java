package edu.sistemas_distribuidos.terceira_entrega.local.proxys;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.local.UDPClient;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Imovel;
import edu.sistemas_distribuidos.terceira_entrega.local.entities.Parametro;
import edu.sistemas_distribuidos.terceira_entrega.message.Mensagem;

import java.io.FileWriter;

public class ProxyGerenciarImovel {
    /**
     * Biblioteca GSON transforma o objeto imovel em uma String JSON e chamamos o método doOperation
     *
     * @param imovel
     * @return
     */
    public static String cadastrarImovel(Imovel imovel) {
        String imovelInJson = new Gson().toJson(imovel);
        return new String(doOperation("Imovel", "cadastrarImovel", imovelInJson.getBytes()));
    }

    public static String buscarImovel(Parametro parametro) {
        String parametroInJson = new Gson().toJson(parametro);
        return new String(doOperation("Imovel", "buscarImovel", parametroInJson.getBytes()));
    }

    public static String removerImovel(Parametro parametro) {
        String parametroInJson = new Gson().toJson(parametro);
        return new String(doOperation("Imovel", "removerImovel", parametroInJson.getBytes()));
    }

    public static String editarImovel(Parametro parametro, Imovel imovel) {
        parametro.setImovel(imovel);
        String parametroInJson = new Gson().toJson(parametro);
        return new String(doOperation("Imovel", "editarImovel", parametroInJson.getBytes()));
    }


    /**
     * Envia uma mensagem de requisição para o objeto remoto e retorna a resposta
     *
     * @param objectRef Objeto Remoto ( ex: imovel,aluno...)
     * @param method    nome do método que será chamado no servidor
     * @param args      argumentos para o método que será chamado no servidor
     * @return retorna a resposta do servidor
     */
    private static byte[] doOperation(String objectRef, String method, byte[] args) {
        byte[] request = empacotaMensagem(objectRef, method, args);

        // Envio
        UDPClient.sendRequest(request);
        if (!new String(args).equals("closeSocket")) {
            System.out.println("Aqui ->> " + new String(request));
            int tentativa = 1;
            Mensagem mensagem;
            while (true) {
                mensagem = desempacotaMensagem(UDPClient.getReply(tentativa));
                if(!mensagem.getMethodId().equals("FalhouRetransmissao"))
                    break;
                UDPClient.sendRequest(request);
                tentativa++;
            }
            return mensagem.getArguments();
        }
        return "Cliente Fechado".getBytes();
    }

    private static byte[] empacotaMensagem(String objectRef, String method, byte[] args) {
        Mensagem mensagem = new Mensagem(0, 0, objectRef, method, args);
        return new Gson().toJson(mensagem).concat("$$").getBytes();
    }

    private static Mensagem desempacotaMensagem(byte[] resposta) {
        char[] c = new String(resposta).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char i : c) {
            if (i != '$') sb.append(i);
            else break;
        }
        return new Gson().fromJson(String.valueOf(sb), Mensagem.class);
    }

    public static void closeSocket() {
        doOperation("noObject", "noMethod", "closeSocket".getBytes());
        UDPClient.closeSocket();
    }


    private static String testandoJson(String json) {
        try (FileWriter writer = new FileWriter("imovel.json")) {
            writer.write(json);
            writer.flush();
            return "Arquivo imovel.json criado";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Nada Aconteceu";
    }
}
