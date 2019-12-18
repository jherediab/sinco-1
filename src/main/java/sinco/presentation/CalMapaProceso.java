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
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.data.CalObjetivosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.presentation.CalMapaProceso;
/*     */ import sinco.presentation.CalMapaProcesoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalMapaProceso
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalMapaProcesoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "L";
/*     */     }
/*     */     
/*  48 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  50 */     this.pagHTML = (CalMapaProcesoHTML)comms.xmlcFactory.create(CalMapaProcesoHTML.class);
/*     */ 
/*     */     
/*  53 */     String proceso = comms.request.getParameter("proceso");
/*  54 */     String subproceso = comms.request.getParameter("subproceso");
/*  55 */     if (subproceso != null) {
/*  56 */       listarSubprocesos(comms, proceso, subproceso);
/*  57 */       HTMLElement eltr = this.pagHTML.getElementTblProcesos();
/*  58 */       eltr.getParentNode().removeChild(eltr);
/*     */     }
/*     */     else {
/*     */       
/*  62 */       listarProcesos(comms, proceso);
/*     */       
/*  64 */       HTMLElement eltr = this.pagHTML.getElementTblSubProcesos();
/*  65 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */     
/*  68 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  69 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listarProcesos(HttpPresentationComms comms, String proceso) throws HttpPresentationException, KeywordValueException {
/*  82 */     CalProcesosDAO rsCalProcesos = new CalProcesosDAO();
/*  83 */     CalObjetivosDAO rsCalObjetivos = new CalObjetivosDAO();
/*  84 */     if (!rsCalProcesos.getEstadoConexion()) {
/*  85 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  87 */     if (!rsCalObjetivos.getEstadoConexion()) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  91 */     CalProcesosDTO reg = rsCalProcesos.cargarRegistro(proceso);
/*  92 */     CalObjetivosDTO registro = rsCalObjetivos.cargarRegistroPorProceso(proceso);
/*  93 */     rsCalObjetivos.close();
/*  94 */     rsCalProcesos.close();
/*     */ 
/*     */ 
/*     */     
/*  98 */     if (reg == null) {
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ProcesoNoExiste"));
/*     */     }
/*     */     
/* 102 */     HTMLElement divDocumentos = this.pagHTML.getElementDivDocumentos();
/* 103 */     divDocumentos.appendChild(documentos(proceso, "", "NM"));
/*     */     
/* 105 */     HTMLElement divProcesos = this.pagHTML.getElementDivSubprocesos();
/* 106 */     divProcesos.appendChild(subprocesos(proceso));
/*     */     
/* 108 */     HTMLElement divAnexos = this.pagHTML.getElementDivAnexos();
/* 109 */     divAnexos.appendChild(documentos(proceso, "", "A"));
/*     */     
/* 111 */     if (registro != null) {
/* 112 */       this.pagHTML.setTextNombreProceso(reg.getDescripcion() + "  " + "      OBJETIVO:  " + registro.getDescripcion());
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
/*     */   
/*     */   private void listarSubprocesos(HttpPresentationComms comms, String proceso, String subproceso) throws HttpPresentationException, KeywordValueException {
/* 128 */     CalProcesosDAO rsCalProcesos = new CalProcesosDAO();
/* 129 */     if (!rsCalProcesos.getEstadoConexion()) {
/* 130 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 132 */     CalProcesosDTO regPro = rsCalProcesos.cargarRegistro(proceso);
/* 133 */     rsCalProcesos.close();
/*     */     
/* 135 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 136 */     if (!rs.getEstadoConexion()) {
/* 137 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 139 */     CalSubProcesosDTO reg = rs.cargarRegistro(proceso, subproceso);
/* 140 */     rs.close();
/*     */     
/* 142 */     if (reg == null) {
/* 143 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ProcesoNoExiste"));
/*     */     }
/*     */     
/* 146 */     HTMLElement divDocumentos = this.pagHTML.getElementDivDescSub();
/* 147 */     divDocumentos.appendChild(documentos(proceso, subproceso, "NM"));
/*     */     
/* 149 */     HTMLElement divProcedi = this.pagHTML.getElementDivProcSub();
/* 150 */     divProcedi.appendChild(documentos(proceso, subproceso, "P"));
/*     */     
/* 152 */     HTMLElement divAnexos = this.pagHTML.getElementDivAnexosSub();
/* 153 */     divAnexos.appendChild(documentos(proceso, subproceso, "A"));
/*     */     
/* 155 */     this.pagHTML.setTextNombreProceso(regPro.getDescripcion());
/* 156 */     this.pagHTML.setTextNombreSubProceso(reg.getDescripcion());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement subprocesos(String proceso) {
/* 167 */     boolean atributo = false;
/*     */     
/* 169 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/*     */ 
/*     */     
/* 172 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */ 
/*     */     
/* 175 */     CalSubProcesosDAO rsCalSubProcesos = new CalSubProcesosDAO();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     Collection arr = rsCalSubProcesos.cargarDeProceso(proceso);
/* 182 */     rsCalSubProcesos.close();
/*     */     
/* 184 */     boolean fondo = true;
/* 185 */     Iterator iterator = arr.iterator();
/* 186 */     while (iterator.hasNext()) {
/* 187 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 188 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 189 */       if (!atributo) {
/* 190 */         eltr.setAttribute("class", "rowodd");
/* 191 */         atributo = true;
/*     */       } else {
/* 193 */         eltr.setAttribute("class", "roweven");
/* 194 */         atributo = false;
/*     */       } 
/*     */       
/* 197 */       fondo = !fondo;
/* 198 */       String url = "CalMapaProceso.po?proceso=" + proceso + "&subproceso=" + reg.getSubproceso();
/* 199 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url, false));
/*     */       
/* 201 */       laTabla.appendChild(eltr);
/*     */     } 
/*     */ 
/*     */     
/* 205 */     return laTabla;
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
/*     */   private HTMLElement documentos(String proceso, String subProceso, String tipoDocumento) {
/* 221 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 222 */     boolean atributo = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 229 */     CalDocumentosDAO rsDoc = new CalDocumentosDAO();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     Collection arr = rsDoc.cargarDocumentosDeProcesoPorTipo(proceso, subProceso, tipoDocumento, "M");
/* 236 */     rsDoc.close();
/*     */     
/* 238 */     boolean fondo = true;
/* 239 */     Iterator iterator = arr.iterator();
/* 240 */     while (iterator.hasNext()) {
/* 241 */       CalDocumentosDTO reg = (CalDocumentosDTO)iterator.next();
/* 242 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 243 */       if (!atributo) {
/* 244 */         eltr.setAttribute("class", "rowodd");
/* 245 */         atributo = true;
/*     */       } else {
/* 247 */         eltr.setAttribute("class", "roweven");
/* 248 */         atributo = false;
/*     */       } 
/* 250 */       fondo = !fondo;
/*     */ 
/*     */ 
/*     */       
/* 254 */       FechaDTO fechaRevision = new FechaDTO(reg.getFechaRevision());
/* 255 */       String url = "";
/*     */       
/* 257 */       if (reg.getNombreArchivoPdf() != "") {
/* 258 */         url = "CalVerDocumento.po?mm=NO&_operacion=VDH&numeroDocumento=" + reg.getNombreArchivoPdf() + "&tipoDocumento=P";
/*     */       } else {
/* 260 */         url = "CalVerDocumento.po?mm=NO&_operacion=VDH&numeroDocumento=" + reg.getNombreArchivoWord() + "&tipoDocumento=P";
/*     */       } 
/*     */       
/* 263 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url, true));
/*     */ 
/*     */       
/* 266 */       laTabla.appendChild(eltr);
/*     */     } 
/*     */ 
/*     */     
/* 270 */     return laTabla;
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
/* 283 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 284 */     atrib.setValue(valor);
/* 285 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva) {
/* 298 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 299 */     Element enlace = this.pagHTML.createElement("a");
/* 300 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 301 */     enlace.appendChild(hijo);
/* 302 */     Attr donde = this.pagHTML.createAttribute("href");
/* 303 */     donde.setValue(vinculo);
/* 304 */     enlace.setAttributeNode(donde);
/* 305 */     if (nueva) {
/* 306 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 308 */     td.appendChild(enlace);
/* 309 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 310 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 320 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 321 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 322 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 323 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalMapaProceso.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */