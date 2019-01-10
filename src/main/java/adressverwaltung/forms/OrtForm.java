/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.forms;

import adressverwaltung.main;
import adressverwaltung.models.Ort;
import adressverwaltung.operators.InOut;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
/**
 *
 * @author chris
 */
// TODO input checks
// TODO remove all references button on delete 
public class OrtForm extends javax.swing.JFrame {

    InOut ioLayer;
    Ort town;
    long count = 0;
    long current = 1;
    boolean search = false;
    List<Ort> searchResults;
    /**
     * Creates new form OrtForm
     * @param io
     */
    public OrtForm(InOut io) {
        initComponents();
        ioLayer = io;
        count = io.countOrt();
        if(count == 0)town = new Ort();
        if(count > 0)town = ioLayer.getPlaces(1, 0).get(0);
        setCurrentTown(town);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton5.setText("<<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(">>");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("Löschen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Speichern");
        jButton3.setPreferredSize(new java.awt.Dimension(80, 29));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Suchen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Neu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("1/x");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Ortsverwaltung");

        jLabel2.setText("Name");

        jLabel3.setText("Plz");

        jButton8.setText("Settings");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("Close");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(jSpinner1))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jButton8)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton7});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(count >= current || current == count+1){
            current--;
            setCurrentTown(search ? searchResults.get(new Integer(current-1+"")) : ioLayer.getPlaces(1, new Integer(current-1+"")).get(0));
            setPlayerButtons();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(count > current){
            current++;
            setCurrentTown(search ? searchResults.get(new Integer(current-1+"")) : ioLayer.getPlaces(1, new Integer(current-1+"")).get(0));
            setPlayerButtons();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if(town.getOid()!= null)ioLayer.deleteOrt(town);
            if(search && searchResults.contains(town)) searchResults.remove(town);
            town = null;
            count = search ? searchResults.size() : (int) ioLayer.countOrt();
            if(count > 0){
                while(current > count){
                    current--;
                }
                setCurrentTown(ioLayer.getPlaces(1, new Integer((current > 0L ? current-1L : 0L)+"")).get(0));
                setPlayerButtons();
            }else{
                town = new Ort();
                if(count == 0)search = false;
                clearInputs();
                current = 1;
                setPlayerButtons();
                setCurrentTown(town);
            }
            main.af.updateOrt();
        } catch (Error ex) {
            JOptionPane.showMessageDialog(null,"Some people might still live in this town","People in the town",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            List<Ort> searchCheck = ioLayer.searchOrt(getCurrentTown().getPlz(), getCurrentTown().getName());
            if(searchCheck.stream().filter(el -> el.getName().equalsIgnoreCase(getCurrentTown().getName()) && el.getPlz() == getCurrentTown().getPlz()).count() == 1 && getCurrentTown().getOid() != null){
                Optional<Ort> found = searchCheck.stream().filter(el -> el.getName().equalsIgnoreCase(getCurrentTown().getName()) && el.getPlz() == getCurrentTown().getPlz()).findFirst();
                if(!(found.get().getOid() == getCurrentTown().getOid())){
                    JOptionPane.showMessageDialog(null,"That town already exists","Town exists",JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    long id = ioLayer.saveOrt(getCurrentTown());
                    count = search ? searchResults.size() : (int) ioLayer.countOrt();
                    setCurrentTown(ioLayer.getOrt(id));
                    setPlayerButtons();
                    main.af.updateOrt();
                }
            }else if(searchCheck.stream().filter(el -> el.getName().equalsIgnoreCase(getCurrentTown().getName()) && el.getPlz() == getCurrentTown().getPlz()).count() > 0){
                JOptionPane.showMessageDialog(null,"That town already exists","Town exists",JOptionPane.ERROR_MESSAGE);
            }else{
                long id = ioLayer.saveOrt(getCurrentTown());
                count = search ? searchResults.size() : (int) ioLayer.countOrt();
                setCurrentTown(ioLayer.getOrt(id));
                setPlayerButtons();
                main.af.updateOrt();
            }
        } catch (SQLException ex) {}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            search = !search;
            if(search){
                searchResults = ioLayer.searchOrt((int)jSpinner1.getValue(), jTextField1.getText());
                if(searchResults.size() > 0){
                    count = searchResults.size();
                    current = 1;
                    setCurrentTown(searchResults.get(0));
                    setPlayerButtons();
                }
                jButton2.setText("Exit Search");
            }else{
                count = ioLayer.countOrt();
                if(count == 0)town = new Ort();
                if(count > 0)town = ioLayer.getPlaces(1, 0).get(0);
                setCurrentTown(town);
                jButton2.setText("Search");
            }
        } catch (SQLException ex) {}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        town = new Ort();
        search = false;
        clearInputs();
        current = count+1;
        setPlayerButtons();
        setCurrentTown(town);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        main.viewConnectionSettings();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        main.viewAdressverwaltung();
    }//GEN-LAST:event_jButton7ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void setCurrentTown(Ort town) {
        this.town = town;
        setPlayerButtons();
        showCurrentTown();
    }
    private void showCurrentTown(){
        jSpinner1.setValue(town != null ? town.getPlz(): 0);
        jTextField1.setText(town != null ? town.getName() : "");
    }
    private Ort getCurrentTown(){
        town.setName(jTextField1.getText());
        town.setPlz((int)jSpinner1.getValue());
        return town;
    }
    private void setPlayerButtons(){
        jLabel9.setText(current > count ? "New Item" : current+"/"+count);
        jButton5.setEnabled(current != 1);
        jButton6.setEnabled(current < count);
    }
        
    private void clearInputs(){
        jSpinner1.setValue(1000);
        jTextField1.setText("");
    }
}
