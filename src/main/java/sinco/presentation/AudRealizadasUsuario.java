/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasAuditadasDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.DescCompetenciasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.AreasAuditadasDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.DescCompetenciasDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.AudRealizadasUsuario;
/*     */ import sinco.presentation.AudRealizadasUsuarioHTML;
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
/*     */ public class AudRealizadasUsuario
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudRealizadasUsuarioHTML pagHTML;
/*     */   boolean fondo2 = false;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  43 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  47 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  49 */     int auditor = 0;
/*     */     try {
/*  51 */       auditor = Integer.parseInt(comms.request.getParameter("auditor"));
/*     */     }
/*  53 */     catch (Exception e) {}
/*     */     
/*  55 */     auditor = idNav;
/*     */     
/*  57 */     String _operacion = "L";
/*     */     
/*  59 */     if (_operacion == null) _operacion = "F1";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  65 */     SisUsuariosDTO p = pf.getPersona(auditor);
/*     */     
/*  67 */     if (p == null) {
/*  68 */       p = pf.getPersona2(auditor);
/*     */     }
/*     */ 
/*     */     
/*  72 */     AreasDAO af = new AreasDAO();
/*  73 */     AreasDTO regArea = new AreasDTO();
/*     */     
/*  75 */     regArea = af.getNombreArea(p.getArea());
/*     */ 
/*     */     
/*  78 */     af.close();
/*     */     
/*  80 */     this.pagHTML = (AudRealizadasUsuarioHTML)comms.xmlcFactory.create(AudRealizadasUsuarioHTML.class);
/*  81 */     this.pagHTML.setTextNombreAuditor(p.getNombre());
/*  82 */     this.pagHTML.setTextNombreArea(regArea.getDescripcion());
/*  83 */     this.pagHTML.setTextCategoria(p.getNombreTipoAuditor());
/*  84 */     this.pagHTML.setTextNombreCiudad(regArea.getNombreMunicipioUbicacion());
/*     */ 
/*     */ 
/*     */     
/*  88 */     listar(comms, _operacion);
/*     */     
/*  90 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*     */     
/*  92 */     comms.response.writeDOM(this.pagHTML);
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
/* 105 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/* 107 */     int auditor = 0;
/*     */     try {
/* 109 */       auditor = Integer.parseInt(comms.request.getParameter("auditor"));
/*     */     }
/* 111 */     catch (Exception e) {}
/*     */     
/* 113 */     auditor = idNav;
/*     */ 
/*     */     
/* 116 */     AreasAuditadasDAO ob = new AreasAuditadasDAO();
/* 117 */     Collection<AreasAuditadasDTO> arr = ob.cargarTodosAuditor(auditor);
/*     */     
/* 119 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 121 */     Iterator<AreasAuditadasDTO> iterator = arr.iterator();
/* 122 */     while (iterator.hasNext()) {
/* 123 */       AreasAuditadasDTO reg = (AreasAuditadasDTO)iterator.next();
/* 124 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 126 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 127 */       String asociadoA = reg.getAsociadoA();
/*     */       
/* 129 */       if (asociadoA.equals("Informe")) {
/* 130 */         String url = "AudAuditarInformes.po?_operacion=V&ciclo=" + reg.getCiclo() + "&codigoInforme=" + reg.getCodigoInforme() + "";
/* 131 */         eltr.appendChild(newtdhref("" + reg.getNombreInforme(), url));
/* 132 */         eltr.appendChild(newtd("" + reg.getNombreCoordinador()));
/* 133 */         eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 134 */         eltr.appendChild(newtd("Informe"));
/* 135 */         hte.appendChild(eltr); continue;
/*     */       } 
/* 137 */       String url = "AudAuditar.po?_operacion=V&ciclo=" + reg.getCiclo() + "&codigoProceso=" + reg.getCodigoProceso() + "";
/* 138 */       eltr.appendChild(newtdhref("" + reg.getNombreProceso(), url));
/* 139 */       eltr.appendChild(newtd("" + reg.getNombreCoordinador()));
/* 140 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 141 */       eltr.appendChild(newtd("Proceso"));
/* 142 */       hte.appendChild(eltr);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 147 */     arr.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 346 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 347 */     atrib.setValue(valor);
/* 348 */     return atrib;
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
/* 359 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 360 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 361 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 362 */     return td;
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
/* 375 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 376 */     Element enlace = this.pagHTML.createElement("a");
/* 377 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 378 */     enlace.appendChild(hijo);
/* 379 */     Attr donde = this.pagHTML.createAttribute("href");
/* 380 */     donde.setValue(vinculo);
/* 381 */     enlace.setAttributeNode(donde);
/* 382 */     td.appendChild(enlace);
/* 383 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 384 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean cargarDescripciones(Vector miVector) {
/* 393 */     DescCompetenciasDAO rs = new DescCompetenciasDAO();
/* 394 */     if (!rs.getEstadoConexion()) {
/* 395 */       return false;
/*     */     }
/*     */     
/* 398 */     rs.cargarTodos();
/* 399 */     DescCompetenciasDTO reg = rs.next();
/* 400 */     while (reg != null) {
/* 401 */       miVector.add(reg);
/* 402 */       reg = rs.next();
/*     */     } 
/* 404 */     rs.close();
/* 405 */     return true;
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
/*     */   private int evaluarCorte(int cuantas, int auditorOld, int auditor, String competenciaOld, String competencia) {
/* 422 */     if (cuantas == 0) {
/* 423 */       return -1;
/*     */     }
/* 425 */     if (auditorOld != auditor) {
/* 426 */       return 1;
/*     */     }
/*     */     
/* 429 */     if (!competenciaOld.equals(competencia)) {
/* 430 */       return 2;
/*     */     }
/* 432 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudRealizadasUsuario.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */