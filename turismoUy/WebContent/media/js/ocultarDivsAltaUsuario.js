function tipoUsuario(x){
	if(x==0){
		document.getElementById('txtTurista').style.display='block';
		document.getElementById('txtProveedor').style.display='none';
		$("#nacionalidad").attr("required","required");
		$("#desc").removeAttr("required");
	}else{
		document.getElementById('txtTurista').style.display='none';
		document.getElementById('txtProveedor').style.display='flex';
		$("#nacionalidad").removeAttr("required");
		$("#desc").attr("required","required");
	}
	return;
}