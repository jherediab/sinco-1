/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalPlanesDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.CalCiclosDAO;
/*     */ import sinco.data.CalPlanMetasDAO;
/*     */ import sinco.data.CalPlanObjetivosDAO;
/*     */ import sinco.data.CalPlanesDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalVerPlanArea;
/*     */ import sinco.presentation.CalVerPlanAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalVerPlanArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalVerPlanAreaHTML pagHTML;
/*  50 */   String idNav = "";
/*  51 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  54 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  55 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  58 */     this.idNav = (String)comms.session.getSessionData().get("miId");
/*  59 */     this.elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  61 */     String _operacion = comms.request.getParameter("_operacion");
/*  62 */     if (_operacion == null || _operacion.length() == 0) {
/*  63 */       _operacion = "FASE1";
/*     */     }
/*     */ 
/*     */     
/*  67 */     if (_operacion.equals("FASE1") || _operacion.equals("RECARGA")) {
/*  68 */       fase1(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     if (_operacion.equals("VER")) {
/*  73 */       verPlan(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  77 */     this.pagHTML = (CalVerPlanAreaHTML)comms.xmlcFactory.create(CalVerPlanAreaHTML.class);
/*  78 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(this.idNav));
/*  79 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private void fase1(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  84 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  86 */     int ciclo = 0;
/*     */     try {
/*  88 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  90 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  93 */     this.pagHTML = (CalVerPlanAreaHTML)comms.xmlcFactory.create(CalVerPlanAreaHTML.class);
/*     */     
/*  95 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  96 */     if (!rsTGen.getEstadoConexion()) {
/*  97 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 100 */     HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
/* 101 */     llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado<>'D' order by ciclo desc");
/*     */ 
/*     */     
/* 104 */     if (ciclo > 0) {
/*     */       
/* 106 */       SisUsuariosDAO pf2 = new SisUsuariosDAO();
/* 107 */       SisUsuariosDTO p = pf2.cargarRegistro(Integer.parseInt(this.idNav));
/*     */       
/* 109 */       SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 110 */       if (!rsSeguridad.getEstadoConexion()) {
/*     */         return;
/*     */       }
/* 113 */       boolean oVerTodas = rsSeguridad.tieneLlave(miGrupo, "cal_ver_todas_las_areas");
/* 114 */       boolean oVerArriba = rsSeguridad.tieneLlave(miGrupo, "cal_ver_area_superior");
/* 115 */       rsSeguridad.close();
/*     */       
/* 117 */       combo = this.pagHTML.getElementIdArea();
/* 118 */       comboAreas(combo, ciclo, p.getArea(), oVerTodas, oVerArriba);
/*     */     } else {
/*     */       
/* 121 */       HTMLElement sel = this.pagHTML.getElementTrArea();
/* 122 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 124 */     rsTGen.close();
/*     */     
/* 126 */     HTMLElement eltr = this.pagHTML.getElementTrPlan();
/* 127 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 129 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 130 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 140 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 142 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 143 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     
/* 145 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 146 */     if (!rsSeguridad.getEstadoConexion()) {
/* 147 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 149 */     boolean bActualizarPlan = rsSeguridad.tieneLlave(miGrupo, "cal_actualizar_plan");
/* 150 */     rsSeguridad.close();
/*     */     
/* 152 */     AreasDAO areaf = new AreasDAO();
/* 153 */     AreasDTO regArea = areaf.getArea(area);
/* 154 */     areaf.close();
/*     */     
/* 156 */     this.pagHTML = (CalVerPlanAreaHTML)comms.xmlcFactory.create(CalVerPlanAreaHTML.class);
/*     */     
/* 158 */     CalPlanesDAO rsPlanes = new CalPlanesDAO();
/* 159 */     if (!rsPlanes.getEstadoConexion()) {
/* 160 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 162 */     CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
/* 163 */     rsPlanes.close();
/* 164 */     this.pagHTML.setTextFechaInsercion(regPlan.getFechaInsercion());
/* 165 */     this.pagHTML.setTextUsuarioInsercion(regPlan.getUsuarioInsercion());
/* 166 */     this.pagHTML.setTextFechaModificacion(regPlan.getFechaModificacion());
/* 167 */     this.pagHTML.setTextUsuarioModificacion(regPlan.getUsuarioModificacion());
/*     */     
/* 169 */     this.pagHTML.getElementCicloV().setValue("" + ciclo);
/* 170 */     this.pagHTML.getElementCodigoPlanV().setValue("" + regPlan.getCodigoPlan());
/* 171 */     this.pagHTML.getElementAreaV().setValue("" + area);
/*     */     
/* 173 */     if (!bActualizarPlan) {
/* 174 */       HTMLElement eltr = this.pagHTML.getElementNuevoObjetivo();
/* 175 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */     
/* 178 */     CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
/* 179 */     if (!rsCalCiclos.getEstadoConexion()) {
/* 180 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 182 */     CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
/* 183 */     rsCalCiclos.close();
/*     */     
/* 185 */     if (regCiclo != null) {
/* 186 */       this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
/*     */     }
/* 188 */     this.pagHTML.setTextNumeroPlan("" + regPlan.getCodigoPlan());
/* 189 */     this.pagHTML.setTextNombreAreaA((regArea != null) ? regArea.getDescripcion() : "");
/*     */     
/* 191 */     CalPlanObjetivosDAO rs = new CalPlanObjetivosDAO();
/* 192 */     if (!rs.getEstadoConexion()) {
/* 193 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 196 */     HTMLTableElement hte = this.pagHTML.getElementPlan();
/* 197 */     boolean fondo = true;
/*     */     
/* 199 */     rs.cargarPlan(ciclo, regPlan.getCodigoPlan(), "A");
/* 200 */     int cuantas = 0;
/*     */     
/* 202 */     CalObjetivosDTO reg = rs.next();
/* 203 */     String proceso = "";
/* 204 */     String subProceso = "";
/* 205 */     while (reg != null) {
/*     */       
/* 207 */       if (cuantas == 0 || !proceso.equals(reg.getProceso()) || !subProceso.equals(reg.getSubProceso())) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 212 */         if (!proceso.equals(reg.getProceso()) || cuantas == 0) {
/* 213 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 214 */           eltr.appendChild(newtd("" + reg.getNombreProceso(), 5, "CA"));
/* 215 */           hte.appendChild(eltr);
/*     */         } 
/*     */         
/* 218 */         if (cuantas == 0 || !proceso.equals(reg.getProceso()) || !subProceso.equals(reg.getSubProceso())) {
/*     */ 
/*     */ 
/*     */           
/* 222 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 223 */           eltr.appendChild(newtd("" + reg.getNombreSubProceso(), 5, "CA2"));
/* 224 */           hte.appendChild(eltr);
/*     */         } 
/*     */       } 
/*     */       
/* 228 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 229 */       fondo = !fondo;
/* 230 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 232 */       if (reg.getTipoObjetivo().equals("E") && bActualizarPlan) {
/* 233 */         eltr.appendChild(newtdhref("" + reg.getDescripcion(), "CalPlanObjAct.po?_operacion=P&ciclo=" + ciclo + "&codigoPlan=" + regPlan.getCodigoPlan() + "&codigoObjetivo=" + reg.getCodigoObjetivo(), false, 0));
/*     */       } else {
/*     */         
/* 236 */         eltr.appendChild(newtd("" + reg.getDescripcion(), 0, ""));
/*     */       } 
/*     */       
/* 239 */       eltr.appendChild(newtd("" + reg.getTipoObjetivo(), 0, ""));
/*     */ 
/*     */ 
/*     */       
/* 243 */       eltr.appendChild(lasMetas(ciclo, regPlan.getCodigoPlan(), reg.getCodigoObjetivo(), true, bActualizarPlan, reg.getTipoObjetivo()));
/*     */       
/* 245 */       hte.appendChild(eltr);
/*     */       
/* 247 */       proceso = reg.getProceso();
/* 248 */       subProceso = reg.getSubProceso();
/* 249 */       reg = rs.next();
/* 250 */       cuantas++;
/*     */     } 
/* 252 */     rs.close();
/*     */     
/* 254 */     HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
/* 255 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 257 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 258 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private HTMLElement lasMetas(int ciclo, int plan, int objetivo, boolean modificaEspecificos, boolean permiso, String tipoObjetivo) {
/* 276 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 277 */     laTabla.setAttributeNode(newAttr("class", "fprofb"));
/* 278 */     laTabla.setAttributeNode(newAttr("align", "left"));
/* 279 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 281 */     CalPlanMetasDAO rs = new CalPlanMetasDAO();
/* 282 */     Collection arr = rs.cargarDeObjetivo(ciclo, plan, objetivo, 0, "A");
/* 283 */     rs.close();
/*     */     
/* 285 */     boolean fondo = true;
/*     */ 
/*     */     
/* 288 */     Iterator iterator = arr.iterator();
/* 289 */     while (iterator.hasNext()) {
/* 290 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/* 291 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 293 */       fondo = !fondo;
/* 294 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 296 */       if (tipoObjetivo.equals("E") && modificaEspecificos && permiso) {
/* 297 */         eltr.appendChild(newtdhref("" + reg.getDescripcion(), "CalPlanMetasAct.po?_operacion=P&ciclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + reg.getCodigoMeta(), false, 70));
/*     */       } else {
/*     */         
/* 300 */         eltr.appendChild(newtd("" + reg.getDescripcion(), 70));
/*     */       } 
/* 302 */       eltr.appendChild(newtd("" + reg.getValorMeta(), 10));
/* 303 */       eltr.appendChild(newtdhref("" + reg.getNombreFrecuenciaMedicion(), "CalPlanRecursos.po?_operacion=P&ciclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + reg.getCodigoMeta(), true, 10));
/* 304 */       eltr.appendChild(newtd("" + reg.getNombreUnidadMedida(), 10));
/*     */       
/* 306 */       laTabla.appendChild(eltr);
/*     */     } 
/*     */     
/* 309 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 310 */     td.appendChild(laTabla);
/* 311 */     return td;
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 325 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 326 */     atrib.setValue(valor);
/* 327 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva, int ancho) {
/* 340 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 341 */     Element enlace = this.pagHTML.createElement("a");
/* 342 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 343 */     enlace.appendChild(hijo);
/* 344 */     Attr donde = this.pagHTML.createAttribute("href");
/* 345 */     donde.setValue(vinculo);
/* 346 */     enlace.setAttributeNode(donde);
/* 347 */     if (nueva) {
/* 348 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 350 */     if (ancho > 0) {
/* 351 */       td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/*     */     }
/* 353 */     td.appendChild(enlace);
/* 354 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, int colspan, String clase) {
/* 364 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 365 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 366 */     if (colspan > 0) {
/* 367 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 369 */     if (clase.length() > 0) {
/* 370 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/*     */     
/* 373 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, int ancho) {
/* 383 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 384 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 385 */     td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/* 386 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion) {
/* 406 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 407 */     op.setValue("0");
/* 408 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 409 */     combo.appendChild(op);
/*     */     
/* 411 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*     */     TGeneralDTO RegGeneral;
/* 413 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 414 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 415 */       op.setValue("" + RegGeneral.getCodigo());
/* 416 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 417 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/* 418 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 419 */         escogida.setValue("on");
/* 420 */         op.setAttributeNode(escogida);
/*     */       } 
/* 422 */       combo.appendChild(op);
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
/*     */   private void comboAreas(HTMLSelectElement combo, int ciclo, int area1, boolean verTodas, boolean oVerArriba) {
/* 437 */     Collection arr = new ArrayList();
/* 438 */     AreasDAO af = new AreasDAO();
/*     */     
/* 440 */     if (verTodas) {
/* 441 */       arr = af.cargarAreasCicloCalidad(ciclo);
/*     */     } else {
/*     */       
/* 444 */       String superior = "";
/* 445 */       if (oVerArriba) {
/* 446 */         Varios oVarios = new Varios();
/* 447 */         superior = oVarios.getNivelSuperior(area1, ParametrosDTO.getInt("cal_numero_areas_arriba"));
/*     */       } 
/* 449 */       arr = af.cargarSecuenciaCalidad(ciclo, area1, superior);
/*     */     } 
/* 451 */     af.close();
/*     */     
/* 453 */     Iterator iterator = arr.iterator();
/* 454 */     while (iterator.hasNext()) {
/* 455 */       AreasDTO area = (AreasDTO)iterator.next();
/* 456 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 457 */       op.setValue("" + area.getCodigo());
/* 458 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*     */       
/* 460 */       if (area1 == area.getCodigo()) {
/* 461 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 462 */         escogida.setValue("on");
/* 463 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 466 */       combo.appendChild(op);
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva) {
/* 481 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 482 */     Element enlace = this.pagHTML.createElement("a");
/* 483 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 484 */     enlace.appendChild(hijo);
/* 485 */     Attr donde = this.pagHTML.createAttribute("href");
/* 486 */     donde.setValue(vinculo);
/* 487 */     enlace.setAttributeNode(donde);
/* 488 */     if (nueva) {
/* 489 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 491 */     td.appendChild(enlace);
/* 492 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 493 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalVerPlanArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */