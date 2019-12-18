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
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContActaInicioDTO;
/*     */ import sinco.business.DocumentosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContActaInicioDAO;
/*     */ import sinco.data.ContContratoDAO;
/*     */ import sinco.data.DocumentosDAO;
/*     */ import sinco.presentation.ContActaInicioAct;
/*     */ import sinco.presentation.ContActaInicioActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContActaInicioAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContActaInicioActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  36 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  40 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "X";
/*     */     }
/*     */     
/*  48 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  49 */       creacion(comms);
/*  50 */       listar(comms, _operacion);
/*     */     } 
/*  52 */     this.pagHTML = (ContActaInicioActHTML)comms.xmlcFactory.create(ContActaInicioActHTML.class);
/*  53 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  54 */       listar(comms, _operacion);
/*     */     }
/*  56 */     else if (_operacion.equals("GenerarActa")) {
/*  57 */       generarActa(comms);
/*  58 */       _operacion = "P";
/*     */     
/*     */     }
/*  61 */     else if (_operacion.equals("P")) {
/*  62 */       editar(comms);
/*  63 */       listar(comms, _operacion);
/*     */     }
/*  65 */     else if (_operacion.equals("Nuevo")) {
/*  66 */       nuevo(comms);
/*  67 */       listar(comms, _operacion);
/*     */     } 
/*     */     
/*  70 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  71 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  72 */       sel.getParentNode().removeChild(sel);
/*  73 */       sel = this.pagHTML.getElementTrDocumentos();
/*  74 */       sel.getParentNode().removeChild(sel);
/*     */     }
/*  76 */     else if (_operacion.equals("Nuevo")) {
/*  77 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  78 */       sel.getParentNode().removeChild(sel);
/*  79 */       sel = this.pagHTML.getElementTrDocumentosAnexos();
/*  80 */       sel.getParentNode().removeChild(sel);
/*  81 */       sel = this.pagHTML.getElementTrDocumentos();
/*  82 */       sel.getParentNode().removeChild(sel);
/*  83 */       sel = this.pagHTML.getElementBtnGenerarActa();
/*  84 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  87 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  88 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 100 */     String _operacion = comms.request.getParameter("_operacion");
/* 101 */     String elUsuario = "" + comms.session.getUser().getName();
/* 102 */     int consecutivoContrato = 0;
/*     */     try {
/* 104 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 106 */     catch (Exception e) {}
/*     */     
/* 108 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 109 */     if (numeroEstudio == null) {
/* 110 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 113 */     boolean rta = false;
/* 114 */     if (_operacion.equals("E")) {
/* 115 */       ContActaInicioDAO rs = new ContActaInicioDAO();
/* 116 */       if (!rs.getEstadoConexion()) {
/* 117 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 119 */       rta = rs.eliminarRegistro(consecutivoContrato);
/* 120 */       rs.close();
/* 121 */       if (!rta) {
/* 122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContActaInicio"));
/*     */       }
/* 124 */       String sPagina = "ContActaInicioAct.po?_operacion=X";
/* 125 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 127 */     String fechaActa = comms.request.getParameter("fechaActa");
/* 128 */     String anexoCedula = comms.request.getParameter("cedula");
/* 129 */     if (anexoCedula == null) anexoCedula = "N"; 
/* 130 */     String anexoPasadojud = comms.request.getParameter("pasadoJudicial");
/* 131 */     if (anexoPasadojud == null) anexoPasadojud = "N"; 
/* 132 */     String anexoRut = comms.request.getParameter("rut");
/* 133 */     if (anexoRut == null) anexoRut = "N"; 
/* 134 */     String anexoProcuraduria = comms.request.getParameter("procuraduria");
/* 135 */     if (anexoProcuraduria == null) anexoProcuraduria = "N"; 
/* 136 */     String anexoContraloria = comms.request.getParameter("contraloria");
/* 137 */     if (anexoContraloria == null) anexoContraloria = "N"; 
/* 138 */     String anexoContrato = comms.request.getParameter("contrato");
/* 139 */     if (anexoContrato == null) anexoContrato = "N"; 
/* 140 */     String anexoPolizas = comms.request.getParameter("polizas");
/* 141 */     if (anexoPolizas == null) anexoPolizas = "N"; 
/* 142 */     String anexoImpuestos = comms.request.getParameter("impuestos");
/* 143 */     if (anexoImpuestos == null) anexoImpuestos = "N"; 
/* 144 */     String anexoRegHab = comms.request.getParameter("habilitacion");
/* 145 */     if (anexoRegHab == null) anexoRegHab = "N"; 
/* 146 */     ContActaInicioDAO rs = new ContActaInicioDAO();
/* 147 */     if (!rs.getEstadoConexion()) {
/* 148 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 150 */     rta = false;
/* 151 */     if (_operacion.equals("C")) {
/* 152 */       rta = rs.crearRegistro(consecutivoContrato, fechaActa, anexoPasadojud, anexoRut, anexoProcuraduria, anexoContraloria, anexoContrato, anexoPolizas, anexoImpuestos, anexoCedula, anexoRegHab, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       rta = rs.modificarRegistro(consecutivoContrato, fechaActa, anexoPasadojud, anexoRut, anexoProcuraduria, anexoContraloria, anexoContrato, anexoPolizas, anexoImpuestos, anexoCedula, anexoRegHab, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     rs.close();
/* 182 */     ContContratoDAO rsC = new ContContratoDAO();
/* 183 */     rsC.generarFechas(-1, consecutivoContrato);
/*     */ 
/*     */ 
/*     */     
/* 187 */     rsC.close();
/* 188 */     if (!rta) {
/* 189 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContActaInicio"));
/*     */     }
/* 191 */     String sPagina = "ContActaInicioAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&numeroEstudio=" + numeroEstudio;
/* 192 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 203 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/* 206 */     int consecutivoContrato = 0;
/*     */     try {
/* 208 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 210 */     catch (Exception e) {}
/*     */     
/* 212 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 213 */     if (numeroEstudio == null) {
/* 214 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 217 */     ContActaInicioDAO rs = new ContActaInicioDAO();
/* 218 */     if (!rs.getEstadoConexion()) {
/* 219 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 221 */     Collection arr = rs.cargarTodos(consecutivoContrato);
/*     */     
/* 223 */     rs.close();
/* 224 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 225 */     int cuantas = 0;
/* 226 */     Iterator iterator = arr.iterator();
/* 227 */     while (iterator.hasNext()) {
/* 228 */       ContActaInicioDTO reg = (ContActaInicioDTO)iterator.next();
/* 229 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 230 */       String url = "ContActaInicioAct.po?_operacion=P&consecutivoContrato=" + reg.getConsecutivoContrato() + "&numeroEstudio=" + numeroEstudio;
/* 231 */       eltr.appendChild(newtdhref("" + reg.getNumeroContrato(), url));
/* 232 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaActa())));
/* 233 */       hte.appendChild(eltr);
/* 234 */       cuantas++;
/*     */     } 
/* 236 */     arr.clear();
/* 237 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 252 */     int consecutivoContrato = 0;
/*     */     try {
/* 254 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 256 */     catch (Exception e) {}
/*     */     
/* 258 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 259 */     if (numeroEstudio == null) {
/* 260 */       numeroEstudio = "";
/*     */     }
/* 262 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/*     */     
/* 264 */     ContActaInicioDAO rs = new ContActaInicioDAO();
/* 265 */     if (!rs.getEstadoConexion()) {
/* 266 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 268 */     ContActaInicioDTO reg = rs.cargarRegistro(consecutivoContrato);
/* 269 */     rs.close();
/* 270 */     Varios oVarios = new Varios();
/* 271 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 273 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oContratosActaInicio");
/* 274 */     if (!oPermisoAct) {
/* 275 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 276 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 278 */       elem = this.pagHTML.getElementBtnEliminar();
/* 279 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 281 */     int cuantos = 0;
/* 282 */     if (reg != null) {
/* 283 */       this.pagHTML.getElementConsecutivoContrato().setValue("" + reg.getConsecutivoContrato());
/* 284 */       this.pagHTML.getElementFechaActa().setValue("" + Utilidades.darFormatoFecha(reg.getFechaActa()));
/*     */       
/* 286 */       if (reg.getAnexoCedula().equals("S")) {
/* 287 */         this.pagHTML.getElementCedula().setChecked(true);
/* 288 */         cuantos++;
/*     */       } 
/* 290 */       if (reg.getAnexoPasadojud().equals("S")) {
/* 291 */         this.pagHTML.getElementPasadoJuducial().setChecked(true);
/* 292 */         cuantos++;
/*     */       } 
/* 294 */       if (reg.getAnexoRut().equals("S")) {
/* 295 */         this.pagHTML.getElementRut().setChecked(true);
/* 296 */         cuantos++;
/*     */       } 
/* 298 */       if (reg.getAnexoProcuraduria().equals("S")) {
/* 299 */         this.pagHTML.getElementProcuraduria().setChecked(true);
/* 300 */         cuantos++;
/*     */       } 
/* 302 */       if (reg.getAnexoContraloria().equals("S")) {
/* 303 */         this.pagHTML.getElementContraloria().setChecked(true);
/* 304 */         cuantos++;
/*     */       } 
/* 306 */       if (reg.getAnexoContrato().equals("S")) {
/* 307 */         this.pagHTML.getElementContrato().setChecked(true);
/* 308 */         cuantos++;
/*     */       } 
/* 310 */       if (reg.getAnexoPolizas().equals("S")) {
/* 311 */         this.pagHTML.getElementPolizas().setChecked(true);
/* 312 */         cuantos++;
/*     */       } 
/* 314 */       if (reg.getAnexoImpuestos().equals("S")) {
/* 315 */         this.pagHTML.getElementImpuestos().setChecked(true);
/* 316 */         cuantos++;
/*     */       } 
/* 318 */       if (reg.getAnexoRegHab().equals("S")) {
/* 319 */         this.pagHTML.getElementHabilitacion().setChecked(true);
/* 320 */         cuantos++;
/*     */       } 
/* 322 */       if (cuantos < 8) {
/* 323 */         HTMLElement sel = this.pagHTML.getElementBtnGenerarActa();
/* 324 */         sel.getParentNode().removeChild(sel);
/*     */       } 
/* 326 */       this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
/* 327 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 328 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 329 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 330 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */       
/* 332 */       cargarDocumentos(consecutivoContrato);
/*     */       
/* 334 */       this.pagHTML.getElementConsecutivoContrato().setReadOnly(true);
/* 335 */       this.pagHTML.getElement_operacion().setValue("M");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 347 */     String consecutivoContrato = comms.request.getParameter("consecutivoContrato");
/* 348 */     if (consecutivoContrato == null) {
/* 349 */       consecutivoContrato = "";
/*     */     }
/* 351 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 352 */     if (consecutivoContrato == null) {
/* 353 */       consecutivoContrato = "";
/*     */     }
/* 355 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 356 */     if (numeroEstudio == null) {
/* 357 */       numeroEstudio = "";
/*     */     }
/* 359 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 360 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 361 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generarActa(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 372 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 374 */     int consecutivoContrato = 0;
/*     */     try {
/* 376 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 378 */     catch (Exception e) {}
/* 379 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 380 */     if (numeroEstudio == null) {
/* 381 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 384 */     DocumentosDAO rs = new DocumentosDAO();
/* 385 */     String rta = rs.crearFormatoActaInicio(consecutivoContrato, elUsuario);
/* 386 */     rs.close();
/*     */     
/* 388 */     if (!rta.equalsIgnoreCase("ok")) {
/* 389 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoDocActaInicio&texto=" + rta));
/*     */     }
/*     */     
/* 392 */     String sPagina = "ContActaInicioAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&numeroEstudio=" + numeroEstudio;
/* 393 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int cargarDocumentos(int consecutivoContrato) {
/* 402 */     int cuantos = 0;
/*     */     
/* 404 */     DocumentosDAO rs = new DocumentosDAO();
/* 405 */     boolean rta = rs.getEstadoConexion();
/* 406 */     if (!rta) {
/* 407 */       return -1;
/*     */     }
/* 409 */     Collection arr = rs.cargarTodos("ActaInicio", consecutivoContrato);
/* 410 */     rs.close();
/*     */     
/* 412 */     Iterator iterator = arr.iterator();
/* 413 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleDocumentos();
/* 414 */     boolean fondo = true;
/* 415 */     while (iterator.hasNext()) {
/* 416 */       DocumentosDTO reg = (DocumentosDTO)iterator.next();
/* 417 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 418 */       fondo = !fondo;
/* 419 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 420 */       eltr.appendChild(newtdhref("Documento No " + reg.getNumeroDocumento(), "VerDocumento.po?tipoDocumento=" + reg.getTipoDocumento() + "&numeroDocumento=" + reg.getNumeroDocumento()));
/*     */       
/* 422 */       eltr.appendChild(newtd("" + reg.getFechaInsercion()));
/* 423 */       hte.appendChild(eltr);
/* 424 */       cuantos++;
/*     */     } 
/* 426 */     return cuantos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 439 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 440 */     atrib.setValue(valor);
/* 441 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 454 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 455 */     Element enlace = this.pagHTML.createElement("a");
/* 456 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 457 */     enlace.appendChild(hijo);
/* 458 */     Attr donde = this.pagHTML.createAttribute("href");
/* 459 */     donde.setValue(vinculo);
/* 460 */     enlace.setAttributeNode(donde);
/* 461 */     td.appendChild(enlace);
/* 462 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 463 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 473 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 474 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 475 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 476 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContActaInicioAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */