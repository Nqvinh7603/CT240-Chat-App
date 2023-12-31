/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.chatapp.view;

import com.chatapp.model.Account;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ChangeAvatarFrm extends javax.swing.JFrame {
    private Account account;
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;
    private String host = "localhost";
    private int port = 9999;
    private ChatFrm chatFrame;
    /**
     * Creates new form ChangeAvatarFrm
     */
    public ChangeAvatarFrm(ChatFrm chat, Account account, DataInputStream dis, DataOutputStream dos) {
        initComponents();
        initComponents();
        chatFrame = chat;
        this.account = account;
        input = dis;
        output = dos;
        labelAvatar.setIcon(new javax.swing.ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/chatapp/image" + account.getAvatar())).getScaledInstance(237, 237, Image.SCALE_SMOOTH)));
        setIconImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnSaveChanges = new javax.swing.JButton();
        btnChooseAvatar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelAvatar = new javax.swing.JLabel();
        labelAvatarText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 153));
        jPanel1.setLayout(null);

        btnBack.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(51, 51, 51));
        btnBack.setText("Back");
        btnBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack);
        btnBack.setBounds(320, 310, 73, 48);

        btnSaveChanges.setBackground(new java.awt.Color(30, 30, 30));
        btnSaveChanges.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnSaveChanges.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveChanges.setText("Save Changes");
        btnSaveChanges.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSaveChanges.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesActionPerformed(evt);
            }
        });
        jPanel1.add(btnSaveChanges);
        btnSaveChanges.setBounds(440, 310, 160, 48);

        btnChooseAvatar.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnChooseAvatar.setForeground(new java.awt.Color(51, 51, 51));
        btnChooseAvatar.setText("Choose new Avatar");
        btnChooseAvatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChooseAvatar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChooseAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseAvatarActionPerformed(evt);
            }
        });
        jPanel1.add(btnChooseAvatar);
        btnChooseAvatar.setBounds(310, 220, 260, 48);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        labelAvatar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.add(labelAvatar);
        labelAvatar.setBounds(10, 10, 237, 237);

        labelAvatarText.setFont(new java.awt.Font("Freestyle Script", 0, 48)); // NOI18N
        labelAvatarText.setForeground(new java.awt.Color(91, 90, 90));
        labelAvatarText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAvatarText.setText("Avatar");
        jPanel2.add(labelAvatarText);
        labelAvatarText.setBounds(15, 265, 230, 45);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 20, 260, 330);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(360, 20, 0, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        chatFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangesActionPerformed
        int tmp = JOptionPane.showConfirmDialog(rootPane, "Are you sure to save the changes?");
        if (tmp == 0) {
            changeAvatar();
        }
    }//GEN-LAST:event_btnSaveChangesActionPerformed

    private void btnChooseAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseAvatarActionPerformed
        String link = System.getProperty("user.dir");
        System.out.println(link);
        final JFileChooser fileDialog = new JFileChooser(link + "\\src\\comm\\chatapp\\image\\");
            int returnVal = fileDialog.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileDialog.getSelectedFile();
                labelAvatar.setIcon(new javax.swing.ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/chatapp/image/" + file.getName()))));
                account.setAvatar(file.getName());
            }
    }//GEN-LAST:event_btnChooseAvatarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChooseAvatar;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAvatar;
    private javax.swing.JLabel labelAvatarText;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("")));
    
    }

    private boolean changeAvatar() {
        ConnectToServer();
        try {
            output.writeUTF("Change Avatar");
            output.writeUTF(account.getUserName());
            output.writeUTF(account.getAvatar());
            String respon = input.readUTF();
            if (respon.equals("Update successful")) {
                JOptionPane.showMessageDialog(rootPane, "Change Avatar Successful");
                chatFrame.setAvatar(account.getAvatar());
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ChangeAvatarFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void ConnectToServer() {
        try {
            if (socket != null) {
                socket.close();
            }
            System.out.println("connect 1");
            socket = new Socket(host, port);
            System.out.println("connect 2");
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
