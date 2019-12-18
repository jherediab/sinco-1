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
import sinco.business.PrcClienteDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.Utilidades;
import sinco.data.PrcClienteDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PrcCliente implements HttpPresentation {
    private PrcClienteHTML pagHTML;

    public PrcCliente() {
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

            this.pagHTML = (PrcClienteHTML)comms.xmlcFactory.create(PrcClienteHTML.class);
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

        int idCliente;
        try {
            idCliente = Integer.parseInt(comms.request.getParameter("idCliente"));
        } catch (Exception var16) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idCliente"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String tipoIdentificacion;
        if (_operacion.equals("E")) {
            PrcClienteDAO ob = new PrcClienteDAO();
            rta = ob.eliminarRegistro(idCliente);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcCliente&p1=" + rta.getMensaje()));
            } else {
                tipoIdentificacion = "PrcCliente.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(tipoIdentificacion));
            }
        } else {
            String identificacionCliente = comms.request.getParameter("identificacionCliente");
            tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
            String nombreCliente = comms.request.getParameter("nombreCliente");
            String direccionCliente = comms.request.getParameter("direccionCliente");
            String telefono = comms.request.getParameter("telefono");
            String correoElectronico = comms.request.getParameter("correoElectronico");
            String estado = comms.request.getParameter("estado");
            String fechaModificacacion = comms.request.getParameter("fechaModificacacion");
            PrcClienteDAO ob = new PrcClienteDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idCliente, identificacionCliente, tipoIdentificacion, nombreCliente, direccionCliente, telefono, correoElectronico, estado, elUsuario, fechaModificacacion);
                idCliente = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idCliente, identificacionCliente, tipoIdentificacion, nombreCliente, direccionCliente, telefono, correoElectronico, estado, elUsuario, fechaModificacacion);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcCliente&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PrcCliente.po?_operacion=P&idCliente=" + idCliente + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idCliente = 0;

        try {
            idCliente = Integer.parseInt(comms.request.getParameter("idCliente"));
        } catch (Exception var6) {
        }

        PrcClienteDAO ob = new PrcClienteDAO();
        PrcClienteDTO reg = ob.cargarRegistro(idCliente);
        if (reg != null) {
            this.pagHTML.getElementIdCliente().setValue("" + reg.getIdCliente());
            this.pagHTML.getElementIdentificacionCliente().setValue("" + reg.getIdentificacionCliente());
            this.pagHTML.getElementNombreCliente().setValue("" + reg.getNombreCliente());
            this.pagHTML.getElementDireccionCliente().setValue("" + reg.getDireccionCliente());
            this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
            this.pagHTML.getElementCorreoElectronico().setValue("" + reg.getCorreoElectronico());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementFechaModificacacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaModificacacion()));
            HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
            this.comboMultivalores(combo, "TIPO_DOCUMENTO", "" + reg.getTipoIdentificacion(), true);
            combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
            this.pagHTML.getElementIdCliente().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
        this.comboMultivalores(combo, "TIPO_DOCUMENTO", "", true);
        combo = this.pagHTML.getElementEstado();
        this.comboMultivalores(combo, "estado_activo_inactivo", "", true);
        this.pagHTML.getElementIdCliente().setReadOnly(true);
        this.pagHTML.getElementIdCliente().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String identificacionCliente = comms.request.getParameter("identificacionCliente");
        String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
        HTMLSelectElement combo = this.pagHTML.getElementFtipoIdentificacion();
        this.comboMultivalores(combo, "TIPO_DOCUMENTO", "" + tipoIdentificacion, true);
        if (!_operacion.equals("X")) {
            PrcClienteDAO ob = new PrcClienteDAO();
            Collection<PrcClienteDTO> arr = ob.cargarTodos(identificacionCliente, tipoIdentificacion);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PrcClienteDTO reg = (PrcClienteDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getIdentificacionCliente()));
                String url = "PrcCliente.po?_operacion=V&idCliente=" + reg.getIdCliente() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombreTipoIdentificacion(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreCliente()));
                eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getFechaModificacacion())));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idCliente = 0;

        try {
            idCliente = Integer.parseInt(comms.request.getParameter("idCliente"));
        } catch (Exception var5) {
        }

        PrcClienteDAO ob = new PrcClienteDAO();
        PrcClienteDTO reg = ob.cargarRegistro(idCliente);
        if (reg != null) {
            this.pagHTML.setTextIdClienteEd("" + reg.getIdCliente());
            this.pagHTML.setTextIdentificacionClienteEd("" + reg.getIdentificacionCliente());
            this.pagHTML.setTextTipoIdentificacionEd("" + reg.getNombreTipoIdentificacion());
            this.pagHTML.setTextNombreClienteEd("" + reg.getNombreCliente());
            this.pagHTML.setTextDireccionClienteEd("" + reg.getDireccionCliente());
            this.pagHTML.setTextTelefonoEd("" + reg.getTelefono());
            this.pagHTML.setTextCorreoElectronicoEd("" + reg.getCorreoElectronico());
            this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextFechaModificacacionEd("" + Utilidades.darFormatoFecha(reg.getFechaModificacacion()));
            this.pagHTML.getElementIdClienteKey().setValue("" + reg.getIdCliente());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admPrcClienteAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admPrcClienteDel");
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
