package interfaces;

import javax.swing.*;
import controladores.fabrica;
import datatypes.DTCategoria;
import helpers.CampoIncompletoException;
import helpers.CategoriaRepetidaException;
import java.awt.*;
import java.awt.event.*;


public class altaCategoriaFrame extends JInternalFrame {	
	
	private static final long serialVersionUID = 1L;

	public altaCategoriaFrame() {
		setTitle("Alta de Categoria");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setVisible(true);
		setPreferredSize(new Dimension(600,200));
		pack();
		
		fabrica f = fabrica.getInstance();
		
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new GridLayout(1,2));
		p2.setLayout(new GridLayout(1,2));
		
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
				
		//Otros paneles
		JPanel pCategoriaIzq = new JPanel();
		JPanel pCategoriaDer = new JPanel();
		JPanel pAceptarIzq = new JPanel();
		JPanel pCancelarDer = new JPanel();
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		fl.setHgap(10);
		
		pCategoriaIzq.setLayout(fl);
		
		pCategoriaDer.setLayout(fl);

		pCategoriaIzq.add(new JLabel("Nombre de la categoría "));
		
		JTextArea tf1 = new JTextArea(1,15);
		pCategoriaDer.add(tf1);
		
		tf1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		
		p1.add(pCategoriaIzq);
		p1.add(pCategoriaDer);
		
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		pAceptarIzq.add(aceptar);
		pCancelarDer.add(cancelar);
		
		p2.add(pAceptarIzq);
		p2.add(pCancelarDer);
		
		//EVENTOS
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tf1.getText().isBlank()) { 
						throw new CampoIncompletoException("Falta ingresar una categoria");
					}
					DTCategoria dtC = new DTCategoria(tf1.getText());
					f.getIControladorTurismo().confirmarAltaCategoria(dtC);
					JOptionPane.showMessageDialog(altaCategoriaFrame.this,"Categoria creada");
				    altaCategoriaFrame.this.dispose();
				} catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(altaCategoriaFrame.this,e2.getMessage());
				} catch(CategoriaRepetidaException e3) {
					JOptionPane.showMessageDialog(altaCategoriaFrame.this,e3.getMessage());
				}
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaCategoriaFrame.this.dispose();
			}
		});
		
	}
}