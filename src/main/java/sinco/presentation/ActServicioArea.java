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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.ActServicioArea;
/*     */ import sinco.presentation.ActServicioAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActServicioArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ActServicioAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     String tipo = comms.request.getParameter("tipo");
/*     */     
/*  44 */     if (tipo == null || tipo.length() == 0) {
/*  45 */       tipo = "";
/*     */     }
/*     */     
/*  48 */     int codigoArea = 0;
/*     */     try {
/*  50 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */     }
/*  52 */     catch (Exception e) {
/*  53 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoArea"));
/*     */     } 
/*     */     
/*  56 */     int codigoServicio = 0;
/*     */     try {
/*  58 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoServicio"));
/*     */     } 
/*     */     
/*  64 */     int pag = Integer.parseInt(comms.request.getParameter("pag"));
/*     */ 
/*     */ 
/*     */     
/*  68 */     if (tipo.equals("M"))
/*     */     {
/*  70 */       creacion(comms);
/*     */     }
/*     */ 
/*     */     
/*  74 */     this.pagHTML = (ActServicioAreaHTML)comms.xmlcFactory.create(ActServicioAreaHTML.class);
/*  75 */     ServicioAreaDAO rsServicioArea = new ServicioAreaDAO();
/*  76 */     if (!rsServicioArea.getEstadoConexion()) {
/*  77 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  79 */     ServicioAreaDTO regServicioArea = rsServicioArea.getServicioArea(codigoArea, codigoServicio);
/*  80 */     if (regServicioArea == null) {
/*  81 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=ServiciosArea"));
/*     */     }
/*  83 */     rsServicioArea.close();
/*     */     
/*  85 */     AreasDAO areaf = new AreasDAO();
/*  86 */     AreasDTO areaProveedor = areaf.getArea(codigoArea);
/*  87 */     areaf.close();
/*     */     
/*  89 */     ServiciosDAO serf = new ServiciosDAO();
/*  90 */     ServiciosDTO regServicio = serf.cargarRegistro(codigoServicio);
/*  91 */     serf.close();
/*     */     
/*  93 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*  94 */     SisUsuariosDTO elResponsable = perf.cargarRegistro(regServicioArea.getPersonaCargo());
/*     */     
/*  96 */     this.pagHTML.setTextNombreArea(areaProveedor.getDescripcion());
/*  97 */     this.pagHTML.setTextNombreServicio(regServicio.getDescripcion());
/*  98 */     this.pagHTML.setTextDuracion("" + regServicio.getDuracion());
/*  99 */     this.pagHTML.setTextResponsable((elResponsable != null) ? elResponsable.getNombre() : "No Asignado");
/*     */     
/* 101 */     this.pagHTML.getElementIdCodigoArea().setValue("" + regServicioArea.getCodigoArea());
/* 102 */     this.pagHTML.getElementIdCodigoServicio().setValue("" + regServicioArea.getCodigoServicio());
/* 103 */     this.pagHTML.getElementIdPag().setValue("" + pag);
/* 104 */     this.pagHTML.getElementIdTipo().setValue("M");
/*     */     
/* 106 */     HTMLSelectElement combo = this.pagHTML.getElementIdPersonaCargo();
/* 107 */     llenarCombo(combo, areaProveedor.getNivelSuperior(), areaProveedor.getSecuencia(), regServicioArea.getPersonaCargo(), areaProveedor.getNivel());
/*     */     
/* 109 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 110 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 124 */     String _operacion = comms.request.getParameter("_operacion");
/* 125 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/* 128 */     int codigoArea = 0;
/*     */     try {
/* 130 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */     }
/* 132 */     catch (Exception e) {
/* 133 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoArea"));
/*     */     } 
/*     */     
/* 136 */     int codigoServicio = 0;
/*     */     try {
/* 138 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*     */     }
/* 140 */     catch (Exception e) {
/* 141 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoServicio"));
/*     */     } 
/*     */     
/* 144 */     int pag = Integer.parseInt(comms.request.getParameter("pag"));
/*     */ 
/*     */     
/* 147 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 149 */     int personaCargo = 0;
/*     */     try {
/* 151 */       personaCargo = Integer.parseInt(comms.request.getParameter("personaCargo"));
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=personaCargo"));
/*     */     } 
/*     */     
/* 157 */     ServicioAreaDAO rsServicioArea = new ServicioAreaDAO();
/* 158 */     if (!rsServicioArea.getEstadoConexion()) {
/* 159 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 161 */     rta = rsServicioArea.modificarRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/* 162 */     rsServicioArea.close();
/* 163 */     if (!rta.isRta()) {
/* 164 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=ServiciosArea"));
/*     */     }
/*     */     
/* 167 */     String sPagina = "ServiciosDeMiArea.po?area=" + codigoArea;
/* 168 */     if (pag == 2)
/* 169 */       sPagina = "FuncionariosDeMiArea.po"; 
/* 170 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean llenarCombo(HTMLSelectElement combo, int nivelSuperior, String secuencia, int defecto, int nivel) {
/* 194 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 195 */     Collection arr = pf.cargarsubditos(nivelSuperior, secuencia, nivel);
/*     */ 
/*     */     
/* 198 */     Iterator iterator = arr.iterator();
/* 199 */     while (iterator.hasNext()) {
/* 200 */       SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
/*     */       
/* 202 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 203 */       op.setValue("" + personaDelArea.getCodigoEmpleado());
/* 204 */       op.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres()));
/* 205 */       if (defecto == personaDelArea.getCodigoEmpleado()) {
/* 206 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 207 */         escogida.setValue("on");
/* 208 */         op.setAttributeNode(escogida);
/*     */       } 
/* 210 */       combo.appendChild(op);
/*     */     } 
/* 212 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ActServicioArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */