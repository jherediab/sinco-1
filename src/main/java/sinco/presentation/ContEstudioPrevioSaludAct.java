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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import sinco.business.ContEstudioPrevioDTO;
import sinco.business.ContEstudioPrevioItemsDTO;
import sinco.business.ContEstudioPrevioServiciosDTO;
import sinco.business.ContImputacionPresupuestalDTO;
import sinco.business.DocumentosDTO;
import sinco.business.LetrasDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.ContContratoDAO;
import sinco.data.ContEstudioPrevioDAO;
import sinco.data.ContEstudioPrevioItemsDAO;
import sinco.data.ContEstudioPrevioServiciosDAO;
import sinco.data.ContImputacionPresupuestalDAO;
import sinco.data.DocumentosDAO;
import sinco.data.ParametrosAplicacionDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;

public class ContEstudioPrevioSaludAct implements HttpPresentation {
    private ContEstudioPrevioSaludActHTML pagHTML;

    public ContEstudioPrevioSaludAct() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "L";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (ContEstudioPrevioSaludActHTML)comms.xmlcFactory.create(ContEstudioPrevioSaludActHTML.class);
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
            } else {
                this.listar(comms, _operacion);
            }

            HTMLDivElement sel;
            if (_operacion.equals("P") || _operacion.equals("D") || _operacion.equals("Nuevo")) {
                sel = this.pagHTML.getElementTrConsulta();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementTrResultados();
                sel.getParentNode().removeChild(sel);
            }

            if (_operacion.equals("L") || _operacion.equals("X")) {
                sel = this.pagHTML.getElementIdCreacionRegistro();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementTrDocumentos();
                sel.getParentNode().removeChild(sel);
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String elUsuario = "" + comms.session.getUser().getName();
        String _operacion = comms.request.getParameter("_operacion");
        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var53) {
        }

        boolean rta = false;
        ContEstudioPrevioDAO rsEstudioPrevio;
        String fechaPresentacion;
        if (_operacion.equals("E")) {
            rsEstudioPrevio = new ContEstudioPrevioDAO();
            if (!rsEstudioPrevio.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rsEstudioPrevio.eliminarRegistro(numeroEstudio);
                rsEstudioPrevio.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEstudioPrevio"));
                } else {
                    fechaPresentacion = "ContEstudioPrevioSaludAct.po?_operacion=L";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(fechaPresentacion));
                }
            }
        } else {
            rsEstudioPrevio = new ContEstudioPrevioDAO();
            if (!rsEstudioPrevio.getEstadoConexion()) {
                rsEstudioPrevio.close();
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                fechaPresentacion = comms.request.getParameter("fechaPresentacion");
                if (fechaPresentacion == null) {
                    fechaPresentacion = "";
                }

                String objetoContratar = comms.request.getParameter("objetoContratarText");
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

                String nombreImputacionPresupuestal = comms.request.getParameter("nombreImputacionPresupuestal");
                if (nombreImputacionPresupuestal == null) {
                    nombreImputacionPresupuestal = "";
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

                String soporteEconomico = comms.request.getParameter("soporteEconomicoText");
                if (soporteEconomico == null) {
                    soporteEconomico = "";
                }

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

                String tarifaDescripcion = comms.request.getParameter("tarifaSaludDescripcionTxt");
                if (tarifaDescripcion == null) {
                    tarifaDescripcion = "";
                }

                String lugarEjecucion = comms.request.getParameter("lugarEjecucion");
                if (lugarEjecucion == null) {
                    lugarEjecucion = "";
                }

                String formaPago = comms.request.getParameter("formaPago");
                if (formaPago == null) {
                    formaPago = "";
                }

                String formaPagoText = comms.request.getParameter("formaPagoText");
                if (formaPagoText == null) {
                    formaPagoText = "";
                }

                String tarifa = comms.request.getParameter("tarifaSalud");
                if (tarifa == null) {
                    tarifa = "";
                }

                String tipoPlazo = comms.request.getParameter("tipoPlazo");
                if (tipoPlazo == null) {
                    tipoPlazo = "";
                }

                double porcentajeAnticipo = 0.0D;

                try {
                    porcentajeAnticipo = Double.parseDouble(comms.request.getParameter("porcentajeAnticipo"));
                } catch (Exception var52) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=porcentajeAnticipo"));
                }

                double porcentajeTarifa = 0.0D;

                try {
                    porcentajeTarifa = Double.parseDouble(comms.request.getParameter("porcentajeTarifa"));
                } catch (Exception var51) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=porcentajeTarifa"));
                }

                double plazoM = 0.0D;

                try {
                    plazoM = Double.parseDouble(comms.request.getParameter("plazo"));
                } catch (Exception var50) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=plazoMeses"));
                }

                double plazoD = 0.0D;

                try {
                    plazoD = Double.parseDouble(comms.request.getParameter("plazoD"));
                } catch (Exception var49) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=plazoDias"));
                }

                double valor = 0.0D;

                try {
                    valor = Double.parseDouble(comms.request.getParameter("valor"));
                } catch (Exception var48) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
                }

                boolean var40 = false;

                int dependencia;
                try {
                    dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
                } catch (Exception var47) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
                }

                String interventor = comms.request.getParameter("interventor");
                if (interventor == null) {
                    interventor = "";
                }

                LetrasDTO valorCont = new LetrasDTO(valor);
                String valotTexto = valorCont.toString();
                double plazo = plazoM + plazoD / 30.0D;
                rta = true;
                if (_operacion.equals("C")) {
                    numeroEstudio = rsEstudioPrevio.crearRegistro("S", fechaPresentacion, objetoContratar, descripcionNecesidad, soporteEconomico, valor, formaPago, formaPagoText, tipoPlazo, plazo, plazoT, tipoContrato, formaContrato, tarifa, tarifaDescripcion, lugarEjecucion, claseGasto, aplicaIva, valotTexto, dependencia, porcentajeAnticipo, porcentajeTarifa, 0, "", "", "", "", elUsuario);
                    if (numeroEstudio < 0) {
                        rta = false;
                    } else {
                        this.guardarItemsEstudioPrevio(numeroEstudio, productosEntregar, definicionTecnica, actividadesDesarrollar, especificacionesTecnicas, alternativasEjecucion, descripcionRiesgos, polizas, nombreImputacionPresupuestal, elUsuario, 0);
                    }
                } else {
                    rta = rsEstudioPrevio.modificarRegistro(numeroEstudio, "S", fechaPresentacion, objetoContratar, descripcionNecesidad, soporteEconomico, valor, formaPago, formaPagoText, tipoPlazo, plazo, plazoT, tipoContrato, formaContrato, tarifa, tarifaDescripcion, lugarEjecucion, claseGasto, aplicaIva, valotTexto, dependencia, porcentajeAnticipo, porcentajeTarifa, 0, "", "", "", "", elUsuario);
                    this.guardarItemsEstudioPrevio(numeroEstudio, productosEntregar, definicionTecnica, actividadesDesarrollar, especificacionesTecnicas, alternativasEjecucion, descripcionRiesgos, polizas, nombreImputacionPresupuestal, elUsuario, 1);
                }

                rsEstudioPrevio.close();
                if (!rta) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorEstudioPrevio"));
                } else {
                    this.generarFormatoEstudioPrevio(comms, numeroEstudio, elUsuario);
                    ContContratoDAO rsC = new ContContratoDAO();
                    rsC.generarFechas(numeroEstudio, -1);
                    rsC.close();
                    String sPagina = "ContEstudioPrevioSaludAct.po?_operacion=D&numeroEstudio=" + numeroEstudio;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int numeroEstudio = 0; //int numeroEstudio = false;
        String _operacion = comms.request.getParameter("_operacion");

       // int numeroEstudio;
        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var10) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero_estudio"));
        }

        ContEstudioPrevioDAO rsEstudioPrevio = new ContEstudioPrevioDAO();
        ContEstudioPrevioDTO reg = rsEstudioPrevio.cargarRegistro(numeroEstudio);
        rsEstudioPrevio.close();
        if (reg != null) {
            this.pagHTML.getElementNumeroEstudio().setValue("" + reg.getNumeroEstudio());
            this.pagHTML.getElementFechaPresentacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaPresentacion()));
            this.pagHTML.getElementObjetoContratar().setValue("" + reg.getObjetoContratar());
            this.pagHTML.getElementDescripcionNecesidad().setValue("" + reg.getDescripcionNecesidad());
            this.pagHTML.getElementSoporteEconomico().setValue("" + reg.getSoporteEconomico());
            this.pagHTML.getElementPlazoT().setValue(reg.getPlazoDescripcion());
            this.pagHTML.getElementTarifaSaludDescripcion().setValue("" + reg.getTarifaDescripcion());
            this.pagHTML.getElementValor().setValue(Utilidades.formatoNumero2(reg.getValor()));
            this.pagHTML.getElementLugarEjecucion().setValue("" + reg.getLugarEjecucion());
            this.pagHTML.getElementPorcentajeTarifa().setValue("" + reg.getPorcentajeTarifa());
            this.pagHTML.getElementPorcentajeAnticipo().setValue("" + reg.getPorcentajeAnticipo());
            this.pagHTML.getElementFechaInsercion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInsercion()));
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaModificacion()));
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElement_operacion().setValue("M");
            this.pagHTML.getElement_operacionCarga().setValue("M");
            this.pagHTML.setTextFormaPagoText(reg.getFormaPagoDescripcion());
            int plazoM = (int)reg.getPlazo();
            int plazoD = (int)Math.round((reg.getPlazo() - (double)plazoM) * 30.0D);
            this.pagHTML.getElementPlazo().setValue("" + plazoM);
            this.pagHTML.getElementPlazoD().setValue("" + plazoD);
            if (reg.getPlazo() != 0.0D) {
                this.pagHTML.getElementPlazoMeses().setChecked(true);
            } else if (reg.getPlazoDescripcion().length() > 0) {
                this.pagHTML.getElementPlazoFecha().setChecked(true);
            }

            if (!reg.getTipoPlazo().equals("")) {
                if (reg.getTipoPlazo().equalsIgnoreCase("textoacta")) {
                    this.pagHTML.getElementTipoPlazoTA().setChecked(true);
                } else if (reg.getTipoPlazo().equalsIgnoreCase("textopoliza")) {
                    this.pagHTML.getElementTipoPlazoTP().setChecked(true);
                }
            }

            if (!reg.getTarifa().equals("5")) {
                this.pagHTML.getElementPorcentajeTarifa().setClassName("INPD");
                this.pagHTML.getElementPorcentajeTarifa().setReadOnly(false);
            }

            if (reg.getPorcentajeAnticipo() > 0.0D) {
                this.pagHTML.getElementPorcentajeAnticipo().setClassName("INPD");
                this.pagHTML.getElementPorcentajeAnticipo().setReadOnly(false);
            }

            HTMLSelectElement combo = this.pagHTML.getElementFormaContrato();
            this.comboMultivalores(combo, "forma_contrato_salud", "" + reg.getFormaContrato(), false);
            combo = this.pagHTML.getElementTipoContrato();
            this.comboMultivalores(combo, "tipo_contrato_salud", "" + reg.getTipoContrato(), false);
            combo = this.pagHTML.getElementFormaPago();
            this.comboMultivalores(combo, "forma_pago_contrato", "" + reg.getFormaPago(), false);
            combo = this.pagHTML.getElementClaseGasto();
            this.comboMultivalores(combo, "tipo_gasto_estudio", "" + reg.getClaseGasto(), false);
            combo = this.pagHTML.getElementTarifaSalud();
            this.comboMultivalores(combo, "tarifa_contrato", "" + reg.getTarifa(), false);
            combo = this.pagHTML.getElementIva();
            this.comboMultivalores(combo, "valor_verdad", "" + reg.getAplicaIva(), false);
            combo = this.pagHTML.getElementTieneAnticipo();
            this.comboMultivalores(combo, "valor_verdad", "" + (reg.getPorcentajeAnticipo() > 0.0D ? "S" : "N"), false);
            combo = this.pagHTML.getElementDependencia();
            this.llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "estado='A'", "" + reg.getDependencia(), false);
            HTMLInputElement sel;
            if (_operacion.equals("D")) {
                this.pagHTML.getElement_operacionCarga().setValue("D");
                combo = this.pagHTML.getElementTipoContrato();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementTarifaSalud();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementFormaPago();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementClaseGasto();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementFormaContrato();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementIva();
                combo.setDisabled(true);
                combo = this.pagHTML.getElementDependencia();
                combo.setDisabled(true);
                this.pagHTML.getElement_operacionCarga().setReadOnly(true);
                this.pagHTML.getElementNumeroEstudio().setReadOnly(true);
                this.pagHTML.getElementFechaPresentacion().setReadOnly(true);
                this.pagHTML.getElementObjetoContratar().setReadOnly(true);
                this.pagHTML.getElementDescripcionNecesidad().setReadOnly(true);
                this.pagHTML.getElementDefinicionTecnica().setReadOnly(true);
                this.pagHTML.getElementSoporteTecnicoEconomico().setReadOnly(true);
                this.pagHTML.getElementEspecificacionesTecnicas().setReadOnly(true);
                this.pagHTML.getElementSoporteEconomico().setReadOnly(true);
                this.pagHTML.getElementAlternativasEjecucion().setReadOnly(true);
                this.pagHTML.getElementDescripcionRiesgos().setReadOnly(true);
                this.pagHTML.getElementPlazo().setReadOnly(true);
                this.pagHTML.getElementPlazoD().setReadOnly(true);
                this.pagHTML.getElementPlazoT().setReadOnly(true);
                this.pagHTML.getElementTarifaSaludDescripcion().setReadOnly(true);
                this.pagHTML.getElementValor().setReadOnly(true);
                this.pagHTML.getElementLugarEjecucion().setReadOnly(true);
                this.pagHTML.getElementObjetoContratarText().setReadOnly(true);
                this.pagHTML.getElementObjetoContratarText().setReadOnly(true);
                this.pagHTML.getElementDescripcionNecesidadText().setReadOnly(true);
                this.pagHTML.getElementSoporteEconomicoText().setReadOnly(true);
                this.pagHTML.getElementProductosEntregarText().setReadOnly(true);
                this.pagHTML.getElementDefinicionTecnicaText().setReadOnly(true);
                this.pagHTML.getElementActividadesDesarrollarText().setReadOnly(true);
                this.pagHTML.getElementEspecificacionesTecnicasText().setReadOnly(true);
                this.pagHTML.getElementAlternativasEjecucionText().setReadOnly(true);
                this.pagHTML.getElementPolizasText().setReadOnly(true);
                this.pagHTML.getElementFormaPagoText().setReadOnly(true);
                this.pagHTML.getElementDescripcionRiesgosText().setReadOnly(true);
                this.pagHTML.getElementTarifaSaludDescripcionTxt().setReadOnly(true);
                sel = this.pagHTML.getElementMiBotonG();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementEliminarRegistro();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementAddServicios();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementAddTarifa();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementAddActividades();
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
        this.cargarImputacion(comms, numeroEstudio, Integer.parseInt(Utilidades.darFormatoFecha(reg.getFechaPresentacion()).substring(1, 4)));
        this.cargarServicios(comms);
    }

    private void nuevo(HttpPresentationComms comms) throws ClientPageRedirectException, HttpPresentationException, KeywordValueException {
        HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementAddServicios();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementMiBotonM();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementMiBotonContrato();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementCopiar();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementAddTarifa();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementAddActividades();
        sel.getParentNode().removeChild(sel);
        HTMLSelectElement combo = this.pagHTML.getElementTipoContrato();
        this.comboMultivalores(combo, "tipo_contrato_salud", "", false);
        combo = this.pagHTML.getElementFormaContrato();
        this.comboMultivalores(combo, "forma_contrato_salud", "", false);
        combo = this.pagHTML.getElementFormaPago();
        this.comboMultivalores(combo, "forma_pago_contrato", "", false);
        combo = this.pagHTML.getElementClaseGasto();
        this.comboMultivalores(combo, "tipo_gasto_estudio", "", false);
        combo = this.pagHTML.getElementTarifaSalud();
        this.comboMultivalores(combo, "tarifa_contrato", "", false);
        combo = this.pagHTML.getElementIva();
        this.comboMultivalores(combo, "valor_verdad", "", false);
        combo = this.pagHTML.getElementDependencia();
        this.llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "estado='A'", "3000", false);
        this.cargarPolizas(comms);
        this.cargarServicios(comms);
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
            rsEstudioPrevio.close();
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            int nuevoNumeroEstudio = rsEstudioPrevio.copiarRegistro(numeroEstudio, elUsuario);
            rsEstudioPrevio.close();
            this.generarFormatoEstudioPrevio(comms, nuevoNumeroEstudio, elUsuario);
            String sPagina = "ContEstudioPrevioSaludAct.po?_operacion=D&numeroEstudio=" + nuevoNumeroEstudio;
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    public void generarFormatoEstudioPrevio(HttpPresentationComms comms, int numero, String usuario) throws HttpPresentationException, KeywordValueException {
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
        int tablaAlternativas = 0;
        int tablaRiesgos = 0;
        int tablaPolizas = 0;
        String script1 = " ";
        Collection<ContEstudioPrevioItemsDTO> resultados = rsItems.cargarTodos(numeroEstudio, "");
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
            } else if (regItems.getTipoItem().equals("alternativaEjecucion")) {
                script1 = script1 + " nuevosAlternativasEjecucion[" + tablaAlternativas + "]='" + regItems.getDescripcionItem().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
                ++tablaAlternativas;
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
        script = script + " var nuevosAlternativasEjecucion=new Array(" + tablaAlternativas + ");";
        script = script + " var nuevosRiesgos=new Array(" + tablaRiesgos + ");";
        script = script + " var nuevosPolizas=new Array(" + tablaPolizas + ");";
        script = script + script1;
        return script;
    }

    private void guardarItemsEstudioPrevio(int numeroEstudio, String productosEntregar, String definicionTecnica, String actividadesDesarrollar, String especificacionesTecnicas, String alternativasEjecucion, String descripcionRiesgos, String polizas, String imputacion, String elUsuario, int operacion) {
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

        items = alternativasEjecucion.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "alternativaEjecucion", items[i], elUsuario);
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

        items = imputacion.split("~");

        for(i = 0; i < items.length; ++i) {
            rsItems.crearRegistro(numeroEstudio, nroItem, "imputacion", items[i].trim(), elUsuario);
            ++nroItem;
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
            Collection<DocumentosDTO> arr = rs.cargarTodos("EstudioPrevio", numeroEstudioPrevio);
            rs.close();
            Iterator<DocumentosDTO> iterator = arr.iterator();
            HTMLTableElement hte = this.pagHTML.getElementDetalleDocumentos();

            for(boolean fondo = true; iterator.hasNext(); ++cuantos) {
                DocumentosDTO reg = (DocumentosDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtdhref1("Documento No " + reg.getNumeroDocumento(), "VerDocumento.po?tipoDocumento=" + reg.getTipoDocumento() + "&numeroDocumento=" + reg.getNumeroDocumento()));
                eltr.appendChild(this.newtd("" + reg.getTipoDocumento()));
                eltr.appendChild(this.newtd("" + reg.getFechaInsercion()));
                hte.appendChild(eltr);
            }

            return cuantos;
        }
    }

    private void cargarServicios(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        ContEstudioPrevioServiciosDAO rsSrvc = new ContEstudioPrevioServiciosDAO();
        if (!rsSrvc.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            int numeroEstudio = 0;

            try {
                numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
            } catch (Exception var10) {
            }

            Collection<ContEstudioPrevioServiciosDTO> arr = rsSrvc.cargarTodos(numeroEstudio);
            rsSrvc.close();
            Iterator<ContEstudioPrevioServiciosDTO> it = arr.iterator();
            HTMLTableElement hte = this.pagHTML.getElementTSrvc();
            boolean fondo = false;

            while(it.hasNext()) {
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                ContEstudioPrevioServiciosDTO reg = (ContEstudioPrevioServiciosDTO)it.next();
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtd("" + reg.getCodigoServicio()));
                eltr.appendChild(this.newtd("" + reg.getNombreServicio()));
                eltr.appendChild(this.newtd("" + reg.getNombreMunicipio()));
                eltr.appendChild(this.newtd("" + reg.getFactor()));
                eltr.appendChild(this.newtd("" + reg.getPorcentajeAfiliados()));
                hte.appendChild(eltr);
            }

        }
    }

    private void cargarPolizas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        SisMultiValoresDAO rsSis = new SisMultiValoresDAO();
        ArrayList<SisMultiValoresDTO> arr = rsSis.cargarTablaActivos("tipo_poliza");
        rsSis.close();
        Iterator<SisMultiValoresDTO> it = arr.iterator();
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
            hte.appendChild(eltr);
        }

        this.pagHTML.getElementCuantos().setValue("" + cuantos);
    }

    private void cargarImputacion(HttpPresentationComms comms, int numEstudio, int anio) throws HttpPresentationException, KeywordValueException {
        ContImputacionPresupuestalDAO rsSis = new ContImputacionPresupuestalDAO();
        Collection<ContImputacionPresupuestalDTO> arr = rsSis.cargarTodos("", "", anio);
        rsSis.close();
        ContEstudioPrevioItemsDAO rsImp = new ContEstudioPrevioItemsDAO();
        Iterator<ContImputacionPresupuestalDTO> it = arr.iterator();
        HTMLTableElement hte = this.pagHTML.getElementTImputacion();
        boolean fondo = true;

        int cuantos;
        for(cuantos = 0; it.hasNext(); ++cuantos) {
            ContImputacionPresupuestalDTO regSis = (ContImputacionPresupuestalDTO)it.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.setAttribute("id", "FilaImputacion" + cuantos);
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            HTMLElement tdSel = (HTMLElement)this.pagHTML.createElement("td");
            HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
            checkbox.setAttribute("type", "checkbox");
            checkbox.setAttribute("id", "imp" + cuantos);
            checkbox.setAttribute("value", regSis.getCodigoImputacion());
            ContEstudioPrevioItemsDTO regImp = rsImp.cargarRegistro(numEstudio, "Imputacion", regSis.getCodigoImputacion());
            if (regImp != null) {
                checkbox.setAttribute("checked", "true");
            }

            tdSel.appendChild(checkbox);
            eltr.appendChild(tdSel);
            HTMLElement tdCodigo = (HTMLElement)this.pagHTML.createElement("td");
            tdCodigo.setAttribute("id", "codImp" + cuantos);
            tdCodigo.appendChild(this.pagHTML.createTextNode(regSis.getCodigoImputacion() + "- " + regSis.getDescripcion()));
            eltr.appendChild(tdCodigo);
            hte.appendChild(eltr);
        }

        rsImp.close();
        this.pagHTML.getElementCuantosImp().setValue("" + cuantos);
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var22) {
        }

        String tipoEstudio = comms.request.getParameter("tipoEstudio");
        if (tipoEstudio == null) {
            tipoEstudio = "";
        }

        String fechaPresentacionDesde = comms.request.getParameter("fechaPresentacionDesde");
        if (fechaPresentacionDesde == null) {
            fechaPresentacionDesde = "";
        }

        String fechaPresentacionHasta = comms.request.getParameter("fechaPresentacionHasta");
        if (fechaPresentacionHasta == null) {
            fechaPresentacionHasta = "";
        }

        String tipoContrato = comms.request.getParameter("tipoContrato");
        if (tipoContrato == null) {
            tipoContrato = "";
        }

        String formaContrato = comms.request.getParameter("formaContrato");
        if (formaContrato == null) {
            formaContrato = "";
        }

        int dependencia = 0;

        try {
            dependencia = Integer.parseInt(comms.request.getParameter("dependencia"));
        } catch (Exception var21) {
        }

        HTMLSelectElement combo = this.pagHTML.getElementFtipoEstudio();
        this.comboMultivalores(combo, "tipo_estudio", "", true);
        combo = this.pagHTML.getElementFtipoContrato();
        this.comboMultivalores(combo, "tipo_contrato_salud", "", true);
        combo = this.pagHTML.getElementFformaContrato();
        this.comboMultivalores(combo, "forma_contrato_salud", "", true);
        combo = this.pagHTML.getElementFdependencia();
        this.llenarCombo(combo, "unidades_dependencia", "codigo", "descripcion", "estado='A'", "", true);
        if (!_operacion.equals("X")) {
            ContEstudioPrevioDAO rs = new ContEstudioPrevioDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection<ContEstudioPrevioDTO> arr = rs.cargarTodos(numeroEstudio, tipoEstudio, fechaPresentacionDesde, fechaPresentacionHasta, tipoContrato, formaContrato, dependencia);
                rs.close();
                HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
                int cuantas = 0;

                for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                    ContEstudioPrevioDTO reg = (ContEstudioPrevioDTO)iterator.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    String url = "";
                    if (reg.getTipoEstudio().equals("A")) {
                        url = "ContEstudioPrevioAdminAct.po?_operacion=D&numeroEstudio=" + reg.getNumeroEstudio();
                    } else {
                        url = "ContEstudioPrevioSaludAct.po?_operacion=D&numeroEstudio=" + reg.getNumeroEstudio();
                    }

                    eltr.appendChild(this.newtdhref("" + reg.getNumeroEstudio(), url));
                    eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getFechaPresentacion())));

                    try {
                        eltr.appendChild(this.newtd("" + reg.getObjetoContratar().substring(0, 100) + "..."));
                    } catch (Exception var20) {
                        eltr.appendChild(this.newtd("" + reg.getObjetoContratar()));
                    }

                    eltr.appendChild(this.newtd("" + reg.getDescripcionTipoContrato()));
                    eltr.appendChild(this.newtd("" + Utilidades.miles(reg.getValor())));
                    eltr.appendChild(this.newtd("" + reg.getLugarEjecucion()));
                    hte.appendChild(eltr);
                }

                arr.clear();
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }
        }
    }

    private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        ArrayList<SisMultiValoresDTO> arr = rs.cargarTablaActivos(tabla);
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
        Collection<TGeneralDTO> arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
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
        td.appendChild(enlace);
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtdhref1(String contenido, String vinculo) {
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
}
