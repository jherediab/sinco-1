/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CalGenericaDTO;
/*     */ import sinco.business.CalPlantillasObjetivosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CalGenericaDAO;
/*     */ import sinco.data.CalPlantillasObjetivosDAO;
/*     */ import sinco.data.LibreDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalPlantillasObjAct;
/*     */ import sinco.presentation.CalPlantillasObjActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalPlantillasObjAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalPlantillasObjActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  43 */     String elUsuario = "" + comms.session.getUser().getName();
/*  44 */     String proceso = comms.request.getParameter("proceso");
/*  45 */     if (proceso == null) proceso = "X";
/*     */     
/*  47 */     String subproceso = comms.request.getParameter("subproceso");
/*  48 */     if (subproceso == null) subproceso = "X";
/*     */     
/*  50 */     String tipoObjetivo = comms.request.getParameter("tipoObjetivo");
/*  51 */     if (tipoObjetivo == null) tipoObjetivo = "X";
/*     */     
/*  53 */     String _operacion = comms.request.getParameter("_operacion");
/*  54 */     if (_operacion == null || _operacion.length() == 0) {
/*  55 */       _operacion = "L";
/*     */     }
/*     */ 
/*     */     
/*  59 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  60 */       int plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/*     */       
/*  62 */       CalPlantillasObjetivosDAO rs = new CalPlantillasObjetivosDAO();
/*  63 */       if (!rs.getEstadoConexion()) {
/*  64 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*     */       
/*  67 */       rs.eliminarRegistro(plantilla, elUsuario);
/*     */       
/*  69 */       Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */       
/*  72 */       while (enumera.hasMoreElements()) {
/*  73 */         String param = (String)enumera.nextElement();
/*  74 */         if (param.substring(0, 2).equals("_X")) {
/*  75 */           int codigoObjetivo = Integer.parseInt(param.substring(2, param.length()));
/*  76 */           rs.crearRegistro(plantilla, codigoObjetivo, elUsuario);
/*     */         } 
/*     */       } 
/*  79 */       rs.close();
/*     */       
/*  81 */       String sPagina = "CalPlantillasObjAct.po?_operacion=L&plantilla=" + plantilla + "&proceso=" + proceso + "&subproceso=" + subproceso + "&tipoObjetivo=" + tipoObjetivo;
/*  82 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (_operacion.equals("L")) {
/*     */       
/*  88 */       int plantilla = 0;
/*     */       try {
/*  90 */         plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/*     */       }
/*  92 */       catch (Exception e) {}
/*     */ 
/*     */       
/*  95 */       this.pagHTML = (CalPlantillasObjActHTML)comms.xmlcFactory.create(CalPlantillasObjActHTML.class);
/*     */ 
/*     */ 
/*     */       
/*  99 */       String elScript = generarScript();
/* 100 */       this.pagHTML.setTextElScript("" + elScript);
/*     */       
/* 102 */       comboProcesos(comms, proceso);
/*     */       
/* 104 */       TGeneralDAO rsTGen = new TGeneralDAO();
/* 105 */       if (!rsTGen.getEstadoConexion()) {
/* 106 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 108 */       HTMLSelectElement combo = this.pagHTML.getElementTipoObjetivo();
/* 109 */       llenarCombo(rsTGen, combo, "sis_multivalores", "valor", "descripcion", "tabla='CAL_TIPO_OBJETIVO' order by descripcion", tipoObjetivo);
/* 110 */       rsTGen.close();
/*     */       
/* 112 */       combo = this.pagHTML.getElementIdPlantilla();
/* 113 */       int plantillaDefecto = llenarCombo(combo, plantilla);
/*     */ 
/*     */ 
/*     */       
/* 117 */       CalPlantillasObjetivosDAO rsCalPlantillas = new CalPlantillasObjetivosDAO();
/* 118 */       if (!rsCalPlantillas.getEstadoConexion()) {
/* 119 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 121 */       HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 122 */       boolean fondo = true;
/*     */ 
/*     */       
/* 125 */       rsCalPlantillas.cargarTodos(plantillaDefecto, proceso, subproceso, tipoObjetivo);
/* 126 */       CalPlantillasObjetivosDTO reg = rsCalPlantillas.next();
/* 127 */       while (reg != null) {
/* 128 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 129 */         fondo = !fondo;
/* 130 */         eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */         
/* 132 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 133 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 134 */         checkbox.setAttribute("type", "checkbox");
/* 135 */         checkbox.setName("_X" + reg.getCodigoObjetivo());
/* 136 */         checkbox.setChecked(reg.getExiste());
/* 137 */         tdMarca.appendChild(checkbox);
/*     */         
/* 139 */         eltr.appendChild(tdMarca);
/*     */         
/* 141 */         eltr.appendChild(newtd("" + reg.getCodigoObjetivo()));
/* 142 */         eltr.appendChild(newtd("" + reg.getNombreObjetivo()));
/* 143 */         eltr.appendChild(newtd("" + reg.getTipoObjetivo()));
/* 144 */         eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 145 */         eltr.appendChild(newtd("" + reg.getNombreSubproceso()));
/*     */         
/* 147 */         hte.appendChild(eltr);
/* 148 */         reg = rsCalPlantillas.next();
/*     */       } 
/*     */       
/* 151 */       rsCalPlantillas.close();
/*     */       
/* 153 */       CalGenericaDAO rsPlantilla = new CalGenericaDAO();
/* 154 */       CalGenericaDTO regPlantilla = rsPlantilla.cargarPlantilla(plantillaDefecto);
/* 155 */       rsPlantilla.close();
/*     */       
/* 157 */       if (regPlantilla != null && 
/* 158 */         regPlantilla.getEstado().equals("I")) {
/* 159 */         HTMLElement elBoton = this.pagHTML.getElementIdBoton();
/* 160 */         elBoton.getParentNode().removeChild(elBoton);
/*     */       } 
/*     */ 
/*     */       
/* 164 */       this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 165 */       comms.response.writeDOM(this.pagHTML);
/*     */       return;
/*     */     } 
/* 168 */     this.pagHTML = (CalPlantillasObjActHTML)comms.xmlcFactory.create(CalPlantillasObjActHTML.class);
/*     */     
/* 170 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 171 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 183 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 184 */     atrib.setValue(valor);
/* 185 */     return atrib;
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
/* 196 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 197 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 198 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 199 */     return td;
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
/* 217 */     int plantillaDefecto = 0;
/* 218 */     int i = 0;
/*     */     
/* 220 */     CalPlantillasObjetivosDAO rs = new CalPlantillasObjetivosDAO();
/* 221 */     rs.cargarPlantillas();
/*     */     
/*     */     CalPlantillasObjetivosDTO reg;
/* 224 */     while ((reg = rs.nextPlantilla()) != null) {
/* 225 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 226 */       op.setValue("" + reg.getCodigoPlantilla());
/* 227 */       op.appendChild(this.pagHTML.createTextNode(reg.getNombrePlantilla()));
/*     */       
/* 229 */       if (i == 0) {
/* 230 */         plantillaDefecto = reg.getCodigoPlantilla();
/* 231 */         if (defecto == 0) defecto = plantillaDefecto;
/*     */       
/*     */       } 
/* 234 */       if (defecto == reg.getCodigoPlantilla()) {
/* 235 */         plantillaDefecto = defecto;
/* 236 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 237 */         escogida.setValue("on");
/* 238 */         op.setAttributeNode(escogida);
/*     */       } 
/* 240 */       combo.appendChild(op);
/* 241 */       i++;
/*     */     } 
/* 243 */     rs.close();
/* 244 */     return plantillaDefecto;
/*     */   }
/*     */   
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String where, String defecto) {
/* 248 */     rsTGen.cargarTodos(tabla, codigo, descripcion, where);
/*     */ 
/*     */     
/* 251 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 252 */     op.setValue("X");
/* 253 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 254 */     combo.appendChild(op); TGeneralDTO RegGeneral;
/* 255 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 256 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 257 */       op.setValue("" + RegGeneral.getCodigo());
/* 258 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 259 */       if (defecto.equals(RegGeneral.getCodigo())) {
/* 260 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 261 */         escogida.setValue("on");
/* 262 */         op.setAttributeNode(escogida);
/*     */       } 
/* 264 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String generarScript() {
/* 270 */     Vector vProcesos = new Vector();
/* 271 */     LibreDAO rsLibre = new LibreDAO();
/* 272 */     rsLibre.doExecute("select codigo,descripcion from procesos where codigo<>'00' and estado='A' order by descripcion");
/* 273 */     while (rsLibre.next()) {
/* 274 */       CalProcesosDTO regProcesos = new CalProcesosDTO();
/* 275 */       regProcesos.setProceso(rsLibre.getString("codigo"));
/* 276 */       regProcesos.setDescripcion(rsLibre.getString("descripcion"));
/* 277 */       vProcesos.addElement(regProcesos);
/*     */     } 
/*     */     
/* 280 */     Vector vSubProcesos = new Vector();
/* 281 */     rsLibre.doExecute("select PROCESO,CODIGO,DESCRIPCION from subprocesos where estado='A' and codigo<>'00' order by proceso,codigo");
/* 282 */     while (rsLibre.next()) {
/* 283 */       CalProcesosDTO regProcesos = new CalProcesosDTO();
/* 284 */       regProcesos.setProceso(rsLibre.getString("proceso"));
/* 285 */       regProcesos.setSubProceso(rsLibre.getString("codigo"));
/* 286 */       regProcesos.setDescripcion(rsLibre.getString("descripcion"));
/* 287 */       vSubProcesos.addElement(regProcesos);
/*     */     } 
/* 289 */     rsLibre.close();
/*     */ 
/*     */     
/* 292 */     String script = "var matriz=new Array(" + vSubProcesos.size() + ");";
/* 293 */     script = script + " var iCont=0;";
/* 294 */     int i = 0;
/* 295 */     while (i < vSubProcesos.size()) {
/* 296 */       CalProcesosDTO regProceso = (CalProcesosDTO)vSubProcesos.elementAt(i);
/* 297 */       script = script + " matriz[iCont++]=new add_member('" + regProceso.getProceso() + "','" + regProceso.getSubProceso() + "','" + regProceso.getDescripcion() + "');";
/* 298 */       i++;
/*     */     } 
/* 300 */     return script;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboProcesos(HttpPresentationComms comms, String proceso) throws HttpPresentationException, KeywordValueException {
/* 307 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*     */     
/* 309 */     if (!rsTGen.getEstadoConexion()) {
/* 310 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 312 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/*     */     
/* 314 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 315 */     op.setValue("X");
/* 316 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 317 */     combo.appendChild(op);
/*     */     
/* 319 */     rsTGen.executeQuery("select codigo,descripcion from procesos where codigo<>'00' and estado='A' order by descripcion");
/*     */     TGeneralDTO RegGeneral;
/* 321 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 322 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 323 */       op.setValue("" + RegGeneral.getCodigo());
/* 324 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 325 */       if (proceso.equals(RegGeneral.getCodigo())) {
/* 326 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 327 */         escogida.setValue("on");
/* 328 */         op.setAttributeNode(escogida);
/*     */       } 
/* 330 */       combo.appendChild(op);
/*     */     } 
/* 332 */     rsTGen.close();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalPlantillasObjAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */