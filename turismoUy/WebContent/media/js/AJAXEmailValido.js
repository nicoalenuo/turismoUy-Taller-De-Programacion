$(document).ready(function() {
		  $('#inputEmail').on('change',function() {
		   	var email = $('#inputEmail').val();
	   		$.ajax({
	   			type: 'POST',
	   			url: 'altaUsuario',
	   			data: {email: email},
	   			success: function(result){ 
	   				if (result == "Correcto"){
	   					document.getElementById('txtErrorEmail').style.display='none';
   						document.getElementById('txtCorrectoEmail').style.display='block';
   					}
	   				else{
	   					document.getElementById('txtErrorEmail').style.display='block';
   						document.getElementById('txtCorrectoEmail').style.display='none';
	   				}
				}
				
			  }); 
		  }); 
	  }); 
