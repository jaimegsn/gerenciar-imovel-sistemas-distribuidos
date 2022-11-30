package edu.sistemas_distribuidos.segunda_entrega.message;

public class Mensagem {
    private int messageType;
    private int Id = 0;
    private String remoteObjectRef;
    private String methodId;
    private byte[] arguments;

    public Mensagem(int messageType, int id, String remoteObjectRef, String methodId, byte[] arguments) {
        this.messageType = messageType;
        Id = id;
        this.remoteObjectRef = remoteObjectRef;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRemoteObjectRef() {
        return remoteObjectRef;
    }

    public void setRemoteObjectRef(String remoteObjectRef) {
        this.remoteObjectRef = remoteObjectRef;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public byte[] getArguments() {
        return arguments;
    }

    public void setArguments(byte[] arguments) {
        this.arguments = arguments;
    }
}
