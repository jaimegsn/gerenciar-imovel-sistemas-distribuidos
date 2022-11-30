package edu.sistemas_distribuidos.segunda_entrega.local;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    static DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Enviando Mensagem:
    public static void sendRequest(byte[] request) {
        try {
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(request,
                    request.length, ip, 5000);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Recebendo Resposta:
    public static byte[] getReply() {
        try {
            byte[] response = new byte[1024];
            DatagramPacket packet = new DatagramPacket(response,
                    response.length);
            socket.receive(packet);
            String data = new String(packet.getData());
            socket.close();
            return data.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
