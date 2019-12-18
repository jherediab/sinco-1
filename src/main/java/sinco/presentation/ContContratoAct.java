//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.presentation;

import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
import com.lutris.appserver.server.httpPresentation.HttpPresentation;
import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
import com.lutris.util.KeywordValueException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.ContCdpEstudioDTO;
import sinco.business.ContContratoDTO;
import sinco.business.ContEstudioPrevioDTO;
import sinco.business.ContEstudioPrevioServiciosDTO;
import sinco.business.DocumentosDTO;
import sinco.business.FechaDTO;
import sinco.business.ParametrosDTO;
import sinco.business.RedPrestadorDTO;
import sinco.business.RedSucursalDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.ContCdpEstudioDAO;
import sinco.data.ContContratoDAO;
import sinco.data.ContContratoServicioDAO;
import sinco.data.ContEstudioPrevioDAO;
import sinco.data.ContEstudioPrevioServiciosDAO;
import sinco.data.DBManager;
import sinco.data.DocumentosDAO;
import sinco.data.RedPrestadorDAO;
import sinco.data.RedSucursalDAO;
import sinco.data.SisConsecutivosDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class ContContratoAct implements HttpPresentation {
    private ContContratoActHTML pagHTML;

    public ContContratoAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
            int numeroEstudio = 0;

            try {
                numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
            } catch (Exception var11) {
            }

            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "X";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (ContContratoActHTML)comms.xmlcFactory.create(ContContratoActHTML.class);
            HTMLInputElement sel;
            Varios oVarios;
            boolean oPermisoAct;
            HTMLInputElement elem;
            if (!_operacion.equals("L") && !_operacion.equals("X")) {
                if (_operacion.equals("P")) {
                    this.editar(comms);
                } else if (_operacion.equals("Nuevo")) {
                    sel = this.pagHTML.getElementBtnEliminar();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementGenerarContrato();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementPolizas();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementImpuestos();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementActaInicio();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementAdicion();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementLiquidar();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementBtnCodigoRp();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementBtnNuevo();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementBtnServicios();
                    sel.getParentNode().removeChild(sel);
                    oVarios = new Varios();
                    oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
                    if (!oPermisoAct) {
                        elem = this.pagHTML.getElementBtnGrabar();
                        elem.getParentNode().removeChild(elem);
                    }

                    this.nuevo(comms);
                    this.listar(comms, "", numeroEstudio);
                } else if (_operacion.equals("GenerarContrato")) {
                    this.generarContrato(comms);
                } else if (_operacion.equals("Exportar")) {
                    this.exportar(comms, _operacion);
                }
            } else {
                this.listar(comms, _operacion, numeroEstudio);
            }

            HTMLDivElement sel0;
            if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
                sel0 = this.pagHTML.getElementTrConsulta();
                sel0.getParentNode().removeChild(sel0);
            }

            if (_operacion.equals("L") || _operacion.equals("X")) {
                sel0 = this.pagHTML.getElementTrCreacionRegistro();
                sel0.getParentNode().removeChild(sel0);
                sel = this.pagHTML.getElementBtnCrear();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementTrDocumentos();
                sel0.getParentNode().removeChild(sel);
                oVarios = new Varios();
                oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
                if (!oPermisoAct) {
                    try {
                        elem = this.pagHTML.getElementBtnCrear();
                        elem.getParentNode().removeChild(elem);
                    } catch (Exception var10) {
                    }
                }
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        String elUsuario = "" + comms.session.getUser().getName();
        String tipoEstudio = comms.request.getParameter("tipoEstudio");
        int consecutivoContrato = 0;

        try {
            consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
        } catch (Exception var63) {
        }

        boolean rta = false;
        if (_operacion.equals("E")) {
            ContContratoDAO rs = new ContContratoDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rs.eliminarRegistro(consecutivoContrato);
                rs.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContContrato"));
                } else {
                    String sPagina = "ContContratoAct.po?_operacion=X&tipoEstudio=" + tipoEstudio;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        } else {
            int numeroEstudio = 0;

            try {
                numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
            } catch (Exception var62) {
            }

            double valor = 0.0D;

            try {
                valor = Double.parseDouble(comms.request.getParameter("valorContrato"));
            } catch (Exception var61) {
            }

            long var10 = 0L;

            try {
                var10 = (long)Integer.parseInt(comms.request.getParameter("nrcc"));
            } catch (Exception var60) {
            }

            long var12 = 0L;

            try {
                var12 = (long)Integer.parseInt(comms.request.getParameter("telefonoCelular"));
            } catch (Exception var59) {
            }

            long cdpN = 0L;

            try {
                cdpN = (long)Integer.parseInt(comms.request.getParameter("cdpN"));
            } catch (Exception var58) {
            }

            double valorNumero = 0.0D;

            try {
                valorNumero = Double.parseDouble(comms.request.getParameter("valorNumero"));
            } catch (Exception var57) {
            }

            int codigoSucursal = 0;

            try {
                codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
            } catch (Exception var56) {
            }

            String formaContrato = comms.request.getParameter("formaContrato");
            String fechaContrato = comms.request.getParameter("fechaContrato");
            String tipoDocInterventor = comms.request.getParameter("tipoDocInterventor");
            String nombreContratista = comms.request.getParameter("nombreContratista");
            if (codigoSucursal != 0) {
                RedSucursalDAO redS = new RedSucursalDAO();
                RedSucursalDTO objRed = redS.cargarRegistro(codigoSucursal);
                nombreContratista = objRed.getNombreSede();
                redS.close();
            }

            String email = comms.request.getParameter("email");
            String valorLetra = comms.request.getParameter("valorLetra");
            String valorContratoLetra = comms.request.getParameter("valorContratoLetra");
            String fechaContratoHasta = comms.request.getParameter("fechaContratoHasta");
            String vigencia = comms.request.getParameter("vigencia");
            String regimenTributario = comms.request.getParameter("regimenTributario");
            String tipoContrato = comms.request.getParameter("tipoContrato");
            String direccion = comms.request.getParameter("direccion");
            String expedida = comms.request.getParameter("expedida");
            String municipioContrato = comms.request.getParameter("municipio");
            if (municipioContrato == null) {
                municipioContrato = "";
            }

            String departamentoContrato = comms.request.getParameter("departamento");
            if (departamentoContrato == null) {
                departamentoContrato = "";
            }

            String numeroContrato = comms.request.getParameter("numeroContrato");
            if (numeroContrato == null) {
                numeroContrato = "";
            }

            String estado = comms.request.getParameter("estado");
            String condicionesEspeciales = comms.request.getParameter("condicionesEspeciales");
            long docInterventor = 0L;

            try {
                docInterventor = Long.parseLong(comms.request.getParameter("docInterventor"));
            } catch (Exception var55) {
            }

            long nrocc = 0L;

            try {
                nrocc = Long.parseLong(comms.request.getParameter("nrocc"));
            } catch (Exception var54) {
            }

            long var41 = 0L;

            try {
                cdpN = Long.parseLong(comms.request.getParameter("cdp"));
            } catch (Exception var53) {
            }

            long var43 = 0L;

            try {
                var12 = Long.parseLong(comms.request.getParameter("telefonoCelular"));
            } catch (Exception var52) {
            }

            ContContratoDAO rs = new ContContratoDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = false;
                if (_operacion.equals("C")) {
                    FechaDTO ofecha = new FechaDTO();
                    SisConsecutivosDAO rscon = new SisConsecutivosDAO();
                    rscon.crearConsecutivo("SEC_CONTRATOS_" + ofecha.getAnno(), elUsuario);
                    rscon.close();
                    int registro = rs.crearRegistro(numeroEstudio, numeroContrato, codigoSucursal, nrocc, valorNumero, valorLetra, valorContratoLetra, fechaContratoHasta, cdpN, regimenTributario, tipoContrato, fechaContrato, tipoDocInterventor, docInterventor, valor, estado, condicionesEspeciales, municipioContrato, departamentoContrato, elUsuario);
                    if (registro != 0) {
                        rta = true;
                        consecutivoContrato = registro;
                    }
                } else {
                    rta = rs.modificarRegistro(consecutivoContrato, numeroEstudio, codigoSucursal, nrocc, valorNumero, valorLetra, valorContratoLetra, fechaContratoHasta, cdpN, regimenTributario, tipoContrato, fechaContrato, tipoDocInterventor, docInterventor, valor, estado, condicionesEspeciales, municipioContrato, departamentoContrato, elUsuario);
                }

                rs.generarFechas(-1, consecutivoContrato);
                rs.close();
                if (!formaContrato.equals("C")) {
                    ContContratoServicioDAO rsC = new ContContratoServicioDAO();
                    rsC.eliminarRegistro(consecutivoContrato, -1);
                    rsC.close();
                    ContEstudioPrevioServiciosDAO rsE = new ContEstudioPrevioServiciosDAO();
                    Collection arr = rsE.cargarTodos(numeroEstudio);
                    rsE.close();
                    Iterator iterator = arr.iterator();
                    ContContratoServicioDAO rsCS = new ContContratoServicioDAO();

                    while(iterator.hasNext()) {
                        ContEstudioPrevioServiciosDTO reg = (ContEstudioPrevioServiciosDTO)iterator.next();
                        rsCS.crearRegistro(consecutivoContrato, reg.getCodigoServicio(), elUsuario, "C");
                    }

                    rsCS.close();
                }

                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContContrato"));
                } else {
                    String sPagina = "ContContratoAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&tipoEstudio=" + tipoEstudio + "&numeroEstudio=" + numeroEstudio + "&contratista=" + nrocc;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void listar(HttpPresentationComms comms, String _operacion, int numeroEstudio) throws HttpPresentationException, KeywordValueException {
        String numeroContrato = comms.request.getParameter("numeroContrato");
        if (numeroContrato == null) {
            numeroContrato = "";
        }

        int codigoSucursal = 0;

        try {
            codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
        } catch (Exception var29) {
        }

        String fechaInicioDesde = comms.request.getParameter("fechaInicioDesde");
        if (fechaInicioDesde == null) {
            fechaInicioDesde = "";
        }

        String fechaInicioHasta = comms.request.getParameter("fechaInicioHasta");
        if (fechaInicioHasta == null) {
            fechaInicioHasta = "";
        }

        String fechaFinalDesde = comms.request.getParameter("fechaFinalDesde");
        if (fechaFinalDesde == null) {
            fechaFinalDesde = "";
        }

        String fechaFinalHasta = comms.request.getParameter("fechaFinalHasta");
        if (fechaFinalHasta == null) {
            fechaFinalHasta = "";
        }

        String fechaContratoDesde = comms.request.getParameter("fechaContratoDesde");
        if (fechaContratoDesde == null) {
            fechaContratoDesde = "";
        }

        String fechaContratoHasta = comms.request.getParameter("fechaContratoHasta");
        if (fechaContratoHasta == null) {
            fechaContratoHasta = "";
        }

        String estado = comms.request.getParameter("estado");
        if (estado == null) {
            estado = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        String sucursal = comms.request.getParameter("codigoSucursal");
        if (sucursal == null) {
            sucursal = "";
        }

        String codigoServicio = comms.request.getParameter("codigoServicio");
        if (codigoServicio == null) {
            codigoServicio = "";
        }

        String tipoRed = comms.request.getParameter("tipoRed");
        if (tipoRed == null) {
            tipoRed = "";
        }

        String imputaciones = comms.request.getParameter("fimputacion");
        if (imputaciones == null) {
            imputaciones = "";
        }

        String tipoEstudio = comms.request.getParameter("ftipoEstudio");
        if (tipoEstudio == null) {
            tipoEstudio = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementFdepartamento();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + departamento, true);
        combo = this.pagHTML.getElementFmunicipio();
        this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + departamento + "'", "" + municipio, true);
        combo = this.pagHTML.getElementFcodigoSucursal();
        this.llenarCombo(combo, "red_sucursal", "codigo_sucursal", "nombre_sede", "1=1", "" + sucursal, true);
        combo = this.pagHTML.getElementTipoRed();
        this.comboMultivalores(combo, "naturaleza_juridica", "", true);
        combo = this.pagHTML.getElementFimputacion();
        this.llenarCombo(combo, "cont_imputacion_presupuestal", "codigo_imputacion", "anio||' - '||descripcion", "1=1", "", true);
        combo = this.pagHTML.getElementFtipoEstudio();
        this.comboMultivalores(combo, "tipo_estudio", tipoEstudio, true);
        combo = this.pagHTML.getElementFestado();
        this.comboMultivalores(combo, "estado_contrato", "" + estado, true);
        if (!_operacion.equals("X")) {
            ContContratoDAO rs = new ContContratoDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection arr = rs.cargarTodos(numeroEstudio, numeroContrato, codigoSucursal, departamento, municipio, fechaInicioDesde, fechaInicioHasta, fechaFinalDesde, fechaFinalHasta, fechaContratoDesde, fechaContratoHasta, codigoServicio, tipoRed, imputaciones, tipoEstudio, estado);
                rs.close();
                HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
                int cuantas = 0;

                for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                    ContContratoDTO reg = (ContContratoDTO)iterator.next();
                    numeroEstudio = numeroEstudio == 0 ? reg.getNumeroEstudio() : numeroEstudio;
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    String url = "ContContratoAct.po?_operacion=P&consecutivoContrato=" + reg.getConsecutivoContrato() + "&tipoEstudio=" + tipoEstudio + "&numeroEstudio=" + numeroEstudio + "&contratista=" + reg.getnrocc();
                    eltr.appendChild(this.newtdhref("" + reg.getNumeroContrato(), url));
                    eltr.appendChild(this.newtdhref("" + reg.getNombreSucursal() + " (" + reg.getNombreMunicipio() + ")", url));
                    eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getFechaContrato())));
                    eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getPlazoEjecucion())));
                    url = "ContResumenContratoAct.po?consecutivoContrato=" + reg.getConsecutivoContrato();
                    eltr.appendChild(this.newtdhref("Ver resumen", url));
                    hte.appendChild(eltr);
                }

                arr.clear();
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int consecutivoContrato = 0;

        try {
            consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
        } catch (Exception var20) {
        }

        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var19) {
        }

        String contratista = comms.request.getParameter("contratista");
        if (contratista == null) {
            contratista = "";
        }

        String tipoEstudio = comms.request.getParameter("tipoEstudio");
        if (tipoEstudio == null) {
            tipoEstudio = "";
        }

        this.pagHTML.getElementTipoEstudio().setValue("" + tipoEstudio);
        this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
        ContContratoDAO rs = new ContContratoDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            ContContratoDTO reg = rs.cargarRegistro(consecutivoContrato);
            rs.close();
            RedPrestadorDAO rs2 = new RedPrestadorDAO();
            if (!rs2.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                RedPrestadorDTO reg2 = rs2.cargarRegistro(contratista);
                rs2.close();
                int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
                Varios oVarios = new Varios();
                boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
                if (!oPermisoAct) {
                    HTMLElement elem = this.pagHTML.getElementBtnGrabar();
                    elem.getParentNode().removeChild(elem);
                    elem = this.pagHTML.getElementBtnEliminar();
                    elem.getParentNode().removeChild(elem);
                }

                this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
                if (reg != null) {
                    this.pagHTML.getElementNumeroEstudio().setValue("" + reg.getNumeroEstudio());
                    this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
                    this.pagHTML.getElementNrocc().setValue("" + reg.getnrocc());
                    HTMLSelectElement combo = this.pagHTML.getElementExpedida();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getexpedida(), true);
                    combo = this.pagHTML.getElementExpedidaMun();
                    this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getexpedida() + "'", "" + reg.getExpedidaMunicipio(), true);
                    this.pagHTML.getElementExpedida().setDisabled(true);
                    this.pagHTML.getElementExpedidaMun().setDisabled(true);
                    this.pagHTML.getElementTipoPersona().setDisabled(true);
                    this.pagHTML.getElementNaturalezaJuridica().setDisabled(true);
                    this.pagHTML.getElementNumeroRegistro().setReadOnly(true);
                    this.pagHTML.getElementNumeroFolio().setReadOnly(true);
                    this.pagHTML.getElementNumeroLibro().setReadOnly(true);
                    this.pagHTML.getElementFechaInscripcion().setReadOnly(true);
                    this.pagHTML.getElementDireccion().setValue("" + reg.getdireccion());
                    this.pagHTML.getElementTelefonoCelular().setValue("" + reg.gettelefonoCelular());
                    this.pagHTML.getElementEmail().setValue("" + reg.getemail());
                    String valorLetras = reg.getValorContratoLetra();
                    valorLetras = valorLetras.replace("DE PESOS. M/CTE", "");
                    valorLetras = valorLetras.replace("PESOS. M/CTE", "");
                    this.pagHTML.getElementValorContratoLetra().setValue("" + valorLetras);
                    this.pagHTML.getElementFechaContratoHasta().setValue("" + Utilidades.darFormatoFecha(reg.getPlazoEjecucion()));
                    this.pagHTML.getElementCdp().setValue("" + reg.getcdpN());
                    this.pagHTML.getElementCdpN().setValue("" + reg.getCodigoImputacion());
                    this.pagHTML.getElementVigencia().setValue("" + reg.getvigencia());
                    this.pagHTML.getElementFechaInicio().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInicio()));
                    this.pagHTML.getElementFechaFinal().setValue("" + Utilidades.darFormatoFecha(reg.getFechaFinal()));
                    this.pagHTML.getElementFechaContrato().setValue("" + Utilidades.darFormatoFecha(reg.getFechaContrato()));
                    this.pagHTML.getElementTipoDocInterventor().setValue("" + reg.getTipoDocInterventor());
                    this.pagHTML.getElementDocInterventor().setValue("" + reg.getDocInterventor());
                    this.pagHTML.getElementNombreInterventor().setValue("" + reg.getNombreInterventor());
                    this.pagHTML.getElementValorContrato().setValue("" + Utilidades.formatDouble(reg.getValor()));
                    this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
                    this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
                    this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
                    this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
                    this.pagHTML.getElementCodigoSucursalHidden().setValue("" + reg.getCodigoSucursal());
                    this.pagHTML.getElementCondicionesEspeciales().appendChild(this.pagHTML.createTextNode("" + reg.getCondicionesEspeciales()));
                    combo = this.pagHTML.getElementTipoPersona();
                    this.comboMultivalores(combo, "tipo_persona", "" + reg2.getTipoPersona(), true);
                    combo = this.pagHTML.getElementEstado();
                    this.comboMultivalores(combo, "estado_contrato", "" + reg.getEstado(), true);
                    combo = this.pagHTML.getElementDepartamento();
                    this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
                    combo = this.pagHTML.getElementMunicipio();
                    this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamento() + "'", "" + reg.getMunicipio(), true);
                    combo = this.pagHTML.getElementCodigoSucursal();
                    this.llenarCombo(combo, "red_sucursal", "codigo_sucursal", "nombre_sede", "codigo_sucursal=" + reg.getCodigoSucursal(), "", false);
                    combo = this.pagHTML.getElementRegimenTributario();
                    this.comboMultivalores(combo, "regimen_tributario", reg.getregimenTributario(), false);
                    combo = this.pagHTML.getElementTipoContrato();
                    this.comboMultivalores(combo, "tipo_contrato_admin", reg.getTipoContrato(), false);
                    combo = this.pagHTML.getElementSupervisor();
                    this.llenarCombo(combo, "sis_usuarios", "codigo_empleado", "nombres||' '||apellidos nombre", "usuario_supervisor='S'", "" + reg.getSupervisor(), false);
                    this.pagHTML.getElementConsecutivoContrato().setReadOnly(true);
                    this.pagHTML.getElementSupervisor().setDisabled(true);
                    this.pagHTML.getElementTelefonoCelular().setReadOnly(true);
                    this.pagHTML.getElementEmail().setReadOnly(true);
                    this.pagHTML.getElementDireccion().setReadOnly(true);
                    this.pagHTML.getElementCdpN().setReadOnly(true);
                    this.pagHTML.getElementVigencia().setReadOnly(true);
                    this.pagHTML.getElementNrocc().setReadOnly(true);
                    if (reg2.getTipoPersona().equals("1")) {
                        HTMLElement sel = this.pagHTML.getElementTrCamposJuridicos();
                        sel.getParentNode().removeChild(sel);
                    } else if (reg2.getTipoPersona().equals("2")) {
                        combo = this.pagHTML.getElementNaturalezaJuridica();
                        this.comboMultivalores(combo, "naturaleza_juridica", "" + reg2.getNaturalezaJuridica(), false);
                        this.pagHTML.getElementNumeroRegistro().setValue("" + reg2.getNumeroRegistro());
                        this.pagHTML.getElementNumeroFolio().setValue("" + reg2.getNumeroFolio());
                        this.pagHTML.getElementNumeroLibro().setValue("" + reg2.getNumeroLibro());
                        this.pagHTML.getElementFechaInscripcion().setValue("" + reg2.getFechaInscripcion());
                    }

                    ContEstudioPrevioDAO rsEst = new ContEstudioPrevioDAO();
                    if (!rsEst.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    }

                    ContEstudioPrevioDTO regEst = rsEst.cargarRegistro(reg.getNumeroEstudio());
                    rsEst.close();
                    if (regEst != null) {
                        this.pagHTML.getElementTipoEstudio().setValue("" + regEst.getTipoEstudio());
                        this.pagHTML.getElementNumeroEstudio().setValue("" + regEst.getNumeroEstudio());
                        this.pagHTML.getElementFormaContrato().setValue("" + regEst.getFormaContrato());
                        this.pagHTML.setTextTxtFormaContrato("" + regEst.getDescripcionFormaContrato());
                        this.pagHTML.setTextFormaPago("" + regEst.getDescripcionFormaPago());
                        if (!regEst.getFormaContrato().equals("C")) {
                            try {
                                HTMLElement sel = this.pagHTML.getElementBtnServicios();
                                sel.getParentNode().removeChild(sel);
                            } catch (Exception var18) {
                            }
                        }

                        this.listar(comms, "", regEst.getNumeroEstudio());
                    }

                    this.cargarDocumentos(consecutivoContrato, reg.getEstado(), reg.getTipoContrato());
                    this.pagHTML.getElement_operacion().setValue("M");
                }

            }
        }
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var10) {
        }

        this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
        ContEstudioPrevioDAO rs = new ContEstudioPrevioDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            ContEstudioPrevioDTO regEst = rs.cargarRegistro(numeroEstudio);
            rs.close();
            String valorLetras;
            if (regEst != null) {
                this.pagHTML.getElementTipoEstudio().setValue("" + regEst.getTipoEstudio());
                this.pagHTML.getElementNumeroEstudio().setValue("" + regEst.getNumeroEstudio());
                this.pagHTML.getElementFormaContrato().setValue("" + regEst.getFormaContrato());
                if (regEst.getTipoContrato().equals("6")) {
                    this.pagHTML.getElementValorContrato().setValue("" + Utilidades.formatDouble(regEst.getValor()));
                    valorLetras = regEst.getValorTexto();
                    valorLetras = valorLetras.replace("M/CTE", "");
                    this.pagHTML.getElementValorContratoLetra().setValue("" + valorLetras);
                }

                HTMLSelectElement combo = this.pagHTML.getElementSupervisor();
                this.llenarCombo(combo, "sis_usuarios", "codigo_empleado", "nombres||' '||apellidos nombre", "usuario_supervisor='S'", "" + regEst.getEmpleadoSupervisor(), true);
                this.pagHTML.getElementSupervisor().setDisabled(true);
                combo = this.pagHTML.getElementTipoContrato();
                this.comboMultivalores(combo, "tipo_contrato_admin", regEst.getTipoContrato(), false);
                this.pagHTML.setTextTxtFormaContrato("" + regEst.getDescripcionFormaContrato());
                this.pagHTML.setTextFormaPago("" + regEst.getDescripcionFormaPago());
                ContCdpEstudioDAO rsCdp = new ContCdpEstudioDAO();
                Collection<ContCdpEstudioDTO> arr = rsCdp.cargarTodos(numeroEstudio);
                Iterator iterator = arr.iterator();

                while(iterator.hasNext()) {
                    ContCdpEstudioDTO regCdp = (ContCdpEstudioDTO)iterator.next();
                    this.pagHTML.getElementVigencia().setValue(regCdp.getVigencia());
                    this.pagHTML.getElementCdpN().setValue("" + regCdp.getCodigoImputacion());
                    this.pagHTML.getElementCdp().setValue("" + regCdp.getConsecutivoCdp());
                }

                this.pagHTML.getElementVigencia().setReadOnly(true);
                this.pagHTML.getElementCdpN().setReadOnly(true);
            }

            this.pagHTML.getElementCondicionesEspeciales().setTextContent("De acuerdo con lo previsto en el Artículo 195, numeral 6 de la ley 100 de 1993, en el presente contrato se pactan las cláusulas excepcionales de que tratan los Artículos 14 a 18 de la ley 80 de 1993. EL HOSPITAL, también declarará la caducidad en presencia de cualquiera de las causales establecidas en el Artículo 90 de la Ley 418 de 1997, en las circunstancias previstas en el último inciso del Artículo 5° de la Ley 80 de 1993, en el Artículo 61 de la Ley 610 de 2000, en el Artículo 1° de la Ley 828 de 2003.");
            this.pagHTML.getElementExpedida().setDisabled(true);
            this.pagHTML.getElementExpedidaMun().setDisabled(true);
            this.pagHTML.getElementNrocc().setReadOnly(true);
            this.pagHTML.getElementTelefonoCelular().setReadOnly(true);
            this.pagHTML.getElementEmail().setReadOnly(true);
            this.pagHTML.getElementDireccion().setReadOnly(true);
            this.pagHTML.getElementNaturalezaJuridica().setDisabled(true);
            this.pagHTML.getElementNumeroRegistro().setReadOnly(true);
            this.pagHTML.getElementNumeroFolio().setReadOnly(true);
            this.pagHTML.getElementNumeroLibro().setReadOnly(true);
            this.pagHTML.getElementFechaInscripcion().setReadOnly(true);
            valorLetras = "15";
            String municipio = "176";
            HTMLSelectElement combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "estado_contrato", "A", false);
            combo = this.pagHTML.getElementDepartamento();
            this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", valorLetras, true);
            combo = this.pagHTML.getElementMunicipio();
            this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + valorLetras + "'", municipio, true);
            combo = this.pagHTML.getElementTipoPersona();
            this.comboMultivalores(combo, "tipo_persona", "", true);
            combo = this.pagHTML.getElementRegimenTributario();
            this.comboMultivalores(combo, "regimen_tributario", "A", false);
        }
    }

    private void exportar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        HttpPresentationOutputStream out = comms.response.getOutputStream();
        DBManager rs = new DBManager();
        Connection conexion = null;

        try {
            String numeroContrato = comms.request.getParameter("numeroContrato");
            if (numeroContrato == null) {
                numeroContrato = "";
            }

            int codigoSucursal = 0;

            try {
                codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
            } catch (Exception var36) {
            }

            String fechaInicioDesde = comms.request.getParameter("fechaInicioDesde");
            if (fechaInicioDesde == null) {
                fechaInicioDesde = "";
            }

            String fechaInicioHasta = comms.request.getParameter("fechaInicioHasta");
            if (fechaInicioHasta == null) {
                fechaInicioHasta = "";
            }

            String fechaFinalDesde = comms.request.getParameter("fechaFinalDesde");
            if (fechaFinalDesde == null) {
                fechaFinalDesde = "";
            }

            String fechaFinalHasta = comms.request.getParameter("fechaFinalHasta");
            if (fechaFinalHasta == null) {
                fechaFinalHasta = "";
            }

            String fechaContratoDesde = comms.request.getParameter("fechaContratoDesde");
            if (fechaContratoDesde == null) {
                fechaContratoDesde = "";
            }

            String fechaContratoHasta = comms.request.getParameter("fechaContratoHasta");
            if (fechaContratoHasta == null) {
                fechaContratoHasta = "";
            }

            String estado = comms.request.getParameter("estado");
            if (estado == null) {
                estado = "";
            }

            String departamento = comms.request.getParameter("departamento");
            if (departamento == null) {
                departamento = "";
            }

            String municipio = comms.request.getParameter("municipio");
            if (municipio == null) {
                municipio = "";
            }

            String sucursal = comms.request.getParameter("codigoSucursal");
            if (sucursal == null) {
                sucursal = "";
            }

            String codigoServicio = comms.request.getParameter("codigoServicio");
            if (codigoServicio == null) {
                codigoServicio = "";
            }

            String tipoRed = comms.request.getParameter("tipoRed");
            if (tipoRed == null) {
                tipoRed = "";
            }

            String imputaciones = comms.request.getParameter("fimputacion");
            if (imputaciones == null) {
                imputaciones = "";
            }

            String tipoEstudio = comms.request.getParameter("ftipoEstudio");
            if (tipoEstudio == null) {
                tipoEstudio = "";
            }

            int numeroEstudio = 0;

            try {
                numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
            } catch (Exception var35) {
            }

            HTMLSelectElement combo = this.pagHTML.getElementFdepartamento();
            this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + departamento, true);
            combo = this.pagHTML.getElementFmunicipio();
            this.llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + departamento + "'", "" + municipio, true);
            combo = (HTMLSelectElement)this.pagHTML.getElementCodigoSucursalHidden();
            this.llenarCombo(combo, "red_sucursal", "codigo_sucursal", "nombre_sede", "1=1 order by nombre_sede", "" + sucursal, true);
            combo = this.pagHTML.getElementTipoRed();
            this.comboMultivalores(combo, "naturaleza_juridica", "", true);
            combo = this.pagHTML.getElementFimputacion();
            this.llenarCombo(combo, "cont_imputacion_presupuestal", "codigo_imputacion", "anio||' - '||descripcion", "1=1 ORDER BY anio,descripcion", "", true);
            combo = this.pagHTML.getElementFtipoEstudio();
            this.comboMultivalores(combo, "tipo_estudio", tipoEstudio, true);
            combo = this.pagHTML.getElementFestado();
            this.comboMultivalores(combo, "estado_contrato", "" + estado, true);
            String s = "";
            if (numeroEstudio > 0) {
                s = s + " and con.numero_estudio=" + numeroEstudio;
            }

            if (tipoEstudio.length() > 0) {
                s = s + " and est.tipo_estudio='" + tipoEstudio + "'";
            }

            if (numeroContrato.length() > 0) {
                s = s + " and upper(con.numero_contrato) like upper('%" + numeroContrato + "%')";
            }

            if (departamento.length() > 0) {
                s = s + " and upper(suc.departamento) like upper('%" + departamento + "%')";
            }

            if (municipio.length() > 0) {
                s = s + " and upper(suc.municipio) like upper('%" + municipio + "%')";
            }

            if (codigoSucursal > 0) {
                s = s + " and con.codigo_sucursal=" + codigoSucursal;
            }

            if (fechaInicioDesde.length() > 0) {
                s = s + " and con.fecha_inicio>=" + Utilidades.formatoFecha2(fechaInicioDesde);
            }

            if (fechaInicioHasta.length() > 0) {
                s = s + " and con.fecha_inicio < " + Utilidades.formatoFecha2(fechaInicioHasta);
            }

            if (fechaContratoDesde.length() > 0) {
                s = s + " and con.fecha_contrato>=" + Utilidades.formatoFecha2(fechaContratoDesde);
            }

            if (fechaContratoHasta.length() > 0) {
                s = s + " and con.fecha_contrato < " + Utilidades.formatoFecha2(fechaContratoHasta);
            }

            if (fechaFinalDesde.length() > 0) {
                s = s + " and con.fecha_final>=" + Utilidades.formatoFecha2(fechaFinalDesde);
            }

            if (fechaFinalHasta.length() > 0) {
                s = s + " and con.fecha_final < " + Utilidades.formatoFecha2(fechaFinalHasta);
            }

            if (tipoRed.length() > 0) {
                s = s + " and pre.naturaleza_juridica='" + tipoRed + "'";
            }

            if (codigoServicio.length() > 0) {
                s = s + " and es.codigo_servicio=" + codigoServicio;
            }

            if (imputaciones.length() > 0) {
                s = s + " and it.descripcion_item='" + imputaciones + "'";
            }

            if (estado.length() > 0) {
                s = s + " and upper(con.estado) like upper('%" + estado + "%')";
            }

            String reporte = comms.request.getParameter("reporte");
            String formaExportacion = comms.request.getParameter("formatoExportacion");
            if (formaExportacion == null) {
                formaExportacion = "";
            }

            Map parameters = new HashMap();
            parameters.put("PSQL", s);
            parameters.put("PRUTAIMG", ParametrosDTO.getString("rutaReportes") + "/img/");
            FileInputStream appendedFile = new FileInputStream(ParametrosDTO.getString("rutaReportes") + reporte + ".jasper");
            conexion = this.getConnection();
            JasperRunManager.runReportToPdfStream(appendedFile, out, parameters, conexion);
            conexion.close();
        } catch (JRException var37) {
            var37.printStackTrace();
            Utilidades.writeError("", var37);
        } catch (Exception var38) {
            var38.printStackTrace();
            Utilidades.writeError("", var38);
        } finally {
            rs.close();
        }

    }

    public void generarContrato(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String elUsuario = "" + comms.session.getUser().getName();
        String tipoEstudio = comms.request.getParameter("tipoEstudio");
        int consecutivoContrato = 0;

        try {
            consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
        } catch (Exception var11) {
        }

        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var10) {
        }

        String contratista = comms.request.getParameter("nrocc");
        if (contratista == null) {
            contratista = "";
        }

        DocumentosDAO rs = new DocumentosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            String rta = rs.crearFormatoContrato(consecutivoContrato, elUsuario);
            rs.close();
            if (rta != null) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoDocContrato&texto=" + rta));
            } else {
                String sPagina = "ContContratoAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&tipoEstudio=" + tipoEstudio + "&numeroEstudio=" + numeroEstudio + "&contratista=" + contratista;
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private int cargarDocumentos(int consecutivoContrato, String estadoContrato, String tipoContrato) {
        int cuantos = 0;
        DocumentosDAO rs = new DocumentosDAO();
        boolean rta = rs.getEstadoConexion();
        if (!rta) {
            rs.close();
            return -1;
        } else {
            Collection arr = rs.cargarTodos("Cont%", consecutivoContrato);
            rs.close();
            Iterator iterator = arr.iterator();
            HTMLTableElement hte = this.pagHTML.getElementDetalleDocumentos();

            boolean fondo;
            HTMLElement eltr;
            for(fondo = true; iterator.hasNext(); ++cuantos) {
                DocumentosDTO reg = (DocumentosDTO)iterator.next();
                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtdhref("Documento No " + reg.getNumeroDocumento(), "VerDocumento.po?tipoDocumento=" + reg.getTipoDocumento() + "&numeroDocumento=" + reg.getNumeroDocumento()));
                eltr.appendChild(this.newtd("" + reg.getTipoDocumento()));
                eltr.appendChild(this.newtd("" + reg.getFechaInsercion()));
                hte.appendChild(eltr);
            }

            if (estadoContrato.equals("L")) {
                String nombreReporte = "";
                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                if (tipoContrato.equals("6")) {
                    nombreReporte = "ContActaLiqPersonal";
                } else if (!tipoContrato.equals("9") && !tipoContrato.equals("12") && !tipoContrato.equals("7") && !tipoContrato.equals("8")) {
                    nombreReporte = "ContActaLiquidacionSalud";
                } else {
                    nombreReporte = "ContActaLiquidacionAdmin";
                }

                eltr.appendChild(this.newtdhref("Documento Liquidacion ", "ReporteJasper.po?reporte=" + nombreReporte + "&parametros=PCONSECUTIVO&valores=" + consecutivoContrato + "&formatoExportacion=docx"));
                eltr.appendChild(this.newtd("Liquidacion"));
                eltr.appendChild(this.newtd(""));
                hte.appendChild(eltr);
                ++cuantos;
            }

            return cuantos;
        }
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
        Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
        rsTGen.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion().toUpperCase()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup(ParametrosDTO.getString("ConectorBaseDatos"));
            if (envContext == null) {
                throw new Exception("Error: No Context");
            }

            if (ds == null) {
                throw new Exception("Error: No DataSource");
            }

            if (ds != null) {
                conn = ds.getConnection();
            }

            if (conn != null) {
                return conn;
            }
        } catch (Exception var5) {
            Utilidades.writeError("Error abriendo conexino ", var5);
            var5.printStackTrace();
        }

        return null;
    }

    private void prestador(HTMLSelectElement combo, int defecto, String departamento, String municipio, boolean dejarBlanco) {
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        String tipoPrestador = "PERSONAL','TERCERO";
        RedSucursalDAO rs = new RedSucursalDAO();
        Collection<RedSucursalDTO> arr = rs.cargarTodos2("", "", "", "", "", tipoPrestador, "");
        rs.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            RedSucursalDTO reg = (RedSucursalDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigoSucursal());
            op.appendChild(this.pagHTML.createTextNode(reg.getNombreSede().toUpperCase() + " - " + reg.getMunicipio().toUpperCase()));
            if (defecto == reg.getCodigoSucursal()) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }
}
