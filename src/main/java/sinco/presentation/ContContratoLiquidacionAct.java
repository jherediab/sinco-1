/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import sinco.business.ContContratoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContContratoDAO;
/*     */ import sinco.presentation.ContContratoLiquidacionAct;
/*     */ import sinco.presentation.ContContratoLiquidacionActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContContratoLiquidacionAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContContratoLiquidacionActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  31 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  32 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */ 
/*     */     
/*  35 */     String _operacion = comms.request.getParameter("_operacion");
/*  36 */     if (_operacion == null) {
/*  37 */       _operacion = "Nuevo";
/*     */     }
/*     */     
/*  40 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  41 */       creacion(comms);
/*     */     }
/*  43 */     this.pagHTML = (ContContratoLiquidacionActHTML)comms.xmlcFactory.create(ContContratoLiquidacionActHTML.class);
/*  44 */     if (_operacion.equals("P")) {
/*  45 */       editar(comms);
/*     */     }
/*  47 */     else if (_operacion.equals("Nuevo")) {
/*  48 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  49 */       sel.getParentNode().removeChild(sel);
/*  50 */       nuevo(comms);
/*     */     } 
/*  52 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  53 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  54 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  56 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  57 */     comms.response.writeDOM(this.pagHTML);
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
/*  69 */     String _operacion = comms.request.getParameter("_operacion");
/*  70 */     String elUsuario = "" + comms.session.getUser().getName();
/*  71 */     int consecutivoContrato = 0;
/*     */     try {
/*  73 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/*  75 */     catch (Exception e) {}
/*  76 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/*  77 */     boolean rta = false;
/*  78 */     if (_operacion.equals("E")) {
/*  79 */       ContContratoDAO rs = new ContContratoDAO();
/*  80 */       if (!rs.getEstadoConexion()) {
/*  81 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  83 */       rta = rs.eliminarLiquidacion(consecutivoContrato, elUsuario);
/*  84 */       rs.close();
/*  85 */       if (!rta) {
/*  86 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContRp"));
/*     */       }
/*  88 */       String sPagina = "ContContratoLiquidacionAct.po?_operacion=Nuevo&consecutivoContrato=" + consecutivoContrato + "&numeroContrato=" + numeroContrato;
/*  89 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*  91 */     String fechaLiquidacion = comms.request.getParameter("fechaLiquidacion");
/*  92 */     double valorLiquidacion = 0.0D;
/*     */     try {
/*  94 */       valorLiquidacion = Double.parseDouble(comms.request.getParameter("valorLiquidacion"));
/*     */     }
/*  96 */     catch (Exception e) {}
/*     */     
/*  98 */     double valorCancelado = 0.0D;
/*     */     try {
/* 100 */       valorCancelado = Double.parseDouble(comms.request.getParameter("valorCancelado"));
/*     */     }
/* 102 */     catch (Exception e) {}
/*     */     
/* 104 */     ContContratoDAO rs = new ContContratoDAO();
/* 105 */     if (!rs.getEstadoConexion()) {
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 108 */     rta = rs.actualizarLiquidacion(consecutivoContrato, fechaLiquidacion, valorLiquidacion, valorCancelado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     rs.close();
/* 116 */     if (!rta) {
/* 117 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContRp"));
/*     */     }
/* 119 */     String sPagina = "ContContratoLiquidacionAct.po?_operacion=P&numeroContrato=" + numeroContrato + "&consecutivoContrato=" + consecutivoContrato;
/* 120 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 134 */     int consecutivoContrato = 0;
/*     */     try {
/* 136 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 138 */     catch (Exception e) {}
/* 139 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/*     */     
/* 141 */     ContContratoDAO rs = new ContContratoDAO();
/* 142 */     if (!rs.getEstadoConexion()) {
/* 143 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 145 */     ContContratoDTO reg = rs.cargarRegistro(consecutivoContrato);
/* 146 */     rs.close();
/* 147 */     Varios oVarios = new Varios();
/* 148 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 150 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 151 */     if (!oPermisoAct) {
/* 152 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 153 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 155 */       elem = this.pagHTML.getElementBtnEliminar();
/* 156 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 158 */     if (reg != null) {
/* 159 */       this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
/* 160 */       this.pagHTML.getElementValorContrato().setValue("" + Utilidades.formatDouble(reg.getValor()));
/* 161 */       this.pagHTML.getElementFechaLiquidacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaLiquidacion()));
/* 162 */       this.pagHTML.getElementValorLiquidacion().setValue("" + Utilidades.formatDouble(reg.getValorLiquidacion()));
/* 163 */       this.pagHTML.getElementValorCancelado().setValue("" + Utilidades.formatDouble(reg.getValorCancelado()));
/* 164 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 165 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 166 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 167 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */       
/* 169 */       this.pagHTML.getElementNumeroContrato().setReadOnly(true);
/* 170 */       this.pagHTML.getElementFechaLiquidacion().setReadOnly(true);
/*     */       
/* 172 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 184 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 185 */     if (numeroContrato == null) {
/* 186 */       numeroContrato = "";
/*     */     }
/* 188 */     int consecutivoContrato = 0;
/*     */     try {
/* 190 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 192 */     catch (Exception e) {}
/* 193 */     ContContratoDAO rs = new ContContratoDAO();
/* 194 */     if (!rs.getEstadoConexion()) {
/* 195 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 197 */     ContContratoDTO reg = rs.cargarRegistro(consecutivoContrato);
/* 198 */     rs.close();
/* 199 */     if (reg != null && 
/* 200 */       reg.getEstado().equals("L")) {
/* 201 */       editar(comms);
/*     */     }
/*     */     
/* 204 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 205 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
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
/* 217 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 218 */     atrib.setValue(valor);
/* 219 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 230 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 231 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 232 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 233 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alineacionDerecha) {
/* 244 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 245 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 246 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 247 */     if (alineacionDerecha) {
/* 248 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 250 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContContratoLiquidacionAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */