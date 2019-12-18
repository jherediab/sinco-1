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
/*     */ import sinco.business.ContInterventorDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.ContInterventorDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ContInterventorAct;
/*     */ import sinco.presentation.ContInterventorActHTML;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContInterventorAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContInterventorActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*  45 */     if (_operacion == null || _operacion.length() == 0) {
/*  46 */       _operacion = "Nuevo";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  51 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  52 */       creacion(comms);
/*     */     }
/*  54 */     this.pagHTML = (ContInterventorActHTML)comms.xmlcFactory.create(ContInterventorActHTML.class);
/*  55 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  56 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  59 */     else if (_operacion.equals("P")) {
/*  60 */       editar(comms);
/*  61 */       listar(comms, "L");
/*     */     }
/*  63 */     else if (_operacion.equals("Nuevo")) {
/*  64 */       nuevo(comms);
/*  65 */       listar(comms, _operacion);
/*  66 */       Varios oVarios = new Varios();
/*  67 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  68 */       if (!oPermisoAct) {
/*  69 */         HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/*  70 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*     */     } 
/*  73 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  84 */     String _operacion = comms.request.getParameter("_operacion");
/*  85 */     String elUsuario = "" + comms.session.getUser().getName();
/*  86 */     String tipoDocumento = comms.request.getParameter("tipoIdentificacion");
/*     */ 
/*     */     
/*  89 */     long numeroDocumento = 0L;
/*     */     try {
/*  91 */       numeroDocumento = Long.parseLong(comms.request.getParameter("identificacionInterventor"));
/*  92 */     } catch (Exception e) {
/*  93 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numeroDocumento"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     boolean rta = false;
/* 100 */     if (_operacion.equals("E")) {
/* 101 */       ContInterventorDAO rs = new ContInterventorDAO();
/* 102 */       if (!rs.getEstadoConexion()) {
/* 103 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 105 */       rta = rs.eliminarRegistro(tipoDocumento, numeroDocumento);
/*     */ 
/*     */       
/* 108 */       rs.close();
/*     */       
/* 110 */       if (!rta) {
/* 111 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorInterventor"));
/*     */       }
/* 113 */       String sPagina = "ContInterventorAct.po?_operacion=X";
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 116 */     String apellidosInterventor = comms.request.getParameter("apellidosInterventor");
/* 117 */     String nombresInterventor = comms.request.getParameter("nombresInterventor");
/* 118 */     String direccionInterventor = comms.request.getParameter("direccionInterventor");
/* 119 */     String telefonoInterventor = comms.request.getParameter("telefonoInterventor");
/* 120 */     ContInterventorDAO rs = new ContInterventorDAO();
/* 121 */     if (!rs.getEstadoConexion()) {
/* 122 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 124 */     rta = false;
/* 125 */     if (_operacion.equals("C")) {
/* 126 */       rta = rs.crearRegistro(tipoDocumento, numeroDocumento, apellidosInterventor, nombresInterventor, direccionInterventor, telefonoInterventor, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       rta = rs.modificarRegistro(tipoDocumento, numeroDocumento, apellidosInterventor, nombresInterventor, direccionInterventor, telefonoInterventor, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     rs.close();
/* 146 */     if (!rta) {
/* 147 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorInterventor"));
/*     */     }
/* 149 */     String sPagina = "ContInterventorAct.po?_operacion=P&tipoDocumento=" + tipoDocumento + "&numeroDocumento=" + numeroDocumento + "";
/* 150 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 161 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 165 */     ContInterventorDAO rs = new ContInterventorDAO();
/* 166 */     if (!rs.getEstadoConexion()) {
/* 167 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 169 */     Collection<ContInterventorDTO> arr = rs.cargarTodos();
/* 170 */     rs.close();
/* 171 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 172 */     int cuantas = 0;
/* 173 */     Iterator<ContInterventorDTO> iterator = arr.iterator();
/* 174 */     while (iterator.hasNext()) {
/* 175 */       ContInterventorDTO reg = (ContInterventorDTO)iterator.next();
/* 176 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 177 */       eltr.appendChild(newtd("" + reg.getTipoDocumento()));
/* 178 */       String url = "ContInterventorAct.po?_operacion=P&tipoDocumento=" + reg.getTipoDocumento() + "&numeroDocumento=" + reg.getNumeroDocumento() + "";
/* 179 */       eltr.appendChild(newtdhref("" + reg.getNumeroDocumento(), url));
/* 180 */       eltr.appendChild(newtd("" + reg.getApellidos()));
/* 181 */       eltr.appendChild(newtd("" + reg.getNombres()));
/* 182 */       eltr.appendChild(newtd("" + reg.getDireccion()));
/* 183 */       eltr.appendChild(newtd("" + reg.getTelefono()));
/* 184 */       hte.appendChild(eltr);
/* 185 */       cuantas++;
/*     */     } 
/* 187 */     arr.clear();
/* 188 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 202 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 204 */     String tipoDocumento = comms.request.getParameter("tipoDocumento");
/* 205 */     String numeroDocumento = comms.request.getParameter("numeroDocumento");
/* 206 */     ContInterventorDAO rs = new ContInterventorDAO();
/* 207 */     if (!rs.getEstadoConexion()) {
/* 208 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 210 */     ContInterventorDTO reg = rs.cargarRegistro(tipoDocumento, numeroDocumento);
/*     */ 
/*     */     
/* 213 */     rs.close();
/* 214 */     Varios oVarios = new Varios();
/* 215 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 216 */     if (!oPermisoAct) {
/* 217 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 218 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 220 */     if (reg != null) {
/* 221 */       this.pagHTML.getElementIdentificacionInterventor().setValue("" + reg.getNumeroDocumento());
/* 222 */       this.pagHTML.getElementApellidosInterventor().setValue("" + reg.getApellidos());
/* 223 */       this.pagHTML.getElementNombresInterventor().setValue("" + reg.getNombres());
/* 224 */       this.pagHTML.getElementDireccionInterventor().setValue("" + reg.getDireccion());
/* 225 */       this.pagHTML.getElementTelefonoInterventor().setValue("" + reg.getTelefono());
/* 226 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 227 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 228 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 229 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 230 */       HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
/* 231 */       comboMultivalores(combo, "tipo_documento", "" + reg.getTipoDocumento(), false);
/*     */       
/* 233 */       this.pagHTML.getElementIdentificacionInterventor().setReadOnly(true);
/* 234 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 246 */     HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
/* 247 */     comboMultivalores(combo, "tipo_documento", "CC", false);
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
/* 259 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 260 */     atrib.setValue(valor);
/* 261 */     return atrib;
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
/* 274 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 275 */     Element enlace = this.pagHTML.createElement("a");
/* 276 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 277 */     enlace.appendChild(hijo);
/* 278 */     Attr donde = this.pagHTML.createAttribute("href");
/* 279 */     donde.setValue(vinculo);
/* 280 */     enlace.setAttributeNode(donde);
/* 281 */     td.appendChild(enlace);
/* 282 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 283 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 293 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 294 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 295 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 296 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 311 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 312 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 313 */     rs.close();
/* 314 */     if (dejarBlanco) {
/* 315 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 316 */       op.setValue("");
/* 317 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 318 */       combo.appendChild(op);
/*     */     } 
/* 320 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 321 */     while (iterator.hasNext()) {
/* 322 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 323 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 324 */       op.setValue("" + reg.getCodigo());
/* 325 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 326 */       if (defecto.equals(reg.getCodigo())) {
/* 327 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 328 */         escogida.setValue("on");
/* 329 */         op.setAttributeNode(escogida);
/*     */       } 
/* 331 */       combo.appendChild(op);
/*     */     } 
/* 333 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContInterventorAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */