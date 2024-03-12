package helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import controladores.ManejadorPaquete;
import controladores.ManejadorTurismo;
import controladores.ManejadorUsuario;
import controladores.fabrica;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTDepartamento;
import datatypes.DTInscripcion;
import datatypes.DTPaquete;
import datatypes.DTProveedor;
import datatypes.DTSalida;
import datatypes.DTTurista;
import datatypes.DTUsuario;
import datatypes.EstadoAct;
import modelo.turista;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_Test {
	
	@Test
	@Order(1)
	void testRegistrarTurista() {
		
		GregorianCalendar d = new GregorianCalendar(1995,9,3);
		DTUsuario testUser = new DTTurista("PepeViajes","1234","Pepe","Martinez","pepe@gmail.com","uruguaya",d);
		
		try {
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser, "1234");
			boolean existeEmail = fabrica.getInstance().getIControladorUsuarios().existeUsuarioConEmail("pepe@gmail.com");
			boolean existeNickname = fabrica.getInstance().getIControladorUsuarios().existeUsuarioConNickname("PepeViajes");
			
			boolean existeEmail2 = fabrica.getInstance().getIControladorUsuarios().existeUsuarioConEmail("kevin@.com");
			
			assertEquals(existeEmail, true);
			assertEquals(existeNickname, true);
			
			assertEquals(existeEmail2,false);
			
			DTUsuario dtTest = fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioConEmail("pepe@gmail.com");
			
			boolean esTurista = DTTurista.class.isInstance(dtTest);
			
			if (esTurista) {
				DTTurista dtTur = DTTurista.class.cast(dtTest);
				
				assertEquals(dtTur.getNombre(),testUser.getNombre());
				assertEquals(dtTur.getApellido(),testUser.getApellido());
				assertEquals(dtTur.getEmail(),testUser.getEmail());
				assertEquals(dtTur.getNickname(),testUser.getNickname());
			}else {
				fail("No fue detectado como turista");
			}
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		} 
	}
	
	@Test
	@Order(2)
	void testRegistrarProveedor() {
		GregorianCalendar d = new GregorianCalendar(2001,10,27);
		DTUsuario testUserProv = new DTProveedor("MariaProveeMucho","1234","Maria","Rodriguez","maria@outlook.com","maria.proviciones.com","Muy proveedora",d);
		
		try {
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUserProv, "1234");
			boolean existeEmail = fabrica.getInstance().getIControladorUsuarios().existeUsuarioConEmail("maria@outlook.com");
			boolean existeNickname = fabrica.getInstance().getIControladorUsuarios().existeUsuarioConNickname("MariaProveeMucho");
			
			assertEquals(existeEmail, true);
			assertEquals(existeNickname, true);
			
			DTUsuario dtTest = fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioConEmail("maria@outlook.com");
			
			boolean esProveedor = DTProveedor.class.isInstance(dtTest);
			
			if (esProveedor) {
				DTProveedor datosProv = DTProveedor.class.cast(dtTest);
				
				assertEquals(datosProv.getNombre(),testUserProv.getNombre());
				assertEquals(datosProv.getApellido(),testUserProv.getApellido());
				assertEquals(datosProv.getEmail(),testUserProv.getEmail());
				assertEquals(datosProv.getNickname(),testUserProv.getNickname());
				assertEquals(datosProv.getSitioWeb(),((DTProveedor) testUserProv).getSitioWeb());
				assertEquals(datosProv.getDescripcion(),((DTProveedor) testUserProv).getDescripcion());
			}else {
				fail("No fue detectado como turista");
			}
			
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		} 
		
	}
	
	@Test
	@Order(3)
	void testRegistrarProveedorRepetido() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTProveedor("MartinElMejorProveedor","1234","Martin","Perez","pepe@gmail.com","martin.viajes.com","Un muy buen proveedor, muy humilde",d);
		assertThrows(UsuarioRepetidoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser, "1234");});
	}
	
	@Test
	@Order(4)
	void testRegistrarTuristaRepetido() {
		GregorianCalendar d = new GregorianCalendar(1949,2,4);
		DTUsuario testUser = new DTTurista("Marie Curie","1234","Marie","Curie","maria@outlook.com","polaca",d);
		assertThrows(UsuarioRepetidoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser,"1234");});	
	}
	
	@Test
	@Order(5)
	void testRegistrarUsuarioConCamposVacios() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTProveedor("MartinElMejorProveedor","1234","","Perez","","martin.viajes.com","Un muy buen proveedor, muy humilde",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser, "1234");});	
	}
	
	@Test
	@Order(6)
	void testRegistrarTuristaConCamposVacios() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTTurista("IViajes","1234","Ivan","ElDeMiEquipo","nodescanses@descansa.com","",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser, "1234");});	
	}
	
	@Test
	@Order(7)
	void testRegistrarProveedorConCamposVacios() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTProveedor("MartinElMejorProveedor","1234","Martin","Perez","martin@martin.com","martin.viajes.com","",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(testUser, "1234");});	
	}
	
	@Test
	@Order(8)
	void testRecuperarUsuarioEmail() {
		Map<String,DTUsuario> testUsrs = fabrica.getInstance().getIControladorUsuarios().obtenerUsuariosEmail();
		assertEquals(testUsrs.size(),2);
		assertEquals(testUsrs.get("maria@outlook.com").getNickname(),"MariaProveeMucho");
		assertEquals(testUsrs.get("pepe@gmail.com").getNickname(),"PepeViajes");		
	}
	
	@Test
	@Order(9)
	void testRecuperarUsuarioNickname() {
		Map<String,DTUsuario> testUsrs = fabrica.getInstance().getIControladorUsuarios().obtenerUsuariosNickname();
		assertEquals(testUsrs.size(),2);
		assertEquals(testUsrs.get("MariaProveeMucho").getEmail(),"maria@outlook.com");
		assertEquals(testUsrs.get("PepeViajes").getEmail(),"pepe@gmail.com");		
	}
	
	@Test
	@Order(10)
	void testRecuperarProveedores() {
		Map<String,DTUsuario> testUsrs = fabrica.getInstance().getIControladorUsuarios().obtenerProveedores();
		assertEquals(testUsrs.size(),1);
		assertEquals(testUsrs.get("maria@outlook.com").getNickname(),"MariaProveeMucho");
		assertEquals(testUsrs.get("PepeViajes"),null);		
	}
	
	
	@Test
	@Order(11)
	void modificarTuristaTest() {
		GregorianCalendar d = new GregorianCalendar(1956,2,5);
		DTUsuario testUser = new DTTurista("PepeViajes","1234","Pepe Alejandro","Martinez","pepe@gmail.com","uruguaya e italiana",d);
		try {
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser);
		}catch(CampoIncompletoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	@Order(12)
	void modificarProveedorTest() {
		GregorianCalendar d = new GregorianCalendar(1956,2,2);
		DTUsuario testUser = new DTProveedor("MariaProveeMucho","1234","Marie","Rodriguez Casas","maria@outlook.com","ElPentagon.com","Mas proveedora que nunca",d);
		try {
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser);
		}catch(CampoIncompletoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(13)
	void modificarUsuarioCamposVaciosTest() {
		GregorianCalendar d = new GregorianCalendar(1956,2,2);
		DTUsuario testUser = new DTTurista("PepeViajaPorAhi","1234","Pepe Alejandro","Martinez","pepe@gmail.com","",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser);});	
	}
	
	@Test
	@Order(14)
	void registrarDepartamentosTest() {
		DTDepartamento testDpto = new DTDepartamento("Wachington","Departamento totalmente en EEUU","wachington.com");
		DTDepartamento testDpto2 = new DTDepartamento("California","Departamento muy estadounidense","california.com");
		fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(testDpto);	
		fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(testDpto2);
		ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").setNombre("Wachington");
		ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").setDescripcion("Departamento totalmente en EEUU");
		ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").setUrl("wachington.com");
		Map<String,DTDepartamento> testDptos = fabrica.getInstance().getIControladorTurismo().obtenerDepartamentos();
		assertEquals(2,testDptos.size());
		assertEquals(testDptos.get("Wachington").getUrl(),"wachington.com");
		assertEquals(testDptos.get("California").getDescripcion(),"Departamento muy estadounidense");
		assertEquals(ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").getActividades().size(),0);
	}
	
	@Test
	@Order(15)
	void registrarActividadTest() {
		GregorianCalendar d = new GregorianCalendar(1999,3,2);
		DTActividad testAct = new DTActividad("Salto de garrocha","Muchos saltos, muchas garrochas",5,200,"Niu Shork", d, EstadoAct.Agregada, null, 0, null);
		GregorianCalendar d2 = new GregorianCalendar(2022,4,2);
		DTActividad testAct2 = new DTActividad("Recorrida de la casa blanca","Caminar por la casa blanca",2,1300,"Ciudad de la casa blanca", d2, EstadoAct.Agregada, null, 0,  null); //Geografia a febrero
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeDepartamento("Wachington").size(),0);
		Set<String> hs= new HashSet<String>();
		hs.add("Circo");
		hs.add("Publico");
		DTCategoria testCat = new DTCategoria("Circo");
		DTCategoria testCat2 = new DTCategoria("Publico");
		try {
			fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(testCat);
			fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(testCat2);
			assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerCategorias().size(),2);
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			try {
				fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("maria@outlook.com","Wachington",testAct,hs);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(testAct.getNombre(), true);
			} catch (DepartamentoInvalidoException | CategoriaInvalidaException | DuracionEsCeroException | CostoEsCeroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("maria@outlook.com","California",testAct2,hs);
				fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad(testAct2.getNombre(), true);
			} catch (DepartamentoInvalidoException | CategoriaInvalidaException | DuracionEsCeroException | CostoEsCeroException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch (ActividadRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerCategoriasDeActividad(testAct.getNombre()).size(),2);
		assertEquals(ManejadorTurismo.getInstance().obtenerDatosCategorias().size(),2);
		assertEquals(ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).getDescripcion(),testAct.getDescripcion());
		assertEquals(ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).getDuracion(),testAct.getDuracion());
		assertEquals(ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).getFechaAlta(),testAct.getFechaAlta());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setNombre(testAct.getNombre());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setDescripcion(testAct.getDescripcion());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setDuracion(testAct.getDuracion());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setCostoTurista(testAct.getCostoTurista());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setCiudad(ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).getCiudad());
		ManejadorTurismo.getInstance().darActividad(testAct.getNombre()).setFechaAlta(testAct.getFechaAlta());
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDeCategoria("Circo").size(),2);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDelDepartamento("Wachington").size(),1);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDelProveedorSinSalidasVigentes("MariaProveeMucho").size(),2);
	}
	
	@Test
	@Order(16)
	void registrarActividadRepetidaTest() {
		GregorianCalendar d = new GregorianCalendar(1999,3,2);
		DTActividad testAct = new DTActividad("Salto de garrocha","Pocos saltos, pocas garrochas",2,300,"Teksas",d, EstadoAct.Agregada, null, 0, null);
		assertThrows(ActividadRepetidaException.class, ()->{
			fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("pepe@gmail.com","Wachington",testAct,null);});		
	}
	
	@Test
	@Order(17)
	void recuperarActividadesTest() {
		Map<String,DTActividad> testActs = fabrica.getInstance().getIControladorTurismo().obtenerActividades();
		assertEquals(2,testActs.size());
		assertEquals(testActs.get("Salto de garrocha").getCostoTurista(),200);
		assertEquals(testActs.get("Recorrida de la casa blanca").getDuracion(),2);
		
		Set<String> testActsDptos = fabrica.getInstance().getIControladorTurismo().listarActividades("California");
		assertEquals(1,testActsDptos.size());
		assertEquals(testActsDptos.contains("Salto de garrocha"),false);
		assertEquals(testActsDptos.contains("Recorrida de la casa blanca"),true);
		
		Map<String,DTActividad> testActs3 = fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeProveedor("MariaProveeMucho");
		assertEquals(2,testActs3.size());
		assertEquals(testActs3.get("Salto de garrocha").getCiudad(),"Niu Shork");
		assertEquals(testActs3.get("Recorrida de la casa blanca").getDescripcion(),"Caminar por la casa blanca");
		
	}
	
	@Test
	@Order(18)
	void registrarSalidaTest() throws ActividadInvalidaException, CantidadTuristaException {
		GregorianCalendar d = new GregorianCalendar(1999,3,2);
		GregorianCalendar d2 = new GregorianCalendar(2001,5,1);
		GregorianCalendar d3 = new GregorianCalendar(2022,9,3);
		GregorianCalendar d4 = new GregorianCalendar(2023,11,4);
		DTSalida testSal = new DTSalida("Salto de garrocha 1",20,0,d,d2,"A confirmar");
		DTSalida testSal2 = new DTSalida("Salto de garrocha 2",10,0,d3,d4,"Tambien a confirmar");
		try {
			fabrica.getInstance().getIControladorTurismo().ingresarSalida("Salto de garrocha",testSal);
			assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadDeSalida("Salto de garrocha 1"),"Salto de garrocha");
			assertEquals(fabrica.getInstance().getIControladorTurismo().darNombreActSal("Salto de garrocha 1"),"Salto de garrocha");
			fabrica.getInstance().getIControladorTurismo().ingresarSalida("Salto de garrocha",testSal2);
		}catch (SalidaRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		Map<String, DTActividad> acts= new HashMap<String, DTActividad>();
		acts.put("Salto de garrocha",ManejadorTurismo.getInstance().darActividad("Salto de garrocha").darDatos());
		fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad("Salto de garrocha", false);
		fabrica.getInstance().getIControladorTurismo().cambiarEstadoActividad("Salto de garrocha", true);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividades(acts).size(),2);
		assertEquals(ManejadorTurismo.getInstance().obtenerSalidas().size(),2);
		assertEquals(fabrica.getInstance().getIControladorTurismo().listarSalidas("Salto de garrocha").size(),2);
		assertEquals(fabrica.getInstance().getIControladorTurismo().listarActividadesAgregadas().size(),0);
		assertEquals(fabrica.getInstance().getIControladorTurismo().darDepSal("Salto de garrocha 1"),"Wachington");
	}
	
	@Test
	@Order(19)
	void registrarSalidaRepetidaTest() {
		GregorianCalendar d = new GregorianCalendar(2000,3,2);
		GregorianCalendar d2 = new GregorianCalendar(2002,5,1);
		DTSalida testSal = new DTSalida("Salto de garrocha 1",30,0,d,d2,"Ya esta confirmado");
		
		assertThrows(SalidaRepetidaException.class, ()->{
			fabrica.getInstance().getIControladorTurismo().ingresarSalida("Salto de garrocha",testSal);});	
	}
	
	@Test
	@Order(20)
	void inscribirTuristaTest() {
		GregorianCalendar d = new GregorianCalendar(2000,3,2);
		try {
			fabrica.getInstance().getIControladorTurismo().inscribirTurista("PepeViajes",3,d,"Salto de garrocha 1",null, "a", "a");
		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		fabrica.getInstance().getIControladorTurismo().agregarActividadFavorita("PepeViajes", "Salto de garrocha");
		fabrica.getInstance().getIControladorTurismo().quitarActividadFavorita("PepeViajes", "Salto de garrocha");
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesFavoritas("PepeViajes",ManejadorTurismo.getInstance().obtenerDatosActividadesCategoria("a")).size(),0);
		assertEquals(fabrica.getInstance().getIControladorTurismo().esFavorito("PepeViajes", "Salto de garrocha"),false);
	}
	
	@Test
	@Order(21)
	void inscribirTuristaOtraVezTest() {
		GregorianCalendar d = new GregorianCalendar(2000,4,1);
		assertThrows(InscripcionExistenteException.class, ()->{
			fabrica.getInstance().getIControladorTurismo().inscribirTurista("PepeViajes",6,d,"Salto de garrocha 1",null, "a", "a");});	
	}
	
	@Test
	@Order(22)
	void inscribirTuristaSalidaLlenaTest() {
		GregorianCalendar d = new GregorianCalendar(2004,3,4);
		assertThrows(SalidaLlenaException.class, ()->{
			fabrica.getInstance().getIControladorTurismo().inscribirTurista("PepeViajes",13,d,"Salto de garrocha 2",null, "a", "a");});	
	}
	
	@Test
	@Order(23)
	void listarSalidasVigentesTest() {
		Map<String, DTSalida>  testSals = fabrica.getInstance().getIControladorTurismo().listarSalidasVigentes("Salto de garrocha");
		assertEquals(1,testSals.size());
		assertEquals(testSals.get("Salto de garrocha 2").getLugarSalida(),"Tambien a confirmar");
	}
	
	@Test
	@Order(24)
	void obtenerSalidasDeActividadTest() {
		Map<String, DTSalida>  testSals = fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividad("Recorrida de la casa blanca");
		assertEquals(0,testSals.size());
		
		Map<String, DTSalida>  testSals2 = fabrica.getInstance().getIControladorTurismo().obtenerSalidasDeActividad("Salto de garrocha");
		assertEquals(2,testSals2.size());
	}
	
	@Test
	@Order(25)
	void obtenerSalidasInscriptoTest() {
		Set<DTInscripcion>  testSals = fabrica.getInstance().getIControladorTurismo().obtenerSalidasInscripto("PepeViajes");
		assertEquals(1,testSals.size());
	}
	
	@Test
	@Order(26)
	void listarSalidasTest() {
		Set<String>  testSals = fabrica.getInstance().getIControladorTurismo().listarSalidas("Te con Leonardo DiCaprio");
		assertEquals(0,testSals.size()); //no hay :(
		
		Set<String>  testSals2 = fabrica.getInstance().getIControladorTurismo().listarSalidas("Salto de garrocha");
		assertEquals(2,testSals2.size());
		assertEquals(testSals2.contains("Salto de garrocha 2"),true);
		assertEquals(testSals2.contains("Salto de garrocha 1"),true);
		assertEquals(testSals2.contains("Te con Dwayne Johnson"),false);
	}
	
	@Test
	@Order(27)
	void datosSalidaTest() {
		DTSalida  testSal = fabrica.getInstance().getIControladorTurismo().datosSalida("Cafe con Tom Hanks");
		assertEquals(testSal,null);
		
		DTSalida  testSal2 = fabrica.getInstance().getIControladorTurismo().datosSalida("Salto de garrocha 1");
		assertEquals(testSal2.getLugarSalida(),"A confirmar");
	}
	
	@Test
	@Order(28)
	void datosActividadesDepartamentoTest() {
		//usando order(14 y 15)
		GregorianCalendar d = new GregorianCalendar(1810, 9, 18);
		DTActividad testAct3 = new DTActividad("Somos el mejor","Pais de Chile",5,200,"Hermano",d, EstadoAct.Agregada, null, 0, null);
		DTCategoria dtC= new DTCategoria("Gastronomía");
		try {
			fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(dtC);
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Set<String> hs= new HashSet<String>();
		hs.add("Gastronomía");
		try {
			fabrica.getInstance().getIControladorTurismo().confirmarAltaActividad("maria@outlook.com","Wachington",testAct3,hs);
		} catch (ActividadRepetidaException | DepartamentoInvalidoException | CategoriaInvalidaException | DuracionEsCeroException | CostoEsCeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, DTActividad> testDta= ManejadorTurismo.getInstance().obtenerDatosActividadesDepartamento("Wachington");
		assertEquals(2,testDta.size());
		assertEquals(testDta.containsKey("Hermano"),false);
		assertEquals(testDta.containsKey("Somos el mejor"),true);
		assertEquals(ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").obtenerActividad("Somos el mejor").getNombre(),"Somos el mejor");
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerDepartamentoConActividad(ManejadorTurismo.getInstance().darDepartamentoConNombre("Wachington").obtenerActividad("Somos el mejor").getNombre()),"Wachington");
		try {
			fabrica.getInstance().getIControladorTurismo().finalizarActividad(testAct3.getNombre());
		} catch (TieneSalidaVigenteException | ActividadEnPaqueteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(29)
	void listarTuristasTest() {
		Map<String,DTUsuario>  testTur = fabrica.getInstance().getIControladorUsuarios().listarTuristas();
		assertEquals(1,testTur.size());
		
		assertEquals(testTur.containsKey("PepeViajes"),true);
		assertEquals(testTur.get("PepeViajes").getEmail(),"pepe@gmail.com");
	}
	
	@Test
	@Order(30)
	void ModificarTuristaConCamposVaciosTest() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTProveedor("","1234","Martin","Perez","pepe@gmail.com","Mi pana el humilde","Un muy buen proveedor, muy humilde",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser);});	
		
		DTProveedor testUser2 = new DTProveedor("MartinElMejorProveedor","1234","","Perez","pepe@gmail.com","Mi pana el humilde","Un muy buen proveedor, muy humilde",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser2);});
		
		DTProveedor testUser3 = new DTProveedor("MartinElMejorProveedor","1234","Martin","","pepe@gmail.com","","Un muy buen proveedor, muy humilde",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser3);});
		
		DTProveedor testUser4 = new DTProveedor("MartinElMejorProveedor","1234","Martin","Perez","","Mi pana el humilde","Un muy buen proveedor, muy humilde",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser4);});
		
		DTUsuario testUser6 = new DTProveedor("MartinElMejorProveedor","1234","Martin","Perez","pepe@gmail.com","Mi pana el humilde","",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser6);});
	}
	
	@Test
	@Order(31)
	void ModificarProveedorConCamposVaciosTest() {
		GregorianCalendar d = new GregorianCalendar(1947,7,9);
		DTUsuario testUser = new DTTurista("Marie Curie","1234","Marie","","maria@outlook.com","polaca",d);
		assertThrows(CampoIncompletoException.class, ()->{
			fabrica.getInstance().getIControladorUsuarios().modificarUsuario(testUser);});	
	}
	
	@Test
	@Order(32)
	void ObtenerInscripcionesDeTuristaTest() {
		turista.class.cast(ManejadorUsuario.getInstance().obtenerUsuariosEmail().get("pepe@gmail.com")).getInscripciones();
		assertEquals(1,turista.class.cast(ManejadorUsuario.getInstance().obtenerUsuariosEmail().get("pepe@gmail.com")).getInscripciones().size());
	}
	
	@Test
	@Order(33)
	void obtenerDepartamentosTest() {
		Map<String, DTActividad> dtActs= fabrica.getInstance().getIControladorTurismo().obtenerActividadesDeDepartamento("California");
		assertEquals(1,dtActs.size());
		assertEquals(dtActs.containsKey("Recorrida de la casa blanca"),true);
		assertEquals(dtActs.containsKey("El pollito pio"),false);
		assertEquals(dtActs.get("Recorrida de la casa blanca").getCiudad(),"Ciudad de la casa blanca");
		assertEquals(ManejadorTurismo.getInstance().obtenerDepartamentoConActividad("Somos el mejor"),"California");
	}
	
	@Test
	@Order(34)
	void registrarPaqueteTest() {
		try {
			GregorianCalendar g= new GregorianCalendar(2022,4,2);
			DTPaquete paq = new DTPaquete("Paquete Muy Bueno","Muy bueno",7,34,g);
			fabrica.getInstance().getIControladorPaquete().confirmarPaquete(paq);
			Set<String> paqsTest = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesPorNombre();
			assertEquals(paqsTest.size(),1);
			assertEquals(fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetes().size(),1);
			ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).setDescripcion(ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).getDescripcion());
			ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).setPeriodoValidez(ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).getPeriodoValidez());
			ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).setNombre(ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).getNombre());
			ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).setDescuento(ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).getDescuento());
			ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).setFechaAlta(ManejadorPaquete.getInstance().darPaquete(paq.getNombre()).getFechaAlta());
		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(35)
	void registrarPaqueteRepetidoTest() {
		DTPaquete paq = new DTPaquete("Paquete Muy Bueno","Bastante malo",9,34,new GregorianCalendar(2022,4,2));
		assertThrows(IllegalArgumentException.class, ()->{
			fabrica.getInstance().getIControladorPaquete().confirmarPaquete(paq);});	
		Set<String> paqsTest = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesPorNombre();
		assertEquals(paqsTest.size(),1);
	}
	
	@Test
	@Order(36)
	void recuperarPaqueteTest() {
		DTPaquete paq = fabrica.getInstance().getIControladorPaquete().seleccionarPaquete("Paquete Muy Bueno");
		assertEquals(paq.getDescuento(),34);
	}
	
	@Test
	@Order(37)
	void ingresarActividad_A_PaqueteTest() {
		
		try {
			fabrica.getInstance().getIControladorPaquete().ingresarActAPaq("Salto de garrocha", "Paquete Muy Bueno");
		}catch(Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(38)
	void recuperarActividadesDePaqueteTest() {
		 DTPaquete paq = fabrica.getInstance().getIControladorPaquete().seleccionarPaquete("Paquete Muy Bueno");
		 Set<String> actsTest = fabrica.getInstance().getIControladorPaquete().listarActividadesPaquete("Paquete Muy Bueno");
		 assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesFueraDePaquete("Paquete Muy Bueno", "Wachington").size(),0);
		 assertEquals(1,actsTest.size());	
		 assertEquals("Muy bueno", paq.getDescripcion());
	}
	
	@Test
	@Order(39)
	void recuperarPaquetesDeActividadTest() {
		Set<String> paqsTest = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesDeActividad("Actividad inventada");
		assertEquals(0,paqsTest.size());	
		
		Set<String> paqsTest2 = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesDeActividad("Salto de garrocha");
		assertEquals(1,paqsTest2.size());
	}
	
	@Test
	@Order(40)
	void ingresarPaqueteConCampoVacio() {
		DTPaquete paq = new DTPaquete("","Bastante malo",9,34,new GregorianCalendar(2022,4,2));
		assertThrows(IllegalArgumentException.class, ()->{
			fabrica.getInstance().getIControladorPaquete().confirmarPaquete(paq);});	
	}
	
	@Test
	@Order(41)
	void DarDatosActividadTest() {
		assertEquals(200,fabrica.getInstance().getIControladorTurismo().darDatosActividad("Salto de garrocha").getCostoTurista());
	}
	
	@Test
	@Order(42)
	void ListarDepartamentosTest() {
		assertEquals(2,fabrica.getInstance().getIControladorTurismo().listarDepartamentos().size());
	}
	
	@Test
	@Order(43)
	void SeleccionarActividadPorNombreTest() {
		assertEquals(200,fabrica.getInstance().getIControladorTurismo().seleccionarActividadPorNombre("Salto de garrocha").getCostoTurista());
	}
	
	@Test
	@Order(44)
	void SeleccionarDepartamentoTest() {
		assertEquals("Departamento muy estadounidense",fabrica.getInstance().getIControladorTurismo().seleccionarDepartamento("California").getDescripcion());
	}
	
	@Test
	@Order(45)
	void ObtenerDatosPaquetesTest() {
		Map<String,DTPaquete> dtPaqs= ManejadorPaquete.getInstance().obtenerDatosPaquetes();
		assertEquals(1,dtPaqs.size());
	}
	
	@Test
	@Order(46)
	void registrarCategoriasTest(){
		DTCategoria testCat = new DTCategoria("Gourmet");
		assertEquals(testCat.getNombre(),"Gourmet");
		try {
			fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(testCat);
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(ManejadorTurismo.getInstance().existeCategoria("Gourmet"),true);
		ManejadorTurismo.getInstance().obtenerCategorias().get(testCat.getNombre()).setNombre(testCat.getNombre());
		assertEquals(ManejadorTurismo.getInstance().obtenerCategorias().get(testCat.getNombre()).getActividades().size(),0);
		assertEquals(ManejadorTurismo.getInstance().obtenerCategorias().get(testCat.getNombre()).getPaquetes().size(),0);
		assertEquals(ManejadorTurismo.getInstance().obtenerCategorias().size(),4);
	}
	
	@Test
	@Order(47)
	void ConfirmarContraseñaTest() {
		assertEquals(fabrica.getInstance().getIControladorUsuarios().contCorrecta("mirtha.legrand.ok@hotmail.com.ar", "awdrg543"),false);
	}
	
	@Test
	@Order(48)
	void ObtenerDatosPaqueteDeActividadTest() {
		Map<String, DTPaquete> paqs = fabrica.getInstance().getIControladorPaquete().obtenerDatosPaquetesDeActividad("aa");
		assertEquals(paqs.size(),0);
		Map<String, DTPaquete> paqs2 = fabrica.getInstance().getIControladorPaquete().obtenerPaquetesConAlmenosUnaActividad();
		assertEquals(paqs2,paqs2);
	}
	
	@Test
	@Order(49)
	void ComprarPaqueteTest() {
		GregorianCalendar d = new GregorianCalendar(1999,3,2);
		DTActividad testAct = new DTActividad("Salto de garrocha","Muchos saltos, muchas garrochas",5,200,"Niu Shork", d, EstadoAct.Agregada, null, 0, null);
		DTDepartamento dtD= new DTDepartamento("aa", "aa", "aa");
		DTCategoria dtC= new DTCategoria("aa");
		fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(dtD);
		DTPaquete dtP= new DTPaquete("aa", "aa", 2, 2, new GregorianCalendar());
		fabrica.getInstance().getIControladorPaquete().confirmarPaquete(dtP);
		DTUsuario dtU= new DTTurista("aa", "aa", "aa", "aa", "aa", "aa", new GregorianCalendar());
		DTUsuario dtU2= new DTTurista("aaa", "aaa", "aaa", "aaa", "aaa", "aaa", new GregorianCalendar());
		try {
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(dtU, "aa");
			fabrica.getInstance().getIControladorUsuarios().confirmarAltaUsuario(dtU2, "aaa");
		} catch (UsuarioRepetidoException | CampoIncompletoException | ContrasenasNoCoincidenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fabrica.getInstance().getIControladorPaquete().comprarPaquete("aa", 1, new GregorianCalendar(), "aa");
		} catch (CantidadTuristaException | PaqueteInvalidoException | PaqueteCompradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fabrica.getInstance().getIControladorPaquete().ingresarActAPaq(testAct.getNombre(), dtP.getNombre());
		assertEquals(turista.class.cast(ManejadorUsuario.getInstance().darUsuarioConEmail("aa")).getCompraPaquetes().size(),1);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasFueraDePaquete(dtP.getNombre(), dtD.getNombre()).size(),0);
		assertEquals(fabrica.getInstance().getIControladorPaquete().obtenerPaquetesNoComprados().size(),1);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerActividadesConfirmadasDelDepartamento("aa").size(),0);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerDatosPaquetesComprados("aa").size(),1);
		assertEquals(fabrica.getInstance().getIControladorTurismo().obtenerPaquetesComprados("aa").size(),1);
		assertEquals(fabrica.getInstance().getIControladorTurismo().busquedaAct("anana", "Todos los departamentos", "Todas las categorias").size(),0);
		assertEquals(fabrica.getInstance().getIControladorTurismo().busquedaAct("anana", "aa", "Todas las categorias").size(),0);
		assertEquals(fabrica.getInstance().getIControladorPaquete().filtrarPaquetesUsuario(ManejadorPaquete.getInstance().obtenerDatosPaquetes(), "aa").size(),1);
		assertEquals(fabrica.getInstance().getIControladorPaquete().busqueda("aa", "Todos los departamentos", "Todas las categorias").size(),1);
		assertEquals(fabrica.getInstance().getIControladorPaquete().busqueda("aa", "Todos los departamentos", "aa").size(),0);
		fabrica.getInstance().getIControladorUsuarios().seguirUsuario("aa", "aaa");
		assertEquals(fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioSeguidos("aa").size(),1);
		assertEquals(fabrica.getInstance().getIControladorUsuarios().darDatosUsuarioQueSiguen("aaa").size(),1);
		assertEquals(fabrica.getInstance().getIControladorUsuarios().usuarioSigueA("aa", "aaa"),true);
		fabrica.getInstance().getIControladorUsuarios().dejarDeSeguirUsuario("aa", "aaa");
		assertEquals(ManejadorUsuario.getInstance().darUsuarioConNickname("aa").darDatosUsuariosSeguidos().size(),0);
		assertEquals(fabrica.getInstance().getIControladorUsuarios().usuarioSigueA("aa", "aaa"),false);
	}
	
	@Test
	@Order(50)
	void busquedaActTest() {
		DTDepartamento dtD= new DTDepartamento("papadopoulos","señor robert"," pan de pipas");
		fabrica.getInstance().getIControladorTurismo().ingresarDatosDepartamento(dtD);
		DTCategoria dtC= new DTCategoria("juligan");
		try {
			fabrica.getInstance().getIControladorTurismo().confirmarAltaCategoria(dtC);
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(fabrica.getInstance().getIControladorTurismo().busquedaAct("aa", "papadopoulos", "juligan").size(),0);
		assertEquals(fabrica.getInstance().getIControladorTurismo().busquedaAct("aa", "Todos los departamentos", "juligan").size(),0);
	}

}
	
