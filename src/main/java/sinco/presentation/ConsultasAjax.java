/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.io.StringWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import org.apache.xml.serialize.OutputFormat;
/*      */ import org.apache.xml.serialize.XMLSerializer;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import sinco.business.AreasDTO;
/*      */ import sinco.business.CalSubProcesosDTO;
/*      */ import sinco.business.CaracteristicasServicioDTO;
/*      */ import sinco.business.CaracteristicasValorDTO;
/*      */ import sinco.business.ElemAreaDTO;
/*      */ import sinco.business.FormaGeneralDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.PersonasAreaDTO;
/*      */ import sinco.business.PoaActividadesDTO;
/*      */ import sinco.business.PoaMetasPlanDesarrolloDTO;
/*      */ import sinco.business.PoaMetasProyectoDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.ServicioAreaDTO;
/*      */ import sinco.business.ServiciosDTO;
/*      */ import sinco.business.SisUsuariosDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.AreasDAO;
/*      */ import sinco.data.CalSubProcesosDAO;
/*      */ import sinco.data.CaracteristicasDAO;
/*      */ import sinco.data.CaracteristicasServicioDAO;
/*      */ import sinco.data.CaracteristicasValorDAO;
/*      */ import sinco.data.FormaGeneralDAO;
/*      */ import sinco.data.PersonasAreaDAO;
/*      */ import sinco.data.PoaActividadesDAO;
/*      */ import sinco.data.PoaMetasPlanDesarrolloDAO;
/*      */ import sinco.data.PoaMetasProyectoDAO;
/*      */ import sinco.data.ServicioAreaDAO;
/*      */ import sinco.data.ServiciosDAO;
/*      */ import sinco.data.SisUsuariosDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.ConsultasAjax;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConsultasAjax
/*      */   implements HttpPresentation
/*      */ {
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   71 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   72 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?NoSession"));
/*      */     }
/*      */     
/*   75 */     String _operacion = comms.request.getParameter("_operacion");
/*      */     
/*   77 */     Document doc = newDocument();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   82 */     if (doc == null) {
/*      */       
/*      */       try {
/*   85 */         comms.response.setContentType("text/xml");
/*   86 */         comms.response.setHeader("Content-Disposition", "inline;");
/*   87 */         comms.response.setStatus(200, "Good job");
/*   88 */         HttpPresentationOutputStream out = comms.response.getOutputStream();
/*   89 */         String respuesta = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><reg><estado>err</estado><error>causa desconocida</error></reg>";
/*      */         
/*   91 */         byte[] byteContents = respuesta.getBytes();
/*   92 */         out.write(byteContents);
/*   93 */         out.flush();
/*      */       }
/*   95 */       catch (Exception e) {
/*   96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + e.getMessage()));
/*      */       } 
/*      */     }
/*      */     
/*  100 */     String respuesta = "";
/*  101 */     if (_operacion.equals("FormaGeneral")) {
/*  102 */       respuesta = consultaFormaGeneral(comms, doc, _operacion);
/*      */     }
/*  104 */     else if (_operacion.equals("responsableAsunto")) {
/*  105 */       respuesta = responsableAsunto(comms, doc);
/*      */     }
/*  107 */     else if (_operacion.equals("asuntosArea")) {
/*  108 */       respuesta = asuntosArea(comms, doc);
/*      */     }
/*  110 */     else if (_operacion.equals("personas_area")) {
/*  111 */       respuesta = personasArea(comms, doc);
/*      */     }
/*  113 */     else if (_operacion.equals("caracteristicasAnidadas")) {
/*  114 */       respuesta = caracteristicasAnidadas(comms, doc);
/*      */     }
/*  116 */     else if (_operacion.equals("ejecutarProcedimiento")) {
/*  117 */       respuesta = ejecutarProcedimiento(comms, doc);
/*      */     }
/*  119 */     else if (_operacion.equals("buscarAreas")) {
/*  120 */       respuesta = buscarAreas(comms, doc);
/*      */     }
/*  122 */     else if (_operacion.equals("buscarServicios")) {
/*  123 */       respuesta = buscarServicios(comms, doc);
/*      */     }
/*  125 */     else if (_operacion.equals("buscarServiciosArea")) {
/*  126 */       respuesta = buscarServiciosArea(comms, doc);
/*      */     }
/*  128 */     else if (_operacion.equals("buscarAreasServicio")) {
/*  129 */       respuesta = buscarAreasServicio(comms, doc);
/*      */     }
/*  131 */     else if (_operacion.equals("buscarResponsablesServicio")) {
/*  132 */       respuesta = buscarResponsablesServicio(comms, doc);
/*      */     }
/*  134 */     else if (_operacion.equals("caracteristicas")) {
/*  135 */       respuesta = caracteristicas(comms, doc);
/*      */     }
/*  137 */     else if (_operacion.equals("valorCaracteristica")) {
/*  138 */       respuesta = valorCaracteristica(comms, doc);
/*  139 */     } else if (_operacion.equals("actividadesArea")) {
/*  140 */       respuesta = actividadesTipo(comms, doc);
/*  141 */     } else if (_operacion.equals("tipoActividad")) {
/*  142 */       respuesta = tipoActividad(comms, doc);
/*      */     }
/*  144 */     else if (_operacion.equals("buscarPersonas")) {
/*  145 */       respuesta = buscarPersonas(comms, doc);
/*      */     }
/*  147 */     else if (_operacion.equals("metaProyecto")) {
/*  148 */       respuesta = buscarMetaProyecto(comms, doc);
/*      */     }
/*  150 */     else if (_operacion.equals("metaPlan")) {
/*  151 */       respuesta = filtarMetasPlan(comms, doc);
/*      */     }
/*  153 */     else if (_operacion.equals("cargarSubprocesos")) {
/*  154 */       respuesta = cargarSubprocesos(comms, doc);
/*      */     }
/*  156 */     else if (_operacion.equals("serviciosSubproceso")) {
/*  157 */       respuesta = serviciosSubproceso(comms, doc);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  162 */     if (respuesta == null) {
/*  163 */       respuesta = error("Error");
/*      */     }
/*  165 */     respuesta = respuesta.replace('&', '_');
/*      */ 
/*      */ 
/*      */     
/*  169 */     comms.response.setContentType("text/xml");
/*  170 */     comms.response.setHeader("Content-Disposition", "inline;");
/*  171 */     comms.response.setStatus(200, "Good job");
/*  172 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*      */     try {
/*  174 */       byte[] byteContents = respuesta.getBytes();
/*  175 */       out.write(byteContents);
/*  176 */       out.flush();
/*      */     }
/*  178 */     catch (Exception e) {
/*  179 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + e.getMessage()));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Document newDocument() {
/*      */     try {
/*  190 */       DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
/*  191 */       DocumentBuilder parser = fact.newDocumentBuilder();
/*  192 */       return parser.newDocument();
/*      */     
/*      */     }
/*  195 */     catch (Exception ex) {
/*  196 */       Utilidades.writeError("subtipoproblema ", ex);
/*  197 */       ex.printStackTrace();
/*      */       
/*  199 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node createNode(Document doc, String name, String content) {
/*  211 */     Node node = doc.createElement(name);
/*  212 */     node.appendChild(doc.createTextNode(content));
/*  213 */     return node;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String documentToString(Document document) {
/*  223 */     String soapRequest = null;
/*  224 */     if (document != null) {
/*      */       try {
/*  226 */         OutputFormat format = new OutputFormat(document);
/*      */         
/*  228 */         format.setEncoding(ParametrosDTO.getString("encoding"));
/*      */         
/*  230 */         StringWriter strOut = new StringWriter();
/*  231 */         XMLSerializer XMLSerial = new XMLSerializer(strOut, format);
/*  232 */         XMLSerial.serialize(document.getDocumentElement());
/*  233 */         soapRequest = strOut.toString();
/*      */       }
/*  235 */       catch (Exception e) {
/*  236 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*  239 */     return soapRequest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attr newAttr(Document doc, String nombre, String valor) {
/*  253 */     Attr atrib = doc.createAttribute(nombre);
/*  254 */     atrib.setValue(valor);
/*  255 */     return atrib;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String error(String mensaje) {
/*      */     try {
/*  266 */       DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
/*  267 */       DocumentBuilder parser = fact.newDocumentBuilder();
/*  268 */       Document doc = parser.newDocument();
/*      */       
/*  270 */       Node root = doc.createElement("root");
/*      */       
/*  272 */       root.appendChild(createNode(doc, "estado", "error"));
/*  273 */       root.appendChild(createNode(doc, "mensaje", "" + mensaje));
/*      */       
/*  275 */       doc.appendChild(root);
/*  276 */       return documentToString(doc);
/*      */     }
/*  278 */     catch (Exception ex) {
/*  279 */       ex.printStackTrace();
/*      */       
/*  281 */       return "";
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String consultaFormaGeneral(HttpPresentationComms comms, Document doc, String operacion) throws HttpPresentationException, KeywordValueException {
/*  302 */     String tabla = (String)comms.session.getSessionData().get("tabla");
/*  303 */     String campo1 = (String)comms.session.getSessionData().get("campo1");
/*  304 */     String campo2 = (String)comms.session.getSessionData().get("campo2");
/*  305 */     int numerico = Integer.parseInt((String)comms.session.getSessionData().get("numerico"));
/*  306 */     int size1 = Integer.parseInt((String)comms.session.getSessionData().get("size1"));
/*  307 */     int size2 = Integer.parseInt((String)comms.session.getSessionData().get("size2"));
/*      */     
/*  309 */     FormaGeneralDAO rsFormaGeneral = new FormaGeneralDAO(tabla, campo1, campo2, numerico);
/*  310 */     if (!rsFormaGeneral.getEstadoConexion()) {
/*  311 */       return error("Error");
/*      */     }
/*      */     
/*  314 */     String scodigo = comms.request.getParameter("codigo");
/*      */     
/*  316 */     FormaGeneralDTO reg = rsFormaGeneral.cargarFormaGeneral(scodigo);
/*  317 */     rsFormaGeneral.close();
/*      */     
/*  319 */     if (reg == null) {
/*  320 */       return error("Error");
/*      */     }
/*      */     
/*      */     try {
/*  324 */       Node root = doc.createElement("root");
/*  325 */       doc.appendChild(root);
/*      */       
/*  327 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  328 */       root.appendChild(createNode(doc, "nombre", reg.getNombre()));
/*  329 */       return documentToString(doc);
/*      */     }
/*  331 */     catch (Exception ex) {
/*  332 */       Utilidades.writeError("subtipoproblema ", ex);
/*  333 */       ex.printStackTrace();
/*      */       
/*  335 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String responsableAsunto(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  354 */     int asunto = Integer.parseInt(comms.request.getParameter("asunto"));
/*      */     
/*      */     try {
/*  357 */       Node root = doc.createElement("root");
/*  358 */       doc.appendChild(root);
/*      */       
/*  360 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  362 */       TGeneralDAO rs = new TGeneralDAO();
/*  363 */       Collection<?> arr = rs.cargarResponsableAsunto(asunto);
/*  364 */       rs.close();
/*      */       
/*  366 */       Iterator<?> iterator = arr.iterator();
/*  367 */       while (iterator.hasNext()) {
/*  368 */         TGeneralDTO reg = (TGeneralDTO)iterator.next();
/*      */         
/*  370 */         Element mainNode = doc.createElement("persona");
/*  371 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoInt()));
/*  372 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*      */         
/*  374 */         root.appendChild(mainNode);
/*      */       } 
/*  376 */       return documentToString(doc);
/*      */     }
/*  378 */     catch (Exception ex) {
/*  379 */       ex.printStackTrace();
/*      */ 
/*      */       
/*  382 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String asuntosArea(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  406 */     int codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*      */     
/*      */     try {
/*  409 */       Node root = doc.createElement("root");
/*  410 */       doc.appendChild(root);
/*      */       
/*  412 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  414 */       TGeneralDAO rs = new TGeneralDAO();
/*  415 */       Collection<?> arr = rs.cargarAsuntos(codigoArea);
/*  416 */       rs.close();
/*      */       
/*  418 */       Iterator<?> iterator = arr.iterator();
/*  419 */       while (iterator.hasNext()) {
/*  420 */         TGeneralDTO reg = (TGeneralDTO)iterator.next();
/*      */         
/*  422 */         Element mainNode = doc.createElement("asunto");
/*  423 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoInt()));
/*  424 */         mainNode.setAttributeNode(newAttr(doc, "descripcion", "" + reg.getDescripcion()));
/*      */         
/*  426 */         root.appendChild(mainNode);
/*      */       } 
/*      */       
/*  429 */       PersonasAreaDAO rspa = new PersonasAreaDAO();
/*  430 */       Collection<?> arrpa = rspa.personasArea(codigoArea);
/*  431 */       rspa.close();
/*      */       
/*  433 */       Iterator<?> iteratorpa = arrpa.iterator();
/*  434 */       while (iteratorpa.hasNext()) {
/*  435 */         PersonasAreaDTO reg = (PersonasAreaDTO)iteratorpa.next();
/*      */         
/*  437 */         Element mainNode = doc.createElement("persona");
/*  438 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoEmpleado()));
/*  439 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getNombrePersona()));
/*      */         
/*  441 */         root.appendChild(mainNode);
/*      */       } 
/*      */       
/*  444 */       return documentToString(doc);
/*      */     }
/*  446 */     catch (Exception ex) {
/*  447 */       Utilidades.writeError("subtipoproblema ", ex);
/*  448 */       ex.printStackTrace();
/*      */       
/*  450 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String personasArea(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  468 */     int codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*      */     
/*      */     try {
/*  471 */       Node root = doc.createElement("root");
/*  472 */       doc.appendChild(root);
/*      */       
/*  474 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  476 */       PersonasAreaDAO rs = new PersonasAreaDAO();
/*  477 */       Collection<PersonasAreaDTO> arr = rs.personasArea(codigoArea);
/*  478 */       rs.close();
/*      */       
/*  480 */       Iterator<?> iterator = arr.iterator();
/*  481 */       while (iterator.hasNext()) {
/*  482 */         PersonasAreaDTO reg = (PersonasAreaDTO)iterator.next();
/*      */         
/*  484 */         Element mainNode = doc.createElement("persona");
/*  485 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoEmpleado()));
/*  486 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getNombrePersona()));
/*      */         
/*  488 */         root.appendChild(mainNode);
/*      */       } 
/*  490 */       return documentToString(doc);
/*      */     }
/*  492 */     catch (Exception ex) {
/*  493 */       ex.printStackTrace();
/*      */ 
/*      */       
/*  496 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String caracteristicasAnidadas(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  513 */     int caracteristica = Integer.parseInt(comms.request.getParameter("caracteristica"));
/*  514 */     int valor = Integer.parseInt(comms.request.getParameter("valor"));
/*      */     
/*      */     try {
/*  517 */       Node root = doc.createElement("root");
/*  518 */       doc.appendChild(root);
/*      */       
/*  520 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  522 */       CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/*  523 */       Collection<?> arr = rs.cargarAnidadas(caracteristica, valor);
/*  524 */       rs.close();
/*      */       
/*  526 */       Iterator<?> iterator = arr.iterator();
/*  527 */       while (iterator.hasNext()) {
/*  528 */         CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/*      */         
/*  530 */         Element mainNode = doc.createElement("caracteristica");
/*  531 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getValor()));
/*  532 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*      */         
/*  534 */         root.appendChild(mainNode);
/*      */       } 
/*  536 */       return documentToString(doc);
/*      */     }
/*  538 */     catch (Exception ex) {
/*  539 */       Utilidades.writeError("ConsultasAjax::caracteristicasAnidadas", ex);
/*  540 */       ex.printStackTrace();
/*      */ 
/*      */       
/*  543 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String ejecutarProcedimiento(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  558 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  559 */     int caracteristica = Integer.parseInt(comms.request.getParameter("caracteristica"));
/*      */     
/*  561 */     int anidada = 0;
/*      */     try {
/*  563 */       anidada = Integer.parseInt(comms.request.getParameter("anidada"));
/*      */     }
/*  565 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  570 */     String valor = comms.request.getParameter("valor");
/*  571 */     String proceso = comms.request.getParameter("proceso");
/*      */ 
/*      */     
/*  574 */     CaracteristicasDAO rs = new CaracteristicasDAO();
/*  575 */     RespuestaBD rta = rs.validarProcedimiento(idsol, caracteristica, valor, proceso, anidada);
/*  576 */     rs.close();
/*      */     
/*      */     try {
/*  579 */       Node root = doc.createElement("root");
/*  580 */       doc.appendChild(root);
/*      */       
/*  582 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  584 */       Element mainNode = doc.createElement("reg");
/*  585 */       mainNode.setAttributeNode(newAttr(doc, "msg", "" + rta.getCausal()));
/*  586 */       mainNode.setAttributeNode(newAttr(doc, "estado", "" + rta.getCerrarSolicitud()));
/*  587 */       root.appendChild(mainNode);
/*      */       try {
/*  589 */         Collection<?> arr = rta.getResultados();
/*  590 */         Iterator<?> iterator = arr.iterator();
/*  591 */         while (iterator.hasNext()) {
/*  592 */           ElemAreaDTO reg = (ElemAreaDTO)iterator.next();
/*      */           
/*  594 */           Element linea = doc.createElement("caracteristica");
/*  595 */           linea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigo()));
/*  596 */           linea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*      */           
/*  598 */           root.appendChild(linea);
/*      */         }
/*      */       
/*  601 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*  605 */       return documentToString(doc);
/*      */     }
/*  607 */     catch (Exception ex) {
/*  608 */       Utilidades.writeError("ConsultasAjax::ejecutarProcedimiento", ex);
/*  609 */       ex.printStackTrace();
/*      */ 
/*      */       
/*  612 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarAreas(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  630 */     String nombreArea = comms.request.getParameter("nombreArea");
/*      */     
/*  632 */     AreasDAO rs = new AreasDAO();
/*  633 */     if (!rs.getEstadoConexion()) {
/*  634 */       return error("Login");
/*      */     }
/*      */     
/*  637 */     Collection<AreasDTO> arr = rs.cargarActivas(nombreArea);
/*  638 */     rs.close();
/*      */     
/*      */     try {
/*  641 */       Node root = doc.createElement("root");
/*  642 */       doc.appendChild(root);
/*  643 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  644 */       Iterator<AreasDTO> iterator = arr.iterator();
/*  645 */       while (iterator.hasNext()) {
/*      */         
/*  647 */         AreasDTO reg = (AreasDTO)iterator.next();
/*  648 */         Element nTarea = doc.createElement("area");
/*  649 */         nTarea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigo()));
/*  650 */         nTarea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*  651 */         root.appendChild(nTarea);
/*      */       } 
/*  653 */       return documentToString(doc);
/*      */     }
/*  655 */     catch (Exception ex) {
/*  656 */       Utilidades.writeError("ConsultasAjax::buscarAreas", ex);
/*  657 */       ex.printStackTrace();
/*      */       
/*  659 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarServicios(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  675 */     String nombreServicio = comms.request.getParameter("nombreServicio");
/*  676 */     if (nombreServicio == null) {
/*  677 */       nombreServicio = "";
/*      */     }
/*  679 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*      */     
/*  681 */     ServiciosDAO rs = new ServiciosDAO();
/*  682 */     if (!rs.getEstadoConexion()) {
/*  683 */       return error("Login");
/*      */     }
/*      */     
/*  686 */     Collection<ServiciosDTO> arr = rs.cargarParecidos(nombreServicio, idNav);
/*      */     
/*  688 */     rs.close();
/*      */     
/*      */     try {
/*  691 */       Node root = doc.createElement("root");
/*  692 */       doc.appendChild(root);
/*  693 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  694 */       Iterator<ServiciosDTO> iterator = arr.iterator();
/*  695 */       while (iterator.hasNext()) {
/*      */         
/*  697 */         ServiciosDTO reg = (ServiciosDTO)iterator.next();
/*  698 */         Element nTarea = doc.createElement("servicio");
/*  699 */         nTarea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigo()));
/*  700 */         nTarea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*  701 */         root.appendChild(nTarea);
/*      */       } 
/*  703 */       return documentToString(doc);
/*      */     }
/*  705 */     catch (Exception ex) {
/*  706 */       Utilidades.writeError("ConsultasAjax::buscarServicios", ex);
/*  707 */       ex.printStackTrace();
/*      */       
/*  709 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarServiciosArea(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  726 */     int codigoArea = Integer.parseInt(comms.request.getParameter("area"));
/*  727 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*      */     
/*  729 */     ServiciosDAO rs = new ServiciosDAO();
/*  730 */     if (!rs.getEstadoConexion()) {
/*  731 */       return error("Login");
/*      */     }
/*      */     
/*  734 */     Collection<ServiciosDTO> arr = rs.cargarTodosDeArea(codigoArea, idNav);
/*  735 */     rs.close();
/*      */     
/*      */     try {
/*  738 */       Node root = doc.createElement("root");
/*  739 */       doc.appendChild(root);
/*  740 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  741 */       Iterator<ServiciosDTO> iterator = arr.iterator();
/*  742 */       while (iterator.hasNext()) {
/*      */         
/*  744 */         ServiciosDTO reg = (ServiciosDTO)iterator.next();
/*  745 */         Element nTarea = doc.createElement("servicio");
/*  746 */         nTarea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigo()));
/*  747 */         nTarea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*  748 */         root.appendChild(nTarea);
/*      */       } 
/*  750 */       return documentToString(doc);
/*      */     }
/*  752 */     catch (Exception ex) {
/*  753 */       Utilidades.writeError("ConsultasAjax::buscarServiciosArea", ex);
/*  754 */       ex.printStackTrace();
/*      */       
/*  756 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarAreasServicio(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  773 */     int codigoServicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*      */     
/*  775 */     AreasDAO rs = new AreasDAO();
/*  776 */     if (!rs.getEstadoConexion()) {
/*  777 */       return error("Login");
/*      */     }
/*      */     
/*  780 */     Collection<AreasDTO> arr = rs.cargarTodosParaServicioActivos(codigoServicio);
/*  781 */     rs.close();
/*      */ 
/*      */     
/*      */     try {
/*  785 */       Node root = doc.createElement("root");
/*  786 */       doc.appendChild(root);
/*  787 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  788 */       Iterator<AreasDTO> iterator = arr.iterator();
/*  789 */       while (iterator.hasNext()) {
/*  790 */         AreasDTO reg = (AreasDTO)iterator.next();
/*  791 */         Element nTarea = doc.createElement("area");
/*  792 */         nTarea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigo()));
/*  793 */         nTarea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*  794 */         root.appendChild(nTarea);
/*      */       } 
/*  796 */       return documentToString(doc);
/*      */     }
/*  798 */     catch (Exception ex) {
/*  799 */       Utilidades.writeError("ConsultasAjax::buscarAreasServicio", ex);
/*  800 */       ex.printStackTrace();
/*      */       
/*  802 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarResponsablesServicio(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  820 */     int codigoArea = Integer.parseInt(comms.request.getParameter("area"));
/*  821 */     int servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*  822 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*      */     
/*  824 */     ServiciosDAO sf = new ServiciosDAO();
/*  825 */     ServiciosDTO regServ = sf.cargarRegistro(servicio);
/*  826 */     sf.close();
/*      */     
/*  828 */     ServicioAreaDAO rsServArea = new ServicioAreaDAO();
/*  829 */     ServicioAreaDTO regServicioArea = rsServArea.getServicioArea(codigoArea, servicio);
/*  830 */     rsServArea.close();
/*      */     
/*  832 */     if (regServicioArea == null) {
/*  833 */       return error("No hay responsable para el servicio");
/*      */     }
/*  835 */     if (regServ.esEspecializado()) {
/*      */       try {
/*  837 */         SisUsuariosDAO pfactory = new SisUsuariosDAO();
/*  838 */         SisUsuariosDTO experto = pfactory.cargarPersonaArea(regServicioArea.getCodigoArea(), regServicioArea.getPersonaCargo());
/*      */         
/*  840 */         if (experto != null) {
/*  841 */           Node root = doc.createElement("root");
/*  842 */           doc.appendChild(root);
/*  843 */           root.appendChild(createNode(doc, "estado", "ok"));
/*      */           
/*  845 */           Element nTarea = doc.createElement("personas");
/*  846 */           nTarea.setAttributeNode(newAttr(doc, "codigo", "" + experto.getCodigoEmpleado()));
/*  847 */           nTarea.setAttributeNode(newAttr(doc, "nombre", "" + experto.getNombre()));
/*  848 */           root.appendChild(nTarea);
/*  849 */           return documentToString(doc);
/*      */         }
/*      */       
/*  852 */       } catch (Exception ex) {
/*  853 */         Utilidades.writeError("ConsultasAjax::buscarResponsablesServicio", ex);
/*  854 */         ex.printStackTrace();
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  860 */         Node root = doc.createElement("root");
/*  861 */         doc.appendChild(root);
/*  862 */         root.appendChild(createNode(doc, "estado", "ok"));
/*      */         
/*  864 */         Collection arr = new ArrayList();
/*  865 */         SisUsuariosDAO pf = new SisUsuariosDAO();
/*  866 */         if (regServ.esMultiple()) {
/*  867 */           arr = pf.cargarProveedoresMultiples(codigoArea, servicio);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  878 */           arr = pf.cargarActivoDeArea(codigoArea);
/*      */         } 
/*  880 */         Iterator iterator = arr.iterator();
/*  881 */         while (iterator.hasNext()) {
/*  882 */           SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/*  883 */           if (regPersona.getCodigoEmpleado() != idNav) {
/*  884 */             Element nTarea = doc.createElement("personas");
/*  885 */             nTarea.setAttributeNode(newAttr(doc, "codigo", "" + regPersona.getCodigoEmpleado()));
/*  886 */             nTarea.setAttributeNode(newAttr(doc, "nombre", "" + regPersona.getNombre()));
/*  887 */             root.appendChild(nTarea);
/*      */           } 
/*      */         } 
/*      */         
/*  891 */         return documentToString(doc);
/*      */       }
/*  893 */       catch (Exception ex) {
/*  894 */         Utilidades.writeError("ConsultasAjax::buscarResponsablesServicio", ex);
/*  895 */         ex.printStackTrace();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  902 */       Node root = doc.createElement("root");
/*  903 */       doc.appendChild(root);
/*  904 */       root.appendChild(createNode(doc, "estado", "ok"));
/*  905 */       Element nTarea = doc.createElement("personas");
/*  906 */       nTarea.setAttributeNode(newAttr(doc, "codigo", ""));
/*  907 */       nTarea.setAttributeNode(newAttr(doc, "nombre", "No hay responsables para atender este servicio"));
/*  908 */       return documentToString(doc);
/*      */     
/*      */     }
/*  911 */     catch (Exception ex) {
/*  912 */       Utilidades.writeError("ConsultasAjax::buscarResponsablesServicio", ex);
/*  913 */       ex.printStackTrace();
/*      */       
/*  915 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String caracteristicas(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  931 */     int servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*      */     
/*      */     try {
/*  934 */       Node root = doc.createElement("root");
/*  935 */       doc.appendChild(root);
/*      */       
/*  937 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */ 
/*      */       
/*  940 */       CaracteristicasServicioDAO rs = new CaracteristicasServicioDAO();
/*  941 */       Collection<CaracteristicasServicioDTO> arr = rs.cargarParaServicio(servicio);
/*  942 */       rs.close();
/*      */       
/*  944 */       Iterator<CaracteristicasServicioDTO> iterator = arr.iterator();
/*  945 */       while (iterator.hasNext()) {
/*  946 */         CaracteristicasServicioDTO reg = (CaracteristicasServicioDTO)iterator.next();
/*      */         
/*  948 */         Element mainNode = doc.createElement("reg");
/*  949 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoCaracteristica()));
/*  950 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*      */         
/*  952 */         root.appendChild(mainNode);
/*      */       } 
/*  954 */       return documentToString(doc);
/*      */     }
/*  956 */     catch (Exception ex) {
/*  957 */       ex.printStackTrace();
/*      */ 
/*      */       
/*  960 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String valorCaracteristica(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/*  977 */     int caracteristica = Integer.parseInt(comms.request.getParameter("caracteristica"));
/*      */     
/*      */     try {
/*  980 */       Node root = doc.createElement("root");
/*  981 */       doc.appendChild(root);
/*      */       
/*  983 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/*  985 */       CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/*  986 */       Collection<CaracteristicasValorDTO> arr = rs.cargarParaCaracteristica(caracteristica);
/*  987 */       rs.close();
/*      */       
/*  989 */       Iterator<CaracteristicasValorDTO> iterator = arr.iterator();
/*  990 */       while (iterator.hasNext()) {
/*      */         
/*  992 */         CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/*  993 */         Element mainNode = doc.createElement("reg");
/*  994 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getValor()));
/*  995 */         mainNode.setAttributeNode(newAttr(doc, "nombre", "" + reg.getDescripcion()));
/*      */         
/*  997 */         root.appendChild(mainNode);
/*      */       } 
/*  999 */       return documentToString(doc);
/*      */     }
/* 1001 */     catch (Exception ex) {
/* 1002 */       ex.printStackTrace();
/*      */ 
/*      */       
/* 1005 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String actividadesTipo(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1020 */     int caracteristica = Integer.parseInt(comms.request.getParameter("area"));
/*      */     
/*      */     try {
/* 1023 */       Node root = doc.createElement("root");
/* 1024 */       doc.appendChild(root);
/*      */       
/* 1026 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1028 */       PoaActividadesDAO rs = new PoaActividadesDAO();
/* 1029 */       Collection<PoaActividadesDTO> arr = rs.cargarTodos("", "", caracteristica, "A");
/* 1030 */       rs.close();
/*      */       
/* 1032 */       Iterator<PoaActividadesDTO> iterator = arr.iterator();
/* 1033 */       while (iterator.hasNext()) {
/*      */         
/* 1035 */         PoaActividadesDTO reg = (PoaActividadesDTO)iterator.next();
/* 1036 */         Element mainNode = doc.createElement("actividad");
/* 1037 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoActividad()));
/* 1038 */         mainNode.setAttributeNode(newAttr(doc, "descripcion", "" + reg.getDescripcion()));
/*      */         
/* 1040 */         root.appendChild(mainNode);
/*      */       } 
/* 1042 */       return documentToString(doc);
/*      */     }
/* 1044 */     catch (Exception ex) {
/* 1045 */       ex.printStackTrace();
/*      */ 
/*      */       
/* 1048 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String tipoActividad(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1063 */     int caracteristica = Integer.parseInt(comms.request.getParameter("actividad"));
/*      */ 
/*      */     
/*      */     try {
/* 1067 */       PoaActividadesDAO rs = new PoaActividadesDAO();
/* 1068 */       PoaActividadesDTO actividad = rs.cargarRegistro(caracteristica);
/* 1069 */       rs.close();
/*      */ 
/*      */       
/* 1072 */       if (actividad == null) {
/* 1073 */         return error("Actividad No Existe");
/*      */       }
/*      */ 
/*      */       
/* 1077 */       Node root = doc.createElement("root");
/*      */       
/* 1079 */       doc.appendChild(root);
/*      */       
/* 1081 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1083 */       Element nTarea = doc.createElement("tipo");
/* 1084 */       nTarea.setAttributeNode(newAttr(doc, "codigo", "" + actividad.getTipoActividad()));
/* 1085 */       nTarea.setAttributeNode(newAttr(doc, "nombre", "" + actividad.getNombreTipoActividad()));
/* 1086 */       root.appendChild(nTarea);
/* 1087 */       return documentToString(doc);
/*      */     }
/* 1089 */     catch (Exception ex) {
/* 1090 */       ex.printStackTrace();
/*      */ 
/*      */       
/* 1093 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarPersonas(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1110 */     String apellidos = comms.request.getParameter("apellidosCliente");
/* 1111 */     System.out.println(apellidos);
/* 1112 */     String nombreCliente = comms.request.getParameter("nombreCliente");
/* 1113 */     if (nombreCliente == null) nombreCliente = "";
/*      */     
/* 1115 */     SisUsuariosDAO rs = new SisUsuariosDAO();
/*      */     
/* 1117 */     Collection arr = rs.cargarSimilares(apellidos, nombreCliente);
/*      */     
/*      */     try {
/* 1120 */       Node root = doc.createElement("root");
/* 1121 */       doc.appendChild(root);
/* 1122 */       root.appendChild(createNode(doc, "estado", "ok"));
/* 1123 */       Iterator iterator = arr.iterator();
/* 1124 */       while (iterator.hasNext()) {
/* 1125 */         SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/* 1126 */         Element nTarea = doc.createElement("persona");
/* 1127 */         nTarea.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCodigoEmpleado()));
/* 1128 */         nTarea.setAttributeNode(newAttr(doc, "nombre", "" + reg.getApellidos() + " " + reg.getNombres()));
/*      */         
/* 1130 */         root.appendChild(nTarea);
/*      */       } 
/* 1132 */       return documentToString(doc);
/*      */     }
/* 1134 */     catch (Exception ex) {
/* 1135 */       ex.printStackTrace();
/*      */       
/* 1137 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String buscarMetaProyecto(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1152 */     int caracteristica = Integer.parseInt(comms.request.getParameter("metaPlan"));
/*      */     
/*      */     try {
/* 1155 */       Node root = doc.createElement("root");
/* 1156 */       doc.appendChild(root);
/*      */       
/* 1158 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1160 */       PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 1161 */       Collection<PoaMetasProyectoDTO> arr = rs.cargarTodos(caracteristica);
/* 1162 */       rs.close();
/*      */       
/* 1164 */       Iterator<PoaMetasProyectoDTO> iterator = arr.iterator();
/* 1165 */       while (iterator.hasNext()) {
/*      */         
/* 1167 */         PoaMetasProyectoDTO reg = (PoaMetasProyectoDTO)iterator.next();
/* 1168 */         Element mainNode = doc.createElement("meta");
/* 1169 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getCODIGOMETAPROYECTO()));
/* 1170 */         mainNode.setAttributeNode(newAttr(doc, "descripcion", "" + reg.getDescripcion()));
/*      */         
/* 1172 */         root.appendChild(mainNode);
/*      */       } 
/* 1174 */       return documentToString(doc);
/*      */     }
/* 1176 */     catch (Exception ex) {
/* 1177 */       ex.printStackTrace();
/*      */ 
/*      */       
/* 1180 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String filtarMetasPlan(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1196 */     int codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*      */     
/*      */     try {
/* 1199 */       Node root = doc.createElement("root");
/* 1200 */       doc.appendChild(root);
/*      */       
/* 1202 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1204 */       PoaMetasPlanDesarrolloDAO rs = new PoaMetasPlanDesarrolloDAO();
/* 1205 */       Collection<PoaMetasPlanDesarrolloDTO> arr = rs.metasArea(codigoArea);
/* 1206 */       rs.close();
/*      */       
/* 1208 */       Iterator<PoaMetasPlanDesarrolloDTO> iterator = arr.iterator();
/* 1209 */       while (iterator.hasNext()) {
/*      */         
/* 1211 */         PoaMetasPlanDesarrolloDTO reg = (PoaMetasPlanDesarrolloDTO)iterator.next();
/*      */         
/* 1213 */         Element mainNode = doc.createElement("metaPlan");
/* 1214 */         mainNode.setAttributeNode(newAttr(doc, "codigoMeta", "" + reg.getCodigoMetaPlan()));
/* 1215 */         mainNode.setAttributeNode(newAttr(doc, "meta", "" + reg.getDescripcion()));
/*      */         
/* 1217 */         root.appendChild(mainNode);
/*      */       } 
/* 1219 */       return documentToString(doc);
/*      */     }
/* 1221 */     catch (Exception ex) {
/* 1222 */       ex.printStackTrace();
/*      */ 
/*      */ 
/*      */       
/* 1226 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String cargarSubprocesos(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1241 */     String proceso = comms.request.getParameter("proceso");
/*      */     
/*      */     try {
/* 1244 */       Node root = doc.createElement("root");
/* 1245 */       doc.appendChild(root);
/*      */       
/* 1247 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1249 */       CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 1250 */       Collection<CalSubProcesosDTO> arr = rs.cargarDeProceso(proceso);
/* 1251 */       rs.close();
/*      */       
/* 1253 */       Iterator<CalSubProcesosDTO> iterator = arr.iterator();
/* 1254 */       while (iterator.hasNext()) {
/*      */         
/* 1256 */         CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/*      */         
/* 1258 */         Element mainNode = doc.createElement("subproceso");
/* 1259 */         mainNode.setAttributeNode(newAttr(doc, "codigo", "" + reg.getSubproceso()));
/* 1260 */         mainNode.setAttributeNode(newAttr(doc, "descripcion", "" + reg.getDescripcion()));
/*      */         
/* 1262 */         root.appendChild(mainNode);
/*      */       } 
/* 1264 */       return documentToString(doc);
/*      */     }
/* 1266 */     catch (Exception ex) {
/* 1267 */       ex.printStackTrace();
/*      */ 
/*      */ 
/*      */       
/* 1271 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String serviciosSubproceso(HttpPresentationComms comms, Document doc) throws HttpPresentationException, KeywordValueException {
/* 1287 */     String proceso = comms.request.getParameter("proceso");
/* 1288 */     String subproceso = comms.request.getParameter("subproceso");
/* 1289 */     String estado = comms.request.getParameter("estado");
/*      */     
/*      */     try {
/* 1292 */       Node root = doc.createElement("root");
/* 1293 */       doc.appendChild(root);
/*      */       
/* 1295 */       root.appendChild(createNode(doc, "estado", "ok"));
/*      */       
/* 1297 */       CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 1298 */       Collection<CalSubProcesosDTO> arr = rs.cargarServicios(proceso, subproceso, estado);
/* 1299 */       rs.close();
/*      */       
/* 1301 */       Iterator<CalSubProcesosDTO> iterator = arr.iterator();
/* 1302 */       while (iterator.hasNext()) {
/*      */         
/* 1304 */         CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/*      */         
/* 1306 */         Element mainNode = doc.createElement("servicios");
/* 1307 */         mainNode.setAttributeNode(newAttr(doc, "servicio", "" + reg.getServicio()));
/* 1308 */         mainNode.setAttributeNode(newAttr(doc, "descripcion", "" + reg.getDescripcion()));
/* 1309 */         mainNode.setAttributeNode(newAttr(doc, "estado", "" + reg.getEstado()));
/*      */         
/* 1311 */         root.appendChild(mainNode);
/*      */       } 
/* 1313 */       return documentToString(doc);
/*      */     }
/* 1315 */     catch (Exception ex) {
/* 1316 */       ex.printStackTrace();
/*      */ 
/*      */ 
/*      */       
/* 1320 */       return null;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ConsultasAjax.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */