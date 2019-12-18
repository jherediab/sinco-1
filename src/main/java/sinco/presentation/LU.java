/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.LoginHTML;
/*     */ import sinco.spec.MenuFactory;
/*     */ import sinco.spec.UserDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LU
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/*  24 */     String tipoentrada = comms.request.getParameter("t");
/*  25 */     String texto = "ErrorEnAutenticacion";
/*     */     
/*  27 */     if (tipoentrada == null) tipoentrada = "w";
/*     */     
/*  29 */     String id = "";
/*  30 */     String password = "";
/*  31 */     String pagina = "";
/*  32 */     long miCedula = 0L;
/*     */     try {
/*  34 */       id = comms.request.getParameter("l");
/*  35 */       password = comms.request.getParameter("p");
/*  36 */       pagina = comms.request.getParameter("h");
/*     */       
/*  38 */       if (tipoentrada.equals("m") && comms.session.getSessionData().containsKey("miId")) {
/*  39 */         id = (String)comms.session.getSessionData().get("miId");
/*     */       
/*     */       }
/*     */     }
/*  43 */     catch (Exception e) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     } 
/*     */     
/*  47 */     if (tipoentrada.equals("m") && !comms.session.getSessionData().containsKey("miId")) {
/*  48 */       LoginHTML pagHTML = (LoginHTML)comms.xmlcFactory.create(LoginHTML.class);
/*     */       
/*  50 */       if (pagina == null) {
/*  51 */         pagina = "WelcomePresentation.po";
/*     */       }
/*  53 */       if (pagina.length() > 0) {
/*  54 */         pagHTML.getElementH().setValue("" + pagina);
/*     */       }
/*  56 */       comms.response.writeDOM(pagHTML);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  61 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*     */ 
/*     */     
/*  64 */     SisUsuariosDTO regPersona = null;
/*     */     try {
/*  66 */       if (tipoentrada.equals("m"))
/*     */       {
/*  68 */         regPersona = pf.cargarRegistro(Integer.parseInt(id));
/*     */       }
/*  70 */       else if (tipoentrada.equals("w"))
/*     */       {
/*  72 */         regPersona = pf.getPersonaPorEmail(id);
/*     */       }
/*     */     
/*  75 */     } catch (Exception e) {
/*  76 */       e.printStackTrace();
/*  77 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=IdNoValido"));
/*     */     } 
/*     */     
/*  80 */     if (regPersona == null) {
/*  81 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=UsuarioNoValido&p1=" + id));
/*     */     }
/*     */     
/*  84 */     if (!regPersona.getEstado().equals("A")) {
/*  85 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=UsuarioInactivo"));
/*     */     }
/*     */ 
/*     */     
/*  89 */     boolean okIngreso = true;
/*     */     
/*  91 */     int rtaCorreo = 0;
/*  92 */     if (ParametrosDTO.getInt("pruebas") == 0) {
/*  93 */       if (!tipoentrada.equals("m")) {
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
/*     */ 
/*     */ 
/*     */         
/* 113 */         String rta = "";
/*     */         
/* 115 */         if (!regPersona.getPassword().equals(password))
/*     */         {
/*     */ 
/*     */           
/* 119 */           rta = "N";
/*     */         }
/*     */ 
/*     */         
/* 123 */         if (rta.equals("N")) {
/* 124 */           okIngreso = false;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 129 */       if (!okIngreso) {
/* 130 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEnAutenticacion&p1=" + texto));
/*     */       }
/*     */     } 
/*     */     
/* 134 */     comms.session.getSessionData().set("miId", "" + regPersona.getCodigoEmpleado());
/* 135 */     comms.session.getSessionData().set("RemoteAddr", "" + comms.request.getRemoteAddr());
/*     */     
/* 137 */     comms.session.setUser(new UserDO(regPersona.getIdcorreo()));
/*     */     
/* 139 */     PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/* 140 */     int nroAreas = rsPersonasArea.getNumeroAreas(regPersona.getCodigoEmpleado());
/* 141 */     int miClase = -1;
/*     */     
/* 143 */     if (nroAreas == 1 || tipoentrada.equals("m")) {
/* 144 */       PersonasAreaDTO regPerfil = null;
/* 145 */       if (nroAreas == 1) {
/* 146 */         regPerfil = rsPersonasArea.cargarPersonaUnArea(regPersona.getCodigoEmpleado());
/*     */       } else {
/*     */         
/* 149 */         regPerfil = rsPersonasArea.cargarPersona(regPersona.getCodigoEmpleado());
/*     */       } 
/* 151 */       if (regPerfil == null) {
/* 152 */         rsPersonasArea.close();
/* 153 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FuncionarioSinAreaPrincipal"));
/*     */       } 
/* 155 */       miClase = regPerfil.getClase();
/* 156 */       rsPersonasArea.actualizarAreaPersona(regPersona.getCodigoEmpleado(), regPerfil.getCodigoArea(), miClase, regPersona.getIdcorreo());
/*     */       
/* 158 */       comms.session.getSessionData().set("miGrupo", "" + miClase);
/* 159 */       comms.session.getSessionData().set("miArea", "" + regPerfil.getCodigoArea());
/*     */     } else {
/*     */       
/* 162 */       if (nroAreas == 0) {
/* 163 */         rsPersonasArea.close();
/* 164 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoTieneAreaAsignada"));
/*     */       } 
/* 166 */       if (nroAreas > 1) {
/* 167 */         rsPersonasArea.close();
/* 168 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("EscogerPerfil.po"));
/*     */       } 
/* 170 */     }  rsPersonasArea.close();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     MenuFactory miMenu = new MenuFactory();
/* 176 */     if (miClase >= 0 && !miMenu.crearMenu("" + regPersona.getCodigoEmpleado(), miClase, "SOLICITUDES")) {
/* 177 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=arc_ErrorConstruccionMenu"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (tipoentrada.equals("m") && (pagina.indexOf("VSEnCurso.po") >= 0 || pagina.indexOf("DocCorrespondenciaDetalle.po") >= 0))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 188 */       pagina = pagina.replace('-', '&');
/*     */     }
/*     */ 
/*     */     
/* 192 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\LU.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */