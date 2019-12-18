//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.PrcRecursoDTO;
import sinco.business.RespuestaBD;
import sinco.business.Utilidades;

public class PrcRecursoDAO {
    public PrcRecursoDAO() {
    }

    public PrcRecursoDTO leerRegistro(ResultSet rs) {
        try {
            PrcRecursoDTO reg = new PrcRecursoDTO();
            reg.setIdRecurso(rs.getInt("id_recurso"));
            reg.setIdTipoRecurso(rs.getString("id_tipo_recurso"));
            reg.setDescripcionRecurso(rs.getString("descripcion_recurso"));
            reg.setIdProcedimiento(rs.getInt("id_procedimiento"));
            reg.setEstado(rs.getString("estado"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setNombreIdTipoRecurso(rs.getString("nombre_id_tipo_recurso"));
            reg.setNombreIdProcedimiento(rs.getString("nombre_id_procedimiento"));
            reg.setNombreEstado(rs.getString("nombre_estado"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("PrcRecursoDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<PrcRecursoDTO> cargarTodos() {
        Collection<PrcRecursoDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_recurso,t.id_tipo_recurso,m1.descripcion as nombre_id_tipo_recurso,t.descripcion_recurso,t.id_procedimiento,r2.objetivo as nombre_id_procedimiento,t.estado,m3.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_recurso t  left join sis_multivalores m1 on (m1.tabla='tipo_recurso' and m1.valor=t.id_tipo_recurso) left join prc_procedimientos r2 on (r2.id_procedimiento=t.id_procedimiento) left join sis_multivalores m3 on (m3.tabla='estado_activo_inactivo' and m3.valor=t.estado) where 1=1";
            s = s + " order by 1";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var6 = (ArrayList) resultados;
                return var6;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                resultados.add(this.leerRegistro(rs));
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("PrcRecursoDAO:cargarTodos ", var10);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public PrcRecursoDTO cargarRegistro(int idRecurso) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_recurso,t.id_tipo_recurso,m1.descripcion as nombre_id_tipo_recurso,t.descripcion_recurso,t.id_procedimiento,r2.objetivo as nombre_id_procedimiento,t.estado,m3.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_recurso t  left join sis_multivalores m1 on (m1.tabla='tipo_recurso' and m1.valor=t.id_tipo_recurso) left join prc_procedimientos r2 on (r2.id_procedimiento=t.id_procedimiento) left join sis_multivalores m3 on (m3.tabla='estado_activo_inactivo' and m3.valor=t.estado) where  t.id_recurso=" + idRecurso + "";
            boolean rtaDB = dat.parseSql(s);
            PrcRecursoDTO var6;
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
            Utilidades.writeError("PrcRecursoDAO:cargarPrcRecurso", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(id_recurso) from prc_recurso ";
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
            Utilidades.writeError("PrcRecursoDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int idRecurso) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from prc_recurso where  id_recurso=" + idRecurso + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("PrcRecursoDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int idRecurso, String idTipoRecurso, String descripcionRecurso, int idProcedimiento, String estado, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into prc_recurso(id_recurso,id_tipo_recurso,descripcion_recurso,id_procedimiento,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + idTipoRecurso + "'," + "'" + descripcionRecurso + "'," + "" + idProcedimiento + "," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var14) {
                var14.printStackTrace();
                Utilidades.writeError("%PrcRecursoDAO:crearRegistro ", var14);
                rta.setMensaje(var14.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int idRecurso, String idTipoRecurso, String descripcionRecurso, int idProcedimiento, String estado, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update prc_recurso set  id_tipo_recurso='" + idTipoRecurso + "'," + " descripcion_recurso='" + descripcionRecurso + "'," + " id_procedimiento=" + idProcedimiento + "," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_recurso=" + idRecurso + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("PrcRecursoDAO:modificarRegistro ", var13);
            rta.setMensaje(var13.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
