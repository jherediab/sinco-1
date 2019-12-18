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
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.AreasDTO;
import sinco.business.CalCiclosDTO;
import sinco.business.CalLogrosDTO;
import sinco.business.CalMetasDTO;
import sinco.business.CalObjetivosDTO;
import sinco.business.CalPlanesDTO;
import sinco.business.ParametrosDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.AreasDAO;
import sinco.data.CalCiclosDAO;
import sinco.data.CalLogrosDAO;
import sinco.data.CalPlanMetasDAO;
import sinco.data.CalPlanObjetivosDAO;
import sinco.data.CalPlanesDAO;
import sinco.data.SeguridadDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class CalVerResultados implements HttpPresentation {
    private CalVerResultadosHTML pagHTML;
    private int numeroNota = 1;
    String idNav = "";
    String elUsuario = "";

    public CalVerResultados() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            this.idNav = (String)comms.session.getSessionData().get("miId");
            this.elUsuario = "" + comms.session.getUser().getName();
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "FASE1";
            }

            if (!_operacion.equals("FASE1") && !_operacion.equals("RECARGA")) {
                if (_operacion.equals("VER")) {
                    this.verPlan(comms);
                } else if (_operacion.equals("GRAFICA")) {
                    this.verGrafica(comms);
                } else {
                    this.pagHTML = (CalVerResultadosHTML)comms.xmlcFactory.create(CalVerResultadosHTML.class);
                    this.pagHTML.setTextElMenu(MenuDO.getMenu(this.idNav));
                    comms.response.writeDOM(this.pagHTML);
                }
            } else {
                this.fase1(comms);
            }
        }
    }

    private void fase1(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var11) {
        }

        this.pagHTML = (CalVerResultadosHTML)comms.xmlcFactory.create(CalVerResultadosHTML.class);
        TGeneralDAO rsTGen = new TGeneralDAO();
        if (!rsTGen.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
            this.llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado<>'D' order by ciclo desc");
            rsTGen.close();
            if (ciclo > 0) {
                SisUsuariosDAO pf2 = new SisUsuariosDAO();
                SisUsuariosDTO p = pf2.cargarRegistro(Integer.parseInt(this.idNav));
                SeguridadDAO rsSeguridad = new SeguridadDAO();
                if (!rsSeguridad.getEstadoConexion()) {
                    return;
                }

                boolean verTodas = rsSeguridad.tieneLlave(miGrupo, "cal_ver_todas_las_areas");
                boolean oVerArriba = rsSeguridad.tieneLlave(miGrupo, "cal_ver_area_superior");
                rsSeguridad.close();
                combo = this.pagHTML.getElementIdArea();
                this.comboAreas(combo, ciclo, p.getArea(), verTodas, oVerArriba);
            } else {
                HTMLElement sel = this.pagHTML.getElementTrArea();
                sel.getParentNode().removeChild(sel);
            }

            HTMLElement eltr = this.pagHTML.getElementTrPlan();
            eltr.getParentNode().removeChild(eltr);
            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void resumen(int ciclo, int codigoPlan) {
        HTMLTableSectionElement hte = this.pagHTML.getElementResumen();
        CalLogrosDAO rs = new CalLogrosDAO();
        Collection arr = rs.cumplimentoPlan(ciclo, codigoPlan);
        rs.close();
        boolean fondo = false;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            CalLogrosDTO reg = (CalLogrosDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            eltr.appendChild(this.newtd("" + reg.getNombreProceso()));
            eltr.appendChild(this.newtd("" + Utilidades.miles2(reg.getValorLogro())));
            hte.appendChild(eltr);
        }

        arr.clear();
    }

    private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = Integer.parseInt(comms.request.getParameter("area"));
        AreasDAO areaf = new AreasDAO();
        AreasDTO regArea = areaf.getArea(area);
        areaf.close();
        SeguridadDAO rsSeguridad = new SeguridadDAO();
        if (!rsSeguridad.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            boolean bActulizarJustificacion = rsSeguridad.tieneLlave(miGrupo, "cal_actualizar_justificacion_plan");
            rsSeguridad.close();
            this.pagHTML = (CalVerResultadosHTML)comms.xmlcFactory.create(CalVerResultadosHTML.class);
            CalPlanesDAO rsPlanes = new CalPlanesDAO();
            if (!rsPlanes.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
                rsPlanes.close();
                this.pagHTML.getElementCicloV().setValue("" + ciclo);
                this.pagHTML.getElementCodigoPlanV().setValue("" + regPlan.getCodigoPlan());
                this.pagHTML.getElementAreaV().setValue("" + area);
                CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
                if (!rsCalCiclos.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                } else {
                    CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
                    rsCalCiclos.close();
                    if (regCiclo != null) {
                        this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
                    }

                    this.pagHTML.setTextNumeroPlan("" + regPlan.getCodigoPlan());
                    this.pagHTML.setTextNombreAreaA(regArea != null ? regArea.getDescripcion() : "");
                    CalPlanObjetivosDAO rs = new CalPlanObjetivosDAO();
                    if (!rs.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    } else {
                        HTMLTableElement hte = this.pagHTML.getElementPlan();
                        boolean fondo = true;
                        rs.cargarPlan(ciclo, regPlan.getCodigoPlan(), "A");
                        int cuantas = 0;
                        CalObjetivosDTO objetivo = rs.next();
                        String proceso = "";

                        for(String subProceso = ""; objetivo != null; ++cuantas) {
                            HTMLElement eltr;
                            if (cuantas == 0 || !proceso.equals(objetivo.getProceso()) || !subProceso.equals(objetivo.getSubProceso())) {
                                if (!proceso.equals(objetivo.getProceso()) || cuantas == 0) {
                                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtd("" + objetivo.getNombreProceso() + objetivo.getEstadoProceso(), "CA", 0));
                                    eltr.appendChild(this.losTitulos());
                                    hte.appendChild(eltr);
                                }

                                if (cuantas == 0 || !proceso.equals(objetivo.getProceso()) || !subProceso.equals(objetivo.getSubProceso())) {
                                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtd("" + objetivo.getNombreSubProceso() + objetivo.getEstadoSubProceso(), "CA2", 2));
                                    hte.appendChild(eltr);
                                }
                            }

                            eltr = (HTMLElement)this.pagHTML.createElement("tr");
                            fondo = !fondo;
                            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                            String url = "CalDetalleObjetivo.po?_operacion=VO&codigoCiclo=" + ciclo + "&codigoPlan=" + regPlan.getCodigoPlan() + "&codigoObjetivo=" + objetivo.getCodigoObjetivo();
                            eltr.appendChild(this.newtdTitle("" + objetivo.getDescripcion() + objetivo.getNombreEstado(), url, 10, "", "Estado:" + objetivo.getNombreEstado() + "\r\nTipo De objetivo:" + objetivo.getNombreTipoObjetivo()));
                            eltr.appendChild(this.lasMetas(ciclo, area, regPlan.getCodigoPlan(), objetivo.getCodigoObjetivo(), bActulizarJustificacion));
                            hte.appendChild(eltr);
                            proceso = objetivo.getProceso();
                            subProceso = objetivo.getSubProceso();
                            objetivo = rs.next();
                        }

                        rs.close();
                        this.resumen(ciclo, regPlan.getCodigoPlan());
                        HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
                        eltr.getParentNode().removeChild(eltr);
                        this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                        comms.response.writeDOM(this.pagHTML);
                    }
                }
            }
        }
    }

    private HTMLElement lasMetas(int ciclo, int area, int plan, int objetivo, boolean bActulizarJustificacion) {
        HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
        laTabla.setAttributeNode(this.newAttr("class", "fprofb"));
        laTabla.setAttributeNode(this.newAttr("align", "left"));
        laTabla.setAttributeNode(this.newAttr("width", "100%%"));
        int annoActual = Utilidades.getAnnoActual();
        CalPlanMetasDAO rs = new CalPlanMetasDAO();
        Collection arr = rs.cargarDeObjetivo(ciclo, plan, objetivo, 0, "A");
        rs.close();
        CalLogrosDAO rsLogros = new CalLogrosDAO();
        boolean fondo = true;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            CalMetasDTO meta = (CalMetasDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            String url = "CalDetalleObjetivo.po?_operacion=Me&codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + meta.getCodigoMeta();
            eltr.appendChild(this.newtdTitle("" + meta.getDescripcion() + meta.getNombreEstadoVer(), url, 22, "", "Estado:" + meta.getNombreEstado() + "\r\n Frecuencia Medición: " + meta.getNombreFrecuenciaMedicion() + " Unidad Medida:" + meta.getNombreUnidadMedida()));
            eltr.appendChild(this.newtd("" + meta.getNombreFrecuenciaMedicion(), 6, ""));
            Collection arrLogros = rsLogros.cargarLogros(ciclo, plan, objetivo, meta.getCodigoMeta());
            Vector vVector = new Vector();
            vVector.setSize(12);
            Iterator iLogros = arrLogros.iterator();

            while(iLogros.hasNext()) {
                CalLogrosDTO regLogro = (CalLogrosDTO)iLogros.next();
                vVector.add(regLogro.getPeriodo() - 1, regLogro);
            }

            int noCumplen = 0;

            for(int i = 0; i < 12; ++i) {
                boolean obliga = false;
                int miPeriodo = i + 1;
                if ((annoActual == ciclo && miPeriodo < Utilidades.getMesActual() || ciclo < annoActual) && (meta.getMes01().equals("S") && miPeriodo == 1 || meta.getMes02().equals("S") && miPeriodo == 2 || meta.getMes03().equals("S") && miPeriodo == 3 || meta.getMes04().equals("S") && miPeriodo == 4 || meta.getMes05().equals("S") && miPeriodo == 5 || meta.getMes06().equals("S") && miPeriodo == 6 || meta.getMes07().equals("S") && miPeriodo == 7 || meta.getMes08().equals("S") && miPeriodo == 8 || meta.getMes09().equals("S") && miPeriodo == 9 || meta.getMes10().equals("S") && miPeriodo == 10 || meta.getMes11().equals("S") && miPeriodo == 11 || meta.getMes12().equals("S") && miPeriodo == 12)) {
                    obliga = true;
                }

                CalLogrosDTO regLogro;
                try {
                    regLogro = (CalLogrosDTO)vVector.elementAt(i);
                } catch (Exception var27) {
                    regLogro = null;
                }

                String sResultado;
                if (regLogro != null) {
                    sResultado = "" + regLogro.getValorLogro();
                    if (regLogro.getValorMeta() != regLogro.getValorMetaPlan()) {
                        sResultado = "" + regLogro.getValorLogro() + " / " + regLogro.getValorMeta();
                    }

                    String consecutivos;
                    if (regLogro.getEstado().equals("T")) {
                        consecutivos = "CalJustificacion.po?codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + meta.getCodigoMeta() + "&periodo=" + miPeriodo + "&consecutivos=N&codigoArea=" + area + "&cumple=X";
                        eltr.appendChild(this.newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), consecutivos, 6, "verde", "center", regLogro.getJustificacion(), false));
                    } else if (regLogro.getCumple().equals("S")) {
                        if (bActulizarJustificacion) {
                            consecutivos = "CalJustificacion.po?codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + regLogro.getCodigoMeta() + "&periodo=" + regLogro.getPeriodo() + "&consecutivos=N&codigoArea=" + area + "&cumple=S";
                            eltr.appendChild(this.newtdhref("" + sResultado + " " + meta.getNombreUnidadMed(), consecutivos, 6, "verde", "center", regLogro.getJustificacion(), false));
                        } else {
                            eltr.appendChild(this.newtdMeta("" + sResultado + " " + meta.getNombreUnidadMed(), 6, "verde", "center", regLogro.getJustificacion()));
                        }

                        noCumplen = 0;
                    } else {
                        ++noCumplen;
                        consecutivos = "N";
                        if (noCumplen >= ParametrosDTO.getInt("CalNumeroElementoNoCumplen")) {
                            consecutivos = "S";
                        }

                        if (bActulizarJustificacion) {
                            String sPagina = "CalJustificacion.po?codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + regLogro.getCodigoMeta() + "&periodo=" + regLogro.getPeriodo() + "&consecutivos=" + consecutivos + "&codigoArea=" + area + "&cumple=N";
                            eltr.appendChild(this.newtdhref("" + sResultado + " " + meta.getNombreUnidadMed(), sPagina, 6, "rojo", "center", regLogro.getJustificacion(), false));
                        } else {
                            eltr.appendChild(this.newtdMeta("" + sResultado + " " + meta.getNombreUnidadMed(), 6, "rojo", "center", regLogro.getJustificacion()));
                        }
                    }
                } else if (obliga) {
                    sResultado = "CalJustificacion.po?codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + meta.getCodigoMeta() + "&periodo=" + miPeriodo + "&consecutivos=N&codigoArea=" + area + "&cumple=X";
                    eltr.appendChild(this.newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sResultado, 6, "verde", "center", "", false));
                } else {
                    eltr.appendChild(this.newtdMeta(" ", 6, "", "", ""));
                }
            }

            eltr.appendChild(this.tdAcciones(ciclo, plan, meta.getCodigoMeta(), meta.getNumeroAcciones()));
            String sPagina = "CalVerGrafica.po?_operacion=L&codigoCiclo=" + ciclo + "&codigoPlan=" + plan + "&codigoObjetivo=" + objetivo + "&codigoMeta=" + meta.getCodigoMeta() + "&tg=" + meta.getTipoGrafica();
            eltr.appendChild(this.newtdhref("Grafico", sPagina, 6, "verde", "center", true, meta.getTipoGrafica()));
            laTabla.appendChild(eltr);
        }

        rsLogros.close();
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(laTabla);
        return td;
    }

    private HTMLElement losTitulos() {
        HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
        laTabla.setAttributeNode(this.newAttr("class", "tabw"));
        laTabla.setAttributeNode(this.newAttr("align", "left"));
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtdMeta("Meta", 22, "cf2", "center", "Descripción Meta"));
        eltr.appendChild(this.newtdMeta("Frec.", 6, "cf1", "center", "Frecuencia"));

        for(int i = 1; i <= 12; ++i) {
            eltr.appendChild(this.newtdMeta(Utilidades.nombreMes(i).substring(0, 3), 6, "cf" + (i % 2 == 0 ? 1 : 2), "center", Utilidades.nombreMes(i)));
        }

        eltr.appendChild(this.newtdMeta("Acción", 6, "cf1", "center", ""));
        eltr.appendChild(this.newtdMeta("Graf", 6, "cf2", "center", ""));
        laTabla.appendChild(eltr);
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        laTabla.setAttributeNode(this.newAttr("class", "CF2"));
        td.appendChild(laTabla);
        return td;
    }

    private void verGrafica(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int codigoPlan = Integer.parseInt(comms.request.getParameter("codigoPlan"));
        CalLogrosDAO rs = new CalLogrosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            Collection arr = rs.cumplimentoPlan(ciclo, codigoPlan);
            rs.close();
            DefaultPieDataset dataset = new DefaultPieDataset();
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                CalLogrosDTO reg = (CalLogrosDTO)iterator.next();
                dataset.setValue("" + reg.getNombreProceso() + " " + Utilidades.miles(reg.getValorLogro(), 2), reg.getValorLogro());
            }

            JFreeChart jfreechart = ChartFactory.createPieChart("Resultados Del Plan", dataset, true, true, false);
            PiePlot pieplot = (PiePlot)jfreechart.getPlot();
            pieplot.setBackgroundPaint(new Color(231, 231, 231));
            double valor = 0.0D;
            iterator = arr.iterator();

            int i;
            CalLogrosDTO reg;
            for(i = 0; iterator.hasNext(); valor += reg.getValorLogro()) {
                reg = (CalLogrosDTO)iterator.next();
                Color color = new Color(0, 104, 57);
                if (i % 6 == 0) {
                    color = new Color(0, 104, 57);
                } else if (i % 6 == 1) {
                    color = new Color(255, 102, 0);
                } else if (i % 6 == 2) {
                    color = new Color(0, 153, 0);
                } else if (i % 6 == 3) {
                    color = new Color(0, 143, 79);
                } else if (i % 6 == 4) {
                    color = new Color(160, 197, 160);
                } else if (i % 6 == 5) {
                    color = new Color(0, 126, 0);
                }

                ++i;
                pieplot.setSectionPaint("" + reg.getNombreProceso() + " " + Utilidades.miles(reg.getValorLogro(), 2), color);
            }

            if (i > 0) {
                valor /= (double)i;
            }

            iterator = arr.iterator();

            while(iterator.hasNext()) {
                reg = (CalLogrosDTO)iterator.next();
                if (reg.getValorLogro() >= valor) {
                    pieplot.setExplodePercent("" + reg.getNombreProceso() + " " + Utilidades.miles(reg.getValorLogro(), 2), 0.01001D);
                } else {
                    pieplot.setExplodePercent("" + reg.getNombreProceso() + " " + Utilidades.miles(reg.getValorLogro(), 2), 0.15001D);
                }
            }

            try {
                ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreechart, ParametrosDTO.getInt("CAL_ANCHO_GRAFICA"), ParametrosDTO.getInt("CAL_ALTO_GRAFICA"));
            } catch (Exception var15) {
                var15.printStackTrace();
                Utilidades.writeError("CalVerGrafia ", var15);
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafico"));
            }
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private void newNota(String contenido) {
        Element anotacion = this.pagHTML.createElement("div");
        anotacion.setAttributeNode(this.newAttr("class", "atip"));
        anotacion.setAttributeNode(this.newAttr("style", "width:262px"));
        anotacion.setAttributeNode(this.newAttr("id", "sticky" + this.numeroNota));
        Node hijo = this.pagHTML.createTextNode(contenido);
        anotacion.appendChild(hijo);
        HTMLElement notaalpie = this.pagHTML.getElementNotasAlPie();
        notaalpie.appendChild(anotacion);
        ++this.numeroNota;
    }

    private HTMLElement tdAcciones(int ciclo, int plan, int meta, int acciones) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
        laTabla.setAttributeNode(this.newAttr("class", "tabw"));
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        String sPagina = "AMActV2.po?_operacion=Nuevo&asociado=P&ciclo=" + ciclo + "&plan=" + plan + "&meta=" + meta;
        eltr.appendChild(this.newtdhref("Acción", sPagina, 0, "verde", "center", "", true));
        HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
        sPagina = "AMPlan.po?_operacion=L&ciclo=" + ciclo + "&plan=" + plan + "&meta=" + meta;
        eltr2.appendChild(this.newtdhref("" + acciones, sPagina, 0, "verde", "center", "", true));
        laTabla.appendChild(eltr);
        laTabla.appendChild(eltr2);
        td.appendChild(laTabla);
        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, int ancho, String clase, String align, String title, boolean nuevaVentana) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        if (title.length() > 0) {
            enlace.setAttributeNode(this.newAttr("data-tooltip", "sticky" + this.numeroNota));
        }

        if (clase.equals("rojo")) {
            enlace.setAttributeNode(this.newAttr("class", "blanco"));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        donde.setValue(vinculo);
        if (nuevaVentana) {
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        }

        enlace.setAttributeNode(donde);
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        if (align.length() > 0) {
            td.setAttributeNode(this.newAttr("align", "center"));
        }

        td.appendChild(enlace);
        if (title.length() > 0) {
            this.newNota(title);
        }

        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, int ancho, String clase, String align, boolean nuevaVentana, String tipoGrafica) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLImageElement imagen = (HTMLImageElement)this.pagHTML.createElement("IMG");
        if (tipoGrafica.equals("L")) {
            imagen.setSrc("media/icon_linea.jpg");
        } else if (tipoGrafica.equals("T")) {
            imagen.setSrc("media/icon_torta.jpg");
        } else {
            imagen.setSrc("media/icon_barra.jpg");
        }

        Element enlace = this.pagHTML.createElement("a");
        enlace.appendChild(imagen);
        Attr donde = this.pagHTML.createAttribute("href");
        if (nuevaVentana) {
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        }

        if (clase.equals("rojo")) {
            enlace.setAttributeNode(this.newAttr("class", "blanco"));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        if (align.length() > 0) {
            td.setAttributeNode(this.newAttr("align", "center"));
        }

        td.appendChild(enlace);
        return td;
    }

    private HTMLElement newtdMeta(String contenido, int ancho, String clase, String align, String title) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (align.length() > 0) {
            td.setAttributeNode(this.newAttr("align", "center"));
        }

        if (title.length() > 0) {
            td.setAttributeNode(this.newAttr("title", title));
        }

        return td;
    }

    private HTMLElement newtd(String contenido, String clase, int colspan) {
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

    private HTMLElement newtd(String contenido, int ancho, String clase) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        return td;
    }

    private HTMLElement newtdTitle(String contenido, String vinculo, int ancho, String clase, String title) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        if (clase.equals("rojo")) {
            enlace.setAttributeNode(this.newAttr("class", "blanco"));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (vinculo.length() > 0) {
            Attr donde = this.pagHTML.createAttribute("href");
            donde.setValue(vinculo);
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
            enlace.setAttributeNode(donde);
        }

        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%%"));
        }

        td.appendChild(enlace);
        return td;
    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion) {
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("0");
        op.appendChild(this.pagHTML.createTextNode(""));
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

    private void comboAreas(HTMLSelectElement combo, int ciclo, int area1, boolean verTodas, boolean oVerArriba) {
        new ArrayList();
        AreasDAO af = new AreasDAO();
        Collection arr;
        if (verTodas) {
            arr = af.cargarAreasCicloCalidad(ciclo);
        } else {
            String superior = "";
            if (oVerArriba) {
                Varios oVarios = new Varios();
                superior = oVarios.getNivelSuperior(area1, ParametrosDTO.getInt("cal_numero_areas_arriba"));
            }

            arr = af.cargarSecuenciaCalidad(ciclo, area1, superior);
        }

        af.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            AreasDTO area = (AreasDTO)iterator.next();
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
}
