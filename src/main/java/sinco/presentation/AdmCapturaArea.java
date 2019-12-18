/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.AdmCapturaArea;
/*     */ import sinco.presentation.AdmCapturaAreaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmCapturaArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmCapturaAreaHTML pagHTML;
/*  29 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  36 */     this.elUsuario = "" + comms.session.getUser().getName();
/*  37 */     String _operacion = comms.request.getParameter("_operacion");
/*  38 */     String especializado = comms.request.getParameter("especializado");
/*  39 */     if (_operacion == null) _operacion = "L"; 
/*  40 */     int codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*     */     
/*  42 */     this.pagHTML = (AdmCapturaAreaHTML)comms.xmlcFactory.create(AdmCapturaAreaHTML.class);
/*  43 */     this.pagHTML.getElementEspecializado().setValue("" + especializado);
/*  44 */     this.pagHTML.getElementEspecializado2().setValue("" + especializado);
/*     */     
/*  46 */     this.pagHTML.getElementCodigoServicio().setValue("" + codigoServicio);
/*  47 */     this.pagHTML.getElementCodigoServicio2().setValue("" + codigoServicio);
/*     */     
/*  49 */     int cuantos = 0;
/*  50 */     if (_operacion.equals("L")) {
/*  51 */       cuantos = listar(comms);
/*  52 */       if (cuantos == 0) {
/*  53 */         Element division = this.pagHTML.getElementTrNuevo();
/*  54 */         division.getParentNode().removeChild(division);
/*     */       }
/*     */     
/*  57 */     } else if (_operacion.equals("SEL")) {
/*  58 */       buscarPesonas(comms);
/*     */     } 
/*  60 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  68 */     int codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*  69 */     String descripcion = comms.request.getParameter("descripcion");
/*  70 */     if (descripcion == null) descripcion = "";
/*     */     
/*  72 */     if (descripcion.length() == 0) {
/*  73 */       return 0;
/*     */     }
/*  75 */     AreasDAO rs = new AreasDAO();
/*  76 */     if (!rs.getEstadoConexion()) {
/*  77 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  80 */     Collection<AreasDTO> arr = rs.cargarParecidas(descripcion, codigoServicio);
/*  81 */     rs.close();
/*     */     
/*  83 */     HTMLSelectElement hte = this.pagHTML.getElementAreas();
/*  84 */     int cuantos = 0;
/*     */     
/*  86 */     Iterator<AreasDTO> iterator = arr.iterator();
/*  87 */     while (iterator.hasNext()) {
/*  88 */       AreasDTO reg = (AreasDTO)iterator.next();
/*  89 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  90 */       op.setValue("" + reg.getCodigo());
/*  91 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  92 */       hte.appendChild(op);
/*  93 */       cuantos++;
/*     */     } 
/*     */     
/*  96 */     HTMLElement sel = this.pagHTML.getElementFuncionario();
/*  97 */     sel.getParentNode().removeChild(sel);
/*     */     
/*  99 */     return cuantos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int buscarPesonas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 107 */     int area = Integer.parseInt(comms.request.getParameter("areas"));
/*     */     
/* 109 */     AreasDAO rs = new AreasDAO();
/* 110 */     if (!rs.getEstadoConexion()) {
/* 111 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 114 */     int cuantos = 0;
/* 115 */     Collection<AreasDTO> arr2 = rs.cargarArea(area);
/* 116 */     rs.close();
/*     */     
/* 118 */     HTMLSelectElement hte = this.pagHTML.getElementAreas();
/*     */     
/* 120 */     Iterator<AreasDTO> iterator2 = arr2.iterator();
/* 121 */     while (iterator2.hasNext()) {
/* 122 */       AreasDTO reg = (AreasDTO)iterator2.next();
/* 123 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 124 */       op.setValue("" + reg.getCodigo());
/* 125 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 126 */       hte.appendChild(op);
/*     */     } 
/*     */     
/* 129 */     HTMLSelectElement sel = this.pagHTML.getElementPersonas();
/*     */     
/* 131 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 132 */     Collection arr = pf.cargarActivoDeArea(area);
/* 133 */     Iterator iterator = arr.iterator();
/* 134 */     while (iterator.hasNext()) {
/* 135 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/* 136 */       HTMLOptionElement opersona = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 137 */       opersona.appendChild(this.pagHTML.createTextNode(regPersona.getApellidos() + ", " + regPersona.getNombres()));
/* 138 */       opersona.setValue("" + regPersona.getCodigoEmpleado());
/* 139 */       sel.appendChild(opersona);
/*     */     } 
/* 141 */     this.pagHTML.getElement_operacion().setValue("ACEPTO");
/*     */     
/* 143 */     return cuantos;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmCapturaArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */