package edu.sistemas_distribuidos.segunda_entrega.server;

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
    public static byte[] getRequest() {
        try {
            socket.receive(packetReceived);
            return packetReceived.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendReply(byte[] response){
        try {
            InetAddress ipClient = packetReceived.getAddress();
            int portaClient = packetReceived.getPort();

            DatagramPacket sendPacket = new DatagramPacket(response, response.length, ipClient, portaClient);
            socket.send(sendPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendReply(getRequest());
        /*
        try (DatagramSocket tomadaServidor = new DatagramSocket(5000);) {


            byte[] cartaAReceber = new byte[1024];
            DatagramPacket envelopeAReceber = new DatagramPacket(cartaAReceber,
                    cartaAReceber.length);
            tomadaServidor.receive(envelopeAReceber);
            String textoRecebido = new String(envelopeAReceber.getData());
            System.out.println(textoRecebido);

            //Enviar Mensagem ao Cliente:
            byte[] response;
            response = textoRecebido.getBytes();
            InetAddress ipClient = envelopeAReceber.getAddress();
            int portaClient = envelopeAReceber.getPort();

            DatagramPacket envelopeAEnviar = new DatagramPacket(response,
                    response.length, ipClient, portaClient);
            tomadaServidor.send(envelopeAEnviar);

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
