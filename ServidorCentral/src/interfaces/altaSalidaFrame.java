package interfaces;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controladores.fabrica;
import datatypes.DTDepartamento;
import datatypes.DTSalida;
import helpers.ActividadInvalidaException;
import helpers.CampoIncompletoException;
import helpers.CantidadTuristaException;
import helpers.SalidaRepetidaException;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class altaSalidaFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String [] nomColumnasDepartamento = {"Nombre Departamento"};
	private Object[][] datosFilaDepartamento;

	private String[] nomColumnasActividades = {"Nombre de las actividades en el departamento"};
	private Object[][] datosFilaActividades;
	
	JTable tDepartamentos = null;
	JTable tActividades = null;
	JTable tSalidas = null;
	JScrollPane spDepartamento = null;
	JScrollPane spActividades = null;
	JScrollPane spSalidas = null;

	public altaSalidaFrame() {
		fabrica f = fabrica.getInstance();

		setTitle("Alta de salida");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(650,800));
		pack();

		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p3 = new JPanel();
		
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p3.setLayout(new GridLayout(0,2));
		//p1.add(p2);
		add(p1);
		
		
		//departamentos 

		int i = 0;
		Map<String,DTDepartamento> departamentos = f.getIControladorTurismo().obtenerDepartamentos();
		datosFilaDepartamento = new Object[departamentos.size()][1];
		Iterator<String> it = departamentos.keySet().iterator();
		String key;
		
		while (it.hasNext()) {
			key = it.next();
			datosFilaDepartamento[i][0] = departamentos.get(key).getNombre();
			//datosFilaDepartamento[i][1] = departamentos.get(key).getDescripcion();
			//datosFilaDepartamento[i][2] = departamentos.get(key).getUrl();
			i++;
		}

		tDepartamentos = new JTable(datosFilaDepartamento,nomColumnasDepartamento);
		//tDepartamentos.getColumnModel().getColumn(3).setPreferredWidth(200);
		spDepartamento = new JScrollPane(tDepartamentos);
		Dimension d = new Dimension(120,60);
		spDepartamento.setPreferredSize(d);
		tDepartamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(spDepartamento);

		revalidate();
		//actividades
		
		
		datosFilaActividades = new Object[0][1];
		tActividades = new JTable(datosFilaActividades,nomColumnasActividades);
		spActividades = new JScrollPane(tActividades);
		spActividades.setPreferredSize(d);
		p1.add(spActividades);
		tActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(p3);
		

		JPanel pNombreLabel = new JPanel();
		JPanel pCantMaxLabel = new JPanel();
		JPanel pFechaSalidaLabel = new JPanel();
		JPanel pFechaAltaLabel = new JPanel();
		JPanel pLugarSalidaLabel = new JPanel();
		JPanel pCancelar = new JPanel();

		JPanel pNombre = new JPanel();
		JPanel pCantMax = new JPanel();
		JPanel pFechaSalida = new JPanel();
		JPanel pFechaAlta = new JPanel();
		JPanel pLugarSalida = new JPanel();
		JPanel pIngresarSalida = new JPanel();

		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		fl.setHgap(10);
		
		pNombreLabel.setLayout(fl);
		pCantMaxLabel.setLayout(fl);
		pFechaSalidaLabel.setLayout(fl);
		pFechaAltaLabel.setLayout(fl);
		pLugarSalidaLabel.setLayout(fl);
		pCancelar.setLayout(fl);

		pNombre.setLayout(fl);
		pCantMax.setLayout(fl);;
		pFechaSalida.setLayout(fl);
		pFechaAlta.setLayout(fl);
		pLugarSalida.setLayout(fl);
		pIngresarSalida.setLayout(fl);

        JTextArea taHoraSalida = new JTextArea(1,3);
		JTextArea taMinutosSalida = new JTextArea(1,3);

		JTextArea taNombre = new JTextArea(1,15);
		pNombreLabel.add(new JLabel("Nombre:  "));
		pNombre.add(taNombre);
		
		JTextArea taCantMax = new JTextArea(1,15);
		pCantMaxLabel.add(new JLabel("Cantidad Maxima: "));
		pCantMax.add(taCantMax);

		JTextArea taLugarSalida = new JTextArea(1,15);
		pLugarSalidaLabel.add(new JLabel("Lugar de Salida:   "));
		pLugarSalida.add(taLugarSalida);
		
		pFechaSalidaLabel.add(new JLabel("Fecha de salida:"));
		JDateChooser taFecha = new JDateChooser();
		taFecha.setPreferredSize(new Dimension(150,20));
        pFechaSalida.add(taFecha);
        
        pFechaSalida.add(taHoraSalida);
        pFechaSalida.add(new JLabel(":"));
        pFechaSalida.add(taMinutosSalida);
                     
        pFechaAltaLabel.add(new JLabel("Fecha de alta:"));
        JDateChooser taFecha2 = new JDateChooser();
		pFechaAlta.add(taFecha2);
		taFecha.setPreferredSize(new Dimension(150,20));
        
		taNombre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taCantMax.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taLugarSalida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taHoraSalida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		taMinutosSalida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));


		JButton ingresarSalida = new JButton("Ingresar Salida");
		pIngresarSalida.add(ingresarSalida);
		JButton cancelar = new JButton("Cancelar");
		pCancelar.add(cancelar);


		p3.add(pNombreLabel);
		p3.add(pNombre);
		p3.add(pCantMaxLabel);
		p3.add(pCantMax);
		p3.add(pFechaSalidaLabel);
		p3.add(pFechaSalida);
		p3.add(pFechaAltaLabel);
		p3.add(pFechaAlta);
		p3.add(pLugarSalidaLabel);
		p3.add(pLugarSalida);
		p3.add(pIngresarSalida);
		
		taHoraSalida.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();
		        }
		     }
		});
		
		taMinutosSalida.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();
		        }
		     }
		});
		

		tDepartamentos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				datosFilaActividades = new Object[0][1];
				
				
				DefaultTableModel dm3 = new DefaultTableModel(datosFilaActividades,nomColumnasActividades);
				tActividades.setModel(dm3); 
				
				String seleccionado = (String) datosFilaDepartamento[tDepartamentos.getSelectedRow()][0];
				Set<String> actividadesDepartamento = fabrica.getInstance().getIControladorTurismo().listarActividades(seleccionado);
				int j = 0;
				String act;
				datosFilaActividades = new Object[actividadesDepartamento.size()][1];


				Iterator<String> it = actividadesDepartamento.iterator();
				String key;
				while (it.hasNext()) {
					key = it.next();
					act = key;
					datosFilaActividades[j][0] = act;
					j++;
				}
				
				DefaultTableModel dm = new DefaultTableModel(datosFilaActividades,nomColumnasActividades);
				tActividades.setModel(dm);
				
				revalidate();
				
			}
		});
		taCantMax.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();
		        }
		     }
		});
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaSalidaFrame.this.dispose();	
			}
		});
		ingresarSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTSalida nueva;
				try {
					if (taNombre.getText().isEmpty() || taCantMax.getText().isEmpty() || taHoraSalida.getText().isEmpty() || taMinutosSalida.getText().isEmpty() || taFecha.getDate() == null || taFecha2.getDate() == null || tActividades.getSelectedRow() == -1 || tDepartamentos.getSelectedRow() == -1) { 
						throw new CampoIncompletoException("Falta algun campo por completar");
					}

					if (Integer.parseInt(taHoraSalida.getText()) > 23 || Integer.parseInt(taMinutosSalida.getText()) > 59) {
						throw new IllegalArgumentException("La hora de salida no es valida");
					}
					String seleccionada = (String) datosFilaActividades[tActividades.getSelectedRow()][0];
				
	                GregorianCalendar cal = new GregorianCalendar();
	                cal.setTime(taFecha.getDate());
	                cal.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(taHoraSalida.getText()));
	                cal.set(GregorianCalendar.MINUTE, Integer.parseInt(taMinutosSalida.getText()));
	                
	                GregorianCalendar cal2 = new GregorianCalendar();
	                cal2.setTime(taFecha2.getDate());
	                
					nueva = new DTSalida(taNombre.getText(), Integer.valueOf(taCantMax.getText()),0, cal2, cal, taLugarSalida.getText());
					f.getIControladorTurismo().ingresarSalida(seleccionada, nueva);
					JOptionPane.showMessageDialog(altaSalidaFrame.this,"Inscripcion a Salida existosa");
					altaSalidaFrame.this.dispose();
					altaSalidaFrame.this.revalidate();		
				} catch(SalidaRepetidaException e2) {
					JOptionPane.showMessageDialog(altaSalidaFrame.this,e2.getMessage());
				} catch (CampoIncompletoException e1) {
					JOptionPane.showMessageDialog(altaSalidaFrame.this,e1.getMessage());
				}
				catch (IllegalArgumentException e3) {
					JOptionPane.showMessageDialog(altaSalidaFrame.this,e3.getMessage());
				} catch (ActividadInvalidaException e1) {
					JOptionPane.showMessageDialog(altaSalidaFrame.this,e1.getMessage());
				} catch (CantidadTuristaException e1) {
					JOptionPane.showMessageDialog(altaSalidaFrame.this,e1.getMessage());
				}
				
			}
		});
	}
}
