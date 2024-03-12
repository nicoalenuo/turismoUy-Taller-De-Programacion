	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="servidorusuarios.DtTurista" %>
	<%@ page import="servidorusuarios.DtProveedor" %>
	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="java.util.*" %>
	
	<%
	DtUsuario user = (DtUsuario) session.getAttribute("datosUsuarioSesion");
	String q = request.getParameter("q");
	boolean movil = request.getHeader("User-Agent").contains("Mobi");
	%>
 
<nav id="header" class="navbar navbar-expand-lg fixed-top navbar-light bg-light">
    <button class="btn px-1 py-0 open-btn"> <i class="fa fa-bars"></i></button>
    <a class="navbar-brand ml-5" <% if (!movil || user!=null){  %>href="index" <% } %>>
      <span class="" style="font-size: 25px; color: rgb(136, 31, 48);"><i class="fa fa-plane"></i></span>
      turismo.<b>Uy</b>
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="fa fa-user-o"></span>
    </button>
	
    <div class="collapse navbar-collapse justify-content-end align-center" id="navbarSupportedContent">
      <% if (!movil){ %>
        <form action="busqueda" method="GET" class="form-inline my-4 my-lg-0 ml-auto mr-auto text">
            <input name="q" class="form-control mr-sm-2 mr-2" type="search" id="searchBar" placeholder="Buscar actividades, usuarios, etc" aria-label="Search" <% if(q!=null){  %> value="<%=q%>" <%} %>  style="width: 25rem">
            <button type="submit" class="" style="color: rgb(136, 31, 48); border:none; background:none"><i class="fa fa-search"></i></button>
        </form>
	<% } %>
      <ul class="navbar-nav">
      <% if (!movil){ %>
        <li class="nav-item dropdown mr-auto ml-auto">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Herramientas
          </a>
          
          <% if (user==null){ %>
          
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="consultaUsuario"><i class="fa fa-users mr-2" style="color: rgb(220, 53, 69)"></i>Consultar usuarios</a>
            <a class="dropdown-item" href="consultaPaquete">Consultar paquetes de actividades</a>
          </div>
          
          <%  }else{  
          
          	if (DtTurista.class.isInstance(user)){ %>
           
	          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
	            <a class="dropdown-item" href="consultaUsuario"><i class="fa fa-users mr-2" style="color: rgb(220, 53, 69)"></i>Consultar usuarios</a>
	            <a class="dropdown-item" href="consultaPaquete">Consultar paquetes de actividades</a>
	            <div class="dropdown-divider"></div>
	            <a class="dropdown-item" href="inscripcionSalida">Inscribirse a salida turística</a>
	            <a class="dropdown-item" href="compraPaquete">Comprar paquete de actividades</a>
	          </div>
	          
	        <% }else{  %>
	        
	          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="consultaUsuario"><i class="fa fa-users mr-2" style="color: rgb(220, 53, 69)"></i>Consultar usuarios</a>
                <a class="dropdown-item" href="consultaPaquete">Consultar paquetes de actividades</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="altaActividad">Alta de actividad turística</a>
                <a class="dropdown-item" href="altaSalida">Alta de salida turística</a>
              </div>
	        
	       <%  } 
          
          } %>
          
        </li>
        <% } %>
        <% if (user!=null){ %>
        
        <li class="nav-item dropdown mr-auto ml-auto">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          
          
            <img src="imagenes?tipo=usuario&id=<%= user.getNickname() %>" class="card-img" style="max-height: 40px; max-width: 40px;">
            <%= user.getNombre() +" "+ user.getApellido() %>
          
          </a>
          
	          
          
          
          
          	<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
          	<% if (!movil){ %>
              <a class="dropdown-item" href="consultaUsuario?id=<%= user.getNickname() %>"><i class="fa fa-info mr-3" style="color: rgb(220, 53, 69)"></i>Mi perfil</a>
              <div class="dropdown-divider"></div>
            <% } %>
              <a class="dropdown-item" href="login"><i class="fa fa-sign-out mr-2" style="color: rgb(220, 53, 69)"></i>Cerrar sesión</a>
            </div>	
          
        </li>   
      </ul>
      
      <% }else{ %>
      
      
      <div style="margin-top: 8px; margin-left: 5px">
      	<% if (!movil){ %><a href="altaUsuario" style="color:rgb(220, 53, 69)"><i class="fa fa-address-card-o mr-2" style="color: rgb(220, 53, 69)"></i>Registrarse</a> | <%} %> <a href="home" style="color:rgb(220, 53, 69)"><i class="fa fa-sign-out mr-2" style="color: rgb(220, 53, 69)"></i>Iniciar sesión</a>
      </div>
      
      <% } %>
       
    </div>
   
  </nav>