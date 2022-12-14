package edu.sistemas_distribuidos.terceira_entrega.local;

import com.google.gson.Gson;
import edu.sistemas_distribuidos.terceira_entrega.message.Mensagem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

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
    public static byte[] getReply(int tentativa) {
        try {
            byte[] response = new byte[1024];
            DatagramPacket packet = new DatagramPacket(response,
                    response.length);
            socket.setSoTimeout(3000);
            socket.receive(packet);
            //String data = new String(packet.getData());
            return packet.getData();

            // Retransmissão:
        } catch (SocketTimeoutException e) {
            System.out.println("Tentativa numero " + tentativa + " Falhou");
            if (tentativa < 3) {
                return new Gson().toJson(new Mensagem(1,0,"FalhouRetransmissao",
                        "FalhouRetransmissao", "Falhou, Tentando Novamente...".getBytes())).getBytes();
            } else {
                System.out.println("ERRO TEMPO");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeSocket() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
