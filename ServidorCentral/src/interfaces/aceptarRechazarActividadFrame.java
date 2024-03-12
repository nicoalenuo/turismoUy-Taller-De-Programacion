package interfaces;

import controladores.fabrica;
import helpers.CampoIncompletoException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class aceptarRechazarActividadFrame extends JInternalFrame {
    private static final long serialVersionUID = 1L;

	Set<String> acts;

	String [] datosActs;
	
	public aceptarRechazarActividadFrame() {
        fabrica f = fabrica.getInstance();
        
		setTitle("Aceptar/Rechazar Actividad");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(550,850));
		pack();
		getContentPane().setLayout(new GridLayout(4,2));
		
		JPanel plAct = new JPanel();
		
		JPanel pCbAct = new JPanel();
				
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
		
		JPanel panelRechazar = new JPanel();
		getContentPane().add(panelRechazar);
		
		JButton btnRechazar = new JButton("Rechazar");
		panelRechazar.add(btnRechazar);

		

		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        acts = f.getIControladorTurismo().listarActividadesAgregadas();
        datosActs = new String[acts.size() + 1];
        Iterator<String> it = acts.iterator();
        String key;
        int i = 1;
        datosActs[0] = "Seleccione";
        while (it.hasNext()) {
            key = it.next();
            datosActs[i] = key;
            i++;
        }
        DefaultComboBoxModel<String> cb = new DefaultComboBoxModel<String>(datosActs);
        cbAct.setModel(cb);

        btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbAct.getSelectedItem() == "Seleccione")
						throw new CampoIncompletoException("Seleccione Actividad");
					String act = cbAct.getSelectedItem().toString();
					f.getIControladorTurismo().cambiarEstadoActividad(act, false);
					JOptionPane.showMessageDialog(aceptarRechazarActividadFrame.this,"Actividad Rechazada");
					dispose();			
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(aceptarRechazarActividadFrame.this,e2.getMessage());
				}
			}
		});

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbAct.getSelectedItem() == "Seleccione")
						throw new CampoIncompletoException("Seleccione Actividad");
					String act = cbAct.getSelectedItem().toString();
					f.getIControladorTurismo().cambiarEstadoActividad(act, true);
					JOptionPane.showMessageDialog(aceptarRechazarActividadFrame.this,"Actividad Aceptada");
					dispose();			
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(aceptarRechazarActividadFrame.this,e2.getMessage());
				}
			}
		});

	}
}
