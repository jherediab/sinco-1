/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.presentation.EscogerAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EscogerArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  37 */     if (!comms.session.getSessionData().containsKey("ts")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ReiniciarOperacion"));
/*     */     }
/*     */     
/*  41 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  43 */     int elServicio = -1;
/*     */     try {
/*  45 */       elServicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  47 */     catch (Exception e) {}
/*     */     
/*  49 */     int laGemela = -1;
/*     */     try {
/*  51 */       laGemela = Integer.parseInt(comms.request.getParameter("gemela"));
/*     */     }
/*  53 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  56 */     int elPadre = -1;
/*     */     try {
/*  58 */       elPadre = Integer.parseInt(comms.request.getParameter("padre"));
/*     */     }
/*  60 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  64 */     AreasDAO af = new AreasDAO();
/*  65 */     EscogerAreaHTML pagHTML = (EscogerAreaHTML)comms.xmlcFactory.create(EscogerAreaHTML.class);
/*     */     
/*  67 */     int tipo = 0;
/*  68 */     String filtro = "";
/*     */     try {
/*  70 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*  71 */       if (tipo == 1) {
/*  72 */         filtro = comms.request.getParameter("filtro");
/*     */       }
/*     */     }
/*  75 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  78 */     boolean rta = false;
/*  79 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/*  80 */     if (tipo == 1) {
/*  81 */       arr = af.cargarTodosAbiertos(filtro, elServicio);
/*     */     
/*     */     }
/*  84 */     else if (elServicio > 0) {
/*  85 */       arr = af.cargarTodosParaServicioAbiertos(elServicio);
/*     */     } else {
/*     */       
/*  88 */       FechaDTO miFecha = new FechaDTO(Utilidades.fechaActual());
/*  89 */       miFecha.fechaMasDias(15L);
/*  90 */       String fechaH = miFecha.getFecha();
/*  91 */       miFecha.fechaMasDias((-15 - ParametrosDTO.getInt("numero.dias.retorno")));
/*  92 */       arr = af.cargarFrecuentes(idNav, elServicio, miFecha.getFecha(), fechaH);
/*     */     } 
/*     */     
/*  95 */     af.close();
/*     */     
/*  97 */     if (!rta) {
/*  98 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=areas"));
/*     */     }
/*     */     
/* 101 */     HTMLFormElement forma = pagHTML.getElementForma();
/* 102 */     if (elServicio > 0) {
/* 103 */       forma.setAction("EscogerPersona.po");
/*     */     } else {
/*     */       
/* 106 */       forma.setAction("EscogerServicio.po");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     HTMLSelectElement areas = pagHTML.getElementIdarea();
/*     */     
/* 115 */     HTMLOptionElement op = (HTMLOptionElement)pagHTML.createElement("option");
/* 116 */     op.setValue("0");
/* 117 */     op.appendChild(pagHTML.createTextNode(" "));
/* 118 */     areas.appendChild(op);
/*     */     
/* 120 */     boolean hay = false;
/* 121 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 122 */     while (iterator.hasNext()) {
/* 123 */       AreasDTO area = (AreasDTO)iterator.next();
/* 124 */       hay = true;
/* 125 */       op = (HTMLOptionElement)pagHTML.createElement("option");
/* 126 */       op.setValue("" + area.getCodigo());
/* 127 */       op.appendChild(pagHTML.createTextNode(area.getDescripcion()));
/* 128 */       areas.appendChild(op);
/*     */     } 
/* 130 */     if (!hay) {
/* 131 */       Element division = pagHTML.getElementSeleccion();
/* 132 */       division.getParentNode().removeChild(division);
/*     */     } 
/*     */     
/* 135 */     if (elServicio > 0) {
/* 136 */       HTMLInputElement temp = pagHTML.getElementIdServicio();
/* 137 */       temp.setValue("" + elServicio);
/*     */       
/* 139 */       temp = pagHTML.getElementIdServicio2();
/* 140 */       temp.setValue("" + elServicio);
/*     */     } 
/*     */     
/* 143 */     if (laGemela > 0) {
/* 144 */       HTMLInputElement temp = pagHTML.getElementIdGemela2();
/* 145 */       temp.setValue("" + laGemela);
/*     */       
/* 147 */       temp = pagHTML.getElementIdGemela();
/* 148 */       temp.setValue("" + laGemela);
/*     */     } 
/*     */     
/* 151 */     if (elPadre > 0) {
/* 152 */       HTMLInputElement temp = pagHTML.getElementIdPadre();
/* 153 */       temp.setValue("" + elPadre);
/*     */       
/* 155 */       temp = pagHTML.getElementIdPadre2();
/* 156 */       temp.setValue("" + elPadre);
/*     */     } 
/*     */     
/* 159 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 160 */     comms.response.writeDOM(pagHTML);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */