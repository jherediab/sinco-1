/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.TipoCalificacionDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.IndiceSatisfaccionDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.data.TipoCalificacionDAO;
/*     */ import sinco.presentation.RepIndiceSatPersona;
/*     */ import sinco.presentation.RepIndiceSatPersonaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepIndiceSatPersona implements HttpPresentation {
/*     */   private RepIndiceSatPersonaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  31 */     int persona = Integer.parseInt(comms.request.getParameter("sujeto"));
/*  32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  34 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  35 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  36 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     
/*  38 */     IndiceSatisfaccionDAO rsLibre = new IndiceSatisfaccionDAO();
/*  39 */     IndiceSatisfaccionDTO reg = rsLibre.cargarIndicePersona(persona, anno, mes1, mes2);
/*  40 */     rsLibre.close();
/*     */     
/*  42 */     if (reg == null) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=BusquedaSinResultados"));
/*     */     }
/*     */     
/*  46 */     String fechaInicio = anno + "-" + Utilidades.formato(mes1, 2) + "-01";
/*  47 */     String fechaFin = anno + "-" + Utilidades.formato(mes2, 2) + "-01";
/*  48 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/*  49 */     miFecha.fechaMasDias(32L);
/*  50 */     fechaFin = miFecha.getPrimerDiaMes();
/*     */     
/*  52 */     SolicitudDAO sf = new SolicitudDAO();
/*  53 */     int numeroClientes = sf.cuantosClientesHay(2, persona, fechaInicio, fechaFin);
/*  54 */     int numeroServicios = sf.cuantosServiciosHay(2, persona, fechaInicio, fechaFin);
/*  55 */     sf.close();
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.pagHTML = (RepIndiceSatPersonaHTML)comms.xmlcFactory.create(RepIndiceSatPersonaHTML.class);
/*  60 */     this.pagHTML.setTextIdPersona("" + reg.getNombre());
/*  61 */     this.pagHTML.setTextNumeroClientes("" + numeroClientes);
/*  62 */     this.pagHTML.setTextNumeroServicios("" + numeroServicios);
/*  63 */     this.pagHTML.setTextFechaInicial("" + fechaInicio);
/*  64 */     this.pagHTML.setTextFechaFinal("" + fechaFin);
/*     */ 
/*     */     
/*  67 */     if (reg.getOportunidadExcelente() > 0 && mes1 == mes2) {
/*  68 */       HTMLElement elemento = this.pagHTML.getElementOpEx();
/*  69 */       elemento.appendChild(newhref("" + reg.getOportunidadExcelente(), "SolicitudesPeriodo.po?tipo=2&operacion=1&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/*  72 */       this.pagHTML.setTextOpEx("" + reg.getOportunidadExcelente());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     if (reg.getOportunidadBuena() > 0 && mes1 == mes2) {
/*  77 */       HTMLElement elemento = this.pagHTML.getElementOpBu();
/*  78 */       elemento.appendChild(newhref("" + reg.getOportunidadBuena(), "SolicitudesPeriodo.po?tipo=2&operacion=2&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/*  81 */       this.pagHTML.setTextOpBu("" + reg.getOportunidadBuena());
/*     */     } 
/*     */ 
/*     */     
/*  85 */     if (reg.getOportunidadRegular() > 0 && mes1 == mes2) {
/*  86 */       HTMLElement elemento = this.pagHTML.getElementOpRe();
/*  87 */       elemento.appendChild(newhref("" + reg.getOportunidadRegular(), "SolicitudesPeriodo.po?tipo=2&operacion=3&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/*  90 */       this.pagHTML.setTextOpRe("" + reg.getOportunidadRegular());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (reg.getConfiabilidadExcelente() > 0 && mes1 == mes2) {
/*  97 */       HTMLElement elemento = this.pagHTML.getElementConEx();
/*  98 */       elemento.appendChild(newhref("" + reg.getConfiabilidadExcelente(), "SolicitudesPeriodo.po?tipo=2&operacion=4&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/* 101 */       this.pagHTML.setTextConEx("" + reg.getConfiabilidadExcelente());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (reg.getConfiabilidadBuena() > 0 && mes1 == mes2) {
/* 107 */       HTMLElement elemento = this.pagHTML.getElementConBu();
/* 108 */       elemento.appendChild(newhref("" + reg.getConfiabilidadBuena(), "SolicitudesPeriodo.po?tipo=2&operacion=5&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/* 111 */       this.pagHTML.setTextConBu("" + reg.getConfiabilidadBuena());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (reg.getConfiabilidadRegular() > 0 && mes1 == mes2) {
/* 117 */       HTMLElement elemento = this.pagHTML.getElementConRe();
/* 118 */       elemento.appendChild(newhref("" + reg.getConfiabilidadRegular(), "SolicitudesPeriodo.po?tipo=2&operacion=6&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + persona));
/*     */     } else {
/*     */       
/* 121 */       this.pagHTML.setTextConRe("" + reg.getConfiabilidadRegular());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.pagHTML.setTextOpIndSat((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + " %"));
/* 128 */     this.pagHTML.setTextConIndSat((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + " %"));
/*     */     
/* 130 */     double indice_total = reg.getIndiceTotal();
/*     */     
/* 132 */     this.pagHTML.setTextIdPorcentaje((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %"));
/*     */     
/* 134 */     if (numeroClientes != 0 && numeroServicios != 0) {
/* 135 */       TipoCalificacionDAO tcalf = new TipoCalificacionDAO();
/* 136 */       tcalf.cargarPorPorcentajeIndice((int)indice_total);
/* 137 */       TipoCalificacionDTO tipocal = tcalf.next();
/* 138 */       if (tipocal != null) {
/* 139 */         this.pagHTML.setTextIdservicio(tipocal.getSatisfaccion());
/*     */       }
/* 141 */       tcalf.close();
/*     */     } 
/* 143 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 144 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Element newhref(String descripcion, String url) {
/* 156 */     Element enlace = this.pagHTML.createElement("a");
/* 157 */     Node hijo = this.pagHTML.createTextNode(descripcion);
/* 158 */     enlace.appendChild(hijo);
/* 159 */     Attr atrib = this.pagHTML.createAttribute("href");
/* 160 */     atrib.setValue(url);
/* 161 */     enlace.setAttributeNode(atrib);
/* 162 */     return enlace;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepIndiceSatPersona.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */