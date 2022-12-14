package edu.sistemas_distribuidos.terceira_entrega.server;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.message.Mensagem;
import edu.sistemas_distribuidos.terceira_entrega.server.services.DespachanteGerenciarImovel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class UDPServer {

    public static DatagramSocket socket;
    public static DatagramPacket packetReceived;

    public static byte[] messageReceived;

    private static Set<byte[]> lastsReq = new HashSet<>();

    static {
        try {
            socket = new DatagramSocket(5000);
            messageReceived = new byte[1024];
            packetReceived = new DatagramPacket(messageReceived, messageReceived.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recebendo Mensagem do Cliente
    private static byte[] getRequest() {
        try {
            socket.receive(packetReceived);
            return packetReceived.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sendReply(byte[] response) {

        try {
            InetAddress ipClient = packetReceived.getAddress();
            int portaClient = packetReceived.getPort();
            DatagramPacket sendPacket = new DatagramPacket(response, response.length, ipClient, portaClient);
            socket.send(sendPacket);
            lastsReq.clear();
            getRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Mensagem desempacotaMensagem(byte[] request) {
        StringBuilder sb = new StringBuilder();
        for (char i : new String(request).toCharArray()) {
            if (i != '$') sb.append(i);
            else break;
        }
        return new Gson().fromJson(String.valueOf(sb), Mensagem.class);
    }


    public static void main(String[] args) {
        while (true) {
            if (fluxo().equals("close"))
                break;
        }
    }

    public static String fluxo() {
        byte[] mensagemEmpacotada = getRequest();


        if (mensagemEmpacotada != null && !lastsReq.contains(mensagemEmpacotada)) {
            lastsReq.add(mensagemEmpacotada);
            Mensagem mensagem = desempacotaMensagem(mensagemEmpacotada);
            if (new String(mensagem.getArguments()).equals("closeSocket")) {
                try {
                    socket.close();
                    return "close";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            switch (mensagem.getRemoteObjectRef()) {
                case "Imovel":
                    sendReply(DespachanteGerenciarImovel.selecionarEsqueleto(mensagem));
                    //lastReq = null;
                    break;
            }
        }
        return "";
    }
}
