//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisPlantillasDTO;
import sinco.business.Utilidades;

public class SisPlantillasDAO {
    public SisPlantillasDAO() {
    }

    public SisPlantillasDTO leerRegistro(ResultSet rs) {
        try {
            SisPlantillasDTO reg = new SisPlantillasDTO();
            reg.setCodigo(rs.getString("codigo"));
            reg.setDescripcion(rs.getString("descripcion"));
            reg.setEstado(rs.getString("estado"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setNombreEstado(rs.getString("nombre_estado"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisPlantillasDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisPlantillasDTO> cargarTodos(String codigo, String descripcion) {
        Collection<SisPlantillasDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var8;
        try {
            String s = "select t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_plantillas_documentos t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where 1=1";
            if (codigo.length() > 0) {
                s = s + " and upper(t.codigo) like upper('%" + codigo + "%')";
            }

            if (descripcion.length() > 0) {
                s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
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
            Utilidades.writeError("SisPlantillasDAO:cargarTodos ", var12);
            return resultados;
        } finally {
            dat.close();
        }

        return var8;
    }

    public SisPlantillasDTO cargarRegistro(String codigo) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo,t.descripcion,t.documento,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_plantillas_documentos t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where  t.codigo='" + codigo + "'" + "";
            boolean rtaDB = dat.parseSql(s);
            SisPlantillasDTO reg;
            if (!rtaDB) {
                reg = null;
                return reg;
            }

            ResultSet rs = dat.getResultSet();
            if (rs.next()) {
                reg = new SisPlantillasDTO();
                reg.setCodigo(rs.getString("codigo"));
                reg.setDescripcion(rs.getString("descripcion"));
                reg.setDocumento(rs.getString("documento"));
                reg.setEstado(rs.getString("estado"));
                reg.setFechaInsercion(rs.getString("fecha_insercion"));
                reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
                reg.setFechaModificacion(rs.getString("fecha_modificacion"));
                reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
                reg.setNombreEstado(rs.getString("nombre_estado"));
                SisPlantillasDTO var7 = reg;
                return var7;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisPlantillasDAO:cargarSisPlantillas", var11);
        } finally {
            dat.close();
        }

        return null;
    }

    public RespuestaBD eliminarRegistro(String codigo) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_plantillas_documentos where  codigo='" + codigo + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisPlantillasDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(String codigo, String descripcion, String estado, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into sis_plantillas_documentos(codigo,descripcion,estado,fecha_insercion,usuario_insercion) values ('" + codigo + "'," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("%SisPlantillasDAO:crearRegistro ", var11);
            rta.setMensaje(var11.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(String codigo, String descripcion, String estado, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_plantillas_documentos set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo='" + codigo + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisPlantillasDAO:modificarRegistro ", var11);
            rta.setMensaje(var11.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD actualizar(String codigoPlantilla, byte[] archivo) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            rta = dat.actulizarClobPlantillas(codigoPlantilla, archivo);
        } catch (Exception var9) {
            var9.printStackTrace();
            Utilidades.writeError("SisPlantillasDAO:modificarRegistro ", var9);
            rta.setMensaje(var9.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
