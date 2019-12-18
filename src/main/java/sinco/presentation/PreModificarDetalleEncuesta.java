/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DetalleEncuestaDAO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.presentation.PreModificarDetalleEncuesta;
/*     */ import sinco.presentation.PreModificarDetalleEncuestaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class PreModificarDetalleEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PreModificarDetalleEncuestaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  40 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  41 */     int miEncuesta = Integer.parseInt(comms.request.getParameter("encuesta"));
/*  42 */     EncuestaDTO regEncuesta = rsEncuesta.cargaRegistro(miEncuesta);
/*  43 */     rsEncuesta.close();
/*     */     
/*  45 */     ServiciosDAO serf = new ServiciosDAO();
/*  46 */     ServiciosDTO servicio = serf.cargarRegistro(regEncuesta.getCodigo_servicio());
/*  47 */     serf.close();
/*     */     
/*  49 */     this.pagHTML = (PreModificarDetalleEncuestaHTML)comms.xmlcFactory.create(PreModificarDetalleEncuestaHTML.class);
/*  50 */     this.pagHTML.setTextNumerosolicitud("" + regEncuesta.getNumero());
/*  51 */     this.pagHTML.setTextServicio(servicio.getDescripcion());
/*     */ 
/*     */ 
/*     */     
/*  55 */     HTMLInputElement hies = this.pagHTML.getElementIdSolDetalle();
/*  56 */     hies.setValue("" + miEncuesta);
/*     */     
/*  58 */     String pagina = comms.request.getParameter("pagina_siguiente");
/*     */     
/*  60 */     hies = this.pagHTML.getElementPagina_siguiente();
/*  61 */     hies.setValue(pagina);
/*     */     
/*  63 */     boolean haycaracteristicas = false;
/*     */     
/*  65 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  67 */     HTMLTableElement tabla = this.pagHTML.getElementIdtblDetalle();
/*  68 */     DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/*  69 */     dsf.cargarParaSolicitud(regEncuesta.getNumero());
/*  70 */     DetalleSolicitudDTO ds = dsf.next();
/*     */     
/*  72 */     CaracteristicasDAO cf = new CaracteristicasDAO();
/*  73 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*     */ 
/*     */     
/*  76 */     boolean fondo = true;
/*  77 */     while (ds != null) {
/*     */       HTMLSelectElement combo; HTMLInputElement inp;
/*  79 */       CaracteristicasDTO car = cf.cargarRegistro(servicio.getCodigo(), ds.getCodigoCaracteristica(), "C");
/*     */       
/*  81 */       if (car == null) {
/*  82 */         ds = dsf.next();
/*     */         
/*     */         continue;
/*     */       } 
/*  86 */       haycaracteristicas = true;
/*  87 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  88 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/*  89 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/*  91 */       fondo = !fondo;
/*  92 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */ 
/*     */       
/*  95 */       tdCaracteristica.appendChild(this.pagHTML.createTextNode(car.getDescripcion()));
/*     */       
/*  97 */       switch (Integer.parseInt(car.getTipo())) {
/*     */         case 1:
/*     */         case 3:
/*     */         case 4:
/* 101 */           inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 102 */           inp.setMaxLength(200);
/* 103 */           inp.setSize("70");
/* 104 */           inp.setName("" + car.getCodigo() + "_" + ds.getConsecutivoDetalle());
/* 105 */           inp.setValue(ds.getObservacion());
/* 106 */           inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 107 */           inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 108 */           switch (Integer.parseInt(car.getTipo())) {
/*     */             case 1:
/* 110 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*     */               break;
/*     */             case 3:
/* 113 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/*     */               break;
/*     */             case 4:
/* 116 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*     */               break;
/*     */           } 
/* 119 */           tdDescripcion.appendChild(inp);
/*     */           break;
/*     */         case 2:
/* 122 */           combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 123 */           combo.setName("" + car.getCodigo() + "_" + ds.getConsecutivoDetalle());
/* 124 */           llenarCombo(rsTGen, combo, car.getCodigo(), ds.getValor());
/* 125 */           combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 126 */           combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 127 */           tdDescripcion.appendChild(combo);
/*     */           break;
/*     */       } 
/* 130 */       eltr.appendChild(tdCaracteristica);
/* 131 */       eltr.appendChild(tdDescripcion);
/* 132 */       tabla.appendChild(eltr);
/* 133 */       ds = dsf.next();
/*     */     } 
/* 135 */     dsf.close();
/* 136 */     cf.close();
/* 137 */     rsTGen.close();
/*     */     
/* 139 */     if (!haycaracteristicas) {
/* 140 */       Element ce = this.pagHTML.getElementDetalles();
/* 141 */       ce.getParentNode().removeChild(ce);
/*     */     } 
/* 143 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 144 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 154 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 155 */     atrib.setValue(valor);
/* 156 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarCombo(CaracteristicasValorDAO rsTGen, HTMLSelectElement combo, int caract, int defecto) {
/* 167 */     rsTGen.cargarTodos(caract);
/*     */     CaracteristicasValorDTO RegGeneral;
/* 169 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 170 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 171 */       op.setValue("" + RegGeneral.getValor());
/* 172 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 173 */       if (defecto == RegGeneral.getValor()) {
/* 174 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 175 */         escogida.setValue("on");
/* 176 */         op.setAttributeNode(escogida);
/*     */       } 
/* 178 */       combo.appendChild(op);
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
/*     */   private String leerDescripcion(CaracteristicasValorDAO rsTGen, int caract, int dato) {
/* 190 */     CaracteristicasValorDTO RegGeneral = rsTGen.cargarRegistro(caract, dato);
/* 191 */     if (RegGeneral != null) {
/* 192 */       return RegGeneral.getDescripcion();
/*     */     }
/* 194 */     return "No encontrado";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreModificarDetalleEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */