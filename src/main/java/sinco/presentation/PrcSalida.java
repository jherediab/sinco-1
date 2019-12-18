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
import sinco.business.PrcSalidaDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.Utilidades;
import sinco.data.PrcSalidaDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PrcSalida implements HttpPresentation {
    private PrcSalidaHTML pagHTML;

    public PrcSalida() {
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

            this.pagHTML = (PrcSalidaHTML)comms.xmlcFactory.create(PrcSalidaHTML.class);
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

        int idSalida;
        try {
            idSalida = Integer.parseInt(comms.request.getParameter("idSalida"));
        } catch (Exception var13) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idSalida"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String descSalida;
        if (_operacion.equals("E")) {
            PrcSalidaDAO ob = new PrcSalidaDAO();
            rta = ob.eliminarRegistro(idSalida);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcSalida&p1=" + rta.getMensaje()));
            } else {
                descSalida = "PrcSalida.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(descSalida));
            }
        } else {
            String codigoSalida = comms.request.getParameter("codigoSalida");
            descSalida = comms.request.getParameter("descSalida");
            String estado = comms.request.getParameter("estado");
            String usuarioNotificacion = comms.request.getParameter("usuarioNotificacion");
            String fechaNotificacion = comms.request.getParameter("fechaNotificacion");
            PrcSalidaDAO ob = new PrcSalidaDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idSalida, codigoSalida, descSalida, estado, elUsuario, usuarioNotificacion, fechaNotificacion);
                idSalida = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idSalida, codigoSalida, descSalida, estado, usuarioNotificacion, fechaNotificacion);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcSalida&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PrcSalida.po?_operacion=P&idSalida=" + idSalida + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idSalida = 0;

        try {
            idSalida = Integer.parseInt(comms.request.getParameter("idSalida"));
        } catch (Exception var6) {
        }

        PrcSalidaDAO ob = new PrcSalidaDAO();
        PrcSalidaDTO reg = ob.cargarRegistro(idSalida);
        if (reg != null) {
            this.pagHTML.getElementIdSalida().setValue("" + reg.getIdSalida());
            this.pagHTML.getElementCodigoSalida().setValue("" + reg.getCodigoSalida());
            this.pagHTML.getElementDescSalida().setValue("" + reg.getDescSalida());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioNotificacion().setValue("" + reg.getUsuarioNotificacion());
            this.pagHTML.getElementFechaNotificacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaNotificacion()));
            HTMLSelectElement combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
            this.pagHTML.getElementIdSalida().setReadOnly(true);
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
        this.pagHTML.getElementIdSalida().setReadOnly(true);
        this.pagHTML.getElementIdSalida().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String codigoSalida = comms.request.getParameter("codigoSalida");
        String descSalida = comms.request.getParameter("descSalida");
        if (!_operacion.equals("X")) {
            PrcSalidaDAO ob = new PrcSalidaDAO();
            Collection<PrcSalidaDTO> arr = ob.cargarTodos(codigoSalida, descSalida);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PrcSalidaDTO reg = (PrcSalidaDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getIdSalida()));
                String url = "PrcSalida.po?_operacion=V&idSalida=" + reg.getIdSalida() + "";
                eltr.appendChild(this.newtdhref("" + reg.getCodigoSalida(), url));
                eltr.appendChild(this.newtd("" + reg.getDescSalida()));
                eltr.appendChild(this.newtd("" + reg.getNombreEstado()));
                eltr.appendChild(this.newtd("" + reg.getUsuarioNotificacion()));
                eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getFechaNotificacion())));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idSalida = 0;

        try {
            idSalida = Integer.parseInt(comms.request.getParameter("idSalida"));
        } catch (Exception var5) {
        }

        PrcSalidaDAO ob = new PrcSalidaDAO();
        PrcSalidaDTO reg = ob.cargarRegistro(idSalida);
        if (reg != null) {
            this.pagHTML.setTextIdSalidaEd("" + reg.getIdSalida());
            this.pagHTML.setTextCodigoSalidaEd("" + reg.getCodigoSalida());
            this.pagHTML.setTextDescSalidaEd("" + reg.getDescSalida());
            this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioNotificacionEd("" + reg.getUsuarioNotificacion());
            this.pagHTML.setTextFechaNotificacionEd("" + Utilidades.darFormatoFecha(reg.getFechaNotificacion()));
            this.pagHTML.getElementIdSalidaKey().setValue("" + reg.getIdSalida());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admPrcSalidaAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admPrcSalidaDel");
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
