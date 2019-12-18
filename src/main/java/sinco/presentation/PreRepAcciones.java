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
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.PreRepAcciones;
/*     */ import sinco.presentation.PreRepAccionesHTML;
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
/*     */ public class PreRepAcciones
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PreRepAccionesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  43 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  45 */     int accion = Integer.parseInt(comms.request.getParameter("accion"));
/*     */ 
/*     */     
/*  48 */     this.pagHTML = (PreRepAccionesHTML)comms.xmlcFactory.create(PreRepAccionesHTML.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     HTMLInputElement elem = this.pagHTML.getElementIdAccion();
/*  56 */     elem.setValue("" + accion);
/*     */     
/*  58 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  59 */     SisUsuariosDTO p = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */     
/*  61 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  62 */     if (!rsSeguridad.getEstadoConexion()) {
/*  63 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  65 */     boolean bVerAreasHijas = rsSeguridad.tieneLlave(miGrupo, "oVerAreasACargo");
/*  66 */     boolean bMostrarTodasLasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/*  67 */     rsSeguridad.close();
/*     */     
/*  69 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/*     */     
/*  71 */     AreasDAO af = new AreasDAO();
/*  72 */     HTMLSelectElement areas = this.pagHTML.getElementIdArea();
/*  73 */     if (bMostrarTodasLasAreas) {
/*  74 */       arr = af.cargarTodos();
/*     */     }
/*  76 */     else if (bVerAreasHijas) {
/*  77 */       arr = af.cargarSecuencia(p.getArea());
/*     */     } else {
/*     */       
/*  80 */       arr = af.cargarArea(p.getArea());
/*     */     } 
/*  82 */     af.close();
/*     */     
/*  84 */     Iterator<AreasDTO> iterator = arr.iterator();
/*  85 */     while (iterator.hasNext()) {
/*  86 */       AreasDTO area = (AreasDTO)iterator.next();
/*     */       
/*  88 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  89 */       op.setValue("" + area.getCodigo());
/*  90 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*  91 */       areas.appendChild(op);
/*  92 */       if (p.getArea() == area.getCodigo()) {
/*  93 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  94 */         escogida.setValue("on");
/*  95 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 100 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 101 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreRepAcciones.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */