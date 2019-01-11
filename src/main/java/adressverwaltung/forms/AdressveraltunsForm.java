/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.forms;

import adressverwaltung.main;
import adressverwaltung.operators.InOut;
import adressverwaltung.models.Person;
import adressverwaltung.models.Ort;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author chris
 */
// TODO search list
// TODO input checks
public class AdressveraltunsForm extends javax.swing.JFrame {

    InOut ioLayer;
    Person cp;
    int count = 0;
    int current = 1;
    boolean search = false;
    List<Person> searchResults;
    List<Ort> ortlist;
    Ort o;
    HashMap<Object, Boolean> validations;
    
    /**
     * Creates new form AdressveraltunsForm
     * @throws SQLException throws a exception if needed
     */
    public AdressveraltunsForm(InOut io) throws SQLException {
        initComponents();
        this.setTitle("Adressverwaltung");
        ioLayer = io;
        
        ArrayList<Component> comp = new ArrayList<>();
        comp.add(this.jName);
        comp.add(this.jVorname);
        comp.add(this.jStrasse);
        comp.add(this.jPLZ);
        comp.add(this.jTelNr);
        comp.add(this.jHandy);
        comp.add(this.jEmail);
        
        loadTowns();
        this.setFocusTraversalPolicy(new FtpFormPerson(comp));
        count = search ? searchResults.size() : (int) ioLayer.countPeople();
        setPlayerButtons();
        
        if(count >0){
            setPerson(ioLayer.getPeople(1, 0).get(0));
        }else{
            cp = new Person();
        }
        
        validations = new HashMap<>();
        validations.put(jName,false);
        validations.put(jVorname,false);
        validations.put(jStrasse,false);
        validations.put(jPLZ,false);
        validations.put(jTelNr,false);
        validations.put(jHandy,false);
        validations.put(jEmail,false);
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jName = new javax.swing.JTextField();
        jVorname = new javax.swing.JTextField();
        jStrasse = new javax.swing.JTextField();
        jTelNr = new javax.swing.JTextField();
        jHandy = new javax.swing.JTextField();
        jEmail = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPLZ = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        lblErr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Adressverwaltung");

        jLabel2.setText("Name:");

        jLabel3.setText("Vorname:");

        jLabel4.setText("Strasse:");

        jLabel5.setText("PLZ/Ort:");

        jLabel6.setText("Telefon:");

        jLabel7.setText("Handy:");

        jLabel8.setText("Email:");

        jName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNameKeyReleased(evt);
            }
        });

        jVorname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jVornameKeyReleased(evt);
            }
        });

        jStrasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jStrasseKeyReleased(evt);
            }
        });

        jTelNr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTelNrKeyReleased(evt);
            }
        });

        jHandy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHandyKeyReleased(evt);
            }
        });

        jEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jEmailKeyReleased(evt);
            }
        });

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

        jButton7.setText("Export");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(4, 4, 4)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)))
        );

        jPLZ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPLZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPLZActionPerformed(evt);
            }
        });
        jPLZ.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPLZPropertyChange(evt);
            }
        });

        jLabel9.setText("1/x");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Search to get a list of results" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jList1CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton8.setText("Settings");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Edit");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        lblErr.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jName)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPLZ, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9))
                                    .addComponent(jStrasse)
                                    .addComponent(jVorname)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErr)
                                    .addComponent(jHandy)
                                    .addComponent(jEmail)
                                    .addComponent(jTelNr))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jVorname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jStrasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jPLZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTelNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jHandy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErr)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(count >= current || current == count+1){
           current--;
           setPerson(search ? searchResults.get(current-1) : ioLayer.getPeople(1, current-1).get(0));
           setPlayerButtons();
       }
        validateEntrys();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if(count > current){
           current++;
           setPerson(search ? searchResults.get(current-1) : ioLayer.getPeople(1, current-1).get(0));
           setPlayerButtons();
       }
       validateEntrys();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if(search){
                search = false;
                jButton2.setText("Search");
                count = search ? searchResults.size() : (int) ioLayer.countPeople();
                setPlayerButtons();

                if(count >0){
                    setPerson(ioLayer.getPeople(1, 0).get(0));
                }else{
                    cp = new Person();
                }

                ortlist = ioLayer.getPlaces();
                if(new Long(cp.getOid()+"") != -1){
                    o = ioLayer.getOrt(cp.getOid());
                    if(o != null)selectOrt(o.getName()+" "+o.getPlz());
                }
                DefaultListModel dlm = new DefaultListModel();
                dlm.addElement("Search to get a list of results");

                jList1.setModel(dlm);
                searchResults = null;
            }else{
                search = true;
                searchResults = ioLayer.searchPerson(jVorname.getText(), jName.getText());
                if(searchResults.size() > 0){
                    count = searchResults.size();
                    current = 1;
                    setPerson(searchResults.get(0));
                    setPlayerButtons();
                }
                jButton2.setText("Exist search");
                
                DefaultListModel dlm = new DefaultListModel();
                for(Person p : searchResults){
                    dlm.addElement(p.getVorname()+" "+p.getName());
                }
                jList1.setModel(dlm);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdressveraltunsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        validateEntrys();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if(!validations.containsValue(false))
            {
                castInputToCurrentPerson();
                long id = ioLayer.savePerson(cp);
                setPerson(ioLayer.getPerson(id));
                count = search ? searchResults.size() : (int) ioLayer.countPeople();
                setPlayerButtons();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdressveraltunsForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        validateEntrys();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton2.setText("Search");
        count = search ? searchResults.size() : (int) ioLayer.countPeople();
        ortlist = ioLayer.getPlaces();
        if(new Long(cp.getOid()+"") != -1){
            o = ioLayer.getOrt(cp.getOid());
            if(o != null)selectOrt(o.getName()+" "+o.getPlz());
        }
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement("Search to get a list of results");

        jList1.setModel(dlm);
        searchResults = null;
        search = false;
        cp = new Person();
        clearInputs();
        current = count+1;
        setPlayerButtons();
        castInputToCurrentPerson();
        validateEntrys();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if(cp.getId() != null)ioLayer.deletePerson(cp);
            if(search) searchResults.remove(cp);
            cp = null;
            count = search ? searchResults.size() : (int) ioLayer.countPeople();
            if(count > 0){
                while(current > count){
                    current--;
                }
                setPerson(ioLayer.getPeople(1, current > 0 ? current-1 : 0).get(0));
                setPlayerButtons();
            }else{
                clearInputs();
                current = 1;
                setPlayerButtons();
            }
        } catch (Exception ex) {
            
        }
        validateEntrys();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(search){
            try {
                ioLayer.searchExport(searchResults);
            } catch (Exception ex) {
                Logger.getLogger(AdressveraltunsForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                ioLayer.export();
            } catch (Exception ex) {
                Logger.getLogger(AdressveraltunsForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        validateEntrys();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       main.viewOrtverwlatung();
       validateEntrys();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       main.viewConnectionSettings();
       validateEntrys();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jPLZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPLZActionPerformed
       if(ortlist != null){
            if(ortlist.stream().filter(el-> (el.getPlz()+" "+el.getName()).equals(jPLZ.getSelectedItem())).count() == 1){
                Optional<Ort> ort = ortlist.stream().filter(el-> (el.getPlz()+" "+el.getName()).equals(jPLZ.getSelectedItem())).findFirst();
                cp.setOid(new Integer(""+ort.get().getOid()));
            }else{
                if(cp != null)cp.setOid(-1);
            }
        }
    }//GEN-LAST:event_jPLZActionPerformed

    private void jPLZPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPLZPropertyChange

    }//GEN-LAST:event_jPLZPropertyChange

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jList1CaretPositionChanged
        
    }//GEN-LAST:event_jList1CaretPositionChanged

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        jList1.getSelectedIndex();
        current = jList1.getSelectedIndex()+1;
        setPlayerButtons();
        setPerson(searchResults.get(jList1.getSelectedIndex()));
    }//GEN-LAST:event_jList1ValueChanged

    private void jNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNameKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jNameKeyReleased
    
    private void jEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEmailKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jEmailKeyReleased

    private void jHandyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHandyKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jHandyKeyReleased

    private void jTelNrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTelNrKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jTelNrKeyReleased

    private void jStrasseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jStrasseKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jStrasseKeyReleased

    private void jVornameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jVornameKeyReleased
        validateEntrys();
    }//GEN-LAST:event_jVornameKeyReleased

    private void validateEntrys()
    {
        if(jName.getText().matches(".+"))
        {
            jName.setForeground(Color.BLACK);
            validations.put(jName, true);
        }
        else
        {
            jName.setForeground(Color.RED);
            validations.put(jName, false);
        }
        
        if(jEmail.getText().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"))
        {
            jEmail.setForeground(Color.BLACK);
            validations.put(jEmail, true);
        }
        else
        {
            jEmail.setForeground(Color.RED);
            validations.put(jEmail, false);
        }
        
        if(jHandy.getText().matches("\\d+"))
        {
            jHandy.setForeground(Color.BLACK);
            validations.put(jHandy, true);
        }
        else
        {
            jHandy.setForeground(Color.RED);
            validations.put(jHandy, false);
        }
        
        if(jTelNr.getText().matches("\\d+"))
        {
            jTelNr.setForeground(Color.BLACK);
            validations.put(jTelNr, true);
        }
        else
        {
            jTelNr.setForeground(Color.RED);
            validations.put(jTelNr, false);
        }
        
        if(jStrasse.getText().matches(".+\\s\\d+"))
        {
            jStrasse.setForeground(Color.BLACK);
            validations.put(jStrasse, true);
        }
        else
        {
            jStrasse.setForeground(Color.RED);
            validations.put(jStrasse, false);
        }
        
        if(jVorname.getText().matches(".+"))
        {
            jVorname.setForeground(Color.BLACK);
            validations.put(jVorname, true);
        }
        else
        {
            jVorname.setForeground(Color.RED);
            validations.put(jVorname, false);
        }
        
        if(validations.containsValue(false))
        {
            lblErr.setText("Bitte korrigieren sie ihre Eingaben.");
        }
        else
        {
            lblErr.setText("");
        }
    }
    
    private void showCurrentPerson(){
        jName.setText(cp.getName());
        jVorname.setText(cp.getVorname());
        jStrasse.setText(cp.getStrasse());
        jHandy.setText(cp.getTelefon());
        jTelNr.setText(cp.getHandy());
        jEmail.setText(cp.getEmail());
    }
    
    private void loadTowns(){
        jPLZ.removeAllItems();
        jPLZ.addItem("Please select a town");
        ortlist = ioLayer.getPlaces();
        ortlist.sort(Comparator.naturalOrder());
        ArrayList<String> duplicateFilterList = new ArrayList<>();
        jPLZ.removeAllItems();
        jPLZ.addItem("Please select a town");
        ortlist.forEach((ort) -> {
            if(duplicateFilterList.contains(ort.getPlz()+" "+ort.getName()) == false){
                jPLZ.addItem(ort.getPlz()+" "+ort.getName());
                duplicateFilterList.add(ort.getPlz()+" "+ort.getName());
            }
        });
    }
    
    private void clearInputs(){
        jName.setText("");
        jVorname.setText("");
        jStrasse.setText("");
        jHandy.setText("");
        jTelNr.setText("");
        jEmail.setText("");
        
    }
    
    private void castInputToCurrentPerson(){
        if(cp == null)cp = new Person();
        cp.setName(jName.getText());
        cp.setVorname(jVorname.getText());
        cp.setStrasse(jStrasse.getText());
        cp.setTelefon(jHandy.getText());
        cp.setHandy(jTelNr.getText());
        cp.setEmail(jEmail.getText());
    }
    
    private void setPlayerButtons(){
        if(current == 0)current = 1;
        jLabel9.setText(current > count ? "New Item" : current+"/"+count);
        jButton5.setEnabled(current != 1);
        jButton6.setEnabled(current < count);
    }
    
    private void setPerson(Person p){
        cp = p;
        showCurrentPerson();
        if(new Long(cp.getOid()+"") != -1){
            o = ioLayer.getOrt(cp.getOid());
            if(o != null){
                selectOrt(o.getPlz()+" "+o.getName());
            }else{
                p.setOid(-1);
                selectOrt("Please select a town");
            }
        }else{
            selectOrt("Please select a town");
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public javax.swing.JTextField jEmail;
    public javax.swing.JTextField jHandy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    public javax.swing.JTextField jName;
    public javax.swing.JComboBox<String> jPLZ;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jStrasse;
    public javax.swing.JTextField jTelNr;
    public javax.swing.JTextField jVorname;
    private javax.swing.JLabel lblErr;
    // End of variables declaration//GEN-END:variables

    private void selectOrt(String ort) {
        jPLZ.setSelectedItem(ort);
    }
    
    public void updateOrt(){
        loadTowns();
        if(cp.getOid() != null){
            Ort o;
            if((o = ioLayer.getOrt(cp.getOid())) != null){
                selectOrt(o.getPlz()+" "+o.getName());
            }else{
                cp.setOid(-1);
            }
        }
    }
}

class FtpFormPerson extends FocusTraversalPolicy
{
    ArrayList<Component> order;
    
    public FtpFormPerson(ArrayList<Component> order) {
      this.order = new ArrayList<Component>(order.size());
      this.order.addAll(order);
    }

    public Component getComponentAfter(Container focusCycleRoot,
        Component aComponent) {
      int idx = (order.indexOf(aComponent) + 1) % order.size();
      return order.get(idx);
    }

    public Component getComponentBefore(Container focusCycleRoot,
        Component aComponent) {
      int idx = order.indexOf(aComponent) - 1;
      if (idx < 0) {
        idx = order.size() - 1;
      }
      return order.get(idx);
    }

    public Component getDefaultComponent(Container focusCycleRoot) {
      return order.get(0);
    }

    public Component getLastComponent(Container focusCycleRoot) {
      return order.get(order.size()-1);
    }
    
    public Component getFirstComponent(Container focusCycleRoot) {
      return order.get(0);
    }
}

