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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.TASetTipo;
/*     */ import sinco.presentation.TASetTipoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TASetTipo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private TASetTipoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     int miArea = 0;
/*     */     try {
/*  44 */       miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     }
/*  46 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  50 */     int elPadre = -1;
/*     */     try {
/*  52 */       elPadre = Integer.parseInt(comms.request.getParameter("padre"));
/*     */     }
/*  54 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  58 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  59 */     SisUsuariosDTO p = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */     
/*  61 */     int servicio = 0;
/*     */     try {
/*  63 */       servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  65 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  68 */     int area = 0;
/*     */     try {
/*  70 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  72 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  75 */     String mensaje = comms.request.getParameter("mensaje");
/*     */     
/*  77 */     this.pagHTML = (TASetTipoHTML)comms.xmlcFactory.create(TASetTipoHTML.class);
/*     */     
/*  79 */     HTMLSelectElement combo = this.pagHTML.getElementAreaCliente();
/*  80 */     llenarAreaCliente(combo, idNav, miArea);
/*     */ 
/*     */ 
/*     */     
/*  84 */     if (elPadre != -1) {
/*  85 */       Element divPadre = this.pagHTML.getElementIdEncuesta();
/*  86 */       divPadre.getParentNode().removeChild(divPadre);
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (elPadre > 0) {
/*  91 */       this.pagHTML.getElementPadre().setValue("" + elPadre);
/*     */     }
/*     */     
/*  94 */     if (area > 0) {
/*  95 */       areasActual(area);
/*  96 */       serviciosArea(area, idNav, servicio);
/*     */     } else {
/*     */       
/*  99 */       areasFrecuentas(idNav);
/*     */     } 
/*     */     
/* 102 */     if (mensaje != null) {
/* 103 */       this.pagHTML.setTextMensaje(mensaje);
/*     */     } else {
/*     */       
/* 106 */       Element divPadre = this.pagHTML.getElementDivMensaje();
/* 107 */       divPadre.getParentNode().removeChild(divPadre);
/*     */     } 
/* 109 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 110 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private boolean llenarAreaCliente(HTMLSelectElement combo, int empleadoCliente, int miArea) {
/* 128 */     PersonasAreaDAO rs = new PersonasAreaDAO();
/* 129 */     if (!rs.getEstadoConexion()) {
/* 130 */       return false;
/*     */     }
/* 132 */     rs.cargarDePersona(empleadoCliente);
/* 133 */     PersonasAreaDTO reg = rs.next();
/* 134 */     while (reg != null) {
/* 135 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 136 */       op.setValue("" + reg.getCodigoArea());
/* 137 */       op.appendChild(this.pagHTML.createTextNode(reg.getNombreArea()));
/* 138 */       combo.appendChild(op);
/* 139 */       if (miArea == reg.getCodigoArea()) {
/* 140 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 141 */         escogida.setValue("on");
/* 142 */         op.setAttributeNode(escogida);
/*     */       } 
/* 144 */       reg = rs.next();
/*     */     } 
/* 146 */     rs.close();
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void areasFrecuentas(int idNav) {
/* 157 */     FechaDTO miFecha = new FechaDTO(Utilidades.fechaActual());
/* 158 */     miFecha.fechaMasDias(15L);
/* 159 */     String fechaH = miFecha.getFecha();
/* 160 */     miFecha.fechaMasDias((-15 - ParametrosDTO.getInt("numero.dias.retorno")));
/*     */ 
/*     */     
/* 163 */     HTMLSelectElement areas = this.pagHTML.getElementAreaBA();
/* 164 */     AreasDAO af = new AreasDAO();
/* 165 */     Collection<AreasDTO> arr = af.cargarFrecuentes(idNav, 0, miFecha.getFecha(), fechaH);
/*     */ 
/*     */     
/* 168 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 169 */     op.setValue("");
/* 170 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 171 */     areas.appendChild(op);
/*     */     
/* 173 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 174 */     while (iterator.hasNext()) {
/* 175 */       AreasDTO area = (AreasDTO)iterator.next();
/* 176 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 177 */       op.setValue("" + area.getCodigo());
/* 178 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/* 179 */       areas.appendChild(op);
/*     */     } 
/* 181 */     af.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void areasActual(int codigoArea) {
/* 191 */     HTMLSelectElement areas = this.pagHTML.getElementAreaBA();
/* 192 */     AreasDAO af = new AreasDAO();
/* 193 */     AreasDTO area = af.getArea(codigoArea);
/* 194 */     af.close();
/*     */ 
/*     */     
/* 197 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 198 */     op.setValue("");
/* 199 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 200 */     areas.appendChild(op);
/*     */     
/* 202 */     if (area != null) {
/* 203 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 204 */       op.setValue("" + area.getCodigo());
/* 205 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/* 206 */       areas.appendChild(op);
/* 207 */       Attr escogida = this.pagHTML.createAttribute("selected");
/* 208 */       escogida.setValue("on");
/* 209 */       op.setAttributeNode(escogida);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void serviciosArea(int area, int idNav, int defecto) {
/* 220 */     HTMLSelectElement servicios = this.pagHTML.getElementServicioBA();
/*     */     
/* 222 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 223 */     op.setValue("");
/* 224 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 225 */     servicios.appendChild(op);
/*     */     
/* 227 */     ServiciosDAO sf = new ServiciosDAO();
/* 228 */     Collection<ServiciosDTO> arr = sf.cargarTodosDeArea(area, idNav);
/* 229 */     sf.close();
/*     */     
/* 231 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 232 */     while (iterator.hasNext()) {
/*     */       
/* 234 */       ServiciosDTO reg = (ServiciosDTO)iterator.next();
/* 235 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 236 */       op.setValue("" + reg.getCodigo());
/* 237 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 239 */       if (reg.getCodigo() == defecto) {
/* 240 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 241 */         escogida.setValue("on");
/* 242 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */ 
/*     */       
/* 246 */       servicios.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\TASetTipo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */