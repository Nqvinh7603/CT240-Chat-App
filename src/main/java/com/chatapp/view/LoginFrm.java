/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.chatapp.view;

import com.chatapp.model.Account;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LoginFrm extends javax.swing.JFrame {
    private Account account;
    private DataInputStream input;
    private DataOutputStream output;
    private String host = "localhost";
    private int port = 9999;
    private Socket socket;
    
    /**
     * Creates new form LoginFrm
     */
    public LoginFrm() {
        initComponents();
        setLocationRelativeTo(null);
        scaleImage();
        labelNotification.setVisible(false);
        txtPassword.setEchoChar('\u25cf');
    }
    public void scaleImage() {
        //Show password icon
        ImageIcon show_icon = new ImageIcon(getClass().getResource("/com/chatapp/Image/Iconoir-Team-Iconoir-Eye-empty.32.png"));
        Image showicon_img = show_icon.getImage();
        Image showIconImgScale = showicon_img.getScaledInstance(lbShow.getWidth(), lbShow.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon showIconScaledIcon = new ImageIcon(showIconImgScale);
        lbShow.setIcon(showIconScaledIcon);

        //Hide password icon
        ImageIcon hide_icon = new ImageIcon(getClass().getResource("/com/chatapp/Image/Iconoir-Team-Iconoir-Eye-off.32.png"));
        Image hideicon_img = hide_icon.getImage();
        Image hideIconImgScale = hideicon_img.getScaledInstance(lbHide.getWidth(), lbHide.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon hideIconScaledIcon = new ImageIcon(hideIconImgScale);
        lbHide.setIcon(hideIconScaledIcon);
    }
    // kiem tra ten tai khoan co chua khoang trong khong
    public boolean checkUsername(String name) {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (ch == ' ') {
                return false;
            }

        }
        return true;
    }

    public String check() {

        if (txtUserName.getText().equals("") && txtPassword.getText().equals("")) {
            return "Please enter username and password.";
        } else if (txtUserName.getText().equals("")) {
            return "Please enter your password.";
        } else if (txtPassword.getText().equals("")) {
        } else if (txtPassword.getText().length() < 8) {
            return "Password must be at least 8 characters.\n";
        } else if (!checkUsername(txtUserName.getText())) {
            return "Username does not contain spaces.";
        }
        return "OK";
    }

    public String login(Account a) {
        try {
            connectToServer();
            output.writeUTF("Log in");
            output.writeUTF(a.getUserName());
            output.writeUTF(a.getPassword());
            output.flush();
            String response;
            response = input.readUTF();
            //String linkavatar = input.readUTF();
            account.setAvatar("");
            System.out.println("da" + response);
            return response;
        } catch (IOException e) {
            return "Network error: " + e.getMessage();
        }
    }

    public String signup(Account acc) {
        try {
            connectToServer();
            output.writeUTF("Sign up");
            output.writeUTF(acc.getUserName());
            output.writeUTF(acc.getPassword());
            output.flush();
            String response = input.readUTF();
            return response;
        } catch (IOException e) {
            return "Network error: " + e.getMessage();
        }
    }
    public void connectToServer() {
        try {
            if (socket != null) {
                socket.close();
            }
            socket = new Socket(host, port);
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        lbHide = new javax.swing.JLabel();
        lbShow = new javax.swing.JLabel();
        labelNotification = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHAT APP CT240");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Password");

        txtUserName.setForeground(new java.awt.Color(102, 102, 102));
        txtUserName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        txtPassword.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSignUp.setText("Sign up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        lbHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/chatapp/Image/Iconoir-Team-Iconoir-Eye-off.32.png"))); // NOI18N
        lbHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbHideMousePressed(evt);
            }
        });

        lbShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/chatapp/Image/Iconoir-Team-Iconoir-Eye-empty.32.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnSignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(396, 396, 396))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))))
            .addGroup(layout.createSequentialGroup()
                .addGap(856, 856, 856)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(183, 183, 183))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lbShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHide))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(329, 329, 329))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(176, 176, 176)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(lbHide)
                                            .addComponent(lbShow))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)))
                                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1)
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(labelNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        if (!check().equals("OK")) {
            labelNotification.setVisible(true);
            labelNotification.setForeground(Color.red);
            labelNotification.setText(check());
        } else {
            labelNotification.setVisible(false);
            account = new Account();
            account.setUserName(txtUserName.getText());
            account.setPassword(txtPassword.getText());
            String response = login(account);
            if (response.equals("Login successful")) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            ChatFrm frame = new ChatFrm(account, input, output);
                            frame.setVisible(true);
                            setVisible(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                labelNotification.setVisible(true);
                btnLogin.setEnabled(true);
                btnSignUp.setEnabled(true);
                txtPassword.setText("");
                labelNotification.setText(response);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // TODO add your handling code here:
        if (check().equals("OK")) {
            labelNotification.setVisible(false);
            Account newUser = new Account();
            newUser.setUserName(txtUserName.getText());
            newUser.setPassword(txtPassword.getText());
            String response = signup(newUser);
            if (response.equals("Sign up successful")) {
                JOptionPane.showMessageDialog(rootPane, "Sign up successful");
                labelNotification.setText(" ");
            } else {
                labelNotification.setVisible(true);
                labelNotification.setText(response);
            }
        } else {
            labelNotification.setVisible(true);
            labelNotification.setText(check());
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!check().equals("OK")) {
                labelNotification.setVisible(true);
                labelNotification.setForeground(Color.red);
                labelNotification.setText(check());
            } else {
                labelNotification.setVisible(false);
                account = new Account();
                account.setUserName(txtUserName.getText());
                account.setPassword(txtPassword.getText());
                String response = login(account);
                if (response.equals("Login successful")) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                ChatFrm frame = new ChatFrm(account, input, output);
                                frame.setVisible(true);
                                setVisible(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    labelNotification.setVisible(true);
                    btnLogin.setEnabled(true);
                    btnSignUp.setEnabled(true);
                    txtPassword.setText("");
                    labelNotification.setText(response);
                }
            }

        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void lbHideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHideMousePressed
        // TODO add your handling code here:
        lbShow.setVisible(true);
        lbHide.setVisible(false);
        txtPassword.setEchoChar('\u25cf');
    }//GEN-LAST:event_lbHideMousePressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelNotification;
    private javax.swing.JLabel lbHide;
    private javax.swing.JLabel lbShow;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
