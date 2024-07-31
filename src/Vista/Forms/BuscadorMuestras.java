package Vista.Forms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modelo.Entidad.Muestra;
import Modelo.Repositorio.RepositorioMuestra;
import Modelo.Repositorio.RepositorioSignatarios;

public class BuscadorMuestras extends javax.swing.JFrame {
    RepositorioMuestra repoMuest;
    RepositorioSignatarios repoSig;

    /**
     * Creates new form BuscadorMuestras
     */
    public BuscadorMuestras() {
        repoMuest = new RepositorioMuestra();
        repoSig = new RepositorioSignatarios();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        numCTF = new javax.swing.JTextField();
        proyectoTF = new javax.swing.JTextField();
        fmTF = new javax.swing.JTextField();
        frTF = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        muestrasTb = new javax.swing.JTable();
        buscarBtn3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        muestrasTb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num. C.", "Proyecto", "F. Muestreo", "F. Recepción", "Muestreador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(muestrasTb);
        if (muestrasTb.getColumnModel().getColumnCount() > 0) {
            muestrasTb.getColumnModel().getColumn(4).setResizable(false);
        }

        buscarBtn3.setText("Buscar");
        buscarBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtn3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Proyecto");

        jLabel2.setText("Numero de Control");

        jLabel4.setText("Fecha de Recepción");

        jLabel5.setText("Fecha Muestreo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(buscarBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(proyectoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fmTF, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(frTF)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proyectoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fmTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarBtn3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadMuestras(List<Muestra> list) throws Exception {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Num. C.", "Proyecto", "F. Muestreo", "H. Muestreo", "F. Recepción", "Muestreador"}, 0);

        for (Muestra m : list) {
            String fecha = m.fMuestreo.getYear()+"-"+m.fMuestreo.getMonthValue()+"-"+m.fMuestreo.getDayOfMonth();
            String hora = m.fMuestreo.getHour()+":"+m.fMuestreo.getMinute();
            String muestreador = "XXX";
            // TODO bug en repositorio
            // String muestreador = repoSig.searchBy(m.muestreador).siglas;
            Object[] newRow = { m.numControl, m.proyecto, fecha, hora, m.fRecepcion, muestreador};
            model.addRow(newRow);
        }

        muestrasTb.setModel(model);
    }

    private void buscarBtn3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buscarBtn3ActionPerformed
        LocalDateTime fM = null;
        LocalDate fR = null;
        String numC = numCTF.getText();
        String proj = proyectoTF.getText();
        String input;

        try {
            input = fmTF.getText().trim();

            if (input.length() != 0){
                System.out.println("input:" + input);
                System.out.println("input:" + input.length());
                fM = LocalDateTime.parse(input);
            }
                

            input = frTF.getText().trim();
            if (input.length() != 0){
                System.out.println("input:" + input);
                System.out.println("input:" + input.length());
                fR = LocalDate.parse(input);
            }
                

            List<Muestra> muestras = repoMuest.searchBy(numC, proj, fM, fR);
            loadMuestras(muestras);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Formato de fecha u hora no válidos",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error:" + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_buscarBtn3ActionPerformed

    public void preparar(){
        try {
            loadMuestras(repoMuest.getAllMuestras());    
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error:" + e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn3;
    private javax.swing.JTextField fmTF;
    private javax.swing.JTextField frTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable muestrasTb;
    private javax.swing.JTextField numCTF;
    private javax.swing.JTextField proyectoTF;
    // End of variables declaration//GEN-END:variables
}
