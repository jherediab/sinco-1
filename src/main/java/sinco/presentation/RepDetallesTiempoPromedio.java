/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.LibreDAO;
/*     */ import sinco.presentation.RepDetallesTiempoPromedio;
/*     */ import sinco.presentation.RepDetallesTiempoPromedioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepDetallesTiempoPromedio
/*     */   implements HttpPresentation {
/*     */   private RepDetallesTiempoPromedioHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  29 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  31 */     int areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
/*  32 */     int areaCliente = Integer.parseInt(comms.request.getParameter("areaCliente"));
/*  33 */     String fechaInicial = comms.request.getParameter("fechaInicial");
/*  34 */     String fechaFinal = comms.request.getParameter("fechaFinal");
/*     */     
/*  36 */     if (!Utilidades.validarFormatoFecha(fechaInicial) || !Utilidades.validarFormatoFecha(fechaFinal))
/*     */     {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=Inicial y final"));
/*     */     }
/*     */     
/*  41 */     this.pagHTML = (RepDetallesTiempoPromedioHTML)comms.xmlcFactory.create(RepDetallesTiempoPromedioHTML.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     String s = "select solicitudes.tipo_solicitud,solicitudes.numero,servicios.descripcion,solicitudes.duracion,fecha_vigencia,fecha_real_terminacion,fecha_estimada_terminacion,dias_diff(fecha_vigencia,fecha_real_terminacion) as dias";
/*     */ 
/*     */     
/*  59 */     s = s + " from solicitudes,servicios ";
/*  60 */     s = s + " where servicios.codigo=solicitudes.codigo_servicio";
/*  61 */     s = s + " and solicitudes.fecha_real_terminacion IS NOT NULL";
/*  62 */     s = s + " and solicitudes.area_proveedor=" + areaProveedor;
/*  63 */     s = s + " and solicitudes.area_cliente=" + areaCliente;
/*  64 */     s = s + " and solicitudes.fecha_real_terminacion>=" + Utilidades.formatoFecha2(fechaInicial);
/*  65 */     s = s + " and solicitudes.fecha_real_terminacion<=" + Utilidades.formatoFecha2(fechaFinal);
/*  66 */     s = s + " order by servicios.descripcion";
/*     */     
/*  68 */     LibreDAO rsLibre = new LibreDAO();
/*  69 */     rsLibre.doExecute(s);
/*     */     
/*  71 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  72 */     boolean fondo = true;
/*  73 */     int cuantas = 0;
/*  74 */     while (rsLibre.next()) {
/*  75 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  76 */       HTMLElement tdNumero = (HTMLElement)this.pagHTML.createElement("td");
/*  77 */       HTMLElement tdServicio = (HTMLElement)this.pagHTML.createElement("td");
/*  78 */       HTMLElement tdDuracion = (HTMLElement)this.pagHTML.createElement("td");
/*  79 */       HTMLElement tdFechaVigencia = (HTMLElement)this.pagHTML.createElement("td");
/*  80 */       HTMLElement tdFechaEstimada = (HTMLElement)this.pagHTML.createElement("td");
/*  81 */       HTMLElement tdFechaTermina = (HTMLElement)this.pagHTML.createElement("td");
/*  82 */       HTMLElement tdDias = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/*  84 */       fondo = !fondo;
/*  85 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  87 */       int iNumero = rsLibre.getInt("numero");
/*  88 */       String sPagina = (rsLibre.getInt("tipo_solicitud") == 2) ? ("TAVSEnCurso.po?solicitud=" + iNumero) : ("VSEnCurso.po?solicitud=" + iNumero);
/*  89 */       tdNumero.appendChild(newhref("" + iNumero, sPagina));
/*     */       
/*  91 */       tdServicio.appendChild(this.pagHTML.createTextNode(rsLibre.getString("descripcion")));
/*  92 */       tdDuracion.appendChild(this.pagHTML.createTextNode(rsLibre.getString("duracion")));
/*  93 */       tdFechaVigencia.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(rsLibre.getString("fecha_vigencia"))));
/*  94 */       tdFechaTermina.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(rsLibre.getString("fecha_real_terminacion"))));
/*  95 */       tdFechaEstimada.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(rsLibre.getString("fecha_estimada_terminacion"))));
/*     */       
/*  97 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
/*  98 */         tdDias.appendChild(this.pagHTML.createTextNode(Utilidades.analizarEdad(rsLibre.getDouble("dias"))));
/*     */       } else {
/*     */         
/* 101 */         tdDias.appendChild(this.pagHTML.createTextNode(rsLibre.getString("dias")));
/*     */       } 
/*     */       
/* 104 */       eltr.appendChild(tdNumero);
/* 105 */       eltr.appendChild(tdServicio);
/* 106 */       eltr.appendChild(tdDuracion);
/* 107 */       eltr.appendChild(tdFechaVigencia);
/* 108 */       eltr.appendChild(tdFechaEstimada);
/* 109 */       eltr.appendChild(tdFechaTermina);
/* 110 */       eltr.appendChild(tdDias);
/* 111 */       hte.appendChild(eltr);
/* 112 */       cuantas++;
/*     */     } 
/* 114 */     rsLibre.close();
/* 115 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 116 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 117 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 121 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 122 */     atrib.setValue(valor);
/* 123 */     return atrib;
/*     */   }
/*     */   
/*     */   private Element newhref(String descripcion, String url) {
/* 127 */     Element enlace = this.pagHTML.createElement("a");
/* 128 */     Node hijo = this.pagHTML.createTextNode(descripcion);
/* 129 */     enlace.appendChild(hijo);
/* 130 */     Attr atrib = this.pagHTML.createAttribute("href");
/* 131 */     atrib.setValue(url);
/* 132 */     enlace.setAttributeNode(atrib);
/* 133 */     return enlace;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepDetallesTiempoPromedio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */