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
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import sinco.business.AudBloquesDTO;
import sinco.business.AudCriteriosDTO;
import sinco.business.AudPlanAnualDTO;
import sinco.business.AudProcesosPlanAnualDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.Utilidades;
import sinco.data.AudBloquesDAO;
import sinco.data.AudCriteriosDAO;
import sinco.data.AudPlanAnualDAO;
import sinco.data.AudProcesosPlanAnualDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;

public class AudResultados implements HttpPresentation {
    private AudResultadosHTML pagHTML;

    public AudResultados() {
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

            this.pagHTML = (AudResultadosHTML)comms.xmlcFactory.create(AudResultadosHTML.class);
            this.permisos(comms);
            if (!_operacion.equals("L") && !_operacion.equals("X")) {
                if (_operacion.equals("V")) {
                    this.verPlan(comms);
                }
            } else {
                this.listar(comms, _operacion);
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int ciclo;
        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var6) {
            ciclo = Utilidades.getAnnoActual();
        }

        String codigoProceso = comms.request.getParameter("codigoProceso");
        if (codigoProceso == null) {
            codigoProceso = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementCiclo();
        this.comboCiclos(combo, ciclo, false);
        if (ciclo > 0) {
            combo = this.pagHTML.getElementCodigoProceso();
            this.comboProcesos(combo, ciclo, codigoProceso, true);
        }

    }

    private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        this.activarVista("verPlan");
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var11) {
        }

        String codigoProceso = comms.request.getParameter("codigoProceso");
        if (codigoProceso == null) {
            codigoProceso = "";
        }

        this.verRegistro(ciclo, codigoProceso);
        AudBloquesDAO ob = new AudBloquesDAO();
        Collection<AudBloquesDTO> bloques = ob.cargarTodos(ciclo, codigoProceso, 0, "P");
        HTMLElement hte = this.pagHTML.getElementDivCreacionRegistro();
        Iterator ibloque = bloques.iterator();

        while(ibloque.hasNext()) {
            AudBloquesDTO bloque = (AudBloquesDTO)ibloque.next();
            HTMLElement tabla = this.agregarBloque(ciclo, codigoProceso, bloque.getBloque());
            hte.appendChild(tabla);
            HTMLElement espacio = (HTMLElement)this.pagHTML.createElement("br");
            hte.appendChild(espacio);
        }

        bloques.clear();
        this.pagHTML.getElementCicloKey().setValue("" + ciclo);
        this.pagHTML.getElementCodigoProcesokey().setValue("" + codigoProceso);
    }

    private HTMLElement agregarBloque(int ciclo, String codigoProceso, int bloque) {
        HTMLElement hte = (HTMLElement)this.pagHTML.createElement("table");
        hte.setAttributeNode(this.newAttr("class", "tabf"));
        AudCriteriosDAO rs = new AudCriteriosDAO();
        Collection<AudCriteriosDTO> criterios = rs.cargarAsignados(ciclo, codigoProceso, bloque);
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("CRITERIOS", 4, "ca"));
        hte.appendChild(eltr);
        Iterator icriterios = criterios.iterator();

        while(icriterios.hasNext()) {
            AudCriteriosDTO criterio = (AudCriteriosDTO)icriterios.next();
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + criterio.getCriterio() + " " + criterio.getDescripcion(), 4, ""));
            hte.appendChild(eltr);
        }

        criterios.clear();
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("PREGUNTA", 0, "ca2"));
        eltr.appendChild(this.newtd("EVIDENCIA OBJETIVA", 0, "ca2"));
        eltr.appendChild(this.newtd("ANOTACIONES", 0, "ca2"));
        eltr.appendChild(this.newtd("CONFORME", 0, "ca2"));
        hte.appendChild(eltr);
        AudBloquesDAO ob = new AudBloquesDAO();
        Collection<AudBloquesDTO> arr = ob.cargarPreguntas(ciclo, codigoProceso, 0, bloque, "P");
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            AudBloquesDTO reg = (AudBloquesDTO)iterator.next();
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getPregunta() + " - " + reg.getDescripcionPregunta()));
            eltr.appendChild(this.newtd(reg.getRespuesta()));
            eltr.appendChild(this.newtd(reg.getAnotaciones()));
            String conformidad = this.comboMultivalores("AUD_RESPUESTA_CONFORMIDAD", "" + reg.getIndConformidad());
            eltr.appendChild(this.newtd(conformidad));
            hte.appendChild(eltr);
        }

        arr.clear();

        try {
            HTMLElement elem = this.pagHTML.getElementBtnConsultar();
            elem.getParentNode().removeChild(elem);
        } catch (Exception var14) {
        }

        return hte;
    }

    private void verRegistro(int ciclo, String codigoProceso) {
        AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
        AudProcesosPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoProceso);
        if (reg != null) {
            this.pagHTML.setTextFecha("" + Utilidades.darFormatoFecha(Utilidades.fechaActual()));
            this.pagHTML.setTextCicloEd("" + reg.getCiclo());
            this.pagHTML.setTextCodigoProcesoEd("" + reg.getNombreCodigoProceso());
            this.pagHTML.setTextTipoAuditoriaEd("" + reg.getNombreTipoAuditoria());
            this.pagHTML.setTextAreaResponsableEd("" + reg.getNombreAreaResponsable());
            this.pagHTML.setTextEquipoAuditorEd("" + reg.getEquipoAuditor());
        }

    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
    }

    private void activarVista(String vista) {
        HTMLDivElement sel;
        if (!vista.equals("consulta")) {
            sel = this.pagHTML.getElementDivConsulta();
            sel.getParentNode().removeChild(sel);
        }

        if (!vista.equals("verPlan")) {
            sel = this.pagHTML.getElementDivEdicion();
            sel.getParentNode().removeChild(sel);
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

    private HTMLElement newtd(String contenido, int colspan, String clase) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        } else {
            td.setAttributeNode(this.newAttr("class", "ctd"));
        }

        return td;
    }

    private void comboCiclos(HTMLSelectElement combo, int defecto, boolean dejarBlanco) {
        AudPlanAnualDAO ob = new AudPlanAnualDAO();
        Collection<AudPlanAnualDTO> arr = ob.cargarTodos(0);
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            AudPlanAnualDTO reg = (AudPlanAnualDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCiclo());
            op.appendChild(this.pagHTML.createTextNode("" + reg.getCiclo()));
            if (defecto == reg.getCiclo()) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

        arr.clear();
    }

    private void comboProcesos(HTMLSelectElement combo, int ciclo, String defecto, boolean dejarBlanco) {
        AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
        Collection<AudProcesosPlanAnualDTO> arr = ob.cargarPlan(ciclo, "");
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            AudProcesosPlanAnualDTO reg = (AudProcesosPlanAnualDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigoProceso());
            op.appendChild(this.pagHTML.createTextNode("" + reg.getNombreCodigoProceso()));
            if (defecto == reg.getCodigoProceso()) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

        arr.clear();
    }

    private String comboMultivalores(String tabla, String defecto) {
        SisMultiValoresDAO ob = new SisMultiValoresDAO();
        SisMultiValoresDTO arr = ob.cargarRegistro(tabla, defecto);
        ob.close();
        return arr != null ? arr.getDescripcion() : "";
    }
}
