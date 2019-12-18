/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.TipoCalificacionDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.data.TipoCalificacionDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.PreCalificarEncuesta;
/*     */ import sinco.presentation.PreCalificarEncuestaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class PreCalificarEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   boolean hay = false;
/*     */   private PreCalificarEncuestaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  38 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  40 */     int numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */ 
/*     */     
/*  43 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  44 */     EncuestaDTO regEncuesta = rsEncuesta.cargaRegistro(numero);
/*  45 */     rsEncuesta.close();
/*     */     
/*  47 */     if (regEncuesta == null) {
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudAnulada"));
/*     */     }
/*     */     
/*  51 */     this.pagHTML = (PreCalificarEncuestaHTML)comms.xmlcFactory.create(PreCalificarEncuestaHTML.class);
/*  52 */     this.pagHTML.setTextNumero("" + numero);
/*  53 */     this.pagHTML.setTextServicio(regEncuesta.getNombreServicio());
/*  54 */     this.pagHTML.setTextDescripcion(regEncuesta.getDescripcion());
/*  55 */     this.pagHTML.setTextFecha(Utilidades.darFormatoFecha(regEncuesta.getFecha()));
/*  56 */     this.pagHTML.setTextEstado(regEncuesta.getNombreEstado());
/*     */ 
/*     */     
/*  59 */     HTMLInputElement input = this.pagHTML.getElementEncuesta();
/*  60 */     input.setValue("" + numero);
/*     */ 
/*     */     
/*  63 */     HTMLSelectElement calsel = this.pagHTML.getElementConfiabilidades();
/*  64 */     HTMLOptionElement toe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  65 */     toe.setValue("X");
/*  66 */     toe.appendChild(this.pagHTML.createTextNode(" "));
/*  67 */     calsel.appendChild(toe);
/*     */     
/*  69 */     TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*  70 */     tcf.cargarTodos(ParametrosDTO.getInt("maximo.numero.devoluciones") + 2);
/*  71 */     TipoCalificacionDTO tipocal = tcf.next();
/*  72 */     while (tipocal != null) {
/*  73 */       toe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  74 */       toe.setValue("" + tipocal.getCodigo());
/*  75 */       toe.appendChild(this.pagHTML.createTextNode(tipocal.getDescripcion()));
/*  76 */       calsel.appendChild(toe);
/*  77 */       tipocal = tcf.next();
/*     */     } 
/*  79 */     tcf.close();
/*     */ 
/*     */     
/*  82 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  83 */     rsSol.participantes(numero);
/*     */     
/*  85 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  86 */     boolean fondo = true;
/*  87 */     VSolicitudesDTO regSol = rsSol.siguienteResultado();
/*  88 */     int participantes = 0;
/*  89 */     while (regSol != null) {
/*  90 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  92 */       fondo = !fondo;
/*  93 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  95 */       eltr.appendChild(newtd("" + regSol.getObservaciones(), false));
/*  96 */       eltr.appendChild(newtd("" + regSol.getNumero(), false));
/*     */       
/*  98 */       hte.appendChild(eltr);
/*  99 */       participantes += regSol.getNumero();
/*     */       
/* 101 */       regSol = rsSol.siguienteResultado();
/*     */     } 
/* 103 */     rsSol.close();
/* 104 */     this.pagHTML.setTextParticipantes("" + participantes);
/*     */     
/* 106 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 107 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 111 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 112 */     atrib.setValue(valor);
/* 113 */     return atrib;
/*     */   }
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 116 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 117 */     Element enlace = this.pagHTML.createElement("a");
/* 118 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 119 */     enlace.appendChild(hijo);
/* 120 */     Attr donde = this.pagHTML.createAttribute("href");
/* 121 */     donde.setValue(vinculo);
/* 122 */     enlace.setAttributeNode(donde);
/* 123 */     td.appendChild(enlace);
/* 124 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 125 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 128 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 129 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 130 */     if (alinear) {
/* 131 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 133 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 134 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreCalificarEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */