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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisGruposDTO;
/*     */ import sinco.business.SisPermisosGrupoDTO;
/*     */ import sinco.data.SisGruposDAO;
/*     */ import sinco.data.SisPermisosGrupoDAO;
/*     */ import sinco.presentation.SisPermisosGrupo;
/*     */ import sinco.presentation.SisPermisosGrupoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisPermisosGrupo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisPermisosGrupoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  36 */     String _operacion = comms.request.getParameter("_operacion");
/*  37 */     if (_operacion == null || _operacion.length() == 0) {
/*  38 */       _operacion = "X";
/*     */     }
/*     */     
/*  41 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  42 */       creacion(comms);
/*     */     }
/*     */     
/*  45 */     this.pagHTML = (SisPermisosGrupoHTML)comms.xmlcFactory.create(SisPermisosGrupoHTML.class);
/*     */     
/*  47 */     int grupo = 0;
/*     */     try {
/*  49 */       grupo = Integer.parseInt(comms.request.getParameter("grupo"));
/*     */     }
/*  51 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  54 */     this.pagHTML = (SisPermisosGrupoHTML)comms.xmlcFactory.create(SisPermisosGrupoHTML.class);
/*     */     
/*  56 */     HTMLSelectElement combo = this.pagHTML.getElementGrupo();
/*  57 */     llenarCombo(combo, grupo);
/*     */     
/*  59 */     origen(grupo);
/*  60 */     destino(grupo);
/*     */     
/*  62 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*     */     
/*  64 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private boolean llenarCombo(HTMLSelectElement combo, int defecto) {
/*  79 */     SisGruposDAO rs = new SisGruposDAO();
/*  80 */     if (!rs.getEstadoConexion()) {
/*  81 */       return false;
/*     */     }
/*  83 */     Collection<SisGruposDTO> arr = rs.cargarTodos(0, "");
/*  84 */     rs.close();
/*  85 */     Iterator<SisGruposDTO> iterator = arr.iterator();
/*  86 */     while (iterator.hasNext()) {
/*  87 */       SisGruposDTO reg = (SisGruposDTO)iterator.next();
/*     */       
/*  89 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  90 */       op.setValue("" + reg.getCodigo());
/*  91 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  92 */       if (defecto == reg.getCodigo()) {
/*  93 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  94 */         escogida.setValue("on");
/*  95 */         op.setAttributeNode(escogida);
/*     */       } 
/*  97 */       combo.appendChild(op);
/*     */     } 
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean destino(int grupo) {
/* 109 */     SisPermisosGrupoDAO rsPermisosGrupo = new SisPermisosGrupoDAO();
/* 110 */     if (!rsPermisosGrupo.getEstadoConexion()) {
/* 111 */       return false;
/*     */     }
/* 113 */     rsPermisosGrupo.cargarTodos(grupo);
/* 114 */     SisPermisosGrupoDTO reg = rsPermisosGrupo.next();
/*     */     
/* 116 */     HTMLSelectElement combo = this.pagHTML.getElementDestinoSelected();
/*     */     
/* 118 */     while (reg != null) {
/*     */       
/* 120 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 121 */       op.setValue("" + reg.getPermiso());
/* 122 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 123 */       combo.appendChild(op);
/*     */       
/* 125 */       reg = rsPermisosGrupo.next();
/*     */     } 
/* 127 */     rsPermisosGrupo.close();
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   private boolean origen(int grupo) {
/* 132 */     SisPermisosGrupoDAO rsPermisosGrupo = new SisPermisosGrupoDAO();
/* 133 */     if (!rsPermisosGrupo.getEstadoConexion()) {
/* 134 */       return false;
/*     */     }
/* 136 */     rsPermisosGrupo.cargarSinAsignar(grupo);
/* 137 */     SisPermisosGrupoDTO reg = rsPermisosGrupo.next();
/*     */     
/* 139 */     HTMLSelectElement combo = this.pagHTML.getElementOrigenSelected();
/*     */     
/* 141 */     while (reg != null) {
/*     */       
/* 143 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 144 */       op.setValue("" + reg.getPermiso());
/* 145 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 146 */       combo.appendChild(op);
/*     */       
/* 148 */       reg = rsPermisosGrupo.next();
/*     */     } 
/* 150 */     rsPermisosGrupo.close();
/* 151 */     return true;
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 165 */     String elUsuario = "" + comms.session.getUser().getName();
/* 166 */     int grupo = 0;
/*     */     try {
/* 168 */       grupo = Integer.parseInt(comms.request.getParameter("grupo"));
/*     */     }
/* 170 */     catch (Exception e) {
/* 171 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=grupo"));
/*     */     } 
/*     */     
/* 174 */     String permiso = comms.request.getParameter("permiso");
/* 175 */     if (permiso == null) {
/* 176 */       permiso = "";
/*     */     }
/* 178 */     RespuestaBD rta = new RespuestaBD();
/* 179 */     SisPermisosGrupoDAO rs = new SisPermisosGrupoDAO();
/* 180 */     if (!rs.getEstadoConexion()) {
/* 181 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 184 */     String permisos = comms.request.getParameter("destino");
/* 185 */     rta = rs.crearRegistro(grupo, permisos, elUsuario);
/*     */     
/* 187 */     rs.close();
/* 188 */     if (!rta.isRta()) {
/* 189 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisPermisosGrupo&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 192 */     String sPagina = "SisPermisosGrupo.po?_operacion=P&grupo=" + grupo + "&permiso=" + permiso + "";
/* 193 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisPermisosGrupo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */