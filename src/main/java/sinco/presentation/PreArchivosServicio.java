/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.presentation.PreArchivosServicio;
/*    */ import sinco.presentation.PreArchivosServicioHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class PreArchivosServicio
/*    */   implements HttpPresentation
/*    */ {
/*    */   private PreArchivosServicioHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 23 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 24 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 26 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 28 */     int codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*    */     
/* 30 */     ServiciosDAO rs = new ServiciosDAO();
/* 31 */     if (!rs.getEstadoConexion()) {
/* 32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*    */     }
/* 34 */     ServiciosDTO reg = rs.cargarRegistro(codigo);
/* 35 */     rs.close();
/*    */ 
/*    */     
/* 38 */     this.pagHTML = (PreArchivosServicioHTML)comms.xmlcFactory.create(PreArchivosServicioHTML.class);
/* 39 */     this.pagHTML.setTextServicio("" + reg.getDescripcion());
/*    */ 
/*    */     
/* 42 */     if (reg.getArchivoAnexo() != null) {
/*    */       
/* 44 */       String url = "CalVerDocumento.po?numeroDocumento=" + reg.getArchivoAnexo() + "&tipoDocumento=W&_operacion=VDC";
/* 45 */       Element enlaceC = this.pagHTML.getElementNombreArchivo();
/* 46 */       enlaceC.appendChild(newhref(reg.getArchivoAnexo(), url, true));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     this.pagHTML.getElementCodigo().setValue("" + codigo);
/* 55 */     this.pagHTML.getElementCodigoV().setValue("" + codigo);
/*    */     
/* 57 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 58 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Element newhref(String contenido, String vinculo, boolean nueva) {
/* 71 */     Element enlace = this.pagHTML.createElement("a");
/* 72 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 73 */     enlace.appendChild(hijo);
/* 74 */     Attr donde = this.pagHTML.createAttribute("href");
/* 75 */     donde.setValue(vinculo);
/* 76 */     enlace.setAttributeNode(donde);
/* 77 */     if (nueva) {
/* 78 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*    */     }
/* 80 */     return enlace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Attr newAttr(String tipo, String valor) {
/* 93 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 94 */     atrib.setValue(valor);
/* 95 */     return atrib;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreArchivosServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */