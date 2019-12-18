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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AMAccionesEstadisticaDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AMEstadisticasDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.presentation.AMNoEfectivas;
/*     */ import sinco.presentation.AMNoEfectivasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Utilidades2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMNoEfectivas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMNoEfectivasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  45 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  49 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  50 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*  51 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  53 */     int area = 0;
/*     */     
/*     */     try {
/*  56 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       area = miArea;
/*     */     } 
/*     */     
/*  62 */     String _operacion = comms.request.getParameter("_operacion");
/*  63 */     if (_operacion == null) _operacion = "Fase1";
/*     */     
/*  65 */     this.pagHTML = (AMNoEfectivasHTML)comms.xmlcFactory.create(AMNoEfectivasHTML.class);
/*     */     
/*  67 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/*  68 */     if (fechaDesde == null) fechaDesde = Utilidades.getAnnoActual() + "-01-01";
/*     */     
/*  70 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/*  71 */     if (fechaHasta == null) fechaHasta = Utilidades.darFormatoFecha(Utilidades2.diaSiguiente());
/*     */ 
/*     */     
/*  74 */     this.pagHTML.getElementFechaDesde().setValue("" + fechaDesde);
/*  75 */     this.pagHTML.getElementFechaHasta().setValue("" + fechaHasta);
/*     */     
/*  77 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  78 */     if (!rsSeguridad.getEstadoConexion()) {
/*  79 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  81 */     boolean verTodas = rsSeguridad.tieneLlave(miGrupo, "cal_ver_todas_las_areas");
/*  82 */     rsSeguridad.close();
/*     */ 
/*     */     
/*  85 */     comboAreas(miArea, area, verTodas);
/*     */     
/*  87 */     if (_operacion.equals("Listar")) {
/*  88 */       boolean bTodas = false;
/*  89 */       if (miArea == ParametrosDTO.getInt("codigo.gerencia.sinco.servicio")) {
/*  90 */         bTodas = true;
/*     */       }
/*     */       
/*  93 */       int cuantos = resultados(fechaDesde, fechaHasta, bTodas, area);
/*  94 */       if (cuantos == -1) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*     */       
/*  98 */       Element elemento = this.pagHTML.getElementTblDetalle();
/*  99 */       elemento.getParentNode().removeChild(elemento);
/*     */     
/*     */     }
/* 102 */     else if (_operacion.equals("Detalle") || _operacion.equals("causas")) {
/* 103 */       int areaImplanta = 0;
/*     */       try {
/* 105 */         areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */       }
/* 107 */       catch (Exception e) {}
/*     */ 
/*     */       
/* 110 */       String accion = comms.request.getParameter("accion");
/* 111 */       String origen = comms.request.getParameter("origen");
/* 112 */       String cumplio = comms.request.getParameter("cumplio");
/* 113 */       int estado = Integer.parseInt(comms.request.getParameter("estado"));
/* 114 */       int cuantos = detalleResultados(fechaDesde, fechaHasta, areaImplanta, accion, origen, cumplio, estado);
/* 115 */       if (cuantos == -1) {
/* 116 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 118 */       Element elemento = this.pagHTML.getElementTblResultados();
/* 119 */       elemento.getParentNode().removeChild(elemento);
/*     */     }
/*     */     else {
/*     */       
/* 123 */       Element elemento = this.pagHTML.getElementTblResultados();
/* 124 */       elemento.getParentNode().removeChild(elemento);
/*     */       
/* 126 */       elemento = this.pagHTML.getElementTblDetalle();
/* 127 */       elemento.getParentNode().removeChild(elemento);
/*     */     } 
/*     */ 
/*     */     
/* 131 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 132 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private int resultados(String fechaDesde, String fechaHasta, boolean bTodas, int elArea) {
/* 145 */     AMEstadisticasDAO rs = new AMEstadisticasDAO();
/* 146 */     if (!rs.getEstadoConexion()) {
/* 147 */       return -1;
/*     */     }
/* 149 */     int cuantos = 0;
/*     */     
/* 151 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleR();
/* 152 */     boolean fondo = true;
/* 153 */     Collection arr = rs.cargarEstadisticaNoEfectivas(fechaDesde, fechaHasta, bTodas, elArea);
/* 154 */     rs.close();
/*     */     
/* 156 */     int tNroAcciones = 0;
/* 157 */     int tNroCausas = 0;
/* 158 */     int tCorrectivas = 0;
/* 159 */     int tPreventivas = 0;
/* 160 */     int tCorrecciones = 0;
/* 161 */     int tSatisfactorias = 0;
/* 162 */     int tInSatisfactorias = 0;
/*     */     
/* 164 */     Iterator iterator = arr.iterator();
/* 165 */     while (iterator.hasNext()) {
/* 166 */       AMAccionesEstadisticaDTO reg = (AMAccionesEstadisticaDTO)iterator.next();
/* 167 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 168 */       fondo = !fondo;
/* 169 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 171 */       eltr.appendChild(newtd("" + reg.getNombreArea(), false, 0));
/* 172 */       eltr.appendChild(newtd("" + reg.getTotal(), true, 0));
/* 173 */       eltr.appendChild(newtd("" + reg.getNumeroCausas(), true, 0));
/*     */       
/* 175 */       String url = "AMNoEfectivas.po?_operacion=Detalle&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&accion=C&cumplio=N&areaImplanta=" + reg.getAreaImplanta() + "&estado=0";
/*     */ 
/*     */       
/* 178 */       eltr.appendChild(newtdhref("" + reg.getCorrectivas(), url, 0));
/*     */       
/* 180 */       url = "AMNoEfectivas.po?_operacion=Detalle&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&accion=P&cumplio=N&areaImplanta=" + reg.getAreaImplanta() + "&estado=0";
/*     */       
/* 182 */       eltr.appendChild(newtdhref("" + reg.getPreventivas(), url, 0));
/*     */ 
/*     */       
/* 185 */       url = "AMNoEfectivas.po?_operacion=Detalle&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&accion=R&cumplio=N&areaImplanta=" + reg.getAreaImplanta() + "&estado=0";
/*     */       
/* 187 */       eltr.appendChild(newtdhref("" + reg.getCorrecciones(), url, 0));
/*     */       
/* 189 */       url = "AMNoEfectivas.po?_operacion=causas&areaImplanta=" + reg.getAreaImplanta() + "&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&estado=2";
/* 190 */       eltr.appendChild(newtdhref("" + reg.getSatisfactorias(), url, 0));
/*     */       
/* 192 */       url = "AMNoEfectivas.po?_operacion=causas&areaImplanta=" + reg.getAreaImplanta() + "&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&estado=3";
/* 193 */       eltr.appendChild(newtdhref("" + reg.getInsatisfactorias(), url, 0));
/*     */       
/* 195 */       hte.appendChild(eltr);
/* 196 */       cuantos++;
/*     */       
/* 198 */       tNroAcciones += reg.getTotal();
/* 199 */       tNroCausas += reg.getNumeroCausas();
/* 200 */       tCorrectivas += reg.getCorrectivas();
/* 201 */       tPreventivas += reg.getPreventivas();
/* 202 */       tCorrecciones += reg.getCorrecciones();
/* 203 */       tSatisfactorias += reg.getSatisfactorias();
/* 204 */       tInSatisfactorias += reg.getInsatisfactorias();
/*     */     } 
/*     */     
/* 207 */     this.pagHTML.setTextTNroAcciones("" + tNroAcciones);
/* 208 */     this.pagHTML.setTextTNroCausas("" + tNroCausas);
/* 209 */     this.pagHTML.setTextTCorrectivas("" + tCorrectivas);
/* 210 */     this.pagHTML.setTextTPreventivas("" + tPreventivas);
/* 211 */     this.pagHTML.setTextTCorrecciones("" + tCorrecciones);
/* 212 */     this.pagHTML.setTextTSatisfactorias("" + tSatisfactorias);
/* 213 */     this.pagHTML.setTextTInSatisfactorias("" + tInSatisfactorias);
/*     */     
/* 215 */     return cuantos;
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
/*     */   private int detalleResultados(String fechaDesde, String fechaHasta, int areaImplanta, String accion, String origen, String cumplio, int estado) {
/* 238 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/* 239 */     Collection arr = (estado == 0) ? rsAcciones.cargarParaNoEfectivas(areaImplanta, fechaDesde, fechaHasta, accion, cumplio) : rsAcciones.cargarParaNoEfectivasPorCausa(areaImplanta, fechaDesde, fechaHasta, estado);
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
/* 256 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleD();
/* 257 */     boolean fondo = true;
/* 258 */     int iCuantos = 0;
/* 259 */     Iterator iterator = arr.iterator();
/* 260 */     while (iterator.hasNext()) {
/* 261 */       AMAccionesDTO regAcciones = (AMAccionesDTO)iterator.next();
/* 262 */       iCuantos++;
/* 263 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 264 */       fondo = !fondo;
/* 265 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 266 */       eltr.appendChild(newtd("" + regAcciones.getNombreAreaImplanta(), false, 0));
/* 267 */       eltr.appendChild(newtdhref("" + regAcciones.getNumero(), "AMDetalleV2.po?numero=" + regAcciones.getNumero(), 0));
/* 268 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regAcciones.getFechaRealTerminacion()), false, 0));
/* 269 */       eltr.appendChild(newtd("" + regAcciones.getOrigenAccion(), false, 0));
/* 270 */       eltr.appendChild(newtd("" + regAcciones.getTipoAccion(), false, 0));
/* 271 */       eltr.appendChild(newtd("" + regAcciones.getObservacionesCierre(), false, 0));
/* 272 */       eltr.appendChild(newtd("" + regAcciones.getNombre(), false, 0));
/* 273 */       eltr.appendChild(newtd("" + regAcciones.getObservacionesCalidad(), false, 0));
/* 274 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regAcciones.getFechaCalidad()), false, 0));
/*     */       
/* 276 */       hte.appendChild(eltr);
/*     */     } 
/* 278 */     arr.clear();
/* 279 */     this.pagHTML.setTextNroRegistros("" + iCuantos);
/* 280 */     return iCuantos;
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 296 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 297 */     atrib.setValue(valor);
/* 298 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido, boolean alinear, int colspan) {
/* 310 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 311 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 312 */     if (alinear) {
/* 313 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 315 */     if (colspan > 0) {
/* 316 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/*     */     
/* 319 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 320 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, int colspan) {
/* 331 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 332 */     if (colspan > 0) {
/* 333 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 335 */     Element enlace = this.pagHTML.createElement("a");
/* 336 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 337 */     enlace.appendChild(hijo);
/* 338 */     Attr donde = this.pagHTML.createAttribute("href");
/* 339 */     donde.setValue(vinculo);
/* 340 */     enlace.setAttributeNode(donde);
/* 341 */     td.appendChild(enlace);
/* 342 */     td.setAttributeNode(newAttr("class", "ctdr"));
/* 343 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int comboAreas(int area, int defecto, boolean verTodas) {
/* 351 */     HTMLSelectElement combo = this.pagHTML.getElementIdArea();
/*     */     
/* 353 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/* 354 */     AreasDAO af = new AreasDAO();
/* 355 */     if (verTodas) {
/* 356 */       arr = af.cargarTodos();
/*     */     } else {
/* 358 */       arr = af.cargarSecuencia(area);
/*     */     } 
/*     */     
/* 361 */     int i = 0;
/* 362 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 363 */     while (iterator.hasNext()) {
/* 364 */       AreasDTO regArea = (AreasDTO)iterator.next();
/* 365 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 366 */       op.setValue("" + regArea.getCodigo());
/* 367 */       op.appendChild(this.pagHTML.createTextNode(regArea.getDescripcion()));
/*     */       
/* 369 */       if (defecto == regArea.getCodigo()) {
/* 370 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 371 */         escogida.setValue("on");
/* 372 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 375 */       combo.appendChild(op);
/* 376 */       i++;
/*     */     } 
/* 378 */     af.close();
/* 379 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMNoEfectivas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */