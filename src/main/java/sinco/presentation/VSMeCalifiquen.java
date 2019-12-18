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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.AtencionSolicitudDTO;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.HistoriaSolicitudDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.SolicitudDTO;
/*     */ import sinco.business.TipoCalificacionDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.HistoriaSolicitudDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.data.TipoCalificacionDAO;
/*     */ import sinco.presentation.VSMeCalifiquen;
/*     */ import sinco.presentation.VerSolicitudNoCalificadaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class VSMeCalifiquen
/*     */   implements HttpPresentation
/*     */ {
/*     */   private VerSolicitudNoCalificadaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  47 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  50 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  53 */     SolicitudDAO sf = new SolicitudDAO();
/*  54 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  55 */     SolicitudDTO regSol = sf.getSolicitud(idsol);
/*  56 */     sf.close();
/*     */     
/*  58 */     ServiciosDAO serf = new ServiciosDAO();
/*  59 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/*  60 */     serf.close();
/*     */     
/*  62 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*  63 */     SisUsuariosDTO proveedor = perf.cargarRegistro(regSol.getEmpleadoProveedor());
/*  64 */     SisUsuariosDTO cliente = perf.cargarRegistro(regSol.getEmpleadoCliente());
/*     */     
/*  66 */     AreasDAO areaf = new AreasDAO();
/*  67 */     AreasDTO area = areaf.getArea(regSol.getAreaProveedor());
/*  68 */     AreasDTO areacliente = areaf.getArea(cliente.getArea());
/*  69 */     areaf.close();
/*     */     
/*  71 */     EstadoDAO estadof = new EstadoDAO();
/*  72 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/*  73 */     estadof.close();
/*     */ 
/*     */     
/*  76 */     this.pagHTML = (VerSolicitudNoCalificadaHTML)comms.xmlcFactory.create(VerSolicitudNoCalificadaHTML.class);
/*  77 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumero());
/*  78 */     this.pagHTML.setTextServicio(regServicio.getDescripcion());
/*  79 */     this.pagHTML.setTextAreap(area.getDescripcion());
/*  80 */     this.pagHTML.setTextAreacliente(areacliente.getDescripcion());
/*  81 */     this.pagHTML.setTextProveedor(proveedor.getApellidos() + ", " + proveedor.getNombres());
/*  82 */     this.pagHTML.setTextCliente(cliente.getApellidos() + ", " + cliente.getNombres());
/*  83 */     this.pagHTML.setTextFechagenerada(Utilidades.darFormatoFecha(regSol.getFechaGenerada()));
/*  84 */     this.pagHTML.setTextFechavigencia(Utilidades.darFormatoFecha(regSol.getFechaVigencia()));
/*  85 */     this.pagHTML.setTextFechaestimada(Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()));
/*  86 */     this.pagHTML.setTextEstado(estado.getDescripcion());
/*  87 */     this.pagHTML.setTextEscalamiento("" + regSol.getNivelEscalamiento());
/*     */ 
/*     */     
/*  90 */     if (!regSol.getCodigoOportunidad().equals("")) {
/*  91 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*  92 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(regSol.getCodigoOportunidad().charAt(0));
/*  93 */       this.pagHTML.setTextOportunidad("" + tipocali.getDescripcion());
/*  94 */       tcf.close();
/*     */     } 
/*     */     
/*  97 */     if (!regServicio.isCalificarServicio()) {
/*  98 */       this.pagHTML.setTextConfiabilidad("No aplica");
/*     */     }
/* 100 */     else if (!regSol.getCodigoConfiabilidad().equals("")) {
/* 101 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/* 102 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(regSol.getCodigoConfiabilidad().charAt(0));
/* 103 */       this.pagHTML.setTextConfiabilidad("" + tipocali.getDescripcion());
/* 104 */       tcf.close();
/*     */     } 
/*     */     
/* 107 */     HTMLInputElement idsolinput = this.pagHTML.getElementIdSolicitud();
/* 108 */     idsolinput.setValue("" + regSol.getNumero());
/*     */     
/* 110 */     idsolinput = this.pagHTML.getElementSolicitudatencion();
/* 111 */     idsolinput.setValue("" + regSol.getNumero());
/*     */     
/* 113 */     idsolinput = this.pagHTML.getElementPagina_siguienteAtencion();
/* 114 */     idsolinput.setValue("VSMeCalifiquen.po");
/*     */ 
/*     */     
/* 117 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/* 118 */     CaracteristicasDAO cf = new CaracteristicasDAO();
/* 119 */     Collection arr = cf.cargarTodosParaServicio(regServicio.getCodigo(), "T", "T");
/* 120 */     cf.close();
/*     */ 
/*     */     
/* 123 */     boolean hay = false;
/* 124 */     boolean fondo = true;
/*     */     
/* 126 */     Iterator iterator = arr.iterator();
/* 127 */     while (iterator.hasNext()) {
/* 128 */       CaracteristicasDTO car = (CaracteristicasDTO)iterator.next();
/* 129 */       hay = true;
/* 130 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 131 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/* 132 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 134 */       fondo = !fondo;
/* 135 */       eltr.setAttributeNode(newAttr("class", "ctd"));
/*     */       
/* 137 */       tdCaracteristica.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 138 */       tdDescripcion.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */ 
/*     */       
/* 141 */       tdCaracteristica.appendChild(this.pagHTML.createTextNode(car.getDescripcion()));
/*     */       
/* 143 */       DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 144 */       dsf.cargarParaSolicitud(regSol.getNumero(), car.getCodigo());
/* 145 */       DetalleSolicitudDTO ds = dsf.next();
/* 146 */       tdDescripcion.appendChild(this.pagHTML.createTextNode(""));
/* 147 */       while (ds != null) {
/* 148 */         tdDescripcion.appendChild(this.pagHTML.createTextNode(ds.getObservacion() + " "));
/* 149 */         ds = dsf.next();
/*     */       } 
/* 151 */       dsf.close();
/* 152 */       eltr.appendChild(tdCaracteristica);
/* 153 */       eltr.appendChild(tdDescripcion);
/* 154 */       tabla.appendChild(eltr);
/*     */     } 
/* 156 */     arr.clear();
/*     */ 
/*     */     
/* 159 */     if (!hay) {
/* 160 */       Element divcaracteristicas = this.pagHTML.getElementMostrarcaracteristicas();
/* 161 */       divcaracteristicas.getParentNode().removeChild(divcaracteristicas);
/*     */     } 
/*     */ 
/*     */     
/* 165 */     HTMLTableElement tablaat = this.pagHTML.getElementAtencion();
/* 166 */     AtencionSolicitudDAO af = new AtencionSolicitudDAO();
/* 167 */     af.cargarTodosParaSolicitud(regSol.getNumero());
/* 168 */     AtencionSolicitudDTO ate = af.next();
/* 169 */     hay = false;
/*     */     
/* 171 */     fondo = true;
/* 172 */     while (ate != null) {
/* 173 */       hay = true;
/* 174 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 175 */       HTMLElement tdFecha = (HTMLElement)this.pagHTML.createElement("td");
/* 176 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 178 */       fondo = !fondo;
/* 179 */       eltr.setAttributeNode(newAttr("class", "ctd"));
/* 180 */       tdFecha.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(ate.getFecha())));
/* 181 */       tdDescripcion.appendChild(this.pagHTML.createTextNode(ate.getObservacion()));
/*     */       
/* 183 */       tdFecha.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 184 */       tdDescripcion.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 186 */       ate = af.next();
/* 187 */       eltr.appendChild(tdFecha);
/* 188 */       eltr.appendChild(tdDescripcion);
/* 189 */       tablaat.appendChild(eltr);
/*     */     } 
/* 191 */     af.close();
/*     */     
/* 193 */     if (!hay) {
/* 194 */       tablaat.getParentNode().removeChild(tablaat);
/*     */     }
/*     */ 
/*     */     
/* 198 */     HTMLTableElement tablahistoria = this.pagHTML.getElementHistoria();
/*     */     
/* 200 */     HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
/* 201 */     hsf.cargarTodosParaSolicitud(regSol.getNumero());
/* 202 */     HistoriaSolicitudDTO hs = hsf.next();
/* 203 */     while (hs != null) {
/* 204 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 205 */       HTMLElement tdEstado = (HTMLElement)this.pagHTML.createElement("td");
/* 206 */       HTMLElement tdFecha = (HTMLElement)this.pagHTML.createElement("td");
/* 207 */       HTMLElement tdObservacion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 209 */       fondo = !fondo;
/* 210 */       eltr.setAttributeNode(newAttr("class", "ctd"));
/*     */       
/* 212 */       EstadoDAO ef = new EstadoDAO();
/* 213 */       EstadoDTO estadoh = ef.getEstado(hs.getEstadoFinal());
/* 214 */       ef.close();
/* 215 */       tdEstado.appendChild(this.pagHTML.createTextNode(estadoh.getDescripcion()));
/* 216 */       tdFecha.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(hs.getFecha())));
/* 217 */       tdObservacion.appendChild(this.pagHTML.createTextNode(hs.getObservaciones()));
/*     */       
/* 219 */       tdFecha.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 220 */       tdEstado.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 221 */       tdObservacion.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 223 */       eltr.appendChild(tdEstado);
/* 224 */       eltr.appendChild(tdFecha);
/* 225 */       eltr.appendChild(tdObservacion);
/*     */       
/* 227 */       tablahistoria.appendChild(eltr);
/* 228 */       hs = hsf.next();
/*     */     } 
/*     */     
/* 231 */     hsf.close();
/*     */     
/* 233 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 234 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 238 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 239 */     atrib.setValue(valor);
/* 240 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VSMeCalifiquen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */