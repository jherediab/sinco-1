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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContEstudioPrevioFirmaDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.ContEstudioPrevioFirmaDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.ContEstudioPrevioFirmaAct;
/*     */ import sinco.presentation.ContEstudioPrevioFirmaActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContEstudioPrevioFirmaAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContEstudioPrevioFirmaActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*     */ 
/*     */     
/*  47 */     int numeroEstudio = 0;
/*     */     try {
/*  49 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/*  51 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  54 */     if (_operacion == null || _operacion.length() == 0) {
/*  55 */       _operacion = "Nuevo";
/*     */     }
/*     */     
/*  58 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  59 */       creacion(comms);
/*     */     }
/*  61 */     this.pagHTML = (ContEstudioPrevioFirmaActHTML)comms.xmlcFactory.create(ContEstudioPrevioFirmaActHTML.class);
/*     */ 
/*     */     
/*  64 */     if (_operacion.equals("P")) {
/*  65 */       editar(comms);
/*     */     }
/*  67 */     else if (_operacion.equals("Nuevo")) {
/*  68 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  69 */       sel.getParentNode().removeChild(sel);
/*  70 */       nuevo(comms);
/*     */     } 
/*     */     
/*  73 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  74 */     comms.response.writeDOM(this.pagHTML);
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
/*  86 */     String _operacion = comms.request.getParameter("_operacion");
/*  87 */     String elUsuario = "" + comms.session.getUser().getName();
/*  88 */     int numeroEstudio = 0;
/*     */     try {
/*  90 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/*  92 */     catch (Exception e) {}
/*     */     
/*  94 */     String tipoFirma = comms.request.getParameter("tipoFirma");
/*  95 */     String tipoEstudio = comms.request.getParameter("tipoEstudio");
/*  96 */     int dependencia = 0;
/*     */     try {
/*  98 */       dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
/*     */     }
/* 100 */     catch (Exception e) {}
/* 101 */     boolean rta = false;
/* 102 */     if (_operacion.equals("E")) {
/*     */       
/* 104 */       ContEstudioPrevioFirmaDAO rs = new ContEstudioPrevioFirmaDAO();
/* 105 */       if (!rs.getEstadoConexion()) {
/* 106 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 108 */       rta = rs.eliminarRegistro(numeroEstudio, tipoFirma);
/* 109 */       rs.close();
/*     */       
/* 111 */       if (!rta) {
/* 112 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContEstudioPrevioFirma"));
/*     */       }
/* 114 */       String sPagina = "ContEstudioPrevioFirmaAct.po?_operacion=Nuevo&tipoEstudio=" + tipoEstudio + "&numeroEstudio=" + numeroEstudio + "&dependencia=" + dependencia;
/* 115 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 118 */     int funcionario = 0;
/*     */     try {
/* 120 */       funcionario = Integer.parseInt(comms.request.getParameter("funcionario"));
/* 121 */     } catch (Exception e) {}
/* 122 */     String descripcionFirma = comms.request.getParameter("descripcionFirma");
/*     */ 
/*     */     
/* 125 */     ContEstudioPrevioFirmaDAO rs = new ContEstudioPrevioFirmaDAO();
/* 126 */     if (!rs.getEstadoConexion()) {
/* 127 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 129 */     rta = false;
/*     */     
/* 131 */     if (_operacion.equals("C")) {
/* 132 */       ContEstudioPrevioFirmaDTO reg = rs.cargarRegistro(numeroEstudio, tipoFirma);
/* 133 */       if (reg != null) {
/* 134 */         _operacion = "M";
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 139 */     if (_operacion.equals("C")) {
/*     */ 
/*     */       
/* 142 */       rta = rs.crearRegistro(numeroEstudio, tipoFirma, funcionario, descripcionFirma, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 150 */       rta = rs.modificarRegistro(numeroEstudio, tipoFirma, funcionario, descripcionFirma, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     rs.close();
/* 158 */     if (!rta) {
/* 159 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContEstudioPrevioFirma"));
/*     */     }
/* 161 */     String sPagina = "ContEstudioPrevioFirmaAct.po?_operacion=P&numeroEstudio=" + numeroEstudio + "&tipoFirma=" + tipoFirma + "&tipoEstudio=" + tipoEstudio + "&dependencia=" + dependencia;
/* 162 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, int numeroEstudio, int dependencia) throws HttpPresentationException, KeywordValueException {
/* 173 */     ContEstudioPrevioFirmaDAO rs = new ContEstudioPrevioFirmaDAO();
/* 174 */     if (!rs.getEstadoConexion()) {
/* 175 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 178 */     Collection<ContEstudioPrevioFirmaDTO> arr = rs.cargarTodos(numeroEstudio);
/* 179 */     rs.close();
/* 180 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 181 */     int cuantas = 0;
/* 182 */     Iterator<ContEstudioPrevioFirmaDTO> iterator = arr.iterator();
/* 183 */     while (iterator.hasNext()) {
/* 184 */       ContEstudioPrevioFirmaDTO reg = (ContEstudioPrevioFirmaDTO)iterator.next();
/* 185 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 186 */       eltr.appendChild(newtd("" + reg.getDescripcionTipoFirma()));
/* 187 */       String url = "ContEstudioPrevioFirmaAct.po?_operacion=P&numeroEstudio=" + reg.getNumeroEstudio() + "&tipoFirma=" + reg.getTipoFirma() + "&dependencia=" + dependencia + "";
/* 188 */       eltr.appendChild(newtdhref("" + reg.getNombreFuncionario(), url));
/* 189 */       eltr.appendChild(newtd("" + reg.getDescripcionFirma()));
/* 190 */       hte.appendChild(eltr);
/* 191 */       cuantas++;
/*     */     } 
/* 193 */     arr.clear();
/* 194 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 209 */     int numeroEstudio = 0;
/*     */     try {
/* 211 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 213 */     catch (Exception e) {}
/*     */     
/* 215 */     String tipoFirma = comms.request.getParameter("tipoFirma");
/* 216 */     String tipoEstudio = comms.request.getParameter("tipoEstudio");
/* 217 */     int dependencia = 0;
/*     */     try {
/* 219 */       dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
/*     */     }
/* 221 */     catch (Exception e) {}
/* 222 */     this.pagHTML.getElementDependencia().setValue("" + dependencia);
/* 223 */     ContEstudioPrevioFirmaDAO rs = new ContEstudioPrevioFirmaDAO();
/* 224 */     if (!rs.getEstadoConexion()) {
/* 225 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 227 */     ContEstudioPrevioFirmaDTO reg = rs.cargarRegistro(numeroEstudio, tipoFirma);
/*     */     
/* 229 */     rs.close();
/* 230 */     if (reg != null) {
/* 231 */       this.pagHTML.getElementNumeroEstudio().setValue("" + reg.getNumeroEstudio());
/* 232 */       this.pagHTML.getElementTipoEstudio().setValue("" + tipoEstudio);
/* 233 */       this.pagHTML.getElementDescripcionFirma().setValue("" + reg.getDescripcionFirma());
/* 234 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 235 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 236 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 237 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 239 */       HTMLSelectElement combo = this.pagHTML.getElementTipoFirma();
/* 240 */       comboMultivaloresUnico(combo, "tipo_firma", "" + reg.getTipoFirma());
/*     */ 
/*     */       
/* 243 */       if (tipoFirma.equals("R")) {
/* 244 */         combo = this.pagHTML.getElementFuncionario();
/* 245 */         llenarCombo(combo, "sis_usuarios s, sis_usuarios_area sa", "s.codigo_empleado", "s.nombres||' '||s.apellidos", "s.estado='A' and sa.area_principal='S' and sa.codigo_empleado=s.codigo_empleado and (sa.codigo_area=200 OR sa.codigo_area=300 OR sa.codigo_area=420)", "" + reg.getFuncionario(), true);
/*     */       }
/*     */       else {
/*     */         
/* 249 */         combo = this.pagHTML.getElementFuncionario();
/* 250 */         llenarCombo(combo, "sis_usuarios s, sis_usuarios_area sa", "s.codigo_empleado", "s.nombres||' '||s.apellidos", "s.estado='A' and sa.area_principal='S' and sa.codigo_empleado=s.codigo_empleado and sa.codigo_area=" + dependencia + "", "" + reg.getFuncionario(), true);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 255 */       listar(comms, reg.getNumeroEstudio(), dependencia);
/*     */       
/* 257 */       this.pagHTML.getElementNumeroEstudio().setReadOnly(true);
/* 258 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 270 */     int numeroEstudio = 0;
/*     */     try {
/* 272 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 274 */     catch (Exception e) {}
/*     */     
/* 276 */     int dependencia = 0;
/*     */     try {
/* 278 */       dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
/*     */     }
/* 280 */     catch (Exception e) {}
/* 281 */     this.pagHTML.getElementDependencia().setValue("" + dependencia);
/*     */     
/* 283 */     String tipoFirma = comms.request.getParameter("tipoFirma");
/* 284 */     if (tipoFirma == null) {
/* 285 */       tipoFirma = "";
/*     */     }
/*     */     
/* 288 */     String tipoEstudio = comms.request.getParameter("tipoEstudio");
/*     */     
/* 290 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 291 */     this.pagHTML.getElementTipoEstudio().setValue("" + tipoEstudio);
/*     */ 
/*     */     
/* 294 */     if (tipoFirma.equals("")) {
/* 295 */       HTMLSelectElement combo = this.pagHTML.getElementTipoFirma();
/* 296 */       comboMultivalores(combo, "tipo_firma", "", false);
/* 297 */       combo = this.pagHTML.getElementFuncionario();
/* 298 */       llenarCombo(combo, "sis_usuarios s, sis_usuarios_area sa", "s.codigo_empleado", "s.nombres||' '||s.apellidos", "s.estado='A' and sa.area_principal='S' and sa.codigo_empleado=s.codigo_empleado and sa.codigo_area=" + dependencia + "", "", true);
/*     */     } else {
/*     */       
/* 301 */       HTMLSelectElement combo = this.pagHTML.getElementTipoFirma();
/* 302 */       comboMultivalores(combo, "tipo_firma", tipoFirma, false);
/*     */       
/* 304 */       if (tipoFirma.equals("R")) {
/* 305 */         combo = this.pagHTML.getElementFuncionario();
/* 306 */         llenarCombo(combo, "sis_usuarios s, sis_usuarios_area sa", "s.codigo_empleado", "s.nombres||' '||s.apellidos", "s.estado='A' and sa.area_principal='S' and sa.codigo_empleado=s.codigo_empleado and (sa.codigo_area=200 OR sa.codigo_area=300 OR sa.codigo_area=420 OR sa.codigo_area=350)", "", true);
/*     */       }
/*     */       else {
/*     */         
/* 310 */         combo = this.pagHTML.getElementFuncionario();
/* 311 */         llenarCombo(combo, "sis_usuarios s, sis_usuarios_area sa", "s.codigo_empleado", "s.nombres||' '||s.apellidos", "s.estado='A' and sa.area_principal='S' and sa.codigo_empleado=s.codigo_empleado and sa.codigo_area=" + dependencia + "", "", true);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     listar(comms, numeroEstudio, dependencia);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 330 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 331 */     atrib.setValue(valor);
/* 332 */     return atrib;
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
/* 345 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 346 */     Element enlace = this.pagHTML.createElement("a");
/* 347 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 348 */     enlace.appendChild(hijo);
/* 349 */     Attr donde = this.pagHTML.createAttribute("href");
/* 350 */     donde.setValue(vinculo);
/* 351 */     enlace.setAttributeNode(donde);
/* 352 */     td.appendChild(enlace);
/* 353 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 354 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 364 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 365 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 366 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 367 */     return td;
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
/* 382 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 383 */     Collection<SisMultiValoresDTO> arr = rs.cargarTablaOrderEntero(tabla);
/* 384 */     rs.close();
/* 385 */     if (dejarBlanco) {
/* 386 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 387 */       op.setValue("");
/* 388 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 389 */       combo.appendChild(op);
/*     */     } 
/* 391 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 392 */     while (iterator.hasNext()) {
/* 393 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 394 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 395 */       op.setValue("" + reg.getCodigo());
/* 396 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 397 */       if (defecto.equals(reg.getCodigo())) {
/* 398 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 399 */         escogida.setValue("on");
/* 400 */         op.setAttributeNode(escogida);
/*     */       } 
/* 402 */       combo.appendChild(op);
/*     */     } 
/* 404 */     arr.clear();
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
/*     */   private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto) {
/* 418 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 419 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla, defecto);
/* 420 */     rs.close();
/*     */     
/* 422 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 423 */     while (iterator.hasNext()) {
/* 424 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 425 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 426 */       op.setValue("" + reg.getCodigo());
/* 427 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 428 */       if (defecto.equals(reg.getCodigo())) {
/* 429 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 430 */         escogida.setValue("on");
/* 431 */         op.setAttributeNode(escogida);
/*     */       } 
/* 433 */       combo.appendChild(op);
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
/* 459 */     if (dejarBlanco) {
/* 460 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 461 */       op.setValue("");
/* 462 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 463 */       combo.appendChild(op);
/*     */     } 
/* 465 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 466 */     Collection<TGeneralDTO> arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
/* 467 */     rsTGen.close();
/*     */     
/* 469 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 470 */     while (iterator.hasNext()) {
/* 471 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 472 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 473 */       op.setValue("" + regGeneral.getCodigo());
/* 474 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 475 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 476 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 477 */         escogida.setValue("on");
/* 478 */         op.setAttributeNode(escogida);
/*     */       } 
/* 480 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContEstudioPrevioFirmaAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */