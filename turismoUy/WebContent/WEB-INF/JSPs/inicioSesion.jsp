<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  <%@ page import="servidorusuarios.DtUsuario" %> 
  <%
   String error = (String) request.getAttribute("error");
   boolean movil = request.getHeader("User-Agent").contains("Mobi");
   DtUsuario user = (DtUsuario) session.getAttribute("datosUsuarioSesion");
  %>

</head>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid"  style="min-height: 800px;">
    <div class="row">
      
      <% if (!movil || user!=null){ %>
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>
	  <%} %>
      <div class="col py-4">

        <div class="row border-bottom mb-3" style="max-width: 80rem">
          <div class="col">
              <h2>Ingresar</h2>
          </div>
        </div>

        <div class="col-lg-5 col-sm-12 py-4 mx-auto">
           <form action="login" method="POST" accept-charset="utf-8">
               <div class="form-group">
                 <div class="form-group">
                   <label class="mb-1" style="min-width:8rem">Ingresar email o nickname</label>              
                   <input name="usuarioIngresado" type="text" class="form-control" placeholder="Email o nickname" style="min-width:8rem; max-width: 400px;" required>
                   	<% if (error!=null && error.equals("usuario")){ %>
                   	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*No existe un usuario con el email o nickname ingresado</small>  
                   	<% } %>
                   	<% if (error!=null && error.equals("movilProveedor")){ %>
                   	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Solo pueden acceder turistas mediante un movil</small>  
                   	<% } %>
                   <span id="prueba"></span>
                 </div>
                 <div class="form-group">
                   <label class="mt-3 mb-1" style="min-width:8rem">Ingresar contraseña</label>
                   <input name="contraseñaIngresada" type="password" class="form-control" placeholder="Contraseña" style="min-width:8rem; max-width: 400px;" required>
                   <%if (error!=null && error.equals("contraseña")){ %>
                   <small class="form-text text-black-50 ml-2" style="font-size: 12px">*Contraseña incorrecta</small>  
                   <% } %>
                 </div>
                 <div class="form-inline">
                   <button type="submit" class="btn btn-danger mt-3 mr-2" style="min-width: 8rem;">Iniciar sesión</button>
                   <% if (!movil) { %> 
                   <a href="altaUsuario" class="btn btn-outline-danger mt-3"  style="min-width: 8rem;">O registrate aqui</a>  
                   <% } %>  
                 </div>
               </div>
           </form>
        </div>
      </div>
    </div>
  </div>
  
  <footer id="sticky-footer" class="py-4 bg-light text-black-50 border">
      <div class="container text-center">
        <small>Copyright &copy; turismo.Uy</small>
      </div>
  </footer>

  <script src="media/js/menuIzq.js"></script>

</body>
</html>
