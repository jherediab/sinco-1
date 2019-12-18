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
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.PdeNivelPlanDTO;
import sinco.business.PdeObjetivosEspecificosDTO;
import sinco.business.PdePlanDesarrolloDTO;
import sinco.business.PdeUnidadNivelDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisEntidadDTO;
import sinco.data.PdeNivelPlanDAO;
import sinco.data.PdeObjetivosEspecificosDAO;
import sinco.data.PdePlanDesarrolloDAO;
import sinco.data.PdeUnidadNivelDAO;
import sinco.data.SisEntidadDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdeObjetivosEspecificos implements HttpPresentation {
    private PdeObjetivosEspecificosHTML pagHTML;

    public PdeObjetivosEspecificos() {
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

            this.pagHTML = (PdeObjetivosEspecificosHTML)comms.xmlcFactory.create(PdeObjetivosEspecificosHTML.class);
            this.permisos(comms);
            boolean var4 = false;

            int idUnidadNivel;
            try {
                idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
            } catch (Exception var15) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idUnidadNivel"));
            }

            PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
            PdeUnidadNivelDTO reg = ob.cargarRegistro(idUnidadNivel, 0, 0);
            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(reg.getIdNivel(), 0, 0);
            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
            PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
            SisEntidadDAO obj3 = new SisEntidadDAO();
            SisEntidadDTO reg3 = obj3.cargarRegistro(reg2.getNitEntidad());
            if (reg2.getPlanCargado().equals("S")) {
                try {
                    HTMLElement sel = this.pagHTML.getElementBtnCrear();
                    sel.getParentNode().removeChild(sel);
                } catch (Exception var14) {
                }
            }

            this.pagHTML.getElementNombrePlan().setTextContent(" " + reg2.getNombrePlanDesarrollo());
            this.pagHTML.getElementNombreEntidad().setTextContent(" " + reg3.getNombre());
            this.pagHTML.getElementNombreNivel().setTextContent(regN.getNombreNivel() + ":   " + reg.getCodigoUnidad() + " - " + reg.getNombreUnidad());
            this.pagHTML.getElementObjetivoGeneralNivel().setTextContent("Objetivo General: " + reg.getObjetivoGeneral());
            this.pagHTML.getElementIdUnidadNivelHidden().setValue("" + idUnidadNivel);
            this.pagHTML.getElementIdNivelHidden().setValue("" + reg.getIdNivel());
            this.pagHTML.getElementIdUnidadSuperiorHidden().setValue("" + reg.getIdUnidadSuperior());
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

        int idObjetivoEspecifico;
        try {
            idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
        } catch (Exception var12) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idObjetivoEspecifico"));
        }

        boolean var5 = false;

        int idUnidadNivel;
        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var11) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idUnidadNivel"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String descripcionObjetivoEspecifico;
        if (_operacion.equals("E")) {
            PdeObjetivosEspecificosDAO ob = new PdeObjetivosEspecificosDAO();
            rta = ob.eliminarRegistro(idObjetivoEspecifico);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeObjetivosEspecificos&p1=" + rta.getMensaje()));
            } else {
                descripcionObjetivoEspecifico = "PdeObjetivosEspecificos.po?_operacion=L&idObjetivoEspecifico=" + idObjetivoEspecifico + "&idUnidadNivel=" + idUnidadNivel + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(descripcionObjetivoEspecifico));
            }
        } else {
            String codigoObjetivoEspecifico = comms.request.getParameter("codigoObjetivoEspecifico");
            descripcionObjetivoEspecifico = comms.request.getParameter("descripcionObjetivoEspecifico");
            PdeObjetivosEspecificosDAO ob = new PdeObjetivosEspecificosDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idObjetivoEspecifico, codigoObjetivoEspecifico, idUnidadNivel, descripcionObjetivoEspecifico, elUsuario);
                idObjetivoEspecifico = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idObjetivoEspecifico, codigoObjetivoEspecifico, idUnidadNivel, descripcionObjetivoEspecifico, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeObjetivosEspecificos&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PdeObjetivosEspecificos.po?_operacion=P&idObjetivoEspecifico=" + idObjetivoEspecifico + "&idUnidadNivel=" + idUnidadNivel + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idObjetivoEspecifico = 0;

        try {
            idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
        } catch (Exception var8) {
        }

        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var7) {
        }

        PdeObjetivosEspecificosDAO ob = new PdeObjetivosEspecificosDAO();
        PdeObjetivosEspecificosDTO reg = ob.cargarRegistro(idObjetivoEspecifico, idUnidadNivel);
        if (reg != null) {
            this.pagHTML.getElementIdObjetivoEspecifico().setValue("" + reg.getIdObjetivoEspecifico());
            this.pagHTML.getElementCodigoObjetivoEspecifico().setValue("" + reg.getCodigoObjetivoEspecifico());
            this.pagHTML.getElementDescripcionObjetivoEspecifico().setValue("" + reg.getDescripcionObjetivoEspecifico());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            HTMLElement sel = this.pagHTML.getElementBtnVolver();
            sel.setAttribute("onClick", "CambiarVolver()");
            this.pagHTML.getElementIdObjetivoEspecifico().setReadOnly(true);
        }

        this.pagHTML.getElement_operacion().setValue("M");
        this.activarVista("nuevo");
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");
        this.pagHTML.getElement_operacion().setValue("C");

        try {
            HTMLElement sel = this.pagHTML.getElementBtnEliminar();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var4) {
        }

        this.activarVista("nuevo");
        this.pagHTML.getElementIdObjetivoEspecifico().setReadOnly(true);
        this.pagHTML.getElementIdObjetivoEspecifico().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        HTMLElement sel = this.pagHTML.getElementDivConsulta();
        sel.getParentNode().removeChild(sel);
        String codigoObjetivoEspecifico = comms.request.getParameter("codigoObjetivoEspecifico");
        if (codigoObjetivoEspecifico == null) {
            codigoObjetivoEspecifico = "";
        }

        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var15) {
        }

        String descripcionObjetivoEspecifico = comms.request.getParameter("descripcionObjetivoEspecifico");
        if (descripcionObjetivoEspecifico == null) {
            descripcionObjetivoEspecifico = "";
        }

        if (!_operacion.equals("X")) {
            PdeObjetivosEspecificosDAO ob = new PdeObjetivosEspecificosDAO();
            Collection<PdeObjetivosEspecificosDTO> arr = ob.cargarTodos("", idUnidadNivel, "");
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PdeObjetivosEspecificosDTO reg = (PdeObjetivosEspecificosDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCodigoObjetivoEspecifico()));
                String url = "PdeObjetivosEspecificos.po?_operacion=V&idObjetivoEspecifico=" + reg.getIdObjetivoEspecifico() + "&idUnidadNivel=" + reg.getIdUnidadNivel() + "";
                eltr.appendChild(this.newtdhref("" + reg.getDescripcionObjetivoEspecifico(), url));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idObjetivoEspecifico = 0;

        try {
            idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
        } catch (Exception var15) {
        }

        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var14) {
        }

        PdeUnidadNivelDAO ob2 = new PdeUnidadNivelDAO();
        PdeUnidadNivelDTO reg2 = ob2.cargarRegistro(idUnidadNivel, 0, 0);
        PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
        PdeNivelPlanDTO regN = obj.cargarRegistro(reg2.getIdNivel(), 0, 0);
        PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
        PdePlanDesarrolloDTO reg3 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
        if (reg3.getPlanCargado().equals("S")) {
            try {
                HTMLElement sel = this.pagHTML.getElementBtnModificar();
                sel.getParentNode().removeChild(sel);
            } catch (Exception var13) {
            }
        }

        PdeObjetivosEspecificosDAO ob = new PdeObjetivosEspecificosDAO();
        PdeObjetivosEspecificosDTO reg = ob.cargarRegistro(idObjetivoEspecifico, idUnidadNivel);
        if (reg != null) {
            this.pagHTML.setTextIdObjetivoEspecificoEd("" + reg.getIdObjetivoEspecifico());
            this.pagHTML.setTextCodigoObjetivoEspecificoEd("" + reg.getCodigoObjetivoEspecifico());
            this.pagHTML.setTextIdUnidadNivelEd("" + reg.getIdUnidadNivel());
            this.pagHTML.setTextDescripcionObjetivoEspecificoEd("" + reg.getDescripcionObjetivoEspecifico());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            HTMLElement sel = this.pagHTML.getElementBtnVolver();
            sel.setAttribute("onClick", "CambiarVolver()");
            this.pagHTML.getElementIdObjetivoEspecificoKey().setValue("" + reg.getIdObjetivoEspecifico());
            this.pagHTML.getElementIdUnidadNivelKey().setValue("" + reg.getIdUnidadNivel());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeObjetivosEspecificosAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeObjetivosEspecificosDel");
        HTMLInputElement elem;
        if (!oPermisoAct) {
            try {
                elem = this.pagHTML.getElementBtnCrear();
                elem.getParentNode().removeChild(elem);
                elem = this.pagHTML.getElementBtnGrabar();
                elem.getParentNode().removeChild(elem);
                elem = this.pagHTML.getElementBtnModificar();
                elem.getParentNode().removeChild(elem);
            } catch (Exception var7) {
            }
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
}
