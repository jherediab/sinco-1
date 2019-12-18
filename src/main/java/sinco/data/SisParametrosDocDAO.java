

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisParametrosDocDTO;
import sinco.business.Utilidades;

public class SisParametrosDocDAO {
    public SisParametrosDocDAO() {
    }

    public SisParametrosDocDTO leerRegistro(ResultSet rs) {
        try {
            SisParametrosDocDTO reg = new SisParametrosDocDTO();
            reg.setIdParametro(rs.getInt("id_parametro"));
            reg.setDescripcion(rs.getString("descripcion"));
            reg.setAsociadoA(rs.getString("asociado_a"));
            reg.setEstado(rs.getString("estado"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setNombreAsociadoA(rs.getString("nombre_asociado_a"));
            reg.setNombreEstado(rs.getString("nombre_estado"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisParametrosDocDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisParametrosDocDTO> cargarTodos(String descripcion) {
        Collection<SisParametrosDocDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_parametro,t.descripcion,t.asociado_a,m1.DESCRIPCION as nombre_asociado_a,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_parametros_doc t  left join sis_multivalores m1 on (m1.tabla='PAR_DOCUMENTOS' and m1.VALOR=t.asociado_a) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where 1=1";
            if (descripcion.length() > 0) {
                s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
            }

            s = s + " order by 1";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var7 = (ArrayList) resultados;
                return var7;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                resultados.add(this.leerRegistro(rs));
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisParametrosDocDAO:cargarTodos ", var11);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SisParametrosDocDTO cargarRegistro(int idParametro) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_parametro,t.descripcion,t.asociado_a,m1.DESCRIPCION as nombre_asociado_a,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_parametros_doc t  left join sis_multivalores m1 on (m1.tabla='PAR_DOCUMENTOS' and m1.VALOR=t.asociado_a) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.id_parametro=" + idParametro + "";
            boolean rtaDB = dat.parseSql(s);
            SisParametrosDocDTO var6;
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
            Utilidades.writeError("SisParametrosDocDAO:cargarSisParametrosDoc", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(id_parametro) from sis_parametros_doc ";
        DBManager dat = new DBManager();

        int var6;
        try {
            boolean rta = dat.parseSql(s);
            if (!rta) {
                byte var12 = 0;
                return var12;
            }

            ResultSet rs = dat.getResultSet();
            if (rs.next()) {
                s = rs.getString(1);
                if (!rs.wasNull()) {
                    inumero = Integer.parseInt(s) + 1;
                }
            }

            var6 = inumero;
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("SisParametrosDocDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int idParametro) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_parametros_doc where  id_parametro=" + idParametro + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisParametrosDocDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int idParametro, String descripcion, String asociadoA, String estado, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into sis_parametros_doc(id_parametro,descripcion,asociado_a,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + asociadoA + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var13) {
                var13.printStackTrace();
                Utilidades.writeError("%SisParametrosDocDAO:crearRegistro ", var13);
                rta.setMensaje(var13.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int idParametro, String descripcion, String asociadoA, String estado, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_parametros_doc set  descripcion='" + descripcion + "'," + " asociado_a='" + asociadoA + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_parametro=" + idParametro + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisParametrosDocDAO:modificarRegistro ", var12);
            rta.setMensaje(var12.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
