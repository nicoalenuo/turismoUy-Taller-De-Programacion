<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>
	
	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="servidorusuarios.DtProveedor" %>
	<%@ page import="servidorusuarios.DtTurista" %>
	<%@ page import="servidorusuarios.DtActividad" %>
	<%@ page import="servidorusuarios.DtInscripcion" %>
	<%@ page import="servidorusuarios.SetOfDTInscripcion" %>
	<%@ page import="servidorusuarios.SetOfDTCompraPaquete" %>
	<%@ page import="servidorusuarios.StringDTUsuarioMap" %>
	<%@ page import="servidorusuarios.StringDTActividadMap" %>
	<%@ page import="servidorusuarios.StringDTSalidaMap" %>
	<%@ page import="servidorusuarios.DtCompraPaquete" %>
	<%@ page import="servidorusuarios.DtSalida" %>
	<%@ page import="servidorusuarios.EstadoAct" %>
	<%@ page import="servidorusuarios.EstadoAct" %>
	<%@ page import="servidoractividades.SetOfDTActividad" %>
	<%@ page import="java.util.*" %>
	
	<%
	DtUsuario dtu = (DtUsuario) request.getAttribute("usuarioConsulta");
	String id = (String) dtu.getNickname();
	GregorianCalendar gc = dtu.getFechaNac().toGregorianCalendar();
	String fechaNac = Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc.get(GregorianCalendar.YEAR));
	List<DtInscripcion> salidasInscripto = null;
	List<DtCompraPaquete> paquetesComprados = null;
	StringDTActividadMap actividadesProvistas = null;
	StringDTSalidaMap salidasParaActsProvistas = null;
	SetOfDTActividad actividadesFinalizadas = null;
	StringDTUsuarioMap usuariosSeguidos = (StringDTUsuarioMap) request.getAttribute("usuariosSeguidos");
	StringDTUsuarioMap usuariosQueSiguen = (StringDTUsuarioMap) request.getAttribute("usuariosQueSiguen");
	String error = (String) request.getAttribute("error");
	
	boolean seguible = (boolean) request.getAttribute("seguible");
	boolean yaLoSigue = (boolean) request.getAttribute("yaLoSigue");
	
	if (DtTurista.class.isInstance(dtu)){
		SetOfDTInscripcion salidasInscriptoSet = (SetOfDTInscripcion) request.getAttribute("salidasInscripto");
		salidasInscripto = salidasInscriptoSet.getItem();
		if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){
			SetOfDTCompraPaquete paquetesCompradosSet = (SetOfDTCompraPaquete) request.getAttribute("paquetesComprados");
			paquetesComprados = paquetesCompradosSet.getItem();
		}
	} else {
		actividadesProvistas = (StringDTActividadMap) request.getAttribute("actividadesProvistas");
		salidasParaActsProvistas = (StringDTSalidaMap) request.getAttribute("salidasParaActsProvistas");
		actividadesFinalizadas = (SetOfDTActividad) request.getAttribute("actividadesFinalizadas");
	}
	
	
	%>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

  <style>
    @media (max-width: 400px) {  
      #achicar{
        font-size:11px;
      }
    }
  </style>

  <link rel="stylesheet" href="media/css/imgChooser.css">
  <link rel="stylesheet" href="media/css/bootstrap-datepicker.css">

</head>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid"  style="min-height: 1000px;">
    <div class="row">
      
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>
      
      <div class="col py-4">

        <div class="row border-bottom mb-3" style="max-width: 80rem">
          <div class="col">
              <h2>Perfil</h2>
          </div>
        </div>
        <div class="row mx-3 flex-column flex-md-row">
            <img src="imagenes?tipo=usuario&id=<%= dtu.getNickname() %>" class="card-img mr-3 mb-3" style="max-height: 300px; max-width: 400px;">
            <div class="col">
                <h3><%=dtu.getNombre()+" " %><%=dtu.getApellido() %></h3>
                <label><%=dtu.getNickname() %> /<span>  <%=dtu.getEmail() %> </span></label>
                <% if (seguible) { %>
                <form action="consultaUsuario" method="POST">
                	<% if (!yaLoSigue){ %>
                	<input type="hidden" name="usuarioSeguir" value="<%=dtu.getNickname()%>">
                	<button type="submit" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">Seguir</button>
                	<% } else { %>
                	<input type="hidden" name="usuarioDejarDeSeguir" value="<%=dtu.getNickname()%>">
                	<button type="submit" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">Dejar de seguir</button>
                	<% } %>
                </form>
                <% } %>
            </div>
            
        </div>
        
        	<% if (error!=null && error.equals("errorContraseña")){ %>                            
            <small class="form-text text-black-50 ml-2" style="font-size: 12px">Error al modificar usuario: La contraseña ingresada no coincide con la confirmación</small>  
            <% } %>
            <% if (error!=null && error.equals("actEnPaquete")){ %>                            
            <small class="form-text text-black-50 ml-2" style="font-size: 12px">Error al finalizar actividad: La actividad forma parte de al menos un paquete</small>  
            <% } %>
            <% if (error!=null && error.equals("salidaVigente")){ %>                            
            <small class="form-text text-black-50 ml-2" style="font-size: 12px">Error al finalizar actividad: La actividad tiene al menos una salida vigente</small>  
            <% } %>
        
        <div class="card text-center ml-3 mt-3" style="max-width: 800px;">
            <div class="card-header">
              <nav class="nav nav-pills card-header-pills flex-column flex-md-row">
                    <a class="nav-link active" id="linkD" href="#perfil" data-toggle="tab">Perfil</a>
                    <a class="nav-link" id="linkD" href="#usuariosSeguidos" data-toggle="tab">Usuarios seguidos</a>
                    <a class="nav-link" id="linkD" href="#usuariosQueSiguenA" data-toggle="tab">Usuarios que le siguen</a>
                     <%if (DtTurista.class.isInstance(dtu)){ %>
                    <a class="nav-link" id="linkD" href="#salidas" data-toggle="tab">Salidas inscripto</a>        
                        <%if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
                    	<a class="nav-link" id="linkD" href="#paquetes" data-toggle="tab">Paquetes comprados</a>
                    	<a class="nav-link" id="linkD" href="#editar" data-toggle="tab">Editar datos</a>
                    	
                    	<%}%>                               
                    <%}else{%>
                    	<a class="nav-link" id="linkD" href="#actividadesConf" data-toggle="tab">Actividades confirmadas</a>
                    	<%if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
	                    	<a class="nav-link" id="linkD" href="#actividadesNoConf" data-toggle="tab">Actividades no confirmadas</a>
	                    	<a class="nav-link" id="linkD" href="#actividadesFin" data-toggle="tab">Actividades finalizadas</a>
	                    	<a class="nav-link" id="linkD" href="#salidasAct" data-toggle="tab">Salidas para las actividades</a>
	                    	<a class="nav-link" id="linkD" href="#editar" data-toggle="tab">Editar datos</a>
	                    <%} else {%>
	                    	<a class="nav-link" id="linkD" href="#salidasAct" data-toggle="tab">Salidas para las actividades</a>
                    		<a class="nav-link" id="linkD" href="#editar" data-toggle="tab">Editar datos</a>
	                    <% } %>
	                    
                    <%}%>
                    
                    
              </nav>
            </div>
           
            
            <div class="tab-content py-4">
                <div class="tab-pane align-left active" id="perfil">
                    <h5 class="card-title">Nickname</h5>
                    <label><%=dtu.getNickname() %></label>
                    <h5 class="card-title">Nombre</h5>
                    <label><%=dtu.getNombre() %></label>
                    <h5 class="card-title">Apellido</h5>
                    <label><%=dtu.getApellido() %></label>
                    <h5 class="card-title">Email</h5>
                    <label><%=dtu.getEmail() %></label>
                    <h5 class="card-title">Fecha de nacimiento</h5>
                    <label><%=fechaNac %></label>
                    <% if(DtTurista.class.isInstance(dtu)){
                       	  DtTurista dtt = DtTurista.class.cast(dtu);
                    %>
                    <h5 class="card-title">Nacionalidad</h5>
                    <label><%=dtt.getNacionalidad() %></label>
                    <% }else{
                          DtProveedor dtp = DtProveedor.class.cast(dtu);
                    %>
                    <h5 class="card-title">Descripción</h5>
                    <label><%=dtp.getDescripcion() %></label>
                    <h5 class="card-title">Sitio web</h5>
                    <label><%=dtp.getSitioWeb() %></label>            
                    <% } %>
                </div>
                
                <% if (DtTurista.class.isInstance(dtu)){ %>
                
                <div class="tab-pane" id="salidas">
                  <table class="table table-sm">
                    <thead>
                        <tr>
                            <th>Salida</th>
                            <%if (session.getAttribute("datosUsuarioSesion") != null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
                            <th>Cant insc</th>
                            <th>Costo</th>
                            <th>Fecha insc</th>
                            <th>Paquete</th>
                            <th>PDF</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                    	<%
                      	Iterator<DtInscripcion> it = salidasInscripto.iterator();
              			DtInscripcion key;
              			while (it.hasNext()) {
              				key = it.next();
              				GregorianCalendar gc2 = key.getFechaInscripcion().toGregorianCalendar();
              				String fechaInsc = Integer.toString(gc2.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc2.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc2.get(GregorianCalendar.YEAR));

                      %>
                    
                      <tr>
                        <td><a href="consultaSalida?id=<%=key.getNombreSalida() %>" style="color:rgb(220, 53, 69)" id="achicar"><%=key.getNombreSalida() %></a></td>
                        <%if (session.getAttribute("datosUsuarioSesion") != null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
                        <td id="achicar"><%=key.getInscriptos() %></td>
                        <td id="achicar"><%=key.getCosto() %></td>
                        <td id="achicar"><%=fechaInsc %></td>
                        <% if (key.getNombrePaquete() != null){ %>
                        <td><a href="consultaPaquete?id=<%=key.getNombrePaquete() %>" style="color:rgb(220, 53, 69)" id="achicar"><%=key.getNombrePaquete() %></a></td>
                        <% } else { %>
                        <td id="achicar">General</td>
                        <% }
                        
                        %>
                        
                        <td>
                        <form action="descargaPDF" method="POST">
                        <input type="hidden" name="id" value="<%=((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname()%>">
                        <input type="hidden" name="salida" value="<%=key.getNombreSalida()%>">
                        <input type="hidden" name="cant" value="<%=key.getInscriptos() %>">
                        <input type="hidden" name="fecha" value="<%=fechaInsc %>">
                        <button type="submit" style="color:rgb(220, 53, 69); border:none; background:none">x</button>
                        </form>
                        </td>
                        <%}%>
                      </tr>
                      
					<% } %>
                    
                   
                    </tbody>
                  </table>    
                </div>
                
                <%if (session.getAttribute("datosUsuarioSesion") != null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
                
                <div class="tab-pane" id="paquetes">
                  <table class="table table-sm">
                    <thead>
                        <tr>
                            <th>Paquete</th>
                            <th>Cant turistas</th>
                            <th>Fecha compra</th>
                        </tr>
                    </thead>
                    <tbody>
                      <tbody>
                      
                      <%
                      	Iterator<DtCompraPaquete> it2 = paquetesComprados.iterator();
              			DtCompraPaquete key2;
              			while (it2.hasNext()) {
              				key2 = it2.next();
              				GregorianCalendar gc3 = key2.getFechaCompra().toGregorianCalendar();
              				String fechaCompra = Integer.toString(gc3.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc3.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc3.get(GregorianCalendar.YEAR));

                      %>     
                      <tr>
                        <td><a href="consultaPaquete?id=<%=key2.getNomPaquete() %>" style="color:rgb(220, 53, 69)"><%=key2.getNomPaquete() %></a></td>
                        <td><%=key2.getCantTuristas() %></td>
                        <td><%=fechaCompra %></td>
                      </tr>
                      
					<% } %>
                    </tbody>

                  </table>
                </div>
                <% }
                
                 } else { %>
				
				
					<div class="tab-pane" id="actividadesConf">
	                  <table class="table table-sm">
	                    <thead>
	                        <tr>
	                            <th>Actividad</th>
	                            <th>Duracion</th>
	                            <th>Costo</th>
	                            <th>Ciudad</th>
	                            <%if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){  %>
	                        	<th>Finalizar</th>
	                       		<% } %>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    
	                    <%
	                    List<StringDTActividadMap.Entry> acts = actividadesProvistas.getEntry();
                      	Iterator<StringDTActividadMap.Entry> it = acts.iterator();
              			StringDTActividadMap.Entry key;
              			DtActividad act = null;
              			while (it.hasNext()) {
              				key = it.next();
              				act = key.getValue();
              				if (act.getEstado() == EstadoAct.CONFIRMADA){
                     	 %>
	                      <tr>
	                        <td><a href="consultaActividad?id=<%=act.getNombre()%>" style="color:rgb(220, 53, 69)"><%=act.getNombre() %></a></td>
	                        <td><%=act.getDuracion() %> días</td>
	                        <td>$<%=act.getCostoTurista() %></td>
	                        <td><%=act.getCiudad() %></td>
	                        <%if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){  %>
	                        <td>
		                        <form action="finalizarActividad" method="POST" accept-charset="utf-8">
		                        	<input type="hidden" name="actividadCancelar" value="<%=act.getNombre()%>">
		                        	<button type="submit" style="border:none; background:none">x</button>
		                        </form>
	                        </td>
	                        <% } %>
	                      </tr>                   
	                      <% } 
	                      } %>
	                    </tbody>
	                  </table>    
	                </div>
	                
						
				
					<%if (session.getAttribute("datosUsuarioSesion")!=null && id.equals(((DtUsuario) session.getAttribute("datosUsuarioSesion")).getNickname())){ %>
						<div class="tab-pane" id="actividadesNoConf">
		                  <table class="table table-sm">
		                    <thead>
		                        <tr>
		                            <th>Actividad</th>
		                            <th>Duracion</th>
		                            <th>Costo</th>
		                            <th>Ciudad</th>
		                            <th>Estado</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    
		                    <% 
			                    List<StringDTActividadMap.Entry> acts2 = actividadesProvistas.getEntry();
		                      	Iterator<StringDTActividadMap.Entry> it2 = acts.iterator();
		              			StringDTActividadMap.Entry key2;
		              			DtActividad act2 = null;
		              			while (it2.hasNext()) {
		              				key2 = it2.next();
		              				act2 = key2.getValue();
		              				if (act2.getEstado() == EstadoAct.AGREGADA || act2.getEstado() == EstadoAct.RECHAZADA){
		                     	 %>
			                      <tr>
			                        <td><a href="consultaActividad?id=<%=act2.getNombre() %>" style="color:rgb(220, 53, 69)"><%=act2.getNombre() %></a></td>
			                        <td><%=act2.getDuracion() %> días</td>
			                        <td>$<%=act2.getCostoTurista() %></td>
			                        <td><%=act2.getCiudad() %></td>
			                        <td><%=act2.getEstado() %></td>
			                      </tr>
			                      
			                      <% } 
			                      } %>
		                    </tbody>
		                  </table>    
		                </div>
		                <div class="tab-pane" id="actividadesFin">
		                  <table class="table table-sm">
		                    <thead>
		                        <tr>
		                            <th>Actividad</th>
		                            <th>Duracion</th>
		                            <th>Costo</th>
		                            <th>Ciudad</th>
		                            <th>Fecha finalizada</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    
		                    <% 
			                    List<servidoractividades.DtActividad> acts3 = actividadesFinalizadas.getItem();
		                      	Iterator<servidoractividades.DtActividad> itFin = acts3.iterator();
		                      	servidoractividades.DtActividad keyFin;	                      	
		              			while (itFin.hasNext()) {
		              				keyFin = itFin.next();
		              				GregorianCalendar gcFin = keyFin.getFechaFinalizada().toGregorianCalendar();
		              				String fechaFin = Integer.toString(gcFin.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gcFin.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gcFin.get(GregorianCalendar.YEAR));

		                     	 %>
			                      <tr>
			                        <td><%=keyFin.getNombre() %></td>
			                        <td><%=keyFin.getDuracion() %> días</td>
			                        <td>$<%=keyFin.getCostoTurista() %></td>
			                        <td><%=keyFin.getCiudad() %></td>
			                        <td><%=fechaFin %></td>
			                      </tr>
			                      
			                      <% }  %>
		                    </tbody>
		                  </table>    
		                </div>
						
					<% } %>
					
					<div class="tab-pane" id="salidasAct">
	                  <table class="table table-sm">
	                    <thead>
	                        <tr>
	                            <th>Salida</th>
	                            <th>Cant. max</th>
	                            <th>Fecha de salida</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                     <% 
	                    	List<StringDTSalidaMap.Entry> sals = salidasParaActsProvistas.getEntry();
	                      	Iterator<StringDTSalidaMap.Entry> it3 = sals.iterator();
	              			StringDTSalidaMap.Entry key3;
	              			DtSalida sal = null;
	              			while (it3.hasNext()) {
	              				key3 = it3.next();
	              				sal = key3.getValue();
	              				GregorianCalendar gc3 = sal.getFechaSalida().toGregorianCalendar();
	              				String fechaSalida = Integer.toString(gc3.get(GregorianCalendar.DAY_OF_MONTH)) +"/"+ Integer.toString(gc3.get(GregorianCalendar.MONTH)+1) +"/"+Integer.toString(gc3.get(GregorianCalendar.YEAR));

	                     	 %>
		                      <tr>
		                        <td><a href="consultaSalida?id=<%=sal.getNombre()%>" style="color:rgb(220, 53, 69)"><%=sal.getNombre() %></a></td>
		                        <td><%=sal.getCantMax() %> </td>
		                        <td><%=fechaSalida %></td>
		                      </tr>
		                      
		                      <% } %>
	                    </tbody>
	                  </table>    
	                </div>
					
					
                <% } %>
                <div class="tab-pane" id="editar">
                  <div class="col-lg-8 col-sm-12 py-4 mx-auto">
  
                      <form id="formSub" action="consultaUsuario" method="POST" enctype="multipart/form-data" accept-charset="utf-8">
                          <div class="form-row">
                            <div class="form-group col-md-6">
                              <label>Nombre</label>
                              <input name="nombre" type="text" class="form-control" value="<%= dtu.getNombre() %>" required>
                            </div>
                            <div class="form-group col-md-6">
                              <label>Apellido</label>
                              <input name="apellido" type="text" class="form-control" value="<%= dtu.getApellido() %>" required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="inputAddress">Email</label>
                            <input name="email" type="email" class="form-control" value="<%= dtu.getEmail() %>" readonly>
                          </div>
                          <div class="form-group">
                              <label>Nickname</label>
                              <input name="nickname" class="form-control" value="<%= dtu.getNickname() %>" readonly>
                            </div>
                          <div class="form-row">
                              <div class="form-group col-md-6">
                                <label>Contraseña</label>
                                <input name="contraseña" type="password" class="form-control" placeholder="Contraseña">
                              </div>
                              <div class="form-group col-md-6">
                                <label>Repetir contraseña</label>
                                <input name="confirmacion" type="password" class="form-control" placeholder="Confirmacion">
                              </div>
  
                          </div>
                          <% if (DtTurista.class.isInstance(dtu)){ %>
                          <input type="hidden" name="tipoUs" value="Turista">
                          <% } else { %>
                          <input type="hidden" name="tipoUs" value="Proveedor">
							
						  <% } %>
						  <%if (DtTurista.class.isInstance(dtu)){ 
						  		DtTurista dtt = (DtTurista) dtu;
						  
						  %>

                          <div class="form-group">
                            <label>Nacionalidad</label>
                            <input name="nacionalidad" type="text" class="form-control" value=" <%= dtt.getNacionalidad() %> " required>
                          </div>
                          
                          <% }else{ 
          						DtProveedor dtp = (DtProveedor) dtu; 
          				  %> 
          				  
          				  <div class="form-row">
                            <div class="form-group col-md-6">
                              <label>Sitio web</label>
                              <input name="web" type="text" class="form-control" value=" <%= dtp.getSitioWeb() %> ">
                            </div>
                            <div class="form-group col-md-6">
                              <label>Descripción</label>
                              <textarea name="desc" class="form-control" rows="3" required><%= dtp.getDescripcion() %></textarea>
                            </div>
                          </div> 		
          				
          				  <% } %>
          				  
                          <div class="form-row mb-3">
                            <label>Fecha de nacimiento</label>
                            <div class="input-group date dateChooser">
                              <span class="input-group-addon mr-2" style="margin:auto; color:rgb(220, 53, 69)"><i class="fa fa-calendar-minus-o" type="date" style="cursor: pointer;"></i></span><input name="fechaN" type="text" value="<%= fechaNac %>" class="form-control" required>
                            </div>
                          </div>
                          
                          <div class="form-row">
                            <figure class="image-container" id="imgContainerImgChooser">
                              <input name="imagenSel" type="file" id="upload-button" accept="image/*">
                              <label class="border" for="upload-button" id="imgChooserLabel">
                                <i class="fa fa-upload" style="color:rgb(220, 53, 69)"></i>
                                Elige una foto
                              </label>
                              <img id="chosen-image">
                            </figure>        
                          </div>
          
                          <button type="submit" class="btn btn-danger mt-3">Guardar</button>
                        </form>
                    </div> 
                </div>
                <div class="tab-pane" id="usuariosSeguidos">
                	<table class="table table-sm">
                	<thead>
                        <tr>
                            <th>Imagen</th>
                            <th>Nombre/Apellido</th>
                            <th>Nickname</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                        List<StringDTUsuarioMap.Entry> users = usuariosSeguidos.getEntry();
                      	Iterator<StringDTUsuarioMap.Entry> it = users.iterator();
              			StringDTUsuarioMap.Entry key;
              			DtUsuario user = null;
              			while (it.hasNext()) {
              				key = it.next();
              				user = key.getValue();
           
                      %>
                      <tr>
                        <td><img src="imagenes?tipo=usuario&id=<%= user.getNickname() %>" style="width:50px; height:50px"></td>
                        <td><a href="consultaUsuario?id=<%=user.getNickname()%>" style="color:rgb(220, 53, 69)"><h5> <%=user.getNombre()+" "+user.getApellido() %></h5></a></td>
                        <td><%=user.getNickname() %></td>
                      </tr>
					<%} %>  
                    </tbody>
                  </table>
                </div>  
                <div class="tab-pane" id="usuariosQueSiguenA">
                	<table class="table table-sm">
                	<thead>
                        <tr>
                            <th>Imagen</th>
                            <th>Nombre/Apellido</th>
                            <th>Nickname</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                        List<StringDTUsuarioMap.Entry> usersSiguen = usuariosQueSiguen.getEntry();
                      	Iterator<StringDTUsuarioMap.Entry> itSiguen = usersSiguen.iterator();
              			StringDTUsuarioMap.Entry keySiguen;
              			DtUsuario userSiguen = null;
              			while (itSiguen.hasNext()) {
              				keySiguen = itSiguen.next();
              				userSiguen = keySiguen.getValue();
           
                      %>
                      <tr>
                        <td><img src="imagenes?tipo=usuario&id=<%= userSiguen.getNickname() %>" style="width:50px; height:50px"></td>
                        <td><a href="consultaUsuario?id=<%=userSiguen.getNickname()%>" style="color:rgb(220, 53, 69)"><h5> <%=userSiguen.getNombre()+" "+userSiguen.getApellido() %></h5></a></td>
                        <td><%=userSiguen.getNickname() %></td>
                      </tr>
					<%} %>  
                    </tbody>
                  </table>
                </div>

            </div>
        </div>

    </div>
  </div>
  </div>
  
  <footer id="sticky-footer" class="py-4 bg-light text-black-50 border">
      <div class="container text-center">
        <small>Copyright &copy; turismo.Uy</small>
      </div>
  </footer>
  
  <script src="media/js/bootstrap-datepicker.min.js"></script>
  <script src="media/js/bootstrap-datepicker.es.min.js"></script>
  <script src="media/js/menuIzq.js"></script>
  <script src="media/js/dateChooser.js"></script>
  <script src="media/js/imgChooser.js"></script>

</body>
</html>
