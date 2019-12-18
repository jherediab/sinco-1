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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.UnidadMedidaDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.UnidadMedidaDAO;
/*     */ import sinco.presentation.ServiciosDeArea;
/*     */ import sinco.presentation.ServiciosDeAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class ServiciosDeArea
/*     */   implements HttpPresentation {
/*     */   private ServiciosDeAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  36 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  38 */     this.pagHTML = (ServiciosDeAreaHTML)comms.xmlcFactory.create(ServiciosDeAreaHTML.class);
/*  39 */     HTMLTableElement hte = this.pagHTML.getElementServicios();
/*  40 */     int id = Integer.parseInt(comms.request.getParameter("area"));
/*     */     
/*  42 */     AreasDAO af = new AreasDAO();
/*  43 */     AreasDTO area = af.getArea(id);
/*  44 */     af.close();
/*     */     
/*  46 */     this.pagHTML.setTextNombreArea(area.getDescripcion());
/*     */     
/*  48 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*     */     
/*  50 */     ServiciosDAO sf = new ServiciosDAO();
/*  51 */     Collection<ServiciosDTO> arr = sf.cargarTodosDeArea(id);
/*  52 */     sf.close();
/*     */ 
/*     */     
/*  55 */     ServicioAreaDAO saf = new ServicioAreaDAO();
/*  56 */     UnidadMedidaDAO umf = new UnidadMedidaDAO();
/*     */     
/*  58 */     boolean fondo = true;
/*  59 */     int cuantas = 0;
/*  60 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/*  61 */     while (iterator.hasNext()) {
/*  62 */       ServiciosDTO regServicio = (ServiciosDTO)iterator.next();
/*  63 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  65 */       fondo = !fondo;
/*  66 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  68 */       eltr.appendChild(newtd("" + regServicio.getCodigo()));
/*  69 */       eltr.appendChild(newtd("" + regServicio.getDescripcion()));
/*     */       
/*  71 */       UnidadMedidaDTO um = umf.getUnidadMedida(regServicio.getUnidadMedida());
/*     */       
/*  73 */       eltr.appendChild(newtd("" + ((regServicio != null) ? ("" + regServicio.getDuracion()) : " ") + " " + ((um != null) ? um.getDescripcion() : " ")));
/*     */       
/*  75 */       if (regServicio.esEspecializado()) {
/*  76 */         ServicioAreaDTO sa = saf.getServicioArea(id, regServicio.getCodigo());
/*  77 */         int idpersona = sa.getPersonaCargo();
/*  78 */         if (idpersona > 0) {
/*  79 */           SisUsuariosDTO pers = pf.cargarRegistro(idpersona);
/*  80 */           if (pers != null) {
/*  81 */             eltr.appendChild(newtd(pers.getNombres() + " " + pers.getApellidos()));
/*     */           } else {
/*  83 */             eltr.appendChild(newtd("No hay persona encargada definida"));
/*     */           } 
/*     */         } 
/*  86 */       } else if (regServicio.esMultiple()) {
/*  87 */         eltr.appendChild(newtd("Multiples proveedores"));
/*     */       }
/*  89 */       else if (regServicio.esMultiple()) {
/*  90 */         eltr.appendChild(newtd("Todos"));
/*     */       } 
/*     */ 
/*     */       
/*  94 */       hte.appendChild(eltr);
/*  95 */       cuantas++;
/*     */     } 
/*  97 */     umf.close();
/*  98 */     saf.close();
/*  99 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 100 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 101 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 107 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 108 */     atrib.setValue(valor);
/* 109 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido) {
/* 121 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 122 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 123 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 124 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ServiciosDeArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */