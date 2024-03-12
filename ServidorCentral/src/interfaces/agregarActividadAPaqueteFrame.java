package interfaces;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controladores.ManejadorTurismo;
import controladores.fabrica;
import datatypes.DTDepartamento;
import datatypes.DTPaquete;
import helpers.CampoIncompletoException;

public class agregarActividadAPaqueteFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	Set<String> acts;

	String [] datosActs;
	
	public agregarActividadAPaqueteFrame() {
		setTitle("Agregar actividad a paquete");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(550,850));
		pack();
		getContentPane().setLayout(new GridLayout(4,2));
		
		JPanel plNombre = new JPanel();
		JPanel plDepto = new JPanel();
		JPanel plAct = new JPanel();
		
		JPanel pCbNombre = new JPanel();
		JPanel pCbDepto = new JPanel();
		JPanel pCbAct = new JPanel();
		
		
		JLabel lblNombre = new JLabel("Nombre del paquete:");
		plNombre.add(lblNombre);
		add(plNombre);
		
		JComboBox<String> cbNombre = new JComboBox<String>();
		pCbNombre.add(cbNombre);
		add(pCbNombre);
		
		JLabel lblDepto = new JLabel("Departamento:");
		plDepto.add(lblDepto);
		add(plDepto);
		
		JComboBox<String> cbDepto = new JComboBox<String>();
		pCbDepto.add(cbDepto);
		add(pCbDepto);
		
		JLabel lblAct = new JLabel("Actividad:");
		plAct.add(lblAct);
		add(plAct);
		JComboBox<String> cbAct = new JComboBox<String>();
		pCbAct.add(cbAct);
		add(pCbAct);
		cbAct.addItem("Seleccione");
		
		JPanel panelAcept = new JPanel();
		getContentPane().add(panelAcept);
		
		JButton btnAceptar = new JButton("Aceptar");
		panelAcept.add(btnAceptar);
		
		JPanel panelCancel = new JPanel();
		getContentPane().add(panelCancel);
		
		JButton btnCancel = new JButton("Cancelar");
		panelCancel.add(btnCancel);

		cbNombre.addItem("Seleccione");
		
		Map<String,DTPaquete> paquetes = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesNoComprados();
		Iterator<String> it0 = paquetes.keySet().iterator();
		String key0;
		while (it0.hasNext()) {
			key0 = it0.next();
			cbNombre.addItem(paquetes.get(key0).getNombre());
		}

		cbDepto.addItem("Seleccione");

		Map<String,DTDepartamento> deptos = ManejadorTurismo.getInstance().obtenerDatosDepartamentos();
		Iterator<String> it1 = deptos.keySet().iterator();
		String key1;
		while (it1.hasNext()) {
			key1 = it1.next();
			cbDepto.addItem(deptos.get(key1).getNombre());
		}


		
		cbDepto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbNombre.getSelectedItem().toString() != "Seleccione" && cbDepto.getSelectedItem().toString() != "Seleccione") {
					acts = fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasFueraDePaquete(cbNombre.getSelectedItem().toString(), cbDepto.getSelectedItem().toString());
					datosActs = new String[acts.size() + 1];
					Iterator<String> it2 = acts.iterator();
					String key2;
					int i = 1;
					datosActs[0] = "Seleccione";
					while (it2.hasNext()) {
						key2 = it2.next();
						datosActs[i] = key2;
						i++;
					}
					
					DefaultComboBoxModel<String> cb = new DefaultComboBoxModel<String>(datosActs);
					cbAct.setModel(cb);
				}
			}
	        	
	    });

		cbNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbNombre.getSelectedItem() != "Seleccione" && cbDepto.getSelectedItem() != "Seleccione") {
					acts = fabrica.getInstance().getIControladorTurismo().obtenerActividadesFueraDePaquete(cbNombre.getSelectedItem().toString(), cbDepto.getSelectedItem().toString());
					datosActs = new String[acts.size() + 1];
					Iterator<String> it2 = acts.iterator();
					String key2;
					int i = 1;
					datosActs[0] = "Seleccione";
					while (it2.hasNext()) {
						key2 = it2.next();
						datosActs[i] = key2;
						i++;
					}
					
					DefaultComboBoxModel<String> cb = new DefaultComboBoxModel<String>(datosActs);
					cbAct.setModel(cb);


				}
			}
	    });

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cbNombre.getSelectedItem() == "Seleccione" || cbDepto.getSelectedItem() == "Seleccione" || cbAct.getSelectedItem() == "Seleccione")
						throw new CampoIncompletoException("Seleccione todas las opciones");
					String paq = cbNombre.getSelectedItem().toString();
					String act = cbAct.getSelectedItem().toString();
					fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act, paq);
					JOptionPane.showMessageDialog(agregarActividadAPaqueteFrame.this,"Actividad agregada");
					dispose();			
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(agregarActividadAPaqueteFrame.this,e2.getMessage());
				}
			}
		});

	}
}
