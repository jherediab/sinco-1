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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContCdpDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpDAO;
/*     */ import sinco.presentation.ContCdpAct;
/*     */ import sinco.presentation.ContCdpActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContCdpAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContCdpActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  37 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  40 */     String _operacion = comms.request.getParameter("_operacion");
/*  41 */     if (_operacion == null) {
/*  42 */       _operacion = "Nuevo";
/*     */     }
/*  44 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  45 */       creacion(comms);
/*     */     }
/*  47 */     this.pagHTML = (ContCdpActHTML)comms.xmlcFactory.create(ContCdpActHTML.class);
/*  48 */     if (_operacion.equals("P")) {
/*  49 */       editar(comms);
/*     */     
/*     */     }
/*  52 */     else if (_operacion.equals("L") || _operacion.equals("X")) {
/*  53 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  54 */       sel.getParentNode().removeChild(sel);
/*  55 */       listar(comms, _operacion);
/*     */     } 
/*  57 */     if (_operacion.equals("P")) {
/*  58 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  59 */       sel.getParentNode().removeChild(sel);
/*  60 */       sel = this.pagHTML.getElementTrResultados();
/*  61 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  63 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  64 */     comms.response.writeDOM(this.pagHTML);
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
/*  76 */     String _operacion = comms.request.getParameter("_operacion");
/*  77 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  79 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/*  80 */     String anio = comms.request.getParameter("anio");
/*  81 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/*  82 */     String fechaVencimiento = comms.request.getParameter("fechaVencimiento");
/*     */     
/*  84 */     double valorCertificado = 0.0D;
/*     */     try {
/*  86 */       valorCertificado = Double.parseDouble(comms.request.getParameter("valorCertificado"));
/*     */     }
/*  88 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  91 */     ContCdpDAO rs = new ContCdpDAO();
/*  92 */     if (!rs.getEstadoConexion()) {
/*  93 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  95 */     boolean rta = false;
/*  96 */     if (_operacion.equals("M")) {
/*  97 */       rta = rs.modificarRegistro(codigoCertificado, valorCertificado, fechaExpedicion, fechaVencimiento, elUsuario);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     rs.close();
/* 105 */     if (!rta) {
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContCdp"));
/*     */     }
/* 108 */     String sPagina = "ContCdpAct.po?_operacion=P&codigoCertificado=" + codigoCertificado + "&anio=" + anio;
/* 109 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 120 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/* 121 */     if (codigoCertificado == null) {
/* 122 */       codigoCertificado = "";
/*     */     }
/* 124 */     String fechaExpedicionDesde = comms.request.getParameter("fechaExpedicionDesde");
/* 125 */     if (fechaExpedicionDesde == null) {
/* 126 */       fechaExpedicionDesde = "";
/*     */     }
/* 128 */     String fechaExpedicionHasta = comms.request.getParameter("fechaExpedicionHasta");
/* 129 */     if (fechaExpedicionHasta == null) {
/* 130 */       fechaExpedicionHasta = "";
/*     */     }
/* 132 */     String fechaVencimientoDesde = comms.request.getParameter("fechaVencimientoDesde");
/* 133 */     if (fechaVencimientoDesde == null) {
/* 134 */       fechaVencimientoDesde = "";
/*     */     }
/* 136 */     String fechaVencimientoHasta = comms.request.getParameter("fechaVencimientoHasta");
/* 137 */     if (fechaVencimientoHasta == null) {
/* 138 */       fechaVencimientoHasta = "";
/*     */     }
/* 140 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 144 */     ContCdpDAO rs = new ContCdpDAO();
/* 145 */     if (!rs.getEstadoConexion()) {
/* 146 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 148 */     Collection arr = rs.cargarTodos(codigoCertificado, fechaExpedicionDesde, fechaExpedicionHasta, fechaVencimientoDesde, fechaVencimientoHasta);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     rs.close();
/*     */     
/* 156 */     int cuantas = 0;
/* 157 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 158 */     Iterator iterator = arr.iterator();
/* 159 */     while (iterator.hasNext()) {
/* 160 */       ContCdpDTO reg = (ContCdpDTO)iterator.next();
/* 161 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 162 */       String url = "ContCdpAct.po?_operacion=P&codigoCertificado=" + reg.getCodigoCertificado() + "&anio=" + reg.getAnio();
/* 163 */       eltr.appendChild(newtdhref("" + reg.getCodigoCertificado(), url));
/* 164 */       eltr.appendChild(newtdhref("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()), url));
/* 165 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVencimiento())));
/* 166 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValorCertificado())));
/* 167 */       hte.appendChild(eltr);
/* 168 */       cuantas++;
/*     */     } 
/* 170 */     arr.clear();
/* 171 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 186 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/* 187 */     if (codigoCertificado == null) {
/* 188 */       codigoCertificado = "";
/*     */     }
/*     */     
/* 191 */     String anio = comms.request.getParameter("anio");
/* 192 */     if (anio == null) {
/* 193 */       anio = "";
/*     */     }
/*     */     
/* 196 */     ContCdpDAO rs = new ContCdpDAO();
/* 197 */     if (!rs.getEstadoConexion()) {
/* 198 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 200 */     ContCdpDTO reg = rs.cargarRegistro(codigoCertificado, anio);
/* 201 */     rs.close();
/* 202 */     Varios oVarios = new Varios();
/* 203 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 205 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 206 */     if (!oPermisoAct) {
/* 207 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 208 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 210 */     if (reg != null) {
/* 211 */       this.pagHTML.getElementCodigoCertificado().setValue("" + reg.getCodigoCertificado());
/* 212 */       this.pagHTML.getElementValorCertificado().setValue("" + Utilidades.formatDouble(reg.getValorCertificado()));
/* 213 */       this.pagHTML.getElementFechaExpedicion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()));
/* 214 */       this.pagHTML.getElementFechaVencimiento().setValue("" + Utilidades.darFormatoFecha(reg.getFechaVencimiento()));
/* 215 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 216 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 217 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 218 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 219 */       this.pagHTML.getElementAnio().setValue("" + reg.getFechaExpedicion().substring(6, 10));
/*     */       
/* 221 */       this.pagHTML.getElementCodigoCertificado().setReadOnly(true);
/* 222 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 236 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 237 */     atrib.setValue(valor);
/* 238 */     return atrib;
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
/* 251 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 252 */     Element enlace = this.pagHTML.createElement("a");
/* 253 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 254 */     enlace.appendChild(hijo);
/* 255 */     Attr donde = this.pagHTML.createAttribute("href");
/* 256 */     donde.setValue(vinculo);
/* 257 */     enlace.setAttributeNode(donde);
/* 258 */     td.appendChild(enlace);
/* 259 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 260 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 270 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 271 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 272 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 273 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContCdpAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */