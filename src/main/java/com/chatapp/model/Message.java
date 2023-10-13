/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.model;

import javax.swing.JTextPane;

/**
 *
 * @author Admin
 */
public abstract class Message {
    private int id, idChannel, idMessageType;
    String time;
    String receiver;
    
    String sender;
    boolean yourMessage;

    public Message(String sender, String receiver, boolean yourMessage) {
        this.id = 0;
        this.idChannel = 0;
        this.idMessageType = 0;
        String realtime = java.time.LocalDateTime.now().toString().substring(0, 19);
        this.time = realtime;
        this.receiver = receiver;
        this.sender = sender;
        this.yourMessage = yourMessage;
    }
    
    public abstract void printMessage(String username, JTextPane chatWindows);

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isYourMessage() {
        return yourMessage;
    }

    public void setYourMessage(boolean yourMessage) {
        this.yourMessage = yourMessage;
    }

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
