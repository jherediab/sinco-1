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
import org.w3c.dom.html.HTMLTableElement;
import sinco.business.ContEstudioPrevioDTO;
import sinco.business.ContEstudioPrevioItemsDTO;
import sinco.business.DocumentosDTO;
import sinco.business.FechaDTO;
import sinco.business.LetrasDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.ContContratoDAO;
import sinco.data.ContEstudioPrevioDAO;
import sinco.data.ContEstudioPrevioItemsDAO;
import sinco.data.DocumentosDAO;
import sinco.data.ParametrosAplicacionDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;

public class ContEstudioPrevioAdminAct implements HttpPresentation {
    private ContEstudioPrevioAdminActHTML pagHTML;

    public ContEstudioPrevioAdminAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "Nuevo";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (ContEstudioPrevioAdminActHTML)comms.xmlcFactory.create(ContEstudioPrevioAdminActHTML.class);
            if (!_operacion.equals("L") && !_operacion.equals("X")) {
                if (!_operacion.equals("P") && !_operacion.equals("D")) {
                    if (_operacion.equals("Nuevo")) {
                        this.nuevo(comms);
                    } else if (_operacion.equals("Copiar")) {
                        this.copiar(comms);
                    }
                } else {
                    this.editar(comms);
                }
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String elUsuario = "" + comms.session.getUser().getName();
        String _operacion = comms.request.getParameter("_operacion");
        int numeroEstudio = 0;
        if (!_operacion.equals("C")) {
            try {
                numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
            } catch (Exception var50) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero_estudio"));
            }
        }

        boolean rta = false;
        String objetoContratar;
        if (_operacion.equals("E")) {
            ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
            if (!rsEstudioPrevio.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rsEstudioPrevio.eliminarRegistro(numeroEstudio);
                rsEstudioPrevio.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEstudioPrevio"));
                } else {
                    objetoContratar = "ContEstudioPrevioAdminAct.po?_operacion=L";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(objetoContratar));
                }
            }
        } else {
            String fechaPresentacion = comms.request.getParameter("fechaPresentacion");
            if (fechaPresentacion == null) {
                fechaPresentacion = "";
            }

            objetoContratar = comms.request.getParameter("objetoContratarText");
            if (objetoContratar == null) {
                objetoContratar = "";
            }

            String formaContrato = comms.request.getParameter("formaContrato");
            if (formaContrato == null) {
                formaContrato = "";
            }

            String claseGasto = comms.request.getParameter("claseGasto");
            if (claseGasto == null) {
                claseGasto = "";
            }

            String imputacion = comms.request.getParameter("nombreImputacionPresupuestal");
            if (imputacion == null) {
                imputacion = "";
            }

            String productosEntregar = comms.request.getParameter("productosAEntregar");
            if (productosEntregar == null) {
                productosEntregar = "";
            }

            String descripcionNecesidad = comms.request.getParameter("descripcionNecesidadText");
            if (descripcionNecesidad == null) {
                descripcionNecesidad = "";
            }

            String definicionTecnica = comms.request.getParameter("definicionTecnica");
            if (definicionTecnica == null) {
                definicionTecnica = "";
            }

            String actividadesDesarrollar = comms.request.getParameter("actividadesADesarrollar");
            if (actividadesDesarrollar == null) {
                actividadesDesarrollar = "";
            }

            String especificacionesTecnicas = comms.request.getParameter("especificacionesTecnicas");
            if (especificacionesTecnicas == null) {
                especificacionesTecnicas = "";
            }

            String soporteEconomico = "";
            String alternativasEjecucion = comms.request.getParameter("alternativasEjecucion");
            if (alternativasEjecucion == null) {
                alternativasEjecucion = "";
            }

            String descripcionRiesgos = comms.request.getParameter("descripcionRiesgos");
            if (descripcionRiesgos == null) {
                descripcionRiesgos = "";
            }

            String polizas = comms.request.getParameter("polizas");
            if (polizas == null) {
                polizas = "";
            }

            String tipoContrato = comms.request.getParameter("tipoContrato");
            if (tipoContrato == null) {
                tipoContrato = "";
            }

            String aplicaIva = comms.request.getParameter("iva");
            if (aplicaIva == null) {
                aplicaIva = "";
            }

            String plazoT = comms.request.getParameter("plazoT");
            if (plazoT == null) {
                plazoT = "";
            }

            String formaPagoText = comms.request.getParameter("formaPagoText");
            if (formaPagoText == null) {
                formaPagoText = "";
            }

            String tipoPlazo = comms.request.getParameter("tipoPlazo");
            if (tipoPlazo == null) {
                tipoPlazo = "";
            }

            double plazoM = 0.0D;

            try {
                plazoM = Double.parseDouble(comms.request.getParameter("plazo"));
            } catch (Exception var49) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=plazoMeses"));
            }

            double plazoD = 0.0D;

            try {
                plazoD = Double.parseDouble(comms.request.getParameter("plazoD"));
            } catch (Exception var48) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=plazoDias"));
            }

            String fechaDesde = comms.request.getParameter("fechaDesde");
            if (fechaDesde == null) {
                fechaDesde = "";
            }

            String fechaHasta = comms.request.getParameter("fechaHasta");
            if (fechaHasta == null) {
                fechaHasta = "";
            }

            double valor = 0.0D;

            try {
                valor = Double.parseDouble(comms.request.getParameter("valor"));
            } catch (Exception var47) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
            }

            boolean var33 = false;

            int dependencia;
            try {
                dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
            } catch (Exception var46) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
            }

            String lugarEjecucion = comms.request.getParameter("lugarEjecucion");
            String formaPago = "";
            formaPago = comms.request.getParameter("formaPago");
            double plazo = plazoM + plazoD / 30.0D;
            String justificacionValor = comms.request.getParameter("justificacionValor");
            boolean var39 = false;

            int interventor;
            try {
                interventor = Integer.parseInt(comms.request.getParameter("interventor"));
            } catch (Exception var45) {
                interventor = 0;
            }

            String actividadEspecifica = comms.request.getParameter("actividadEspecifica");
            LetrasDTO valorCont = new LetrasDTO(valor);
            String valotTexto = valorCont.toString();
            ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
            if (!rsEstudioPrevio.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = true;
                if (_operacion.equals("C")) {
                    numeroEstudio = rsEstudioPrevio.crearRegistro("A", fechaPresentacion, objetoContratar, descripcionNecesidad, soporteEconomico, valor, formaPago, formaPagoText, tipoPlazo, plazo, plazoT, tipoContrato, formaContrato, "", "", lugarEjecucion, claseGasto, aplicaIva, valotTexto, dependencia, 0.0D, 0.0D, interventor, justificacionValor, actividadEspecifica, fechaDesde, fechaHasta, elUsuario);
                    if (numeroEstudio < 0) {
                        rta = false;
                    } else {
                        this.guardarItemsEstudioPrevio(numeroEstudio, productosEntregar, definicionTecnica, actividadesDesarrollar, especificacionesTecnicas, alternativasEjecucion, descripcionRiesgos, polizas, elUsuario, imputacion, 0);
                    }
                } else {
                    rta = rsEstudioPrevio.modificarRegistro(numeroEstudio, "A", fechaPresentacion, objetoContratar, descripcionNecesidad, soporteEconomico, valor, formaPago, formaPagoText, tipoPlazo, plazo, plazoT, tipoContrato, formaContrato, "", "", lugarEjecucion, claseGasto, aplicaIva, valotTexto, dependencia, 0.0D, 0.0D, interventor, justificacionValor, actividadEspecifica, fechaDesde, fechaHasta, elUsuario);
                    this.guardarItemsEstudioPrevio(numeroEstudio, productosEntregar, definicionTecnica, actividadesDesarrollar, especificacionesTecnicas, alternativasEjecucion, descripcionRiesgos, polizas, elUsuario, imputacion, 1);
                }

                rsEstudioPrevio.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEstudioPrevio"));
                } else {
                    if (rta) {
                        this.generarFormatoEstudioPrevio(comms, numeroEstudio, elUsuario);
                        ContContratoDAO rsC = new ContContratoDAO();
                        rsC.generarFechas(numeroEstudio, -1);
                        rsC.close();
                    }

                    String sPagina = "ContEstudioPrevioAdminAct.po?_operacion=D&numeroEstudio=" + numeroEstudio;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        boolean var3 = false;

        int numeroEstudio;
        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var11) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero_estudio"));
        }

        ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
        ContEstudioPrevioDTO reg = rsEstudioPrevio.cargarRegistro(numeroEstudio);
        if (reg != null) {
            this.pagHTML.getElementNumeroEstudio().setValue("" + reg.getNumeroEstudio());
            this.pagHTML.getElementFechaPresentacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaPresentacion()));
            this.pagHTML.getElementObjetoContratar().setValue("" + reg.getObjetoContratar());
            this.pagHTML.getElementDescripcionNecesidad().setValue("" + reg.getDescripcionNecesidad());
            int plazoM = (int)reg.getPlazo();
            int plazoD = (int)Math.round((reg.getPlazo() - (double)plazoM) * 30.0D);
            if (!reg.getTipoPlazo().equals("")) {
                if (reg.getTipoPlazo().equalsIgnoreCase("textoacta")) {
                    this.pagHTML.getElementTipoPlazoTA().setChecked(true);
                } else if (reg.getTipoPlazo().equalsIgnoreCase("textopoliza")) {
                    this.pagHTML.getElementTipoPlazoTP().setChecked(true);
                } else if (reg.getTipoPlazo().equalsIgnoreCase("textorp")) {
                    this.pagHTML.getElementTipoPlazoTRP().setChecked(true);
                }
            }

            this.pagHTML.setTextFormaPagoText(reg.getFormaPagoDescripcion());
            this.pagHTML.getElementPlazo().setValue("" + plazoM);
            this.pagHTML.getElementPlazoD().setValue("" + plazoD);
            this.pagHTML.getElementValor().setValue(Utilidades.formatoNumero2(reg.getValor()));
            this.pagHTML.getElementLugarEjecucion().setValue("" + reg.getLugarEjecucion());
            this.pagHTML.getElementFechaInsercion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInsercion()));
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaModificacion()));
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementJustificacionValor().setValue("" + reg.getJustificacionValor());
            this.pagHTML.getElementFechaDesde().setValue("" + Utilidades.darFormatoFecha(reg.getFechaDesde()));
            this.pagHTML.getElementFechaHasta().setValue("" + Utilidades.darFormatoFecha(reg.getFechaHasta()));
            this.pagHTML.getElement_operacion().setValue("M");
            this.pagHTML.getElement_operacionCarga().setValue("M");
            HTMLSelectElement combo = this.pagHTML.getElementFormaContrato();
            this.comboMultivalores(combo, "forma_contrato_admin", reg.getFormaContrato(), false);
            combo = this.pagHTML.getElementTipoContrato();
            this.comboMultivalores(combo, "tipo_contrato_admin", reg.getTipoContrato(), false);
            combo = this.pagHTML.getElementFormaPago();
            this.comboMultivalores(combo, "forma_pago_contrato", reg.getFormaPago(), false);
            combo = this.pagHTML.getElementClaseGasto();
            this.comboMultivalores(combo, "tipo_gasto_estudio", reg.getClaseGasto(), false);
            combo = this.pagHTML.getElementIva();
            this.comboMultivalores(combo, "valor_verdad", reg.getAplicaIva(), false);
            combo = this.pagHTML.getElementActividadEspecifica();
            this.comboMultivalores(combo, "Actividad_Especifica", "" + reg.getActividadEspecifica(), false);
            FechaDTO fp = new FechaDTO(Utilidades.darFormatoFecha(reg.getFechaPresentacion()));
            combo = this.pagHTML.getElementNombreImputacionPresupuestal();
            this.llenarCombo(combo, "cont_imputacion_presupuestal", "codigo_imputacion", "descripcion", "anio='" + fp.getAnno() + "'", "" + rsEstudioPrevio.getImputacionAdmin(numeroEstudio), false);
            combo = this.pagHTML.getElementDependencia();
            this.llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "estado='A'", "" + reg.getDependencia(), reg.getDependencia() == 0);
            combo = this.pagHTML.getElementInterventor();
            this.llenarCombo(combo, "sis_usuarios t, sis_usuarios_area ua", "t.codigo_empleado", "t.nombres||' '||t.apellidos nombre", "t.Codigo_Empleado = UA.Codigo_Empleado and t.usuario_supervisor='S' and ua.codigo_area=" + reg.getDependencia(), "" + reg.getEmpleadoSupervisor(), false);
            rsEstudioPrevio.close();
            HTMLInputElement sel;
            if (_operacion.equals("D")) {
                this.pagHTML.getElement_operacionCarga().setValue("D");
                combo = this.pagHTML.getElementTipoContrato();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementFormaPago();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementClaseGasto();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementFormaContrato();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementNombreImputacionPresupuestal();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementIva();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementDependencia();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementInterventor();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementActividadEspecifica();
                combo.setDisabled(true);
                this.pagHTML.getElement_operacionCarga().setReadOnly(true);
                this.pagHTML.getElementNumeroEstudio().setReadOnly(true);
                this.pagHTML.getElementFechaPresentacion().setReadOnly(true);
                this.pagHTML.getElementObjetoContratar().setReadOnly(true);
                this.pagHTML.getElementDescripcionNecesidad().setReadOnly(true);
                this.pagHTML.getElementDefinicionTecnica().setReadOnly(true);
                this.pagHTML.getElementSoporteTecnicoEconomico().setReadOnly(true);
                this.pagHTML.getElementEspecificacionesTecnicas().setReadOnly(true);
                this.pagHTML.getElementAlternativasEjecucion().setReadOnly(true);
                this.pagHTML.getElementDescripcionRiesgos().setReadOnly(true);
                this.pagHTML.getElementPlazo().setReadOnly(true);
                this.pagHTML.getElementPlazoD().setReadOnly(true);
                this.pagHTML.getElementValor().setReadOnly(true);
                this.pagHTML.getElementLugarEjecucion().setReadOnly(true);
                this.pagHTML.getElementFechaDesde().setReadOnly(true);
                this.pagHTML.getElementFechaHasta().setReadOnly(true);
                this.pagHTML.getElementObjetoContratarText().setReadOnly(true);
                this.pagHTML.getElementObjetoContratarText().setReadOnly(true);
                this.pagHTML.getElementDescripcionNecesidadText().setReadOnly(true);
                this.pagHTML.getElementProductosEntregarText().setReadOnly(true);
                this.pagHTML.getElementDefinicionTecnicaText().setReadOnly(true);
                this.pagHTML.getElementActividadesDesarrollarText().setReadOnly(true);
                this.pagHTML.getElementEspecificacionesTecnicasText().setReadOnly(true);
                this.pagHTML.getElementPolizasText().setReadOnly(true);
                this.pagHTML.getElementFormaPagoText().setReadOnly(true);
                this.pagHTML.getElementDescripcionRiesgosText().setReadOnly(true);
                sel = this.pagHTML.getElementMiBotonG();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementEliminarRegistro();
                sel.getParentNode().removeChild(sel);
                this.cargarDocumentos(numeroEstudio);
            } else {
                sel = this.pagHTML.getElementMiBotonM();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementEliminarRegistro();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementMiBotonContrato();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementCopiar();
                sel.getParentNode().removeChild(sel);
            }

            this.pagHTML.setTextScriptCarga(this.cargarScripts(numeroEstudio));
        }

        this.cargarPolizas(comms);
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
        HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementMiBotonM();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementBtnFirmas();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementMiBotonContrato();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementCopiar();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementAgregarCdp();
        sel.getParentNode().removeChild(sel);
        HTMLSelectElement combo = this.pagHTML.getElementTipoContrato();
        this.comboMultivalores(combo, "tipo_contrato_admin", "", false);
        combo = this.pagHTML.getElementFormaContrato();
        this.comboMultivalores(combo, "forma_contrato_admin", "", false);
        combo = this.pagHTML.getElementFormaPago();
        this.comboMultivalores(combo, "forma_pago_contrato", "", false);
        combo = this.pagHTML.getElementClaseGasto();
        this.comboMultivalores(combo, "tipo_gasto_estudio", "", false);
        combo = this.pagHTML.getElementIva();
        this.comboMultivalores(combo, "valor_verdad", "", false);
        combo = this.pagHTML.getElementActividadEspecifica();
        this.comboMultivalores(combo, "Actividad_Especifica", "", true);
        combo = this.pagHTML.getElementDependencia();
        this.llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "estado='A'", "", true);
        this.pagHTML.getElementTipoPlazoTP().setChecked(true);
        this.cargarPolizas(comms);
        ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
        String fecha = rsEstudioPrevio.fechaMaxima();
        rsEstudioPrevio.close();
        this.pagHTML.getElementTipoPlazoTP().setChecked(true);
        this.pagHTML.getElementFechaEstudio().setValue("" + Utilidades.darFormatoFecha(fecha));
    }

    private void copiar(HttpPresentationComms comms) throws ClientPageRedirectException, HttpPresentationException, HttpPresentationException, KeywordValueException {
        String elUsuario = "" + comms.session.getUser().getName();
        boolean var3 = false;

        int numeroEstudio;
        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var7) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero_estudio"));
        }

        ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
        if (!rsEstudioPrevio.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            int nuevoNumeroEstudio = rsEstudioPrevio.copiarRegistro(numeroEstudio, elUsuario);
            rsEstudioPrevio.close();
            this.generarFormatoEstudioPrevio(comms, nuevoNumeroEstudio, elUsuario);
            String sPagina = "ContEstudioPrevioAdminAct.po?_operacion=D&numeroEstudio=" + nuevoNumeroEstudio;
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private void generarFormatoEstudioPrevio(HttpPresentationComms comms, int numero, String usuario) throws HttpPresentationException, KeywordValueException {
        DocumentosDAO rs = new DocumentosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            String rta = rs.crearFormatoEstudioPrevio(numero, usuario);
            rs.close();
            if (rta != null) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoDocEstudioPrevio&texto=" + rta));
            }
        }
    }

    private String cargarScripts(int numeroEstudio) {
        ContEstudioPrevioItemsDAO rsItems = new ContEstudioPrevioItemsDAO();
        int tablaProductosEntregar = 0;
        int tablaDefinicionTecnica = 0;
        int tablaActividades = 0;
        int tablaEspecificaciones = 0;
        int tablaAlternativas = 0; //int tablaAlternativas = false;
        int tablaRiesgos = 0;
        int tablaPolizas = 0;
        String script1 = " ";
        Collection resultados = rsItems.cargarTodos(numeroEstudio, "");
        Iterator it = resultados.iterator();

        while(it.hasNext()) {
            ContEstudioPrevioItemsDTO regItems = (ContEstudioPrevioItemsDTO)it.next();
            if (regItems.getTipoItem().equals("productoEntregar")) {
                script1 = script1 + " nuevosProductosEntregar[" + tablaProductosEntregar + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaProductosEntregar;
            } else if (regItems.getTipoItem().equals("definicionTecnica")) {
                script1 = script1 + " nuevosDefinicionTecnica[" + tablaDefinicionTecnica + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaDefinicionTecnica;
            } else if (regItems.getTipoItem().equals("actividadDesarrollar")) {
                script1 = script1 + " nuevosActividadesDesarrollar[" + tablaActividades + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaActividades;
            } else if (regItems.getTipoItem().equals("especificacionTecnica")) {
                script1 = script1 + " nuevosEspecificacionesTecnicas[" + tablaEspecificaciones + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaEspecificaciones;
            } else if (regItems.getTipoItem().equals("riesgo")) {
                script1 = script1 + " nuevosRiesgos[" + tablaRiesgos + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaRiesgos;
            } else if (regItems.getTipoItem().equals("poliza")) {
                script1 = script1 + " nuevosPolizas[" + tablaPolizas + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaPolizas;
            }
        }

        rsItems.close();
        String script = "var nuevosProductosEntregar=new Array(" + tablaProductosEntregar + ");";
        script = script + " var nuevosDefinicionTecnica=new Array(" + tablaDefinicionTecnica + ");";
        script = script + " var nuevosActividadesDesarrollar=new Array(" + tablaActividades + ");";
        script = script + " var nuevosEspecificacionesTecnicas=new Array(" + tablaEspecificaciones + ");";
        script = script + " var nuevosRiesgos=new Array(" + tablaRiesgos + ");";
        script = script + " var nuevosPolizas=new Array(" + tablaPolizas + ");";
        script = script + script1;
        return script;
    }

    private void guardarItemsEstudioPrevio(int numeroEstudio, String productosEntregar, String definicionTecnica, String actividadesDesarrollar, String especificacionesTecnicas, String alternativasEjecucion, String descripcionRiesgos, String polizas, String elUsuario, String imputacion, int operacion) {
        ContEstudioPrevioItemsDAO rsItems = new ContEstudioPrevioItemsDAO();
        if (operacion == 1) {
            rsItems.eliminarRegistros(numeroEstudio);
        }

        int nroItem = 1;
        String[] items = productosEntregar.split("~");

        int i;
        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "productoEntregar", items[i], elUsuario);
            ++nroItem;
        }

        items = definicionTecnica.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "definicionTecnica", items[i], elUsuario);
            ++nroItem;
        }

        items = actividadesDesarrollar.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "actividadDesarrollar", items[i], elUsuario);
            ++nroItem;
        }

        items = especificacionesTecnicas.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "especificacionTecnica", items[i], elUsuario);
            ++nroItem;
        }

        items = descripcionRiesgos.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "riesgo", items[i], elUsuario);
            ++nroItem;
        }

        items = polizas.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "poliza", items[i], elUsuario);
            ++nroItem;
        }

        if (imputacion != "") {
            rsItems.crearRegistro(numeroEstudio, nroItem, "imputacion", imputacion, elUsuario);
        }

        rsItems.close();
    }

    private int cargarDocumentos(int numeroEstudioPrevio) {
        int cuantos = 0;
        DocumentosDAO rs = new DocumentosDAO();
        boolean rta = rs.getEstadoConexion();
        if (!rta) {
            return -1;
        } else {
            Collection arr = rs.cargarTodos("EstudioPrevio", numeroEstudioPrevio);
            rs.close();
            Iterator iterator = arr.iterator();
            HTMLTableElement hte = this.pagHTML.getElementDetalleDocumentos();

            for(boolean fondo = true; iterator.hasNext(); ++cuantos) {
                DocumentosDTO reg = (DocumentosDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtdhref("Documento No " + reg.getNumeroDocumento(), "VerDocumento.po?tipoDocumento=" + reg.getTipoDocumento() + "&numeroDocumento=" + reg.getNumeroDocumento()));
                eltr.appendChild(this.newtd("" + reg.getTipoDocumento()));
                eltr.appendChild(this.newtd("" + reg.getFechaInsercion()));
                hte.appendChild(eltr);
            }

            return cuantos;
        }
    }

    private void cargarPolizas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        SisMultiValoresDAO rsSis = new SisMultiValoresDAO();
        Collection arr = rsSis.cargarTablaActivos("tipo_poliza");
        rsSis.close();
        Iterator it = arr.iterator();
        HTMLTableElement hte = this.pagHTML.getElementTPoliza();
        boolean fondo = true;

        int cuantos;
        for(cuantos = 0; it.hasNext(); ++cuantos) {
            SisMultiValoresDTO regSis = (SisMultiValoresDTO)it.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            HTMLElement tdSel = (HTMLElement)this.pagHTML.createElement("td");
            HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
            checkbox.setAttribute("type", "checkbox");
            checkbox.setAttribute("id", "poliza" + cuantos);
            tdSel.appendChild(checkbox);
            eltr.appendChild(tdSel);
            HTMLElement tdCodigo = (HTMLElement)this.pagHTML.createElement("td");
            tdCodigo.setAttribute("id", "cod" + cuantos);
            tdCodigo.appendChild(this.pagHTML.createTextNode(regSis.getDescripcion()));
            eltr.appendChild(tdCodigo);
            HTMLElement tdPor = (HTMLElement)this.pagHTML.createElement("td");
            HTMLInputElement hidden = (HTMLInputElement)this.pagHTML.createElement("input");
            hidden.setAttribute("type", "hidden");
            hidden.setAttribute("id", "duracion" + cuantos);
            ParametrosAplicacionDAO rsPar = new ParametrosAplicacionDAO();
            String duracionPoliza = rsPar.getDescripcion("duracion_poliza", regSis.getCodigo());
            rsPar.close();
            hidden.setAttribute("value", duracionPoliza);
            tdPor.appendChild(hidden);
            HTMLInputElement text = (HTMLInputElement)this.pagHTML.createElement("input");
            text.setAttribute("type", "Text");
            text.setAttribute("size", "3");
            text.setAttribute("maxlength", "3");
            text.setAttribute("id", "porcentaje" + cuantos);
            tdPor.appendChild(text);
            eltr.appendChild(tdPor);
            HTMLElement tdPor2 = (HTMLElement)this.pagHTML.createElement("td");
            HTMLInputElement text2 = (HTMLInputElement)this.pagHTML.createElement("input");
            text2.setAttribute("type", "Text");
            text2.setAttribute("size", "15");
            text2.setAttribute("maxlength", "20");
            text2.setAttribute("id", "durabilidad" + cuantos);
            tdPor2.appendChild(text2);
            eltr.appendChild(tdPor2);
            hte.appendChild(eltr);
        }

        this.pagHTML.getElementCuantos().setValue("" + cuantos);
    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        td.appendChild(enlace);
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
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }
}
