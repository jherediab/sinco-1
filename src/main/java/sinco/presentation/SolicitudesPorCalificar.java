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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.TipoCalificacionDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.TipoCalificacionDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesPorCalificar;
/*     */ import sinco.presentation.SolicitudesPorCalificarHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class SolicitudesPorCalificar
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesPorCalificarHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  36 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  38 */     this.pagHTML = (SolicitudesPorCalificarHTML)comms.xmlcFactory.create(SolicitudesPorCalificarHTML.class);
/*     */     
/*  40 */     HTMLTableSectionElement hte = this.pagHTML.getElementSolicitudes();
/*  41 */     int id = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  43 */     TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*  44 */     Collection<TipoCalificacionDTO> estados = tcf.cargarEstados();
/*  45 */     tcf.close();
/*     */     
/*  47 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  48 */     rsSol.cargarTodosPorCalificarPor(id);
/*  49 */     VSolicitudesDTO regSol = rsSol.next();
/*  50 */     boolean fondo = true;
/*  51 */     int cuantas = 0;
/*     */     
/*  53 */     while (regSol != null) {
/*  54 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  56 */       fondo = !fondo;
/*  57 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  59 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  60 */       String sPagina = "VSEnCurso.po?solicitud=" + regSol.getNumero();
/*  61 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  62 */       eltr.appendChild(newtd("" + regSol.getNombreAreaProveedora(), false));
/*  63 */       eltr.appendChild(newtd("" + regSol.getNombreProveedor(), false));
/*  64 */       eltr.appendChild(newtd("" + regSol.getObservaciones(), false));
/*  65 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), false));
/*  66 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaRealTerminacion()), false));
/*  67 */       eltr.appendChild(newtd("" + regSol.getNivelEscalamiento(), false));
/*     */       
/*  69 */       HTMLElement tdEstado = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/*  71 */       HTMLSelectElement combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/*  72 */       combo.setName("SOL_" + regSol.getNumero());
/*  73 */       combo.setId("" + regSol.getNumero());
/*     */       
/*  75 */       comboEstados(combo, estados);
/*  76 */       combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/*  77 */       combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*  78 */       combo.setAttributeNode(newAttr("class", "cal"));
/*  79 */       tdEstado.appendChild(combo);
/*  80 */       eltr.appendChild(tdEstado);
/*     */       
/*  82 */       hte.appendChild(eltr);
/*  83 */       regSol = rsSol.next();
/*  84 */       cuantas++;
/*     */     } 
/*  86 */     rsSol.close();
/*     */     
/*  88 */     HTMLSelectElement calsel = this.pagHTML.getElementConfiabilidad();
/*  89 */     comboEstados(calsel, estados);
/*     */     
/*  91 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  92 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  93 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 105 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 106 */     atrib.setValue(valor);
/* 107 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 117 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 118 */     Element enlace = this.pagHTML.createElement("a");
/* 119 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 120 */     enlace.appendChild(hijo);
/* 121 */     Attr donde = this.pagHTML.createAttribute("href");
/* 122 */     donde.setValue(vinculo);
/* 123 */     enlace.setAttributeNode(donde);
/* 124 */     td.appendChild(enlace);
/* 125 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 126 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 137 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 138 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 139 */     if (alinear) {
/* 140 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 142 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 143 */     return td;
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
/*     */   private void comboEstados(HTMLSelectElement combo, Collection<?> arr) {
/* 158 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 159 */     op.setValue("");
/* 160 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 161 */     combo.appendChild(op);
/*     */     
/* 163 */     Iterator<?> iterator = arr.iterator();
/* 164 */     while (iterator.hasNext()) {
/* 165 */       TipoCalificacionDTO reg = (TipoCalificacionDTO)iterator.next();
/*     */       
/* 167 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 168 */       op.setValue("" + reg.getCodigo());
/* 169 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 170 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesPorCalificar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */