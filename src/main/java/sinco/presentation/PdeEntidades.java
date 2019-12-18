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
import sinco.business.PdeEntidadesDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.data.PdeEntidadesDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdeEntidades implements HttpPresentation {
    private PdeEntidadesHTML pagHTML;

    public PdeEntidades() {
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

            this.pagHTML = (PdeEntidadesHTML)comms.xmlcFactory.create(PdeEntidadesHTML.class);
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
        long nitEntidad = 0L;

        try {
            nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
        } catch (Exception var15) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=nitEntidad"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String direccion;
        if (_operacion.equals("E")) {
            PdeEntidadesDAO ob = new PdeEntidadesDAO();
            rta = ob.eliminarRegistro(nitEntidad);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeEntidades&p1=" + rta.getMensaje()));
            } else {
                direccion = "PdeEntidades.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(direccion));
            }
        } else {
            String nombre = comms.request.getParameter("nombre");
            direccion = comms.request.getParameter("direccion");
            String departamento = comms.request.getParameter("departamento");
            String municipio = comms.request.getParameter("municipio");
            int telefono = 0;

            try {
                telefono = Integer.parseInt(comms.request.getParameter("telefono"));
            } catch (Exception var14) {
            }

            PdeEntidadesDAO ob = new PdeEntidadesDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(nitEntidad, nombre, direccion, departamento, municipio, telefono, elUsuario);
            } else {
                rta = ob.modificarRegistro(nitEntidad, nombre, direccion, departamento, municipio, telefono, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeEntidades&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PdeEntidades.po?_operacion=P&nitEntidad=" + nitEntidad + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        long nitEntidad = 0L;

        try {
            nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
        } catch (Exception var7) {
        }

        PdeEntidadesDAO ob = new PdeEntidadesDAO();
        PdeEntidadesDTO reg = ob.cargarRegistro(nitEntidad);
        if (reg != null) {
            this.pagHTML.getElementNitEntidad().setValue("" + reg.getNitEntidad());
            this.pagHTML.getElementNombre().setValue("" + reg.getNombre());
            this.pagHTML.getElementDireccion().setValue("" + reg.getDireccion());
            this.pagHTML.getElementMunicipio().setValue("" + reg.getMunicipio());
            this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementDepartamento();
            this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
            this.pagHTML.getElementNitEntidad().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementDepartamento();
        this.llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String nombre = comms.request.getParameter("nombre");
        nombre = "";
        PdeEntidadesDAO ob = new PdeEntidadesDAO();
        Collection<PdeEntidadesDTO> arr = ob.cargarTodos(nombre);
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
        int cuantas = 0;

        for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
            PdeEntidadesDTO reg = (PdeEntidadesDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getNitEntidad()));
            String url = "PdePlanDesarrollo.po?_operacion=A&nitEntidad=" + reg.getNitEntidad() + "";
            eltr.appendChild(this.newtdhref("" + reg.getNombre(), url));
            eltr.appendChild(this.newtd("" + reg.getNombreDepartamento()));
            eltr.appendChild(this.newtd("" + reg.getNombreMunicipio()));
            hte.appendChild(eltr);
        }

        arr.clear();
        this.pagHTML.setTextNroRegistros("" + cuantas);
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        long nitEntidad = 0L;

        try {
            nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
        } catch (Exception var6) {
        }

        PdeEntidadesDAO ob = new PdeEntidadesDAO();
        PdeEntidadesDTO reg = ob.cargarRegistro(nitEntidad);
        if (reg != null) {
            this.pagHTML.setTextNitEntidadEd("" + reg.getNitEntidad());
            this.pagHTML.setTextNombreEd("" + reg.getNombre());
            this.pagHTML.setTextDireccionEd("" + reg.getDireccion());
            this.pagHTML.setTextDepartamentoEd("" + reg.getNombreDepartamento());
            this.pagHTML.setTextMunicipioEd("" + reg.getMunicipio());
            this.pagHTML.setTextTelefonoEd("" + reg.getTelefono());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementNitEntidadKey().setValue("" + reg.getNitEntidad());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeEntidadesAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeEntidadesDel");
        HTMLInputElement elem;
        if (!oPermisoAct) {
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

    private HTMLElement newimg(String contenido, long variable) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLElement img = (HTMLElement)this.pagHTML.createElement("img");
        img.setAttribute("src", contenido);
        img.setAttribute("height", "30");
        img.setAttribute("width", "150");
        img.setAttribute("onclick", "prueba(" + variable + ")");
        HTMLElement img2 = (HTMLElement)this.pagHTML.createElement("img");
        img2.setAttribute("src", contenido);
        img2.setAttribute("height", "30");
        img2.setAttribute("width", "150");
        img2.setAttribute("onclick", "prueba(" + variable + ")");
        td.appendChild(img);
        td.appendChild(img2);
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

    private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        TGeneralDAO rsTGen = new TGeneralDAO();
        Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
        rsTGen.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigoS());
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }
}
