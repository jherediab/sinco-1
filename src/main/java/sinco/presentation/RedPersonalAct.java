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
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.RedPrestadorDTO;
import sinco.business.RedPrestadorDocumentosDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.data.RedPrestadorDAO;
import sinco.data.RedPrestadorDocumentosDAO;
import sinco.data.RedSucursalDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class RedPersonalAct implements HttpPresentation {
    private RedPersonalActHTML pagHTML;

    public RedPersonalAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "X";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (RedPersonalActHTML)comms.xmlcFactory.create(RedPersonalActHTML.class);
            HTMLInputElement sel;
            if (!_operacion.equals("L") && !_operacion.equals("X")) {
                if (!_operacion.equals("P") && !_operacion.equals("Consultar")) {
                    if (_operacion.equals("Nuevo")) {
                        sel = this.pagHTML.getElementSalir();
                        sel.getParentNode().removeChild(sel);
                        sel = this.pagHTML.getElementBtnEliminar();
                        sel.getParentNode().removeChild(sel);
                        this.nuevo(comms);
                    }
                } else {
                    this.editar(comms, _operacion);
                }
            } else {
                this.listar(comms, _operacion);
            }

            HTMLTableRowElement sel0;
            if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
                sel0 = this.pagHTML.getElementTrConsulta();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementTrResultados();
                sel0.getParentNode().removeChild(sel0);
                sel = this.pagHTML.getElementMiBotonM();
                sel0.getParentNode().removeChild(sel0);
            }

            if (_operacion.equals("L") || _operacion.equals("X")) {
                sel0 = this.pagHTML.getElementTrCreacionRegistro();
                sel0.getParentNode().removeChild(sel0);
                Varios oVarios = new Varios();
                boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oSisMultivaloresAct");
                if (!oPermisoAct) {
                }
            }

            if (_operacion.equals("Consultar")) {
                sel0 = this.pagHTML.getElementTrConsulta();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementTrResultados();
                sel0.getParentNode().removeChild(sel0);
                sel = this.pagHTML.getElementMiBotonG();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementBtnEliminar();
                sel.getParentNode().removeChild(sel);
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        String elUsuario = "" + comms.session.getUser().getName();
        String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
        if (tipoIdentificacion == null) {
            tipoIdentificacion = "";
        }

        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        boolean rta = false;
        String municipioExpedicion;
        if (_operacion.equals("E")) {
            RedPrestadorDAO rsNuevaIps = new RedPrestadorDAO();
            if (!rsNuevaIps.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rsNuevaIps.eliminarRegistro(numeroIdentificacion);
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorNuevoPersonal"));
                } else {
                    rsNuevaIps.close();
                    municipioExpedicion = "RedPersonalAct.po?_operacion=X";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(municipioExpedicion));
                }
            }
        } else {
            String departamentoExpedicion = comms.request.getParameter("departamentoExpedicion");
            if (departamentoExpedicion == null) {
                departamentoExpedicion = "";
            }

            municipioExpedicion = comms.request.getParameter("municipioExpedicion");
            if (municipioExpedicion == null) {
                municipioExpedicion = "";
            }

            String departamento = comms.request.getParameter("departamento");
            if (departamento == null) {
                departamento = "";
            }

            String municipio = comms.request.getParameter("municipio");
            if (municipio == null) {
                municipio = "";
            }

            String direccion = comms.request.getParameter("direccion");
            if (direccion == null) {
                direccion = "";
            }

            String telefono = comms.request.getParameter("telefono");
            if (telefono == null) {
                telefono = "";
            }

            int dv = 0;

            try {
                dv = Integer.parseInt(comms.request.getParameter("dv"));
            } catch (Exception var28) {
            }

            String sitioWeb = comms.request.getParameter("sitioWeb");
            if (sitioWeb == null) {
                sitioWeb = "";
            }

            String fax = comms.request.getParameter("fax");
            if (fax == null) {
                fax = "";
            }

            String eMail = comms.request.getParameter("eMail");
            if (eMail == null) {
                eMail = "";
            }

            String primerApellido = comms.request.getParameter("primerApellido");
            if (primerApellido == null) {
                primerApellido = "";
            }

            String segundoApellido = comms.request.getParameter("segundoApellido");
            if (segundoApellido == null) {
                segundoApellido = "";
            }

            String primerNombre = comms.request.getParameter("primerNombre");
            if (primerNombre == null) {
                primerNombre = "";
            }

            String segundoNombre = comms.request.getParameter("segundoNombre");
            if (segundoNombre == null) {
                segundoNombre = "";
            }

            String tipoPersona = comms.request.getParameter("tipoPersona");
            if (tipoPersona == null) {
                tipoPersona = "";
            }

            String clasePrestador = comms.request.getParameter("clasePrestador");
            if (clasePrestador == null) {
                clasePrestador = "";
            }

            String estado = "A";
            String[] tipoDocumento = new String[9];

            String sPagina;
            for(int i = 1; i <= 9; ++i) {
                sPagina = "documento" + i;
                tipoDocumento[i - 1] = comms.request.getParameter(sPagina);
                if (tipoDocumento[i - 1] == null) {
                    tipoDocumento[i - 1] = "";
                    if (estado.equals("A")) {
                        estado = "I";
                    }
                }
            }

            RedPrestadorDAO rsNuevaIps = new RedPrestadorDAO();
            if (!rsNuevaIps.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = false;
                int i;
                RedPrestadorDocumentosDAO doc;
                RedSucursalDAO suc;
                if (_operacion.equals("C")) {
                    rta = rsNuevaIps.crearRegistroPersonal(tipoIdentificacion, numeroIdentificacion, dv, primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido, direccion, telefono, tipoIdentificacion, numeroIdentificacion, departamentoExpedicion, municipioExpedicion, elUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, clasePrestador, tipoPersona, sitioWeb, fax, eMail, departamento, municipio, estado, "PERSONAL");
                    if (rta) {
                        doc = new RedPrestadorDocumentosDAO();

                        for(i = 0; i <= 8; ++i) {
                            if (!tipoDocumento[i].equals("")) {
                                rta = doc.crearRegistro(numeroIdentificacion, tipoDocumento[i], 0, "", "", "");
                            }
                        }

                        doc.close();
                        suc = new RedSucursalDAO();
                        suc.crearRegistro(1, "", numeroIdentificacion, departamento, municipio, direccion, telefono, primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido, elUsuario, "A", "S", "", "", fax, eMail, "", "", "", "", "", "", "");
                        suc.close();
                    }
                } else {
                    rta = rsNuevaIps.modificarRegistroPersonal(tipoIdentificacion, numeroIdentificacion, dv, primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido, direccion, telefono, tipoIdentificacion, numeroIdentificacion, departamentoExpedicion, municipioExpedicion, elUsuario, primerNombre, segundoNombre, primerApellido, segundoApellido, clasePrestador, tipoPersona, sitioWeb, fax, eMail, estado, departamento, municipio);
                    if (rta) {
                        doc = new RedPrestadorDocumentosDAO();
                        rta = doc.eliminarRegistro(numeroIdentificacion, "", -1);

                        for(i = 0; i <= 8; ++i) {
                            if (tipoDocumento[i] != "") {
                                rta = doc.crearRegistro(numeroIdentificacion, tipoDocumento[i], 0, "", "", "");
                            }
                        }

                        doc.close();
                        suc = new RedSucursalDAO();
                        suc.modificarNombrePersonal(numeroIdentificacion, primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido, departamento, municipio, direccion, telefono, fax, eMail, elUsuario);
                        suc.close();
                    }
                }

                rsNuevaIps.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorNuevoPersonal"));
                } else {
                    sPagina = "RedPersonalAct.po?_operacion=P&numeroIdentificacion=" + numeroIdentificacion;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void editar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        RedPrestadorDAO rs = new RedPrestadorDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            RedPrestadorDTO reg = rs.cargarRegistro(numeroIdentificacion);
            rs.close();
            RedPrestadorDocumentosDAO doc = new RedPrestadorDocumentosDAO();
            if (!doc.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection arr = doc.cargarTodos(numeroIdentificacion, "", -1, "", "", "", "", "");
                doc.close();
                Varios oVarios = new Varios();
                boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oSisMultivaloresAct");
                if (!oPermisoAct) {
                }

                if (reg != null) {
                    Iterator iterator = arr.iterator();

                    while(iterator.hasNext()) {
                        RedPrestadorDocumentosDTO documento = (RedPrestadorDocumentosDTO)iterator.next();
                        if (documento.getTipoDocumento().equals("1")) {
                            this.pagHTML.getElementCartaPresentacion().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("2")) {
                            this.pagHTML.getElementCopiaRut().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("3")) {
                            this.pagHTML.getElementPropuestaTecnica().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("4")) {
                            this.pagHTML.getElementGarantiaSeriedad().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("5")) {
                            this.pagHTML.getElementCopiaDocumento().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("6")) {
                            this.pagHTML.getElementCopiaPasadoJudicial().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("7")) {
                            this.pagHTML.getElementCertificadoContraloria().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("8")) {
                            this.pagHTML.getElementCertificadoProcuraduria().setChecked(true);
                        } else if (documento.getTipoDocumento().equals("9")) {
                            this.pagHTML.getElementHojaVida().setChecked(true);
                        }
                    }

                    this.pagHTML.getElementSitioWeb().setValue("" + reg.getSitioWeb());
                    HTMLSelectElement combo = this.pagHTML.getElementClasePrestador();
                    this.llenarCombo(combo, "sis_multivalores", "valor", "descripcion", "tabla='clase_prestador' and valor='5'", "", false);
                    combo = this.pagHTML.getElementTipoPersona();
                    this.comboMultivalores(combo, "tipo_persona", "" + reg.getTipoPersona(), true);
                    combo = this.pagHTML.getElementTipoIdentificacion();
                    this.comboMultivalores(combo, "tipo_identificacion_entidad", "" + reg.getTipoIdentificacion(), true);
                    this.pagHTML.getElementDireccion().setValue("" + reg.getDireccion());
                    this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
                    this.pagHTML.getElementFax().setValue("" + reg.getFax());
                    this.pagHTML.getElementEMail().setValue("" + reg.getEmail());
                    this.pagHTML.getElementDv().setValue("" + reg.getDv());
                    combo = this.pagHTML.getElementDepartamento();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
                    if (reg.getDepartamento().length() > 0) {
                        combo = this.pagHTML.getElementMunicipio();
                        this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamento() + "'", "" + reg.getMunicipio(), true);
                    }

                    combo = this.pagHTML.getElementDepartamentoExpedicion();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamentoRepresentante(), true);
                    if (reg.getDepartamentoRepresentante().length() > 0) {
                        combo = this.pagHTML.getElementMunicipioExpedicion();
                        this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamentoRepresentante() + "'", "" + reg.getMunicipioRepresentante(), true);
                    }

                    this.pagHTML.getElementNumeroIdentificacion().setValue("" + reg.getNumeroIdentificacion());
                    this.pagHTML.getElementPrimerApellido().setValue("" + reg.getPrimerApellidoRepresentante());
                    this.pagHTML.getElementSegundoApellido().setValue("" + reg.getSegundoApellidoRepresentante());
                    this.pagHTML.getElementPrimerNombre().setValue("" + reg.getPrimerNombreRepresentante());
                    this.pagHTML.getElementSegundoNombre().setValue("" + reg.getSegundoNombreRepresentante());
                    this.pagHTML.getElement_operacion().setValue("M");
                    if (_operacion.equals("Consultar")) {
                        this.pagHTML.getElementCartaPresentacion().setDisabled(true);
                        this.pagHTML.getElementCopiaRut().setDisabled(true);
                        this.pagHTML.getElementPropuestaTecnica().setDisabled(true);
                        this.pagHTML.getElementGarantiaSeriedad().setDisabled(true);
                        this.pagHTML.getElementCopiaDocumento().setDisabled(true);
                        this.pagHTML.getElementCopiaPasadoJudicial().setDisabled(true);
                        this.pagHTML.getElementCertificadoContraloria().setDisabled(true);
                        this.pagHTML.getElementCertificadoProcuraduria().setDisabled(true);
                        this.pagHTML.getElementHojaVida().setDisabled(true);
                        this.pagHTML.getElementDv().setReadOnly(true);
                        this.pagHTML.getElementSitioWeb().setReadOnly(true);
                        this.pagHTML.getElementClasePrestador().setDisabled(true);
                        this.pagHTML.getElementTipoPersona().setDisabled(true);
                        this.pagHTML.getElementTipoIdentificacion().setDisabled(true);
                        this.pagHTML.getElementDireccion().setReadOnly(true);
                        this.pagHTML.getElementTelefono().setReadOnly(true);
                        this.pagHTML.getElementFax().setReadOnly(true);
                        this.pagHTML.getElementEMail().setReadOnly(true);
                        this.pagHTML.getElementDepartamento().setDisabled(true);
                        this.pagHTML.getElementMunicipio().setDisabled(true);
                        this.pagHTML.getElementDepartamentoExpedicion().setDisabled(true);
                        this.pagHTML.getElementMunicipioExpedicion().setDisabled(true);
                        this.pagHTML.getElementPrimerApellido().setReadOnly(true);
                        this.pagHTML.getElementSegundoApellido().setReadOnly(true);
                        this.pagHTML.getElementPrimerNombre().setReadOnly(true);
                        this.pagHTML.getElementSegundoNombre().setReadOnly(true);
                    }
                }

            }
        }
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        int cuantas = 0;
        String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
        if (tipoIdentificacion == null) {
            tipoIdentificacion = "";
        }

        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        if (numeroIdentificacion == null) {
            numeroIdentificacion = "";
        }

        String primerNombre = comms.request.getParameter("primerNombre");
        if (primerNombre == null) {
            primerNombre = "";
        }

        String primerApellido = comms.request.getParameter("primerApellido");
        if (primerApellido == null) {
            primerApellido = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementFDepartamento();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + departamento, true);
        if (departamento != "") {
            combo = this.pagHTML.getElementFMunicipio();
            this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + departamento + "'", "" + municipio, true);
        }

        combo = this.pagHTML.getElementFTipoIdentificacion();
        this.comboMultivalores(combo, "tipo_identificacion_entidad", "" + tipoIdentificacion, true);
        if (!_operacion.equals("X")) {
            this.pagHTML.getElementFNumeroIdentificacion().setValue(numeroIdentificacion);
            this.pagHTML.getElementFPrimerNombre().setValue(primerNombre);
            this.pagHTML.getElementFPrimerApellido().setValue(primerApellido);
            RedPrestadorDAO rs = new RedPrestadorDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection arr = rs.cargarTodos("", numeroIdentificacion, "", departamento, municipio, "", primerNombre, primerApellido, tipoIdentificacion, "PERSONAL");
                rs.close();
                HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();

                for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                    RedPrestadorDTO reg = (RedPrestadorDTO)iterator.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    String url = "RedPersonalAct.po?_operacion=Consultar&numeroIdentificacion=" + reg.getNumeroIdentificacion();
                    eltr.appendChild(this.newtd("" + reg.getTipoIdentificacion()));
                    eltr.appendChild(this.newtdhref("" + reg.getNumeroIdentificacion(), url));
                    eltr.appendChild(this.newtd("" + reg.getPrimerApellidoRepresentante()));
                    eltr.appendChild(this.newtd("" + reg.getPrimerNombreRepresentante()));
                    eltr.appendChild(this.newtd("" + reg.getDepartamento()));
                    eltr.appendChild(this.newtd("" + reg.getMunicipio()));
                    if (reg.getEstado().equals("A")) {
                        eltr.appendChild(this.newtd("Activo"));
                    } else if (reg.getEstado().equals("I")) {
                        eltr.appendChild(this.newtd("Pendiente Documentos"));
                    }

                    hte.appendChild(eltr);
                }

                arr.clear();
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }
        }
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLSelectElement combo = this.pagHTML.getElementClasePrestador();
        this.llenarCombo(combo, "sis_multivalores", "valor", "descripcion", "tabla='clase_prestador' and valor='5'", "", false);
        combo = this.pagHTML.getElementTipoPersona();
        this.comboMultivalores(combo, "tipo_persona", "", true);
        combo = this.pagHTML.getElementTipoIdentificacion();
        this.comboMultivalores(combo, "tipo_identificacion_entidad", "", true);
        combo = this.pagHTML.getElementDepartamento();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
        combo = this.pagHTML.getElementDepartamentoExpedicion();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtdhref(String contenido, String vinculo) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        td.appendChild(enlace);
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection arr = rs.cargarTabla(tabla);
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

    private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        TGeneralDAO rsTGen = new TGeneralDAO();
        Collection arr = rsTGen.cargarTodosArray(tabla, codigo, descripcion, condicion);
        rsTGen.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }
}
