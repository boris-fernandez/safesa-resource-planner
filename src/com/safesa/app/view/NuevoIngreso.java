/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.safesa.app.view;

import com.safesa.app.controller.MovimientoController;
import com.safesa.app.dto.ProductoDto;
import java.util.ArrayList;

/**
 *
 * @author BORIS
 */
public class NuevoIngreso extends javax.swing.JFrame {
    private MovimientoController controller;
    private ProductoDto productoDto;
    /**
     * Creates new form Ingreso
     */
    public NuevoIngreso() {
        initComponents();
        setLocationRelativeTo(null);
        productoDto = new ProductoDto();
        controller = new MovimientoController();
        llenarComboBoxProductos();
    }

    private void llenarComboBoxProductos() {
        ArrayList<String> nombresProductos = productoDto.listaProductoPorNombre();
        cbxNombreProd.removeAllItems();
        for (String nombre : nombresProductos) {
            cbxNombreProd.addItem(nombre);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxNombreProd = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtMetodPago = new javax.swing.JTextField();
        btnHistMovimiento = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        txtApellidos1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnDatos = new javax.swing.JToggleButton();
        btnMonto = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnAgregarIngreso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(16, 20, 34));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/IngresosGrande.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 106, 122));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingresos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ingresa los datos del producto:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresa los datos del cliente");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, -1, -1));

        cbxNombreProd.setBackground(new java.awt.Color(255, 255, 255));
        cbxNombreProd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxNombreProd.setForeground(new java.awt.Color(0, 0, 0));
        cbxNombreProd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre del Producto", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbxNombreProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 197, 34));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Metodo de pago(Efectivo, Tarjeta):");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DNI:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombres:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));

        txtDNI.setBackground(new java.awt.Color(255, 255, 255));
        txtDNI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDNI.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 217, 28));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 217, 30));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 217, 31));

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 197, 30));

        txtMetodPago.setBackground(new java.awt.Color(255, 255, 255));
        txtMetodPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMetodPago.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtMetodPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 200, 30));

        btnHistMovimiento.setBackground(new java.awt.Color(0, 0, 204));
        btnHistMovimiento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHistMovimiento.setForeground(new java.awt.Color(255, 255, 255));
        btnHistMovimiento.setText("Agregar Ingreso");
        btnHistMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistMovimientoActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 180, 30));

        btnRegresar.setBackground(new java.awt.Color(0, 255, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Volver");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 110, 40));

        txtApellidos1.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidos1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtApellidos1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtApellidos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 217, 31));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Apellidos:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, -1, -1));

        btnDatos.setBackground(new java.awt.Color(153, 255, 153));
        btnDatos.setForeground(new java.awt.Color(255, 153, 102));
        btnDatos.setText("Mostrar Datos");
        btnDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosActionPerformed(evt);
            }
        });
        jPanel1.add(btnDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, -1, -1));

        btnMonto.setBackground(new java.awt.Color(153, 255, 153));
        btnMonto.setForeground(new java.awt.Color(255, 153, 102));
        btnMonto.setText("Mostrar Monto");
        btnMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMontoActionPerformed(evt);
            }
        });
        jPanel1.add(btnMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Telefono:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 217, 31));

        txtMonto.setEditable(false);
        txtMonto.setBackground(new java.awt.Color(255, 255, 255));
        txtMonto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMonto.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Monto:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        btnAgregarIngreso.setBackground(new java.awt.Color(255, 51, 51));
        btnAgregarIngreso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarIngreso.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarIngreso.setText("Historial Movimiento");
        btnAgregarIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarIngresoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 180, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        MenuOpciones mo = new MenuOpciones();
        mo.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnHistMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistMovimientoActionPerformed
        // TODO add your handling code here:
        controller.agregarIngreso(cbxNombreProd, txtCantidad, txtMonto, txtDNI, txtNombre, txtApellidos1, txtTelefono,txtEmail,txtMetodPago);
    }//GEN-LAST:event_btnHistMovimientoActionPerformed

    private void btnDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosActionPerformed
        // TODO add your handling code here:
        controller.mostrarDatosCliente(txtDNI, txtNombre, txtApellidos1, txtTelefono,txtEmail);
    }//GEN-LAST:event_btnDatosActionPerformed

    private void btnMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMontoActionPerformed
        // TODO add your handling code here:
        controller.mostrarIngresoMonto(cbxNombreProd, txtCantidad, txtMonto);
    }//GEN-LAST:event_btnMontoActionPerformed

    private void btnAgregarIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarIngresoActionPerformed
        // TODO add your handling code here:
        var historalMovimiento = new HistorialMovimiento();
        historalMovimiento.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAgregarIngresoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarIngreso;
    private javax.swing.JToggleButton btnDatos;
    private javax.swing.JButton btnHistMovimiento;
    private javax.swing.JToggleButton btnMonto;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxNombreProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellidos1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMetodPago;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
