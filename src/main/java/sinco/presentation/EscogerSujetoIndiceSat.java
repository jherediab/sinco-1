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
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.EscogerSujetoIndiceSat;
/*     */ import sinco.presentation.EscogerSujetoIndiceSatHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class EscogerSujetoIndiceSat
/*     */   implements HttpPresentation
/*     */ {
/*     */   private int idNav;
/*     */   private EscogerSujetoIndiceSatHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  34 */     this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  35 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  36 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */     
/*  38 */     int sujeto = 0;
/*     */     try {
/*  40 */       sujeto = Integer.parseInt(comms.request.getParameter("sujeto"));
/*     */     }
/*  42 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  45 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/*  46 */     SisUsuariosDTO p = rsPersona.cargarRegistro(this.idNav);
/*     */     
/*  48 */     this.pagHTML = (EscogerSujetoIndiceSatHTML)comms.xmlcFactory.create(EscogerSujetoIndiceSatHTML.class);
/*     */     
/*  50 */     HTMLSelectElement combo = this.pagHTML.getElementIdSujeto();
/*     */     
/*  52 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  53 */     if (!rsSeguridad.getEstadoConexion()) {
/*  54 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  57 */     boolean gIndiceArea = rsSeguridad.tieneLlave(miGrupo, "oIndiceArea");
/*  58 */     boolean gIndiceFuncionarios = rsSeguridad.tieneLlave(miGrupo, "oIndiceFuncionarios");
/*  59 */     boolean bMostrarTodasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/*  60 */     rsSeguridad.close();
/*     */     
/*  62 */     HTMLFormElement forma = this.pagHTML.getElementForma();
/*  63 */     if (sujeto == 2) {
/*  64 */       forma.setAction("RepIndiceSatGeneral.po");
/*     */       
/*  66 */       Collection arr = null;
/*     */       
/*  68 */       AreasDAO af = new AreasDAO();
/*  69 */       if (bMostrarTodasAreas) {
/*  70 */         arr = af.cargarActivas();
/*     */       }
/*  72 */       else if (gIndiceArea) {
/*  73 */         arr = af.cargarAreasHijas(this.idNav);
/*     */       } else {
/*     */         
/*  76 */         arr = af.cargarMisAreas(this.idNav);
/*     */       } 
/*  78 */       af.close();
/*     */       
/*  80 */       Iterator iterator = arr.iterator();
/*  81 */       while (iterator.hasNext()) {
/*  82 */         AreasDTO reg = (AreasDTO)iterator.next();
/*  83 */         HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  84 */         op.setValue("" + reg.getCodigo());
/*  85 */         op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  86 */         combo.appendChild(op);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  91 */       forma.setAction("RepIndiceSatPersona.po");
/*  92 */       combos(comms);
/*  93 */       if (!gIndiceFuncionarios) {
/*  94 */         HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  95 */         op.setValue("" + p.getCodigoEmpleado());
/*  96 */         op.appendChild(this.pagHTML.createTextNode(p.getApellidos() + " " + p.getNombres()));
/*  97 */         combo.appendChild(op);
/*     */       } else {
/*     */         
/* 100 */         cargarArea(combo, miArea);
/*     */       } 
/*     */     } 
/* 103 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 104 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cargarArea(HTMLSelectElement combo, int area) {
/* 114 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/* 115 */     Collection arr = rsPersona.cargarActivoDeArea(area);
/*     */     
/* 117 */     Iterator iterator = arr.iterator();
/* 118 */     while (iterator.hasNext()) {
/* 119 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/* 120 */       HTMLOptionElement hpa = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 121 */       hpa.setValue("" + reg.getCodigoEmpleado());
/* 122 */       hpa.appendChild(this.pagHTML.createTextNode(reg.getApellidos() + " " + reg.getNombres()));
/* 123 */       combo.appendChild(hpa);
/* 124 */       if (this.idNav == reg.getCodigoEmpleado()) {
/* 125 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 126 */         escogida.setValue("on");
/* 127 */         hpa.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/* 130 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void combos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 140 */     int anno = 0;
/*     */     try {
/* 142 */       anno = Integer.parseInt(comms.request.getParameter("anno"));
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       anno = Utilidades.getAnnoActual();
/*     */     } 
/* 147 */     int mes1 = 0;
/*     */     try {
/* 149 */       mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       mes1 = Utilidades.getMesActual();
/*     */     } 
/* 154 */     int mes2 = 0;
/*     */     try {
/* 156 */       mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     }
/* 158 */     catch (Exception e) {
/* 159 */       mes2 = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 162 */     HTMLSelectElement annos = this.pagHTML.getElementIdAnno();
/* 163 */     for (int j = 2000; j <= Utilidades.getAnnoActual(); j++) {
/* 164 */       HTMLOptionElement op1 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 165 */       op1.setValue("" + j);
/* 166 */       op1.appendChild(this.pagHTML.createTextNode("" + j));
/* 167 */       annos.appendChild(op1);
/* 168 */       if (j == anno) {
/* 169 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 170 */         escogida.setValue("on");
/* 171 */         op1.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/* 175 */     HTMLSelectElement sMes1 = this.pagHTML.getElementIdMes1();
/* 176 */     HTMLSelectElement sMes2 = this.pagHTML.getElementIdMes2();
/* 177 */     String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
/*     */     
/* 179 */     for (int j = 1; j <= 12; j++) {
/* 180 */       HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 181 */       op2.setValue("" + j);
/* 182 */       op2.appendChild(this.pagHTML.createTextNode(meses[j - 1]));
/* 183 */       sMes1.appendChild(op2);
/* 184 */       if (j == mes1) {
/* 185 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 186 */         escogida.setValue("on");
/* 187 */         op2.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 190 */       HTMLOptionElement op3 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 191 */       op3.setValue("" + j);
/* 192 */       op3.appendChild(this.pagHTML.createTextNode(meses[j - 1]));
/* 193 */       sMes2.appendChild(op3);
/* 194 */       if (j == mes2) {
/* 195 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 196 */         escogida.setValue("on");
/* 197 */         op3.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerSujetoIndiceSat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */