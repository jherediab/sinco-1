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
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import sinco.business.ContContratoDTO;
import sinco.business.ContImputacionPresupuestalDTO;
import sinco.business.ContInterventorDTO;
import sinco.business.FechaDTO;
import sinco.business.LetrasDTO;
import sinco.business.ParCiudadDTO;
import sinco.business.ParametrosDTO;
import sinco.business.PdeNivelPlanDTO;
import sinco.business.PdeUnidadNivelDTO;
import sinco.business.RedPrestadorDTO;
import sinco.business.RedSucursalDTO;
import sinco.business.SisMultiValoresDTO;
import sinco.business.SisUnidadesMedidaDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.Utilidades;
import sinco.data.ContContratoDAO;
import sinco.data.ContEstudioPrevioDAO;
import sinco.data.ContImputacionPresupuestalDAO;
import sinco.data.ContInterventorDAO;
import sinco.data.ContPolizaDAO;
import sinco.data.ParCiudadDAO;
import sinco.data.ParametrosAplicacionDAO;
import sinco.data.PdeNivelPlanDAO;
import sinco.data.PdePlanDesarrolloDAO;
import sinco.data.PdeUnidadNivelDAO;
import sinco.data.RedPrestadorDAO;
import sinco.data.RedSucursalDAO;
import sinco.data.SisMultiValoresDAO;
import sinco.data.SisUnidadesMedidaDAO;
import sinco.data.SisUsuariosDAO;

public class ConsultasAjaxOld implements HttpPresentation {
    int idNav = 0;

    public ConsultasAjaxOld() {
    }

    public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        if (!comms.session.getSessionData().containsKey("miId")) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
        } else {
            this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
            String _operacion = comms.request.getParameter("_operacion");
            String respuesta = "";
            if (_operacion.equals("buscarMunicipiosPorDepartamento")) {
                respuesta = this.buscarMunicipiosPorDepartamento(comms, _operacion);
            } else if (_operacion.equals("cargarImputaciones")) {
                respuesta = this.cargarImputaciones(comms, _operacion);
            } else if (_operacion.equals("buscarIps")) {
                respuesta = this.buscarIps(comms);
            } else if (_operacion.equals("verificarSedePrincipal")) {
                respuesta = this.verificarSedePrincipal(comms);
            } else if (_operacion.equals("buscarSucursalporPrestador")) {
                respuesta = this.buscarSucursalPorPrestador(comms);
            } else if (_operacion.equals("buscarSucursalporMunicipio")) {
                respuesta = this.buscarSucursalporMunicipio(comms);
            } else if (_operacion.equals("buscarContratistaPorTipoPersona")) {
                respuesta = this.buscarContratistaPorTipoPersona(comms);
            } else if (_operacion.equals("buscarDocumentoExpedicionDepMunNumContratista")) {
                respuesta = this.buscarDocumentoExpedicionDepMunNumContratista(comms);
            } else if (_operacion.equals("verificarCodigoHabilitacionSucursal")) {
                respuesta = this.verificarCodigoHabilitacionSucursal(comms);
            } else if (_operacion.equals("buscarIpsPorCiudad")) {
                respuesta = this.buscarIpsPorCiudad(comms);
            } else if (_operacion.equals("buscarIpsPorCiudad2")) {
                respuesta = this.buscarIpsPorCiudad2(comms);
            } else if (_operacion.equals("validarContratoPrestador")) {
                respuesta = this.validarContratoPrestador(comms);
            } else if (_operacion.equals("buscarPorcentajePoliza")) {
                respuesta = this.buscarPorcentajePoliza(comms);
            } else if (_operacion.equals("verificarContrato")) {
                respuesta = this.verificarContrato(comms);
            } else if (_operacion.equals("buscarContratoSucursal")) {
                respuesta = this.buscarContratoSucursal(comms);
            } else if (_operacion.equals("buscarContratoSucursalJSON")) {
                respuesta = this.buscarContratoSucursalJSON(comms);
            } else if (_operacion.equals("buscarContratoImputacionSucursal")) {
                respuesta = this.buscarContratoImputacionSucursal(comms);
            } else if (_operacion.equals("cargarCausalTraslado")) {
                respuesta = this.cargarCausalTraslado(comms);
            } else if (_operacion.equals("buscarSucursal")) {
                respuesta = this.buscarSucursal(comms);
            } else if (!_operacion.equals("cargarServiciosMacroservicio")) {
                if (_operacion.equals("verificarNit")) {
                    respuesta = this.verificarNit(comms);
                } else if (_operacion.equals("cargarImputaciones2")) {
                    respuesta = this.cargarImputaciones2(comms);
                } else if (_operacion.equals("buscarDocumentosRespaldo")) {
                    respuesta = this.buscarDocumentosRespaldo(comms);
                } else if (_operacion.equals("verificarDocumentosNOObtenidos")) {
                    respuesta = this.verificarDocumentosNOObtenidos(comms);
                } else if (_operacion.equals("cargarCargos")) {
                    respuesta = this.cargarCargos(comms);
                } else if (_operacion.equals("convertirNumero")) {
                    respuesta = this.convertirNumero(comms);
                } else if (_operacion.equals("buscarDatosInterventor")) {
                    respuesta = this.buscarDatosInterventor(comms);
                } else if (_operacion.equals("buscarUnidades")) {
                    respuesta = this.buscarUnidades(comms);
                } else if (_operacion.equals("guardarPlan")) {
                    respuesta = this.guardarPlan(comms);
                } else if (_operacion.equals("consultarPlan")) {
                    respuesta = this.consultarPlan(comms);
                } else if (_operacion.equals("buscarPersonas")) {
                    respuesta = this.buscarPersonas(comms);
                } else if (_operacion.equals("buscarSupervisores")) {
                    respuesta = this.buscarSupervisores(comms);
                } else if (_operacion.equals("consultarNumeroEstudio")) {
                    respuesta = this.consultarNumeroEstudio(comms);
                } else if (_operacion.equals("buscarNiveles")) {
                    respuesta = this.buscarNiveles(comms);
                } else if (_operacion.equals("buscarNivelesSuperiores")) {
                    respuesta = this.buscarNivelesSuperiores(comms);
                }
            }

            if (_operacion.equals("nuevoMetas")) {
                respuesta = this.nuevoMetas(comms);
            }

            respuesta = respuesta.replace('&', '_');
            comms.response.setContentType("text/xml");
            comms.response.setHeader("Content-Disposition", "inline;");
            comms.response.setStatus(200, "Good job");
            HttpPresentationOutputStream out = comms.response.getOutputStream();

            try {
                byte[] byteContents = respuesta.getBytes();
                out.write(byteContents);
                out.flush();
            } catch (Exception var6) {
                throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + var6.getMessage()));
            }
        }
    }

    private String buscarMunicipiosPorDepartamento(HttpPresentationComms comms, String operacion) throws HttpPresentationException, KeywordValueException {
        String codigoDepto = comms.request.getParameter("codigoDepto");
        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        if (codigoDepto == "") {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        ParCiudadDAO rs = new ParCiudadDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        Collection arr = rs.cargarTodos("", "", codigoDepto);
        rs.close();
        Iterator iterator = arr.iterator();

        ParCiudadDTO reg;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("municipio", "codigo", reg.getCodigoCiudad(), "nombre", reg.getNombreCiudad())) {
            reg = (ParCiudadDTO)iterator.next();
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String buscarSupervisores(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int area = 0;

        try {
            area = Integer.parseInt(comms.request.getParameter("area"));
        } catch (Exception var10) {
        }

        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        SisUsuariosDAO rs = new SisUsuariosDAO();
        Collection arr = rs.cargarSupervisores(area);
        Iterator iterator = arr.iterator();

        SisUsuariosDTO reg;
        String codigo;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("supervisor", "codigo", codigo, "nombre", reg.getNombres() + " " + reg.getApellidos())) {
            reg = (SisUsuariosDTO)iterator.next();
            codigo = Integer.toString(reg.getCodigoEmpleado());
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String buscarUnidades(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int codigo_grupo = 0;

        try {
            codigo_grupo = Integer.parseInt(comms.request.getParameter("codigo_grupo"));
        } catch (Exception var9) {
        }

        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        if (codigo_grupo == 0) {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        SisUnidadesMedidaDAO rs = new SisUnidadesMedidaDAO();
        Collection arr = rs.cargarTodos(codigo_grupo, "", "");
        Iterator iterator = arr.iterator();

        SisUnidadesMedidaDTO reg;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("unidad", "codigo_unidad", reg.getCodigoUnidad(), "nombre_unidad", reg.getNombreUnidad())) {
            reg = (SisUnidadesMedidaDTO)iterator.next();
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String buscarNiveles(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int id_nivel = 0;

        try {
            id_nivel = Integer.parseInt(comms.request.getParameter("nivel"));
        } catch (Exception var15) {
        }

        String tipoNivel = comms.request.getParameter("tipoNivel");
        if (tipoNivel == null) {
            tipoNivel = "";
        }

        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "UTF-8";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        if (id_nivel == 0) {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        PdeUnidadNivelDAO rs = new PdeUnidadNivelDAO();
        PdeNivelPlanDAO rs2 = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg2 = new PdeNivelPlanDTO();
        boolean ultimoNivel = false;
        Collection arr = null;
        Iterator iterator;
        if (tipoNivel.equals("S")) {
            arr = rs.cargarTodos(id_nivel, 0, "");
        } else if (tipoNivel.equals("I")) {
            arr = rs.cargarTodos(0, id_nivel, "");
            if (id_nivel > 0) {
                PdeUnidadNivelDTO r = rs.cargarRegistro(id_nivel, 0, 0);
                ultimoNivel = rs2.ultimoNivel(r.getIdNivel());
            }

            iterator = arr.iterator();
            PdeUnidadNivelDTO reg;
            if (iterator.hasNext()) {
                reg = (PdeUnidadNivelDTO)iterator.next();
                reg2 = rs2.cargarRegistro(reg.getIdNivel(), 0, 0);
            } else {
                reg = rs.cargarRegistro(id_nivel, 0, 0);
                reg2 = rs2.cargarRegistro(0, 0, reg.getIdNivel());
            }
        }

        iterator = arr.iterator();
        respuesta = respuesta + "<reg><estado>ok</estado>";
        int registros = 1;

        for(respuesta = respuesta + this.agregaRegistro("nivel", "codigo", "-1", "nombre", "0 - Todos"); iterator.hasNext(); ++registros) {
            PdeUnidadNivelDTO reg = (PdeUnidadNivelDTO)iterator.next();
            respuesta = respuesta + this.agregaRegistro("nivel", "codigo", "" + reg.getIdUnidadNivel(), "nombre", "" + registros + " - " + reg.getNombreUnidad());
        }

        if (tipoNivel.equals("I")) {
            try {
                respuesta = respuesta + this.agregaRegistro("nivelGenerico", "codigoGenerico", "" + reg2.getIdNivel(), "nombreGenerico", "" + reg2.getNombreNivel());
            } catch (Exception var14) {
                respuesta = respuesta + this.agregaRegistro("nivelGenerico", "codigoGenerico", "", "nombreGenerico", "");
            }
        }

        if (ultimoNivel) {
            respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
        } else {
            respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String buscarNivelesSuperiores(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int id_nivel = 0;

        try {
            id_nivel = Integer.parseInt(comms.request.getParameter("nivel"));
        } catch (Exception var11) {
        }

        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "UTF-8";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        if (id_nivel == 0) {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        PdeUnidadNivelDAO rs = new PdeUnidadNivelDAO();
        PdeNivelPlanDAO rs2 = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg2 = rs2.cargarRegistro(id_nivel, 0, 0);
        respuesta = respuesta + "<reg><estado>ok</estado>";
        if (reg2.getNivelSuperior() > 0) {
            Collection arr = rs.cargarTodos(reg2.getNivelSuperior(), 0, "");
            Iterator iterator = arr.iterator();

            PdeUnidadNivelDTO reg;
            for(respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("nivel", "codigo", "" + reg.getIdUnidadNivel(), "nombre", "" + reg.getNombreUnidad())) {
                reg = (PdeUnidadNivelDTO)iterator.next();
            }

            arr.clear();
        } else {
            respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
        }

        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String nuevoMetas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int id_nivel = 0;

        try {
            id_nivel = Integer.parseInt(comms.request.getParameter("nivel"));
        } catch (Exception var11) {
        }

        String charSet = ParametrosDTO.getString("encoding");
        if (charSet == null) {
            charSet = "UTF-8";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        PdeUnidadNivelDAO rs = new PdeUnidadNivelDAO();
        PdeNivelPlanDAO rs2 = new PdeNivelPlanDAO();
        PdeNivelPlanDTO reg2 = rs2.cargarRegistro(id_nivel, 0, 0);
        respuesta = respuesta + "<reg><estado>ok</estado>";
        if (reg2.getNivelSuperior() > 0) {
            Collection arr = rs.cargarTodos(reg2.getNivelSuperior(), 0, "");
            Iterator iterator = arr.iterator();

            PdeUnidadNivelDTO reg;
            for(respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("nivel", "codigo", "" + reg.getIdUnidadNivel(), "nombre", "" + reg.getNombreUnidad())) {
                reg = (PdeUnidadNivelDTO)iterator.next();
            }

            arr.clear();
        } else {
            respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
        }

        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String cargarImputaciones(HttpPresentationComms comms, String operacion) throws HttpPresentationException, KeywordValueException {
        int anio = -1;

        try {
            anio = Integer.parseInt(comms.request.getParameter("anio"));
        } catch (Exception var14) {
        }

        int contrato = -1;

        try {
            contrato = Integer.parseInt(comms.request.getParameter("contrato"));
        } catch (Exception var13) {
        }

        int adicion = -1;

        try {
            adicion = Integer.parseInt(comms.request.getParameter("adicion"));
        } catch (Exception var12) {
        }

        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        if (anio == -1) {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        Collection arr = rs.cargarTodosCdp("", "", anio, contrato, adicion);
        rs.close();
        Iterator iterator = arr.iterator();

        ContImputacionPresupuestalDTO reg;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("imputacion", "codigo", reg.getCodigoImputacion(), "nombre", reg.getDescripcion())) {
            reg = (ContImputacionPresupuestalDTO)iterator.next();
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String agregaElemento(String elemento, String valor) {
        return "<" + elemento + ">" + valor + "</" + elemento + ">";
    }

    private String agregaRegistro(String registro, String codigo, String valorCodigo, String descripcion, String valorDescripcion) {
        return "<" + registro + ">" + this.agregaElemento(codigo, valorCodigo) + this.agregaElemento(descripcion, valorDescripcion) + "</" + registro + ">";
    }

    private Document newDocument() {
        try {
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = fact.newDocumentBuilder();
            Document doc = parser.newDocument();
            return doc;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public String documentToString(Document document, String encoding) {
        String soapRequest = null;
        if (document != null) {
            try {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                t.setOutputProperty("encoding", "" + encoding);
                StringWriter strOut = new StringWriter();
                t.transform(new DOMSource(document), new StreamResult(strOut));
                soapRequest = strOut.toString();
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        return soapRequest;
    }

    private String buscarIps(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String nombre = comms.request.getParameter("nombre");
        if (nombre == null) {
            nombre = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedPrestadorDAO rs = new RedPrestadorDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>La Busqueda No Produjo Resultados</error></reg>";
        } else {
            Collection arr = rs.cargarTodos("", "", nombre, departamento, municipio, "", "", "", "", "IPS");
            rs.close();
            Iterator iterator = arr.iterator();

            RedPrestadorDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + "<ips>" + "<codigo>" + reg.getNumeroIdentificacion() + "</codigo>" + "<nombre>" + reg.getNombreEntidad() + "</nombre>" + "</ips>") {
                reg = (RedPrestadorDTO)iterator.next();
            }

            respuesta = respuesta + "</reg>";
        }

        return respuesta;
    }

    private String verificarSedePrincipal(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String numeroIdentificacion = comms.request.getParameter("codigo");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedSucursalDAO rsIps = new RedSucursalDAO();
        if (!rsIps.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>La Busqueda No Produjo Resultados</error></reg>";
        } else {
            respuesta = respuesta + "<reg><estado>ok</estado>";
            if (rsIps.existeSedePrincipal(numeroIdentificacion)) {
                respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
            } else {
                respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
            }

            respuesta = respuesta + "</reg>";
        }

        rsIps.close();
        return respuesta;
    }

    private String buscarSucursalPorPrestador(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String nombre = comms.request.getParameter("nombre");
        if (nombre == null) {
            nombre = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        if (numeroIdentificacion == null) {
            numeroIdentificacion = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        String nombreSucursal = comms.request.getParameter("nombreSucursal");
        if (nombreSucursal == null) {
            nombreSucursal = "";
        }

        String tipoPrestador = comms.request.getParameter("tipoPrestador");
        if (tipoPrestador == null) {
            tipoPrestador = "";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            Node root = doc.createElement("root");
            doc.appendChild(root);
            if (tipoPrestador.equals("Ter")) {
                tipoPrestador = "PERSONAL','TERCERO";
            }

            RedSucursalDAO rs = new RedSucursalDAO();
            Collection arr = rs.cargarTodos(numeroIdentificacion, nombre, nombreSucursal, departamento, municipio, tipoPrestador);
            rs.close();
            Iterator iterator = arr.iterator();
            respuesta = respuesta + "<reg><estado>ok</estado>";

            while(iterator.hasNext()) {
                RedSucursalDTO reg = (RedSucursalDTO)iterator.next();
                Element sucursal = doc.createElement("sucursal");
                sucursal.setAttributeNode(this.newAttr(doc, "codigo", "" + reg.getCodigoSucursal()));
                sucursal.setAttributeNode(this.newAttr(doc, "nombre", reg.getNombreSede().toUpperCase() + " - " + reg.getMunicipio().toUpperCase()));
                root.appendChild(sucursal);
            }

            arr.clear();
            respuesta = this.documentToString(doc, charCode);
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var17) {
            Utilidades.writeError("ConsultasAjax consultarSucursal  ", var17);
        }

        return respuesta;
    }

    private String buscarSucursalporMunicipio(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String nombre = comms.request.getParameter("nombre");
        if (nombre == null) {
            nombre = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        String numeroIdentificacion = comms.request.getParameter("numeroIdentificacion");
        if (numeroIdentificacion == null) {
            numeroIdentificacion = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        String nombreSucursal = comms.request.getParameter("nombreSucursal");
        if (nombreSucursal == null) {
            nombreSucursal = "";
        }

        String tipoPrestador = comms.request.getParameter("tipoPrestador");
        if (tipoPrestador == null) {
            tipoPrestador = "";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            RedSucursalDAO rs = new RedSucursalDAO();
            Collection arr = rs.cargarTodos2(numeroIdentificacion, nombre, nombreSucursal, departamento, municipio, tipoPrestador, "");
            rs.close();
            Iterator iterator = arr.iterator();

            RedSucursalDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("codigoSucursal", "codigo", Integer.toString(reg.getCodigoSucursal()), "nombre", reg.getNombreSede().toUpperCase())) {
                reg = (RedSucursalDTO)iterator.next();
            }

            arr.clear();
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var15) {
            Utilidades.writeError("ConsultasAjax consultarSucursal  ", var15);
        }

        return respuesta;
    }

    private String guardarPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int idPlan = 0;

        try {
            idPlan = Integer.parseInt(comms.request.getParameter("idPlan"));
        } catch (Exception var9) {
        }

        String elUsuario = "" + comms.session.getUser().getName();
        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            PdePlanDesarrolloDAO dao = new PdePlanDesarrolloDAO();
            dao.guardarPlan(idPlan, elUsuario);
            respuesta = respuesta + "<reg><estado>ok</estado>";
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var8) {
            Utilidades.writeError("ConsultasAjaxOld guardarPlan  ", var8);
        }

        return respuesta;
    }

    private String cargarCargos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int persona = 0;

        try {
            persona = Integer.parseInt(comms.request.getParameter("persona"));
        } catch (Exception var9) {
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            SisUsuariosDAO rs = new SisUsuariosDAO();
            SisUsuariosDTO reg = rs.cargarRegistro(persona);
            respuesta = respuesta + "<reg><estado>ok</estado>";
            respuesta = respuesta + this.agregaElemento("cargo", reg.getNombreCargoGenerico());
            respuesta = respuesta + this.agregaElemento("area", Integer.toString(reg.getArea()));
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var8) {
            Utilidades.writeError("ConsultasAjax cargarCargos  ", var8);
        }

        return respuesta;
    }

    private String buscarContratistaPorTipoPersona(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String tipoPersona = comms.request.getParameter("tipoPersona");
        if (tipoPersona == null) {
            tipoPersona = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            RedSucursalDAO rs = new RedSucursalDAO();
            Collection<RedSucursalDTO> arr = rs.cargarTodos2("", "", "", "", "", "", tipoPersona);
            rs.close();
            Iterator<RedSucursalDTO> iterator = arr.iterator();

            RedSucursalDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("codigoSucursal", "codigo", Integer.toString(reg.getCodigoSucursal()), "nombre", reg.getNombreSede().toUpperCase())) {
                reg = (RedSucursalDTO)iterator.next();
            }

            arr.clear();
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var10) {
            Utilidades.writeError("ConsultasAjax consultarSucursal  ", var10);
        }

        return respuesta;
    }

    private String buscarDocumentoExpedicionDepMunNumContratista(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String respuesta2 = "";
        String respuesta3 = "";
        String respuesta4 = "";
        String respuesta5 = "";
        String respuesta6 = "";
        String respuesta7 = "";
        String respuesta8 = "";
        String respuesta9 = "";
        String respuesta10 = "";
        String respuesta11 = "";
        String codigoSucursalContratista = comms.request.getParameter("codigoSucursalContratista");
        if (codigoSucursalContratista == null) {
            codigoSucursalContratista = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            RedSucursalDAO rs = new RedSucursalDAO();
            Collection<RedSucursalDTO> arr = rs.cargarTodos2(codigoSucursalContratista, "", "", "", "", "", "");
            rs.close();
            Iterator<RedSucursalDTO> iterator = arr.iterator();

            RedSucursalDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta11 = respuesta11 + this.agregaRegistro("FecIns", "c", "0", "fechaIns", reg.getFechaInscripcion())) {
                reg = (RedSucursalDTO)iterator.next();
                RedSucursalDAO rs2 = new RedSucursalDAO();
                String nombreDepartamento = rs2.nombreDepartamento(reg.getDepartamentosExpedicion());
                String nombreMunicipio = rs2.nombreMunicipio(reg.getMunicipioExpedicion(), reg.getDepartamentosExpedicion());
                String naturalezaJuridica = rs2.nombreNaturalezaJuridica(reg.getNaturalezaJuridica());
                rs2.close();
                respuesta = respuesta + this.agregaRegistro("DepExpedicion", "codigo", reg.getDepartamentosExpedicion(), "nombre", nombreDepartamento);
                respuesta2 = respuesta2 + this.agregaRegistro("MunExpedicion", "codigoMun", reg.getMunicipioExpedicion(), "nombreMun", nombreMunicipio);
                respuesta3 = respuesta3 + this.agregaRegistro("NumCc", "c", "0", "numero", reg.getNumeroIdentificacion());
                respuesta4 = respuesta4 + this.agregaRegistro("Tel", "c", "0", "telefono", reg.getTelefono());
                respuesta5 = respuesta5 + this.agregaRegistro("Email", "c", "0", "email", reg.getEmail());
                respuesta6 = respuesta6 + this.agregaRegistro("Dir", "c", "0", "direccion", reg.getDireccion());
                respuesta7 = respuesta7 + this.agregaRegistro("NatJur", "codigoNatJur", reg.getNaturalezaJuridica(), "nombreNatJur", naturalezaJuridica);
                respuesta8 = respuesta8 + this.agregaRegistro("NumReg", "c", "0", "numeroReg", reg.getNumeroRegistro());
                respuesta9 = respuesta9 + this.agregaRegistro("NumFol", "c", "0", "numeroFol", reg.getNumeroFolio());
                respuesta10 = respuesta10 + this.agregaRegistro("NumLib", "c", "0", "numeroLib", reg.getNumeroLibro());
            }

            arr.clear();
            respuesta = respuesta + respuesta2 + respuesta3 + respuesta4 + respuesta5 + respuesta6 + respuesta7 + respuesta8 + respuesta9 + respuesta10 + respuesta11;
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var24) {
            Utilidades.writeError("ConsultasAjax buscarDocumentoExpedicionContratista  ", var24);
        }

        return respuesta;
    }

    private String verificarCodigoHabilitacionSucursal(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String codigoHabilitacion = comms.request.getParameter("codigo");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedSucursalDAO rsIps = new RedSucursalDAO();
        if (!rsIps.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>La Busqueda No Produjo Resultados</error></reg>";
        } else {
            respuesta = respuesta + "<reg><estado>ok</estado>";
            if (rsIps.existeCodigoHabilitacion(codigoHabilitacion)) {
                respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
            } else {
                respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
            }

            respuesta = respuesta + "</reg>";
        }

        rsIps.close();
        return respuesta;
    }

    private String buscarIpsPorCiudad(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String ciudad = comms.request.getParameter("ciudad");
        String departamento = comms.request.getParameter("departamento");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedSucursalDAO rs = new RedSucursalDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        Collection arr = rs.cargarTodos("", "", "", departamento, ciudad, "IPS");
        rs.close();
        Iterator iterator = arr.iterator();

        RedSucursalDTO reg;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("ips", "codigo", reg.getCodigoSucursal() + "-" + reg.getNumeroSede(), "nombre", reg.getNombreSede())) {
            reg = (RedSucursalDTO)iterator.next();
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String buscarIpsPorCiudad2(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String ciudad = comms.request.getParameter("ciudad");
        String departamento = comms.request.getParameter("departamento");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedSucursalDAO rs = new RedSucursalDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        Collection arr = rs.cargarTodos("", "", "", departamento, ciudad, "IPS");
        rs.close();
        Iterator iterator = arr.iterator();

        RedSucursalDTO reg;
        for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("ips", "codigo", reg.getCodigoHabilitacion(), "nombre", reg.getNombreSede())) {
            reg = (RedSucursalDTO)iterator.next();
        }

        arr.clear();
        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private Attr newAttr(Document doc, String nombre, String valor) {
        Attr atrib = doc.createAttribute(nombre);
        atrib.setValue(valor);
        return atrib;
    }

    private String validarContratoPrestador(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String numeroContrato = comms.request.getParameter("numeroContrato");
        if (numeroContrato == null || numeroContrato == "undefined") {
            numeroContrato = "";
        }

        String prestador = comms.request.getParameter("prestador");
        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContContratoDAO rs = new ContContratoDAO();
        Collection arr = rs.cargarTodosPorPrestador(numeroContrato, prestador, 0);
        rs.close();
        Iterator it = arr.iterator();
        Node root = doc.createElement("root");
        doc.appendChild(root);
        Element contrato = doc.createElement("contrato");
        if (it.hasNext()) {
            contrato.setAttributeNode(this.newAttr(doc, "existe", "SI"));
        } else {
            contrato.setAttributeNode(this.newAttr(doc, "existe", "NO"));
        }

        arr.clear();
        root.appendChild(contrato);
        respuesta = this.documentToString(doc, charSet);
        return respuesta;
    }

    private String buscarContratoSucursal(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int codigoSucursal = 0;

        try {
            codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
        } catch (Exception var14) {
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContContratoDAO rs = new ContContratoDAO();
        Collection arr = rs.cargarTodos(0, "", codigoSucursal, departamento, municipio, "", "", "", "", "", "", "", "", "", "", "");
        rs.close();
        Iterator it = arr.iterator();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        while(it.hasNext()) {
            ContContratoDTO reg = (ContContratoDTO)it.next();
            Element contrato = doc.createElement("contrato");
            contrato.setAttributeNode(this.newAttr(doc, "consecutivoContrato", "" + reg.getConsecutivoContrato()));
            contrato.setAttributeNode(this.newAttr(doc, "numeroContrato", "" + reg.getNumeroContrato()));
            root.appendChild(contrato);
        }

        arr.clear();
        respuesta = this.documentToString(doc, charSet);
        return respuesta;
    }

    private String buscarContratoImputacionSucursal(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int codigoSucursal = 0;

        try {
            codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
        } catch (Exception var13) {
        }

        String fecha = comms.request.getParameter("fecha");
        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContContratoDAO rs = new ContContratoDAO();
        Collection arr = rs.cargarTodosVigenteSucursal(codigoSucursal, fecha);
        rs.close();
        Iterator it = arr.iterator();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        while(it.hasNext()) {
            ContContratoDTO reg = (ContContratoDTO)it.next();
            Element contrato = doc.createElement("contrato");
            contrato.setAttributeNode(this.newAttr(doc, "consecutivoContrato", "" + reg.getConsecutivoContrato()));
            contrato.setAttributeNode(this.newAttr(doc, "numeroContrato", "" + reg.getNumeroContrato() + " " + reg.getImputaciones()));
            root.appendChild(contrato);
        }

        arr.clear();
        respuesta = this.documentToString(doc, charSet);
        return respuesta;
    }

    private String buscarContratoSucursalJSON(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int codigoSucursal = 0;

        try {
            codigoSucursal = Integer.parseInt(comms.request.getParameter("codigoSucursal"));
        } catch (Exception var11) {
        }

        String numeroContrato = comms.request.getParameter("numeroContrato");
        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContContratoDAO rs = new ContContratoDAO();
        Collection arr = rs.cargarTodosPorPrestador(numeroContrato, "", codigoSucursal);
        rs.close();
        Iterator it = arr.iterator();

        ContContratoDTO reg;
        for(respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?><results>"; it.hasNext(); respuesta = respuesta + "<rs id=\"" + reg.getNumeroContrato() + "\" info=\"" + reg.getNombreEstado() + " Fecha Inicio: " + Utilidades.darFormatoFecha(reg.getFechaInicio()) + " Fecha Fin: " + Utilidades.darFormatoFecha(reg.getFechaFinal()) + "\">" + reg.getNumeroContrato() + "</rs>") {
            reg = (ContContratoDTO)it.next();
        }

        arr.clear();
        respuesta = respuesta + "</results>";
        return respuesta;
    }

    private String cargarCausalTraslado(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int codigo = -1;

        try {
            codigo = Integer.parseInt(comms.request.getParameter("codigo"));
        } catch (Exception var12) {
        }

        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        SisMultiValoresDAO rs = new SisMultiValoresDAO();
        Collection arr = null;
        if (codigo == 0) {
            arr = rs.cargarTabla("causal_negacion");
        } else if (codigo == 1) {
            arr = rs.cargarTabla("causal_aceptacion");
        }

        rs.close();
        Iterator it = arr.iterator();
        Node root = doc.createElement("root");
        doc.appendChild(root);

        while(it.hasNext()) {
            SisMultiValoresDTO reg = (SisMultiValoresDTO)it.next();
            Element causalEstado = doc.createElement("causalEstado");
            causalEstado.setAttributeNode(this.newAttr(doc, "codigo", "" + reg.getCodigo()));
            causalEstado.setAttributeNode(this.newAttr(doc, "nombre", "" + reg.getDescripcion()));
            root.appendChild(causalEstado);
        }

        arr.clear();
        respuesta = this.documentToString(doc, charSet);
        return respuesta;
    }

    private String buscarPorcentajePoliza(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var18) {
        }

        String poliza = comms.request.getParameter("tipoPoliza");
        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "ISO-8859-1";
        }

        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContPolizaDAO rs = new ContPolizaDAO();
        double porcentajePoliza = rs.cargarPorcentajePoliza(numeroEstudio, poliza);
        rs.close();
        SisMultiValoresDAO rsSis = new SisMultiValoresDAO();
        SisMultiValoresDTO regSis = rsSis.cargarRegistroNombre("tipo_poliza", poliza);
        rsSis.close();
        ParametrosAplicacionDAO rsPar = new ParametrosAplicacionDAO();
        double duracionPoliza = 0.0D;

        try {
            duracionPoliza = Double.parseDouble(rsPar.getValor("real", "", regSis.getCodigo()));
        } catch (Exception var17) {
        }

        rsPar.close();
        Node root = doc.createElement("root");
        doc.appendChild(root);
        Element porcentaje = doc.createElement("porcen");
        porcentaje.setAttributeNode(this.newAttr(doc, "porcentaje", "" + porcentajePoliza));
        porcentaje.setAttributeNode(this.newAttr(doc, "plazo", "" + duracionPoliza));
        root.appendChild(porcentaje);
        respuesta = this.documentToString(doc, charSet);
        return respuesta;
    }

    private String consultarNumeroEstudio(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int numeroEstudio = 0;

        try {
            numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
        } catch (Exception var7) {
        }

        String respuesta = "";
        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        ContEstudioPrevioDAO ob = new ContEstudioPrevioDAO();
        respuesta = respuesta + "<reg><estado>ok</estado>";
        if (ob.existeNumeroEstudio(numeroEstudio)) {
            respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
        } else {
            respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
        }

        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String consultarPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        int idPlan = 0;

        try {
            idPlan = Integer.parseInt(comms.request.getParameter("idPlan"));
        } catch (Exception var7) {
        }

        String respuesta = "";
        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
        respuesta = respuesta + "<reg><estado>ok</estado>";
        if (ob.planCargado(idPlan)) {
            respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
        } else {
            respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
        }

        respuesta = respuesta + "</reg>";
        return respuesta;
    }

    private String verificarContrato(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String numeroContrato = comms.request.getParameter("numeroContrato");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        ContContratoDAO rs = new ContContratoDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>La Busqueda No Produjo Resultados</error></reg>";
        } else {
            respuesta = respuesta + "<reg><estado>ok</estado>";
            if (rs.existeContrato(numeroContrato)) {
                respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
            } else {
                respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
            }

            respuesta = respuesta + "</reg>";
        }

        rs.close();
        return respuesta;
    }

    private String buscarSucursal(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String nombre = comms.request.getParameter("nombre");
        if (nombre == null) {
            nombre = "";
        }

        String departamento = comms.request.getParameter("departamento");
        if (departamento == null) {
            departamento = "";
        }

        String municipio = comms.request.getParameter("municipio");
        if (municipio == null) {
            municipio = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            Node root = doc.createElement("root");
            doc.appendChild(root);
            RedSucursalDAO rs = new RedSucursalDAO();
            Collection arr = rs.cargarTodosDistinct(nombre, departamento, municipio);
            rs.close();
            Iterator iterator = arr.iterator();
            respuesta = respuesta + "<reg><estado>ok</estado>";

            while(iterator.hasNext()) {
                RedSucursalDTO reg = (RedSucursalDTO)iterator.next();
                Element sucursal = doc.createElement("sucursal");
                sucursal.setAttributeNode(this.newAttr(doc, "codigo", "" + reg.getCodigoSucursal()));
                sucursal.setAttributeNode(this.newAttr(doc, "nombre", reg.getNombreSede().toUpperCase() + " - " + reg.getMunicipio().toUpperCase()));
                root.appendChild(sucursal);
            }

            arr.clear();
            respuesta = this.documentToString(doc, charCode);
        } catch (Exception var14) {
            Utilidades.writeError("ConsultasAjax: buscarSucursal ", var14);
        }

        return respuesta;
    }

    private String verificarNit(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String numeroIdentificacion = comms.request.getParameter("codigo");
        String respuesta = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>";
        RedPrestadorDAO rsIps = new RedPrestadorDAO();
        if (!rsIps.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>La Busqueda No Produjo Resultados</error></reg>";
        } else {
            respuesta = respuesta + "<reg><estado>ok</estado>";
            if (rsIps.existeNit(numeroIdentificacion)) {
                respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
            } else {
                respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
            }

            respuesta = respuesta + "</reg>";
        }

        rsIps.close();
        return respuesta;
    }

    private String buscarPersonas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String apellidos = comms.request.getParameter("apellidosCliente");
        String nombreCliente = comms.request.getParameter("nombreCliente");
        System.out.println(apellidos);
        if (nombreCliente == null) {
            nombreCliente = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "UTF-8";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            SisUsuariosDAO rs = new SisUsuariosDAO();
            Collection arr = rs.cargarSimilares(apellidos, nombreCliente);
            Iterator<SisUsuariosDTO> iterator = arr.iterator();
            respuesta = respuesta + "<reg><estado>ok</estado>";

            while(iterator.hasNext()) {
                SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
                respuesta = respuesta + this.agregaRegistro("persona", "codigo", "" + reg.getCodigoEmpleado(), "nombre", "" + reg.getApellidos() + " " + reg.getNombres());
                System.out.println(reg.getApellidos() + " " + reg.getNombres());
            }

            arr.clear();
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var11) {
            Utilidades.writeError("ConsultasAjax consultarSucursal  ", var11);
        }

        return respuesta;
    }

    private String cargarImputaciones2(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String fecha = comms.request.getParameter("fecha");
        Document doc = this.newDocument();
        String charSet = comms.request.getParameter("charset");
        if (charSet == null) {
            charSet = "iso-8859-1";
        }

        String respuesta = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        FechaDTO newFecha = new FechaDTO(fecha);
        if (newFecha.getAnno() == -1) {
            respuesta = respuesta + "<reg><estado>''</estado></reg>";
        }

        ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
        if (!rs.getEstadoConexion()) {
            respuesta = respuesta + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
            return respuesta;
        } else {
            Collection<ContImputacionPresupuestalDTO> arr = rs.cargarTodos("", "", newFecha.getAnno());
            rs.close();
            Node root = doc.createElement("root");
            doc.appendChild(root);
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                ContImputacionPresupuestalDTO reg = (ContImputacionPresupuestalDTO)iterator.next();
                Element actividad = doc.createElement("imputacion");
                actividad.setAttributeNode(this.newAttr(doc, "codigo", "" + reg.getCodigoImputacion()));
                actividad.setAttributeNode(this.newAttr(doc, "nombre", "" + reg.getDescripcion()));
                root.appendChild(actividad);
            }

            arr.clear();
            respuesta = this.documentToString(doc, charSet);
            return respuesta;
        }
    }

    private String buscarDocumentosRespaldo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String tipoPersona = comms.request.getParameter("tipoPersona");
        if (tipoPersona == null) {
            tipoPersona = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            SisMultiValoresDAO rs = new SisMultiValoresDAO();
            if (tipoPersona.equals("Natural")) {
                tipoPersona = "N%";
            } else {
                tipoPersona = "%J%";
            }

            Collection<SisMultiValoresDTO> arr = rs.cargarTabla("documentos_respaldo_terceros", "A", tipoPersona);
            rs.close();
            Iterator<SisMultiValoresDTO> iterator = arr.iterator();

            SisMultiValoresDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("documento", "codigo", reg.getCodigo(), "nombre", reg.getDescripcion())) {
                reg = (SisMultiValoresDTO)iterator.next();
            }

            arr.clear();
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var10) {
            Utilidades.writeError("ConsultasAjax consultarDocumentos  ", var10);
        }

        return respuesta;
    }

    private String verificarDocumentosNOObtenidos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String tipoPersona = comms.request.getParameter("tpPersona");
        if (tipoPersona == null) {
            tipoPersona = "";
        }

        String numeroIdentificacion = comms.request.getParameter("codigo");
        if (tipoPersona == null) {
            tipoPersona = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            SisMultiValoresDAO rs = new SisMultiValoresDAO();
            if (tipoPersona.equals("Natural")) {
                tipoPersona = "N%";
            } else {
                tipoPersona = "%J%";
            }

            Collection<SisMultiValoresDTO> arr = rs.cargarTablaConIdentificacion("documentos_respaldo_terceros", "A", tipoPersona, numeroIdentificacion);
            rs.close();
            Iterator<SisMultiValoresDTO> iterator = arr.iterator();

            SisMultiValoresDTO reg;
            for(respuesta = respuesta + "<reg><estado>ok</estado>"; iterator.hasNext(); respuesta = respuesta + this.agregaRegistro("documento", "codigo", reg.getCodigo(), "nombre", reg.getDescripcion())) {
                reg = (SisMultiValoresDTO)iterator.next();
            }

            arr.clear();
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var11) {
            Utilidades.writeError("ConsultasAjax consultarDocumentosNoObtenidos  ", var11);
        }

        return respuesta;
    }

    private String convertirNumero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        double valor = 0.0D;

        try {
            valor = Double.parseDouble(comms.request.getParameter("numero"));
        } catch (Exception var11) {
            throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            LetrasDTO valorCont = new LetrasDTO(valor);
            String valotTexto = valorCont.toString();
            String valorLetras = "";
            valorLetras = valotTexto.replace("M/CTE", "");
            respuesta = respuesta + "<reg><estado>ok</estado>";
            respuesta = respuesta + this.agregaElemento("numero", valorLetras);
            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var10) {
            Utilidades.writeError("ConsultasAjax convertirNumero  ", var10);
        }

        return respuesta;
    }

    private String buscarDatosInterventor(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
        String respuesta = "";
        String tipoDocumento = comms.request.getParameter("tipoDocumento");
        if (tipoDocumento == null) {
            tipoDocumento = "";
        }

        String numeroDocumento = comms.request.getParameter("numeroDocumento");
        if (numeroDocumento == null) {
            numeroDocumento = "";
        }

        String charCode = comms.request.getParameter("charCode");
        if (charCode == null) {
            charCode = "iso-8859-1";
        }

        Document doc = this.newDocument();
        if (doc == null) {
            respuesta = "<?xml version=\"1.0\" encoding=\"" + charCode + "\"?>" + "<reg><estado>err</estado><error>causa desconocida</error></reg>";
        }

        try {
            ContInterventorDAO rs = new ContInterventorDAO();
            ContInterventorDTO reg = rs.cargarRegistro(tipoDocumento, numeroDocumento);
            rs.close();
            respuesta = respuesta + "<reg><estado>ok</estado>";
            if (reg != null) {
                respuesta = respuesta + this.agregaElemento("tipoDoc", "" + reg.getTipoDocumento());
                respuesta = respuesta + this.agregaElemento("numDoc", "" + Long.toString(reg.getNumeroDocumento()));
                respuesta = respuesta + this.agregaElemento("apellidos", "" + reg.getApellidos());
                respuesta = respuesta + this.agregaElemento("nombres", "" + reg.getNombres());
                respuesta = respuesta + this.agregaElemento("direccion", "" + reg.getDireccion());
                respuesta = respuesta + this.agregaElemento("telefono", "" + reg.getTelefono());
                respuesta = respuesta + this.agregaElemento("fechaInser", "" + reg.getFechaInsercion());
                respuesta = respuesta + this.agregaElemento("usuarioInser", "" + reg.getUsuarioInsercion());
                respuesta = respuesta + this.agregaElemento("fechaMod", "" + reg.getFechaModificacion());
                respuesta = respuesta + this.agregaElemento("usuaarioMod", "" + reg.getUsuarioModificacion());
                respuesta = respuesta + "<respuesta><existe>true</existe></respuesta>";
            } else {
                respuesta = respuesta + "<respuesta><existe>false</existe></respuesta>";
            }

            respuesta = respuesta + "</reg>";
            Utilidades.grabarLog("respuesta=" + respuesta);
        } catch (Exception var9) {
            Utilidades.writeError("ConsultasAjax buscarDatosInterventor  ", var9);
        }

        return respuesta;
    }
}
