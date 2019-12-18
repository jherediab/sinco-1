//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisGruposUnidadesDTO;
import sinco.business.Utilidades;

public class SisGruposUnidadesDAO {
    public SisGruposUnidadesDAO() {
    }

    public SisGruposUnidadesDTO leerRegistro(ResultSet rs) {
        try {
            SisGruposUnidadesDTO reg = new SisGruposUnidadesDTO();
            reg.setCodigoGrupo(rs.getInt("codigo_grupo"));
            reg.setNombreGrupo(rs.getString("nombre_grupo"));
            reg.setEstadoGrupo(rs.getString("estado_grupo"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setNombreEstadoGrupo(rs.getString("nombre_estado_grupo"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisGruposUnidadesDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisGruposUnidadesDTO> cargarTodos(String nombreGrupo, String estadoGrupo) {
        Collection<SisGruposUnidadesDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var8;
        try {
            String s = "select t.codigo_grupo,t.nombre_grupo,t.estado_grupo,m1.DESCRIPCION as nombre_estado_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_unidades t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_grupo) where 1=1";
            if (nombreGrupo.length() > 0) {
                s = s + " and upper(t.nombre_grupo) like upper('%" + nombreGrupo + "%')";
            }

            if (estadoGrupo.length() > 0) {
                s = s + " and upper(t.estado_grupo) like upper('%" + estadoGrupo + "%')";
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
            Utilidades.writeError("SisGruposUnidadesDAO:cargarTodos ", var12);
            return resultados;
        } finally {
            dat.close();
        }

        return var8;
    }

    public SisGruposUnidadesDTO cargarRegistro(int codigoGrupo) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_grupo,t.nombre_grupo,t.estado_grupo,m1.DESCRIPCION as nombre_estado_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_unidades t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_grupo) where  t.codigo_grupo=" + codigoGrupo + "";
            boolean rtaDB = dat.parseSql(s);
            SisGruposUnidadesDTO var6;
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
            Utilidades.writeError("SisGruposUnidadesDAO:cargarSisGruposUnidades", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(codigo_grupo) from sis_grupos_unidades ";
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
            Utilidades.writeError("SisGruposUnidadesDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int codigoGrupo) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_grupos_unidades where  codigo_grupo=" + codigoGrupo + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisGruposUnidadesDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int codigoGrupo, String nombreGrupo, String estadoGrupo, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into sis_grupos_unidades(codigo_grupo,nombre_grupo,estado_grupo,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + nombreGrupo + "'," + "'" + estadoGrupo + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var12) {
                var12.printStackTrace();
                Utilidades.writeError("%SisGruposUnidadesDAO:crearRegistro ", var12);
                rta.setMensaje(var12.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int codigoGrupo, String nombreGrupo, String estadoGrupo, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_grupos_unidades set  nombre_grupo='" + nombreGrupo + "'," + " estado_grupo='" + estadoGrupo + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_grupo=" + codigoGrupo + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisGruposUnidadesDAO:modificarRegistro ", var11);
            rta.setMensaje(var11.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
