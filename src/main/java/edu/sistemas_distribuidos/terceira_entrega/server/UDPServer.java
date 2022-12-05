package edu.sistemas_distribuidos.terceira_entrega.server;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.message.Mensagem;
import edu.sistemas_distribuidos.terceira_entrega.server.services.DespachanteGerenciarImovel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    static DatagramSocket socket;
    static DatagramPacket packetReceived;
    static byte[] messageReceived;

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
            Mensagem mensagem = desempacotaMensagem(getRequest());
            switch (mensagem.getRemoteObjectRef()) {
                case "Imovel":
                    sendReply(DespachanteGerenciarImovel.selecionarEsqueleto(mensagem));
                    break;
            }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
