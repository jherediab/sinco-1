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
import java.awt.GradientPaint;
import java.awt.Paint;
import java.util.Collection;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.w3c.dom.Attr;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.CalCiclosDTO;
import sinco.business.CalLogrosDTO;
import sinco.business.CalMetasDTO;
import sinco.business.CalObjetivosDTO;
import sinco.business.CalPlanesDTO;
import sinco.business.ParametrosDTO;
import sinco.business.Utilidades;
import sinco.data.CalCiclosDAO;
import sinco.data.CalLogrosDAO;
import sinco.data.CalPlanMetasDAO;
import sinco.data.CalPlanObjetivosDAO;
import sinco.data.CalPlanesDAO;
import sinco.spec.MenuDO;

public class CalVerGrafica implements HttpPresentation {
    private CalVerGraficaHTML pagHTML;
    int idNav = 0;
    String elUsuario = "";

    public CalVerGrafica() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            this.elUsuario = "" + comms.session.getUser().getName();
            String operacion = comms.request.getParameter("_operacion");
            if (operacion == null || operacion.length() == 0) {
                operacion = "L";
            }

            if (operacion.equals("G")) {
                this.grafico(comms);
            } else {
                this.pagHTML = (CalVerGraficaHTML)comms.xmlcFactory.create(CalVerGraficaHTML.class);
                this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
                this.fase1(comms, operacion);
                comms.response.writeDOM(this.pagHTML);
            }
        }
    }

    private void fase1(HttpPresentationComms comms, String reporte) throws HttpPresentationException, KeywordValueException {
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("codigoCiclo"));
        } catch (Exception var30) {
        }

        int codigoPlan = 0;

        try {
            codigoPlan = Integer.parseInt(comms.request.getParameter("codigoPlan"));
        } catch (Exception var29) {
        }

        int codigoObjetivo = 0;

        try {
            codigoObjetivo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
        } catch (Exception var28) {
        }

        int codigoMeta = 0;

        try {
            codigoMeta = Integer.parseInt(comms.request.getParameter("codigoMeta"));
        } catch (Exception var27) {
        }

        String tipoGrafica = comms.request.getParameter("tg");
        if (tipoGrafica == null) {
            tipoGrafica = "B";
        }

        CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
        if (!rsCalCiclos.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
            rsCalCiclos.close();
            if (regCiclo != null) {
                this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
            }

            CalPlanesDAO rsPlanes = new CalPlanesDAO();
            if (!rsPlanes.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                CalPlanesDTO regPlan = rsPlanes.cargarArea(ciclo, codigoPlan);
                rsPlanes.close();
                if (regPlan != null) {
                    this.pagHTML.setTextNombreAreaA(regPlan.getNombreArea());
                }

                CalPlanObjetivosDAO rsCalObjetivos = new CalPlanObjetivosDAO();
                if (!rsCalObjetivos.getEstadoConexion()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                } else {
                    CalObjetivosDTO regObj = rsCalObjetivos.cargarRegistro(ciclo, codigoPlan, codigoObjetivo);
                    rsCalObjetivos.close();
                    if (regObj != null) {
                        this.pagHTML.setTextObjetivo(regObj.getDescripcion());
                    }

                    CalPlanMetasDAO rsMeta = new CalPlanMetasDAO();
                    if (!rsMeta.getEstadoConexion()) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                    } else {
                        CalMetasDTO regMeta = rsMeta.cargarRegistro(ciclo, codigoPlan, codigoMeta);
                        rsMeta.close();
                        if (regMeta != null) {
                            this.pagHTML.setTextMeta(regMeta.getDescripcion());
                        }

                        CalLogrosDAO rs = new CalLogrosDAO();
                        if (!rs.getEstadoConexion()) {
                            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                        } else {
                            Collection arr = rs.cargarLogros(ciclo, codigoPlan, codigoObjetivo, codigoMeta);
                            rs.close();
                            HTMLImageElement laFirma = this.pagHTML.getElementFoto();
                            String sPagina = "CalVerGrafica.po?_operacion=G&codigoCiclo=" + ciclo + "&codigoPlan=" + codigoPlan + "&codigoObjetivo=" + codigoObjetivo + "&codigoMeta=" + codigoMeta + "&tg=" + tipoGrafica;
                            laFirma.setSrc(sPagina);
                            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
                            double valor = 0.0D;
                            int cuantas = 0;

                            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                                CalLogrosDTO reg = (CalLogrosDTO)iterator.next();
                                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                eltr.appendChild(this.newtd("" + Utilidades.nombreMes(reg.getPeriodo())));
                                if (reg.getCumple().equals("S")) {
                                    eltr.appendChild(this.newtd("" + reg.getValorLogro()));
                                } else {
                                    eltr.appendChild(this.newtdLogro("" + reg.getValorLogro(), 0, "rojo", ""));
                                }

                                hte.appendChild(eltr);
                                valor += reg.getValorLogro();
                            }

                            arr.clear();
                            this.pagHTML.setTextNroRegistros("" + cuantas);
                            this.pagHTML.setTextPromedio("" + (cuantas > 0 ? Utilidades.miles2(valor / (double)cuantas) : "0.0"));
                        }
                    }
                }
            }
        }
    }

    private static Paint[] createPaint() {
        Paint[] apaint = new Paint[]{new GradientPaint(0.0F, 0.0F, new Color(0, 104, 57), 0.0F, 0.0F, new Color(0, 143, 79)), new GradientPaint(0.0F, 0.0F, new Color(255, 102, 0), 0.0F, 0.0F, Color.white), new GradientPaint(0.0F, 0.0F, new Color(0, 143, 79), 0.0F, 0.0F, new Color(0, 104, 57)), new GradientPaint(0.0F, 0.0F, new Color(0, 153, 0), 0.0F, 0.0F, new Color(0, 143, 79)), new GradientPaint(0.0F, 0.0F, Color.yellow, 0.0F, 0.0F, Color.white)};
        return apaint;
    }

    private void grafico(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("codigoCiclo"));
        } catch (Exception var25) {
        }

        int codigoPlan = 0;

        try {
            codigoPlan = Integer.parseInt(comms.request.getParameter("codigoPlan"));
        } catch (Exception var24) {
        }

        int codigoObjetivo = 0;

        try {
            codigoObjetivo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
        } catch (Exception var23) {
        }

        int codigoMeta = 0;

        try {
            codigoMeta = Integer.parseInt(comms.request.getParameter("codigoMeta"));
        } catch (Exception var22) {
        }

        String tipoGrafica = comms.request.getParameter("tg");
        if (tipoGrafica == null) {
            tipoGrafica = "B";
        }

        DefaultCategoryDataset objDatos = new DefaultCategoryDataset();
        CalLogrosDAO rs = new CalLogrosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            Collection arr = rs.cargarLogros(ciclo, codigoPlan, codigoObjetivo, codigoMeta);
            rs.close();
            Iterator iterator;
            CalLogrosDTO reg;
            CategoryPlot categoryplot;
            JFreeChart jfreechart;
            if (tipoGrafica.equals("L")) {
                iterator = arr.iterator();

                while(iterator.hasNext()) {
                    reg = (CalLogrosDTO)iterator.next();
                    objDatos.addValue(reg.getValorLogro(), reg.getNombreMeta(), "" + Utilidades.nombreMes(reg.getPeriodo()));
                }

                jfreechart = ChartFactory.createLineChart("Resultados De la Meta", "Metas", "Valores", objDatos, PlotOrientation.VERTICAL, true, true, false);
                categoryplot = (CategoryPlot)jfreechart.getPlot();
                categoryplot.setBackgroundPaint(new Color(231, 231, 231));

                try {
                    ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreechart, ParametrosDTO.getInt("CAL_ANCHO_GRAFICA"), ParametrosDTO.getInt("CAL_ALTO_GRAFICA"));
                } catch (Exception var21) {
                    var21.printStackTrace();
                    Utilidades.writeError("CalVerGrafia ", var21);
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafico"));
                }
            } else if (tipoGrafica.equals("B")) {
                iterator = arr.iterator();

                while(iterator.hasNext()) {
                    reg = (CalLogrosDTO)iterator.next();
                    objDatos.addValue(reg.getValorLogro(), Utilidades.nombreMes(reg.getPeriodo()), "" + Utilidades.nombreMes(reg.getPeriodo()));
                }

                jfreechart = ChartFactory.createBarChart("Resultados De la Meta", "Metas", "Valores", objDatos, PlotOrientation.VERTICAL, true, true, false);
                categoryplot = (CategoryPlot)jfreechart.getPlot();
                categoryplot.setBackgroundPaint(new Color(231, 231, 231));
                categoryplot.setDomainGridlinesVisible(true);
                categoryplot.setRangeCrosshairVisible(true);
                categoryplot.setRangeCrosshairPaint(Color.blue);
                NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
                numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
                barrenderer.setDrawBarOutline(false);
                iterator = arr.iterator();
                int i = 0;

                while(iterator.hasNext()) {
                    reg = (CalLogrosDTO)iterator.next();
                    GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 104, 57), 0.0F, 0.0F, new Color(0, 104, 75));
                    if (i % 6 == 0) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 104, 57), 0.0F, 0.0F, new Color(0, 104, 75));
                    } else if (i % 6 == 1) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(255, 102, 0), 0.0F, 0.0F, new Color(255, 102, 20));
                    } else if (i % 6 == 2) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 153, 0), 0.0F, 0.0F, new Color(0, 153, 20));
                    } else if (i % 6 == 3) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 143, 79), 0.0F, 0.0F, new Color(0, 143, 99));
                    } else if (i % 6 == 4) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(160, 197, 160), 0.0F, 0.0F, new Color(160, 197, 180));
                    } else if (i % 6 == 5) {
                        gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(0, 126, 0), 0.0F, 0.0F, new Color(0, 126, 20));
                    }

                    ++i;
                    barrenderer.setSeriesPaint(i, gradientpaint);
                }

                barrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.HORIZONTAL));
                barrenderer.setDrawBarOutline(false);
                barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));

                try {
                    ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreechart, ParametrosDTO.getInt("CAL_ANCHO_GRAFICA"), ParametrosDTO.getInt("CAL_ALTO_GRAFICA"));
                } catch (Exception var20) {
                    var20.printStackTrace();
                    Utilidades.writeError("CalVerGrafia ", var20);
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafico"));
                }
            } else if (tipoGrafica.equals("T")) {
                DefaultPieDataset dataset = new DefaultPieDataset();
                iterator = arr.iterator();

                while(iterator.hasNext()) {
                    reg = (CalLogrosDTO)iterator.next();
                    dataset.setValue("" + Utilidades.nombreMes(reg.getPeriodo()) + " " + Utilidades.miles2(reg.getValorLogro()), reg.getValorLogro());
                }

                JFreeChart jfreeChart = ChartFactory.createPieChart("Resultados De la Meta", dataset, true, true, false);
                PiePlot pieplot = (PiePlot)jfreeChart.getPlot();
                pieplot.setBackgroundPaint(new Color(231, 231, 231));
                double valor = 0.0D;
                iterator = arr.iterator();

                int i;
                //CalLogrosDTO reg;
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
                    pieplot.setSectionPaint("" + Utilidades.nombreMes(reg.getPeriodo()) + " " + Utilidades.miles2(reg.getValorLogro()), color);
                }

                if (i > 0) {
                    valor /= (double)i;
                }

                iterator = arr.iterator();

                while(iterator.hasNext()) {
                    reg = (CalLogrosDTO)iterator.next();
                    if (reg.getValorLogro() >= valor) {
                        pieplot.setExplodePercent("" + Utilidades.nombreMes(reg.getPeriodo()) + " " + Utilidades.miles2(reg.getValorLogro()), 0.01001D);
                    } else {
                        pieplot.setExplodePercent("" + Utilidades.nombreMes(reg.getPeriodo()) + " " + Utilidades.miles2(reg.getValorLogro()), 0.15001D);
                    }
                }

                try {
                    ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreeChart, ParametrosDTO.getInt("CAL_ANCHO_GRAFICA"), ParametrosDTO.getInt("CAL_ALTO_GRAFICA"));
                } catch (Exception var19) {
                    var19.printStackTrace();
                    Utilidades.writeError("CalVerGrafia ", var19);
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafico"));
                }
            }

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

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }
}
