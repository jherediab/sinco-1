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
/*     */ import sinco.presentation.ServiciosDeAreaHTML;
/*     */ import sinco.presentation.ServiciosDeMiArea;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class ServiciosDeMiArea
/*     */   implements HttpPresentation {
/*     */   private ServiciosDeAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     this.pagHTML = (ServiciosDeAreaHTML)comms.xmlcFactory.create(ServiciosDeAreaHTML.class);
/*  39 */     HTMLTableElement hte = this.pagHTML.getElementServicios();
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*  43 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(idNav);
/*     */     
/*  45 */     AreasDAO af = new AreasDAO();
/*  46 */     AreasDTO area = af.getArea(elNavegante.getArea());
/*  47 */     af.close();
/*     */     
/*  49 */     this.pagHTML.setTextNombreArea(area.getDescripcion());
/*     */     
/*  51 */     ServicioAreaDAO saf = new ServicioAreaDAO();
/*  52 */     UnidadMedidaDAO umf = new UnidadMedidaDAO();
/*  53 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*     */     
/*  55 */     ServiciosDAO sf = new ServiciosDAO();
/*  56 */     Collection arr = sf.cargarTodosDeArea(area.getCodigo());
/*  57 */     sf.close();
/*  58 */     boolean fondo = true;
/*  59 */     int cuantas = 0;
/*     */     
/*  61 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/*  62 */     while (iterator.hasNext()) {
/*  63 */       ServiciosDTO regServicio = (ServiciosDTO)iterator.next();
/*     */       
/*  65 */       if (!regServicio.esEspecializado())
/*  66 */         continue;  HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  68 */       fondo = !fondo;
/*  69 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  71 */       eltr.appendChild(newtd("" + regServicio.getCodigo()));
/*  72 */       if (regServicio.esEspecializado()) {
/*  73 */         eltr.appendChild(newtdhref("" + regServicio.getDescripcion(), "ActServicioArea.po?codigoServicio=" + regServicio.getCodigo() + "&codigoArea=" + area.getCodigo() + "&tipo=P&pag=1"));
/*     */       } else {
/*     */         
/*  76 */         eltr.appendChild(newtd("" + regServicio.getDescripcion()));
/*     */       } 
/*     */       
/*  79 */       UnidadMedidaDTO um = umf.getUnidadMedida(regServicio.getUnidadMedida());
/*     */       
/*  81 */       eltr.appendChild(newtd("" + regServicio.getDuracion() + " " + um.getDescripcion()));
/*     */       
/*  83 */       ServicioAreaDTO sa = saf.getServicioArea(area.getCodigo(), regServicio.getCodigo());
/*     */       
/*  85 */       int idpersona = sa.getPersonaCargo();
/*  86 */       if (idpersona < 10) {
/*  87 */         eltr.appendChild(newtd("Todos"));
/*     */       } else {
/*     */         
/*  90 */         SisUsuariosDTO pers = pf.cargarRegistro(idpersona);
/*     */         
/*  92 */         if (pers != null) {
/*  93 */           eltr.appendChild(newtd(pers.getNombres() + " " + pers.getApellidos()));
/*     */         } else {
/*     */           
/*  96 */           eltr.appendChild(newtd("No hay persona encargada definida"));
/*     */         } 
/*     */       } 
/*  99 */       hte.appendChild(eltr);
/* 100 */       cuantas++;
/*     */     } 
/*     */     
/* 103 */     saf.close();
/* 104 */     umf.close();
/* 105 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 106 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 107 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 112 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 113 */     atrib.setValue(valor);
/* 114 */     return atrib;
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
/* 126 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 127 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 128 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 129 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 133 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 134 */     Element enlace = this.pagHTML.createElement("a");
/* 135 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 136 */     enlace.appendChild(hijo);
/* 137 */     Attr donde = this.pagHTML.createAttribute("href");
/* 138 */     donde.setValue(vinculo);
/* 139 */     enlace.setAttributeNode(donde);
/* 140 */     td.appendChild(enlace);
/* 141 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 142 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ServiciosDeMiArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */