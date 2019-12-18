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
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.AreasDTO;
import sinco.business.CalCiclosDTO;
import sinco.business.CalEstadisticasDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.AreasDAO;
import sinco.data.CalCiclosDAO;
import sinco.data.CalEstadisticasDAO;
import sinco.data.SeguridadDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;

public class CalVerPlanEspecial implements HttpPresentation {
    private CalVerPlanEspecialHTML pagHTML;

    public CalVerPlanEspecial() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "FASE1";
            }

            this.pagHTML = (CalVerPlanEspecialHTML)comms.xmlcFactory.create(CalVerPlanEspecialHTML.class);
            HTMLDivElement sel;
            if (!_operacion.equals("FASE1") && !_operacion.equals("RECARGA")) {
                if (_operacion.equals("VER")) {
                    sel = this.pagHTML.getElementTrVarios();
                    sel.getParentNode().removeChild(sel);
                    this.verPlan(comms);
                } else if (_operacion.equals("VAL") || _operacion.equals("INC")) {
                    sel = this.pagHTML.getElementTrPlan();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrSeleccionar();
                    sel.getParentNode().removeChild(sel);
                    this.verDetalle(comms);
                }
            } else {
                sel = this.pagHTML.getElementTrVarios();
                sel.getParentNode().removeChild(sel);
                this.fase1(comms);
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void fase1(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var12) {
        }

        TGeneralDAO rsTGen = new TGeneralDAO();
        if (!rsTGen.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
            this.llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado<>'D' order by ciclo desc");
            if (ciclo > 0) {
                SisUsuariosDAO pf2 = new SisUsuariosDAO();
                SisUsuariosDTO p = pf2.cargarRegistro(idNav);
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

            rsTGen.close();
            HTMLElement eltr = this.pagHTML.getElementTrPlan();
            eltr.getParentNode().removeChild(eltr);
            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = Integer.parseInt(comms.request.getParameter("area"));
        AreasDAO areaf = new AreasDAO();
        AreasDTO regArea = areaf.getArea(area);
        areaf.close();
        CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
        if (!rsCalCiclos.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
            rsCalCiclos.close();
            if (regCiclo != null) {
                this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
            }

            this.pagHTML.setTextNombreAreaA(regArea != null ? regArea.getDescripcion() : "");
            CalEstadisticasDAO rs = new CalEstadisticasDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection arr = rs.cargarPlanEspecial(ciclo, regArea.getSecuencia());
                rs.close();
                HTMLTableElement hte = this.pagHTML.getElementPlan();
                boolean fondo = true;
                int cuantas = 0;
                String proceso = "";
                String subProceso = "";
                String objetivo = "";
                Collection bloque = new ArrayList();

                for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                    CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
                    int rta = this.evaluarCorte(cuantas, proceso, reg.getNombreProceso(), subProceso, reg.getNombreSubProceso(), objetivo, reg.getNombreObjetivo());
                    HTMLElement eltr;
                    if (rta == -1) {
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.appendChild(this.newtd("" + reg.getNombreProceso(), "CA", 2));
                        hte.appendChild(eltr);
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.appendChild(this.newtd("" + reg.getNombreSubProceso(), "CA2", 2));
                        hte.appendChild(eltr);
                    }

                    switch(rta) {
                    case 2:
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                        eltr.appendChild(this.newtd("" + objetivo, "", 0));
                        eltr.appendChild(this.lasMetas(ciclo, area, bloque));
                        hte.appendChild(eltr);
                        bloque.clear();
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.appendChild(this.newtd("" + reg.getNombreSubProceso(), "CA2", 2));
                        hte.appendChild(eltr);
                    case 1:
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                        eltr.appendChild(this.newtd("" + objetivo, "", 0));
                        eltr.appendChild(this.lasMetas(ciclo, area, bloque));
                        hte.appendChild(eltr);
                        bloque.clear();
                        break;
                    case 3:
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                        eltr.appendChild(this.newtd("" + objetivo, "", 0));
                        eltr.appendChild(this.lasMetas(ciclo, area, bloque));
                        hte.appendChild(eltr);
                        bloque.clear();
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.appendChild(this.newtd("" + reg.getNombreProceso(), "CA", 2));
                        hte.appendChild(eltr);
                        eltr = (HTMLElement)this.pagHTML.createElement("tr");
                        eltr.appendChild(this.newtd("" + reg.getNombreSubProceso(), "CA2", 2));
                        hte.appendChild(eltr);
                    }

                    bloque.add(reg);
                    proceso = reg.getNombreProceso();
                    subProceso = reg.getNombreSubProceso();
                    objetivo = reg.getNombreObjetivo();
                }

                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtd("" + objetivo, "", 0));
                eltr.appendChild(this.lasMetas(ciclo, area, bloque));
                hte.appendChild(eltr);
                bloque.clear();
                eltr = this.pagHTML.getElementTrSeleccionar();
                eltr.getParentNode().removeChild(eltr);
            }
        }
    }

    private void verDetalle(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        int area = Integer.parseInt(comms.request.getParameter("area"));
        int codigoObjetivo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
        int codigoMeta = Integer.parseInt(comms.request.getParameter("codigoMeta"));
        int periodo = Integer.parseInt(comms.request.getParameter("periodo"));
        double valor = Double.parseDouble(comms.request.getParameter("valor"));
        double meta = 0.0D;

        try {
            meta = Double.parseDouble(comms.request.getParameter("meta"));
        } catch (Exception var20) {
            meta = -9999.0D;
        }

        AreasDAO areaf = new AreasDAO();
        AreasDTO regArea = areaf.getArea(area);
        areaf.close();
        CalEstadisticasDAO rs = new CalEstadisticasDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            Collection arr = rs.cargarValorPlanEspecial(ciclo, periodo, regArea.getSecuencia(), codigoObjetivo, codigoMeta, valor, meta);
            rs.close();
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            Iterator iterator = arr.iterator();

            int cuantos;
            for(cuantos = 0; iterator.hasNext(); ++cuantos) {
                CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtdhref("" + reg.getNombreArea(), "CalVerResultados.po?_operacion=VER&ciclo=" + ciclo + "&area=" + reg.getCodigoArea()));
                area = reg.getCodigoArea();
                hte.appendChild(eltr);
            }

            this.pagHTML.setTextNroRegistros("" + arr.size());
            arr.clear();
            if (cuantos == 1) {
                String url = "CalVerResultados.po?_operacion=VER&ciclo=" + ciclo + "&area=" + area;
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(url));
            }
        }
    }

    private HTMLElement lasMetas(int ciclo, int area, Collection arr) {
        HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
        laTabla.setAttributeNode(this.newAttr("class", "fprofb"));
        laTabla.setAttributeNode(this.newAttr("align", "left"));
        laTabla.setAttributeNode(this.newAttr("width", "100%"));
        HTMLElement eltrt = (HTMLElement)this.pagHTML.createElement("tr");
        eltrt.appendChild(this.newtdMeta("", "Meta", 22, "cf2", "center", ""));

        for(int i = 1; i <= 12; ++i) {
            eltrt.appendChild(this.newtdMeta("", Utilidades.nombreMes(i).substring(0, 3), 6, "cf" + (i % 2 == 0 ? 1 : 2), "center", ""));
        }

        laTabla.appendChild(eltrt);
        boolean fondo = true;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            eltr.appendChild(this.newtd("" + reg.getNombreMeta(), 22, ""));
            eltr.appendChild(this.punto(1, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniEne(), reg.getMaxiEne(), reg.getLogroEne(), reg.getMetaEne(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(2, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniFeb(), reg.getMaxiFeb(), reg.getLogroFeb(), reg.getMetaFeb(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(3, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniMar(), reg.getMaxiMar(), reg.getLogroMar(), reg.getMetaMar(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(4, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniAbr(), reg.getMaxiAbr(), reg.getLogroAbr(), reg.getMetaAbr(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(5, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniMay(), reg.getMaxiMay(), reg.getLogroMay(), reg.getMetaMay(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(6, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniJun(), reg.getMaxiJun(), reg.getLogroJun(), reg.getMetaJun(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(7, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniJul(), reg.getMaxiJul(), reg.getLogroJul(), reg.getMetaJul(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(8, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniAgo(), reg.getMaxiAgo(), reg.getLogroAgo(), reg.getMetaAgo(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(9, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniSep(), reg.getMaxiSep(), reg.getLogroSep(), reg.getMetaSep(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(10, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniOct(), reg.getMaxiOct(), reg.getLogroOct(), reg.getMetaOct(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(11, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniNov(), reg.getMaxiNov(), reg.getLogroNov(), reg.getMetaNov(), reg.getNombreUnidadMed()));
            eltr.appendChild(this.punto(12, ciclo, area, reg.getCodigoObjetivo(), reg.getCodigoMeta(), reg.getTipoMedicion(), reg.getMiniDic(), reg.getMaxiDic(), reg.getLogroDic(), reg.getMetaDic(), reg.getNombreUnidadMed()));
            laTabla.appendChild(eltr);
        }

        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(laTabla);
        return td;
    }

    private HTMLElement punto(int periodo, int ciclo, int area, int codigoObjetivo, int codigoMeta, String tipoMedicion, double minimo, double maximo, double promedio, double meta, String unidadMedida) {
        HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
        laTabla.setAttributeNode(this.newAttr("class", "fprofb"));
        laTabla.setAttributeNode(this.newAttr("align", "left"));
        laTabla.setAttributeNode(this.newAttr("width", "100%"));
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
        if (promedio != 0.0D) {
            String url = "CalVerPlanEspecial.po?_operacion=VAL&periodo=" + periodo + "&ciclo=" + ciclo + "&area=" + area + "&codigoObjetivo=" + codigoObjetivo + "&codigoMeta=" + codigoMeta + "&valor=" + Utilidades.round2(minimo);
            eltr.appendChild(this.newtdMeta(url, "" + Utilidades.round2(minimo) + " " + unidadMedida, 0, "verde", "center", "Minimo"));
            url = "CalVerPlanEspecial.po?_operacion=VAL&periodo=" + periodo + "&ciclo=" + ciclo + "&area=" + area + "&codigoObjetivo=" + codigoObjetivo + "&codigoMeta=" + codigoMeta + "&valor=" + Utilidades.round2(maximo);
            eltr.appendChild(this.newtdMeta(url, "" + Utilidades.round2(maximo) + " " + unidadMedida, 0, "verde", "center", "Maximo"));
            url = "";
            if (!this.cumple(tipoMedicion, promedio, meta)) {
                url = "CalVerPlanEspecial.po?_operacion=VAL&periodo=" + periodo + "&ciclo=" + ciclo + "&area=" + area + "&codigoObjetivo=" + codigoObjetivo + "&codigoMeta=" + codigoMeta + "&valor=" + Utilidades.round2(maximo) + "&meta=" + meta;
            }

            eltr2.appendChild(this.newtdMeta(url, "" + Utilidades.round2(promedio) + " " + unidadMedida, 0, this.cumple(tipoMedicion, promedio, meta) ? "verde" : "rojo", "center", "Promedio"));
            eltr2.appendChild(this.newtdMeta("", "" + Utilidades.round2(meta) + " " + unidadMedida, 0, "verde", "center", "Meta"));
        } else {
            eltr.appendChild(this.newtd("", 0, "verde"));
            eltr.appendChild(this.newtd("", 0, "verde"));
            eltr2.appendChild(this.newtd("", 0, "verde"));
            eltr2.appendChild(this.newtd("", 0, "verde"));
        }

        laTabla.appendChild(eltr);
        laTabla.appendChild(eltr2);
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("width", "6%"));
        td.appendChild(laTabla);
        return td;
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtdMeta(String vinculo, String contenido, int ancho, String clase, String align, String title) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        if (ancho > 0) {
            td.setAttributeNode(this.newAttr("width", "" + ancho + "%"));
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

        if (vinculo.length() > 0) {
            Element enlace = this.pagHTML.createElement("a");
            Node hijo = this.pagHTML.createTextNode(contenido);
            enlace.appendChild(hijo);
            Attr donde = this.pagHTML.createAttribute("href");
            donde.setValue(vinculo);
            enlace.setAttributeNode(donde);
            td.appendChild(enlace);
        } else {
            td.appendChild(this.pagHTML.createTextNode(contenido));
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
        td.setAttributeNode(this.newAttr("width", "" + ancho + "%"));
        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

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
        return td;
    }

    private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion) {
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
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
            arr = af.cargarTodos();
        } else {
            arr = af.cargarSecuencia(area1);
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

    private int evaluarCorte(int cuantas, String procesoOld, String proceso, String subProcesoOld, String subproceso, String objetivoOld, String objetivo) {
        if (cuantas == 0) {
            return -1;
        } else if (!procesoOld.equals(proceso)) {
            return 3;
        } else if (!subProcesoOld.equals(subproceso)) {
            return 2;
        } else {
            return !objetivoOld.equals(objetivo) ? 1 : 0;
        }
    }

    private boolean cumple(String tipoMedicion, double logro, double meta) {
        if (tipoMedicion.equals("A") && logro > meta) {
            return true;
        } else if (tipoMedicion.equals("B") && logro >= meta) {
            return true;
        } else if (tipoMedicion.equals("C") && logro == meta) {
            return true;
        } else if (tipoMedicion.equals("D") && logro <= meta) {
            return true;
        } else {
            return tipoMedicion.equals("E") && logro < meta;
        }
    }
}
