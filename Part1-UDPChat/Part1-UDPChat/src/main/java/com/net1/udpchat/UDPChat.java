package com.net1.udpchat;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Define a timer for automatically removing messages from the archive
public class UDPChat extends javax.swing.JFrame {

    private DatagramSocket socket;
    private DefaultListModel<String> logModel = new DefaultListModel<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private ArrayList<String> archivedMessages = new ArrayList<>();
    private boolean connectionStatus;
    private boolean running;
  
    private HashMap<String, Timer> messageTimers = new HashMap<>();
    private HashMap<String, Long> expirationTimes = new HashMap<>(); // Store expiration times  
    private DefaultListModel<String> archivedMessagesModel = new DefaultListModel<>();
    JList<String> archivedMessagesList = new JList<>(archivedMessagesModel);

    public UDPChat() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameLabel = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        sendingAreaScrollPane = new javax.swing.JScrollPane();
        sendingArea = new javax.swing.JTextArea();
        statusLabel = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        connectPanel = new javax.swing.JPanel();
        localIpLabel1 = new javax.swing.JLabel();
        localPortLabel = new javax.swing.JLabel();
        localPortField = new javax.swing.JTextField();
        localIpField = new javax.swing.JTextField();
        remoteIpLabel = new javax.swing.JLabel();
        remotePortLabel = new javax.swing.JLabel();
        remoteIpField = new javax.swing.JTextField();
        remotePortField = new javax.swing.JTextField();
        testButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        deleteMessageButton = new javax.swing.JButton();
        loggingAreaScrollPane = new javax.swing.JScrollPane();
        loggingArea = new javax.swing.JList<>(logModel);
        deleteConversationButton = new javax.swing.JButton();
        Archive = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UDP Chat");
        setBackground(new java.awt.Color(0, 255, 255));
        setResizable(false);

        userNameLabel.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        userNameLabel.setText("Username:");

