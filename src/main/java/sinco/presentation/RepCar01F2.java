/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.RepCar01F2;
/*     */ import sinco.presentation.RepCar01F2HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepCar01F2 implements HttpPresentation {
/*     */   private RepCar01F2HTML pagHTML;
/*  27 */   int iTitulos = 0;
/*     */ 
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  37 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  38 */     String fechaFin = comms.request.getParameter("fechaFin");
/*  39 */     int rol = Integer.parseInt(comms.request.getParameter("rol"));
/*  40 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*  41 */     int servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */ 
/*     */     
/*  44 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/*  46 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  47 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(servicio, "F", "T");
/*  48 */     rsCaracteristicas.close();
/*     */ 
/*     */     
/*  51 */     Vector cabecera = new Vector();
/*     */ 
/*     */     
/*  54 */     while (enumera.hasMoreElements()) {
/*  55 */       String param = (String)enumera.nextElement();
/*  56 */       if (!param.equals("area") && !param.equals("servicio") && !param.equals("fechaInicio") && !param.equals("fechaFin") && !param.equals("rol") && !param.equals("clickcontrol") && !param.equals("clickcontrol2") && !param.equals("miBoton"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  66 */         if (param.substring(0, 4).equals("cab_")) {
/*  67 */           cabecera.addElement(param.substring(4, param.length()));
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/*  72 */     Vector cualesProceso = new Vector();
/*  73 */     Iterator iterator = arr.iterator();
/*  74 */     while (iterator.hasNext()) {
/*  75 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*     */       
/*  77 */       String existe = comms.request.getParameter("_" + Utilidades.formato(regCar.getCodigo(), 5));
/*  78 */       if (existe != null) {
/*  79 */         cualesProceso.add(regCar);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.pagHTML = (RepCar01F2HTML)comms.xmlcFactory.create(RepCar01F2HTML.class);
/*  86 */     HTMLElement elDiv = this.pagHTML.getElementLaTabla();
/*  87 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/*  88 */     laTabla.setAttributeNode(newAttr("class", "fprofb"));
/*  89 */     laTabla.setAttributeNode(newAttr("align", "center"));
/*     */ 
/*     */ 
/*     */     
/*  93 */     HTMLElement eltrTitulos = (HTMLElement)this.pagHTML.createElement("tr");
/*     */     
/*  95 */     if (cabecera.indexOf("numero") >= 0) {
/*  96 */       eltrTitulos.appendChild(newTDTitulo("Número"));
/*     */     }
/*     */     
/*  99 */     if (cabecera.indexOf("nombreAreaProveedora") >= 0) {
/* 100 */       eltrTitulos.appendChild(newTDTitulo("Area Proveedora"));
/*     */     }
/*     */     
/* 103 */     if (cabecera.indexOf("nombreProveedor") >= 0) {
/* 104 */       eltrTitulos.appendChild(newTDTitulo("Nombre Proveedor"));
/*     */     }
/*     */     
/* 107 */     if (cabecera.indexOf("fecha_vigencia") >= 0) {
/* 108 */       eltrTitulos.appendChild(newTDTitulo("Fecha Vigencia"));
/*     */     }
/*     */     
/* 111 */     if (cabecera.indexOf("fecha_real_terminacion") >= 0) {
/* 112 */       eltrTitulos.appendChild(newTDTitulo("Fecha Terminación"));
/*     */     }
/*     */     
/* 115 */     if (cabecera.indexOf("codigo_oportunidad") >= 0) {
/* 116 */       eltrTitulos.appendChild(newTDTitulo("Oport."));
/*     */     }
/*     */     
/* 119 */     if (cabecera.indexOf("codigo_confiabilidad") >= 0) {
/* 120 */       eltrTitulos.appendChild(newTDTitulo("Conf."));
/*     */     }
/*     */     
/* 123 */     if (cabecera.indexOf("nombreAreaCliente") >= 0) {
/* 124 */       eltrTitulos.appendChild(newTDTitulo("Area cliente"));
/*     */     }
/*     */     
/* 127 */     if (cabecera.indexOf("nombreCliente") >= 0) {
/* 128 */       eltrTitulos.appendChild(newTDTitulo("Nombre cliente"));
/*     */     }
/*     */     
/* 131 */     if (cabecera.indexOf("tiempo_promedio") >= 0) {
/* 132 */       eltrTitulos.appendChild(newTDTitulo("T Promedio."));
/*     */     }
/*     */     
/* 135 */     if (cabecera.indexOf("observaciones") >= 0) {
/* 136 */       eltrTitulos.appendChild(newTDTitulo("Observaciones Solicitud"));
/*     */     }
/* 138 */     if (cabecera.indexOf("atencion") >= 0) {
/* 139 */       eltrTitulos.appendChild(newTDTitulo("Atencion Solicitud"));
/*     */     }
/*     */ 
/*     */     
/* 143 */     laTabla.appendChild(eltrTitulos);
/*     */     
/* 145 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*     */     
/* 147 */     Collection arrDatos = rs.repCar01F2(servicio, rol, fechaInicio, fechaFin, area, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     rs.close();
/*     */ 
/*     */     
/* 158 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*     */     
/* 160 */     boolean fondo = true;
/* 161 */     int entradas = 0;
/* 162 */     float confiabiliad = 0.0F;
/* 163 */     float oportunidad = 0.0F;
/*     */ 
/*     */     
/* 166 */     Iterator iter = arrDatos.iterator();
/* 167 */     while (iter.hasNext()) {
/* 168 */       VSolicitudesDTO reg = (VSolicitudesDTO)iter.next();
/*     */       
/* 170 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 172 */       fondo = !fondo;
/* 173 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 175 */       entradas++;
/*     */       
/* 177 */       int numero = reg.getNumero();
/*     */       
/* 179 */       if (cabecera.indexOf("numero") >= 0) {
/* 180 */         eltr.appendChild(newTD("" + reg.getNumero()));
/*     */       }
/*     */       
/* 183 */       if (cabecera.indexOf("nombreAreaProveedora") >= 0) {
/* 184 */         eltr.appendChild(newTD(reg.getNombreAreaProveedora()));
/*     */       }
/*     */       
/* 187 */       if (cabecera.indexOf("nombreProveedor") >= 0) {
/* 188 */         eltr.appendChild(newTD(reg.getNombreProveedor()));
/*     */       }
/* 190 */       if (cabecera.indexOf("fecha_vigencia") >= 0) {
/* 191 */         eltr.appendChild(newTD(Utilidades.darFormatoFecha(reg.getFechaVigencia())));
/*     */       }
/* 193 */       if (cabecera.indexOf("fecha_real_terminacion") >= 0) {
/* 194 */         eltr.appendChild(newTD(Utilidades.darFormatoFecha(reg.getFechaRealTerminacion())));
/*     */       }
/* 196 */       if (cabecera.indexOf("codigo_oportunidad") >= 0) {
/* 197 */         float miOportunidad = Utilidades.porcentajeSatisfaccion(reg.getCodigoOportunidad());
/* 198 */         oportunidad += miOportunidad;
/* 199 */         eltr.appendChild(newTD("" + miOportunidad));
/*     */       } 
/* 201 */       if (cabecera.indexOf("codigo_confiabilidad") >= 0) {
/* 202 */         float miConfiabilidad = Utilidades.porcentajeSatisfaccion(reg.getCodigoConfiabilidad());
/* 203 */         confiabiliad += miConfiabilidad;
/* 204 */         eltr.appendChild(newTD("" + miConfiabilidad));
/*     */       } 
/* 206 */       if (cabecera.indexOf("nombreAreaCliente") >= 0) {
/* 207 */         eltr.appendChild(newTD(reg.getNombreAreaCliente()));
/*     */       }
/*     */       
/* 210 */       if (cabecera.indexOf("nombreCliente") >= 0) {
/* 211 */         eltr.appendChild(newTD(reg.getNombreCliente()));
/*     */       }
/*     */       
/* 214 */       if (cabecera.indexOf("tiempo_promedio") >= 0) {
/* 215 */         eltr.appendChild(newTD(reg.getTiempoPromedio()));
/*     */       }
/*     */       
/* 218 */       if (cabecera.indexOf("observaciones") >= 0) {
/* 219 */         eltr.appendChild(newTD(reg.getObservaciones()));
/*     */       }
/* 221 */       if (cabecera.indexOf("atencion") >= 0) {
/* 222 */         eltr.appendChild(newTD(reg.getAtencion()));
/*     */       }
/*     */       
/* 225 */       int i = 0;
/* 226 */       while (i < cualesProceso.size()) {
/* 227 */         CaracteristicasDTO regCar = (CaracteristicasDTO)cualesProceso.elementAt(i);
/* 228 */         dsf.cargarParaSolicitud(numero, regCar.getCodigo());
/* 229 */         DetalleSolicitudDTO regDes = dsf.next();
/* 230 */         String respuesta = "";
/* 231 */         while (regDes != null) {
/* 232 */           respuesta = respuesta + regDes.getObservacion();
/* 233 */           regDes = dsf.next();
/*     */         } 
/* 235 */         eltr.appendChild(newTD("" + respuesta));
/* 236 */         i++;
/*     */       } 
/*     */       
/* 239 */       laTabla.appendChild(eltr);
/*     */     } 
/* 241 */     dsf.close();
/* 242 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */     
/* 244 */     eltr.appendChild(newTD("Totales..."));
/* 245 */     eltr.appendChild(newTD("Oportunidad="));
/* 246 */     eltr.appendChild(newTD("" + (oportunidad / entradas)));
/* 247 */     eltr.appendChild(newTD("Confiabilidad="));
/* 248 */     eltr.appendChild(newTD("" + (confiabiliad / entradas)));
/* 249 */     eltr.appendChild(newTD("Numero registros="));
/* 250 */     eltr.appendChild(newTD("" + entradas));
/* 251 */     laTabla.appendChild(eltr);
/* 252 */     elDiv.appendChild(laTabla);
/*     */     
/* 254 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 255 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 265 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 266 */     atrib.setValue(valor);
/* 267 */     return atrib;
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
/*     */   private HTMLElement newTDTitulo(String contenido) {
/* 279 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 280 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 281 */     if (this.iTitulos % 2 == 0) {
/* 282 */       td.setAttributeNode(newAttr("class", "cf1"));
/*     */     } else {
/*     */       
/* 285 */       td.setAttributeNode(newAttr("class", "cf2"));
/*     */     } 
/* 287 */     this.iTitulos++;
/* 288 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newTD(String contenido) {
/* 296 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 297 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 298 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepCar01F2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */