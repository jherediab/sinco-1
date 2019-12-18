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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AudInformesPlanAnualDTO;
/*     */ import sinco.business.AudPlanAnualDTO;
/*     */ import sinco.business.AudProcesosPlanAnualDTO;
/*     */ import sinco.data.AudInformesPlanAnualDAO;
/*     */ import sinco.data.AudPlanAnualDAO;
/*     */ import sinco.data.AudProcesosPlanAnualDAO;
/*     */ import sinco.presentation.AudVerPlanAnual;
/*     */ import sinco.presentation.AudVerPlanAnualHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudVerPlanAnual
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudVerPlanAnualHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  40 */     int ciclo = 0;
/*     */     try {
/*  42 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  44 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.pagHTML = (AudVerPlanAnualHTML)comms.xmlcFactory.create(AudVerPlanAnualHTML.class);
/*     */     
/*  51 */     verRegistro(ciclo);
/*     */     
/*  53 */     listarProcesos(ciclo, "P");
/*  54 */     listarProcesos(ciclo, "E");
/*     */     
/*  56 */     listarInformes(ciclo, "L");
/*  57 */     listarInformes(ciclo, "D");
/*     */     
/*  59 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  60 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listarProcesos(int ciclo, String tipoProceso) throws HttpPresentationException, KeywordValueException {
/*  74 */     AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/*  75 */     Collection<AudProcesosPlanAnualDTO> arr = ob.cargarPlan(ciclo, tipoProceso);
/*     */     
/*  77 */     HTMLTableSectionElement hte = tipoProceso.equals("P") ? this.pagHTML.getElementTblProcesos() : this.pagHTML.getElementTblProcesosEspeciales();
/*     */     
/*  79 */     Iterator<AudProcesosPlanAnualDTO> iterator = arr.iterator();
/*  80 */     while (iterator.hasNext()) {
/*  81 */       AudProcesosPlanAnualDTO reg = (AudProcesosPlanAnualDTO)iterator.next();
/*  82 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  83 */       eltr.appendChild(newtd("" + reg.getNombreCodigoProceso()));
/*  84 */       eltr.appendChild(newtd("" + reg.getNombreTipoProceso()));
/*  85 */       eltr.appendChild(newtd("" + reg.getNombrecoordinadorAuditoria()));
/*  86 */       eltr.appendChild(newtd("" + reg.getEquipoAuditor()));
/*  87 */       eltr.appendChild(newtdMes("" + (reg.getMes01().equals("S") ? "X" : " ")));
/*  88 */       eltr.appendChild(newtdMes("" + (reg.getMes02().equals("S") ? "X" : " ")));
/*  89 */       eltr.appendChild(newtdMes("" + (reg.getMes03().equals("S") ? "X" : " ")));
/*  90 */       eltr.appendChild(newtdMes("" + (reg.getMes04().equals("S") ? "X" : " ")));
/*  91 */       eltr.appendChild(newtdMes("" + (reg.getMes05().equals("S") ? "X" : " ")));
/*  92 */       eltr.appendChild(newtdMes("" + (reg.getMes06().equals("S") ? "X" : " ")));
/*  93 */       eltr.appendChild(newtdMes("" + (reg.getMes07().equals("S") ? "X" : " ")));
/*  94 */       eltr.appendChild(newtdMes("" + (reg.getMes08().equals("S") ? "X" : " ")));
/*  95 */       eltr.appendChild(newtdMes("" + (reg.getMes09().equals("S") ? "X" : " ")));
/*  96 */       eltr.appendChild(newtdMes("" + (reg.getMes10().equals("S") ? "X" : " ")));
/*  97 */       eltr.appendChild(newtdMes("" + (reg.getMes11().equals("S") ? "X" : " ")));
/*  98 */       eltr.appendChild(newtdMes("" + (reg.getMes12().equals("S") ? "X" : " ")));
/*  99 */       eltr.appendChild(newtd("" + reg.getNombreAreaResponsable()));
/* 100 */       hte.appendChild(eltr);
/*     */     } 
/* 102 */     arr.clear();
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
/*     */   private void listarInformes(int ciclo, String tipoInforme) throws HttpPresentationException, KeywordValueException {
/* 114 */     AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
/* 115 */     Collection<AudInformesPlanAnualDTO> arr = ob.cargarPlan(ciclo, tipoInforme);
/*     */     
/* 117 */     HTMLTableSectionElement hte = tipoInforme.equals("L") ? this.pagHTML.getElementTblInformes() : this.pagHTML.getElementTblInformesEspeciales();
/*     */     
/* 119 */     Iterator<AudInformesPlanAnualDTO> iterator = arr.iterator();
/* 120 */     while (iterator.hasNext()) {
/* 121 */       AudInformesPlanAnualDTO reg = (AudInformesPlanAnualDTO)iterator.next();
/* 122 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 123 */       eltr.appendChild(newtd("" + reg.getNombreCodigoInforme()));
/* 124 */       eltr.appendChild(newtd("" + reg.getNombreTipoProceso()));
/* 125 */       eltr.appendChild(newtd("" + reg.getNombreCoordinadorAuditoría()));
/* 126 */       eltr.appendChild(newtd("" + reg.getEquipoAuditor()));
/*     */       
/* 128 */       eltr.appendChild(newtdMes("" + (reg.getMes01().equals("S") ? "X" : " ")));
/* 129 */       eltr.appendChild(newtdMes("" + (reg.getMes02().equals("S") ? "X" : " ")));
/* 130 */       eltr.appendChild(newtdMes("" + (reg.getMes03().equals("S") ? "X" : " ")));
/* 131 */       eltr.appendChild(newtdMes("" + (reg.getMes04().equals("S") ? "X" : " ")));
/* 132 */       eltr.appendChild(newtdMes("" + (reg.getMes05().equals("S") ? "X" : " ")));
/* 133 */       eltr.appendChild(newtdMes("" + (reg.getMes06().equals("S") ? "X" : " ")));
/* 134 */       eltr.appendChild(newtdMes("" + (reg.getMes07().equals("S") ? "X" : " ")));
/* 135 */       eltr.appendChild(newtdMes("" + (reg.getMes08().equals("S") ? "X" : " ")));
/* 136 */       eltr.appendChild(newtdMes("" + (reg.getMes09().equals("S") ? "X" : " ")));
/* 137 */       eltr.appendChild(newtdMes("" + (reg.getMes10().equals("S") ? "X" : " ")));
/* 138 */       eltr.appendChild(newtdMes("" + (reg.getMes11().equals("S") ? "X" : " ")));
/* 139 */       eltr.appendChild(newtdMes("" + (reg.getMes12().equals("S") ? "X" : " ")));
/* 140 */       eltr.appendChild(newtd("" + reg.getNombreLiderProceso()));
/*     */ 
/*     */       
/* 143 */       hte.appendChild(eltr);
/*     */     } 
/* 145 */     arr.clear();
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
/*     */   private void verRegistro(int ciclo) {
/* 158 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/* 159 */     AudPlanAnualDTO reg = ob.cargarRegistro(ciclo);
/* 160 */     if (reg != null) {
/* 161 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 162 */       this.pagHTML.setTextObjetivoEd("" + reg.getObjetivo());
/* 163 */       this.pagHTML.setTextAlcanceEd("" + reg.getAlcance());
/* 164 */       this.pagHTML.setTextCriteriosEd("" + reg.getCriterios());
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 180 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 181 */     atrib.setValue(valor);
/* 182 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 195 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 196 */     Element enlace = this.pagHTML.createElement("a");
/* 197 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 198 */     enlace.appendChild(hijo);
/* 199 */     Attr donde = this.pagHTML.createAttribute("href");
/* 200 */     donde.setValue(vinculo);
/* 201 */     enlace.setAttributeNode(donde);
/* 202 */     td.appendChild(enlace);
/* 203 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 204 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 214 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 215 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 216 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 217 */     return td;
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
/*     */   private HTMLElement newtdMes(String contenido) {
/* 229 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 230 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 231 */     if (contenido.equals("X")) {
/* 232 */       td.setAttributeNode(newAttr("class", "ctMes"));
/*     */     } else {
/*     */       
/* 235 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 237 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudVerPlanAnual.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */