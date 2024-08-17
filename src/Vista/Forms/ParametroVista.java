package Vista.Forms;

import Controlador.ControladorParametros;
import Controlador.EventManager;
import Controlador.EventListener;
import Modelo.Entidad.Parametro;
import Vista.Extras.VentanaUtils;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ParametroVista extends javax.swing.JFrame {
    private static ParametroVista instancia;
    private ControladorParametros parametrosCtl = new ControladorParametros();
    private VentanaUtils utils = new VentanaUtils(this);
    private List<Parametro> parametros = new ArrayList<>();
    private Parametro parametro;
    private EventManager events = new EventManager(EVENTO_BUSQUEDA);

    public static final String EVENTO_BUSQUEDA = "param-busqueda";
    public static final String MODO_BUSQUEDA = "busqueda";
    public static final String MODO_GESTION = "gestion";
    
    private ParametroVista() {
        initComponents();
        try{
            parametros = parametrosCtl.getAllParams();
            loadParametros(parametros);
            preparar();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void subscribe(EventListener listener){
        events.subscribe(EVENTO_BUSQUEDA, listener);
    }

    public void unsuscribe(EventListener listener){
        events.unsubscribe(EVENTO_BUSQUEDA, listener);
    }

    public static ParametroVista getInstancia(){
        if(ParametroVista.instancia == null){
            ParametroVista.instancia = new ParametroVista();
        }
        return ParametroVista.instancia;
    }
    
    public void preparar(String modo){
        switch (modo) {
            case MODO_GESTION:
                this.nombreLbl.setVisible(true);
                this.nombreTF.setVisible(true);
                this.nuevoBtn.setVisible(true);
                this.eliminarBtn.setVisible(true);
                this.busquedaAceptarBtn.setVisible(false);
                this.aceptarBtn.setVisible(true);
                break;
            case MODO_BUSQUEDA:
                this.busquedaAceptarBtn.setVisible(true);
                this.aceptarBtn.setVisible(false);
                this.nombreLbl.setVisible(false);
                this.nombreTF.setVisible(false);
                this.nuevoBtn.setVisible(false);
                this.eliminarBtn.setVisible(false);
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void preparar(){
        utils.centrarEnPantalla();
        buscarTF.setText("");
        nombreTF.setText("");
    }
    
    public void loadParametros(List<Parametro> lista){
        DefaultListModel<String> modeloSigs = new DefaultListModel<>();   
        this.parametros = lista;
        for(Parametro p : lista){
            modeloSigs.addElement(p.nombre);
        }
        this.parametrosScrollList.setModel(modeloSigs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        parametrosScrollList = new javax.swing.JList<>();
        nombreLbl = new javax.swing.JLabel();
        nombreTF = new javax.swing.JTextField();
        aceptarBtn = new javax.swing.JButton();
        buscarTF = new javax.swing.JTextField();
        nuevoBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        busquedaAceptarBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        parametrosScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parametrosScrollListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(parametrosScrollList);

        nombreLbl.setText("Nombre");

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtnActionPerformed(evt);
            }
        });

        buscarTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarTFKeyReleased(evt);
            }
        });

        nuevoBtn.setText("Nuevo");
        nuevoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        busquedaAceptarBtn.setText("Aceptar");
        busquedaAceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaAceptarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(busquedaAceptarBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombreLbl)
                                    .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(nuevoBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eliminarBtn)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(aceptarBtn)
                                .addGap(45, 45, 45))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buscarTF, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(buscarTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(nombreLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aceptarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevoBtn)
                            .addComponent(eliminarBtn))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busquedaAceptarBtn)
                        .addGap(18, 18, 18))))
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

    private void parametrosScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parametrosScrollListMouseClicked
        int index = parametrosScrollList.getSelectedIndex();
        if(index == -1) return;
        this.parametro = parametros.get(index);
        nombreTF.setText(this.parametro.nombre);
    }//GEN-LAST:event_parametrosScrollListMouseClicked

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        if(this.parametro == null) return;
        
        Parametro p = new Parametro(
                parametro.idParametro,
                nombreTF.getText().trim());
        
        try {
            if(this.parametro.idParametro == -1){
                parametrosCtl.add(p);
                this.nombreTF.setText("");
                this.parametro = null;
            }
            else
                parametrosCtl.modify(p);
            
            String busqueda = buscarTF.getText().trim();
            this.parametros = parametrosCtl.searchByName(busqueda);
            loadParametros(this.parametros);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_aceptarBtnActionPerformed

    private void nuevoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBtnActionPerformed
        this.parametro = new Parametro();
        nombreTF.setText("Nuevo parámetro");
    }//GEN-LAST:event_nuevoBtnActionPerformed

    private void buscarTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarTFKeyReleased
        String busqueda = buscarTF.getText().trim();
        
        try{
            this.parametros = parametrosCtl.searchByName(busqueda);
            loadParametros(parametros);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarTFKeyReleased

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        int index = parametrosScrollList.getSelectedIndex();
        if(index == -1) {
            JOptionPane.showMessageDialog(
                null,
                "Se debe elegir un parámetro",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try{
            Parametro p = parametros.get(index);
            String msg = "¿Está seguro de que quiere elminar el parámetro " + p.nombre + "?";
            int response = JOptionPane.showConfirmDialog(
                this,
                msg,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
            
            if(response != 0) return;
            
            parametrosCtl.remove(p);
            JOptionPane.showMessageDialog(
                null,
                "Se eliminó correctamente",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            
            this.nombreTF.setText("");
            this.parametro = null;
            String busqueda = buscarTF.getText().trim();
            this.parametros = parametrosCtl.searchByName(busqueda);
            loadParametros(parametros);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void busquedaAceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaAceptarBtnActionPerformed
        events.notify(EVENTO_BUSQUEDA, this.parametro);
        this.setVisible(false);
    }//GEN-LAST:event_busquedaAceptarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JTextField buscarTF;
    private javax.swing.JButton busquedaAceptarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreLbl;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JList<String> parametrosScrollList;
    // End of variables declaration//GEN-END:variables
}
