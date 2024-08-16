package Vista.Forms;

import Controlador.ControladorParametros;
import Controlador.EventListener;
import Modelo.Entidad.Parametro;
import Modelo.Entidad.Prueba;
import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Controlador.ControladorPruebas;
import Modelo.Repositorio.Conector;
import Modelo.Repositorio.RepositorioParametro;
import Vista.Extras.VentanaUtils;
import javax.swing.JOptionPane;

public class PruebasVista extends javax.swing.JFrame implements EventListener {
    private Prueba prueba;
    private long idParametro;
    private boolean agregando;
    private List<Prueba> pruebas;
    VentanaUtils utils = new VentanaUtils(this);
    private ControladorPruebas pruebasCtl = new ControladorPruebas();
    private RepositorioParametro repoParam = new RepositorioParametro();
    
    public PruebasVista() {
        initComponents();
        utils.centrarEnPantalla();
        ParametroVista.getInstancia().subscribe(this);
        preparar();
    }
    
    @Override
    public void update(String eventType, Object obj) {
        switch (eventType) {
            case "busqueda":
                Parametro p = (Parametro) obj;
                this.parametroLbl.setText(p.nombre);
                this.idParametro = p.idParametro;
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private void loadPruebas(){
        if(pruebas == null) return;
        DefaultListModel<String> modeloSigs = new DefaultListModel<>();   
        for(Prueba p : pruebas){
            modeloSigs.addElement(p.nombre);
        }
        this.pruebasScrollList.setModel(modeloSigs);
    }
    
    public void preparar(){
        try {
            this.agregando = false;
            pruebas = pruebasCtl.searchByName(
                busquedaTF.getText().trim()
            );
            loadPruebas();
            nombreTF.setText("");
            parametroLbl.setText("[Seleccione una prueba]");
            prueba = null;
            idParametro = -1;
            loadPruebas();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        busquedaTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pruebasScrollList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        nombreTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        parametroLbl = new javax.swing.JLabel();
        buscarBtn = new javax.swing.JToggleButton();
        aceptarBtn = new javax.swing.JToggleButton();
        nuevoBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        busquedaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaTFKeyReleased(evt);
            }
        });

        pruebasScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pruebasScrollListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pruebasScrollList);

        jLabel1.setText("Nombre");

        jLabel2.setText("Parámetro:");

        parametroLbl.setText("[Parámetro]");

        buscarBtn.setText("Buscar");
        buscarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarBtnMouseClicked(evt);
            }
        });

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarBtnMouseClicked(evt);
            }
        });

        nuevoBtn.setText("Nuevo");
        nuevoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoBtnMouseClicked(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busquedaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreTF)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(buscarBtn))
                                    .addComponent(parametroLbl))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(aceptarBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(nuevoBtn)
                .addGap(18, 18, 18)
                .addComponent(eliminarBtn)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(busquedaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscarBtn)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parametroLbl)
                        .addGap(18, 18, 18)
                        .addComponent(aceptarBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoBtn)
                    .addComponent(eliminarBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void busquedaTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaTFKeyReleased
        preparar();
    }//GEN-LAST:event_busquedaTFKeyReleased

    private void pruebasScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pruebasScrollListMouseClicked
        this.agregando = false;
        this.idParametro = -1;
        int index = pruebasScrollList.getSelectedIndex();
        if(index == -1) return;
        
        prueba = pruebas.get(index);
        nombreTF.setText(prueba.nombre);
        try {
            Parametro param = repoParam.getById(prueba.idParametro);
            parametroLbl.setText(param.nombre);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_pruebasScrollListMouseClicked

    private void aceptarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarBtnMouseClicked
        try {
            Prueba p = new Prueba(
                agregando ? -1 : prueba.idPrueba, 
                nombreTF.getText().trim(), 
                idParametro != -1  ? idParametro : prueba.idParametro
            );
            
            if(agregando)
                pruebasCtl.add(p);
            else
                pruebasCtl.modify(p);
            preparar();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_aceptarBtnMouseClicked

    private void buscarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarBtnMouseClicked
        if(this.prueba == null && !agregando) return;
        ParametroVista p = ParametroVista.getInstancia();
        p.preparar(ParametroVista.MODO_BUSQUEDA);
        p.setVisible(true);
    }//GEN-LAST:event_buscarBtnMouseClicked

    private void nuevoBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoBtnMouseClicked
        this.agregando = true;
        this.nombreTF.setText("Nueva prueba");
    }//GEN-LAST:event_nuevoBtnMouseClicked

    private void eliminarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBtnMouseClicked
        int index = pruebasScrollList.getSelectedIndex();
        if(index == -1) return;
        Prueba p = pruebas.get(index);
        
        try{
           pruebasCtl.remove(p);
           preparar();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_eliminarBtnMouseClicked

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
            java.util.logging.Logger.getLogger(PruebasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PruebasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PruebasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PruebasVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conector.login("rubenor", "");
                    new PruebasVista().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton aceptarBtn;
    private javax.swing.JToggleButton buscarBtn;
    private javax.swing.JTextField busquedaTF;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JLabel parametroLbl;
    private javax.swing.JList<String> pruebasScrollList;
    // End of variables declaration//GEN-END:variables
}
