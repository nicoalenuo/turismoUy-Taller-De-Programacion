<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  
  <link rel="stylesheet" href="media/css/imgChooser.css">
  <link rel="stylesheet" href="media/css/bootstrap-datepicker.css">
  
	<%
	String error = (String) request.getAttribute("error");
	%>


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
              <h2>Registrarse</h2>
          </div>
        </div>
        <div class="col-lg-5 col-sm-12 py-4 mx-auto">
    
            <form id="formSub" action="altaUsuario" method="POST" enctype="multipart/form-data" accept-charset="utf-8">
                <div class="form-row">
                  <div class="form-group col-md-6">
                    <label>Nombre</label>
                    <input name="nombre" class="form-control" required>
                  </div>
                  <div class="form-group col-md-6">
                    <label>Apellido</label>
                    <input name="apellido" class="form-control" required>
                  </div>
                </div>
                <div class="form-group">
                  <label>Email</label>
                  <input id="inputEmail" name="email" type="email" class="form-control" required>
                </div>
                <small id="txtErrorEmail" class="form-text text-black-50 ml-2" style="font-size: 12px; display:none">*El email ya está en uso</small> 
                <small id="txtCorrectoEmail" class="form-text text-black-50 ml-2" style="font-size: 12px; display:none; color: green">*El email está libre</small> 
                <div class="form-group">
                    <label>Nickname</label>
                    <input id="inputNickname" name="nickname" class="form-control" required>
                  </div>
                  <small id="txtErrorNickname" class="form-text text-black-50 ml-2" style="font-size: 12px; display:none">*El nickname ya está en uso</small> 
                  <small id="txtCorrectoNickname" class="form-text text-black-50 ml-2" style="font-size: 12px; display:none; color:green">*El nickname está libre</small> 
                <div class="form-row">
                    <div class="form-group col-md-6">
                      <label>Contraseña</label>
                      <input name="contraseña" type="password" class="form-control" required>
                    </div>
                    <div class="form-group col-md-6">
                      <label>Confirmación</label>
                      <input name="confirmacion" type="password" class="form-control" required>
                    </div>
                </div>
                	<% if (error!=null && error.equals("errorContraseña")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*La contraseña ingresada no coincide con la confirmación</small>  
                    <% } %>
                <div class="custom-control custom-radio">
                    <input name="tipoUs" type="radio" onclick="tipoUsuario(0)" class="custom-control-input" id="customControlValidation2" value="Turista" required>
                    <label class="custom-control-label" for="customControlValidation2">Turista</label>
                  </div>
                  <div class="custom-control custom-radio mb-3">
                    <input name="tipoUs" type="radio" onclick="tipoUsuario(1)" class="custom-control-input" id="customControlValidation3" value="Proveedor" required>
                    <label class="custom-control-label" for="customControlValidation3">Proveedor</label>
                  </div>

                  <div class="form-group" id="txtTurista" style="display:none">
                    <label>Nacionalidad</label>
                    <input name="nacionalidad" class="form-control">
                  </div>
                <div class="form-row" id="txtProveedor" style="display:none">
                    <div class="form-group col-md-6">
                      <label>Sitio web</label>
                      <input name="web" class="form-control" placeholder="turismo.uy">
                      <small class="form-text text-black-50 ml-2" style="font-size: 12px">Opcional</small>  
                    </div>
                    <div class="form-group col-md-6">
                      <label>Descripción</label>
                      <textarea name="desc" class="form-control" rows="3"></textarea>
                    </div>
                </div> 

                <div class="form-row mb-3">
                  <label>Fecha de nacimiento</label>
                  <div class="input-group date dateChooser">
                    <span class="input-group-addon mr-2" style="margin:auto; color:rgb(220, 53, 69)"><i class="fa fa-calendar-minus-o" type="date" style="cursor: pointer;"></i></span><input name="fechaN" type="text" class="form-control" placeholder="dd/mm/aaaa" required>
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
                

                <button type="submit" class="btn btn-danger mt-3">Registrarse</button>
                	<% if (error!=null && error.equals("errorUsuarios")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ya existe un usuario con el email o nickname ingresados</small>  
                    <% } %>
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
  
  <script src="media/js/bootstrap-datepicker.min.js"></script>
  <script src="media/js/bootstrap-datepicker.es.min.js"></script>
  <script src="media/js/ocultarDivsAltaUsuario.js"></script>
  <script src="media/js/menuIzq.js"></script>  
  <script src="media/js/dateChooser.js"></script>
  <script src="media/js/imgChooser.js"></script>
  <script src="media/js/AJAXNicknameValido.js"></script>
   <script src="media/js/AJAXEmailValido.js"></script>
</body>
</html>
