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
import sinco.business.AudInformesPlanAnualDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.data.AudInformesPlanAnualDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class AudInformesPlanAnual implements HttpPresentation {
    private AudInformesPlanAnualHTML pagHTML;

    public AudInformesPlanAnual() {
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

            this.pagHTML = (AudInformesPlanAnualHTML)comms.xmlcFactory.create(AudInformesPlanAnualHTML.class);
            this.permisos(comms);
            boolean var4 = false;

            int ciclo;
            try {
                ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
            } catch (Exception var6) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
            }

            this.pagHTML.getElementCicloHidden().setValue("" + ciclo);
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

        int ciclo;
        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var29) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
        }

        boolean var5 = false;

        int codigoInforme;
        try {
            codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
        } catch (Exception var28) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoInforme"));
        }

        boolean var6 = false;

        int areaResponsable;
        try {
            areaResponsable = Integer.parseInt(comms.request.getParameter("areaResponsable"));
        } catch (Exception var27) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=areaResponsable"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String equipoAuditor;
        if (_operacion.equals("E")) {
            AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
            rta = ob.eliminarRegistro(ciclo, codigoInforme);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudInformesPlanAnual&p1=" + rta.getMensaje()));
            } else {
                equipoAuditor = "AudInformesPlanAnual.po?_operacion=X&ciclo=" + ciclo + "&codigoInforme=" + codigoInforme + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(equipoAuditor));
            }
        } else {
            String coordinadorAuditoría = comms.request.getParameter("coordinadorAuditoría");
            equipoAuditor = comms.request.getParameter("equipoAuditor");
            String titulo = comms.request.getParameter("tituloAuditoria");
            if (titulo == null) {
                titulo = "";
            }

            String objetivos_especificos = comms.request.getParameter("objetivosEspecificosText");
            if (objetivos_especificos == null) {
                objetivos_especificos = "";
            }

            String alcance = comms.request.getParameter("alcanceText");
            if (alcance == null) {
                alcance = "";
            }

            String mes01 = comms.request.getParameter("mes01");
            if (mes01 == null) {
                mes01 = "N";
            }

            String mes02 = comms.request.getParameter("mes02");
            if (mes02 == null) {
                mes02 = "N";
            }

            String mes03 = comms.request.getParameter("mes03");
            if (mes03 == null) {
                mes03 = "N";
            }

            String mes04 = comms.request.getParameter("mes04");
            if (mes04 == null) {
                mes04 = "N";
            }

            String mes05 = comms.request.getParameter("mes05");
            if (mes05 == null) {
                mes05 = "N";
            }

            String mes06 = comms.request.getParameter("mes06");
            if (mes06 == null) {
                mes06 = "N";
            }

            String mes07 = comms.request.getParameter("mes07");
            if (mes07 == null) {
                mes07 = "N";
            }

            String mes08 = comms.request.getParameter("mes08");
            if (mes08 == null) {
                mes08 = "N";
            }

            String mes09 = comms.request.getParameter("mes09");
            if (mes09 == null) {
                mes09 = "N";
            }

            String mes10 = comms.request.getParameter("mes10");
            if (mes10 == null) {
                mes10 = "N";
            }

            String mes11 = comms.request.getParameter("mes11");
            if (mes11 == null) {
                mes11 = "N";
            }

            String mes12 = comms.request.getParameter("mes12");
            if (mes12 == null) {
                mes12 = "N";
            }

            AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
            if (_operacion.equals("C")) {
                AudInformesPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoInforme);
                if (reg != null) {
                    _operacion = "M";
                }
            }

            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(ciclo, codigoInforme, areaResponsable, coordinadorAuditoría, equipoAuditor, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, titulo, objetivos_especificos, alcance, elUsuario);
            } else {
                rta = ob.modificarRegistro(ciclo, codigoInforme, areaResponsable, coordinadorAuditoría, equipoAuditor, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, titulo, objetivos_especificos, alcance, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudInformesPlanAnual&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "AudInformesPlanAnual.po?_operacion=P&ciclo=" + ciclo + "&codigoInforme=" + codigoInforme + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var8) {
        }

        int codigoInforme = 0;

        try {
            codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
        } catch (Exception var7) {
        }

        AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
        AudInformesPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoInforme);
        if (reg != null) {
            this.pagHTML.getElementEquipoAuditor2().setValue("" + reg.getEquipoAuditor());
            this.pagHTML.getElementTituloAuditoria().setValue("" + reg.getTitulo());
            this.pagHTML.getElementAlcance().setValue("" + reg.getAlcance());
            this.pagHTML.getElementObjetivosEspecificos().setValue("" + reg.getObjetivos_especificos());
            if (reg.getMes01().equals("S")) {
                this.pagHTML.getElementMes01().setChecked(true);
            }

            if (reg.getMes02().equals("S")) {
                this.pagHTML.getElementMes02().setChecked(true);
            }

            if (reg.getMes03().equals("S")) {
                this.pagHTML.getElementMes03().setChecked(true);
            }

            if (reg.getMes04().equals("S")) {
                this.pagHTML.getElementMes04().setChecked(true);
            }

            if (reg.getMes05().equals("S")) {
                this.pagHTML.getElementMes05().setChecked(true);
            }

            if (reg.getMes06().equals("S")) {
                this.pagHTML.getElementMes06().setChecked(true);
            }

            if (reg.getMes07().equals("S")) {
                this.pagHTML.getElementMes07().setChecked(true);
            }

            if (reg.getMes08().equals("S")) {
                this.pagHTML.getElementMes08().setChecked(true);
            }

            if (reg.getMes09().equals("S")) {
                this.pagHTML.getElementMes09().setChecked(true);
            }

            if (reg.getMes10().equals("S")) {
                this.pagHTML.getElementMes10().setChecked(true);
            }

            if (reg.getMes11().equals("S")) {
                this.pagHTML.getElementMes11().setChecked(true);
            }

            if (reg.getMes12().equals("S")) {
                this.pagHTML.getElementMes12().setChecked(true);
            }

            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            HTMLSelectElement combo = this.pagHTML.getElementCodigoInforme();
            this.llenarCombo(combo, "AUD_INFORMES", "Codigo", "nombre", "Codigo=" + reg.getCodigoInforme(), "" + reg.getCodigoInforme(), false);
            combo = this.pagHTML.getElementCoordinadorAuditoría();
            this.comboMultivalores(combo, "CAL_COORDINADOR_AUDITORIA", "" + reg.getCoordinadorAuditoría(), true);
            combo = this.pagHTML.getElementAreaResponsable();
            this.llenarCombo(combo, "Unidades_Dependencia", "Codigo", "Descripcion", "Codigo=" + reg.getAreaResponsable(), "" + reg.getAreaResponsable(), true);
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
        HTMLSelectElement combo = this.pagHTML.getElementCodigoInforme();
        this.llenarCombo(combo, "AUD_INFORMES", "Codigo", "nombre", "1=1", "", true);
        combo = this.pagHTML.getElementCoordinadorAuditoría();
        this.comboMultivalores(combo, "CAL_COORDINADOR_AUDITORIA", "", true);
        combo = this.pagHTML.getElementAreaResponsable();
        this.llenarCombo(combo, "Unidades_Dependencia", "Codigo", "Descripcion", "1=1", "", true);
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int ciclo;
        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var14) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
        }

        String tipoInforme = comms.request.getParameter("tipoInforme");
        if (tipoInforme == null) {
            tipoInforme = "";
        }

        HTMLSelectElement combo = this.pagHTML.getElementTipoInforme();
        this.comboMultivalores(combo, "CAL_TIPO_INFORME", "" + tipoInforme, true);
        if (!_operacion.equals("X")) {
            AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
            Collection<AudInformesPlanAnualDTO> arr = ob.cargarTodos(ciclo, tipoInforme);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                AudInformesPlanAnualDTO reg = (AudInformesPlanAnualDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCiclo()));
                String url = "AudInformesPlanAnual.po?_operacion=V&ciclo=" + reg.getCiclo() + "&codigoInforme=" + reg.getCodigoInforme() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombreCodigoInforme(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreCoordinadorAuditoría()));
                eltr.appendChild(this.newtd("" + reg.getEquipoAuditor()));
                eltr.appendChild(this.newtd("" + reg.getNombreTipoInforme()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int ciclo = 0;

        try {
            ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
        } catch (Exception var7) {
        }

        int codigoInforme = 0;

        try {
            codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
        } catch (Exception var6) {
        }

        AudInformesPlanAnualDAO ob = new AudInformesPlanAnualDAO();
        AudInformesPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoInforme);
        if (reg != null) {
            this.pagHTML.setTextCicloEd("" + reg.getCiclo());
            this.pagHTML.setTextCodigoInformeEd("" + reg.getNombreCodigoInforme());
            this.pagHTML.setTextCoordinadorAuditoríaEd("" + reg.getNombreCoordinadorAuditoría());
            this.pagHTML.setTextTituloAuditoriaEd("" + reg.getTitulo());
            this.pagHTML.setTextObjetivosEspecificosEd("" + reg.getObjetivos_especificos());
            this.pagHTML.setTextAlcanceEd("" + reg.getAlcance());
            this.pagHTML.setTextEquipoAuditorEd("" + reg.getEquipoAuditor());
            this.pagHTML.setTextAreaResponsableEd("" + reg.getNombreAreaResponsable());
            this.pagHTML.setTextMes01Ed("" + reg.getMes01());
            this.pagHTML.setTextMes02Ed("" + reg.getMes02());
            this.pagHTML.setTextMes03Ed("" + reg.getMes03());
            this.pagHTML.setTextMes04Ed("" + reg.getMes04());
            this.pagHTML.setTextMes05Ed("" + reg.getMes05());
            this.pagHTML.setTextMes06Ed("" + reg.getMes06());
            this.pagHTML.setTextMes07Ed("" + reg.getMes07());
            this.pagHTML.setTextMes08Ed("" + reg.getMes08());
            this.pagHTML.setTextMes09Ed("" + reg.getMes09());
            this.pagHTML.setTextMes10Ed("" + reg.getMes10());
            this.pagHTML.setTextMes11Ed("" + reg.getMes11());
            this.pagHTML.setTextMes12Ed("" + reg.getMes12());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
            this.pagHTML.getElementCodigoInformeKey().setValue("" + reg.getCodigoInforme());
            this.pagHTML.getElementCodigoPadre().setValue("" + reg.getCodigoInforme());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudInformesPlanAnualAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudInformesPlanAnualDel");
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

    private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto) {
        SisMultiValoresDAO ob = new SisMultiValoresDAO();
        Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla, defecto);
        ob.close();

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
