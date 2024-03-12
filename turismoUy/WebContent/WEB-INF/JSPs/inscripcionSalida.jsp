<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>

  <jsp:include page="/WEB-INF/Template/head.jsp"/>
  
  <link rel="stylesheet" href="media/css/imgChooser.css"> 
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
              <h2>Inscribirse a Salida</h2>
          </div>
        </div>

        <%@ page import="servidorusuarios.DtUsuario" %>
        <%@ page import="servidorusuarios.DtTurista" %>
        <%@ page import="servidorusuarios.DtProveedor" %>
        <%@ page import="servidorsalidas.DtSalida" %>
        <%@ page import="servidorsalidas.DtPaquete" %>
        <%@ page import="servidoractividades.DtDepartamento" %>
        <%@ page import="servidoractividades.DtCategoria" %>
        <%@ page import="servidorsalidas.DtActividad" %>
        <%@ page import="servidoractividades.StringDTDepartamentoMap" %>
        <%@ page import="servidorsalidas.StringDTActividadMap" %>
        <%@ page import="servidorsalidas.StringDTSalidaMap" %>
        <%@ page import="servidorsalidas.StringDTPaqueteMap" %>
        <%@ page import="java.util.*" %>
        
        <% String dptoSeleccionado = (String) request.getAttribute("departamentoSeleccionado"); %>
        <% String actSeleccionada = (String) request.getAttribute("actividadSeleccionada"); %>
        <% String salidaSeleccionada = (String) request.getAttribute("salidaSeleccionada"); %>
        <% String paqueteSeleccionado = (String) request.getAttribute("paqueteSeleccionado"); 
        if(dptoSeleccionado==null){
        		dptoSeleccionado="Departamentos";
        	}
       	if(actSeleccionada==null){
       		actSeleccionada="Actividades";
       	}
       	if(salidaSeleccionada==null){
       		salidaSeleccionada="Salidas";
       	}
       	if(paqueteSeleccionado==null){
       		paqueteSeleccionado="Paquetes";
       	}%>
        <% StringDTDepartamentoMap deps = (StringDTDepartamentoMap) request.getAttribute("departamentoListar"); %> 
        <% StringDTActividadMap acts = (StringDTActividadMap) request.getAttribute("actividadesListar"); %> 
        <% StringDTSalidaMap salidas = (StringDTSalidaMap) request.getAttribute("salidasListar"); %>
        <% StringDTPaqueteMap paquetes = (StringDTPaqueteMap) request.getAttribute("paquetesListar"); %> 
        <% String error = (String) request.getParameter("error"); %> 
        
          
        <div class="col-lg-5 col-sm-12 py-4 mx-auto">
              <form  method="GET" action="inscripcionSalida" accept-charset="utf-8">
                <select name="departamento" onchange='{ this.form.submit(); }'>
                        <% if (deps!=null && deps.getEntry()!=null && !deps.getEntry().isEmpty()){ %>
		                   	<% if (dptoSeleccionado != "Departamentos") {%>
		                       	<option value="<%=dptoSeleccionado%>"><%=dptoSeleccionado%></option>
		                    <% }else{ %>
		                    	<option value="Departamentos">Departamentos</option>
		                    <% } %>
	                       <%List<StringDTDepartamentoMap.Entry> departamentos = deps.getEntry();
	                    	Iterator<StringDTDepartamentoMap.Entry> it = departamentos.iterator();
	            			StringDTDepartamentoMap.Entry key;
	            			DtDepartamento dep = null;
	    	     			while (it.hasNext()) {
	    	     				key = it.next();
	    	     				dep= key.getValue();%>
	                           	<option value="<%=dep.getNombre()%>"><%=dep.getNombre()%></option>
	    	     			<%}
	    	     		}else{ %>
                        	<option value="Departamentos">Departamentos</option>
                        <% } %>
              </select>
              </form>
              <form  method="GET" action="inscripcionSalida" accept-charset="utf-8">
              <select class="select" name="actividad" onchange='{ this.form.submit(); }'>
              
                      <% if (acts!=null && acts.getEntry()!=null && !acts.getEntry().isEmpty()){ %>
		                   	<% if (actSeleccionada != "Actividades") {%>
		                       	<option value="<%=actSeleccionada%>"><%=actSeleccionada%></option>
		                    <% }else{ %>
		                    	<option value="Actividades">Actividades</option>
		                    <% } %>
	                       <%List<StringDTActividadMap.Entry> actividades = acts.getEntry();
	                    	Iterator<StringDTActividadMap.Entry> it2 = actividades.iterator();
	            			StringDTActividadMap.Entry key2;
	            			DtActividad act = null;
	    	     			while (it2.hasNext()) {
	    	     				key2 = it2.next();
	    	     				act= key2.getValue();%>
	                           	<option value="<%=act.getNombre()%>"><%=act.getNombre()%></option>
	    	     			<%}
	    	     		}else{ %>
                        	<option value="Actividades">Actividades</option>
                        <% } %>
                  </select>
                  <input type="hidden" name="departamento" value="<%=dptoSeleccionado%>">
            </form>
            <form class="formsub" action="inscripcionSalida" method="POST" accept-charset="utf-8">
                  <div class="form-row">
                    <div class="form-group col-md-6">
                        <select class="select" name="salida">
                            <% if (salidas!=null && salidas.getEntry()!=null && !salidas.getEntry().isEmpty()){ %>
			                   	<% if (salidaSeleccionada != "Salidas") {%>
			                       	<option value="<%=salidaSeleccionada%>"><%=salidaSeleccionada%></option>
			                    <% }else{ %>
			                    	<option value="Salidas">Salidas</option>
			                    <% } %>
	                       <%List<StringDTSalidaMap.Entry> sals = salidas.getEntry();
	                    	Iterator<StringDTSalidaMap.Entry> it3 = sals.iterator();
	            			StringDTSalidaMap.Entry key3;
	            			DtSalida sal = null;
	    	     			while (it3.hasNext()) {
	    	     				key3 = it3.next();
	    	     				sal= key3.getValue();%>
	                           	<option value="<%=sal.getNombre()%>"><%=sal.getNombre()%></option>
	    	     			<%}
	    	     		}else{ %>
                        	<option value="Salidas">Salidas</option>
                        <% } %>   
                        </select>
                      </div>
                  </div>
                  <div class="form-row">
                    <div class="form-group col-md-4">
                      <label for="inputCantTuristas">Cantidad de turistas</label>
                      <input name="cantTuristas" type="number" min="0" class="form-control" required> 
                    </div>
                                    
                  </div>
                  <div class="form-row mb-3">
                    <select class="select" name="paquete">
                      <% if (paquetes!=null && paquetes.getEntry()!=null && !paquetes.getEntry().isEmpty()){ %>
			                   	<% if (paqueteSeleccionado != "Paquetes") {%>
			                       	<option value="<%=paqueteSeleccionado%>"><%=paqueteSeleccionado%></option>
			                    <% }else{ %>
			                    	<option value="Paquetes">Paquetes</option>
			                    <% } %>
	                       <%List<StringDTPaqueteMap.Entry> paqs = paquetes.getEntry();
	                    	Iterator<StringDTPaqueteMap.Entry> it4 = paqs.iterator();
	            			StringDTPaqueteMap.Entry key4;
	            			DtPaquete paq = null;
	    	     			while (it4.hasNext()) {
	    	     				key4 = it4.next();
	    	     				paq= key4.getValue();%>
	                           	<option value="<%=paq.getNombre()%>"><%=paq.getNombre()%></option>
	    	     			<%}
	    	     		}else{ %>
                        	<option value="Paquetes">Paquetes</option>
                        <% } %>  
                    </select>
                    <input type="hidden" name="departamento" value="<%=dptoSeleccionado%>">
                    <input type="hidden" name="actividad" value="<%=actSeleccionada%>">
                  </div>

                  <button type="submit" class="btn btn-danger mt-3">Inscribirse</button>
                  <% if (error!=null && error.equals("errorSalida")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*La salida no tiene cupos suficientes</small>  
                  <% } %>
                  <% if (error!=null && error.equals("errorSalida2")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Seleccione una salida</small>  
                  <% } %>
                  <% if (error!=null && error.equals("errorActividad")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Seleccione una actividad</small>  
                  <% } %>
                  <% if (error!=null && error.equals("errorDepartamento")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Seleccione un departamento</small>  
                  <% } %>
                  <% if (error!=null && error.equals("errorSalida3")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*Ya está inscripto a esta salida</small>  
                  <% } %>
                  <% if (error!=null && error.equals("errorCantidadTuristas")){ %>
                  	<small class="form-text text-black-50 ml-2" style="font-size: 12px">*La cantidad de turistas ingresado excede a la cantidad de turistas del paquete, o a ingresado cero </small>  
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
  
  <script src="media/js/menuIzq.js"></script>  
  
  
</body>
</html>