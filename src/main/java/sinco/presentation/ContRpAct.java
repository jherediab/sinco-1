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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContAdicionContratoDTO;
/*     */ import sinco.business.ContRpDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContAdicionContratoDAO;
/*     */ import sinco.data.ContRpDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.ContRpAct;
/*     */ import sinco.presentation.ContRpActHTML;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContRpAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContRpActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     String _operacion = comms.request.getParameter("_operacion");
/*  47 */     if (_operacion == null) {
/*  48 */       _operacion = "Nuevo";
/*     */     }
/*     */     
/*  51 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  52 */       creacion(comms);
/*     */     }
/*  54 */     this.pagHTML = (ContRpActHTML)comms.xmlcFactory.create(ContRpActHTML.class);
/*  55 */     if (_operacion.equals("P")) {
/*  56 */       editar(comms);
/*  57 */     } else if (_operacion.equals("Nuevo")) {
/*  58 */       nuevo(comms);
/*  59 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/*  60 */       elem.getParentNode().removeChild(elem);
/*  61 */       Varios oVarios = new Varios();
/*  62 */       int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */       
/*  64 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  65 */       if (!oPermisoAct) {
/*  66 */         elem = this.pagHTML.getElementBtnGrabar();
/*  67 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  72 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  73 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  75 */     comms.response.writeDOM(this.pagHTML);
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
/*  87 */     String _operacion = comms.request.getParameter("_operacion");
/*  88 */     String elUsuario = "" + comms.session.getUser().getName();
/*  89 */     String numeroRegistro = comms.request.getParameter("numeroRegistro");
/*  90 */     String anio = comms.request.getParameter("anio");
/*  91 */     String imputacion = comms.request.getParameter("imputacion");
/*  92 */     int consecutivoContrato = 0;
/*     */     try {
/*  94 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*  95 */     } catch (Exception e) {}
/*  96 */     int numeroAdicion = 0;
/*     */     try {
/*  98 */       numeroAdicion = Integer.parseInt(comms.request.getParameter("numeroAdicion"));
/*  99 */     } catch (Exception e) {}
/* 100 */     double valor = 0.0D;
/*     */     try {
/* 102 */       valor = Double.parseDouble(comms.request.getParameter("valor"));
/*     */     }
/* 104 */     catch (Exception e) {}
/*     */     
/* 106 */     double valorContrato = 0.0D;
/*     */     try {
/* 108 */       valorContrato = Double.parseDouble(comms.request.getParameter("valorContrato"));
/* 109 */     } catch (Exception e) {}
/* 110 */     boolean rta = false;
/* 111 */     if (_operacion.equals("E")) {
/* 112 */       ContRpDAO rs = new ContRpDAO();
/* 113 */       if (!rs.getEstadoConexion()) {
/* 114 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 116 */       rta = rs.eliminarRegistro(numeroRegistro, anio);
/* 117 */       rs.close();
/* 118 */       if (!rta) {
/* 119 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContRp"));
/*     */       }
/*     */       
/* 122 */       String sPagina = "ContRpAct.po?_operacion=Nuevo&consecutivoContrato=" + consecutivoContrato + "&numeroAdicion=" + numeroAdicion + "&origen=C&valorContrato=" + Utilidades.formatDouble(valorContrato);
/*     */       
/* 124 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 126 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/*     */ 
/*     */     
/* 129 */     String dependencia = comms.request.getParameter("dependencia");
/* 130 */     ContRpDAO rs = new ContRpDAO();
/* 131 */     if (!rs.getEstadoConexion()) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 134 */     rta = false;
/* 135 */     if (_operacion.equals("C")) {
/* 136 */       rta = rs.crearRegistro(numeroRegistro, anio, fechaExpedicion, valor, dependencia, consecutivoContrato, numeroAdicion, imputacion, elUsuario);
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
/* 148 */       rta = rs.modificarRegistro(numeroRegistro, anio, fechaExpedicion, valor, dependencia, consecutivoContrato, numeroAdicion, imputacion, elUsuario);
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
/* 159 */     rs.close();
/* 160 */     if (!rta) {
/* 161 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContRp"));
/*     */     }
/* 163 */     String sPagina = "ContRpAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&numeroAdicion=" + numeroAdicion + "&origen=C&valorContrato=" + Utilidades.formatDouble(valorContrato) + "&numeroRegistro=" + numeroRegistro + "&anio=" + anio;
/*     */ 
/*     */     
/* 166 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 180 */     double valorContrato = 0.0D;
/*     */     try {
/* 182 */       valorContrato = Double.parseDouble(comms.request.getParameter("valorContrato"));
/* 183 */     } catch (Exception e) {}
/* 184 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/*     */     
/* 186 */     double valorEjec = listar(comms, "L");
/* 187 */     int consecutivoContrato = 0;
/*     */     try {
/* 189 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 191 */     catch (Exception e) {}
/*     */     
/* 193 */     int numeroAdicion = 0;
/*     */     try {
/* 195 */       numeroAdicion = Integer.parseInt(comms.request.getParameter("numeroAdicion"));
/* 196 */     } catch (Exception e) {}
/* 197 */     String numeroRegistro = comms.request.getParameter("numeroRegistro");
/* 198 */     String anio = comms.request.getParameter("anio");
/*     */     
/* 200 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 201 */     this.pagHTML.getElementNumeroAdicion().setValue("" + numeroAdicion);
/*     */     
/* 203 */     ContRpDAO rs = new ContRpDAO();
/* 204 */     if (!rs.getEstadoConexion()) {
/* 205 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 207 */     ContRpDTO reg = rs.cargarRegistro(numeroRegistro, anio);
/* 208 */     rs.close();
/* 209 */     Varios oVarios = new Varios();
/* 210 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 212 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 213 */     if (!oPermisoAct) {
/* 214 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 215 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 217 */       elem = this.pagHTML.getElementBtnEliminar();
/* 218 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 220 */     if (reg != null) {
/* 221 */       this.pagHTML.getElementNumeroRegistro().setValue("" + reg.getNumeroRegistro());
/* 222 */       this.pagHTML.getElementFechaExpedicion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()));
/* 223 */       this.pagHTML.getElementValor().setValue("" + Utilidades.formatDouble(reg.getValor()));
/* 224 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 225 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 226 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 227 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 228 */       this.pagHTML.getElementConsecutivoContrato().setValue("" + reg.getConsecutivoContrato());
/* 229 */       this.pagHTML.getElementNumeroAdicion().setValue("" + reg.getNumeroAdicion());
/* 230 */       this.pagHTML.getElementAnio().setValue("" + reg.getAnio());
/* 231 */       HTMLSelectElement combo = this.pagHTML.getElementDependencia();
/* 232 */       cargarImputaciones(reg.getImputacion(), reg.getConsecutivoContrato());
/* 233 */       llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "1=1", "" + reg.getDependencia(), true);
/* 234 */       this.pagHTML.getElementValorPend().setValue("" + Utilidades.formatDouble(valorContrato - valorEjec + reg.getValor()));
/*     */       
/* 236 */       this.pagHTML.getElement_operacion().setValue("M");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 244 */     double valorContrato = 0.0D;
/*     */     try {
/* 246 */       valorContrato = Double.parseDouble(comms.request.getParameter("valorContrato"));
/* 247 */     } catch (Exception e) {}
/*     */     
/* 249 */     int consecutivoContrato = 0;
/*     */     try {
/* 251 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 253 */     catch (Exception e) {}
/*     */     
/* 255 */     int numeroAdicion = 0;
/*     */     try {
/* 257 */       numeroAdicion = Integer.parseInt(comms.request.getParameter("numeroAdicion"));
/* 258 */     } catch (Exception e) {}
/* 259 */     double valorAdicion = 0.0D;
/* 260 */     ContAdicionContratoDAO rsAd = new ContAdicionContratoDAO();
/* 261 */     Collection adiciones = rsAd.cargarTodos(consecutivoContrato);
/* 262 */     rsAd.close();
/* 263 */     Iterator it = adiciones.iterator();
/* 264 */     while (it.hasNext()) {
/* 265 */       ContAdicionContratoDTO reg = (ContAdicionContratoDTO)it.next();
/* 266 */       valorAdicion += reg.getValorAdicionado();
/*     */     } 
/* 268 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 269 */     this.pagHTML.getElementNumeroAdicion().setValue("" + numeroAdicion);
/* 270 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/*     */     
/* 272 */     double valorEjec = listar(comms, "L");
/* 273 */     this.pagHTML.getElementValor().setValue("" + Utilidades.formatDouble(valorContrato - valorEjec));
/* 274 */     this.pagHTML.getElementValorPend().setValue("" + Utilidades.formatDouble(valorContrato + valorAdicion - valorEjec));
/*     */ 
/*     */     
/* 277 */     this.pagHTML.getElement_operacion().setValue("C");
/* 278 */     HTMLSelectElement combo = this.pagHTML.getElementDependencia();
/* 279 */     llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "1=1", "2200", true);
/* 280 */     cargarImputaciones("", consecutivoContrato);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 291 */     double valorEjec = 0.0D;
/* 292 */     int consecutivoContrato = 0;
/*     */     try {
/* 294 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 296 */     catch (Exception e) {}
/*     */     
/* 298 */     int numeroAdicion = 0;
/*     */     try {
/* 300 */       numeroAdicion = Integer.parseInt(comms.request.getParameter("numeroAdicion"));
/* 301 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 304 */     double valorContrato = 0.0D;
/*     */     try {
/* 306 */       valorContrato = Double.parseDouble(comms.request.getParameter("valorContrato"));
/* 307 */     } catch (Exception e) {}
/* 308 */     if (_operacion.equals("X")) {
/* 309 */       return 0.0D;
/*     */     }
/*     */     
/* 312 */     ContRpDAO rs = new ContRpDAO();
/* 313 */     if (!rs.getEstadoConexion()) {
/* 314 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 316 */     Collection arr = rs.cargarTodos(consecutivoContrato, "", "", "", numeroAdicion);
/* 317 */     rs.close();
/*     */     
/* 319 */     int cuantas = 0;
/* 320 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 321 */     Iterator iterator = arr.iterator();
/* 322 */     while (iterator.hasNext()) {
/* 323 */       ContRpDTO reg = (ContRpDTO)iterator.next();
/* 324 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 325 */       String url = "ContRpAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&numeroAdicion=" + numeroAdicion + "&origen=C&valorContrato=" + Utilidades.formatDouble(valorContrato) + "&numeroRegistro=" + reg.getNumeroRegistro() + "&anio=" + reg.getAnio();
/*     */ 
/*     */       
/* 328 */       eltr.appendChild(newtdhref("" + reg.getNumeroRegistro(), url));
/* 329 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion())));
/* 330 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValor(), 2)));
/* 331 */       eltr.appendChild(newtd("" + reg.getImputacion()));
/* 332 */       hte.appendChild(eltr);
/* 333 */       valorEjec += reg.getValor();
/* 334 */       cuantas++;
/*     */     } 
/* 336 */     arr.clear();
/* 337 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 338 */     return valorEjec;
/*     */   }
/*     */ 
/*     */   
/*     */   private void cargarImputaciones(String imputaciones, int consecutivoContrato) {
/* 343 */     TGeneralDAO rsTG = new TGeneralDAO();
/* 344 */     Collection arr = rsTG.cargarTodosArray("cont_cdp_contrato", "distinct on (imputacion)  imputacion", "imputacion", "consecutivo_contrato=" + consecutivoContrato);
/* 345 */     rsTG.close();
/* 346 */     Iterator it = arr.iterator();
/*     */     
/* 348 */     HTMLTableElement hte = this.pagHTML.getElementTbImputaciones();
/* 349 */     boolean fondo = false;
/* 350 */     while (it.hasNext()) {
/* 351 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 352 */       fondo = !fondo;
/* 353 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 355 */       TGeneralDTO reg = (TGeneralDTO)it.next();
/* 356 */       HTMLElement checkbox = (HTMLElement)this.pagHTML.createElement("input");
/* 357 */       checkbox.setAttributeNode(newAttr("type", "checkbox"));
/* 358 */       checkbox.setAttributeNode(newAttr("name", "chkImputacion"));
/* 359 */       checkbox.setAttributeNode(newAttr("value", "" + reg.getCodigo()));
/*     */       
/* 361 */       if (imputaciones.indexOf(reg.getCodigo()) != -1) {
/* 362 */         checkbox.setAttributeNode(newAttr("checked", "true"));
/*     */       }
/*     */       
/* 365 */       HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 366 */       td.setAttributeNode(newAttr("class", "tit"));
/* 367 */       td.appendChild(checkbox);
/* 368 */       eltr.appendChild(td);
/*     */ 
/*     */       
/* 371 */       td = (HTMLElement)this.pagHTML.createElement("td");
/* 372 */       td.setAttributeNode(newAttr("class", "dat"));
/* 373 */       td.appendChild(this.pagHTML.createTextNode("" + reg.getDescripcion()));
/* 374 */       eltr.appendChild(td);
/* 375 */       hte.appendChild(eltr);
/*     */     } 
/* 377 */     arr.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 400 */     if (dejarBlanco) {
/* 401 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 402 */       op.setValue("");
/* 403 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 404 */       combo.appendChild(op);
/*     */     } 
/* 406 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 407 */     Collection arr = rsTGen.cargarTodosArray(tabla, codigo, descripcion, condicion);
/* 408 */     rsTGen.close();
/* 409 */     Iterator iterator = arr.iterator();
/* 410 */     while (iterator.hasNext()) {
/* 411 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 412 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 413 */       op.setValue("" + regGeneral.getCodigo());
/* 414 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 415 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 416 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 417 */         escogida.setValue("on");
/* 418 */         op.setAttributeNode(escogida);
/*     */       } 
/* 420 */       combo.appendChild(op);
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
/* 434 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 435 */     atrib.setValue(valor);
/* 436 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 446 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 447 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 448 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 449 */     return td;
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
/* 462 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 463 */     Element enlace = this.pagHTML.createElement("a");
/* 464 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 465 */     enlace.appendChild(hijo);
/* 466 */     Attr donde = this.pagHTML.createAttribute("href");
/* 467 */     donde.setValue(vinculo);
/* 468 */     enlace.setAttributeNode(donde);
/* 469 */     td.appendChild(enlace);
/* 470 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 471 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContRpAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */