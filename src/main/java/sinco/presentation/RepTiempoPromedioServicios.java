/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
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
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.RepTiempoPromedioServicios;
/*     */ import sinco.presentation.RepTiempoPromedioServiciosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepTiempoPromedioServicios
/*     */   implements HttpPresentation
/*     */ {
/*     */   private RepTiempoPromedioServiciosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  45 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  46 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  47 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     
/*  49 */     String _operacion = comms.request.getParameter("_operacion");
/*  50 */     if (_operacion == null || _operacion.length() == 0) {
/*  51 */       _operacion = "X";
/*     */     }
/*     */     
/*  54 */     Varios oVarios = new Varios();
/*  55 */     boolean bMostrarTodasAreas = oVarios.tienePermiso(miGrupo, "oMostrarTodasLasAreas");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.pagHTML = (RepTiempoPromedioServiciosHTML)comms.xmlcFactory.create(RepTiempoPromedioServiciosHTML.class);
/*  62 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  63 */       String respuesta = listar(comms, bMostrarTodasAreas, miArea, _operacion);
/*     */       
/*  65 */       if (respuesta != null) {
/*  66 */         comms.response.setContentType("application/xls");
/*  67 */         comms.response.setHeader("Content-Disposition", "inline;filename=IndiceSatisfaccion.xls");
/*  68 */         comms.response.setStatus(200, "Good job");
/*     */         
/*  70 */         HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */         
/*     */         try {
/*  74 */           out.write(respuesta.getBytes());
/*  75 */           out.flush();
/*     */           
/*     */           return;
/*  78 */         } catch (Exception e) {
/*  79 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */         } 
/*     */       } 
/*     */       
/*  83 */       HTMLElement sel = this.pagHTML.getElementTrDetallado();
/*  84 */       sel.getParentNode().removeChild(sel);
/*     */     
/*     */     }
/*  87 */     else if (_operacion.equals("P")) {
/*  88 */       editar(comms);
/*     */       
/*  90 */       HTMLElement sel = this.pagHTML.getElementTrResultados();
/*  91 */       sel.getParentNode().removeChild(sel);
/*     */       
/*  93 */       sel = this.pagHTML.getElementTrConsulta();
/*  94 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  97 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  98 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private String listar(HttpPresentationComms comms, boolean bMostrarTodasAreas, int miArea, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 113 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/* 114 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/* 116 */     int areaProveedor = 0;
/*     */     try {
/* 118 */       areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
/*     */     }
/* 120 */     catch (Exception e) {
/* 121 */       areaProveedor = miArea;
/*     */     } 
/* 123 */     int areaCliente = 0;
/*     */     try {
/* 125 */       areaCliente = Integer.parseInt(comms.request.getParameter("areaCliente"));
/*     */     }
/* 127 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 130 */     AreasDAO rsArea = new AreasDAO();
/* 131 */     AreasDTO regArea = rsArea.getArea(miArea);
/* 132 */     rsArea.close();
/*     */     
/* 134 */     HTMLSelectElement combo = this.pagHTML.getElementAreaProveedor();
/* 135 */     comboAreas(combo, bMostrarTodasAreas, regArea.getSecuencia(), miArea, areaProveedor, false);
/*     */     
/* 137 */     combo = this.pagHTML.getElementAreaCliente();
/* 138 */     comboAreas(combo, bMostrarTodasAreas, null, miArea, areaCliente, true);
/*     */     
/* 140 */     if (_operacion.equals("X")) {
/* 141 */       return null;
/*     */     }
/*     */     
/* 144 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/* 145 */     miFecha.fechaMasDias(1L);
/*     */     
/* 147 */     String exportar = comms.request.getParameter("exportar");
/* 148 */     if (exportar == null) exportar = "N";
/*     */     
/* 150 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/* 151 */     if (!rs.getEstadoConexion()) {
/* 152 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 154 */     Collection arr = rs.tiempoPromedioServicio(areaProveedor, areaCliente, fechaInicio, miFecha.getFecha());
/* 155 */     rs.close();
/*     */     
/* 157 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 159 */     Iterator iterator = arr.iterator();
/* 160 */     String respuesta = "Servicio\tDuración\tCantidad de solicitudes\tPromedio tiempo de atención";
/* 161 */     while (iterator.hasNext()) {
/* 162 */       VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
/*     */       
/* 164 */       if (exportar.equals("S")) {
/* 165 */         respuesta = respuesta + "\n" + reg.getNombreServicio() + reg.getDuracion() + " " + reg.getUnidadMedida() + "\t" + reg.getNumero() + "\t" + reg.getTiempoPromedio() + "\t";
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/* 172 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 174 */       String url = "RepTiempoPromedioServicios.po?_operacion=P&servicio=" + reg.getCodigoServicio() + "&areaProveedor=" + areaProveedor + "&areaCliente=" + areaCliente + "&fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), url));
/*     */       
/* 182 */       eltr.appendChild(newtd("" + reg.getDuracion() + " " + reg.getUnidadMedida(), true));
/* 183 */       eltr.appendChild(newtd("" + reg.getNumero(), true));
/* 184 */       eltr.appendChild(newtd("" + reg.getTiempoPromedio(), true));
/* 185 */       hte.appendChild(eltr);
/*     */     } 
/* 187 */     this.pagHTML.setTextNroRegistros("" + arr.size());
/* 188 */     arr.clear();
/*     */     
/* 190 */     if (exportar.equals("S")) {
/* 191 */       return respuesta;
/*     */     }
/*     */     
/* 194 */     return null;
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
/* 208 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/* 209 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/* 211 */     int areaProveedor = 0;
/*     */     try {
/* 213 */       areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
/*     */     }
/* 215 */     catch (Exception e) {}
/*     */     
/* 217 */     int areaCliente = 0;
/*     */     try {
/* 219 */       areaCliente = Integer.parseInt(comms.request.getParameter("areaCliente"));
/*     */     }
/* 221 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 224 */     int servicio = 0;
/*     */     try {
/* 226 */       servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/* 228 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 231 */     ServiciosDAO rsServicio = new ServiciosDAO();
/* 232 */     ServiciosDTO regServicio = rsServicio.cargarRegistro(servicio);
/* 233 */     rsServicio.close();
/* 234 */     if (regServicio != null) {
/* 235 */       this.pagHTML.setTextNombreServio(regServicio.getDescripcion());
/*     */     }
/*     */ 
/*     */     
/* 239 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/* 240 */     if (!rs.getEstadoConexion()) {
/* 241 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 243 */     Collection arr = rs.detallePromedioServicio(areaProveedor, areaCliente, fechaInicio, fechaFin, servicio);
/* 244 */     rs.close();
/*     */     
/* 246 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleServicio();
/*     */     
/* 248 */     Iterator iterator = arr.iterator();
/* 249 */     while (iterator.hasNext()) {
/* 250 */       VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
/* 251 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 253 */       String url = "VSEnCurso.po?solicitud=" + reg.getNumero();
/* 254 */       eltr.appendChild(newtdhref("" + reg.getNumero(), url));
/*     */       
/* 256 */       eltr.appendChild(newtd("" + reg.getNombreServicio(), false));
/* 257 */       eltr.appendChild(newtd("" + reg.getNombreAreaCliente(), false));
/* 258 */       eltr.appendChild(newtd("" + reg.getDuracion() + " " + reg.getUnidadMedida(), true));
/* 259 */       eltr.appendChild(newtd("" + reg.getFechaVigencia(), true));
/* 260 */       eltr.appendChild(newtd("" + reg.getFechaEstimadaTerminacion(), true));
/* 261 */       eltr.appendChild(newtd("" + reg.getFechaRealTerminacion(), true));
/* 262 */       eltr.appendChild(newtd("" + reg.getTiempoPromedio(), true));
/* 263 */       hte.appendChild(eltr);
/*     */     } 
/* 265 */     this.pagHTML.setTextNroSolicitudes("" + arr.size());
/* 266 */     arr.clear();
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
/* 279 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 280 */     atrib.setValue(valor);
/* 281 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean align) {
/* 292 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 293 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 294 */     if (align) {
/* 295 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 298 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 300 */     return td;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 314 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 315 */     Element enlace = this.pagHTML.createElement("a");
/* 316 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 317 */     enlace.appendChild(hijo);
/* 318 */     Attr donde = this.pagHTML.createAttribute("href");
/* 319 */     donde.setValue(vinculo);
/* 320 */     enlace.setAttributeNode(donde);
/* 321 */     td.appendChild(enlace);
/* 322 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 323 */     return td;
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
/*     */   private void comboAreas(HTMLSelectElement combo, boolean bMostrarTodasAreas, String secuencia, int area, int defecto, boolean dejarBlanco) {
/*     */     Collection arr;
/* 342 */     AreasDAO rs = new AreasDAO();
/*     */ 
/*     */     
/* 345 */     if (bMostrarTodasAreas) {
/* 346 */       arr = rs.cargarActivas();
/*     */     }
/* 348 */     else if (secuencia == null) {
/* 349 */       arr = rs.cargarAreasFrecuentas(area);
/*     */     } else {
/*     */       
/* 352 */       arr = rs.cargarAreasSecuencia(secuencia);
/*     */     } 
/* 354 */     rs.close();
/*     */     
/* 356 */     if (dejarBlanco) {
/* 357 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 358 */       op.setValue("");
/* 359 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 360 */       combo.appendChild(op);
/*     */     } 
/* 362 */     Iterator iterator = arr.iterator();
/* 363 */     while (iterator.hasNext()) {
/* 364 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 365 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 366 */       op.setValue("" + reg.getCodigo());
/* 367 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 368 */       if (defecto == reg.getCodigo()) {
/* 369 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 370 */         escogida.setValue("on");
/* 371 */         op.setAttributeNode(escogida);
/*     */       } 
/* 373 */       combo.appendChild(op);
/*     */     } 
/* 375 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepTiempoPromedioServicios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */