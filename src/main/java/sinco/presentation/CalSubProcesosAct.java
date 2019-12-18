/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalSubProcesosAct;
/*     */ import sinco.presentation.CalSubProcesosActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalSubProcesosAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalSubProcesosActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  45 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  46 */     String _operacion = comms.request.getParameter("_operacion");
/*     */ 
/*     */     
/*  49 */     if (_operacion == null || _operacion.length() == 0) {
/*  50 */       _operacion = "L";
/*     */     }
/*     */     
/*  53 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  54 */       creacion(comms);
/*     */     }
/*     */     
/*  57 */     this.pagHTML = (CalSubProcesosActHTML)comms.xmlcFactory.create(CalSubProcesosActHTML.class);
/*  58 */     permisos(comms);
/*     */ 
/*     */     
/*  61 */     String proceso = comms.request.getParameter("proceso");
/*  62 */     if (proceso == null) {
/*  63 */       proceso = "";
/*     */     }
/*  65 */     String objetivoHidden = comms.request.getParameter("objetivoHidden");
/*  66 */     if (objetivoHidden == null) {
/*  67 */       objetivoHidden = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.pagHTML.getElementProcesoHidden().setValue("" + proceso);
/*  73 */     this.pagHTML.getElementObjetivoHidden().setValue("" + objetivoHidden);
/*     */ 
/*     */     
/*  76 */     CalProcesosDAO rs = new CalProcesosDAO();
/*  77 */     if (!rs.getEstadoConexion()) {
/*  78 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  80 */     CalProcesosDTO reg = rs.cargarRegistro(proceso);
/*  81 */     rs.close();
/*  82 */     this.pagHTML.setTextMiProceso(reg.getDescripcion());
/*     */     
/*  84 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  85 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  88 */     else if (_operacion.equals("P")) {
/*  89 */       editar(comms);
/*     */     }
/*  91 */     else if (_operacion.equals("Nuevo")) {
/*  92 */       nuevo(comms, proceso);
/*     */     } 
/*  94 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  95 */     comms.response.writeDOM(this.pagHTML);
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
/* 107 */     String _operacion = comms.request.getParameter("_operacion");
/* 108 */     String elUsuario = "" + comms.session.getUser().getName();
/* 109 */     String proceso = comms.request.getParameter("proceso");
/* 110 */     if (proceso == null) {
/* 111 */       proceso = "";
/*     */     }
/* 113 */     String subproceso = comms.request.getParameter("subproceso");
/* 114 */     if (subproceso == null) {
/* 115 */       subproceso = "";
/*     */     }
/* 117 */     RespuestaBD rta = new RespuestaBD();
/* 118 */     RespuestaBD rta2 = new RespuestaBD();
/* 119 */     if (_operacion.equals("E")) {
/*     */       
/* 121 */       CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 122 */       if (!rs.getEstadoConexion()) {
/* 123 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 125 */       rta = rs.eliminarRegistro(proceso, subproceso);
/*     */ 
/*     */       
/* 128 */       if (!rta.isRta()) {
/* 129 */         rs.close();
/* 130 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalSubProcesos&p1=" + rta.getMensaje()));
/*     */       } 
/* 132 */       rta2 = rs.eliminarServicioPorSubProceso(subproceso);
/* 133 */       rs.close();
/* 134 */       if (!rta2.isRta()) {
/* 135 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalSubProcesos&p1=" + rta.getMensaje()));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 140 */       String sPagina = "CalSubProcesosAct.po?_operacion=X&proceso=" + proceso + "&subproceso=" + subproceso + "";
/* 141 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 143 */     String descripcion = comms.request.getParameter("descripcion");
/* 144 */     String estado = comms.request.getParameter("estado");
/* 145 */     int factor = 0;
/*     */     try {
/* 147 */       factor = Integer.parseInt(comms.request.getParameter("factor"));
/*     */     }
/* 149 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 153 */     Collection<CalSubProcesosDTO> resultados = new ArrayList<CalSubProcesosDTO>();
/*     */     
/* 155 */     Enumeration<?> enumera = comms.request.getParameterNames();
/*     */     
/* 157 */     while (enumera.hasMoreElements()) {
/* 158 */       String param = (String)enumera.nextElement();
/*     */       
/* 160 */       if (param.startsWith("_S_")) {
/* 161 */         CalSubProcesosDTO reg = new CalSubProcesosDTO();
/* 162 */         int idFila = Integer.parseInt(param.substring(3));
/*     */         
/* 164 */         String servicio = comms.request.getParameter("_S_" + idFila);
/*     */         
/* 166 */         if (servicio.length() > 0) {
/* 167 */           reg.setServicio(servicio);
/* 168 */           String descripcionSegmento = comms.request.getParameter("_D_" + idFila);
/* 169 */           reg.setDescripcion(descripcionSegmento);
/* 170 */           reg.setEstado(comms.request.getParameter("_E_" + idFila));
/* 171 */           resultados.add(reg);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 179 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 180 */     if (!rs.getEstadoConexion()) {
/* 181 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 183 */     if (_operacion.equals("C")) {
/* 184 */       rta = rs.crearRegistro(proceso, subproceso, descripcion, estado, factor, resultados, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       rta = rs.modificarRegistro(proceso, subproceso, descripcion, estado, factor, resultados, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     rs.close();
/* 204 */     if (!rta.isRta()) {
/* 205 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalSubProcesos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 208 */     String sPagina = "CalSubProcesosAct.po?_operacion=P&proceso=" + proceso + "&subproceso=" + subproceso + "";
/* 209 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 220 */     activarVista("consulta");
/* 221 */     String proceso = comms.request.getParameter("proceso");
/* 222 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 226 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 227 */     if (!rs.getEstadoConexion()) {
/* 228 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 230 */     Collection<?> arr = rs.cargarTodos(proceso);
/*     */     
/* 232 */     rs.close();
/* 233 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 234 */     int cuantas = 0;
/* 235 */     int sumaFactor = 0;
/* 236 */     Iterator<?> iterator = arr.iterator();
/* 237 */     while (iterator.hasNext()) {
/* 238 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 239 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 240 */       String url = "CalSubProcesosAct.po?_operacion=P&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "";
/* 241 */       eltr.appendChild(newtdhref("" + reg.getSubproceso(), url));
/* 242 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 243 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 244 */       eltr.appendChild(newtd("" + reg.getFactor()));
/* 245 */       hte.appendChild(eltr);
/* 246 */       cuantas++;
/* 247 */       if (reg.getEstado().equals("A")) {
/* 248 */         sumaFactor += reg.getFactor();
/*     */       }
/*     */     } 
/* 251 */     arr.clear();
/* 252 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 253 */     this.pagHTML.setTextSumatoria("" + sumaFactor);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 267 */     String proceso = comms.request.getParameter("proceso");
/* 268 */     String subproceso = comms.request.getParameter("subproceso");
/* 269 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 270 */     if (!rs.getEstadoConexion()) {
/* 271 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 273 */     CalSubProcesosDTO reg = rs.cargarRegistro(proceso, subproceso);
/* 274 */     int suma = rs.getSuma(proceso, subproceso);
/* 275 */     rs.close();
/*     */ 
/*     */     
/*     */     try {
/* 279 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 280 */       sel.setAttribute("style", "display: none");
/*     */     
/*     */     }
/* 283 */     catch (Exception e) {}
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
/*     */     try {
/* 297 */       HTMLElement sel = this.pagHTML.getElementBtnEditar();
/* 298 */       sel.setAttribute("style", "display: inline");
/*     */     
/*     */     }
/* 301 */     catch (Exception e) {}
/*     */ 
/*     */     
/*     */     try {
/* 305 */       HTMLElement sel = this.pagHTML.getElementBtnGrabar();
/* 306 */       sel.setAttribute("style", "display: none");
/*     */     
/*     */     }
/* 309 */     catch (Exception e) {}
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
/* 322 */     if (reg != null) {
/* 323 */       this.pagHTML.getElementSubproceso().setValue("" + reg.getSubproceso());
/* 324 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 325 */       this.pagHTML.getElementFactor().setValue("" + reg.getFactor());
/* 326 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 327 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 328 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 329 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 330 */       this.pagHTML.getElementSuma().setValue("" + suma);
/* 331 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 332 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */ 
/*     */       
/* 336 */       this.pagHTML.getElementDescripcion().setReadOnly(true);
/* 337 */       this.pagHTML.getElementFactor().setReadOnly(true);
/* 338 */       this.pagHTML.getElementEstado().setDisabled(true);
/*     */       
/* 340 */       this.pagHTML.getElementSubproceso().setReadOnly(true);
/*     */     } 
/* 342 */     this.pagHTML.getElement_operacion().setValue("M");
/* 343 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms, String proceso) throws HttpPresentationException, KeywordValueException {
/* 355 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     
/*     */     try {
/* 358 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 359 */       sel.getParentNode().removeChild(sel);
/*     */     
/*     */     }
/* 362 */     catch (Exception e) {}
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
/*     */     try {
/* 376 */       HTMLElement sel2 = this.pagHTML.getElementBtnEditar();
/* 377 */       sel2.getParentNode().removeChild(sel2);
/* 378 */     } catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 384 */     activarVista("nuevo");
/* 385 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 386 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 400 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */ 
/*     */     
/* 403 */     Varios oVarios = new Varios();
/* 404 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "cal_procesos_act");
/* 405 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "cal_procesos_del");
/* 406 */     if (!oPermisoAct) {
/* 407 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 408 */       elem.getParentNode().removeChild(elem);
/* 409 */       elem = this.pagHTML.getElementBtnGrabar();
/* 410 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 412 */     if (!oPermisoDel) {
/* 413 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 414 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 425 */     if (!vista.equals("nuevo")) {
/* 426 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/* 427 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 429 */     if (!vista.equals("consulta")) {
/* 430 */       HTMLElement sel = this.pagHTML.getElementTrResultados();
/* 431 */       sel.getParentNode().removeChild(sel);
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
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 445 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 446 */     atrib.setValue(valor);
/* 447 */     return atrib;
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
/* 460 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 461 */     Element enlace = this.pagHTML.createElement("a");
/* 462 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 463 */     enlace.appendChild(hijo);
/* 464 */     Attr donde = this.pagHTML.createAttribute("href");
/* 465 */     donde.setValue(vinculo);
/* 466 */     enlace.setAttributeNode(donde);
/* 467 */     td.appendChild(enlace);
/* 468 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 469 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 479 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 480 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 481 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 482 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 497 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 498 */     Collection<?> arr = rs.cargarTabla(tabla);
/* 499 */     rs.close();
/* 500 */     if (dejarBlanco) {
/* 501 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 502 */       op.setValue("");
/* 503 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 504 */       combo.appendChild(op);
/*     */     } 
/* 506 */     Iterator<?> iterator = arr.iterator();
/* 507 */     while (iterator.hasNext()) {
/* 508 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 509 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 510 */       op.setValue("" + reg.getCodigo());
/* 511 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 512 */       if (defecto.equals(reg.getCodigo())) {
/* 513 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 514 */         escogida.setValue("on");
/* 515 */         op.setAttributeNode(escogida);
/*     */       } 
/* 517 */       combo.appendChild(op);
/*     */     } 
/* 519 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalSubProcesosAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */