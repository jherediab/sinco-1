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
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.ServiciosDeFuncionario;
/*     */ import sinco.presentation.ServiciosDeFuncionarioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class ServiciosDeFuncionario
/*     */   implements HttpPresentation {
/*     */   private ServiciosDeFuncionarioHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  32 */     this.pagHTML = (ServiciosDeFuncionarioHTML)comms.xmlcFactory.create(ServiciosDeFuncionarioHTML.class);
/*  33 */     HTMLTableElement hte = this.pagHTML.getElementServicios();
/*     */ 
/*     */     
/*  36 */     int codigoEmpleado = 0;
/*     */     try {
/*  38 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/*  40 */     catch (Exception e) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEmpleado"));
/*     */     } 
/*     */     
/*  44 */     ServiciosDAO sf = new ServiciosDAO();
/*  45 */     Collection arr = sf.cargarServiciosDeFuncionario(codigoEmpleado);
/*  46 */     sf.close();
/*     */     
/*  48 */     boolean fondo = true;
/*  49 */     int cuantas = 0;
/*     */     
/*  51 */     Iterator iterator = arr.iterator();
/*  52 */     while (iterator.hasNext()) {
/*  53 */       ServiciosDTO regServicio = (ServiciosDTO)iterator.next();
/*     */       
/*  55 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  57 */       fondo = !fondo;
/*  58 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  60 */       eltr.appendChild(newtd("" + regServicio.getCodigo()));
/*  61 */       eltr.appendChild(newtdhref("" + regServicio.getDescripcion(), "ActServicioArea.po?codigoServicio=" + regServicio.getCodigo() + "&codigoArea=" + regServicio.getCodigoArea() + "&tipo=P&pag=2"));
/*  62 */       eltr.appendChild(newtd("" + regServicio.getDuracion() + " " + regServicio.getUnidadMedida()));
/*  63 */       eltr.appendChild(newtd(regServicio.getNombreResponsable()));
/*  64 */       eltr.appendChild(newtd(regServicio.getNombreArea()));
/*  65 */       hte.appendChild(eltr);
/*  66 */       cuantas++;
/*     */     } 
/*     */     
/*  69 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  70 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  71 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  77 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  78 */     atrib.setValue(valor);
/*  79 */     return atrib;
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
/*  91 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  92 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  93 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  94 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  98 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  99 */     Element enlace = this.pagHTML.createElement("a");
/* 100 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 101 */     enlace.appendChild(hijo);
/* 102 */     Attr donde = this.pagHTML.createAttribute("href");
/* 103 */     donde.setValue(vinculo);
/* 104 */     enlace.setAttributeNode(donde);
/* 105 */     td.appendChild(enlace);
/* 106 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 107 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ServiciosDeFuncionario.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */