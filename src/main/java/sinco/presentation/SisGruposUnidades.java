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
import sinco.business.RespuestaBD;
import sinco.business.SisGruposUnidadesDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.data.SisGruposUnidadesDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class SisGruposUnidades implements HttpPresentation {
    private SisGruposUnidadesHTML pagHTML;

    public SisGruposUnidades() {
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

            this.pagHTML = (SisGruposUnidadesHTML)comms.xmlcFactory.create(SisGruposUnidadesHTML.class);
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

        int codigoGrupo;
        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var10) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoGrupo"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String estadoGrupo;
        if (_operacion.equals("E")) {
            SisGruposUnidadesDAO ob = new SisGruposUnidadesDAO();
            rta = ob.eliminarRegistro(codigoGrupo);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisGruposUnidades&p1=" + rta.getMensaje()));
            } else {
                estadoGrupo = "SisGruposUnidades.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(estadoGrupo));
            }
        } else {
            String nombreGrupo = comms.request.getParameter("nombreGrupo");
            estadoGrupo = comms.request.getParameter("estadoGrupo");
            SisGruposUnidadesDAO ob = new SisGruposUnidadesDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(codigoGrupo, nombreGrupo, estadoGrupo, elUsuario);
                codigoGrupo = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(codigoGrupo, nombreGrupo, estadoGrupo, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisGruposUnidades&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "SisGruposUnidades.po?_operacion=P&codigoGrupo=" + codigoGrupo + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigoGrupo = 0;

        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var6) {
        }

        SisGruposUnidadesDAO ob = new SisGruposUnidadesDAO();
        SisGruposUnidadesDTO reg = ob.cargarRegistro(codigoGrupo);
        if (reg != null) {
            this.pagHTML.getElementCodigoGrupo().setValue("" + reg.getCodigoGrupo());
            this.pagHTML.getElementNombreGrupo().setValue("" + reg.getNombreGrupo());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementEstadoGrupo();
            this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstadoGrupo(), true);
            this.pagHTML.getElementCodigoGrupo().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementEstadoGrupo();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
        this.pagHTML.getElementCodigoGrupo().setReadOnly(true);
        this.pagHTML.getElementCodigoGrupo().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        String nombreGrupo = comms.request.getParameter("nombreGrupo");
        String estadoGrupo = comms.request.getParameter("estadoGrupo");
        HTMLSelectElement combo = this.pagHTML.getElementFestadoGrupo();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + estadoGrupo, true);
        if (!_operacion.equals("X")) {
            SisGruposUnidadesDAO ob = new SisGruposUnidadesDAO();
            Collection<SisGruposUnidadesDTO> arr = ob.cargarTodos(nombreGrupo, estadoGrupo);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                SisGruposUnidadesDTO reg = (SisGruposUnidadesDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCodigoGrupo()));
                String url = "SisGruposUnidades.po?_operacion=V&codigoGrupo=" + reg.getCodigoGrupo() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombreGrupo(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreEstadoGrupo()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigoGrupo = 0;

        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var5) {
        }

        SisGruposUnidadesDAO ob = new SisGruposUnidadesDAO();
        SisGruposUnidadesDTO reg = ob.cargarRegistro(codigoGrupo);
        if (reg != null) {
            this.pagHTML.setTextCodigoGrupoEd("" + reg.getCodigoGrupo());
            this.pagHTML.setTextNombreGrupoEd("" + reg.getNombreGrupo());
            this.pagHTML.setTextEstadoGrupoEd("" + reg.getNombreEstadoGrupo());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementCodigoGrupoKey().setValue("" + reg.getCodigoGrupo());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoSisGruposUnidadesAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoSisGruposUnidadesDel");
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
