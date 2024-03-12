package interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controladores.fabrica;
import datatypes.*;
import helpers.ActividadInvalidaException;
import helpers.ActividadRepetidaException;
import helpers.CampoIncompletoException;
import helpers.CantidadTuristaException;
import helpers.CategoriaInvalidaException;
import helpers.CategoriaRepetidaException;
import helpers.ContrasenasNoCoincidenException;
import helpers.CostoEsCeroException;
import helpers.DepartamentoInvalidoException;
import helpers.DuracionEsCeroException;
import helpers.InscripcionExistenteException;
import helpers.PaqueteCompradoException;
import helpers.PaqueteInvalidoException;
import helpers.SalidaInvalidaException;
import helpers.SalidaLlenaException;
import helpers.SalidaRepetidaException;
import helpers.UsuarioRepetidoException;

import java.util.*;

public class mainFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    
	public static JDesktopPane centro = new JDesktopPane();
	
	mainFrame(){	
		
		setContentPane(new JDesktopPane());
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		setExtendedState(MAXIMIZED_BOTH);
		setPreferredSize(new Dimension(1000,1000));
		this.setTitle("turismoUy");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(true);  
	    this.pack();
        this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
	    
	    
	    //Creacion del MENU
	    JMenuBar menuBar = new JMenuBar();
	    
		JMenu menuUsuarios = new JMenu("Usuarios");
		JMenu menuTurismo = new JMenu("Turismo");
		JMenu menuDatosPrueba = new JMenu("Datos");
		
		JMenuItem menuItemAltaUsuario = new JMenuItem("Alta usuario");
		JMenuItem menuItemConsultaUsuario = new JMenuItem("Consulta usuario");
		JMenuItem menuItemModificarUsuario = new JMenuItem("Modificar usuario");
		JMenuItem menuItemAltaCategoria = new JMenuItem("Alta categoria");
		JMenuItem menuItemAltaActividad = new JMenuItem("Alta actividad");
		JMenuItem menuItemConsultaActividad = new JMenuItem("Consulta actividad");
		JMenuItem menuItemAceptarRechazarActividad = new JMenuItem("Aceptar/Rechazar Actividad");
		JMenuItem menuItemAltaSalida = new JMenuItem("Alta salida");
		JMenuItem menuItemConsultarSalida = new JMenuItem("Consultar salida");
		JMenuItem menuItemInscripcionSalida = new JMenuItem("Incripcion salida");
		JMenuItem menuItemAltaPaqueteSalida = new JMenuItem("Alta paquete salida");
		JMenuItem menuItemAgregarActividadPaquete = new JMenuItem("Agregar actividad a paquete");
		JMenuItem menuItemConsultaPaqueteActividad = new JMenuItem("Consultar paquete");
		JMenuItem menuItemCargarDatosPrueba = new JMenuItem("Cargar datos de prueba");
		
		JMenu menuItemActividad = new JMenu("Actividades");
		JMenu menuItemPaquete = new JMenu("Paquetes");
		JMenu menuItemSalida = new JMenu("Salidas");
		
		getContentPane().add(menuBar);
		getContentPane().add(centro);
		
		menuBar.add(menuUsuarios);
		menuBar.add(menuTurismo);
		menuBar.add(menuDatosPrueba);
		
		menuUsuarios.add(menuItemAltaUsuario);
		menuUsuarios.add(menuItemModificarUsuario);
		menuUsuarios.add(menuItemConsultaUsuario);
		
		menuItemActividad.add(menuItemAltaCategoria);

		menuItemActividad.add(menuItemAceptarRechazarActividad);
		
		menuItemActividad.add(menuItemAltaActividad);
		menuItemActividad.add(menuItemConsultaActividad);
		
		menuItemSalida.add(menuItemAltaSalida);
		menuItemSalida.add(menuItemInscripcionSalida);
		menuItemSalida.add(menuItemConsultarSalida);
		
		menuItemPaquete.add(menuItemAltaPaqueteSalida);
		menuItemPaquete.add(menuItemAgregarActividadPaquete);
		menuItemPaquete.add(menuItemConsultaPaqueteActividad);
		
		menuTurismo.add(menuItemActividad);
		menuTurismo.add(menuItemSalida);
		menuTurismo.add(menuItemPaquete);
		
		menuDatosPrueba.add(menuItemCargarDatosPrueba);
		
		menuItemAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new altaUsuarioFrame());
			}
		});
		
		menuItemAltaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new altaCategoriaFrame());
			}
		});
		
		
		menuItemAltaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new altaActividadFrame());
			}
		});
		
		menuItemConsultaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new consultaActividadFrame(null));
			}
		});

		menuItemAceptarRechazarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new aceptarRechazarActividadFrame());
			}
		});
		
		menuItemAltaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new altaSalidaFrame());
			}
		});
		menuItemInscripcionSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new inscripcionSalidaFrame());
			}
		});
		
		menuItemConsultaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new consultaUsuarioFrame());
			}
		});

		menuItemConsultarSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new consultaSalidaFrame(null));
			}
		});
		
		menuItemModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new modificarUsuarioFrame());
				
			}
		});

		menuItemAltaPaqueteSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new altaPaqueteFrame());
			}
		});

		menuItemAgregarActividadPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new agregarActividadAPaqueteFrame());
			}
		});
		
		menuItemConsultaPaqueteActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centro.add(new consultaPaqueteFrame(null));
			}
		});

		menuItemCargarDatosPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuItemCargarDatosPrueba.setEnabled(false);
				menuItemCargarDatosPrueba.setText("Datos de prueba ya cargados");
				
				GregorianCalendar d1 = new GregorianCalendar(1970, 8, 14);
				DTUsuario pr1 = new DTProveedor("washington", "asdfg654", "Washington", "Rocha", "washington@turismorocha.gub.uy", "http://turismorocha.gub.uy/", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay", d1);

				GregorianCalendar d2 = new GregorianCalendar(1965, 5, 27);
				DTUsuario pr2 = new DTProveedor("eldiez", "ytrewq10", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", "http://wwww.socfomturriv.org.uy", "Pablo es el presidente de la Sociedad de Fomento Turístico de Rivera (conocida como Socfomturriv)", d2);
				
				GregorianCalendar d3 = new GregorianCalendar(1990, 11, 31);
				DTUsuario pr3 = new DTProveedor("meche", "mnjkiu89", "Mercedes", "Venn", "meche@colonia.gub.uy", "https://colonia.gub.uy/turismo/", "Departamento de Turismo del Departamento de Colonia", d3);
				

				try {
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(pr1, pr1.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(pr2, pr2.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(pr3, pr3.getContra());
				} catch (UsuarioRepetidoException | CampoIncompletoException | ContrasenasNoCoincidenException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}

				
				GregorianCalendar d5 = new GregorianCalendar(1927, 1, 23);
				DTUsuario tu1 = new DTTurista("lachiqui", "awdrg543", "Rosa Maria", "Martinez", "mirtha.legrand.ok@hotmail.com.ar", "argentina", d5);
				
				GregorianCalendar d6 = new GregorianCalendar(1926, 3, 21);
				DTUsuario tu2 = new DTTurista("isabelita", "r5t6y7u8", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", "inglesa", d6);
				
				GregorianCalendar d7 = new GregorianCalendar(1937, 11, 31);
				DTUsuario tu3 = new DTTurista("anibal", "edrft543", "Aníbal", "Lecter", "anibal@fing.edu.uy", "lituana", d7);
				
				GregorianCalendar d8 = new GregorianCalendar(1990, 3, 15);
				DTUsuario tu4 = new DTTurista("waston", "poiuy987", "Emma", "Waston", "e.waston@gmail.com", "inglesa", d8);

				GregorianCalendar d9 = new GregorianCalendar(1971, 6, 30);
				DTUsuario tu5 = new DTTurista("elelvis", "45idgaf67", "Elvis", "Lacio", "suavemente@hotmail.com", "estadounidense", d9);

				GregorianCalendar d10 = new GregorianCalendar(2004, 1, 19);
				DTUsuario tu6 = new DTTurista("eleven11", "xdrgb657", "Eleven", "Once", "eleven11@gmail.com", "española", d10);

				GregorianCalendar d11 = new GregorianCalendar(1999, 4, 1);
				DTUsuario tu7 = new DTTurista("bobesponja", "sbsplol1", "Bob", "Esponja", "bobesponja@nickelodeon.com", "japonesa", d11);

				GregorianCalendar d12 = new GregorianCalendar(1976, 3, 11);
				DTUsuario tu8 = new DTTurista("tony", "okmnji98 ", "Antonio", "Pacheco", "eltony@manya.org.uy", "uruguaya", d12);

				GregorianCalendar d13 = new GregorianCalendar(1976, 2, 17);
				DTUsuario tu9 = new DTTurista("chino", "qsxcdw43", "Alvaro", "Recoba", "chino@trico.org.uy", "uruguaya", d13);

				GregorianCalendar d14 = new GregorianCalendar(1922, 1, 7);
				DTUsuario tu10 = new DTTurista("mastropiero", "qpwoei586", "Jogann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", "austríaca", d14);
				
				try {
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu1, tu1.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu2, tu2.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu3, tu3.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu4, tu4.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu5, tu5.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu6, tu6.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu7, tu7.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu8, tu8.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu9, tu9.getContra());
					fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(tu10, tu10.getContra());
				}catch(CampoIncompletoException e2) {
					e2.printStackTrace();
				}catch(UsuarioRepetidoException e2) {
					e2.printStackTrace();
				}catch(ContrasenasNoCoincidenException e2) {
					e2.printStackTrace();
				}
				
				DTDepartamento A = new DTDepartamento("Canelones", "División Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
				DTDepartamento B = new DTDepartamento("Maldonado", "División Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
				DTDepartamento C = new DTDepartamento("Rocha", "La Organización de Gestión del Destino (OGD) Rocha es un ámbito de articulación público - privada en el sector turístico que integran la Corporación Rochense de Turismo y la Intendencia de Rocha a través de su Dirección de Turismo.", "http://www.turismorocha.gub.uy/");
				DTDepartamento D = new DTDepartamento("Treinta y Tres", "División Turismo de la Intendencia", "https://treintaytres.gub.uy/");
				DTDepartamento E = new DTDepartamento("Cerro Largo", "División Turismo de la Intendencia", "https://www.gub.uy/intendencia-cerro-largo/");
				DTDepartamento F = new DTDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interés turístico con la participación institucional pública - privada en bien del desarrollo socioeconómico de la comunidad.", "http://www.rivera.gub.uy/social/turismo/");
				DTDepartamento G = new DTDepartamento("Artigas", "División Turismo de la Intendencia", "http://www.artigas.gub.uy/");
				DTDepartamento H = new DTDepartamento("Salto", "División Turismo de la Intendencia", "https://www.salto.gub.uy/");
				DTDepartamento I = new DTDepartamento("Paysandú", "División Turismo de la Intendencia", "https://www.paysandu.gub.uy/");
				DTDepartamento J = new DTDepartamento("Río Negro", "División Turismo de la Intendencia", "https://www.rionegro.gub.uy/");
				DTDepartamento K = new DTDepartamento("Soriano", "División Turismo de la Intendencia", "https://www.soriano.gub.uy/");
				DTDepartamento L = new DTDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");
				DTDepartamento M = new DTDepartamento("San José", "División Turismo de la Intendencia", "https://sanjose.gub.uy/");
				DTDepartamento N = new DTDepartamento("Flores", "División Turismo de la Intendencia", "https://flores.gub.uy/");
				DTDepartamento O = new DTDepartamento("Florida", "División Turismo de la Intendencia", "http://www.florida.gub.uy/");
				DTDepartamento P = new DTDepartamento("Lavalleja", "División Turismo de la Intendencia", "http://www.lavalleja.gub.uy/");
				DTDepartamento Q = new DTDepartamento("Durazno", "División Turismo de la Intendencia", "https://durazno.uy/");
				DTDepartamento R = new DTDepartamento("Tacuarembó", "División Turismo de la Intendencia", "https://tacuarembo.gub.uy/");
				DTDepartamento S = new DTDepartamento("Montevideo", "División Turismo de la Intendencia", "https://montevideo.gub.uy/areas-tematicas/turismo");
				
				
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(A);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(B);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(C);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(D);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(E);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(F);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(G);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(H);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(I);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(J);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(K);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(L);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(M);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(N);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(O);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(P);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(Q);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(R);
				fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(S);
				
				
				DTCategoria C1 = new DTCategoria("Aventura y Deporte");
				DTCategoria C2 = new DTCategoria("Campo y Naturaleza");
				DTCategoria C3 = new DTCategoria("Cultura y Patrimonio");
				DTCategoria C4 = new DTCategoria("Gastronomía");
				DTCategoria C5 = new DTCategoria("Turismo Playas");
				
				try {
					fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(C1);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(C2);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(C3);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(C4);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(C5);
				} catch (CategoriaRepetidaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				GregorianCalendar fAct1 = new GregorianCalendar(2022, 6, 20);
				DTActividad act1 = new DTActividad("Degusta", "Festival gastronómico de productos locales en Rocha", 3, 800, "Rocha", fAct1, EstadoAct.Agregada, "https://www.youtube.com/embed/zQjSMQ6uV1g", 0, null);

				GregorianCalendar fAct2 = new GregorianCalendar(2022, 6, 21);
				DTActividad act2 = new DTActividad("Teatro con Sabores", "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500, "Rocha", fAct2, EstadoAct.Agregada, "https://www.youtube.com/embed/Lxit3xvKShc", 0, null);
				
				GregorianCalendar fAct3 = new GregorianCalendar(2022, 7, 1);
				DTActividad act3 = new DTActividad("Tour por Colonia del Sacramento", "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400, "Colonia del Sacramento", fAct3, EstadoAct.Agregada, "https://www.youtube.com/embed/zVDGjURCBz8", 0, null);
				
				GregorianCalendar fAct4 = new GregorianCalendar(2022, 7, 1);
				DTActividad act4 = new DTActividad("Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menú internacional", 2, 800, "Colonia del Sacramento", fAct4, EstadoAct.Agregada, "https://www.youtube.com/embed/wfyDxicM1PQ", 0, null);
				
				GregorianCalendar fAct5 = new GregorianCalendar(2022, 7, 1);
				DTActividad act5 = new DTActividad("Almuerzo en Valle del Lunarejo", "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300, "Tranqueras", fAct5, EstadoAct.Agregada, "https://www.youtube.com/embed/5uaEdiQVEEE", 0, null);
				
				GregorianCalendar fAct6 = new GregorianCalendar(2022, 7, 1);
				DTActividad act6 = new DTActividad("Cabalgata en Valle del Lunarejo", "Cabalgata por el área protegida. Varios recorridos para elegir.", 2, 150, "Tranqueras", fAct6, EstadoAct.Agregada, "https://www.youtube.com/embed/dlUb22YfXDg", 0, null);
				
				GregorianCalendar fAct7 = new GregorianCalendar(2022, 8, 1);
				DTActividad act7 = new DTActividad("Bus turístico Colonia", "Recorrida por los principales atractivos de la ciudad", 3, 600, "Colonia del Sacramento", fAct7, EstadoAct.Agregada, "https://www.youtube.com/embed/dlUb22YfXDg", 0, null);
				
				GregorianCalendar fAct8 = new GregorianCalendar(2022, 8, 3);
				DTActividad act8 = new DTActividad("Colonia Premium Tour", "Visita lugares exclusivos y relevantes", 4, 2600, "Colonia del Sacramento", fAct8, EstadoAct.Agregada, null, 0, null);
				
				GregorianCalendar fAct9 = new GregorianCalendar(2022, 8, 5);
				DTActividad act9 = new DTActividad("Deportes náuticos sin uso de motor", "kitsurf - windsurf - kayakismo - canotaje en Rocha", 3, 1200, "Rocha", fAct9, EstadoAct.Agregada, "https://www.youtube.com/embed/a7Lfx4Flb28", 0, null);
				
				GregorianCalendar fAct10 = new GregorianCalendar(2022, 8, 16);
				DTActividad act10 = new DTActividad("Descubre Rivera", "Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicación geográfica privilegiada", 2, 650, "Rivera", fAct10, EstadoAct.Agregada, null, 0, null);
				
				Set<String> CA1= new HashSet<String>();
				CA1.add(C4.getNombre());
				
				Set<String> CA2= new HashSet<String>();
				CA2.add(C3.getNombre());
				CA2.add(C4.getNombre());
				
				Set<String> CA3= new HashSet<String>();
				CA3.add(C3.getNombre());
				
				Set<String> CA4= new HashSet<String>();
				CA4.add(C4.getNombre());
				
				Set<String> CA5= new HashSet<String>();
				CA5.add(C2.getNombre());
				CA5.add(C4.getNombre());
				
				Set<String> CA6= new HashSet<String>();
				CA6.add(C2.getNombre());
				
				Set<String> CA7= new HashSet<String>();
				CA7.add(C3.getNombre());
				
				Set<String> CA8= new HashSet<String>();
				CA8.add(C3.getNombre());
				
				Set<String> CA9= new HashSet<String>();
				CA9.add(C1.getNombre());
				CA9.add(C5.getNombre());
				
				Set<String> CA10= new HashSet<String>();
				CA10.add(C3.getNombre());

				
				try {
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("washington@turismorocha.gub.uy", "Rocha", act1, CA1);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("washington@turismorocha.gub.uy", "Rocha", act2, CA2);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("meche@colonia.gub.uy", "Colonia", act3, CA3);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("meche@colonia.gub.uy", "Colonia", act4, CA4);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("eldiez@socfomturriv.org.uy", "Rivera", act5, CA5);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("eldiez@socfomturriv.org.uy", "Rivera", act6, CA6);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("meche@colonia.gub.uy", "Colonia", act7, CA7);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("meche@colonia.gub.uy", "Colonia", act8, CA8);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("washington@turismorocha.gub.uy", "Rocha", act9, CA9);
					fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("eldiez@socfomturriv.org.uy", "Rivera", act10, CA10);
				} catch (DepartamentoInvalidoException | CategoriaInvalidaException | DuracionEsCeroException
						| CostoEsCeroException | ActividadRepetidaException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act1.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act2.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act3.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act4.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act5.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act6.getNombre(), true);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act8.getNombre(), false);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(act10.getNombre(), false);

					
				DTSalida S1 = new DTSalida("Degusta Agosto", 20, 0, new GregorianCalendar(2022, 6, 21), new GregorianCalendar(2022, 7, 20, 17, 0), "Sociedad Agropecuaria de Rocha");
				DTSalida S2 = new DTSalida("Degusta Setiembre", 20, 0, new GregorianCalendar(2022, 6, 22), new GregorianCalendar(2022, 8, 3, 17, 0), "Sociedad Agropecuaria de Rocha");
				DTSalida S3 = new DTSalida("Teatro con Sabores 1", 30, 0, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 4, 18, 0), "Club Deportivo Unión");
				DTSalida S4 = new DTSalida("Teatro con Sabores 2", 30, 0, new GregorianCalendar(2022, 6, 23), new GregorianCalendar(2022, 8, 11, 18, 0), "Club Deportivo Unión");
				DTSalida S5 = new DTSalida("Tour Colonia del Sacramento 11-09", 5, 0, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 11, 10, 0), "Encuentro en la base del Faro");
				DTSalida S6 = new DTSalida("Tour Colonia del Sacramento 18-09", 5, 0, new GregorianCalendar(2022, 7, 5), new GregorianCalendar(2022, 8, 18, 10, 0), "Encuentro en la base del Faro");
				DTSalida S7 = new DTSalida("Almuerzo 1", 5, 0, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 18, 12, 0), "Restaurante de la Plaza de Toros");
				DTSalida S8 = new DTSalida("Almuerzo 2", 5, 0, new GregorianCalendar(2022, 7, 4), new GregorianCalendar(2022, 8, 25, 12, 0), "Restaurante de la Plaza de Toros");
				DTSalida S9 = new DTSalida("Almuerzo 3", 4, 0, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10, 12, 0), "Posada Del Lunarejo");
				DTSalida S10 = new DTSalida("Almuerzo 4", 4, 0, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11, 12, 0), "Posada Del Lunarejo");
				DTSalida S11 = new DTSalida("Cabalgata 1", 4, 0, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 10, 16, 0), "Posada Del Lunarejo");
				DTSalida S12 = new DTSalida("Cabalgata 2", 4, 0, new GregorianCalendar(2022, 7, 15), new GregorianCalendar(2022, 8, 11, 16, 0), "Posada Del Lunarejo");
				DTSalida S13 = new DTSalida("Degusta Octubre", 20, 0, new GregorianCalendar(2022, 8, 22), new GregorianCalendar(2022, 9, 30, 17, 0), "Sociedad Agropecuaria de Rocha");
				DTSalida S14 = new DTSalida("Degusta Noviembre", 20, 0, new GregorianCalendar(2022, 9, 2), new GregorianCalendar(2022, 10, 5, 17, 0), "Sociedad Agropecuaria de Rocha");
				DTSalida S15 = new DTSalida("Teatro con Sabores 3", 30, 0, new GregorianCalendar(2022, 7, 25), new GregorianCalendar(2022, 10, 11, 18, 0), "Club Deportivo Unión");
				DTSalida S16 = new DTSalida("Tour Colonia del Sacramento 30-10", 10, 0, new GregorianCalendar(2022, 8, 7), new GregorianCalendar(2022, 9, 30, 10, 0), "Encuentro en la base del Faro");
				DTSalida S17 = new DTSalida("Cabalgata Extrema", 4, 0, new GregorianCalendar(2022, 8, 15), new GregorianCalendar(2022, 9, 30, 16, 0), "Posada Del Lunarejo");
				DTSalida S18 = new DTSalida("Almuerzo en el Real 1", 10, 0, new GregorianCalendar(2022, 9, 10), new GregorianCalendar(2022, 9, 30, 12, 0), "Posada Del Lunarejo");
				DTSalida S19 = new DTSalida("Degusta Diciembre", 20, 0, new GregorianCalendar(2022, 10, 7), new GregorianCalendar(2022, 11, 2, 17, 0), "Sociedad Agropecuaria de Rocha");
				DTSalida S20 = new DTSalida("Teatro con Sabores 4", 30, 0, new GregorianCalendar(2022, 10, 7), new GregorianCalendar(2022, 11, 3, 18, 0), "Club Deportivo Unión");
				try {
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act1.getNombre(), S1);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act1.getNombre(), S2);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act2.getNombre(), S3);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act2.getNombre(), S4);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act3.getNombre(), S5);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act3.getNombre(), S6);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act4.getNombre(), S7);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act4.getNombre(), S8);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act5.getNombre(), S9);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act5.getNombre(), S10);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act6.getNombre(), S11);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act6.getNombre(), S12);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act1.getNombre(), S13);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act1.getNombre(), S14);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act2.getNombre(), S15);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act3.getNombre(), S16);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act6.getNombre(), S17);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act4.getNombre(), S18);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act1.getNombre(), S19);
					fabrica.getInstance().getIControladorTurismo().ingresarSalida(act2.getNombre(), S20);
				}catch(SalidaRepetidaException e2) {
					//Es seguro que no ocurrira la excepcion
				} catch (ActividadInvalidaException e1) {
					//Es seguro que no ocurrira la excepcion
				} catch (CantidadTuristaException e1) {
					//Es seguro que no ocurrira la excepcion
				}
				
				DTPaquete P1 = new DTPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, new GregorianCalendar(2022, 7, 10));
				DTPaquete P2 = new DTPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, new GregorianCalendar(2022, 7, 1));
				DTPaquete P3 = new DTPaquete("Valle Del Lunarejo", "Visite un área protegida con un paisaje natural hermoso", 60, 15, new GregorianCalendar(2022, 8, 15));
				DTPaquete P4 = new DTPaquete("Rocha de Fiesta", "Para cerrar el año a lo grande en nuestro departamento más oceanico", 45, 30, new GregorianCalendar(2022, 10, 7));
				
				fabrica.getInstance().getIControladorPaquete().confirmarPaquete(P1);
				fabrica.getInstance().getIControladorPaquete().confirmarPaquete(P2);
				fabrica.getInstance().getIControladorPaquete().confirmarPaquete(P3);
				fabrica.getInstance().getIControladorPaquete().confirmarPaquete(P4);

				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act1.getNombre(), P1.getNombre());
				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act2.getNombre(), P1.getNombre());
				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act3.getNombre(), P2.getNombre());
				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act5.getNombre(), P3.getNombre());
				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act6.getNombre(), P3.getNombre());
				fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(act1.getNombre(), P4.getNombre());
				
				//comprarPaquetes
				GregorianCalendar fc1 = new GregorianCalendar(2022, 7, 15);
				GregorianCalendar fc2 = new GregorianCalendar(2022, 7, 20);
				GregorianCalendar fc3 = new GregorianCalendar(2022, 8, 15);
				GregorianCalendar fc4 = new GregorianCalendar(2022, 8, 1);
				GregorianCalendar fc5 = new GregorianCalendar(2022, 8, 18);
				GregorianCalendar fc6 = new GregorianCalendar(2022, 8, 2);
				try {
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu1.getNickname(), 2, fc1, P1.getNombre());
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu1.getNickname(), 5, fc2, P2.getNombre());
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu4.getNickname(), 1, fc3, P2.getNombre());
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu5.getNickname(), 10, fc4, P1.getNombre());
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu5.getNickname(), 2, fc5, P2.getNombre());
					fabrica.getInstance().getIControladorPaquete().comprarPaquete(tu10.getNickname(), 6, fc6, P2.getNombre());
				} catch (CantidadTuristaException | PaqueteInvalidoException | PaqueteCompradoException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				try {
					//General
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 3, new GregorianCalendar(2022, 7, 15), S1.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu5.getNickname(), 5, new GregorianCalendar(2022, 7, 16), S1.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 3, new GregorianCalendar(2022, 7, 18), S6.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu2.getNickname(), 1, new GregorianCalendar(2022, 7, 19), S6.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu10.getNickname(), 2, new GregorianCalendar(2022, 7, 19), S8.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu9.getNickname(), 1, new GregorianCalendar(2022, 7, 19), S3.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu9.getNickname(), 10, new GregorianCalendar(2022, 7, 20), S4.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu7.getNickname(), 2, new GregorianCalendar(2022, 7, 20), S4.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu3.getNickname(), 1, new GregorianCalendar(2022, 7, 21), S4.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu8.getNickname(), 11, new GregorianCalendar(2022, 7, 21), S2.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 5, new GregorianCalendar(2022, 8, 3), S7.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu4.getNickname(), 1, new GregorianCalendar(2022, 8, 5), S8.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu5.getNickname(), 2, new GregorianCalendar(2022, 9, 11), S18.getNombre(), null, "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu10.getNickname(), 4, new GregorianCalendar(2022, 9, 12), S18.getNombre(), null, "a", "a");
					
					//Paquete
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 2, new GregorianCalendar(2022, 9, 3), S14.getNombre(), P1.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 2, new GregorianCalendar(2022, 9, 3), S15.getNombre(), P1.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu5.getNickname(), 5, new GregorianCalendar(2022, 8, 2), S2.getNombre(), P1.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu5.getNickname(), 5, new GregorianCalendar(2022, 8, 2), S3.getNombre(), P1.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu1.getNickname(), 5, new GregorianCalendar(2022, 8, 3), S5.getNombre(), P2.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu4.getNickname(), 1, new GregorianCalendar(2022, 8, 5), S6.getNombre(), P2.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu5.getNickname(), 2, new GregorianCalendar(2022, 9, 2), S16.getNombre(), P2.getNombre(), "a", "a");
					fabrica.getInstance().getIControladorTurismo().inscribirTurista(tu10.getNickname(), 4, new GregorianCalendar(2022, 9, 12), S16.getNombre(), P2.getNombre(), "a", "a");
					
				} catch (SalidaLlenaException | DepartamentoInvalidoException | PaqueteInvalidoException | ActividadInvalidaException | SalidaInvalidaException | CantidadTuristaException | InscripcionExistenteException e4) {
					e4.printStackTrace();
				}
				
				
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu1.getNickname(), act1.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu1.getNickname(), act3.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu2.getNickname(), act3.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu2.getNickname(), act4.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu3.getNickname(), act4.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu3.getNickname(), act5.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu3.getNickname(), act6.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu4.getNickname(), act1.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu4.getNickname(), act2.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu4.getNickname(), act3.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu4.getNickname(), act4.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu5.getNickname(), act6.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu6.getNickname(), act1.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu6.getNickname(), act2.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu7.getNickname(), act3.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu7.getNickname(), act4.getNombre());
				fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita(tu8.getNickname(), act2.getNombre());
				
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu1.getNickname(), tu2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu1.getNickname(), tu10.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu1.getNickname(), pr1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu1.getNickname(), pr2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu1.getNickname(), pr3.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu2.getNickname(), tu1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu3.getNickname(), tu4.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu3.getNickname(), tu6.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu3.getNickname(), tu7.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu3.getNickname(), pr3.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu4.getNickname(), tu2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu4.getNickname(), pr1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu5.getNickname(), tu7.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu5.getNickname(), tu8.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu5.getNickname(), pr2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu6.getNickname(), tu1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu6.getNickname(), tu4.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu6.getNickname(), tu10.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu7.getNickname(), tu3.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu7.getNickname(), tu6.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu8.getNickname(), tu9.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu8.getNickname(), pr2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu9.getNickname(), tu5.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu9.getNickname(), tu10.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu9.getNickname(), pr1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(tu9.getNickname(), pr3.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr1.getNickname(), tu10.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr1.getNickname(), tu4.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr2.getNickname(), tu8.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr3.getNickname(), tu1.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr3.getNickname(), tu2.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr3.getNickname(), tu4.getNickname());
				fabrica.getInstance().getIControladorUsuarios().seguirUsuario(pr3.getNickname(), tu6.getNickname());

				
				
				JOptionPane.showMessageDialog(centro,"Datos de prueba cargados");
			}
		});
		
		revalidate();	
	}
}
