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
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.w3c.dom.Attr;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableElement;
import sinco.business.AreasDTO;
import sinco.business.CalCiclosDTO;
import sinco.business.CalLogrosDTO;
import sinco.business.CalPlanesDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.AreasDAO;
import sinco.data.CalCiclosDAO;
import sinco.data.CalLogrosDAO;
import sinco.data.CalPlanesDAO;
import sinco.data.SeguridadDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;

public class CalEstadistica implements HttpPresentation {
    private CalEstadisticaHTML pagHTML;
    int idNav = 0;
    String elUsuario = "";
    int cuantasMetas = 0;
    int miGrupo = 0;

    public CalEstadistica() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            this.miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
            this.elUsuario = "" + comms.session.getUser().getName();
            String reporte = comms.request.getParameter("reporte");
            String operacion = comms.request.getParameter("operacion");
            if (operacion == null || operacion.length() == 0) {
                operacion = "FASE1";
            }

            if (!operacion.equals("FASE1") && !operacion.equals("RECARGA")) {
                if (reporte.equals("areas")) {
                    if (operacion.equals("VER")) {
                        this.verReporteAreas(comms, reporte);
                        return;
                    }
                } else if (reporte.equals("subprocesos")) {
                    this.verEstadisticaSubProcesos(comms, operacion);
                } else if (reporte.equals("resumen")) {
                    this.verResumenAreas(comms, operacion);
                    return;
                }

                this.pagHTML = (CalEstadisticaHTML)comms.xmlcFactory.create(CalEstadisticaHTML.class);
                this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                comms.response.writeDOM(this.pagHTML);
            } else {
                this.fase1(comms, reporte);
            }
        }
    }

    private void fase1(HttpPresentationComms comms, String reporte) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var13) {
        }

        this.pagHTML = (CalEstadisticaHTML)comms.xmlcFactory.create(CalEstadisticaHTML.class);
        TGeneralDAO rsTGen = new TGeneralDAO();
        if (!rsTGen.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
            this.llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado<>'D' order by ciclo desc", true);
            combo = this.pagHTML.getElementProceso();
            this.llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", "", "estado='A' and codigo<>'00'", true);
            combo = this.pagHTML.getElementIdTipoProceso();
            this.llenarCombo(rsTGen, combo, "sis_multivalores", "valor", "descripcion", "", "tabla='CAL_NOMBRE_TIPO_PROCESO'", true);
            if (ciclo > 0) {
                SisUsuariosDAO pf2 = new SisUsuariosDAO();
                SisUsuariosDTO p = pf2.cargarRegistro(this.idNav);
                SeguridadDAO rsSeguridad = new SeguridadDAO();
                if (!rsSeguridad.getEstadoConexion()) {
                    return;
                }

                boolean bVerAreasHijas = rsSeguridad.tieneLlave(miGrupo, "oVerAreasACargo");
                boolean bMostrarTodasLasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
                boolean oVerArriba = rsSeguridad.tieneLlave(miGrupo, "cal_ver_area_superior");
                rsSeguridad.close();
                combo = this.pagHTML.getElementArea();
                this.comboAreas(combo, p.getArea(), bMostrarTodasLasAreas, bVerAreasHijas, oVerArriba);
            }

            rsTGen.close();
            HTMLElement eltr = this.pagHTML.getElementTrEstArea();
            eltr.getParentNode().removeChild(eltr);
            eltr = this.pagHTML.getElementTrProcesos();
            eltr.getParentNode().removeChild(eltr);
            eltr = this.pagHTML.getElementTrSubprocesos();
            eltr.getParentNode().removeChild(eltr);
            eltr = this.pagHTML.getElementTrEstRES();
            eltr.getParentNode().removeChild(eltr);
            this.pagHTML.getElementReporte().setValue("" + reporte);
            combo = this.pagHTML.getElementPeriodo1();
            this.comboMeses(combo, 1);
            combo = this.pagHTML.getElementPeriodo2();
            this.comboMeses(combo, 12);
            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void verReporteAreas(HttpPresentationComms comms, String reporte) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = -1;

        try {
            area = Integer.parseInt(comms.request.getParameter("area"));
        } catch (Exception var62) {
        }

        if (area == -1) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=Cal_DebeEscogerArea"));
        } else {
            int periodo1 = Integer.parseInt(comms.request.getParameter("periodo1"));
            int periodo2 = Integer.parseInt(comms.request.getParameter("periodo2"));
            int areasHijas = 0;

            try {
                areasHijas = Integer.parseInt(comms.request.getParameter("areasHijas"));
            } catch (Exception var61) {
            }

            String sMiProceso = comms.request.getParameter("proceso");
            if (sMiProceso == null) {
                sMiProceso = "";
            }

            String tipoProceso = comms.request.getParameter("tipoProceso");
            if (tipoProceso == null) {
                tipoProceso = "";
            }

            SeguridadDAO rsSeguridad = new SeguridadDAO();
            if (rsSeguridad.getEstadoConexion()) {
                boolean bMostrarTodasLasAreas = rsSeguridad.tieneLlave(this.miGrupo, "oMostrarTodasLasAreas");
                rsSeguridad.close();
                this.pagHTML = (CalEstadisticaHTML)comms.xmlcFactory.create(CalEstadisticaHTML.class);
                int micolspan = 2;
                if (!bMostrarTodasLasAreas) {
                    micolspan = 0;
                    HTMLElement eltr = this.pagHTML.getElementTrTit1();
                    eltr.getParentNode().removeChild(eltr);
                    eltr = this.pagHTML.getElementTrTit2();
                    eltr.getParentNode().removeChild(eltr);
                }

                int codigoPlan = 0;
                String secuencia = "";
                if (area > 0) {
                    CalPlanesDAO rsPlanes = new CalPlanesDAO();
                    if (!rsPlanes.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    }

                    CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
                    rsPlanes.close();
                    if (regPlan != null) {
                        codigoPlan = regPlan.getCodigoPlan();
                    }

                    AreasDAO areaf = new AreasDAO();
                    AreasDTO regArea = areaf.getArea(area);
                    areaf.close();
                    secuencia = regArea.getSecuencia();
                    this.pagHTML.setTextNombreAreaEA(regArea != null ? regArea.getDescripcion() : "");
                }

                CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
                if (!rsCalCiclos.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                } else {
                    CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
                    rsCalCiclos.close();
                    if (regCiclo != null) {
                        this.pagHTML.setTextNombreCicloEA(regCiclo.getDescripcion());
                    }

                    this.pagHTML.setTextNombrePeriodo1EA(Utilidades.nombreMes(periodo1));
                    this.pagHTML.setTextNombrePeriodo2EA(Utilidades.nombreMes(periodo2));
                    this.pagHTML.getElementCicloEA().setValue("" + ciclo);
                    this.pagHTML.getElementAreaEA().setValue("" + area);
                    this.pagHTML.getElementProcesoEA().setValue("" + sMiProceso);
                    CalLogrosDAO rs = new CalLogrosDAO();
                    if (!rs.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    } else {
                        HTMLTableElement laTablaPrincipal = this.pagHTML.getElementTblEstArea();
                        rs.generarEstadisticaAreas(ciclo, codigoPlan, periodo1, periodo2, areasHijas, secuencia, sMiProceso, tipoProceso);
                        int cuantas = 0;
                        CalLogrosDTO reg = rs.nextEstAreas();
                        String proceso = "";
                        String subProceso = "";
                        String meta = "";
                        String nombreArea = "";
                        String nombreProceso = "";
                        String nombreSubProceso = "";
                        double factor = 0.0D;
                        double iUltimaMeta = 0.0D;
                        String tipoMedicion = "";
                        double nroSubLogros = 0.0D;
                        double nroSubLogrosSuperados = 0.0D;
                        double nroProLogros = 0.0D;
                        double nroProLogrosSuperados = 0.0D;
                        double dLogros = 0.0D;
                        double dLogrosSuperados = 0.0D;
                        boolean fondo = false;
                        boolean fondo2 = true;
                        Vector vVector = new Vector();
                        vVector.setSize(13);

                        HTMLElement eltr;
                        HTMLElement tdResumen;
                        HTMLElement eltrD;
                        while(reg != null) {
                            int rta = this.evaluarCorte(cuantas, proceso, reg.getProceso(), subProceso, reg.getSubproceso(), meta, reg.getNombreMeta(), nombreArea, reg.getNombreArea());
                            //HTMLElement eltrD;
                            int i;
                            if (rta > 0) {
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                fondo = !fondo;
                                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                                eltr.appendChild(this.newtd("" + nombreArea, 0, "", 25));
                                tdResumen = (HTMLElement)this.pagHTML.createElement("td");
                                eltrD = (HTMLElement)this.pagHTML.createElement("table");
                                eltrD.setAttributeNode(this.newAttr("align", "center"));
                                eltrD.setAttributeNode(this.newAttr("width", "100%%"));
                                eltrD = (HTMLElement)this.pagHTML.createElement("tr");
                                fondo2 = !fondo2;
                                eltrD.setAttributeNode(this.newAttr("class", "ct" + (fondo2 ? "1" : "2")));
                                i = 0;
                                double suma = 0.0D;

                                for(i = periodo1; i <= periodo2; ++i) {
                                    CalLogrosDTO regLogro = null;

                                    try {
                                        regLogro = (CalLogrosDTO)vVector.elementAt(i);
                                    } catch (Exception var60) {
                                        regLogro = null;
                                    }

                                    if (regLogro != null) {
                                        suma += regLogro.getValorLogro();
                                        ++i;
                                        eltrD.appendChild(this.newtdLogro("" + regLogro.getValorLogro() + "%", 7, regLogro.getCumple().equals("S") ? "" : "rojo", "Meta del plan=" + regLogro.getValorMetaPlan() + " " + regLogro.getJustificacion()));
                                    } else {
                                        eltrD.appendChild(this.newtdLogro(" ", 7, "", ""));
                                    }
                                }

                                eltrD.appendChild(eltrD);
                                tdResumen.appendChild(eltrD);
                                eltr.appendChild(tdResumen);
                                eltr.appendChild(this.newtdLogro("" + iUltimaMeta + "%", 7, "", ""));
                                double miCumplimiento = Utilidades.round2(suma / (double)i);
                                eltr.appendChild(this.newtdLogro("" + miCumplimiento + "%", 7, "", ""));
                                double resultado = 0.0D;
                                if (!tipoMedicion.equals("A") && !tipoMedicion.equals("B") && !tipoMedicion.equals("C")) {
                                    resultado = Utilidades.round2(iUltimaMeta - miCumplimiento);
                                } else {
                                    resultado = Utilidades.round2(miCumplimiento - iUltimaMeta);
                                }

                                eltr.appendChild(this.newtdLogro("" + Utilidades.miles(resultado, 2) + "%", 7, resultado < 0.0D ? "rojo" : "", ""));
                                if (bMostrarTodasLasAreas) {
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(miCumplimiento * factor) + "%", 1, "", 7));
                                    eltr.appendChild(this.newtd("", 1, "", 7));
                                }

                                laTablaPrincipal.appendChild(eltr);
                                vVector.clear();
                                vVector.setSize(13);
                            }

                            switch(rta) {
                            case 1:
                            case 2:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                eltr.appendChild(this.newtd("   **** " + nombreSubProceso + " **** ", 3, "CA2"));
                                eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroSubLogrosSuperados / nroSubLogros), 1, "CA2"));
                                eltr.appendChild(this.newtd("", 1, "CA2"));
                                if (bMostrarTodasLasAreas) {
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroSubLogrosSuperados / nroSubLogros * factor), 1, "CA2"));
                                    eltr.appendChild(this.newtd("", 1, "CA2"));
                                }

                                laTablaPrincipal.appendChild(eltr);
                                nroSubLogrosSuperados = 0.0D;
                                nroSubLogros = 0.0D;
                                if (rta == 1) {
                                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtd("   **** " + nombreProceso + " ****", 3, "CA"));
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProLogrosSuperados / nroProLogros), 1, "CA"));
                                    eltr.appendChild(this.newtd("", 1, "CA"));
                                    if (bMostrarTodasLasAreas) {
                                        eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProLogrosSuperados / nroProLogros * factor), 1, "CA"));
                                        eltr.appendChild(this.newtd("", 1, "CA"));
                                    }

                                    laTablaPrincipal.appendChild(eltr);
                                    nroProLogrosSuperados = 0.0D;
                                    nroProLogros = 0.0D;
                                }
                            }

                            switch(rta) {
                            case -1:
                            case 1:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                eltr.appendChild(this.newtd("" + reg.getNombreProceso(), 5 + micolspan, "CA"));
                                laTablaPrincipal.appendChild(eltr);
                            case 2:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                eltr.appendChild(this.newtd("" + reg.getNombreSubProceso(), 4 + micolspan, "CA2"));
                                if (bMostrarTodasLasAreas) {
                                    eltr.appendChild(this.newtd("" + reg.getFactor(), 0, "CA2"));
                                } else {
                                    eltr.appendChild(this.newtd("", 0, "CA2"));
                                }

                                laTablaPrincipal.appendChild(eltr);
                            case 3:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                fondo = !fondo;
                                eltr.appendChild(this.newtd("" + reg.getNombreMeta(), 0, "tit"));
                                tdResumen = (HTMLElement)this.pagHTML.createElement("td");
                                eltrD = (HTMLElement)this.pagHTML.createElement("table");
                                eltrD.setAttributeNode(this.newAttr("align", "center"));
                                eltrD.setAttributeNode(this.newAttr("width", "100%%"));
                                eltrD = (HTMLElement)this.pagHTML.createElement("tr");
                                fondo2 = !fondo2;
                                eltrD.setAttributeNode(this.newAttr("class", "ct" + (fondo2 ? "1" : "2")));

                                for(i = periodo1; i <= periodo2; ++i) {
                                    eltrD.appendChild(this.newtdLogro("" + Utilidades.nombreMes(i).substring(0, 3), 7, "tit", Utilidades.nombreMes(i)));
                                }

                                eltrD.appendChild(eltrD);
                                tdResumen.appendChild(eltrD);
                                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                                eltr.appendChild(tdResumen);
                                eltr.appendChild(this.newtd("", 0, "tit", 5));
                                eltr.appendChild(this.newtd("", 0, "tit", 5));
                                eltr.appendChild(this.newtd("", 0, "tit", 5));
                                if (bMostrarTodasLasAreas) {
                                    eltr.appendChild(this.newtd("", 0, "tit", 5));
                                    eltr.appendChild(this.newtd("", 0, "tit", 4));
                                }

                                laTablaPrincipal.appendChild(eltr);
                            case 0:
                            }

                            vVector.add(reg.getPeriodo(), reg);
                            proceso = reg.getProceso();
                            subProceso = reg.getSubproceso();
                            nombreProceso = reg.getNombreProceso();
                            nombreSubProceso = reg.getNombreSubProceso();
                            meta = reg.getNombreMeta();
                            nombreArea = reg.getNombreArea();
                            ++nroSubLogros;
                            ++nroProLogros;
                            ++dLogros;
                            if (reg.getCumple().equals("S")) {
                                ++nroSubLogrosSuperados;
                                ++nroProLogrosSuperados;
                                ++dLogrosSuperados;
                            }

                            factor = reg.getFactor() / 100.0D;
                            nombreProceso = reg.getNombreProceso();
                            ++cuantas;
                            iUltimaMeta = reg.getValorMetaPlan();
                            tipoMedicion = reg.getTipoMedicion();
                            reg = rs.nextEstAreas();
                        }

                        rs.close();
                        if (cuantas > 0) {
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            fondo = !fondo;
                            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                            eltr.appendChild(this.newtd("" + nombreArea, 0, "", 25));
                            tdResumen = (HTMLElement)this.pagHTML.createElement("td");
                            tdResumen = (HTMLElement)this.pagHTML.createElement("table");
                            tdResumen.setAttributeNode(this.newAttr("align", "center"));
                            tdResumen.setAttributeNode(this.newAttr("width", "100%%"));
                            eltrD = (HTMLElement)this.pagHTML.createElement("tr");
                            fondo2 = !fondo2;
                            eltrD.setAttributeNode(this.newAttr("class", "ct" + (fondo2 ? "1" : "2")));
                            int iNroEntradas = 0;
                            double suma = 0.0D;

                            for(int i = periodo1; i <= periodo2; ++i) {
                                CalLogrosDTO regLogro = null;

                                try {
                                    regLogro = (CalLogrosDTO)vVector.elementAt(i);
                                } catch (Exception var59) {
                                    regLogro = null;
                                }

                                if (regLogro != null) {
                                    suma += regLogro.getValorLogro();
                                    ++iNroEntradas;
                                    eltrD.appendChild(this.newtdLogro("" + regLogro.getValorLogro() + "%", 7, regLogro.getCumple().equals("S") ? "" : "rojo", "Meta del plan=" + regLogro.getValorMetaPlan() + " " + regLogro.getJustificacion()));
                                } else {
                                    eltrD.appendChild(this.newtdLogro(" ", 7, "", ""));
                                }
                            }

                            tdResumen.appendChild(eltrD);
                            tdResumen.appendChild(tdResumen);
                            eltr.appendChild(tdResumen);
                            eltr.appendChild(this.newtdLogro("" + iUltimaMeta + "%", 7, "", ""));
                            double miCumplimiento = Utilidades.round2(suma / (double)iNroEntradas);
                            eltr.appendChild(this.newtdLogro("" + miCumplimiento + "%", 7, "", ""));
                            double resultado = 0.0D;
                            if (!tipoMedicion.equals("A") && !tipoMedicion.equals("B") && !tipoMedicion.equals("C")) {
                                resultado = Utilidades.round2(iUltimaMeta - miCumplimiento);
                            } else {
                                resultado = Utilidades.round2(miCumplimiento - iUltimaMeta);
                            }

                            eltr.appendChild(this.newtdLogro("" + Utilidades.miles(resultado, 2) + "%", 7, resultado < 0.0D ? "rojo" : "", ""));
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("" + Utilidades.round2(miCumplimiento * factor) + "%", 1, "", 7));
                                eltr.appendChild(this.newtd("", 1, "", 7));
                            }

                            laTablaPrincipal.appendChild(eltr);
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            eltr.appendChild(this.newtd("   **** " + nombreSubProceso + " ****", 3, "CA2"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroSubLogrosSuperados / nroSubLogros), 1, "CA2"));
                            eltr.appendChild(this.newtd("", 1, "CA2"));
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroSubLogrosSuperados / nroSubLogros * factor), 1, "CA2"));
                                eltr.appendChild(this.newtd("", 1, "CA2"));
                            }

                            laTablaPrincipal.appendChild(eltr);
                            nroSubLogrosSuperados = 0.0D;
                            nroSubLogros = 0.0D;
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            eltr.appendChild(this.newtd("   **** " + nombreProceso + " ****", 3, "CA"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProLogrosSuperados / nroProLogros), 1, "CA"));
                            eltr.appendChild(this.newtd("", 1, "CA"));
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProLogrosSuperados / nroProLogros * factor), 1, "CA"));
                                eltr.appendChild(this.newtd("", 1, "CA"));
                            }

                            laTablaPrincipal.appendChild(eltr);
                            nroProLogrosSuperados = 0.0D;
                            nroProLogros = 0.0D;
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            eltr.appendChild(this.newtd("   **** TOTAL GENERAL ****", 3, "CA"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * dLogrosSuperados / dLogros), 1, "CA"));
                            eltr.appendChild(this.newtd("   ", 1, "CA"));
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("", 2, "CA"));
                            }

                            laTablaPrincipal.appendChild(eltr);
                        }

                        this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                        eltr = this.pagHTML.getElementTrSeleccionar();
                        eltr.getParentNode().removeChild(eltr);
                        eltr = this.pagHTML.getElementTrSubprocesos();
                        eltr.getParentNode().removeChild(eltr);
                        eltr = this.pagHTML.getElementTrProcesos();
                        eltr.getParentNode().removeChild(eltr);
                        eltr = this.pagHTML.getElementTrEstRES();
                        eltr.getParentNode().removeChild(eltr);
                        comms.response.writeDOM(this.pagHTML);
                    }
                }
            }
        }
    }

    private void verEstadisticaSubProcesos(HttpPresentationComms comms, String operacion) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = -1;

        try {
            area = Integer.parseInt(comms.request.getParameter("area"));
        } catch (Exception var61) {
        }

        if (area == -1) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=Cal_DebeEscogerArea"));
        } else {
            int periodo1 = Integer.parseInt(comms.request.getParameter("periodo1"));
            int periodo2 = Integer.parseInt(comms.request.getParameter("periodo2"));
            int areasHijas = 0;

            try {
                areasHijas = Integer.parseInt(comms.request.getParameter("areasHijas"));
            } catch (Exception var60) {
            }

            String sMiProceso = comms.request.getParameter("proceso");
            if (sMiProceso == null) {
                sMiProceso = "";
            }

            String tipoProceso = comms.request.getParameter("tipoProceso");
            if (tipoProceso == null) {
                tipoProceso = "";
            }

            this.pagHTML = (CalEstadisticaHTML)comms.xmlcFactory.create(CalEstadisticaHTML.class);
            this.pagHTML.getElementCicloSUB().setValue("" + ciclo);
            this.pagHTML.getElementAreaSUB().setValue("" + area);
            this.pagHTML.getElementPeriodo1SUB().setValue("" + periodo1);
            this.pagHTML.getElementPeriodo2SUB().setValue("" + periodo2);
            this.pagHTML.getElementProcesoSUB().setValue("" + sMiProceso);
            this.pagHTML.getElementAreasHijasSUB().setValue("" + areasHijas);
            this.pagHTML.getElementTipoProcesoSUB().setValue("" + tipoProceso);
            this.pagHTML.setTextNombrePeriodo1SUB(Utilidades.nombreMes(periodo1));
            this.pagHTML.setTextNombrePeriodo2SUB(Utilidades.nombreMes(periodo2));
            int codigoPlan = 0;
            String secuencia = "";
            if (area > 0) {
                CalPlanesDAO rsPlanes = new CalPlanesDAO();
                if (!rsPlanes.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                }

                CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
                rsPlanes.close();
                if (regPlan != null) {
                    codigoPlan = regPlan.getCodigoPlan();
                }

                AreasDAO areaf = new AreasDAO();
                AreasDTO regArea = areaf.getArea(area);
                areaf.close();
                secuencia = regArea.getSecuencia();
                this.pagHTML.setTextNombreAreaSUB(regArea != null ? regArea.getDescripcion() : "");
            }

            SeguridadDAO rsSeguridad = new SeguridadDAO();
            if (rsSeguridad.getEstadoConexion()) {
                boolean bMostrarTodasLasAreas = rsSeguridad.tieneLlave(this.miGrupo, "oMostrarTodasLasAreas");
                rsSeguridad.close();
                int miColSpan = 2;
                if (!bMostrarTodasLasAreas) {
                    miColSpan = 0;
                    HTMLElement eltr = this.pagHTML.getElementTrEfSUB1();
                    eltr.getParentNode().removeChild(eltr);
                    eltr = this.pagHTML.getElementTrEfSUB2();
                    eltr.getParentNode().removeChild(eltr);
                }

                CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
                if (!rsCalCiclos.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                } else {
                    CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
                    rsCalCiclos.close();
                    if (regCiclo != null) {
                        this.pagHTML.setTextNombreCicloSUB(regCiclo.getDescripcion());
                    }

                    CalLogrosDAO rs = new CalLogrosDAO();
                    if (!rs.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    } else {
                        HTMLTableElement laTablaPrincipal = this.pagHTML.getElementTblSUB();
                        rs.generarEstadisticaAreas(ciclo, codigoPlan, periodo1, periodo2, areasHijas, secuencia, sMiProceso, tipoProceso);
                        int cuantas = 0;
                        CalLogrosDTO reg = rs.nextEstAreas();
                        String proceso = "";
                        String subProceso = "";
                        String meta = "";
                        String nombreArea = "";
                        String nombreProceso = "";
                        String nombreSubProceso = "";
                        double factor = 0.0D;
                        double nroProMetas = 0.0D;
                        double nroProMetasCumplidas = 0.0D;
                        double nroSubLogros = 0.0D;
                        double nroSubLogrosSuperados = 0.0D;
                        double nroSubMetas = 0.0D;
                        double dLogros = 0.0D;
                        double dLogrosSuperados = 0.0D;
                        double dEficaciaProceso = 0.0D;
                        double nroGenMetas = 0.0D;
                        double nroGenMetasCumplidas = 0.0D;
                        boolean fondo = false;

                        HTMLElement eltr;
                        DefaultCategoryDataset objDatos;
                        for(objDatos = new DefaultCategoryDataset(); reg != null; reg = rs.nextEstAreas()) {
                            int rta = this.evaluarCorte(cuantas, proceso, reg.getProceso(), subProceso, reg.getSubproceso(), meta, reg.getNombreMeta(), nombreArea, reg.getNombreArea());
                            if (rta > 0 && rta < 4) {
                                ++nroSubMetas;
                            }

                            switch(rta) {
                            case 1:
                            case 2:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                fondo = !fondo;
                                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                                eltr.appendChild(this.newtd("" + nombreSubProceso, 1, ""));
                                double dMetasCumplidas = Utilidades.round2(nroSubLogrosSuperados / nroSubLogros * nroSubMetas);
                                double miCumplimiento = Utilidades.round2(100.0D * dMetasCumplidas / nroSubMetas);
                                double miEficacia = Utilidades.round2(miCumplimiento * factor);
                                dEficaciaProceso += miEficacia;
                                eltr.appendChild(this.newtdLogro("" + Utilidades.round2(nroSubMetas), 0, "", ""));
                                eltr.appendChild(this.newtdLogro("" + dMetasCumplidas, 0, "", ""));
                                eltr.appendChild(this.newtdLogro("" + miCumplimiento, 0, "", ""));
                                nroGenMetas += nroSubMetas;
                                nroGenMetasCumplidas += dMetasCumplidas;
                                nroProMetas += nroSubMetas;
                                nroProMetasCumplidas += dMetasCumplidas;
                                objDatos.addValue(miCumplimiento, nombreSubProceso, "Cumplimiento");
                                if (bMostrarTodasLasAreas) {
                                    eltr.appendChild(this.newtdLogro("" + Utilidades.round2(miEficacia), 0, "", ""));
                                    eltr.appendChild(this.newtdLogro("" + factor * 100.0D, 0, "", ""));
                                    objDatos.addValue(miEficacia, nombreSubProceso, "Eficacia");
                                }

                                laTablaPrincipal.appendChild(eltr);
                                if (rta == 1) {
                                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtd("** " + nombreProceso + "  **", 1, "CA2"));
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(nroProMetas), 1, "CA2"));
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(nroProMetasCumplidas), 1, "CA2"));
                                    eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProMetasCumplidas / nroProMetas), 1, "CA2"));
                                    laTablaPrincipal.appendChild(eltr);
                                    if (bMostrarTodasLasAreas) {
                                        eltr.appendChild(this.newtd("" + Utilidades.round2(dEficaciaProceso), 0, "CA2"));
                                        eltr.appendChild(this.newtd("", 0, "CA2"));
                                        objDatos.addValue(dEficaciaProceso, nombreSubProceso, "Eficacia");
                                    }

                                    dEficaciaProceso = 0.0D;
                                    nroProMetas = 0.0D;
                                    nroProMetasCumplidas = 0.0D;
                                }

                                nroSubLogros = 0.0D;
                                nroSubLogrosSuperados = 0.0D;
                                nroSubMetas = 0.0D;
                            }

                            switch(rta) {
                            case -1:
                            case 1:
                                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                eltr.appendChild(this.newtd("" + reg.getNombreProceso(), 4 + miColSpan, "CA"));
                                laTablaPrincipal.appendChild(eltr);
                            }

                            ++nroSubLogros;
                            ++dLogros;
                            if (reg.getCumple().equals("S")) {
                                ++nroSubLogrosSuperados;
                                ++dLogrosSuperados;
                            }

                            proceso = reg.getProceso();
                            subProceso = reg.getSubproceso();
                            nombreProceso = reg.getNombreProceso();
                            nombreSubProceso = reg.getNombreSubProceso();
                            meta = reg.getNombreMeta();
                            nombreArea = reg.getNombreArea();
                            factor = reg.getFactor() / 100.0D;
                            ++cuantas;
                        }

                        rs.close();
                        if (dLogros > 0.0D) {
                            ++nroSubMetas;
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            fondo = !fondo;
                            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                            eltr.appendChild(this.newtd("" + nombreSubProceso, 1, ""));
                            double dMetasCumplidas = Utilidades.round2(nroSubLogrosSuperados / nroSubLogros * nroSubMetas);
                            double miCumplimiento = Utilidades.round2(100.0D * dMetasCumplidas / nroSubMetas);
                            double miEficacia = Utilidades.round2(miCumplimiento * factor);
                            dEficaciaProceso += miEficacia;
                            eltr.appendChild(this.newtdLogro("" + Utilidades.round2(nroSubMetas), 0, "", ""));
                            eltr.appendChild(this.newtdLogro("" + Utilidades.round2(dMetasCumplidas), 0, "", ""));
                            eltr.appendChild(this.newtdLogro("" + Utilidades.round2(miCumplimiento), 0, "", ""));
                            nroProMetas += nroSubMetas;
                            nroProMetasCumplidas += dMetasCumplidas;
                            nroGenMetas += nroSubMetas;
                            nroGenMetasCumplidas += dMetasCumplidas;
                            objDatos.addValue(miCumplimiento, nombreSubProceso, "Cumplimiento");
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtdLogro("" + Utilidades.round2(miEficacia), 0, "", ""));
                                eltr.appendChild(this.newtdLogro("" + factor * 100.0D, 0, "", ""));
                                objDatos.addValue(miEficacia, nombreSubProceso, "Eficacia");
                            }

                            laTablaPrincipal.appendChild(eltr);
                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            eltr.appendChild(this.newtd("** " + nombreProceso + "  **", 1, "CA2"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(nroProMetas), 1, "CA2"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(nroProMetasCumplidas), 1, "CA2"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroProMetasCumplidas / nroProMetas), 1, "CA2"));
                            laTablaPrincipal.appendChild(eltr);
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("" + Utilidades.round2(dEficaciaProceso), 0, "CA2"));
                                eltr.appendChild(this.newtd("", 0, "CA2"));
                                objDatos.addValue(dEficaciaProceso, nombreSubProceso, "Eficacia");
                            }

                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            eltr.appendChild(this.newtd(" TOTAL GENERAL  **", 1, "CA"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(nroGenMetas), 1, "CA"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(nroGenMetasCumplidas), 1, "CA"));
                            eltr.appendChild(this.newtd("" + Utilidades.round2(100.0D * nroGenMetasCumplidas / nroGenMetas), 1, "CA"));
                            if (bMostrarTodasLasAreas) {
                                eltr.appendChild(this.newtd("", 2, "CA"));
                            }

                            laTablaPrincipal.appendChild(eltr);
                        }

                        if (!operacion.equals("GRAFICA")) {
                            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                            eltr = this.pagHTML.getElementTrSeleccionar();
                            eltr.getParentNode().removeChild(eltr);
                            eltr = this.pagHTML.getElementTrEstArea();
                            eltr.getParentNode().removeChild(eltr);
                            eltr = this.pagHTML.getElementTrProcesos();
                            eltr.getParentNode().removeChild(eltr);
                            eltr = this.pagHTML.getElementTrEstRES();
                            eltr.getParentNode().removeChild(eltr);
                            comms.response.writeDOM(this.pagHTML);
                        } else {
                            JFreeChart objGrafico = ChartFactory.createBarChart3D("Evalución por Sub Procesos", "SubProcesos", "Valores", objDatos, PlotOrientation.VERTICAL, true, true, false);

                            try {
                                ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), objGrafico, 1200, 400);
                            } catch (Exception var59) {
                                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafica"));
                            }
                        }

                    }
                }
            }
        }
    }

    private void verResumenAreas(HttpPresentationComms comms, String operacion) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = -1;

        try {
            area = Integer.parseInt(comms.request.getParameter("area"));
        } catch (Exception var27) {
        }

        if (area == -1) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=Cal_DebeEscogerArea"));
        } else {
            int periodo1 = Integer.parseInt(comms.request.getParameter("periodo1"));
            int periodo2 = Integer.parseInt(comms.request.getParameter("periodo2"));
            int areasHijas = 0;

            try {
                areasHijas = Integer.parseInt(comms.request.getParameter("areasHijas"));
            } catch (Exception var26) {
            }

            String sMiProceso = comms.request.getParameter("proceso");
            if (sMiProceso == null) {
                sMiProceso = "";
            }

            String tipoProceso = comms.request.getParameter("tipoProceso");
            if (tipoProceso == null) {
                tipoProceso = "";
            }

            this.pagHTML = (CalEstadisticaHTML)comms.xmlcFactory.create(CalEstadisticaHTML.class);
            int codigoPlan = 0;
            String secuencia = "";
            if (area > 0) {
                CalPlanesDAO rsPlanes = new CalPlanesDAO();
                if (!rsPlanes.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                }

                CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
                rsPlanes.close();
                if (regPlan != null) {
                    codigoPlan = regPlan.getCodigoPlan();
                }

                AreasDAO areaf = new AreasDAO();
                AreasDTO regArea = areaf.getArea(area);
                areaf.close();
                secuencia = regArea.getSecuencia();
                this.pagHTML.setTextNombreAreaRES(regArea != null ? regArea.getDescripcion() : "");
            }

            CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
            if (!rsCalCiclos.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
                rsCalCiclos.close();
                if (regCiclo != null) {
                    this.pagHTML.setTextNombreCicloRES(regCiclo.getDescripcion());
                }

                this.pagHTML.setTextNombrePeriodo1RES(Utilidades.nombreMes(periodo1));
                this.pagHTML.setTextNombrePeriodo2RES(Utilidades.nombreMes(periodo2));
                this.pagHTML.getElementCicloRES().setValue("" + ciclo);
                this.pagHTML.getElementAreaRES().setValue("" + area);
                this.pagHTML.getElementProcesoRES().setValue("" + sMiProceso);
                this.pagHTML.getElementPeriodo1RES().setValue("" + periodo1);
                this.pagHTML.getElementPeriodo2RES().setValue("" + periodo2);
                this.pagHTML.getElementAreasHijasRES().setValue("" + areasHijas);
                this.pagHTML.getElementTipoProcesoRES().setValue("" + tipoProceso);
                CalLogrosDAO rs = new CalLogrosDAO();
                if (!rs.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                } else {
                    rs.generarEstadistica2(ciclo, codigoPlan, periodo1, periodo2, areasHijas, secuencia, sMiProceso, tipoProceso);
                    CalLogrosDTO reg = rs.next2();
                    HTMLTableElement hte = this.pagHTML.getElementTblEstRES();
                    boolean fondo = true;
                    int cuantos = 0;
                    double dSumaCumplimiento = 0.0D;

                    DefaultCategoryDataset objDatos;
                    for(objDatos = new DefaultCategoryDataset(); reg != null; reg = rs.next2()) {
                        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        fondo = !fondo;
                        eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                        eltr.appendChild(this.newtd("" + reg.getNombreArea(), 0));
                        if (reg.getNumeroLogros() != 0) {
                            dSumaCumplimiento += (double)reg.getCumplen() / (double)reg.getNumeroLogros() * 100.0D;
                            eltr.appendChild(this.newtdLogro("" + Utilidades.round2((double)reg.getCumplen() / (double)reg.getNumeroLogros() * 100.0D), 0, "", ""));
                        } else {
                            eltr.appendChild(this.newtdLogro("", 0, "", ""));
                        }

                        ++cuantos;
                        objDatos.addValue(Utilidades.round2((double)reg.getCumplen() / (double)reg.getNumeroLogros() * 100.0D), reg.getNombreArea(), "Cumplimiento");
                        hte.appendChild(eltr);
                    }

                    rs.close();
                    if (cuantos > 0) {
                        this.pagHTML.setTextCumplimiento("" + Utilidades.round2(dSumaCumplimiento / (double)cuantos));
                    }

                    this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                    HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
                    eltr.getParentNode().removeChild(eltr);
                    eltr = this.pagHTML.getElementTrSubprocesos();
                    eltr.getParentNode().removeChild(eltr);
                    eltr = this.pagHTML.getElementTrProcesos();
                    eltr.getParentNode().removeChild(eltr);
                    eltr = this.pagHTML.getElementTrEstArea();
                    eltr.getParentNode().removeChild(eltr);
                    if (!operacion.equals("GRAFICA")) {
                        comms.response.writeDOM(this.pagHTML);
                    } else {
                        JFreeChart objGrafico = ChartFactory.createBarChart3D("Evalución por Areas", "Areas", "Valores", objDatos, PlotOrientation.VERTICAL, true, true, false);

                        try {
                            ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), objGrafico, 1200, 400);
                        } catch (Exception var25) {
                            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafica"));
                        }
                    }

                }
            }
        }
    }

    private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion, boolean incluirTodos) {
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(" "));
        combo.appendChild(op);
        rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);

        TGeneralDTO RegGeneral;
        for(; (RegGeneral = rsTGen.next()) != null; combo.appendChild(op)) {
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + RegGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void comboMeses(HTMLSelectElement combo, int defecto) {
        String[] meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        for(int mes = 1; mes <= 12; ++mes) {
            HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
            op2.setValue("" + mes);
            op2.appendChild(this.pagHTML.createTextNode(meses[mes - 1]));
            if (mes == defecto) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op2.setAttributeNode(escogida);
            }

            combo.appendChild(op2);
        }

    }

    private void comboAreas(HTMLSelectElement combo, int area1, boolean verTodas, boolean verHijas, boolean oVerArriba) {
        new ArrayList();
        AreasDAO af = new AreasDAO();
        Collection arr;
        AreasDTO area;
        if (verTodas) {
            arr = af.cargarTodos();
        } else if (oVerArriba) {
            int iSuperior1 = 0;
            area = af.getArea(area1);
            if (area != null) {
                iSuperior1 = area.getNivelSuperior();
            }

            arr = af.cargarSecuencia(area1, iSuperior1);
        } else if (verHijas) {
            arr = af.cargarSecuencia(area1);
        } else {
            arr = af.cargarArea(area1);
        }

        af.close();
        if (verTodas) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("0");
            op.appendChild(this.pagHTML.createTextNode(" "));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            area = (AreasDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + area.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
            if (area1 == area.getCodigo()) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private int evaluarCorte(int cuantas, String procesoOld, String proceso, String subProcesoOld, String subproceso, String metaOld, String meta, String areaOld, String area) {
        if (cuantas == 0) {
            return -1;
        } else if (!procesoOld.equals(proceso)) {
            return 1;
        } else if (!subProcesoOld.equals(subproceso)) {
            return 2;
        } else if (!metaOld.equals(meta)) {
            return 3;
        } else {
            return !areaOld.equals(area) ? 4 : 0;
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtd(String contenido, int colspan, String clase) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        return td;
    }

    private HTMLElement newtd(String contenido, int colspan, String clase, int ancho) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        return td;
    }

    private HTMLElement newtd(String contenido, int ancho) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        return td;
    }

    private HTMLElement newtdLogro(String contenido, int ancho, String clase, String title) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%"));
        }

        td.setAttributeNode(this.newAttr("align", "center"));
        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (title.length() > 0) {
            td.setAttributeNode(this.newAttr("title", title));
        }

        return td;
    }
}
