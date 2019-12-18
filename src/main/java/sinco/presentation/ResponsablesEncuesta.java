/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.regex.Pattern;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.data.EncuestadosDAO;
/*     */ import sinco.data.LibreDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.ResponsablesEncuesta;
/*     */ import sinco.presentation.ResponsablesEncuestaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class ResponsablesEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ResponsablesEncuestaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  44 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  45 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  47 */     int miEncuesta = Integer.parseInt(comms.request.getParameter("encuesta"));
/*  48 */     String accion = comms.request.getParameter("accion");
/*     */     
/*  50 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  51 */     EncuestaDTO regEncuesta = rsEncuesta.cargaRegistro(miEncuesta);
/*  52 */     rsEncuesta.close();
/*     */     
/*  54 */     if (regEncuesta == null) {
/*  55 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=Encuestas"));
/*     */     }
/*     */     
/*  58 */     ServiciosDAO sf = new ServiciosDAO();
/*  59 */     ServiciosDTO regServicio = sf.cargarRegistro(regEncuesta.getCodigo_servicio());
/*  60 */     sf.close();
/*     */     
/*  62 */     if (regServicio == null) {
/*  63 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=Servicios"));
/*     */     }
/*     */     
/*  66 */     if (accion.equals("T")) {
/*  67 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/*  68 */       rsEncuestados.incluirTodos(miEncuesta, regEncuesta.getCodigo_servicio(), regServicio.getEspecializado(), elUsuario);
/*  69 */       rsEncuestados.close();
/*  70 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/*  71 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/*  73 */     if (accion.equals("J")) {
/*     */       
/*  75 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/*  76 */       rsEncuestados.jefesArea(miEncuesta, regEncuesta.getCodigo_servicio(), regServicio.getEspecializado(), elUsuario);
/*  77 */       rsEncuestados.close();
/*  78 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/*  79 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/*  81 */     if (accion.equals("CA")) {
/*  82 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/*  83 */       rsEncuestados.convocatoria(miEncuesta, elUsuario);
/*  84 */       rsEncuestados.close();
/*  85 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/*  86 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/*  88 */     if (accion.equals("SA")) {
/*  89 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/*  90 */       rsEncuestados.auditoresLideres(miEncuesta, regEncuesta.getCiclo(), elUsuario);
/*  91 */       rsEncuestados.close();
/*  92 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/*  93 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/*  95 */     if (accion.equals("B")) {
/*  96 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/*  97 */       Enumeration enumera = comms.request.getParameterNames();
/*     */       
/*  99 */       while (enumera.hasMoreElements()) {
/* 100 */         String param = (String)enumera.nextElement();
/* 101 */         if (!param.equals("encuesta") && !param.equals("accion") && !param.equals("clickcontrol")) {
/*     */           
/* 103 */           Pattern p = Pattern.compile("_");
/* 104 */           String[] items = p.split(param);
/*     */           
/* 106 */           int persona = Integer.parseInt(items[1]);
/* 107 */           int area = Integer.parseInt(items[2]);
/*     */           
/* 109 */           rsEncuestados.borrarPersona(miEncuesta, persona, area);
/*     */         } 
/*     */       } 
/* 112 */       rsEncuestados.close();
/* 113 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/* 116 */     if (accion.equals("IP")) {
/* 117 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/* 118 */       Enumeration enumera = comms.request.getParameterNames();
/*     */       
/* 120 */       while (enumera.hasMoreElements()) {
/* 121 */         String param = (String)enumera.nextElement();
/* 122 */         if (!param.equals("encuesta") && !param.equals("accion") && !param.equals("clickcontrol")) {
/*     */           
/* 124 */           Pattern p = Pattern.compile("_");
/* 125 */           String[] items = p.split(param);
/*     */           
/* 127 */           int persona = Integer.parseInt(items[1]);
/* 128 */           int area = Integer.parseInt(items[2]);
/*     */           
/* 130 */           rsEncuestados.incluirPersona(miEncuesta, persona, area, elUsuario);
/*     */         } 
/*     */       } 
/* 133 */       rsEncuestados.close();
/* 134 */       accion = "P";
/*     */     }
/* 136 */     else if (accion.equals("IA")) {
/*     */       
/* 138 */       int area = Integer.parseInt(comms.request.getParameter("area"));
/* 139 */       EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/* 140 */       rsEncuestados.incluirArea(miEncuesta, area, regEncuesta.getCodigo_servicio(), regServicio.getEspecializado(), elUsuario);
/* 141 */       rsEncuestados.close();
/* 142 */       String pagina = "LlenarEncuesta.po?encuesta=" + miEncuesta;
/* 143 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */     } 
/*     */ 
/*     */     
/* 147 */     this.pagHTML = (ResponsablesEncuestaHTML)comms.xmlcFactory.create(ResponsablesEncuestaHTML.class);
/*     */     
/* 149 */     this.pagHTML.setTextNumeroEncuesta("" + miEncuesta);
/* 150 */     this.pagHTML.setTextServicio("" + regEncuesta.getNombreServicio());
/* 151 */     this.pagHTML.setTextEstado("" + regEncuesta.getNombreEstado());
/*     */ 
/*     */     
/* 154 */     HTMLInputElement hie = this.pagHTML.getElementIdEncuestaPersona();
/* 155 */     hie.setValue("" + miEncuesta);
/*     */     
/* 157 */     hie = this.pagHTML.getElementIdEncuestaArea();
/* 158 */     hie.setValue("" + miEncuesta);
/*     */     
/* 160 */     hie = this.pagHTML.getElementIdEncuestaArea1();
/* 161 */     hie.setValue("" + miEncuesta);
/*     */     
/* 163 */     hie = this.pagHTML.getElementIdEncuestaPersonas();
/* 164 */     hie.setValue("" + miEncuesta);
/*     */     
/* 166 */     hie = this.pagHTML.getElementIdEncuestaVolver();
/* 167 */     hie.setValue("" + miEncuesta);
/*     */     
/* 169 */     if (accion.equals("A") || accion.equals("FI")) {
/* 170 */       HTMLSelectElement areas = this.pagHTML.getElementIdArea();
/* 171 */       AreasDAO rsArea = new AreasDAO();
/*     */       
/* 173 */       Collection arr = new ArrayList();
/* 174 */       if (accion.equals("FI")) {
/* 175 */         String filtro = comms.request.getParameter("filtro");
/* 176 */         arr = rsArea.cargarTodosParaServicioAbiertos(regEncuesta.getCodigo_servicio(), filtro);
/*     */       } else {
/*     */         
/* 179 */         arr = rsArea.cargarTodosParaServicioAbiertos(regEncuesta.getCodigo_servicio());
/*     */       } 
/* 181 */       rsArea.close();
/*     */       
/* 183 */       boolean hay = false;
/* 184 */       Iterator<AreasDTO> iterator = arr.iterator();
/* 185 */       while (iterator.hasNext()) {
/* 186 */         AreasDTO area = (AreasDTO)iterator.next();
/* 187 */         hay = true;
/* 188 */         HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 189 */         op.setValue("" + area.getCodigo());
/* 190 */         op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/* 191 */         areas.appendChild(op);
/*     */       } 
/* 193 */       if (!hay) {
/* 194 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoAreasDisponibles"));
/*     */       }
/*     */       
/* 197 */       Element divPadre = this.pagHTML.getElementDivPersonas();
/* 198 */       divPadre.getParentNode().removeChild(divPadre);
/*     */       
/* 200 */       divPadre = this.pagHTML.getElementDivPersonasI();
/* 201 */       divPadre.getParentNode().removeChild(divPadre);
/*     */     }
/* 203 */     else if (accion.equals("P")) {
/* 204 */       Element divPadre = this.pagHTML.getElementDivAreas();
/* 205 */       divPadre.getParentNode().removeChild(divPadre);
/*     */       
/* 207 */       divPadre = this.pagHTML.getElementDivFiltro();
/* 208 */       divPadre.getParentNode().removeChild(divPadre);
/*     */       
/* 210 */       String aproximacion = "";
/* 211 */       int recargar = 0;
/*     */       try {
/* 213 */         recargar = Integer.parseInt(comms.request.getParameter("recargar"));
/* 214 */         aproximacion = comms.request.getParameter("aproximacion");
/*     */       }
/* 216 */       catch (Exception e) {}
/*     */ 
/*     */       
/* 219 */       boolean hay = false;
/* 220 */       boolean fondo = true;
/* 221 */       if (recargar == 1) {
/* 222 */         LibreDAO rsPersonas = new LibreDAO();
/* 223 */         String s = "select p.codigo_empleado,p.apellidos,p.nombres,u.descripcion as nombre_area,pa.codigo_area from sis_usuarios p,sis_usuarios_area pa,unidades_dependencia u,servicios_area sa";
/*     */ 
/*     */         
/* 226 */         if (regServicio.getEspecializado().equals("M")) {
/* 227 */           s = s + ",proveedor_multiple pm";
/*     */         }
/*     */         
/* 230 */         s = s + " where pa.codigo_area=u.codigo" + " and p.codigo_empleado=pa.codigo_empleado" + " and sa.codigo_area=u.codigo" + " and sa.codigo_servicio=" + regEncuesta.getCodigo_servicio();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 235 */         if (regServicio.getEspecializado().equals("M")) {
/* 236 */           s = s + " and pm.codigo_area=u.codigo";
/* 237 */           s = s + " and pm.codigo_servicio=sa.codigo_servicio";
/* 238 */           s = s + " and pm.persona_cargo=p.codigo_empleado";
/*     */         } 
/*     */         
/* 241 */         if (regServicio.getEspecializado().equals("S")) {
/* 242 */           s = s + " and sa.persona_cargo=p.codigo_empleado";
/*     */         }
/*     */         
/* 245 */         s = s + " and p.estado='A'" + " and pa.area_principal='S'" + " and upper(p.apellidos) like upper('" + aproximacion + "%')";
/*     */ 
/*     */         
/* 248 */         s = s + " and p.codigo_empleado not in (select proveedor from encuestados where numero=" + miEncuesta + ")";
/*     */         
/* 250 */         rsPersonas.doExecute(s);
/* 251 */         HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 252 */         while (rsPersonas.next()) {
/* 253 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 254 */           HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */           
/* 256 */           fondo = !fondo;
/* 257 */           eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */           
/* 259 */           HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 260 */           checkbox.setAttribute("type", "checkbox");
/* 261 */           checkbox.setName("E_" + rsPersonas.getString("codigo_empleado") + "_" + rsPersonas.getString("codigo_area"));
/* 262 */           tdMarca.appendChild(checkbox);
/*     */           
/* 264 */           eltr.appendChild(tdMarca);
/* 265 */           eltr.appendChild(newtd("" + rsPersonas.getString("nombres"), false));
/* 266 */           eltr.appendChild(newtd("" + rsPersonas.getString("apellidos"), false));
/* 267 */           eltr.appendChild(newtd("" + rsPersonas.getString("nombre_area"), false));
/*     */           
/* 269 */           hte.appendChild(eltr);
/* 270 */           hay = true;
/*     */         } 
/* 272 */         rsPersonas.close();
/*     */       } 
/* 274 */       if (!hay) {
/* 275 */         Element idCondiciones = this.pagHTML.getElementDivPersonasI();
/* 276 */         idCondiciones.getParentNode().removeChild(idCondiciones);
/*     */       } 
/*     */     } 
/*     */     
/* 280 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 281 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 292 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 293 */     atrib.setValue(valor);
/* 294 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String leerDescripcion(CaracteristicasValorDAO rsTGen, int caract, int dato) {
/* 305 */     CaracteristicasValorDTO RegGeneral = rsTGen.cargarRegistro(caract, dato);
/* 306 */     if (RegGeneral != null) {
/* 307 */       return RegGeneral.getDescripcion();
/*     */     }
/* 309 */     return "No encontrado";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 318 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 319 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 320 */     if (alinear) {
/* 321 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 323 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 324 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ResponsablesEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */