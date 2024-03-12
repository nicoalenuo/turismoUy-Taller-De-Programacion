<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html lang="en">
<head>

	<%@ page import="servidorusuarios.StringDTUsuarioMap" %>
	<%@ page import="servidorusuarios.DtUsuario" %>
	<%@ page import="java.util.*" %>
	
	<%
		StringDTUsuarioMap usuarios = (StringDTUsuarioMap) request.getAttribute("usuariosListar"); 

	%>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>

</head>

<body>

  <jsp:include page="/WEB-INF/Template/header.jsp"/>

  <div style="height: 65px;"></div>
  <div class="container-fluid">
    <div class="row">
      
      <jsp:include page="/WEB-INF/Template/menuIzq.jsp"/>

        <div class="col py-4">
            
            <div class="row border-bottom mb-3" style="max-width: 80rem">
                <div class="col">
                    <h2>Usuarios</h2>
                </div>
            </div> 

            <div class="row pt-1">
                <div class="col-12">
                  <table class="table table-xxl">
                    <thead>
                        <tr>
                            <th>Imagen</th>
                            <th>Nombre/Apellido</th>
                            <th>Nickname</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                        List<StringDTUsuarioMap.Entry> users = usuarios.getEntry();
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
