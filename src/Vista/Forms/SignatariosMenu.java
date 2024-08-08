package Vista.Forms;

import Modelo.Entidad.Parametro;
import Modelo.Entidad.Prueba;
import Modelo.Entidad.Signatario;
import Modelo.Repositorio.RepositorioParametro;
import Modelo.Repositorio.RepositorioPrueba;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import Controlador.ControladorSignatarios;
import Vista.Extras.VentanaUtils;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class CheckBoxYPrueba{
    JCheckBox cbox;
    Prueba p;

    public CheckBoxYPrueba(JCheckBox cbox, Prueba p){
        this.cbox = cbox;
        this.p = p;
    }
}

public class SignatariosMenu extends javax.swing.JFrame {
    Signatario signatario;
    Parametro parametro;
    RepositorioParametro repoParam = new RepositorioParametro();
    RepositorioPrueba repoPrueb = new RepositorioPrueba();
    ControladorSignatarios sigCtl = new ControladorSignatarios();
    Map<String, CheckBoxYPrueba> checkboxs;
    List<Parametro> params;
    List<Signatario> signatarios;
    VentanaUtils utils = new VentanaUtils(this);

    private static SignatariosMenu instancia;

    public static SignatariosMenu getInstancia(){
        if(SignatariosMenu.instancia == null){
            SignatariosMenu.instancia = new SignatariosMenu();
        }

        return SignatariosMenu.instancia;
    }
    
