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
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableElement;
import sinco.business.AreasDTO;
import sinco.business.ParametrosDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.Utilidades;
import sinco.data.AreasDAO;
import sinco.data.LibreDAO;
import sinco.data.SisUsuariosDAO;
import sinco.spec.MenuDO;

public class ListarEscalamientos implements HttpPresentation {
    private ListarEscalamientosHTML pagHTML;
    private int tAcciones_mejora;
    private int tOportunidad;
    private int tConfiabilidad;
    private int stConfiabilidad;
    private int stAcciones_mejora;
    private int stOportunidad;
    private int codigo_area;
    private int codigo_areaOld;
    private int codigo_servicio;
    private int codigo_servicioOld;
    private int codigo_empleado;
    private int codigo_empleadoOld;
    private int cuantos;

    public ListarEscalamientos() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            int anno = 0;
            int mes1 = 0;
            int mes2 = 0;
            int area = 0;

            try {
                String parametros = comms.request.getParameter("parametros");
                if (parametros != null && parametros.length() > 0) {
                    anno = Integer.parseInt(parametros.substring(5, 9));
                    mes1 = Integer.parseInt(parametros.substring(14, 16));
                    mes2 = Integer.parseInt(parametros.substring(17, 18));
                    area = Integer.parseInt(parametros.substring(22, parametros.length()));
                } else {
                    anno = Integer.parseInt(comms.request.getParameter("anno"));
                    mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
                    mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
                    area = Integer.parseInt(comms.request.getParameter("area"));
                }
            } catch (Exception var18) {
            }

            if (area == 0) {
                SisUsuariosDAO pfactory = new SisUsuariosDAO();
                SisUsuariosDTO experto = pfactory.cargarRegistro(idNav);
                area = experto.getArea();
            }

            AreasDAO areaf = new AreasDAO();
            AreasDTO areaProveedor = areaf.getArea(area);
            areaf.close();
            this.pagHTML = (ListarEscalamientosHTML)comms.xmlcFactory.create(ListarEscalamientosHTML.class);
            this.pagHTML.setTextPeriodo(Utilidades.nombreMes(mes1) + " a " + Utilidades.nombreMes(mes2) + " del " + anno);
            this.pagHTML.setTextNombreArea("" + areaProveedor.getDescripcion());
            LibreDAO rsLibre = new LibreDAO();
            rsLibre.doExecute(this.crearSentencia(areaProveedor.getSecuencia(), anno, mes1, mes2, ParametrosDTO.getInt("numero.de.imcumplimientos")));
            this.tAcciones_mejora = 0;
            this.stAcciones_mejora = 0;
            this.tOportunidad = 0;
            this.stOportunidad = 0;
            this.tConfiabilidad = 0;
            this.stConfiabilidad = 0;
            HTMLTableElement hte = this.pagHTML.getElementDetalle();
            boolean fondo = false;
            this.codigo_areaOld = 0;
            this.codigo_servicioOld = 0;
            this.codigo_empleadoOld = 0;

