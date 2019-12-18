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
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTextAreaElement;
import sinco.business.AMAccionesDTO;
import sinco.business.AMCausasDTO;
import sinco.business.AMSeguimientoDTO;
import sinco.business.ArchivosSolicitudDTO;
import sinco.business.AreasDTO;
import sinco.business.ParametrosDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.TGeneralDTO;
import sinco.business.Utilidades;
import sinco.data.AMAccionesDAO;
import sinco.data.AMCausasDAO;
import sinco.data.AMSeguimientoDAO;
import sinco.data.ArchivosSolicitudDAO;
import sinco.data.AreasDAO;
import sinco.data.SeguridadDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.TGeneralDAO;
import sinco.spec.MenuDO;
import sinco.spec.Utilidades2;

public class AMDetalleV2 implements HttpPresentation {
    private AMDetalleV2HTML pagHTML;
    boolean cerrar = true;

    public AMDetalleV2() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            boolean var3 = false;

            int numero;
            try {
                numero = Integer.parseInt(comms.request.getParameter("numero"));
            } catch (Exception var5) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
            }

            this.pagHTML = (AMDetalleV2HTML)comms.xmlcFactory.create(AMDetalleV2HTML.class);
            this.detallaAccion(comms, numero);
            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void detallaAccion(HttpPresentationComms comms, int numero) throws HttpPresentationException, KeywordValueException {
        int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        AMAccionesDAO rsAcciones = new AMAccionesDAO();
        AMAccionesDTO regAccion = rsAcciones.cargarRegistro(numero);
        if (regAccion == null) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AccionesMejoramiento"));
        } else {
            int lectura = 0;

            try {
                lectura = Integer.parseInt(comms.request.getParameter("lectura"));
            } catch (Exception var28) {
            }

            SisUsuariosDAO pf = new SisUsuariosDAO();
            SisUsuariosDTO elNavegante = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
            SeguridadDAO rsSeguridad = new SeguridadDAO();
            if (!rsSeguridad.getEstadoConexion()) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
            } else {
                boolean permisoCalificarEnArea = rsSeguridad.tieneLlave(miGrupo, "AM_PermisoCalificarEnArea");
                boolean permisoCalificarGeneral = rsSeguridad.tieneLlave(miGrupo, "AM_PermisoCalificarGeneral");
                boolean permisoVerBitacora = rsSeguridad.tieneLlave(miGrupo, "oVerBitacora");
                boolean permisoModificar = rsSeguridad.tieneLlave(miGrupo, "AM_Modificar");
                boolean permisoCerrar = rsSeguridad.tieneLlave(miGrupo, "AM_Finalizar");
                boolean permisoAgregarCausas = rsSeguridad.tieneLlave(miGrupo, "AM_AgregarCausas");
                boolean permisoCerrarEnArea = rsSeguridad.tieneLlave(miGrupo, "AM_CerrarAccionesArea");
                rsSeguridad.close();
                this.pagHTML.setTextNumero((regAccion.getAccion().equals("R") ? "Corrección Nro " : "Acción de Mejora Nro: ") + regAccion.getNumero());
                this.pagHTML.setTextEmpleado_cliente("" + regAccion.getNombre());
                this.pagHTML.setTextCodigo_estado("" + regAccion.getNombreEstado());
                this.pagHTML.setTextFechaGenerada("" + Utilidades.darFormatoFecha(regAccion.getFechaGenerada()));
                this.pagHTML.setTextFecha_vigencia("" + Utilidades.darFormatoFecha(regAccion.getFechaVigencia()));
                this.pagHTML.setTextFechaEstimada("" + Utilidades.darFormatoFecha(regAccion.getFechaEstimadaTerminacion()));
                this.pagHTML.setTextFechaReal("" + Utilidades.darFormatoFecha(regAccion.getFechaRealTerminacion()));
                this.pagHTML.setTextAccion("" + regAccion.getTipoAccion());
                this.pagHTML.setTextOrigen("" + regAccion.getOrigenAccion());
                this.pagHTML.setTextProceso("" + regAccion.getNombreProceso());
                this.pagHTML.setTextNorma("" + regAccion.getNorma());
                this.pagHTML.setTextSubproceso("" + regAccion.getNombreSubproceso());
                this.pagHTML.setTextNumeral("" + regAccion.getNumeral());
                this.pagHTML.setTextDescripcion("" + regAccion.getDescripcion());
                this.pagHTML.setTextImpacto("" + regAccion.getNombreImpacto());
                if (regAccion.getCodigoEstado() != 2 && regAccion.getCodigoEstado() != 3) {
                    HTMLElement eltr = this.pagHTML.getElementTrFinaliza();
                    eltr.getParentNode().removeChild(eltr);
                } else {
                    this.pagHTML.setTextCumplio("" + (regAccion.getCumplio().equals("S") ? "SI" : "NO"));
                    this.pagHTML.setTextFuncionario1("" + regAccion.getFuncionario1());
                    this.pagHTML.setTextCargo1("" + regAccion.getCargo1());
                    this.pagHTML.setTextFecha1("" + Utilidades.darFormatoFecha(regAccion.getFecha1()));
                    this.pagHTML.setTextObservacionCierre(regAccion.getObservacionesCierre());
                }

                this.pagHTML.getElementIdNumeroMo().setValue("" + numero);
                this.pagHTML.getElementIdNumeroNc().setValue("" + numero);
                this.pagHTML.getElementIdNumeroAr().setValue("" + numero);
                this.pagHTML.getElementIdNumeroEn().setValue("" + numero);
                this.pagHTML.getElementIdNumeroAn().setValue("" + numero);
                this.pagHTML.getElementIdNumeroCe().setValue("" + numero);
                this.pagHTML.getElementIdNumeroCa().setValue("" + numero);
                this.pagHTML.getElementLlave().setValue("" + numero);
                AreasDAO areaf = new AreasDAO();
                String secuenciaProveedor = "";
                AreasDTO regAreaProveedor = areaf.getArea(regAccion.getAreaImplanta());
                if (regAreaProveedor != null) {
                    secuenciaProveedor = regAreaProveedor.getSecuencia();
                }

                AreasDTO regAreaNav = areaf.getArea(elNavegante.getArea());
                areaf.close();
                this.agregarArchivos(numero, regAccion.getEmpleadoCliente(), idNav);
                int numeroCausas = 0; //int numeroCausas = false;
                 numeroCausas = this.agregarCausas(comms, regAccion, numero, regAccion.getCodigoEstado(), idNav, regAccion.getEmpleadoCliente(), lectura, permisoModificar, secuenciaProveedor, regAreaNav.getSecuencia());
                HTMLTableCellElement eltr;
                if (numeroCausas < ParametrosDTO.getInt("AM_numero_causas_requeridad") && regAccion.getCodigoEstado() == 0 || regAccion.getCodigoEstado() != 0 || lectura == 1) {
                    eltr = this.pagHTML.getElementTrEnviar();
                    eltr.getParentNode().removeChild(eltr);
                }

                if (regAccion.getImpacto().equals("V")) {
                    this.listarAreas(comms, regAccion.getNumero());
                } else {
                    HTMLElement eltr2 = this.pagHTML.getElementTrAreas();
                    eltr2.getParentNode().removeChild(eltr2);
                }

                if (regAccion.getCodigoEstado() != 0 && regAccion.getCodigoEstado() != 1 || regAccion.getEmpleadoCliente() != idNav && (!permisoAgregarCausas || !Utilidades.esSecuenciaHija(secuenciaProveedor, regAreaNav.getSecuencia())) || lectura != 0 || regAccion.getAccion().equals("R") && regAccion.getCodigoEstado() >= 1) {
                    eltr = this.pagHTML.getElementTrNuevaCausa();
                    eltr.getParentNode().removeChild(eltr);
                }

                long diasCorridos = Utilidades2.diferenciaEnDias(regAccion.getFechaGenerada(), Utilidades.fechaActual());
                if (regAccion.getCodigoEstado() != 0 && regAccion.getCodigoEstado() != 1 || diasCorridos > (long)ParametrosDTO.getInt("AM_numero.dias.editar") || lectura != 0 || idNav != regAccion.getEmpleadoCliente() && (!permisoModificar || !Utilidades.esSecuenciaHija(secuenciaProveedor, regAreaNav.getSecuencia()))) {
                     HTMLElement eltr2 = this.pagHTML.getElementTrPreModificar();
                    eltr2.getParentNode().removeChild(eltr2);
                }

                boolean bAnularAccion = regAccion.getCodigoEstado() != 0 || lectura == 1;
                if (regAccion.getCodigoEstado() == 0 && regAccion.getOrigen().equals("ACI")) {
                    bAnularAccion = true;
                }

                if (bAnularAccion) {
                    HTMLElement eltr2 = this.pagHTML.getElementTrAnular();
                    eltr2.getParentNode().removeChild(eltr2);                }

                if (!this.cerrar || regAccion.getCodigoEstado() != 1 || idNav != regAccion.getEmpleadoCliente() && (!permisoCerrarEnArea || elNavegante.getArea() != regAccion.getAreaImplanta()) && (!permisoCerrar || !Utilidades.esSecuenciaHija(secuenciaProveedor, regAreaNav.getSecuencia())) || lectura != 0) {
                    HTMLElement eltr2 = this.pagHTML.getElementTrCerrar();
                    eltr2.getParentNode().removeChild(eltr2);
                }

                int codigoMadrina = 0;
                //HTMLTableCellElement eltr;
                if (regAccion.getCodigoEstado() != 5 || regAreaNav.getNivel() > 4 && regAccion.getEmpleadoCliente() == elNavegante.getCodigoEmpleado() || regAccion.getOrigen().equals("ACI") && codigoMadrina != elNavegante.getCodigoEmpleado() || (!permisoCalificarEnArea || elNavegante.getArea() != regAccion.getAreaImplanta()) && (!permisoCalificarEnArea || !Utilidades.esSecuenciaHija(secuenciaProveedor, regAreaNav.getSecuencia())) && !permisoCalificarGeneral && codigoMadrina != elNavegante.getCodigoEmpleado()) {
                    eltr = this.pagHTML.getElementTrCalificar();
                    eltr.getParentNode().removeChild(eltr);
                }

                if (regAccion.getCodigoEstado() == 4 || idNav != regAccion.getEmpleadoCliente() && idNav != regAreaNav.getPersonaResponsable() || lectura == 1) {
                    eltr = this.pagHTML.getElementTrAgregarArchivo();
                    eltr.getParentNode().removeChild(eltr);
                }

                if (regAccion.getCumplio().equals("N") && elNavegante.getArea() == ParametrosDTO.getInt("codigo.gerencia.sinco.servicio")) {
                    this.pagHTML.getElementIdNumeroGC().setValue("" + numero);
                } else {
                    HTMLElement eltr2 = this.pagHTML.getElementTrObsGerencia();
                    eltr2.getParentNode().removeChild(eltr2);
                }

                if (!permisoVerBitacora) {
                    eltr = this.pagHTML.getElementTrBitacora();
                    eltr.getParentNode().removeChild(eltr);
                }

            }
        }
    }

    private HTMLElement detalleCausa(AMCausasDTO reg, int estadoAccion, int idNav, int empleadoCliente, int lectura, boolean permisoNuevaAtencion, String secuenciaProveedor, String secuenciaNavegante, boolean permisoModificar) {
        HTMLFormElement formulario = (HTMLFormElement)this.pagHTML.createElement("form");
        formulario.setAction("AMAgregarSeguimiento.po");
        HTMLInputElement inp = (HTMLInputElement)this.pagHTML.createElement("input");
        inp.setName("_operacion");
        inp.setValue("SEGV2");
        inp.setAttributeNode(this.newAttr("type", "hidden"));
        formulario.appendChild(inp);
        inp = (HTMLInputElement)this.pagHTML.createElement("input");
        inp.setName("numero");
        inp.setValue("" + reg.getNumero());
        inp.setAttributeNode(this.newAttr("type", "hidden"));
        formulario.appendChild(inp);
        inp = (HTMLInputElement)this.pagHTML.createElement("input");
        inp.setName("causa");
        inp.setValue("" + reg.getConsecutivo());
        inp.setAttributeNode(this.newAttr("type", "hidden"));
        formulario.appendChild(inp);
        HTMLElement elemento = (HTMLElement)this.pagHTML.createElement("table");
        elemento.setAttributeNode(this.newAttr("class", "tabf"));
        HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
        String url = "AMDetalleCausasV2.po?_operacion=E&numero=" + reg.getNumero() + "&causa=" + reg.getConsecutivo() + "&lectura=" + lectura;
        eltr.appendChild(this.newtdhref("Causa Número " + reg.getConsecutivo(), url, "ca2", 4));
        elemento.appendChild(eltr);
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("Porque", "tit", 0));
        eltr.appendChild(this.newtd(reg.getPorque(), "dat", 3));
        elemento.appendChild(eltr);
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("td");
        eltr2.appendChild(this.newtd("Acción", "tit", 0));
        HTMLElement tdDetalle = (HTMLElement)this.pagHTML.createElement("span");
        tdDetalle.appendChild(this.pagHTML.createTextNode(reg.getAccion()));
        tdDetalle.setAttributeNode(this.newAttr("id", "_causa" + reg.getConsecutivo()));
        eltr.setAttributeNode(this.newAttr("class", "dat"));
        eltr.setAttributeNode(this.newAttr("colspan", "3"));
        eltr.appendChild(tdDetalle);
        eltr.appendChild(eltr);
        elemento.appendChild(eltr);
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("Beneficio", "tit", 0));
        eltr.appendChild(this.newtd(reg.getBeneficio(), "dat", 3));
        elemento.appendChild(eltr);
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("Fecha Inicio", "tit", 0));
        eltr.appendChild(this.newtd(Utilidades.darFormatoFecha(reg.getFechaInicio()), "dat", 0));
        eltr.appendChild(this.newtd("Fecha Estimada Terminación", "tit", 0));
        eltr = (HTMLElement)this.pagHTML.createElement("td");
        eltr.setAttributeNode(this.newAttr("class", "dat"));
        eltr.setAttributeNode(this.newAttr("id", "_fecha" + reg.getConsecutivo()));
        eltr.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(reg.getFechaEstimadaTerminacion())));
        eltr.appendChild(eltr);
        elemento.appendChild(eltr);
        eltr = (HTMLElement)this.pagHTML.createElement("tr");
        eltr.appendChild(this.newtd("Responsable", "tit", 0));
        eltr.appendChild(this.newtd(reg.getNombreResponsable(), "dat", 0));
        eltr.appendChild(this.newtd("Estado", "tit", 0));
        long diasCorridos = Utilidades2.diferenciaEnDias(reg.getFechaInicio(), Utilidades.fechaActual());
        String id;
        if (lectura == 0 && (estadoAccion == 0 || estadoAccion == 1) && (reg.getEstado() == 0 || reg.getEstado() == 1) && (idNav == empleadoCliente || permisoModificar && Utilidades.esSecuenciaHija(secuenciaProveedor, secuenciaNavegante)) && diasCorridos <= (long)ParametrosDTO.getInt("AM_numero.dias.editar")) {
            id = "AMActCausasV2.po?_operacion=P&numero=" + reg.getNumero() + "&causa=" + reg.getConsecutivo();
            eltr.appendChild(this.newtdhref(reg.getNombreEstado(), id));
        } else {
            eltr.appendChild(this.newtd(reg.getNombreEstado(), "dat", 0));
        }

        elemento.appendChild(eltr);
        AMSeguimientoDAO rsseg = new AMSeguimientoDAO();
        AMSeguimientoDTO seguimiento = rsseg.cargarUltimoSeguimiento(reg.getNumero(), reg.getConsecutivo());
        rsseg.close();
        if (seguimiento != null) {
            tdDetalle = (HTMLElement)this.pagHTML.createElement("tr");
            tdDetalle.appendChild(this.newtd("Ultimo Seguimiento", "tit", 0));
            tdDetalle.appendChild(this.newtd(seguimiento.getObservacion(), "tit", 3));
            elemento.appendChild(tdDetalle);
        }

        boolean cerrada = reg.getEstado() != 1;
        if (lectura == 0 && !cerrada && reg.getEstado() == 1 && (idNav == reg.getResponsable() || permisoNuevaAtencion && Utilidades.esSecuenciaHija(secuenciaProveedor, secuenciaNavegante) || idNav == empleadoCliente)) {
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("Seguimiento", "tit", 0));
            tdDetalle = (HTMLElement)this.pagHTML.createElement("td");
            tdDetalle.setAttributeNode(this.newAttr("class", "dat"));
            tdDetalle.setAttributeNode(this.newAttr("colspan", "3"));
            id = "_observacion_" + reg.getConsecutivo();
            HTMLInputElement hidden = (HTMLInputElement)this.pagHTML.createElement("input");
            hidden.setAttributeNode(this.newAttr("type", "hidden"));
            hidden.setAttributeNode(this.newAttr("id", "H" + id));
            HTMLTextAreaElement inp2 = (HTMLTextAreaElement)this.pagHTML.createElement("TEXTAREA");
            inp2.setName("observacion");
            inp2.setAttributeNode(this.newAttr("id", "" + id));
            inp2.setRows(6);
            inp2.setCols(130);
            inp2.setAttributeNode(this.newAttr("onkeypress", "validarTecla(event, 'AL');"));
            inp2.setAttributeNode(this.newAttr("onkeyDown", "vallength('" + id + "','H" + id + "',512);"));
            inp2.setAttributeNode(this.newAttr("onkeyup", "vallength('" + id + "','H" + id + "',512);"));
            inp2.setAttributeNode(this.newAttr("onpaste", "vallength('" + id + "','H" + id + "',512);"));
            tdDetalle.appendChild(hidden);
            tdDetalle.appendChild(inp2);
            eltr.appendChild(tdDetalle);
            elemento.appendChild(eltr);
            eltr = (HTMLElement)this.pagHTML.createElement("tr");
            tdDetalle = (HTMLElement)this.pagHTML.createElement("td");
            tdDetalle.setAttributeNode(this.newAttr("class", "dat2"));
            tdDetalle.setAttributeNode(this.newAttr("colspan", "4"));
            HTMLInputElement inp3 = (HTMLInputElement)this.pagHTML.createElement("input");
            inp3.setAttribute("type", "Submit");
            inp3.setAttribute("class", "BOT");
            inp3.setValue("Grabar");
            tdDetalle.appendChild(inp3);
            eltr.appendChild(tdDetalle);
            elemento.appendChild(eltr);
        }

        formulario.appendChild(elemento);
        return formulario;
    }

    private HTMLElement agregarCausa(AMCausasDTO reg, int estadoAccion, int idNav, int empleadoCliente, int lectura, boolean permisoNuevaAtencion, String secuenciaProveedor, String secuenciaNavegante, boolean permisoModificar) {
        HTMLElement div = (HTMLElement)this.pagHTML.createElement("div");
        div.appendChild(this.detalleCausa(reg, estadoAccion, idNav, empleadoCliente, lectura, permisoNuevaAtencion, secuenciaProveedor, secuenciaNavegante, permisoModificar));
        div.appendChild(this.pagHTML.createElement("p"));
        return div;
    }

    private int agregarCausas(HttpPresentationComms comms, AMAccionesDTO regAcciones, int numero, int codigoEstado, int idNav, int empleadoCliente, int lectura, boolean permisoModificar, String secuenciaProveedor, String secuenciaNavegante) throws HttpPresentationException, KeywordValueException {
        int i = 0;
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        SeguridadDAO rsSeguridad = new SeguridadDAO();
        boolean permisoNuevaAtencion = rsSeguridad.tieneLlave(miGrupo, "AM_AgregarSeguimientoCausa");
        boolean tienePermisoDireccionar = rsSeguridad.tieneLlave(miGrupo, "oCambiarProveedor");
        boolean permisoSolicitarProrroga = rsSeguridad.tieneLlave(miGrupo, "AM_SolicitarProrrogaCausa");
        boolean permisoCambioEstado = rsSeguridad.tieneLlave(miGrupo, "AM_CambioEstadoCausa");
        rsSeguridad.close();
        AMCausasDAO rs = new AMCausasDAO();
        if (!rs.getEstadoConexion()) {
            return 0;
        } else {
            Collection arr = rs.cargarTodos(numero);
            rs.close();
            HTMLElement hte = this.pagHTML.getElementDetalle();
            HTMLSelectElement comboCausaCacao = this.pagHTML.getElementCausaJ();
            HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            comboCausaCacao.appendChild(op);
            HTMLSelectElement comboCausaResponsable = this.pagHTML.getElementCausaR();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            comboCausaResponsable.appendChild(op);
            HTMLSelectElement comboCausaEstado = this.pagHTML.getElementCausaE();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("");
            op.appendChild(this.pagHTML.createTextNode(""));
            comboCausaEstado.appendChild(op);
            Iterator iterator = arr.iterator();

            while(true) {
                AMCausasDTO reg;
                //HTMLOptionElement op;
                do {
                    do {
                        do {
                            if (!iterator.hasNext()) {
                                HTMLDivElement eltr;
                                if (comboCausaCacao.getLength() > 1) {
                                    this.pagHTML.getElementNumeroJ().setValue("" + regAcciones.getNumero());
                                } else {
                                    eltr = this.pagHTML.getElementTrPedirCacao();
                                    eltr.getParentNode().removeChild(eltr);
                                }

                                HTMLSelectElement combo;
                                if (comboCausaResponsable.getLength() > 1) {
                                    this.pagHTML.getElementNumeroR().setValue("" + regAcciones.getNumero());
                                    combo = this.pagHTML.getElementIdResponsable();
                                    this.comboPersonas(combo, regAcciones.getNumero(), regAcciones.getImpacto(), idNav, regAcciones.getAreaImplanta());
                                } else {
                                    eltr = this.pagHTML.getElementCambiarResp();
                                    eltr.getParentNode().removeChild(eltr);
                                }

                                if (comboCausaEstado.getLength() > 1) {
                                    this.pagHTML.getElementNumeroE().setValue("" + regAcciones.getNumero());
                                    combo = this.pagHTML.getElementEstado();
                                    TGeneralDAO rsTGen = new TGeneralDAO();
                                    if (!rsTGen.getEstadoConexion()) {
                                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
                                    }

                                    this.llenarCombo(rsTGen, combo, "am_estado_causas", "codigo", "descripcion", "codigo in(2,3)");
                                    rsTGen.close();
                                } else {
                                    eltr = this.pagHTML.getElementTrCambioEstado();
                                    eltr.getParentNode().removeChild(eltr);
                                }

                                if (i < ParametrosDTO.getInt("AM_numero_causas_requeridad")) {
                                    this.cerrar = false;
                                }

                                return i;
                            }

                            reg = (AMCausasDTO)iterator.next();
                            hte.appendChild(this.agregarCausa(reg, codigoEstado, idNav, empleadoCliente, lectura, permisoNuevaAtencion, secuenciaProveedor, secuenciaNavegante, permisoModificar));
                            if (reg.getEstado() == 1) {
                                this.cerrar = false;
                            }

                            ++i;
                            if (reg.getProrrogas() < ParametrosDTO.getInt("AM_numero.prorrogas") && reg.getEstado() == 1 && (idNav == reg.getResponsable() || permisoSolicitarProrroga && Utilidades.esSecuenciaHija(secuenciaProveedor, secuenciaNavegante) || idNav == empleadoCliente) && lectura == 0) {
                                op = (HTMLOptionElement)this.pagHTML.createElement("option");
                                op.setValue("" + reg.getConsecutivo());
                                op.appendChild(this.pagHTML.createTextNode("Causa numero " + reg.getConsecutivo()));
                                comboCausaCacao.appendChild(op);
                            }

                            if (tienePermisoDireccionar && codigoEstado == 1 && reg.getEstado() == 1 && (Utilidades.esSecuenciaHija(secuenciaProveedor, secuenciaNavegante) && regAcciones.getImpacto().equals("A") || regAcciones.getImpacto().equals("C") && idNav == reg.getResponsable() || regAcciones.getImpacto().equals("V") && idNav == reg.getResponsable())) {
                                op = (HTMLOptionElement)this.pagHTML.createElement("option");
                                op.setValue("" + reg.getConsecutivo());
                                op.appendChild(this.pagHTML.createTextNode("Causa numero " + reg.getConsecutivo()));
                                comboCausaResponsable.appendChild(op);
                            }
                        } while(reg.getSeguimientos() < 1);
                    } while(reg.getEstado() != 1);
                } while(idNav != reg.getResponsable() && idNav != regAcciones.getEmpleadoCliente() && (!permisoCambioEstado || !Utilidades.esSecuenciaHija(secuenciaProveedor, secuenciaNavegante)));

                if (lectura == 0) {
                    op = (HTMLOptionElement)this.pagHTML.createElement("option");
                    op.setValue("" + reg.getConsecutivo());
                    op.appendChild(this.pagHTML.createTextNode("Causa numero " + reg.getConsecutivo()));
                    comboCausaEstado.appendChild(op);
                }
            }
        }
    }

    private void agregarArchivos(int numero, int cliente, int idNav) {
        HTMLTableElement tabla = this.pagHTML.getElementDetalleArchivos();
        ArchivosSolicitudDAO rs = new ArchivosSolicitudDAO();
        rs.getArchivosAccionMejora(numero, 0);
        ArchivosSolicitudDTO reg = rs.nextA();
        boolean hay = false;

        for(boolean fondo = true; reg != null; reg = rs.nextA()) {
            hay = true;
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            if (reg.getEstado().equals("B")) {
                eltr.appendChild(this.newtd("Borrado"));
            } else {
                HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
                HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
                checkbox.setAttribute("type", "checkbox");
                checkbox.setName("" + reg.getConsecutivo());
                tdMarca.appendChild(checkbox);
                eltr.appendChild(tdMarca);
            }

            if (reg.getEstado().equals("B")) {
                eltr.appendChild(this.newtd(reg.getArchivo()));
            }

            eltr.appendChild(this.newtdhref(reg.getArchivo(), "VerArchivo.po?ruta=archivos_acciones&archivo=" + reg.getArchivo(), true));
            eltr.appendChild(this.newtd("" + Utilidades.darFormatoFecha(reg.getFechaInsercion())));
            if (reg.getCausa() > 0) {
                eltr.appendChild(this.newtd("" + reg.getCausa()));
            } else {
                eltr.appendChild(this.newtd(" "));
            }

            tabla.appendChild(eltr);
        }

        rs.close();
        this.pagHTML.getElementIdNumeroArch().setValue("" + numero);
        this.pagHTML.getElementIdCausaArch().setValue("0");
        if (cliente != idNav) {
            Element divArchivos = this.pagHTML.getElementIdBotonArchivos();
            divArchivos.getParentNode().removeChild(divArchivos);
        }

        if (!hay) {
            Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
            divArchivos.getParentNode().removeChild(divArchivos);
        }

    }

    private HTMLElement newtd(String contenido) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
    }

    private Attr newAttr(String tipo, String valor) {
        Attr atrib = this.pagHTML.createAttribute(tipo);
        atrib.setValue(valor);
        return atrib;
    }

    private HTMLElement newtd(String contenido, String clase, int colspan) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        if (nuevaVentana) {
            enlace.setAttributeNode(this.newAttr("TARGET", "_blank"));
        }

        td.appendChild(enlace);
        td.setAttributeNode(this.newAttr("class", "ctd"));
        return td;
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
        td.setAttributeNode(this.newAttr("class", "dat"));
        return td;
    }

    private HTMLElement newtdhref(String contenido, String vinculo, String clase, int colspan) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        if (clase.length() > 0) {
            td.setAttributeNode(this.newAttr("class", clase));
        }

        if (colspan > 0) {
            td.setAttributeNode(this.newAttr("colspan", "" + colspan));
        }

        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        td.appendChild(enlace);
        return td;
    }

    private void listarAreas(HttpPresentationComms comms, int numero) throws HttpPresentationException, KeywordValueException {
        AMAccionesDAO rs = new AMAccionesDAO();
        Collection arr = rs.cargarAreas(numero);
        HTMLTableElement hte = this.pagHTML.getElementAreas();
        Iterator iterator = arr.iterator();
        boolean fondo = false;

        while(iterator.hasNext()) {
            AMAccionesDTO reg = (AMAccionesDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            fondo = !fondo;
            eltr.setAttributeNode(this.newAttr("class", "ct" + (fondo ? "1" : "2")));
            eltr.appendChild(this.newtd("" + reg.getNombreAreaImplanta()));
            hte.appendChild(eltr);
        }

        arr.clear();
    }

    private void comboPersonas(HTMLSelectElement combo, int numeroAccion, String impacto, int defecto, int area) {
        new ArrayList();
        SisUsuariosDAO pf = new SisUsuariosDAO();
        Collection arr = pf.cargarActivoDeArea(area);

        HTMLOptionElement op;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
            SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + personaDelArea.getCodigoEmpleado());
            op.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres()));
            if (defecto == personaDelArea.getCodigoEmpleado()) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op.setAttributeNode(escogida);
            }
        }

    }

    private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion) {
        HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
        op.setValue("");
        op.appendChild(this.pagHTML.createTextNode(""));
        combo.appendChild(op);
        rsTGen.cargarTodos(tabla, codigo, descripcion, condicion + " order by " + codigo);

        TGeneralDTO RegGeneral;
        while((RegGeneral = rsTGen.next()) != null) {
            op = (HTMLOptionElement)this.pagHTML.createElement("option");
            op.setValue("" + RegGeneral.getCodigo());
            op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
            combo.appendChild(op);
        }

    }
}
