/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisMensajesDTO;
/*     */ import sinco.data.SisMensajesDAO;
/*     */ import sinco.presentation.MensajeHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mensaje
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  28 */     String codigo = comms.request.getParameter("codigo");
/*     */     
/*  30 */     String texto = "";
/*     */     try {
/*  32 */       texto = comms.request.getParameter("texto");
/*     */     }
/*  34 */     catch (Exception e) {}
/*     */     
/*  36 */     if (texto == null) texto = "";
/*     */     
/*  38 */     String ultimoError = "";
/*  39 */     if (ParametrosDTO.getInt("error") != 0) {
/*  40 */       ultimoError = "" + ParametrosDTO.getString("mensajeError");
/*     */     }
/*     */     
/*  43 */     String p1 = "";
/*     */     try {
/*  45 */       p1 = comms.request.getParameter("p1");
/*     */     }
/*  47 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  50 */     String p2 = "";
/*     */     try {
/*  52 */       p2 = comms.request.getParameter("p2");
/*     */     }
/*  54 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  57 */     String p3 = "";
/*     */     try {
/*  59 */       p3 = comms.request.getParameter("p3");
/*     */     }
/*  61 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  64 */     String p4 = "";
/*     */     try {
/*  66 */       p4 = comms.request.getParameter("p4");
/*     */     }
/*  68 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  71 */     String p5 = "";
/*     */     try {
/*  73 */       p5 = comms.request.getParameter("p5");
/*     */     }
/*  75 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  78 */     SisMensajesDAO rsMensajes = new SisMensajesDAO();
/*  79 */     SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
/*  80 */     rsMensajes.close();
/*     */     
/*  82 */     if (regMensajes == null) {
/*  83 */       MensajeHTML pagHTML = (MensajeHTML)comms.xmlcFactory.create(MensajeHTML.class);
/*     */       
/*  85 */       if (codigo.equals("NoSesion") || codigo.equals("UsuarioNoValido") || codigo.equals("UsuarioInactivo") || codigo.equals("PasswordErrado")) {
/*  86 */         HTMLElement eltr = pagHTML.getElementMostrarMenu();
/*  87 */         eltr.getParentNode().removeChild(eltr);
/*     */       } 
/*  89 */       if (!codigo.equals("NoSesion")) {
/*  90 */         HTMLElement eltr = pagHTML.getElementTrBtnLogin();
/*  91 */         eltr.getParentNode().removeChild(eltr);
/*     */       } 
/*     */       
/*  94 */       pagHTML.setTextMensaje("Ocurrio el error numero " + codigo + " " + texto + ". Este mensaje no existe el la tabla de mensajes");
/*  95 */       pagHTML.setTextErrorBaseDatos(ultimoError);
/*  96 */       pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  97 */       comms.response.writeDOM(pagHTML);
/*     */       
/*     */       return;
/*     */     } 
/* 101 */     String salida = regMensajes.format(p1, p2, p3, p4, p5);
/*     */     
/* 103 */     MensajeHTML pagHTML = (MensajeHTML)comms.xmlcFactory.create(MensajeHTML.class);
/*     */     
/* 105 */     if (codigo.equals("NoSesion") || codigo.equals("UsuarioNoValido") || codigo.equals("UsuarioInactivo") || codigo.equals("PasswordErrado")) {
/* 106 */       HTMLElement eltr = pagHTML.getElementMostrarMenu();
/* 107 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/* 109 */     if (!codigo.equals("NoSesion")) {
/* 110 */       HTMLElement eltr = pagHTML.getElementTrBtnLogin();
/* 111 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */ 
/*     */     
/* 115 */     pagHTML.setTextMensaje(salida + " " + texto);
/* 116 */     pagHTML.setTextErrorBaseDatos(ultimoError);
/* 117 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 118 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Mensaje.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */