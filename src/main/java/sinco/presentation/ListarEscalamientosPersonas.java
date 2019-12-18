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
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.LibreDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.ListarEscalamientosPersonas;
/*     */ import sinco.presentation.SolicitudesEnEstadoHTML;
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
/*     */ 
/*     */ public class ListarEscalamientosPersonas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SolicitudesEnEstadoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  44 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  46 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  47 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  48 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*  49 */     int codigo_servicio = Integer.parseInt(comms.request.getParameter("codigo_servicio"));
/*  50 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*  51 */     int codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*  52 */     int titulo = Integer.parseInt(comms.request.getParameter("titulo"));
/*  53 */     int tipoAccion = 0;
/*     */     try {
/*  55 */       tipoAccion = Integer.parseInt(comms.request.getParameter("tipoAccion"));
/*     */     }
/*  57 */     catch (Exception e) {}
/*     */     
/*  59 */     String sFechaInicio = Utilidades.formato(anno, 4) + "-" + Utilidades.formato(mes1, 2) + "-01";
/*  60 */     String sFechaFinal = Utilidades.formato(anno, 4) + "-" + Utilidades.formato(mes2, 2) + "-01";
/*  61 */     sFechaFinal = Utilidades.fechaMas(sFechaFinal, 1, "mm");
/*     */     
/*  63 */     this.pagHTML = (SolicitudesEnEstadoHTML)comms.xmlcFactory.create(SolicitudesEnEstadoHTML.class);
/*  64 */     if (titulo == 1) {
/*  65 */       this.pagHTML.setTextTitulo("Solicitudes Escaladas de " + Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
/*  66 */     } else if (titulo == 4) {
/*  67 */       this.pagHTML.setTextTitulo("Solicitudes con no conformidad en percepción " + Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
/*  68 */     } else if (titulo == 2) {
/*  69 */       this.pagHTML.setTextTitulo("Acciones de mejora de " + Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
/*     */     }
/*  71 */     else if (titulo == 3) {
/*  72 */       this.pagHTML.setTextTitulo("Solicitudes Asociadas a una acción de mejora. " + Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
/*     */     } 
/*     */     
/*  75 */     Collection arr = new ArrayList();
/*     */     
/*  77 */     if (titulo == 1) {
/*  78 */       arr = crearSentencia(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*     */     }
/*  80 */     else if (titulo == 2) {
/*  81 */       arr = crearSentencia2(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*  82 */     } else if (titulo == 3) {
/*  83 */       arr = crearSentencia3(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado, tipoAccion);
/*     */     }
/*  85 */     else if (titulo == 4) {
/*  86 */       arr = crearSentencia4(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*     */     } 
/*     */     
/*  89 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/*  90 */     boolean fondo = true;
/*  91 */     int cuantos = 0;
/*  92 */     LibreDAO rsAux = new LibreDAO();
/*     */     
/*  94 */     Iterator iterator = arr.iterator();
/*  95 */     while (iterator.hasNext()) {
/*  96 */       VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
/*     */       
/*  98 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  99 */       fondo = !fondo;
/* 100 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 102 */       int numeroSol = reg.getNumero();
/* 103 */       eltr.appendChild(newtd("" + numeroSol, false, 0));
/* 104 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), "VSEnCurso.po?solicitud=" + numeroSol + "&lectura=1", 0));
/*     */       
/* 106 */       if (titulo == 2) {
/* 107 */         FechaDTO miFecha = new FechaDTO(reg.getFechaVigencia());
/* 108 */         miFecha.fechaMasDias(-30L);
/* 109 */         int cod_servicio = rsAux.getValorInt("select valor from detalles_solicitud WHERE numero_solicitud=" + numeroSol + " and codigo_caracteristica=" + ParametrosDTO.getInt("car.nombre.servicio.requiere.mejora"));
/* 110 */         int miTipoAccion = rsAux.getValorInt("select valor from detalles_solicitud WHERE numero_solicitud=" + numeroSol + " and codigo_caracteristica=" + ParametrosDTO.getInt("car.cuantos.servicio.requiere.mejora"));
/* 111 */         String sPagina = "ListarEscalamientosPersonas.po?titulo=3&anno=" + miFecha.getAnno() + "&mes1=" + miFecha.getMes() + "&mes2=" + miFecha.getMes() + "&area=" + area + "&codigoEmpleado=" + codigoEmpleado + "&codigo_servicio=" + cod_servicio + "&tipoAccion=" + miTipoAccion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 119 */         eltr.appendChild(newtdhref("" + reg.getNombreAreaProveedora(), sPagina, 0));
/*     */       } else {
/*     */         
/* 122 */         eltr.appendChild(newtd("" + reg.getNombreAreaProveedora(), false, 0));
/*     */       } 
/*     */       
/* 125 */       eltr.appendChild(newtd("" + reg.getNombreProveedor(), false, 0));
/* 126 */       eltr.appendChild(newtd("" + reg.getFechaVigencia(), false, 0));
/* 127 */       eltr.appendChild(newtd("" + reg.getNombreEstado(), false, 0));
/*     */       
/* 129 */       hte.appendChild(eltr);
/* 130 */       cuantos++;
/*     */     } 
/* 132 */     arr.clear();
/* 133 */     rsAux.close();
/* 134 */     this.pagHTML.setTextNroRegistros("" + cuantos);
/* 135 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 136 */     comms.response.writeDOM(this.pagHTML);
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
/* 147 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 148 */     atrib.setValue(valor);
/* 149 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido, boolean alinear, int colspan) {
/* 161 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 162 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 163 */     if (alinear) {
/* 164 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 166 */     if (colspan > 0) {
/* 167 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/*     */     
/* 170 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 171 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, int colspan) {
/* 182 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 183 */     if (colspan > 0) {
/* 184 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 186 */     Element enlace = this.pagHTML.createElement("a");
/* 187 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 188 */     enlace.appendChild(hijo);
/* 189 */     Attr donde = this.pagHTML.createAttribute("href");
/* 190 */     donde.setValue(vinculo);
/* 191 */     enlace.setAttributeNode(donde);
/* 192 */     td.appendChild(enlace);
/* 193 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 194 */     return td;
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
/*     */   private Collection crearSentencia(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 214 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 216 */     Collection arr = rs.solicitudesEscaladas(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     rs.close();
/* 224 */     return arr;
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
/*     */   private Collection crearSentencia2(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 244 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 246 */     Collection arr = rs.sentencia2(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     rs.close();
/* 254 */     return arr;
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
/*     */   private Collection crearSentencia3(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado, int tipoAccion) {
/* 279 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 281 */     Collection arr = rs.sentencia3(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado, tipoAccion);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     rs.close();
/* 290 */     return arr;
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
/*     */   private Collection crearSentencia4(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 310 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 312 */     Collection arr = rs.sentencia4(codigo_servicio, area, sFechaInicio, sFechaFinal, codigoEmpleado);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 319 */     rs.close();
/* 320 */     return arr;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ListarEscalamientosPersonas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */