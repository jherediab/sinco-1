var FormatoFecha = "AAAA-MM-DD";
var SepMiles = ",";
var SepDecimal = ".";
var AnoInicial = 1870;
var AnoFinal = 2025;

var Rutinas = {
  Version: '1.0.0.3',

   Browser: (function(){
    var ua = navigator.userAgent;
    var isOpera = Object.prototype.toString.call(window.opera) == '[object Opera]';
    return {
      IE:             !!window.attachEvent && !isOpera,
      Opera:          isOpera,
      WebKit:         ua.indexOf('AppleWebKit/') > -1,
      Gecko:          ua.indexOf('Gecko') > -1 && ua.indexOf('KHTML') === -1,
      MobileSafari:   /Apple.*Mobile/.test(ua)
    }
  })()
};


function f_salto2(event){
   if (Rutinas.Browser.IE){
	   var tecla = (document.all) ? event.keyCode : event.which;
	   if (tecla ==13){
		   event.returnValue=false;
		   return false;
	   }
   }
   return true;
 }

function f_salto(event){
   var tecla = (document.all) ? event.keyCode : event.which;

   if (tecla ==9){
     return false;
   }
   if (tecla ==13){
      tecla=9;
   }
   if (event.ctrlKey){
      return true;
   }
   if (event.altKey){
      return true;
   }
   
   var retorno=true;
   if (tecla==9){
      if (!document.all) {
        document.all = document.getElementsByTagName("*");
      }
      
      var objActual;
      if (Rutinas.Browser.IE){
         objActual=window.event.srcElement;
         event.keyCode=9;      
         retorno=false;
      }       
      else {
         objActual=event.target;
         retorno=false;
      }
      for (var i=0;i<document.all.length;i++){
         if (document.all[i]==objActual){
            if (event.shiftKey && tecla==9){
               for (var j=i-1;j>=0;j--){
                  if (!document.all[j].disabled && !document.all[j].readOnly &&
                      document.all[j].style.display == "" && document.all[j].type){
                      var  visible=true;
                      var p1 = document.all[j].parentNode;
                      while (p1){
                        if (p1.style)
                           if (p1.style.display == "none")
                              visible=false;
                           p1=p1.parentNone;
                      }
                      
                     if (document.all[j].type!="hidden" && visible){
                        try {
                           event.returnValue=false;
                           document.all[j].focus();
                           return false;
                        }
                        catch(e){                        
                        }
                     }
                  }
                  if (j==0)
                    j=document.all.length;
               }
          }
          if (!event.shiftKey && tecla==9){
               for (var j=i+1;j<document.all.length;j++){
                  if (!document.all[j].disabled && !document.all[j].readOnly &&
                      document.all[j].style.display!="none" && document.all[j].type){
                      var  visible=true;
                      var p1 = document.all[j].parentNode;
                      while (p1){
                        if (p1.style)
                           if (p1.style.display=="none")
                              visible=false;
                           p1=p1.parentNone;
                      }
                      
                     if (document.all[j].type!="hidden" && visible){
                        try {
                          document.all[j].focus();
                          event.returnValue=false;
                           return false;
                        }
                        catch(e){                        
                        }
                     }
                  }
                  if (j==document.all.length-1)
                    j=-1;
               }
          }
       }
    }
  }
  return retorno;
}


function validarTecla(event,li_tipo){
   var tecla = 0;
   if (Rutinas.Browser.IE){
      tecla = event.keyCode ;
   }
   else {
     tecla=event.which;
   }
    
   if (tecla==0) {
     tecla=((document.all) ? event.keyCode : event.which); 
   }
   
    if (tecla==8) {return true; }
    if (tecla==0) {return true; }
    
    if (event.ctrlKey){
      return true;
    }
    if (event.altKey){
      return true;
    }

    // retornar si las teclas son enter , tab o shit
    if (tecla==13||tecla==9){
       return true;
    }

    // retornar cuando la tecla pulsada es feclas 
    var patron =""; 
    switch(li_tipo){
		case "AL": // Elimina Unicamente comilla y doble comilla
		case "ALSE": // Elimina Unicamente comilla y doble comilla
		case "NO": // Permite unicamente numeros y letras
		case "LE": // Permite unicamente letras, espacio blanco, ñ, Ñ, tilde de vocales mayusculas y minusculas
                  patron = /[A-Za-z0-9{}_áéíóúñÑ;,:.!#@$%&/()=?¡"\s-]/; 
			break;
		case "F2": // Unicamente permite numeros y el /
                  patron =/[0123456789/]/; 
			break;
		case "FE": // Unicamente permite numeros y el -
                  patron =/[0123456789/-]/; 
			break;
		case "EN": // Unicamente permite numeros
                  patron = /\d/;  
			break;
		case "DE": // Permite numeros y el punto decimal
                  patron =/[0123456789.-]/; 
			break;
		case "HO": // Unicamente permite numeros y el :
                  patron =/[0123456789:]/; 
			break;
    }	
    var te = String.fromCharCode(tecla); 
    if (patron.test(te)==false ){
      if (Rutinas.Browser.IE){
        event.returnValue=false;
      }
      else {       
        event.preventDefault();
      }       
    }
    
    if (li_tipo=="ALSE" && tecla==13){
      if (Rutinas.Browser.IE){
        event.returnValue=false;
      }
      else {       
        event.preventDefault();
      }       
    }
}

function validarTecla2(e){
  var obj;
    var tecla ;
    if (Rutinas.Browser.IE){
       obj   = window.event.srcElement;
       tecla = window.event.keyCode;
    }       
    else {
      obj=e.target;
      tecla = e.which
    }
    var li_tipo=obj.dato;
    
    if (tecla==0 ||tecla==13||tecla==9||tecla==8){
       return true;
    } 
    var patron =""; 
    switch(li_tipo){
		case "AL": // Elimina Unicamente comilla y doble comilla
		case "NO": // Permite unicamente numeros y letras
		case "LE": // Permite unicamente letras, espacio blanco, ñ, Ñ, tilde de vocales mayusculas y minusculas
                  patron = /[A-Za-z0-9{}áéíóúñÑ;,:.!#@$%&/()=?¡"\s-]/; 
			break;
		case "F2": // Unicamente permite numeros y el /
                  patron =/[0123456789/]/; 
			break;
		case "FE": // Unicamente permite numeros y el -
                  patron =/[0123456789-]/; 
			break;
		case "EN": // Unicamente permite numeros
                  patron = /\d/;  
			break;
		case "DE": // Permite numeros y el punto decimal
                  patron =/[0123456789.-]/; 
			break;
		case "HO": // Unicamente permite numeros y el :
                  patron =/[0123456789:]/; 
			break;
    }	
    if (patron=="") patron=/[A-Za-z0-9{}áéíóúñÑ;,:.!#@$%&/()=?¡"\s-]/;
    te = String.fromCharCode(tecla); 
    if (patron.test(te)==false){
      if (Rutinas.Browser.IE){
         window.event.returnValue=false;
      }
      else {       
       e.preventDefault();
      }
    }
}

function formatnum ( fstring )
{
    var fnum = "";
    var punto = -10;

    if ( isNaN(fstring) == true )
      return fstring;

    for ( i=0; i <  fstring.length; i++ )
    {
        if ( fstring.charAt(i) == "." )
        {
            fnum =  fstring.substring(i,fstring.length);
            punto = i - 1;
        }
    }

    if ( punto == -10 )
      punto = fstring.length - 1;

    for ( ; punto >= 3 ; punto -= 3 )
      fnum = "," + fstring.substring(punto - 2,punto+1)+ fnum;

    punto +=  1;
    fnum = fstring.substring(0,punto) + fnum;
    return fnum;
}

function desformatnum  ( fstring )
{
    var fnum = "";
    var i = 0;
    for ( i=0; i <  fstring.length; i++ )
    {
        if ( fstring.charAt(i) != "," && fstring.charAt(i) != " ")
        fnum = fnum + fstring.charAt(i) ;
    }

    if ( isNaN(fnum) )
      fnum = "";
    return fnum;
}

function limpieza(){
  if (!document.all) {
    document.all = document.getElementsByTagName("*");
  }
  for (var i=0;i<document.all.length;i++){
        var caja=document.all[i].title;
        var contenido=""+document.all[i].value;
        if (caja=="numero"){
           document.all[i].value = desformatnum(contenido);
         }
  }
}

function textCounter(field, countfield, maxlimit) {
  if ((field.value.length) > maxlimit)
    field.value = field.value.substring(0, maxlimit);
  else 
    countfield.value = maxlimit - (field.value.length);
}

function Ocultar(){
 try{   
   for (var i=0;i<document.forms.length;i++){
     len     =       document.forms[i].elements.length;
     var     index   =       0;
     for( index=0; index < len; index++ ){
       if (document.forms[i].elements[index].className!="BOB" && (document.forms[i].elements[index].type=="submit" || document.forms[i].elements[index].type=="button")){
          document.forms[i].elements[index].style.display='none';
      }
     }    
   }
  }
  catch(e){
  }
  return true;
}

function Mostrar(){
 try{   
   for (var i=0;i<document.forms.length;i++){
     len     =       document.forms[i].elements.length;
     var     index   =       0;
     for( index=0; index < len; index++ ){
       if (document.forms[i].elements[index].type=="submit" || document.forms[i].elements[index].type=="button"){
          document.forms[i].elements[index].style.display='';
      }
     }    
   }
  }
  catch(e){
  }
  return true;
}


function Mostrar2(){
 try{   
   for (var i=0;i<document.forms.length;i++){
     len     =       document.forms[i].elements.length;
     var     index   =       0;
     for( index=0; index < len; index++ ){
       if (document.forms[i].elements[index].type=="submit" || document.forms[i].elements[index].type=="button"){		   
		   if(document.forms[i].elements[index].id=="btnEditar"){		   
		   }else{
			        document.forms[i].elements[index].style.display=''; 
		   }
      }
     }    
   }
  }
  catch(e){
  }
  return true;
}

function toggle(id,valor) {
     obj = document.getElementById(id);
     hijo = obj.childNodes[0]
     if (valor.checked){
         hijo.style.display = ""
     }
     else {
         hijo.style.display = "none"
     }
     
 }


function validar(Objeto, Titulo, TipoDato, Decimales, Obliga, ValorMinimo, ValorMaximo)
{
	var Valor = desblancos(Objeto.value);
	Objeto.value = Valor;
	if(Obliga == "OBL")
	{
		if (Valor.length < 1)
		{
			mostrar_error(1, Titulo);
			if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
				 Objeto.focus();
			return false;
		}
	}
	if(TipoDato == "ENT" || TipoDato == "DEC"){
		if (Valor.length > 0)
		{
			Valor = desformatnum(Valor)
			if (isNaN(parseFloat(Valor,10)))
			{
				mostrar_error(2, Titulo, Valor);
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
			if (parseFloat(Valor,10) < ValorMinimo)
			{
				mostrar_error(3, Titulo, Valor, ValorMinimo.toString());
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
			if (parseFloat(Valor,10) > ValorMaximo)
			{
				mostrar_error(4, Titulo, Valor, ValorMaximo.toString());
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
			var intPos = Valor.indexOf(".");
			if(TipoDato == "DEC")
			{
				if(intPos > 0)
				{
					if(Decimales > 0)
					{
						Objeto.value = Valor.substring(intPos + 1, Valor.length);
						if(!validar(Objeto, "numero de decimales de " + Titulo, "ENT", 0, "OBL", 0, Math.pow(10, Decimales) - 1))
						{
							Objeto.value = Valor;
							return false;
						}
						Objeto.value = Valor;
					}
					else
					{
						if(intPos > 0)
						{
							mostrar_error(5, Titulo);
							if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
								Objeto.focus();
							return false;
						}
					}
				}
			}
			else
			{
				if(intPos > 0)
				{
					mostrar_error(5, Titulo);
					if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
						Objeto.focus();
					return false;
				}
			}
		}
	}

	if(TipoDato == "FEC"){
	
		if (Valor.length > 0){
			if (!jesfecha(Valor)){
				mostrar_error(6, Titulo, FormatoFecha);
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
		}
	}
	if(TipoDato == "FECHAHORA"){
	
		if (Valor.length > 0){
			if (!jesfechaHora(Valor)){
				mostrar_error(6, Titulo, FormatoFecha);
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
		}
	}
	
	if(TipoDato == "EMA")
	{
		if (Valor.length > 0)
		{
			if (!validarEmail(Valor))
			{
				mostrar_error(16, Titulo, Valor);
				if(Objeto.type != "hidden" && Objeto.disabled == false && Objeto.style.display != "none" && Objeto.focus() != null)
					Objeto.focus();
				return false;
			}
		}
	}
	return true;
}

function jesfecha(fstring)
{
	var ano;
	var mes;
	var dia;
	if(FormatoFecha == "AAAA-MM-DD"){
		ano = parseInt(fstring.substring(0,4),10);
		mes = parseInt(fstring.substring(5,7),10);
		dia = parseInt(fstring.substring(8,10),10);
	}
	else if(FormatoFecha == "DD/MM/AAAA"){
		dia = parseInt(fstring.substring(0,2),10);
		mes = parseInt(fstring.substring(3,5),10);
		ano = parseInt(fstring.substring(6,10),10);
	}
	var vanoini = AnoInicial;
	var vanofin = AnoFinal;

       /*
	if(isNaN(parseInt(anoini, 10)) == false)
		vanoini = parseInt(anoini, 10);

	if(isNaN(parseInt(anofin, 10)) == false)
		vanofin = parseInt(anofin, 10);
		
      */

	if(fstring.length != 10)
	{
		mostrar_error(7, fstring, FormatoFecha);
		return false;
	}
	if(isNaN(dia) == true)
	{
		mostrar_error(8, fstring.substring(8, 10));
		return false;
	}
	if(isNaN(mes) == true)
	{
		mostrar_error(9, fstring.substring(5, 7));
		return false;
	}
	if(isNaN(ano) == true)
	{
		mostrar_error(10, fstring.substring(0, 4));
		return false;
	}
	if(ano < vanoini || ano > vanofin)
	{
		mostrar_error(11, ano, vanoini, vanofin);
		return false;
	}
	if(mes < 1 || mes > 12)
	{
		mostrar_error(12, mes);
		return false;
	}
	if(dia < 1 || dia > 31)
	{
		mostrar_error(13, dia);
		return false;
	}
	var bisiesto = (Math.floor(ano/4) * 4 ) - ano;
	/* si da cero es bisiesto */
	if(( (mes==2 || mes==4 || mes==6 || mes==9 || mes==11) && dia==31)
	   || (mes==2 && bisiesto==0 && dia>29) || (mes==2 && bisiesto!=0 && dia>28))
	{
		mostrar_error(14, dia);
		return false;
	}
	return true;
}


function jeshora(objeto,nombreCampo,Obliga)
{
   var fstring=objeto.value;
   var Valor = desblancos(objeto.value);

   	if(Obliga == "OBL")
	{
		if (Valor.length < 1)
		{
			mostrar_error(1, nombreCampo);
			if(objeto.type != "hidden" && objeto.disabled == false && objeto.style.display != "none" && objeto.focus() != null)
				 Objeto.focus();
			return false;
		}
	}   	
	var hora;
	var minuto;
	hora = parseInt(fstring.substring(0,2),10);
	minuto = parseInt(fstring.substring(3,5),10);
	
	if(hora<0||hora>24){
		alert("La Hora Debe Estar de 0 a 23 ");
		return false;
	}
	if(minuto<0||minuto>60){
		alert("Los Minutos Deben Estar de 0 a 59");
		return false;
	}
	
	return true;
}



function jesfechaHora(fstring)
{
	var ano;
	var mes;
	var dia;
	if(FormatoFecha == "AAAA-MM-DD"){
		ano = parseInt(fstring.substring(0,4),10);
		mes = parseInt(fstring.substring(5,7),10);
		dia = parseInt(fstring.substring(8,10),10);
	}
	else if(FormatoFecha == "DD/MM/AAAA"){
		dia = parseInt(fstring.substring(0,2),10);
		mes = parseInt(fstring.substring(3,5),10);
		ano = parseInt(fstring.substring(6,10),10);
	}
	var vanoini = AnoInicial;
	var vanofin = AnoFinal;

	if(fstring.length != 16)
	{
		mostrar_error(25, fstring, FormatoFecha+" hh:mi");
		return false;
	}
	if(isNaN(dia) == true)
	{
		mostrar_error(8, fstring.substring(8, 10));
		return false;
	}
	if(isNaN(mes) == true)
	{
		mostrar_error(9, fstring.substring(5, 7));
		return false;
	}
	if(isNaN(ano) == true)
	{
		mostrar_error(10, fstring.substring(0, 4));
		return false;
	}
	if(ano < vanoini || ano > vanofin)
	{
		mostrar_error(11, ano, vanoini, vanofin);
		return false;
	}
	if(mes < 1 || mes > 12)
	{
		mostrar_error(12, mes);
		return false;
	}
	if(dia < 1 || dia > 31)
	{
		mostrar_error(13, dia);
		return false;
	}
	var bisiesto = (Math.floor(ano/4) * 4 ) - ano;
	/* si da cero es bisiesto */
	if(( (mes==2 || mes==4 || mes==6 || mes==9 || mes==11) && dia==31)
	   || (mes==2 && bisiesto==0 && dia>29) || (mes==2 && bisiesto!=0 && dia>28))
	{
		mostrar_error(14, dia);
		return false;
	}
	return true;
}


function mostrar_error(error)
{
	var args = mostrar_error.arguments;
	var Errores = new Array();
	Errores[0] = "Sesion no valida.";
	Errores[1] = "El campo " + args[1] + " es obligatorio.";
	Errores[2] = "El campo " + args[1] + "( " + args[2] + " ) debe ser numérico.";
	Errores[3] = "El campo " + args[1] + "( " + args[2] + " ) debe ser mayor o igual a " + args[3];
	Errores[4] = "El campo " + args[1] + "( " + args[2] + " ) debe ser menor o igual a " + args[3];
	Errores[5] = "El campo " + args[1] + " no debe tener decimales ";
	Errores[6] = "El campo " + args[1] + " debe ser una fecha válida (" + args[2] + ")";
	Errores[7] = "La fecha " + args[1] + " debe estar en formato " + args[2];
	Errores[8] = "El día " + args[1] + " no es un número.";
	Errores[9] = "El mes " + args[1] + " no es un número.";
	Errores[10] = "El año " + args[1] + " no es un número.";
	Errores[11] = "El año " + args[1] + " debe estar entre " + args[2] + " y " + args[3] + ".";
	Errores[12] = "El mes " + args[1] + " es inválido.";
	Errores[13] = "El día " + args[1] + " es inválido.";
	Errores[14] = "El día " + args[1] + " es inválido para el mes digitado.";
	Errores[15] = "El campo " + args[1] + " no puede tener mas de " + args[2] + " caracteres.";
	Errores[16] = "El campo " + args[1] + "( " + args[2] + " ) debe ser un email válido.";
	Errores[17] = "";
	Errores[18] = "";
	Errores[19] = "";
	Errores[20] = "";
	Errores[21] = "El campo " + args[1] + " debe tener mínimo " + args[2] + " caracter(es).";
	Errores[22] = "El campo " + args[1] + " debe ser igual al campo " + args[2] + ".";
	Errores[23] = "El usuario se encuentra INACTVO. Por favor comunicarse con el CALL CENTER";
	Errores[24] = "El campo " + args[1] + " debe tener máximo " + args[2] + " caracter(es).";
	Errores[25] = "El campo " + args[1] + " debe ser una fecha válida (" + args[2] + ")";

	alert(Errores[error]);
}
function desblancos(fstring)
{
	var fnum = "";
	var i=0;
	var letra = "N";
	for(i=0; i<fstring.length; i++)
		if((fstring.charAt(i) != " " || letra == "S") && fstring.charAt(i) != '"' && fstring.charAt(i) != "'")
		{
			letra = "S";
			fnum = fnum + fstring.charAt(i);
		}
	for(i=fnum.length-1; i>=0; i--)
		if(fnum.charAt(i) == " ")
			fnum = fnum.substring(0,i);
		else
			break;
	return fnum;
}


function crearDate(fstring){
     var ano;
     var mes;
     var dia;

     if(FormatoFecha == "AAAA-MM-DD"){
		ano = parseInt(fstring.substring(0,4),10);
		mes = parseInt(fstring.substring(5,7),10)-1;
		dia = parseInt(fstring.substring(8,10),10);
     }
     else if(FormatoFecha == "DD/MM/AAAA"){
		dia = parseInt(fstring.substring(0,2),10);
		mes = parseInt(fstring.substring(3,5),10)-1;
		ano = parseInt(fstring.substring(6,10),10);
     }

     var someDate = new Date(ano,mes,dia);
     return someDate;
}

function getFechaActual(){
      var d = new Date();
	  var hoy = new Date(d.getFullYear(),d.getMonth(),d.getDate());
      return hoy;
}

function FechaMenor(dtFechaIni, dtFechaFin, Titulo)
{
    var dtFechaIni, dtFechaFin, valFechaIni, valFechaFin;

    valFechaIni = parseInt(dtFechaIni.substring(6,10), 10) * 365;
    valFechaIni = valFechaIni + parseInt(dtFechaIni.substring(3,5), 10) * 30;
    valFechaIni = valFechaIni + parseInt(dtFechaIni.substring(0,2), 10);

    valFechaFin = parseInt(dtFechaFin.substring(6,10),10) * 365;
    valFechaFin = valFechaFin + parseInt(dtFechaFin.substring(3,5), 10) * 30;
    valFechaFin = valFechaFin + parseInt(dtFechaFin.substring(0,2), 10);

    if(valFechaFin<valFechaIni)
	{
        alert(Titulo);
        return false;
    }
    return true
}

function validarEmail(email) 
{
     if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
       return (true)
     }
     return (false);
}

function fMarcar(flag, my_form, field_name ){
len     =       my_form.elements.length;
var     index   =       0;
   for( index=0; index < len; index++ ){
        if( (my_form.elements[index].name).substring(0,field_name.length)== field_name ){
            my_form.elements[index].checked=flag;
        }
    }
}

function openPG(pagina)
{  
    var w = window.open(pagina);    
}

function llenarCombo(objeto,servicios,identificador,textoMostrar){
   objeto.length=0;   
   for (var i=0;i<servicios.length;i++){
     var optionObj = new Option(getNodeValue(servicios[i],textoMostrar),getNodeValue(servicios[i],identificador));
     objeto.options[i] = optionObj;
   }
}

function llenarComboBlanco(objeto,servicios,identificador,textoMostrar){
   objeto.length=0;   
   var optionObj = new Option("","");
   objeto.options[0] = optionObj;
   for (var i=0;i<servicios.length;i++){
     optionObj = new Option(getNodeValue(servicios[i],textoMostrar),getNodeValue(servicios[i],identificador));
     objeto.options[i+1] = optionObj;
   }
}

function llenarTextBox(objeto,registro,campo){
 	var elestado=getNodeValue(registro[0],'estado');
 	if(elestado=="ok"){
 		var eldato=getNodeValue(registro[0],campo);
 		objeto.value=eldato;  
 	}
}	
   
function getNodeValue(obj,tag){
  try {
   return obj.getElementsByTagName(tag)[0].firstChild.nodeValue;
  }
  catch (e){
  }
  return "";
}

function llenarBlancoAtr(objeto,servicios,identificador,textoMostrar){
   objeto.length=0;   
   var optionObj = new Option("","");
   objeto.options[0] = optionObj;
   for (var i=0;i<servicios.length;i++){
        var anchor = servicios[i];
        optionObj = new Option(anchor.getAttribute(textoMostrar),anchor.getAttribute(identificador));
        objeto.options[i+1] = optionObj;
   }
}



function regresar(){
	history.back(-1);
}

/* Convertir cadena a valor numerico */
function StringToFloat(str){
 if (str.length==0){
    return 0;
 }
 try {
   return parseFloat(desformatnum (str));
 }
 catch(e){
 }
 return 0;
}


function imprimir(){
  window.print();
} 

function CerrarVentana(){
 try {
   window.open('','_parent','');
 }
 catch(e){}
 window.close();
}


function getCookie(c_name){
if (document.cookie.length>0) {
  c_start=document.cookie.indexOf(c_name + "=");
  if (c_start!=-1){ 
    c_start=c_start + c_name.length+1; 
    c_end=document.cookie.indexOf(";",c_start);
    if (c_end==-1) c_end=document.cookie.length;
       return unescape(document.cookie.substring(c_start,c_end));
    } 
  }
  return "";
}

function setCookie(c_name,value,expiredays){
  var exdate=new Date();
  exdate.setDate(exdate.getDate()+expiredays);
  document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

function checkCookie(){
username=getCookie('username');
if (username!=null && username!=""){
     alert('Welcome again '+username+'!');
  }
  else {
    username=prompt('Please enter your name:',"");
    if (username!=null && username!=""){
       setCookie('username',username,365);
    }
  }
}



function show_help(HPage) {
    newWindow = window.open("VerAyuda.po?_operacion=V&pantalla=" + HPage, "Ayuda", 
				"menubar=1, scrollbars=1, resizable=1, location=1, width=400, height=400");
}



function BorrarTabla(identificador){

	var tabla = document.getElementById(identificador);
	var numFilas = tabla.rows.length;
	
	while (numFilas>0){
  	   tabla.deleteRow(0);
	   var numFilas = tabla.rows.length;
	}
}

 
 function stopEvent(e) {
    if (!e) e = window.event;
    if (e.stopPropagation) {
        e.stopPropagation();
    } else {
        e.cancelBubble = true;
    }
  }
  
/*  
String.prototype.trim = function(){return 
(
  this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""))
}
  
String.prototype.startsWith = function(str) 
{ return (this.match("^"+str)==str)  
}

*/