package interfaces;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import controladores.fabrica;
import datatypes.*;
import helpers.ActividadInvalidaException;
import helpers.CampoIncompletoException;
import helpers.CantidadTuristaException;
import helpers.DepartamentoInvalidoException;
import helpers.InscripcionExistenteException;
import helpers.PaqueteInvalidoException;
import helpers.SalidaInvalidaException;
import helpers.SalidaLlenaException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;

public class inscripcionSalidaFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private String [] nomColumnas = {"Nickname","Email"};
	private Object[][] datosFila;
	JTable tUsuarios = null;
	JScrollPane sp3 = null;


	
	Map<String,DTUsuario> turistas;
	private String [] nomColumnasDepartamento = {"Nombre Departamento"};
	private Object[][] datosFilaDepartamento;

	private String[] nomColumnasActividades = {"Nombre de las actividades en el departamento"};
	private Object[][] datosFilaActividades;

    private String [] nomColumnasSalidas = {"Nombre", "C.Max", "inscriptos", "F.Alta", "F.Salida", "Lugar de Salida"};
	private Object[][] datosFilaSalidas;

	
	JTable tDepartamentos = null;
	JTable tActividades = null;
	JTable tSalidas = null;
	JScrollPane spDepartamento = null;
	JScrollPane spActividades = null;
	JScrollPane spSalidas = null;

	public inscripcionSalidaFrame() {
		fabrica f = fabrica.getInstance();

		setTitle("Inscripcion a salidas");
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
		JPanel p3 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p2.setLayout(new GridLayout(1,2));

		JPanel pTur = new JPanel();
		pTur.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lTur= new JLabel("Seleccione Turista");
		pTur.add(lTur);
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
		
		datosFilaSalidas = new Object[0][6];
		tSalidas = new JTable(datosFilaSalidas,nomColumnasSalidas);
		spSalidas = new JScrollPane(tSalidas);
		spSalidas.setPreferredSize(new Dimension(20,20));
		tSalidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(spSalidas);

		//turistas
		int j=0;
		Map<String,DTUsuario> users = f.getIControladorUsuarios().obtenerUsuariosEmail();
		datosFila = new Object[users.size()][2];
		Iterator<String> it2 = users.keySet().iterator();
		String key2;
		
		while (it2.hasNext()) {
			key2 = it2.next();
			
			if (DTTurista.class.isInstance(users.get(key2))) {
				DTTurista t = DTTurista.class.cast(users.get(key2));
				datosFila[j][0] = t.getNickname();
				datosFila[j][1] = t.getEmail();
				j++;
			}
		}
		tUsuarios = new JTable(datosFila,nomColumnas);
		sp3 = new JScrollPane(tUsuarios);
		tUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		p1.add(sp3);
		sp3.setPreferredSize(new Dimension(150,150));
		add(p2,BorderLayout.SOUTH);
		

		JPanel pNombreLabel = new JPanel();
		JPanel pCantMaxLabel = new JPanel();
		JPanel pCantInsriptosLabel = new JPanel();
		JPanel pFechaSalidaLabel = new JPanel();
		JPanel pLugarSalidaLabel = new JPanel();

		JPanel pCantTuristasLabel = new JPanel();
        JPanel pCancelar = new JPanel();


        JPanel pCantTuristas = new JPanel();
		JPanel pIngresarSalida = new JPanel();
		
		JPanel pNombre = new JPanel();
		JPanel pCantMax = new JPanel();
		JPanel pCantInsriptos = new JPanel();
		JPanel pFechaSalida = new JPanel();
		JPanel pLugarSalida = new JPanel();

		/*p2.add(pNombreLabel);
		p2.add(pNombre);
		p2.add(pCantMaxLabel);
		p2.add(pCantMax);
		p2.add(pCantInsriptosLabel);
		p2.add(pCantInsriptos);
		p2.add(pFechaSalidaLabel);
		p2.add(pFechaSalida);
		p2.add(pLugarSalidaLabel);
		p2.add(pLugarSalida); */
		p3.add(pCancelar);
		p3.add(pIngresarSalida);

		JTextArea taCantTuristas = new JTextArea(1,15);
		pCantTuristasLabel.add(new JLabel("Cantidad de turistas: "));
		pCantTuristas.add(taCantTuristas);
		
		JButton ingresarSalida = new JButton("Inscribir a Salida");
		pIngresarSalida.add(ingresarSalida);
		JButton cancelar = new JButton("Cancelar");
		pCancelar.add(cancelar);


		add(pCantTuristasLabel);
		add(pCantTuristas);

		add(pCancelar);
		add(pIngresarSalida);
		
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
		taCantTuristas.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(1,1,1,1)));
		tDepartamentos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
				revalidate();
				
			}
		});

		
		tActividades.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (tActividades.getSelectedRow()!=-1) {
					
					String actSeleccionada = (String) datosFilaActividades[tActividades.getSelectedRow()][0];
					Map<String, DTSalida> salidasDeActividad = fabrica.getInstance().getIControladorTurismo().listarSalidasVigentes(actSeleccionada);
					int k = 0;
					DTSalida sal;
					datosFilaSalidas = new Object[salidasDeActividad.size()][6];
					Iterator<String> it = salidasDeActividad.keySet().iterator();
					String key;
					while(it.hasNext()) {
						key = it.next();
						sal = salidasDeActividad.get(key);
						datosFilaSalidas[k][0] = sal.getNombre();
						datosFilaSalidas[k][1] = sal.getCantMax();
						datosFilaSalidas[k][2] = sal.getCantInscriptos();
						datosFilaSalidas[k][3] = Integer.toString(sal.getFechaAlta().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(sal.getFechaAlta().get(Calendar.MONTH)+1)+"/"+Integer.toString(sal.getFechaAlta().get(Calendar.YEAR));
						datosFilaSalidas[k][4] = Integer.toString(sal.getFechaSalida().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(sal.getFechaSalida().get(Calendar.MONTH)+1)+"/"+Integer.toString(sal.getFechaSalida().get(Calendar.YEAR));
						datosFilaSalidas[k][5] = sal.getLugarSalida();
						k++;
					}
	
					DefaultTableModel dm = new DefaultTableModel(datosFilaSalidas,nomColumnasSalidas);
					tSalidas.setModel(dm); 
					
					lNombre.setText("");
					
					lCantMax.setText("");
	
					lCantInscriptos.setText("");
	
					lFechaSalida.setText("");
					
					lLugarSalida.setText("");
	
					revalidate();
				}
			}
		});

		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inscripcionSalidaFrame.this.dispose();	
			}
		});

		taCantTuristas.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();
		        }
		     }
		});
		ingresarSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tUsuarios.getSelectedRow()== -1 || taCantTuristas.getText().isEmpty() || tActividades.getSelectedRow()==-1 ||tSalidas.getSelectedRow()== -1 || tDepartamentos.getSelectedRow()== -1)
						throw new CampoIncompletoException("Falta algun campo por completar");
					String seleccionada = (String) datosFilaSalidas[tSalidas.getSelectedRow()][0];
					String seleccionado = (String) datosFila[tUsuarios.getSelectedRow()][0];
					f.getIControladorTurismo().inscribirTurista(seleccionado, Integer.valueOf(taCantTuristas.getText()), new GregorianCalendar(), seleccionada, null, "a", "a");
					JOptionPane.showMessageDialog(inscripcionSalidaFrame.this,"Inscripcion a Salida existosa");
					inscripcionSalidaFrame.this.dispose();
					inscripcionSalidaFrame.this.revalidate();		
				}catch(CampoIncompletoException e2) {
					JOptionPane.showMessageDialog(inscripcionSalidaFrame.this,e2.getMessage());
				}catch(SalidaLlenaException e2) {
					JOptionPane.showMessageDialog(inscripcionSalidaFrame.this,e2.getMessage());
				} catch (NumberFormatException | SalidaInvalidaException | ActividadInvalidaException
						| PaqueteInvalidoException | DepartamentoInvalidoException | CantidadTuristaException | InscripcionExistenteException e1) {
					JOptionPane.showMessageDialog(inscripcionSalidaFrame.this,e1.getMessage());
				}
			}
		});
	}
}
