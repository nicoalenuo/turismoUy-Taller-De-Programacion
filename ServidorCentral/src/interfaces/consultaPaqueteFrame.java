package interfaces;

import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import controladores.*;
import datatypes.*;

public class consultaPaqueteFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private String [] nomColumnasPaquetes= {"Nombre","Descripcion","Descuento"};
	private Object[][] datosFilaPaquetes;

	private String [] nomColumnasActividades= {"Actividades dentro del paquete"};
	private Object[][] datosFilaActividades;
	
	private String [] nomColumnasCategorias = {"Nombre"};
	private Object[][] datosFilaCategorias;
	
	JTable tPaquetes= null;
	JScrollPane scrollpane = null;
	
	JTable tCategorias;
	JScrollPane scrollpane2;

	public consultaPaqueteFrame(String n) {
		setTitle("Consulta de paquete");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(800,800));
		pack();
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		JPanel pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(6,2));
		
		int i=0;
		Map<String,DTPaquete> paquetes = fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetes();
		datosFilaPaquetes = new Object[paquetes.size()][3];
		Iterator<String> it = paquetes.keySet().iterator();
		String key;

		while (it.hasNext()) {
			key = it.next();
			datosFilaPaquetes[i][0] = paquetes.get(key).getNombre();
			datosFilaPaquetes[i][1] = paquetes.get(key).getDescripcion();
			datosFilaPaquetes[i][2] = paquetes.get(key).getDescuento();
			i++;
		}

		tPaquetes = new JTable(datosFilaPaquetes,nomColumnasPaquetes);
		scrollpane=new JScrollPane(tPaquetes);
		tPaquetes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scrollpane);
		scrollpane.setPreferredSize(new Dimension(150,350));
		
		datosFilaActividades = new Object[0][1];
		JTable tActividades = new JTable(datosFilaActividades,nomColumnasActividades);
		JScrollPane sp = new JScrollPane(tActividades);
		revalidate();
		
		add(pCentro);
		
		JPanel pNombreIzq = new JPanel();
		JPanel pDescripcionIzq = new JPanel();
		JPanel pPeriodoValidezIzq = new JPanel();
		JPanel pDescuentoIzq = new JPanel();
		JPanel pFechaAltaIzq = new JPanel();
		JPanel pCategoriaIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pDescripcionDer = new JPanel();
		JPanel pPeriodoValidezDer = new JPanel();
		JPanel pDescuentoDer = new JPanel();
		JPanel pFechaAltaDer = new JPanel();
	
		pCentro.add(pNombreIzq);
		pCentro.add(pNombreDer);
		pCentro.add(pDescripcionIzq);
		pCentro.add(pDescripcionDer);
		pCentro.add(pPeriodoValidezIzq);
		pCentro.add(pPeriodoValidezDer);
		pCentro.add(pDescuentoIzq);
		pCentro.add(pDescuentoDer);
		pCentro.add(pFechaAltaIzq);
		pCentro.add(pFechaAltaDer);
		pCentro.add(pCategoriaIzq);
		
		JLabel lNombre = new JLabel("Nombre:");
		JLabel lNombreInfo = new JLabel("");
		JLabel lDescripcion = new JLabel("Descripcion:");
		JTextArea taDescripcionInfo = new JTextArea(2,22);
		taDescripcionInfo.setVisible(false);
		taDescripcionInfo.setLineWrap(true);
		taDescripcionInfo.setWrapStyleWord(true);
		taDescripcionInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1))); 
	    taDescripcionInfo.setEnabled(false);
	    taDescripcionInfo.setDisabledTextColor(Color.black);
		JLabel lPeriodoValidez = new JLabel("Periodo de validez:");
		JLabel lPeriodoValidezInfo = new JLabel("");
		JLabel lDescuento = new JLabel("Descuento:");
		JLabel lDescuentoInfo = new JLabel("");
		JLabel lFechaAlta = new JLabel("Fecha de alta:");
		JLabel lFechaAltaInfo = new JLabel("");

		pNombreIzq.add(lNombre);
		pNombreDer.add(lNombreInfo);
		pDescripcionIzq.add(lDescripcion);
		pDescripcionDer.add(taDescripcionInfo);
		pPeriodoValidezIzq.add(lPeriodoValidez);
		pPeriodoValidezDer.add(lPeriodoValidezInfo);
		pDescuentoIzq.add(lDescuento);
		pDescuentoDer.add(lDescuentoInfo);
		pFechaAltaIzq.add(lFechaAlta);
		pFechaAltaDer.add(lFechaAltaInfo);
		pCategoriaIzq.add(new JLabel("Categorias del paquete:"));
		
		add(sp);
		
		datosFilaCategorias = new Object[0][1];
		
		JTable tCategorias = new JTable(datosFilaCategorias,nomColumnasCategorias);
		scrollpane2 = new JScrollPane(tCategorias);
		pCentro.add(scrollpane2);
		
		JButton btnAceptar = new JButton("Consultar actividad");
		btnAceptar.setEnabled(false);
		
		JPanel pBtn = new JPanel();
		pBtn.setLayout(new FlowLayout());
		pBtn.add(btnAceptar);
		add(pBtn);
		

		tPaquetes.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DTPaquete seleccionado = paquetes.get(datosFilaPaquetes[tPaquetes.getSelectedRow()][0]);
	        	
	        	lNombreInfo.setText(seleccionado.getNombre());
	        	taDescripcionInfo.setVisible(true);
	        	taDescripcionInfo.setText(seleccionado.getDescripcion());
	        	lPeriodoValidezInfo.setText(Integer.toString(seleccionado.getPeriodoValidez()));
	        	lDescuentoInfo.setText(Float.toString(seleccionado.getDescuento()));
	        	lFechaAltaInfo.setText(Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(seleccionado.getFechaAlta().get(GregorianCalendar.YEAR)));
	        	
	        	Set<String> actividades = fabrica.getInstance().getIControladorPaquete().listarActividadesPaquete((String) datosFilaPaquetes[tPaquetes.getSelectedRow()][0]);
	        	Set<String> categorias= new HashSet<String>();
	        	datosFilaActividades = new Object[actividades.size()][1];
	        	Iterator<String> it = actividades.iterator();
	        	String key;
	        	int i=0;
	        	while (it.hasNext()) {
	        		key = it.next();
	        		datosFilaActividades[i][0] = key;
	        		categorias.addAll(fabrica.getInstance().getIControladorTurismo().obtenerCategoriasDeActividad(key));
	        		i++;
	        	}
	        	i=0;
	        	datosFilaCategorias= new Object[categorias.size()][1];
	    		Iterator<String> it2 = categorias.iterator();
	    		String key2;
	    		while (it2.hasNext()) {
	    			key2 = it2.next();
	    			datosFilaCategorias[i][0] = key2;
	    			i++;
	    		}
	    		
	    		tCategorias.setModel(new DefaultTableModel(datosFilaCategorias,nomColumnasCategorias));
	        	
	        	DefaultTableModel dm = new DefaultTableModel(datosFilaActividades,nomColumnasActividades);
			    tActividades.setModel(dm);
	        	
	        }
	    });
		
		tActividades.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) { 	
	        	btnAceptar.setEnabled(true);
	        }
	    });
		
		btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.centro.add(new consultaActividadFrame((String) datosFilaActividades[tActividades.getSelectedRow()][0]));
            }
        }); 
		
		if (n!=null) {
			int l =-1;
			boolean encontrado = false;
			while (!encontrado) {
				l++;
				encontrado = (String) datosFilaPaquetes[l][0]==n;	
			}
			tPaquetes.setRowSelectionInterval(l,l);
		}
		
	}
}
