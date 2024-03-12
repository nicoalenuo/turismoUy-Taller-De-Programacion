package interfaces;

import javax.swing.*;
import javax.swing.event.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controladores.*;
import datatypes.*;
import helpers.CampoIncompletoException;


public class modificarUsuarioFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private String [] nomColumnas = {"Tipo", "Nickname", "Email"};
	
	private Object[][] datosFila;
	
	JTable tUsuarios = null;
	JScrollPane sp = null;
	
	public modificarUsuarioFrame() {
		fabrica f = fabrica.getInstance();
		
		setTitle("Modificar usuario");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(800, 800));
		pack();
		
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		
		JButton modificar = new JButton("Modificar");
		modificar.setEnabled(false);
		
		int i = 0;
		Map<String, DTUsuario> users = f.getIControladorUsuarios().obtenerUsuariosEmail();
		datosFila = new Object[users.size()][3];
		Iterator<String> it = users.keySet().iterator();
		String key;
		
		while (it.hasNext()) {
			key = it.next();
			
			if (DTProveedor.class.isInstance(users.get(key))) {
				DTProveedor p = DTProveedor.class.cast(users.get(key));
				datosFila[i][0] = "Proveedor";
				datosFila[i][1] = p.getNickname();
				datosFila[i][2] = p.getEmail();
			} else {
				DTTurista t = DTTurista.class.cast(users.get(key));
				datosFila[i][0] = "Turista";
				datosFila[i][1] = t.getNickname();
				datosFila[i][2] = t.getEmail();
			}
			i++;
		}
		tUsuarios = new JTable(datosFila, nomColumnas);
		tUsuarios.getColumnModel().getColumn(2).setPreferredWidth(200);
		sp = new JScrollPane(tUsuarios);
		tUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(sp);
		sp.setPreferredSize(new Dimension(150,150));
		
		JTextArea taNacionalidad;
		JTextArea taSitioWeb;
		JTextArea taDescripcion;
		
		JPanel pNombreIzq = new JPanel();
		JPanel pApellidoIzq = new JPanel();
		JPanel pNicknameIzq = new JPanel();
		JPanel pEmailIzq = new JPanel();
		JPanel pContraIzq = new JPanel();
		JPanel pSeleccionIzq = new JPanel();
		JPanel pNacionalidadWebIzq = new JPanel();
		JPanel pDescIzq = new JPanel();
		JPanel pFechaIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pApellidoDer = new JPanel();
		JPanel pNicknameDer = new JPanel();
		JPanel pEmailDer = new JPanel();
		JPanel pContraDer = new JPanel();
		JPanel pSeleccionDer = new JPanel();
		JPanel pNacionalidadWebDer = new JPanel();
		JPanel pDescDer = new JPanel();
		JPanel pFechaDer = new JPanel();
		
		JLabel lTipo = new JLabel();
		
		lTipo.setVisible(false);
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		fl.setHgap(10);
		
		p3.setLayout(fl);
		
		pNombreIzq.setLayout(fl);
		pApellidoIzq.setLayout(fl);
		pNicknameIzq.setLayout(fl);
		pEmailIzq.setLayout(fl);
		pContraIzq.setLayout(fl);
		pSeleccionIzq.setLayout(fl);
		pNacionalidadWebIzq.setLayout(fl);
		pDescIzq.setLayout(fl);
		pFechaIzq.setLayout(fl);
		
		pNombreDer.setLayout(fl);
		pApellidoDer.setLayout(fl);
		pNicknameDer.setLayout(fl);
		pEmailDer.setLayout(fl);
		pContraDer.setLayout(fl);
		pSeleccionDer.setLayout(fl);
		pNacionalidadWebDer.setLayout(fl);
		pDescDer.setLayout(fl);
		pFechaDer.setLayout(fl);
		
		p2.setLayout(new GridLayout(10,2));
		
		JTextArea taNombre = new JTextArea(1,15);
		pNombreIzq.add(new JLabel("Nombre:  "));
		pNombreDer.add(taNombre);
		
		JTextArea taApellido = new JTextArea(1,15);
		pApellidoIzq.add(new JLabel("Apellido: "));
		pApellidoDer.add(taApellido);
		
		JTextArea taNickname = new JTextArea(1,15);
		pNicknameIzq.add(new JLabel("Nickname:"));
		pNicknameDer.add(taNickname);
		taNickname.setEditable(false);
		
		JTextArea taEmail = new JTextArea(1,15);
		pEmailIzq.add(new JLabel("Email:   "));
		pEmailDer.add(taEmail);
		taEmail.setEditable(false);

		JTextArea taContra = new JTextArea(1,15);
		pContraIzq.add(new JLabel("Contraseña:   "));
		pContraDer.add(taContra);
		
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
		taContra.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taNacionalidad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taSitioWeb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
	    taDescripcion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1))); 
		
	    pSeleccionIzq.add(new JLabel("Tipo:"));
		pSeleccionDer.add(lTipo);
		pFechaIzq.add(new JLabel("Fecha de nacimiento:"));
		
		
		JDateChooser taFecha = new JDateChooser();
		pFechaDer.add(taFecha);
		taFecha.setPreferredSize(new Dimension(150,20));
		
		p2.add(pNombreIzq);
		p2.add(pNombreDer);
		p2.add(pApellidoIzq);
		p2.add(pApellidoDer);
		p2.add(pNicknameIzq);
		p2.add(pNicknameDer);
		p2.add(pEmailIzq);
		p2.add(pEmailDer);
		p2.add(pContraIzq);
		p2.add(pContraDer);
		p2.add(pSeleccionIzq);
		p2.add(pSeleccionDer);
		p2.add(pNacionalidadWebIzq);
		p2.add(pNacionalidadWebDer);
		p2.add(pDescIzq);
		p2.add(pDescDer);
		p2.add(pFechaIzq);
		p2.add(pFechaDer);
		
		tUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	lTipo.setVisible(true);
	        	DTUsuario seleccionado = users.get((String) datosFila[tUsuarios.getSelectedRow()][2]);	
	        	modificar.setEnabled(true);
	        	taNacionalidad.setText("");
	        	taSitioWeb.setText("");
	        	taDescripcion.setText("");
	        	
	        	taNombre.setText(seleccionado.getNombre());
	        	taApellido.setText(seleccionado.getApellido());
	        	taNickname.setText(seleccionado.getNickname());
	        	taContra.setText(seleccionado.getContra());
	        	taEmail.setText(seleccionado.getEmail());
	        	
	        	taFecha.setToolTipText(Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.DAY_OF_MONTH)) + "/" + Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.MONTH) + 1) + "/" + Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.YEAR)));

	        	if (DTProveedor.class.isInstance(seleccionado)) {
	        		lTipo.setText("Proveedor");
	        		DTProveedor p = DTProveedor.class.cast(seleccionado);
	        		
	        		taNacionalidad.setVisible(false);
					lbl5.setVisible(false);
					
					taSitioWeb.setVisible(true);
					lbl6.setVisible(true);
					
					taDescripcion.setVisible(true);
					lbl7.setVisible(true);
					
					taSitioWeb.setText(p.getSitioWeb());
					taDescripcion.setText(p.getDescripcion());
				} else {
	        		DTTurista t = DTTurista.class.cast(seleccionado);
	        		lTipo.setText("Turista");
					taNacionalidad.setVisible(true);
					lbl5.setVisible(true);
					
					taSitioWeb.setVisible(false);
					lbl6.setVisible(false);
					
					taDescripcion.setVisible(false);
					lbl7.setVisible(false);
					
					taNacionalidad.setText(t.getNacionalidad());
				}
	        }
	    });
		
		p3.add(modificar);
		
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (taFecha.getDate() == null) { 
						throw new CampoIncompletoException("Falta algun campo por completar");
					}
					
					DTUsuario nuevo;
					
	                GregorianCalendar cal = new GregorianCalendar();
	                cal.setTime(taFecha.getDate());
	                
					if ((String) lTipo.getText() == "Proveedor") {
						nuevo = new DTProveedor(taNickname.getText(), taContra.getText(), taNombre.getText(), taApellido.getText(), taEmail.getText(), taSitioWeb.getText(), taDescripcion.getText(), cal);
					} else {
						nuevo = new DTTurista(taNickname.getText(), taContra.getText(), taNombre.getText(), taApellido.getText(), taEmail.getText(), taNacionalidad.getText(), cal);
					}
					
					f.getIControladorUsuarios().modificarUsuario(nuevo);
					JOptionPane.showMessageDialog(modificarUsuarioFrame.this,"Usuario modificado");
					dispose();	
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(modificarUsuarioFrame.this,e2.getMessage());
				}
			}
		});
		
		revalidate();

	}
}