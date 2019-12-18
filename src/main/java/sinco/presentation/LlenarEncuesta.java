/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLImageElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.Encuestados;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DetalleEncuestaDAO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.data.EncuestadosDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.LlenarEncuesta;
/*     */ import sinco.presentation.LlenarEncuestaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LlenarEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   private LlenarEncuestaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  45 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  47 */     int miEncuesta = Integer.parseInt(comms.request.getParameter("encuesta"));
/*     */     
/*  49 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  50 */     EncuestaDTO regEncuesta = rsEncuesta.cargaRegistro(miEncuesta);
/*  51 */     rsEncuesta.close();
/*     */     
/*  53 */     if (regEncuesta == null) {
/*  54 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=Encuestas"));
/*     */     }
/*     */     
/*  57 */     ServiciosDAO serf = new ServiciosDAO();
/*  58 */     ServiciosDTO regServicio = serf.cargarRegistro(regEncuesta.getCodigo_servicio());
/*  59 */     serf.close();
/*     */     
/*  61 */     if (regServicio == null) {
/*  62 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ServicioNoDisponible"));
/*     */     }
/*     */     
/*  65 */     this.pagHTML = (LlenarEncuestaHTML)comms.xmlcFactory.create(LlenarEncuestaHTML.class);
/*     */     
/*  67 */     this.pagHTML.setTextNumeroEncuesta("" + miEncuesta);
/*  68 */     this.pagHTML.setTextServicio("" + regEncuesta.getNombreServicio());
/*  69 */     this.pagHTML.setTextEstado("" + regEncuesta.getNombreEstado());
/*     */ 
/*     */     
/*  72 */     this.pagHTML.getElementEncuesta().setValue("" + miEncuesta);
/*  73 */     this.pagHTML.getElementIdEncuestaModificar().setValue("" + miEncuesta);
/*  74 */     this.pagHTML.getElementIdEncuestaEnviar().setValue("" + miEncuesta);
/*  75 */     this.pagHTML.getElementIdEncuestaCancelar().setValue("" + miEncuesta);
/*  76 */     this.pagHTML.getElementIdEncuestaExtender().setValue("" + miEncuesta);
/*  77 */     this.pagHTML.getElementIdEncuestaBorrar().setValue("" + miEncuesta);
/*  78 */     this.pagHTML.getElementIdEncuestaPersonas().setValue("" + miEncuesta);
/*     */     
/*  80 */     caracteristicas(regEncuesta.getNumero(), regServicio.getTipoServicio(), regEncuesta.getCodigo_servicio());
/*     */     
/*  82 */     encuestados(miEncuesta);
/*     */     
/*  84 */     if (!regServicio.getTipoServicio().equals(Integer.toString(1)) && !regServicio.getTipoServicio().equals(Integer.toString(5))) {
/*  85 */       Element divPadre = this.pagHTML.getElementConvocatoria();
/*  86 */       divPadre.getParentNode().removeChild(divPadre);
/*     */     } 
/*     */     
/*  89 */     if (!regServicio.getTipoServicio().equals(Integer.toString(2))) {
/*  90 */       Element divPadre = this.pagHTML.getElementAuditoria();
/*  91 */       divPadre.getParentNode().removeChild(divPadre);
/*     */     } 
/*     */     
/*  94 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  95 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void caracteristicas(int miEncuesta, String tipoServicio, int codigoServicio) {
/* 104 */     boolean hayExtension = false;
/* 105 */     boolean haycaracteristicas = false;
/* 106 */     if (!tipoServicio.equals(Integer.toString(2))) {
/*     */       
/* 108 */       CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*     */       
/* 110 */       HTMLTableSectionElement tabla = this.pagHTML.getElementCaracteristicas();
/* 111 */       HTMLSelectElement extensiones = this.pagHTML.getElementExtensiones();
/*     */       
/* 113 */       CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 114 */       Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigoServicio, "C", "A");
/* 115 */       rsCaracteristicas.close();
/*     */ 
/*     */       
/* 118 */       boolean fondo = true;
/* 119 */       String cadenaFechas = "";
/*     */       
/* 121 */       Iterator iterator = arr.iterator();
/* 122 */       while (iterator.hasNext()) {
/* 123 */         CaracteristicasDTO car = (CaracteristicasDTO)iterator.next();
/* 124 */         haycaracteristicas = true;
/* 125 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 126 */         HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/* 127 */         HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */         
/* 129 */         fondo = !fondo;
/* 130 */         eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */         
/* 132 */         tdCaracteristica.appendChild(this.pagHTML.createTextNode(((car.getObliga() == true) ? "* " : "  ") + car.getDescripcion()));
/*     */         
/* 134 */         DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/* 135 */         dsf.cargarParaSolicitud(miEncuesta, car.getCodigo());
/* 136 */         DetalleSolicitudDTO ds = dsf.next();
/* 137 */         boolean hay = false;
/* 138 */         while (ds != null) {
/* 139 */           if (car.getTipo().equals("2")) {
/* 140 */             tdDescripcion.appendChild(this.pagHTML.createTextNode("" + leerDescripcion(rsTGen, car.getCodigo(), ds.getValor())));
/*     */           } else {
/*     */             
/* 143 */             tdDescripcion.appendChild(this.pagHTML.createTextNode(ds.getObservacion() + ". "));
/*     */           } 
/* 145 */           ds = dsf.next();
/* 146 */           hay = true;
/*     */         } 
/* 148 */         dsf.close();
/*     */         
/* 150 */         if (!hay) {
/* 151 */           HTMLSelectElement combo; HTMLInputElement inp; switch (Integer.parseInt(car.getTipo())) {
/*     */             case 1:
/*     */             case 3:
/*     */             case 4:
/* 155 */               inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 156 */               inp.setMaxLength(200);
/*     */               
/* 158 */               if (car.getLongitud() >= 200) {
/* 159 */                 inp.setSize("70");
/*     */               }
/* 161 */               else if (car.getLongitud() <= 20) {
/* 162 */                 inp.setSize("15");
/*     */               }
/* 164 */               else if (car.getLongitud() > 20 && car.getLongitud() < 100) {
/* 165 */                 inp.setSize("30");
/*     */               } else {
/*     */                 
/* 168 */                 inp.setSize("40");
/*     */               } 
/*     */               
/* 171 */               inp.setName("" + car.getCodigo());
/* 172 */               inp.setId("" + car.getCodigo());
/*     */               
/* 174 */               inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 175 */               inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*     */               
/* 177 */               inp.setAttributeNode(newAttr("obliga", "" + ((car.getObliga() == true) ? "S" : "N")));
/* 178 */               inp.setAttributeNode(newAttr("tipo", "" + car.getTipo()));
/*     */               
/* 180 */               switch (Integer.parseInt(car.getTipo())) {
/*     */                 case 1:
/* 182 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*     */                   break;
/*     */                 case 3:
/* 185 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/*     */                   break;
/*     */                 case 4:
/* 188 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*     */                   break;
/*     */               } 
/*     */               
/* 192 */               if (car.getNombreProcedimiento().length() > 0) {
/* 193 */                 inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + car.getNombreProcedimiento() + "'," + car.getCaracteristicaAnida() + ");"));
/*     */               }
/*     */               
/* 196 */               tdDescripcion.appendChild(inp);
/*     */               
/* 198 */               if (car.getTipo().equals("4")) {
/* 199 */                 cadenaFechas = cadenaFechas + agregarCapturaFecha("" + car.getCodigo());
/* 200 */                 HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/* 201 */                 inp2.setSrc("media/calendario.jpg");
/* 202 */                 inp2.setId("b" + car.getCodigo());
/* 203 */                 inp2.setAlt("Calendario");
/* 204 */                 inp2.setHeight("20");
/* 205 */                 inp2.setWidth("20");
/* 206 */                 tdDescripcion.appendChild(inp2);
/*     */               } 
/*     */               
/* 209 */               if (car.getNombreProcedimiento().length() > 0) {
/* 210 */                 HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/* 211 */                 inp3.setId("msg" + car.getCodigo());
/* 212 */                 inp3.setAttributeNode(newAttr("class", "error"));
/* 213 */                 tdDescripcion.appendChild(inp3);
/*     */               } 
/*     */               break;
/*     */             
/*     */             case 2:
/*     */             case 8:
/* 219 */               combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 220 */               combo.setName("" + car.getCodigo());
/* 221 */               combo.setId("" + car.getCodigo());
/*     */               
/* 223 */               if (car.getCaracteristicaAnida() > 0) {
/* 224 */                 combo.setAttributeNode(newAttr("onchange", "buscarAnidadas(this.name,this.value," + car.getCaracteristicaAnida() + ");"));
/*     */               }
/*     */               
/* 227 */               llenarCombo(rsTGen, combo, car.getCodigo());
/* 228 */               combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 229 */               combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 230 */               tdDescripcion.appendChild(combo);
/*     */               break;
/*     */           } 
/*     */         
/* 234 */         } else if (!car.getTipo().equals("2")) {
/* 235 */           HTMLOptionElement extension = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 236 */           extension.setValue("" + car.getCodigo());
/* 237 */           extension.appendChild(this.pagHTML.createTextNode(car.getDescripcion()));
/* 238 */           extensiones.appendChild(extension);
/* 239 */           hayExtension = true;
/*     */         } 
/*     */         
/* 242 */         eltr.appendChild(tdCaracteristica);
/* 243 */         eltr.appendChild(tdDescripcion);
/* 244 */         tabla.appendChild(eltr);
/*     */       } 
/* 246 */       rsTGen.close();
/* 247 */       arr.clear();
/*     */ 
/*     */ 
/*     */       
/* 251 */       if (!haycaracteristicas) {
/* 252 */         Element formacaracteristicas = this.pagHTML.getElementNueva();
/* 253 */         formacaracteristicas.getParentNode().removeChild(formacaracteristicas);
/*     */       } 
/*     */       
/* 256 */       if (!hayExtension) {
/* 257 */         Element formaExtension = this.pagHTML.getElementExtender();
/* 258 */         formaExtension.getParentNode().removeChild(formaExtension);
/*     */       } 
/*     */       
/* 261 */       if (cadenaFechas.length() > 0) {
/* 262 */         this.pagHTML.setTextJSValfechas(cadenaFechas);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void encuestados(int miEncuesta) {
/* 275 */     EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/* 276 */     rsEncuestados.cargarTodos(miEncuesta);
/* 277 */     Encuestados regEncuestados = rsEncuestados.next();
/* 278 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 279 */     boolean fondo = true;
/* 280 */     boolean hay = false;
/* 281 */     while (regEncuestados != null) {
/*     */       
/* 283 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 285 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 287 */       fondo = !fondo;
/* 288 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 290 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 291 */       checkbox.setAttribute("type", "checkbox");
/* 292 */       checkbox.setName("E_" + regEncuestados.getProveedor() + "_" + regEncuestados.getAreaProveedor());
/* 293 */       tdMarca.appendChild(checkbox);
/* 294 */       eltr.appendChild(tdMarca);
/*     */       
/* 296 */       eltr.appendChild(newtd("" + regEncuestados.getNombreAreaProveedor(), false));
/* 297 */       eltr.appendChild(newtd("" + regEncuestados.getApellidos(), false));
/* 298 */       eltr.appendChild(newtd("" + regEncuestados.getNombres(), false));
/* 299 */       eltr.appendChild(newtd("" + regEncuestados.getNombreClase(), false));
/*     */       
/* 301 */       hte.appendChild(eltr);
/* 302 */       regEncuestados = rsEncuestados.next();
/* 303 */       hay = true;
/*     */     } 
/* 305 */     rsEncuestados.close();
/* 306 */     if (!hay) {
/* 307 */       Element divPersonas = this.pagHTML.getElementDivPersonas();
/* 308 */       divPersonas.getParentNode().removeChild(divPersonas);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 320 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 321 */     atrib.setValue(valor);
/* 322 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarCombo(CaracteristicasValorDAO rsTGen, HTMLSelectElement combo, int caract) {
/* 333 */     rsTGen.cargarTodos(caract);
/*     */     
/* 335 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 336 */     op.setValue("0");
/* 337 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 338 */     combo.appendChild(op); CaracteristicasValorDTO RegGeneral;
/* 339 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 340 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 341 */       op.setValue("" + RegGeneral.getValor());
/* 342 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 343 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String leerDescripcion(CaracteristicasValorDAO rsTGen, int caract, int dato) {
/* 355 */     CaracteristicasValorDTO RegGeneral = rsTGen.cargarRegistro(caract, dato);
/* 356 */     if (RegGeneral != null) {
/* 357 */       return RegGeneral.getDescripcion();
/*     */     }
/* 359 */     return "No encontrado";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 368 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 369 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 370 */     if (alinear) {
/* 371 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 373 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 374 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   private String agregarCapturaFecha(String fecha) { return "Calendar.setup({inputField :  '" + fecha + "'," + "ifFormat   :  '%Y-%m-%d'," + "button     :  'b" + fecha + "'" + "});"; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\LlenarEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */