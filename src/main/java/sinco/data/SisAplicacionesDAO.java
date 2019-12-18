//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisAplicacionesDTO;
import sinco.business.Utilidades;

public class SisAplicacionesDAO {
    public SisAplicacionesDAO() {
    }

    public SisAplicacionesDTO leerRegistro(ResultSet rs) {
        try {
            SisAplicacionesDTO reg = new SisAplicacionesDTO();
            reg.setAplicacion(rs.getString("aplicacion"));
            reg.setDescripcion(rs.getString("descripcion"));
            reg.setModulo(rs.getString("modulo"));
            reg.setOrdenAplicacion(rs.getInt("orden_aplicacion"));
            reg.setLinkAplicacion(rs.getString("link_aplicacion"));
            reg.setTipoAplicacion(rs.getString("tipo_aplicacion"));
            reg.setMenuAplicacion(rs.getString("menu_aplicacion"));
            reg.setAncho(rs.getInt("ancho"));
            reg.setAlto(rs.getInt("alto"));
            reg.setEntorno(rs.getString("entorno"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));

            try {
                reg.setNombreTipoAplicacion(rs.getString("nombre_tipo_aplicacion"));
            } catch (Exception var4) {
            }

            return reg;
        } catch (Exception var5) {
            var5.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:leerRegistro ", var5);
            return null;
        }
    }

    public Collection<SisAplicacionesDTO> cargarTodos(String aplicacion, String descripcion, String modulo, String tipoAplicacion, String menuAplicacion) {
        Collection<SisAplicacionesDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var11;
        try {
            String s = "select t.aplicacion,t.descripcion,t.modulo,t.orden_aplicacion,t.link_aplicacion,t.tipo_aplicacion,m1.DESCRIPCION as nombre_tipo_aplicacion,t.menu_aplicacion,t.ancho,t.alto,t.entorno,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from SIS_APLICACIONES t  left join sis_multivalores m1 on (m1.tabla='tipo_aplicacion' and m1.valor=t.tipo_aplicacion) where 1=1";
            if (aplicacion.length() > 0) {
                s = s + " and upper(t.aplicacion) like upper('%" + aplicacion + "%')";
            }

            if (descripcion.length() > 0) {
                s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
            }

            if (modulo.length() > 0) {
                s = s + " and upper(t.modulo) like upper('%" + modulo + "%')";
            }

            if (tipoAplicacion.length() > 0) {
                s = s + " and upper(t.tipo_aplicacion) like upper('%" + tipoAplicacion + "%')";
            }

            if (menuAplicacion.length() > 0) {
                s = s + " and upper(t.menu_aplicacion) like upper('%" + menuAplicacion + "%')";
            }

            s = s + " order by t.descripcion";
            boolean rtaDB = dat.parseSql(s);
            if (rtaDB) {
                ResultSet rs = dat.getResultSet();

                while(rs.next()) {
                    resultados.add(this.leerRegistro(rs));
                }

                return resultados;
            }

            var11 = (ArrayList) resultados;
        } catch (Exception var15) {
            var15.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:cargarTodos ", var15);
            return resultados;
        } finally {
            dat.close();
        }

        return var11;
    }

    public SisAplicacionesDTO cargarRegistro(String aplicacion) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.aplicacion,t.descripcion,t.modulo,t.orden_aplicacion,t.link_aplicacion,t.tipo_aplicacion,m1.DESCRIPCION as nombre_tipo_aplicacion,t.menu_aplicacion,t.ancho,t.alto,t.entorno,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from SIS_APLICACIONES t  left join sis_multivalores m1 on (m1.tabla='tipo_aplicacion' and m1.valor=t.tipo_aplicacion) where  t.aplicacion='" + aplicacion + "'" + "";
            boolean rtaDB = dat.parseSql(s);
            SisAplicacionesDTO var6;
            if (!rtaDB) {
                var6 = null;
                return var6;
            }

            ResultSet rs = dat.getResultSet();
            if (rs.next()) {
                var6 = this.leerRegistro(rs);
                return var6;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:cargarSisAplicaciones", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public RespuestaBD eliminarRegistro(String aplicacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from SIS_APLICACIONES where  aplicacion='" + aplicacion + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(String aplicacion, String descripcion, String modulo, int ordenAplicacion, String linkAplicacion, String tipoAplicacion, String menuAplicacion, int ancho, int alto, String entorno, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into SIS_APLICACIONES(aplicacion,descripcion,modulo,orden_aplicacion,link_aplicacion,tipo_aplicacion,menu_aplicacion,ancho,alto,entorno,fecha_insercion,usuario_insercion) values ('" + aplicacion + "'," + "'" + descripcion + "'," + "'" + modulo + "'," + "" + ordenAplicacion + "," + "'" + linkAplicacion + "'," + "'" + tipoAplicacion + "'," + "'" + menuAplicacion + "'," + "" + ancho + "," + "" + alto + "," + "'" + entorno + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var18) {
            var18.printStackTrace();
            Utilidades.writeError("%SisAplicacionesDAO:crearRegistro ", var18);
            rta.setMensaje(var18.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(String aplicacion, String descripcion, String modulo, int ordenAplicacion, String linkAplicacion, String tipoAplicacion, String menuAplicacion, int ancho, int alto, String entorno, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update SIS_APLICACIONES set  descripcion='" + descripcion + "'," + " modulo='" + modulo + "'," + " orden_aplicacion=" + ordenAplicacion + "," + " link_aplicacion='" + linkAplicacion + "'," + " tipo_aplicacion='" + tipoAplicacion + "'," + " menu_aplicacion='" + menuAplicacion + "'," + " ancho=" + ancho + "," + " alto=" + alto + "," + " entorno='" + entorno + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " aplicacion='" + aplicacion + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var18) {
            var18.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:modificarRegistro ", var18);
            rta.setMensaje(var18.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public Collection<SisAplicacionesDTO> cargarMenuPrincipal(int grupo, String modulo) {
        Collection<SisAplicacionesDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select a.* from sis_aplicaciones a, Sis_Permisos_Grupo p where a.Aplicacion = p.permiso and p.grupo=" + grupo + " and a.modulo='" + modulo + "'" + " and a.tipo_aplicacion='M'" + " order by a.orden_aplicacion";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var8 = (ArrayList) resultados;
                return var8;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                resultados.add(this.leerRegistro(rs));
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:cargarTodos ", var12);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public Collection<SisAplicacionesDTO> cargarSubMenu(int grupo, String menu, String modulo) {
        Collection<SisAplicacionesDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select a.* from sis_aplicaciones a, Sis_Permisos_Grupo p where a.Aplicacion = p.permiso and p.grupo=" + grupo + " and a.modulo='" + modulo + "'" + " and a.tipo_aplicacion in('S','I')" + " and a.menu_aplicacion='" + menu + "'" + " order by a.orden_aplicacion";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var9 = (ArrayList) resultados;
                return var9;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                resultados.add(this.leerRegistro(rs));
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("SisAplicacionesDAO:cargarTodos ", var13);
        } finally {
            dat.close();
        }

        return resultados;
    }
}
