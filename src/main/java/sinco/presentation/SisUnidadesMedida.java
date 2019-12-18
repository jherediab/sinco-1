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
import sinco.business.SisMultiValoresDTO;
import sinco.business.SisUnidadesMedidaDTO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.SisUnidadesMedidaDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class SisUnidadesMedida implements HttpPresentation {
    private SisUnidadesMedidaHTML pagHTML;

    public SisUnidadesMedida() {
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

            this.pagHTML = (SisUnidadesMedidaHTML)comms.xmlcFactory.create(SisUnidadesMedidaHTML.class);
            this.permisos(comms);
            boolean var4 = false;

            int codigoGrupo;
            try {
                codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
            } catch (Exception var6) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoGrupo"));
            }

            this.pagHTML.getElementCodigoGrupoHidden().setValue("" + codigoGrupo);
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
        String codigoUnidad = comms.request.getParameter("codigoUnidad");
        if (codigoUnidad == null) {
            codigoUnidad = "";
        }

        boolean var5 = false;

        int codigoGrupo;
        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var13) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoGrupo"));
        }

        boolean var6 = false;

        int factor;
        try {
            factor = Integer.parseInt(comms.request.getParameter("factor"));
        } catch (Exception var12) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=factor"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String estadoUnidad;
        if (_operacion.equals("E")) {
            SisUnidadesMedidaDAO ob = new SisUnidadesMedidaDAO();
            rta = ob.eliminarRegistro(codigoUnidad, codigoGrupo);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisUnidadesMedida&p1=" + rta.getMensaje()));
            } else {
                estadoUnidad = "SisUnidadesMedida.po?_operacion=X&codigoUnidad=" + codigoUnidad + "&codigoGrupo=" + codigoGrupo + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(estadoUnidad));
            }
        } else {
            String nombreUnidad = comms.request.getParameter("nombreUnidad");
            estadoUnidad = comms.request.getParameter("estadoUnidad");
            SisUnidadesMedidaDAO ob = new SisUnidadesMedidaDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(codigoUnidad, codigoGrupo, nombreUnidad, estadoUnidad, factor, elUsuario);
            } else {
                rta = ob.modificarRegistro(codigoUnidad, codigoGrupo, nombreUnidad, estadoUnidad, factor, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisUnidadesMedida&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "SisUnidadesMedida.po?_operacion=P&codigoUnidad=" + codigoUnidad + "&codigoGrupo=" + codigoGrupo + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigoUnidad = comms.request.getParameter("codigoUnidad");
        if (codigoUnidad == null) {
            codigoUnidad = "";
        }

        int codigoGrupo = 0;

        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var7) {
        }

        SisUnidadesMedidaDAO ob = new SisUnidadesMedidaDAO();
        SisUnidadesMedidaDTO reg = ob.cargarRegistro(codigoUnidad, codigoGrupo);
        if (reg != null) {
            this.pagHTML.getElementCodigoUnidad().setValue("" + reg.getCodigoUnidad());
            this.pagHTML.getElementNombreUnidad().setValue("" + reg.getNombreUnidad());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementFactor().setValue("" + reg.getFactorConversion());
            HTMLSelectElement combo = this.pagHTML.getElementEstadoUnidad();
            this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstadoUnidad(), true);
            this.pagHTML.getElementCodigoUnidad().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementEstadoUnidad();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        int codigoGrupo = 0;

        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var15) {
        }

        String nombreUnidad = comms.request.getParameter("nombreUnidad");
        if (nombreUnidad == null) {
            nombreUnidad = "";
        }

        String estadoUnidad = comms.request.getParameter("estadoUnidad");
        if (estadoUnidad == null) {
            estadoUnidad = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementFestadoUnidad();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + estadoUnidad, true);
        if (!_operacion.equals("X")) {
            SisUnidadesMedidaDAO ob = new SisUnidadesMedidaDAO();
            Collection<SisUnidadesMedidaDTO> arr = ob.cargarTodos(codigoGrupo, nombreUnidad, estadoUnidad);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                SisUnidadesMedidaDTO reg = (SisUnidadesMedidaDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                String url = "SisUnidadesMedida.po?_operacion=V&codigoUnidad=" + reg.getCodigoUnidad() + "&codigoGrupo=" + reg.getCodigoGrupo() + "";
                eltr.appendChild(this.newtdhref("" + reg.getCodigoUnidad(), url));
                eltr.appendChild(this.newtd("" + reg.getCodigoGrupo()));
                eltr.appendChild(this.newtdhref("" + reg.getNombreUnidad(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreEstadoUnidad()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigoUnidad = comms.request.getParameter("codigoUnidad");
        if (codigoUnidad == null) {
            codigoUnidad = "";
        }

        int codigoGrupo = 0;

        try {
            codigoGrupo = Integer.parseInt(comms.request.getParameter("codigoGrupo"));
        } catch (Exception var6) {
        }

        SisUnidadesMedidaDAO ob = new SisUnidadesMedidaDAO();
        SisUnidadesMedidaDTO reg = ob.cargarRegistro(codigoUnidad, codigoGrupo);
        if (reg != null) {
            this.pagHTML.setTextCodigoUnidadEd("" + reg.getCodigoUnidad());
            this.pagHTML.setTextCodigoGrupoEd("" + reg.getCodigoGrupo());
            this.pagHTML.setTextNombreUnidadEd("" + reg.getNombreUnidad());
            this.pagHTML.setTextEstadoUnidadEd("" + reg.getNombreEstadoUnidad());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextFactorEd("" + reg.getFactorConversion());
            this.pagHTML.getElementCodigoUnidadKey().setValue("" + reg.getCodigoUnidad());
            this.pagHTML.getElementCodigoGrupoKey().setValue("" + reg.getCodigoGrupo());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoSisUnidadesMedidaAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoSisUnidadesMedidaDel");
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
