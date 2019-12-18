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
import sinco.business.PrcProveedorDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.data.PrcProveedorDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PrcProveedor implements HttpPresentation {
    private PrcProveedorHTML pagHTML;

    public PrcProveedor() {
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

            this.pagHTML = (PrcProveedorHTML)comms.xmlcFactory.create(PrcProveedorHTML.class);
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

        int idProveedor;
        try {
            idProveedor = Integer.parseInt(comms.request.getParameter("idProveedor"));
        } catch (Exception var14) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idProveedor"));
        }

        String identificacionProveedor = comms.request.getParameter("identificacionProveedor");
        if (identificacionProveedor == null) {
            identificacionProveedor = "";
        }

        new RespuestaBD();
        RespuestaBD rta;
        String nombreProveedor;
        if (_operacion.equals("E")) {
            PrcProveedorDAO ob = new PrcProveedorDAO();
            rta = ob.eliminarRegistro(idProveedor, identificacionProveedor);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcProveedor&p1=" + rta.getMensaje()));
            } else {
                nombreProveedor = "PrcProveedor.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(nombreProveedor));
            }
        } else {
            String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
            nombreProveedor = comms.request.getParameter("nombreProveedor");
            String direccionProveedor = comms.request.getParameter("direccionProveedor");
            String telefono = comms.request.getParameter("telefono");
            String estado = comms.request.getParameter("estado");
            PrcProveedorDAO ob = new PrcProveedorDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idProveedor, identificacionProveedor, tipoIdentificacion, nombreProveedor, direccionProveedor, telefono, estado, elUsuario);
                idProveedor = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idProveedor, identificacionProveedor, tipoIdentificacion, nombreProveedor, direccionProveedor, telefono, estado, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcProveedor&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PrcProveedor.po?_operacion=P&idProveedor=" + idProveedor + "&identificacionProveedor=" + identificacionProveedor + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idProveedor = 0;

        try {
            idProveedor = Integer.parseInt(comms.request.getParameter("idProveedor"));
        } catch (Exception var7) {
        }

        String identificacionProveedor = comms.request.getParameter("identificacionProveedor");
        PrcProveedorDAO ob = new PrcProveedorDAO();
        PrcProveedorDTO reg = ob.cargarRegistro(idProveedor, identificacionProveedor);
        if (reg != null) {
            this.pagHTML.getElementIdProveedor().setValue("" + reg.getIdProveedor());
            this.pagHTML.getElementIdentificacionProveedor().setValue("" + reg.getIdentificacionProveedor());
            this.pagHTML.getElementNombreProveedor().setValue("" + reg.getNombreProveedor());
            this.pagHTML.getElementDireccionProveedor().setValue("" + reg.getDireccionProveedor());
            this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
            this.comboMultivalores(combo, "TIPO_DOCUMENTO", "" + reg.getTipoIdentificacion(), true);
            combo = this.pagHTML.getElementEstado();
            this.comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
            this.pagHTML.getElementIdProveedor().setReadOnly(true);
            this.pagHTML.getElementIdentificacionProveedor().setReadOnly(true);
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
        this.pagHTML.getElementIdProveedor().setReadOnly(true);
        this.pagHTML.getElementIdProveedor().setValue("0");
        this.pagHTML.getElementIdentificacionProveedor().setReadOnly(true);
        this.pagHTML.getElementIdentificacionProveedor().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String identificacionProveedor = comms.request.getParameter("identificacionProveedor");
        String nombreProveedor = comms.request.getParameter("nombreProveedor");
        String direccionProveedor = comms.request.getParameter("direccionProveedor");
        String telefono = comms.request.getParameter("telefono");
        if (!_operacion.equals("X")) {
            PrcProveedorDAO ob = new PrcProveedorDAO();
            Collection<PrcProveedorDTO> arr = ob.cargarTodos(identificacionProveedor, nombreProveedor, direccionProveedor, telefono);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PrcProveedorDTO reg = (PrcProveedorDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getIdProveedor()));
                String url = "PrcProveedor.po?_operacion=V&idProveedor=" + reg.getIdProveedor() + "&identificacionProveedor=" + reg.getIdentificacionProveedor() + "";
                eltr.appendChild(this.newtdhref("" + reg.getIdentificacionProveedor(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreTipoIdentificacion()));
                eltr.appendChild(this.newtd("" + reg.getNombreProveedor()));
                eltr.appendChild(this.newtd("" + reg.getDireccionProveedor()));
                eltr.appendChild(this.newtd("" + reg.getTelefono()));
                eltr.appendChild(this.newtd("" + reg.getNombreEstado()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idProveedor = 0;

        try {
            idProveedor = Integer.parseInt(comms.request.getParameter("idProveedor"));
        } catch (Exception var6) {
        }

        String identificacionProveedor = comms.request.getParameter("identificacionProveedor");
        PrcProveedorDAO ob = new PrcProveedorDAO();
        PrcProveedorDTO reg = ob.cargarRegistro(idProveedor, identificacionProveedor);
        if (reg != null) {
            this.pagHTML.setTextIdProveedorEd("" + reg.getIdProveedor());
            this.pagHTML.setTextIdentificacionProveedorEd("" + reg.getIdentificacionProveedor());
            this.pagHTML.setTextTipoIdentificacionEd("" + reg.getNombreTipoIdentificacion());
            this.pagHTML.setTextNombreProveedorEd("" + reg.getNombreProveedor());
            this.pagHTML.setTextDireccionProveedorEd("" + reg.getDireccionProveedor());
            this.pagHTML.setTextTelefonoEd("" + reg.getTelefono());
            this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.getElementIdProveedorKey().setValue("" + reg.getIdProveedor());
            this.pagHTML.getElementIdentificacionProveedorKey().setValue("" + reg.getIdentificacionProveedor());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admPrcProveedorAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admPrcProveedorDel");
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
