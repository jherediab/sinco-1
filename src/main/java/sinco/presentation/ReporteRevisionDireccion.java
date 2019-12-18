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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.IndiceSatisfaccionDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ReporteRevisionDireccion;
/*     */ import sinco.presentation.ReporteRevisionDireccionHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReporteRevisionDireccion
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ReporteRevisionDireccionHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*  45 */     if (_operacion == null || _operacion.length() == 0) {
/*  46 */       _operacion = "X";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.pagHTML = (ReporteRevisionDireccionHTML)comms.xmlcFactory.create(ReporteRevisionDireccionHTML.class);
/*  53 */     String respuesta = listar(comms, _operacion);
/*     */     
/*  55 */     if (respuesta != null) {
/*  56 */       comms.response.setContentType("application/xls");
/*  57 */       comms.response.setHeader("Content-Disposition", "inline;filename=IndiceSatisfaccion.xls");
/*  58 */       comms.response.setStatus(200, "Good job");
/*     */       
/*  60 */       HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */       
/*     */       try {
/*  64 */         out.write(respuesta.getBytes());
/*  65 */         out.flush();
/*     */         
/*     */         return;
/*  68 */       } catch (Exception e) {
/*  69 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */       } 
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
/*     */   private String listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  86 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  87 */     String fechaFin = comms.request.getParameter("fechaFin");
/*     */     
/*  89 */     String sujeto = comms.request.getParameter("sujeto");
/*     */     
/*  91 */     HTMLSelectElement combo = this.pagHTML.getElementSujeto();
/*  92 */     comboMultivalores(combo, "TIPO_AREA", "" + sujeto, true);
/*     */     
/*  94 */     if (_operacion.equals("X")) {
/*  95 */       return null;
/*     */     }
/*     */     
/*  98 */     FechaDTO miFecha = new FechaDTO(fechaFin);
/*  99 */     miFecha.fechaMasDias(1L);
/* 100 */     fechaFin = miFecha.getFecha();
/*     */     
/* 102 */     String exportar = comms.request.getParameter("exportar");
/* 103 */     if (exportar == null) exportar = "N";
/*     */ 
/*     */     
/* 106 */     IndiceSatisfaccionDAO rs = new IndiceSatisfaccionDAO();
/* 107 */     if (!rs.getEstadoConexion()) {
/* 108 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 110 */     Collection arr = rs.reporteRevisionDireccion(fechaInicio, fechaFin, sujeto);
/* 111 */     rs.close();
/*     */     
/* 113 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 114 */     int cuantas = 0;
/*     */     
/* 116 */     int oe = 0, ob = 0, or = 0;
/* 117 */     int ce = 0, cb = 0, cr = 0;
/* 118 */     int recibidas = 0;
/* 119 */     int atendidas = 0;
/* 120 */     int porAtender = 0;
/* 121 */     int escaladas = 0;
/* 122 */     boolean fondo = true;
/*     */     
/* 124 */     Iterator iterator = arr.iterator();
/* 125 */     String respuesta = "Area\tTotal Recibidas\tTotal Atendidas\tTotal Por Atender\tTotal Escaladas\tInd Oportunidad %\tInd Perc. Serv. %\tInd Satisfacción %";
/* 126 */     while (iterator.hasNext()) {
/* 127 */       IndiceSatisfaccionDTO reg = (IndiceSatisfaccionDTO)iterator.next();
/*     */       
/* 129 */       if (exportar.equals("S")) {
/* 130 */         respuesta = respuesta + "\n" + reg.getNombre() + reg.getRecibidas() + "\t" + reg.getAtendidas() + "\t" + reg.getPorAtender() + "\t" + reg.getEscaladas() + "\t" + Utilidades.round2(reg.getIndiceOportunidad()) + "\t" + Utilidades.round2(reg.getIndiceConfiabilidad()) + "\t" + Utilidades.round2(reg.getIndiceTotal());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 144 */       fondo = !fondo;
/* 145 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 147 */       eltr.appendChild(newtd("" + reg.getNombre(), false));
/*     */       
/* 149 */       eltr.appendChild(newtd("" + reg.getRecibidas(), true));
/* 150 */       eltr.appendChild(newtd("" + reg.getAtendidas(), true));
/* 151 */       eltr.appendChild(newtd("" + reg.getPorAtender(), true));
/*     */       
/* 153 */       eltr.appendChild(newtd("" + reg.getEscaladas(), true));
/*     */       
/* 155 */       eltr.appendChild(newtd((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2), true));
/* 156 */       eltr.appendChild(newtd((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2), true));
/*     */       
/* 158 */       oe += reg.getOportunidadExcelente();
/* 159 */       ob += reg.getOportunidadBuena();
/* 160 */       or += reg.getOportunidadRegular();
/*     */       
/* 162 */       ce += reg.getConfiabilidadExcelente();
/* 163 */       cb += reg.getConfiabilidadBuena();
/* 164 */       cr += reg.getConfiabilidadRegular();
/*     */       
/* 166 */       recibidas += reg.getRecibidas();
/* 167 */       atendidas += reg.getAtendidas();
/* 168 */       porAtender += reg.getPorAtender();
/* 169 */       escaladas += reg.getEscaladas();
/*     */       
/* 171 */       double indice_total = reg.getIndiceTotal();
/*     */       
/* 173 */       eltr.appendChild(newtd("" + ((indice_total == 0.0D) ? "N/A" : Utilidades.miles(Utilidades.round2(indice_total), 2)), true));
/*     */       
/* 175 */       hte.appendChild(eltr);
/*     */     } 
/* 177 */     arr.clear();
/*     */     
/* 179 */     if (exportar.equals("S")) {
/* 180 */       return respuesta;
/*     */     }
/*     */     
/* 183 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 184 */     this.pagHTML.setTextTR("" + recibidas);
/* 185 */     this.pagHTML.setTextTA("" + atendidas);
/* 186 */     this.pagHTML.setTextTPA("" + porAtender);
/* 187 */     this.pagHTML.setTextTE("" + escaladas);
/*     */     
/* 189 */     float indiceOportunidad = Utilidades.calcularIndice(oe, ob, or);
/* 190 */     float indiceConfiabilidad = Utilidades.calcularIndice(ce, cb, cr);
/*     */     
/* 192 */     double indice_total = 0.0D;
/* 193 */     if (indiceOportunidad == 0.0F || indiceConfiabilidad == 0.0F) {
/* 194 */       indice_total = (indiceOportunidad + indiceConfiabilidad);
/*     */     } else {
/*     */       
/* 197 */       indice_total = ((indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad")) / 100.0F);
/*     */     } 
/*     */     
/* 200 */     this.pagHTML.setTextIO("" + ((indiceOportunidad == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indiceOportunidad), 2) + " %")));
/* 201 */     this.pagHTML.setTextIP("" + ((indiceConfiabilidad == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indiceConfiabilidad), 2) + " %")));
/* 202 */     this.pagHTML.setTextIT("" + ((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %")));
/*     */     
/* 204 */     return null;
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 219 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 220 */     atrib.setValue(valor);
/* 221 */     return atrib;
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
/* 232 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 233 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 234 */     if (align) {
/* 235 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 238 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 240 */     return td;
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
/* 256 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 257 */     Collection arr = rs.cargarTabla(tabla);
/* 258 */     rs.close();
/* 259 */     if (dejarBlanco) {
/* 260 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 261 */       op.setValue("");
/* 262 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 263 */       combo.appendChild(op);
/*     */     } 
/* 265 */     Iterator iterator = arr.iterator();
/* 266 */     while (iterator.hasNext()) {
/* 267 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 268 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 269 */       op.setValue("" + reg.getCodigo());
/* 270 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 271 */       if (defecto.equals(reg.getCodigo())) {
/* 272 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 273 */         escogida.setValue("on");
/* 274 */         op.setAttributeNode(escogida);
/*     */       } 
/* 276 */       combo.appendChild(op);
/*     */     } 
/* 278 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ReporteRevisionDireccion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */