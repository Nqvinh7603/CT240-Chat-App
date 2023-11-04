/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.model;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Admin
 */
public class TextMessage extends Message{
    
    String message;

    public TextMessage(String message, String sender, String receiver, boolean yourMessage) {
        super(sender, receiver, yourMessage);
        this.message = message;
    }

    @Override
    public void printMessage(String sender, JTextPane chatWindows) {
        StyledDocument doc = chatWindows.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        SimpleAttributeSet left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);

        Style userStyle = doc.getStyle("User style");
        if (userStyle == null) {
            userStyle = doc.addStyle("User style", null);
            StyleConstants.setBold(userStyle, true);
        }

        if (yourMessage == true) {
            //StyleConstants.setForeground(userStyle, Color.RED);
            //doc.setParagraphAttributes(doc.getLength(), 1, right, false);
            // In ra tên người gửi
            try {
                doc.insertString(doc.getLength(), "You: ", userStyle);
            } catch (BadLocationException e) {
            }
        } else {
            try {
                doc.insertString(doc.getLength(), sender + ": ", userStyle);
            } catch (BadLocationException e) {
            }
        }

        Style messageStyle = doc.getStyle("Message style");
        if (messageStyle == null) {
            messageStyle = doc.addStyle("Message style", null);
            StyleConstants.setForeground(messageStyle, Color.BLACK);
            StyleConstants.setBold(messageStyle, false);
        }

        // In ra nội dung tin nhắn
        try {
            doc.insertString(doc.getLength(), message + "\n", messageStyle);
        } catch (BadLocationException e) {
        }
    }
}
