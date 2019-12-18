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
import sinco.business.PdeNivelPlanDTO;
import sinco.business.PdePlanDesarrolloDTO;
import sinco.business.PdeUnidadNivelDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisEntidadDTO;
import sinco.data.PdeNivelPlanDAO;
import sinco.data.PdePlanDesarrolloDAO;
import sinco.data.PdeUnidadNivelDAO;
import sinco.data.SisEntidadDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdeUnidadNivel implements HttpPresentation {
    private PdeUnidadNivelHTML pagHTML;

    public PdeUnidadNivel() {
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

            this.pagHTML = (PdeUnidadNivelHTML)comms.xmlcFactory.create(PdeUnidadNivelHTML.class);
            this.permisos(comms);
            int idNivel = 0;

            try {
                idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
            } catch (Exception var15) {
            }

            int idUnidadSuperior = 0;

            try {
                idUnidadSuperior = Integer.parseInt(comms.request.getParameter("idUnidadSuperior"));
            } catch (Exception var14) {
            }

            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(idNivel, 0, 0);
            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
            PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
            SisEntidadDAO obj3 = new SisEntidadDAO();
            SisEntidadDTO reg3 = obj3.cargarRegistro(reg2.getNitEntidad());
            if (reg2.getPlanCargado().equals("S")) {
                try {
                    HTMLElement sel = this.pagHTML.getElementBtnCrear();
                    sel.getParentNode().removeChild(sel);
                } catch (Exception var13) {
                }
            }

            this.pagHTML.getElementNombrePlan().setTextContent(" " + reg2.getNombrePlanDesarrollo());
            this.pagHTML.getElementNombreEntidad().setTextContent(" " + reg3.getNombre());
            this.pagHTML.getElementNumeroNivel().setTextContent("Cargue de datos - Nivel " + regN.getTipoNivel());
            this.pagHTML.getElementNitEntidadHidden().setValue("" + reg2.getNitEntidad());
            this.pagHTML.getElementIdPlanDesarrolloHidden().setValue("" + regN.getIdPlanDesarrollo());
            this.pagHTML.getElementIdNivelHidden().setValue("" + idNivel);
            this.pagHTML.getElementIdUnidadSuperiorHidden().setValue("" + idUnidadSuperior);
            if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
                this.creacion(comms);
            }

            if (_operacion.equals("S")) {
                this.seleccionar(comms, _operacion);
            }

            if (_operacion.equals("L") || _operacion.equals("X")) {
                this.listar(comms, _operacion);
            }

            if (_operacion.equals("Z")) {
                this.listarTodo(comms, _operacion);
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
        int idUnidadNivelModal = 0;

        try {
            idUnidadNivelModal = Integer.parseInt(comms.request.getParameter("idUnidadNivelModal"));
        } catch (Exception var40) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var39) {
        }

        int idUnidadSuperior = 0;

        try {
            idUnidadSuperior = Integer.parseInt(comms.request.getParameter("idUnidadSuperior"));
        } catch (Exception var38) {
        }

        new RespuestaBD();
        String codigoUnidad = comms.request.getParameter("codigoUnidadModal");
        String nombreUnidad = comms.request.getParameter("nombreUnidadModal");
        String objetivoGeneral = comms.request.getParameter("objetivoGeneralModal");
        if (objetivoGeneral == null) {
            objetivoGeneral = "";
        }

        int nivelSuperiorModalCreacion = 0;

        try {
            nivelSuperiorModalCreacion = Integer.parseInt(comms.request.getParameter("nivelSuperiorModalCreacion"));
        } catch (Exception var37) {
        }

        String codigoUnidadModalCreacion = comms.request.getParameter("codigoUnidadModalCreacion");
        String nombreUnidadModalCreacion = comms.request.getParameter("nombreUnidadModalCreacion");
        String objetivoGeneralModalCreacion = comms.request.getParameter("objetivoGeneralModalCreacion");
        if (objetivoGeneralModalCreacion == null) {
            objetivoGeneralModalCreacion = "";
        }

        int idPlanDesarrollo = 0;

        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlan"));
        } catch (Exception var36) {
        }

        int criterio1 = 0;

        try {
            criterio1 = Integer.parseInt(comms.request.getParameter("criterio1"));
        } catch (Exception var35) {
        }

        int criterio2 = 0;

        try {
            criterio2 = Integer.parseInt(comms.request.getParameter("criterio2"));
        } catch (Exception var34) {
        }

        int criterio3 = 0;

        try {
            criterio3 = Integer.parseInt(comms.request.getParameter("criterio3"));
        } catch (Exception var33) {
        }

        int criterio4 = 0;

        try {
            criterio4 = Integer.parseInt(comms.request.getParameter("criterio4"));
        } catch (Exception var32) {
        }

        if (criterio4 > 0) {
            idNivel = criterio4;
        } else if (criterio3 > 0) {
            idNivel = criterio3;
        } else if (criterio2 > 0) {
            idNivel = criterio2;
        } else if (criterio1 > 0) {
            idNivel = criterio1;
        }

        PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
        new PdeNivelPlanDTO();
        PdeNivelPlanDAO ob2 = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg;
        if (idUnidadNivelModal > 0) {
            PdeUnidadNivelDTO unidadDTO = ob.cargarRegistro(idUnidadNivelModal, 0, 0);
            reg = ob2.cargarRegistro(unidadDTO.getIdNivel(), 0, 0);
            idNivel = unidadDTO.getIdNivel();
            idUnidadSuperior = unidadDTO.getIdUnidadSuperior();
        } else {
            reg = ob2.cargarRegistro(idNivel, 0, 0);
        }

        int criterio1Todo = 0;

        try {
            criterio1Todo = Integer.parseInt(comms.request.getParameter("criterio1Todo"));
        } catch (Exception var31) {
        }

        int criterio2Todo = 0;

        try {
            criterio2Todo = Integer.parseInt(comms.request.getParameter("criterio2Todo"));
        } catch (Exception var30) {
        }

        int criterio3Todo = 0;

        try {
            criterio3Todo = Integer.parseInt(comms.request.getParameter("criterio3Todo"));
        } catch (Exception var29) {
        }

        int criterio4Todo = 0;

        try {
            criterio4Todo = Integer.parseInt(comms.request.getParameter("criterio4Todo"));
        } catch (Exception var28) {
        }

        RespuestaBD rta;
        if (reg.getTipoNivel() > 1) {
            if (_operacion.equals("C")) {
                rta = ob.crearRegistro(idUnidadNivelModal, codigoUnidadModalCreacion, idNivel, nivelSuperiorModalCreacion, nombreUnidadModalCreacion, objetivoGeneralModalCreacion, elUsuario);
                idUnidadNivelModal = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistro(idUnidadNivelModal, codigoUnidad, idNivel, idUnidadSuperior, nombreUnidad, objetivoGeneral, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeUnidadNivel&p1=" + rta.getMensaje()));
            }
        } else {
            if (_operacion.equals("C")) {
                rta = ob.crearRegistroSuperior(idUnidadNivelModal, codigoUnidadModalCreacion, idNivel, nombreUnidadModalCreacion, objetivoGeneralModalCreacion, elUsuario);
                idUnidadNivelModal = rta.getSecuencia();
            } else {
                rta = ob.modificarRegistroSuperior(idUnidadNivelModal, codigoUnidad, idNivel, nombreUnidad, objetivoGeneral, elUsuario);
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeUnidadNivel&p1=" + rta.getMensaje()));
            }
        }

        String sPagina = "PdeNivelPlan.po?_operacion=LL&idPlanDesarrollo=" + idPlanDesarrollo + "&criterio1=" + criterio1 + "&criterio1Todo=" + criterio1Todo + "&criterio2=" + criterio2 + "&criterio2Todo=" + criterio2Todo + "&criterio3=" + criterio3 + "&criterio3Todo=" + criterio3Todo + "&criterio4=" + criterio4 + "&criterio4Todo=" + criterio4Todo;
        throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");
        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var12) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var11) {
        }

        int idUnidadSuperior = 0;

        try {
            idUnidadSuperior = Integer.parseInt(comms.request.getParameter("idUnidadSuperior"));
        } catch (Exception var10) {
        }

        PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
        PdeNivelPlanDTO regN = obj.cargarRegistro(idNivel, 0, 0);
        if (regN.getObjetivoGeneral().equals("N")) {
            HTMLElement sel = this.pagHTML.getElementObjGeneral();
            sel.getParentNode().removeChild(sel);
            this.pagHTML.getElementObjGeneralHidden().setValue("N");
        }

        this.pagHTML.getElementNombreNivelCreacion().setTextContent("" + regN.getNombreNivel());
        PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
        PdeUnidadNivelDTO reg = ob.cargarRegistro(idUnidadNivel, idNivel, idUnidadSuperior);
        if (reg != null) {
            this.pagHTML.getElementIdUnidadNivel().setValue("" + reg.getIdUnidadNivel());
            this.pagHTML.getElementCodigoUnidad().setValue("" + reg.getCodigoUnidad());
            this.pagHTML.getElementNombreUnidad().setValue("" + reg.getNombreUnidad());
            this.pagHTML.getElementObjetivoGeneral().setValue("" + reg.getObjetivoGeneral());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            this.pagHTML.getElementIdUnidadNivel().setReadOnly(true);
        }

        this.pagHTML.getElement_operacion().setValue("M");
        this.activarVista("nuevo");
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        this.pagHTML.getElement_operacion().setValue("C");
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");

        try {
            HTMLElement sel = this.pagHTML.getElementBtnEliminar();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var8) {
        }

        boolean var9 = false;

        int idNivel;
        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var7) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idNivel"));
        }

        PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
        PdeNivelPlanDTO regN = obj.cargarRegistro(idNivel, 0, 0);
        if (regN.getObjetivoGeneral().equals("N")) {
            HTMLElement sel = this.pagHTML.getElementObjGeneral();
            sel.getParentNode().removeChild(sel);
            this.pagHTML.getElementObjGeneralHidden().setValue("N");
        }

        this.pagHTML.getElementNombreNivelCreacion().setTextContent("" + regN.getNombreNivel());
        this.activarVista("nuevo");
        this.pagHTML.getElementIdUnidadNivel().setReadOnly(true);
        this.pagHTML.getElementIdUnidadNivel().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");

        try {
            HTMLElement sel = this.pagHTML.getElementDivConsulta();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var19) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var18) {
        }

        int idUnidadSuperior = 0;

        try {
            idUnidadSuperior = Integer.parseInt(comms.request.getParameter("idUnidadSuperior"));
        } catch (Exception var17) {
        }

        String nombreUnidad = comms.request.getParameter("nombreUnidad");
        if (nombreUnidad == null) {
            nombreUnidad = "";
        }

        if (!_operacion.equals("X")) {
            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(idNivel, 0, 0);
            if (regN.getObjetivoGeneral().equals("N")) {
                HTMLElement sel2 = this.pagHTML.getElementObjGeneralListar();
                sel2.getParentNode().removeChild(sel2);
                this.pagHTML.getElementObjGeneralHidden().setValue("N");
            }

            this.pagHTML.getElementNombreNivel().setTextContent("" + regN.getNombreNivel());
            PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
            PdeUnidadNivelDTO r = ob.cargarRegistro(idUnidadSuperior, 0, 0);
            if (idUnidadSuperior > 0) {
                this.pagHTML.getElementNombreNivelSuperior().setTextContent(r.getNombreUnidad());
            }

            Collection arr;
            if (regN.getNivelSuperior() > 0) {
                arr = ob.cargarTodos(regN.getNivelSuperior(), 0, "");
                if (arr.isEmpty()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NivelNoCompletado&p1=idNivel"));
                }
            }

            arr = ob.cargarTodos(idNivel, idUnidadSuperior, "");
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PdeUnidadNivelDTO reg = (PdeUnidadNivelDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCodigoUnidad()));
                String url = "PdeUnidadNivel.po?_operacion=V&idUnidadNivel=" + reg.getIdUnidadNivel() + "&idNivel=" + reg.getIdNivel() + "&idUnidadSuperior=" + reg.getIdUnidadSuperior() + "";
                eltr.appendChild(this.newtdhref("" + reg.getNombreUnidad(), url));
                if (regN.getObjetivoGeneral().equals("S")) {
                    eltr.appendChild(this.newtd("" + reg.getObjetivoGeneral()));
                }

                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void listarTodo(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("seleccion");
        HTMLElement sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);

        try {
            sel = this.pagHTML.getElementBtnCrear();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var17) {
        }

        sel = this.pagHTML.getElementNomNivelSup();
        sel.getParentNode().removeChild(sel);
        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var16) {
        }

        boolean var5 = false;

        try {
            int var19 = Integer.parseInt(comms.request.getParameter("idNivelSuperior"));
        } catch (Exception var15) {
        }

        PdeNivelPlanDAO o = new PdeNivelPlanDAO();
        PdeNivelPlanDTO regi = o.cargarRegistro(idNivel, 0, 0);
        this.pagHTML.getElementNombreSuperior().setTextContent(" " + regi.getNombreNivel());
        this.pagHTML.getElementNombreSuperior2().setTextContent("" + regi.getNombreNivel());
        PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
        Collection<PdeUnidadNivelDTO> arr = ob.cargarTodos(idNivel, 0, "");
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalleSeleccion();
        int cuantas = 0;

        for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
            PdeUnidadNivelDTO reg = (PdeUnidadNivelDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getCodigoUnidad()));
            eltr.appendChild(this.newtd("" + reg.getNombreUnidad()));
            hte.appendChild(eltr);
        }

    }

    private void seleccionar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("seleccion");
        HTMLElement sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);

        try {
            sel = this.pagHTML.getElementBtnCrear();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var18) {
        }

        sel = this.pagHTML.getElementNomNivelSup();
        sel.getParentNode().removeChild(sel);
        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var17) {
        }

        int idNivelSuperior = 0;

        try {
            idNivelSuperior = Integer.parseInt(comms.request.getParameter("idNivelSuperior"));
        } catch (Exception var16) {
        }

        PdeNivelPlanDAO o = new PdeNivelPlanDAO();
        PdeNivelPlanDTO regi = o.cargarRegistro(idNivelSuperior, 0, 0);
        this.pagHTML.getElementNombreSuperior().setTextContent("Seleccion de" + regi.getNombreNivel());
        this.pagHTML.getElementNombreSuperior2().setTextContent("" + regi.getNombreNivel());
        PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
        Collection<PdeUnidadNivelDTO> arr = ob.cargarTodos(idNivelSuperior, 0, "");
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalleSeleccion();
        int cuantas = 0;

        for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
            PdeUnidadNivelDTO reg = (PdeUnidadNivelDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getCodigoUnidad()));
            String url = "PdeUnidadNivel.po?_operacion=L&idNivel=" + idNivel + "&idUnidadSuperior=" + reg.getIdUnidadNivel() + "";
            eltr.appendChild(this.newtdhref("" + reg.getNombreUnidad(), url));
            hte.appendChild(eltr);
        }

    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var15) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var14) {
        }

        int idUnidadSuperior = 0;

        try {
            idUnidadSuperior = Integer.parseInt(comms.request.getParameter("idUnidadSuperior"));
        } catch (Exception var13) {
        }

        PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
        PdeUnidadNivelDTO reg = ob.cargarRegistro(idUnidadNivel, idNivel, idUnidadSuperior);
        if (reg != null) {
            this.pagHTML.setTextIdUnidadNivelEd("" + reg.getIdUnidadNivel());
            this.pagHTML.setTextCodigoUnidadEd("" + reg.getCodigoUnidad());
            this.pagHTML.setTextIdNivelEd("" + reg.getIdNivel());
            this.pagHTML.setTextNombreUnidadEd("" + reg.getNombreUnidad());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(idNivel, 0, 0);
            PdePlanDesarrolloDAO obPlan = new PdePlanDesarrolloDAO();
            PdePlanDesarrolloDTO registro = obPlan.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
            HTMLInputElement sel;
            if (registro.getPlanCargado().equals("S")) {
                try {
                    sel = this.pagHTML.getElementBtnModificar();
                    sel.getParentNode().removeChild(sel);
                } catch (Exception var12) {
                }
            }

            if (regN.getObjetivoGeneral().equals("N")) {
                HTMLElement sel0 = this.pagHTML.getElementObjGeneralEd();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementMetas();
                sel0.getParentNode().removeChild(sel0);
                sel0 = this.pagHTML.getElementObjEspecificos();
                sel0.getParentNode().removeChild(sel0);
                this.pagHTML.getElementObjGeneralHidden().setValue("N");
            } else {
                if (regN.getMetasGeneral().equals("N")) {
                    sel = this.pagHTML.getElementMetas();
                    sel.getParentNode().removeChild(sel);
                }

                if (regN.getObjetivoEspecifico().equals("N")) {
                    sel = this.pagHTML.getElementObjEspecificos();
                    sel.getParentNode().removeChild(sel);
                }

                this.pagHTML.setTextObjetivoGeneralEd("" + reg.getObjetivoGeneral());
            }

            sel = this.pagHTML.getElementBtnVolver();
            sel.setAttribute("onClick", "CambiarVolver()");
            this.pagHTML.getElementIdUnidadNivelKey().setValue("" + reg.getIdUnidadNivel());
            this.pagHTML.getElementIdNivelKey().setValue("" + reg.getIdNivel());
            this.pagHTML.getElementIdUnidadSuperiorKey().setValue("" + reg.getIdUnidadSuperior());
            this.pagHTML.getElementIdUnidadSuperiorHidden().setValue("" + reg.getIdUnidadSuperior());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
    }

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeUnidadNivelAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeUnidadNivelDel");
        HTMLInputElement elem;
        if (!oPermisoAct) {
            try {
                elem = this.pagHTML.getElementBtnCrear();
                elem.getParentNode().removeChild(elem);
                elem = this.pagHTML.getElementBtnGrabar();
                elem.getParentNode().removeChild(elem);
                elem = this.pagHTML.getElementBtnModificar();
                elem.getParentNode().removeChild(elem);
            } catch (Exception var7) {
            }
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

        if (!vista.equals("seleccion")) {
            sel = this.pagHTML.getElementDivSeleccion();
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
