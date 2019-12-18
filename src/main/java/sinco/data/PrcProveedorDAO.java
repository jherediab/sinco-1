//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.PrcProveedorDTO;
import sinco.business.RespuestaBD;
import sinco.business.Utilidades;

public class PrcProveedorDAO {
    public PrcProveedorDAO() {
    }

    public PrcProveedorDTO leerRegistro(ResultSet rs) {
        try {
            PrcProveedorDTO reg = new PrcProveedorDTO();
            reg.setIdProveedor(rs.getInt("id_proveedor"));
            reg.setIdentificacionProveedor(rs.getString("identificacion_proveedor"));
            reg.setTipoIdentificacion(rs.getString("tipo_identificacion"));
            reg.setNombreProveedor(rs.getString("nombre_proveedor"));
            reg.setDireccionProveedor(rs.getString("direccion_proveedor"));
            reg.setTelefono(rs.getString("telefono"));
            reg.setEstado(rs.getString("estado"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setNombreTipoIdentificacion(rs.getString("nombre_tipo_identificacion"));
            reg.setNombreEstado(rs.getString("nombre_estado"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("PrcProveedorDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<PrcProveedorDTO> cargarTodos(String identificacionProveedor, String nombreProveedor, String direccionProveedor, String telefono) {
        Collection<PrcProveedorDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var10;
        try {
            String s = "select t.id_proveedor,t.identificacion_proveedor,t.tipo_identificacion,m1.descripcion as nombre_tipo_identificacion,t.nombre_proveedor,t.direccion_proveedor,t.telefono,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_proveedor t  left join sis_multivalores m1 on (m1.tabla='TIPO_DOCUMENTO' and m1.valor =t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where 1=1";
            if (identificacionProveedor.length() > 0) {
                s = s + " and upper(t.identificacion_proveedor) like upper('%" + identificacionProveedor + "%')";
            }

            if (nombreProveedor.length() > 0) {
                s = s + " and upper(t.nombre_proveedor) like upper('%" + nombreProveedor + "%')";
            }

            if (direccionProveedor.length() > 0) {
                s = s + " and upper(t.direccion_proveedor) like upper('%" + direccionProveedor + "%')";
            }

            if (telefono.length() > 0) {
                s = s + " and upper(t.telefono) like upper('%" + telefono + "%')";
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

            var10 = (ArrayList) resultados;
        } catch (Exception var14) {
            var14.printStackTrace();
            Utilidades.writeError("PrcProveedorDAO:cargarTodos ", var14);
            return resultados;
        } finally {
            dat.close();
        }

        return var10;
    }

    public PrcProveedorDTO cargarRegistro(int idProveedor, String identificacionProveedor) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.id_proveedor,t.identificacion_proveedor,t.tipo_identificacion,m1.descripcion as nombre_tipo_identificacion,t.nombre_proveedor,t.direccion_proveedor,t.telefono,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_proveedor t  left join sis_multivalores m1 on (m1.tabla='TIPO_DOCUMENTO' and m1.valor =t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where  t.id_proveedor=" + idProveedor + " and t.identificacion_proveedor='" + identificacionProveedor + "'" + "";
            boolean rtaDB = dat.parseSql(s);
            PrcProveedorDTO var7;
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
            Utilidades.writeError("PrcProveedorDAO:cargarPrcProveedor", var11);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro(int idProveedor) {
        int inumero = 1;
        String s = "select max(identificacion_proveedor) from prc_proveedor  where  id_proveedor=" + idProveedor + "";
        DBManager dat = new DBManager();

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

                int var13 = inumero;
                return var13;
            }

            byte var7 = 0;
            return var7;
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("PrcProveedorDAO:siguienteRegistro ", var11);
        } finally {
            dat.close();
        }

        return 0;
    }

    public RespuestaBD eliminarRegistro(int idProveedor, String identificacionProveedor) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from prc_proveedor where  id_proveedor=" + idProveedor + "  and identificacion_proveedor='" + identificacionProveedor + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var9) {
            var9.printStackTrace();
            Utilidades.writeError("PrcProveedorDAO:eliminarRegistro ", var9);
            rta.setMensaje(var9.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int idProveedor, String identificacionProveedor, String tipoIdentificacion, String nombreProveedor, String direccionProveedor, String telefono, String estado, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro(idProveedor);
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into prc_proveedor(id_proveedor,identificacion_proveedor,tipo_identificacion,nombre_proveedor,direccion_proveedor,telefono,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + identificacionProveedor + "'," + "'" + tipoIdentificacion + "'," + "'" + nombreProveedor + "'," + "'" + direccionProveedor + "'," + "'" + telefono + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var16) {
                var16.printStackTrace();
                Utilidades.writeError("%PrcProveedorDAO:crearRegistro ", var16);
                rta.setMensaje(var16.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int idProveedor, String identificacionProveedor, String tipoIdentificacion, String nombreProveedor, String direccionProveedor, String telefono, String estado, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update prc_proveedor set  tipo_identificacion='" + tipoIdentificacion + "'," + " nombre_proveedor='" + nombreProveedor + "'," + " direccion_proveedor='" + direccionProveedor + "'," + " telefono='" + telefono + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_proveedor=" + idProveedor + " and identificacion_proveedor='" + identificacionProveedor + "'" + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var15) {
            var15.printStackTrace();
            Utilidades.writeError("PrcProveedorDAO:modificarRegistro ", var15);
            rta.setMensaje(var15.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
