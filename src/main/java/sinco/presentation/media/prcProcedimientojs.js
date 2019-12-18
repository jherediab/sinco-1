 var sVolver2="NO";
 var sVolver="NO";
 var totalPolitica = 0;
 var totalProcAntecedente = 0;
 var totalProveedorEntrada = 0;
 var totalClienteSalida	= 0;
 var totalPlanear = 0;
 var totalHacer = 0;
 var totalVerificar = 0;
 var totalActual = 0;
 var totalRecursos = 0;

function Cargar(){
  try {
    if (document.forms[0]!=null){
      document.forms[0].elements[0].focus();
    }
  }
   catch(e){
   }
 }

 function Salvar2(){
   Ocultar();
   if (sVolver2=="SI"){
      return true;
   }
   return true;
 }
 
 function Salvar(){
   var rta;
   Ocultar();
   if ($('_operacion').value=="L"){
      return Salvar2();
   }

   if (sVolver=="SI"){
      return true;
   }

	//-----------------------------VALIDAR LOS CAMPOS 1..N
	if( totalPolitica <= 0 ){
		alert("No ha agregado alguna politica.");
		document.getElementById("PoliticaText").focus();
		Mostrar();
		return false;
	}
	rta=totalizarPolitica();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	rta=totalizarPolitica();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalProveedorEntrada <= 0 ){
		alert("No ha agregado proveedor.");
		document.getElementById("Proveedores").focus();
		Mostrar();
		return false;
	}
	rta=totalizarProveedores();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalClienteSalida <= 0 ){
		alert("No ha agregado Cliente.");
		document.getElementById("Clientes").focus();
		Mostrar();
		return false;
	}
	rta=totalizarClientes();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalPlanear <= 0 ){
		alert("No ha agregado medida planear.");
		document.getElementById("codigoEmpleadoDescPlan").focus();
		Mostrar();
		return false;
	}
	rta=totalizarPlanear();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalHacer<= 0 ){
		alert("No ha agregado medida hacer.");
		document.getElementById("codigoEmpleadoDescHac").focus();
		Mostrar();
		return false;
	}
	rta=totalizarHacer();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalVerificar<= 0 ){
		alert("No ha agregado medida verificar.");
		document.getElementById("codigoEmpleadoDescVer").focus();
		Mostrar();
		return false;
	}
	rta=totalizarVerificar();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalActual<= 0 ){
		alert("No ha agregado medida actuar.");
		document.getElementById("codigoEmpleadoDescAct").focus();
		Mostrar();
		return false;
	}
	rta=totalizarActuar();
	if (!rta) { 
		Mostrar();
		return false;
	}
	
	if( totalRecursos<= 0 ){
		alert("No ha agregado un recurso.");
		document.getElementById("tipoRecurso").focus();
		Mostrar();
		return false;
	}
	rta=totalizarRecurso();
	if (!rta) { 
		Mostrar();
		return false;
	}
	

   rta=validar($('idProcedimiento'),"Id Procedimiento","ENT",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('procesoId'),"Proceso Id","ENT",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('subprocesoId'),"Subproceso Id","ENT",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('servicioUnidadId'),"ServicioUnidad Id","ENT",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('codigoEmpleado'),"Código Empleado","ENT",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('objetivo'),"Objetivo","ALFA",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('alcance'),"Alcance","ALFA",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('definiciones'),"Definiciónes","ALFA",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('concepto'),"Concepto","ALFA",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   rta=validar($('estado'),"Estado","ALFA",0,"OBL","0","999999999999999");
   if(!rta){
      Mostrar();
      return false;
   }
   var resp = confirm('Esta seguro de realizar esta operaci\xf3n?'); 
   if(!resp)  Mostrar(); 
      return resp;
 }

 function Eliminar(){
   $('_operacion').value="E";
   return true;
 }
 
 function Grabar(){
   limpieza();
   return true;
 }

 function Consultar(){
   $('_operacion').value="L";
   return true;
 }

 function PreparaConsulta(){
   sVolver2="SI";
   sVolver="SI";
   $('_operacion').value="X";
   return true;
 }

 function Salir(){
    sVolver="SI";
    if ($('_operacion').value=="M"){
      $('_operacion').value="V";
    }
    else {
      $('_operacion').value="X";
    }
    return true;
 }

 function Nuevo(){
   sVolver="SI";
   $('_operacion').value="Nuevo";
   return true;
 }

 function Modificar(){
   sVolver="SI";
   $('_operacion').value="P";
   return true;
 }

// FUNCIONES AGREGAR POLITICA
 function agregarPolitica(){
   if(document.getElementById('PoliticaText').value.length!=0){
 	   var tabla=document.getElementById('TablaPoliticaText').getElementsByTagName("TBODY")[0];
       var fila = document.createElement("TR"); 
       fila.id = "FilaPoliticaText" + totalPolitica;
       
       var td1 = document.createElement("TD");
       var td2 = document.createElement("TD");

 	   input = document.createElement("input");
       input.id = "FilaPoliticaText"+ totalPolitica;
       input.idfila = "FilaPoliticaText" + totalPolitica;
       input.name = "EliminarLinea";
       input.type = "button";
       input.value = "Eliminar";
       input.className="submitLink";
       input.onclick = eliminarPolitica;
       
		if(document.forms[0]._operacion.value=="D"){
				 td2.appendChild(document.createTextNode(" "));
		}else{
			td2.appendChild(input);
		}
		
		td1.appendChild(document.createTextNode(document.getElementById('PoliticaText').value));
		fila.appendChild(td1); 
		fila.appendChild(td2);
		tabla.appendChild(fila);
		totalPolitica = totalPolitica + 1;
		//alert(totalPolitica);
		document.getElementById('PoliticaText').value="";
	}
	else{
		alert("Debe digitar la Politica");
	}
 }
 function eliminarPolitica(e){
      var Tabla = document.getElementById("TablaPoliticaText");
      var obj;
      if (navigator.appVersion.indexOf("MSIE")!=-1){
         obj=window.event.srcElement;
      }       
      else if(navigator.userAgent.indexOf("Firefox")!=-1){
        obj=e.target;
      }
      Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
      totalPolitica = totalPolitica - 1;
      return;
 }
	function totalizarPolitica(){
		var politicas="";
		for(var i=1;i<document.getElementById('TablaPoliticaText').rows.length;i++){
			if(politicas!=""){
				politicas = politicas + "~";
			}
			politicas = politicas + document.getElementById('TablaPoliticaText').rows[i].cells[0].innerHTML;
		}
		document.getElementById('PoliticasCant').value = politicas;
		return true;
	}
 
	 // FUNCIONES PROCEDIMIENTOS ANTECEDENTES
	 function agregarProcAnteriores(){
		var indice = document.getElementById('AnterioresText').selectedIndex;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un procedimiento antecesor");
		}
		else{
		   var tabla=document.getElementById('TablaAnterioresText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaProcAntecedenteText" + totalProcAntecedente;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaProcAntecedenteText"+ totalProcAntecedente;
		   input.idfila = "FilaProcAntecedenteText" + totalProcAntecedente;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarProcAntecesor;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td2.appendChild(document.createTextNode(" "));
			}else{
				td2.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(document.getElementById('AnterioresText').value));
			fila.appendChild(td1); 
			fila.appendChild(td2);
			tabla.appendChild(fila);
			totalProcAntecedente = totalProcAntecedente + 1;
			//alert(totalProcAntecedente);
			document.getElementById('AnterioresText').selectedIndex = "";
		}
	 }
	 function eliminarProcAntecesor(e){
		 debugger;
		  var Tabla = document.getElementById("TablaAnterioresText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalProcAntecedente = totalProcAntecedente - 1;
		  return;
	 }
 
	function totalizarProcAnteriores(){
		var Anteriores="";
		for(var i=1;i<document.getElementById('TablaAnterioresText').rows.length;i++){
			if(Anteriores!=""){
				Anteriores = Anteriores + "~";
			}
			Anteriores = Anteriores + document.getElementById('TablaAnterioresText').rows[i].cells[0].innerHTML;
		}
		document.getElementById('AnterioresCant').value = Anteriores;
		return true;
	}
 
	// FUNCIONES PROCEDIMIENTOS PROVEEDOR/ENTRADA
	 function agregarProveedoresEntradas(){
		var indice = document.getElementById('Proveedores').selectedIndex;
		var indice2 = document.getElementById('Entradas').selectedIndex;
		debugger;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un proveedor.");
		}
		else if(indice2 == null || indice2 == 0){
			alert("Debe seleccionar una entrada.");
		}
		else{
		   var tabla=document.getElementById('TablaProveedorText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaProveedorText" + totalProveedorEntrada;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaProveedorText"+ totalProveedorEntrada;
		   input.idfila = "FilaProveedorText" + totalProveedorEntrada;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarProveedoresEntradas;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td3.appendChild(document.createTextNode(" "));
			}else{
				td3.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(document.getElementById('Proveedores').value));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(document.getElementById('Entradas').value));
			fila.appendChild(td2);
			fila.appendChild(td3);
			tabla.appendChild(fila);
			totalProveedorEntrada = totalProveedorEntrada + 1;
			//alert(totalProveedorEntrada);
			document.getElementById('Proveedores').selectedIndex = 0;
			document.getElementById('Entradas').selectedIndex = 0;
		}
	 }
	 function eliminarProveedoresEntradas(e){
		  var Tabla = document.getElementById("TablaProveedorText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalProveedorEntrada = totalProveedorEntrada - 1;
		  return;
	}
	
	function totalizarProveedores(){
		var proveedores="";
		var entradas = "";
		for(var i=1;i<document.getElementById('TablaProveedorText').rows.length;i++){
			if(proveedores!=""){
				proveedores = proveedores + "~";
				entradas = entradas + "~";
			}
			proveedores = proveedores + document.getElementById('TablaProveedorText').rows[i].cells[0].innerHTML;
			entradas = entradas + document.getElementById('TablaProveedorText').rows[i].cells[1].innerHTML;
		}
		document.getElementById('ProveedoresCant').value = proveedores;
		document.getElementById('EntradasCant').value = entradas;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS CLIENTE/SALIDA
	 function agregarClientesSalidas(){
		var indice = document.getElementById('Clientes').selectedIndex;
		var indice2 = document.getElementById('Salidas').selectedIndex;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un Clientes.");
		}
		else if(indice2 == null || indice2 == 0){
			alert("Debe seleccionar una Salidas.");
		}
		else{
		   var tabla=document.getElementById('TablaClientesText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaClientesText" + totalClienteSalida;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaClientesText"+ totalClienteSalida;
		   input.idfila = "FilaClientesText" + totalClienteSalida;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarClienteEntrada;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td3.appendChild(document.createTextNode(" "));
			}else{
				td3.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(document.getElementById('Clientes').value));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(document.getElementById('Salidas').value));
			fila.appendChild(td2);
			fila.appendChild(td3);
			tabla.appendChild(fila);
			totalClienteSalida = totalClienteSalida + 1;
			//alert(totalClienteSalida);
			document.getElementById('Clientes').selectedIndex = 0;
			document.getElementById('Salidas').selectedIndex = 0;
		}
	 }
	 
	 function eliminarClienteEntrada(e){
		  var Tabla = document.getElementById("TablaClientesText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalClienteSalida = totalClienteSalida - 1;
		  return;
	}
	
	function totalizarClientes(){
		var clientes="";
		var salidas = "";
		for(var i=1;i<document.getElementById('TablaClientesText').rows.length;i++){
			if(clientes!=""){
				clientes = clientes + "~";
				salidas = salidas + "~";
			}
			clientes = clientes + document.getElementById('TablaClientesText').rows[i].cells[0].innerHTML;
			salidas = salidas + document.getElementById('TablaClientesText').rows[i].cells[1].innerHTML;
		}
		document.getElementById('ClientesCant').value = clientes;
		document.getElementById('SalidasCant').value = salidas;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS PLANEAR
	 function agregarDescripcionPlanear(){
		var indice = document.getElementById('codigoEmpleadoDescPlan').selectedIndex;
		var txtDesc = document.getElementById('DescripcionProcPlan').value;
		var txtRegistro = document.getElementById('RegistroPlan').value;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un responsable.");
		}
		else if(txtDesc == ""){
			alert("Debe ingresar una descripci&oacute;n.");
		}
		else{
		   var tabla=document.getElementById('TablaPlanearText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaPlanearText" + totalPlanear;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");
		   var td4 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaPlanearText"+ totalPlanear;
		   input.idfila = "FilaPlanearText" + totalPlanear;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarDescripcionPlanear;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td4.appendChild(document.createTextNode(" "));
			}else{
				td4.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(txtDesc));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(indice));
			fila.appendChild(td2);
			td3.appendChild(document.createTextNode(txtRegistro));
			fila.appendChild(td3);
			fila.appendChild(td4);
			tabla.appendChild(fila);
			totalPlanear = totalPlanear + 1;
			//alert(totalPlanear);
			document.getElementById('codigoEmpleadoDescPlan').selectedIndex = 0;
			document.getElementById('DescripcionProcPlan').value = "";
			document.getElementById('RegistroPlan').value = "";
		}
	 }
	 function eliminarDescripcionPlanear(e){
		  var Tabla = document.getElementById("TablaPlanearText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalPlanear = totalPlanear - 1;
		  return;
	}
	
	function totalizarPlanear(){
		var planear="";
		var responsablePl = "";
		var registroPl = "";
		for(var i=1;i<document.getElementById('TablaPlanearText').rows.length;i++){
			if(planear!=""){
				planear = planear + "~";
				responsablePl = responsablePl + "~";
				registroPl = registroPl + "~";
			}
			planear = planear + document.getElementById('TablaPlanearText').rows[i].cells[0].innerHTML;
			responsablePl = responsablePl + document.getElementById('TablaPlanearText').rows[i].cells[1].innerHTML;
			registroPl = registroPl + document.getElementById('TablaPlanearText').rows[i].cells[2].innerHTML;
		}
		document.getElementById('PlanearCant').value = planear;
		document.getElementById('RespPlanearCant').value = responsablePl;
		document.getElementById('RegiPlanearCant').value = registroPl;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS HACER
	function agregarDescripcionHacer(){
		var indice = document.getElementById('codigoEmpleadoDescHac').selectedIndex;
		var txtDesc = document.getElementById('DescripcionProcHac').value;
		var txtRegistro = document.getElementById('RegistroHac').value;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un responsable.");
		}
		else if(txtDesc == ""){
			alert("Debe ingresar una descripci&oacute;n.");
		}
		else{
		   var tabla=document.getElementById('TablaHacerText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaHacerText" + totalHacer;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");
		   var td4 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaHacerText"+ totalHacer;
		   input.idfila = "FilaHacerText" + totalHacer;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarDescripcionHacer;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td4.appendChild(document.createTextNode(" "));
			}else{
				td4.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(txtDesc));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(indice));
			fila.appendChild(td2);
			td3.appendChild(document.createTextNode(txtRegistro));
			fila.appendChild(td3);
			fila.appendChild(td4);
			tabla.appendChild(fila);
			totalHacer = totalHacer + 1;
			//alert(totalHacer);
			document.getElementById('codigoEmpleadoDescHac').selectedIndex = 0;
			document.getElementById('DescripcionProcHac').value = "";
			document.getElementById('RegistroHac').value = "";
		}
	 }
	 function eliminarDescripcionHacer(e){
		  var Tabla = document.getElementById("TablaHacerText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalHacer = totalHacer - 1;
		  return;
	}
	
	function totalizarHacer(){
		var hacer="";
		var responsableHa = "";
		var registroHa = "";
		for(var i=1;i<document.getElementById('TablaHacerText').rows.length;i++){
			if(hacer!=""){
				hacer = hacer + "~";
				responsableHa = responsableHa + "~";
				registroHa = registroHa + "~";
			}
			hacer = hacer + document.getElementById('TablaHacerText').rows[i].cells[0].innerHTML;
			responsableHa = responsableHa + document.getElementById('TablaHacerText').rows[i].cells[1].innerHTML;
			registroHa = registroHa + document.getElementById('TablaHacerText').rows[i].cells[2].innerHTML;
		}
		document.getElementById('HacerCant').value = hacer;
		document.getElementById('RespHacerCant').value = responsableHa;
		document.getElementById('RegiHacerCant').value = registroHa;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS VERIFICAR
	 function agregarDescripcionVerificar(){
		var indice = document.getElementById('codigoEmpleadoDescVer').selectedIndex;
		var txtDesc = document.getElementById('DescripcionProcVer').value;
		var txtRegistro = document.getElementById('RegistroVer').value;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un responsable.");
		}
		else if(txtDesc == ""){
			alert("Debe ingresar una descripci&oacute;n.");
		}
		else{
		   var tabla=document.getElementById('TablaVerificarText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaVerificarText" + totalVerificar;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");
		   var td4 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaVerificarText"+ totalVerificar;
		   input.idfila = "FilaVerificarText" + totalVerificar;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarDescripcionVerificar;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td4.appendChild(document.createTextNode(" "));
			}else{
				td4.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(txtDesc));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(indice));
			fila.appendChild(td2);
			td3.appendChild(document.createTextNode(txtRegistro));
			fila.appendChild(td3);
			fila.appendChild(td4);
			tabla.appendChild(fila);
			totalVerificar = totalVerificar + 1;
			//alert(totalVerificar);
			document.getElementById('codigoEmpleadoDescVer').selectedIndex = 0;
			document.getElementById('DescripcionProcVer').value = "";
			document.getElementById('RegistroVer').value = "";
		}
	 }
	 function eliminarDescripcionVerificar(e){
		  var Tabla = document.getElementById("TablaVerificarText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalVerificar = totalVerificar - 1;
		  return;
	}
	
	function totalizarVerificar(){
		var verificar="";
		var responsableVe = "";
		var registroVe = "";
		for(var i=1;i<document.getElementById('TablaVerificarText').rows.length;i++){
			if(verificar!=""){
				verificar = verificar + "~";
				responsableVe = responsableVe + "~";
				registroVe = registroVe + "~";
			}
			verificar = verificar + document.getElementById('TablaVerificarText').rows[i].cells[0].innerHTML;
			responsableVe = responsableVe + document.getElementById('TablaVerificarText').rows[i].cells[1].innerHTML;
			registroVe = registroVe + document.getElementById('TablaVerificarText').rows[i].cells[2].innerHTML;
		}
		document.getElementById('VerificarCant').value = verificar;
		document.getElementById('RespVerificarCant').value = responsableVe;
		document.getElementById('RegiVerificarCant').value = registroVe;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS ACTUAR
	 function agregarDescripcionActuar(){
		var indice = document.getElementById('codigoEmpleadoDescAct').selectedIndex;
		var txtDesc = document.getElementById('DescripcionProcAct').value;
		var txtRegistro = document.getElementById('RegistroAct').value;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un responsable.");
		}
		else if(txtDesc == ""){
			alert("Debe ingresar una descripci&oacute;n.");
		}
		else{
		   var tabla=document.getElementById('TablaActualText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaActuarText" + totalActual;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");
		   var td4 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaActuarText"+ totalActual;
		   input.idfila = "FilaActuarText" + totalActual;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarDescripcionActuar;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td4.appendChild(document.createTextNode(" "));
			}else{
				td4.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(txtDesc));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(indice));
			fila.appendChild(td2);
			td3.appendChild(document.createTextNode(txtRegistro));
			fila.appendChild(td3);
			fila.appendChild(td4);
			tabla.appendChild(fila);
			totalActual = totalActual + 1;
			//alert(totalActual);
			document.getElementById('codigoEmpleadoDescAct').selectedIndex = 0;
			document.getElementById('DescripcionProcAct').value = "";
			document.getElementById('RegistroAct').value = "";
		}
	 }
	 function eliminarDescripcionActuar(e){
		  var Tabla = document.getElementById("TablaActualText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalActual = totalActual - 1;
		  return;
	}
	
	function totalizarActuar(){
		var actuar="";
		var responsableAc = "";
		var registroAc = "";
		for(var i=1;i<document.getElementById('TablaActualText').rows.length;i++){
			if(actuar!=""){
				actuar = actuar + "~";
				responsableAc = responsableAc + "~";
				registroAc = registroAc + "~";
			}
			actuar = actuar + document.getElementById('TablaActualText').rows[i].cells[0].innerHTML;
			responsableAc = responsableAc + document.getElementById('TablaActualText').rows[i].cells[1].innerHTML;
			registroAc = registroAc + document.getElementById('TablaActualText').rows[i].cells[2].innerHTML;
		}
		document.getElementById('ActuarCant').value = actuar;
		document.getElementById('RespActuarCant').value = responsableAc;
		document.getElementById('RegiActuarCant').value = registroAc;
		return true;
	}
	
	// FUNCIONES PROCEDIMIENTOS RECURSOS
	 function agregarRecurso(){
		var indice = document.getElementById('tipoRecurso').selectedIndex;
		var txtDesc = document.getElementById('DescRecurso').value;
		if(indice == null || indice == 0){
			alert("Debe seleccionar un responsable.");
		}
		else if(txtDesc == ""){
			alert("Debe ingresar una descripci&oacute;n.");
		}
		else{
		   var tabla=document.getElementById('TablaRecursosText').getElementsByTagName("TBODY")[0];
		   var fila = document.createElement("TR"); 
		   fila.id = "FilaRecursoText" + totalRecursos;
		   
		   var td1 = document.createElement("TD");
		   var td2 = document.createElement("TD");
		   var td3 = document.createElement("TD");

		   input = document.createElement("input");
		   input.id = "FilaRecursoText"+ totalRecursos;
		   input.idfila = "FilaRecursoText" + totalRecursos;
		   input.name = "EliminarLinea";
		   input.type = "button";
		   input.value = "Eliminar";
		   input.className="submitLink";
		   input.onclick = eliminarRecurso;
		   
			if(document.forms[0]._operacion.value=="D"){
					 td3.appendChild(document.createTextNode(" "));
			}else{
				td3.appendChild(input);
			}
			
			td1.appendChild(document.createTextNode(indice));
			fila.appendChild(td1);
			td2.appendChild(document.createTextNode(txtDesc));
			fila.appendChild(td2);
			fila.appendChild(td3);
			tabla.appendChild(fila);
			totalRecursos = totalRecursos + 1;
			//alert(totalRecursos);
			document.getElementById('tipoRecurso').selectedIndex = 0;
			document.getElementById('DescRecurso').value = "";
			document.getElementById('RegistroAct').value = "";
		}
	 }
	 function eliminarRecurso(e){
		  var Tabla = document.getElementById("TablaRecursosText");
		  var obj;
		  if (navigator.appVersion.indexOf("MSIE")!=-1){
			 obj=window.event.srcElement;
		  }       
		  else if(navigator.userAgent.indexOf("Firefox")!=-1){
			obj=e.target;
		  }
		  Tabla.tBodies[0].removeChild(document.getElementById(obj.idfila));
		  totalRecursos = totalRecursos - 1;
		  return;
	}
	
	function totalizarRecurso(){
		var recurso="";
		var desRecurso = "";
		for(var i=1;i<document.getElementById('TablaRecursosText').rows.length;i++){
			if(recurso!=""){
				recurso = recurso + "~";
				desRecurso = desRecurso + "~";
			}
			recurso = recurso + document.getElementById('TablaRecursosText').rows[i].cells[0].innerHTML;
			desRecurso = desRecurso + document.getElementById('TablaRecursosText').rows[i].cells[1].innerHTML;
		}
		document.getElementById('RecursoCant').value = recurso;
		document.getElementById('DescRecursoCant').value = desRecurso;
		return true;
	}