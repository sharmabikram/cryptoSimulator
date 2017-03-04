/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Algorithms.CrypticAlgo;
import Algorithms.CrypticObject;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.xml.bind.DatatypeConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Sneh
 */


public class AppGUI extends javax.swing.JFrame {

    /**
     * Creates new form AppGUI
     */
    
   String plainText;
   String technique;
   String algo_used;
   String asym[] = new String[] { "RSA", "ElGamal", "EllipticCurve"};
   String sym[] = new String[] { "BlowFish", "DES", "AES" };
   String hash[] = new String[] {"MD5", "SHA-1"};
   byte encrypted[];
   byte decrypted[];
   byte plainBytes[];
   long sTime, eTime;
   CrypticObject crypt;
   
   int dataSet[][] = new int[2][10];
    XYSeriesCollection collect;
    final static int countAlgo = 4;
    String [] algoList = new String[]{"RSA", "AES", "BlowFish", "DES"};
   
   Algorithms.CrypticAlgo algo;
   ApplicationContext context;
   BufferedImage graph;
    public AppGUI() {
        initComponents();
        analPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modulePane = new javax.swing.JLayeredPane();
        analPanel = new javax.swing.JPanel();
        RSACheck = new javax.swing.JCheckBox();
        AESCheck = new javax.swing.JCheckBox();
        BlowFishCheck = new javax.swing.JCheckBox();
        DESCheck = new javax.swing.JCheckBox();
        plotGraphButton = new javax.swing.JButton();
        graphLabel = new javax.swing.JLabel();
        simPanel = new javax.swing.JPanel();
        plainLabel = new javax.swing.JLabel();
        algoCombo = new javax.swing.JComboBox();
        techniqueCombo = new javax.swing.JComboBox();
        browseButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        techniqueLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        plainArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        cipherArea = new javax.swing.JTextArea();
        cipherLabel = new javax.swing.JLabel();
        decryptedLabel = new javax.swing.JLabel();
        algoLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        decryptedArea = new javax.swing.JTextArea();
        sizeLabel = new javax.swing.JLabel();
        sizeText = new javax.swing.JTextField();
        eTimeLabel = new javax.swing.JLabel();
        eTimeField = new javax.swing.JTextField();
        eUnitLabel = new javax.swing.JLabel();
        dTimeLabel = new javax.swing.JLabel();
        dTimeField = new javax.swing.JTextField();
        dUnitLabel = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CryptoSimulator");
        setBounds(new java.awt.Rectangle(0, 0, 1024, 768));
        setLocation(new java.awt.Point(300, 80));
        setPreferredSize(new java.awt.Dimension(825, 600));
        setResizable(false);

        RSACheck.setText("RSA");
        RSACheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSACheckActionPerformed(evt);
            }
        });

        AESCheck.setText("AES");

        BlowFishCheck.setText("BlowFish");

        DESCheck.setText("DES");

        plotGraphButton.setText("Plot Graph");
        plotGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotGraphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout analPanelLayout = new javax.swing.GroupLayout(analPanel);
        analPanel.setLayout(analPanelLayout);
        analPanelLayout.setHorizontalGroup(
            analPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(analPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RSACheck)
                    .addComponent(plotGraphButton)
                    .addComponent(AESCheck)
                    .addComponent(BlowFishCheck)
                    .addComponent(DESCheck))
                .addGap(27, 27, 27)
                .addComponent(graphLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        analPanelLayout.setVerticalGroup(
            analPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analPanelLayout.createSequentialGroup()
                .addGroup(analPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(analPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(RSACheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AESCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BlowFishCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DESCheck)
                        .addGap(18, 18, 18)
                        .addComponent(plotGraphButton))
                    .addGroup(analPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(graphLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plainLabel.setText("Plain Text");

        algoCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RSA", "ElGamal", "Elliptic-Curve" }));
        algoCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algoComboActionPerformed(evt);
            }
        });

        techniqueCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asymmetric", "Symmetric", "Hash", "Quantum" }));
        techniqueCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                techniqueComboActionPerformed(evt);
            }
        });

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        techniqueLabel.setText(" Cryptography Technique");

        plainArea.setColumns(20);
        plainArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        plainArea.setLineWrap(true);
        plainArea.setRows(5);
        jScrollPane1.setViewportView(plainArea);

        cipherArea.setEditable(false);
        cipherArea.setColumns(20);
        cipherArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        cipherArea.setLineWrap(true);
        cipherArea.setRows(5);
        jScrollPane2.setViewportView(cipherArea);

        cipherLabel.setText("Cipher Text");

        decryptedLabel.setText("Decrypted Text");

        algoLabel.setText("Algorithm");

        decryptedArea.setEditable(false);
        decryptedArea.setColumns(20);
        decryptedArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        decryptedArea.setLineWrap(true);
        decryptedArea.setRows(5);
        jScrollPane3.setViewportView(decryptedArea);

        sizeLabel.setText(" Size of Input Text (KB)");

        sizeText.setEditable(false);
        sizeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeTextActionPerformed(evt);
            }
        });

        eTimeLabel.setText("Encryption Time");

        eTimeField.setEditable(false);
        eTimeField.setText(" ");
        eTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eTimeFieldActionPerformed(evt);
            }
        });

        eUnitLabel.setText("ms");

        dTimeLabel.setText("Decrypition Time");

        dTimeField.setEditable(false);
        dTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dTimeFieldActionPerformed(evt);
            }
        });

        dUnitLabel.setText("ms");

        javax.swing.GroupLayout simPanelLayout = new javax.swing.GroupLayout(simPanel);
        simPanel.setLayout(simPanelLayout);
        simPanelLayout.setHorizontalGroup(
            simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simPanelLayout.createSequentialGroup()
                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseButton)
                            .addGroup(simPanelLayout.createSequentialGroup()
                                .addComponent(plainLabel)
                                .addGap(231, 231, 231)
                                .addComponent(cipherLabel)
                                .addGap(217, 217, 217)
                                .addComponent(decryptedLabel))
                            .addGroup(simPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(simPanelLayout.createSequentialGroup()
                                .addComponent(techniqueLabel)
                                .addGap(10, 10, 10)
                                .addComponent(techniqueCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(algoLabel)
                                .addGap(10, 10, 10)
                                .addComponent(algoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(applyButton))
                            .addGroup(simPanelLayout.createSequentialGroup()
                                .addComponent(sizeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(sizeText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(eTimeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dTimeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        simPanelLayout.setVerticalGroup(
            simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plainLabel)
                    .addComponent(cipherLabel)
                    .addComponent(decryptedLabel))
                .addGap(6, 6, 6)
                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(browseButton)
                .addGap(59, 59, 59)
                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(techniqueLabel))
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(techniqueCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(algoLabel))
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(algoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(applyButton))
                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(simPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eTimeLabel)
                            .addComponent(eTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dTimeLabel)
                            .addComponent(dTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        modulePane.setLayer(analPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        modulePane.setLayer(simPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout modulePaneLayout = new javax.swing.GroupLayout(modulePane);
        modulePane.setLayout(modulePaneLayout);
        modulePaneLayout.setHorizontalGroup(
            modulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modulePaneLayout.createSequentialGroup()
                .addComponent(simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(analPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        modulePaneLayout.setVerticalGroup(
            modulePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(simPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(analPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Module");

        jMenuItem1.setText("Simulation");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Analysis");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setText("Exit");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        mainMenu.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem3.setText("Documentation");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Help");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        mainMenu.add(jMenu2);

        setJMenuBar(mainMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modulePane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modulePane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        simPanel.setVisible(true);
        analPanel.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        simPanel.setVisible(false);
        analPanel.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void plotGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotGraphButtonActionPerformed
        int i, j;
        collect = new XYSeriesCollection();
        if(RSACheck.isSelected()){
            collect.addSeries(Algorithms.Asymmetric.RSA.getDataSet());

        }
        if(AESCheck.isSelected()){
            collect.addSeries(Algorithms.Symmetric.AES.getDataSet());
        }
        if(BlowFishCheck.isSelected()){
            collect.addSeries(Algorithms.Symmetric.BlowFish.getDataSet());
        }
        if(DESCheck.isSelected()){
            collect.addSeries(Algorithms.Symmetric.DES.getDataSet());
        }
        plotGraph();
    }//GEN-LAST:event_plotGraphButtonActionPerformed

    private void RSACheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSACheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RSACheckActionPerformed

    private void dTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dTimeFieldActionPerformed

    private void eTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eTimeFieldActionPerformed

    private void sizeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeTextActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        
        int beg = 0, end;
        plainText = plainArea.getText();
        int length = plainText.length();
        
        technique = techniqueCombo.getSelectedItem().toString();
        algo_used = algoCombo.getSelectedItem().toString();
        sizeText.setText(""+((float)plainText.length()/1024));
        
        context = new ClassPathXmlApplicationContext("spring.xml");
        algo = (CrypticAlgo)context.getBean(algo_used);
        
        /*if(algo == (CrypticAlgo)context.getBean("RSA"))
        {
            sTime = System.currentTimeMillis();
            while(beg < length){
                end = (beg+245>=length)?(length):(beg+245);
                plainBytes = plainText.substring(beg, end).getBytes();
                encrypted = algo.encrypt(plainBytes);
                decrypted = algo.decrypt(encrypted);
                cipherArea.append((""+DatatypeConverter.printHexBinary(encrypted)).toLowerCase());
                decryptedArea.append(new String(decrypted));
                beg += 245;
                
            }
            eTime = System.currentTimeMillis();
            eTimeField.setText(""+(eTime-sTime));
            return;
        }
        if(algo == (CrypticAlgo)context.getBean("ElGamal"))
        {
            sTime = System.currentTimeMillis();
            while(beg < length){
                end = (beg+20>=length)?(length):(beg+20);
                plainBytes = plainText.substring(beg, end).getBytes();
                encrypted = algo.encrypt(plainBytes);
                decrypted = algo.decrypt(encrypted);
                cipherArea.append((""+DatatypeConverter.printHexBinary(encrypted)).toLowerCase());
                decryptedArea.append(new String(decrypted));
                beg += 20;
                
            }
            eTime = System.currentTimeMillis();
            eTimeField.setText(""+(eTime-sTime));
            return;
        }*/
        
        
        
        crypt = algo.encrypt(plainText.getBytes());
        cipherArea.setText((""+javax.xml.bind.DatatypeConverter.printHexBinary(crypt.data)).toLowerCase());
        eTimeField.setText(""+crypt.time);
        
        crypt = algo.decrypt(crypt.data);
        decryptedArea.setText(""+new String(crypt.data));
        dTimeField.setText(""+crypt.time);

    }//GEN-LAST:event_applyButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser openTextFile = new JFileChooser();
        int returnVal;
        String line;

        if(evt.getSource() == browseButton){

            returnVal = openTextFile.showOpenDialog(simPanel);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = openTextFile.getSelectedFile();
                plainArea.setText("");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
                    while((line = reader.readLine()) != null){
                        plainArea.append(line);
                    }
                    reader.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void techniqueComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_techniqueComboActionPerformed

        String item = techniqueCombo.getSelectedItem().toString();
        decryptedArea.setText("");
        cipherArea.setText("");
        decryptedArea.setEnabled(true);
        cipherLabel.setText("Cipher Text");
        decryptedLabel.setVisible(true);
        decryptedArea.setVisible(true);
        dTimeLabel.setVisible(true);
        dUnitLabel.setVisible(true);
        dTimeField.setVisible(true);
        algoCombo.setEnabled(true);
        switch(item)
        {
            case "Asymmetric":
            algoCombo.setModel(new DefaultComboBoxModel(asym));
            break;
            case "Symmetric":
            algoCombo.setModel(new DefaultComboBoxModel(sym));
            break;
            case "Hash":
            algoCombo.setModel(new DefaultComboBoxModel(hash));
            cipherLabel.setText("Message Digest");
            decryptedLabel.setVisible(false);
            decryptedArea.setEnabled(false);
            decryptedArea.setVisible(false);
            dTimeLabel.setVisible(false);
            dUnitLabel.setVisible(false);
            dTimeField.setVisible(false);
            break;
            default:
            algoCombo.setEnabled(false);
        }
    }//GEN-LAST:event_techniqueComboActionPerformed

    private void algoComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algoComboActionPerformed
        // TODO add your handling code here:
        //algo_used = jComboBox2.getSelectedItem().toString();
    }//GEN-LAST:event_algoComboActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    public void plotGraph(){
       
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Analysis of Algorithm",
                "memory",
                "time",
                collect,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        
       try {
           ChartUtilities.saveChartAsJPEG(new File("chart.jpg"), chart, 500, 300);
           showImage();
       } catch (IOException ex) {
           ex.printStackTrace();
       }
       
       
    }
    
    public void showImage() throws IOException{
        BufferedImage image = ImageIO.read(new File("chart.jpg"));
        ImageIcon icon = new ImageIcon(image);
        graphLabel.setIcon(icon);
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Security.addProvider(new BouncyCastleProvider());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppGUI().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox AESCheck;
    private javax.swing.JCheckBox BlowFishCheck;
    private javax.swing.JCheckBox DESCheck;
    private javax.swing.JCheckBox RSACheck;
    private javax.swing.JComboBox algoCombo;
    private javax.swing.JLabel algoLabel;
    private javax.swing.JPanel analPanel;
    private javax.swing.JButton applyButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JTextArea cipherArea;
    private javax.swing.JLabel cipherLabel;
    private javax.swing.JTextField dTimeField;
    private javax.swing.JLabel dTimeLabel;
    private javax.swing.JLabel dUnitLabel;
    private javax.swing.JTextArea decryptedArea;
    private javax.swing.JLabel decryptedLabel;
    private javax.swing.JTextField eTimeField;
    private javax.swing.JLabel eTimeLabel;
    private javax.swing.JLabel eUnitLabel;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JLayeredPane modulePane;
    private javax.swing.JTextArea plainArea;
    private javax.swing.JLabel plainLabel;
    private javax.swing.JButton plotGraphButton;
    private javax.swing.JPanel simPanel;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JTextField sizeText;
    private javax.swing.JComboBox techniqueCombo;
    private javax.swing.JLabel techniqueLabel;
    // End of variables declaration//GEN-END:variables
}
