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
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.FuncionariosDeMiArea;
/*     */ import sinco.presentation.FuncionariosDeMiAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class FuncionariosDeMiArea
/*     */   implements HttpPresentation {
/*     */   private FuncionariosDeMiAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  32 */     this.pagHTML = (FuncionariosDeMiAreaHTML)comms.xmlcFactory.create(FuncionariosDeMiAreaHTML.class);
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  34 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     
/*  36 */     int area = 0;
/*     */     try {
/*  38 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  40 */     catch (Exception e) {
/*  41 */       area = miArea;
/*     */     } 
/*     */     
/*  44 */     AreasDAO af = new AreasDAO();
/*  45 */     AreasDTO regArea = af.getArea(area);
/*  46 */     af.close();
/*     */     
/*  48 */     this.pagHTML.setTextNombreArea(regArea.getDescripcion());
/*     */ 
/*     */     
/*  51 */     SisUsuariosDAO rs = new SisUsuariosDAO();
/*  52 */     Collection arr = rs.cargarActivoDeArea(regArea.getCodigo());
/*     */     
/*  54 */     boolean fondo = true;
/*  55 */     HTMLTableElement hte = this.pagHTML.getElementServicios();
/*     */     
/*  57 */     ServicioAreaDAO saf = new ServicioAreaDAO();
/*  58 */     Iterator iterator = arr.iterator();
/*  59 */     while (iterator.hasNext()) {
/*  60 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/*     */       
/*  62 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  64 */       fondo = !fondo;
/*  65 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  67 */       int codigo_empleado = reg.getCodigoEmpleado();
/*  68 */       int serviciosEspecializados = saf.cuantosEspecializados(codigo_empleado);
/*  69 */       int solicitudesAbiertas = saf.getSolicitudesAbiertas(codigo_empleado);
/*  70 */       int solicitudesEscaladas = saf.getSolicitudesEscaladas(codigo_empleado);
/*     */       
/*  72 */       eltr.appendChild(newtd("" + codigo_empleado));
/*  73 */       eltr.appendChild(newtdhref("" + reg.getNombre(), "ActPersona.po?codigoEmpleado=" + codigo_empleado + "&codigoArea=" + regArea.getCodigo() + "&tipo=P"));
/*     */       
/*  75 */       if (serviciosEspecializados == 0) {
/*  76 */         eltr.appendChild(newtd("" + serviciosEspecializados));
/*     */       } else {
/*  78 */         eltr.appendChild(newtdhref("" + serviciosEspecializados, "ServiciosDeFuncionario.po?codigoEmpleado=" + codigo_empleado));
/*  79 */       }  eltr.appendChild(newtdhref("" + solicitudesAbiertas, "SolicitudesPorAtenderPorArea.po?proveedor=" + codigo_empleado));
/*  80 */       eltr.appendChild(newtd("" + solicitudesEscaladas));
/*  81 */       eltr.appendChild(newtd(reg.getNombreEstado()));
/*     */       
/*  83 */       hte.appendChild(eltr);
/*     */     } 
/*  85 */     saf.close();
/*     */     
/*  87 */     AreasDAO rsAreas = new AreasDAO();
/*  88 */     Collection<AreasDTO> arra = rsAreas.cargarDebajo2(area);
/*  89 */     rsAreas.close();
/*     */ 
/*     */ 
/*     */     
/*  93 */     HTMLTableElement hte2 = this.pagHTML.getElementSubareas();
/*     */     
/*  95 */     Iterator<AreasDTO> iteratora = arra.iterator();
/*  96 */     while (iteratora.hasNext()) {
/*  97 */       AreasDTO regAreaa = (AreasDTO)iterator.next();
/*  98 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 100 */       fondo = !fondo;
/* 101 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 103 */       eltr.appendChild(newtdhref("" + regAreaa.getDescripcion(), "FuncionariosDeMiArea.po?area=" + regAreaa.getCodigo()));
/* 104 */       hte2.appendChild(eltr);
/*     */     } 
/* 106 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 107 */     comms.response.writeDOM(this.pagHTML);
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
/* 119 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 120 */     atrib.setValue(valor);
/* 121 */     return atrib;
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
/* 133 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 134 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 135 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 136 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 146 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 147 */     Element enlace = this.pagHTML.createElement("a");
/* 148 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 149 */     enlace.appendChild(hijo);
/* 150 */     Attr donde = this.pagHTML.createAttribute("href");
/* 151 */     donde.setValue(vinculo);
/* 152 */     enlace.setAttributeNode(donde);
/* 153 */     td.appendChild(enlace);
/* 154 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 155 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\FuncionariosDeMiArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */