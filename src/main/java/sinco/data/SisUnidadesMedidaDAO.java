//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisUnidadesMedidaDTO;
import sinco.business.Utilidades;

public class SisUnidadesMedidaDAO {
    public SisUnidadesMedidaDAO() {
    }

    public SisUnidadesMedidaDTO leerRegistro(ResultSet rs) {
        try {
            SisUnidadesMedidaDTO reg = new SisUnidadesMedidaDTO();
            reg.setCodigoUnidad(rs.getString("codigo_unidad"));
            reg.setCodigoGrupo(rs.getInt("codigo_grupo"));
            reg.setNombreUnidad(rs.getString("nombre_unidad"));
            reg.setEstadoUnidad(rs.getString("estado_unidad"));
            reg.setFactorConversion(rs.getInt("factor_conversion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setNombreEstadoUnidad(rs.getString("nombre_estado_unidad"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisUnidadesMedidaDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisUnidadesMedidaDTO> cargarTodos(int codigoGrupo, String nombreUnidad, String estadoUnidad) {
        Collection<SisUnidadesMedidaDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var9;
        try {
            String s = "select t.codigo_unidad,t.codigo_grupo,t.nombre_unidad,t.estado_unidad,t.factor_conversion,m1.DESCRIPCION as nombre_estado_unidad,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_unidades_medida t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_unidad) where 1=1";
            if (codigoGrupo > 0) {
                s = s + " and t.codigo_grupo=" + codigoGrupo;
            }

            if (nombreUnidad.length() > 0) {
                s = s + " and upper(t.nombre_unidad) like upper('%" + nombreUnidad + "%')";
            }

            if (estadoUnidad.length() > 0) {
                s = s + " and upper(t.estado_unidad) like upper('%" + estadoUnidad + "%')";
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
            Utilidades.writeError("SisUnidadesMedidaDAO:cargarTodos ", var13);
            return resultados;
        } finally {
            dat.close();
        }

        return var9;
    }

    public SisUnidadesMedidaDTO cargarRegistro(String codigoUnidad, int codigoGrupo) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_unidad,t.codigo_grupo,t.nombre_unidad,t.estado_unidad,t.factor_conversion,m1.DESCRIPCION as nombre_estado_unidad,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_unidades_medida t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_unidad) where  t.codigo_unidad='" + codigoUnidad + "'";
            if (codigoGrupo > 0) {
                s = s + " and t.codigo_grupo=" + codigoGrupo;
            }

            boolean rtaDB = dat.parseSql(s);
            SisUnidadesMedidaDTO var7;
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
            Utilidades.writeError("SisUnidadesMedidaDAO:cargarSisUnidadesMedida", var11);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(codigo_unidad) from sis_unidades_medida ";
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
            Utilidades.writeError("SisUnidadesMedidaDAO:siguienteRegistro ", var10);
        } finally {
            dat.close();
        }

        return 0;
    }

    public RespuestaBD eliminarRegistro(String codigoUnidad, int codigoGrupo) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_unidades_medida where  codigo_unidad='" + codigoUnidad + "'" + "  and codigo_grupo=" + codigoGrupo + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var9) {
            var9.printStackTrace();
            Utilidades.writeError("SisUnidadesMedidaDAO:eliminarRegistro ", var9);
            rta.setMensaje(var9.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(String codigoUnidad, int codigoGrupo, String nombreUnidad, String estadoUnidad, int factor, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into sis_unidades_medida(codigo_unidad,codigo_grupo,nombre_unidad,estado_unidad,factor_conversion,fecha_insercion,usuario_insercion) values ('" + codigoUnidad + "'," + "" + codigoGrupo + "," + "'" + nombreUnidad + "'," + "'" + estadoUnidad + "'," + "" + factor + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("%SisUnidadesMedidaDAO:crearRegistro ", var13);
            rta.setMensaje(var13.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(String codigoUnidad, int codigoGrupo, String nombreUnidad, String estadoUnidad, int factor, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_unidades_medida set  nombre_unidad='" + nombreUnidad + "'," + " estado_unidad='" + estadoUnidad + "'," + " factor_conversion=" + factor + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_unidad='" + codigoUnidad + "'" + " and codigo_grupo=" + codigoGrupo + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var13) {
            var13.printStackTrace();
            Utilidades.writeError("SisUnidadesMedidaDAO:modificarRegistro ", var13);
            rta.setMensaje(var13.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
