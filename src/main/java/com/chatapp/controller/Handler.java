/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.controller;

import com.chatapp.model.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
// Luồng riêng dùng để giao tiếp với mỗi user
// Object để synchronize các hàm cần thiết
// Các client đều có chung object này được thừa hưởng từ chính server
public class Handler implements Runnable{
    private final Object lock;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Account account;

    public Handler(Socket socket1, Account account1, Object lock1) throws IOException {
        this.socket = socket1;
        this.account = account1;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.lock = lock1;
    }

    public String getUsername() {
        return this.account.getUserName();
    }

    public String getPassword() {
        return this.account.getPassword();
    }

    public DataOutputStream getOutput() {
        return this.output;
    }

    public String getAvatr() {
        return account.getAvatar();
    }

    public void setPassword(String pass) {
        account.setPassword(pass);
    }

    public void setAvatar(String Avatar) {
        account.setAvatar(Avatar);
    }

    @Override
    public void run() {

        while (true) {
//            Thread handlerMessageThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
//            handlerMessageThread.start();
            try {
                // Đọc yêu cầu từ user
                String[] messageReceived = input.readUTF().split(",");

                // Yêu cầu đăng xuất từ user
                if (messageReceived[0].equals("Log out")) {

                    // Đóng socket và chuyển trạng thái thành offline
                    Server.LogOutAccount(Handler.this);
                    socket.close();
                    // Thông báo cho các user khác cập nhật danh sách người dùng trực tuyến
                    Server.updateOnlineUsers();
                    break;

                } // Yêu cầu gửi tin nhắn dạng văn bản
                else if (messageReceived[0].equals("Text")) {

                    String sender = messageReceived[1];
                    String receiver = messageReceived[2];
                    String content = messageReceived[3];

                    for (Handler client : Server.clients) {
                        if (client.getUsername().equals(receiver)) {
                            synchronized (lock) {
                                try {
                                    String messageSent = "Text" + "," + sender + ","
                                            + receiver + "," + content;
                                    client.getOutput().writeUTF(messageSent);
                                    client.getOutput().flush();
                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }

                } // Yêu cầu gửi tin nhắn dạng file
                else if (messageReceived[0].equals("File")) {
                    // Đọc các header của tin nhắn gửi file
                    String sender = messageReceived[1];
                    String receiver = messageReceived[2];
                    String filename = messageReceived[3];
                    int size = Integer.parseInt(messageReceived[4]);
                    int bufferSize = 2048;
                    byte[] buffer = new byte[bufferSize];

                    for (Handler client : Server.clients) {
                        if (client.getUsername().equals(receiver)) {
                            synchronized (lock) {
                                String messageSent = "File" + "," + sender + "," + receiver + ","
                                        + filename + "," + String.valueOf(size);
                                client.getOutput().writeUTF(messageSent);

                                while (size > 0) {
                                    // Gửi lần lượt từng buffer cho người nhận cho đến khi hết file
                                    input.read(buffer, 0, Math.min(size, bufferSize));
                                    client.getOutput().write(buffer, 0, Math.min(size, bufferSize));
                                    size -= bufferSize;
                                }
                                client.getOutput().flush();
                                break;
                            }
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
