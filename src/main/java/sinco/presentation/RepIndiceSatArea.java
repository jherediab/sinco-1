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
/*     */ import sinco.presentation.RepIndiceSatArea;
/*     */ import sinco.presentation.RepIndiceSatAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepIndiceSatArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private RepIndiceSatAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  35 */     int codarea = Integer.parseInt(comms.request.getParameter("sujeto"));
/*     */     
/*  37 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  38 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  39 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     
/*  41 */     String fechaInicio = anno + "-" + Utilidades.formato(mes1, 2) + "-01";
/*  42 */     String fechaFin = anno + "-" + Utilidades.formato(mes2, 2) + "-01";
/*  43 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/*  44 */     miFecha.fechaMasDias(32L);
/*  45 */     fechaFin = miFecha.getPrimerDiaMes();
/*     */     
/*  47 */     SolicitudDAO sf = new SolicitudDAO();
/*  48 */     int numeroClientes = sf.cuantosClientesHay(1, codarea, fechaInicio, fechaFin);
/*  49 */     int numeroServicios = sf.cuantosServiciosHay(1, codarea, fechaInicio, fechaFin);
/*  50 */     sf.close();
/*     */ 
/*     */     
/*  53 */     IndiceSatisfaccionDAO rsLibre = new IndiceSatisfaccionDAO();
/*  54 */     IndiceSatisfaccionDTO reg = rsLibre.cargarIndiceArea(codarea, anno, mes1, mes2);
/*  55 */     rsLibre.close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     this.pagHTML = (RepIndiceSatAreaHTML)comms.xmlcFactory.create(RepIndiceSatAreaHTML.class);
/*  62 */     this.pagHTML.setTextIdarea(reg.getNombre());
/*  63 */     this.pagHTML.setTextNumeroClientes("" + numeroClientes);
/*  64 */     this.pagHTML.setTextNumeroServicios("" + numeroServicios);
/*  65 */     this.pagHTML.setTextFechaInicial("" + fechaInicio);
/*  66 */     this.pagHTML.setTextFechaFinal("" + fechaFin);
/*     */ 
/*     */     
/*  69 */     if (reg.getOportunidadExcelente() > 0 && mes1 == mes2) {
/*  70 */       HTMLElement elemento = this.pagHTML.getElementOpEx();
/*  71 */       elemento.appendChild(newhref("" + reg.getOportunidadExcelente(), "SolicitudesPeriodo.po?tipo=1&operacion=1&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
/*     */     } else {
/*     */       
/*  74 */       this.pagHTML.setTextOpEx("" + reg.getOportunidadExcelente());
/*     */     } 
/*     */ 
/*     */     
/*  78 */     if (reg.getOportunidadBuena() > 0 && mes1 == mes2) {
/*  79 */       HTMLElement elemento = this.pagHTML.getElementOpBu();
/*  80 */       elemento.appendChild(newhref("" + reg.getOportunidadBuena(), "SolicitudesPeriodo.po?tipo=1&operacion=2&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
/*     */     } else {
/*     */       
/*  83 */       this.pagHTML.setTextOpBu("" + reg.getOportunidadBuena());
/*     */     } 
/*     */ 
/*     */     
/*  87 */     if (reg.getOportunidadRegular() > 0 && mes1 == mes2) {
/*  88 */       HTMLElement elemento = this.pagHTML.getElementOpRe();
/*  89 */       elemento.appendChild(newhref("" + reg.getOportunidadRegular(), "SolicitudesPeriodo.po?tipo=1&operacion=3&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
/*     */     } else {
/*     */       
/*  92 */       this.pagHTML.setTextOpRe("" + reg.getOportunidadRegular());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  97 */     if (reg.getConfiabilidadExcelente() > 0 && mes1 == mes2) {
/*  98 */       HTMLElement elemento = this.pagHTML.getElementConEx();
/*  99 */       elemento.appendChild(newhref("" + reg.getConfiabilidadExcelente(), "SolicitudesPeriodo.po?tipo=1&operacion=4&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
/*     */     } else {
/*     */       
/* 102 */       this.pagHTML.setTextConEx("" + reg.getConfiabilidadExcelente());
/*     */     } 
/*     */ 
/*     */     
/* 106 */     if (reg.getConfiabilidadBuena() > 0 && mes1 == mes2) {
/* 107 */       HTMLElement elemento = this.pagHTML.getElementConBu();
/* 108 */       elemento.appendChild(newhref("" + reg.getConfiabilidadBuena(), "SolicitudesPeriodo.po?tipo=1&operacion=5&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
/*     */     } else {
/*     */       
/* 111 */       this.pagHTML.setTextConBu("" + reg.getConfiabilidadBuena());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (reg.getConfiabilidadRegular() > 0 && mes1 == mes2) {
/* 117 */       HTMLElement elemento = this.pagHTML.getElementConRe();
/* 118 */       elemento.appendChild(newhref("" + reg.getConfiabilidadRegular(), "SolicitudesPeriodo.po?tipo=1&operacion=6&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&proveedor=" + codarea));
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


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepIndiceSatArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */