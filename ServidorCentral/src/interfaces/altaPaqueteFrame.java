package interfaces;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import controladores.*;
import datatypes.*;

public class altaPaqueteFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea txtNombre;
	private JTextArea txtDescripcion;
	private JTextArea txtPeriodoValidez;
	private JTextArea txtDescuento;

	public altaPaqueteFrame() {
		fabrica inst_fabrica = fabrica.getInstance();

		getContentPane().setLayout(new BorderLayout());
		setTitle("Alta de paquete");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(800,800));
		pack();
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		JPanel pNombreIzq = new JPanel();
		JPanel pNombreDer = new JPanel();
		JPanel pDescripcionIzq = new JPanel();
		JPanel pDescripcionDer = new JPanel();
		JPanel pPeriodoValIzq = new JPanel();
		JPanel pPeriodoValDer = new JPanel();
		JPanel pDescuentoIzq = new JPanel();
		JPanel pDescuentoDer = new JPanel();
		JPanel pFechaIzq = new JPanel();
		JPanel pFechaDer = new JPanel();
		JPanel pAceptarIzq = new JPanel();
		JPanel pCancelarDer = new JPanel();
		
		pNombreIzq.setLayout(new FlowLayout());
		pNombreDer.setLayout(new FlowLayout());
		pDescripcionIzq.setLayout(new FlowLayout());
		pDescripcionDer.setLayout(new FlowLayout());
		pPeriodoValIzq.setLayout(new FlowLayout());
		pPeriodoValDer.setLayout(new FlowLayout());
		pDescuentoIzq.setLayout(new FlowLayout());
		pDescuentoDer.setLayout(new FlowLayout());
		pFechaIzq.setLayout(new FlowLayout());
		pFechaDer.setLayout(new FlowLayout());
		pAceptarIzq.setLayout(new FlowLayout());
		pCancelarDer.setLayout(new FlowLayout());
		
		panel.add(pNombreIzq);
		panel.add(pNombreDer);
		panel.add(pDescripcionIzq);
		panel.add(pDescripcionDer);
		panel.add(pPeriodoValIzq);
		panel.add(pPeriodoValDer);
		panel.add(pDescuentoIzq);
		panel.add(pDescuentoDer);
		panel.add(pFechaIzq);
		panel.add(pFechaDer);
		panel.add(pAceptarIzq);
		panel.add(pCancelarDer);
		
		//NOMBRE
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		pNombreIzq.add(lblNombre);
		
		txtNombre = new JTextArea(1,15);
		pNombreDer.add(txtNombre);
		
		//DESCRIPCION
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		pDescripcionIzq.add(lblDescripcion);
		
		txtDescripcion = new JTextArea(2,22);
		pDescripcionDer.add(txtDescripcion);
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		
		//PERIODO DE VALIDEZ
		JLabel lblPeriodoValidez = new JLabel("Periodo de validez:");
		lblPeriodoValidez.setHorizontalAlignment(SwingConstants.CENTER);
		pPeriodoValIzq.add(lblPeriodoValidez);
		
		txtPeriodoValidez = new JTextArea(1,5);
		txtPeriodoValidez.setToolTipText("Ingrese el periodo de validez en dias.");
		pPeriodoValDer.add(txtPeriodoValidez);

		txtPeriodoValidez.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		
		//DESCUENTO
		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		pDescuentoIzq.add(lblDescuento);
		
		txtDescuento = new JTextArea(1,5);
		txtDescuento.setToolTipText("Ingrese el descuento en porcentaje.");
		pDescuentoDer.add(txtDescuento);
		
		txtDescuento.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		
		//NOMBRE
		JLabel lblFecha = new JLabel("Fecha de alta:");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		pFechaIzq.add(lblFecha);
		
		JDateChooser taFecha = new JDateChooser();
		pFechaDer.add(taFecha);
		taFecha.setPreferredSize(new Dimension(150,20));
		
		txtNombre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		txtDescripcion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		txtPeriodoValidez.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		txtDescuento.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		
		//BOTON ACEPTAR Y CANCELAR
		JButton btnAceptar = new JButton("Aceptar");
		JButton btnCancelar = new JButton("Cancelar");
		pAceptarIzq.add(btnAceptar);
		pCancelarDer.add(btnCancelar);

		//EVENTO ACEPTAR
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				if(txtNombre.getText().isEmpty() || txtDescripcion.getText().isEmpty() || txtPeriodoValidez.getText().isEmpty() || txtDescuento.getText().isEmpty() || taFecha.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Hay campos sin completar.");
				} else {
					try {
						int descuentoInt = Integer.parseInt(txtDescuento.getText());
						if(descuentoInt < 0 || descuentoInt > 100) {
							JOptionPane.showMessageDialog(null, "El descuento debe estar entre 0 y 100.");
						} else {
							int periodoValidezInt = Integer.parseInt(txtPeriodoValidez.getText());
							if(periodoValidezInt < 0) {
								JOptionPane.showMessageDialog(null, "El periodo de validez debe ser mayor a 0.");
							} else {
								String nombre = txtNombre.getText();
								String descripcion = txtDescripcion.getText();
								GregorianCalendar cal = new GregorianCalendar();
				                cal.setTime(taFecha.getDate());
								DTPaquete paquete = new DTPaquete(nombre, descripcion, periodoValidezInt, descuentoInt,cal);
								inst_fabrica.getIControladorPaquete().confirmarPaquete(paquete);
								JOptionPane.showMessageDialog(null, "Paquete creado.");
								altaPaqueteFrame.this.dispose();
							}
						}
					} catch (IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(altaPaqueteFrame.this, e2.getMessage());
					}
				}

			}
		});
		
		//EVENTO CANCELAR
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				altaPaqueteFrame.this.dispose();
			}
		});
		
	}
}	