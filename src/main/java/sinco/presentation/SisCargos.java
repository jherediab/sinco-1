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
import sinco.business.RespuestaBD;
import sinco.business.SisCargosDTO;
import sinco.data.SisCargosDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class SisCargos implements HttpPresentation {
    private SisCargosHTML pagHTML;

    public SisCargos() {
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

            this.pagHTML = (SisCargosHTML)comms.xmlcFactory.create(SisCargosHTML.class);
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
        String codigo = comms.request.getParameter("codigo");
        if (codigo == null) {
            codigo = "";
        }

        new RespuestaBD();
        RespuestaBD rta;
        if (_operacion.equals("E")) {
            SisCargosDAO rs = new SisCargosDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                rta = rs.eliminarRegistro(codigo);
                if (!rta.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisCargos&p1=" + rta.getMensaje()));
                } else {
                    rs.close();
                    String sPagina = "SisCargos.po?_operacion=X";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        } else {
            String descripcion = comms.request.getParameter("descripcion");
            SisCargosDAO rs = new SisCargosDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                if (_operacion.equals("C")) {
                    rta = rs.crearRegistro(codigo, descripcion, elUsuario);
                } else {
                    rta = rs.modificarRegistro(codigo, descripcion, elUsuario);
                }

                rs.close();
                if (!rta.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisCargos&p1=" + rta.getMensaje()));
                } else {
                    String sPagina = "SisCargos.po?_operacion=P&codigo=" + codigo + "";
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigo = comms.request.getParameter("codigo");
        SisCargosDAO rs = new SisCargosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            SisCargosDTO reg = rs.cargarRegistro(codigo);
            rs.close();
            if (reg != null) {
                this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
                this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
                this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
                this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
                this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
                this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
                this.pagHTML.getElementCodigo().setReadOnly(true);
            }

            this.pagHTML.getElement_operacion().setValue("M");
            this.activarVista("nuevo");
        }
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        this.pagHTML.getElement_operacion().setValue("C");

        try {
            HTMLElement sel = this.pagHTML.getElementBtnEliminar();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var3) {
        }

        this.activarVista("nuevo");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        if (!_operacion.equals("X")) {
            SisCargosDAO rs = new SisCargosDAO();
            if (!rs.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                Collection<SisCargosDTO> arr = rs.cargarTodos();
                rs.close();
                HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
                int cuantas = 0;

                for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                    SisCargosDTO reg = (SisCargosDTO)iterator.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtd("" + reg.getCodigo()));
                    String url = "SisCargos.po?_operacion=V&codigo=" + reg.getCodigo() + "";
                    eltr.appendChild(this.newtdhref("" + reg.getDescripcion(), url));
                    hte.appendChild(eltr);
                }

                arr.clear();
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigo = comms.request.getParameter("codigo");
        SisCargosDAO rs = new SisCargosDAO();
        if (!rs.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            SisCargosDTO reg = rs.cargarRegistro(codigo);
            rs.close();
            if (reg != null) {
                this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
                this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
                this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
                this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
                this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
                this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
                this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
                this.pagHTML.getElement_operacion().setValue("P");
            }

            this.activarVista("editar");
        }
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisCargosAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisCargosDel");
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
}
