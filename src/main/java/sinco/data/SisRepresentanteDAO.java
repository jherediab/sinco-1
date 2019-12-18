//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SisRepresentanteDTO;
import sinco.business.Utilidades;

public class SisRepresentanteDAO {
    public SisRepresentanteDAO() {
    }

    public SisRepresentanteDTO leerRegistro(ResultSet rs) {
        try {
            SisRepresentanteDTO reg = new SisRepresentanteDTO();
            reg.setIdRepresentante(rs.getInt("Id_representante"));
            reg.setTipoDocumento(rs.getString("Tipo_documento"));
            reg.setNDocumento(rs.getDouble("N_documento"));
            reg.setExpedicion(rs.getString("Expedicion"));
            reg.setNombre1(rs.getString("Nombre_1"));
            reg.setNombre2(rs.getString("Nombre_2"));
            reg.setApellido1(rs.getString("Apellido_1"));
            reg.setApellido2(rs.getString("Apellido_2"));
            reg.setDireccion(rs.getString("Direccion"));
            reg.setTelefono(rs.getDouble("Telefono"));
            reg.setCargo(rs.getString("Cargo"));
            reg.setTituloProfecion(rs.getString("Titulo_profecion"));
            reg.setActaNombramiento(rs.getString("Acta_nombramiento"));
            reg.setNActa(rs.getDouble("N_acta"));
            reg.setFechaActa(rs.getString("Fecha_acta"));
            reg.setPeriodoInicial(rs.getString("Periodo_inicial"));
            reg.setPeriodoFinal(rs.getString("Periodo_final"));
            reg.setNitEntidad(rs.getLong("nit_entidad"));
            reg.setDepartamento(rs.getString("departamento"));
            reg.setMunicipio(rs.getString("municipio"));
            reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
            reg.setFechaInsercion(rs.getString("fecha_insercion"));
            reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
            reg.setFechaModificacion(rs.getString("fecha_modificacion"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SisRepresentanteDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SisRepresentanteDTO> cargarTodos(long nitEntidad, long numeroDocRepresentante, String nombreRepresentanteConsulta) {
        Collection<SisRepresentanteDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        ArrayList var11;
        try {
            String s = "select t.Id_representante,t.Tipo_documento,t.N_documento,t.Expedicion,t.Nombre_1,t.Nombre_2,t.Apellido_1,t.Apellido_2,t.Direccion,t.Telefono,t.Cargo,t.Titulo_profecion,t.Acta_nombramiento,t.N_acta,t.Fecha_acta,t.Periodo_inicial,t.Periodo_final,t.nit_entidad,t.departamento,t.municipio,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_representante t  where 1=1";
            if (nitEntidad > 0L) {
                s = s + " and t.nit_entidad=" + nitEntidad;
            }

            if (numeroDocRepresentante > 0L) {
                s = s + " and t.N_documento=" + numeroDocRepresentante;
            }

            if (nombreRepresentanteConsulta.length() > 0) {
                s = s + " and (upper(t.Nombre_1) LIKE upper('%" + nombreRepresentanteConsulta + "%') " + "OR upper(t.Nombre_2) LIKE upper('%" + nombreRepresentanteConsulta + "%') " + "OR upper(t.Apellido_1) LIKE upper('%" + nombreRepresentanteConsulta + "%')" + "OR upper(t.Apellido_2) LIKE upper('%" + nombreRepresentanteConsulta + "%'))" + "";
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

            var11 = (ArrayList) resultados;
        } catch (Exception var15) {
            var15.printStackTrace();
            Utilidades.writeError("SisRepresentanteDAO:cargarTodos ", var15);
            return resultados;
        } finally {
            dat.close();
        }

        return var11;
    }

    public SisRepresentanteDTO cargarRegistro(int IdRepresentante, long nitEntidad) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.Id_representante,t.Tipo_documento,t.N_documento,t.Expedicion,t.Nombre_1,t.Nombre_2,t.Apellido_1,t.Apellido_2,t.Direccion,t.Telefono,t.Cargo,t.Titulo_profecion,t.Acta_nombramiento,t.N_acta,t.Fecha_acta,t.Periodo_inicial,t.Periodo_final,t.nit_entidad,t.departamento,t.municipio,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from sis_representante t  where  t.Id_representante=" + IdRepresentante + " and t.nit_entidad=" + nitEntidad + "";
            boolean rtaDB = dat.parseSql(s);
            SisRepresentanteDTO var8;
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
            Utilidades.writeError("SisRepresentanteDAO:cargarSisRepresentante", var12);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(Id_representante) from sis_representante ";
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
            Utilidades.writeError("SisRepresentanteDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int IdRepresentante, long codigoEntidad) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from sis_representante where  Id_representante=" + IdRepresentante + " and nit_entidad=" + codigoEntidad + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var10) {
            var10.printStackTrace();
            Utilidades.writeError("SisRepresentanteDAO:eliminarRegistro ", var10);
            rta.setMensaje(var10.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int IdRepresentante, String TipoDocumento, double NDocumento, String Expedicion, String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Direccion, double Telefono, String Cargo, String TituloProfecion, String ActaNombramiento, double NActa, String FechaActa, String PeriodoInicial, String PeriodoFinal, long nitEntidad, String departamento, String municipio, String usuarioInsercion) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into sis_representante(Id_representante,Tipo_documento,N_documento,Expedicion,Nombre_1,Nombre_2,Apellido_1,Apellido_2,Direccion,Telefono,Cargo,Titulo_profecion,Acta_nombramiento,N_acta,Fecha_acta,Periodo_inicial,Periodo_final,nit_entidad,departamento,municipio,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + TipoDocumento + "'," + "" + NDocumento + "," + "'" + Expedicion + "'," + "'" + Nombre1 + "'," + "'" + Nombre2 + "'," + "'" + Apellido1 + "'," + "'" + Apellido2 + "'," + "'" + Direccion + "'," + "" + Telefono + "," + "'" + Cargo + "'," + "'" + TituloProfecion + "'," + "'" + ActaNombramiento + "'," + "" + NActa + "," + "" + (FechaActa.length() > 0 ? Utilidades.formatoFecha2(FechaActa) : "null") + "," + "" + (PeriodoInicial.length() > 0 ? Utilidades.formatoFecha2(PeriodoInicial) : "null") + "," + "" + (PeriodoFinal.length() > 0 ? Utilidades.formatoFecha2(PeriodoFinal) : "null") + "," + "" + nitEntidad + "," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var33) {
                var33.printStackTrace();
                Utilidades.writeError("%SisRepresentanteDAO:crearRegistro ", var33);
                rta.setMensaje(var33.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int IdRepresentante, String TipoDocumento, double NDocumento, String Expedicion, String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Direccion, double Telefono, String Cargo, String TituloProfecion, String ActaNombramiento, double NActa, String FechaActa, String PeriodoInicial, String PeriodoFinal, long nitEntidad, String departamento, String municipio, String usuarioModificacion) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update sis_representante set  Tipo_documento='" + TipoDocumento + "'," + " N_documento=" + NDocumento + "," + " Expedicion='" + Expedicion + "'," + " Nombre_1='" + Nombre1 + "'," + " Nombre_2='" + Nombre2 + "'," + " Apellido_1='" + Apellido1 + "'," + " Apellido_2='" + Apellido2 + "'," + " Direccion='" + Direccion + "'," + " Telefono=" + Telefono + "," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " Cargo='" + Cargo + "'," + " Titulo_profecion='" + TituloProfecion + "'," + " Acta_nombramiento='" + ActaNombramiento + "'," + " N_acta=" + NActa + "," + " Fecha_acta=" + (FechaActa.length() > 0 ? Utilidades.formatoFecha2(FechaActa) : "null") + "," + " Periodo_inicial=" + (PeriodoInicial.length() > 0 ? Utilidades.formatoFecha2(PeriodoInicial) : "null") + "," + " Periodo_final=" + (PeriodoFinal.length() > 0 ? Utilidades.formatoFecha2(PeriodoFinal) : "null") + "," + " nit_entidad=" + nitEntidad + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " Id_representante=" + IdRepresentante + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var32) {
            var32.printStackTrace();
            Utilidades.writeError("SisRepresentanteDAO:modificarRegistro ", var32);
            rta.setMensaje(var32.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
