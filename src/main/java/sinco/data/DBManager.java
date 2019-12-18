 package sinco.data;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import sinco.business.ElemAreaDTO;
import sinco.business.FechaDTO;
import sinco.business.ParametrosDTO;
import sinco.business.RespuestaBD;
import sinco.business.Utilidades;
/*      */ 
/*      */ public class DBManager {
                 private Connection con = null;
    private ResultSet rs;
    private Statement stmtp;
    boolean autocommit;
    private boolean estadoConexion = true;
    private int afectados;

    public DBManager() {
        this.autocommit = true;
        this.createConnection();
    }

    public DBManager(boolean p) {
        this.estadoConexion = false;
        this.autocommit = p;
        this.createConnection();
    }

    private boolean createConnection() {
        this.estadoConexion = false;

        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            //DataSource ds = (DataSource)envContext.lookup(ParametrosDTO.getString("ConectorBaseDatos"));
            
            DataSource ds = (DataSource) envContext.lookup("jdbc/postgres");
            
            
            if (envContext == null) {
                throw new Exception("Error: No Context");
            } else if (ds == null) {
                throw new Exception("Error: No DataSource");
            } else {
                if (ds != null) {
                    this.con = ds.getConnection();
                }

                this.con.setAutoCommit(true);
                this.estadoConexion = true;
                return true;
            }
        } catch (Exception var4) {
            Utilidades.writeError("Error abriendo conexino ", var4);
            var4.printStackTrace();
            return false;
        }
    }

    public void cerrar() {
        try {
            this.rs.close();
        } catch (Exception var4) {
        }

        try {
            this.stmtp.close();
        } catch (Exception var3) {
        }

        try {
            this.con.close();
        } catch (Exception var2) {
        }

        this.estadoConexion = false;
    }

    public void close() {
        try {
            this.rs.close();
        } catch (Exception var4) {
        }

        try {
            this.stmtp.close();
        } catch (Exception var3) {
        }

        try {
            this.con.close();
        } catch (Exception var2) {
        }

        this.estadoConexion = false;
    }

    public boolean parseSql(String sqlsentence) {
        int veces = 0;

        while(veces < 100) {
            ParametrosDTO.put("error", "0");
            ParametrosDTO.put("mensajeError", "");
            ++veces;

            try {
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.writeInfo(sqlsentence);
                }

                if (this.con == null) {
                    this.cerrar();
                    this.createConnection();
                }

                this.stmtp = this.con.createStatement();
                this.rs = this.stmtp.executeQuery(sqlsentence);
                return true;
            } catch (SQLException var4) {
                Utilidades.writeError("Error en \n" + sqlsentence + "\n" + " Status=" + var4.getSQLState() + "\n", var4);
                var4.printStackTrace();
                ParametrosDTO.put("mensajeError", var4.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var4.getSQLState() != null || var4.getMessage().indexOf("socket write error") < 0) && (var4.getSQLState() != null || var4.getMessage().indexOf("Socket closed") < 0) && (var4.getSQLState() == null || !var4.getSQLState().equals("08S01"))) {
                    veces = 105;
                } else {
                    Utilidades.writeError("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return false;
    }

    public boolean executeUpdate(String sqlsentence) {
        int veces = 0;

        while(veces < 100) {
            ParametrosDTO.put("error", "0");
            ParametrosDTO.put("mensajeError", "");
            ++veces;

            try {
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.writeInfo(sqlsentence);
                }

                if (this.con == null) {
                    this.cerrar();
                    this.createConnection();
                }

                Statement stmtl = this.con.createStatement();
                this.afectados = stmtl.executeUpdate(sqlsentence);
                stmtl.close();
                return true;
            } catch (SQLException var4) {
                Utilidades.writeError("Error en \n" + sqlsentence + "\n" + " Status=" + var4.getSQLState() + "\n", var4);
                var4.printStackTrace();
                ParametrosDTO.put("mensajeError", var4.getMessage());
                ParametrosDTO.put("error", "1");
                if ((var4.getSQLState() != null || var4.getMessage().indexOf("socket write error") < 0) && (var4.getSQLState() != null || var4.getMessage().indexOf("Socket closed") < 0) && (var4.getSQLState() == null || !var4.getSQLState().equals("08S01"))) {
                    veces = 105;
                } else {
                    Utilidades.writeError("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return false;
    }

    public RespuestaBD executeUpdate2(String sqlsentence) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < 100) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlsentence);
                }

                Statement stmtl = this.con.createStatement();
                this.afectados = stmtl.executeUpdate(sqlsentence);
                stmtl.close();
                rtaBD.setRta(true);
                return rtaBD;
            } catch (SQLException var5) {
                Utilidades.writeError("Error en \n" + sqlsentence + "\n" + " Status=" + var5.getSQLState() + "\n", var5);
                var5.printStackTrace();
                rtaBD.setMensaje(var5.getMessage());
                if (this.con != null && (var5.getSQLState() != null || var5.getMessage().indexOf("socket write error") < 0) && (var5.getSQLState() != null || var5.getMessage().indexOf("Socket closed") < 0) && (var5.getSQLState() != null || var5.getMessage().indexOf("E/S") < 0) && (var5.getSQLState() != null || var5.getMessage().indexOf("attempt to access") < 0) && (var5.getSQLState() != null || var5.getMessage().indexOf("Sentencia cerrada") < 0) && (var5.getSQLState() == null || !var5.getSQLState().equals("08S01"))) {
                    veces = 105;
                } else {
                    Utilidades.writeError("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD ejecutarProceso(int anno, int trimestre, String usuario) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call PACK_PROCESOS.fun_reporte_quejas(?,?)}";
                FechaDTO fFecha = new FechaDTO(anno + "-" + Utilidades.formato(trimestre * 3, 2) + "-01");
                Date miDate = new Date((new GregorianCalendar(fFecha.getAnno(), fFecha.getMes() - 1, fFecha.getDia())).getTimeInMillis());
                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setDate(2, miDate);
                callableStatement.setString(3, usuario);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var11) {
                Utilidades.writeError("ejecutarProceso", var11);
                var11.printStackTrace();
                ParametrosDTO.put("mensajeError", var11.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con == null || var11.getSQLState() == null && var11.getMessage().indexOf("socket write error") >= 0 || var11.getSQLState() == null && var11.getMessage().indexOf("Socket closed") >= 0 || var11.getSQLState() != null && var11.getSQLState().equals("08S01")) {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                } else {
                    rtaBD.setRta(false);
                    rtaBD.setMensaje(var11.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD procesarNovedad(int solicitud, int codigoEstado, String usuario) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call pack_solicitudes.fun_proceso(?,?,?)}";
                if (ParametrosDTO.getInt("tipoBaseDatos") != 5) {
                    sqlInsert = "{ ? = call fun_proceso_novedad(?,?,?)}";
                }

                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + solicitud);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setInt(2, solicitud);
                callableStatement.setInt(3, codigoEstado);
                callableStatement.setString(4, usuario);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                } else {
                    Utilidades.writeError("Error procesando solicitud " + solicitud + " " + rta);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var9) {
                Utilidades.writeError("ejecutarCierre", var9);
                var9.printStackTrace();
                ParametrosDTO.put("mensajeError", var9.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var9.getSQLState() != null || var9.getMessage().indexOf("socket write error") < 0) && (var9.getSQLState() != null || var9.getMessage().indexOf("Socket closed") < 0) && (var9.getSQLState() == null || !var9.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var9.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD reasignarFuncionario(int codigoEmpleado, int nuevoResponsable, String estado, String usuario) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call pack_procesos.Fun_reasignar_responsable(?,?,?,?)}";
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setInt(2, codigoEmpleado);
                callableStatement.setInt(3, nuevoResponsable);
                callableStatement.setString(4, estado);
                callableStatement.setString(5, usuario);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                } else {
                    Utilidades.writeError("Error procesando reasignacion de usuario " + rta);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var10) {
                Utilidades.writeError("ejecutarCierre", var10);
                var10.printStackTrace();
                ParametrosDTO.put("mensajeError", var10.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var10.getSQLState() != null || var10.getMessage().indexOf("socket write error") < 0) && (var10.getSQLState() != null || var10.getMessage().indexOf("Socket closed") < 0) && (var10.getSQLState() == null || !var10.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var10.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD validarProcedimiento(int solicitud, int caracteristica, String valor, String procedimiento) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call " + procedimiento + "(?,?,?,?,?)}";
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + solicitud);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setInt(2, solicitud);
                callableStatement.setInt(3, caracteristica);
                callableStatement.setString(4, valor);
                callableStatement.registerOutParameter(5, 12);
                callableStatement.registerOutParameter(6, 12);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                    rtaBD.setCerrarSolicitud(callableStatement.getString(5));
                    rtaBD.setCausal(callableStatement.getString(6));
                } else {
                    Utilidades.writeError("Error procesando solicitud " + solicitud + " " + rta);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var10) {
                Utilidades.writeError("ejecutarCierre", var10);
                var10.printStackTrace();
                ParametrosDTO.put("mensajeError", var10.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var10.getSQLState() != null || var10.getMessage().indexOf("socket write error") < 0) && (var10.getSQLState() != null || var10.getMessage().indexOf("Socket closed") < 0) && (var10.getSQLState() == null || !var10.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var10.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD procedimientoCursor(int solicitud, int caracteristica, String valor, String procedimiento) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            Collection<ElemAreaDTO> resultados = new ArrayList();
            ++veces;

            try {
                CallableStatement stmt = null;
                String sqlInsert = "{ ? = call " + procedimiento + "(?,?,?,?,?)}";
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + solicitud);
                }

                stmt = this.con.prepareCall(sqlInsert);
                stmt.setInt(2, solicitud);
                stmt.setInt(3, caracteristica);
                stmt.setString(4, valor);
                stmt.registerOutParameter(5, 12);
                stmt.registerOutParameter(6, 12);
                stmt.execute();
                rtaBD.setCerrarSolicitud(stmt.getString(5));
                rtaBD.setCausal(stmt.getString(6));
                if (rtaBD.getCerrarSolicitud().equals("S")) {
                    this.rs = (ResultSet)stmt.getObject(1);
                    if (this.rs != null) {
                        while(this.rs.next()) {
                            ElemAreaDTO reg = new ElemAreaDTO();
                            reg.setCodigo(this.rs.getString("codigo"));
                            reg.setDescripcion(this.rs.getString("descripcion"));
                            resultados.add(reg);
                        }
                    }
                }

                rtaBD.setRta(true);
                rtaBD.setResultados(resultados);
                stmt.close();
                return rtaBD;
            } catch (SQLException var11) {
                Utilidades.writeError("ejecutarCierre", var11);
                var11.printStackTrace();
                ParametrosDTO.put("mensajeError", var11.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var11.getSQLState() != null || var11.getMessage().indexOf("socket write error") < 0) && (var11.getSQLState() != null || var11.getMessage().indexOf("Socket closed") < 0) && (var11.getSQLState() == null || !var11.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var11.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public RespuestaBD registrarCorreos(int solicitud, String usuario) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call pack_solicitudes.Fun_registrar_correos(?,?)}";
                if (ParametrosDTO.getInt("tipoBaseDatos") != 5) {
                    sqlInsert = "{ ? = call Fun_registrar_correos(?,?,?)}";
                }

                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + solicitud);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setInt(2, solicitud);
                callableStatement.setString(3, usuario);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                } else {
                    Utilidades.writeError("Error registrarCorreos " + solicitud + " " + rta);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var8) {
                Utilidades.writeError("ejecutarCierre", var8);
                var8.printStackTrace();
                if (this.con != null && (var8.getSQLState() != null || var8.getMessage().indexOf("socket write error") < 0) && (var8.getSQLState() != null || var8.getMessage().indexOf("Socket closed") < 0) && (var8.getSQLState() == null || !var8.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var8.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public String crearPlanes(int ciclo, String usuario) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "{ ? = call FUN_CREAR_PLANES(?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, ciclo);
            callableStatement.setString(3, usuario);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var6) {
            Utilidades.writeError("SQLException caught: ( " + var6.getSQLState() + " ) ", var6);
            ParametrosDTO.put("mensajeError", var6.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var7) {
            Utilidades.writeError("SQLException caught: (", var7);
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public RespuestaBD enviarSolicitudes(String ciclo, int usuario, String usuarioModificacion) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call Pack_Auditoria.Fun_Enviar_Auditoria(?,?,?)}";
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + ciclo + " " + usuario + " " + usuarioModificacion);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setString(2, ciclo);
                callableStatement.setInt(3, usuario);
                callableStatement.setString(4, usuarioModificacion);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var9) {
                Utilidades.writeError("enviarSolicitudes", var9);
                var9.printStackTrace();
                ParametrosDTO.put("mensajeError", var9.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var9.getSQLState() != null || var9.getMessage().indexOf("socket write error") < 0) && (var9.getSQLState() != null || var9.getMessage().indexOf("Socket closed") < 0) && (var9.getSQLState() == null || !var9.getSQLState().equals("08S01"))) {
                    rtaBD.setRta(false);
                    rtaBD.setMensaje(var9.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }

    public boolean getEstadoConexion() {
        return this.estadoConexion;
    }

    public ResultSet getResultSet() {
        return this.rs;
    }

    protected void finalize() {
        this.close();
    }

    public void shutdown() {
    }

    public boolean commit() {
        try {
            this.con.commit();
            return true;
        } catch (Exception var2) {
            var2.printStackTrace();
            Utilidades.writeError("Exception caught:  ", var2);
            return false;
        }
    }

    public boolean rollback() {
        try {
            this.con.rollback();
            return true;
        } catch (Exception var2) {
            var2.printStackTrace();
            Utilidades.writeError("Exception caught:  ", var2);
            return false;
        }
    }

    public String generarFechasContrato(int numeroEstudio, int consecutivo) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "{ ? = call fun_act_fechas_contrato(?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, consecutivo);
            callableStatement.setInt(3, numeroEstudio);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var6) {
            System.err.println("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            Utilidades.writeError("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            ParametrosDTO.put("mensajeError", var6.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var7) {
            System.err.println("SQLException caught: ( " + var7.getMessage());
            Utilidades.writeError("SQLException caught: (" + var7.getMessage());
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public String crearFormatoEstudioPrevio(int numeroAutorizacion, String usuario) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "{ ? = call fun_crear_estudio_previo(?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, numeroAutorizacion);
            callableStatement.setString(3, usuario);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var6) {
            System.err.println("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            Utilidades.writeError("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            ParametrosDTO.put("mensajeError", var6.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var7) {
            System.err.println("SQLException caught: ( " + var7.getMessage());
            Utilidades.writeError("SQLException caught: (" + var7.getMessage());
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public String crearFormatoContrato(int idconsecutivo, String usuario) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "";
            sqlInsert = "{ ? = call fun_crear_contrato(?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, idconsecutivo);
            callableStatement.setString(3, usuario);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var6) {
            System.err.println("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            Utilidades.writeError("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            ParametrosDTO.put("mensajeError", var6.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var7) {
            System.err.println("SQLException caught: ( " + var7.getMessage());
            Utilidades.writeError("SQLException caught: (" + var7.getMessage());
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public String crearFormatoAdicion(int consecutivoContrato, int consecutivoAdicion, String usuario) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "";
            sqlInsert = "{ ? = call fun_crear_adicion_contrato(?,?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, consecutivoContrato);
            callableStatement.setInt(3, consecutivoAdicion);
            callableStatement.setString(4, usuario);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var7) {
            System.err.println("SQLException caught: ( " + var7.getSQLState() + " ) " + var7.getMessage());
            Utilidades.writeError("SQLException caught: ( " + var7.getSQLState() + " ) " + var7.getMessage());
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var8) {
            System.err.println("SQLException caught: ( " + var8.getMessage());
            Utilidades.writeError("SQLException caught: (" + var8.getMessage());
            ParametrosDTO.put("mensajeError", var8.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public String crearFormatoActaInicio(int consecutivoContrato, String usuario) {
        try {
            CallableStatement callableStatement = null;
            String sqlInsert = "";
            sqlInsert = "{ ? = call fun_crear_acta_inicio(?,?)}";
            callableStatement = this.con.prepareCall(sqlInsert);
            callableStatement.registerOutParameter(1, 12);
            callableStatement.setInt(2, consecutivoContrato);
            callableStatement.setString(3, usuario);
            callableStatement.execute();
            String mensaje = callableStatement.getString(1);
            return mensaje;
        } catch (SQLException var6) {
            System.err.println("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            Utilidades.writeError("SQLException caught: ( " + var6.getSQLState() + " ) " + var6.getMessage());
            ParametrosDTO.put("mensajeError", var6.getMessage());
            ParametrosDTO.put("error", "1");
        } catch (Exception var7) {
            System.err.println("SQLException caught: ( " + var7.getMessage());
            Utilidades.writeError("SQLException caught: (" + var7.getMessage());
            ParametrosDTO.put("mensajeError", var7.getMessage());
            ParametrosDTO.put("error", "1");
        }

        return null;
    }

    public RespuestaBD actulizarClobPlantillas(String nombrePlantilla, byte[] archivo) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < 100) {
            ParametrosDTO.put("error", "0");
            ++veces;

            try {
                String updateTableSQL = "UPDATE sis_plantillas_documentos SET documento  = ? WHERE codigo  = ?";
                PreparedStatement preparedStatement = this.con.prepareStatement(updateTableSQL);
                String str = new String(archivo, "UTF-8");
                preparedStatement.setString(1, str);
                preparedStatement.setString(2, nombrePlantilla);
                preparedStatement.executeUpdate();
                return null;
            } catch (SQLException var8) {
                Utilidades.writeError("actulizarClobPlantillas", var8);
                var8.printStackTrace();
                ParametrosDTO.put("mensajeError", var8.getMessage());
                ParametrosDTO.put("error", "1");
                if (this.con != null && (var8.getSQLState() != null || var8.getMessage().indexOf("socket write error") < 0) && (var8.getSQLState() != null || var8.getMessage().indexOf("Socket closed") < 0) && (var8.getSQLState() == null || !var8.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var8.getMessage());
                } else {
                    Utilidades.writeInfo("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            } catch (Exception var9) {
                Utilidades.writeError("actulizarClobPlantillas", var9);
                var9.printStackTrace();
            }
        }

        return rtaBD;
    }

    public RespuestaBD enviarSolicitudesEncuesta(int encuesta, String usuario) {
        RespuestaBD rtaBD = new RespuestaBD();
        int veces = 0;

        while(veces < ParametrosDTO.getInt("sinco.maxconn")) {
            rtaBD.setRta(false);
            rtaBD.setMensaje("");
            ++veces;

            try {
                CallableStatement callableStatement = null;
                String sqlInsert = "{ ? = call Pack_Proceso_Diario.Fun_enviar_Encuesta(?,?)}";
                if (ParametrosDTO.getInt("tipoBaseDatos") != 5) {
                    sqlInsert = "{ ? = call Fun_enviar_Encuesta(?,?)}";
                }

                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.grabarLog(sqlInsert + " " + encuesta);
                }

                callableStatement = this.con.prepareCall(sqlInsert);
                callableStatement.registerOutParameter(1, 12);
                callableStatement.setInt(2, encuesta);
                callableStatement.setString(3, usuario);
                callableStatement.execute();
                String rta = callableStatement.getString(1);
                if (rta == null) {
                    rtaBD.setRta(true);
                } else {
                    Utilidades.writeError("Error enviarSolicitudesEncuesta " + encuesta + " " + rta);
                }

                rtaBD.setMensaje(rta);
                return rtaBD;
            } catch (SQLException var8) {
                Utilidades.writeError("enviarSolicitudesEncuesta", var8);
                var8.printStackTrace();
                if (this.con != null && (var8.getSQLState() != null || var8.getMessage().indexOf("socket write error") < 0) && (var8.getSQLState() != null || var8.getMessage().indexOf("Socket closed") < 0) && var8.getMessage().indexOf("PACK_PROCESO_DIARIO") < 0 && (var8.getSQLState() == null || !var8.getSQLState().equals("08S01"))) {
                    rtaBD.setMensaje(var8.getMessage());
                    veces = ParametrosDTO.getInt("sinco.maxconn") + 10;
                } else {
                    Utilidades.grabarLog("parseSql: Reemplazo conexion veces=" + veces);
                    this.cerrar();
                    this.createConnection();
                }
            }
        }

        return rtaBD;
    }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DBManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */