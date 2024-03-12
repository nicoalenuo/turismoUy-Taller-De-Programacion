$(document).ready(function() {
		  $('#inputNickname').on('change',function() {
		   	var nickname = $('#inputNickname').val();
	   		$.ajax({
	   			type: 'POST',
	   			url: 'altaUsuario',
	   			data: {nickname: nickname},
	   			success: function(result){ 
	   				if (result == "Correcto"){
	   				 	document.getElementById('txtErrorNickname').style.display='none';
   						document.getElementById('txtCorrectoNickname').style.display='block';
					}
	   				else{
	   					document.getElementById('txtErrorNickname').style.display='block';
   						document.getElementById('txtCorrectoNickname').style.display='none';
	   				}
				}
				
			  }); 
		  }); 
	  }); 
