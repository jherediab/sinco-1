/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.presentation.AMEstadisticaCerradas;
/*     */ import sinco.presentation.AMEstadisticaCerradasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMEstadisticaCerradas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMEstadisticaCerradasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  39 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  41 */     String _operacion = comms.request.getParameter("_operacion");
/*  42 */     if (_operacion == null || _operacion.length() == 0) {
/*  43 */       _operacion = "X";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.pagHTML = (AMEstadisticaCerradasHTML)comms.xmlcFactory.create(AMEstadisticaCerradasHTML.class);
/*     */     
/*  51 */     String respuesta = listar(comms, _operacion);
/*     */     
/*  53 */     if (respuesta != null) {
/*  54 */       comms.response.setContentType("application/xls");
/*  55 */       comms.response.setHeader("Content-Disposition", "inline;filename=SolCiclo.xls");
/*  56 */       comms.response.setStatus(200, "Good job");
/*     */       
/*  58 */       HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */       
/*     */       try {
/*  62 */         out.write(respuesta.getBytes());
/*  63 */         out.flush();
/*     */         
/*     */         return;
/*  66 */       } catch (Exception e) {
/*  67 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  72 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private String listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  85 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  86 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  87 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     
/*  89 */     int areaImplanta = 0;
/*     */     try {
/*  91 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */     }
/*  93 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  96 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  97 */     if (!rsSeguridad.getEstadoConexion()) {
/*  98 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 100 */     boolean bMostrarAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/* 101 */     rsSeguridad.close();
/*     */     
/* 103 */     HTMLSelectElement combo = this.pagHTML.getElementIdAreaImplanta();
/* 104 */     comboAreas(combo, miArea, bMostrarAreas);
/*     */     
/* 106 */     if (_operacion.equals("X")) {
/* 107 */       return null;
/*     */     }
/*     */     
/* 110 */     int areasHijas = 0;
/*     */     try {
/* 112 */       areasHijas = Integer.parseInt(comms.request.getParameter("areasHijas"));
/*     */     }
/* 114 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 117 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/* 118 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/*     */     
/* 120 */     String ciclo = comms.request.getParameter("ciclo");
/*     */     
/* 122 */     String exportar = comms.request.getParameter("exportar");
/* 123 */     if (exportar == null) exportar = "N";
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
/* 174 */     return null;
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
/* 188 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 189 */     atrib.setValue(valor);
/* 190 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean align) {
/* 201 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 202 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 203 */     if (align) {
/* 204 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 207 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 209 */     return td;
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
/*     */   private void comboAreas(HTMLSelectElement combo, int area1, boolean bMostrarAreas) {
/* 223 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/* 224 */     AreasDAO af = new AreasDAO();
/*     */     
/* 226 */     if (bMostrarAreas) {
/* 227 */       arr = af.cargarTodos();
/*     */     } else {
/*     */       
/* 230 */       arr = af.cargarSecuencia(area1);
/*     */     } 
/* 232 */     af.close();
/*     */     
/* 234 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 235 */     while (iterator.hasNext()) {
/* 236 */       AreasDTO area = (AreasDTO)iterator.next();
/* 237 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 238 */       op.setValue("" + area.getCodigo());
/* 239 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*     */       
/* 241 */       if (area1 == area.getCodigo()) {
/* 242 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 243 */         escogida.setValue("on");
/* 244 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 247 */       combo.appendChild(op);
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 261 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 262 */     Element enlace = this.pagHTML.createElement("a");
/* 263 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 264 */     enlace.appendChild(hijo);
/* 265 */     Attr donde = this.pagHTML.createAttribute("href");
/* 266 */     donde.setValue(vinculo);
/* 267 */     enlace.setAttributeNode(donde);
/* 268 */     td.appendChild(enlace);
/* 269 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 270 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMEstadisticaCerradas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */