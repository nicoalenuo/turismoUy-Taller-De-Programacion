<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">
<head>

	<jsp:include page="/WEB-INF/Template/head.jsp"/>

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
              <h2>Bienvenidos/as</h2>
            </div>
          </div>
          <div class="row flex-column flex-md-row">
            <div class="col py-4 px-4">
              <div class="row g-3">

                <p>Bienvenidos/as a turismoUy, donde puede conocer al Uruguay</p>
                
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
