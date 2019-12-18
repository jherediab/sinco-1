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
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.presentation.CalDocumentosArchivos;
/*     */ import sinco.presentation.CalDocumentosArchivosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class CalDocumentosArchivos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalDocumentosArchivosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  31 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  33 */     String codigo = comms.request.getParameter("codigo");
/*     */     
/*  35 */     String doc = comms.request.getParameter("Doc");
/*  36 */     String doc2 = comms.request.getParameter("Doc");
/*     */     
/*  38 */     String objetivoHidden = comms.request.getParameter("objetivoHidden");
/*  39 */     if (doc == null || doc.length() == 0) doc = ""; 
/*  40 */     if (doc2 == null || doc2.length() == 0) doc2 = ""; 
/*  41 */     if (objetivoHidden == null || objetivoHidden.length() == 0) objetivoHidden = ""; 
/*  42 */     this.pagHTML = (CalDocumentosArchivosHTML)comms.xmlcFactory.create(CalDocumentosArchivosHTML.class);
/*     */     
/*  44 */     verDetalle(codigo);
/*     */     
/*  46 */     this.pagHTML.getElementDoc().setValue(doc);
/*  47 */     this.pagHTML.getElementDoc2().setValue(doc2);
/*  48 */     this.pagHTML.getElementCodigoV().setValue("" + codigo);
/*     */     
/*  50 */     this.pagHTML.getElementObjetivoHidden().setValue("" + objetivoHidden);
/*     */ 
/*     */     
/*  53 */     String _operacion = comms.request.getParameter("_operacion");
/*  54 */     if (_operacion.equals("Subir")) {
/*  55 */       this.pagHTML.getElementCodigoDocumento().setValue("" + codigo);
/*     */       
/*  57 */       HTMLElement sel = this.pagHTML.getElementTblVersiones();
/*  58 */       sel.getParentNode().removeChild(sel);
/*     */     }
/*     */     else {
/*     */       
/*  62 */       listarHistoria(codigo);
/*  63 */       HTMLElement sel = this.pagHTML.getElementTblsubir();
/*  64 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  67 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  68 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean listarHistoria(String codigo) {
/*  79 */     CalDocumentosDAO rs = new CalDocumentosDAO();
/*  80 */     if (!rs.getEstadoConexion()) {
/*  81 */       return false;
/*     */     }
/*  83 */     Collection arr = rs.cargarHistoria(codigo);
/*  84 */     rs.close();
/*  85 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  86 */     boolean fondo = true;
/*  87 */     int cuantas = 0;
/*  88 */     Iterator iterator = arr.iterator();
/*  89 */     while (iterator.hasNext()) {
/*  90 */       CalDocumentosDTO reg = (CalDocumentosDTO)iterator.next();
/*  91 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  92 */       fondo = !fondo;
/*  93 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*  94 */       eltr.appendChild(newtd("" + reg.getVersion()));
/*  95 */       eltr.appendChild(newtd("" + reg.getFechaVersion()));
/*     */       
/*  97 */       String url = "CalVerDocumento.po?&_operacion=VDH&numeroDocumento=" + reg.getNombreArchivoWord() + "&tipoDocumento=W&version=" + reg.getVersion();
/*  98 */       eltr.appendChild(newtdhref("" + reg.getNombreArchivoWord(), url, true));
/*     */       
/* 100 */       url = "CalVerDocumento.po?&_operacion=VDH&numeroDocumento=" + reg.getNombreArchivoPdf() + "&tipoDocumento=P&version=" + reg.getVersion();
/* 101 */       eltr.appendChild(newtdhref("" + reg.getNombreArchivoPdf(), url, true));
/* 102 */       hte.appendChild(eltr);
/* 103 */       cuantas++;
/*     */     } 
/*     */     
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean verDetalle(String codigo) {
/* 116 */     CalDocumentosDAO rsCalDocumentos = new CalDocumentosDAO();
/* 117 */     if (!rsCalDocumentos.getEstadoConexion()) {
/* 118 */       return false;
/*     */     }
/* 120 */     CalDocumentosDTO reg = rsCalDocumentos.cargarDocumento(codigo);
/* 121 */     rsCalDocumentos.close();
/* 122 */     if (reg == null) {
/* 123 */       return false;
/*     */     }
/*     */     
/* 126 */     this.pagHTML.setTextCodigo("" + reg.getCodigo());
/* 127 */     this.pagHTML.setTextTipoDocumento("" + reg.getTipoDocumento());
/* 128 */     this.pagHTML.setTextProceso("" + reg.getNombreProceso());
/* 129 */     this.pagHTML.setTextSubproceso("" + reg.getNombreSubProceso());
/* 130 */     this.pagHTML.setTextDescripcion("" + reg.getDescripcion());
/* 131 */     this.pagHTML.setTextVersion("" + reg.getVersion());
/* 132 */     this.pagHTML.setTextFechaVersion("" + reg.getFechaVersion());
/* 133 */     this.pagHTML.setTextEstado("" + reg.getEstado());
/* 134 */     this.pagHTML.setTextNombreArchivoWord("" + reg.getNombreArchivoWord());
/* 135 */     this.pagHTML.setTextNombreArchivoPdf("" + reg.getNombreArchivoPdf());
/* 136 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attr newAttr(String tipo, String valor) {
/* 147 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 148 */     atrib.setValue(valor);
/* 149 */     return atrib;
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
/*     */   public HTMLElement newtd(String contenido) {
/* 161 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 162 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 163 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 164 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 174 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 175 */     Element enlace = this.pagHTML.createElement("a");
/* 176 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 177 */     enlace.appendChild(hijo);
/* 178 */     Attr donde = this.pagHTML.createAttribute("href");
/* 179 */     donde.setValue(vinculo);
/* 180 */     enlace.setAttributeNode(donde);
/* 181 */     if (nuevaVentana) {
/* 182 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/*     */     
/* 185 */     td.appendChild(enlace);
/* 186 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 187 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */