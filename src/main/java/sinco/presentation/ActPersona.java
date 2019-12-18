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
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import sinco.business.RespuestaBD;
import sinco.business.SisUsuariosDTO;
import sinco.data.LibreDAO;
import sinco.data.ServicioAreaDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.VSolicitudesDAO;
import sinco.spec.MenuDO;

public class ActPersona implements HttpPresentation {
    private ActPersonaHTML pagHTML;

    public ActPersona() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
            String tipo = comms.request.getParameter("tipo");
            if (tipo == null || tipo.length() == 0) {
                tipo = "";
            }

            boolean var5 = false;

            int codigoEmpleado;
            try {
                codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
            } catch (Exception var13) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEmpleado"));
            }

            if (tipo.equals("M")) {
                this.cambiarEstado(comms);
                String sPagina = "FuncionariosDeMiArea.po";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            } else {
                this.pagHTML = (ActPersonaHTML)comms.xmlcFactory.create(ActPersonaHTML.class);
                SisUsuariosDAO perf = new SisUsuariosDAO();
                SisUsuariosDTO elFuncionario = perf.cargarRegistro(codigoEmpleado);
                ServicioAreaDAO saf = new ServicioAreaDAO();
                int serviciosEspecializados = saf.cuantosEspecializados(codigoEmpleado);
                saf.close();
                VSolicitudesDAO sf = new VSolicitudesDAO();
                int solicitudesAbiertas = sf.cuantasEnCursoPedidasA(codigoEmpleado);
                sf.close();
                this.pagHTML.setTextNombre(elFuncionario != null ? elFuncionario.getNombre() : "No Asignado");
                this.pagHTML.setTextSolicitudesAbiertas("" + solicitudesAbiertas);
                this.pagHTML.setTextEspecializados("" + serviciosEspecializados);
                this.pagHTML.getElementCodigoEmpleado().setValue("" + codigoEmpleado);
                HTMLSelectElement combo = this.pagHTML.getElementEstado();
                this.llenarCombo(combo, elFuncionario.getEstado());
                combo = this.pagHTML.getElementResponsable();
                this.funcionarios(combo, miArea);
                this.pagHTML.getElementIdTipo().setValue("M");
                this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
                comms.response.writeDOM(this.pagHTML);
            }
        }
    }

    private boolean llenarCombo(HTMLSelectElement combo, String estado) {
        LibreDAO rs = new LibreDAO();
        rs.doExecute("select * from estados_personas order by descripcion");

        HTMLOptionElement op;
        for(; rs.next(); combo.appendChild(op)) {
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + rs.getString("codigo"));
            op.appendChild(this.pagHTML.createTextNode(rs.getString("descripcion")));
            if (estado.equals(rs.getString("codigo"))) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

        rs.close();
        return true;
    }

    private void funcionarios(HTMLSelectElement combo, int area) {
        SisUsuariosDAO rs = new SisUsuariosDAO();
        Collection arr = rs.cargarActivoDeArea(area);
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode("Seleccione un nuevo proveedor si inactiva al funcionario"));
        combo.appendChild(op);
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + reg.getCodigoEmpleado());
            op.appendChild(this.pagHTML.createTextNode(reg.getNombre()));
            combo.appendChild(op);
        }

    }

    private void cambiarEstado(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String elUsuario = "" + comms.session.getUser().getName();
        boolean var3 = false;

        int codigoEmpleado;
        try {
            codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
        } catch (Exception var9) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEmpleado"));
        }

        int responsable = 0;

        try {
            responsable = Integer.parseInt(comms.request.getParameter("responsable"));
        } catch (Exception var8) {
        }

        String estado = comms.request.getParameter("estado");
        SisUsuariosDAO perf = new SisUsuariosDAO();
        RespuestaBD rta = perf.reasignarFuncionario(codigoEmpleado, responsable, estado, elUsuario);
        if (!rta.isRta()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorReasignando&p1=" + rta.getMensaje()));
        }
    }
}
