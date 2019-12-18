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
import sinco.business.PrcPoliticaDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.data.PrcPoliticaDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PrcPolitica implements HttpPresentation {
    private PrcPoliticaHTML pagHTML;

    public PrcPolitica() {
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

            this.pagHTML = (PrcPoliticaHTML)comms.xmlcFactory.create(PrcPoliticaHTML.class);
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

        int idPolitica;
        try {
            idPolitica = Integer.parseInt(comms.request.getParameter("idPolitica"));
        } catch (Exception var10) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPolitica"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String estado;
        if (_operacion.equals("E")) {
            PrcPoliticaDAO ob = new PrcPoliticaDAO();
            rta = ob.eliminarRegistro(idPolitica);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcPolitica&p1=" + rta.getMensaje()));
            } else {
                estado = "PrcPolitica.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(estado));
            }
        } else {
            String descripcionPolitica = comms.request.getParameter("descripcionPolitica");
            estado = comms.request.getParameter("estado");
            PrcPoliticaDAO ob = new PrcPoliticaDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idPolitica, descripcionPolitica, estado, elUsuario);
                idPolitica = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idPolitica, descripcionPolitica, estado, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcPolitica&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PrcPolitica.po?_operacion=P&idPolitica=" + idPolitica + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idPolitica = 0;

        try {
            idPolitica = Integer.parseInt(comms.request.getParameter("idPolitica"));
        } catch (Exception var6) {
        }

        PrcPoliticaDAO ob = new PrcPoliticaDAO();
        PrcPoliticaDTO reg = ob.cargarRegistro(idPolitica);
        if (reg != null) {
            this.pagHTML.getElementIdPolitica().setValue("" + reg.getIdPolitica());
            this.pagHTML.getElementDescripcionPolitica().setValue("" + reg.getDescripcionPolitica());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
            this.pagHTML.getElementIdPolitica().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementEstado();
        this.comboMultivalores(combo, "estado_activo_inactivo", "", true);
        this.pagHTML.getElementIdPolitica().setReadOnly(true);
        this.pagHTML.getElementIdPolitica().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String descripcionPolitica = comms.request.getParameter("descripcionPolitica");
        if (!_operacion.equals("X")) {
            PrcPoliticaDAO ob = new PrcPoliticaDAO();
            Collection<PrcPoliticaDTO> arr = ob.cargarTodos(descripcionPolitica);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PrcPoliticaDTO reg = (PrcPoliticaDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getIdPolitica()));
                String url = "PrcPolitica.po?_operacion=V&idPolitica=" + reg.getIdPolitica() + "";
                eltr.appendChild(this.newtdhref("" + reg.getDescripcionPolitica(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreEstado()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idPolitica = 0;

        try {
            idPolitica = Integer.parseInt(comms.request.getParameter("idPolitica"));
        } catch (Exception var5) {
        }

        PrcPoliticaDAO ob = new PrcPoliticaDAO();
        PrcPoliticaDTO reg = ob.cargarRegistro(idPolitica);
        if (reg != null) {
            this.pagHTML.setTextIdPoliticaEd("" + reg.getIdPolitica());
            this.pagHTML.setTextDescripcionPoliticaEd("" + reg.getDescripcionPolitica());
            this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.getElementIdPoliticaKey().setValue("" + reg.getIdPolitica());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "sincoPrcPoliticaAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "sincoPrcPoliticaDel");
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
