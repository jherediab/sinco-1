/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.ElemAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.RepCar01F1;
/*     */ import sinco.presentation.RepCar01F1HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepCar01F1
/*     */   implements HttpPresentation {
/*     */   boolean fondo = true;
/*     */   private RepCar01F1HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  34 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  36 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/*  37 */     String fechaFin = comms.request.getParameter("fechaFin");
/*  38 */     int rol = Integer.parseInt(comms.request.getParameter("rol"));
/*  39 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*  40 */     int servicio = 0;
/*     */     try {
/*  42 */       servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  44 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  47 */     if (servicio == 0) {
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     }
/*     */     
/*  51 */     ServiciosDAO serf = new ServiciosDAO();
/*  52 */     ServiciosDTO regServicio = serf.cargarRegistro(servicio);
/*  53 */     serf.close();
/*     */     
/*  55 */     this.pagHTML = (RepCar01F1HTML)comms.xmlcFactory.create(RepCar01F1HTML.class);
/*     */     
/*  57 */     this.pagHTML.setTextElServicio("" + regServicio.getDescripcion());
/*     */     
/*  59 */     HTMLInputElement idFecha = this.pagHTML.getElementIdFechaInicio();
/*  60 */     idFecha.setValue("" + fechaInicio);
/*     */     
/*  62 */     idFecha = this.pagHTML.getElementIdFechaFin();
/*  63 */     idFecha.setValue("" + fechaFin);
/*     */     
/*  65 */     idFecha = this.pagHTML.getElementIdRol();
/*  66 */     idFecha.setValue("" + rol);
/*     */     
/*  68 */     idFecha = this.pagHTML.getElementIdArea();
/*  69 */     idFecha.setValue("" + area);
/*     */     
/*  71 */     idFecha = this.pagHTML.getElementIdServicio();
/*  72 */     idFecha.setValue("" + servicio);
/*     */ 
/*     */     
/*  75 */     Vector elementos = new Vector();
/*  76 */     agregaElemento(elementos, "numero", "Número", true);
/*  77 */     agregaElemento(elementos, "fecha_vigencia", "Fecha Vigencia", true);
/*  78 */     agregaElemento(elementos, "fecha_real_terminacion", "Fecha Terminación", true);
/*  79 */     agregaElemento(elementos, "nombreAreaProveedora", "Area Proveedora", true);
/*  80 */     agregaElemento(elementos, "nombreAreaCliente", "Area Cliente", true);
/*  81 */     agregaElemento(elementos, "nombreCliente", "Nombre Cliente", true);
/*  82 */     agregaElemento(elementos, "nombreProveedor", "Nombre Proveedor", true);
/*  83 */     agregaElemento(elementos, "codigo_oportunidad", "Oportunidad", true);
/*  84 */     agregaElemento(elementos, "codigo_confiabilidad", "Confiabilidad", true);
/*  85 */     agregaElemento(elementos, "tiempo_promedio", "tiempo Promedio", true);
/*  86 */     agregaElemento(elementos, "observaciones", "observaciones", true);
/*  87 */     agregaElemento(elementos, "atencion", "Atencion", true);
/*     */ 
/*     */ 
/*     */     
/*  91 */     HTMLTableElement laTabla = this.pagHTML.getElementCabecera();
/*  92 */     newTR(laTabla, elementos);
/*     */ 
/*     */     
/*  95 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/*     */     
/*  97 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  98 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(regServicio.getCodigo(), "F", "T");
/*  99 */     rsCaracteristicas.close();
/*     */     
/* 101 */     boolean hay = false;
/* 102 */     this.fondo = true;
/*     */     
/* 104 */     Iterator iterator = arr.iterator();
/* 105 */     while (iterator.hasNext()) {
/* 106 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/* 107 */       hay = true;
/* 108 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 109 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/* 110 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 112 */       this.fondo = !this.fondo;
/* 113 */       eltr.setAttributeNode(newAttr("class", "ct" + (this.fondo ? "1" : "2")));
/*     */       
/* 115 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 116 */       checkbox.setAttribute("type", "checkbox");
/* 117 */       checkbox.setName("_" + Utilidades.formato(regCar.getCodigo(), 5));
/* 118 */       tdCaracteristica.appendChild(checkbox);
/*     */       
/* 120 */       tdDescripcion.appendChild(this.pagHTML.createTextNode((regCar.getRol().equals("P") ? "P " : "  ") + ((regCar.getObliga() == true) ? "* " : "  ") + regCar.getDescripcion()));
/*     */       
/* 122 */       eltr.appendChild(tdDescripcion);
/* 123 */       eltr.appendChild(tdCaracteristica);
/* 124 */       tabla.appendChild(eltr);
/*     */     } 
/* 126 */     arr.clear();
/*     */     
/* 128 */     if (!hay) {
/* 129 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AreaSinServicios"));
/*     */     }
/* 131 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 132 */     comms.response.writeDOM(this.pagHTML);
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
/* 144 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 145 */     atrib.setValue(valor);
/* 146 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void agregaElemento(Vector elementos, String key, String contenido, boolean checked) {
/* 156 */     ElemAreaDTO ea = new ElemAreaDTO(key, contenido, checked);
/* 157 */     elementos.addElement(ea);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void newTR(HTMLTableElement laTabla, Vector vector) {
/* 167 */     int i = 1;
/* 168 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 169 */     while (i <= vector.size()) {
/* 170 */       ElemAreaDTO temp = (ElemAreaDTO)vector.elementAt(i - 1);
/* 171 */       HTMLElement eltd = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 173 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 174 */       checkbox.setAttribute("type", "checkbox");
/* 175 */       checkbox.setName("cab_" + temp.getCodigo());
/* 176 */       checkbox.setValue("1");
/* 177 */       checkbox.setChecked(temp.isChecked());
/* 178 */       eltd.appendChild(checkbox);
/* 179 */       eltd.appendChild(this.pagHTML.createTextNode(temp.getDescripcion()));
/*     */       
/* 181 */       eltr.appendChild(eltd);
/*     */       
/* 183 */       if (i % 6 == 0 && i > 0) {
/* 184 */         this.fondo = !this.fondo;
/* 185 */         eltr.setAttributeNode(newAttr("class", "ct" + (this.fondo ? "1" : "2")));
/* 186 */         laTabla.appendChild(eltr);
/* 187 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       } 
/*     */       
/* 190 */       i++;
/*     */     } 
/* 192 */     laTabla.appendChild(eltr);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepCar01F1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */