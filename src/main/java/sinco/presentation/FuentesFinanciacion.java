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
import sinco.business.FuentesFinanciacionDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.data.FuentesFinanciacionDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class FuentesFinanciacion implements HttpPresentation {
    private FuentesFinanciacionHTML pagHTML;

    public FuentesFinanciacion() {
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

            this.pagHTML = (FuentesFinanciacionHTML)comms.xmlcFactory.create(FuentesFinanciacionHTML.class);
            this.permisos(comms);
            boolean var4 = false;

            int codigoRecurso;
            try {
                codigoRecurso = Integer.parseInt(comms.request.getParameter("codigoRecurso"));
            } catch (Exception var6) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoRecurso"));
            }

            this.pagHTML.getElementCodigoRecursoHidden().setValue("" + codigoRecurso);
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

        int codigoFuente;
        try {
            codigoFuente = Integer.parseInt(comms.request.getParameter("codigoFuente"));
        } catch (Exception var12) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoFuente"));
        }

        boolean var5 = false;

        int codigoRecurso;
        try {
            codigoRecurso = Integer.parseInt(comms.request.getParameter("codigoRecurso"));
        } catch (Exception var11) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoRecurso"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String estadoFuente;
        if (_operacion.equals("E")) {
            FuentesFinanciacionDAO ob = new FuentesFinanciacionDAO();
            rta = ob.eliminarRegistro(codigoFuente, codigoRecurso);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFuentesFinanciacion&p1=" + rta.getMensaje()));
            } else {
                estadoFuente = "FuentesFinanciacion.po?_operacion=X&codigoFuente=" + codigoFuente + "&codigoRecurso=" + codigoRecurso + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(estadoFuente));
            }
        } else {
            String nombreFuente = comms.request.getParameter("nombreFuente");
            estadoFuente = comms.request.getParameter("estadoFuente");
            FuentesFinanciacionDAO ob = new FuentesFinanciacionDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(codigoFuente, codigoRecurso, nombreFuente, estadoFuente, elUsuario);
                codigoFuente = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(codigoFuente, codigoRecurso, nombreFuente, estadoFuente, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFuentesFinanciacion&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "FuentesFinanciacion.po?_operacion=P&codigoFuente=" + codigoFuente + "&codigoRecurso=" + codigoRecurso + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigoFuente = 0;

        try {
            codigoFuente = Integer.parseInt(comms.request.getParameter("codigoFuente"));
        } catch (Exception var8) {
        }

        int codigoRecurso = 0;

        try {
            codigoRecurso = Integer.parseInt(comms.request.getParameter("codigoRecurso"));
        } catch (Exception var7) {
        }

        FuentesFinanciacionDAO ob = new FuentesFinanciacionDAO();
        FuentesFinanciacionDTO reg = ob.cargarRegistro(codigoFuente, codigoRecurso);
        if (reg != null) {
            this.pagHTML.getElementCodigoFuente().setValue("" + reg.getCodigoFuente());
            this.pagHTML.getElementNombreFuente().setValue("" + reg.getNombreFuente());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementEstadoFuente();
            this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstadoFuente(), true);
            this.pagHTML.getElementCodigoFuente().setReadOnly(true);
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
        HTMLSelectElement combo = this.pagHTML.getElementEstadoFuente();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
        this.pagHTML.getElementCodigoFuente().setReadOnly(true);
        this.pagHTML.getElementCodigoFuente().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        int codigoRecurso = 0;

        try {
            codigoRecurso = Integer.parseInt(comms.request.getParameter("codigoRecurso"));
        } catch (Exception var15) {
        }

        String nombreFuente = comms.request.getParameter("nombreFuente");
        if (nombreFuente == null) {
            nombreFuente = "";
        }

        String estadoFuente = comms.request.getParameter("estadoFuente");
        if (estadoFuente == null) {
            estadoFuente = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementFestadoFuente();
        this.comboMultivalores(combo, "ESTADO_REGISTRO", "" + estadoFuente, true);
        if (!_operacion.equals("X")) {
            FuentesFinanciacionDAO ob = new FuentesFinanciacionDAO();
            Collection<FuentesFinanciacionDTO> arr = ob.cargarTodos(codigoRecurso, nombreFuente, estadoFuente);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                FuentesFinanciacionDTO reg = (FuentesFinanciacionDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                String url = "FuentesFinanciacion.po?_operacion=V&codigoFuente=" + reg.getCodigoFuente() + "&codigoRecurso=" + reg.getCodigoRecurso() + "";
                eltr.appendChild(this.newtdhref("" + reg.getCodigoFuente(), url));
                eltr.appendChild(this.newtd("" + reg.getCodigoRecurso()));
                eltr.appendChild(this.newtdhref("" + reg.getNombreFuente(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreEstadoFuente()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigoFuente = 0;

        try {
            codigoFuente = Integer.parseInt(comms.request.getParameter("codigoFuente"));
        } catch (Exception var7) {
        }

        int codigoRecurso = 0;

        try {
            codigoRecurso = Integer.parseInt(comms.request.getParameter("codigoRecurso"));
        } catch (Exception var6) {
        }

        FuentesFinanciacionDAO ob = new FuentesFinanciacionDAO();
        FuentesFinanciacionDTO reg = ob.cargarRegistro(codigoFuente, codigoRecurso);
        if (reg != null) {
            this.pagHTML.setTextCodigoFuenteEd("" + reg.getCodigoFuente());
            this.pagHTML.setTextCodigoRecursoEd("" + reg.getCodigoRecurso());
            this.pagHTML.setTextNombreFuenteEd("" + reg.getNombreFuente());
            this.pagHTML.setTextEstadoFuenteEd("" + reg.getNombreEstadoFuente());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementCodigoFuenteKey().setValue("" + reg.getCodigoFuente());
            this.pagHTML.getElementCodigoRecursoKey().setValue("" + reg.getCodigoRecurso());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoFuentesFinanciacionAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoFuentesFinanciacionDel");
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