        userNameField.setNextFocusableComponent(localIpField);
        userNameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }
        });

        sendingArea.setColumns(20);
        sendingArea.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        sendingArea.setForeground(java.awt.Color.gray);
        sendingArea.setRows(5);
        sendingArea.setText("Enter Text Here");
        sendingArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sendingAreaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                sendingAreaFocusLost(evt);
            }
        });
        sendingArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateSendButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateSendButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateSendButtonState();
            }
        });
        sendingAreaScrollPane.setViewportView(sendingArea);

        statusLabel.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        statusLabel.setText("Status:");

        statusField.setEditable(false);

        sendButton.setBackground(new java.awt.Color(0, 204, 204));
        sendButton.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        sendButton.setText("Send Message");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        connectPanel.setForeground(new java.awt.Color(0, 255, 255));

        localIpLabel1.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        localIpLabel1.setText("Local IP:");

        localPortLabel.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        localPortLabel.setText("Local Port:");

        localPortField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }
        });

        localIpField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }
        });

        remoteIpLabel.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        remoteIpLabel.setText("Remote IP:");

        remotePortLabel.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        remotePortLabel.setText("Remote Port:");

        remoteIpField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }
        });

        remotePortField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTestButtonState();
            }
        });

        testButton.setBackground(new java.awt.Color(0, 255, 51));
        testButton.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        testButton.setText("Test Connection");
        testButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        testButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                testButtonFocusLost(evt);
            }
        });
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Enter The IP Address of the remote user you want to contact to");

        javax.swing.GroupLayout connectPanelLayout = new javax.swing.GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(localIpLabel1)
                            .addComponent(remoteIpLabel)
                            .addComponent(remotePortLabel)
                            .addComponent(localPortLabel))
                        .addGap(14, 14, 14)
                        .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(localPortField)
                            .addComponent(localIpField)
                            .addComponent(remoteIpField)
                            .addComponent(remotePortField))))
                .addContainerGap())
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(testButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        connectPanelLayout.setVerticalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(localIpLabel1)
                    .addComponent(localIpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(localPortLabel)
                    .addComponent(localPortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(remoteIpLabel)
                    .addComponent(remoteIpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(remotePortLabel)
                    .addComponent(remotePortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(testButton)
                .addGap(20, 20, 20))
        );

        deleteMessageButton.setBackground(new java.awt.Color(0, 204, 204));
        deleteMessageButton.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        deleteMessageButton.setText("Delete Message");
        deleteMessageButton.setEnabled(false);
        deleteMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMessageButtonActionPerformed(evt);
            }
        });

        loggingArea.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loggingArea.setToolTipText("");
        loggingArea.setMaximumSize(new java.awt.Dimension(62, 20));
        loggingArea.setMinimumSize(new java.awt.Dimension(62, 20));
        loggingArea.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                loggingAreaValueChanged(evt);
            }
        });
        loggingArea.getModel().addListDataListener(new javax.swing.event.ListDataListener() {
            public void intervalAdded(javax.swing.event.ListDataEvent e) {
                if (loggingArea.getModel().getSize() > 0) {
                    deleteConversationButton.setEnabled(true);
                }
            }

            public void intervalRemoved(javax.swing.event.ListDataEvent e) {
                if (loggingArea.getModel().getSize() == 0) {
                    deleteConversationButton.setEnabled(false);
                }
            }

            public void contentsChanged(javax.swing.event.ListDataEvent e) {
                if (loggingArea.getModel().getSize() > 0) {
                    deleteConversationButton.setEnabled(true);
                } else {
                    deleteConversationButton.setEnabled(false);
                }
            }
        });
        loggingAreaScrollPane.setViewportView(loggingArea);

        deleteConversationButton.setBackground(new java.awt.Color(0, 204, 204));
        deleteConversationButton.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        deleteConversationButton.setText("Delete Conversation");
        deleteConversationButton.setEnabled(false);
        deleteConversationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteConversationButtonActionPerformed(evt);
            }
        });

        Archive.setBackground(new java.awt.Color(255, 0, 0));
        Archive.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        Archive.setText("Archive");
        Archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArchiveActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 0, 24)); // NOI18N
        jLabel2.setText("Peer To Peer UDP connection");

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel3.setText("Chat Box");

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel4.setText("The massage you want to send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(330, 330, 330))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(connectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(deleteMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(109, 109, 109)
                                        .addComponent(deleteConversationButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusField)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(loggingAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(Archive, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendingAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userNameLabel)
                            .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loggingAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendingAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteConversationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Archive)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusLabel)
                        .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void logActivity(String activity) {
    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
    String logEntry = timestamp + " - " + activity;

    try (FileWriter writer = new FileWriter("log.txt", true)) {
        writer.write(logEntry + System.lineSeparator());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
        int destPort = Integer.parseInt(remotePortField.getText());
        InetAddress destIP = InetAddress.getByName(remoteIpField.getText());
        String dataMessage = sendingArea.getText();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        String finalMessage = time + " " + userNameField.getText() + ": " + dataMessage;

        colors.add(1); // Mark as sent message with Blue color
        logModel.addElement(finalMessage);
        updateLoggingArea();
        sendingArea.setText("");
        sendData(destIP, destPort, finalMessage);

        // Log this activity
        logActivity("Sent: " + finalMessage);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Remote IP or Remote Port!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (UnknownHostException ex) {
        JOptionPane.showMessageDialog(this, "Test Connection before start sending.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        connectionStatus = false;
    if (socket != null && !socket.isClosed()) {
        socket.close();
    }

    if (localPortField.getText().trim().isEmpty() || localIpField.getText().trim().isEmpty()
            || remoteIpField.getText().trim().isEmpty() || remotePortField.getText().trim().isEmpty()
            || userNameField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "You should enter all of text fields", "Error", JOptionPane.ERROR_MESSAGE);
        logActivity("Connection test failed: Missing input fields.");
    } else {

        try {
            int sourcePort = Integer.parseInt(localPortField.getText());
            String sourceIP = localIpField.getText();

            try {
                socket = new DatagramSocket(sourcePort);
                JOptionPane.showMessageDialog(this, "Connection Started...", "Success", JOptionPane.INFORMATION_MESSAGE);
                statusField.setText("Connection Started...");
                connectionStatus = true;
                updateSendButtonState();
                logActivity("Connection established on port: " + sourcePort);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Connection Failed! Please Try Another Port.", "Error", JOptionPane.ERROR_MESSAGE);
                statusField.setText("Connection Failed! Please Try Another Port.");
                logActivity("Connection failed: Port " + sourcePort + " is unavailable.");
            }

            // Listening for messages in a new thread
            new Thread(() -> {
                running = true;
                while (running) {
                    try {
                        byte[] receivedData = new byte[2048];
                        DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
                        socket.receive(packet);
                        String message = new String(packet.getData()).trim();

                        try {
                            int index = Integer.parseInt(message);
                            logModel.removeElementAt(index);
                            colors.remove(index);
                            logActivity("Received deletion command for message index: " + index);
                        } catch (NumberFormatException ex) {
                            if (message.equals("clear")) {
                                logModel.removeAllElements();
                                colors.clear();
                                logActivity("Received clear conversation command.");
                            } else {
                                colors.add(0);
                                logModel.addElement(message);
                                updateLoggingArea();
                                logActivity("Received message: \"" + message + "\" from " 
                                    + packet.getAddress() + ":" + packet.getPort());
                                statusField.setText("Received from IP = " + packet.getAddress().toString().split("/")[1] + ", Port = " + packet.getPort());
                            }
                        }

                    } catch (Exception e) {
                        break;
                    }
                }
            }).start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Local IP or Local Port!", "Error", JOptionPane.ERROR_MESSAGE);
            logActivity("Connection failed: Invalid local IP or port.");
        }
    }
    }//GEN-LAST:event_testButtonActionPerformed

    private void deleteMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMessageButtonActionPerformed
         int destPort = Integer.parseInt(remotePortField.getText());
    try {
        InetAddress destIP = InetAddress.getByName(remoteIpField.getText());
        int[] selectedIndices = loggingArea.getSelectedIndices();

        if (selectedIndices.length > 0) {
            int index = selectedIndices[0];
          
            String deletedMessage = logModel.getElementAt(index);

            moveMessageToArchive(deletedMessage);
            logModel.removeElementAt(index);
            colors.remove(index);
            sendIndex(destIP, destPort, String.valueOf(index));

            // Log the deletion
            logActivity("Deleted: " + deletedMessage);
        }
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_deleteMessageButtonActionPerformed

    private void deleteConversationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteConversationButtonActionPerformed
                                                            
    int destPort = Integer.parseInt(remotePortField.getText());
    try {
        InetAddress destIP = InetAddress.getByName(remoteIpField.getText());

        if (logModel.getSize() > 0) {
            // Move all current messages to archive
            for (int i = 0; i < logModel.size(); i++) {
                moveMessageToArchive(logModel.getElementAt(i));
            }

            // Clear the local chat
            logModel.removeAllElements();
            colors.clear();
            updateLoggingArea();

            // Send 'clear' command to remote side
            sendData(destIP, destPort, "clear");

            // Log the full conversation deletion
            logActivity("Entire conversation cleared and archived.");
        }
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }


    }//GEN-LAST:event_deleteConversationButtonActionPerformed

    private void loggingAreaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_loggingAreaValueChanged
        deleteMessageButton.setEnabled(!loggingArea.isSelectionEmpty());
    }//GEN-LAST:event_loggingAreaValueChanged

    private void sendingAreaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sendingAreaFocusGained
        if (sendingArea.getText().equals("Enter Text Here")) {
            sendingArea.setText("");
            sendingArea.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_sendingAreaFocusGained

    private void sendingAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sendingAreaFocusLost
        if (sendingArea.getText().trim().isEmpty()) {
            sendingArea.setForeground(Color.GRAY);
            sendingArea.setText("Enter Text Here");
            sendButton.setEnabled(false);
        }
    }//GEN-LAST:event_sendingAreaFocusLost

    private void testButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_testButtonFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_testButtonFocusLost

    private void ArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArchiveActionPerformed


        JFrame archiveFrame = new JFrame("Archived Messages");
        archiveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane archiveScrollPane = new JScrollPane();
      
         archivedMessagesList = new JList<>(archivedMessages.toArray(new String[0])); 
         archivedMessagesList.setModel(archivedMessagesModel);
        // Create JList for archived messages
        archiveScrollPane.setViewportView(archivedMessagesList);
        archiveFrame.getContentPane().add(archiveScrollPane);

        // Restore button
        JButton restoreButton = new JButton("Restore");
        archiveFrame.getContentPane().add(restoreButton, BorderLayout.SOUTH);
        initRestoreButton(restoreButton); // Initialize restoreButton after adding it to the frame

        // Restore all button
        JButton restoreAllButton = new JButton("Restore All");
        archiveFrame.getContentPane().add(restoreAllButton, BorderLayout.NORTH);
        initRestoreAllButton(restoreAllButton); // Initialize restoreAllButton after adding it to the frame

        archiveFrame.pack();
        archiveFrame.setLocationRelativeTo(this); // Center the archive frame relative to the main frame
        archiveFrame.setSize(600, 500);
        archiveFrame.setVisible(true);
    }//GEN-LAST:event_ArchiveActionPerformed
  private void moveMessageToArchive(String message) {
    archivedMessages.add(message);
    archivedMessagesModel.addElement(message);

    logActivity("Archived: " + message);

    // Create individual Timer per message
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            archivedMessages.remove(message);
            archivedMessagesModel.removeElement(message);
           
            messageTimers.remove(message);
            logActivity("Archived message removed: " + message);
        }
    }, 2 * 60 * 1000); // 2 minutes

    messageTimers.put(message, timer); // Save per message
}

   private void initRestoreButton(JButton restoreButton) {
    restoreButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedIndices = archivedMessagesList.getSelectedIndices();
            if (selectedIndices.length > 0) {
                int index = selectedIndices[0];
                String selectedMessage = archivedMessagesModel.getElementAt(index);

                if (selectedMessage != null) {
                    Timer timer = messageTimers.remove(selectedMessage);
                    if (timer != null) {
                        timer.cancel();
                    }
                    archivedMessagesModel.removeElementAt(index); // Only this
                    restoreMessage(selectedMessage); // Restore to logging area
                }
            }
        }
    });
}

    private void initRestoreAllButton(JButton restoreAllButton) {
        restoreAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restoreAllMessages();
            }
        });
    }

    private void restoreMessage(String message) {
    int destPort = Integer.parseInt(remotePortField.getText());
    try {
        InetAddress destIP = InetAddress.getByName(remoteIpField.getText());
        colors.add(2); // Mark restored message with Green color
        
        logModel.addElement(message); // Restore message to log
        updateLoggingArea();
        sendData(destIP, destPort, message); // Send restored message to the other party

        // Log the restoration
        logActivity("Restored: " + message);

        JOptionPane.showMessageDialog(null, "Message restored successfully.");
    } catch (UnknownHostException ex) {
        Logger.getLogger(UDPChat.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void restoreAllMessages() {
    for (int i = 0; i < archivedMessagesModel.getSize(); i++) {
        int destPort = Integer.parseInt(remotePortField.getText());
        try {
            InetAddress destIP = InetAddress.getByName(remoteIpField.getText());
            String message = archivedMessagesModel.getElementAt(i);

            colors.add(2); // Mark restored message with Green color
            logModel.addElement(message); // Restore message to log
            updateLoggingArea();
            sendData(destIP, destPort, message); // Send restored message to the other party

            // Log each restoration
            logActivity("Restored: " + message);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Clear the archive
    archivedMessagesModel.removeAllElements();
    for (Timer timer : messageTimers.values()) {
        timer.cancel();
    }
    messageTimers.clear();
    expirationTimes.clear();

    JOptionPane.showMessageDialog(null, "All messages restored successfully.");

    // Log the bulk restoration
    logActivity("All messages restored.");
}

   private void updateLoggingArea() {
    loggingArea.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (colors.get(index) == 0) {
                renderer.setForeground(Color.ORANGE); // Received message
            } else if (colors.get(index) == 1) {
                renderer.setForeground(Color.YELLOW); // Sent message
            } else {
                renderer.setForeground(Color.GREEN); // Restored message
            }

            return renderer;
        }
    });
}


    private void updateSendButtonState() {
        if (sendingArea.getText().trim().isEmpty()) {
            sendButton.setEnabled(false);
        } else if (connectionStatus && !sendingArea.getForeground().equals(Color.GRAY)) {
            sendButton.setEnabled(true);
        }
    }

    private void updateTestButtonState() {
        if (userNameField.getText().trim().isEmpty() || localIpField.getText().trim().isEmpty()
                || localPortField.getText().trim().isEmpty() || remoteIpField.getText().trim().isEmpty()
                || remotePortField.getText().trim().isEmpty()) {
            testButton.setEnabled(false);
        } else {
            testButton.setEnabled(true);
        }
    }

    public void sendData(InetAddress destIP, int destPort, String message) {
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destIP, destPort);
        try {
            socket.send(sendPacket);
            statusField.setText("Sent to IP = " + sendPacket.getAddress().toString().split("/")[1] + ", Port = " + destPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendIndex(InetAddress destIP, int destPort, String index) {
        byte[] sendData = index.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, destIP, destPort);
        try {
            socket.send(sendPacket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception exp) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        com.formdev.flatlaf.FlatIntelliJLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UDPChat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Archive;
    private javax.swing.JPanel connectPanel;
    private javax.swing.JButton deleteConversationButton;
    private javax.swing.JButton deleteMessageButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField localIpField;
    private javax.swing.JLabel localIpLabel1;
    private javax.swing.JTextField localPortField;
    private javax.swing.JLabel localPortLabel;
    public javax.swing.JList<String> loggingArea;
    private javax.swing.JScrollPane loggingAreaScrollPane;
    private javax.swing.JTextField remoteIpField;
    private javax.swing.JLabel remoteIpLabel;
    private javax.swing.JTextField remotePortField;
    private javax.swing.JLabel remotePortLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea sendingArea;
    private javax.swing.JScrollPane sendingAreaScrollPane;
    public javax.swing.JTextField statusField;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton testButton;
    private javax.swing.JTextField userNameField;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}


