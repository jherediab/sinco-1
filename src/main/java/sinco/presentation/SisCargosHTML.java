//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.presentation;

import java.lang.reflect.Field;
import org.enhydra.xml.xmlc.XMLCRuntimeException;
import org.enhydra.xml.xmlc.XMLObject;
import org.enhydra.xml.xmlc.deferredparsing.DocumentLoader;
import org.enhydra.xml.xmlc.deferredparsing.StandardDocumentLoader;
import org.enhydra.xml.xmlc.dom.XMLCDomFactory;
import org.enhydra.xml.xmlc.dom.XMLCDomFactoryCache;
import org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory;
import org.enhydra.xml.xmlc.html.HTMLObject;
import org.enhydra.xml.xmlc.html.HTMLObjectImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDivElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLInputElement;
import org.w3c.dom.html.HTMLLabelElement;
import org.w3c.dom.html.HTMLScriptElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class SisCargosHTML extends HTMLObjectImpl implements XMLObject, HTMLObject {
    private HTMLInputElement $element_BtnAyuda;
    private HTMLInputElement $element_BtnConsultar;
    private HTMLInputElement $element_BtnCrear;
    private HTMLInputElement $element_BtnEliminar;
    private HTMLInputElement $element_BtnGrabar;
    private HTMLInputElement $element_BtnModificar;
    private HTMLInputElement $element_BtnPrincipal;
    private HTMLInputElement $element_BtnSalir;
    private HTMLInputElement $element_Codigo;
    private HTMLElement $element_CodigoEd;
    private HTMLInputElement $element_CodigoKey;
    private HTMLInputElement $element_Descripcion;
    private HTMLElement $element_DescripcionEd;
    private HTMLTableSectionElement $element_Detalle;
    private HTMLDivElement $element_DivConsulta;
    private HTMLDivElement $element_DivCreacionRegistro;
    private HTMLDivElement $element_DivEdicion;
    private HTMLDivElement $element_DivResultados;
    private HTMLScriptElement $element_ElMenu;
    private HTMLInputElement $element_FechaInsercion;
    private HTMLElement $element_FechaInsercionEd;
    private HTMLInputElement $element_FechaModificacion;
    private HTMLElement $element_FechaModificacionEd;
    private HTMLLabelElement $element_LabelNombre;
    private HTMLElement $element_NroRegistros;
    private HTMLInputElement $element_UsuarioInsercion;
    private HTMLElement $element_UsuarioInsercionEd;
    private HTMLInputElement $element_UsuarioModificacion;
    private HTMLElement $element_UsuarioModificacionEd;
    private HTMLInputElement $element__operacion;
    public static final String CLASS_BOB = "BOB";
    public static final String CLASS_BOT = "BOT";
    public static final String CLASS_IND = "IND";
    public static final String CLASS_INP = "INP";
    public static final String CLASS_PIE = "PIE";
    public static final String CLASS_ca = "ca";
    public static final String CLASS_cb = "cb";
    public static final String CLASS_cf1 = "cf1";
    public static final String CLASS_cf2 = "cf2";
    public static final String CLASS_container = "container";
    public static final String CLASS_dat = "dat";
    public static final String CLASS_dat2 = "dat2";
    public static final String CLASS_datc = "datc";
    public static final String CLASS_resizable = "resizable";
    public static final String CLASS_sortable = "sortable";
    public static final String CLASS_tabf = "tabf";
    public static final String CLASS_tit = "tit";
    public static final String NAME__operacion = "_operacion";
    public static final String NAME_codigo = "codigo";
    public static final String NAME_descripcion = "descripcion";
    public static final String NAME_fechaInsercion = "fechaInsercion";
    public static final String NAME_fechaModificacion = "fechaModificacion";
    public static final String NAME_ff = "ff";
    public static final String NAME_labelNombre = "labelNombre";
    public static final String NAME_usuarioInsercion = "usuarioInsercion";
    public static final String NAME_usuarioModificacion = "usuarioModificacion";
    public static final Class XMLC_GENERATED_CLASS = SisCargosHTML.class;
    public static final String XMLC_SOURCE_FILE = "sinco/presentation/SisCargos.html";
    private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(LazyHTMLDomFactory.class);
    private final DocumentLoader fDocumentLoader;

    public SisCargosHTML() {
        this(StandardDocumentLoader.getInstance());
        this.buildDocument();
    }

    public SisCargosHTML(boolean buildDOM) {
        this(StandardDocumentLoader.getInstance(), buildDOM);
    }

    public SisCargosHTML(SisCargosHTML src) {
        this.fDocumentLoader = src.getDocumentLoader();
        this.setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
        this.syncAccessMethods();
    }

    public SisCargosHTML(DocumentLoader loader, boolean buildDOM) {
        this.fDocumentLoader = loader;
        if (buildDOM) {
            this.buildDocument();
        }

    }

    public SisCargosHTML(DocumentLoader loader) {
        this(loader, true);
    }

    public void buildDocument() {
        this.setDocument(this.getDocumentLoader().getDocument(this.getClass()), "text/html", "ISO-8859-1");
        this.syncAccessMethods();
    }

    public Node cloneNode(boolean deep) {
        this.cloneDeepCheck(deep);
        return new SisCargosHTML(this);
    }

    protected final DocumentLoader getDocumentLoader() {
        return this.fDocumentLoader;
    }

    protected final XMLCDomFactory getDomFactory() {
        return fDOMFactory;
    }

    public HTMLInputElement getElementBtnAyuda() {
        return this.$element_BtnAyuda;
    }

    public HTMLInputElement getElementBtnConsultar() {
        return this.$element_BtnConsultar;
    }

    public HTMLInputElement getElementBtnCrear() {
        return this.$element_BtnCrear;
    }

    public HTMLInputElement getElementBtnEliminar() {
        return this.$element_BtnEliminar;
    }

    public HTMLInputElement getElementBtnGrabar() {
        return this.$element_BtnGrabar;
    }

    public HTMLInputElement getElementBtnModificar() {
        return this.$element_BtnModificar;
    }

    public HTMLInputElement getElementBtnPrincipal() {
        return this.$element_BtnPrincipal;
    }

    public HTMLInputElement getElementBtnSalir() {
        return this.$element_BtnSalir;
    }

    public HTMLInputElement getElementCodigo() {
        return this.$element_Codigo;
    }

    public HTMLElement getElementCodigoEd() {
        return this.$element_CodigoEd;
    }

    public HTMLInputElement getElementCodigoKey() {
        return this.$element_CodigoKey;
    }

    public HTMLInputElement getElementDescripcion() {
        return this.$element_Descripcion;
    }

    public HTMLElement getElementDescripcionEd() {
        return this.$element_DescripcionEd;
    }

    public HTMLTableSectionElement getElementDetalle() {
        return this.$element_Detalle;
    }

    public HTMLDivElement getElementDivConsulta() {
        return this.$element_DivConsulta;
    }

    public HTMLDivElement getElementDivCreacionRegistro() {
        return this.$element_DivCreacionRegistro;
    }

    public HTMLDivElement getElementDivEdicion() {
        return this.$element_DivEdicion;
    }

    public HTMLDivElement getElementDivResultados() {
        return this.$element_DivResultados;
    }

    public HTMLScriptElement getElementElMenu() {
        return this.$element_ElMenu;
    }

    public HTMLInputElement getElementFechaInsercion() {
        return this.$element_FechaInsercion;
    }

    public HTMLElement getElementFechaInsercionEd() {
        return this.$element_FechaInsercionEd;
    }

    public HTMLInputElement getElementFechaModificacion() {
        return this.$element_FechaModificacion;
    }

    public HTMLElement getElementFechaModificacionEd() {
        return this.$element_FechaModificacionEd;
    }

    public HTMLLabelElement getElementLabelNombre() {
        return this.$element_LabelNombre;
    }

    public HTMLElement getElementNroRegistros() {
        return this.$element_NroRegistros;
    }

    public HTMLInputElement getElementUsuarioInsercion() {
        return this.$element_UsuarioInsercion;
    }

    public HTMLElement getElementUsuarioInsercionEd() {
        return this.$element_UsuarioInsercionEd;
    }

    public HTMLInputElement getElementUsuarioModificacion() {
        return this.$element_UsuarioModificacion;
    }

    public HTMLElement getElementUsuarioModificacionEd() {
        return this.$element_UsuarioModificacionEd;
    }

    public HTMLInputElement getElement_operacion() {
        return this.$element__operacion;
    }

    public void setTextCodigoEd(String text) {
        this.doSetText(this.$element_CodigoEd, text);
    }

    public void setTextDescripcionEd(String text) {
        this.doSetText(this.$element_DescripcionEd, text);
    }

    public void setTextDivConsulta(String text) {
        this.doSetText(this.$element_DivConsulta, text);
    }

    public void setTextDivCreacionRegistro(String text) {
        this.doSetText(this.$element_DivCreacionRegistro, text);
    }

    public void setTextDivEdicion(String text) {
        this.doSetText(this.$element_DivEdicion, text);
    }

    public void setTextDivResultados(String text) {
        this.doSetText(this.$element_DivResultados, text);
    }

    public void setTextElMenu(String text) {
        this.doSetText(this.$element_ElMenu, text);
    }

    public void setTextFechaInsercionEd(String text) {
        this.doSetText(this.$element_FechaInsercionEd, text);
    }

    public void setTextFechaModificacionEd(String text) {
        this.doSetText(this.$element_FechaModificacionEd, text);
    }

    public void setTextLabelNombre(String text) {
        this.doSetText(this.$element_LabelNombre, text);
    }

    public void setTextNroRegistros(String text) {
        this.doSetText(this.$element_NroRegistros, text);
    }

    public void setTextUsuarioInsercionEd(String text) {
        this.doSetText(this.$element_UsuarioInsercionEd, text);
    }

    public void setTextUsuarioModificacionEd(String text) {
        this.doSetText(this.$element_UsuarioModificacionEd, text);
    }

    protected void syncWithDocument(Node node) {
        if (node.getNodeType() != 9) {
            throw new XMLCRuntimeException("Node must be a document node");
        } else {
            Document doc = (Document)node;

            try {
                Field[] fs = this.getClass().getDeclaredFields();
                int substStart = "$element_".length();

                for(int i = 0; i < fs.length; ++i) {
                    Field f = fs[i];
                    if (f.getName().startsWith("$element_")) {
                        String id = f.getName().substring(substStart);
                        Node idNode = doc.getElementById(id);
                        if (idNode == null) {
                            id = id.substring(0, 1).toLowerCase() + id.substring(1);
                            idNode = doc.getElementById(id);
                        }

                        if (idNode != null) {
                            f.set(this, idNode);
                        }
                    }
                }

            } catch (Exception var9) {
                throw new XMLCRuntimeException("Error reflecting on element access fields", var9);
            }
        }
    }
}
