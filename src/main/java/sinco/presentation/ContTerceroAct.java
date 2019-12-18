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
import sinco.business.Utilidades;
import sinco.data.RedPrestadorDAO;
import sinco.data.RedPrestadorDocumentosDAO;
import sinco.data.RedSucursalDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class ContTerceroAct implements HttpPresentation {
    private ContTerceroActHTML pagHTML;

    public ContTerceroAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "X";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (ContTerceroActHTML)comms.xmlcFactory.create(ContTerceroActHTML.class);
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

                try {
                    sel = this.pagHTML.getElementMiBotonM();
                    sel.getParentNode().removeChild(sel0);
                } catch (Exception var7) {
                }
            }

            if (_operacion.equals("L") || _operacion.equals("X")) {
                sel0 = this.pagHTML.getElementTrCreacionRegistro();
                sel0.getParentNode().removeChild(sel0);
            }

            if (_operacion.equals("Consultar")) {
                sel0 = this.pagHTML.getElementTrConsulta();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementTrResultados();
                sel0.getParentNode().removeChild(sel0);

                try {
                    sel = this.pagHTML.getElementMiBotonG();
                    sel.getParentNode().removeChild(sel);
                } catch (Exception var6) {
                }

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
        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        boolean rta = false;
        String nombreEntidad;
        if (_operacion.equals("E")) {
            RedPrestadorDAO rsNuevaIps = new RedPrestadorDAO();
            if (!rsNuevaIps.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rsNuevaIps.eliminarRegistro(numeroIdentificacion);
                rsNuevaIps.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorNuevaIps"));
                } else {
                    nombreEntidad = "ContTerceroAct.po?_operacion=X";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(nombreEntidad));
                }
            }
        } else {
            String idcodentidad = comms.request.getParameter("idcodentidad");
            nombreEntidad = comms.request.getParameter("nombreEntidad");
            if (nombreEntidad == null) {
                nombreEntidad = "";
            }

            String departamento = comms.request.getParameter("departamento");
            if (departamento == null) {
                departamento = "";
            }

            String municipio = comms.request.getParameter("municipio");
            if (municipio == null) {
                municipio = "";
            }

            String direccionentidad = comms.request.getParameter("direccion");
            if (direccionentidad == null) {
                direccionentidad = "";
            }

            String telefonoentidad = comms.request.getParameter("telefono");
            if (telefonoentidad == null) {
                telefonoentidad = "";
            }

            String clasePrestador = comms.request.getParameter("clasePrestador");
            if (clasePrestador == null) {
                clasePrestador = "";
            }

            String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
            if (tipoIdentificacion == null) {
                tipoIdentificacion = "";
            }

            boolean var14 = false;

            int dv;
            try {
                if (comms.request.getParameter("dv") == "") {
                    dv = 0;
                } else {
                    dv = Integer.parseInt(comms.request.getParameter("dv"));
                }
            } catch (Exception var49) {
                System.out.println("entre aca");
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=dv"));
            }

            String tipoPersona = comms.request.getParameter("tipoPersona");
            if (tipoPersona == null) {
                tipoPersona = "";
            }

            String naturalezaJuridica = comms.request.getParameter("naturalezaJuridica");
            if (naturalezaJuridica == null) {
                naturalezaJuridica = "";
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

            String numeroRegistro = comms.request.getParameter("numeroRegistro");
            if (numeroRegistro == null) {
                numeroRegistro = "";
            }

            String numeroLibro = comms.request.getParameter("numeroLibro");
            if (numeroLibro == null) {
                numeroLibro = "";
            }

            String numeroFolio = comms.request.getParameter("numeroFolio");
            if (numeroFolio == null) {
                numeroFolio = "";
            }

            String fechaInscripcion = comms.request.getParameter("fechaInscripcion");
            if (fechaInscripcion == null) {
                fechaInscripcion = "";
            }

            String primerApellidoRepresentante = comms.request.getParameter("primerApellidoRepresentante");
            if (primerApellidoRepresentante == null) {
                primerApellidoRepresentante = "";
            }

            String segundoApellidoRepresentante = comms.request.getParameter("segundoApellidoRepresentante");
            if (segundoApellidoRepresentante == null) {
                segundoApellidoRepresentante = "";
            }

            String primerNombreRepresentante = comms.request.getParameter("primerNombreRepresentante");
            if (primerNombreRepresentante == null) {
                primerNombreRepresentante = "";
            }

            String segundoNombreRepresentante = comms.request.getParameter("segundoNombreRepresentante");
            if (segundoNombreRepresentante == null) {
                segundoNombreRepresentante = "";
            }

            String tipoIdentificacionRepresentante = comms.request.getParameter("tipoIdentificacionRepresentante");
            if (tipoIdentificacionRepresentante == null) {
                tipoIdentificacionRepresentante = "";
            }

            String numeroIdentificacionRepresentante = comms.request.getParameter("numeroIdentificacionRepresentante");
            if (numeroIdentificacionRepresentante == null) {
                numeroIdentificacionRepresentante = "";
            }

            String departamentoRepresentante = comms.request.getParameter("departamentoRepresentante");
            if (departamentoRepresentante == null) {
                departamentoRepresentante = "";
            }

            String municipioRepresentante = comms.request.getParameter("municipioRepresentante");
            if (municipioRepresentante == null) {
                municipioRepresentante = "";
            }

            String formaVinculacionRepresentante = comms.request.getParameter("formaVinculacionRepresentante");
            if (formaVinculacionRepresentante == null) {
                formaVinculacionRepresentante = "";
            }

            String caracterTerritorial = comms.request.getParameter("caracterTerritorial");
            if (caracterTerritorial == null) {
                caracterTerritorial = "";
            }

            String codigoHabilitacion = "";
            String resolucionCreacion = "";
            String complejidad = comms.request.getParameter("complejidad");
            if (complejidad == null) {
                complejidad = "";
            }

            String nivelAtencion = comms.request.getParameter("nivelAtencion");
            if (nivelAtencion == null) {
                nivelAtencion = "1";
            }

            String empresaSocialEstado = comms.request.getParameter("empresaSocialEstado");
            if (empresaSocialEstado == null) {
                empresaSocialEstado = "";
            }

            String actoConstitucion = comms.request.getParameter("actoConstitucion");
            if (actoConstitucion == null) {
                actoConstitucion = "";
            }

            String numeroActo = comms.request.getParameter("numeroActo");
            if (numeroActo == null) {
                numeroActo = "";
            }

            String fechaActo = comms.request.getParameter("fechaActo");
            if (fechaActo == null) {
                fechaActo = "";
            }

            String entidadExpide = comms.request.getParameter("entidadExpide");
            if (entidadExpide == null) {
                entidadExpide = "";
            }

            String departamentoExpide = comms.request.getParameter("departamentoExpide");
            if (departamentoExpide == null) {
                departamentoExpide = "";
            }

            String municipioExpide = comms.request.getParameter("municipioExpide");
            if (municipioExpide == null) {
                municipioExpide = "";
            }

            String documentos = comms.request.getParameter("documentos");
            if (documentos == null) {
                documentos = "";
            }

            String estado = "A";
            RedPrestadorDAO rs = new RedPrestadorDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = false;
                RedSucursalDAO rsSuc;
                if (_operacion.equals("C")) {
                    rta = rs.crearRegistro(codigoHabilitacion, tipoIdentificacion, idcodentidad, dv, nombreEntidad, direccionentidad, telefonoentidad, tipoIdentificacionRepresentante, numeroIdentificacionRepresentante, departamentoRepresentante, municipioRepresentante, complejidad, elUsuario, resolucionCreacion, numeroRegistro, numeroLibro, numeroFolio, Utilidades.formatoFecha(fechaInscripcion), primerNombreRepresentante, segundoNombreRepresentante, primerApellidoRepresentante, segundoApellidoRepresentante, clasePrestador, tipoPersona, naturalezaJuridica, sitioWeb, fax, eMail, formaVinculacionRepresentante, caracterTerritorial, empresaSocialEstado, actoConstitucion, numeroActo, Utilidades.formatoFecha(fechaActo), entidadExpide, departamento, municipio, estado, departamentoExpide, municipioExpide, nivelAtencion, "TERCERO");
                    if (rta) {
                        this.guardarDocumentosTercero(idcodentidad, documentos, elUsuario, 0);
                        rsSuc = new RedSucursalDAO();
                        rsSuc.crearRegistro(1, codigoHabilitacion, idcodentidad, departamento, municipio, direccionentidad, telefonoentidad, nombreEntidad, elUsuario, "A", "S", "", "", fax, eMail, primerNombreRepresentante, segundoNombreRepresentante, primerApellidoRepresentante, segundoApellidoRepresentante, tipoIdentificacionRepresentante, numeroIdentificacionRepresentante, "");
                        rsSuc.close();
                    }
                } else {
                    rta = rs.modificarRegistro(numeroIdentificacion, codigoHabilitacion, tipoIdentificacion, idcodentidad, dv, nombreEntidad, direccionentidad, telefonoentidad, tipoIdentificacionRepresentante, numeroIdentificacionRepresentante, departamentoRepresentante, municipioRepresentante, complejidad, elUsuario, resolucionCreacion, numeroRegistro, numeroLibro, numeroFolio, Utilidades.formatoFecha(fechaInscripcion), primerNombreRepresentante, segundoNombreRepresentante, primerApellidoRepresentante, segundoApellidoRepresentante, clasePrestador, tipoPersona, naturalezaJuridica, sitioWeb, fax, eMail, formaVinculacionRepresentante, caracterTerritorial, empresaSocialEstado, actoConstitucion, numeroActo, Utilidades.formatoFecha(fechaActo), entidadExpide, departamento, municipio, estado, departamentoExpide, municipioExpide, nivelAtencion);
                    this.guardarDocumentosTercero(idcodentidad, documentos, elUsuario, 1);
                    if (rta) {
                        rsSuc = new RedSucursalDAO();
                        rsSuc.modificarNombrePersonal(numeroIdentificacion, nombreEntidad, departamento, municipio, direccionentidad, telefonoentidad, fax, eMail, elUsuario);
                        rsSuc.close();
                    }
                }

                rs.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorNuevaIps"));
                } else {
                    String sPagina = "ContTerceroAct.po?_operacion=P&idcodentidad=" + idcodentidad;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void editar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        String numeroIdentificacion = comms.request.getParameter("idcodentidad");
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
                doc.cargarTodos(numeroIdentificacion, "", -1, "", "", "", "", "");
                doc.close();
                Varios oVarios = new Varios();
                boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oRedPrestadoraModificar");
                HTMLInputElement elem;
                if (!oPermisoAct) {
                    elem = this.pagHTML.getElementMiBotonM();
                    elem.getParentNode().removeChild(elem);
                }

                oPermisoAct = oVarios.tienePermiso(miGrupo, "oRedPrestadoraCrear");
                if (!oPermisoAct) {
                    elem = this.pagHTML.getElementMiBotonG();
                    elem.getParentNode().removeChild(elem);
                }

                if (reg != null) {
                    this.pagHTML.getElement_operacionCarga().setValue("M");
                    this.pagHTML.getElementNombreEntidad().setValue("" + reg.getNombreEntidad());
                    this.pagHTML.getElementIdcodentidad().setValue("" + reg.getNumeroIdentificacion());
                    this.pagHTML.getElementNumeroIdentificacion().setValue("" + reg.getNumeroIdentificacion());
                    this.pagHTML.getElementDv().setValue("" + reg.getDv());
                    this.pagHTML.getElementSitioWeb().setValue("" + reg.getSitioWeb());
                    this.pagHTML.getElementDireccion().setValue("" + reg.getDireccion());
                    this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
                    this.pagHTML.getElementFax().setValue("" + reg.getFax());
                    this.pagHTML.getElementEMail().setValue("" + reg.getEmail());
                    this.pagHTML.getElementNumeroRegistro().setValue("" + reg.getNumeroRegistro());
                    this.pagHTML.getElementNumeroLibro().setValue("" + reg.getNumeroLibro());
                    this.pagHTML.getElementNumeroFolio().setValue("" + reg.getNumeroFolio());
                    this.pagHTML.getElementFechaInscripcion().setValue("" + reg.getFechaInscripcion());
                    this.pagHTML.getElementPrimerApellidoRepresentante().setValue("" + reg.getPrimerApellidoRepresentante());
                    this.pagHTML.getElementSegundoApellidoRepresentante().setValue("" + reg.getSegundoApellidoRepresentante());
                    this.pagHTML.getElementPrimerNombreRepresentante().setValue("" + reg.getPrimerNombreRepresentante());
                    this.pagHTML.getElementSegundoNombreRepresentante().setValue("" + reg.getSegundoNombreRepresentante());
                    this.pagHTML.getElementNumeroIdentificacionRepresentante().setValue("" + reg.getDocumentoRepresentante());
                    this.pagHTML.getElement_operacion().setValue("M");
                    this.pagHTML.getElementFechaInsercion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInsercion()));
                    this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
                    this.pagHTML.getElementFechaModificacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaModificacion()));
                    this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
                    HTMLSelectElement combo = this.pagHTML.getElementNaturalezaJuridica();
                    this.comboMultivalores(combo, "naturaleza_juridica", "" + reg.getNaturalezaJuridica(), true);
                    combo = this.pagHTML.getElementTipoPersona();
                    this.comboMultivalores(combo, "tipo_persona", "" + reg.getTipoPersona(), true);
                    combo = this.pagHTML.getElementTipoIdentificacion();
                    this.comboMultivalores(combo, "tipo_identificacion_entidad", "" + reg.getTipoIdentificacion(), true);
                    combo = this.pagHTML.getElementDepartamento();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
                    combo = this.pagHTML.getElementMunicipio();
                    this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamento() + "'", "" + reg.getMunicipio(), true);
                    combo = this.pagHTML.getElementTipoIdentificacionRepresentante();
                    this.comboMultivalores(combo, "tipo_documento", "" + reg.getTipoDocumentoRepresentate(), true);
                    combo = this.pagHTML.getElementDepartamentoRepresentante();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamentoRepresentante(), true);
                    combo = this.pagHTML.getElementMunicipioRepresentante();
                    this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamentoRepresentante() + "'", "" + reg.getMunicipioRepresentante(), true);
                    this.pagHTML.getElementIdcodentidad().setReadOnly(true);
                    if (_operacion.equals("Consultar")) {
                        this.pagHTML.getElement_operacionCarga().setValue("D");
                        this.pagHTML.getElementDocumentosSelect().setDisabled(true);
                        this.pagHTML.getElementNaturalezaJuridica().setDisabled(true);
                        this.pagHTML.getElementTipoPersona().setDisabled(true);
                        this.pagHTML.getElementTipoIdentificacion().setDisabled(true);
                        this.pagHTML.getElementDepartamento().setDisabled(true);
                        this.pagHTML.getElementMunicipio().setDisabled(true);
                        this.pagHTML.getElementTipoIdentificacionRepresentante().setDisabled(true);
                        this.pagHTML.getElementDepartamentoRepresentante().setDisabled(true);
                        this.pagHTML.getElementMunicipioRepresentante().setDisabled(true);
                        this.pagHTML.getElement_operacionCarga().setReadOnly(true);
                        this.pagHTML.getElementNombreEntidad().setReadOnly(true);
                        this.pagHTML.getElementDv().setReadOnly(true);
                        this.pagHTML.getElementSitioWeb().setReadOnly(true);
                        this.pagHTML.getElementDireccion().setReadOnly(true);
                        this.pagHTML.getElementTelefono().setReadOnly(true);
                        this.pagHTML.getElementFax().setReadOnly(true);
                        this.pagHTML.getElementEMail().setReadOnly(true);
                        this.pagHTML.getElementNumeroRegistro().setReadOnly(true);
                        this.pagHTML.getElementNumeroLibro().setReadOnly(true);
                        this.pagHTML.getElementNumeroFolio().setReadOnly(true);
                        this.pagHTML.getElementFechaInscripcion().setReadOnly(true);
                        this.pagHTML.getElementPrimerApellidoRepresentante().setReadOnly(true);
                        this.pagHTML.getElementSegundoApellidoRepresentante().setReadOnly(true);
                        this.pagHTML.getElementPrimerNombreRepresentante().setReadOnly(true);
                        this.pagHTML.getElementSegundoNombreRepresentante().setReadOnly(true);
                        this.pagHTML.getElementNumeroIdentificacionRepresentante().setReadOnly(true);
                    }

                    this.pagHTML.setTextScriptCarga(this.cargarScripts(numeroIdentificacion));
                }

            }
        }
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        int cuantas = 0;
        String codigoHabilitacion = comms.request.getParameter("codigoHabilitacion");
        if (codigoHabilitacion == null) {
            codigoHabilitacion = "";
        }

        String numeroIdentificacion = comms.request.getParameter("codigoIps");
        if (numeroIdentificacion == null) {
            numeroIdentificacion = "";
        }

        String nombreIps = comms.request.getParameter("nombreIps");
        if (nombreIps == null) {
            nombreIps = "";
        }

        String complejidad = comms.request.getParameter("ccomplejidad");
        if (complejidad == null) {
            complejidad = "";
        }

        String departamento = comms.request.getParameter("departamentoIps");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipioIps");
        if (municipio == null) {
            municipio = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementDepartamentoIps();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + departamento, true);
        if (departamento != "") {
            combo = this.pagHTML.getElementMunicipioIps();
            this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + departamento + "'", "" + municipio, true);
        }

        if (!_operacion.equals("X")) {
            this.pagHTML.getElementNumeroDocumento().setValue(numeroIdentificacion);
            this.pagHTML.getElementCnombreIps().setValue(nombreIps);
            RedPrestadorDAO rs = new RedPrestadorDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection<RedPrestadorDTO> arr = rs.cargarTodos(codigoHabilitacion, numeroIdentificacion, nombreIps, departamento, municipio, complejidad, "", "", "", "TERCERO");
                rs.close();
                if (_operacion.equals("L")) {
                    HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();

                    for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                        RedPrestadorDTO reg = (RedPrestadorDTO)iterator.next();
                        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        String url = "ContTerceroAct.po?_operacion=Consultar&idcodentidad=" + reg.getNumeroIdentificacion();
                        eltr.appendChild(this.newtd("" + reg.getNumeroIdentificacion()));
                        eltr.appendChild(this.newtdhref("" + reg.getNombreEntidad(), url));
                        eltr.appendChild(this.newtd("" + reg.getDepartamento()));
                        eltr.appendChild(this.newtd("" + reg.getMunicipio()));
                        if (reg.getEstado().equals("A")) {
                            eltr.appendChild(this.newtd("Activo"));
                        } else if (reg.getEstado().equals("I")) {
                            eltr.appendChild(this.newtd("Pendiente Documentos"));
                        }

                        hte.appendChild(eltr);
                    }
                }

                arr.clear();
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }
        }
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLSelectElement combo = this.pagHTML.getElementNaturalezaJuridica();
        this.comboMultivalores(combo, "naturaleza_juridica", "", true);
        combo = this.pagHTML.getElementTipoPersona();
        this.comboMultivalores(combo, "tipo_persona", "", true);
        combo = this.pagHTML.getElementTipoIdentificacion();
        this.comboMultivalores(combo, "tipo_identificacion_entidad", "", true);
        combo = this.pagHTML.getElementDepartamento();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
        combo = this.pagHTML.getElementTipoIdentificacionRepresentante();
        this.comboMultivalores(combo, "tipo_documento", "", true);
        combo = this.pagHTML.getElementDepartamentoRepresentante();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
        combo = this.pagHTML.getElementDocumentosSelect();
        this.comboMultivalores(combo, "documentos_tercero", "", true);
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

    private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
        rs.close();
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
            if (defecto.equals(reg.getCodigo())) {
                HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
                op.setValue("" + reg.getCodigo());
                op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
                combo.appendChild(op);
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
        Collection<TGeneralDTO> arr = rsTGen.cargarTodosArray(tabla, codigo, descripcion, condicion);
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

    private String cargarScripts(String idcodentidad) {
        RedPrestadorDocumentosDAO doc = new RedPrestadorDocumentosDAO();
        int tablaDocumentos = 0;
        String script1 = " ";
        Collection<RedPrestadorDocumentosDTO> resultados = doc.cargarTodos(idcodentidad, "", -1, "", "", "", "", "");

        for(Iterator it = resultados.iterator(); it.hasNext(); ++tablaDocumentos) {
            RedPrestadorDocumentosDTO regItems = (RedPrestadorDocumentosDTO)it.next();
            script1 = script1 + " nuevosDocumentos[" + tablaDocumentos + "]='" + regItems.getTipoDocumento().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
        }

        doc.close();
        String script = " var nuevosDocumentos=new Array(" + tablaDocumentos + ");";
        script = script + script1;
        return script;
    }

    private void guardarDocumentosTercero(String idcodentidad, String documentos, String elUsuario, int operacion) {
        RedPrestadorDocumentosDAO doc = new RedPrestadorDocumentosDAO();
        if (operacion == 1) {
            doc.eliminarRegistro(idcodentidad, "", -1);
        }

        String[] items = documentos.split("~");

        for(int i = 0; i < items.length; ++i) {
            doc.crearRegistro(idcodentidad, items[i], 0, "N", "", "");
        }

        doc.close();
    }
}