    private SignatariosMenu() {
        initComponents();
        preparar();
        
        // Agregar WindowListener para capturar el evento de cierre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarVentana();
            }
        });
    }
    
    public void preparar(){
        utils.centrarEnPantalla();
        DefaultListModel<String> modeloParams;
        this.signatario = null;
        this.parametro = null;
        this.PruebasPanel.removeAll();
        limpiarInputSignatario();
        this.usrLbl.setVisible(false);
        this.usrTF.setVisible(false);
        this.pwd1Lbl.setVisible(false);
        this.pwdTF.setVisible(false);
        this.pwd2Lbl.setVisible(false);
        this.pwd2TF.setVisible(false);
        
        try{
            params = repoParam.getAllParams();      
            modeloParams = new DefaultListModel<>();
            for(Parametro p : params){
                modeloParams.addElement(p.nombre);
            }
            this.ParamsScrollList.setModel(modeloParams);
            loadSignatarios();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public void crearEventoCbox(JCheckBox cbox){
        cbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                    if(signatario == null) return;

                String nombrePrueba = cbox.getText();
                CheckBoxYPrueba p;
                p = checkboxs.get(nombrePrueba);
                
                try {
                    String siglas = sigCtl.getSiglas(signatario.idSignatario);
                    if(cbox.isSelected())
                        repoPrueb.agregarPruebaASignatario(signatario.idSignatario, p.p.idPrueba);
                    else
                        repoPrueb.quitarPruebaASignatario(signatario.idSignatario, p.p.idPrueba); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                        null,
                        "Error: " + ex.toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    loadPruebas(new ArrayList<>());
                }
            }
        });
    }

    public void loadSignatarios() throws Exception{
        DefaultListModel<String> modeloSigs = new DefaultListModel<>();   
        this.signatarios = sigCtl.getAllSignatarios();
        for(Signatario s : signatarios){
            modeloSigs.addElement(s.toString());
        }
        this.SignatScrollList.setModel(modeloSigs);
    }
    
    private void loadPruebas(List<Prueba> pruebas) {
        this.PruebasPanel.setVisible(false);
        this.PruebasPanel.removeAll();
        this.checkboxs = new HashMap<>();
        for(Prueba p : pruebas) {
            JCheckBox cbox = new JCheckBox(p.nombre);
            crearEventoCbox(cbox);
            this.PruebasPanel.add(cbox);
            this.checkboxs.put(p.nombre, new CheckBoxYPrueba(cbox, p));
        }
        
        this.PruebasPanel.setVisible(true);
    }
    
    private void pruebasPorSignatario(Parametro param){
        if(signatario == null){
            return;
        }
        
        try{
            List<Prueba> pruebasPorSig = repoPrueb
                    .searchBy(signatario.idSignatario, param.idParametro);
            
            for(String key : this.checkboxs.keySet()){
                CheckBoxYPrueba  cbox = checkboxs.getOrDefault(key, null);
                if(cbox == null) continue;
                cbox.cbox.setSelected(false);
            }
            
            for(Prueba p : pruebasPorSig){
                CheckBoxYPrueba cbox = this.checkboxs.getOrDefault(p.nombre, null);
                if(cbox == null) continue;
                cbox.cbox.setSelected(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SignatarioSrollPane = new javax.swing.JScrollPane();
        signatPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombre2TF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellido1TF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellido2TF = new javax.swing.JTextField();
        usrLbl = new javax.swing.JLabel();
        usrTF = new javax.swing.JTextField();
        pwd1Lbl = new javax.swing.JLabel();
        pwd2Lbl = new javax.swing.JLabel();
        pwdTF = new javax.swing.JPasswordField();
        pwd2TF = new javax.swing.JPasswordField();
        posicionComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sueldoTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bonoTF = new javax.swing.JTextField();
        fIngresoTF = new Vista.Extras.FechaTF();
        jLabel7 = new javax.swing.JLabel();
        fNacTF = new Vista.Extras.FechaTF();
        jLabel9 = new javax.swing.JLabel();
        nuevoSigBtn = new javax.swing.JButton();
        SignatariosScrollPane = new javax.swing.JScrollPane();
        SignatScrollList = new javax.swing.JList<>();
        elminarSigBtn = new javax.swing.JButton();
        AceptarBtn = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        ParamsScrollPane = new javax.swing.JScrollPane();
        ParamsScrollList = new javax.swing.JList<>();
        PruebasScrollPane = new javax.swing.JScrollPane();
        PruebasPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(626, 416));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(626, 234));
        jPanel1.setMinimumSize(new java.awt.Dimension(626, 234));

        jLabel1.setText("Nombre");

        jLabel2.setText("Segundo nombre");

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Sueldo");

        usrLbl.setText("Usuario");

        usrTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usrTFActionPerformed(evt);
            }
        });

        pwd1Lbl.setText("Contraseña");

        pwd2Lbl.setText("<html>Confirmar<br>contraseña");

        posicionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><br>", "Dirección", "Muestreo", "Pruebas", "Sindicalizado" }));

        jLabel8.setText("Posición");

        jLabel5.setText("Apellido Materno");

        jLabel6.setText("Bono");

        jLabel7.setText("Fecha Ingreso");

        jLabel9.setText("Fecha Ingreso");

        javax.swing.GroupLayout signatPanelLayout = new javax.swing.GroupLayout(signatPanel);
        signatPanel.setLayout(signatPanelLayout);
        signatPanelLayout.setHorizontalGroup(
            signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signatPanelLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signatPanelLayout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addComponent(usrTF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(signatPanelLayout.createSequentialGroup()
                            .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pwd2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pwd1Lbl)
                                .addComponent(usrLbl))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pwdTF)
                                .addComponent(pwd2TF, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
                    .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signatPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(apellido1TF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nombre2TF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(signatPanelLayout.createSequentialGroup()
                            .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(signatPanelLayout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel1))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fNacTF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(posicionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreTF)
                                    .addComponent(apellido2TF)
                                    .addComponent(sueldoTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bonoTF, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fIngresoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        signatPanelLayout.setVerticalGroup(
            signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(apellido1TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posicionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sueldoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bonoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fIngresoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fNacTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usrLbl)
                    .addComponent(usrTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(signatPanelLayout.createSequentialGroup()
                        .addComponent(pwd1Lbl)
                        .addGap(7, 7, 7))
                    .addComponent(pwdTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(signatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pwd2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwd2Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        SignatarioSrollPane.setViewportView(signatPanel);

        nuevoSigBtn.setText("Nuevo");
        nuevoSigBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoSigBtnMouseClicked(evt);
            }
        });

        SignatScrollList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        SignatScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignatScrollListMouseClicked(evt);
            }
        });
        SignatariosScrollPane.setViewportView(SignatScrollList);

        elminarSigBtn.setText("Eliminar");
        elminarSigBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                elminarSigBtnMouseClicked(evt);
            }
        });

        AceptarBtn.setText("Aceptar");
        AceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SignatariosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(nuevoSigBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elminarSigBtn)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SignatarioSrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AceptarBtn)
                        .addGap(131, 131, 131))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SignatarioSrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(SignatariosScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(elminarSigBtn)
                        .addComponent(AceptarBtn))
                    .addComponent(nuevoSigBtn))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 255, 204));

        ParamsScrollList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ParamsScrollList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ParamsScrollListMouseClicked(evt);
            }
        });
        ParamsScrollPane.setViewportView(ParamsScrollList);

        PruebasScrollPane.setMaximumSize(new java.awt.Dimension(342, 153));
        PruebasScrollPane.setMinimumSize(new java.awt.Dimension(342, 153));

        PruebasPanel.setBackground(new java.awt.Color(255, 255, 255));
        PruebasPanel.setMaximumSize(new java.awt.Dimension(341, 152));
        PruebasPanel.setMinimumSize(new java.awt.Dimension(341, 152));
        PruebasPanel.setLayout(new javax.swing.BoxLayout(PruebasPanel, javax.swing.BoxLayout.Y_AXIS));
        PruebasScrollPane.setViewportView(PruebasPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ParamsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PruebasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ParamsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(PruebasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ParamsScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParamsScrollListMouseClicked
        int index = this.ParamsScrollList.getSelectedIndex();
        this.parametro = params.get(index);
        try{
            loadPruebas(repoPrueb.searchBy(this.parametro.idParametro));
            pruebasPorSignatario(this.parametro);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error: " + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            loadPruebas(new ArrayList<>());
        }
    }//GEN-LAST:event_ParamsScrollListMouseClicked

    private void SignatScrollListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignatScrollListMouseClicked
        int index = this.SignatScrollList.getSelectedIndex();
        if(index == -1) return;
        this.signatario = signatarios.get(index);
        
        this.nombreTF.setText(signatario.primNombre);
        this.nombre2TF.setText(signatario.segNombre);
        this.apellido1TF.setText(signatario.apellidoP);
        this.apellido2TF.setText(signatario.apellidoM);
        String usuario = signatario.usuario != null ? signatario.usuario.split("@")[0] : "";
        this.fIngresoTF.setDate(signatario.fIngreso);
        this.fNacTF.setDate(signatario.fNacimiento);
        this.sueldoTF.setText(String.valueOf(signatario.sueldo));
        this.bonoTF.setText(String.valueOf(signatario.bono));
        this.usrLbl.setVisible(false);
        this.usrTF.setVisible(false);
        this.pwd1Lbl.setVisible(false);
        this.pwdTF.setVisible(false);
        this.pwd2Lbl.setVisible(false);
        this.pwd2TF.setVisible(false);
        this.usrTF.setText("");
        this.pwdTF.setText("");
        this.pwd2TF.setText("");
        
        switch (signatario.posicion) {
            case Signatario.POSICION_DIRECCION:
                this.posicionComboBox.setSelectedIndex(1);
                break;
            case Signatario.POSICION_MUESTREO:
                this.posicionComboBox.setSelectedIndex(2);
                break;
            case Signatario.POSICION_PRUEBAS:
                this.posicionComboBox.setSelectedIndex(3);
                break;
            case Signatario.POSICION_SINDICALIZADO:
                this.posicionComboBox.setSelectedIndex(4);
                break;
            default:
                this.posicionComboBox.setSelectedIndex(0);
        }
        
        if(checkboxs != null) pruebasPorSignatario(this.parametro);
        
    }//GEN-LAST:event_SignatScrollListMouseClicked

    private void AceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarBtnActionPerformed
        if(this.signatario == null) return;
        
        try{
            signatario.primNombre = nombreTF.getText().trim();
            signatario.segNombre = nombre2TF.getText().trim();
            signatario.apellidoP = apellido1TF.getText().trim();
            signatario.apellidoM = apellido2TF.getText().trim();
            signatario.sueldo = Float.parseFloat(sueldoTF.getText());
            signatario.bono = Float.parseFloat(bonoTF.getText());
            signatario.fIngreso = fIngresoTF.getDate();
            signatario.fNacimiento = fNacTF.getDate();
            
            if(signatario.idSignatario == -1){
                signatario.usuario = usrTF.getText().trim();
            }
            
            switch (posicionComboBox.getSelectedIndex()) {
                case 1:
                    signatario.posicion = Signatario.POSICION_DIRECCION;
                    break;
                case 2:
                    signatario.posicion = Signatario.POSICION_MUESTREO;
                    break;
                case 3:
                    signatario.posicion = Signatario.POSICION_PRUEBAS;
                    break;
                case 4:
                    signatario.posicion = Signatario.POSICION_SINDICALIZADO;
                    break;
                default:
                    throw new Exception("Se debe elegir una posición");
            }
            
            if(signatario.idSignatario != -1)
                sigCtl.modify(signatario);
            else{
                sigCtl.add(signatario);
                // crear usuario
                this.signatario = null;
                limpiarInputSignatario();
                JOptionPane.showMessageDialog(
                    null,
                    "Signatario agregado correctamente",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            }
                
            
            loadSignatarios();
        }catch(ParseException e){
            JOptionPane.showMessageDialog(
            null,
            "Error: El sueldo y bono deben ser numeros reales",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
            null,
            "Error: " + e.toString(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AceptarBtnActionPerformed

    private void usrTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usrTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usrTFActionPerformed

    private void nuevoSigBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoSigBtnMouseClicked
        this.signatario = new Signatario();
        this.signatario.idSignatario = -1;
        limpiarInputSignatario();
        this.nombreTF.setText("NUEVO USUARIO");
    }//GEN-LAST:event_nuevoSigBtnMouseClicked

    private void elminarSigBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_elminarSigBtnMouseClicked
        if(this.signatario == null){
            JOptionPane.showConfirmDialog(
                this,
                "Se debe seleccionar un signatario",
                "Error",
                JOptionPane.INFORMATION_MESSAGE);
            
            return;
        }
        
        String msg = "¿Estás seguro que quieres eliminar al signatario " + signatario.getNombreCompleto() + "?";
        int response = JOptionPane.showConfirmDialog(
            this,
            msg,
            "Confirmación",
            JOptionPane.YES_NO_OPTION);
        
        if(response != 0) return;
        
        try {
            sigCtl.remove(signatario);
            limpiarInputSignatario();
            loadSignatarios();
            this.PruebasPanel.removeAll();
            signatario = null;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(
                this,
                "Error: " + e.toString(),
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
        }
        

    }//GEN-LAST:event_elminarSigBtnMouseClicked

    public void limpiarInputSignatario(){
                this.nombreTF.setText("");
        this.nombre2TF.setText("");
        this.apellido1TF.setText("");
        this.apellido2TF.setText("");
        this.fIngresoTF.setText("");
        this.fNacTF.setText("");
        this.sueldoTF.setText("");
        this.bonoTF.setText("");
        this.usrLbl.setVisible(true);
        this.usrTF.setVisible(true);
        this.pwd1Lbl.setVisible(true);
        this.pwdTF.setVisible(true);
        this.pwd2Lbl.setVisible(true);
        this.pwd2TF.setVisible(true);
        this.usrTF.setText("");
        this.pwdTF.setText("");
        this.pwd2TF.setText("");
        this.posicionComboBox.setSelectedIndex(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton AceptarBtn;
    private javax.swing.JList<String> ParamsScrollList;
    private javax.swing.JScrollPane ParamsScrollPane;
    private javax.swing.JPanel PruebasPanel;
    private javax.swing.JScrollPane PruebasScrollPane;
    private javax.swing.JList<String> SignatScrollList;
    private javax.swing.JScrollPane SignatarioSrollPane;
    private javax.swing.JScrollPane SignatariosScrollPane;
    private javax.swing.JTextField apellido1TF;
    private javax.swing.JTextField apellido2TF;
    private javax.swing.JTextField bonoTF;
    private javax.swing.JButton elminarSigBtn;
    private Vista.Extras.FechaTF fIngresoTF;
    private Vista.Extras.FechaTF fNacTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField nombre2TF;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JButton nuevoSigBtn;
    private javax.swing.JComboBox<String> posicionComboBox;
    private javax.swing.JLabel pwd1Lbl;
    private javax.swing.JLabel pwd2Lbl;
    private javax.swing.JPasswordField pwd2TF;
    private javax.swing.JPasswordField pwdTF;
    private javax.swing.JPanel signatPanel;
    private javax.swing.JTextField sueldoTF;
    private javax.swing.JLabel usrLbl;
    private javax.swing.JTextField usrTF;
    // End of variables declaration//GEN-END:variables

    public void cerrarVentana(){
        this.signatario = null;
        this.parametro = null;
        this.setVisible(false);
    }
}
