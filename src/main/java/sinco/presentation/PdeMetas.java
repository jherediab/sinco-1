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
import java.util.Enumeration;
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
import sinco.business.SisEntidadDTO;
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
import sinco.data.SisEntidadDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.SisUnidadesMedidaDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Varios;

public class PdeMetas implements HttpPresentation {
    private PdeMetasHTML pagHTML;

    public PdeMetas() {
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

            this.pagHTML = (PdeMetasHTML)comms.xmlcFactory.create(PdeMetasHTML.class);
            this.permisos(comms);
            int idNivel = 0;

            try {
                idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
            } catch (Exception var20) {
            }

            int idUnidadNivel = 0;

            try {
                idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
            } catch (Exception var19) {
            }

            int idObjetivoEspecifico = 0;

            try {
                idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
            } catch (Exception var18) {
            }

            if (idObjetivoEspecifico > 0) {
                PdeObjetivosEspecificosDAO o = new PdeObjetivosEspecificosDAO();
                PdeObjetivosEspecificosDTO re = o.cargarRegistro(idObjetivoEspecifico, 0);
                this.pagHTML.getElementObjetivoEspecificoNivel().setTextContent("Objetivo Específico: " + re.getDescripcionObjetivoEspecifico());
                if (idUnidadNivel == 0) {
                    idUnidadNivel = re.getIdUnidadNivel();
                }
            } else {
                try {
                    HTMLElement sel = this.pagHTML.getElementObjEspecificoRefe();
                    sel.getParentNode().removeChild(sel);
                } catch (Exception var17) {
                }
            }

            if (idUnidadNivel > 0) {
                PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
                PdeUnidadNivelDTO reg = ob.cargarRegistro(idUnidadNivel, 0, 0);
                PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
                PdeNivelPlanDTO regN = obj.cargarRegistro(reg.getIdNivel(), 0, 0);
                PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
                PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
                SisEntidadDAO obj3 = new SisEntidadDAO();
                SisEntidadDTO reg3 = obj3.cargarRegistro(reg2.getNitEntidad());
                if (reg2.getPlanCargado().equals("S")) {
                    try {
                        HTMLElement sel = this.pagHTML.getElementBtnCrear();
                        sel.getParentNode().removeChild(sel);
                    } catch (Exception var16) {
                    }
                }

                this.pagHTML.getElementNombrePlan().setTextContent(" " + reg2.getNombrePlanDesarrollo());
                this.pagHTML.getElementNombreEntidad().setTextContent(" " + reg3.getNombre());
                this.pagHTML.getElementNombreNivel().setTextContent(regN.getNombreNivel() + ":   " + reg.getCodigoUnidad() + " - " + reg.getNombreUnidad());
                this.pagHTML.getElementObjetivoGeneralNivel().setTextContent("Objetivo General: " + reg.getObjetivoGeneral());
            }

            this.pagHTML.getElementIdNivelHidden().setValue("" + idNivel);
            this.pagHTML.getElementIdUnidadNivelHidden().setValue("" + idUnidadNivel);
            this.pagHTML.getElementIdObjetivoEspecificoHidden().setValue("" + idObjetivoEspecifico);
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

        int idMeta;
        try {
            idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
        } catch (Exception var50) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idMeta"));
        }

        boolean var5 = false;

