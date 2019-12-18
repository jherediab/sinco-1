//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisEntidadDTO;
import sinco.business.Utilidades;

public class SisEntidadDAO {
    public SisEntidadDAO() {
    }

    public SisEntidadDTO leerRegistro(ResultSet rs) {
        try {
            SisEntidadDTO reg = new SisEntidadDTO();
            reg.setNitEntidad(rs.getLong("nit_entidad"));
            reg.setNombre(rs.getString("nombre"));
            reg.setDireccion(rs.getString("direccion"));
            reg.setDepartamento(rs.getString("departamento"));
            reg.setMunicipio(rs.getString("municipio"));
            reg.setTelefono(rs.getDouble("telefono"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setNombreDepartamento(rs.getString("nombre_departamento"));
            reg.setNombreMunicipio(rs.getString("nombre_municipio"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisEntidadDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisEntidadDTO> cargarTodos() {
        Collection<SisEntidadDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.nit_entidad,t.nombre,t.direccion,t.departamento,r1.nombre_departamento  as nombre_departamento,t.municipio,r2.nombre_ciudad as nombre_municipio,t.telefono,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_entidad t  left join par_departamento r1 on (r1.codigo_departamento=t.departamento) left join par_ciudad r2 on (r2.codigo_departamento=t.departamento and r2.codigo_ciudad=t.municipio) where 1=1";
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
            Utilidades.writeError("SisEntidadDAO:cargarTodos ", var10);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SisEntidadDTO cargarRegistro(long nitEntidad) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.nit_entidad,t.nombre,t.direccion,t.departamento,r1.nombre_departamento  as nombre_departamento,t.municipio,r2.nombre_ciudad as nombre_municipio,t.telefono,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_entidad t  left join par_departamento r1 on (r1.codigo_departamento=t.departamento) left join par_ciudad r2 on (r2.codigo_departamento=t.departamento and r2.codigo_ciudad=t.municipio) where  t.nit_entidad=" + nitEntidad + "";
            boolean rtaDB = dat.parseSql(s);
            SisEntidadDTO var7;
            if (!rtaDB) {
                var7 = null;
                return var7;
            }

            ResultSet rs = dat.getResultSet();
            if (rs.next()) {
                var7 = this.leerRegistro(rs);
                return var7;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisEntidadDAO:cargarSisEntidad", var11);
        } finally {
            dat.close();
        }

        return null;
    }

    public RespuestaBD eliminarRegistro(long nitEntidad) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_entidad where  nit_entidad=" + nitEntidad + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var9) {
            var9.printStackTrace();
            Utilidades.writeError("SisEntidadDAO:eliminarRegistro ", var9);
            rta.setMensaje(var9.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(long nitEntidad, String nombre, String direccion, String departamento, String municipio, double telefono, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into sis_entidad(nit_entidad,nombre,direccion,departamento,municipio,telefono,fecha_insercion,usuario_insercion) values (" + nitEntidad + "," + "'" + nombre + "'," + "'" + direccion + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "" + telefono + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var16) {
            var16.printStackTrace();
            Utilidades.writeError("%SisEntidadDAO:crearRegistro ", var16);
            rta.setMensaje(var16.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(long nitEntidad, String nombre, String direccion, String departamento, String municipio, double telefono, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_entidad set  nombre='" + nombre + "'," + " direccion='" + direccion + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " telefono=" + telefono + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " nit_entidad=" + nitEntidad + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var16) {
            var16.printStackTrace();
            Utilidades.writeError("SisEntidadDAO:modificarRegistro ", var16);
            rta.setMensaje(var16.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
