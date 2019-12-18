//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.PrcSalidaDTO;
import sinco.business.RespuestaBD;
import sinco.business.Utilidades;

public class PrcSalidaDAO {
    public PrcSalidaDAO() {
    }

    public PrcSalidaDTO leerRegistro(ResultSet rs) {
        try {
            PrcSalidaDTO reg = new PrcSalidaDTO();
            reg.setIdSalida(rs.getInt("id_salida"));
            reg.setCodigoSalida(rs.getString("codigo_salida"));
            reg.setDescSalida(rs.getString("desc_salida"));
            reg.setEstado(rs.getString("estado"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioNotificacion(rs.getString("usuario_notificacion"));
            reg.setFechaNotificacion(rs.getString("fecha_notificacion"));
            reg.setNombreEstado(rs.getString("nombre_estado"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("PrcSalidaDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<PrcSalidaDTO> cargarTodos(String codigoSalida, String descSalida) {
        Collection<PrcSalidaDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var8;
        try {
            String s = "select t.id_salida,t.codigo_salida,t.desc_salida,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_notificacion,t.fecha_notificacion from prc_salida t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where 1=1";
            if (codigoSalida.length() > 0) {
                s = s + " and upper(t.codigo_salida) like upper('%" + codigoSalida + "%')";
            }

            if (descSalida.length() > 0) {
                s = s + " and upper(t.desc_salida) like upper('%" + descSalida + "%')";
            }

            s = s + " order by 1";
            boolean rtaDB = dat.parseSql(s);
            if (rtaDB) {
                ResultSet rs = dat.getResultSet();

                while(rs.next()) {
                    resultados.add(this.leerRegistro(rs));
                }

                return resultados;
            }

            var8 = (ArrayList) resultados;
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("PrcSalidaDAO:cargarTodos ", var12);
            return resultados;
        } finally {
            dat.close();
        }

        return var8;
    }

    public PrcSalidaDTO cargarRegistro(int idSalida) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_salida,t.codigo_salida,t.desc_salida,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_notificacion,t.fecha_notificacion from prc_salida t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where  t.id_salida=" + idSalida + "";
            boolean rtaDB = dat.parseSql(s);
            PrcSalidaDTO var6;
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
            Utilidades.writeError("PrcSalidaDAO:cargarPrcSalida", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(id_salida) from prc_salida ";
        DBManager dat = new DBManager();

        byte var6;
        try {
            boolean rta = dat.parseSql(s);
            if (rta) {
                ResultSet rs = dat.getResultSet();
                if (rs.next()) {
                    s = rs.getString(1);
                    if (!rs.wasNull()) {
                        inumero = Integer.parseInt(s) + 1;
                    }
                }

                int var12 = inumero;
                return var12;
            }

            var6 = 0;
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("PrcSalidaDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int idSalida) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from prc_salida where  id_salida=" + idSalida + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("PrcSalidaDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int idSalida, String codigoSalida, String descSalida, String estado, String usuarioInsercion, String usuarioNotificacion, String fechaNotificacion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into prc_salida(id_salida,codigo_salida,desc_salida,estado,usuario_insercion,fecha_insercion,usuario_notificacion,fecha_notificacion) values (" + elSiguiente + "," + "'" + codigoSalida + "'," + "'" + descSalida + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioNotificacion + "'," + "" + (fechaNotificacion.length() > 0 ? Utilidades.formatoFecha2(fechaNotificacion) : "null") + "" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var15) {
                var15.printStackTrace();
                Utilidades.writeError("%PrcSalidaDAO:crearRegistro ", var15);
                rta.setMensaje(var15.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int idSalida, String codigoSalida, String descSalida, String estado, String usuarioNotificacion, String fechaNotificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update prc_salida set  codigo_salida='" + codigoSalida + "'," + " desc_salida='" + descSalida + "'," + " estado='" + estado + "'," + " usuario_notificacion='" + usuarioNotificacion + "'," + " fecha_notificacion=" + (fechaNotificacion.length() > 0 ? Utilidades.formatoFecha2(fechaNotificacion) : "null") + "" + " where" + " id_salida=" + idSalida + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("PrcSalidaDAO:modificarRegistro ", var13);
            rta.setMensaje(var13.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
