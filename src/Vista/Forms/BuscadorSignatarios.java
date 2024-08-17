package Vista.Forms;

import Controlador.EventListener;
import Controlador.EventManager;
import Modelo.Entidad.Prueba;
import Modelo.Entidad.Signatario;
import Modelo.Repositorio.Conector;
import Modelo.Repositorio.RepositorioSignatarios;
import Vista.Extras.VentanaUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class BuscadorSignatarios extends javax.swing.JFrame {
    private Signatario signatario;
    private List<Signatario> signatarios;
    private static BuscadorSignatarios instancia;
    private VentanaUtils utils = new VentanaUtils(this);
    private RepositorioSignatarios repoSig = new RepositorioSignatarios();
    private EventManager events = new EventManager(EVENTO_BUSQUEDA);
    public static final String EVENTO_BUSQUEDA = "signatario-busqueda";
    
    private BuscadorSignatarios() {
        initComponents();
        utils.centrarEnPantalla();
    }
    
    public static BuscadorSignatarios getInstancia(){
        if(BuscadorSignatarios.instancia == null){
            BuscadorSignatarios.instancia = new BuscadorSignatarios();
        }
        return BuscadorSignatarios.instancia;
    }
    
    public void subscribe(EventListener listener){
        events.subscribe(EVENTO_BUSQUEDA, listener);
    }
    
    public void unsuscribe(EventListener listener){
        events.unsubscribe(EVENTO_BUSQUEDA, listener);
    }

    public void buscar(){
        try {
            String busqueda = busquedaTF.getText().trim();
            this.signatarios = repoSig.searchByFullName(busqueda);
            DefaultListModel<String> modeloSigs = new DefaultListModel<>();   
            for(Signatario s : signatarios){
                modeloSigs.addElement(s.getNombreCompleto());
            }
            this.signatarioScrollList.setModel(modeloSigs);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Error: "+e.toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        signatarioScrollList = new javax.swing.JList<>();
        busquedaTF = new javax.swing.JTextField();
        aceptarBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        signatarioScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signatarioScrollListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(signatarioScrollList);

        busquedaTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaTFKeyReleased(evt);
            }
        });

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busquedaTF)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(aceptarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(busquedaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        buscar();
    }//GEN-LAST:event_busquedaTFKeyReleased

    private void aceptarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarBtnMouseClicked
        if(signatario == null) return;
        events.notify(EVENTO_BUSQUEDA, signatario);
        this.setVisible(false);
    }//GEN-LAST:event_aceptarBtnMouseClicked

    private void signatarioScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signatarioScrollListMouseClicked
        int index = signatarioScrollList.getSelectedIndex();
        if(index == -1) return;
        signatario = signatarios.get(index);
        System.out.println(signatario);
    }//GEN-LAST:event_signatarioScrollListMouseClicked

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
            java.util.logging.Logger.getLogger(BuscadorSignatarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscadorSignatarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscadorSignatarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscadorSignatarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conector.login("rubenor", "");
                    var a = BuscadorSignatarios.getInstancia();
                    a.buscar();
                    a.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
                    a.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JTextField busquedaTF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> signatarioScrollList;
    // End of variables declaration//GEN-END:variables

}
