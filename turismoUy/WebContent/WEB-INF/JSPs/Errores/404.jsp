<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

	<jsp:include page="/WEB-INF/Template/head.jsp"/>
	<%@ page import="java.util.*" %>

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
              <h2>Error 404</h2>
            </div>
          </div>
          <div class="row flex-column flex-md-row">
            <div class="col py-4 px-4">
              <div class="row g-3">

                <p>Has intentado acceder a una pagina inexistente, puedes volver al inicio presionando <a href="index" style="color: rgb(136, 31, 48)">aqui</a>.</p>
                
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

  <script src="media/js/menuIzq.js"></script>  

</body>
</html>
