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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasAuditadasDTO;
/*     */ import sinco.business.AudDetallesDTO;
/*     */ import sinco.data.AreasAuditadasDAO;
/*     */ import sinco.data.AudDetallesDAO;
/*     */ import sinco.presentation.AudVerAreas;
/*     */ import sinco.presentation.AudVerAreasHTML;
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
/*     */ public class AudVerAreas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudVerAreasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "X";
/*     */     }
/*     */     
/*  48 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     this.pagHTML = (AudVerAreasHTML)comms.xmlcFactory.create(AudVerAreasHTML.class);
/*     */     
/*  59 */     AudDetallesDAO rs = new AudDetallesDAO();
/*  60 */     Collection<AudDetallesDTO> arr = rs.areasAuditadas();
/*  61 */     rs.close();
/*     */ 
/*     */ 
/*     */     
/*  65 */     boolean hay = false;
/*  66 */     HTMLSelectElement areas = this.pagHTML.getElementIdArea();
/*  67 */     Iterator iterator = arr.iterator();
/*  68 */     while (iterator.hasNext()) {
/*  69 */       AudDetallesDTO reg = (AudDetallesDTO)iterator.next();
/*     */       
/*  71 */       hay = true;
/*  72 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  73 */       op.setValue("" + reg.getAreaAuditada());
/*  74 */       op.appendChild(this.pagHTML.createTextNode(reg.getNombreAreaAuditada()));
/*  75 */       areas.appendChild(op);
/*     */     } 
/*  77 */     if (!hay) {
/*  78 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoHayAreas"));
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  83 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  86 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  87 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 100 */     activarVista("consulta");
/*     */ 
/*     */     
/* 103 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     int area = 0;
/*     */     try {
/* 109 */       area = Integer.parseInt(comms.request.getParameter("idArea"));
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=area"));
/*     */     } 
/*     */ 
/*     */     
/* 116 */     AreasAuditadasDAO ob = new AreasAuditadasDAO();
/* 117 */     Collection<AreasAuditadasDTO> arr = ob.cargarTodos(area);
/*     */     
/* 119 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 121 */     Iterator<AreasAuditadasDTO> iterator = arr.iterator();
/* 122 */     while (iterator.hasNext()) {
/* 123 */       AreasAuditadasDTO reg = (AreasAuditadasDTO)iterator.next();
/* 124 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 125 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/*     */       
/* 127 */       String asociadoA = reg.getAsociadoA();
/*     */       
/* 129 */       if (asociadoA.equals("I")) {
/* 130 */         eltr.appendChild(newtd("" + reg.getNombreInforme()));
/* 131 */         eltr.appendChild(newtd("" + reg.getNombreCoordinador()));
/* 132 */         eltr.appendChild(newtd("" + reg.getEquipoInforme()));
/* 133 */         eltr.appendChild(newtd("Informe"));
/* 134 */         hte.appendChild(eltr); continue;
/*     */       } 
/* 136 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 137 */       eltr.appendChild(newtd("" + reg.getNombreCoordinador()));
/* 138 */       eltr.appendChild(newtd("" + reg.getEquipoProceso()));
/* 139 */       eltr.appendChild(newtd("Proceso"));
/* 140 */       hte.appendChild(eltr);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 166 */     if (!vista.equals("consulta")) {
/* 167 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/* 168 */       sel.getParentNode().removeChild(sel);
/* 169 */       sel = this.pagHTML.getElementDivResultados();
/* 170 */       sel.getParentNode().removeChild(sel);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 185 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 186 */     atrib.setValue(valor);
/* 187 */     return atrib;
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
/* 198 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 199 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 200 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 201 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudVerAreas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */