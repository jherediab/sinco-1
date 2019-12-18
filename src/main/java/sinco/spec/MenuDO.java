//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

public class MenuDO {
    private static Cache elcache;

    public MenuDO() {
    }

    public static void iniciarMenu() {
        elcache = new Cache();
        elcache.getInstance();
    }

    public static String getMenu(String usuario) {
        if (elcache == null) {
            return "var MENU_ITEMS = [['Problemas Recuperando el menu', null, null,],];";
        } else {
            String elMenu = elcache.getObject(usuario);
            return elMenu == null ? "var MENU_ITEMS = [['Problemas Recuperando el menu', null, null,],];" : elMenu;
        }
    }

    public static boolean setMenu(String usuario, StringBuffer buf) {
        elcache.addObject(usuario, buf);
        return true;
    }

    public static boolean expire(String usuario) {
        elcache.expire(usuario);
        return true;
    }

    public static boolean existe(String usuario) {
        String elMenu = elcache.getObject(usuario);
        return elMenu != null;
    }
}
