//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisSedesDTO;
import sinco.business.Utilidades;

public class SisSedesDAO {
    public SisSedesDAO() {
    }

    public SisSedesDTO leerRegistro(ResultSet rs) {
        try {
            SisSedesDTO reg = new SisSedesDTO();
            reg.setIdSede(rs.getInt("id_sede"));
            reg.setNitEntidad(rs.getLong("nit_entidad"));
            reg.setNombreSede(rs.getString("nombre_sede"));
            reg.setDireccion(rs.getString("direccion"));
            reg.setDepartamento(rs.getString("departamento"));
            reg.setMunicipio(rs.getString("municipio"));
            reg.setTelefono(rs.getString("telefono"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setNombreNitEntidad(rs.getString("nombre_nit_entidad"));
            reg.setNombreDepartamento(rs.getString("nombre_departamento"));
            reg.setNombreMunicipio(rs.getString("nombre_municipio"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisSedesDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisSedesDTO> cargarTodos(long nitEntidad, String nombreSede) {
        Collection<SisSedesDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var9;
        try {
            String s = "select t.id_sede,t.nit_entidad,r1.nombre as nombre_nit_entidad,t.nombre_sede,t.direccion,t.departamento,r2.nombre_departamento as nombre_departamento,t.municipio,r3.nombre_ciudad as nombre_municipio,t.telefono,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_sedes t  left join sis_entidad r1 on (r1.nit_entidad=t.nit_entidad) left join par_departamento r2 on (r2.codigo_departamento=t.departamento) left join par_ciudad r3 on (t.departamento=r3.codigo_departamento and r3.codigo_ciudad=t.municipio) where 1=1";
            if (nombreSede.length() > 0) {
                s = s + " and t.nombre_sede LIKE '%" + nombreSede + "%'";
            }

            if (nitEntidad > 0L) {
                s = s + " and t.nit_entidad=" + nitEntidad;
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

            var9 = (ArrayList) resultados;
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("SisSedesDAO:cargarTodos ", var13);
            return resultados;
        } finally {
            dat.close();
        }

        return var9;
    }

    public SisSedesDTO cargarRegistro(int idSede, long nitEntidad) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_sede,t.nit_entidad,r1.nombre as nombre_nit_entidad,t.nombre_sede,t.direccion,t.departamento,r2.nombre_departamento as nombre_departamento,t.municipio,r3.nombre_ciudad as nombre_municipio,t.telefono,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_sedes t  left join sis_entidad r1 on (r1.nit_entidad=t.nit_entidad) left join par_departamento r2 on (r2.codigo_departamento=t.departamento) left join par_ciudad r3 on (r3.codigo_departamento=t.departamento and r3.codigo_ciudad=t.municipio) where  t.id_sede=" + idSede + " and t.nit_entidad=" + nitEntidad + "";
            boolean rtaDB = dat.parseSql(s);
            SisSedesDTO var8;
            if (!rtaDB) {
                var8 = null;
                return var8;
            }

            ResultSet rs = dat.getResultSet();
            if (rs.next()) {
                var8 = this.leerRegistro(rs);
                return var8;
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisSedesDAO:cargarSisSedes", var12);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(id_sede) from sis_sedes ";
        DBManager dat = new DBManager();

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

            int var6 = inumero;
            return var6;
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("SisSedesDAO:siguienteRegistro ", var10);
        } finally {
            dat.close();
        }

        return 0;
    }

    public RespuestaBD eliminarRegistro(int idSede, long codigoEntidad) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_sedes where  id_sede=" + idSede + " and nit_entidad=" + codigoEntidad + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("SisSedesDAO:eliminarRegistro ", var10);
            rta.setMensaje(var10.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int idSede, long nitEntidad, String nombreSede, String direccion, String departamento, String municipio, String telefono, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into sis_sedes(id_sede,nit_entidad,nombre_sede,direccion,departamento,municipio,telefono,usuario_insercion,fecha_insercion) values (" + idSede + "," + "" + nitEntidad + "," + "'" + nombreSede + "'," + "'" + direccion + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + telefono + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var16) {
            var16.printStackTrace();
            Utilidades.writeError("%SisSedesDAO:crearRegistro ", var16);
            rta.setMensaje(var16.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(int idSede, long nitEntidad, String nombreSede, String direccion, String departamento, String municipio, String telefono, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_sedes set  nit_entidad=" + nitEntidad + "," + " nombre_sede='" + nombreSede + "'," + " direccion='" + direccion + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " telefono='" + telefono + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_sede=" + idSede + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var16) {
            var16.printStackTrace();
            Utilidades.writeError("SisSedesDAO:modificarRegistro ", var16);
            rta.setMensaje(var16.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
