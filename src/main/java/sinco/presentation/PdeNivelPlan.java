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
import java.util.ArrayList;
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
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.CalResponsablesDTO;
import sinco.business.FechaDTO;
import sinco.business.PdeAniosDTO;
import sinco.business.PdeMetasDTO;
import sinco.business.PdeNivelPlanDTO;
import sinco.business.PdeObjetivosEspecificosDTO;
import sinco.business.PdePlanDesarrolloDTO;
import sinco.business.PdeUnidadNivelDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisMultiValoresDTO;
import sinco.business.SisUnidadesMedidaDTO;
import sinco.business.TGeneralDTO;
import sinco.data.CalResponsablesDAO;
import sinco.data.PdeAniosDAO;
import sinco.data.PdeMetasDAO;
import sinco.data.PdeNivelPlanDAO;
import sinco.data.PdeObjetivosEspecificosDAO;
import sinco.data.PdePlanDesarrolloDAO;
import sinco.data.PdeUnidadNivelDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.SisUnidadesMedidaDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdeNivelPlan implements HttpPresentation {
    private PdeNivelPlanHTML pagHTML;

    public PdeNivelPlan() {
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

            this.pagHTML = (PdeNivelPlanHTML)comms.xmlcFactory.create(PdeNivelPlanHTML.class);
            this.permisos(comms);
            long nitEntidad = 0L;

            try {
                nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
            } catch (Exception var9) {
            }

            if (_operacion.equals("NuevoMetas")) {
                this.nuevoMetas(comms);
            }

            this.pagHTML.getElementNitEntidadHidden().setValue("" + nitEntidad);
            boolean var6 = false;

            int idPlanDesarrollo;
            try {
                idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
            } catch (Exception var8) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPlanDesarrollo"));
            }

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
        PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
        ob.contarNivelesPlan(idPlanDesarrollo);

        try {
            HTMLElement sel = this.pagHTML.getElementTr6();
            sel.setAttribute("style", "display : none");
            sel = this.pagHTML.getElementTr5();
            sel.setAttribute("style", "display : none");
            sel = this.pagHTML.getElementTr4();
            sel.setAttribute("style", "display : none");
            sel = this.pagHTML.getElementTr3();
            sel.setAttribute("style", "display : none");
            sel = this.pagHTML.getElementTr2();
            sel.setAttribute("style", "display : none");
        } catch (Exception var5) {
        }

    }

    private void consultaNiveles(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int idPlanDesarrollo;
        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var6) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPlanDesarrollo"));
        }

        this.pagHTML.getElementIdPlanHidden().setValue("" + idPlanDesarrollo);
        HTMLElement sel = this.pagHTML.getElementDivResultados();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementBtnConsultar();
        sel.getParentNode().removeChild(sel);
        sel = this.pagHTML.getElementDivResultados2();
        sel.getParentNode().removeChild(sel);
        HTMLSelectElement combo = this.pagHTML.getElementCriterio1();
        this.llenarComboDocumentos(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo, "", true);
    }

    private void listarNiveles2(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        boolean var3 = false;

        int idPlanDesarrollo;
        try {
            idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
        } catch (Exception var89) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idPlanDesarrollo"));
        }

        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var88) {
        }

        int criterio1 = 0;

        try {
            criterio1 = Integer.parseInt(comms.request.getParameter("criterio1"));
        } catch (Exception var87) {
        }

        int criterio2 = 0;

        try {
            criterio2 = Integer.parseInt(comms.request.getParameter("criterio2"));
        } catch (Exception var86) {
        }

        int criterio3 = 0;

        try {
            criterio3 = Integer.parseInt(comms.request.getParameter("criterio3"));
        } catch (Exception var85) {
        }

        int criterio4 = 0;

        try {
            criterio4 = Integer.parseInt(comms.request.getParameter("criterio4"));
        } catch (Exception var84) {
        }

        int criterio1Todo = 0;

        try {
            criterio1Todo = Integer.parseInt(comms.request.getParameter("criterio1Todo"));
        } catch (Exception var83) {
        }

        int criterio2Todo = 0;

        try {
            criterio2Todo = Integer.parseInt(comms.request.getParameter("criterio2Todo"));
        } catch (Exception var82) {
        }

        int criterio3Todo = 0;

        try {
            criterio3Todo = Integer.parseInt(comms.request.getParameter("criterio3Todo"));
        } catch (Exception var81) {
        }

        int criterio4Todo = 0;

        try {
            criterio4Todo = Integer.parseInt(comms.request.getParameter("criterio4Todo"));
        } catch (Exception var80) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var79) {
        }

        int metaCreada = 0;

        try {
            metaCreada = Integer.parseInt(comms.request.getParameter("metaCreada"));
        } catch (Exception var78) {
        }

        int idMeta = 0;

        try {
            idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
        } catch (Exception var77) {
        }

        new RespuestaBD();
        if (idNivel > 0) {
            PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
            if (ob.poseeNivelInferior(idNivel)) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=a"));
            } else if (ob.poseeObjetivosEspecificos(idNivel)) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=b"));
            } else if (ob.poseeMetas(idNivel)) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=c"));
            } else {
                RespuestaBD rta = ob.eliminarRegistro(idNivel);
                if (!rta.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeUnidadNivel&p1=" + rta.getMensaje()));
                } else {
                    String sPagina = "PdeNivelPlan.po?_operacion=LL&idPlanDesarrollo=" + idPlanDesarrollo + "&criterio1=" + criterio1 + "&criterio1Todo=" + criterio1Todo + "&criterio2=" + criterio2 + "&criterio2Todo=" + criterio2Todo + "&criterio3=" + criterio3 + "&criterio3Todo=" + criterio3Todo + "&criterio4=" + criterio4 + "&criterio4Todo=" + criterio4Todo;
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
                }
            }
        } else {
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
                this.llenarComboDocumentos(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo, "" + criterio1, true);
                combo = this.pagHTML.getElementCriterio1Todo();
                this.llenarComboNiveles(combo, "pde_unidad_nivel", "id_unidad_nivel", "nombre_unidad", "id_nivel=" + criterio1, "" + criterio1Todo, true);
            } else if (criterio1 < 0) {
                combo = this.pagHTML.getElementCriterio1();
                this.llenarComboDocumentos(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo, "" + criterio1, true);
            }

            //HTMLSelectElement combo;
            HTMLTableRowElement sel2;
            if (criterio2 > 0) {
                sel2 = this.pagHTML.getElementTr2();
                sel2.setAttribute("style", "display : table-row");
                combo = this.pagHTML.getElementCriterio2();
                this.llenarCombo(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo + " and id_nivel=" + criterio2, "" + criterio2, false);
                combo = this.pagHTML.getElementCriterio2Todo();
                this.llenarComboNiveles(combo, "pde_unidad_nivel", "id_unidad_nivel", "nombre_unidad", "id_unidad_superior=" + criterio1Todo, "" + criterio2Todo, true);
            }

            if (criterio3 > 0) {
                sel2 = this.pagHTML.getElementTr3();
                sel2.setAttribute("style", "display : table-row");
                combo = this.pagHTML.getElementCriterio3();
                this.llenarCombo(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo + " and id_nivel=" + criterio3, "" + criterio3, false);
                combo = this.pagHTML.getElementCriterio3Todo();
                this.llenarComboNiveles(combo, "pde_unidad_nivel", "id_unidad_nivel", "nombre_unidad", "id_unidad_superior=" + criterio2Todo, "" + criterio3Todo, true);
            }

            if (criterio4 > 0) {
                sel2 = this.pagHTML.getElementTr4();
                sel2.setAttribute("style", "display : table-row");
                combo = this.pagHTML.getElementCriterio4();
                this.llenarCombo(combo, "pde_nivel_plan", "id_nivel", "nombre_nivel", "id_plan_desarrollo=" + idPlanDesarrollo + " and id_nivel=" + criterio4, "" + criterio4, false);
                combo = this.pagHTML.getElementCriterio4Todo();
                this.llenarComboNiveles(combo, "pde_unidad_nivel", "id_unidad_nivel", "nombre_unidad", "id_unidad_superior=" + criterio3Todo, "" + criterio4Todo, true);
            }

            PdeNivelPlanDAO ob = new PdeNivelPlanDAO();
            PdeUnidadNivelDAO ob2 = new PdeUnidadNivelDAO();
            new PdeUnidadNivelDTO();
            Collection<PdeUnidadNivelDTO> arr = new ArrayList();
            PdeUnidadNivelDTO nivelDTO;
            HTMLElement eltr4;
            if (criterio4Todo != 0) {
                if (criterio4Todo > 0) {
                    nivelDTO = ob2.cargarRegistro(criterio4Todo, 0, 0);
                    if (nivelDTO != null) {
                        ((Collection)arr).add(nivelDTO);
                    }
                } else {
                    arr = ob2.cargarTodos(0, criterio3Todo, "");
                }
            } else if (criterio3Todo != 0) {
                if (criterio3Todo > 0) {
                    nivelDTO = ob2.cargarRegistro(criterio3Todo, 0, 0);
                    if (nivelDTO != null) {
                        ((Collection)arr).add(nivelDTO);
                    }
                } else {
                    arr = ob2.cargarTodos(0, criterio2Todo, "");
                }
            } else if (criterio2Todo != 0) {
                if (criterio2Todo > 0) {
                    nivelDTO = ob2.cargarRegistro(criterio2Todo, 0, 0);
                    if (nivelDTO != null) {
                        ((Collection)arr).add(nivelDTO);
                    }
                } else {
                    arr = ob2.cargarTodos(0, criterio1Todo, "");
                }
            } else if (criterio1Todo > 0) {
                nivelDTO = ob2.cargarRegistro(criterio1Todo, 0, 0);
                if (nivelDTO != null) {
                    ((Collection)arr).add(nivelDTO);
                }
            } else if (criterio1 < 0) {
                int cuantas = 0;
                HTMLTableSectionElement hte = this.pagHTML.getElementDetalle2();
                HTMLTableCellElement eltr = this.pagHTML.getElementTrResultNombre();
                eltr.setTextContent("Nombre Documento");
                eltr = this.pagHTML.getElementTrResultTareas();
                eltr.setTextContent("Gestion");
                HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
                HTMLElement eltr3 = (HTMLElement)this.pagHTML.createElement("tr");
                eltr4 = (HTMLElement)this.pagHTML.createElement("tr");
                HTMLElement eltr5 = (HTMLElement)this.pagHTML.createElement("tr");
                eltr2.appendChild(this.newtd("PDI"));
                eltr2.appendChild(this.newtd("Plan de Desarrollo Institucional"));
                eltr2.appendChild(this.newimgDocumento("descargarPlan()"));
                hte.appendChild(eltr2);
                eltr3.appendChild(this.newtd("POAI"));
                eltr3.appendChild(this.newtd("Plan Operativo Anual de Inversiones"));
                eltr3.appendChild(this.newimgDocumento("descargarPoa()"));
                hte.appendChild(eltr3);
                eltr4.appendChild(this.newtd("PI"));
                eltr4.appendChild(this.newtd("Plan Indicativo"));
                eltr4.appendChild(this.newimgDocumento("descargarPlanIndicativo()"));
                hte.appendChild(eltr4);
                eltr5.appendChild(this.newtd("PA"));
                eltr5.appendChild(this.newtd("Plan de Accion"));
                eltr5.appendChild(this.newimgDocumento("descargarPlanAccion()"));
                hte.appendChild(eltr5);
            } else {
                arr = ob2.cargarTodos(criterio1, 0, "");
            }

            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle2();
            int cuantas = 0;
            String unidad = "";
            Iterator iterator = ((Collection)arr).iterator();

            while(iterator.hasNext()) {
                ++cuantas;
                PdeUnidadNivelDTO reg = (PdeUnidadNivelDTO)iterator.next();
                eltr4 = (HTMLElement)this.pagHTML.createElement("tr");
                int unidadSuperior = reg.getIdUnidadSuperior();
                PdeNivelPlanDTO nivelDTO2 = ob.cargarRegistro(reg.getIdNivel(), 0, 0);
                eltr4.appendChild(this.newtdCentrado("" + reg.getCodigoUnidad()));
                eltr4.appendChild(this.newtd("" + reg.getNombreUnidad()));
                eltr4.appendChild(this.newimg(reg.getIdUnidadNivel(), "'" + nivelDTO2.getNombreNivel() + "'", "'" + reg.getNombreUnidad() + "'", "'" + reg.getCodigoUnidad() + "'", "'" + reg.getObjetivoGeneral() + "'", "'" + reg.getFechaInsercion() + "'", "'" + reg.getFechaModificacion() + "'", "'" + reg.getUsuarioInsercion() + "'", "'" + reg.getUsuarioModificacion() + "'", ""));
                hte.appendChild(eltr4);
            }

            ((Collection)arr).clear();
            if (criterio1 < 0) {
                this.pagHTML.setTextNroRegistros("4");
            } else {
                this.pagHTML.setTextNroRegistros("" + cuantas);
            }

            if (idUnidadNivel > 0) {
                Collection<PdeUnidadNivelDTO> arrNivelDTO = ob2.cargarTodos(0, idUnidadNivel, "");
                Iterator<PdeUnidadNivelDTO> iteratorNivelDTO = arrNivelDTO.iterator();
                HTMLTableSectionElement hteNiveles = this.pagHTML.getElementDetalleNiveles();

                int cuantosNiveles;
                for(cuantosNiveles = 0; iteratorNivelDTO.hasNext(); ++cuantosNiveles) {
                    PdeUnidadNivelDTO pdeUnidadNivelDTO = (PdeUnidadNivelDTO)iteratorNivelDTO.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtdCentrado("" + pdeUnidadNivelDTO.getCodigoUnidad()));
                    eltr.appendChild(this.newtd("" + pdeUnidadNivelDTO.getNombreUnidad()));
                    hteNiveles.appendChild(eltr);
                }

                arrNivelDTO.clear();
                this.pagHTML.setTextNroRegistrosNiveles("" + cuantosNiveles);
                nivelDTO = ob2.cargarRegistro(idUnidadNivel, 0, 0);
                PdeNivelPlanDTO nivelDTO2 = ob.cargarRegistro(nivelDTO.getIdNivel(), 0, 0);

                try {
                    PdeNivelPlanDTO nivelDTO3 = ob.cargarRegistro(0, 0, nivelDTO2.getIdNivel());
                    HTMLElement ele = this.pagHTML.getElementNombreSiguienteNivel();
                    ele.setTextContent(nivelDTO3.getNombreNivel());
                } catch (Exception var76) {
                    HTMLElement sel3 = this.pagHTML.getElementDivResultadosNiveles();
                    sel3.getParentNode().removeChild(sel3);
                }

                this.pagHTML.setTextScriptModal("consultarNivel('" + nivelDTO.getIdUnidadNivel() + "'," + "'" + nivelDTO2.getNombreNivel() + "'," + "'" + nivelDTO.getNombreUnidad() + "'," + "'" + nivelDTO.getCodigoUnidad() + "'," + "'" + nivelDTO.getObjetivoGeneral() + "'," + "'" + nivelDTO.getFechaInsercion() + "'," + "'" + nivelDTO.getFechaModificacion() + "'," + "'" + nivelDTO.getUsuarioInsercion() + "'," + "'" + nivelDTO.getUsuarioModificacion() + "');");
                PdeMetasDAO obMetas = new PdeMetasDAO();
                Collection<PdeMetasDTO> arrMetas = obMetas.cargarTodos(idUnidadNivel, 0);
                HTMLTableSectionElement hteMetas = this.pagHTML.getElementDetalleMetas();
                int cuantasMetas = 0;

                for(Iterator iteratorMetas = arrMetas.iterator(); iteratorMetas.hasNext(); ++cuantasMetas) {
                    PdeMetasDTO reg = (PdeMetasDTO)iteratorMetas.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtdCentrado("" + reg.getCodigoMeta()));
                    eltr.appendChild(this.newtd("" + reg.getNombreMeta()));
                    eltr.appendChild(this.newimg(reg.getIdMeta(), "'" + nivelDTO2.getNombreNivel() + "'", "'" + reg.getNombreUnidad() + "'", "'" + reg.getCodigoUnidad() + "'", "'d'", "'" + reg.getFechaInsercion() + "'", "'" + reg.getFechaModificacion() + "'", "'" + reg.getUsuarioInsercion() + "'", "'" + reg.getUsuarioModificacion() + "'", "metas"));
                    hteMetas.appendChild(eltr);
                }

                arrMetas.clear();
                this.pagHTML.setTextNroRegistrosMetas("" + cuantasMetas);
                if (metaCreada != 0) {
                    String elScript;
                    boolean ultimo;
                    int idFila;
                    if (metaCreada == 3) {
                        HTMLElement button = this.pagHTML.getElementBtnGrabarMetas();
                        button.setAttribute("onclick", "GrabarModalModificacionMetas()");
                        HTMLElement modal = this.pagHTML.getElementCreacionModalMetas();
                        modal.setAttribute("style", "display : block");
                        elScript = this.generarAreas();
                        this.pagHTML.setTextScriptAreas("" + elScript);
                        PdeNivelPlanDAO objNivel = new PdeNivelPlanDAO();
                        ultimo = objNivel.ultimoNivel(nivelDTO2.getIdNivel());
                        PdeMetasDTO reg = obMetas.cargarRegistro(idMeta);
                        if (reg != null) {
                            this.pagHTML.getElementIdMeta().setValue("" + reg.getIdMeta());
                            this.pagHTML.getElementCodigoMeta().setValue("" + reg.getCodigoMeta());
                            this.pagHTML.getElementNombreMeta().setValue("" + reg.getNombreMeta());
                            this.pagHTML.getElementCantidad().setValue("" + reg.getCantidad());
                            this.pagHTML.getElementLineaBase().setValue("" + reg.getLineaBase());
                            this.pagHTML.getElementIndicador().setValue("" + reg.getIndicador());
                            this.pagHTML.getElementFechaInsercionMetas().setValue("" + reg.getFechaInsercion());
                            this.pagHTML.getElementUsuarioInsercionMetas().setValue("" + reg.getUsuarioInsercion());
                            this.pagHTML.getElementFechaModificacionMetas().setValue("" + reg.getFechaModificacion());
                            this.pagHTML.getElementUsuarioModificacionMetas().setValue("" + reg.getUsuarioModificacion());
                            PdeUnidadNivelDAO objeto = new PdeUnidadNivelDAO();
                            PdeUnidadNivelDTO registro = objeto.cargarRegistro(idUnidadNivel, 0, 0);
                            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
                            PdeNivelPlanDTO regN = obj.cargarRegistro(registro.getIdNivel(), 0, 0);
                            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
                            PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
                            int anioFinal = 0;
                            int anioInicial = 0;
                            idFila = 1;
                            int aux = 1;
                            FechaDTO fecha = new FechaDTO(reg2.getFechaInicial());
                            FechaDTO fecha2 = new FechaDTO(reg2.getFechaFinal());
                            anioInicial = fecha.getAnno();
                            anioFinal = fecha2.getAnno();
                            HTMLTableSectionElement hteEditar = this.pagHTML.getElementIdDetalleAnio();
                            if (!ultimo) {
                                HTMLElement elem = this.pagHTML.getElementTrimestre();
                                elem.getParentNode().removeChild(elem);
                                elem = this.pagHTML.getElementTrProceso();
                                elem.getParentNode().removeChild(elem);
                                elem = this.pagHTML.getElementTrResponsable();
                                elem.getParentNode().removeChild(elem);
                                elem = this.pagHTML.getElementAnio();
                                elem.setAttribute("style", "width:25%");
                                elem = this.pagHTML.getElementProgramado();
                                elem.setAttribute("style", "width:25%");
                                elem = this.pagHTML.getElementEjecutado();
                                elem.setAttribute("style", "width:25%");
                                elem = this.pagHTML.getElementPorcentaje();
                                elem.setAttribute("style", "width:25%");

                                while(anioInicial <= anioFinal) {
                                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtdAnio("" + anioInicial));
                                    PdeAniosDAO anio = new PdeAniosDAO();
                                    PdeAniosDTO anioDTO = anio.cargarRegistro(anioInicial, idMeta);
                                    if (anioDTO != null) {
                                        eltr.appendChild(this.newinput(idFila, anioDTO.getProgramado()));
                                        eltr.appendChild(this.newinputEjecutado(idFila, anioDTO.getEjecutado()));
                                        eltr.appendChild(this.newinputPorcentaje(idFila, this.calcularPorcentaje(anioDTO.getProgramado(), anioDTO.getEjecutado())));
                                    } else {
                                        eltr.appendChild(this.newinput(idFila, ""));
                                        eltr.appendChild(this.newinputEjecutado(idFila, ""));
                                        eltr.appendChild(this.newinputPorcentaje(idFila, ""));
                                    }

                                    eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                                    hteEditar.appendChild(eltr);
                                    ++anioInicial;
                                    ++idFila;
                                }
                            } else {
                                PdeAniosDAO anio = new PdeAniosDAO();
                                Collection<PdeAniosDTO> arrEditar = anio.cargarTodos(idMeta, true);
                                Iterator<PdeAniosDTO> iteratorEditar = arrEditar.iterator();
                                double consolidadoProgramado = 0.0D;
                                double consolidadoEjecutado = 0.0D;
                                HTMLElement elem = this.pagHTML.getElementTrimestre();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementAnio();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementProgramado();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementEjecutado();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementPorcentaje();
                                elem.setAttribute("style", "width:20%");
                                combo = this.pagHTML.getElementProceso();
                                this.llenarCombo(combo, "procesos", "codigo", "descripcion", "1=1", "" + reg.getProceso(), true);
                                int trimestre = 1;
                                boolean idAnio = false;

                                while(anioInicial <= anioFinal) {
                                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    PdeAniosDTO regi = new PdeAniosDTO();
                                    if (iteratorEditar.hasNext()) {
                                        regi = (PdeAniosDTO)iteratorEditar.next();
                                    }

                                    if (trimestre <= 4 && trimestre != 1) {
                                        eltr.appendChild(this.newtdAnio(""));
                                        idAnio = false;
                                    } else {
                                        if (trimestre > 4) {
                                            ++anioInicial;
                                            if (anioInicial > anioFinal) {
                                                eltr.appendChild(this.newtdAnio("Consolidado"));
                                                eltr.appendChild(this.newtd(""));
                                                eltr.appendChild(this.newtd("" + consolidadoProgramado));
                                                eltr.appendChild(this.newtd("" + consolidadoEjecutado));
                                                eltr.appendChild(this.newtd("" + this.calcularPorcentaje(Double.toString(consolidadoProgramado), Double.toString(consolidadoEjecutado))));
                                                eltr.appendChild(this.newtdAnioHiddenNuevo("" + anioInicial, idFila));
                                                hteEditar.appendChild(eltr);
                                                break;
                                            }
                                        }

                                        eltr.appendChild(this.newtdAnio("" + anioInicial));
                                        idAnio = true;
                                        trimestre = 1;
                                    }

                                    eltr.appendChild(this.newtdAnio("TR." + trimestre));
                                    switch(aux) {
                                    case 1:
                                        eltr.appendChild(this.newinput(idFila, "" + regi.getTr1Programado()));
                                        eltr.appendChild(this.newinputEjecutado(idFila, "" + regi.getTr1Ejecutado()));
                                        eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr1Programado(), regi.getTr1Ejecutado())));
                                        consolidadoProgramado += regi.getTr1Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr1Programado());
                                        consolidadoEjecutado += regi.getTr1Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr1Ejecutado());
                                        break;
                                    case 2:
                                        eltr.appendChild(this.newinput(idFila, "" + regi.getTr2Programado()));
                                        eltr.appendChild(this.newinputEjecutado(idFila, "" + regi.getTr2Ejecutado()));
                                        eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr2Programado(), regi.getTr2Ejecutado())));
                                        consolidadoProgramado += regi.getTr2Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr2Programado());
                                        consolidadoEjecutado += regi.getTr2Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr2Ejecutado());
                                        break;
                                    case 3:
                                        eltr.appendChild(this.newinput(idFila, "" + regi.getTr3Programado()));
                                        eltr.appendChild(this.newinputEjecutado(idFila, "" + regi.getTr3Ejecutado()));
                                        eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr3Programado(), regi.getTr3Ejecutado())));
                                        consolidadoProgramado += regi.getTr3Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr3Programado());
                                        consolidadoEjecutado += regi.getTr3Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr3Ejecutado());
                                        break;
                                    case 4:
                                        eltr.appendChild(this.newinput(idFila, "" + regi.getTr4Programado()));
                                        eltr.appendChild(this.newinputEjecutado(idFila, "" + regi.getTr4Ejecutado()));
                                        eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr4Programado(), regi.getTr4Ejecutado())));
                                        consolidadoProgramado += regi.getTr4Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr4Programado());
                                        consolidadoEjecutado += regi.getTr4Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr4Ejecutado());
                                    }

                                    if (idAnio) {
                                        eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                                    }

                                    hteEditar.appendChild(eltr);
                                    ++trimestre;
                                    ++idFila;
                                    ++aux;
                                    if (aux > 4) {
                                        aux = 1;
                                    }
                                }

                                CalResponsablesDAO rsArea = new CalResponsablesDAO();
                                Collection<CalResponsablesDTO> arr2 = rsArea.cargarCargosMeta(idMeta);
                                rsArea.close();
                                String script = "var mResponsables=new Array(" + arr2.size() + ");";
                                script = script + " var iContB=0;";

                                CalResponsablesDTO regD;
                                for(Iterator iteratorRes = arr2.iterator(); iteratorRes.hasNext(); script = script + " mResponsables[iContB++]=new add_area('" + regD.getCodigoCargo() + "','" + regD.getDescripcion() + "');") {
                                    regD = (CalResponsablesDTO)iteratorRes.next();
                                }

                                this.pagHTML.setTextResponsables("" + script);
                            }

                            SisUnidadesMedidaDAO dao2 = new SisUnidadesMedidaDAO();
                            SisUnidadesMedidaDTO regis = dao2.cargarRegistro(reg.getCodigoUnidad(), 0);
                            combo = this.pagHTML.getElementTipoUnidad();
                            this.llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "" + regis.getCodigoGrupo(), true);
                            combo = this.pagHTML.getElementCodigoUnidad();
                            this.llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=" + regis.getCodigoGrupo(), "" + reg.getCodigoUnidad(), true);
                            combo = this.pagHTML.getElementTipoMeta();
                            this.comboMultivalores(combo, "TIPO_META", "" + reg.getTipoMeta(), true);
                            this.pagHTML.getElementNomUnidad().setTextContent("" + regis.getNombreUnidad());
                            this.pagHTML.getElementNomUnidad2().setTextContent("" + regis.getNombreUnidad());
                        }
                    } else {
                        HTMLDivElement modal;
                        HTMLTableSectionElement hteEd;
                        if (metaCreada == 2) {
                            modal = this.pagHTML.getElementConsultaModalMetas();
                            modal.setAttribute("style", "display : block");
                            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
                            PdeMetasDTO reg = obMetas.cargarRegistro(idMeta);
                            new PdeNivelPlanDAO();
                            ultimo = obj.ultimoNivel(nivelDTO2.getIdNivel());
                            int anioFinal = 0;
                            int anioInicial = 0;
                            new FechaDTO();
                            new FechaDTO();
                            if (reg != null) {
                                FechaDTO fecha;
                                FechaDTO fecha2;
                                if (reg.getIdObjetivoEspecifico() == 0) {
                                    PdeUnidadNivelDAO objet = new PdeUnidadNivelDAO();
                                    PdeUnidadNivelDTO regis = objet.cargarRegistro(reg.getIdUnidadNivel(), 0, 0);
                                    PdeNivelPlanDAO obje = new PdeNivelPlanDAO();
                                    PdeNivelPlanDTO regN = obje.cargarRegistro(regis.getIdNivel(), 0, 0);
                                    PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
                                    PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
                                    fecha = new FechaDTO(reg2.getFechaInicial());
                                    fecha2 = new FechaDTO(reg2.getFechaFinal());
                                    if (reg2.getPlanCargado().equals("S")) {
                                        try {
                                            HTMLElement sele = this.pagHTML.getElementBtnModificar();
                                            sele.getParentNode().removeChild(sele);
                                        } catch (Exception var75) {
                                        }
                                    }
                                } else {
                                    PdeObjetivosEspecificosDAO obEsp = new PdeObjetivosEspecificosDAO();
                                    PdeObjetivosEspecificosDTO regOb = obEsp.cargarRegistro(reg.getIdObjetivoEspecifico(), 0);
                                    PdeUnidadNivelDAO objet = new PdeUnidadNivelDAO();
                                    PdeUnidadNivelDTO regis = objet.cargarRegistro(regOb.getIdUnidadNivel(), 0, 0);
                                    PdeNivelPlanDAO obje = new PdeNivelPlanDAO();
                                    PdeNivelPlanDTO regN = obje.cargarRegistro(regis.getIdNivel(), 0, 0);
                                    PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
                                    PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
                                    fecha = new FechaDTO(reg2.getFechaInicial());
                                    fecha2 = new FechaDTO(reg2.getFechaFinal());
                                    if (reg2.getPlanCargado().equals("S")) {
                                        try {
                                            HTMLElement sele = this.pagHTML.getElementBtnModificar();
                                            sele.getParentNode().removeChild(sele);
                                        } catch (Exception var74) {
                                        }
                                    }
                                }

                                this.pagHTML.setTextCodigoMetaEd("" + reg.getCodigoMeta());
                                SisUnidadesMedidaDAO objunidad = new SisUnidadesMedidaDAO();
                                SisUnidadesMedidaDTO registro = objunidad.cargarRegistro(reg.getCodigoUnidad(), 0);

                                try {
                                    this.pagHTML.setTextCodigoUnidadEd("" + registro.getNombreUnidad());
                                } catch (Exception var73) {
                                }

                                this.pagHTML.setTextNombreMetaEd("" + reg.getNombreMeta());
                                this.pagHTML.setTextTipoMetaEd("" + reg.getNombreTipoMeta());
                                this.pagHTML.setTextCantidadEd("" + reg.getCantidad());
                                this.pagHTML.setTextLineaBaseEd("" + reg.getLineaBase());
                                this.pagHTML.setTextIndicadorEd("" + reg.getIndicador());
                                this.pagHTML.setTextFechaInsercionEdMetas("" + reg.getFechaInsercion());
                                this.pagHTML.setTextUsuarioInsercionEdMetas("" + reg.getUsuarioInsercion());
                                this.pagHTML.setTextFechaModificacionEdMetas("" + reg.getFechaModificacion());
                                this.pagHTML.setTextUsuarioModificacionEdMetas("" + reg.getUsuarioModificacion());
                                PdeAniosDAO objeto = new PdeAniosDAO();
                                hteEd = this.pagHTML.getElementDetalleEd();
                                double consolidadoProgramado = 0.0D;
                                double consolidadoEjecutado = 0.0D;
                                HTMLTableCellElement elem0;
                                Collection arrEd;
                                Iterator iteratorEd;
                                if (!ultimo) {
                                    elem0 = this.pagHTML.getElementTrimestreEd();
                                    elem0.getParentNode().removeChild(elem0);
                                    HTMLElement elem = this.pagHTML.getElementTrProcesoEd();
                                    elem.getParentNode().removeChild(elem);
                                    elem = this.pagHTML.getElementAnioEd();
                                    elem.setAttribute("style", "width:25%");
                                    elem = this.pagHTML.getElementProgramadoEd();
                                    elem.setAttribute("style", "width:25%");
                                    elem = this.pagHTML.getElementEjecutadoEd();
                                    elem.setAttribute("style", "width:25%");
                                    elem = this.pagHTML.getElementPorcentajeEd();
                                    elem.setAttribute("style", "width:25%");
                                    arrEd = objeto.cargarTodos(idMeta, false);
                                    iteratorEd = arrEd.iterator();

                                    while(iteratorEd.hasNext()) {
                                        PdeAniosDTO regi = (PdeAniosDTO)iteratorEd.next();
                                        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                        eltr.appendChild(this.newtd("" + regi.getAnio()));
                                        eltr.appendChild(this.newtd("" + regi.getProgramado()));
                                        eltr.appendChild(this.newtd("" + regi.getEjecutado()));
                                        eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getProgramado(), regi.getEjecutado())));
                                        hteEd.appendChild(eltr);
                                    }

                                    arrEd.clear();
                                } else {
                                    elem0 = this.pagHTML.getElementTrimestreEd();
                                    elem0.setAttribute("style", "width:20%");
                                    elem0 = this.pagHTML.getElementAnioEd();
                                    elem0.setAttribute("style", "width:20%");
                                    elem0 = this.pagHTML.getElementProgramadoEd();
                                    elem0.setAttribute("style", "width:20%");
                                    elem0 = this.pagHTML.getElementEjecutadoEd();
                                    elem0.setAttribute("style", "width:20%");
                                    elem0 = this.pagHTML.getElementPorcentajeEd();
                                    elem0.setAttribute("style", "width:20%");
                                    this.pagHTML.setTextProcesoEd("" + reg.getNombreProceso());
                                    arrEd = objeto.cargarTodos(idMeta, true);
                                    iteratorEd = arrEd.iterator();
                                    idFila = 1;
                                    anioInicial = fecha.getAnno();
                                    anioFinal = fecha2.getAnno();
                                    int trimestre = 1;
                                    boolean idAnio = false;

                                    while(anioInicial <= anioFinal) {
                                        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                        PdeAniosDTO regi = new PdeAniosDTO();
                                        if (iteratorEd.hasNext()) {
                                            regi = (PdeAniosDTO)iteratorEd.next();
                                        }

                                        if (trimestre <= 4 && trimestre != 1) {
                                            eltr.appendChild(this.newtd(""));
                                            idAnio = false;
                                        } else {
                                            if (trimestre > 4) {
                                                ++anioInicial;
                                                if (anioInicial > anioFinal) {
                                                    eltr.appendChild(this.newtd("Consolidado"));
                                                    eltr.appendChild(this.newtd(""));
                                                    eltr.appendChild(this.newtd("" + consolidadoProgramado));
                                                    eltr.appendChild(this.newtd("" + consolidadoEjecutado));
                                                    eltr.appendChild(this.newtd("" + this.calcularPorcentaje(Double.toString(consolidadoProgramado), Double.toString(consolidadoEjecutado))));
                                                    hteEd.appendChild(eltr);
                                                    break;
                                                }
                                            }

                                            eltr.appendChild(this.newtd("" + anioInicial));
                                            idAnio = true;
                                            trimestre = 1;
                                        }

                                        eltr.appendChild(this.newtd("TR." + trimestre));
                                        switch(idFila) {
                                        case 1:
                                            eltr.appendChild(this.newtd("" + regi.getTr1Programado()));
                                            eltr.appendChild(this.newtd("" + regi.getTr1Ejecutado()));
                                            eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr1Programado(), regi.getTr1Ejecutado())));
                                            consolidadoProgramado += regi.getTr1Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr1Programado());
                                            consolidadoEjecutado += regi.getTr1Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr1Ejecutado());
                                            break;
                                        case 2:
                                            eltr.appendChild(this.newtd("" + regi.getTr2Programado()));
                                            eltr.appendChild(this.newtd("" + regi.getTr2Ejecutado()));
                                            eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr2Programado(), regi.getTr2Ejecutado())));
                                            consolidadoProgramado += regi.getTr2Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr2Programado());
                                            consolidadoEjecutado += regi.getTr2Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr2Ejecutado());
                                            break;
                                        case 3:
                                            eltr.appendChild(this.newtd("" + regi.getTr3Programado()));
                                            eltr.appendChild(this.newtd("" + regi.getTr3Ejecutado()));
                                            eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr3Programado(), regi.getTr3Ejecutado())));
                                            consolidadoProgramado += regi.getTr3Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr3Programado());
                                            consolidadoEjecutado += regi.getTr3Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr3Ejecutado());
                                            break;
                                        case 4:
                                            eltr.appendChild(this.newtd("" + regi.getTr4Programado()));
                                            eltr.appendChild(this.newtd("" + regi.getTr4Ejecutado()));
                                            eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getTr4Programado(), regi.getTr4Ejecutado())));
                                            consolidadoProgramado += regi.getTr4Programado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr4Programado());
                                            consolidadoEjecutado += regi.getTr4Ejecutado().equals("") ? 0.0D : this.limpiarNumero(regi.getTr4Ejecutado());
                                        }

                                        hteEd.appendChild(eltr);
                                        ++trimestre;
                                        ++idFila;
                                        if (idFila > 4) {
                                            idFila = 1;
                                        }
                                    }
                                }
                            }
                        } else {
                            modal = this.pagHTML.getElementCreacionModalMetas();
                            modal.setAttribute("style", "display : block");
                            combo = this.pagHTML.getElementTipoUnidad();
                            this.llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "", true);
                            combo = this.pagHTML.getElementTipoMeta();
                            this.comboMultivalores(combo, "TIPO_META", "", true);
                            combo = this.pagHTML.getElementProceso();
                            this.llenarCombo(combo, "procesos", "codigo", "descripcion", "1=1", "", true);
                            elScript = this.generarAreas();
                            this.pagHTML.setTextScriptAreas("" + elScript);
                            ultimo = false;
                            new PdePlanDesarrolloDTO();
                            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
                            ultimo = obj.ultimoNivel(nivelDTO2.getIdNivel());
                            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
                            PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(nivelDTO2.getIdPlanDesarrollo(), 0L);
                            int anioFinal = 0;
                            int anioInicial = 0;
                             idFila = 1;
                            FechaDTO fecha = new FechaDTO(reg2.getFechaInicial());
                            FechaDTO fecha2 = new FechaDTO(reg2.getFechaFinal());
                             anioInicial = fecha.getAnno();
                             anioFinal = fecha2.getAnno();
                            hteEd = this.pagHTML.getElementIdDetalleAnio();
                            HTMLTableCellElement elem;
                            if (!ultimo) {
                                elem = this.pagHTML.getElementTrimestre();
                                elem.getParentNode().removeChild(elem);
                                HTMLElement elem0 = this.pagHTML.getElementTrProceso();
                                elem0.getParentNode().removeChild(elem);
                                elem0 = this.pagHTML.getElementTrResponsable();
                                elem0.getParentNode().removeChild(elem);
                                elem0 = this.pagHTML.getElementAnio();
                                elem0.setAttribute("style", "width:25%");
                                elem0 = this.pagHTML.getElementProgramado();
                                elem0.setAttribute("style", "width:25%");
                                elem0 = this.pagHTML.getElementEjecutado();
                                elem0.setAttribute("style", "width:25%");
                                elem0 = this.pagHTML.getElementPorcentaje();
                                elem.setAttribute("style", "width:25%");

                                while(anioInicial <= anioFinal) {
                                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    eltr.appendChild(this.newtdAnio("" + anioInicial));
                                    eltr.appendChild(this.newinput(idFila, ""));
                                    eltr.appendChild(this.newinputEjecutado(idFila, ""));
                                    eltr.appendChild(this.newtd(""));
                                    eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                                    hteEd.appendChild(eltr);
                                    ++anioInicial;
                                    ++idFila;
                                }
                            } else {
                                elem = this.pagHTML.getElementTrimestre();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementAnio();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementProgramado();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementEjecutado();
                                elem.setAttribute("style", "width:20%");
                                elem = this.pagHTML.getElementPorcentaje();
                                elem.setAttribute("style", "width:20%");
                                idFila = 1;

                                for(boolean idAnio = false; anioInicial <= anioFinal; ++idFila) {
                                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                                    if (idFila <= 4 && idFila != 1) {
                                        eltr.appendChild(this.newtdAnio(""));
                                        idAnio = false;
                                    } else {
                                        if (idFila > 4) {
                                            ++anioInicial;
                                            if (anioInicial > anioFinal) {
                                                break;
                                            }
                                        }

                                        eltr.appendChild(this.newtdAnio("" + anioInicial));
                                        idAnio = true;
                                        idFila = 1;
                                    }

                                    eltr.appendChild(this.newtdAnio("TR." + idFila));
                                    eltr.appendChild(this.newinput(idFila, ""));
                                    eltr.appendChild(this.newinputEjecutado(idFila, ""));
                                    eltr.appendChild(this.newtd(""));
                                    if (idAnio) {
                                        eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                                    }

                                    hteEd.appendChild(eltr);
                                    ++idFila;
                                }
                            }

                            this.pagHTML.getElementIdMeta().setReadOnly(true);
                            this.pagHTML.getElementIdMeta().setValue("0");
                        }
                    }
                }
            }

        }
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

    private HTMLElement newimg(int idNivel, String nombreNivelGenerico, String nombreNivel, String codigoNivel, String objetivoGeneral, String fechaInsercion, String fechaModificacion, String usuarioInsercion, String usuarioModificacion, String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        HTMLElement img = (HTMLElement)this.pagHTML.createElement("img");
        img.setAttribute("src", "media/Consultar.png");
        img.setAttribute("height", "30");
        img.setAttribute("width", "150");
        img.setAttribute("style", "margin:0px -20px 0px 5%; cursor : pointer");
        img.setAttribute("onclick", "consultarNivelPrimero(" + idNivel + ")");
        HTMLElement img2 = (HTMLElement)this.pagHTML.createElement("img");
        img2.setAttribute("src", "media/Editar1.png");
        img2.setAttribute("height", "30");
        img2.setAttribute("width", "150");
        img2.setAttribute("style", "margin:0px -20px 0px 6%; cursor : pointer");
        img2.setAttribute("onclick", "editarNivel(" + idNivel + "," + "" + nombreNivelGenerico + "," + "" + nombreNivel + "," + "" + codigoNivel + "," + "" + objetivoGeneral + "," + "" + fechaInsercion + "," + fechaModificacion + "," + usuarioInsercion + "," + usuarioModificacion + ")");
        HTMLElement img3 = (HTMLElement)this.pagHTML.createElement("img");
        img3.setAttribute("src", "media/Eliminar.png");
        img3.setAttribute("height", "30");
        img3.setAttribute("width", "150");
        img3.setAttribute("style", "margin:0px -20px 0px 6%; cursor : pointer");
        img3.setAttribute("onclick", "Eliminar(" + idNivel + ")");
        if (contenido.equals("metas")) {
            img.setAttribute("onclick", "ConsultarMeta(" + idNivel + ")");
            img2.setAttribute("onclick", "EditarMeta(" + idNivel + ")");
            img3.setAttribute("onclick", "EliminarMeta(" + idNivel + ")");
        }

        td.appendChild(img);
        td.appendChild(img2);
        td.appendChild(img3);
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
