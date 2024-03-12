package interfaces;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controladores.*;
import datatypes.*;


public class consultaUsuarioFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private String [] nomColumnas = {"Tipo","Nickname","Email"};
	private Object[][] datosFila;
	
	private String[] nomColumnasActividades = {""};
	private Object[][] datosFilaActividades;
	
	private String [] nomColumnasSalidas = {""};
	private Object[][] datosFilaSalidas;
	
	JTable tUsuarios = null;
	JScrollPane sp = null;
	
	JTable tActividades = null;
	JScrollPane spAct = null;
	
	JTable tSalidas = null;
	JScrollPane spSal = null;
	

	Map<String,DTActividad> actividadesProveedor = null;
	
	public consultaUsuarioFrame(){
		
		fabrica f = fabrica.getInstance();
		
		setTitle("Consultar usuario");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setVisible(true);
		setIconifiable(true);
		setResizable(true);
		setPreferredSize(new Dimension(650,800));
		pack();
		
		getContentPane().setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		JPanel btnActividad = new JPanel();
		JPanel btnSalida = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		getContentPane().add(p1,BorderLayout.NORTH);
		getContentPane().add(p2, BorderLayout.CENTER);
		getContentPane().add(p3, BorderLayout.SOUTH);
		
		JButton consultaActividad = new JButton("Consultar actividad");
		JButton consultaSalida = new JButton("Consultar salida");
		consultaActividad.setEnabled(false);
		consultaSalida.setEnabled(false);
		
		int i=0;
		Map<String,DTUsuario> users = f.getIControladorUsuarios().obtenerUsuariosEmail();
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
			}else {
				DTTurista t = DTTurista.class.cast(users.get(key));
				datosFila[i][0] = "Turista";
				datosFila[i][1] = t.getNickname();
				datosFila[i][2] = t.getEmail();
			}
			i++;
		}
		tUsuarios = new JTable(datosFila,nomColumnas);
		tUsuarios.getColumnModel().getColumn(2).setPreferredWidth(200);
		sp = new JScrollPane(tUsuarios);
		tUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(sp);
		sp.setPreferredSize(new Dimension(150,150));
		
		datosFilaActividades = new Object[0][1];
		tActividades = new JTable(datosFilaActividades,nomColumnasActividades);
		spAct=new JScrollPane(tActividades);
		tActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		datosFilaSalidas = new Object[0][1];
		tSalidas = new JTable(datosFilaSalidas,nomColumnasSalidas);
		spSal=new JScrollPane(tSalidas);
		tSalidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel pNombreIzq = new JPanel();
		JPanel pApellidoIzq = new JPanel();
		JPanel pNicknameIzq = new JPanel();
		JPanel pEmailIzq = new JPanel();
		JPanel pSeleccionIzq = new JPanel();
		JPanel pNacionalidadWebIzq = new JPanel();
		JPanel pDescIzq = new JPanel();
		JPanel pFechaIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pApellidoDer = new JPanel();
		JPanel pNicknameDer = new JPanel();
		JPanel pEmailDer = new JPanel();
		JPanel pSeleccionDer = new JPanel();
		JPanel pNacionalidadWebDer = new JPanel();
		JPanel pDescDer = new JPanel();
		JPanel pFechaDer = new JPanel();
		
		JLabel lTipo = new JLabel();
		
		lTipo.setVisible(false);
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		fl.setHgap(10);
		
		p3.setLayout(new GridLayout(1,2));
		
		pNombreIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pApellidoIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNicknameIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pEmailIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pSeleccionIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNacionalidadWebIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pDescIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		pFechaIzq.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		pNombreDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pApellidoDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNicknameDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pEmailDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pSeleccionDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pNacionalidadWebDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pDescDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		pFechaDer.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnActividad.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnSalida.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setLayout(new GridLayout(10,2));
		
		JLabel lNombre = new JLabel();
		pNombreIzq.add(new JLabel("Nombre:  "));
		pNombreDer.add(lNombre);
		
		JLabel lApellido = new JLabel();
		pApellidoIzq.add(new JLabel("Apellido: "));
		pApellidoDer.add(lApellido);
		
		JLabel lNickname = new JLabel();
		pNicknameIzq.add(new JLabel("Nickname:"));
		pNicknameDer.add(lNickname);
		
		JLabel lEmail = new JLabel();
		pEmailIzq.add(new JLabel("Email:   "));
		pEmailDer.add(lEmail);
		
		JLabel lNacionalidad = new JLabel();
		JLabel lbl5 = new JLabel("Nacionalidad:");
		pNacionalidadWebIzq.add(lbl5);
		pNacionalidadWebDer.add(lNacionalidad);
		
		JLabel lbl6 = new JLabel("Sitio web: ");
		JLabel lSitioWeb = new JLabel();
		pNacionalidadWebIzq.add(lbl6);
		pNacionalidadWebDer.add(lSitioWeb);
		
		JLabel lbl7 = new JLabel("Descripción: ");
		JTextArea taDescripcion = new JTextArea(2,22);
		taDescripcion.setLineWrap(true);
		taDescripcion.setWrapStyleWord(true);
		taDescripcion.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1))); 
	    taDescripcion.setEnabled(false);
	    taDescripcion.setDisabledTextColor(Color.black);
		pDescIzq.add(lbl7);
		pDescDer.add(taDescripcion);
		
		lNacionalidad.setVisible(false);
		lSitioWeb.setVisible(false);
		taDescripcion.setVisible(false);
		lbl5.setVisible(false);
		lbl6.setVisible(false);
		lbl7.setVisible(false);
		
	    pSeleccionIzq.add(new JLabel("Tipo:"));
		pSeleccionDer.add(lTipo);
		
		pFechaIzq.add(new JLabel("Fecha de nacimiento:"));
		
		JLabel lFecha = new JLabel();
        pFechaDer.add(lFecha);    
		
		p2.add(pNombreIzq);
		p2.add(pNombreDer);
		p2.add(pApellidoIzq);
		p2.add(pApellidoDer);
		p2.add(pNicknameIzq);
		p2.add(pNicknameDer);
		p2.add(pEmailIzq);
		p2.add(pEmailDer);
		p2.add(pSeleccionIzq);
		p2.add(pSeleccionDer);
		p2.add(pNacionalidadWebIzq);
		p2.add(pNacionalidadWebDer);
		p2.add(pDescIzq);
		p2.add(pDescDer);
		p2.add(pFechaIzq);
		p2.add(pFechaDer);
		p2.add(spAct);
		p2.add(spSal);
		
		
		tUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	lTipo.setVisible(true);
	        	DTUsuario seleccionado = users.get((String) datosFila[tUsuarios.getSelectedRow()][2]);	
	        	lNacionalidad.setText("");
	        	lSitioWeb.setText("");
	        	taDescripcion.setText("");
	        	
	        	lNombre.setText(seleccionado.getNombre());
	        	lApellido.setText(seleccionado.getApellido());
	        	lNickname.setText(seleccionado.getNickname());
	        	lEmail.setText(seleccionado.getEmail());
	        	
	        	lFecha.setText(Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(seleccionado.getFechaNac().get(GregorianCalendar.YEAR)));

	        	if (DTProveedor.class.isInstance(seleccionado)) {
	        		lTipo.setText("Proveedor");
	        		DTProveedor p = DTProveedor.class.cast(seleccionado);
	        		
	        		lNacionalidad.setVisible(false);
					lbl5.setVisible(false);
					
					lSitioWeb.setVisible(true);
					lbl6.setVisible(true);
					
					taDescripcion.setVisible(true);
					lbl7.setVisible(true);
					
					lSitioWeb.setText(p.getSitioWeb());
					taDescripcion.setText(p.getDescripcion());
				}else {
	        		DTTurista t = DTTurista.class.cast(seleccionado);
	        		lTipo.setText("Turista");
					lNacionalidad.setVisible(true);
					lbl5.setVisible(true);
					
					lSitioWeb.setVisible(false);
					lbl6.setVisible(false);
					
					taDescripcion.setVisible(false);
					lbl7.setVisible(false);
					
					lNacionalidad.setText(t.getNacionalidad());
				}
	        	
	        	consultaActividad.setEnabled(false);
	        	consultaSalida.setEnabled(false);
	        	

	        	tActividades.clearSelection();
	        	tSalidas.clearSelection();
	        	
	        	datosFilaActividades = new Object[0][1];
	        	datosFilaSalidas = new Object[0][1];
	        	
	        	nomColumnasActividades = new String[1];
				nomColumnasActividades[0] = "";
				
				nomColumnasSalidas = new String[1];
				nomColumnasSalidas[0] = "";
	        	
	        	tActividades.setModel(new DefaultTableModel(datosFilaActividades,nomColumnasActividades));
	        	tSalidas.setModel(new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas));
	        	
	        	consultaActividad.setEnabled(false);
	        	consultaSalida.setEnabled(false);
	        	
	        	
	        	if(DTProveedor.class.isInstance((seleccionado))){
	        		actividadesProveedor = fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeProveedor(seleccionado.getNickname());
		        	int i = 0; 
		        	DTActividad act;
					datosFilaActividades = new Object[actividadesProveedor.size()][1];
					Iterator<String> it = actividadesProveedor.keySet().iterator();
					String key;
					while (it.hasNext()) {
						key = it.next();
						act = actividadesProveedor.get(key);
						datosFilaActividades[i][0] = act.getNombre();
						i++;
					}
		        	
					nomColumnasActividades = new String[1];
					nomColumnasActividades[0] = "Nombre de las actividades que provee";
					
					
		        	DefaultTableModel dm = new DefaultTableModel(datosFilaActividades,nomColumnasActividades);
				    tActividades.setModel(dm);
				    
				    tActividades.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				        public void valueChanged(ListSelectionEvent event) {
					    	if (tActividades.getSelectedRow()!=-1) {
					        	consultaActividad.setEnabled(true);
					        	consultaSalida.setEnabled(false);
					        	Map<String,DTSalida> salidasDeAct = fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividad(actividadesProveedor.get((String) datosFilaActividades[tActividades.getSelectedRow()][0]).getNombre());
					        	int j=0;
								DTSalida sal;
								datosFilaSalidas = new Object[salidasDeAct.size()][1];
								Iterator<String> it = salidasDeAct.keySet().iterator();
								String key;
								while (it.hasNext()) {
									key = it.next();
									sal = salidasDeAct.get(key);
									datosFilaSalidas[j][0] = sal.getNombre();
									j++;
								}
								nomColumnasSalidas = new String[1];
								nomColumnasSalidas[0] = "Nombre de las salidas para la actividad seleccionada";
					        	DefaultTableModel dm = new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas);
							    tSalidas.setModel(dm);
							    
								consultaActividad.setEnabled(true);
					        	consultaSalida.setEnabled(false);
					    	}
						    
						    tSalidas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
						        public void valueChanged(ListSelectionEvent event) {
						        	if (tSalidas.getSelectedRow()!=-1) {
						        		consultaSalida.setEnabled(true);
						        	}
						    	}
						    });
				    	}
				    });
	        	}else {
	        		 Set<DTInscripcion> salidasInsc = fabrica.getInstance().getIControladorTurismo().obtenerSalidasInscripto(seleccionado.getNickname());
	        		
	        		int i=0;
	    			datosFilaSalidas = new Object[salidasInsc.size()][4];
	    			Iterator<DTInscripcion> it = salidasInsc.iterator();
	    			DTInscripcion key;
	    			while (it.hasNext()) {
	    				key = it.next();
	    				datosFilaSalidas[i][0] = key.getNombreSalida();
	    				datosFilaSalidas[i][1] = key.getCantInscriptos();
	    				datosFilaSalidas[i][2] = key.getCosto();
	    				datosFilaSalidas[i][3] = Integer.toString(key.getFechaInscripcion().get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(key.getFechaInscripcion().get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(key.getFechaInscripcion().get(GregorianCalendar.YEAR));
	    				i++;
	    			}
	    			
	    			
	    			nomColumnasSalidas = new String[4];
					nomColumnasSalidas[0] = "Salidas inscipt@";
					nomColumnasSalidas[1] = "Turistas extra";
					nomColumnasSalidas[2] = "Costo";
					nomColumnasSalidas[3] = "Fecha inscripcion";
					
	    			DefaultTableModel dm = new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas);
				    tSalidas.setModel(dm);
				    
				    tSalidas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				        public void valueChanged(ListSelectionEvent event) {
				        	if (tSalidas.getSelectedRow()!=-1) {
				        		consultaSalida.setEnabled(true);
				        	}
				    	}
				    });
	        	}
	        }
	    });
		
		consultaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.centro.add(new consultaActividadFrame((String) datosFilaActividades[tActividades.getSelectedRow()][0]));
			}
		});
		
		consultaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.centro.add(new consultaSalidaFrame((String) datosFilaSalidas[tSalidas.getSelectedRow()][0]));
			}
		});
		
		btnActividad.add(consultaActividad);
		btnSalida.add(consultaSalida);
		p3.add(btnActividad);
		p3.add(btnSalida);
		

		
		revalidate();

	}
}