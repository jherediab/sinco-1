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
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.presentation.PreGerencialHTML;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreGerencial
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  40 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  43 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  44 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  45 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     
/*  47 */     int accion = Integer.parseInt(comms.request.getParameter("accion"));
/*     */     
/*  49 */     PreGerencialHTML pagHTML = (PreGerencialHTML)comms.xmlcFactory.create(PreGerencialHTML.class);
/*  50 */     HTMLFormElement forma = pagHTML.getElementForma();
/*  51 */     switch (accion) {
/*     */       case 1:
/*  53 */         forma.setAction("RepGerencial.po");
/*     */         break;
/*     */       case 2:
/*  56 */         forma.setAction("RepGerencialAdmin.po");
/*     */         break;
/*     */       case 3:
/*  59 */         forma.setAction("ListarEscalamientos.po");
/*     */         break;
/*     */     } 
/*     */     
/*  63 */     HTMLSelectElement annos = pagHTML.getElementIdAnno();
/*  64 */     for (int anno = 2000; anno <= Utilidades.getAnnoActual(); anno++) {
/*  65 */       HTMLOptionElement op1 = (HTMLOptionElement)pagHTML.createElement("option");
/*  66 */       op1.setValue(anno + "");
/*  67 */       op1.appendChild(pagHTML.createTextNode(anno + ""));
/*  68 */       annos.appendChild(op1);
/*  69 */       if (anno == Utilidades.getAnnoActual()) {
/*  70 */         Attr escogida = pagHTML.createAttribute("selected");
/*  71 */         escogida.setValue("on");
/*  72 */         op1.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     HTMLSelectElement mes1 = pagHTML.getElementIdMes1();
/*  77 */     HTMLSelectElement mes2 = pagHTML.getElementIdMes2();
/*     */     
/*  79 */     String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
/*     */     
/*  81 */     for (int mes = 1; mes <= 12; mes++) {
/*  82 */       HTMLOptionElement op2 = (HTMLOptionElement)pagHTML.createElement("option");
/*  83 */       op2.setValue(mes + "");
/*  84 */       op2.appendChild(pagHTML.createTextNode(meses[mes - 1]));
/*  85 */       mes1.appendChild(op2);
/*  86 */       if (mes == Utilidades.getMesActual()) {
/*  87 */         Attr escogida = pagHTML.createAttribute("selected");
/*  88 */         escogida.setValue("on");
/*  89 */         op2.setAttributeNode(escogida);
/*     */       } 
/*     */       
/*  92 */       HTMLOptionElement op3 = (HTMLOptionElement)pagHTML.createElement("option");
/*  93 */       op3.setValue(mes + "");
/*  94 */       op3.appendChild(pagHTML.createTextNode(meses[mes - 1]));
/*  95 */       mes2.appendChild(op3);
/*  96 */       if (mes == Utilidades.getMesActual()) {
/*  97 */         Attr escogida = pagHTML.createAttribute("selected");
/*  98 */         escogida.setValue("on");
/*  99 */         op3.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 104 */     if (!rsSeguridad.getEstadoConexion()) {
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 107 */     boolean gIndiceArea = rsSeguridad.tieneLlave(miGrupo, "oIndiceArea");
/* 108 */     boolean bMostrarTodasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/* 109 */     rsSeguridad.close();
/*     */ 
/*     */ 
/*     */     
/* 113 */     Collection arr = null;
/*     */     
/* 115 */     AreasDAO af = new AreasDAO();
/* 116 */     if (bMostrarTodasAreas) {
/* 117 */       arr = af.cargarActivas();
/*     */     }
/* 119 */     else if (gIndiceArea) {
/* 120 */       arr = af.cargarAreasHijas(idNav);
/*     */     } else {
/*     */       
/* 123 */       arr = af.cargarMisAreas(idNav);
/*     */     } 
/* 125 */     af.close();
/*     */     
/* 127 */     Iterator iterator = arr.iterator();
/* 128 */     HTMLSelectElement combo = pagHTML.getElementIdArea();
/* 129 */     while (iterator.hasNext()) {
/* 130 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 131 */       HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 132 */       op.setValue("" + reg.getCodigo());
/* 133 */       op.appendChild(pagHTML.createTextNode(reg.getDescripcion()));
/* 134 */       combo.appendChild(op);
/*     */       
/* 136 */       if (miArea == reg.getCodigo()) {
/* 137 */         Attr escogida = pagHTML.createAttribute("selected");
/* 138 */         escogida.setValue("on");
/* 139 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/* 142 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 143 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreGerencial.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */