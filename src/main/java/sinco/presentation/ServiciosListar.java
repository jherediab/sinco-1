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
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ServiciosListar;
/*     */ import sinco.presentation.ServiciosListarHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServiciosListar
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ServiciosListarHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "X";
/*     */     }
/*  47 */     this.pagHTML = (ServiciosListarHTML)comms.xmlcFactory.create(ServiciosListarHTML.class);
/*  48 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  49 */       listar(comms, _operacion);
/*     */       
/*  51 */       HTMLElement sel = this.pagHTML.getElementTrAreas();
/*  52 */       sel.getParentNode().removeChild(sel);
/*     */     
/*     */     }
/*  55 */     else if (_operacion.equals("P")) {
/*  56 */       editar(comms);
/*     */       
/*  58 */       HTMLElement sel = this.pagHTML.getElementTrResultados();
/*  59 */       sel.getParentNode().removeChild(sel);
/*     */       
/*  61 */       sel = this.pagHTML.getElementTrConsulta();
/*  62 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  64 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  65 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  76 */     String descripcion = comms.request.getParameter("descripcion");
/*  77 */     if (descripcion == null) {
/*  78 */       descripcion = "";
/*     */     }
/*  80 */     this.pagHTML.getElementFdescripcion().setValue("" + descripcion);
/*     */     
/*  82 */     String especializado = comms.request.getParameter("especializado");
/*  83 */     if (especializado == null) {
/*  84 */       especializado = "";
/*     */     }
/*     */ 
/*     */     
/*  88 */     HTMLSelectElement combo = this.pagHTML.getElementFespecializado();
/*  89 */     comboMultivalores(combo, "CLASE_SERVICIO", "" + especializado, true);
/*     */     
/*  91 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     ServiciosDAO rs = new ServiciosDAO();
/*  96 */     if (!rs.getEstadoConexion()) {
/*  97 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  99 */     Collection arr = rs.cargarTodos(descripcion, especializado);
/* 100 */     rs.close();
/* 101 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 102 */     int cuantas = 0;
/* 103 */     Iterator iterator = arr.iterator();
/* 104 */     while (iterator.hasNext()) {
/* 105 */       ServiciosDTO reg = (ServiciosDTO)iterator.next();
/* 106 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 107 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 108 */       String url = "ServiciosListar.po?_operacion=P&codigo=" + reg.getCodigo() + "";
/* 109 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 110 */       eltr.appendChild(newtd("" + reg.getEspecializado()));
/* 111 */       eltr.appendChild(newtd("" + reg.getDuracion()));
/* 112 */       eltr.appendChild(newtd("" + reg.getUnidadMedida()));
/* 113 */       hte.appendChild(eltr);
/* 114 */       cuantas++;
/*     */     } 
/* 116 */     arr.clear();
/* 117 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 131 */     int codigo = 0;
/*     */     try {
/* 133 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 135 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 138 */     ServiciosDAO rsServicio = new ServiciosDAO();
/* 139 */     ServiciosDTO regServicio = rsServicio.cargarRegistro(codigo);
/* 140 */     rsServicio.close();
/* 141 */     if (regServicio != null) {
/* 142 */       this.pagHTML.setTextNombreServio(regServicio.getDescripcion());
/*     */     }
/*     */ 
/*     */     
/* 146 */     ServicioAreaDAO rs = new ServicioAreaDAO();
/* 147 */     if (!rs.getEstadoConexion()) {
/* 148 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 150 */     Collection arr = rs.cargarTodos(codigo);
/* 151 */     rs.close();
/*     */     
/* 153 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleArea();
/* 154 */     int cuantas = 0;
/* 155 */     Iterator iterator = arr.iterator();
/* 156 */     while (iterator.hasNext()) {
/* 157 */       ServicioAreaDTO reg = (ServicioAreaDTO)iterator.next();
/* 158 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 159 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 160 */       eltr.appendChild(newtd("" + reg.getNombreEspecialista()));
/* 161 */       hte.appendChild(eltr);
/* 162 */       cuantas++;
/*     */     } 
/* 164 */     arr.clear();
/* 165 */     this.pagHTML.setTextNroAreas("" + cuantas);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 179 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 180 */     atrib.setValue(valor);
/* 181 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 194 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 195 */     Element enlace = this.pagHTML.createElement("a");
/* 196 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 197 */     enlace.appendChild(hijo);
/* 198 */     Attr donde = this.pagHTML.createAttribute("href");
/* 199 */     donde.setValue(vinculo);
/* 200 */     enlace.setAttributeNode(donde);
/* 201 */     td.appendChild(enlace);
/* 202 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 203 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 213 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 214 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 215 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 216 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 232 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 233 */     Collection arr = rs.cargarTabla(tabla);
/* 234 */     rs.close();
/* 235 */     if (dejarBlanco) {
/* 236 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 237 */       op.setValue("");
/* 238 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 239 */       combo.appendChild(op);
/*     */     } 
/* 241 */     Iterator iterator = arr.iterator();
/* 242 */     while (iterator.hasNext()) {
/* 243 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 244 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 245 */       op.setValue("" + reg.getCodigo());
/* 246 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 247 */       if (defecto.equals(reg.getCodigo())) {
/* 248 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 249 */         escogida.setValue("on");
/* 250 */         op.setAttributeNode(escogida);
/*     */       } 
/* 252 */       combo.appendChild(op);
/*     */     } 
/* 254 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ServiciosListar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */