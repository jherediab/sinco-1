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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AMPreEstadistica;
/*     */ import sinco.presentation.AMPreEstadisticaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Utilidades2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMPreEstadistica
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMPreEstadisticaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  40 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  44 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  45 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  47 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  48 */     SisUsuariosDTO p = pf.cargarRegistro(idNav);
/*     */     
/*  50 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  51 */     if (!rsSeguridad.getEstadoConexion()) {
/*  52 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  54 */     boolean bAccionesAreas = rsSeguridad.tieneLlave(miGrupo, "oAccionesAreas");
/*  55 */     rsSeguridad.close();
/*     */     
/*  57 */     this.pagHTML = (AMPreEstadisticaHTML)comms.xmlcFactory.create(AMPreEstadisticaHTML.class);
/*     */     
/*  59 */     HTMLInputElement idFecha = this.pagHTML.getElementFechaDesde();
/*  60 */     idFecha.setValue("" + Utilidades.getAnnoActual() + "-01-01");
/*     */     
/*  62 */     idFecha = this.pagHTML.getElementFechaHasta();
/*  63 */     idFecha.setValue("" + Utilidades.darFormatoFecha(Utilidades2.diaSiguiente()));
/*     */     
/*  65 */     HTMLSelectElement combo = this.pagHTML.getElementIdAreaImplanta();
/*  66 */     comboAreas(combo, p.getArea(), bAccionesAreas);
/*     */     
/*  68 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  69 */     if (!rsTGen.getEstadoConexion()) {
/*  70 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */ 
/*     */     
/*  74 */     combo = this.pagHTML.getElementIdOrigen();
/*  75 */     llenarCombo(rsTGen, combo, "am_origen", "codigo", "descripcion", "1=1");
/*     */     
/*  77 */     combo = this.pagHTML.getElementIdProceso();
/*  78 */     llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", "codigo<>'00'");
/*  79 */     rsTGen.close();
/*     */     
/*  81 */     combo = this.pagHTML.getElementIdAccion();
/*  82 */     comboMultivalores(combo, "AM_TIPO_ACCION", "", true);
/*     */     
/*  84 */     combo = this.pagHTML.getElementIdNorma();
/*  85 */     comboMultivalores(combo, "AM_NORMAS", "", true);
/*     */     
/*  87 */     combo = this.pagHTML.getElementIdCodigo_estado();
/*  88 */     comboMultivalores(combo, "AM_ESTADO_ACCION", "", true);
/*     */ 
/*     */     
/*  91 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  92 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void comboAreas(HTMLSelectElement combo, int area1, boolean bAccionesAreas) {
/* 104 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/* 105 */     AreasDAO af = new AreasDAO();
/* 106 */     if (bAccionesAreas) {
/* 107 */       arr = af.cargarTodos();
/*     */     } else {
/*     */       
/* 110 */       arr = af.cargarSecuencia(area1);
/*     */     } 
/*     */     
/* 113 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 114 */     while (iterator.hasNext()) {
/* 115 */       AreasDTO area = (AreasDTO)iterator.next();
/* 116 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 117 */       op.setValue("" + area.getCodigo());
/* 118 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*     */       
/* 120 */       if (area1 == area.getCodigo()) {
/* 121 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 122 */         escogida.setValue("on");
/* 123 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 126 */       combo.appendChild(op);
/*     */     } 
/* 128 */     af.close();
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion) {
/* 147 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 148 */     op.setValue("");
/* 149 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 150 */     combo.appendChild(op);
/*     */     
/* 152 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion + " order by " + descripcion);
/*     */     TGeneralDTO regGeneral;
/* 154 */     while ((regGeneral = rsTGen.next()) != null) {
/* 155 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 156 */       op.setValue("" + regGeneral.getCodigo());
/* 157 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 158 */       combo.appendChild(op);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 177 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 178 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 179 */     rs.close();
/* 180 */     if (dejarBlanco) {
/* 181 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 182 */       op.setValue("");
/* 183 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 184 */       combo.appendChild(op);
/*     */     } 
/* 186 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 187 */     while (iterator.hasNext()) {
/* 188 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 189 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 190 */       op.setValue("" + reg.getCodigo());
/* 191 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 192 */       if (defecto.equals(reg.getCodigo())) {
/* 193 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 194 */         escogida.setValue("on");
/* 195 */         op.setAttributeNode(escogida);
/*     */       } 
/* 197 */       combo.appendChild(op);
/*     */     } 
/* 199 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMPreEstadistica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */