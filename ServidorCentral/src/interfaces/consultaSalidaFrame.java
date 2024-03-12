package interfaces;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import controladores.fabrica;
import datatypes.*;

public class consultaSalidaFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String [] nomColumnasDepartamento = {"Nombre Departamento"};
	private Object[][] datosFilaDepartamento;

	private String[] nomColumnasActividades = {"Nombre de las actividades en el departamento"};
	private Object[][] datosFilaActividades;

    private String [] nomColumnasSalidas = {"Nombre Salida"};
	private Object[][] datosFilaSalidas;

	
	JTable tDepartamentos= null;
	JTable tActividades= null;
	JTable tSalidas= null;
	JScrollPane spDepartamento = null;
	JScrollPane spActividades = null;
	JScrollPane spSalidas = null;

	public consultaSalidaFrame(String n) {
		fabrica f = fabrica.getInstance();

		setTitle("Consulta de salida");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(650,800));
		pack();

		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p2.setLayout(new GridLayout(0,2));
		//p1.add(p2);
		add(p1);
		add(p2);
		
		//departamentos 

		int i=0;
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
		Dimension d= new Dimension(120,60);
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
		
		datosFilaSalidas = new Object[0][1];
		tSalidas = new JTable(datosFilaSalidas,nomColumnasSalidas);
		spSalidas = new JScrollPane(tSalidas);
		spSalidas.setPreferredSize(new Dimension(20,20));
		tSalidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(spSalidas);
		
		add(p2,BorderLayout.SOUTH);
		

		JPanel pNombreLabel = new JPanel();
		JPanel pCantMaxLabel = new JPanel();
		JPanel pCantInsriptosLabel = new JPanel();
		JPanel pFechaSalidaLabel = new JPanel();
		JPanel pLugarSalidaLabel = new JPanel();
		JPanel pFechaAltaLabel = new JPanel();
		
		JPanel pNombre = new JPanel();
		JPanel pCantMax = new JPanel();
		JPanel pCantInsriptos = new JPanel();
		JPanel pFechaSalida = new JPanel();
		JPanel pLugarSalida = new JPanel();
		JPanel pFechaAlta = new JPanel();

		p2.add(pNombreLabel);
		p2.add(pNombre);
		p2.add(pCantMaxLabel);
		p2.add(pCantMax);
		p2.add(pCantInsriptosLabel);
		p2.add(pCantInsriptos);
		p2.add(pFechaSalidaLabel);
		p2.add(pFechaSalida);
		p2.add(pLugarSalidaLabel);
		p2.add(pLugarSalida);
		p2.add(pFechaAltaLabel);
		p2.add(pFechaAlta);
		
		JLabel lNombre = new JLabel();
		pNombreLabel.add(new JLabel("Nombre de la salida: "));
		pNombre.add(lNombre);

		JLabel lCantMax = new JLabel();
		pCantMaxLabel.add(new JLabel("Cantidad máxima de turistas: "));
		pCantMax.add(lCantMax);

		JLabel lCantInscriptos = new JLabel();
		pCantInsriptosLabel.add(new JLabel("Cantidad de turistas Inscriptos: "));
		pCantInsriptos.add(lCantInscriptos);

		JLabel lFechaSalida = new JLabel();
		pFechaSalidaLabel.add(new JLabel("Fecha de salida: "));
		pFechaSalida.add(lFechaSalida);

		JLabel lLugarSalida = new JLabel();
		pLugarSalidaLabel.add(new JLabel("Lugar de salida: "));
		pLugarSalida.add(lLugarSalida);
		
		JLabel lFechaAlta = new JLabel();
		pFechaAltaLabel.add(new JLabel("Fecha del alta: "));
		pFechaAlta.add(lFechaAlta);

		tDepartamentos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				datosFilaActividades = new Object[0][1];
				datosFilaSalidas = new Object[0][1];
				
				DefaultTableModel dm2 = new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas);
				tSalidas.setModel(dm2); 
				
				DefaultTableModel dm3 = new DefaultTableModel(datosFilaActividades,nomColumnasActividades);
				tActividades.setModel(dm3); 
				
				String seleccionado = (String) datosFilaDepartamento[tDepartamentos.getSelectedRow()][0];
				Set<String> actividadesDepartamento = fabrica.getInstance().getIControladorTurismo().listarActividades(seleccionado);
				int j=0;
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
				
				lNombre.setText("");
				
				lCantMax.setText("");

				lCantInscriptos.setText("");

				lFechaSalida.setText("");
				
				lLugarSalida.setText("");
				
				lFechaAlta.setText("");
				
				revalidate();
				
			}
		});

		
		tActividades.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (tActividades.getSelectedRow()!=-1) {
					
					String actSeleccionada = (String) datosFilaActividades[tActividades.getSelectedRow()][0];
					Map<String, DTSalida> salidasDeActividad = fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividad(actSeleccionada);
					int k = 0;
					DTSalida sal;
					datosFilaSalidas = new Object[salidasDeActividad.size()][1];
					Iterator<String> it = salidasDeActividad.keySet().iterator();
					String key;
					while(it.hasNext()) {
						key = it.next();
						sal = salidasDeActividad.get(key);
						datosFilaSalidas[k][0] = sal.getNombre();
						k++;
					}
	
					DefaultTableModel dm = new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas);
					tSalidas.setModel(dm); 
					
					lNombre.setText("");
					
					lCantMax.setText("");
	
					lCantInscriptos.setText("");
	
					lFechaSalida.setText("");
					
					lLugarSalida.setText("");
					
					lFechaAlta.setText("");

					revalidate();
				}
			}
		});

		tSalidas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (tSalidas.getSelectedRow()!=-1) {
					String salSeleccionada = (String) datosFilaSalidas[tSalidas.getSelectedRow()][0];
					DTSalida datosSalida = fabrica.getInstance().getIControladorTurismo().datosSalida(salSeleccionada);
					String horaReal = "";
					String minutoReal= "";
					
					lNombre.setText(datosSalida.getNombre());
	
					lCantMax.setText(Integer.toString(datosSalida.getCantMax()));
	
					lCantInscriptos.setText(Integer.toString(datosSalida.getCantInscriptos()));
					
					if(datosSalida.getFechaSalida().get(GregorianCalendar.HOUR_OF_DAY)<=9) {
						horaReal= "0"+datosSalida.getFechaSalida().get(GregorianCalendar.HOUR_OF_DAY);
					}else {
						horaReal= Integer.toString(datosSalida.getFechaSalida().get(GregorianCalendar.HOUR_OF_DAY));
					}
					
					if(datosSalida.getFechaSalida().get(GregorianCalendar.MINUTE)<=9) {
						minutoReal= "0"+datosSalida.getFechaSalida().get(GregorianCalendar.MINUTE);
					}else {
						minutoReal= Integer.toString(datosSalida.getFechaSalida().get(GregorianCalendar.MINUTE));
					}

					lFechaSalida.setText(datosSalida.getFechaSalida().get(GregorianCalendar.DAY_OF_MONTH)+"/"+Integer.toString(datosSalida.getFechaSalida().get(GregorianCalendar.MONTH) +1)+"/"+datosSalida.getFechaSalida().get(GregorianCalendar.YEAR)+" Hora: "+horaReal+":"+minutoReal);
					
					lLugarSalida.setText(datosSalida.getLugarSalida());
					
					lFechaAlta.setText(datosSalida.getFechaAlta().get(GregorianCalendar.DAY_OF_MONTH)+"/"+Integer.toString(datosSalida.getFechaAlta().get(GregorianCalendar.MONTH) +1)+"/"+datosSalida.getFechaAlta().get(GregorianCalendar.YEAR));

					revalidate();
				}
			}
		});
		
		if (n!=null) {
			String nombreAct = fabrica.getInstance().getIControladorTurismo().obtenerActividadDeSalida(n);
			String nombreDep = fabrica.getInstance().getIControladorTurismo().obtenerDepartamentoConActividad(nombreAct);
			
			
			int j =-1;
			boolean encontrado = false;
			while (!encontrado) {
				j++;
				encontrado = (String) datosFilaDepartamento[j][0]==nombreDep;	
			}
			tDepartamentos.setRowSelectionInterval(j, j);
			
			int k =-1;
			encontrado = false;
			while (!encontrado) {
				k++;
				encontrado = (String) datosFilaActividades[k][0]==nombreAct;	
			}
			tActividades.setRowSelectionInterval(k, k);
			
			int l =-1;
			encontrado = false;
			while (!encontrado) {
				l++;
				encontrado = (String) datosFilaSalidas[l][0]==n;	
			}
			tSalidas.setRowSelectionInterval(l, l);
		}
	}


}
