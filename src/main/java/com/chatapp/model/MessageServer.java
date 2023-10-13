/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.model;

//import javax.swing.JTextPane;

/**
 *
 * @author Admin
 */
public class MessageServer {
   private int id, idChannel, idMessageType;
    protected Object content;
    private String time, receiver;

    public MessageServer(int id, int idChannel, int idMessageType, Object content, String time, String receiver) {
        this.id = id;
        this.idChannel = idChannel;
        this.idMessageType = idMessageType;
        this.content = content;
        this.time = time;
        this.receiver = receiver;
    }
    
    
//    protected abstract boolean send();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getIdMessageType() {
        return idMessageType;
    }

    public void setIdMessageType(int idMessageType) {
        this.idMessageType = idMessageType;
    }
    
    
}
