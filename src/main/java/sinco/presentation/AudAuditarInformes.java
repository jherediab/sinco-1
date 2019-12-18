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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.AudBloquesDTO;
import sinco.business.AudCriteriosDTO;
import sinco.business.AudInformesPlanAnualDTO;
import sinco.business.AudPlanAnualDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.Utilidades;
import sinco.data.AudBloquesDAO;
import sinco.data.AudCriteriosDAO;
import sinco.data.AudInformesPlanAnualDAO;
import sinco.data.AudPlanAnualDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;

public class AudAuditarInformes implements HttpPresentation {
    private AudAuditarInformesHTML pagHTML;

    public AudAuditarInformes() {
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

            this.pagHTML = (AudAuditarInformesHTML)comms.xmlcFactory.create(AudAuditarInformesHTML.class);
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

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        String elUsuario = "" + comms.session.getUser().getName();
        boolean var4 = false;

        int ciclo;
        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var13) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
        }

        boolean var5 = false;

        int codigoInforme;
        try {
            codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
        } catch (Exception var12) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoInforme"));
        }

        Enumeration enumera = comms.request.getParameterNames();
        ArrayList preguntas = new ArrayList();

        while(enumera.hasMoreElements()) {
            String param = (String)enumera.nextElement();
            if (param.substring(0, 2).equals("R_")) {
                AudBloquesDTO pre = new AudBloquesDTO();
                Pattern p = Pattern.compile("_");
                String[] items = p.split(param);
                pre.setBloque(Integer.parseInt(items[1]));
                pre.setPregunta(Integer.parseInt(items[2]));
                pre.setRespuesta(comms.request.getParameter(param));
                pre.setAnotaciones(comms.request.getParameter("A_" + pre.getBloque() + "_" + pre.getPregunta()));
                pre.setIndConformidad(comms.request.getParameter("C_" + pre.getBloque() + "_" + pre.getPregunta()));
                preguntas.add(pre);
            }
        }

        AudBloquesDAO ob = new AudBloquesDAO();
        new RespuestaBD();
        RespuestaBD rta = ob.salvarRespuestasInformes(ciclo, codigoInforme, preguntas, elUsuario);
        if (!rta.isRta()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudBloques&p1=" + rta.getMensaje()));
        } else {
            String sPagina = "AudAuditarInformes.po?_operacion=V&ciclo=" + ciclo + "&codigoInforme=" + codigoInforme + "";
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
        }
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int ciclo;
        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var13) {
            ciclo = Utilidades.getAnnoActual();
        }

        int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
        HTMLSelectElement combo = this.pagHTML.getElementCiclo();
        this.comboCiclos(combo, ciclo, false);
        if (!_operacion.equals("X")) {
            AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
            Collection<AudInformesPlanAnualDTO> arr = ob.cargarTodos(ciclo, "");
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                AudInformesPlanAnualDTO reg = (AudInformesPlanAnualDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCiclo()));
                String url = "AudAuditarInformes.po?_operacion=V&ciclo=" + reg.getCiclo() + "&codigoInforme=" + reg.getCodigoInforme() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombreCodigoInforme(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreCoordinadorAuditoría()));
                eltr.appendChild(this.newtd("" + reg.getEquipoAuditor()));
                eltr.appendChild(this.newtd("" + reg.getNombreTipoInforme()));
                hte.appendChild(eltr);
            }

            arr.clear();
        }
    }

    private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        this.activarVista("verPlan");
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var12) {
        }

        int codigoInforme = 0;

        try {
            codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
        } catch (Exception var11) {
        }

        this.verRegistro(ciclo, codigoInforme);
        AudBloquesDAO ob = new AudBloquesDAO();
        Collection<AudBloquesDTO> bloques = ob.cargarTodos(ciclo, "", codigoInforme, "I");
        HTMLElement hte = this.pagHTML.getElementDivCreacionRegistro();
        Iterator ibloque = bloques.iterator();

        while(ibloque.hasNext()) {
            AudBloquesDTO bloque = (AudBloquesDTO)ibloque.next();
            HTMLElement tabla = this.agregarBloque(ciclo, codigoInforme, bloque.getBloque());
            hte.appendChild(tabla);
            HTMLElement espacio = (HTMLElement)this.pagHTML.createElement("br");
            hte.appendChild(espacio);
        }

        bloques.clear();
        this.pagHTML.getElementCicloKey().setValue("" + ciclo);
        this.pagHTML.getElementCodigoInformekey().setValue("" + codigoInforme);
    }

    private HTMLElement agregarBloque(int ciclo, int codigoInforme, int bloque) {
        HTMLElement hte = (HTMLElement)this.pagHTML.createElement("table");
        hte.setAttributeNode(this.newAttr("class", "tabf"));
        AudCriteriosDAO rs = new AudCriteriosDAO();
        Collection<AudCriteriosDTO> criterios = rs.cargarAsignados(ciclo, "", bloque);
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("CRITERIOS", 2, "ca"));
        hte.appendChild(eltr);
        Iterator icriterios = criterios.iterator();

        while(icriterios.hasNext()) {
            AudCriteriosDTO criterio = (AudCriteriosDTO)icriterios.next();
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + criterio.getCriterio() + " " + criterio.getDescripcion(), 2, ""));
            hte.appendChild(eltr);
        }

        criterios.clear();
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("PREGUNTAS", 2, "ca2"));
        hte.appendChild(eltr);
        AudBloquesDAO ob = new AudBloquesDAO();
        Collection<AudBloquesDTO> arr = ob.cargarPreguntas(ciclo, "", codigoInforme, bloque, "I");
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            AudBloquesDTO reg = (AudBloquesDTO)iterator.next();
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getPregunta() + " - " + reg.getDescripcionPregunta(), 2, "ca3"));
            hte.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            HTMLInputElement inp = (HTMLInputElement)this.pagHTML.createElement("input");
            inp.setMaxLength(512);
            inp.setSize("120");
            inp.setClassName("INP");
            inp.setName("R_" + bloque + "_" + reg.getPregunta());
            inp.setAttributeNode(this.newAttr("onkeypress", "validarTecla(event, 'AL');"));
            inp.setValue(reg.getRespuesta());
            HTMLElement tdRespuesta = (HTMLElement)this.pagHTML.createElement("td");
            tdRespuesta.setAttributeNode(this.newAttr("class", "ctd"));
            tdRespuesta.appendChild(inp);
            eltr.appendChild(this.newtd("EVIDENCIA OBJETIVA"));
            eltr.appendChild(tdRespuesta);
            hte.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            inp = (HTMLInputElement)this.pagHTML.createElement("input");
            inp.setMaxLength(2048);
            inp.setSize("120");
            inp.setClassName("INP");
            inp.setName("A_" + bloque + "_" + reg.getPregunta());
            inp.setAttributeNode(this.newAttr("onkeypress", "validarTecla(event, 'AL');"));
            inp.setValue(reg.getAnotaciones());
            tdRespuesta = (HTMLElement)this.pagHTML.createElement("td");
            tdRespuesta.setAttributeNode(this.newAttr("class", "ctd"));
            tdRespuesta.appendChild(inp);
            eltr.appendChild(this.newtd("ANOTACIONES"));
            eltr.appendChild(tdRespuesta);
            hte.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            HTMLSelectElement combo = (HTMLSelectElement)this.pagHTML.createElement("select");
            combo.setName("C_" + bloque + "_" + reg.getPregunta());
            this.comboMultivalores(combo, "AUD_RESPUESTA_CONFORMIDAD", "" + reg.getIndConformidad(), true);
            tdRespuesta = (HTMLElement)this.pagHTML.createElement("td");
            tdRespuesta.setAttributeNode(this.newAttr("class", "ctd"));
            tdRespuesta.appendChild(combo);
            eltr.appendChild(this.newtd("CONFORMIDAD"));
            eltr.appendChild(tdRespuesta);
            hte.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("", 2, ""));
            hte.appendChild(eltr);
        }

        arr.clear();

        try {
            HTMLElement elem = this.pagHTML.getElementBtnConsultar();
            elem.getParentNode().removeChild(elem);
        } catch (Exception var15) {
        }

        return hte;
    }

    private void verRegistro(int ciclo, int codigoInforme) {
        AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
        AudInformesPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoInforme);
        if (reg != null) {
            this.pagHTML.setTextCicloEd("" + reg.getCiclo());
            this.pagHTML.setTextCoordinadorAuditoríaEd("" + reg.getNombreCoordinadorAuditoría());
            this.pagHTML.setTextEquipoAuditorEd("" + reg.getEquipoAuditor());
            this.pagHTML.setTextCodigoInformeEd("" + reg.getNombreCodigoInforme());
            this.pagHTML.setTextEquipoAuditorEd("" + reg.getEquipoAuditor());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextAreaResponsableEd("" + reg.getNombreAreaResponsable());
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

    private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO ob = new SisMultiValoresDAO();
        Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
        ob.close();
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
}
