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
import sinco.business.CalResponsablesDTO;
import sinco.business.PdeMetasDTO;
import sinco.business.PdeNivelPlanDTO;
import sinco.business.PdePlanDesarrolloDTO;
import sinco.business.PdeUnidadNivelDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.TGeneralDTO;
import sinco.data.CalResponsablesDAO;
import sinco.data.PdeMetasDAO;
import sinco.data.PdeNivelPlanDAO;
import sinco.data.PdePlanDesarrolloDAO;
import sinco.data.PdeUnidadNivelDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdePlanIndicativo implements HttpPresentation {
    private PdePlanIndicativoHTML pagHTML;

    public PdePlanIndicativo() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            String idNav = (String)comms.session.getSessionData().get("miId");
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null || _operacion.length() == 0) {
                _operacion = "LL";
            }

            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            this.pagHTML = (PdePlanIndicativoHTML)comms.xmlcFactory.create(PdePlanIndicativoHTML.class);
            this.permisos(comms);
            long nitEntidad = 0L;

            try {
                nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
            } catch (Exception var7) {
            }

            if (_operacion.equals("NuevoMetas")) {
                this.nuevoMetas(comms);
            }

            this.pagHTML.getElementNitEntidadHidden().setValue("" + nitEntidad);
            int idPlanDesarrollo = 1;
            this.pagHTML.getElementIdPlanDesarrolloKeyMetas().setValue("" + idPlanDesarrollo);
            this.mostrarCriterios(idPlanDesarrollo);
            if (_operacion.equals("A")) {
                this.listarNiveles(comms, _operacion);
            }

            if (_operacion.equals("Z")) {
                this.consultaNiveles(comms, _operacion);
            }

            if (_operacion.equals("LL")) {
                this.listarNiveles2(comms, _operacion);
            }

            if (!_operacion.equals("L") && _operacion.equals("X")) {
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

        int idNivel;
        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var20) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idNivel"));
        }

        boolean var5 = false;

        int idPlanDesarrollo;
        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var19) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPlanDesarrollo"));
        }

        boolean var6 = false;

        int nivelSuperior;
        try {
            nivelSuperior = Integer.parseInt(comms.request.getParameter("nivelSuperior"));
        } catch (Exception var18) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=nivelSuperior"));
        }

        new RespuestaBD();
        RespuestaBD rta;
        String nombreNivel;
        if (_operacion.equals("E")) {
            PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
            rta = ob.eliminarRegistro(idNivel, idPlanDesarrollo, nivelSuperior);
            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeNivelPlan&p1=" + rta.getMensaje()));
            } else {
                nombreNivel = "PdeNivelPlan.po?_operacion=X";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(nombreNivel));
            }
        } else {
            String codigoNivel = comms.request.getParameter("codigoNivel");
            nombreNivel = comms.request.getParameter("nombreNivel");
            int tipoNivel = 0;

            try {
                tipoNivel = Integer.parseInt(comms.request.getParameter("tipoNivel"));
            } catch (Exception var17) {
            }

            String objetivoGeneral = comms.request.getParameter("objetivoGeneral");
            if (objetivoGeneral == null) {
                objetivoGeneral = "N";
            }

            String objetivoEspecifico = comms.request.getParameter("objetivoEspecifico");
            if (objetivoEspecifico == null) {
                objetivoEspecifico = "N";
            }

            String metasGeneral = comms.request.getParameter("metasGeneral");
            if (metasGeneral == null) {
                metasGeneral = "N";
            }

            String metasEspecifico = comms.request.getParameter("metasEspecifico");
            if (metasEspecifico == null) {
                metasEspecifico = "N";
            }

            PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idNivel, idPlanDesarrollo, nivelSuperior, nombreNivel, tipoNivel, objetivoGeneral, objetivoEspecifico, metasGeneral, metasEspecifico, elUsuario);
                idNivel = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idPlanDesarrollo, nombreNivel, objetivoGeneral, objetivoEspecifico, metasGeneral, metasEspecifico, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeNivelPlan&p1=" + rta.getMensaje()));
            } else {
                String sPagina = "PdeNivelPlan.po?_operacion=P&idNivel=" + idNivel + "&idPlanDesarrollo=" + idPlanDesarrollo + "&nivelSuperior=" + nivelSuperior + "";
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
            }
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var9) {
        }

        int idPlanDesarrollo = 0;

        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var8) {
        }

        int nivelSuperior = 0;

        try {
            nivelSuperior = Integer.parseInt(comms.request.getParameter("nivelSuperior"));
        } catch (Exception var7) {
        }

        PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg = ob.cargarRegistro(idNivel, idPlanDesarrollo, nivelSuperior);
        if (reg != null) {
            this.pagHTML.getElementIdNivel().setValue("" + reg.getIdNivel());
            this.pagHTML.getElementIdPlanDesarrollo().setValue("" + reg.getIdPlanDesarrollo());
            this.pagHTML.getElementNivelSuperior().setValue("" + reg.getNivelSuperior());
            this.pagHTML.getElementNombreNivel().setValue("" + reg.getNombreNivel());
            this.pagHTML.getElementTipoNivel().setValue("" + reg.getTipoNivel());
            if (reg.getObjetivoGeneral().equals("S")) {
                this.pagHTML.getElementObjetivoGeneral().setChecked(true);
            }

            if (reg.getObjetivoEspecifico().equals("S")) {
                this.pagHTML.getElementObjetivoEspecifico().setChecked(true);
            }

            if (reg.getMetasGeneral().equals("S")) {
                this.pagHTML.getElementMetasGeneral().setChecked(true);
            }

            if (reg.getMetasEspecifico().equals("S")) {
                this.pagHTML.getElementMetasEspecifico().setChecked(true);
            }

            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementIdNivel().setReadOnly(true);
            this.pagHTML.getElementIdPlanDesarrollo().setReadOnly(true);
            this.pagHTML.getElementNivelSuperior().setReadOnly(true);
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
        this.pagHTML.getElementIdNivel().setReadOnly(true);
        this.pagHTML.getElementIdNivel().setValue("0");
        this.pagHTML.getElementIdPlanDesarrollo().setReadOnly(true);
        this.pagHTML.getElementIdPlanDesarrollo().setValue("0");
        this.pagHTML.getElementNivelSuperior().setReadOnly(true);
        this.pagHTML.getElementNivelSuperior().setValue("0");
    }

    private void nuevoMetas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        System.out.println("entro aca");
        HTMLSelectElement combo = this.pagHTML.getElementTipoUnidad();
        this.llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "", true);
        combo = this.pagHTML.getElementTipoMeta();
        this.comboMultivalores(combo, "TIPO_META", "", true);
    }

    private void mostrarCriterios(int idPlanDesarrollo) {
    }

    private void consultaNiveles(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        int idPlanDesarrollo = 1;
        this.pagHTML.getElementIdPlanHidden().setValue("" + idPlanDesarrollo);
        HTMLElement sel = this.pagHTML.getElementDivResultados();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementDivResultados2();
        sel.getParentNode().removeChild(sel);
        HTMLSelectElement combo = this.pagHTML.getElementCriterio1();
        this.comboMultivalores(combo, "TIPO_METAI", "", true);
        combo = this.pagHTML.getElementCriterio2();
        this.comboMultivalores(combo, "pde_anios", "", true);
    }

    private void listarNiveles2(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        int idPlanDesarrollo = 1;
        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var25) {
        }

        int criterio1 = 0;

        try {
            criterio1 = Integer.parseInt(comms.request.getParameter("criterio1"));
        } catch (Exception var24) {
        }

        int criterio2 = 0;

        try {
            criterio2 = Integer.parseInt(comms.request.getParameter("criterio2"));
        } catch (Exception var23) {
        }

        boolean var7 = false;

        try {
            int var26 = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var22) {
        }

        new RespuestaBD();
        this.pagHTML.getElementIdPlanHidden().setValue("" + idPlanDesarrollo);
        PdePlanDesarrolloDAO planDao = new PdePlanDesarrolloDAO();
        PdePlanDesarrolloDTO planDto = planDao.cargarRegistro(idPlanDesarrollo, 0L);
        this.pagHTML.getElementNombrePlanModal().setTextContent(planDto.getNombrePlanDesarrollo());
        this.pagHTML.getElementNombrePlanModalEdicion().setTextContent(planDto.getNombrePlanDesarrollo());
        HTMLElement sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementDivResultados();
        sel.getParentNode().removeChild(sel);
        HTMLSelectElement combo;
        if (criterio1 > 0) {
            combo = this.pagHTML.getElementCriterio1();
            this.comboMultivalores(combo, "TIPO_METAI", "" + criterio1, true);
        } else if (criterio1 < 0) {
            combo = this.pagHTML.getElementCriterio1();
            this.llenarComboDocumentos(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo, "" + criterio1, true);
        }

        if (criterio2 > 0) {
            combo = this.pagHTML.getElementCriterio2();
            this.comboMultivalores(combo, "pde_anios", "" + criterio2, true);
        }

        PdeMetasDAO obMetas = new PdeMetasDAO();
        obMetas.cargarTodos(idUnidadNivel, 0);
        PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
        PdeNivelPlanDTO nivelDTO2 = ob.cargarUltimoNivel(idPlanDesarrollo);
        this.pagHTML.setTextCodigoMetaRP("Codigo Meta Producto");
        this.pagHTML.setTextMetaRP("Meta de Producto");
        this.pagHTML.setTextIndicadorRP("Indicador de Producto");
        if (criterio1 == 1) {
            nivelDTO2 = ob.cargarRegistro(nivelDTO2.getNivelSuperior(), 0, 0);
            this.pagHTML.setTextCodigoMetaRP("Codigo Meta Resultado");
            this.pagHTML.setTextMetaRP("Meta de Resultado");
            this.pagHTML.setTextIndicadorRP("Indicador de Resultado");
        }

        this.pagHTML.setTextNombreNivelRes(nivelDTO2.getNombreNivel());
        Collection<PdeMetasDTO> arr = obMetas.cargarMetasUltimosNiveles(nivelDTO2.getIdNivel());
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalle2();
        int cuantas = 0;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            ++cuantas;
            PdeMetasDTO reg = (PdeMetasDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getCodigoUnidad() + " " + reg.getNombreUnidad()));
            eltr.appendChild(this.newtd("SIN DEFINIR"));
            eltr.appendChild(this.newtd("" + reg.getCodigoMeta() + " " + reg.getNombreMeta()));
            eltr.appendChild(this.newtd("" + reg.getLineaBase()));
            eltr.appendChild(this.newtd("" + reg.getCantidad()));
            eltr.appendChild(this.newtd("" + reg.getIndicador()));
            eltr.appendChild(this.newimg(criterio1));
            hte.appendChild(eltr);
        }

        this.pagHTML.setTextNroRegistros("" + cuantas);
        arr.clear();
    }

    private void listarNiveles(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int idPlanDesarrollo;
        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var17) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPlanDesarrollo"));
        }

        this.pagHTML.getElementIdPlanHidden().setValue("" + idPlanDesarrollo);
        HTMLElement sel = this.pagHTML.getElementDivConsulta();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);
        PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
        PdeUnidadNivelDAO ob2 = new PdeUnidadNivelDAO();
        Collection<PdeNivelPlanDTO> arr = ob.cargarTodos("", idPlanDesarrollo);
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
        int cuantas = 0;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            ++cuantas;
            PdeNivelPlanDTO reg = (PdeNivelPlanDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            String url = "PdeUnidadNivel.po?_operacion=L&idNivel=" + reg.getIdNivel();
            String url2 = "PdeUnidadNivel.po?_operacion=S&idNivel=" + reg.getIdNivel() + "&idNivelSuperior=" + reg.getNivelSuperior();
            String url3 = "PdeUnidadNivel.po?_operacion=Z&idNivel=" + reg.getIdNivel();
            if (reg.getNivelSuperior() > 0) {
                Collection<PdeUnidadNivelDTO> registros = ob2.cargarTodos(reg.getNivelSuperior(), 0, "");
                if (registros.isEmpty()) {
                    eltr.appendChild(this.newtdhref("Nivel " + cuantas, url, true));
                    eltr.appendChild(this.newtdhref("" + reg.getNombreNivel(), url, true));
                } else {
                    eltr.appendChild(this.newtdhref("Nivel " + cuantas, url2, false));
                    eltr.appendChild(this.newtdhref("" + reg.getNombreNivel(), url2, false));
                    eltr.appendChild(this.newtdhref("Consultar " + reg.getNombreNivel(), url3, false));
                }
            } else {
                eltr.appendChild(this.newtdhref("Nivel " + cuantas, url, false));
                eltr.appendChild(this.newtdhref("" + reg.getNombreNivel(), url, false));
                eltr.appendChild(this.newtdhref("Consultar " + reg.getNombreNivel(), url3, false));
            }

            eltr.setAttribute("class", "");
            hte.appendChild(eltr);
        }

        arr.clear();
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var9) {
        }

        int idPlanDesarrollo = 0;

        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var8) {
        }

        int nivelSuperior = 0;

        try {
            nivelSuperior = Integer.parseInt(comms.request.getParameter("nivelSuperior"));
        } catch (Exception var7) {
        }

        PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg = ob.cargarRegistro(idNivel, idPlanDesarrollo, nivelSuperior);
        if (reg != null) {
            this.pagHTML.setTextIdNivelEd("" + reg.getIdNivel());
            this.pagHTML.setTextIdPlanDesarrolloEd("" + reg.getIdPlanDesarrollo());
            this.pagHTML.setTextNivelSuperiorEd("" + reg.getNivelSuperior());
            this.pagHTML.setTextNombreNivelEd("" + reg.getNombreNivel());
            this.pagHTML.setTextTipoNivelEd("" + reg.getTipoNivel());
            this.pagHTML.setTextObjetivoGeneralEd("" + reg.getObjetivoGeneral());
            this.pagHTML.setTextObjetivoEspecificoEd("" + reg.getObjetivoEspecifico());
            this.pagHTML.setTextMetasGeneralEd("" + reg.getMetasGeneral());
            this.pagHTML.setTextMetasEspecificoEd("" + reg.getMetasEspecifico());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementIdNivelKey().setValue("" + reg.getIdNivel());
            this.pagHTML.getElementIdPlanDesarrolloKey().setValue("" + reg.getIdPlanDesarrollo());
            this.pagHTML.getElementNivelSuperiorKey().setValue("" + reg.getNivelSuperior());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeNivelPlanAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeNivelPlanDel");
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

    private HTMLElement newtdhref(String contenido, String vinculo, boolean bloqueado) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        td.appendChild(enlace);
        if (bloqueado) {
            td.setAttributeNode(this.newAttr("class", "ctdNuevoBloqueado"));
        } else {
            td.setAttributeNode(this.newAttr("class", "ctdNuevo"));
        }

        return td;
    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctdp"));
        return td;
    }

    private HTMLElement newtdCentrado(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        td.setAttribute("style", "text-align:center");
        return td;
    }

    private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection arr = rs.cargarTabla(tabla);
        rs.close();
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
        Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
        rsTGen.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void llenarComboDocumentos(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
        if (dejarBlanco) {
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op);
        }

        TGeneralDAO rsTGen = new TGeneralDAO();
        Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
        rsTGen.close();

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

         op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("-1");
        op.appendChild(this.pagHTML.createTextNode("Descarga de Documentos"));
        combo.appendChild(op);
        if (defecto.equals("-1")) {
            Attr escogida = this.pagHTML.createAttribute("selected");
            escogida.setValue("on");
            op.setAttributeNode(escogida);
        }

    }

    private void llenarComboNiveles(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
        HTMLOptionElement op2;
        if (dejarBlanco) {
            op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
            op2.setValue("");
            op2.appendChild(this.pagHTML.createTextNode(""));
            combo.appendChild(op2);
        }

        op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
        op2.setValue("-1");
        op2.appendChild(this.pagHTML.createTextNode("0 - Todos"));
        if (defecto.equals("-1")) {
            Attr escogida = this.pagHTML.createAttribute("selected");
            escogida.setValue("on");
            op2.setAttributeNode(escogida);
        }

        combo.appendChild(op2);
        TGeneralDAO rsTGen = new TGeneralDAO();
        Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
        rsTGen.close();
        Iterator iterator = arr.iterator();

        for(int cuantas = 1; iterator.hasNext(); ++cuantas) {
            TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + regGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(cuantas + " - " + regGeneral.getDescripcion()));
            if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }

            combo.appendChild(op);
        }

    }

    private HTMLElement newimg(int criterio) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLElement img = (HTMLElement)this.pagHTML.createElement("img");
        if (criterio == 1) {
            img.setAttribute("src", "media/resultado.png");
        } else {
            img.setAttribute("src", "media/producto.png");
        }

        img.setAttribute("height", "30");
        img.setAttribute("width", "150");
        img.setAttribute("style", "margin:0px 0px 0px 0px; cursor : pointer");
        img.setAttribute("onclick", "");
        HTMLElement img2 = (HTMLElement)this.pagHTML.createElement("img");
        img2.setAttribute("src", "media/indicador.png");
        img2.setAttribute("height", "30");
        img2.setAttribute("width", "150");
        img2.setAttribute("style", "margin:0px 0pxpx 0px 0px; cursor : pointer");
        img2.setAttribute("onclick", "");
        td.appendChild(img);
        td.appendChild(img2);
        return td;
    }

    private HTMLElement newimgDocumento(String ruta) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLElement img = (HTMLElement)this.pagHTML.createElement("img");
        img.setAttribute("src", "media/Descargar.png");
        img.setAttribute("height", "30");
        img.setAttribute("width", "150");
        img.setAttribute("style", "margin:0px -20px 0px 50px; cursor : pointer");
        img.setAttribute("onclick", ruta);
        td.appendChild(img);
        return td;
    }

    private String generarAreas() {
        CalResponsablesDAO rsArea = new CalResponsablesDAO();
        Collection<CalResponsablesDTO> arr = rsArea.cargarCargos();
        rsArea.close();
        String script = "var mAreas=new Array(" + arr.size() + ");";
        script = script + " var iCont=0;";

        CalResponsablesDTO reg;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); script = script + " mAreas[iCont++]=new add_area('" + reg.getCodigoCargo() + "','" + reg.getDescripcion() + "');") {
            reg = (CalResponsablesDTO)iterator.next();
        }

        return script;
    }

    private HTMLElement newtdAnio(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "tit"));
        td.setAttributeNode(this.newAttr("style", "text-align:center;"));
        return td;
    }

    private HTMLElement newinputEjecutado(int id, String valor) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("class", "dat"));
        HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
        input.setAttribute("TYPE", "text");
        input.setAttribute("class", "INP");
        input.setAttribute("name", "_J_" + id);
        input.setAttribute("id", "_J_" + id);
        input.setAttribute("maxlength", "7");
        if (valor.length() > 0) {
            input.setAttribute("value", valor);
        }

        td.appendChild(input);
        return td;
    }

    private HTMLElement newinput(int id, String valor) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("class", "dat"));
        HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
        input.setAttribute("TYPE", "text");
        input.setAttribute("class", "INP");
        input.setAttribute("name", "_E_" + id);
        input.setAttribute("id", "_E_" + id);
        input.setAttribute("maxlength", "7");
        if (valor.length() > 0) {
            input.setAttribute("value", valor);
        }

        td.appendChild(input);
        return td;
    }

    private HTMLElement newtdAnioHidden(String contenido, int id) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("TYPE", "hidden"));
        HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("value", contenido);
        input.setAttribute("id", "_S_" + id);
        input.setAttribute("name", "_S_" + id);
        td.appendChild(input);
        return td;
    }

    public String calcularPorcentaje(String programado, String ejecutado) {
        int x;
        for(x = 0; x < programado.length(); ++x) {
            if (Character.isDigit(programado.charAt(x))) {
                programado = programado.substring(x);
                break;
            }
        }

        for(x = 0; x < ejecutado.length(); ++x) {
            if (Character.isDigit(ejecutado.charAt(x))) {
                ejecutado = ejecutado.substring(x);
                break;
            }
        }

        try {
            String porcentaje = Double.toString(Double.parseDouble(ejecutado) * 100.0D / Double.parseDouble(programado));

            try {
                porcentaje = porcentaje.substring(0, 5);
                return porcentaje;
            } catch (Exception var5) {
                return porcentaje;
            }
        } catch (Exception var6) {
            return "";
        }
    }

    public double limpiarNumero(String numero) {
        int x;
        for(x = 0; x < numero.length(); ++x) {
            if (Character.isDigit(numero.charAt(x))) {
                numero = numero.substring(x);
                break;
            }
        }

        for(x = numero.length() - 1; x > 0; --x) {
            if (!Character.isDigit(numero.charAt(x))) {
                numero = numero.substring(0, x);
            }
        }

        try {
            return Double.parseDouble(numero);
        } catch (Exception var3) {
            return 0.0D;
        }
    }

    private HTMLElement newinputPorcentaje(int id, String valor) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("class", "dat"));
        HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
        input.setAttribute("TYPE", "text");
        input.setAttribute("class", "INP");
        input.setAttribute("name", "_P_" + id);
        input.setAttribute("id", "_P_" + id);
        input.setAttribute("maxlength", "7");
        input.setAttribute("readOnly", "true");
        if (valor.length() > 0) {
            input.setAttribute("value", valor);
        }

        td.appendChild(input);
        return td;
    }

    private HTMLElement newtdAnioHiddenNuevo(String contenido, int id) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.setAttributeNode(this.newAttr("TYPE", "hidden"));
        HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("value", contenido);
        input.setAttribute("id", "_Z_" + id);
        input.setAttribute("name", "_Z_" + id);
        td.appendChild(input);
        return td;
    }
}
