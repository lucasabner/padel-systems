//$(document).ready(function(){
//		$("#cpf1").mask("999.999.999-99");
//		$("#cpf2").mask("999.999.999-99");
//	});

//var cpf1 = document.querySelector("#cpf1");
//
//cpf1.addEventListener("blur", function(){
//   if(cpf1.value) cpf1.value = cpf1.value.match(/.{1,3}/g).join(".").replace(/\.(?=[^.]*$)/,"-");
//});
//
//var cpf2 = document.querySelector("#cpf2");
//
//cpf2.addEventListener("blur", function(){
//   if(cpf2.value) cpf2.value = cpf2.value.match(/.{1,3}/g).join(".").replace(/\.(?=[^.]*$)/,"-");
//})

//document.write(document.getElementById("torneio_insc").value);
var ins = String(document.getElementById("torneio_insc").value);
var insComparar = "true";

if (ins === "true") {
	document.getElementById("insc").style.display = 'none';
	document.getElementById("cadDupla").style.display = 'none';
}

function mascaraCpf(i) {

	var v = i.value;

	if (isNaN(v[v.length - 1])) { // impede entrar outro caractere que não
									// seja número
		i.value = v.substring(0, v.length - 1);
		return;
	}

	i.setAttribute("maxlength", "14");
	if (v.length == 3 || v.length == 7)
		i.value += ".";
	if (v.length == 11)
		i.value += "-";

}
