package Vista.Forms;

import Controlador.ControladorClientes;
import Vista.Extras.VentanaUtils;
import Modelo.Entidad.Cliente;
import Modelo.Entidad.Sitio;
import Modelo.Repositorio.Conector;
import Modelo.Repositorio.RepositorioSitio;
import java.util.ArrayList;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ClientesVista extends javax.swing.JFrame {
    private VentanaUtils utils = new VentanaUtils(this);
    private ControladorClientes clientesCtl = new ControladorClientes();
    private RepositorioSitio sitioRepo = new RepositorioSitio();
    private List<Cliente> clientes;
    private Cliente cliente;
    
    public ClientesVista() {
        initComponents();
        preparar();
        try {
            clientes = clientesCtl.getAllClientes();
            loadClientes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadClientes(){
        DefaultListModel<String> modelo = new DefaultListModel<>();   
        for(Cliente c : this.clientes){
            modelo.addElement(c.nombre);
        }
        this.clientesScrollList.setModel(modelo);
    }

    public void loadSitios(List<Sitio> list){
        DefaultListModel<String> modelo = new DefaultListModel<>();   
        for(Sitio s : list){
            modelo.addElement(s.clave);
        }
        this.sitiosScrollList.setModel(modelo);
    }

    private void clientesScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesScrollListMouseClicked
        int index = this.clientesScrollList.getSelectedIndex();
        if(index == -1) return;

        this.cliente = this.clientes.get(index);
        this.nombreTF.setText(this.cliente.nombre);
        try {
            loadSitios(sitioRepo.searhByCliente(this.cliente.idCliente));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_clientesScrollListMouseClicked
    
    private void eliminarCBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarCBtnMouseClicked
        if(this.cliente == null){
            JOptionPane.showMessageDialog(
                null,
                "Se debe elegir un cliente",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        try {
            String msg = "¿Estás seguro que quieres eliminar el cliente " + this.cliente.nombre + "?";
            
            int response = JOptionPane.showConfirmDialog(
                this,
                msg,
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
            if(response != 0) return;
            
            clientesCtl.remove(this.cliente);
            String busqueda = buscadorTF.getText().trim();
            this.clientes = clientesCtl.searchByName(busqueda);
            loadClientes();
            this.cliente = null;
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
    }//GEN-LAST:event_eliminarCBtnMouseClicked

    private void buscadorTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorTFKeyReleased
        try {
            String busqueda = buscadorTF.getText().trim();
            this.clientes = clientesCtl.searchByName(busqueda);
            loadClientes();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                this,
                "Error: " + e.toString(),
                "Confirmación",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscadorTFKeyReleased

    public void preparar(){
        utils.centrarEnPantalla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        buscadorPanel = new javax.swing.JPanel();
        buscadorTF = new javax.swing.JTextField();
        clientesScrollPane = new javax.swing.JScrollPane();
        clientesScrollList = new javax.swing.JList<>();
        nuevoCBtn = new javax.swing.JButton();
        eliminarCBtn = new javax.swing.JButton();
        gestionCPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreTF = new javax.swing.JTextField();
        aceptarBtn = new javax.swing.JButton();
        sitiosScrollPane = new javax.swing.JScrollPane();
        sitiosScrollList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        agregarSBtn = new javax.swing.JButton();
        eliminarSBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(732, 362));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(700, 98));
        mainPanel.setMinimumSize(new java.awt.Dimension(700, 98));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 98));

        buscadorPanel.setBackground(new java.awt.Color(51, 102, 255));

        buscadorTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorTFKeyReleased(evt);
            }
        });

        clientesScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesScrollListMouseClicked(evt);
            }
        });
        clientesScrollPane.setViewportView(clientesScrollList);

        nuevoCBtn.setText("Agregar");
        nuevoCBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoCBtnMouseClicked(evt);
            }
        });

        eliminarCBtn.setText("Eliminar");
        eliminarCBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarCBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout buscadorPanelLayout = new javax.swing.GroupLayout(buscadorPanel);
        buscadorPanel.setLayout(buscadorPanelLayout);
        buscadorPanelLayout.setHorizontalGroup(
            buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscadorTF)
                    .addComponent(clientesScrollPane))
                .addContainerGap())
            .addGroup(buscadorPanelLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(nuevoCBtn)
                .addGap(34, 34, 34)
                .addComponent(eliminarCBtn)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        buscadorPanelLayout.setVerticalGroup(
            buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buscadorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buscadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoCBtn)
                    .addComponent(eliminarCBtn))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        gestionCPanel.setBackground(new java.awt.Color(153, 204, 255));
        gestionCPanel.setPreferredSize(new java.awt.Dimension(278, 137));

        jLabel1.setText("Nombre cliente");

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarBtnMouseClicked(evt);
            }
        });

        sitiosScrollPane.setViewportView(sitiosScrollList);

        jLabel2.setText("Sitios");

        agregarSBtn.setText("Agregar");

        eliminarSBtn.setText("Eliminar");

        javax.swing.GroupLayout gestionCPanelLayout = new javax.swing.GroupLayout(gestionCPanel);
        gestionCPanel.setLayout(gestionCPanelLayout);
        gestionCPanelLayout.setHorizontalGroup(
            gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestionCPanelLayout.createSequentialGroup()
                .addGroup(gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestionCPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gestionCPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nombreTF)))
                    .addComponent(sitiosScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addGroup(gestionCPanelLayout.createSequentialGroup()
                        .addGroup(gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(gestionCPanelLayout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(aceptarBtn))
                            .addGroup(gestionCPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(gestionCPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(agregarSBtn)
                .addGap(18, 18, 18)
                .addComponent(eliminarSBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gestionCPanelLayout.setVerticalGroup(
            gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestionCPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarBtn)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sitiosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gestionCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarSBtn)
                    .addComponent(eliminarSBtn))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(buscadorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gestionCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buscadorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(gestionCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        mainScrollPane.setViewportView(mainPanel);
        mainPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoCBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoCBtnMouseClicked
        this.cliente = new Cliente();
        this.nombreTF.setText("Nuevo cliente");
        this.loadSitios(new ArrayList<>());
    }//GEN-LAST:event_nuevoCBtnMouseClicked

    private void aceptarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarBtnMouseClicked
        Cliente c = new Cliente(
                this.cliente.idCliente,
                this.nombreTF.getText().trim()
        );
        
        try {
            if(c.idCliente == -1)
                clientesCtl.add(c);
            else
                clientesCtl.modify(c);
            
            this.clientes = clientesCtl.searchByName(
                    buscadorTF.getText().trim()
            );
            loadClientes();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_aceptarBtnMouseClicked


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
            java.util.logging.Logger.getLogger(ClientesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conector.login("rubenor", "");
                    new ClientesVista().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton agregarSBtn;
    private javax.swing.JPanel buscadorPanel;
    private javax.swing.JTextField buscadorTF;
    private javax.swing.JList<String> clientesScrollList;
    private javax.swing.JScrollPane clientesScrollPane;
    private javax.swing.JButton eliminarCBtn;
    private javax.swing.JButton eliminarSBtn;
    private javax.swing.JPanel gestionCPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JButton nuevoCBtn;
    private javax.swing.JList<String> sitiosScrollList;
    private javax.swing.JScrollPane sitiosScrollPane;
    // End of variables declaration//GEN-END:variables
}