            for(this.cuantos = 0; rsLibre.next(); this.codigo_empleadoOld = this.codigo_empleado) {
                this.codigo_area = rsLibre.getInt("codigo_area");
                this.codigo_servicio = rsLibre.getInt("codigo_servicio");
                this.codigo_empleado = rsLibre.getInt("codigo_empleado");
                int rta = this.evaluarCorte();
                HTMLElement eltr;
                switch(rta) {
                case 0:
                case 1:
                default:
                    break;
                case 2:
                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.setAttributeNode(this.newAttr("bgcolor", "#DEDDB8"));
                    eltr.appendChild(this.newtdn("" + rsLibre.getString("nombreServicio"), false, 5));
                    hte.appendChild(eltr);
                    break;
                case 3:
                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtdn("Sub Total", false, 2));
                    eltr.appendChild(this.newtdn("" + this.stOportunidad, true, 0));
                    eltr.appendChild(this.newtdn("" + this.stConfiabilidad, true, 0));
                    eltr.appendChild(this.newtdn("" + this.stAcciones_mejora, true, 0));
                    hte.appendChild(eltr);
                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtd(".", false, 5));
                    hte.appendChild(eltr);
                    this.tConfiabilidad += this.stConfiabilidad;
                    this.tOportunidad += this.stOportunidad;
                    this.tAcciones_mejora += this.stAcciones_mejora;
                    this.stConfiabilidad = this.stAcciones_mejora = this.stOportunidad = 0;
                case -1:
                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.setAttributeNode(this.newAttr("bgcolor", "#FFFFFF"));
                    String sPagina = "ListarEscalamientos.po?anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&area=" + this.codigo_area;
                    eltr.appendChild(this.newtdhref("" + rsLibre.getString("nombreArea"), sPagina, 5, false));
                    hte.appendChild(eltr);
                    eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.setAttributeNode(this.newAttr("bgcolor", "#DEDDB8"));
                    eltr.appendChild(this.newtdn("" + rsLibre.getString("nombreServicio"), false, 5));
                    hte.appendChild(eltr);
                }

                ++this.cuantos;
                eltr = (HTMLElement)this.pagHTML.createElement("tr");
                fondo = !fondo;
                eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
                eltr.appendChild(this.newtd("", true, 0));
                eltr.appendChild(this.newtd("" + rsLibre.getString("nombres") + " " + rsLibre.getString("apellidos"), false, 0));
                int oportunidad = rsLibre.getInt("oportunidad");
                if (oportunidad > 0) {
                    String sPagina = "ListarEscalamientosPersonas.po?titulo=1&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&codigo_servicio=" + this.codigo_servicio + "&area=" + this.codigo_area + "&codigoEmpleado=" + this.codigo_empleado;
                    eltr.appendChild(this.newtdhref("" + oportunidad, sPagina, 0, true));
                } else {
                    eltr.appendChild(this.newtd("" + oportunidad, true, 0));
                }

                int confiabilidad = rsLibre.getInt("confiabilidad");
                if (confiabilidad > 0) {
                    String sPagina = "ListarEscalamientosPersonas.po?titulo=4&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&codigo_servicio=" + this.codigo_servicio + "&area=" + this.codigo_area + "&codigoEmpleado=" + this.codigo_empleado;
                    eltr.appendChild(this.newtdhref("" + confiabilidad, sPagina, 0, true));
                } else {
                    eltr.appendChild(this.newtd("" + confiabilidad, true, 0));
                }

                int acciones_mejora = rsLibre.getInt("acciones_mejora");
                if (acciones_mejora > 0) {
                    String sPagina = "ListarEscalamientosPersonas.po?titulo=2&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2 + "&area=" + this.codigo_area + "&codigoEmpleado=" + this.codigo_empleado + "&codigo_servicio=" + this.codigo_servicio;
                    eltr.appendChild(this.newtdhref("" + acciones_mejora, sPagina, 0, true));
                } else {
                    eltr.appendChild(this.newtd("" + acciones_mejora, true, 0));
                }

                hte.appendChild(eltr);
                this.stConfiabilidad += rsLibre.getInt("confiabilidad");
                this.stOportunidad += rsLibre.getInt("oportunidad");
                this.stAcciones_mejora += rsLibre.getInt("acciones_mejora");
                this.codigo_areaOld = this.codigo_area;
                this.codigo_servicioOld = this.codigo_servicio;
            }

            this.tConfiabilidad += this.stConfiabilidad;
            this.tOportunidad += this.stOportunidad;
            this.tAcciones_mejora += this.stAcciones_mejora;
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtdn("Sub Total", false, 2));
            eltr.appendChild(this.newtdn("" + this.stOportunidad, true, 0));
            eltr.appendChild(this.newtdn("" + this.stConfiabilidad, true, 0));
            eltr.appendChild(this.newtdn("" + this.stAcciones_mejora, true, 0));
            hte.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.setAttributeNode(this.newAttr("bgcolor", "#FFFFFF"));
            eltr.appendChild(this.newtdn("Total...", false, 2));
            eltr.appendChild(this.newtdn("" + this.tOportunidad, true, 0));
            eltr.appendChild(this.newtdn("" + this.tConfiabilidad, true, 0));
            eltr.appendChild(this.newtdn("" + this.tAcciones_mejora, true, 0));
            hte.appendChild(eltr);
            rsLibre.close();
            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtd(String contenido, boolean alinear, int colspan) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (alinear) {
            td.setAttributeNode(this.newAttr("align", "right"));
        }

        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtdn(String contenido, boolean alinear, int colspan) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element negrita = this.pagHTML.createElement("b");
        negrita.appendChild(this.pagHTML.createTextNode(contenido));
        td.appendChild(negrita);
        if (alinear) {
            td.setAttributeNode(this.newAttr("align", "right"));
        }

        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, int colspan, boolean alinear) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        Element negrita = this.pagHTML.createElement("b");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        negrita.appendChild(enlace);
        if (alinear) {
            td.setAttributeNode(this.newAttr("align", "right"));
        }

        td.appendChild(negrita);
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private String crearSentencia(String secuencia, int anno, int mes1, int mes2, int cuantos) {
        String s = "select * from (select";
        s = s + " unidades_dependencia.descripcion as nombreArea,";
        s = s + " codigo_area ,";
        s = s + " servicios.descripcion as nombreServicio,";
        s = s + " codigo_servicio,";
        s = s + " unidades_dependencia.secuencia,";
        s = s + " personas.apellidos,personas.nombres,";
        s = s + " escalamientos.codigo_empleado,";
        s = s + " sum(case when confiabilidad>=" + cuantos + " then confiabilidad else 0 end) as confiabilidad,";
        s = s + " sum(case when oportunidad>=" + cuantos + " then oportunidad else 0 end) as oportunidad,";
        s = s + " sum(acciones_mejora) as acciones_mejora";
        s = s + " from escalamientos,unidades_dependencia,servicios,personas";
        s = s + " where";
        s = s + " escalamientos.codigo_area=unidades_dependencia.codigo";
        s = s + " and escalamientos.codigo_servicio=servicios.codigo";
        s = s + " and escalamientos.codigo_empleado=personas.codigo_empleado";
        s = s + " and unidades_dependencia.secuencia like '" + secuencia + "%'";
        s = s + " and anno=" + anno;
        s = s + " and mes>=" + mes1;
        s = s + " and mes<=" + mes2;
        s = s + " group by unidades_dependencia.descripcion,escalamientos.codigo_area,escalamientos.codigo_empleado,servicios.descripcion,codigo_servicio,secuencia,escalamientos.codigo_empleado,personas.apellidos,personas.nombres";
        s = s + " order by secuencia,nombreServicio,personas.nombres,personas.apellidos";
        s = s + " ) ";
        if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
            s = s + " as resultado";
        }

        s = s + " where";
        s = s + " oportunidad>=" + cuantos;
        s = s + " or confiabilidad>=" + cuantos;
        return s;
    }

    private int evaluarCorte() {
        if (this.cuantos == 0) {
            return -1;
        } else if (this.codigo_areaOld != this.codigo_area) {
            return 3;
        } else if (this.codigo_servicioOld != this.codigo_servicio) {
            return 2;
        } else {
            return this.codigo_empleadoOld != this.codigo_empleado ? 1 : 0;
        }
    }
}
