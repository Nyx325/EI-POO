package Vista.Forms;

import Controlador.ControladorGraficos;
import Modelo.Repositorio.Conector;
import Vista.Extras.VentanaUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

public class GraficosVista extends javax.swing.JFrame {
    ControladorGraficos chartCtl = new ControladorGraficos();
    VentanaUtils utils = new VentanaUtils(this);
    
    public GraficosVista() {
        initComponents();
        utils.centrarEnPantalla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartView = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chartComboBox = new javax.swing.JComboBox<>();

        chartView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartViewLayout = new javax.swing.GroupLayout(chartView);
        chartView.setLayout(chartViewLayout);
        chartViewLayout.setHorizontalGroup(
            chartViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartViewLayout.setVerticalGroup(
            chartViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        chartComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html><br>", "1", "2", "3", "4", "5" }));
        chartComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(chartComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chartView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chartComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartComboBoxActionPerformed
        int index = chartComboBox.getSelectedIndex();
        try {
            createChart(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_chartComboBoxActionPerformed

    public void createChart(int opc) throws Exception {
        JFreeChart chart;
        
        switch (opc) {
            case 1:
                chart = ChartFactory.createBarChart3D(
                    "Pruebas realizadas por año", // Nombre del grafico
                    "Prueba", // Nombre de las barras (eje x)
                    "Resultados por año", // Nombre de los valores (eje y)
                    chartCtl.chartDataSet(opc), // Datos del grafico
                    PlotOrientation.VERTICAL, // Orientación
                    true, // Leyenda para los valores en barra (individuales)
                    false, // Herramientas
                    false // URL del gráfico
                );
                break;
            case 2:
                chart = ChartFactory.createBarChart3D(
                    "Signatarios con más resutlados", // Nombre del grafico
                    "Signatario", // Nombre de las barras (eje x)
                    "Resultados", // Nombre de los valores (eje y)
                    chartCtl.chartDataSet(opc), // Datos del grafico
                    PlotOrientation.VERTICAL, // Orientación
                    true, // Leyenda para los valores en barra (individuales)
                    false, // Herramientas
                    false // URL del gráfico
                );
                break;
            case 3:
                chart = ChartFactory.createBarChart3D(
                    "Normas con más pruebas", // Nombre del grafico
                    "Norma", // Nombre de las barras (eje x)
                    "Pruebas", // Nombre de los valores (eje y)
                    chartCtl.chartDataSet(opc), // Datos del grafico
                    PlotOrientation.VERTICAL, // Orientación
                    true, // Leyenda para los valores en barra (individuales)
                    false, // Herramientas
                    false // URL del gráfico
                );
                break;
            case 4:
                chart = ChartFactory.createBarChart3D(
                    "Sitios con más resultados", // Nombre del grafico
                    "Sitios", // Nombre de las barras (eje x)
                    "Resultados", // Nombre de los valores (eje y)
                    chartCtl.chartDataSet(opc), // Datos del grafico
                    PlotOrientation.VERTICAL, // Orientación
                    true, // Leyenda para los valores en barra (individuales)
                    false, // Herramientas
                    false // URL del gráfico
                );
                break;
            case 5:
                chart = ChartFactory.createBarChart3D(
                    "Clientes frecuentes", // Nombre del grafico
                    "Clientes", // Nombre de las barras (eje x)
                    "Resultados", // Nombre de los valores (eje y)
                    chartCtl.chartDataSet(opc), // Datos del grafico
                    PlotOrientation.VERTICAL, // Orientación
                    true, // Leyenda para los valores en barra (individuales)
                    false, // Herramientas
                    false // URL del gráfico
                );
                break;
            default:
                return;
        }

        // Personaliza el eje X
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(400, 200));

        // Asegúrate de que el panel se redimensiona correctamente
        chartPanel.setMaximumDrawWidth(Integer.MAX_VALUE);
        chartPanel.setMaximumDrawHeight(Integer.MAX_VALUE);

        chartView.removeAll();
        chartView.setLayout(new BorderLayout());
        chartView.add(chartPanel, BorderLayout.CENTER); // Añade al centro para ocupar todo el espacio
                                                                         // disponible
        chartView.revalidate();
        chartView.repaint();
    }

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
            java.util.logging.Logger.getLogger(GraficosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Conector.login("rubenor", "");
                    new GraficosVista().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> chartComboBox;
    private javax.swing.JPanel chartView;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
