//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import java.util.Collection;
import java.util.Iterator;
import sinco.business.ParametrosDTO;
import sinco.business.SisAplicacionesDTO;
import sinco.data.SisAplicacionesDAO;

public class MenuFactory {
    StringBuffer dos;

    public MenuFactory() {
    }

    private void agregarOpcion(int grupo, SisAplicacionesDTO reg, String modulo) {
        if (!reg.getTipoAplicacion().equals("M") && !reg.getTipoAplicacion().equals("S")) {
            if (reg.getTipoAplicacion().equals("I")) {
                if (!reg.getLinkAplicacion().contains(".po")) {
                    this.grabarLinea(" ['" + reg.getDescripcion() + "', '" + ParametrosDTO.getString(reg.getLinkAplicacion()) + "'" + (reg.getEntorno().length() > 0 ? ",{'tw':'" + reg.getEntorno() + "'}" : "") + "],");
                } else {
                    this.grabarLinea(" ['" + reg.getDescripcion() + "', '" + reg.getLinkAplicacion() + "'" + (reg.getEntorno().length() > 0 ? ",{'tw':'" + reg.getEntorno() + "'}" : "") + "],");
                }
            }
        } else {
            if (reg.getTipoAplicacion().equals("M")) {
                this.grabarLinea(" [wrap_blue('" + reg.getDescripcion() + "'), null, {'sw':" + reg.getAncho() + ", 'bw':+" + reg.getAlto() + "},");
            } else {
                this.grabarLinea("  ['" + reg.getDescripcion() + "', null, {'sw':" + reg.getAncho() + ", 'bw':" + reg.getAlto() + "},");
            }

            SisAplicacionesDAO rs = new SisAplicacionesDAO();
            Collection arr = rs.cargarSubMenu(grupo, reg.getAplicacion(), modulo);
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                SisAplicacionesDTO submenu = (SisAplicacionesDTO)iterator.next();
                this.agregarOpcion(grupo, submenu, modulo);
            }

            this.grabarLinea("],");
        }

    }

    public boolean crearMenu(String idNav, int grupo, String modulo) {
        if (MenuDO.existe(idNav)) {
            MenuDO.expire(idNav);
        }

        if (!this.abrirArchivo()) {
            return false;
        } else {
            SisAplicacionesDAO rs = new SisAplicacionesDAO();
            Collection arr = rs.cargarMenuPrincipal(grupo, modulo);
            this.grabarLinea("var MENU_ITEMS0 = [");
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                SisAplicacionesDTO reg = (SisAplicacionesDTO)iterator.next();
                this.agregarOpcion(grupo, reg, modulo);
            }

            this.grabarLinea("];\n");
            this.grabarLinea("function wrap_blue (text) {\n");
            this.grabarLinea("\tvar res=[];\n");
            this.grabarLinea("\tfor (var i=0; i<3; i++)\n");
            this.grabarLinea("\t   res[i]=['<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td><img src=\"img/bluey',");
            this.grabarLinea("\t\t(i?2:1),'_l.gif\" width=\"8\" height=\"23\" border=\"0\"></td><td background=\"img/bluey',");
            this.grabarLinea("\t\t(i?2:1),'_m.gif\" style=\"font-family: tahoma, verdana, arial; font-size: 11px; font-weight: bold; color: #FFFFFF\" width=\"100%\">',text,'</td><td><img src=\"img/bluey',");
            this.grabarLinea("\t\t(i?2:1),'_r.gif\" width=\"8\" height=\"23\" border=\"0\"></td></tr></table>'\n");
            this.grabarLinea("\t].join('');");
            this.grabarLinea("\treturn res;");
            this.grabarLinea("}");
            this.cerrarArchivo(idNav);
            return true;
        }
    }

    private boolean abrirArchivo() {
        try {
            this.dos = new StringBuffer("");
            return true;
        } catch (Exception var2) {
            var2.printStackTrace();
            System.out.println("writeFile: caught i/o exception:");
            return false;
        }
    }

    private boolean grabarLinea(String s) {
        try {
            this.dos.append(s);
            return true;
        } catch (Exception var3) {
            var3.printStackTrace();
            System.out.println("writeFile: caught i/o exception:");
            return false;
        }
    }

    private void cerrarArchivo(String usuario) {
        MenuDO.setMenu(usuario, this.dos);
    }
}
