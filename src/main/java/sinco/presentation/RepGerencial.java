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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AcumuladosDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.RepGerencial;
/*     */ import sinco.presentation.RepGerencialHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class RepGerencial
/*     */   implements HttpPresentation {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*     */     int i;
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  35 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/*  36 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*  37 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  43 */       i = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/*  47 */       SisUsuariosDTO regPersona = rsPersona.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*  48 */       i = regPersona.getArea();
/*     */     } 
/*     */     
/*  51 */     AreasDAO areaf = new AreasDAO();
/*  52 */     AreasDTO areaProveedor = areaf.getArea(i);
/*  53 */     areaf.close();
/*     */     
/*  55 */     AcumuladosDAO rsAcumulados = new AcumuladosDAO();
/*     */     
/*  57 */     rsAcumulados.cargarAcumulados(anno, mes1, mes2, areaProveedor.getSecuencia());
/*     */     
/*  59 */     String sCondiciones = "&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2;
/*     */     
/*  61 */     this.pagHTML = (RepGerencialHTML)comms.xmlcFactory.create(RepGerencialHTML.class);
/*  62 */     this.pagHTML.setTextTitulo(Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
/*     */     
/*  64 */     HTMLTableElement laTabla = this.pagHTML.getElementDetalle();
/*  65 */     IndiceSatisfaccionDTO reg = rsAcumulados.next();
/*  66 */     boolean fondo = true;
/*     */     
/*  68 */     int recibidas = 0;
/*  69 */     int atendidas = 0;
/*  70 */     int escaladas = 0;
/*  71 */     int cuantas = 0;
/*     */     
/*  73 */     int oe = 0, ob = 0, or = 0;
/*  74 */     int ce = 0, cb = 0, cr = 0;
/*     */     
/*  76 */     while (reg != null) {
/*     */       
/*  78 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  79 */       fondo = !fondo;
/*  80 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  82 */       String sPagina = "RepGerencial.po?area=" + reg.getCodigoArea() + sCondiciones;
/*  83 */       eltr.appendChild(newtdhref("" + reg.getNombreArea(), sPagina));
/*     */       
/*  85 */       eltr.appendChild(newtd("" + reg.getRecibidas(), true));
/*  86 */       eltr.appendChild(newtd("" + reg.getAtendidas(), true));
/*  87 */       eltr.appendChild(newtd("" + reg.getEscaladas(), true));
/*     */       
/*  89 */       if (reg.getIndiceOportunidad() == 0.0F) {
/*  90 */         eltr.appendChild(newtd("N/A", false));
/*     */       } else {
/*     */         
/*  93 */         eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + "%", false));
/*     */       } 
/*     */       
/*  96 */       if (reg.getIndiceConfiabilidad() == 0.0F) {
/*  97 */         eltr.appendChild(newtd("N/A", false));
/*     */       } else {
/*     */         
/* 100 */         eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + "%", false));
/*     */       } 
/*     */       
/* 103 */       sPagina = "RepGerencialPersonas.po?area=" + reg.getCodigoArea() + sCondiciones;
/* 104 */       if (reg.getIndiceTotal() == 0.0D) {
/* 105 */         eltr.appendChild(newtdhref("N/A", sPagina));
/*     */       } else {
/*     */         
/* 108 */         eltr.appendChild(newtdhref("" + Utilidades.miles(Utilidades.round2(reg.getIndiceTotal()), 2) + "%", sPagina));
/*     */       } 
/* 110 */       laTabla.appendChild(eltr);
/*     */       
/* 112 */       recibidas += reg.getRecibidas();
/* 113 */       atendidas += reg.getAtendidas();
/* 114 */       escaladas += reg.getEscaladas();
/*     */ 
/*     */       
/* 117 */       oe += reg.getOportunidadExcelente();
/* 118 */       ob += reg.getOportunidadBuena();
/* 119 */       or += reg.getOportunidadRegular();
/*     */       
/* 121 */       ce += reg.getConfiabilidadExcelente();
/* 122 */       cb += reg.getConfiabilidadBuena();
/* 123 */       cr += reg.getConfiabilidadRegular();
/*     */ 
/*     */ 
/*     */       
/* 127 */       reg = rsAcumulados.next();
/* 128 */       cuantas++;
/*     */     } 
/* 130 */     rsAcumulados.close();
/*     */ 
/*     */     
/* 133 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 134 */     fondo = !fondo;
/* 135 */     eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */     
/* 137 */     eltr.appendChild(newtd("Totales...", false));
/* 138 */     eltr.appendChild(newtd("" + recibidas, true));
/* 139 */     eltr.appendChild(newtd("" + atendidas, true));
/* 140 */     eltr.appendChild(newtd("" + escaladas, true));
/*     */ 
/*     */     
/* 143 */     float indiceOportunidad = Utilidades.calcularIndice(oe, ob, or);
/* 144 */     float indiceConfiabilidad = Utilidades.calcularIndice(ce, cb, cr);
/*     */     
/* 146 */     double indice_total = 0.0D;
/* 147 */     if (indiceOportunidad == 0.0F || indiceConfiabilidad == 0.0F) {
/* 148 */       indice_total = (indiceOportunidad + indiceConfiabilidad);
/*     */     } else {
/*     */       
/* 151 */       indice_total = ((indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad")) / 100.0F);
/*     */     } 
/*     */ 
/*     */     
/* 155 */     eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(indiceOportunidad), 2) + "%", false));
/* 156 */     eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(indiceConfiabilidad), 2) + "%", false));
/* 157 */     eltr.appendChild(newtd("" + Utilidades.miles(Utilidades.round2(indice_total), 2) + "%", false));
/* 158 */     laTabla.appendChild(eltr);
/*     */     
/* 160 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/* 161 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 162 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private RepGerencialHTML pagHTML;
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 168 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 169 */     Element enlace = this.pagHTML.createElement("a");
/* 170 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 171 */     enlace.appendChild(hijo);
/* 172 */     Attr donde = this.pagHTML.createAttribute("href");
/* 173 */     donde.setValue(vinculo);
/* 174 */     enlace.setAttributeNode(donde);
/* 175 */     td.appendChild(enlace);
/* 176 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 177 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 180 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 181 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 182 */     if (alinear) {
/* 183 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 185 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 186 */     return td;
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 190 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 191 */     atrib.setValue(valor);
/* 192 */     return atrib;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepGerencial.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */