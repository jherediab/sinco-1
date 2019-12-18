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
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisEntidadDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisRepresentanteDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.SisEntidadDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisRepresentanteDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.SisRepresentante;
/*     */ import sinco.presentation.SisRepresentanteHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisRepresentante
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisRepresentanteHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  46 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  47 */     String _operacion = comms.request.getParameter("_operacion");
/*  48 */     if (_operacion == null || _operacion.length() == 0) {
/*  49 */       _operacion = "X";
/*     */     }
/*     */ 
/*     */     
/*  53 */     this.pagHTML = (SisRepresentanteHTML)comms.xmlcFactory.create(SisRepresentanteHTML.class);
/*     */ 
/*     */ 
/*     */     
/*  57 */     SisRepresentanteDAO ob = new SisRepresentanteDAO();
/*  58 */     Collection<SisRepresentanteDTO> arr = ob.cargarTodos(0L, 0L, "");
/*     */ 
/*     */ 
/*     */     
/*  62 */     Iterator<SisRepresentanteDTO> iterator = arr.iterator();
/*  63 */     while (iterator.hasNext()) {
/*  64 */       SisRepresentanteDTO reg = (SisRepresentanteDTO)iterator.next();
/*  65 */       if (reg.getPeriodoFinal().equals("")) {
/*     */         try {
/*  67 */           HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  68 */           elem.getParentNode().removeChild(elem);
/*  69 */         } catch (Exception e) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  78 */       creacion(comms);
/*     */     }
/*     */     
/*  81 */     permisos(comms);
/*     */ 
/*     */     
/*  84 */     long nitEntidad = 0L;
/*     */     try {
/*  86 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/*  88 */     catch (Exception e) {
/*  89 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEntidad"));
/*     */     } 
/*     */     
/*  92 */     this.pagHTML.getElementNitEntidadHidden().setValue("" + nitEntidad);
/*     */     
/*  94 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  95 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  98 */     if (_operacion.equals("P")) {
/*  99 */       editar(comms);
/*     */     }
/* 101 */     else if (_operacion.equals("Nuevo")) {
/* 102 */       nuevo(comms);
/*     */     } 
/*     */     
/* 105 */     if (_operacion.equals("V")) {
/* 106 */       verRegistro(comms);
/*     */     }
/* 108 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 109 */     comms.response.writeDOM(this.pagHTML);
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
/* 121 */     String _operacion = comms.request.getParameter("_operacion");
/* 122 */     String elUsuario = "" + comms.session.getUser().getName();
/* 123 */     int IdRepresentante = 0;
/*     */     try {
/* 125 */       IdRepresentante = Integer.parseInt(comms.request.getParameter("IdRepresentante"));
/*     */     }
/* 127 */     catch (Exception e) {
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=IdRepresentante"));
/*     */     } 
/*     */     
/* 131 */     long nitEntidad = 0L;
/*     */     try {
/* 133 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 135 */     catch (Exception e) {
/* 136 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEntidad"));
/*     */     } 
/*     */     
/* 139 */     RespuestaBD rta = new RespuestaBD();
/* 140 */     if (_operacion.equals("E")) {
/* 141 */       SisRepresentanteDAO ob = new SisRepresentanteDAO();
/* 142 */       rta = ob.eliminarRegistro(IdRepresentante, nitEntidad);
/* 143 */       if (!rta.isRta()) {
/* 144 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisRepresentante&p1=" + rta.getMensaje()));
/*     */       }
/* 146 */       String sPagina = "SisRepresentante.po?_operacion=L&nitEntidad=" + nitEntidad + "";
/* 147 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 149 */     String TipoDocumento = comms.request.getParameter("TipoDocumento");
/* 150 */     double NDocumento = 0.0D;
/*     */     try {
/* 152 */       NDocumento = Double.parseDouble(comms.request.getParameter("NDocumento"));
/*     */     }
/* 154 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 157 */     String Expedicion = comms.request.getParameter("Expedicion");
/* 158 */     String Nombre1 = comms.request.getParameter("Nombre1");
/* 159 */     String Nombre2 = comms.request.getParameter("Nombre2");
/* 160 */     String Apellido1 = comms.request.getParameter("Apellido1");
/* 161 */     String Apellido2 = comms.request.getParameter("Apellido2");
/* 162 */     String Direccion = comms.request.getParameter("Direccion");
/* 163 */     String departamento = comms.request.getParameter("departamento");
/* 164 */     String municipio = comms.request.getParameter("municipio");
/* 165 */     double Telefono = 0.0D;
/*     */     try {
/* 167 */       Telefono = Double.parseDouble(comms.request.getParameter("Telefono"));
/*     */     }
/* 169 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 172 */     String Cargo = comms.request.getParameter("Cargo");
/* 173 */     String TituloProfecion = comms.request.getParameter("TituloProfecion");
/* 174 */     String ActaNombramiento = comms.request.getParameter("ActaNombramiento");
/* 175 */     double NActa = 0.0D;
/*     */     try {
/* 177 */       NActa = Double.parseDouble(comms.request.getParameter("NActa"));
/*     */     }
/* 179 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 182 */     String FechaActa = comms.request.getParameter("FechaActa");
/* 183 */     String PeriodoInicial = comms.request.getParameter("PeriodoInicial");
/* 184 */     String PeriodoFinal = comms.request.getParameter("PeriodoFinal");
/* 185 */     SisRepresentanteDAO ob = new SisRepresentanteDAO();
/* 186 */     if (_operacion.equals("C")) {
/* 187 */       rta = ob.crearRegistro(IdRepresentante, TipoDocumento, NDocumento, Expedicion, Nombre1, Nombre2, Apellido1, Apellido2, Direccion, Telefono, Cargo, TituloProfecion, ActaNombramiento, NActa, FechaActa, PeriodoInicial, PeriodoFinal, nitEntidad, departamento, municipio, elUsuario);
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
/* 209 */       IdRepresentante = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 212 */       rta = ob.modificarRegistro(IdRepresentante, TipoDocumento, NDocumento, Expedicion, Nombre1, Nombre2, Apellido1, Apellido2, Direccion, Telefono, Cargo, TituloProfecion, ActaNombramiento, NActa, FechaActa, PeriodoInicial, PeriodoFinal, nitEntidad, departamento, municipio, elUsuario);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (!rta.isRta()) {
/* 236 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisRepresentante&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 239 */     String sPagina = "SisRepresentante.po?_operacion=P&IdRepresentante=" + IdRepresentante + "&nitEntidad=" + nitEntidad + "";
/* 240 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 251 */     int IdRepresentante = 0;
/*     */     try {
/* 253 */       IdRepresentante = Integer.parseInt(comms.request.getParameter("IdRepresentante"));
/*     */     }
/* 255 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 258 */     long nitEntidad = 0L;
/*     */     try {
/* 260 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 262 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 266 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 267 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 268 */     this.pagHTML.setTextRepresentantesEntidad(" " + regEnt.getNombre());
/*     */     
/* 270 */     SisRepresentanteDAO ob = new SisRepresentanteDAO();
/* 271 */     SisRepresentanteDTO reg = ob.cargarRegistro(IdRepresentante, nitEntidad);
/* 272 */     if (reg != null) {
/* 273 */       this.pagHTML.getElementIdRepresentante().setValue("" + reg.getIdRepresentante());
/* 274 */       this.pagHTML.getElementNDocumento().setValue("" + Utilidades.formatDouble2(reg.getNDocumento()));
/* 275 */       this.pagHTML.getElementExpedicion().setValue("" + reg.getExpedicion());
/* 276 */       this.pagHTML.getElementNDocumento().setReadOnly(true);
/* 277 */       this.pagHTML.getElementExpedicion().setReadOnly(true);
/* 278 */       this.pagHTML.getElementNombre1().setValue("" + reg.getNombre1());
/* 279 */       this.pagHTML.getElementNombre2().setValue("" + reg.getNombre2());
/* 280 */       this.pagHTML.getElementApellido1().setValue("" + reg.getApellido1());
/* 281 */       this.pagHTML.getElementApellido2().setValue("" + reg.getApellido2());
/* 282 */       this.pagHTML.getElementDireccion().setValue("" + reg.getDireccion());
/* 283 */       this.pagHTML.getElementTelefono().setValue("" + Utilidades.formatDouble2(reg.getTelefono()));
/* 284 */       this.pagHTML.getElementCargo().setValue("" + reg.getCargo());
/* 285 */       this.pagHTML.getElementTituloProfecion().setValue("" + reg.getTituloProfecion());
/* 286 */       this.pagHTML.getElementActaNombramiento().setValue("" + reg.getActaNombramiento());
/* 287 */       this.pagHTML.getElementNActa().setValue("" + Utilidades.formatDouble2(reg.getNActa()));
/* 288 */       this.pagHTML.getElementFechaActa().setValue("" + Utilidades.darFormatoFecha(reg.getFechaActa()));
/* 289 */       this.pagHTML.getElementPeriodoInicial().setValue("" + Utilidades.darFormatoFecha(reg.getPeriodoInicial()));
/* 290 */       this.pagHTML.getElementPeriodoFinal().setValue("" + Utilidades.darFormatoFecha(reg.getPeriodoFinal()));
/* 291 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 292 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 293 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 294 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */       
/* 296 */       this.pagHTML.getElementIdRepresentante().setReadOnly(true);
/*     */       
/* 298 */       HTMLSelectElement combo = this.pagHTML.getElementTipoDocumento();
/* 299 */       comboMultivalores(combo, "tipo_documento", "" + reg.getTipoDocumento(), true);
/*     */       
/* 301 */       combo = this.pagHTML.getElementDepartamento();
/* 302 */       llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
/*     */       
/* 304 */       if (reg.getDepartamento().length() > 0) {
/* 305 */         combo = this.pagHTML.getElementMunicipio();
/* 306 */         llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamento() + "'", "" + reg.getMunicipio(), true);
/*     */       } 
/*     */     } 
/*     */     
/* 310 */     this.pagHTML.getElement_operacion().setValue("M");
/* 311 */     activarVista("nuevo");
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
/* 323 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 325 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 326 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 328 */     catch (Exception e) {}
/*     */     
/* 330 */     long nitEntidad = 0L;
/*     */     try {
/* 332 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 334 */     catch (Exception e) {}
/*     */     
/* 336 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 337 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 338 */     this.pagHTML.setTextRepresentantesEntidad(" " + regEnt.getNombre());
/*     */     
/* 340 */     HTMLSelectElement combo = this.pagHTML.getElementTipoDocumento();
/* 341 */     comboMultivalores(combo, "tipo_documento", "", true);
/*     */     
/* 343 */     combo = this.pagHTML.getElementDepartamento();
/* 344 */     llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
/*     */ 
/*     */     
/* 347 */     activarVista("nuevo");
/* 348 */     this.pagHTML.getElementIdRepresentante().setReadOnly(true);
/* 349 */     this.pagHTML.getElementIdRepresentante().setValue("0");
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
/* 360 */     activarVista("consulta");
/*     */     
/* 362 */     long nitEntidad = 0L;
/*     */     try {
/* 364 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 366 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 369 */     long numeroDocRepresentante = 0L;
/*     */     try {
/* 371 */       numeroDocRepresentante = Long.parseLong(comms.request.getParameter("numeroDocRepresentante"));
/*     */     }
/* 373 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 376 */     String nombreRepresentanteConsulta = comms.request.getParameter("nombreRepresentanteConsulta");
/* 377 */     if (nombreRepresentanteConsulta == null) {
/* 378 */       nombreRepresentanteConsulta = "";
/*     */     }
/* 380 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 381 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 382 */     this.pagHTML.setTextRepresentantesEntidad(" " + regEnt.getNombre());
/* 383 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 388 */     SisRepresentanteDAO ob = new SisRepresentanteDAO();
/* 389 */     Collection<SisRepresentanteDTO> arr = ob.cargarTodos(nitEntidad, numeroDocRepresentante, nombreRepresentanteConsulta);
/*     */ 
/*     */ 
/*     */     
/* 393 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 394 */     int cuantas = 0;
/* 395 */     Iterator<SisRepresentanteDTO> iterator = arr.iterator();
/* 396 */     while (iterator.hasNext()) {
/* 397 */       SisRepresentanteDTO reg = (SisRepresentanteDTO)iterator.next();
/* 398 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 399 */       String url = "SisRepresentante.po?_operacion=V&IdRepresentante=" + reg.getIdRepresentante() + "&nitEntidad=" + reg.getNitEntidad() + "";
/* 400 */       eltr.appendChild(newtdhref("" + reg.getIdRepresentante(), url));
/* 401 */       eltr.appendChild(newtdhref("" + Utilidades.miles(reg.getNDocumento()), url));
/*     */       
/* 403 */       eltr.appendChild(newtdhref("" + reg.getNombre1() + " " + reg.getNombre2() + " " + reg.getApellido1() + " " + reg.getApellido2(), url));
/*     */ 
/*     */       
/* 406 */       eltr.appendChild(newtd("" + reg.getCargo()));
/*     */       
/* 408 */       eltr.appendChild(newtd("" + reg.getActaNombramiento()));
/* 409 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getNActa())));
/* 410 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaActa())));
/* 411 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getPeriodoInicial())));
/* 412 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getPeriodoFinal())));
/* 413 */       hte.appendChild(eltr);
/* 414 */       cuantas++;
/*     */     } 
/* 416 */     arr.clear();
/* 417 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 430 */     int IdRepresentante = 0;
/*     */     try {
/* 432 */       IdRepresentante = Integer.parseInt(comms.request.getParameter("IdRepresentante"));
/*     */     }
/* 434 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 437 */     long nitEntidad = 0L;
/*     */     try {
/* 439 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 441 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 444 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 445 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 446 */     this.pagHTML.setTextRepresentantesEntidad(" " + regEnt.getNombre());
/*     */     
/* 448 */     SisRepresentanteDAO ob = new SisRepresentanteDAO();
/* 449 */     SisRepresentanteDTO reg = ob.cargarRegistro(IdRepresentante, nitEntidad);
/* 450 */     if (reg != null) {
/* 451 */       this.pagHTML.setTextIdRepresentanteEd("" + reg.getIdRepresentante());
/* 452 */       this.pagHTML.setTextTipoDocumentoEd("" + reg.getTipoDocumento());
/* 453 */       this.pagHTML.setTextNDocumentoEd("" + Utilidades.miles(reg.getNDocumento()));
/* 454 */       this.pagHTML.setTextExpedicionEd("" + reg.getExpedicion());
/* 455 */       this.pagHTML.setTextNombre1Ed("" + reg.getNombre1());
/* 456 */       this.pagHTML.setTextNombre2Ed("" + reg.getNombre2());
/* 457 */       this.pagHTML.setTextApellido1Ed("" + reg.getApellido1());
/* 458 */       this.pagHTML.setTextApellido2Ed("" + reg.getApellido2());
/* 459 */       this.pagHTML.setTextDireccionEd("" + reg.getDireccion());
/* 460 */       this.pagHTML.setTextTelefonoEd("" + Utilidades.miles(reg.getTelefono()));
/* 461 */       this.pagHTML.setTextCargoEd("" + reg.getCargo());
/* 462 */       this.pagHTML.setTextTituloProfecionEd("" + reg.getTituloProfecion());
/* 463 */       this.pagHTML.setTextActaNombramientoEd("" + reg.getActaNombramiento());
/* 464 */       this.pagHTML.setTextNActaEd("" + Utilidades.miles(reg.getNActa()));
/* 465 */       this.pagHTML.setTextFechaActaEd("" + Utilidades.darFormatoFecha(reg.getFechaActa()));
/* 466 */       this.pagHTML.setTextPeriodoInicialEd("" + Utilidades.darFormatoFecha(reg.getPeriodoInicial()));
/* 467 */       this.pagHTML.setTextPeriodoFinalEd("" + Utilidades.darFormatoFecha(reg.getPeriodoFinal()));
/* 468 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 469 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 470 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 471 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 472 */       this.pagHTML.getElementNitEntidadHidden().setValue("" + reg.getNitEntidad());
/* 473 */       this.pagHTML.getElementIdRepresentanteKey().setValue("" + reg.getIdRepresentante());
/* 474 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */       
/* 476 */       if (!reg.getPeriodoFinal().equals("")) {
/*     */         try {
/* 478 */           HTMLElement elem = this.pagHTML.getElementBtnModificar();
/* 479 */           elem.getParentNode().removeChild(elem);
/* 480 */         } catch (Exception e) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 486 */     activarVista("editar");
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
/* 497 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 499 */     Varios oVarios = new Varios();
/* 500 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoSisRepresentanteAct");
/* 501 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoSisRepresentanteDel");
/* 502 */     if (!oPermisoAct) {
/* 503 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 504 */       elem.getParentNode().removeChild(elem);
/* 505 */       elem = this.pagHTML.getElementBtnGrabar();
/* 506 */       elem.getParentNode().removeChild(elem);
/* 507 */       elem = this.pagHTML.getElementBtnModificar();
/* 508 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 510 */     if (!oPermisoDel) {
/* 511 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 512 */       elem.getParentNode().removeChild(elem);
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
/* 523 */     if (!vista.equals("nuevo")) {
/* 524 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 525 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 527 */     if (!vista.equals("editar")) {
/* 528 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 529 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 531 */     if (!vista.equals("consulta")) {
/* 532 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 533 */       sel.getParentNode().removeChild(sel);
/* 534 */       sel = this.pagHTML.getElementDivResultados();
/* 535 */       sel.getParentNode().removeChild(sel);
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
/* 549 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 550 */     atrib.setValue(valor);
/* 551 */     return atrib;
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
/* 564 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 565 */     Element enlace = this.pagHTML.createElement("a");
/* 566 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 567 */     enlace.appendChild(hijo);
/* 568 */     Attr donde = this.pagHTML.createAttribute("href");
/* 569 */     donde.setValue(vinculo);
/* 570 */     enlace.setAttributeNode(donde);
/* 571 */     td.appendChild(enlace);
/* 572 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 573 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 583 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 584 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 585 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 586 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 602 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 603 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 604 */     ob.close();
/* 605 */     if (dejarBlanco) {
/* 606 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 607 */       op.setValue("");
/* 608 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 609 */       combo.appendChild(op);
/*     */     } 
/* 611 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 612 */     while (iterator.hasNext()) {
/* 613 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 614 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 615 */       op.setValue("" + reg.getCodigo());
/* 616 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 617 */       if (defecto.equals(reg.getCodigo())) {
/* 618 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 619 */         escogida.setValue("on");
/* 620 */         op.setAttributeNode(escogida);
/*     */       } 
/* 622 */       combo.appendChild(op);
/*     */     } 
/* 624 */     arr.clear();
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
/* 647 */     if (dejarBlanco) {
/* 648 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 649 */       op.setValue("");
/* 650 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 651 */       combo.appendChild(op);
/*     */     } 
/* 653 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 654 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 655 */     rsTGen.close();
/* 656 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 657 */     while (iterator.hasNext()) {
/* 658 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 659 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 660 */       op.setValue("" + regGeneral.getCodigoS());
/* 661 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 662 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 663 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 664 */         escogida.setValue("on");
/* 665 */         op.setAttributeNode(escogida);
/*     */       } 
/* 667 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisRepresentante.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */