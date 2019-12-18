//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.presentation;

import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
import com.lutris.appserver.server.httpPresentation.HttpPresentation;
import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
import com.lutris.util.KeywordValueException;
import java.util.Collection;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import sinco.business.AreasDTO;
import sinco.business.IndiceSatisfaccionDTO;
import sinco.business.ServiciosDTO;
import sinco.business.Utilidades;
import sinco.business.VSolicitudesDTO;
import sinco.data.AreasDAO;
import sinco.data.IndiceSatisfaccionDAO;
import sinco.data.SeguridadDAO;
import sinco.data.ServiciosDAO;
import sinco.data.VSolicitudesDAO;
import sinco.spec.MenuDO;

public class RepProntitud implements HttpPresentation {
    private int idNav;
    private RepProntitudHTML pagHTML;

    public RepProntitud() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            String _operacion = comms.request.getParameter("_operacion");
            if (_operacion == null) {
                _operacion = "X";
            }

            this.pagHTML = (RepProntitudHTML)comms.xmlcFactory.create(RepProntitudHTML.class);
            if (!_operacion.equals("X") && !_operacion.equals("L")) {
                HTMLDivElement sel;
                if (_operacion.equals("D")) {
                    this.listarDetalle(comms);
                    sel = this.pagHTML.getElementTrResultados();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrConsulta();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrSolicitudes();
                    sel.getParentNode().removeChild(sel);
                } else if (_operacion.equals("S")) {
                    this.listarSolicitudes(comms);
                    sel = this.pagHTML.getElementTrResultados();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrConsulta();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrDetallado();
                    sel.getParentNode().removeChild(sel);
                } else if (_operacion.equals("SB")) {
                    this.listarSolicitudesBase(comms);
                    sel = this.pagHTML.getElementTrResultados();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrConsulta();
                    sel.getParentNode().removeChild(sel);
                    sel = this.pagHTML.getElementTrDetallado();
                    sel.getParentNode().removeChild(sel);
                }
            } else {
                String exportar = comms.request.getParameter("exportar");
                if (exportar == null) {
                    exportar = "N";
                }

                if (exportar.equals("S")) {
                    comms.response.setContentType("application/xls");
                    comms.response.setHeader("Content-Disposition", "inline;filename=SolCiclo.xls");
                    comms.response.setStatus(200, "Good job");
                    HttpPresentationOutputStream out = comms.response.getOutputStream();

                    try {
                        String respuesta = this.exportar(comms);
                        out.write(respuesta.getBytes());
                        out.flush();
                        return;
                    } catch (Exception var6) {
                        throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
                    }
                }

                HTMLElement sel = this.pagHTML.getElementTrDetallado();
                sel.getParentNode().removeChild(sel);
                sel = this.pagHTML.getElementTrSolicitudes();
                sel.getParentNode().removeChild(sel);
                this.filtros(comms);
                if (_operacion.equals("L")) {
                    this.listar(comms);
                }
            }

            this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
            comms.response.writeDOM(this.pagHTML);
        }
    }

    private void filtros(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
        int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
        boolean var4 = false;

        try {
            int areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
            miArea = areaProveedor;
        } catch (Exception var15) {
        }

        this.combos(comms);
        HTMLSelectElement combo = this.pagHTML.getElementAreaProveedor();
        SeguridadDAO rsSeguridad = new SeguridadDAO();
        if (!rsSeguridad.getEstadoConexion()) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
        } else {
            boolean gIndiceArea = rsSeguridad.tieneLlave(miGrupo, "oIndiceArea");
            boolean bMostrarTodasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
            rsSeguridad.close();
            Collection arr = null;
            AreasDAO af = new AreasDAO();
            if (bMostrarTodasAreas) {
                arr = af.cargarActivas();
            } else if (gIndiceArea) {
                arr = af.cargarAreasHijas(this.idNav);
            } else {
                arr = af.cargarMisAreas(this.idNav);
            }

            af.close();

            HTMLOptionElement op;
            for(Iterator iterator = arr.iterator(); iterator.hasNext(); combo.appendChild(op)) {
                AreasDTO reg = (AreasDTO)iterator.next();
                op = (HTMLOptionElement)this.pagHTML.createElement("option");
                op.setValue("" + reg.getCodigo());
                op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
                if (miArea == reg.getCodigo()) {
                    Attr escogida = this.pagHTML.createAttribute("selected");
                    escogida.setValue("on");
                    op.setAttributeNode(escogida);
                }
            }

        }
    }

    private void combos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var10) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var11) {
            mes1 = Utilidades.getMesActual() - 1;
            if (mes1 == 0) {
                --anno;
            }
        }

        HTMLSelectElement annos = this.pagHTML.getElementAnno();

        for(int j = 2008; j <= Utilidades.getAnnoActual(); ++j) {
            HTMLOptionElement op1 = (HTMLOptionElement)this.pagHTML.createElement("option");
            op1.setValue("" + j);
            op1.appendChild(this.pagHTML.createTextNode("" + j));
            annos.appendChild(op1);
            if (j == anno) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op1.setAttributeNode(escogida);
            }
        }

        HTMLSelectElement sMes1 = this.pagHTML.getElementMes1();
        String[] meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        for(int j = 1; j <= 12; ++j) {
            HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
            op2.setValue("" + j);
            op2.appendChild(this.pagHTML.createTextNode(meses[j - 1]));
            sMes1.appendChild(op2);
            if (j == mes1) {
                Attr escogida = this.pagHTML.createAttribute("selected");
                escogida.setValue("on");
                op2.setAttributeNode(escogida);
            }
        }

    }

    private void listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var23) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var22) {
            mes1 = Utilidades.getMesActual();
        }

        int areaProveedor = 0;

        try {
            areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
        } catch (Exception var21) {
        }

        IndiceSatisfaccionDAO rs = new IndiceSatisfaccionDAO();
        Collection arr = rs.indiceProntitud(anno, mes1, areaProveedor);
        rs.close();
        double tiempoBase = 0.0D;
        double tiempoMovil = 0.0D;
        int numeroBase = 0;
        int numeroMovil = 0;
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
        int cuantas = 0;

        IndiceSatisfaccionDTO reg;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
            reg = (IndiceSatisfaccionDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            String url = "RepProntitud.po?_operacion=D&anno=" + anno + "&mes1=" + mes1 + "&areaProveedor=" + reg.getCodigoArea();
            eltr.appendChild(this.newtdhref("" + reg.getNombreArea(), url));
            eltr.appendChild(this.newtd("" + Utilidades.miles(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), 3), true));
            eltr.appendChild(this.newtd("" + Utilidades.miles(reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil()), 3), true));
            double prontitud = reg.getProntitud(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil()));
            if (prontitud == 0.0D) {
                eltr.appendChild(this.newtd("N/A", true));
            } else {
                eltr.appendChild(this.newtd("" + Utilidades.miles(prontitud, 2), true));
            }

            hte.appendChild(eltr);
            tiempoBase += reg.getTiempoBase();
            tiempoMovil += reg.getTiempoMovil();
            numeroBase += reg.getNumeroBase();
            numeroMovil += reg.getNumeroMovil();
        }

        reg = new IndiceSatisfaccionDTO();
        this.pagHTML.setTextNroRegistros("" + cuantas);
        this.pagHTML.setTextLineaBase("" + Utilidades.miles(reg.getLineaBase(tiempoBase, numeroBase), 3));
        this.pagHTML.setTextLineaMovil("" + Utilidades.miles(reg.getLineaMovil(tiempoMovil, numeroMovil), 3));
        double prontitud = reg.getProntitud(reg.getLineaBase(tiempoBase, numeroBase), reg.getLineaMovil(tiempoMovil, numeroMovil));
        if (prontitud == 0.0D) {
            this.pagHTML.setTextProntitud("N/A");
        } else {
            this.pagHTML.setTextProntitud("" + Utilidades.miles(prontitud, 3));
        }

        arr.clear();
    }

    private String exportar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var18) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var17) {
            mes1 = Utilidades.getMesActual();
        }

        int areaProveedor = 0;

        try {
            areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
        } catch (Exception var16) {
        }

        IndiceSatisfaccionDAO rs = new IndiceSatisfaccionDAO();
        Collection arr = rs.indiceProntitud(anno, mes1, areaProveedor);
        rs.close();
        Iterator iterator = arr.iterator();
        String respuesta = "Area Proveedora\tLinea base\tLinea Movil\tIndice Prontitud\t";
        double tiempoBase = 0.0D;
        double tiempoMovil = 0.0D;
        int numeroBase = 0;

        int numeroMovil;
        IndiceSatisfaccionDTO reg;
        for(numeroMovil = 0; iterator.hasNext(); numeroMovil += reg.getNumeroMovil()) {
            reg = (IndiceSatisfaccionDTO)iterator.next();
            respuesta = respuesta + "\n";
            respuesta = respuesta + reg.getNombreArea() + "\t";
            respuesta = respuesta + Utilidades.miles(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), 3) + "\t";
            respuesta = respuesta + Utilidades.miles(reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil()), 3) + "\t";
            respuesta = respuesta + Utilidades.miles(reg.getProntitud(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil())), 3) + " \t";
            tiempoBase += reg.getTiempoBase();
            tiempoMovil += reg.getTiempoMovil();
            numeroBase += reg.getNumeroBase();
        }

        respuesta = respuesta + "\n";
        reg = new IndiceSatisfaccionDTO();
        respuesta = respuesta + "Totales\t";
        respuesta = respuesta + "" + Utilidades.miles(reg.getLineaBase(tiempoBase, numeroBase), 3) + "\t";
        respuesta = respuesta + "" + Utilidades.miles(reg.getLineaMovil(tiempoMovil, numeroMovil), 3) + "\t";
        respuesta = respuesta + "" + Utilidades.miles(reg.getProntitud(reg.getLineaBase(tiempoBase, numeroBase), reg.getLineaMovil(tiempoMovil, numeroMovil)), 3) + " " + "\t";
        respuesta = respuesta + "\n";
        return respuesta;
    }

    private void listarDetalle(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var26) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var25) {
            mes1 = Utilidades.getMesActual();
        }

        int areaProveedor = 0;

        try {
            areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
        } catch (Exception var24) {
        }

        AreasDAO rsa = new AreasDAO();
        AreasDTO regArea = rsa.getArea(areaProveedor);
        rsa.close();
        if (regArea != null) {
            this.pagHTML.setTextNombreArea(regArea.getDescripcion());
        }

        IndiceSatisfaccionDAO rs = new IndiceSatisfaccionDAO();
        IndiceSatisfaccionDTO regBase = rs.baseProntitud(anno, mes1);
        Collection arr = rs.detalleProntitud(anno, mes1, areaProveedor);
        rs.close();
        double tiempoBase = 0.0D;
        double tiempoMovil = 0.0D;
        int numeroBase = 0;
        int numeroMovil = 0;
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalleServicio();
        int cuantas = 0;

        IndiceSatisfaccionDTO reg;
        for(Iterator iterator = arr.iterator(); iterator.hasNext(); ++cuantas) {
            reg = (IndiceSatisfaccionDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getNombreServicio(), false));
            String url = "RepProntitud.po?_operacion=SB&anno=" + regBase.getAnnoBase() + "&mes1=" + regBase.getMesBase1() + "&mes2=" + regBase.getMesBase2() + "&areaProveedor=" + areaProveedor + "&servicio=" + reg.getCodigoServicio();
            eltr.appendChild(this.newtdhref("" + Utilidades.miles(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), 3), url, true));
            url = "RepProntitud.po?_operacion=S&anno=" + anno + "&mes1=" + mes1 + "&areaProveedor=" + areaProveedor + "&servicio=" + reg.getCodigoServicio();
            eltr.appendChild(this.newtdhref("" + Utilidades.miles(reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil()), 3), url, true));
            double prontitud = reg.getProntitud(reg.getLineaBase(reg.getTiempoBase(), reg.getNumeroBase()), reg.getLineaMovil(reg.getTiempoMovil(), reg.getNumeroMovil()));
            if (prontitud == 0.0D) {
                eltr.appendChild(this.newtd("N/A", true));
            } else {
                eltr.appendChild(this.newtd("" + Utilidades.miles(prontitud, 2), true));
            }

            hte.appendChild(eltr);
            tiempoBase += reg.getTiempoBase();
            tiempoMovil += reg.getTiempoMovil();
            numeroBase += reg.getNumeroBase();
            numeroMovil += reg.getNumeroMovil();
        }

        reg = new IndiceSatisfaccionDTO();
        this.pagHTML.setTextNroRegistrosDet("" + cuantas);
        this.pagHTML.setTextLineaBaseDet("" + Utilidades.miles(reg.getLineaBase(tiempoBase, numeroBase), 3));
        this.pagHTML.setTextLineaMovilDet("" + Utilidades.miles(reg.getLineaMovil(tiempoMovil, numeroMovil), 3));
        double prontitud = reg.getProntitud(reg.getLineaBase(tiempoBase, numeroBase), reg.getLineaMovil(tiempoMovil, numeroMovil));
        if (prontitud == 0.0D) {
            this.pagHTML.setTextProntitudDet("N/A");
        } else {
            this.pagHTML.setTextProntitudDet(" " + Utilidades.miles(prontitud, 2));
        }

        arr.clear();
    }

    private void listarSolicitudes(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var20) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var19) {
            mes1 = Utilidades.getMesActual();
        }

        int areaProveedor = 0;

        try {
            areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
        } catch (Exception var18) {
        }

        int servicio = 0;

        try {
            servicio = Integer.parseInt(comms.request.getParameter("servicio"));
        } catch (Exception var17) {
        }

        ServiciosDAO rsa = new ServiciosDAO();
        ServiciosDTO regServ = rsa.cargarRegistro(servicio);
        rsa.close();
        if (regServ != null) {
            this.pagHTML.setTextNombreServicio("DETALLE LINEA MOVIL " + regServ.getDescripcion());
        }

        VSolicitudesDAO rs = new VSolicitudesDAO();
        Collection arr = rs.solicitudesCerradasPeriodo(anno, mes1, areaProveedor, servicio);
        rs.close();
        double tiempoTotal = 0.0D;
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalleSolicitud();
        int cuantas = 0;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getNumero(), false));
            eltr.appendChild(this.newtd("" + reg.getNombreAreaProveedora(), false));
            eltr.appendChild(this.newtd("" + reg.getNombreProveedor(), false));
            eltr.appendChild(this.newtd("" + reg.getFechaVigencia(), false));
            eltr.appendChild(this.newtd("" + reg.getFechaRealTerminacion(), false));
            eltr.appendChild(this.newtd("" + Utilidades.round2(reg.getTiempoTotal()), true));
            ++cuantas;
            tiempoTotal += reg.getTiempoTotal();
            hte.appendChild(eltr);
        }

        this.pagHTML.setTextNroRegistrosSol("" + cuantas);
        this.pagHTML.setTextTiempo("" + Utilidades.miles(cuantas > 0 ? tiempoTotal / (double)cuantas : 0.0D, 3));
        arr.clear();
    }

    private void listarSolicitudesBase(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        boolean var2 = false;

        int anno;
        try {
            anno = Integer.parseInt(comms.request.getParameter("anno"));
        } catch (Exception var22) {
            anno = Utilidades.getAnnoActual();
        }

        boolean var3 = false;

        int mes1;
        try {
            mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
        } catch (Exception var21) {
            mes1 = Utilidades.getMesActual();
        }

        boolean var4 = false;

        int mes2;
        try {
            mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
        } catch (Exception var20) {
            mes2 = Utilidades.getMesActual();
        }

        int areaProveedor = 0;

        try {
            areaProveedor = Integer.parseInt(comms.request.getParameter("areaProveedor"));
        } catch (Exception var19) {
        }

        int servicio = 0;

        try {
            servicio = Integer.parseInt(comms.request.getParameter("servicio"));
        } catch (Exception var18) {
        }

        ServiciosDAO rsa = new ServiciosDAO();
        ServiciosDTO regServ = rsa.cargarRegistro(servicio);
        rsa.close();
        if (regServ != null) {
            this.pagHTML.setTextNombreServicio("DETALLE LINEA BASE " + regServ.getDescripcion());
        }

        VSolicitudesDAO rs = new VSolicitudesDAO();
        Collection arr = rs.solicitudesCerradasBase(anno, mes1, mes2, areaProveedor, servicio);
        rs.close();
        double tiempoTotal = 0.0D;
        HTMLTableSectionElement hte = this.pagHTML.getElementDetalleSolicitud();
        int cuantas = 0;
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
            HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
            eltr.appendChild(this.newtd("" + reg.getNumero(), false));
            eltr.appendChild(this.newtd("" + reg.getNombreAreaProveedora(), false));
            eltr.appendChild(this.newtd("" + reg.getNombreProveedor(), false));
            eltr.appendChild(this.newtd("" + reg.getFechaVigencia(), false));
            eltr.appendChild(this.newtd("" + reg.getFechaRealTerminacion(), false));
            eltr.appendChild(this.newtd("" + Utilidades.round2(reg.getTiempoTotal()), true));
            ++cuantas;
            tiempoTotal += reg.getTiempoTotal();
            hte.appendChild(eltr);
        }

        this.pagHTML.setTextNroRegistrosSol("" + cuantas);
        this.pagHTML.setTextTiempo("" + Utilidades.miles(cuantas > 0 ? tiempoTotal / (double)cuantas : 0.0D, 3));
        arr.clear();
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

    private HTMLElement newtdhref(String contenido, String vinculo, boolean align) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        Element enlace = this.pagHTML.createElement("a");
        Node hijo = this.pagHTML.createTextNode(contenido);
        enlace.appendChild(hijo);
        Attr donde = this.pagHTML.createAttribute("href");
        donde.setValue(vinculo);
        enlace.setAttributeNode(donde);
        td.appendChild(enlace);
        if (align) {
            td.setAttributeNode(this.newAttr("class", "ctdr"));
        } else {
            td.setAttributeNode(this.newAttr("class", "ctd"));
        }

        return td;
    }

    private HTMLElement newtd(String contenido, boolean align) {
        HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
        td.appendChild(this.pagHTML.createTextNode(contenido));
        if (align) {
            td.setAttributeNode(this.newAttr("class", "ctdr"));
        } else {
            td.setAttributeNode(this.newAttr("class", "ctd"));
        }

        return td;
    }
}
