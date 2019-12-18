/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.presentation.Principal;
/*    */ import sinco.presentation.PrincipalHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ public class Principal
/*    */   implements HttpPresentation {
/*    */   private PrincipalHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 23 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 24 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 27 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 29 */     SisUsuariosDAO personafact = new SisUsuariosDAO();
/* 30 */     SisUsuariosDTO per = personafact.cargarRegistro(idNav);
/*    */     
/* 32 */     this.pagHTML = (PrincipalHTML)comms.xmlcFactory.create(PrincipalHTML.class);
/*    */ 
/*    */ 
/*    */     
/* 36 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/* 37 */     int iPorAtender = sf.cuantasEnCursoPedidasA(idNav);
/* 38 */     int iEnCurso = sf.cuantasEnCursoPedidasPor(idNav);
/* 39 */     int iPorCalificar = sf.cuantasPorCalificarPor(idNav);
/* 40 */     int iEscaladas = sf.cuantasEscaladasA(idNav);
/* 41 */     int iNoEnviadas = sf.cuantasPorEnviarPor(idNav);
/* 42 */     int iNoEscaladas = sf.cuantasEscaladasPara(idNav);
/* 43 */     sf.close();
/*    */ 
/*    */ 
/*    */     
/* 47 */     Element enlaceC = this.pagHTML.getElementPorAtender();
/* 48 */     enlaceC.appendChild(newhref(iPorAtender + " Solicitudes por atender", "SolicitudesPorAtender.po"));
/*    */     
/* 50 */     enlaceC = this.pagHTML.getElementEscalanHoy();
/* 51 */     enlaceC.appendChild(newhref(iEscaladas + " Solicitudes escaladas", "SolicitudesPorAtender.po?escaladas=1"));
/*    */     
/* 53 */     enlaceC = this.pagHTML.getElementPorCalificar();
/* 54 */     enlaceC.appendChild(newhref(iPorCalificar + " Solicitudes por Evaluar", "SolicitudesPorCalificar.po"));
/*    */     
/* 56 */     enlaceC = this.pagHTML.getElementEnCurso();
/* 57 */     enlaceC.appendChild(newhref(iEnCurso + " Solicitudes en curso", "SolicitudesEnCurso.po"));
/*    */     
/* 59 */     enlaceC = this.pagHTML.getElementNoEnviadas();
/* 60 */     enlaceC.appendChild(newhref(iNoEnviadas + " Solicitudes por Enviar", "SolicitudesNoEnviadas.po"));
/*    */     
/* 62 */     enlaceC = this.pagHTML.getElementNoEscalamientos();
/* 63 */     enlaceC.appendChild(newhref(iNoEscaladas + " Solicitudes Escaladas a sus funcionarios", "SolicitudesEscalaronFuncionarios.po"));
/*    */     
/* 65 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 66 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Element newhref(String contenido, String vinculo) {
/* 80 */     Element enlace = this.pagHTML.createElement("a");
/* 81 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 82 */     enlace.appendChild(hijo);
/* 83 */     Attr donde = this.pagHTML.createAttribute("href");
/* 84 */     donde.setValue(vinculo);
/* 85 */     enlace.setAttributeNode(donde);
/* 86 */     return enlace;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Principal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */