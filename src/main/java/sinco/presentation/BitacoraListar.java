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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.BitacoraDTO;
/*     */ import sinco.data.BitacoraDAO;
/*     */ import sinco.presentation.BitacoraListar;
/*     */ import sinco.presentation.BitacoraListarHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitacoraListar
/*     */   implements HttpPresentation
/*     */ {
/*     */   private BitacoraListarHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion&texto=Su sesion no ha sido iniciada o ha caducado. Regresa a la pagina principal e ingrese su login y password de nuevo"));
/*     */     }
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  32 */     String tabla = comms.request.getParameter("tabla");
/*  33 */     String llave = comms.request.getParameter("llave");
/*  34 */     String llave2 = comms.request.getParameter("llave2");
/*  35 */     String llave3 = comms.request.getParameter("llave3");
/*  36 */     String llave4 = comms.request.getParameter("llave4");
/*  37 */     String llave5 = comms.request.getParameter("llave5");
/*     */     
/*  39 */     if (llave == null) llave = ""; 
/*  40 */     if (llave2 == null) llave2 = ""; 
/*  41 */     if (llave3 == null) llave3 = ""; 
/*  42 */     if (llave4 == null) llave4 = ""; 
/*  43 */     if (llave5 == null) llave5 = "";
/*     */     
/*  45 */     this.pagHTML = (BitacoraListarHTML)comms.xmlcFactory.create(BitacoraListarHTML.class);
/*     */     
/*  47 */     BitacoraDAO rsBitacora = new BitacoraDAO();
/*  48 */     if (!rsBitacora.getEstadoConexion()) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  51 */     Collection arr = rsBitacora.cargarBitacora(tabla, llave, llave2, llave3, llave4, llave5);
/*  52 */     rsBitacora.close();
/*     */     
/*  54 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  55 */     boolean fondo = true;
/*     */     
/*  57 */     Iterator iterator = arr.iterator();
/*  58 */     while (iterator.hasNext()) {
/*  59 */       BitacoraDTO reg = (BitacoraDTO)iterator.next();
/*  60 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  61 */       fondo = !fondo;
/*  62 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  64 */       eltr.appendChild(newtd("" + reg.getCampo()));
/*  65 */       eltr.appendChild(newtd("" + reg.getFecha()));
/*  66 */       eltr.appendChild(newtd("" + reg.getUsuario()));
/*  67 */       eltr.appendChild(newtd("" + reg.getValInicial()));
/*  68 */       eltr.appendChild(newtd("" + reg.getValFinal()));
/*  69 */       eltr.appendChild(newtd("" + reg.getObservaciones()));
/*  70 */       hte.appendChild(eltr);
/*     */     } 
/*  72 */     this.pagHTML.setTextNroRegistros("" + arr.size());
/*  73 */     arr.clear();
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
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  88 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  89 */     atrib.setValue(valor);
/*  90 */     return atrib;
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
/* 101 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 102 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 103 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 104 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\BitacoraListar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */