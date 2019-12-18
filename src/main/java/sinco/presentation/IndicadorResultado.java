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
/*     */ import sinco.business.IndicadorResultadoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.IndicadorResultadoDAO;
/*     */ import sinco.presentation.IndicadorResultado;
/*     */ import sinco.presentation.IndicadorResultadoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorResultado
/*     */   implements HttpPresentation
/*     */ {
/*     */   private IndicadorResultadoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  39 */     String _operacion = comms.request.getParameter("_operacion");
/*  40 */     if (_operacion == null || _operacion.length() == 0) {
/*  41 */       _operacion = "X";
/*     */     }
/*     */     
/*  44 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  45 */       creacion(comms);
/*     */     }
/*     */     
/*  48 */     this.pagHTML = (IndicadorResultadoHTML)comms.xmlcFactory.create(IndicadorResultadoHTML.class);
/*  49 */     permisos(comms);
/*     */     
/*  51 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  52 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("P")) {
/*  56 */       editar(comms);
/*     */     }
/*  58 */     else if (_operacion.equals("Nuevo")) {
/*  59 */       nuevo(comms);
/*     */     } 
/*     */     
/*  62 */     if (_operacion.equals("V")) {
/*  63 */       verRegistro(comms);
/*     */     }
/*  65 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  66 */     comms.response.writeDOM(this.pagHTML);
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
/*  78 */     String _operacion = comms.request.getParameter("_operacion");
/*  79 */     String elUsuario = "" + comms.session.getUser().getName();
/*  80 */     int ciclo = 0;
/*     */     try {
/*  82 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  84 */     catch (Exception e) {
/*  85 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  88 */     String indicador = comms.request.getParameter("indicador");
/*  89 */     if (indicador == null) {
/*  90 */       indicador = "";
/*     */     }
/*  92 */     String mes = comms.request.getParameter("mes");
/*  93 */     if (mes == null) {
/*  94 */       mes = "";
/*     */     }
/*  96 */     RespuestaBD rta = new RespuestaBD();
/*  97 */     if (_operacion.equals("E")) {
/*  98 */       IndicadorResultadoDAO rs = new IndicadorResultadoDAO();
/*  99 */       if (!rs.getEstadoConexion()) {
/* 100 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 102 */       rta = rs.eliminarRegistro(ciclo, indicador, mes);
/*     */ 
/*     */       
/* 105 */       if (!rta.isRta()) {
/* 106 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorResultado&p1=" + rta.getMensaje()));
/*     */       }
/* 108 */       rs.close();
/* 109 */       String sPagina = "IndicadorResultado.po?_operacion=X";
/* 110 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 112 */     double valorProgramado = 0.0D;
/*     */     try {
/* 114 */       valorProgramado = Double.parseDouble(comms.request.getParameter("valorProgramado"));
/*     */     }
/* 116 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 119 */     double valorEjecutado = 0.0D;
/*     */     try {
/* 121 */       valorEjecutado = Double.parseDouble(comms.request.getParameter("valorEjecutado"));
/*     */     }
/* 123 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 126 */     String estado = comms.request.getParameter("estado");
/* 127 */     IndicadorResultadoDAO rs = new IndicadorResultadoDAO();
/* 128 */     if (!rs.getEstadoConexion()) {
/* 129 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 131 */     if (_operacion.equals("C")) {
/* 132 */       rta = rs.crearRegistro(ciclo, indicador, mes, valorProgramado, valorEjecutado, estado);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 141 */       rta = rs.modificarRegistro(ciclo, indicador, mes, valorProgramado, valorEjecutado, estado);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     rs.close();
/* 150 */     if (!rta.isRta()) {
/* 151 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorResultado&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 154 */     String sPagina = "IndicadorResultado.po?_operacion=P&ciclo=" + ciclo + "&indicador=" + indicador + "&mes=" + mes + "";
/* 155 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 166 */     int ciclo = 0;
/*     */     try {
/* 168 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 170 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 173 */     String indicador = comms.request.getParameter("indicador");
/* 174 */     String mes = comms.request.getParameter("mes");
/* 175 */     IndicadorResultadoDAO rs = new IndicadorResultadoDAO();
/* 176 */     if (!rs.getEstadoConexion()) {
/* 177 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 179 */     IndicadorResultadoDTO reg = rs.cargarRegistro(ciclo, indicador, mes);
/*     */ 
/*     */     
/* 182 */     rs.close();
/* 183 */     if (reg != null) {
/* 184 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 185 */       this.pagHTML.getElementIndicador().setValue("" + reg.getIndicador());
/* 186 */       this.pagHTML.getElementMes().setValue("" + reg.getMes());
/* 187 */       this.pagHTML.getElementValorProgramado().setValue("" + Utilidades.formatDouble2(reg.getValorProgramado()));
/* 188 */       this.pagHTML.getElementValorEjecutado().setValue("" + Utilidades.formatDouble2(reg.getValorEjecutado()));
/* 189 */       this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/*     */       
/* 191 */       this.pagHTML.getElementCiclo().setReadOnly(true);
/* 192 */       this.pagHTML.getElementIndicador().setReadOnly(true);
/* 193 */       this.pagHTML.getElementMes().setReadOnly(true);
/*     */     } 
/* 195 */     this.pagHTML.getElement_operacion().setValue("M");
/* 196 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 208 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 210 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 211 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 213 */     catch (Exception e) {}
/*     */     
/* 215 */     activarVista("nuevo");
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
/* 226 */     activarVista("consulta");
/* 227 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 232 */     IndicadorResultadoDAO rs = new IndicadorResultadoDAO();
/* 233 */     if (!rs.getEstadoConexion()) {
/* 234 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 236 */     Collection<IndicadorResultadoDTO> arr = rs.cargarTodos();
/* 237 */     rs.close();
/* 238 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 239 */     int cuantas = 0;
/* 240 */     Iterator<IndicadorResultadoDTO> iterator = arr.iterator();
/* 241 */     while (iterator.hasNext()) {
/* 242 */       IndicadorResultadoDTO reg = (IndicadorResultadoDTO)iterator.next();
/* 243 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 244 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 245 */       String url = "IndicadorResultado.po?_operacion=V&ciclo=" + reg.getCiclo() + "&indicador=" + reg.getIndicador() + "&mes=" + reg.getMes() + "";
/* 246 */       eltr.appendChild(newtdhref("" + reg.getIndicador(), url));
/* 247 */       eltr.appendChild(newtd("" + reg.getMes()));
/* 248 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValorProgramado())));
/* 249 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValorEjecutado())));
/* 250 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 251 */       hte.appendChild(eltr);
/* 252 */       cuantas++;
/*     */     } 
/* 254 */     arr.clear();
/* 255 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 268 */     int ciclo = 0;
/*     */     try {
/* 270 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 272 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 275 */     String indicador = comms.request.getParameter("indicador");
/* 276 */     String mes = comms.request.getParameter("mes");
/* 277 */     IndicadorResultadoDAO rs = new IndicadorResultadoDAO();
/* 278 */     if (!rs.getEstadoConexion()) {
/* 279 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 281 */     IndicadorResultadoDTO reg = rs.cargarRegistro(ciclo, indicador, mes);
/*     */ 
/*     */     
/* 284 */     rs.close();
/* 285 */     if (reg != null) {
/* 286 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 287 */       this.pagHTML.setTextIndicadorEd("" + reg.getIndicador());
/* 288 */       this.pagHTML.setTextMesEd("" + reg.getMes());
/* 289 */       this.pagHTML.setTextValorProgramadoEd("" + Utilidades.miles(reg.getValorProgramado()));
/* 290 */       this.pagHTML.setTextValorEjecutadoEd("" + Utilidades.miles(reg.getValorEjecutado()));
/* 291 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/*     */       
/* 293 */       this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
/* 294 */       this.pagHTML.getElementIndicadorKey().setValue("" + reg.getIndicador());
/* 295 */       this.pagHTML.getElementMesKey().setValue("" + reg.getMes());
/* 296 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 298 */     activarVista("editar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 309 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 311 */     Varios oVarios = new Varios();
/* 312 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "IndicadorIndicadorResultadoAct");
/* 313 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "IndicadorIndicadorResultadoDel");
/* 314 */     if (!oPermisoAct) {
/* 315 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 316 */       elem.getParentNode().removeChild(elem);
/* 317 */       elem = this.pagHTML.getElementBtnGrabar();
/* 318 */       elem.getParentNode().removeChild(elem);
/* 319 */       elem = this.pagHTML.getElementBtnModificar();
/* 320 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 322 */     if (!oPermisoDel) {
/* 323 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 324 */       elem.getParentNode().removeChild(elem);
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
/* 335 */     if (!vista.equals("nuevo")) {
/* 336 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 337 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 339 */     if (!vista.equals("editar")) {
/* 340 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 341 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 343 */     if (!vista.equals("consulta")) {
/* 344 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 345 */       sel.getParentNode().removeChild(sel);
/* 346 */       sel = this.pagHTML.getElementDivResultados();
/* 347 */       sel.getParentNode().removeChild(sel);
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
/* 361 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 362 */     atrib.setValue(valor);
/* 363 */     return atrib;
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
/* 376 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 377 */     Element enlace = this.pagHTML.createElement("a");
/* 378 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 379 */     enlace.appendChild(hijo);
/* 380 */     Attr donde = this.pagHTML.createAttribute("href");
/* 381 */     donde.setValue(vinculo);
/* 382 */     enlace.setAttributeNode(donde);
/* 383 */     td.appendChild(enlace);
/* 384 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 385 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 395 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 396 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 397 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 398 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadorResultado.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */