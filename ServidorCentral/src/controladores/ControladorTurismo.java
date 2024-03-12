package controladores;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import datatypes.DTActividad;
import datatypes.DTCategoria;
import datatypes.DTCompraPaquete;
import datatypes.DTDepartamento;
import datatypes.DTSalida;
import datatypes.EstadoAct;
import datatypes.DTInscripcion;
import helpers.ActividadEnPaqueteException;
import helpers.ActividadInvalidaException;
import helpers.ActividadRepetidaException;
import helpers.CantidadTuristaException;
import helpers.CategoriaInvalidaException;
import helpers.CategoriaRepetidaException;
import helpers.CostoEsCeroException;
import helpers.DepartamentoInvalidoException;
import helpers.DuracionEsCeroException;
import helpers.InscripcionExistenteException;
import helpers.PaqueteInvalidoException;
import helpers.SalidaInvalidaException;
import helpers.SalidaLlenaException;
import helpers.SalidaRepetidaException;
import helpers.TieneSalidaVigenteException;
import modelo.actividad;
import modelo.categoria;
import modelo.compraPaquete;
import modelo.departamento;
import modelo.inscripcion;
import modelo.proveedor;
import modelo.salida;
import modelo.turista;
import modelo.usuario;

public class ControladorTurismo implements IControladorTurismo {
	public ControladorTurismo() {
		
	}
	
	public Map<String, DTActividad> obtenerActividadesDeProveedor(String nick) {
		proveedor paq = (proveedor) ManejadorUsuario.getInstance().darUsuarioConNickname(nick);
		return paq.darActividades();
	}
	
	public Map<String, DTSalida> obtenerSalidasDeActividad(String nombreAct) {
		return ManejadorTurismo.getInstance().obtenerDatosSalidasParaActividad(nombreAct);
	} 
	
	public void finalizarActividad(String act) throws TieneSalidaVigenteException, ActividadEnPaqueteException {	
		actividad actBus = ManejadorTurismo.getInstance().darActividad(act);
		if (tieneSalidaVigente(act))
			throw new TieneSalidaVigenteException("La actividad a finalizar tiene una salida vigente");
		if (fabrica.getInstance().getIControladorPaquete().estaEnUnPaquete(act))
			throw new ActividadEnPaqueteException("La actividad a finalizar está en un paquete");
		
		actBus.finalizar();
		Map<String, departamento> deps = ManejadorTurismo.getInstance().obtenerDepartamentos();
		Map<String, categoria> cats = ManejadorTurismo.getInstance().obtenerCategorias();
		Map<String, usuario> users = ManejadorUsuario.getInstance().obtenerUsuariosNickname();
		Iterator<String> itDep = deps.keySet().iterator();
		Iterator<String> itCat = cats.keySet().iterator();
		Iterator<String> itUsr = users.keySet().iterator();
		String keyDep;
		String keyCat;
		String keyUsr;
		while (itDep.hasNext()) {
			keyDep = itDep.next();
			deps.get(keyDep).quitarAct(act);
		}
		while (itCat.hasNext()) {
			keyCat = itCat.next();
			cats.get(keyCat).quitarAct(act);
		}
		while (itUsr.hasNext()) {
			keyUsr = itUsr.next();
			if (proveedor.class.isInstance(users.get(keyUsr)))
				((proveedor) users.get(keyUsr)).quitarAct(act);
		}
			
	}
	
	public boolean tieneSalidaVigente(String act) {
		Map<String, salida> sals = ManejadorTurismo.getInstance().obtenerSalidas();
		Iterator<String> iter = sals.keySet().iterator();
		String key;
		boolean encontrado = false;
		while (iter.hasNext() && !encontrado) {
			key = iter.next();
			if (sals.get(key).tieneActividad(act) && sals.get(key).getFechaSalida().compareTo(new GregorianCalendar()) >= 0)
				encontrado = true;
		}
		return encontrado;
	}
	
	public Set<DTInscripcion> obtenerSalidasInscripto(String nick) {
		turista tur = (turista) ManejadorUsuario.getInstance().darUsuarioConNickname(nick);
		return tur.darSalidasInscripto();
	}
	
	public Set<DTCompraPaquete> obtenerDatosPaquetesComprados(String nick) {
		turista tur = (turista) ManejadorUsuario.getInstance().darUsuarioConNickname(nick);
		return tur.darDatosPaquetesComprados();
	}
	
	public Set<String> obtenerPaquetesComprados(String nick) {
		turista tur = (turista) ManejadorUsuario.getInstance().darUsuarioConNickname(nick);
		return tur.darPaquetesComprados();
	}
	
	public Map<String, DTActividad> busquedaAct(String query, String depto, String cat){
		if (depto.equals("Todos los departamentos") && cat.equals("Todas las categorias")) {
			Map<String, DTActividad> res = new HashMap<>();
			Map<String, departamento> deps = ManejadorTurismo.getInstance().obtenerDepartamentos();
			Map<String, DTActividad> aux = null;
			Iterator<String> iter = deps.keySet().iterator();
			String key;
			while (iter.hasNext()) {
				key = iter.next();
				aux = deps.get(key).obtenerActividadesQueContengan(query);
				res.putAll(aux);
			}
			return res;
		}
		else {
			Map<String, DTActividad> actsDep = null;
			Map<String, DTActividad> actsCat = null;
			
			if (!depto.equals("Todos los departamentos") && !cat.equals("Todas las categorias")) {				
				actsDep = ManejadorTurismo.getInstance().obtenerDatosActividadesConfDepartamento(depto);
				actsCat = ManejadorTurismo.getInstance().obtenerDatosActividadesConfCategoria(cat);
				actsDep.keySet().retainAll(actsCat.keySet());
				return actsDep;
			}
			else if (!depto.equals("Todos los departamentos") && cat.equals("Todas las categorias")) {
				actsDep = ManejadorTurismo.getInstance().obtenerDatosActividadesConfDepartamento(depto);
				return actsDep;
			}
			else {
				actsCat = ManejadorTurismo.getInstance().obtenerDatosActividadesConfCategoria(cat);
				return actsCat;
			}
		}		
	}
	
	
	public Map<String, DTActividad> obtenerActividades() {
		Map<String, departamento> deps = ManejadorTurismo.getInstance().obtenerDepartamentos();
		Map<String, DTActividad> resultado = new HashMap<String, DTActividad>();
		Iterator<String> iter = deps.keySet().iterator();
		String key;
		
		while (iter.hasNext()) {
			key = iter.next();
			resultado.putAll(deps.get(key).darDatosActividades());
		}
		
		return resultado;
	}
	
	public void confirmarAltaCategoria(DTCategoria dtC) throws CategoriaRepetidaException {
		if (ManejadorTurismo.getInstance().existeCategoria(dtC.getNombre())) {
			throw new CategoriaRepetidaException("Ya existe una categoria con el nombre ingresado");
		}
		categoria cat = new categoria(dtC.getNombre());
		ManejadorTurismo.getInstance().agregarCategoria(cat);
	}
	
	public void confirmarAltaActividad(String emailUser, String nomDpto, DTActividad dtA, Set<String> cats) throws ActividadRepetidaException, DepartamentoInvalidoException, CategoriaInvalidaException, DuracionEsCeroException, CostoEsCeroException {
		if (ManejadorTurismo.getInstance().existeActividad(dtA.getNombre())) {
			throw new ActividadRepetidaException("Ya existe una actividad con el nombre ingresado");
		}
		if (nomDpto.equals("Departamentos")) {
			throw new DepartamentoInvalidoException();
		}				
		if (cats == null || cats.isEmpty()) {
			throw new CategoriaInvalidaException();
		}			
		if (ManejadorTurismo.getInstance().existeActividad(dtA.getNombre())) {
			throw new ActividadRepetidaException();
		}
		if (dtA.getDuracion()<=0) {
			throw new DuracionEsCeroException();
		}
		if (dtA.getCostoTurista() <= 0) {
			throw new CostoEsCeroException();
		}
		
		actividad act = new actividad(dtA.getNombre(), dtA.getDescripcion(), dtA.getDuracion(), dtA.getCostoTurista(), dtA.getCiudad(), dtA.getFechaAlta(), dtA.getUrlVideo(), dtA.getCantTuristasFavoritos() );
		ManejadorTurismo.getInstance().darDepartamentoConNombre(nomDpto).agregarActividad(act);
		proveedor.class.cast(ManejadorUsuario.getInstance().darUsuarioConEmail(emailUser)).agregarActividad(act);
		Iterator<String> iter= cats.iterator();
		Map<String, categoria> categorias= ManejadorTurismo.getInstance().obtenerCategorias();
		String key;
		while (iter.hasNext()) {
			key=iter.next();
			categorias.get(key).agregarActividad(act);
		}
	}


	//alta Salida
	public Set<String> listarActividades(String departamento) {
		Set<String> actividades = ManejadorTurismo.getInstance().obtenerActividadesDentroDeDepartamento(departamento);
		return actividades;
	}

	public void ingresarSalida(String actividad, DTSalida salida_datos) throws SalidaRepetidaException, ActividadInvalidaException, CantidadTuristaException {
		//si existe salida con ese nombre
		if (ManejadorTurismo.getInstance().existeSalida(salida_datos.getNombre())) {
			throw new SalidaRepetidaException("Ya existe salida con ese nombre");	
		}
		
		//si alguien llama buscar actividad con cualquier cosa
		if (actividad == null || actividad.equals("Actividades")) {
			throw new ActividadInvalidaException("No se encontro actividad con ese nombre");
		}
		
		if (salida_datos.getCantMax()<=0) {
			throw new CantidadTuristaException("La cantidad máxima de turistas debe ser mayor a 0");
		}
		actividad actividadAsociada = 	ManejadorTurismo.getInstance().darActividad(actividad);

		salida salida_obj = new salida(salida_datos.getNombre(), salida_datos.getCantMax(), salida_datos.getCantInscriptos(), salida_datos.getFechaAlta(), salida_datos.getFechaSalida(), actividadAsociada, salida_datos.getLugarSalida());
		ManejadorTurismo.getInstance().agregarSalida(salida_obj);
		salida_obj.setActividadAsoc(actividadAsociada);
	}
	
	public Map<String, DTActividad> obtenerActividadesDeDepartamento(String nombreDepartamento) {
		Map<String, DTActividad> res = ManejadorTurismo.getInstance().obtenerDatosActividadesDepartamento(nombreDepartamento);
		return res;
	}

	//*****************consulta salida*********************

	public Set<String> listarSalidas(String actividad) {
		Set<String> salidas = ManejadorTurismo.getInstance().obtenerSalidasParaActividad(actividad);
		return salidas;
	}
	
	public DTSalida datosSalida(String nombreSalida) {
		DTSalida res = ManejadorTurismo.getInstance().darDatosSalida(nombreSalida);
		return res;
	}

	//****************Alta Departamento**********************

	public void ingresarDatosDepartamento(DTDepartamento departamento) {
		//si existe departamento con ese nombre
		if (ManejadorTurismo.getInstance().existeDepartamento(departamento.getNombre())) {
			throw new IllegalArgumentException("Ya existe departamento con ese nombre");
		}
		departamento departamento_obj = new departamento(departamento.getNombre(), departamento.getDescripcion(), departamento.getUrl());
		ManejadorTurismo.getInstance().agregarDepartamento(departamento_obj);
	}

	public Map<String, DTDepartamento> obtenerDepartamentos() {
		Map<String, DTDepartamento> res = ManejadorTurismo.getInstance().obtenerDatosDepartamentos();
		return res;
	}


	//***************Inscripcion Salida***********************

	public Map<String, DTSalida> listarSalidasVigentes(String actividad) {
		Map<String, DTSalida> res = ManejadorTurismo.getInstance().salidasVigentes(actividad);
		return res;
	}

	public void inscribirTurista(String nickname, int turistas, GregorianCalendar fechaAlta, String nombreSalida, String nombrePaquete, String nombreDepartamento, String nombreActividad) throws InscripcionExistenteException, SalidaLlenaException, SalidaInvalidaException, ActividadInvalidaException, PaqueteInvalidoException, DepartamentoInvalidoException, CantidadTuristaException {
		usuario usr = ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);

		if (nombreDepartamento == null || nombreDepartamento.equals("Departamentos"))
			throw new DepartamentoInvalidoException("El departamento no es valido");
		if (nombreActividad == null || nombreActividad.equals("Actividades"))
			throw new ActividadInvalidaException("La actividad no es valida");	
		if (nombreSalida == null || nombreSalida.equals("Salidas"))
			throw new SalidaInvalidaException("La salida no es valida");
		if (turistas<=0) {
			throw new CantidadTuristaException("Ingresa una cantidad de turista mayor a cero");
		}
		if (proveedor.class.isInstance(usr)) {
			throw new IllegalArgumentException("Ingresó Nickname de proveedor");
		} else {
			turista turista_obj = turista.class.cast(usr);
			if (turista_obj.existeSalida(nombreSalida))
				throw new InscripcionExistenteException("Ya existe registro para ese turista");
			salida salida_obj = ManejadorTurismo.getInstance().obtenerSalida(nombreSalida);
			if (salida_obj.salidaLlena(turistas))
				throw new SalidaLlenaException("La cantidad de turistas excede la cantidad maxima"); //cant inscriptos INCLUYE al turisa del sistema
			inscripcion inscripcion_obj;
			if (nombrePaquete == null || nombrePaquete.equals("Paquetes")) {//es inscripcion por paquete
				inscripcion_obj = new inscripcion(fechaAlta, salida_obj.obtenerCostoInscripcion(turistas), turistas, turista_obj, salida_obj, null);
			}else {
				compraPaquete compra = fabrica.getInstance().getIControladorUsuarios().obtenerCompraDelPaqueteEnTurista(nickname, nombrePaquete);
				if (compra!=null && turistas>compra.getCantTuristas()) {
					throw new CantidadTuristaException("La cantidad de turístas es mayor que los turistas del paquete comprado");
				}
				float resultado= salida_obj.obtenerCostoInscripcion(turistas)*(1-(ManejadorPaquete.getInstance().darPaquete(nombrePaquete).getDescuento()/100));				
				inscripcion_obj = new inscripcion(fechaAlta, resultado , turistas, turista_obj, salida_obj, compra);
			}
			salida_obj.agregarInscripcion(inscripcion_obj);
			turista_obj.agregarInscripcion(inscripcion_obj);
		}
	}
	
	public Map<String, DTCategoria> obtenerCategorias() {
		Map<String, DTCategoria> res = ManejadorTurismo.getInstance().obtenerDatosCategorias();
		return res;
	}
	
	public DTActividad darDatosActividad(String nombreAct) {
		return ManejadorTurismo.getInstance().darActividad(nombreAct).darDatos();
	}
	
	public Set<String> listarDepartamentos() {
		Set<String> res = new HashSet<String>();
		for (departamento dep : ManejadorTurismo.getInstance().obtenerDepartamentos().values()) {
			res.add(dep.getNombre());
		}
		return res;
	}

	public DTDepartamento seleccionarDepartamento(String nombre) {
		departamento departamento_obj = ManejadorTurismo.getInstance().darDepartamentoConNombre(nombre);
		DTDepartamento departamento_dt = new DTDepartamento(departamento_obj.getNombre(), departamento_obj.getDescripcion(), departamento_obj.getUrl());
		return departamento_dt;
	}

	public Set<String> obtenerActividadesFueraDePaquete(String paq, String dpto) {
		Set<String> res = ManejadorTurismo.getInstance().darDepartamentoConNombre(dpto).darNombresActividades();
		Set<String> act_paq = ManejadorPaquete.getInstance().darPaquete(paq).listarActividades();
		for (String a : act_paq) {
			res.remove(a);
		}		
		return res;
	}
	
	public Set<String> obtenerActividadesConfirmadasFueraDePaquete(String paq, String dpto) {
		Set<String> res = ManejadorTurismo.getInstance().darDepartamentoConNombre(dpto).darNombresActividades();
		Set<String> resultado= new HashSet<String>();
		Set<String> act_paq = ManejadorPaquete.getInstance().darPaquete(paq).listarActividades();
		for (String a : act_paq) {
			res.remove(a);
		}		
		for (String a : res) {
			if (ManejadorTurismo.getInstance().darActividad(a).getEstado()==EstadoAct.Confirmada) {
				resultado.add(a);
			}
		}
		return resultado;
	}

	public DTActividad seleccionarActividadPorNombre(String nombre) {
		actividad actividad_obj = ManejadorTurismo.getInstance().darActividad(nombre);
		DTActividad actividad_dt = actividad_obj.darDatos();
		return actividad_dt;
	}
	
	public String obtenerDepartamentoConActividad(String nombreAct) {
		return ManejadorTurismo.getInstance().obtenerDepartamentoConActividad(nombreAct);
	}
	
	public Set<String> obtenerCategoriasDeActividad(String nombreAct) {
		return ManejadorTurismo.getInstance().obtenerCategoriasDeActividad(nombreAct);
	}
	
	public String obtenerActividadDeSalida(String nombreSalida) {
		return ManejadorTurismo.getInstance().obtenerSalida(nombreSalida).getActividadAsoc().getNombre();
	}

	// Aceptar/rechazar actividad turistica

	public Set<String> listarActividadesAgregadas() {
		Set<String> actividadesAgregadas = ManejadorTurismo.getInstance().listarActividadesAgregadas();
		return actividadesAgregadas;
	}

	public void cambiarEstadoActividad(String actividad, boolean confirmar) {
		ManejadorTurismo.getInstance().cambiarEstadoActividad(actividad, confirmar);
	}
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDelDepartamento(String depto){
		Map<String, DTActividad> dta= ManejadorTurismo.getInstance().obtenerDatosActividadesDepartamento(depto);
		Map<String, DTActividad> resultado= new HashMap<String, DTActividad>();
		if (!dta.isEmpty()) {
			Iterator<String> iter = dta.keySet().iterator();
			String key;
			while (iter.hasNext()) {
				key=iter.next();
				if ( ManejadorTurismo.getInstance().darActividad(key).getEstado()==EstadoAct.Confirmada){
					resultado.put(key, dta.get(key));
				}
			}
		}
		return resultado;
	}
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDeCategoria(String cat){
		Map<String, DTActividad> dta= ManejadorTurismo.getInstance().obtenerDatosActividadesCategoria(cat);
		Map<String, DTActividad> resultado= new HashMap<String, DTActividad>();
		if (!dta.isEmpty()) {
			Iterator<String> iter = dta.keySet().iterator();
			String key;
			while (iter.hasNext()) {
				key=iter.next();
				if (ManejadorTurismo.getInstance().darActividad(key).getEstado() == EstadoAct.Confirmada){
					resultado.put(key, dta.get(key));
				}
			}
		}
		return resultado;
	}
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDeProveedor(String nick){
		return ((proveedor) ManejadorUsuario.getInstance().darUsuarioConNickname(nick)).obtenerActividadesConfirmadas();
	}

	public Map<String, DTSalida> obtenerSalidasDeActividades(Map<String, DTActividad> acts){
		Map<String, DTSalida> resultado = new HashMap<String, DTSalida>();	
		if (acts!=null){
			Map<String, DTSalida> aux;	
			Iterator<String> itAct = acts.keySet().iterator();
			Iterator<String> itAct2;
			String key = null;
			String key2 = null;
			while (itAct.hasNext()) {
				key = itAct.next();
				aux = obtenerSalidasDeActividad(key);
				itAct2 = aux.keySet().iterator();
				while (itAct2.hasNext()) {
					key2 = itAct2.next();
					if (!resultado.containsKey(key2))
						resultado.put(key2, aux.get(key2));
				}			
			}
		}
		return resultado;
	}
	
	public String darNombreActSal(String salida) {
		return  ManejadorTurismo.getInstance().obtenerSalida(salida).getActividadAsoc().getNombre();
	}
	
	public String darDepSal(String salida) {
		salida salida_obj = ManejadorTurismo.getInstance().obtenerSalida(salida);
		String nomActividad = salida_obj.getActividadAsoc().getNombre();
		String nomDepartamento = ManejadorTurismo.getInstance().obtenerDepartamentoConActividad(nomActividad);
		return nomDepartamento;
	}

	public String[] departamentoActividadDeSalida(String salida) {
		salida salida_obj = ManejadorTurismo.getInstance().obtenerSalida(salida);
		String nomActividad = salida_obj.getActividadAsoc().getNombre();
		String nomDepartamento = ManejadorTurismo.getInstance().obtenerDepartamentoConActividad(nomActividad);
		String[] res = {nomDepartamento, nomActividad};

		return res;
	}
	
	public Map<String, DTActividad> obtenerActividadesConfirmadasDelProveedorSinSalidasVigentes(String nick){
		Map<String, DTActividad> resultado= this.obtenerActividadesConfirmadasDeProveedor(nick);
		Iterator<String> iter = resultado.keySet().iterator();
		String key;
		while (iter.hasNext()) {
			key=iter.next();
			Set<String> sal= fabrica.getInstance().getIControladorTurismo().listarSalidas(key);
			Iterator<String> it2= sal.iterator();
			String key2;
			while (it2.hasNext()) {
				key2= it2.next();
				GregorianCalendar fechaActual= new GregorianCalendar();
				if (ManejadorTurismo.getInstance().obtenerSalida(key2).getFechaSalida().compareTo(fechaActual)>0) {
					resultado.remove(key);
				}
			}
		}
		return resultado;
	}
	
	
	public void agregarActividadFavorita(String nickname, String actividad) {
		turista tur= (turista) ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);
		if (!tur.getActividadesFavoritas().contains(actividad)) {
			ManejadorTurismo.getInstance().darActividad(actividad).aumentarCantTuristasFavoritos();
		}
		tur.agregarActividadFavorita(actividad);
	}
	
	public void quitarActividadFavorita(String nickname, String actividad) {
		turista tur= (turista) ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);
		if (tur.getActividadesFavoritas().contains(actividad)) {
			ManejadorTurismo.getInstance().darActividad(actividad).disminuirCantTuristasFavoritos();
		}
		tur.quitarActividadFavorita(actividad);
	}
	
	public int cantTuristasFavoritos(String actividad) {
		return ManejadorTurismo.getInstance().darActividad(actividad).getCantTuristasFavoritos();
	}
	
	public Set<String> obtenerActividadesFavoritas(String nickname, Map<String, DTActividad> actividades){
		Set<String> resultado= new HashSet<String>();
		usuario user= ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);
		if (turista.class.isInstance(user)) {
			Set<String> actividadesFavoritas= turista.class.cast(user).getActividadesFavoritas();
			if (!actividadesFavoritas.isEmpty() && !actividades.isEmpty()) {
				Iterator<String> iter = actividadesFavoritas.iterator();
				String key;
				while (iter.hasNext()) {
					key= iter.next();
					if (actividades.containsKey(key)) {
						resultado.add(key);
					}
				}
			}
		}	
		return resultado;
	}
	
	public Boolean esFavorito(String nickname, String actividad) {
		usuario user= ManejadorUsuario.getInstance().darUsuarioConNickname(nickname);
		Boolean resultado=false;
		if (turista.class.isInstance(user) && actividad!=null) {
			resultado= turista.class.cast(user).getActividadesFavoritas().contains(actividad);
		}
		return resultado;
	}
	
	public Set<DTActividad> obtenerActividadesFinalizadasDeProv(String nick){
		return ((proveedor) ManejadorUsuario.getInstance().darUsuarioConNickname(nick)).obtenerDatosActividadesFinalizadas();
	}
}
