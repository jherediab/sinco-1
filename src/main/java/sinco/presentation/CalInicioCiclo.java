/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.CalPlanesDTO;
/*     */ import sinco.business.CalPlantillasAreasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalCiclosDAO;
/*     */ import sinco.data.CalPlanesDAO;
/*     */ import sinco.data.CalPlantillasAreasDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.CalInicioCiclo;
/*     */ import sinco.presentation.CalInicioCicloHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalInicioCiclo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalInicioCicloHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*     */     
/*  46 */     if (_operacion == null) _operacion = "L";
/*     */ 
/*     */     
/*  49 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  50 */       creacion(comms);
/*     */     }
/*     */     
/*  53 */     this.pagHTML = (CalInicioCicloHTML)comms.xmlcFactory.create(CalInicioCicloHTML.class);
/*     */ 
/*     */     
/*  56 */     if (_operacion.equals("L")) {
/*  57 */       listar(comms);
/*     */     }
/*     */     
/*  60 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  61 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  72 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  74 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  76 */     int ciclo = 0;
/*     */     try {
/*  78 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  80 */     catch (Exception e) {
/*  81 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  84 */     CalCiclosDAO rs = new CalCiclosDAO(false);
/*  85 */     if (!rs.getEstadoConexion()) {
/*  86 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  88 */     CalCiclosDTO regCiclo = rs.cargarCalCiclos(ciclo);
/*     */     
/*  90 */     String rta = rs.abrirCiclo(ciclo, elUsuario);
/*  91 */     rs.close();
/*     */     
/*  93 */     if (rta == null && regCiclo.getEstado().equals("D")) {
/*     */       
/*  95 */       SisUsuariosDAO perf = new SisUsuariosDAO();
/*  96 */       SisUsuariosDTO regNavegante = perf.cargarRegistro(idNav);
/*     */       
/*  98 */       Varios oVarios = new Varios();
/*     */       
/* 100 */       CalPlanesDAO rsplan = new CalPlanesDAO();
/* 101 */       if (!rsplan.getEstadoConexion()) {
/* 102 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 104 */       rsplan.cargarPlanesArea(ciclo);
/*     */       
/* 106 */       CalPlanesDTO regPlan = rsplan.next2();
/* 107 */       while (regPlan != null) {
/* 108 */         String from = regNavegante.getEmail();
/* 109 */         String to = regPlan.getEmail();
/*     */         
/* 111 */         String mensaje = oVarios.formatMensaje("cal_mensaje_plan_abierto", "" + ciclo, "" + regPlan.getCodigoPlan());
/* 112 */         String asunto = oVarios.formatMensaje("cal_asunto_plan_abierto", regPlan.getNombreArea());
/* 113 */         Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, asunto, mensaje);
/*     */         
/* 115 */         regPlan = rsplan.next2();
/*     */       } 
/* 117 */       rsplan.close();
/*     */     } 
/* 119 */     String sPagina = "Mensaje.po?codigo=" + ((rta == null) ? "CicloAbierto" : "ErrorAbriendoCiclo") + "&p1=" + ciclo + "&p2=" + rta;
/* 120 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 133 */     int ciclo = 0;
/*     */     try {
/* 135 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 137 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 141 */     HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
/* 142 */     int cicloDefecto = llenarCombo(combo, ciclo);
/* 143 */     int cuantos = 0;
/* 144 */     if (cicloDefecto > 0) {
/*     */       
/* 146 */       CalPlantillasAreasDAO rsCal = new CalPlantillasAreasDAO();
/* 147 */       if (!rsCal.getEstadoConexion()) {
/* 148 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 150 */       HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 151 */       boolean fondo = true;
/*     */       
/* 153 */       rsCal.cargarTodos(cicloDefecto);
/* 154 */       CalPlantillasAreasDTO reg = rsCal.next3();
/* 155 */       while (reg != null) {
/* 156 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 157 */         fondo = !fondo;
/* 158 */         eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */         
/* 160 */         eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 161 */         eltr.appendChild(newtd("" + reg.getNombrePlantilla()));
/* 162 */         eltr.appendChild(newtd("" + reg.getEstado()));
/* 163 */         hte.appendChild(eltr);
/* 164 */         reg = rsCal.next3();
/* 165 */         cuantos++;
/*     */       } 
/* 167 */       rsCal.close();
/*     */     } 
/* 169 */     this.pagHTML.setTextNroRegistros("" + cuantos);
/* 170 */     this.pagHTML.getElementNumeroAreas().setValue("" + cuantos);
/*     */ 
/*     */     
/* 173 */     if (cuantos == 0) {
/* 174 */       HTMLElement eltr = this.pagHTML.getElementDetalle();
/* 175 */       eltr.getParentNode().removeChild(eltr);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 191 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 192 */     atrib.setValue(valor);
/* 193 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 204 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 205 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 206 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 207 */     return td;
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
/*     */   private int llenarCombo(HTMLSelectElement combo, int defecto) {
/* 225 */     int cicloDefecto = 0;
/* 226 */     int i = 0;
/*     */     
/* 228 */     CalCiclosDAO rs = new CalCiclosDAO();
/* 229 */     rs.cargarTodosEnDefinicion();
/*     */     
/*     */     CalCiclosDTO reg;
/* 232 */     while ((reg = rs.next()) != null) {
/* 233 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 234 */       op.setValue("" + reg.getCiclo());
/* 235 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 237 */       if (i == 0) {
/* 238 */         cicloDefecto = reg.getCiclo();
/*     */       }
/*     */       
/* 241 */       if (defecto == reg.getCiclo()) {
/* 242 */         cicloDefecto = defecto;
/* 243 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 244 */         escogida.setValue("on");
/* 245 */         op.setAttributeNode(escogida);
/*     */       } 
/* 247 */       combo.appendChild(op);
/* 248 */       i++;
/*     */     } 
/* 250 */     rs.close();
/* 251 */     return cicloDefecto;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalInicioCiclo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */