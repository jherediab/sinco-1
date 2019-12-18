//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.presentation;

import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
import com.lutris.appserver.server.httpPresentation.HttpPresentation;
import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
import com.lutris.util.KeywordValueException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import sinco.business.CalDocumentosDTO;
import sinco.business.CalProcesosDTO;
import sinco.business.CalResponsablesDTO;
import sinco.business.CalSubProcesosDTO;
import sinco.business.CalTiposDocumentoDTO;
import sinco.business.ParametrosDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.data.CalDocumentosDAO;
import sinco.data.CalProcesosDAO;
import sinco.data.CalResponsablesDAO;
import sinco.data.CalSubProcesosDAO;
import sinco.data.CalTiposDocumentoDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.ManejadorArchivos;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class CalDocumentosAct implements HttpPresentation {
    String elUsuario = "";
    private CalDocumentosActHTML pagHTML;

    public CalDocumentosAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
            this.elUsuario = "" + comms.session.getUser().getName();
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "X";
            }

            if (!_operacion.equals("C") && !_operacion.equals("M") && !_operacion.equals("E")) {
                if (_operacion.equals("version")) {
                    this.cambioVersion(comms, idNav);
                } else {
                    Varios o = new Varios();
                    boolean bActualizar = o.tienePermiso(miGrupo, "cal_documentos_actualizar");
                    this.pagHTML = (CalDocumentosActHTML)comms.xmlcFactory.create(CalDocumentosActHTML.class);
                    boolean bExisteDoc = false;
                    if (!_operacion.equals("L") && !_operacion.equals("X")) {
                        if (_operacion.equals("P") || _operacion.equals("Nuevo") || _operacion.equals("R")) {
                            String elScript = this.generarAreas();
                            this.pagHTML.setTextScriptAreas("" + elScript);
                            bExisteDoc = this.editar(comms);
                        }
                    } else {
                        this.listar(comms);
                    }

                    if (_operacion.equals("P") || _operacion.equals("Nuevo") || _operacion.equals("R")) {
                        HTMLElement sel = this.pagHTML.getElementTrConsulta();
                        sel.getParentNode().removeChild(sel);
                        sel = this.pagHTML.getElementTrResultados();
                        sel.getParentNode().removeChild(sel);
                        if (_operacion.equals("P")) {
                            bExisteDoc = true;
                        }

                        if (!bExisteDoc) {
                            sel = this.pagHTML.getElementMiBotonD();
                            sel.getParentNode().removeChild(sel);
                            sel = this.pagHTML.getElementMiBotonB();
                            sel.getParentNode().removeChild(sel);
                        }

                        if (_operacion.equals("Nuevo") || _operacion.equals("R")) {
                            String proceso = comms.request.getParameter("proceso");
                            if (proceso == null) {
                                proceso = "";
                            }

                            this.comboProcesos(comms, proceso);
                            String doc = comms.request.getParameter("Doc");
                            if (doc == null || doc.length() == 0) {
                                doc = "X";
                            }

                            String subProceso;
                            if (!doc.equals("Normograma") && !doc.equals("Riesgos") && !doc.equals("Caracterizacion")) {
                                if (_operacion.equals("Nuevo")) {
                                    subProceso = comms.request.getParameter("subProcesoC");
                                    if (subProceso == null) {
                                        subProceso = "";
                                    }

                                    this.comboSubProcesos(comms, proceso, subProceso);
                                } else {
                                    subProceso = comms.request.getParameter("subproceso");
                                    if (subProceso == null) {
                                        subProceso = "";
                                    }

                                    this.comboSubProcesos(comms, proceso, subProceso);
                                }
                            } else {
                                subProceso = comms.request.getParameter("subproceso");
                                if (subProceso == null) {
                                    subProceso = "";
                                }

                                HTMLSelectElement combo;
                                if (doc.equals("Normograma")) {
                                    combo = this.pagHTML.getElementTipoDocumento();
                                    this.comboTipoDocumentos(combo, "N", false);
                                } else if (doc.equals("Riesgos")) {
                                    combo = this.pagHTML.getElementTipoDocumento();
                                    this.comboTipoDocumentos(combo, "R", false);
                                } else {
                                    combo = this.pagHTML.getElementTipoDocumento();
                                    this.comboTipoDocumentos(combo, "W", false);
                                }

                                this.pagHTML.getElementTipoDocumento().setDisabled(true);
                                this.pagHTML.getElementProceso().setDisabled(true);
                                HTMLTableCellElement s2;
                                if (subProceso.length() > 0) {
                                    this.comboSubProcesos(proceso, subProceso);
                                    this.pagHTML.getElementSubproceso().setDisabled(true);
                                } else {
                                    try {
                                        HTMLElement sel3 = this.pagHTML.getElementSubpro();
                                        sel3.getParentNode().removeChild(sel3);
                                        s2 = this.pagHTML.getElementSubpro2();
                                        s2.getParentNode().removeChild(s2);
                                        HTMLElement s = this.pagHTML.getElementPro();
                                        s.setAttribute("colspan", "3");
                                    } catch (Exception var18) {
                                    }
                                }

                                HTMLElement elemento = this.pagHTML.getElementDoc();
                                elemento.setAttribute("value", doc);
                                s2 = this.pagHTML.getElementTitulo();
                                CalProcesosDAO reg = new CalProcesosDAO();
                                CalSubProcesosDAO regsub = new CalSubProcesosDAO();
                                CalSubProcesosDTO r2 = regsub.cargarRegistro(proceso, subProceso);
                                CalProcesosDTO r = reg.cargarRegistro(proceso);
                                reg.close();
                                regsub.close();
                                if (subProceso.length() > 0) {
                                    if (doc.equals("Normograma")) {
                                        s2.setTextContent("Normograma asociado al SubProceso " + r2.getDescripcion());
                                    } else if (doc.equals("Riesgos")) {
                                        s2.setTextContent("Mapa de Riesgos asociado al SubProceso " + r2.getDescripcion());
                                    } else {
                                        s2.setTextContent("Caracterizacion asociada al SubProceso " + r2.getDescripcion());
                                    }
                                } else if (doc.equals("Normograma")) {
                                    s2.setTextContent("Normograma asociado al Proceso " + r.getDescripcion());
                                } else {
                                    s2.setTextContent("Mapa de Riesgos asociado al Proceso " + r.getDescripcion());
                                }
                            }
                        }
                    }

                    if (!bActualizar) {
                        HTMLElement sel = this.pagHTML.getElementTrAct();
                        sel.getParentNode().removeChild(sel);
                    }

                    this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
                    comms.response.writeDOM(this.pagHTML);
                }
            } else {
                this.creacion(comms, _operacion);
            }
        }
    }

    private int creacion(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        String codigo = comms.request.getParameter("codigo");
        String tipoDocumento = comms.request.getParameter("tipoDocumento");
        String proceso = comms.request.getParameter("proceso");
        String subproceso = comms.request.getParameter("subproceso");
        String servicio = comms.request.getParameter("servicio");
        String descripcion = comms.request.getParameter("descripcion");
        String observaciones = comms.request.getParameter("observaciones");
        String version = comms.request.getParameter("version");
        String fechaVersion = comms.request.getParameter("fechaVersion");
        String fechaEmision = comms.request.getParameter("fechaEmision");
        String estado = comms.request.getParameter("estado");
        String objetivoHidden = comms.request.getParameter("objetivoHidden");
        if (objetivoHidden == null) {
            objetivoHidden = "";
        }

        String doc = comms.request.getParameter("Doc");
        if (doc == null || doc.length() == 0) {
            doc = "";
        }

        int orden = 0;

        try {
            orden = Integer.parseInt(comms.request.getParameter("orden"));
        } catch (Exception var24) {
        }

        if (subproceso == null) {
            subproceso = "";
        }

        if (servicio == null) {
            servicio = "";
        }

        CalDocumentosDAO rs = new CalDocumentosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            CalDocumentosDTO reg = rs.cargarDocumento(codigo);
            boolean actualizar = false;
            if (reg != null) {
                actualizar = true;
            }

            boolean rta = false;
            if (!actualizar) {
                rta = rs.crearRegistro(codigo, tipoDocumento, proceso, subproceso, servicio, descripcion, version, fechaVersion, fechaEmision, estado, observaciones, orden, doc, this.elUsuario);
            } else {
                rta = rs.modificarRegistro(codigo, tipoDocumento, proceso, subproceso, servicio, descripcion, version, fechaVersion, fechaEmision, estado, observaciones, orden, this.elUsuario);
            }

            if (!rta) {
                rs.close();
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActDocumento"));
            } else {
                Enumeration enumera = comms.request.getParameterNames();
                rs.eliminarAreasDistribuido(codigo);
                rs.eliminarResponsables(codigo);

                String param;
                while(enumera.hasMoreElements()) {
                    param = (String)enumera.nextElement();
                    int codigoArea;
                    if (param.substring(0, 3).equals("aa_")) {
                        codigoArea = Integer.parseInt(comms.request.getParameter(param));
                        if (codigoArea > 0) {
                            rs.crearAreasDistribuido(codigo, codigoArea, this.elUsuario);
                        }
                    } else if (param.substring(0, 3).equals("ar_")) {
                        codigoArea = Integer.parseInt(comms.request.getParameter(param));
                        if (codigoArea > 0) {
                            rs.crearResponsable(codigo, codigoArea, this.elUsuario);
                        }
                    }
                }

                rs.close();
                param = "CalDocumentosAct.po?_operacion=P&Doc=" + doc + "&codigo=" + codigo + "&objetivoHidden=" + objetivoHidden;
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(param));
            }
        }
    }

    private int cambioVersion(HttpPresentationComms comms, String usuario) throws HttpPresentationException, KeywordValueException {
        String codigo = comms.request.getParameter("codigo");
        CalDocumentosDAO rs = new CalDocumentosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            CalDocumentosDTO reg = rs.cargarDocumento(codigo);
            if (reg == null) {
                rs.close();
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=CalDocumentoNoExiste"));
            } else {
                String origen = ParametrosDTO.getString("cal_archivos") + "/" + reg.getNombreArchivoWord();
                String nombreDestinoWord = reg.getVersion() + "_" + reg.getNombreArchivoWord();
                String destino = ParametrosDTO.getString("cal_archivos") + "/" + nombreDestinoWord;
                ManejadorArchivos mf = new ManejadorArchivos();
                if (mf.exists(origen) == 1) {
                    mf.copy(origen, destino);
                }

                origen = ParametrosDTO.getString("cal_archivos") + "/" + reg.getNombreArchivoPdf();
                String nombreDestinoPdf = reg.getVersion() + "_" + reg.getNombreArchivoPdf();
                destino = ParametrosDTO.getString("cal_archivos") + "/" + nombreDestinoPdf;
                if (mf.exists(origen) == 1) {
                    mf.copy(origen, destino);
                }

                rs.crearVersion(codigo, reg.getVersion(), nombreDestinoWord, nombreDestinoPdf, usuario);
                rs.close();
                String sPagina = "CalDocumentosAct.po?_operacion=P&codigo=" + codigo;
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private boolean editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigo = comms.request.getParameter("codigo");
        if (codigo == null) {
            codigo = "";
        }

        String descripcion = comms.request.getParameter("descripcion");
        if (descripcion == null) {
            descripcion = "";
        }

        String objetivoHidden = comms.request.getParameter("objetivoHidden");
        if (objetivoHidden == null) {
            objetivoHidden = "";
        }

        String observacion = comms.request.getParameter("observaciones");
        if (observacion == null) {
            observacion = "";
        }

        String version = comms.request.getParameter("version");
        if (version == null) {
            version = "";
        }

        String fechaRevision = comms.request.getParameter("fechaVersion");
        if (fechaRevision == null) {
            fechaRevision = "";
        }

        String fechaEmision = comms.request.getParameter("fechaEmision");
        if (fechaEmision == null) {
            fechaEmision = "";
        }

        String doc = comms.request.getParameter("Doc");
        if (doc == null) {
            doc = "";
        }

        int orden = 0;

        try {
            orden = Integer.parseInt(comms.request.getParameter("orden"));
        } catch (Exception var21) {
        }

        this.pagHTML.getElementObjetivoHidden().setValue("" + objetivoHidden);
        this.pagHTML.getElementIdCodigo().setValue("" + codigo);
        this.pagHTML.getElementIdDescripcion().setValue("" + descripcion);
        this.pagHTML.getElementObservaciones().setValue("" + observacion);
        this.pagHTML.getElementIdVersion().setValue("" + version);
        this.pagHTML.getElementOrden().setValue("" + orden);
        this.pagHTML.getElementIdFechaVersion().setValue("" + fechaRevision);
        this.pagHTML.getElementFechaEmision().setValue("" + fechaEmision);
        if (codigo.length() == 0) {
            HTMLSelectElement combo = this.pagHTML.getElementIdEstado();
            this.comboMultivalores(combo, "CAL_ESTADO_DOCUMENTOS", "", false);
            String script = "var mAsignada=new Array(0);";
            this.pagHTML.setTextAreasAsignadas("" + script);
            script = "var mResponsables=new Array(0);";
            this.pagHTML.setTextResponsables("" + script);
            return false;
        } else {
            CalDocumentosDAO rs = new CalDocumentosDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                CalDocumentosDTO reg = rs.cargarDocumento(codigo);
                String url;
                HTMLSelectElement combox;
                String script;
                if (reg != null) {
                    this.pagHTML.getElementIdCodigo().setReadOnly(true);
                    this.pagHTML.getElementIdDescripcion().setValue("" + reg.getDescripcion());
                    this.pagHTML.getElementObservaciones().setValue("" + reg.getObservaciones());
                    this.pagHTML.getElementIdVersion().setValue("" + reg.getVersion());
                    this.pagHTML.getElementIdFechaVersion().setValue("" + reg.getFechaVersion());
                    this.pagHTML.getElementFechaEmision().setValue("" + reg.getFechaEmision());
                    if (reg.getOrden() > 0) {
                        this.pagHTML.getElementOrden().setValue("" + reg.getOrden());
                    }

                    this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
                    this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
                    this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
                    this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
                    this.pagHTML.getElementOperacion().setValue("M");
                    this.pagHTML.getElementEstadoh().setValue("" + reg.getEstado());
                    HTMLAnchorElement enlaceC;
                    if (reg.getExistePdf().equals("S")) {
                        url = "CalVerDocumento.po?numeroDocumento=" + reg.getNombreArchivoPdf() + "&tipoDocumento=P&_operacion=VDC";
                        enlaceC = this.pagHTML.getElementDocumentoPdf();
                        enlaceC.appendChild(this.newhref(reg.getNombreArchivoPdf(), url, true));
                    }

                    if (reg.getExisteWord().equals("S")) {
                        url = "CalVerDocumento.po?numeroDocumento=" + reg.getNombreArchivoWord() + "&tipoDocumento=W&_operacion=VDC";
                        enlaceC = this.pagHTML.getElementDocumentoWord();
                        enlaceC.appendChild(this.newhref(reg.getNombreArchivoWord(), url, true));
                    }

                    HTMLSelectElement combo = this.pagHTML.getElementIdEstado();
                    this.comboMultivalores(combo, "CAL_ESTADO_DOCUMENTOS", "" + reg.getEstado(), false);
                    HTMLTableCellElement s2;
                    if (!reg.getTipoDocumento().equals("N") && !reg.getTipoDocumento().equals("R") && !reg.getTipoDocumento().equals("W")) {
                        combo = this.pagHTML.getElementTipoDocumento();
                        this.comboTipoDocumentos(combo, "" + reg.getTipoDocumento(), false, "N", "R");
                    } else {
                        combo = this.pagHTML.getElementTipoDocumento();
                        this.comboTipoDocumentos(combo, "" + reg.getTipoDocumento(), false);
                        this.pagHTML.getElementTipoDocumento().setDisabled(true);
                        this.pagHTML.getElementProceso().setDisabled(true);
                        if (reg.getSubproceso().equals("")) {
                            try {
                                HTMLElement sel3 = this.pagHTML.getElementSubpro();
                                sel3.getParentNode().removeChild(sel3);
                                s2 = this.pagHTML.getElementSubpro2();
                                s2.getParentNode().removeChild(s2);
                                HTMLElement s = this.pagHTML.getElementPro();
                                s.setAttribute("colspan", "3");
                            } catch (Exception var20) {
                            }
                        } else {
                            this.pagHTML.getElementSubproceso().setDisabled(true);
                        }
                    }

                    if (reg.getEstado().equals("R")) {
                    }

                    this.comboProcesos(comms, reg.getProceso());
                    this.comboSubProcesos(comms, reg.getProceso(), reg.getSubproceso());
                    if (!doc.equals("Normograma") && !doc.equals("Riesgos") && !doc.equals("Caracterizacion")) {
                        if (reg.getSubproceso().length() > 0) {
                        }
                    } else {
                        if (doc.equals("Normograma")) {
                            combox = this.pagHTML.getElementTipoDocumento();
                            this.comboTipoDocumentos(combox, "N", false);
                        } else if (doc.equals("Riesgos")) {
                            combox = this.pagHTML.getElementTipoDocumento();
                            this.comboTipoDocumentos(combox, "R", false);
                        } else {
                            combox = this.pagHTML.getElementTipoDocumento();
                            this.comboTipoDocumentos(combox, "W", false);
                        }

                        this.pagHTML.getElementTipoDocumento().setDisabled(true);
                        this.pagHTML.getElementProceso().setDisabled(true);
                        HTMLElement elemento = this.pagHTML.getElementDoc();
                        elemento.setAttribute("value", doc);
                        s2 = this.pagHTML.getElementTitulo();
                        CalProcesosDAO registro = new CalProcesosDAO();
                        CalProcesosDTO r = registro.cargarRegistro(reg.getProceso());
                        registro.close();
                        if (reg.getSubproceso().length() > 0) {
                            if (doc.equals("Normograma")) {
                                s2.setTextContent("Normograma asociado al SubProceso " + reg.getNombreSubProceso());
                            } else if (doc.equals("Riesgos")) {
                                s2.setTextContent("Mapa de Riesgos asociado al SubProceso " + reg.getNombreSubProceso());
                            } else {
                                s2.setTextContent("Caracterizacion asociada al SubProceso " + reg.getNombreSubProceso());
                            }
                        } else if (doc.equals("Normograma")) {
                            s2.setTextContent("Normograma asociado al Proceso " + r.getDescripcion());
                        } else {
                            s2.setTextContent("Mapa de Riesgos asociado al Proceso " + r.getDescripcion());
                        }
                    }

                    Collection<CalDocumentosDTO> arrDistribuido = rs.cargarDistribuido(codigo);
                    script = "var mAsignada=new Array(" + arrDistribuido.size() + ");";
                    script = script + " var iContA=0;";

                    CalDocumentosDTO regD;
                    for(Iterator iteratorDoc = arrDistribuido.iterator(); iteratorDoc.hasNext(); script = script + " mAsignada[iContA++]=new add_area(" + regD.getCodigoArea() + ",'" + regD.getDescripcion() + "');") {
                        regD = (CalDocumentosDTO)iteratorDoc.next();
                    }

                    this.pagHTML.setTextAreasAsignadas("" + script);
                    Collection<CalDocumentosDTO> arrResponsables = rs.cargarResponsables(codigo);
                    script = "var mResponsables=new Array(" + arrResponsables.size() + ");";
                    script = script + " var iContB=0;";

                    //CalDocumentosDTO regD;
                    for(Iterator iteratorRes = arrResponsables.iterator(); iteratorRes.hasNext(); script = script + " mResponsables[iContB++]=new add_area(" + regD.getCodigoArea() + ",'" + regD.getDescripcion() + "');") {
                        regD = (CalDocumentosDTO)iteratorRes.next();
                    }

                    this.pagHTML.setTextResponsables("" + script);
                } else {
                    url = comms.request.getParameter("tipoDocumento");
                    if (url == null) {
                        url = "";
                    }

                    combox = this.pagHTML.getElementTipoDocumento();
                    this.comboTipoDocumentos(combox, url, false, "N", "R");
                    combox = this.pagHTML.getElementIdEstado();
                    this.comboMultivalores(combox, "CAL_ESTADO_DOCUMENTOS", "", false);
                    script = "var mAsignada=new Array(0);";
                    this.pagHTML.setTextAreasAsignadas("" + script);
                    script = "var mResponsables=new Array(0);";
                    this.pagHTML.setTextResponsables("" + script);
                }

                rs.close();
                return false;
            }
        }
    }

    private void cargarTablaProcesos(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
        CalProcesosDAO rs = new CalProcesosDAO();
        Collection<CalProcesosDTO> arr = rs.cargarTablaProcesos();
        rs.close();
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            CalProcesosDTO reg = (CalProcesosDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getProceso());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getProceso())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
        rs.close();
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

        arr.clear();
    }

    private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla, defecto);
        rs.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void comboProcesos(String defecto) {
        HTMLSelectElement combo = this.pagHTML.getElementProceso();
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(" "));
        combo.appendChild(op);
        CalProcesosDAO rs = new CalProcesosDAO();
        rs.cargarTablaProcesos();
        Collection<CalProcesosDTO> arr = rs.cargarTablaProcesos();
        rs.close();

        //HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            CalProcesosDTO reg = (CalProcesosDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getProceso());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getProceso())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void comboSubProcesos(String proceso, String defecto) {
        HTMLSelectElement combo = this.pagHTML.getElementSubproceso();
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(" "));
        combo.appendChild(op);
        CalSubProcesosDAO rs = new CalSubProcesosDAO();
        Collection<CalSubProcesosDTO> arr = rs.cargarDeProceso(proceso);
        rs.close();

        //HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getSubproceso());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getSubproceso())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

        rs.close();
    }

    private String generarAreas() {
        CalResponsablesDAO rsArea = new CalResponsablesDAO();
        Collection<CalResponsablesDTO> arr = rsArea.cargarAreasCalidad();
        rsArea.close();
        String script = "var mAreas=new Array(" + arr.size() + ");";
        script = script + " var iCont=0;";

        CalResponsablesDTO reg;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); script = script + " mAreas[iCont++]=new add_area(" + reg.getCodigo() + ",'" + reg.getDescripcion() + "');") {
            reg = (CalResponsablesDTO)iterator.next();
        }

        return script;
    }

    private int listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        if (_operacion == null) {
            _operacion = "X";
        }

        HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
        sel.getParentNode().removeChild(sel);
        String tipoDocumento = comms.request.getParameter("tipoDocumento");
        String proceso = comms.request.getParameter("proceso");
        String subproceso = comms.request.getParameter("subProcesoC");
        String codigo = comms.request.getParameter("codigo");
        String descripcion = comms.request.getParameter("descripcionConsulta");
        String observaciones = comms.request.getParameter("fobservaciones");
        String estado = comms.request.getParameter("estado");
        if (codigo == null) {
            codigo = "";
        }

        if (tipoDocumento == null) {
            tipoDocumento = "";
        }

        if (proceso == null) {
            proceso = "";
        }

        if (subproceso == null) {
            subproceso = comms.request.getParameter("subproceso");
        }

        if (subproceso == null) {
            subproceso = "";
        }

        if (descripcion == null) {
            descripcion = "";
        }

        if (observaciones == null) {
            observaciones = "";
        }

        if (estado == null) {
            estado = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementTipoDocumentof();
        this.comboTipoDocumentos(combo, "", true);
        this.comboProcesos(comms, proceso);
        this.comboSubProcesos(comms, proceso, subproceso);
        combo = this.pagHTML.getElementEstadof();
        this.comboMultivalores(combo, "CAL_ESTADO_DOCUMENTOS", "", true);
        if (_operacion.equals("X")) {
            return 0;
        } else {
            CalDocumentosDAO rs = new CalDocumentosDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                HTMLTableElement hte = this.pagHTML.getElementDetalle();
                rs.cargarTodos(codigo, tipoDocumento, proceso, subproceso, "", estado, descripcion, observaciones);
                CalDocumentosDTO reg = rs.next();
                boolean fondo = false;
                int cuantos = 0;
                String oldProceso = "";

                for(String oldSubProceso = ""; reg != null; reg = rs.next()) {
                    ++cuantos;
                    HTMLElement eltr;
                    HTMLElement td;
                    if (!oldProceso.equals("") && oldProceso.equals(reg.getProceso())) {
                        if (oldProceso.equals(reg.getProceso()) && !oldSubProceso.equals(reg.getSubproceso())) {
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            td = (HTMLElement)this.pagHTML.createElement("td");
                            td.setAttributeNode(this.newAttr("class", "ca"));
                            td.setAttributeNode(this.newAttr("colspan", "9"));
                            td.appendChild(this.pagHTML.createTextNode(reg.getNombreSubProceso()));
                            eltr.appendChild(td);
                            hte.appendChild(eltr);
                        }
                    } else {
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        td = (HTMLElement)this.pagHTML.createElement("td");
                        td.setAttributeNode(this.newAttr("class", "PIE2"));
                        td.setAttributeNode(this.newAttr("colspan", "9"));
                        td.appendChild(this.pagHTML.createTextNode(reg.getNombreProceso()));
                        eltr.appendChild(td);
                        hte.appendChild(eltr);
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        td = (HTMLElement)this.pagHTML.createElement("td");
                        td.setAttributeNode(this.newAttr("class", "ca"));
                        td.setAttributeNode(this.newAttr("colspan", "9"));
                        td.appendChild(this.pagHTML.createTextNode(reg.getNombreSubProceso()));
                        eltr.appendChild(td);
                        hte.appendChild(eltr);
                    }

                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    fondo = !fondo;
                    eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                    eltr.appendChild(this.newtd("" + cuantos));
                    eltr.appendChild(this.newtdhref("" + reg.getCodigo(), "CalDocumentosAct.po?_operacion=P&codigo=" + reg.getCodigo(), false));
                    eltr.appendChild(this.newtd("" + reg.getDescripcion()));
                    eltr.appendChild(this.newtd("" + reg.getVersion()));
                    eltr.appendChild(this.newtd("" + reg.getFechaVersion()));
                    eltr.appendChild(this.newtd("" + reg.getEstado()));
                    eltr.appendChild(this.newtd("" + reg.getResponsables()));
                    eltr.appendChild(this.newtd("" + reg.getOrden()));
                    eltr.appendChild(this.newtd("" + reg.getFechaModificacion()));
                    hte.appendChild(eltr);
                    oldProceso = reg.getProceso();
                    oldSubProceso = reg.getSubproceso();
                }

                rs.close();
                return cuantos;
            }
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        if (nueva) {
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        }

        td.appendChild(enlace);
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private Element newhref(String contenido, String vinculo, boolean nueva) {
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        if (nueva) {
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        }

        return enlace;
    }

    private void comboTipoDocumentos(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
        CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
        Collection<CalTiposDocumentoDTO> arr = rs.cargarTodos("");
        rs.close();
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            CalTiposDocumentoDTO reg = (CalTiposDocumentoDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void comboTipoDocumentos(HTMLSelectElement combo, String defecto, boolean dejarBlanco, String normo, String mapa) {
        CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
        Collection<CalTiposDocumentoDTO> arr = rs.cargarTodos("");
        rs.close();
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            CalTiposDocumentoDTO reg = (CalTiposDocumentoDTO)iterator.next();
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
            if (defecto.equals(reg.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }

            if (!normo.equals(reg.getCodigo()) && !mapa.equals(reg.getCodigo())) {
                combo.appendChild(op);
            }
        }

    }

    private void comboProcesos(HttpPresentationComms comms, String defecto) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        if (_operacion == null) {
            _operacion = "X";
        }

        HTMLSelectElement combo;
        if (!_operacion.equals("Nuevo") && !_operacion.equals("P") && !_operacion.equals("R")) {
            combo = this.pagHTML.getElementProcesof();
        } else {
            combo = this.pagHTML.getElementProceso();
        }

        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(""));
        combo.appendChild(op);
        CalProcesosDAO rsTGen = new CalProcesosDAO();
        if (!rsTGen.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            Collection arr = rsTGen.cargarTablaProcesos();
            rsTGen.close();

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
                CalProcesosDTO reg = (CalProcesosDTO)iterator.next();
                op = (HTMLOptionElement)this.pagHTML.createElement("option");
                op.setValue("" + reg.getProceso());
                op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
                if (defecto.equals(reg.getProceso())) {
                    Attr escogida = this.pagHTML.createAttribute("selected");
                    escogida.setValue("on");
                    op.setAttributeNode(escogida);
                }
            }

            arr.clear();
        }
    }

    private void comboSubProcesos(HttpPresentationComms comms, String proceso, String defecto) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        if (_operacion == null) {
            _operacion = "X";
        }

        HTMLSelectElement combo;
        if (!_operacion.equals("Nuevo") && !_operacion.equals("P") && !_operacion.equals("R")) {
            combo = this.pagHTML.getElementSubProcesoC();
        } else {
            combo = this.pagHTML.getElementSubproceso();
        }

        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(""));
        combo.appendChild(op);
        CalSubProcesosDAO rs = new CalSubProcesosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            Collection arr = rs.cargarDeProceso(proceso);
            rs.close();

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
                CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
                op = (HTMLOptionElement)this.pagHTML.createElement("option");
                op.setValue("" + reg.getSubproceso());
                op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
                if (defecto.equals(reg.getSubproceso())) {
                    Attr escogida = this.pagHTML.createAttribute("selected");
                    escogida.setValue("on");
                    op.setAttributeNode(escogida);
                }
            }

            arr.clear();
        }
    }
}
