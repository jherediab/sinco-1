/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AudBloquesDTO;
/*     */ import sinco.business.AudPlanAnualDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudBloquesDAO;
/*     */ import sinco.data.AudPlanAnualDAO;
/*     */ import sinco.presentation.AudAcciones;
/*     */ import sinco.presentation.AudAccionesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudAcciones
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudAccionesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  39 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  40 */     String _operacion = comms.request.getParameter("_operacion");
/*  41 */     if (_operacion == null || _operacion.length() == 0) {
/*  42 */       _operacion = "X";
/*     */     }
/*     */     
/*  45 */     this.pagHTML = (AudAccionesHTML)comms.xmlcFactory.create(AudAccionesHTML.class);
/*     */     
/*  47 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  48 */       listar(comms, _operacion);
/*     */     }
/*  50 */     else if (_operacion.equals("M")) {
/*  51 */       marcar(comms);
/*     */     } 
/*  53 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  54 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  68 */     int ciclo = 0;
/*     */     try {
/*  70 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  72 */     catch (Exception e) {
/*  73 */       ciclo = Utilidades.getAnnoActual();
/*     */     } 
/*     */     
/*  76 */     HTMLSelectElement combo = this.pagHTML.getElementCiclo();
/*  77 */     comboCiclos(combo, ciclo, false);
/*     */     
/*  79 */     if (_operacion.equals("X")) {
/*  80 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/*  81 */       sel.getParentNode().removeChild(sel);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  86 */     AudBloquesDAO ob = new AudBloquesDAO();
/*  87 */     Collection<AudBloquesDTO> arr = ob.cargarInconformidades(ciclo);
/*  88 */     HTMLTableSectionElement hte = this.pagHTML.getElementTblDetalles();
/*     */     
/*  90 */     Iterator<AudBloquesDTO> iterator = arr.iterator();
/*  91 */     while (iterator.hasNext()) {
/*  92 */       AudBloquesDTO reg = (AudBloquesDTO)iterator.next();
/*  93 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  94 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/*  95 */       eltr.appendChild(newtd("" + reg.getDescripcionPregunta()));
/*  96 */       eltr.appendChild(newtd("" + reg.getRespuesta()));
/*  97 */       eltr.appendChild(newtd("" + reg.getAsociadoA()));
/*     */       
/*  99 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 101 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 102 */       checkbox.setAttribute("type", "checkbox");
/* 103 */       checkbox.setName("P_" + reg.getBloque() + "_" + reg.getPregunta());
/* 104 */       tdMarca.appendChild(checkbox);
/* 105 */       tdMarca.setAttributeNode(newAttr("class", "ctd"));
/* 106 */       eltr.appendChild(tdMarca);
/*     */       
/* 108 */       hte.appendChild(eltr);
/*     */     } 
/* 110 */     if (arr.size() == 0) {
/* 111 */       HTMLElement sel = this.pagHTML.getElementTblGrabar();
/* 112 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 114 */     arr.clear();
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
/*     */   private void marcar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 127 */     int ciclo = 0;
/*     */     try {
/* 129 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 131 */     catch (Exception e) {}
/*     */     
/* 133 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 135 */     Enumeration<?> enumera = comms.request.getParameterNames();
/* 136 */     AudBloquesDAO ob = new AudBloquesDAO();
/*     */     
/* 138 */     while (enumera.hasMoreElements()) {
/* 139 */       String param = (String)enumera.nextElement();
/* 140 */       if (param.startsWith("P_")) {
/* 141 */         String[] arregloDatos = param.split("_");
/* 142 */         int bloque = Integer.parseInt(arregloDatos[1]);
/* 143 */         int pregunta = Integer.parseInt(arregloDatos[2]);
/* 144 */         ob.actualizarInconformidad(ciclo, bloque, pregunta, elUsuario);
/*     */       } 
/*     */     } 
/* 147 */     String sPagina = "AudAcciones.po?_operacion=L&ciclo=" + ciclo;
/* 148 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 163 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 164 */     atrib.setValue(valor);
/* 165 */     return atrib;
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
/* 176 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 177 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 178 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 179 */     return td;
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
/*     */   private HTMLElement newtd(String contenido, int colspan, String clase) {
/* 191 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 192 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 193 */     if (colspan > 0) {
/* 194 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 196 */     if (clase.length() > 0) {
/* 197 */       td.setAttributeNode(newAttr("class", clase));
/*     */     } else {
/*     */       
/* 200 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 202 */     return td;
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
/*     */   
/*     */   private void comboCiclos(HTMLSelectElement combo, int defecto, boolean dejarBlanco) {
/* 219 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/* 220 */     Collection<AudPlanAnualDTO> arr = ob.cargarTodos(0);
/*     */     
/* 222 */     if (dejarBlanco) {
/* 223 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 224 */       op.setValue("");
/* 225 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 226 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 229 */     Iterator<AudPlanAnualDTO> iterator = arr.iterator();
/* 230 */     while (iterator.hasNext()) {
/* 231 */       AudPlanAnualDTO reg = (AudPlanAnualDTO)iterator.next();
/* 232 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 233 */       op.setValue("" + reg.getCiclo());
/* 234 */       op.appendChild(this.pagHTML.createTextNode("" + reg.getCiclo()));
/* 235 */       if (defecto == reg.getCiclo()) {
/* 236 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 237 */         escogida.setValue("on");
/* 238 */         op.setAttributeNode(escogida);
/*     */       } 
/* 240 */       combo.appendChild(op);
/*     */     } 
/* 242 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudAcciones.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */