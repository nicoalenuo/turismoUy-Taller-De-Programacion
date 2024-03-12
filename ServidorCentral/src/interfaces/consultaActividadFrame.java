package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.fabrica;
import datatypes.DTActividad;
import datatypes.DTDepartamento;

public class consultaActividadFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String[] datosActs;
	Map<String,DTActividad> acts;
	
	private String[] nomColumnasPaquetes = {"Nombre de los Paquetes que lo proveen"};
	private Object[][] datosFilaPaquetes;
	
	private String [] nomColumnasSalidas = {"Nombre de las salidas para la actividad seleccionada"};
	private Object[][] datosFilaSalidas;
	
	private String [] nomColumnasCategorias = {"Nombre"};
	private Object[][] datosFilaCategorias;
	
	JButton consultaPaquete = new JButton("Consultar paquete");
	JButton consultaSalida = new JButton("Consultar salida");
	JScrollPane sp;
	JScrollPane sp2;
	JScrollPane sp3;
	JTable tSalidas;
	JTable tPaquetes;
	JTable tCategorias;
	
	public consultaActividadFrame(String nombreAct) {
		setTitle("Consulta de actividad");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setVisible(true);
		setPreferredSize(new Dimension(700,700));
		pack();
		
		fabrica f = fabrica.getInstance();
	
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		JPanel p21 = new JPanel();
		p21.setLayout(new GridLayout(7,2));
		JPanel p22 = new JPanel();
		p22.setLayout(new GridLayout(1,2));
		p2.add(p21);
		p2.add(p22);
		p2.setVisible(false);
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(1,2));
		p3.add(consultaSalida);
		p3.add(consultaPaquete);
		p3.setVisible(false);
		p1.setLayout(new GridLayout(1,2));
		JPanel pDep = new JPanel();
		JPanel pAct = new JPanel();
		
		pDep.setLayout(new BoxLayout(pDep,BoxLayout.Y_AXIS));
		JLabel ldep = new JLabel("Nombre de los departamentos");
		pDep.add(ldep);
		
		pAct.setLayout(new BoxLayout(pAct,BoxLayout.Y_AXIS));
		JLabel lAct = new JLabel("Nombre de las actividades que provee");
		pAct.add(lAct);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
				
		Map<String,DTDepartamento> dep = f.getIControladorTurismo().obtenerDepartamentos();
		
		JComboBox<String> datosFila = new JComboBox<String>();
		Iterator<String> it = dep.keySet().iterator();
		String key;
		datosFila.addItem("Seleccione");
		while (it.hasNext()) {
			key = it.next();
			datosFila.addItem(key);
		}
		pDep.add(datosFila);
		
		p1.add(pDep);
		p1.add(pAct);
		
		JComboBox<String> datosFila2 = new JComboBox<String>();
		pAct.add(datosFila2);
		datosFila2.addItem("Seleccione");

		datosFilaSalidas = new Object[0][1];
		
		JTable tSalidas = new JTable(datosFilaSalidas,nomColumnasSalidas);
		sp = new JScrollPane(tSalidas);
		tSalidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		datosFilaPaquetes = new Object[0][1];
		
		JTable tPaquetes = new JTable(datosFilaPaquetes,nomColumnasPaquetes);
		sp2 = new JScrollPane(tPaquetes);
		tPaquetes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		datosFilaCategorias = new Object[0][1];
		
		JTable tCategorias = new JTable(datosFilaCategorias,nomColumnasCategorias);
		sp3 = new JScrollPane(tCategorias);
		
		p22.add(sp);
		p22.add(sp2);
		
		JPanel pNombreIzq = new JPanel();
		JPanel pDescripcionIzq = new JPanel();
		JPanel pDuracionIzq = new JPanel();
		JPanel pCostoTuristaIzq = new JPanel();
		JPanel pCiudadIzq = new JPanel();
		JPanel pFechaIzq = new JPanel();
		JPanel pCategoriaIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pDescripcionDer = new JPanel();
		JPanel pDuracionDer = new JPanel();
		JPanel pCostoTuristaDer = new JPanel();
		JPanel pCiudadDer = new JPanel();
		JPanel pFechaDer = new JPanel();
		
		p21.add(pNombreIzq);
		p21.add(pNombreDer);
		p21.add(pDescripcionIzq);
		p21.add(pDescripcionDer);
		p21.add(pDuracionIzq);
		p21.add(pDuracionDer);
		p21.add(pCostoTuristaIzq);
		p21.add(pCostoTuristaDer);
		p21.add(pCiudadIzq);
		p21.add(pCiudadDer);
		p21.add(pFechaIzq);
		p21.add(pFechaDer);
		p21.add(pCategoriaIzq);
		p21.add(sp3);
		
		JLabel lNombre = new JLabel();
		pNombreIzq.add(new JLabel("Nombre:  "));
		pNombreDer.add(lNombre);
		
		pDescripcionIzq.add(new JLabel("Descripcion: "));
		JTextArea lDescripcion = new JTextArea(2,22);
		lDescripcion.setLineWrap(true);
		lDescripcion.setWrapStyleWord(true);
		lDescripcion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1))); 
	    lDescripcion.setEnabled(false);
	    lDescripcion.setDisabledTextColor(Color.black);
		pDescripcionDer.add(lDescripcion);
		
		JLabel lDuracion = new JLabel();
		pDuracionIzq.add(new JLabel("Duracion:"));
		pDuracionDer.add(lDuracion);
		
		JLabel lCostoTurista = new JLabel();
		pCostoTuristaIzq.add(new JLabel("Costo/Turista:"));
		pCostoTuristaDer.add(lCostoTurista);
		
		JLabel lCiudad = new JLabel();
		pCiudadIzq.add(new JLabel("Ciudad:  "));
		pCiudadDer.add(lCiudad);
		
		JLabel lFecha = new JLabel();
		pFechaIzq.add(new JLabel("Fecha de Alta:"));
		pFechaDer.add(lFecha);
		
		pCategoriaIzq.add(new JLabel("Categorias de la actividad:"));
		
		revalidate();
		
		datosFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p2.setVisible(false);
				p3.setVisible(false);
				//Actividad
				if((String) datosFila.getSelectedItem()!="Seleccione") {
		        	acts = fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeDepartamento((String) datosFila.getSelectedItem());
		        	datosActs = new String[acts.size()+1];
		    		Iterator<String> it2 = acts.keySet().iterator();
		    		datosActs[0]= "Seleccione";
		    		String key2;
		    		int i=1;
		    		while (it2.hasNext()) {
		    			key2 = it2.next();
		    			datosActs[i]= key2;
		    			i++;
		    		}
		    		DefaultComboBoxModel<String> cb= new DefaultComboBoxModel<String>(datosActs);
		        	datosFila2.setModel(cb);
				}else{
					datosActs= new String[1];
					datosActs[0]= "Seleccione";
					DefaultComboBoxModel<String> cb= new DefaultComboBoxModel<String>(datosActs);
		        	datosFila2.setModel(cb);
				}
			}
	        	
	    });
		
		datosFila2.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event) {
	        	if((String) datosFila2.getSelectedItem()!="Seleccione") {
		        	p2.setVisible(true);
		        	p3.setVisible(true);
		        	DTActividad seleccionado= f.getIControladorTurismo().darDatosActividad((String) datosFila2.getSelectedItem());
		        	consultaPaquete.setEnabled(false);
		    		consultaSalida.setEnabled(false);
		    				
		    		Set<String> categoriasDeAct = f.getIControladorTurismo().obtenerCategoriasDeActividad(seleccionado.getNombre());
		    		Set<String> salidasDeAct = f.getIControladorTurismo().listarSalidas(seleccionado.getNombre());
		    		Set<String> paquetesDeAct = f.getIControladorPaquete().obtenerPaquetesDeActividad(seleccionado.getNombre());
		    		
		    		int i=0;
		    		datosFilaCategorias= new Object[categoriasDeAct.size()][1];
		    		Iterator<String> it4 = categoriasDeAct.iterator();
		    		String key4;
		    		while (it4.hasNext()) {
		    			key4 = it4.next();
		    			datosFilaCategorias[i][0] = key4;
		    			i++;
		    		}
		    		
		    		tCategorias.setModel(new DefaultTableModel(datosFilaCategorias,nomColumnasCategorias));
		    		
		    		i=0;
		    		datosFilaSalidas= new Object[salidasDeAct.size()][1];
		    		Iterator<String> it2 = salidasDeAct.iterator();
		    		String key2;
		    		while (it2.hasNext()) {
		    			key2 = it2.next();
		    			datosFilaSalidas[i][0] = key2;
		    			i++;
		    		}
		    		
		    		tSalidas.setModel(new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas));
		    		
		    		i = 0;
		    		datosFilaPaquetes = new Object[paquetesDeAct.size()][1];
		    		Iterator<String> it3 = paquetesDeAct.iterator();
		    		String key3;
		    		while (it3.hasNext()) {
		    			key3 = it3.next();
		    			datosFilaPaquetes[i][0] = key3;
		    			i++;
		    		}
		    		
		    		tPaquetes.setModel(new DefaultTableModel(datosFilaPaquetes,nomColumnasPaquetes));
		    		
		    		
		    		lNombre.setText(seleccionado.getNombre());
		    		lDescripcion.setText(seleccionado.getDescripcion());
		    		lDuracion.setText(Float.toString(seleccionado.getDuracion())+" días");
		    		lCostoTurista.setText(Float.toString(seleccionado.getCostoTurista()));
		    		lCiudad.setText(seleccionado.getCiudad());
		    	 	lFecha.setText(Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.DAY_OF_MONTH)) + "/" + Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.MONTH)+1) + "/" + Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.YEAR)));
		    		
		    		revalidate();
		    		
		    		tSalidas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		                public void valueChanged(ListSelectionEvent event) {
		                	consultaSalida.setEnabled(true);
		                }
		                
		            });
		            
		            tPaquetes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		                public void valueChanged(ListSelectionEvent event) {
		                    consultaPaquete.setEnabled(true);
		                }
		            });
		            
		            consultaSalida.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	mainFrame.centro.add(new consultaSalidaFrame((String) datosFilaSalidas[tSalidas.getSelectedRow()][0]));
		                }
		            }); 
		    			
		            consultaPaquete.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		    				mainFrame.centro.add(new consultaPaqueteFrame((String) datosFilaPaquetes[tPaquetes.getSelectedRow()][0]));
		                }
		            });
	        	}else{
					p2.setVisible(false);
		        	p3.setVisible(false);
				}
	        }
	    });
		
		if(nombreAct!=null) {
			String nombreDepartamento= fabrica.getInstance().getIControladorTurismo().obtenerDepartamentoConActividad(nombreAct);
			datosFila.setSelectedItem(nombreDepartamento);
			datosFila2.setSelectedItem(nombreAct);
		}
	
	}
}
