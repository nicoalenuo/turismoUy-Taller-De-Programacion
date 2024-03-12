package interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controladores.*;
import datatypes.*;
import helpers.CampoIncompletoException;
import helpers.ContrasenasNoCoincidenException;
import helpers.UsuarioRepetidoException;
import com.toedter.calendar.JDateChooser;


public class altaUsuarioFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public altaUsuarioFrame() {
		fabrica f = fabrica.getInstance();
		setTitle("Alta de usuario");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(550,550));
		pack();
		
		JTextArea taNacionalidad;
		JTextArea taSitioWeb;
		JTextArea taDescripcion;
		
		JPanel pNombreIzq = new JPanel();
		JPanel pApellidoIzq = new JPanel();
		JPanel pNicknameIzq = new JPanel();
		JPanel pEmailIzq = new JPanel();
		JPanel pContraIzq = new JPanel();
		JPanel pConContraIzq = new JPanel();
		JPanel pSeleccionIzq = new JPanel();
		JPanel pNacionalidadWebIzq = new JPanel();
		JPanel pDescIzq = new JPanel();
		JPanel pFechaIzq = new JPanel();
		JPanel pAceptarIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pApellidoDer = new JPanel();
		JPanel pNicknameDer = new JPanel();
		JPanel pEmailDer = new JPanel();
		JPanel pContraDer = new JPanel();
		JPanel pConContraDer = new JPanel();
		JPanel pSeleccionDer = new JPanel();
		JPanel pNacionalidadWebDer = new JPanel();
		JPanel pDescDer = new JPanel();
		JPanel pFechaDer = new JPanel();
		JPanel pCancelarDer = new JPanel();
		
		JComboBox<String> cb = new JComboBox<>();
		
		cb.addItem("Seleccione");
		cb.addItem("Turista");
		cb.addItem("Proveedor");
		
		
		pNombreIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pApellidoIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNicknameIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pEmailIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pContraIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pConContraIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pSeleccionIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNacionalidadWebIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pDescIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pFechaIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pAceptarIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		pNombreDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pApellidoDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNicknameDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pEmailDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pContraDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pConContraIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pSeleccionDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNacionalidadWebDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pDescDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pFechaDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pCancelarDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		getContentPane().setLayout(new GridLayout(11,2));
		
		JTextArea taNombre = new JTextArea(1,15);
		pNombreIzq.add(new JLabel("Nombre:  "));
		pNombreDer.add(taNombre);
		
		JTextArea taApellido = new JTextArea(1,15);
		pApellidoIzq.add(new JLabel("Apellido: "));
		pApellidoDer.add(taApellido);
		
		JTextArea taNickname = new JTextArea(1,15);
		pNicknameIzq.add(new JLabel("Nickname:"));
		pNicknameDer.add(taNickname);
		
		JTextArea taEmail = new JTextArea(1,15);
		pEmailIzq.add(new JLabel("Email:   "));
		pEmailDer.add(taEmail);
		
		JTextArea taContra = new JTextArea(1,15);
		pContraIzq.add(new JLabel("Contraseña:"));
		pContraDer.add(taContra);
		
		JTextArea taConContra = new JTextArea(1,15);
		pConContraIzq.add(new JLabel("Confirmacion de contraseña:"));
		pConContraDer.add(taConContra);
		
		JLabel lbl5 = new JLabel("Nacionalidad: ");
		taNacionalidad = new JTextArea(1,15);
		pNacionalidadWebIzq.add(lbl5);
		pNacionalidadWebDer.add(taNacionalidad);
		
		JLabel lbl6 = new JLabel("Sitio web: ");
		taSitioWeb = new JTextArea(1,15);
		pNacionalidadWebIzq.add(lbl6);
		pNacionalidadWebDer.add(taSitioWeb);
		
		JLabel lbl7 = new JLabel("Descripción: ");
		taDescripcion = new JTextArea(2,22);
		taDescripcion.setLineWrap(true);
		taDescripcion.setWrapStyleWord(true);
		pDescIzq.add(lbl7);
		pDescDer.add(taDescripcion);
		
		taNacionalidad.setVisible(false);
		taSitioWeb.setVisible(false);
		taDescripcion.setVisible(false);
		lbl5.setVisible(false);
		lbl6.setVisible(false);
		lbl7.setVisible(false);
		
		taNombre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taApellido.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taNickname.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taNacionalidad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taContra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taConContra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taSitioWeb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
	    taDescripcion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1))); 
	    
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		pSeleccionIzq.add(cb);
		
		pFechaIzq.add(new JLabel("Fecha de nacimiento:"));
        
		JDateChooser taFecha = new JDateChooser();
		pFechaDer.add(taFecha);
		taFecha.setPreferredSize(new Dimension(150,20));
        
		pAceptarIzq.add(aceptar);
		pCancelarDer.add(cancelar);
		
		getContentPane().add(pNombreIzq);
		getContentPane().add(pNombreDer);
		getContentPane().add(pApellidoIzq);
		getContentPane().add(pApellidoDer);
		getContentPane().add(pNicknameIzq);
		getContentPane().add(pNicknameDer);
		getContentPane().add(pEmailIzq);
		getContentPane().add(pEmailDer);
		getContentPane().add(pContraIzq);
		getContentPane().add(pContraDer);
		getContentPane().add(pConContraIzq);
		getContentPane().add(pConContraDer);
		getContentPane().add(pSeleccionIzq);
		getContentPane().add(pSeleccionDer);
		getContentPane().add(pNacionalidadWebIzq);
		getContentPane().add(pNacionalidadWebDer);
		getContentPane().add(pDescIzq);
		getContentPane().add(pDescDer);
		getContentPane().add(pFechaIzq);
		getContentPane().add(pFechaDer);
		
		getContentPane().add(pAceptarIzq);
		getContentPane().add(pCancelarDer);
		
		
		//EVENTOS
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioFrame.this.dispose();	
			}
		});
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTUsuario nuevo;
				try {
					if (taFecha.getDate() == null || (String) cb.getSelectedItem() == "Seleccione") { 
						throw new CampoIncompletoException("Falta algun campo por completar");
					}
				
	                GregorianCalendar cal = new GregorianCalendar();
	                cal.setTime(taFecha.getDate());
	                
					if ((String) cb.getSelectedItem() == "Proveedor") {
						nuevo = new DTProveedor(taNickname.getText(),taContra.getText(), taNombre.getText(), taApellido.getText(), taEmail.getText(), taSitioWeb.getText(), taDescripcion.getText(), cal);
					} else {
						nuevo = new DTTurista(taNickname.getText(),taContra.getText(), taNombre.getText(), taApellido.getText(), taEmail.getText(), taNacionalidad.getText(), cal);
					}
						f.getIControladorUsuarios().confirmarAltaUsuario(nuevo, taConContra.getText());
						JOptionPane.showMessageDialog(altaUsuarioFrame.this,"Usuario creado");
						altaUsuarioFrame.this.dispose();		
				} catch(UsuarioRepetidoException e2) {
					JOptionPane.showMessageDialog(altaUsuarioFrame.this,e2.getMessage());
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(altaUsuarioFrame.this,e2.getMessage());
				} catch(ContrasenasNoCoincidenException e2) {
					JOptionPane.showMessageDialog(altaUsuarioFrame.this,e2.getMessage());
				}
				
			}
		});
		
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((String) cb.getSelectedItem() == "Turista") {
					taNacionalidad.setVisible(true);
					lbl5.setVisible(true);
					
					taSitioWeb.setVisible(false);
					lbl6.setVisible(false);
					
					taDescripcion.setVisible(false);
					lbl7.setVisible(false);
				} else if ((String) cb.getSelectedItem() == "Proveedor") {
					taNacionalidad.setVisible(false);
					lbl5.setVisible(false);
					
					taSitioWeb.setVisible(true);
					lbl6.setVisible(true);
					
					taDescripcion.setVisible(true);
					lbl7.setVisible(true);
				} else {
					taNacionalidad.setVisible(false);
					lbl5.setVisible(false);
					
					taSitioWeb.setVisible(false);
					lbl6.setVisible(false);
					
					taDescripcion.setVisible(false);
					lbl7.setVisible(false);
				}
				
			}
		});
		
	}
}