        int idUnidadNivel;
        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var49) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idUnidadNivel"));
        }

        int idObjetivoEspecifico = 0;

        try {
            idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
        } catch (Exception var48) {
        }

        int criterio1 = 0;

        try {
            criterio1 = Integer.parseInt(comms.request.getParameter("criterio1"));
        } catch (Exception var47) {
        }

        int criterio2 = 0;

        try {
            criterio2 = Integer.parseInt(comms.request.getParameter("criterio2"));
        } catch (Exception var46) {
        }

        int criterio3 = 0;

        try {
            criterio3 = Integer.parseInt(comms.request.getParameter("criterio3"));
        } catch (Exception var45) {
        }

        int criterio4 = 0;

        try {
            criterio4 = Integer.parseInt(comms.request.getParameter("criterio4"));
        } catch (Exception var44) {
        }

        int criterio1Todo = 0;

        try {
            criterio1Todo = Integer.parseInt(comms.request.getParameter("criterio1Todo"));
        } catch (Exception var43) {
        }

        int criterio2Todo = 0;

        try {
            criterio2Todo = Integer.parseInt(comms.request.getParameter("criterio2Todo"));
        } catch (Exception var42) {
        }

        int criterio3Todo = 0;

        try {
            criterio3Todo = Integer.parseInt(comms.request.getParameter("criterio3Todo"));
        } catch (Exception var41) {
        }

        int criterio4Todo = 0;

        try {
            criterio4Todo = Integer.parseInt(comms.request.getParameter("criterio4Todo"));
        } catch (Exception var40) {
        }

        PdeUnidadNivelDAO unidadDAO = new PdeUnidadNivelDAO();
        PdeUnidadNivelDTO unidadDTO = unidadDAO.cargarRegistro(idUnidadNivel, 0, 0);
        PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
        PdeNivelPlanDTO nivelDTO = obj.cargarRegistro(unidadDTO.getIdNivel(), 0, 0);
        int idPlanDesarrollo = nivelDTO.getIdPlanDesarrollo();
        int idNivel = unidadDTO.getIdNivel();
        int metaCreada = 0;
        boolean ultimo = obj.ultimoNivel(idNivel);
        String codigoUnidad = comms.request.getParameter("codigoUnidad");
        if (codigoUnidad == null) {
            codigoUnidad = "";
        }

        new RespuestaBD();
        RespuestaBD rta2 = new RespuestaBD();
        new RespuestaBD();
        RespuestaBD rta;
        String nombreMeta;
        if (_operacion.equals("E")) {
            PdeAniosDAO reg = new PdeAniosDAO();
            rta = reg.eliminarRegistro(idMeta);
            if (rta.isRta()) {
                PdeMetasDAO ob = new PdeMetasDAO();
                RespuestaBD rta3 = ob.eliminarResponsables(idMeta);
                if (!rta3.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                }

                rta2 = ob.eliminarRegistro(idMeta);
                if (!rta2.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                }
            }

            if (!rta.isRta()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetas&p1=" + rta.getMensaje()));
            } else {
                nombreMeta = "PdeNivelPlan.po?_operacion=LL&idUnidadNivel=" + idUnidadNivel + "&idPlanDesarrollo=" + idPlanDesarrollo + "&metaCreada=" + metaCreada + "&criterio1=" + criterio1 + "&criterio1Todo=" + criterio1Todo + "&criterio2=" + criterio2 + "&criterio2Todo=" + criterio2Todo + "&criterio3=" + criterio3 + "&criterio3Todo=" + criterio3Todo + "&criterio4=" + criterio4 + "&criterio4Todo=" + criterio4Todo;
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath(nombreMeta));
            }
        } else {
            String codigoMeta = comms.request.getParameter("codigoMeta");
            nombreMeta = comms.request.getParameter("nombreMeta");
            String indicador = comms.request.getParameter("indicador");
            String lineaBase = comms.request.getParameter("lineaBase");
            String cantidad = comms.request.getParameter("cantidad");
            String tipoMeta = comms.request.getParameter("tipoMeta");
            ArrayList resultados;
            Enumeration enumera;
            String proceso;
            PdeAniosDTO reg;
            int idFila;
            int numeroAnio;
            if (!ultimo) {
                resultados = new ArrayList();
                enumera = comms.request.getParameterNames();

                while(enumera.hasMoreElements()) {
                    proceso = (String)enumera.nextElement();
                    if (proceso.startsWith("_S_")) {
                        reg = new PdeAniosDTO();
                        idFila = Integer.parseInt(proceso.substring(3));
                        numeroAnio = Integer.parseInt(comms.request.getParameter("_S_" + idFila));
                        if (numeroAnio > 0) {
                            reg.setAnio(Integer.parseInt(comms.request.getParameter("_S_" + idFila)));
                            reg.setProgramado(comms.request.getParameter("_E_" + idFila));
                            reg.setEjecutado(comms.request.getParameter("_J_" + idFila));
                            resultados.add(reg);
                        }
                    }
                }

                PdeMetasDAO ob = new PdeMetasDAO();
                //PdeAniosDAO reg;
                Iterator iterator;
                PdeAniosDTO regDet;
                if (_operacion.equals("C")) {
                    rta = ob.crearRegistro(idMeta, codigoMeta, tipoMeta, idUnidadNivel, idObjetivoEspecifico, "", codigoUnidad, nombreMeta, cantidad, lineaBase, indicador, elUsuario);
                    idMeta = rta.getSecuencia();
                    if (rta.isRta()) {
                       PdeAniosDAO reg0 = new PdeAniosDAO();
                        iterator = resultados.iterator();

                        while(iterator.hasNext()) {
                            regDet = (PdeAniosDTO)iterator.next();
                            rta2 = reg0.crearRegistro(0, idMeta, regDet.getAnio(), regDet.getProgramado(), regDet.getEjecutado(), elUsuario);
                            if (!rta2.isRta()) {
                                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                            }
                        }
                    }
                } else {
                    rta = ob.modificarRegistro(idMeta, codigoMeta, tipoMeta, idUnidadNivel, idObjetivoEspecifico, "", codigoUnidad, nombreMeta, cantidad, lineaBase, indicador, elUsuario);
                    if (rta.isRta()) {
                       PdeAniosDAO reg0 = new PdeAniosDAO();
                        iterator = resultados.iterator();

                        while(iterator.hasNext()) {
                            regDet = (PdeAniosDTO)iterator.next();
                            rta2 = reg0.modificarRegistro(idMeta, regDet.getAnio(), regDet.getProgramado(), regDet.getEjecutado(), elUsuario);
                            if (!rta2.isRta()) {
                                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                            }
                        }
                    }
                }

                if (!rta.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetas&p1=" + rta.getMensaje()));
                }
            } else {
                resultados = new ArrayList();
                enumera = comms.request.getParameterNames();

                while(enumera.hasMoreElements()) {
                    proceso = (String)enumera.nextElement();
                    if (proceso.startsWith("_S_")) {
                        reg = new PdeAniosDTO();
                        idFila = Integer.parseInt(proceso.substring(3));
                        numeroAnio = Integer.parseInt(comms.request.getParameter("_S_" + idFila));
                        if (numeroAnio > 0) {
                            reg.setAnio(Integer.parseInt(comms.request.getParameter("_S_" + idFila)));
                            reg.setTr1Programado(comms.request.getParameter("_E_" + idFila));
                            reg.setTr1Ejecutado(comms.request.getParameter("_J_" + idFila));
                            ++idFila;
                            reg.setTr2Programado(comms.request.getParameter("_E_" + idFila));
                            reg.setTr2Ejecutado(comms.request.getParameter("_J_" + idFila));
                            ++idFila;
                            reg.setTr3Programado(comms.request.getParameter("_E_" + idFila));
                            reg.setTr3Ejecutado(comms.request.getParameter("_J_" + idFila));
                            ++idFila;
                            reg.setTr4Programado(comms.request.getParameter("_E_" + idFila));
                            reg.setTr4Ejecutado(comms.request.getParameter("_J_" + idFila));
                            resultados.add(reg);
                        }
                    }
                }

                proceso = comms.request.getParameter("proceso");
                if (proceso == null) {
                    proceso = "";
                }

                PdeMetasDAO ob = new PdeMetasDAO();
                PdeAniosDTO regDet;
                PdeAniosDAO reg0;
                Iterator iterator;
                if (_operacion.equals("C")) {
                    rta = ob.crearRegistro(idMeta, codigoMeta, tipoMeta, idUnidadNivel, idObjetivoEspecifico, proceso, codigoUnidad, nombreMeta, cantidad, lineaBase, indicador, elUsuario);
                    idMeta = rta.getSecuencia();
                    if (rta.isRta()) {
                        reg0 = new PdeAniosDAO();
                        iterator = resultados.iterator();

                        while(iterator.hasNext()) {
                            regDet = (PdeAniosDTO)iterator.next();
                            rta2 = reg0.crearRegistroUltimoNivel(0, idMeta, regDet.getAnio(), regDet.getTr1Programado(), regDet.getTr1Ejecutado(), regDet.getTr2Programado(), regDet.getTr2Ejecutado(), regDet.getTr3Programado(), regDet.getTr3Ejecutado(), regDet.getTr4Programado(), regDet.getTr4Ejecutado(), elUsuario);
                            if (!rta2.isRta()) {
                                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                            }
                        }
                    }
                } else {
                    rta = ob.modificarRegistro(idMeta, codigoMeta, tipoMeta, idUnidadNivel, idObjetivoEspecifico, proceso, codigoUnidad, nombreMeta, cantidad, lineaBase, indicador, elUsuario);
                    if (rta.isRta()) {
                        reg0 = new PdeAniosDAO();
                        iterator = resultados.iterator();

                        while(iterator.hasNext()) {
                            regDet = (PdeAniosDTO)iterator.next();
                            rta2 = reg0.modificarRegistroUltimoNivel(idMeta, regDet.getAnio(), regDet.getTr1Programado(), regDet.getTr1Ejecutado(), regDet.getTr2Programado(), regDet.getTr2Ejecutado(), regDet.getTr3Programado(), regDet.getTr3Ejecutado(), regDet.getTr4Programado(), regDet.getTr4Ejecutado(), elUsuario);
                            if (!rta2.isRta()) {
                                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetasAnios&p1=" + rta2.getMensaje()));
                            }
                        }
                    }
                }

                if (!rta.isRta()) {
                    throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeMetas&p1=" + rta.getMensaje()));
                }

                Enumeration enumera2 = comms.request.getParameterNames();
                ob.eliminarResponsables(idMeta);

                while(enumera2.hasMoreElements()) {
                    String param = (String)enumera2.nextElement();
                    if (param.substring(0, 3).equals("ar_")) {
                        String cargo = comms.request.getParameter(param);
                        if (cargo.length() > 0) {
                            ob.crearResponsable(idMeta, cargo, elUsuario);
                        }
                    }
                }
            }

            String sPagina = "PdeNivelPlan.po?_operacion=LL&idUnidadNivel=" + idUnidadNivel + "&idPlanDesarrollo=" + idPlanDesarrollo + "&metaCreada=" + metaCreada + "&criterio1=" + criterio1 + "&criterio1Todo=" + criterio1Todo + "&criterio2=" + criterio2 + "&criterio2Todo=" + criterio2Todo + "&criterio3=" + criterio3 + "&criterio3Todo=" + criterio3Todo + "&criterio4=" + criterio4 + "&criterio4Todo=" + criterio4Todo;
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
        }
    }

    private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");
        int idMeta = 0;

        try {
            idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
        } catch (Exception var42) {
        }

        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var41) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var40) {
        }

        String elScript = this.generarAreas();
        this.pagHTML.setTextScriptAreas("" + elScript);
        PdeMetasDAO ob = new PdeMetasDAO();
        PdeNivelPlanDAO objNivel = new PdeNivelPlanDAO();
        boolean ultimo = objNivel.ultimoNivel(idNivel);
        PdeMetasDTO reg = ob.cargarRegistro(idMeta);
        if (reg != null) {
            this.pagHTML.getElementIdMeta().setValue("" + reg.getIdMeta());
            this.pagHTML.getElementCodigoMeta().setValue("" + reg.getCodigoMeta());
            this.pagHTML.getElementNombreMeta().setValue("" + reg.getNombreMeta());
            this.pagHTML.getElementCantidad().setValue("" + reg.getCantidad());
            this.pagHTML.getElementLineaBase().setValue("" + reg.getLineaBase());
            this.pagHTML.getElementIndicador().setValue("" + reg.getIndicador());
            this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
            this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
            this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
            this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
            PdeUnidadNivelDAO objeto = new PdeUnidadNivelDAO();
            PdeUnidadNivelDTO registro = objeto.cargarRegistro(idUnidadNivel, 0, 0);
            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(registro.getIdNivel(), 0, 0);
            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
            PdePlanDesarrolloDTO reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
            int anioFinal = 0;
            int anioInicial = 0;
            int idFila = 1;
            int aux = 1;
            FechaDTO fecha = new FechaDTO(reg2.getFechaInicial());
            FechaDTO fecha2 = new FechaDTO(reg2.getFechaFinal());
            anioInicial = fecha.getAnno();
            anioFinal = fecha2.getAnno();
            HTMLTableSectionElement hte = this.pagHTML.getElementIdDetalleAnio();
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
                    hte.appendChild(eltr);
                    ++anioInicial;
                    ++idFila;
                }
            } else {
                PdeAniosDAO anio = new PdeAniosDAO();
                Collection<PdeAniosDTO> arr = anio.cargarTodos(idMeta, true);
                Iterator<PdeAniosDTO> iterator = arr.iterator();
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
                HTMLSelectElement combo = this.pagHTML.getElementProceso();
                this.llenarCombo(combo, "procesos", "codigo", "descripcion", "1=1", "" + reg.getProceso(), true);
                int trimestre = 1;
                boolean idAnio = false;

                while(anioInicial <= anioFinal) {
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    PdeAniosDTO regi = new PdeAniosDTO();
                    if (iterator.hasNext()) {
                        regi = (PdeAniosDTO)iterator.next();
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
                                hte.appendChild(eltr);
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

                    hte.appendChild(eltr);
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
            HTMLSelectElement combo = this.pagHTML.getElementTipoUnidad();
            this.llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "" + regis.getCodigoGrupo(), true);
            combo = this.pagHTML.getElementCodigoUnidad();
            this.llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=" + regis.getCodigoGrupo(), "" + reg.getCodigoUnidad(), true);
            combo = this.pagHTML.getElementTipoMeta();
            this.comboMultivalores(combo, "TIPO_META", "" + reg.getTipoMeta(), true);
            this.pagHTML.getElementNomUnidad().setTextContent("" + regis.getNombreUnidad());
            this.pagHTML.getElementNomUnidad2().setTextContent("" + regis.getNombreUnidad());
            this.pagHTML.getElementIdMeta().setReadOnly(true);
            this.pagHTML.getElementIdMetaKey().setValue("" + idMeta);
        }

        this.pagHTML.getElement_operacion().setValue("M");
        this.activarVista("nuevo");
    }

    private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");
        this.pagHTML.getElement_operacion().setValue("C");

        try {
            HTMLElement sel = this.pagHTML.getElementBtnEliminar();
            sel.getParentNode().removeChild(sel);
        } catch (Exception var21) {
        }

        this.activarVista("nuevo");
        HTMLSelectElement combo = this.pagHTML.getElementTipoUnidad();
        this.llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "", true);
        combo = this.pagHTML.getElementTipoMeta();
        this.comboMultivalores(combo, "TIPO_META", "", true);
        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var20) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var19) {
        }

        combo = this.pagHTML.getElementProceso();
        this.llenarCombo(combo, "procesos", "codigo", "descripcion", "1=1", "", true);
        String elScript = this.generarAreas();
        this.pagHTML.setTextScriptAreas("" + elScript);
        boolean ultimo = false;
        PdePlanDesarrolloDTO reg2 = new PdePlanDesarrolloDTO();
        if (idUnidadNivel > 0) {
            PdeUnidadNivelDAO ob = new PdeUnidadNivelDAO();
            PdeUnidadNivelDTO reg = ob.cargarRegistro(idUnidadNivel, 0, 0);
            PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
            PdeNivelPlanDTO regN = obj.cargarRegistro(reg.getIdNivel(), 0, 0);
            ultimo = obj.ultimoNivel(idNivel);
            PdePlanDesarrolloDAO obj2 = new PdePlanDesarrolloDAO();
            reg2 = obj2.cargarRegistro(regN.getIdPlanDesarrollo(), 0L);
        }

        int anioFinal = 0;
        int anioInicial = 0;
        int idFila = 1;
        FechaDTO fecha = new FechaDTO(reg2.getFechaInicial());
        FechaDTO fecha2 = new FechaDTO(reg2.getFechaFinal());
        anioInicial = fecha.getAnno();
        anioFinal = fecha2.getAnno();
        HTMLTableSectionElement hte = this.pagHTML.getElementIdDetalleAnio();
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
            elem0.setAttribute("style", "width:25%");

            while(anioInicial <= anioFinal) {
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtdAnio("" + anioInicial));
                eltr.appendChild(this.newinput(idFila, ""));
                eltr.appendChild(this.newinputEjecutado(idFila, ""));
                eltr.appendChild(this.newtd(""));
                eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                hte.appendChild(eltr);
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
            int trimestre = 1;

            for(boolean idAnio = false; anioInicial <= anioFinal; ++idFila) {
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                if (trimestre <= 4 && trimestre != 1) {
                    eltr.appendChild(this.newtdAnio(""));
                    idAnio = false;
                } else {
                    if (trimestre > 4) {
                        ++anioInicial;
                        if (anioInicial > anioFinal) {
                            break;
                        }
                    }

                    eltr.appendChild(this.newtdAnio("" + anioInicial));
                    idAnio = true;
                    trimestre = 1;
                }

                eltr.appendChild(this.newtdAnio("TR." + trimestre));
                eltr.appendChild(this.newinput(idFila, ""));
                eltr.appendChild(this.newinputEjecutado(idFila, ""));
                eltr.appendChild(this.newtd(""));
                if (idAnio) {
                    eltr.appendChild(this.newtdAnioHidden("" + anioInicial, idFila));
                }

                hte.appendChild(eltr);
                ++trimestre;
            }
        }

        this.pagHTML.getElementIdMeta().setReadOnly(true);
        this.pagHTML.getElementIdMeta().setValue("0");
    }

    private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
        this.activarVista("consulta");
        HTMLElement sel = this.pagHTML.getElementDivConsulta();
        sel.getParentNode().removeChild(sel);
        int idUnidadNivel = 0;

        try {
            idUnidadNivel = Integer.parseInt(comms.request.getParameter("idUnidadNivel"));
        } catch (Exception var17) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var16) {
        }

        int idObjetivoEspecifico = 0;

        try {
            idObjetivoEspecifico = Integer.parseInt(comms.request.getParameter("idObjetivoEspecifico"));
        } catch (Exception var15) {
        }

        if (!_operacion.equals("X")) {
            if (idObjetivoEspecifico > 0) {
                idUnidadNivel = 0;
            } else {
                HTMLElement sele = this.pagHTML.getElementBtnVolver();
                sele.setAttribute("onClick", "PreparaVolver2()");
            }

            PdeMetasDAO ob = new PdeMetasDAO();
            Collection<PdeMetasDTO> arr = ob.cargarTodos(idUnidadNivel, idObjetivoEspecifico);
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
            int cuantas = 0;

            for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
                PdeMetasDTO reg = (PdeMetasDTO)iterator.next();
                HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                eltr.appendChild(this.newtd("" + reg.getCodigoMeta()));
                String url = "PdeMetas.po?_operacion=V&idMeta=" + reg.getIdMeta() + "&idUnidadNivel=" + reg.getIdUnidadNivel() + "&idObjetivoEspecifico=" + reg.getIdObjetivoEspecifico() + "&codigoUnidad=" + reg.getCodigoUnidad() + "&idNivel=" + idNivel;
                eltr.appendChild(this.newtdhref("" + reg.getNombreMeta(), url));
                eltr.appendChild(this.newtd("" + reg.getNombreUnidad()));
                eltr.appendChild(this.newtd("" + reg.getCantidad()));
                eltr.appendChild(this.newtd("" + reg.getLineaBase()));
                hte.appendChild(eltr);
            }

            arr.clear();
            this.pagHTML.setTextNroRegistros("" + cuantas);
        }
    }

    private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        HTMLElement sele = this.pagHTML.getElementBtnVolver();
        sele.setAttribute("onClick", "CambiarVolver()");
        int idMeta = 0;

        try {
            idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
        } catch (Exception var32) {
        }

        int idNivel = 0;

        try {
            idNivel = Integer.parseInt(comms.request.getParameter("idNivel"));
        } catch (Exception var31) {
        }

        PdeMetasDAO ob = new PdeMetasDAO();
        PdeMetasDTO reg = ob.cargarRegistro(idMeta);
        PdeNivelPlanDAO objPlan = new PdeNivelPlanDAO();
        boolean ultimo = objPlan.ultimoNivel(idNivel);
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
                        HTMLElement sel = this.pagHTML.getElementBtnModificar();
                        sel.getParentNode().removeChild(sel);
                    } catch (Exception var30) {
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
                        HTMLElement sel = this.pagHTML.getElementBtnModificar();
                        sel.getParentNode().removeChild(sel);
                    } catch (Exception var29) {
                    }
                }
            }

            this.pagHTML.setTextCodigoMetaEd("" + reg.getCodigoMeta());
            SisUnidadesMedidaDAO obj = new SisUnidadesMedidaDAO();
            SisUnidadesMedidaDTO registro = obj.cargarRegistro(reg.getCodigoUnidad(), 0);
            this.pagHTML.setTextCodigoUnidadEd("" + registro.getNombreUnidad());
            this.pagHTML.setTextNombreMetaEd("" + reg.getNombreMeta());
            this.pagHTML.setTextTipoMetaEd("" + reg.getNombreTipoMeta());
            this.pagHTML.setTextCantidadEd("" + reg.getCantidad());
            this.pagHTML.setTextLineaBaseEd("" + reg.getLineaBase());
            this.pagHTML.setTextIndicadorEd("" + reg.getIndicador());
            this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
            this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
            this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
            this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
            PdeAniosDAO objeto = new PdeAniosDAO();
            HTMLTableSectionElement hte = this.pagHTML.getElementDetalleEd();
            double consolidadoProgramado = 0.0D;
            double consolidadoEjecutado = 0.0D;
            Collection arr;
            Iterator iterator;
            HTMLTableCellElement elem;
            if (!ultimo) {
                elem = this.pagHTML.getElementTrimestreEd();
                elem.getParentNode().removeChild(elem);
                HTMLElement elem0 = this.pagHTML.getElementTrProcesoEd();
                elem0.getParentNode().removeChild(elem);
                elem0 = this.pagHTML.getElementAnioEd();
                elem0.setAttribute("style", "width:25%");
                elem0 = this.pagHTML.getElementProgramadoEd();
                elem0.setAttribute("style", "width:25%");
                elem0 = this.pagHTML.getElementEjecutadoEd();
                elem0.setAttribute("style", "width:25%");
                elem0 = this.pagHTML.getElementPorcentajeEd();
                elem0.setAttribute("style", "width:25%");
                arr = objeto.cargarTodos(idMeta, false);
                iterator = arr.iterator();

                while(iterator.hasNext()) {
                    PdeAniosDTO regi = (PdeAniosDTO)iterator.next();
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    eltr.appendChild(this.newtd("" + regi.getAnio()));
                    eltr.appendChild(this.newtd("" + regi.getProgramado()));
                    eltr.appendChild(this.newtd("" + regi.getEjecutado()));
                    eltr.appendChild(this.newtd("" + this.calcularPorcentaje(regi.getProgramado(), regi.getEjecutado())));
                    hte.appendChild(eltr);
                }

                arr.clear();
            } else {
                elem = this.pagHTML.getElementTrimestreEd();
                elem.setAttribute("style", "width:20%");
                elem = this.pagHTML.getElementAnioEd();
                elem.setAttribute("style", "width:20%");
                elem = this.pagHTML.getElementProgramadoEd();
                elem.setAttribute("style", "width:20%");
                elem = this.pagHTML.getElementEjecutadoEd();
                elem.setAttribute("style", "width:20%");
                elem = this.pagHTML.getElementPorcentajeEd();
                elem.setAttribute("style", "width:20%");
                this.pagHTML.setTextProcesoEd("" + reg.getNombreProceso());
                arr = objeto.cargarTodos(idMeta, true);
                iterator = arr.iterator();
                int idFila = 1;
                anioInicial = fecha.getAnno();
                anioFinal = fecha2.getAnno();
                int trimestre = 1;
                boolean idAnio = false;

                while(anioInicial <= anioFinal) {
                    HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
                    PdeAniosDTO regi = new PdeAniosDTO();
                    if (iterator.hasNext()) {
                        regi = (PdeAniosDTO)iterator.next();
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
                                hte.appendChild(eltr);
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

                    hte.appendChild(eltr);
                    ++trimestre;
                    ++idFila;
                    if (idFila > 4) {
                        idFila = 1;
                    }
                }
            }

            this.pagHTML.getElementIdMetaKey().setValue("" + reg.getIdMeta());
            this.pagHTML.getElementIdUnidadNivelKey().setValue("" + reg.getIdUnidadNivel());
            this.pagHTML.getElementIdNivelKey().setValue("" + idNivel);
            this.pagHTML.getElementIdObjetivoEspecificoKey().setValue("" + reg.getIdObjetivoEspecifico());
            this.pagHTML.getElement_operacion().setValue("P");
        }

        this.activarVista("editar");
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

    private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        Varios oVarios = new Varios();
        boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeMetasAct");
        boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeMetasDel");
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
        td.setAttributeNode(this.newAttr("style", "text-align:center;"));
        return td;
    }

    private HTMLElement newtdAnio(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "tit"));
        td.setAttributeNode(this.newAttr("style", "text-align:center;"));
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
        for(int x = 0; x < numero.length(); ++x) {
            if (Character.isDigit(numero.charAt(x))) {
                numero = numero.substring(x);
                break;
            }
        }

        return Double.parseDouble(numero);
    }
}
