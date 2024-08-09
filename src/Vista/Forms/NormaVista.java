package Vista.Forms;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Controlador.ControladorNormas;
import Modelo.Entidad.Norma;
import Vista.Extras.VentanaUtils;

public class NormaVista extends javax.swing.JFrame {
    VentanaUtils utils = new VentanaUtils(this);
    private ControladorNormas normasCtl = new ControladorNormas();
    private List<Norma> normas;
    private Norma norma;

    public NormaVista() {
        initComponents();
        try {
            preparar();
            loadNormas(normasCtl.getAllNormas());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preparar(){
        utils.centrarEnPantalla();
        nombreTA.setText("");
        unidadesTF.setText("");
        tipoVComboBox.setSelectedIndex(0);
        buscarTF.setText("");
    }

    public void loadNormas(List<Norma> list){
        DefaultListModel<String> modeloSigs = new DefaultListModel<>();   
        this.normas = list;
        for(Norma n : list){
            modeloSigs.addElement(n.norma);
        }
        this.normaScrollList.setModel(modeloSigs);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        normaScrollList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        buscarTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        unidadesTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tipoVComboBox = new javax.swing.JComboBox<>();
        nuevoBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        aceptarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        nombreTA = new javax.swing.JTextArea();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        normaScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normaScrollListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(normaScrollList);

        jLabel1.setText("Nombre");

        buscarTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarTFKeyReleased(evt);
            }
        });

        jLabel2.setText("Unidades");

        jLabel3.setText("Tipo Ventana");

        tipoVComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><br>", "1", "2" }));

        nuevoBtn.setText("Nuevo");
        nuevoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarBtnMouseClicked(evt);
            }
        });

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarBtnMouseClicked(evt);
            }
        });

        nombreTA.setColumns(20);
        nombreTA.setRows(5);
        jScrollPane2.setViewportView(nombreTA);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(nuevoBtn)
                        .addGap(46, 46, 46)
                        .addComponent(eliminarBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buscarTF)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(aceptarBtn))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(unidadesTF, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoVComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(buscarTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eliminarBtn)
                            .addComponent(nuevoBtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unidadesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoVComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(aceptarBtn)))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void normaScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normaScrollListMouseClicked
        int index = normaScrollList.getSelectedIndex();
        if(index == -1) return;
        norma = normas.get(index); 
        nombreTA.setText(norma.norma);
        unidadesTF.setText(norma.unidades);
        tipoVComboBox.setSelectedIndex((int)norma.tipoVentana);
    }//GEN-LAST:event_normaScrollListMouseClicked

    private void buscarTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarTFKeyReleased
        String busqueda = buscarTF.getText().trim();
        try {
            normas = normasCtl.searchNormaByNombre(busqueda);
            loadNormas(normas);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_buscarTFKeyReleased

    private void aceptarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarBtnMouseClicked
        Norma n = new Norma(
                norma.idNorma,
                nombreTA.getText().trim(),
                unidadesTF.getText().trim(),
                tipoVComboBox.getSelectedIndex());
        
        try {
            if(n.idNorma == -1)
                normasCtl.add(n);
            else
                normasCtl.modify(n);
            
            String busqueda = buscarTF.getText().trim();
            normas = normasCtl.searchNormaByNombre(busqueda);
            loadNormas(normas);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_aceptarBtnMouseClicked

    private void nuevoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBtnActionPerformed
        nombreTA.setText("Nueva norma");
        unidadesTF.setText("");
        tipoVComboBox.setSelectedIndex(0);
        buscarTF.setText("");
        norma = new Norma();
    }//GEN-LAST:event_nuevoBtnActionPerformed

    private void eliminarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBtnMouseClicked
        if(norma == null) {
            JOptionPane.showMessageDialog(
                null,
                "Se debe seleccionar una norma",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            String msg = "¿Estás seguro que quieres eliminar la norma " + this.norma.norma + "?";
            
            int response = JOptionPane.showConfirmDialog(
                this,
                msg,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
            if(response != 0) return;
            
            normasCtl.remove(this.norma);
            String busqueda = buscarTF.getText().trim();
            normas = normasCtl.searchNormaByNombre(busqueda);
            loadNormas(normas);
            norma = null;
            preparar();
            JOptionPane.showMessageDialog(
                this,
                "Tarea realizada con éxito",
                "Confirmación",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                this,
                "Error: " + e.toString(),
                "Confirmación",
                JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_eliminarBtnMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JTextField buscarTF;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea nombreTA;
    private javax.swing.JList<String> normaScrollList;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JComboBox<String> tipoVComboBox;
    private javax.swing.JTextField unidadesTF;
    // End of variables declaration//GEN-END:variables
}
