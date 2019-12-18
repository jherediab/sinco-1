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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.presentation.AMPreArchivos;
/*     */ import sinco.presentation.AMPreArchivosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class AMPreArchivos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMPreArchivosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  32 */     int numero = Integer.parseInt(comms.request.getParameter("numero"));
/*  33 */     int causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     
/*  35 */     this.pagHTML = (AMPreArchivosHTML)comms.xmlcFactory.create(AMPreArchivosHTML.class);
/*  36 */     this.pagHTML.setTextNumero("" + numero);
/*     */     
/*  38 */     this.pagHTML.getElementIdNumero().setValue("" + numero);
/*  39 */     this.pagHTML.getElementIdNumeroV().setValue("" + numero);
/*  40 */     this.pagHTML.getElementIdCausa().setValue("" + causa);
/*  41 */     this.pagHTML.getElementIdCausaV().setValue("" + causa);
/*     */ 
/*     */     
/*  44 */     HTMLTableElement tabla = this.pagHTML.getElementDetalle();
/*  45 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/*  46 */     asf.getArchivosAccionMejora(numero, causa);
/*  47 */     ArchivosSolicitudDTO asv = asf.nextA();
/*     */     
/*  49 */     boolean hay = false;
/*  50 */     boolean fondo = true;
/*  51 */     while (asv != null) {
/*  52 */       hay = true;
/*     */       
/*  54 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  55 */       fondo = !fondo;
/*  56 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  58 */       if (ParametrosDTO.getString("medio_descarga_archivos").equals("ftp")) {
/*  59 */         eltr.appendChild(newtdhref(asv.getArchivo(), ParametrosDTO.getString("ftp") + asv.getArchivo(), true));
/*     */       } else {
/*     */         
/*  62 */         eltr.appendChild(newtdhref(asv.getArchivo(), "VerArchivo.po?ruta=archivos_acciones&archivo=" + asv.getArchivo(), true));
/*     */       } 
/*     */       
/*  65 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(asv.getFechaInsercion())));
/*     */       
/*  67 */       tabla.appendChild(eltr);
/*  68 */       asv = asf.nextA();
/*     */     } 
/*  70 */     asf.close();
/*     */ 
/*     */     
/*  73 */     if (!hay) {
/*  74 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/*  75 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  81 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  85 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  86 */     atrib.setValue(valor);
/*  87 */     return atrib;
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
/*  99 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 100 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 101 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 102 */     return td;
/*     */   }
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 105 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 106 */     Element enlace = this.pagHTML.createElement("a");
/* 107 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 108 */     enlace.appendChild(hijo);
/* 109 */     Attr donde = this.pagHTML.createAttribute("href");
/* 110 */     donde.setValue(vinculo);
/* 111 */     enlace.setAttributeNode(donde);
/* 112 */     if (nuevaVentana) {
/* 113 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 115 */     td.appendChild(enlace);
/* 116 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 117 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMPreArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */