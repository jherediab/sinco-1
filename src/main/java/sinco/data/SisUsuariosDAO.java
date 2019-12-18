//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.ParametrosDTO;
import sinco.business.RespuestaBD;
import sinco.business.SisUsuariosDTO;
import sinco.business.Utilidades;

public class SisUsuariosDAO {
    public SisUsuariosDAO() {
    }

    public SisUsuariosDTO leerRegistro(ResultSet rs) {
        try {
            SisUsuariosDTO reg = new SisUsuariosDTO();
            reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
            reg.setNumeroIdentificacion(rs.getLong("numero_identificacion"));
            reg.setTipoIdentificacion(rs.getString("tipo_identificacion"));
            reg.setNombres(rs.getString("nombres"));
            reg.setApellidos(rs.getString("apellidos"));
            reg.setEstado(rs.getString("estado"));
            reg.setPassword(rs.getString("password"));
            reg.setIdcorreo(rs.getString("idcorreo"));
            reg.setEmail(rs.getString("email"));
            reg.setAuditorCordinador(rs.getString("auditor_cordinador"));
            reg.setTipoAuditor(rs.getString("tipo_auditor"));
            reg.setUsuarioSupervisor(rs.getString("usuario_supervisor"));
            reg.setCargoGenerico(rs.getString("cargo_generico"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setArea(rs.getInt("area"));

            try {
                reg.setNombreTipoIdentificacion(rs.getString("nombre_tipo_identificacion"));
            } catch (Exception var7) {
            }

            try {
                reg.setNombreEstado(rs.getString("nombre_estado"));
            } catch (Exception var6) {
            }

            try {
                reg.setNombreTipoAuditor(rs.getString("nombre_auditor"));
            } catch (Exception var5) {
            }

            try {
                reg.setNombreCargoGenerico(rs.getString("nombre_cargo_generico"));
            } catch (Exception var4) {
            }

            return reg;
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:leerRegistro ", var8);
            return null;
        }
    }

    public SisUsuariosDTO leerRegistroAuditores(ResultSet rs) {
        try {
            SisUsuariosDTO reg = new SisUsuariosDTO();
            reg.setNombres(rs.getString("nombres"));
            reg.setApellidos(rs.getString("apellidos"));
            reg.setCargoGenerico(rs.getString("CARGO_GENERICO"));
            reg.setAuditorCordinador(rs.getString("COORDINADOR"));
            reg.setAuditorLider(rs.getString("LIDER"));
            reg.setNumeroAuditorias(rs.getInt("numero_auditorias"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:leerRegistroAuditores ", var3);
            return null;
        }
    }

    public SisUsuariosDTO leerRegistroSupervisores(ResultSet rs) {
        try {
            SisUsuariosDTO reg = new SisUsuariosDTO();
            reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
            reg.setNombres(rs.getString("nombres"));
            reg.setApellidos(rs.getString("apellidos"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:leerRegistroSupervisores ", var3);
            return null;
        }
    }

    public Collection<SisUsuariosDTO> cargarTodos(int codigoEmpleado, String nombres, String apellidos, String estado) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var10;
        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where 1=1";
            if (codigoEmpleado > 0) {
                s = s + " and t.codigo_empleado=" + codigoEmpleado;
            }

            if (nombres.length() > 0) {
                s = s + " and upper(t.nombres) like upper('%" + nombres + "%')";
            }

            if (apellidos.length() > 0) {
                s = s + " and upper(t.apellidos) like upper('%" + apellidos + "%')";
            }

            if (estado.length() > 0) {
                s = s + " and upper(t.estado) like upper('%" + estado + "%')";
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
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var14);
            return resultados;
        } finally {
            dat.close();
        }

        return var10;
    }

    public Collection<SisUsuariosDTO> cargarPorCodigo(int codigoEmpleado) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t  left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.codigo_empleado=" + codigoEmpleado;
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
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var11);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SisUsuariosDTO cargarRegistro(int codigoEmpleado) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t  left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.codigo_empleado=" + codigoEmpleado + "";
            boolean rtaDB = dat.parseSql(s);
            SisUsuariosDTO var6;
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
            Utilidades.writeError("SisUsuariosDAO:cargarSisUsuarios", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public Collection<SisUsuariosDTO> cargarSupervisores(int area) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.nombres,t.apellidos from sis_usuarios t left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado) where 1=1";
            s = s + " and UA.codigo_area=" + area;
            s = s + " and t.estado='A'";
            s = s + " and t.usuario_supervisor='S'";
            s = s + " order by 2";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var7 = (ArrayList) resultados;
                return var7;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                resultados.add(this.leerRegistroSupervisores(rs));
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:cargarSupervisores ", var11);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SisUsuariosDTO cargarPersonaArea(int area, int codigoEmpleado) {
        DBManager dat = new DBManager();

        try {
            String s = "select  t.codigo_empleado, t.numero_identificacion, t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion, t.nombres, t.apellidos, t.estado,m2.DESCRIPCION as nombre_estado, t.password, t.idcorreo, t.email,t.cargo_generico, r6.DESCRIPCION as nombre_cargo_generico, t.auditor_cordinador,m.DESCRIPCION as nombre_auditor, t.tipo_auditor, t.usuario_supervisor, t.fecha_insercion, t.usuario_insercion, t.fecha_modificacion, t.usuario_modificacion, ua.codigo_area as area from Sis_Usuarios_Area UA, sis_usuarios t  left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.Codigo_Empleado = UA.Codigo_Empleado and t.estado in ('A') and Ua.codigo_area=" + area + " and t.codigo_empleado=" + codigoEmpleado + "";
            boolean rtaDB = dat.parseSql(s);
            SisUsuariosDTO var7;
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
            Utilidades.writeError("SisUsuariosDAO:cargarSisUsuarios", var11);
        } finally {
            dat.close();
        }

        return null;
    }

    public SisUsuariosDTO getPersonaPorEmail(String idCorreo) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t  left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.idcorreo='" + idCorreo + "'";
            boolean rtaDB = dat.parseSql(s);
            SisUsuariosDTO var6;
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
            Utilidades.writeError("SisUsuariosDAO:cargarSisUsuarios", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public RespuestaBD eliminarRegistro(int codigoEmpleado) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_usuarios where  codigo_empleado=" + codigoEmpleado + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int codigoEmpleado, long numeroIdentificacion, String tipoIdentificacion, String nombres, String apellidos, String estado, String password, String idcorreo, String email, String cargoGenerico, String auditorCordinador, String tipoAuditor, String usuarioSupervisor, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "insert into sis_usuarios(codigo_empleado,numero_identificacion,tipo_identificacion,nombres,apellidos,estado,password,idcorreo,email,cargo_generico,auditor_cordinador,tipo_auditor,usuario_supervisor,fecha_insercion,usuario_insercion) values (" + codigoEmpleado + "," + "" + numeroIdentificacion + "," + "'" + tipoIdentificacion + "'," + "'" + nombres + "'," + "'" + apellidos + "'," + "'" + estado + "'," + "'" + password + "'," + "'" + idcorreo + "'," + "'" + email + "'," + "'" + cargoGenerico + "'," + "'" + auditorCordinador + "'," + "'" + tipoAuditor + "'," + "'" + usuarioSupervisor + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
            rta = dat.executeUpdate2(s);
        } catch (Exception var22) {
            var22.printStackTrace();
            Utilidades.writeError("%SisUsuariosDAO:crearRegistro ", var22);
            rta.setMensaje(var22.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD modificarRegistro(int codigoEmpleado, long numeroIdentificacion, String tipoIdentificacion, String nombres, String apellidos, String estado, String password, String idcorreo, String email, String cargoGenerico, String auditorCordinador, String tipoAuditor, String usuarioSupervisor, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_usuarios set  numero_identificacion=" + numeroIdentificacion + "," + " tipo_identificacion='" + tipoIdentificacion + "'," + " nombres='" + nombres + "'," + " apellidos='" + apellidos + "'," + " estado='" + estado + "'," + " password='" + password + "'," + " idcorreo='" + idcorreo + "'," + " email='" + email + "'," + " cargo_generico='" + cargoGenerico + "'," + " auditor_cordinador='" + auditorCordinador + "'," + " tipo_auditor='" + tipoAuditor + "'," + " usuario_supervisor='" + usuarioSupervisor + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_empleado=" + codigoEmpleado + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var22) {
            var22.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:modificarRegistro ", var22);
            rta.setMensaje(var22.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public Collection<SisUsuariosDTO> cargarActivoDeArea(int area) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t  left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.estado in('A','T') and exists (select  'x' from sis_usuarios_area pa where t.codigo_empleado=pa.codigo_empleado  and pa.codigo_area=" + area + " and pa.clase not in(99))" + " order by t.apellidos,t.nombres";
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
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var11);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public Collection<SisUsuariosDTO> cargarsubditos(int nivelSuperior, String secuencia, int nivel) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,pa.codigo_area as area from sis_usuarios_area pa, sis_usuarios t  left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.estado in('A') and pa.clase not in(99) and t.codigo_empleado=pa.codigo_empleado and pa.codigo_area in ( select codigo from unidades_dependencia  where unidades_dependencia.estado='A' and nivel<=" + (nivel + ParametrosDTO.getInt("nivel.produndidad")) + " and secuencia like '" + secuencia + "%') " + " order by t.apellidos,t.nombres";
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
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var13);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public Collection<SisUsuariosDTO> cargarsubditos(String secuencia, int nivel) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,pa.codigo_area as area,u.descripcion nombre_area from sis_usuarios_area pa ,unidades_dependencia u,sis_usuarios t left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.estado in('A') and pa.clase not in(99) and t.codigo_empleado=pa.codigo_empleado and pa.codigo_area=u.codigo and pa.codigo_area in ( select codigo from unidades_dependencia  where unidades_dependencia.estado='A' and nivel<=" + nivel + " and secuencia like '" + secuencia + "%') " + " order by t.apellidos,t.nombres";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var14 = (ArrayList) resultados;
                return var14;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                SisUsuariosDTO reg = new SisUsuariosDTO();
                reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                reg.setNombres(rs.getString("nombres"));
                reg.setApellidos(rs.getString("apellidos"));
                reg.setArea(rs.getInt("area"));
                reg.setNombreArea(rs.getString("nombre_area"));
                resultados.add(reg);
                resultados.add(this.leerRegistro(rs));
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var12);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public RespuestaBD reasignarFuncionario(int codigoEmpleado, int nuevoResponsable, String estado, String usuario) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            RespuestaBD var8 = dat.reasignarFuncionario(codigoEmpleado, nuevoResponsable, estado, usuario);
            return var8;
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var12);
        } finally {
            dat.close();
        }

        return rta;
    }

    public SisUsuariosDTO getJefeArea(int area) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,pa.codigo_area as area from sis_usuarios_area pa, sis_usuarios t  left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.codigo_empleado=pa.codigo_empleado  and pa.codigo_area=" + area + " and t.estado in ('A','T')" + " and pa.RESPONSABLE_AREA='S'";
            boolean rtaDB = dat.parseSql(s);
            SisUsuariosDTO var6;
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
            Utilidades.writeError("SisUsuariosDAO:cargarSisUsuarios", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public Collection<SisUsuariosDTO> cargarDeServicio(int solicitud) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select u.Codigo as area,           p.Codigo_Empleado,           u.Descripcion as Nombre_Area,           p.Nombres,           p.apellidos from   Solicitudes          s,        Proveedor_Multiple   m,        Unidades_Dependencia u,        sis_usuarios         p,        sis_usuarios_area    Pa,        Servicios            Se where  s.Numero = " + solicitud + "        and s.Codigo_Servicio = Se.Codigo" + "        and Se.Codigo = m.Codigo_Servicio" + "        and m.Persona_Cargo = p.Codigo_Empleado" + "        and p.Codigo_Empleado = Pa.Codigo_Empleado" + "        and Se.Especializado in ('M','D','O')" + "        and Pa.Area_Principal = 'S'" + "        and Pa.Codigo_Area = u.Codigo" + "        and p.Estado in ('A')" + " UNION " + "select u.Codigo as area," + "        p.Codigo_Empleado," + "        u.Descripcion as Nombre_Area," + "        p.Nombres," + "        p.Apellidos" + " from   Solicitudes          s," + "        Servicios_Area       Sa," + "        Unidades_Dependencia u," + "        sis_usuarios         p," + "        Servicios            Se," + "        sis_usuarios_area    Pa" + " where  s.Codigo_Servicio = Sa.Codigo_Servicio" + "        and p.Codigo_Empleado = Sa.Persona_Cargo" + "        and Sa.Codigo_Area = u.Codigo" + "        and p.Estado in ('A')" + "        and s.Numero = " + solicitud + "        and s.Codigo_Servicio = Se.Codigo" + "        and p.Codigo_Empleado = Pa.Codigo_Empleado" + "        and Pa.Area_Principal = 'S'" + "        and Se.Especializado = 'S'" + " order by apellidos,Nombres";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var13 = (ArrayList) resultados;
                return var13;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                SisUsuariosDTO reg = new SisUsuariosDTO();
                reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                reg.setArea(rs.getInt("area"));
                reg.setNombres(rs.getString("nombres"));
                reg.setApellidos(rs.getString("apellidos"));
                reg.setNombreArea(rs.getString("nombre_area"));
                resultados.add(reg);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Utilidades.writeError("PersonaFactory::cargarsubditos ", var11);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public Collection<SisUsuariosDTO> cargarSimilares(String apellidos, String nombres) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var8;
        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_usuarios t left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.estado in('A')";
            if (apellidos != null) {
                s = s + " and upper(t.apellidos) like upper('%" + apellidos + "%')";
            }

            if (nombres != null) {
                s = s + " and upper(t.nombres) like upper('%" + nombres + "%')";
            }

            s = s + " order by apellidos";
            boolean rtaDB = dat.parseSql(s);
            if (rtaDB) {
                ResultSet rs = dat.getResultSet();

                while(rs.next()) {
                    SisUsuariosDTO reg = new SisUsuariosDTO();
                    reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                    reg.setNombres(rs.getString("nombres"));
                    reg.setApellidos(rs.getString("apellidos"));
                    resultados.add(reg);
                }

                return resultados;
            }

            var8 = (ArrayList) resultados;
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var12);
            return resultados;
        } finally {
            dat.close();
        }

        return var8;
    }

    public Collection<SisUsuariosDTO> auditoresActivos() {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,ua.codigo_area as area from sis_usuarios t left   join Sis_Usuarios_Area UA on     (t.Codigo_Empleado = UA.Codigo_Empleado and Ua.Area_Principal = 'S') left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.TIPO_AUDITOR IN('S','J','M') and t.estado in ('A','T') order by 1";
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
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var10);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public Collection<SisUsuariosDTO> cargarProveedoresMultiples(int area, int servicio) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.codigo_empleado,t.numero_identificacion,t.tipo_identificacion,m1.DESCRIPCION as nombre_tipo_identificacion,t.nombres,t.apellidos,t.estado,m2.DESCRIPCION as nombre_estado,t.password,t.idcorreo,t.email,t.cargo_generico,r6.DESCRIPCION as nombre_cargo_generico,t.auditor_cordinador,m.DESCRIPCION as nombre_auditor,t.tipo_auditor,t.usuario_supervisor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,pa.codigo_area as area from sis_usuarios_area pa,Proveedor_Multiple Pm,sis_usuarios t left join sis_cargos r6 on (r6.CODIGO=t.cargo_generico) left join sis_multivalores m on (m.VALOR=t.tipo_auditor and m.tabla='AUT_TIPO_AUDITOR') left join sis_multivalores m1 on (m1.tabla='tipo_documento' and m1.VALOR=t.tipo_identificacion) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where t.estado in('A') and pa.clase not in(99) and t.codigo_empleado=pa.codigo_empleado AND pa.CODIGO_AREA=Pm.Codigo_Area AND pA.Codigo_Empleado =pm.Persona_Cargo AND pm.Codigo_Area = " + area + " and Pm.codigo_servicio=" + servicio + " order by t.apellidos,t.nombres";
            boolean rtaDB = dat.parseSql(s);
            if (!rtaDB) {
                ArrayList var14 = (ArrayList) resultados;
                return var14;
            }

            ResultSet rs = dat.getResultSet();

            while(rs.next()) {
                SisUsuariosDTO reg = new SisUsuariosDTO();
                reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
                reg.setArea(rs.getInt("area"));
                reg.setNombres(rs.getString("nombres"));
                reg.setApellidos(rs.getString("apellidos"));
                resultados.add(reg);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("SisUsuariosDAO:cargarTodos ", var12);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SisUsuariosDTO getPersona(int cod) {
        DBManager dat = new DBManager();

        try {
            String s = "select  p.*, s.descripcion as nombre_auditor from   sis_usuarios p, sis_multivalores s where p.tipo_auditor=s.valor and upper(s.tabla)=upper('aut_tipo_auditor')  and p.Codigo_Empleado = " + cod;
            boolean rta = dat.parseSql(s);
            SisUsuariosDTO var6;
            if (!rta) {
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
            Utilidades.writeError("PersonaFactory::getPersona ", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public SisUsuariosDTO getPersona2(int cod) {
        DBManager dat = new DBManager();

        try {
            String s = "select  * from   sis_usuarios where Codigo_Empleado = " + cod;
            boolean rta = dat.parseSql(s);
            SisUsuariosDTO var6;
            if (!rta) {
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
            Utilidades.writeError("PersonaFactory::getPersona2", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public Collection<SisUsuariosDTO> cargarAuditores(String tipoAuditor, String estado) {
        Collection<SisUsuariosDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var8;
        try {
            String s = "WITH num_auditorias  AS (select codigo_empleado, count(*) AS num_aud from aud_grupo_auditor group by codigo_empleado) SELECT s.nombres, s.apellidos, c.descripcion as CARGO_GENERICO, CASE WHEN s.auditor_cordinador='S' THEN 'SI' WHEN s.auditor_cordinador='N' THEN 'NO' ELSE 'NO' END AS COORDINADOR, CASE WHEN s.auditor_lider='S' THEN 'SI' WHEN s.auditor_lider='N' THEN 'NO' ELSE 'NO' END AS LIDER, COALESCE(NULLIF(a.num_aud,0), '0') AS numero_auditorias FROM sis_cargos c, sis_usuarios s  LEFT JOIN num_auditorias a ON (a.codigo_empleado=s.codigo_empleado) where s.cargo_generico=c.codigo and s.tipo_auditor IN ('J','S','M')";
            if (tipoAuditor.length() > 0) {
                s = s + " and upper(s.tipo_auditor) like upper('" + tipoAuditor + "')";
            }

            if (estado.length() > 0) {
                s = s + " and upper(s.estado) like upper('%" + estado + "%')";
            }

            s = s + " order by 6 desc";
            boolean rtaDB = dat.parseSql(s);
            if (rtaDB) {
                ResultSet rs = dat.getResultSet();

                while(rs.next()) {
                    resultados.add(this.leerRegistroAuditores(rs));
                }

                return resultados;
            }

            var8 = (ArrayList) resultados;
        } catch (Exception var12) {
            var12.printStackTrace();
            Utilidades.writeError("PersonasDAO:cargarTodosAuditores ", var12);
            return resultados;
        } finally {
            dat.close();
        }

        return var8;
    }
}
