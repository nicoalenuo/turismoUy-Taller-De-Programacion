package interfaces;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import controladores.fabrica;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTDepartamento;
import datatypes.DTProveedor;
import datatypes.DTUsuario;
import datatypes.EstadoAct;
import helpers.ActividadRepetidaException;
import helpers.CampoIncompletoException;
import helpers.CategoriaInvalidaException;
import helpers.CostoEsCeroException;
import helpers.DepartamentoInvalidoException;
import helpers.DuracionEsCeroException;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class altaActividadFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String [] nomColumnas = {"Nickname","Email"};
	private String [] nomColumnas3 = {"Nombre"};
	
	private Object[][] datosFila;
	private Object[][] datosFila3;

	
	JTable tProveedores = null;
	JTable tCategorias = null;
	
	JScrollPane sp = null;
	JScrollPane sp2 = null;

	
	public altaActividadFrame() {
		setTitle("Alta de actividad turistica");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setVisible(true);
		setPreferredSize(new Dimension(700,1000));
		pack();
		
		fabrica f = fabrica.getInstance();
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(700,700));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.setLayout(new GridLayout(1,2));
		JPanel pProv = new JPanel();
		JPanel pDep = new JPanel();
		
		pProv.setLayout(new BoxLayout(pProv,BoxLayout.Y_AXIS));
		JLabel lprov = new JLabel("Proveedores");
		lprov.setAlignmentY(CENTER_ALIGNMENT);
		pProv.add(lprov);
		pDep.setLayout(new BoxLayout(pDep,BoxLayout.Y_AXIS));
		JLabel ldep = new JLabel("Nombre de departamentos");
		ldep.setAlignmentY(CENTER_ALIGNMENT);
		pDep.add(ldep);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		add(p3, BorderLayout.SOUTH);
		
		//Proveedores
		int i = 0;
		Map<String,DTUsuario> users = f.getIControladorUsuarios().obtenerProveedores();
		datosFila = new Object[users.size()][2];
		Iterator<String> it = users.keySet().iterator();
		String key;
		
		while (it.hasNext()) {
			key = it.next();
			
			if (DTProveedor.class.isInstance(users.get(key))) {
				DTProveedor p = DTProveedor.class.cast(users.get(key));
				datosFila[i][0] = p.getNickname();
				datosFila[i][1] = p.getEmail();
			}
			i++;
		}
		
		tProveedores = new JTable(datosFila,nomColumnas);
		tProveedores.getColumnModel().getColumn(1).setPreferredWidth(200);
		sp = new JScrollPane(tProveedores);
		Dimension d = new Dimension(120,120);
		sp.setPreferredSize(d);
		tProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pProv.add(sp);
		
		
		//Departamentos
		Map<String,DTDepartamento> dep = f.getIControladorTurismo().obtenerDepartamentos();
		
		JComboBox<String> datosFila2 = new JComboBox<String>();
		Iterator<String> it2 = dep.keySet().iterator();
		String key2;
		datosFila2.addItem("Seleccione");
		
		while (it2.hasNext()) {
			key2 = it2.next();
			datosFila2.addItem(key2);
		}
		pDep.add(datosFila2);
		
		//Otros paneles
		JPanel pNombreIzq = new JPanel();
		JPanel pDescIzq = new JPanel();
		JPanel pDuracionIzq = new JPanel();
		JPanel pCostoIzq = new JPanel();
		JPanel pCiudadIzq = new JPanel();
		JPanel pAceptarIzq = new JPanel();
		JPanel pFechaIzq = new JPanel();
		JPanel pCategoriaIzq = new JPanel();
		
		JPanel pNombreDer = new JPanel();
		JPanel pDescDer = new JPanel();
		JPanel pDuracionDer = new JPanel();
		JPanel pCostoDer = new JPanel();
		JPanel pCiudadDer = new JPanel();
		JPanel pCancelarDer = new JPanel();
		JPanel pFechaDer = new JPanel();
		JPanel pCategoriaDer = new JPanel();
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
		fl.setHgap(10);
		
		pNombreIzq.setLayout(fl);
		pDescIzq.setLayout(fl);
		pDuracionIzq.setLayout(fl);
		pCostoIzq.setLayout(fl);
		pCiudadIzq.setLayout(fl);
		pFechaIzq.setLayout(fl);
		pCategoriaIzq.setLayout(fl);
		
		pNombreDer.setLayout(fl);
		pDescDer.setLayout(fl);
		pDuracionDer.setLayout(fl);
		pCostoDer.setLayout(fl);
		pCiudadDer.setLayout(fl);
		pFechaDer.setLayout(fl);
		pCategoriaDer.setLayout(fl);
		
		p2.setLayout(new GridLayout(7,2));
		
		JTextArea tf1 = new JTextArea(1,15);
		pNombreIzq.add(new JLabel("Nombre:"));
		pNombreDer.add(tf1);
		
		JTextArea tf2 = new JTextArea(2,22);
		pDescIzq.add(new JLabel("Descripcion:"));
		tf2.setLineWrap(true);
		tf2.setWrapStyleWord(true);
		pDescDer.add(tf2);
		
		JTextArea tf3 = new JTextArea(1,3);
		pDuracionIzq.add(new JLabel("Duracion:"));
		pDuracionDer.add(tf3);
		pDuracionDer.add(new JLabel("dias"));
		
		JTextArea tf4 = new JTextArea(1,3);
		pCostoIzq.add(new JLabel("Costo:"));
		pCostoDer.add(new JLabel("$"));
		pCostoDer.add(tf4);
		
		JTextArea tf5 = new JTextArea(1,15);
		pCiudadIzq.add(new JLabel("Ciudad:"));
		pCiudadDer.add(tf5);
		   
		pFechaIzq.add(new JLabel("Fecha de Alta:"));
		
		JDateChooser taFecha = new JDateChooser();
		pFechaDer.add(taFecha);
		taFecha.setPreferredSize(new Dimension(150,20));
        
        
		tf3.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE) {
		            e.consume();
		        }
		     }
		});
		
		tf4.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if ((c < '0' || c > '9') && c != KeyEvent.VK_BACK_SPACE) {
		            e.consume();
		        }
		     }
		});
        

		tf1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		tf2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		tf3.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		tf4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		tf5.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		
		pAceptarIzq.add(aceptar);
		pCancelarDer.add(cancelar);
		
		p1.add(pProv);
		p1.add(pDep);
		
		p2.add(pNombreIzq);
		p2.add(pNombreDer);
		p2.add(pDescIzq);
		p2.add(pDescDer);
		p2.add(pDuracionIzq);
		p2.add(pDuracionDer);
		p2.add(pCostoIzq);
		p2.add(pCostoDer);
		p2.add(pCiudadIzq);
		p2.add(pCiudadDer);
		p2.add(pFechaIzq);
		p2.add(pFechaDer);

		pCategoriaIzq.add(new JLabel("Categorias: "));
		
		//Categorias
		i=0;
		Map<String,DTCategoria> cats = f.getIControladorTurismo().obtenerCategorias();
		datosFila3 = new Object[cats.size()][1];
		Iterator<String> it3 = cats.keySet().iterator();
		String key3;
		
		while (it3.hasNext()) {
			key3 = it3.next();	
			DTCategoria dtC = cats.get(key3);
			datosFila3[i][0] = dtC.getNombre();
			i++;
		}
		
		tCategorias = new JTable(datosFila3,nomColumnas3);
		sp2 = new JScrollPane(tCategorias);
		Dimension d2= new Dimension(120,100);
		sp2.setPreferredSize(d2);
		tCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pCategoriaDer.add(sp2);
		
		p3.setLayout(new GridLayout(1,2));
		
		p2.add(pCategoriaIzq);
		p2.add(pCategoriaDer);
		p3.add(pAceptarIzq);
		p3.add(pCancelarDer);
		
		//EVENTOS
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((String) datosFila2.getSelectedItem() == "Seleccione") { 
						throw new CampoIncompletoException("Falta seleccionar un departamento");
					}
					if (tf1.getText().isBlank() || tf2.getText().isBlank() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isBlank() || tProveedores.getSelectedRow() == -1) { 
						throw new CampoIncompletoException("Falta algun campo por completar");
					}
					if (taFecha.getDate() == null) { 
						throw new CampoIncompletoException("Falta algun campo por completar");
					}
					if(tCategorias.getSelectedRowCount() == 0) {
						throw new CampoIncompletoException("Falta seleccionar almenos una categoria");
					}
					
					GregorianCalendar cal = new GregorianCalendar();
	                cal.setTime(taFecha.getDate());
	                
					DTActividad dtA = new DTActividad(tf1.getText(), tf2.getText(), Float.parseFloat(tf3.getText()),Float.parseFloat(tf4.getText()), tf5.getText(), cal, EstadoAct.Agregada, null, 0, null);
					
					int[] aux = tCategorias.getSelectedRows();
					HashSet<String> cats = new HashSet<String>();
					for(int i=0; i<tCategorias.getSelectedRowCount(); i++) {
						cats.add((String) datosFila3[aux[i]][0]);
					}
					
					f.getIControladorTurismo().confirmarAltaActividad((String) datosFila[tProveedores.getSelectedRow()][1],(String) datosFila2.getSelectedItem(),dtA,cats);
					JOptionPane.showMessageDialog(altaActividadFrame.this,"Actividad creada");
				    altaActividadFrame.this.dispose();		
				}catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(altaActividadFrame.this,e2.getMessage());
				}catch(ActividadRepetidaException e3) {
					JOptionPane.showMessageDialog(altaActividadFrame.this,e3.getMessage());
				} catch (DepartamentoInvalidoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CategoriaInvalidaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DuracionEsCeroException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CostoEsCeroException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaActividadFrame.this.dispose();
			}
		});
		
	}
}