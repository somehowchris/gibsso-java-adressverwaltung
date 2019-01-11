/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.forms;

import adressverwaltung.services.FileSystemService;
import adressverwaltung.errors.CanNotConnectToDatabaseError;
import adressverwaltung.main;
import adressverwaltung.utils.DotEnv;
import adressverwaltung.utils.InOut;
import adressverwaltung.utils.MySQLConnection;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christof Weickarhdt
 */
public class ConnectionForm extends javax.swing.JFrame {
    boolean tested = false;

    /**
     * Creates new form ConnectionForm
     */
    public ConnectionForm() {
        this.setTitle("Connection");
        initComponents();
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jSpinner1.setEnabled(false);
        jLabel7.setText("");
        setResizable(false);
        String sep = System.getProperty("file.separator");
        String home = System.getProperty("user.home");

        if (!new File(home).canRead())
            home = System.getProperty("user.dir");

        HashMap<String, String> dotEnv = DotEnv.getDotEnv();

        if (!dotEnv.isEmpty() && dotEnv.containsKey("DATABASE_USE")) {
            jTextField1.setText(dotEnv.get("DATABASE_HOST"));
            jTextField2.setText(dotEnv.get("DATABASE_NAME"));
            jTextField3.setText(dotEnv.get("DATABASE_USER"));
            jTextField4.setText(dotEnv.get("DATABASE_PASSWORD"));
            jSpinner1.setValue(new Integer(dotEnv.get("DATABASE_PORT")));
            if (dotEnv.get("DATABASE_USE").equals("true")) {
                jTextField1.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jTextField4.setEnabled(true);
                jSpinner1.setEnabled(true);

                System.out.println("Connecting database...");

                try {
                    MySQLConnection.verify(jTextField1.getText(), jTextField4.getText(), jTextField2.getText(),jSpinner1.getValue()+"", jTextField3.getText());
                } catch (CanNotConnectToDatabaseError ex) {
                }
                jRadioButton2.setSelected(true);
                jRadioButton1.setSelected(false);
            } else {
                jButton1.setText("Clear fsdb");
            }
        } else {
            jButton1.setText("Clear fsdb");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Connection Information");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("File System");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("MySQL Database");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("localhost");

        jTextField2.setText("adressverwaltung");

        jTextField3.setText("root");

        jLabel2.setText("DB Name");

        jLabel3.setText("DB Host");

        jLabel4.setText("DB User");

        jLabel5.setText("DB Password");

        jLabel6.setText("DB Port");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(3306, null, null, 1));
        jSpinner1.setValue(3306);

        jLabel7.setText("jLabel7");

        jButton1.setText("Test Connection");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Lets Go");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                        .createSequentialGroup().addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel7))
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup().addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton2))
                                .addGroup(layout.createSequentialGroup().addGroup(layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4)
                                                .addComponent(jLabel5))
                                        .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField1).addComponent(jTextField3)
                                                .addComponent(jTextField2)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 149,
                                                        Short.MAX_VALUE)
                                                .addComponent(jSpinner1))))
                        .addGap(0, 21, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                .addContainerGap()));

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
                new java.awt.Component[] { jLabel2, jLabel3, jLabel4, jLabel5 });

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
                new java.awt.Component[] { jTextField1, jTextField2, jTextField3, jTextField4 });

        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton1).addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6).addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1).addComponent(jButton2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton1ActionPerformed
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jSpinner1.setEnabled(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton1.setText("Clear fsdb");
        jLabel7.setText("Files are stored in your root .fsdb folder");
    }// GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jRadioButton2ActionPerformed
        jTextField1.setEnabled(true);
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jSpinner1.setEnabled(true);
        tested = false;
        jButton1.setText("Test Connection");
        jLabel7.setText(tested ? "Database Connected" : "Let's configure your db connection");
    }// GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        try {
            HashMap<String, String> connectionMap = new HashMap<>();
            if (jRadioButton1.isSelected())
                connectionMap.put("DATABASE_USE", "false");
            if (jRadioButton2.isSelected()) {
                connectionMap.put("DATABASE_USE", "true");
                connectionMap.put("DATABASE_HOST", jTextField1.getText());
                connectionMap.put("DATABASE_NAME", jTextField2.getText());
                connectionMap.put("DATABASE_USER", jTextField3.getText());
                connectionMap.put("DATABASE_PASSWORD", jTextField4.getText());
                connectionMap.put("DATABASE_PORT", jSpinner1.getValue() + "");
            }
            DotEnv.setDotEnv(connectionMap);
            main.setupConnection(null);
            main.viewAdressverwaltung();
        } catch (IOException | SQLException | CanNotConnectToDatabaseError ex) {
            Logger.getLogger(ConnectionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            HashMap<String, String> fakeKeys = new HashMap<>();
            fakeKeys.put("DATABASE_USE", "false");
            try {
                InOut io = new InOut(fakeKeys);
                ((FileSystemService) io.connection).clean();
            } catch (SQLException | CanNotConnectToDatabaseError ex) {
                Logger.getLogger(ConnectionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                MySQLConnection.verify(jTextField1.getText(), jTextField4.getText(), jTextField2.getText(),jSpinner1.getValue()+"", jTextField3.getText());
                System.out.println("Database connected!");
                jLabel7.setText("Database Connected");
                tested = true;
                jButton1.setEnabled(true);
                jButton2.setEnabled(true);

            } catch (CanNotConnectToDatabaseError ex) {
                jLabel7.setText("<html><font color='red'>Testing connection to database failed</font></html>");
                tested = false;
                jButton1.setEnabled(true);
                jButton2.setEnabled(false);

            }
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionForm.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ConnectionForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
