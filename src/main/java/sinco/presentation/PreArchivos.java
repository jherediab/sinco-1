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
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.presentation.PreArchivos;
/*     */ import sinco.presentation.PreArchivosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class PreArchivos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PreArchivosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  28 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  29 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  31 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  33 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  34 */     String pagina = comms.request.getParameter("pagina_siguiente");
/*     */     
/*  36 */     this.pagHTML = (PreArchivosHTML)comms.xmlcFactory.create(PreArchivosHTML.class);
/*  37 */     this.pagHTML.setTextNumeroSolicitud("" + idsol);
/*     */     
/*  39 */     this.pagHTML.getElementSolicitud().setValue("" + idsol);
/*  40 */     this.pagHTML.getElementSolicitudV().setValue("" + idsol);
/*  41 */     this.pagHTML.getElementPagina_siguiente().setValue("" + pagina);
/*     */     
/*  43 */     HTMLFormElement forma = this.pagHTML.getElementForma();
/*  44 */     forma.setAction(pagina);
/*     */ 
/*     */     
/*  47 */     HTMLTableElement tabla = this.pagHTML.getElementIdArchivos();
/*  48 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/*  49 */     asf.getArchivosSolicitud(idsol);
/*  50 */     ArchivosSolicitudDTO asv = asf.next();
/*     */     
/*  52 */     boolean hay = false;
/*  53 */     boolean fondo = true;
/*  54 */     while (asv != null) {
/*  55 */       hay = true;
/*     */       
/*  57 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  58 */       fondo = !fondo;
/*  59 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
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
/*  72 */       if (ParametrosDTO.getString("medio_descarga_archivos").equals("ftp")) {
/*  73 */         eltr.appendChild(newtdhref(asv.getArchivo(), ParametrosDTO.getString("ftp") + asv.getArchivo(), true));
/*     */       } else {
/*     */         
/*  76 */         eltr.appendChild(newtdhref(asv.getArchivo(), "VerArchivo.po?archivo=" + asv.getArchivo() + "&fecha=" + Utilidades.darFormatoFecha(asv.getFechaInsercion()), true));
/*     */       } 
/*     */       
/*  79 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(asv.getFechaInsercion())));
/*     */       
/*  81 */       tabla.appendChild(eltr);
/*  82 */       asv = asf.next();
/*     */     } 
/*  84 */     asf.close();
/*     */ 
/*     */     
/*  87 */     if (!hay) {
/*  88 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/*  89 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  95 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 100 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 101 */     atrib.setValue(valor);
/* 102 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido) {
/* 114 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 115 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 116 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 117 */     return td;
/*     */   }
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 120 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 121 */     Element enlace = this.pagHTML.createElement("a");
/* 122 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 123 */     enlace.appendChild(hijo);
/* 124 */     Attr donde = this.pagHTML.createAttribute("href");
/* 125 */     donde.setValue(vinculo);
/* 126 */     enlace.setAttributeNode(donde);
/* 127 */     if (nuevaVentana) {
/* 128 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/*     */     
/* 131 */     td.appendChild(enlace);
/* 132 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 133 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */