/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.controller;

import com.chatapp.helper.AccountDAO;
import com.chatapp.model.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Server  {
    private Object lock;

    private ServerSocket server;
    private Socket socket;
    static ArrayList<Handler> clients = new ArrayList<Handler>();
    Account account;
    AccountDAO accDAO;

    public Server() throws IOException {
        try {
            lock = new Object();
            server = new ServerSocket(9999);
            while (true) {
                // Đợi request đăng nhập/đăng xuất từ client
                socket = server.accept();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // Đọc yêu cầu đăng nhập/đăng xuất
                String request = dis.readUTF();
                if (request.equals("Sign up")) {
                    // Yêu cầu đăng ký từ user
                    String username = dis.readUTF();
                    String password = dis.readUTF();
                    Account acc = new Account(username, password, "");
                    System.out.println(username + ": " + password);
                    // Kiểm tra tên đăng nhập đã tồn tại hay chưa
                    if (isExisted(username) == false) {
                        // Lưu tai khoan vao co so du lieu
                        this.saveAccount(acc);
                        dos.writeUTF("Sign up successful");
                        dos.flush();
                    } else {
                        // Thông báo đăng nhập thất bại
                        dos.writeUTF("This username is being used");
                        dos.flush();
                    }
                } else if (request.equals("Log in")) {
                    // Yêu cầu đăng nhập từ user
                    String username = dis.readUTF();
                    String password = dis.readUTF();

                    // Kiểm tra tên đăng nhập có tồn tại hay không
                    if (isExisted(username) == true) {
                        Account client = getAccount(username);
                        if (client.getUserName().equals(username)) {
                            // Kiểm tra mật khẩu có trùng khớp không
                            if (password.equals(client.getPassword())) {
                                // Tạo Handler mới để giải quyết các request từ user này
                                Handler newHandler = new Handler(socket, client, lock);
                                //newHandler.setIsLoggedIn(true);
                                // Thông báo đăng nhập thành công cho người dùng
                                dos.writeUTF("Login successful");
                                dos.writeUTF(client.getAvatar());
                                clients.add(newHandler);
                                dos.flush();
                                // Tạo một Thread để giao tiếp với user này
                                //Thread t = new Thread(newHandler);
                                Thread t = new Thread((Runnable) newHandler);
                                t.start();

                                // Gửi thông báo cho các client đang online cập nhật danh sách người dùng trực tuyến
                                updateOnlineUsers();
                            } else {
                                dos.writeUTF("Password is not correct");
                                dos.writeUTF(" ");
                                dos.flush();
                            }
                        }
                    } else {
                        dos.writeUTF("This username is not exist");
                        dos.writeUTF(" ");
                        dos.flush();
                    }
                } else if (request.equals("Change Password")) {
                    String username = dis.readUTF();
                    String nowPassword = dis.readUTF();
                    String newPassword = dis.readUTF();
                    Account acc = getAccount(username);
                    if (acc.getPassword().equals(nowPassword)) {
                        acc.setPassword(newPassword);
                        if (updateAccount(acc).equals("Update successful")) {
                            dos.writeUTF("Update successful");
                        } else {
                            acc.setPassword(nowPassword);
                            dos.writeUTF("Not Update");
                        }

                    } else {
                        dos.writeUTF("Current Password does not match");
                    }
                    updateOnlineUsers();
                    dos.flush();
                } else if (request.equals("Change Avatar")) {
                    String userName = dis.readUTF();
                    String linkAvatar = dis.readUTF();
                    Account acc = getAccount(userName);
                    acc.setAvatar(linkAvatar);
                    if (this.updateAccount(acc).equals("Update successful")) {
                        dos.writeUTF("Update successful");
                    } else {
                        dos.writeUTF("ERROR");
                    }
                    updateOnlineUsers();
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }

    private String saveAccount(Account account) {
        accDAO = new AccountDAO();
        try {
            if (accDAO.saveAccountDAO(account)) {
                return "Successful account registration";
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR";
    }

    public String updateAccount(Account account) {
        accDAO = new AccountDAO();
        try {
            if (accDAO.updateAccountDAO(account)) {
                return "Update successful";
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Not Update";
    }

    public boolean isExisted(String username) throws Exception {
        accDAO = new AccountDAO();
        account = accDAO.findAccountDAO(username);
        if (account != null) {
            return true;
        }
        return false;
    }

    private Account getAccount(String username) {
        accDAO = new AccountDAO();
        try {
            account = accDAO.findAccountDAO(username);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public static void LogOutAccount(Handler clientHandler) {
        for (int i = 0; i < clients.size(); i++) {
            if (clientHandler.getUsername().equals(clients.get(i).getUsername())) {
                clients.remove(i);
            }
        }
    }

    /**
     * Gửi yêu cầu các user đang online cập nhật lại danh sách người dùng trực
     * tuyến Được gọi mỗi khi có 1 user online hoặc offline
     */
    public static void updateOnlineUsers() {
        String message = " ";
        if (clients.size() > 0) {
            for (Handler client : clients) {
                message += ",";
                message += client.getUsername();
            }
            if (message.equals(" ") == false) {
                message = message.substring(2);
                for (Handler client : clients) {
                    try {
                        if (client.getOutput() != null) {
                            client.getOutput().writeUTF("Online users");
                            client.getOutput().writeUTF(message);
                            client.getOutput().flush();
                        }
                    } catch (IOException e) {
                        System.out.println("Error");
                        //e.printStackTrace();
                    }
                }
            }
        }

    }

    
}
