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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AMAccionesEstadisticaDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.data.AMEstadisticasDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.presentation.AMEstadisticaProcesos;
/*     */ import sinco.presentation.AMEstadisticaProcesosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMEstadisticaProcesos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMEstadisticaProcesosHTML pagHTML;
/*     */   int codigo_area;
/*     */   int codigo_areaOld;
/*     */   int cuantos;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     int areaImplanta = 0;
/*     */     try {
/*  44 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */     }
/*  46 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  49 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/*  50 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/*     */     
/*  52 */     int tipo = 0;
/*     */     try {
/*  54 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*     */     }
/*  56 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  59 */     int codigo_estado = 0;
/*     */     try {
/*  61 */       codigo_estado = Integer.parseInt(comms.request.getParameter("codigo_estado"));
/*     */     }
/*  63 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  66 */     String accion = comms.request.getParameter("accion");
/*  67 */     String origen = comms.request.getParameter("origen");
/*  68 */     String proceso = comms.request.getParameter("proceso");
/*  69 */     String norma = comms.request.getParameter("norma");
/*     */ 
/*     */     
/*  72 */     this.pagHTML = (AMEstadisticaProcesosHTML)comms.xmlcFactory.create(AMEstadisticaProcesosHTML.class);
/*     */     
/*  74 */     int rta = 0;
/*  75 */     if (tipo == 1) {
/*  76 */       rta = detalle(areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */     } else {
/*     */       
/*  79 */       rta = resumen(areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */     } 
/*     */     
/*  82 */     if (rta == 1) {
/*  83 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  86 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  87 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private int detalle(int areaImplanta, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 112 */     AreasDAO areaf = new AreasDAO();
/* 113 */     AreasDTO regArea = areaf.getArea(areaImplanta);
/* 114 */     areaf.close();
/*     */     
/* 116 */     if (regArea != null) {
/* 117 */       this.pagHTML.setTextArea("" + regArea.getDescripcion());
/*     */     }
/* 119 */     String periodo = "Periodo: ";
/* 120 */     if (fechaDesde.length() > 0) periodo = periodo + "" + fechaDesde; 
/* 121 */     if (fechaHasta.length() > 0) periodo = periodo + " hasta " + fechaHasta;
/*     */     
/* 123 */     this.pagHTML.setTextPeriodo(periodo);
/*     */     
/* 125 */     AMEstadisticasDAO rs = new AMEstadisticasDAO();
/* 126 */     if (!rs.getEstadoConexion()) {
/* 127 */       return 1;
/*     */     }
/*     */     
/* 130 */     Collection arr = rs.estadisticaProcesos(regArea.getSecuencia(), areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
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
/* 141 */     rs.close();
/*     */     
/* 143 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 144 */     boolean fondo = true;
/*     */     
/* 146 */     this.codigo_areaOld = 0;
/* 147 */     this.cuantos = 0;
/*     */     
/* 149 */     String parametros = "AMConsulta.po?fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&origen=" + origen + "&implantada=&revisada_comite=&limite=1&norma=" + norma;
/* 150 */     parametros = parametros + "&accion=" + accion + "&origen=" + origen;
/* 151 */     int tNumero = 0;
/* 152 */     int tgNumero = 0;
/*     */     
/* 154 */     Iterator iterator = arr.iterator();
/* 155 */     while (iterator.hasNext()) {
/* 156 */       AMAccionesEstadisticaDTO reg = (AMAccionesEstadisticaDTO)iterator.next();
/*     */       
/* 158 */       this.codigo_area = reg.getAreaImplanta();
/* 159 */       int numero = reg.getNumero();
/*     */       
/* 161 */       int rta = evaluarCorte();
/*     */ 
/*     */     HTMLElement eltr;
/*     */       
/* 165 */       switch (rta) {
/*     */         case 2:
/* 167 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 168 */           eltr.appendChild(newtdn("Sub Total", false, 0));
/* 169 */           eltr.appendChild(newtdn("" + tNumero, true, 0));
/* 170 */           hte.appendChild(eltr);
/* 171 */           tNumero = 0;
/*     */         
/*     */         case -1:
/* 174 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 175 */           eltr.setAttributeNode(newAttr("bgcolor", "#FFFFFF"));
/* 176 */           eltr.appendChild(newtdn("" + reg.getNombreArea(), false, 2));
/* 177 */           hte.appendChild(eltr);
/*     */           break;
/*     */       } 
/*     */       
/* 181 */       this.cuantos++;
/*     */       
/* 183 */      eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 184 */       fondo = !fondo;
/* 185 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 186 */       eltr.appendChild(newtdhref("" + reg.getNombreProceso(), parametros + "&areaImplanta=" + this.codigo_area + "&proceso=" + reg.getCodigoProceso()));
/* 187 */       eltr.appendChild(newtd("" + reg.getNumero()));
/*     */       
/* 189 */       hte.appendChild(eltr);
/*     */       
/* 191 */       tNumero += numero;
/* 192 */       tgNumero += numero;
/*     */       
/* 194 */       this.codigo_areaOld = this.codigo_area;
/*     */     } 
/*     */ 
/*     */     
/* 198 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 199 */     eltr.appendChild(newtdn("Sub Total", false, 0));
/* 200 */     eltr.appendChild(newtdn("" + tNumero, true, 0));
/* 201 */     hte.appendChild(eltr);
/*     */     
/* 203 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 204 */     eltr.appendChild(newtdp("Totales"));
/* 205 */     eltr.appendChild(newtdn("" + tgNumero, true, 0));
/* 206 */     hte.appendChild(eltr);
/* 207 */     return 0;
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
/*     */   
/*     */   private int resumen(int areaImplanta, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 233 */     AreasDAO areaf = new AreasDAO();
/* 234 */     AreasDTO regArea = areaf.getArea(areaImplanta);
/* 235 */     areaf.close();
/*     */     
/* 237 */     if (regArea != null) {
/* 238 */       this.pagHTML.setTextArea("" + regArea.getDescripcion());
/*     */     }
/* 240 */     String periodo = "Periodo: ";
/* 241 */     if (fechaDesde.length() > 0) periodo = periodo + "" + fechaDesde; 
/* 242 */     if (fechaHasta.length() > 0) periodo = periodo + " hasta " + fechaHasta;
/*     */     
/* 244 */     this.pagHTML.setTextPeriodo(periodo);
/*     */     
/* 246 */     AMEstadisticasDAO rs = new AMEstadisticasDAO();
/* 247 */     if (!rs.getEstadoConexion()) {
/* 248 */       return 1;
/*     */     }
/*     */     
/* 251 */     Collection arr = rs.estadisticaProcesosResumen(regArea.getSecuencia(), areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
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
/* 263 */     rs.close();
/*     */     
/* 265 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 266 */     boolean fondo = true;
/*     */     
/* 268 */     int tgNumero = 0;
/*     */     
/* 270 */     Iterator iterator = arr.iterator();
/* 271 */     while (iterator.hasNext()) {
/* 272 */       AMAccionesEstadisticaDTO reg = (AMAccionesEstadisticaDTO)iterator.next();
/* 273 */       int numero = reg.getNumero();
/* 274 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 275 */       fondo = !fondo;
/* 276 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 277 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 278 */       eltr.appendChild(newtd("" + reg.getNumero()));
/*     */       
/* 280 */       hte.appendChild(eltr);
/*     */       
/* 282 */       tgNumero += numero;
/*     */     } 
/*     */     
/* 285 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 286 */     eltr.setAttributeNode(newAttr("class", "pie"));
/* 287 */     eltr.appendChild(newtd("Totales"));
/* 288 */     eltr.appendChild(newtdn("" + tgNumero, true, 0));
/* 289 */     hte.appendChild(eltr);
/* 290 */     return 0;
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
/* 304 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 305 */     atrib.setValue(valor);
/* 306 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 318 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 319 */     Element enlace = this.pagHTML.createElement("a");
/* 320 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 321 */     enlace.appendChild(hijo);
/* 322 */     Attr donde = this.pagHTML.createAttribute("href");
/* 323 */     donde.setValue(vinculo);
/* 324 */     enlace.setAttributeNode(donde);
/* 325 */     td.appendChild(enlace);
/* 326 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 327 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 337 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 338 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 339 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 340 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdp(String contenido) {
/* 349 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 350 */     td.setAttributeNode(newAttr("class", "pie"));
/* 351 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 352 */     return td;
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
/*     */   private HTMLElement newtdn(String contenido, boolean alinear, int colspan) {
/* 364 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 365 */     Element negrita = this.pagHTML.createElement("b");
/* 366 */     negrita.appendChild(this.pagHTML.createTextNode(contenido));
/* 367 */     td.appendChild(negrita);
/* 368 */     td.setAttributeNode(newAttr("class", "pie"));
/* 369 */     if (alinear) {
/* 370 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 372 */     if (colspan > 0) {
/* 373 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 375 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int evaluarCorte() {
/* 384 */     if (this.cuantos == 0) return -1;
/*     */     
/* 386 */     if (this.codigo_areaOld != this.codigo_area) return 2; 
/* 387 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMEstadisticaProcesos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */