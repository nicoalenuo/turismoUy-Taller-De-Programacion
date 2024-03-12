<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.*" %>
<%@ page import="servidoractividades.DtDepartamento" %>
<%@ page import="servidoractividades.DtCategoria" %>
<%@ page import="servidoractividades.StringDTDepartamentoMap" %>
<%@ page import="servidoractividades.StringDTCategoriaMap" %>

<div class="sidebar border-right h-100 overflow-auto" id="side_nav"> 
	<% List<StringDTDepartamentoMap.Entry> depsMenu = (List<StringDTDepartamentoMap.Entry>) request.getAttribute("depsMenu"); %>
	<% List<StringDTCategoriaMap.Entry> catsMenu = (List<StringDTCategoriaMap.Entry>) request.getAttribute("catsMenu"); 	%>
	
   <h5 class="ml-3"><i class="fa fa-paper-plane-o mr-2 mt-3" style="color: rgb(220, 53, 69)"></i>Categorias</h5>
   <ul class="list-unstyled pl-5 pt-3">
	   	<%
	   	if(catsMenu!=null){
         	Iterator<StringDTCategoriaMap.Entry> iterCatMenu = catsMenu.iterator();
		  	StringDTCategoriaMap.Entry keyCatMenu;
		  	DtCategoria catMenu = null;
		  	while (iterCatMenu.hasNext()) {
				keyCatMenu = iterCatMenu.next();
				catMenu = keyCatMenu.getValue();
			%> 
  			<li class=""><a href="consultaActividad?tipo=categoria&id=<%=catMenu.getNombre() %>" class="text-decoration-none d-block" style="color: rgb(220, 53, 69)"><%=catMenu.getNombre() %></a></li>
          	<% } 
	    }%>
   </ul>
   <hr class="h-color mx-2">
   
   <h5 class="ml-3"><i class="fa fa-map-o mr-2" style="color: rgb(220, 53, 69)"></i>Departamentos</h5>
   <ul class="list-unstyled  pl-5 pt-3 pb-4">
   		<%
   		if(depsMenu != null){
         	Iterator<StringDTDepartamentoMap.Entry> iterDepMenu = depsMenu.iterator();
		  	StringDTDepartamentoMap.Entry keyDepMenu;
		  	DtDepartamento depMenu = null;
		  	while (iterDepMenu.hasNext()) {
				keyDepMenu = iterDepMenu.next();
				depMenu = keyDepMenu.getValue();
			%> 
  			<li class=""><a href="consultaActividad?tipo=departamento&id=<%=depMenu.getNombre() %>" class="text-decoration-none d-block" style="color: rgb(220, 53, 69)"><%=depMenu.getNombre() %></a></li>
          	<% } 
        }%>
       
       <div style="height: 60px;"></div>
   </ul>
 </div>