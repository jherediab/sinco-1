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
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.AudInformesDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.data.AudInformesDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class AudInformes implements HttpPresentation {
    private AudInformesHTML pagHTML;

    public AudInformes() {
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

            this.pagHTML = (AudInformesHTML)comms.xmlcFactory.create(AudInformesHTML.class);
            this.permisos(comms);
            if (_operacion.equals("L") || _operacion.equals("X")) {
                this.listar(comms, _operacion);
            }

            if (_operacion.equals("P")) {
                this.editar(comms);
            } else if (_operacion.equals("Nuevo")) {
                this.nuevo(comms);
            }

            if (_operacion.equals("V")) {
                this.verRegistro(comms);
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String _operacion = comms.request.getParameter("_operacion");
        String elUsuario = "" + comms.session.getUser().getName();
        boolean var4 = false;

        int codigo;
        try {
            codigo = Integer.parseInt(comms.request.getParameter("codigo"));
        } catch (Exception var16) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String tipoInforme;
        if (_operacion.equals("E")) {
            AudInformesDAO ob = new AudInformesDAO();
            rta = ob.eliminarRegistro(codigo);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudInformes&p1=" + rta.getMensaje()));
            } else {
                tipoInforme = "AudInformes.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(tipoInforme));
            }
        } else {
            String nombre = comms.request.getParameter("nombre");
            tipoInforme = comms.request.getParameter("tipoInforme");
            String estrategico = comms.request.getParameter("estrategico");
            if (estrategico == null) {
                estrategico = "N";
            }

            String misional = comms.request.getParameter("misional");
            if (misional == null) {
                misional = "N";
            }

            String apoyo = comms.request.getParameter("apoyo");
            if (apoyo == null) {
                apoyo = "N";
            }

            String evaluacion = comms.request.getParameter("evaluacion");
            if (evaluacion == null) {
                evaluacion = "N";
            }

            String responsable = comms.request.getParameter("responsable");
            String estado = comms.request.getParameter("estado");
            AudInformesDAO ob = new AudInformesDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(codigo, nombre, tipoInforme, estrategico, misional, apoyo, evaluacion, responsable, estado, elUsuario);
                codigo = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(codigo, nombre, tipoInforme, estrategico, misional, apoyo, evaluacion, responsable, estado, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudInformes&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "AudInformes.po?_operacion=P&codigo=" + codigo + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigo = 0;

        try {
            codigo = Integer.parseInt(comms.request.getParameter("codigo"));
        } catch (Exception var6) {
        }

        AudInformesDAO ob = new AudInformesDAO();
        AudInformesDTO reg = ob.cargarRegistro(codigo);
        if (reg != null) {
            this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
            this.pagHTML.getElementNombre().setValue("" + reg.getNombre());
            if (reg.getEstrategico().equals("S")) {
                this.pagHTML.getElementEstrategico().setChecked(true);
            }

            if (reg.getMisional().equals("S")) {
                this.pagHTML.getElementMisional().setChecked(true);
            }

            if (reg.getApoyo().equals("S")) {
                this.pagHTML.getElementApoyo().setChecked(true);
            }

            if (reg.getEvaluacion().equals("S")) {
                this.pagHTML.getElementEvaluacion().setChecked(true);
            }

            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementTipoInforme();
            this.comboMultivalores(combo, "CAL_TIPO_INFORME", "" + reg.getTipoInforme(), true);
            combo = this.pagHTML.getElementResponsable();
            this.comboMultivalores(combo, "CAL_LIDER_PROCESO", "" + reg.getResponsable(), true);
            combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
            this.pagHTML.getElementCodigo().setReadOnly(true);
        }

        this.pagHTML.getElement_operacion().setValue("M");
        this.activarVista("nuevo");
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        this.pagHTML.getElement_operacion().setValue("C");

        try {
            HTMLElement sel = this.pagHTML.getElementBtnEliminar();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var3) {
        }

        this.activarVista("nuevo");
        HTMLSelectElement combo = this.pagHTML.getElementTipoInforme();
        this.comboMultivalores(combo, "CAL_TIPO_INFORME", "", true);
        combo = this.pagHTML.getElementResponsable();
        this.comboMultivalores(combo, "CAL_LIDER_PROCESO", "", true);
        combo = this.pagHTML.getElementEstado();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
        this.pagHTML.getElementCodigo().setReadOnly(true);
        this.pagHTML.getElementCodigo().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String nombre = comms.request.getParameter("nombre");
        if (nombre == null) {
            nombre = "";
        }

        String tipoInforme = comms.request.getParameter("tipoInforme");
        if (tipoInforme == null) {
            tipoInforme = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementFtipoInforme();
        this.comboMultivalores(combo, "CAL_TIPO_INFORME", "" + tipoInforme, true);
        if (!_operacion.equals("X")) {
            AudInformesDAO ob = new AudInformesDAO();
            Collection<AudInformesDTO> arr = ob.cargarTodos(nombre, tipoInforme);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                AudInformesDTO reg = (AudInformesDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCodigo()));
                String url = "AudInformes.po?_operacion=V&codigo=" + reg.getCodigo() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombre(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreTipoInforme()));
                eltr.appendChild(this.newtd("" + reg.getEstrategico()));
                eltr.appendChild(this.newtd("" + reg.getMisional()));
                eltr.appendChild(this.newtd("" + reg.getApoyo()));
                eltr.appendChild(this.newtd("" + reg.getEvaluacion()));
                eltr.appendChild(this.newtd("" + reg.getNombreResponsable()));
                eltr.appendChild(this.newtd("" + reg.getNombreEstado()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigo = 0;

        try {
            codigo = Integer.parseInt(comms.request.getParameter("codigo"));
        } catch (Exception var5) {
        }

        AudInformesDAO ob = new AudInformesDAO();
        AudInformesDTO reg = ob.cargarRegistro(codigo);
        if (reg != null) {
            this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
            this.pagHTML.setTextNombreEd("" + reg.getNombre());
            this.pagHTML.setTextTipoInformeEd("" + reg.getNombreTipoInforme());
            this.pagHTML.setTextEstrategicoEd("" + reg.getEstrategico());
            this.pagHTML.setTextMisionalEd("" + reg.getMisional());
            this.pagHTML.setTextApoyoEd("" + reg.getApoyo());
            this.pagHTML.setTextEvaluacionEd("" + reg.getEvaluacion());
            this.pagHTML.setTextResponsableEd("" + reg.getNombreResponsable());
            this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudInformesAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudInformesDel");
        HTMLInputElement elem;
        if (!oPermisoAct) {
            elem = this.pagHTML.getElementBtnCrear();
            elem.getParentNode().removeChild(elem);
            elem = this.pagHTML.getElementBtnGrabar();
            elem.getParentNode().removeChild(elem);
            elem = this.pagHTML.getElementBtnModificar();
            elem.getParentNode().removeChild(elem);
        }

        if (!oPermisoDel) {
            elem = this.pagHTML.getElementBtnEliminar();
            elem.getParentNode().removeChild(elem);
        }

    }

    private void activarVista(String vista) {
        HTMLDivElement sel;
        if (!vista.equals("nuevo")) {
            sel = this.pagHTML.getElementDivCreacionRegistro();
            sel.getParentNode().removeChild(sel);
        }

        if (!vista.equals("editar")) {
            sel = this.pagHTML.getElementDivEdicion();
            sel.getParentNode().removeChild(sel);
        }

        if (!vista.equals("consulta")) {
            sel = this.pagHTML.getElementDivConsulta();
            sel.getParentNode().removeChild(sel);
            sel = this.pagHTML.getElementDivResultados();
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
