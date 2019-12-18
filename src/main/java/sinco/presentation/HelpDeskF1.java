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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.HelpDeskF1;
/*     */ import sinco.presentation.HelpDeskF1HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HelpDeskF1
/*     */   implements HttpPresentation
/*     */ {
/*     */   private HelpDeskF1HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  36 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  41 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  42 */     SisUsuariosDTO p = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */     
/*  44 */     int tipoSol = 1;
/*  45 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  47 */     int _operacion = 0;
/*  48 */     String filtro = "";
/*     */     try {
/*  50 */       _operacion = Integer.parseInt(comms.request.getParameter("_operacion"));
/*  51 */       if (_operacion == 1) {
/*  52 */         filtro = comms.request.getParameter("filtro");
/*     */       }
/*     */     }
/*  55 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  59 */     int elServicio = 0;
/*  60 */     int elArea = 0;
/*  61 */     int laPersona = 0;
/*  62 */     if (_operacion == 2) {
/*     */       try {
/*  64 */         elServicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */       }
/*  66 */       catch (Exception e) {}
/*     */       
/*     */       try {
/*  69 */         elArea = Integer.parseInt(comms.request.getParameter("area"));
/*     */       }
/*  71 */       catch (Exception e) {}
/*     */       
/*     */       try {
/*  74 */         laPersona = Integer.parseInt(comms.request.getParameter("persona"));
/*     */       }
/*  76 */       catch (Exception e) {}
/*     */     } 
/*     */ 
/*     */     
/*  80 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/*     */     
/*  82 */     AreasDAO af = new AreasDAO();
/*  83 */     this.pagHTML = (HelpDeskF1HTML)comms.xmlcFactory.create(HelpDeskF1HTML.class);
/*     */     
/*  85 */     boolean rta = false;
/*  86 */     if (_operacion == 1) {
/*  87 */       arr = af.cargarTodosAbiertos(filtro, elServicio);
/*     */     }
/*  89 */     else if (_operacion == 0) {
/*  90 */       FechaDTO miFecha = new FechaDTO(Utilidades.fechaActual());
/*  91 */       miFecha.fechaMasDias(15L);
/*  92 */       String fechaH = miFecha.getFecha();
/*  93 */       miFecha.fechaMasDias((-15 - ParametrosDTO.getInt("numero.dias.retorno")));
/*  94 */       arr = af.cargarFrecuentesP(idNav, elServicio, miFecha.getFecha(), fechaH);
/*     */     } 
/*  96 */     if (_operacion == 2) {
/*  97 */       arr = af.cargarArea(elArea);
/*     */     }
/*  99 */     af.close();
/*     */     
/* 101 */     if (!rta) {
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=areas"));
/*     */     }
/*     */     
/* 105 */     HTMLSelectElement areas = this.pagHTML.getElementIdArea();
/*     */     
/* 107 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 108 */     op.setValue("0");
/* 109 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 110 */     areas.appendChild(op);
/*     */     
/* 112 */     boolean hay = false;
/* 113 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 114 */     while (iterator.hasNext()) {
/* 115 */       AreasDTO area = (AreasDTO)iterator.next();
/* 116 */       hay = true;
/* 117 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 118 */       op.setValue("" + area.getCodigo());
/* 119 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/* 120 */       areas.appendChild(op);
/* 121 */       if (elArea != 0) {
/* 122 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 123 */         escogida.setValue("on");
/* 124 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/* 127 */     if (!hay) {
/* 128 */       Element division = this.pagHTML.getElementSeleccion();
/* 129 */       division.getParentNode().removeChild(division);
/*     */     } 
/*     */     
/* 132 */     if (elArea != 0) {
/* 133 */       HTMLSelectElement combo = this.pagHTML.getElementIdPersona();
/* 134 */       cargarPersonasDeArea(combo, elArea, 0);
/*     */     } else {
/*     */       
/* 137 */       Element division = this.pagHTML.getElementFuncionario();
/* 138 */       division.getParentNode().removeChild(division);
/*     */     } 
/* 140 */     if (elArea != 0) {
/* 141 */       HTMLSelectElement combo = this.pagHTML.getElementIdServicio();
/* 142 */       cargarMisServicios(combo, p.getArea(), p.getCodigoEmpleado(), 0);
/*     */     } else {
/*     */       
/* 145 */       Element division = this.pagHTML.getElementTrServicio();
/* 146 */       division.getParentNode().removeChild(division);
/*     */     } 
/*     */     
/* 149 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 150 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private int cargarPersonasDeArea(HTMLSelectElement combo, int elArea, int defecto) {
/* 162 */     SisUsuariosDAO rsPersonas = new SisUsuariosDAO();
/* 163 */     Collection arr = rsPersonas.cargarActivoDeArea(elArea);
/* 164 */     int cuantas = 0;
/* 165 */     Iterator iterator = arr.iterator();
/* 166 */     while (iterator.hasNext()) {
/* 167 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/* 168 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 169 */       op.setValue("" + regPersona.getCodigoEmpleado());
/* 170 */       op.appendChild(this.pagHTML.createTextNode(regPersona.getNombre()));
/* 171 */       combo.appendChild(op);
/* 172 */       if (defecto != 0) {
/* 173 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 174 */         escogida.setValue("on");
/* 175 */         op.setAttributeNode(escogida);
/*     */       } 
/* 177 */       cuantas++;
/*     */     } 
/* 179 */     return cuantas;
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
/*     */   private int cargarMisServicios(HTMLSelectElement combo, int elArea, int funcionario, int defecto) {
/* 191 */     ServiciosDAO rsServicio = new ServiciosDAO();
/* 192 */     Collection<ServiciosDTO> arr = rsServicio.cargarServiciosDeFuncionario(elArea, funcionario);
/* 193 */     rsServicio.close();
/*     */     
/* 195 */     int cuantas = 0;
/* 196 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 197 */     while (iterator.hasNext()) {
/* 198 */       ServiciosDTO regServicio = (ServiciosDTO)iterator.next();
/* 199 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 200 */       op.setValue("" + regServicio.getCodigo());
/* 201 */       op.appendChild(this.pagHTML.createTextNode(regServicio.getDescripcion()));
/* 202 */       combo.appendChild(op);
/* 203 */       if (defecto != 0) {
/* 204 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 205 */         escogida.setValue("on");
/* 206 */         op.setAttributeNode(escogida);
/*     */       } 
/* 208 */       cuantas++;
/*     */     } 
/* 210 */     return cuantas;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\HelpDeskF1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */