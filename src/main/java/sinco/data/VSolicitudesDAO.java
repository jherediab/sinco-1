/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.FechaDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.business.VSolicitudesDTO;
/*      */ import sinco.data.DBManager;
/*      */ import sinco.data.VSolicitudesDAO;
/*      */ 
/*      */ public class VSolicitudesDAO
/*      */ {
/*      */   ResultSet rs;
/*      */   
/*      */   public void close() {
/*      */     try {
/*   21 */       this.dat.close();
/*      */     }
/*   23 */     catch (Exception e) {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   28 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */   
/*   32 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSolicitudesDTO leerRegistro() {
/*      */     try {
/*   42 */       VSolicitudesDTO reg = new VSolicitudesDTO();
/*   43 */       reg.setNumero(this.rs.getInt("numero"));
/*   44 */       String temporal = this.rs.getString("fecha_generada");
/*   45 */       reg.setFechaGenerada(temporal);
/*   46 */       reg.setAreaCliente(this.rs.getInt("area_cliente"));
/*   47 */       reg.setEmpleadoCliente(this.rs.getInt("empleado_cliente"));
/*   48 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*   49 */       reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/*   50 */       reg.setEmpleadoProveedor(this.rs.getInt("empleado_proveedor"));
/*   51 */       reg.setCodigoEstado(this.rs.getInt("codigo_estado"));
/*   52 */       reg.setEstadoAnterior(this.rs.getInt("estado_anterior"));
/*   53 */       reg.setDuracion(this.rs.getInt("duracion"));
/*   54 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*      */       
/*   56 */       if (this.rs.getString("abierta").toLowerCase().equals("s")) {
/*   57 */         reg.setAbierta(true);
/*      */       } else {
/*   59 */         reg.setAbierta(false);
/*      */       } 
/*   61 */       reg.setNivelEscalamiento(this.rs.getInt("nivel_escalamiento"));
/*      */       
/*   63 */       reg.setProveedorAnterior(this.rs.getInt("proveedor_anterior"));
/*   64 */       reg.setNotificar(this.rs.getString("notificar"));
/*      */       
/*   66 */       String temp = this.rs.getString("fecha_vigencia");
/*      */       
/*   68 */       if (!this.rs.wasNull()) {
/*   69 */         reg.setFechaVigencia(temp);
/*      */       } else {
/*      */         
/*   72 */         reg.setFechaVigencia("");
/*      */       } 
/*      */       
/*   75 */       temp = this.rs.getString("fecha_estimada_terminacion");
/*      */       
/*   77 */       if (!this.rs.wasNull()) {
/*   78 */         reg.setFechaEstimadaTerminacion(temp);
/*      */       } else {
/*      */         
/*   81 */         reg.setFechaEstimadaTerminacion("");
/*      */       } 
/*      */       
/*   84 */       temp = this.rs.getString("fecha_real_terminacion");
/*      */       
/*   86 */       if (!this.rs.wasNull()) {
/*   87 */         reg.setFechaRealTerminacion(temp);
/*      */       } else {
/*      */         
/*   90 */         reg.setFechaRealTerminacion("");
/*      */       } 
/*      */       
/*   93 */       temp = this.rs.getString("fecha_base_escalamientos");
/*      */       
/*   95 */       if (!this.rs.wasNull()) {
/*   96 */         reg.setFechaBaseEscalamientos(temp);
/*      */       } else {
/*      */         
/*   99 */         reg.setFechaBaseEscalamientos("");
/*      */       } 
/*      */ 
/*      */       
/*  103 */       temp = this.rs.getString("solicitud_padre");
/*      */       
/*  105 */       if (!this.rs.wasNull()) {
/*  106 */         reg.setSolicitudPadre(Integer.parseInt(temp));
/*      */       } else {
/*      */         
/*  109 */         reg.setSolicitudPadre(-1);
/*      */       } 
/*      */       
/*  112 */       temp = this.rs.getString("fecha_oportunidad");
/*      */       
/*  114 */       if (!this.rs.wasNull()) {
/*  115 */         reg.setFechaOportunidad(temp);
/*      */       } else {
/*      */         
/*  118 */         reg.setFechaOportunidad("");
/*      */       } 
/*      */       
/*  121 */       temp = this.rs.getString("codigo_oportunidad");
/*      */       
/*  123 */       if (!this.rs.wasNull()) {
/*  124 */         reg.setCodigoOportunidad(temp);
/*      */       } else {
/*      */         
/*  127 */         reg.setCodigoOportunidad("");
/*      */       } 
/*      */       
/*  130 */       temp = this.rs.getString("codigo_confiabilidad");
/*      */       
/*  132 */       if (!this.rs.wasNull()) {
/*  133 */         reg.setCodigoConfiabilidad(temp);
/*      */       } else {
/*      */         
/*  136 */         reg.setCodigoConfiabilidad("");
/*      */       } 
/*      */       
/*  139 */       temp = this.rs.getString("fecha_confiabilidad");
/*      */       
/*  141 */       if (!this.rs.wasNull()) {
/*  142 */         reg.setFechaConfiabilidad(temp);
/*      */       } else {
/*      */         
/*  145 */         reg.setFechaConfiabilidad("");
/*      */       } 
/*      */       
/*  148 */       temp = this.rs.getString("observaciones");
/*      */       
/*  150 */       if (!this.rs.wasNull()) {
/*  151 */         reg.setObservaciones(temp);
/*      */       } else {
/*      */         
/*  154 */         reg.setObservaciones("");
/*      */       } 
/*      */       
/*  157 */       reg.setCiclo(this.rs.getString("ciclo"));
/*  158 */       reg.setEncuesta(this.rs.getInt("encuesta"));
/*      */       
/*  160 */       reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/*  161 */       reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/*  162 */       reg.setNombreCliente(this.rs.getString("nombre_cliente"));
/*  163 */       reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/*  164 */       reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/*  165 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*      */       
/*  167 */       reg.setTipoServicio(this.rs.getInt("tipo_servicio"));
/*  168 */       reg.setEmpleadoAuditor(this.rs.getInt("empleado_auditor"));
/*  169 */       reg.setTipoEstado(this.rs.getString("tipo_estado"));
/*  170 */       reg.setTipoCalificacionAuditor(this.rs.getString("tipo_calificacion_auditor"));
/*  171 */       reg.setMacroServicio(this.rs.getString("macro_servicio"));
/*  172 */       reg.setNumeroDevoluciones(this.rs.getInt("numero_devoluciones"));
/*  173 */       reg.setTiempoServicio(this.rs.getInt("tiempo_servicio"));
/*  174 */       reg.setSecuenciaExterna(this.rs.getInt("secuencia_externa"));
/*  175 */       reg.setNumeroMostrar(this.rs.getInt("numero_mostrar"));
/*  176 */       reg.setNumeroFlujo(this.rs.getInt("Numero_Flujo"));
/*  177 */       return reg;
/*      */     }
/*  179 */     catch (SQLException e) {
/*  180 */       e.printStackTrace();
/*  181 */       Utilidades.writeError("leerRegistro", e);
/*      */       
/*  183 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSolicitudesDTO next() {
/*      */     try {
/*  192 */       if (this.rs.next()) {
/*  193 */         return leerRegistro();
/*      */       }
/*      */     }
/*  196 */     catch (SQLException e) {
/*  197 */       Utilidades.writeError("next", e);
/*  198 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  201 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosNoEnviadasPedidasPor(int cliente, int solicitud) {
/*      */     try {
/*  213 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  257 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  258 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/*  261 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/*  263 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s     left join Unidades_Dependencia Areap on(s.Area_Proveedor = Areap.Codigo)   left join sis_usuarios Personap  on(s.Empleado_Proveedor = Personap.Codigo_Empleado),   Servicios            Ser,   Unidades_Dependencia Areac,   sis_usuarios         Personac,   Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.empleado_cliente=" + cliente + "        and s.abierta='S'" + "        and e.tipo_estado='INI'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  289 */       if (solicitud > 0) {
/*  290 */         s = s + " and s.solicitud_padre=" + solicitud;
/*      */       }
/*  292 */       s = s + " order by s.numero";
/*      */       
/*  294 */       boolean rta = this.dat.parseSql(s);
/*  295 */       if (!rta) return false; 
/*  296 */       this.rs = this.dat.getResultSet();
/*  297 */       return true;
/*      */     }
/*  299 */     catch (Exception e) {
/*  300 */       e.printStackTrace();
/*  301 */       Utilidades.writeError("cargarTodosNoEnviadasPedidasPor ", e);
/*      */       
/*  303 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosPorCalificarPor(int cliente) {
/*      */     try {
/*  314 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  358 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  359 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/*  362 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/*  364 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.empleado_cliente=" + cliente + "        and s.abierta='S'" + "        and e.tipo_estado='CAL'" + " order by fecha_estimada_terminacion,numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  393 */       boolean rta = this.dat.parseSql(s);
/*  394 */       if (!rta) return false; 
/*  395 */       this.rs = this.dat.getResultSet();
/*  396 */       return true;
/*      */     }
/*  398 */     catch (Exception e) {
/*  399 */       e.printStackTrace();
/*  400 */       Utilidades.writeError("cargarTodosPorCalificarPor ", e);
/*      */       
/*  402 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosPorCalificarA(int Proveedor) {
/*      */     try {
/*  413 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  457 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  458 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/*  461 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/*  463 */       s = s + "case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.empleado_proveedor=" + Proveedor + "        and s.abierta='S'" + "        and e.tipo_estado='CAL'" + " order by fecha_estimada_terminacion,numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  492 */       boolean rta = this.dat.parseSql(s);
/*  493 */       if (!rta) return false; 
/*  494 */       this.rs = this.dat.getResultSet();
/*  495 */       return true;
/*      */     }
/*  497 */     catch (Exception e) {
/*  498 */       e.printStackTrace();
/*  499 */       Utilidades.writeError("cargarTodosPorCalificarA ", e);
/*      */       
/*  501 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarEnEscalamientoPedidasA(int prov) {
/*  512 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */     
/*      */     try {
/*  516 */       String s = "select s.Numero,        Ser.Descripcion as Nombre_Servicio,        s.Fecha_Estimada_Terminacion,        Areac.Descripcion as Nombre_Area_Cliente,        Areap.Descripcion as Nombre_Area_Proveedora,        Personac.Apellidos || ' ' || Personac.Nombres as Nombre_Cliente,        case           when Coalesce(Ser.Autonumerar_Solicitud,                         'N') = 'S' then            Coalesce(s.Secuencia_Externa,                     s.Numero)           when Coalesce(s.Macro_Servicio,                         'N') = 'S' then            Coalesce(s.Solicitud_Padre,                     s.Numero)           else            s.Numero        end as Numero_Mostrar,        sum(case               when Eh.Tipo_Estado is not null then                1               else                0            end) Total_Hijas,        sum(case               when Eh.Tipo_Estado in ('ESC', 'PRV') then                1               else                0            end) Hijas_Abiertas from   Solicitudes          s        left join Solicitudes h on (s.Numero = h.Solicitud_Padre)        left join Estados  Eh on (h.Codigo_Estado = Eh.Codigo),        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.Abierta = 'S'        and s.nivel_escalamiento> 0         and e.tipo_estado='ESC'        and s.Empleado_Proveedor = " + prov + " group  by s.Numero," + "           Ser.Descripcion," + "           s.Fecha_Estimada_Terminacion," + "           Areac.Descripcion," + "           Areap.Descripcion," + "           Personac.Apellidos || ' ' || Personac.Nombres," + "           case" + "              when Coalesce(Ser.Autonumerar_Solicitud," + "                            'N') = 'S' then" + "               Coalesce(s.Secuencia_Externa," + "                        s.Numero)" + "              when Coalesce(s.Macro_Servicio," + "                            'N') = 'S' then" + "               Coalesce(s.Solicitud_Padre," + "                        s.Numero)" + "              else" + "               s.Numero" + "           end" + " order  by Fecha_Estimada_Terminacion,Numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  583 */       boolean rta = this.dat.parseSql(s);
/*  584 */       if (!rta) return resultados; 
/*  585 */       this.rs = this.dat.getResultSet();
/*  586 */       while (this.rs.next()) {
/*  587 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*  588 */         reg.setNumero(this.rs.getInt("numero"));
/*  589 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/*  590 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/*  591 */         reg.setNombreAreaCliente(this.rs.getString("Nombre_Area_Cliente"));
/*  592 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/*  593 */         reg.setNombreCliente(this.rs.getString("Nombre_Cliente"));
/*  594 */         reg.setNumeroMostrar(this.rs.getInt("numero_mostrar"));
/*  595 */         reg.setNumeroHijas(this.rs.getInt("Total_Hijas"));
/*  596 */         reg.setNumeroHijasAbiertas(this.rs.getInt("Hijas_Abiertas"));
/*  597 */         resultados.add(reg);
/*      */       }
/*      */     
/*  600 */     } catch (Exception e) {
/*  601 */       e.printStackTrace();
/*  602 */       Utilidades.writeError("cargarTodosEnCursoPedidasA ", e);
/*      */     } 
/*  604 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarEnEscalamientoPara(int prov) {
/*  615 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */     
/*      */     try {
/*  619 */       String s = "select s.Numero,        Ser.Descripcion as Nombre_Servicio,        s.Fecha_Estimada_Terminacion,        Areac.Descripcion as Nombre_Area_Cliente,        Areap.Descripcion as Nombre_Area_Proveedora,        Personap.Apellidos || ' ' || Personap.Nombres as Nombre_Proveedor,        case           when Coalesce(Ser.Autonumerar_Solicitud,                         'N') = 'S' then            Coalesce(s.Secuencia_Externa,                     s.Numero)           when Coalesce(s.Macro_Servicio,                         'N') = 'S' then            Coalesce(s.Solicitud_Padre,                     s.Numero)           else            s.Numero        end as Numero_Mostrar from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.Abierta            = 'S'        and s.nivel_escalamiento> 0         and e.tipo_estado        ='ESC'        and s.empleado_escalo    =" + prov + " order  by s.Fecha_Estimada_Terminacion,s.Numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  654 */       boolean rta = this.dat.parseSql(s);
/*  655 */       if (!rta) return resultados; 
/*  656 */       this.rs = this.dat.getResultSet();
/*  657 */       while (this.rs.next()) {
/*  658 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*  659 */         reg.setNumero(this.rs.getInt("numero"));
/*  660 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/*  661 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/*  662 */         reg.setNombreAreaCliente(this.rs.getString("Nombre_Area_Cliente"));
/*  663 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/*  664 */         reg.setNombreProveedor(this.rs.getString("Nombre_Proveedor"));
/*  665 */         reg.setNumeroMostrar(this.rs.getInt("numero_mostrar"));
/*  666 */         resultados.add(reg);
/*      */       }
/*      */     
/*  669 */     } catch (Exception e) {
/*  670 */       e.printStackTrace();
/*  671 */       Utilidades.writeError("cargarTodosEnCursoPedidasA ", e);
/*      */     } 
/*  673 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosCerradasPedidasA(int prov, String fechaInicio, String fechaFin) {
/*      */     try {
/*  686 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  730 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  731 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/*  734 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/*  736 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.empleado_proveedor=" + prov + "        and e.tipo_estado  not in('INI','CAL','DV','AN')" + "        and s.abierta='N'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  764 */       if (fechaInicio.length() > 0) {
/*  765 */         s = s + " and s.fecha_real_terminacion>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/*  768 */       if (fechaFin.length() > 0) {
/*  769 */         s = s + " and s.fecha_real_terminacion<" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*  771 */       s = s + " order by s.fecha_estimada_terminacion,s.numero";
/*      */       
/*  773 */       boolean rta = this.dat.parseSql(s);
/*  774 */       if (!rta) return false; 
/*  775 */       this.rs = this.dat.getResultSet();
/*  776 */       return true;
/*      */     }
/*  778 */     catch (Exception e) {
/*  779 */       e.printStackTrace();
/*  780 */       Utilidades.writeError("cargarTodosCerradasPedidasA ", e);
/*      */       
/*  782 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarTodosEnCursoPedidasAArea(int prov) {
/*  791 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */     
/*      */     try {
/*  795 */       String s = "select s.Numero,        Ser.Descripcion as Nombre_Servicio,        s.Fecha_Estimada_Terminacion,        Areac.Descripcion as Nombre_Area_Cliente,        Areap.Descripcion as Nombre_Area_Proveedora,        Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,        Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,        case           when Coalesce(Ser.Autonumerar_Solicitud,                         'N') = 'S' then            Coalesce(s.Secuencia_Externa,                     s.Numero)           when Coalesce(s.Macro_Servicio,                         'N') = 'S' then            Coalesce(s.Solicitud_Padre,                     s.Numero)           else            s.Numero        end as Numero_Mostrar,        sum(case               when Eh.Tipo_Estado is not null then                1               else                0            end) Total_Hijas,        sum(case               when Eh.Tipo_Estado in ('ESC', 'PRV') then                1               else                0            end) Hijas_Abiertas from   Solicitudes s        left join Solicitudes h on (s.Numero = h.Solicitud_Padre)        left join Estados  Eh on (h.Codigo_Estado = Eh.Codigo),        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personap,        sis_usuarios         Personac,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_proveedor = Personap.Codigo_Empleado        and s.Empleado_cliente = Personac.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.Abierta = 'S'        and e.Tipo_Estado in ('ESC', 'PRV')        and s.area_proveedor     =" + prov + " group  by s.Numero," + "           Ser.Descripcion," + "           s.Fecha_Estimada_Terminacion," + "           Areac.Descripcion," + "           Areap.Descripcion," + "           Personac.Apellidos || ' ' || Personac.Nombres," + "           Personap.Apellidos || ' ' || Personap.Nombres," + "           case" + "              when Coalesce(Ser.Autonumerar_Solicitud," + "                            'N') = 'S' then" + "               Coalesce(s.Secuencia_Externa," + "                        s.Numero)" + "              when Coalesce(s.Macro_Servicio," + "                            'N') = 'S' then" + "               Coalesce(s.Solicitud_Padre," + "                        s.Numero)" + "              else" + "               s.Numero" + "           end" + " order  by Fecha_Estimada_Terminacion,Numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  865 */       boolean rta = this.dat.parseSql(s);
/*  866 */       if (!rta) return resultados; 
/*  867 */       this.rs = this.dat.getResultSet();
/*  868 */       while (this.rs.next()) {
/*  869 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*  870 */         reg.setNumero(this.rs.getInt("numero"));
/*  871 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/*  872 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/*  873 */         reg.setNombreAreaCliente(this.rs.getString("Nombre_Area_Cliente"));
/*  874 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/*  875 */         reg.setNombreCliente(this.rs.getString("Nombre_Cliente"));
/*  876 */         reg.setNombreProveedor(this.rs.getString("Nombre_proveedor"));
/*  877 */         reg.setNumeroMostrar(this.rs.getInt("numero_mostrar"));
/*  878 */         reg.setNumeroHijas(this.rs.getInt("Total_Hijas"));
/*  879 */         reg.setNumeroHijasAbiertas(this.rs.getInt("Hijas_Abiertas"));
/*  880 */         resultados.add(reg);
/*      */       }
/*      */     
/*  883 */     } catch (Exception e) {
/*  884 */       e.printStackTrace();
/*  885 */       Utilidades.writeError("cargarTodosEnCursoPedidasA ", e);
/*      */     } 
/*  887 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarTodosEnCursoPedidasA(int prov) {
/*  897 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */     
/*      */     try {
/*  901 */       String s = "select s.Numero,        Ser.Descripcion as Nombre_Servicio,        s.Fecha_Estimada_Terminacion,        Areac.Descripcion as Nombre_Area_Cliente,        Areap.Descripcion as Nombre_Area_Proveedora,        Personac.Apellidos || ' ' || Personac.Nombres as Nombre_Cliente,        Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,        case           when Coalesce(Ser.Autonumerar_Solicitud,                         'N') = 'S' then            Coalesce(s.Secuencia_Externa,                     s.Numero)           when Coalesce(s.Macro_Servicio,                         'N') = 'S' then            Coalesce(s.Solicitud_Padre,                     s.Numero)           else            s.Numero        end as Numero_Mostrar,        sum(case               when Eh.Tipo_Estado is not null then                1               else                0            end) Total_Hijas,        sum(case               when Eh.Tipo_Estado in ('ESC', 'PRV') then                1               else                0            end) Hijas_Abiertas from   Solicitudes          s        left join Solicitudes h on (s.Numero = h.Solicitud_Padre)        left join Estados  Eh on (h.Codigo_Estado = Eh.Codigo),        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.Abierta = 'S'        and e.Tipo_Estado in ('ESC', 'PRV')        and s.Empleado_Proveedor = " + prov + " group  by s.Numero," + "           Ser.Descripcion," + "           s.Fecha_Estimada_Terminacion," + "           Areac.Descripcion," + "           Areap.Descripcion," + "           Personac.Apellidos || ' ' || Personac.Nombres," + "           Personap.Apellidos || ' ' || Personap.Nombres," + "           case" + "              when Coalesce(Ser.Autonumerar_Solicitud," + "                            'N') = 'S' then" + "               Coalesce(s.Secuencia_Externa," + "                        s.Numero)" + "              when Coalesce(s.Macro_Servicio," + "                            'N') = 'S' then" + "               Coalesce(s.Solicitud_Padre," + "                        s.Numero)" + "              else" + "               s.Numero" + "           end" + " order  by Fecha_Estimada_Terminacion,Numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  971 */       boolean rta = this.dat.parseSql(s);
/*  972 */       if (!rta) return resultados; 
/*  973 */       this.rs = this.dat.getResultSet();
/*  974 */       while (this.rs.next()) {
/*  975 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*  976 */         reg.setNumero(this.rs.getInt("numero"));
/*  977 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/*  978 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/*  979 */         reg.setNombreAreaCliente(this.rs.getString("Nombre_Area_Cliente"));
/*  980 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/*  981 */         reg.setNombreCliente(this.rs.getString("Nombre_Cliente"));
/*  982 */         reg.setNombreProveedor(this.rs.getString("Nombre_proveedor"));
/*  983 */         reg.setNumeroMostrar(this.rs.getInt("numero_mostrar"));
/*  984 */         reg.setNumeroHijas(this.rs.getInt("Total_Hijas"));
/*  985 */         reg.setNumeroHijasAbiertas(this.rs.getInt("Hijas_Abiertas"));
/*  986 */         resultados.add(reg);
/*      */       }
/*      */     
/*  989 */     } catch (Exception e) {
/*  990 */       e.printStackTrace();
/*  991 */       Utilidades.writeError("cargarTodosEnCursoPedidasA ", e);
/*      */     } 
/*  993 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosEnCursoPedidasPor(int cliente) {
/*      */     try {
/* 1005 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1049 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1050 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1053 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1055 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.empleado_cliente=" + cliente + "        and s.abierta            ='S'" + "        and e.tipo_estado in ('PRV', 'ESC')" + " order by s.fecha_estimada_terminacion,s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1084 */       boolean rta = this.dat.parseSql(s);
/* 1085 */       if (!rta) return false; 
/* 1086 */       this.rs = this.dat.getResultSet();
/* 1087 */       return true;
/*      */     }
/* 1089 */     catch (Exception e) {
/* 1090 */       e.printStackTrace();
/* 1091 */       Utilidades.writeError("cargarTodosEnCursoPedidasPor ", e);
/*      */       
/* 1093 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosCerradasPedidasPor(int cliente, String fechaInicio, String fechaFin) {
/*      */     try {
/* 1107 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1151 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1152 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1155 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1157 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.abierta            ='N'        and e.tipo_estado not in ('INI','AN','DV','CAL')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1183 */       if (fechaInicio != null) {
/* 1184 */         s = s + " and s.fecha_generada>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/* 1186 */       if (fechaFin != null) {
/* 1187 */         s = s + " and s.fecha_generada<=" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*      */       
/* 1190 */       s = s + "        and s.empleado_cliente   = " + cliente;
/*      */       
/* 1192 */       s = s + " order by s.fecha_estimada_terminacion,s.numero";
/*      */       
/* 1194 */       boolean rta = this.dat.parseSql(s);
/* 1195 */       if (!rta) return false; 
/* 1196 */       this.rs = this.dat.getResultSet();
/* 1197 */       return true;
/*      */     
/*      */     }
/* 1200 */     catch (Exception e) {
/* 1201 */       e.printStackTrace();
/* 1202 */       Utilidades.writeError("cargarTodosCerradasPedidasPor ", e);
/*      */       
/* 1204 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarAnuladasPedidasA(int proveedor, String fechaInicio, String fechaFin) {
/*      */     try {
/* 1218 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1262 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1263 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1266 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1268 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.empleado_proveedor =" + proveedor + "        and s.abierta            ='N'" + "        and e.tipo_estado in('AN')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1295 */       if (fechaInicio != null) {
/* 1296 */         s = s + " and s.fecha_generada>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/* 1298 */       if (fechaFin != null) {
/* 1299 */         s = s + " and s.fecha_generada<=" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*      */       
/* 1302 */       s = s + " order by s.fecha_estimada_terminacion,s.numero";
/*      */       
/* 1304 */       boolean rta = this.dat.parseSql(s);
/* 1305 */       if (!rta) return false; 
/* 1306 */       this.rs = this.dat.getResultSet();
/* 1307 */       return true;
/*      */ 
/*      */     
/*      */     }
/* 1311 */     catch (Exception e) {
/* 1312 */       e.printStackTrace();
/* 1313 */       Utilidades.writeError("cargarAnuladasPedidasA ", e);
/*      */       
/* 1315 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarAnuladasPedidasPor(int cliente, String fechaInicio, String fechaFin) {
/*      */     try {
/* 1328 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1372 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1373 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1376 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1378 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.empleado_cliente=" + cliente + "        and e.tipo_estado in('AN')" + "        and s.abierta            ='N'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1406 */       if (fechaInicio != null) {
/* 1407 */         s = s + " and s.fecha_generada>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/* 1409 */       if (fechaFin != null) {
/* 1410 */         s = s + " and s.fecha_generada<=" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*      */       
/* 1413 */       s = s + " order by s.fecha_estimada_terminacion,s.numero";
/*      */       
/* 1415 */       boolean rta = this.dat.parseSql(s);
/* 1416 */       if (!rta) return false; 
/* 1417 */       this.rs = this.dat.getResultSet();
/* 1418 */       return true;
/*      */     }
/* 1420 */     catch (Exception e) {
/* 1421 */       e.printStackTrace();
/* 1422 */       Utilidades.writeError("cargarAnuladasPedidasPor ", e);
/*      */       
/* 1424 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarHijasGeneradas(int nroSol) {
/* 1435 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 1438 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1482 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1483 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1486 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1488 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.solicitud_padre=" + nroSol + "        and e.tipo_estado in('INI')" + " order by fecha_estimada_terminacion,numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1516 */       boolean rta = this.dat.parseSql(s);
/* 1517 */       if (!rta) {
/* 1518 */         return resultados;
/*      */       }
/* 1520 */       this.rs = this.dat.getResultSet();
/* 1521 */       while (this.rs.next()) {
/* 1522 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1525 */     catch (Exception e) {
/* 1526 */       e.printStackTrace();
/* 1527 */       Utilidades.writeError("cargarHijasGeneradas ", e);
/*      */     } 
/* 1529 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int existenHijas(int nroSol) {
/* 1541 */     int respuesta = 0;
/*      */     
/*      */     try {
/* 1544 */       String s = "select count(0) numero_registros from   Solicitudes s,Estados e where s.Codigo_Estado      = e.Codigo  and e.tipo_estado in('INI')  and s.solicitud_padre=" + nroSol;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1550 */       this.dat.parseSql(s);
/* 1551 */       this.rs = this.dat.getResultSet();
/*      */       
/* 1553 */       while (this.rs.next()) {
/* 1554 */         respuesta = this.rs.getInt("numero_registros");
/*      */       }
/*      */       
/* 1557 */       return respuesta;
/*      */     }
/* 1559 */     catch (Exception e) {
/* 1560 */       e.printStackTrace();
/* 1561 */       Utilidades.writeError("existenHijas ", e);
/*      */       
/* 1563 */       return respuesta;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarTodosDevueltasPedidasPor(int cliente, int prTipoSolicitud) {
/*      */     try {
/* 1577 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1621 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1622 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1625 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1627 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.empleado_cliente=" + cliente + "        and s.abierta            ='S'" + "        and e.tipo_estado in('DV','NAT')" + " order by fecha_estimada_terminacion,numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1656 */       boolean rta = this.dat.parseSql(s);
/* 1657 */       if (!rta) return false; 
/* 1658 */       this.rs = this.dat.getResultSet();
/* 1659 */       return true;
/*      */     }
/* 1661 */     catch (Exception e) {
/* 1662 */       e.printStackTrace();
/* 1663 */       Utilidades.writeError("cargarTodosDevueltasPedidasPor ", e);
/*      */       
/* 1665 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarSolicitudesDeEstado(int tipo, int prov, String fechaInicio, String fechaFinal, int estado) {
/*      */     try {
/* 1681 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1725 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1726 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1729 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1731 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1756 */       switch (tipo) {
/*      */         case 1:
/* 1758 */           s = s + " and s.area_proveedor=" + prov;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1762 */           s = s + " and s.empleado_proveedor=" + prov;
/*      */           break;
/*      */       } 
/*      */       
/* 1766 */       if (fechaInicio.length() > 0) {
/* 1767 */         s = s + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 1770 */       if (fechaFinal.length() > 0) {
/* 1771 */         s = s + " and s.fecha_vigencia<=" + Utilidades.formatoFecha2(fechaFinal);
/*      */       }
/*      */       
/* 1774 */       if (estado > 0) {
/* 1775 */         s = s + " and s.codigo_estado=" + estado;
/*      */       }
/*      */       
/* 1778 */       s = s + " order by s.codigo_estado,s.fecha_vigencia,s.numero";
/*      */ 
/*      */       
/* 1781 */       boolean rta = this.dat.parseSql(s);
/* 1782 */       if (!rta) return false; 
/* 1783 */       this.rs = this.dat.getResultSet();
/* 1784 */       return true;
/*      */     }
/* 1786 */     catch (Exception e) {
/* 1787 */       e.printStackTrace();
/* 1788 */       Utilidades.writeError("cargarSolicitudesDeEstado ", e);
/*      */       
/* 1790 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarDelAreaAuditada(int area, int anno) {
/* 1803 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 1806 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1850 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1851 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1854 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1856 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.numero in(            select solicitud from detalle_auditoria            where area_auditada=" + area + " ) " + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2("" + anno + "-01-01") + " and s.fecha_vigencia<" + Utilidades.formatoFecha2("" + (anno + 1) + "-01-01") + " order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1888 */       boolean rta = this.dat.parseSql(s);
/* 1889 */       if (!rta) {
/* 1890 */         return resultados;
/*      */       }
/* 1892 */       this.rs = this.dat.getResultSet();
/* 1893 */       while (this.rs.next()) {
/* 1894 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1897 */     catch (Exception e) {
/* 1898 */       e.printStackTrace();
/* 1899 */       Utilidades.writeError("cargarDelAreaAuditada ", e);
/*      */     } 
/* 1901 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSolicitudesDTO getSolicitud(int numero) {
/*      */     try {
/* 1913 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1957 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1958 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 1961 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 1963 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.numero=" + numero + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1990 */       boolean rta = this.dat.parseSql(s);
/* 1991 */       if (!rta) return null; 
/* 1992 */       this.rs = this.dat.getResultSet();
/* 1993 */       if (this.rs.next()) {
/* 1994 */         return leerRegistro();
/*      */       }
/*      */     }
/* 1997 */     catch (SQLException e) {
/* 1998 */       e.printStackTrace();
/* 1999 */       Utilidades.writeError("getSolicitud ", e);
/*      */     } 
/*      */ 
/*      */     
/* 2003 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean participantes(int encuesta) {
/*      */     try {
/* 2014 */       String s = "select estados.descripcion, count(0) as numero   from solicitudes,estados where solicitudes.codigo_estado=estados.codigo  and encuesta=" + encuesta + " group by descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2020 */       boolean rta = this.dat.parseSql(s);
/* 2021 */       if (!rta) return false; 
/* 2022 */       this.rs = this.dat.getResultSet();
/* 2023 */       return true;
/*      */     }
/* 2025 */     catch (Exception e) {
/* 2026 */       e.printStackTrace();
/* 2027 */       Utilidades.writeError("participantes ", e);
/*      */       
/* 2029 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSolicitudesDTO siguienteResultado() {
/*      */     try {
/* 2039 */       if (this.rs.next()) {
/* 2040 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2041 */         reg.setNumero(this.rs.getInt("numero"));
/* 2042 */         reg.setObservaciones(this.rs.getString("descripcion"));
/* 2043 */         return reg;
/*      */       }
/*      */     
/* 2046 */     } catch (SQLException e) {
/* 2047 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 2050 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean actualizarCampo(int sol, String campo, String valor, boolean com, String usuario) {
/* 2064 */     if (com) {
/* 2065 */       valor = "'" + valor + "'";
/*      */     }
/* 2067 */     String s = "update solicitudes set " + campo + "=" + valor + ",";
/* 2068 */     s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 2069 */     s = s + "usuario_modificacion='" + usuario + "'";
/* 2070 */     s = s + "where numero=" + sol;
/* 2071 */     return this.dat.executeUpdate(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean actualizarCampos(int sol, String set) {
/* 2081 */     String temp = "update solicitudes set " + set + " where numero=" + sol;
/* 2082 */     return this.dat.executeUpdate(temp);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasEnCursoPedidasA(int prov) {
/*      */     try {
/* 2092 */       String s = "select count(0) as numero  from solicitudes  where empleado_proveedor=" + prov + "" + "      and abierta='S'" + "      and codigo_estado in (" + "        select codigo from estados " + "           where tipo_estado in('ESC','PRV'))";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2099 */       boolean rta = this.dat.parseSql(s);
/* 2100 */       if (!rta) return 0; 
/* 2101 */       this.rs = this.dat.getResultSet();
/* 2102 */       if (this.rs.next()) {
/* 2103 */         return this.rs.getInt("numero");
/*      */       }
/*      */     }
/* 2106 */     catch (Exception e) {
/* 2107 */       e.printStackTrace();
/* 2108 */       Utilidades.writeError("cuantasEnCursoPedidasA ", e);
/*      */     } 
/* 2110 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasEnCursoPedidasPor(int cliente) {
/*      */     try {
/* 2120 */       String s = "select count(0) as numero   from solicitudes s,estados e  where s.empleado_cliente=" + cliente + "        and s.abierta='S'" + "        and s.codigo_estado =e.codigo " + "        and e.tipo_Estado not in('INI','CAL','DV')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2126 */       boolean rta = this.dat.parseSql(s);
/* 2127 */       if (!rta) return 0; 
/* 2128 */       this.rs = this.dat.getResultSet();
/* 2129 */       int numero = 0;
/* 2130 */       while (this.rs.next()) {
/* 2131 */         numero += this.rs.getInt("numero");
/*      */       }
/* 2133 */       return numero;
/*      */     }
/* 2135 */     catch (Exception e) {
/* 2136 */       e.printStackTrace();
/* 2137 */       Utilidades.writeError("cuantasEnCursoPedidasPor ", e);
/*      */       
/* 2139 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasPorCalificarPor(int cliente) {
/*      */     try {
/* 2150 */       String s = "select count(0) as numero   from solicitudes s,estados e  where s.empleado_cliente=" + cliente + "        and s.abierta='S'" + "        and s.codigo_estado =e.codigo " + "        and  e.tipo_Estado in('CAL')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2157 */       boolean rta = this.dat.parseSql(s);
/* 2158 */       if (!rta) return 0; 
/* 2159 */       this.rs = this.dat.getResultSet();
/* 2160 */       if (this.rs.next()) {
/* 2161 */         return this.rs.getInt("numero");
/*      */       }
/*      */     }
/* 2164 */     catch (Exception e) {
/* 2165 */       e.printStackTrace();
/* 2166 */       Utilidades.writeError("cuantasPorCalificarPor ", e);
/*      */     } 
/* 2168 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasPorEnviarPor(int cliente) {
/*      */     try {
/* 2179 */       String s = "select count(0) as numero   from solicitudes s,estados e  where s.empleado_cliente=" + cliente + "        and s.abierta='S'" + "        and s.codigo_estado =e.codigo " + "        and  e.tipo_Estado in('INI')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2186 */       boolean rta = this.dat.parseSql(s);
/* 2187 */       if (!rta) return 0; 
/* 2188 */       this.rs = this.dat.getResultSet();
/* 2189 */       int numero = 0;
/* 2190 */       if (this.rs.next()) {
/* 2191 */         numero += this.rs.getInt("numero");
/*      */       }
/* 2193 */       return numero;
/*      */     }
/* 2195 */     catch (Exception e) {
/* 2196 */       e.printStackTrace();
/* 2197 */       Utilidades.writeError("cuantasPorCalificarPor ", e);
/*      */       
/* 2199 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasEscaladasA(int prov) {
/*      */     try {
/* 2209 */       String s = "select count(0) as numero   from solicitudes s,estados e  where s.empleado_proveedor=" + prov + "        and s.abierta='S'" + "        and s.nivel_escalamiento> 0" + "        and s.codigo_estado =e.codigo " + "        and  e.tipo_Estado in('ESC')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2217 */       boolean rta = this.dat.parseSql(s);
/* 2218 */       if (!rta) return 0; 
/* 2219 */       this.rs = this.dat.getResultSet();
/* 2220 */       if (this.rs.next()) {
/* 2221 */         return this.rs.getInt("numero");
/*      */       }
/*      */     }
/* 2224 */     catch (Exception e) {
/* 2225 */       e.printStackTrace();
/* 2226 */       Utilidades.writeError("cuantasEscaladasA ", e);
/*      */     } 
/* 2228 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantasEscaladasPara(int prov) {
/*      */     try {
/* 2239 */       String s = "select count(0) as numero   from solicitudes s,estados e  where s.abierta='S'        and s.nivel_escalamiento> 0        and s.codigo_estado =e.codigo         and  e.tipo_Estado in('ESC')        and s.empleado_escalo=" + prov;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2247 */       boolean rta = this.dat.parseSql(s);
/* 2248 */       if (!rta) return 0; 
/* 2249 */       this.rs = this.dat.getResultSet();
/* 2250 */       if (this.rs.next()) {
/* 2251 */         return this.rs.getInt("numero");
/*      */       }
/*      */     }
/* 2254 */     catch (Exception e) {
/* 2255 */       e.printStackTrace();
/* 2256 */       Utilidades.writeError("cuantasEscaladasA ", e);
/*      */     } 
/* 2258 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> tiempoPromedioServicio(int areaProveedor, int areaCliente, String fechaInicio, String fechaFin) {
/* 2277 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2280 */       String s = "select solicitudes.codigo_servicio,servicios.descripcion as nombre_servicio,solicitudes.duracion,solicitudes.unidad_medida,count(0) as numero, avg(dias_diff(fecha_vigencia,fecha_real_terminacion)) as promedio  from solicitudes,servicios  where servicios.codigo=solicitudes.codigo_servicio and solicitudes.fecha_real_terminacion IS NOT NULL and solicitudes.area_proveedor=" + areaProveedor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2289 */       if (areaCliente > 0) {
/* 2290 */         s = s + " and solicitudes.area_cliente=" + areaCliente;
/*      */       }
/* 2292 */       s = s + " and solicitudes.fecha_real_terminacion>=" + Utilidades.formatoFecha2(fechaInicio) + " and solicitudes.fecha_real_terminacion<=" + Utilidades.formatoFecha2(fechaFin) + " group by solicitudes.codigo_servicio,servicios.descripcion,solicitudes.duracion,solicitudes.unidad_medida" + " order by servicios.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2297 */       boolean rta = this.dat.parseSql(s);
/* 2298 */       if (!rta) {
/* 2299 */         return resultados;
/*      */       }
/* 2301 */       this.rs = this.dat.getResultSet();
/* 2302 */       while (this.rs.next()) {
/* 2303 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2304 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2305 */         reg.setDuracion(this.rs.getInt("duracion"));
/* 2306 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 2307 */         reg.setNumero(this.rs.getInt("numero"));
/* 2308 */         reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*      */         
/* 2310 */         if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
/* 2311 */           reg.setTiempoPromedio(Utilidades.analizarEdad(this.rs.getDouble("promedio")));
/*      */         } else {
/*      */           
/* 2314 */           reg.setTiempoPromedio(this.rs.getString("promedio"));
/*      */         } 
/* 2316 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2319 */     } catch (Exception e) {
/* 2320 */       e.printStackTrace();
/* 2321 */       Utilidades.writeError("tiempoPromedioServicio ", e);
/*      */     } 
/* 2323 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> detallePromedioServicio(int areaProveedor, int areaCliente, String fechaInicio, String fechaFin, int servicio) {
/* 2343 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2346 */       String s = "select s.numero,ser.descripcion as nombre_Servicio,s.duracion,s.unidad_medida,u.descripcion as nombre_area_cliente,s.fecha_vigencia,s.fecha_real_terminacion,s.fecha_estimada_terminacion,dias_diff(s.fecha_vigencia,s.fecha_real_terminacion) as promedio from solicitudes s,servicios ser, unidades_dependencia u  where s.fecha_real_terminacion IS NOT NULL    and s.codigo_servicio=ser.codigo    and s.area_cliente=u.codigo    and s.area_proveedor=" + areaProveedor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2360 */       if (areaCliente > 0) {
/* 2361 */         s = s + " and s.area_cliente=" + areaCliente;
/*      */       }
/* 2363 */       s = s + " and s.codigo_servicio=" + servicio + " and s.fecha_real_terminacion>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_real_terminacion<=" + Utilidades.formatoFecha2(fechaFin) + " order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2368 */       boolean rta = this.dat.parseSql(s);
/* 2369 */       if (!rta) {
/* 2370 */         return resultados;
/*      */       }
/* 2372 */       this.rs = this.dat.getResultSet();
/* 2373 */       while (this.rs.next()) {
/* 2374 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*      */         
/* 2376 */         reg.setNumero(this.rs.getInt("numero"));
/* 2377 */         reg.setNombreServicio(this.rs.getString("nombre_Servicio"));
/* 2378 */         reg.setNombreAreaCliente(this.rs.getString("nombre_Area_Cliente"));
/* 2379 */         reg.setDuracion(this.rs.getInt("duracion"));
/* 2380 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 2381 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2382 */         reg.setFechaEstimadaTerminacion(this.rs.getString("fecha_estimada_terminacion"));
/* 2383 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 2384 */         if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
/* 2385 */           reg.setTiempoPromedio(Utilidades.analizarEdad(this.rs.getDouble("promedio")));
/*      */         } else {
/*      */           
/* 2388 */           reg.setTiempoPromedio(this.rs.getString("promedio"));
/*      */         } 
/* 2390 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2393 */     } catch (Exception e) {
/* 2394 */       e.printStackTrace();
/* 2395 */       Utilidades.writeError("detallePromedioServicio ", e);
/*      */     } 
/* 2397 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesDeCiclo(String ciclo, int servicio, int estado, String auditorIntegral, int codigoMentor, boolean bMostrarTodasSolicitudes) {
/* 2419 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */     
/*      */     try {
/* 2423 */       String s = "select  s.Numero,  s.Fecha_Generada,  s.Area_Cliente,  s.Empleado_Cliente,  s.Codigo_Servicio,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Codigo_Estado,  s.Abierta,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  s.Solicitud_Padre,  s.Codigo_Oportunidad,  s.Fecha_Oportunidad,  s.Codigo_Confiabilidad,  s.Fecha_Confiabilidad,  s.Observaciones,  s.Encuesta,  s.Duracion,  s.Unidad_Medida,  s.Ciclo,  s.Proveedor_Anterior,  s.Notificar,  s.Usuario_Insercion,  s.Usuario_Modificacion,  s.Fecha_Modificacion,  s.Empleado_Auditor,  s.Tipo_Calificacion_Auditor,  s.Estado_Anterior,  s.Macro_Servicio,  s.Numero_Devoluciones,  s.Secuencia_Externa,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado,  e.Tipo_Estado,  Ser.Tipo_Servicio,";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2467 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 2468 */         s = s + "  Dias_Diff(Date_Trunc('day',s.Fecha_Vigencia),Date_Trunc('day',s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } else {
/*      */         
/* 2471 */         s = s + "  Dias_Diff(Trunc(s.Fecha_Vigencia),Trunc(s.Fecha_Estimada_Terminacion)) as Tiempo_Servicio,";
/*      */       } 
/* 2473 */       s = s + "  case     when Coalesce(Ser.Autonumerar_Solicitud,'N') = 'S' then      Coalesce(s.Secuencia_Externa,               s.Numero)     when Coalesce(s.Macro_Servicio,'N') = 'S' then      Coalesce(s.Solicitud_Padre,               s.Numero)     else      s.Numero  end as Numero_Mostrar, s.NUMERO_FLUJO from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente           = Areac.Codigo        and s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Cliente   = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and s.ciclo              = '" + ciclo + "'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2499 */       if (servicio > 0) {
/* 2500 */         s = s + " and s.codigo_servicio=" + servicio;
/*      */       }
/* 2502 */       if (estado > 0) {
/* 2503 */         s = s + " and s.codigo_estado=" + estado;
/*      */       }
/*      */       
/* 2506 */       if (!bMostrarTodasSolicitudes)
/*      */       {
/* 2508 */         if (auditorIntegral.equals("S")) {
/* 2509 */           s = s + " and s.empleado_proveedor in(" + " select" + " p.codigo_empleado" + " from sis_usuarios p" + " where p.codigo_mentor=" + codigoMentor + ")";
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2515 */       s = s + " order by s.numero";
/*      */       
/* 2517 */       boolean rta = this.dat.parseSql(s);
/* 2518 */       if (!rta) {
/* 2519 */         return resultados;
/*      */       }
/* 2521 */       this.rs = this.dat.getResultSet();
/* 2522 */       while (this.rs.next()) {
/* 2523 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 2526 */     catch (Exception e) {
/* 2527 */       e.printStackTrace();
/* 2528 */       Utilidades.writeError("solicitudesDeCiclo ", e);
/*      */     } 
/* 2530 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesEscaladas(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 2546 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     try {
/* 2548 */       String s = "select s.numero,  Ser.Descripcion as Nombre_servicio,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  e.Descripcion as Nombre_estado,  s.fecha_vigencia from   Solicitudes          s,        Unidades_Dependencia Areap,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e,        historia_solicitud   h where  s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and h.numero_solicitud=s.numero        and h.estado_final =8        and h.estado_inicial =3        and h.fecha_cambio >=" + Utilidades.formatoFecha2(sFechaInicio) + "        and h.fecha_cambio <" + Utilidades.formatoFecha2(sFechaFinal) + "        and h.observaciones like 'Correo%'" + "        and s.codigo_servicio=" + codigo_servicio + "        and s.area_proveedor =" + area + "        and s.empleado_proveedor =" + codigoEmpleado + "        order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2576 */       boolean rtaDB = this.dat.parseSql(s);
/* 2577 */       if (!rtaDB) {
/* 2578 */         return resultados;
/*      */       }
/* 2580 */       this.rs = this.dat.getResultSet();
/* 2581 */       while (this.rs.next()) {
/* 2582 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2583 */         reg.setNumero(this.rs.getInt("numero"));
/* 2584 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 2585 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 2586 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2587 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 2588 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2589 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2592 */     } catch (Exception e) {
/* 2593 */       e.printStackTrace();
/* 2594 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 2596 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> sentencia2(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 2611 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2614 */       String s = "select s.numero,  Ser.Descripcion as Nombre_servicio,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  e.Descripcion as Nombre_estado,  s.fecha_vigencia from   Solicitudes          s,        Unidades_Dependencia Areap,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e,        detalles_solicitud   d where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and d.numero_solicitud=s.numero        and d.codigo_caracteristica=" + ParametrosDTO.getInt("car.nombre.servicio.requiere.mejora") + "        and d.valor=" + codigo_servicio + "        and s.fecha_generada >=" + Utilidades.formatoFecha2(sFechaInicio) + "        and s.fecha_generada <" + Utilidades.formatoFecha2(sFechaFinal) + "        and s.codigo_servicio=" + ParametrosDTO.getInt("servicio_solicitar.accion.mejora") + "        and s.area_proveedor =" + area + "        and s.empleado_proveedor =" + codigoEmpleado + "        order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2642 */       boolean rtaDB = this.dat.parseSql(s);
/* 2643 */       if (!rtaDB) {
/* 2644 */         return resultados;
/*      */       }
/* 2646 */       this.rs = this.dat.getResultSet();
/* 2647 */       while (this.rs.next()) {
/* 2648 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2649 */         reg.setNumero(this.rs.getInt("numero"));
/* 2650 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 2651 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 2652 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2653 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 2654 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2655 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2658 */     } catch (Exception e) {
/* 2659 */       e.printStackTrace();
/* 2660 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 2662 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> sentencia3(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado, int tipoAccion) {
/* 2679 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2682 */       String s = "select s.numero,  Ser.Descripcion as Nombre_servicio,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  e.Descripcion as Nombre_estado,  s.fecha_vigencia from   Solicitudes          s,        Unidades_Dependencia Areap,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.codigo_servicio=" + codigo_servicio;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2699 */       if (tipoAccion == 1) {
/* 2700 */         s = s + " and s.fecha_oportunidad >=" + Utilidades.formatoFecha2(sFechaInicio);
/* 2701 */         s = s + " and s.fecha_oportunidad <" + Utilidades.formatoFecha2(sFechaFinal);
/* 2702 */         s = s + " and s.codigo_oportunidad='R'";
/*      */       }
/* 2704 */       else if (tipoAccion == 2) {
/* 2705 */         s = s + " and s.fecha_confiabilidad >=" + Utilidades.formatoFecha2(sFechaInicio);
/* 2706 */         s = s + " and s.fecha_confiabilidad <" + Utilidades.formatoFecha2(sFechaFinal);
/* 2707 */         s = s + " and s.codigo_confiabilidad='R'";
/*      */       }
/* 2709 */       else if (tipoAccion == 0) {
/* 2710 */         s = s + " and ((s.codigo_oportunidad='R'";
/* 2711 */         s = s + " and s.fecha_oportunidad >=" + Utilidades.formatoFecha2(sFechaInicio);
/* 2712 */         s = s + " and s.fecha_oportunidad <" + Utilidades.formatoFecha2(sFechaFinal);
/* 2713 */         s = s + " ) or (";
/* 2714 */         s = s + " s.codigo_confiabilidad='R'";
/* 2715 */         s = s + " and s.fecha_confiabilidad >=" + Utilidades.formatoFecha2(sFechaInicio);
/* 2716 */         s = s + " and s.fecha_confiabilidad <" + Utilidades.formatoFecha2(sFechaFinal);
/* 2717 */         s = s + " ))";
/*      */       } 
/* 2719 */       s = s + " and s.area_proveedor =" + area;
/* 2720 */       s = s + " and s.empleado_proveedor =" + codigoEmpleado;
/* 2721 */       s = s + " order by s.numero";
/*      */       
/* 2723 */       boolean rtaDB = this.dat.parseSql(s);
/* 2724 */       if (!rtaDB) {
/* 2725 */         return resultados;
/*      */       }
/* 2727 */       this.rs = this.dat.getResultSet();
/* 2728 */       while (this.rs.next()) {
/* 2729 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2730 */         reg.setNumero(this.rs.getInt("numero"));
/* 2731 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 2732 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 2733 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2734 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 2735 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2736 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2739 */     } catch (Exception e) {
/* 2740 */       e.printStackTrace();
/* 2741 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 2743 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> sentencia4(int codigo_servicio, int area, String sFechaInicio, String sFechaFinal, int codigoEmpleado) {
/* 2759 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2762 */       String s = "select distinct s.numero,  Ser.Descripcion as Nombre_servicio,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.descripcion as nombre_servicio,  e.Descripcion as Nombre_estado,  s.fecha_vigencia from   Solicitudes          s,        Unidades_Dependencia Areap,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.fecha_confiabilidad >=" + Utilidades.formatoFecha2(sFechaInicio) + "        and s.fecha_confiabilidad <" + Utilidades.formatoFecha2(sFechaFinal) + "        and s.codigo_confiabilidad='R'" + "        and s.codigo_servicio=" + codigo_servicio + "        and s.area_proveedor =" + area + "        and s.empleado_proveedor =" + codigoEmpleado + "        order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2786 */       boolean rtaDB = this.dat.parseSql(s);
/* 2787 */       if (!rtaDB) {
/* 2788 */         return resultados;
/*      */       }
/* 2790 */       this.rs = this.dat.getResultSet();
/* 2791 */       while (this.rs.next()) {
/* 2792 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2793 */         reg.setNumero(this.rs.getInt("numero"));
/* 2794 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 2795 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 2796 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2797 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 2798 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2799 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2802 */     } catch (Exception e) {
/* 2803 */       e.printStackTrace();
/* 2804 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 2806 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> repCar01F2(int servicio, int rol, String fechaInicio, String fechaFinal, int area, boolean todosLosServicios) {
/* 2824 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2827 */       String s = "select s.Numero, s.fecha_vigencia, s.fecha_real_terminacion, Areap.Descripcion as Nombre_area_proveedora, Areac.Descripcion as Nombre_area_cliente, Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente, Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor, s.codigo_oportunidad, s.codigo_confiabilidad, Ser.descripcion as nombre_servicio, e.Descripcion as Nombre_estado, s.observaciones, case when s.fecha_real_terminacion is not null then dias_diff(s.fecha_vigencia,s.fecha_real_terminacion) else 0 end as tiempo_promedio, string_agg(Ate.Observacion,',') atencion from   Solicitudes          s        left join ATENCION_SOLICITUD  ATe on(s.NUMERO=ate.NUMERO_SOLICITUD),        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.codigo_servicio=" + servicio + "        and e.tipo_estado in ('EF','CAL','PRV','ESC')";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2858 */       if (!todosLosServicios) {
/* 2859 */         s = s + "     and " + ((rol == 0) ? " s.area_proveedor=" : "s.area_cliente=") + area;
/*      */       }
/* 2861 */       if (fechaInicio != null) {
/* 2862 */         s = s + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 2865 */       if (fechaFinal != null) {
/* 2866 */         s = s + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(fechaFinal);
/*      */       }
/*      */       
/* 2869 */       s = s + " GROUP BY s.Numero,        s.Fecha_Vigencia,        s.Fecha_Real_Terminacion,        Areap.Descripcion ,        Areac.Descripcion ,        Personac.Apellidos || ' ' || Personac.Nombres ,        Personap.Apellidos || ' ' || Personap.Nombres ,        s.Codigo_Oportunidad,        s.Codigo_Confiabilidad,        Ser.Descripcion ,        e.Descripcion ,        s.Observaciones,        case           when s.Fecha_Real_Terminacion is not null then            Dias_Diff(s.Fecha_Vigencia,                      s.Fecha_Real_Terminacion)           else            0        end order by Areap.Descripcion,s.fecha_real_terminacion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2890 */       boolean rtaDB = this.dat.parseSql(s);
/* 2891 */       if (!rtaDB) {
/* 2892 */         return resultados;
/*      */       }
/* 2894 */       this.rs = this.dat.getResultSet();
/* 2895 */       while (this.rs.next()) {
/* 2896 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 2897 */         reg.setNumero(this.rs.getInt("numero"));
/* 2898 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 2899 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 2900 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 2901 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 2902 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 2903 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 2904 */         reg.setCodigoOportunidad(this.rs.getString("codigo_oportunidad"));
/* 2905 */         reg.setCodigoConfiabilidad(this.rs.getString("codigo_confiabilidad"));
/* 2906 */         reg.setTiempoPromedio(this.rs.getString("tiempo_promedio"));
/* 2907 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 2908 */         reg.setNombreCliente(this.rs.getString("nombre_cliente"));
/* 2909 */         reg.setObservaciones(this.rs.getString("observaciones"));
/* 2910 */         reg.setAtencion(this.rs.getString("atencion"));
/* 2911 */         resultados.add(reg);
/*      */       }
/*      */     
/* 2914 */     } catch (Exception e) {
/* 2915 */       e.printStackTrace();
/* 2916 */       Utilidades.writeError("VSolicitudesFactory:repCar01F2 ", e);
/*      */     } 
/* 2918 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> reporte01F4(int servicio, int estado, int rol, String fechaInicio, String fechaFin, int area, int area2, int persona) {
/* 2939 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 2942 */       String s = "select s.Numero, s.fecha_vigencia, s.nivel_escalamiento, s.fecha_real_terminacion, s.fecha_estimada_terminacion, Areap.Descripcion as Nombre_area_proveedora, Areac.Descripcion as Nombre_area_cliente, Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente, Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor, Ser.Descripcion as Nombre_servicio, s.codigo_oportunidad, s.codigo_confiabilidad from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and " + ((rol == 0) ? " s.area_proveedor=" : "s.area_cliente=") + area;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2970 */       if (estado != 0) {
/* 2971 */         s = s + " and codigo_estado=" + estado;
/*      */       }
/*      */       
/* 2974 */       if (fechaInicio != null && fechaInicio.length() > 0) {
/* 2975 */         s = s + " and fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 2978 */       if (fechaFin != null && fechaFin.length() > 0) {
/* 2979 */         s = s + " and fecha_vigencia<" + Utilidades.formatoFecha2(Utilidades.siguienteDia(fechaFin));
/*      */       }
/* 2981 */       if (servicio != 0) {
/* 2982 */         s = s + " and codigo_servicio=" + servicio;
/*      */       }
/* 2984 */       if (area2 != 0) {
/* 2985 */         s = s + " and " + ((rol == 1) ? " area_proveedor=" : "area_cliente=") + area2;
/*      */       }
/*      */       
/* 2988 */       if (persona != 0) {
/* 2989 */         s = s + " and " + ((rol == 0) ? " empleado_cliente=" : "empleado_proveedor=") + persona;
/*      */       }
/*      */       
/* 2992 */       s = s + " order by numero";
/*      */       
/* 2994 */       boolean rtaDB = this.dat.parseSql(s);
/* 2995 */       if (!rtaDB) {
/* 2996 */         return resultados;
/*      */       }
/* 2998 */       this.rs = this.dat.getResultSet();
/* 2999 */       while (this.rs.next()) {
/* 3000 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/*      */         
/* 3002 */         reg.setNumero(this.rs.getInt("numero"));
/* 3003 */         reg.setNivelEscalamiento(this.rs.getInt("nivel_escalamiento"));
/* 3004 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3005 */         reg.setFechaEstimadaTerminacion(this.rs.getString("fecha_estimada_terminacion"));
/* 3006 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 3007 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3008 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3009 */         reg.setNombreCliente(this.rs.getString("nombre_cliente"));
/* 3010 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3011 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 3012 */         reg.setCodigoOportunidad(this.rs.getString("codigo_oportunidad"));
/* 3013 */         reg.setCodigoConfiabilidad(this.rs.getString("codigo_confiabilidad"));
/* 3014 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3017 */     } catch (Exception e) {
/* 3018 */       e.printStackTrace();
/* 3019 */       Utilidades.writeError("VSolicitudesFactory:repCar01F2 ", e);
/*      */     } 
/* 3021 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> reporte01F4Grupo(String fechaInicio, String fechaFin, int rol, int area, int estado, int servicio, int area2, int persona) {
/* 3040 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 3043 */       String s = "select s.area_proveedor, s.area_cliente, s.codigo_servicio, Areap.Descripcion as Nombre_area_proveedora, Areac.Descripcion as Nombre_area_cliente, Ser.Descripcion as Nombre_servicio, count(0) as cuantos from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        Servicios            Ser where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Codigo_Servicio = Ser.Codigo        and " + ((rol == 0) ? " area_proveedor=" : "area_cliente=") + area;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3060 */       if (estado != 0) {
/* 3061 */         s = s + " and s.codigo_estado=" + estado;
/*      */       }
/*      */       
/* 3064 */       if (fechaInicio != null && fechaInicio.length() > 0) {
/* 3065 */         s = s + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 3068 */       if (fechaFin != null && fechaFin.length() > 0) {
/* 3069 */         s = s + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(Utilidades.siguienteDia(fechaFin));
/*      */       }
/* 3071 */       if (servicio != 0) {
/* 3072 */         s = s + " and s.codigo_servicio=" + servicio;
/*      */       }
/* 3074 */       if (area2 != 0) {
/* 3075 */         s = s + " and " + ((rol == 1) ? " area_proveedor=" : "area_cliente=") + area2;
/*      */       }
/*      */       
/* 3078 */       if (persona != 0) {
/* 3079 */         s = s + " and " + ((rol == 0) ? " s.empleado_cliente=" : "s.empleado_proveedor=") + persona;
/*      */       }
/* 3081 */       s = s + " group by " + ((rol == 0) ? "s.area_proveedor" : "s.area_cliente");
/* 3082 */       s = s + "," + ((rol == 0) ? " Areap.Descripcion" : "Areac.Descripcion");
/* 3083 */       s = s + "," + ((rol == 1) ? " s.area_proveedor" : "s.area_cliente");
/* 3084 */       s = s + "," + ((rol == 1) ? " Areap.Descripcion" : "Areac.Descripcion");
/* 3085 */       s = s + ",s.codigo_servicio,Ser.Descripcion";
/*      */       
/* 3087 */       s = s + " order by " + ((rol == 0) ? "s.area_proveedor" : "s.area_cliente");
/* 3088 */       s = s + "," + ((rol == 0) ? " Areap.Descripcion" : "Areac.Descripcion");
/* 3089 */       s = s + "," + ((rol == 1) ? " s.area_proveedor" : "s.area_cliente");
/* 3090 */       s = s + "," + ((rol == 1) ? " Areap.Descripcion" : "Areac.Descripcion");
/* 3091 */       s = s + ",s.codigo_servicio,Ser.Descripcion";
/*      */       
/* 3093 */       boolean rtaDB = this.dat.parseSql(s);
/* 3094 */       if (!rtaDB) {
/* 3095 */         return resultados;
/*      */       }
/* 3097 */       this.rs = this.dat.getResultSet();
/* 3098 */       while (this.rs.next()) {
/* 3099 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3100 */         reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/* 3101 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3102 */         reg.setAreaCliente(this.rs.getInt("area_cliente"));
/* 3103 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3104 */         reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/* 3105 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 3106 */         reg.setNumero(this.rs.getInt("cuantos"));
/*      */         
/* 3108 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3111 */     } catch (Exception e) {
/* 3112 */       e.printStackTrace();
/* 3113 */       Utilidades.writeError("VSolicitudesFactory:repCar01F2 ", e);
/*      */     } 
/* 3115 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesDeArea(int tipo, int area, int servicio, String fechaInicio, String fechaFin, int rol) {
/* 3133 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 3136 */       String s = "select  s.Numero,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3159 */       if (tipo == 1) {
/* 3160 */         s = s + " and s.area_proveedor=" + area;
/*      */       }
/* 3162 */       else if (tipo == 2) {
/* 3163 */         s = s + " and s.area_proveedor=" + area + " and nivel_escalamiento>0";
/*      */ 
/*      */       
/*      */       }
/* 3167 */       else if (tipo == 4) {
/* 3168 */         s = s + " and s.codigo_servicio=" + servicio + " and " + ((rol == 0) ? " s.area_proveedor=" : "s.area_cliente=") + area;
/*      */       } 
/*      */ 
/*      */       
/* 3172 */       if (fechaInicio != null) {
/* 3173 */         s = s + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 3176 */       if (fechaFin != null) {
/* 3177 */         s = s + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*      */       
/* 3180 */       s = s + " order by s.fecha_estimada_terminacion";
/*      */       
/* 3182 */       boolean rtaDB = this.dat.parseSql(s);
/* 3183 */       if (!rtaDB) {
/* 3184 */         return resultados;
/*      */       }
/* 3186 */       this.rs = this.dat.getResultSet();
/* 3187 */       while (this.rs.next()) {
/* 3188 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3189 */         reg.setNumero(this.rs.getInt("numero"));
/* 3190 */         reg.setNivelEscalamiento(this.rs.getInt("nivel_escalamiento"));
/* 3191 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3192 */         reg.setFechaEstimadaTerminacion(this.rs.getString("fecha_estimada_terminacion"));
/* 3193 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3194 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3195 */         reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/* 3196 */         reg.setAreaCliente(this.rs.getInt("area_cliente"));
/* 3197 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 3198 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3201 */     } catch (Exception e) {
/* 3202 */       e.printStackTrace();
/* 3203 */       Utilidades.writeError("VSolicitudesFactory:solicitudesDeArea ", e);
/*      */     } 
/* 3205 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesDePersona(int tipo, int persona, int plan, String fechaInicio, String fechaFin) {
/* 3223 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 3226 */       String s = "select  s.Numero,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado from   Solicitudes          s,        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3249 */       if (tipo == 1) {
/* 3250 */         s = s + " and s.empleado_proveedor=" + persona;
/*      */       }
/* 3252 */       else if (tipo == 2) {
/* 3253 */         s = s + " s.empleado_proveedor=" + persona + " and nivel_escalamiento>0";
/*      */       } 
/*      */ 
/*      */       
/* 3257 */       if (fechaInicio != null) {
/* 3258 */         s = s + " and s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */       }
/*      */       
/* 3261 */       if (fechaFin != null) {
/* 3262 */         s = s + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin);
/*      */       }
/*      */       
/* 3265 */       s = s + " order by s.fecha_estimada_terminacion";
/*      */       
/* 3267 */       boolean rtaDB = this.dat.parseSql(s);
/* 3268 */       if (!rtaDB) {
/* 3269 */         return resultados;
/*      */       }
/* 3271 */       this.rs = this.dat.getResultSet();
/* 3272 */       while (this.rs.next()) {
/* 3273 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3274 */         reg.setNumero(this.rs.getInt("numero"));
/* 3275 */         reg.setNivelEscalamiento(this.rs.getInt("nivel_escalamiento"));
/* 3276 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3277 */         reg.setFechaEstimadaTerminacion(this.rs.getString("fecha_estimada_terminacion"));
/* 3278 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3279 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3280 */         reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/* 3281 */         reg.setAreaCliente(this.rs.getInt("area_cliente"));
/* 3282 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 3283 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3286 */     } catch (Exception e) {
/* 3287 */       e.printStackTrace();
/* 3288 */       Utilidades.writeError("VSolicitudesFactory:solicitudesDePersona ", e);
/*      */     } 
/* 3290 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarSolicitudesCalificadas(int quien, int prov, int tipo, String codigo, int prTipoSolicitud, int anno, int mes1, int mes2) {
/* 3313 */     int mesActual = Utilidades.getMesActual();
/* 3314 */     int annoActual = Utilidades.getAnnoActual();
/*      */     
/* 3316 */     String fechaInicio = anno + "-" + Utilidades.formato(mes1, 2) + "-01";
/* 3317 */     String fechaFinal = anno + "-" + Utilidades.formato(mes2, 2) + "-01";
/* 3318 */     FechaDTO miFecha = new FechaDTO(fechaFinal);
/* 3319 */     miFecha.fechaMasDias(32L);
/* 3320 */     fechaFinal = miFecha.getPrimerDiaMes();
/*      */     
/* 3322 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 3325 */       String s = "select  s.Numero,  s.Nivel_Escalamiento,  s.Fecha_Vigencia,  Areap.Descripcion as Nombre_area_proveedora,  Areac.Descripcion as Nombre_area_cliente,  Personac.Apellidos || ' ' || Personac.Nombres as Nombre_cliente,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado from   ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3337 */       if (annoActual == anno && mesActual == mes1) {
/* 3338 */         s = s + "Solicitudes   s,";
/*      */       } else {
/*      */         
/* 3341 */         s = s + "Solicitudes_periodo   s,";
/*      */       } 
/*      */       
/* 3344 */       s = s + "        Unidades_Dependencia Areap,        Unidades_Dependencia Areac,        sis_usuarios         Personac,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Area_Proveedor = Areap.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3357 */       if (annoActual != anno || mesActual != mes1)
/*      */       {
/*      */ 
/*      */         
/* 3361 */         s = s + "        and s.periodo='" + Utilidades.formato(anno, 4) + Utilidades.formato(mes1, 2) + "'";
/*      */       }
/*      */       
/* 3364 */       if (quien == 1) {
/* 3365 */         s = s + " and s.area_proveedor=" + prov;
/*      */       } else {
/*      */         
/* 3368 */         s = s + " and s.empleado_proveedor=" + prov;
/*      */       } 
/*      */ 
/*      */       
/* 3372 */       switch (tipo) {
/*      */         case 1:
/* 3374 */           s = s + " and s.codigo_oportunidad='" + codigo + "'" + " and s.fecha_oportunidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_oportunidad<" + Utilidades.formatoFecha2(fechaFinal) + " order by s.fecha_oportunidad,s.numero";
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 3381 */           s = s + " and s.codigo_confiabilidad='" + codigo + "'" + " and s.fecha_confiabilidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_confiabilidad<" + Utilidades.formatoFecha2(fechaFinal) + " order by s.fecha_confiabilidad,s.numero";
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3388 */       boolean rtaDB = this.dat.parseSql(s);
/* 3389 */       if (!rtaDB) {
/* 3390 */         return resultados;
/*      */       }
/* 3392 */       this.rs = this.dat.getResultSet();
/* 3393 */       while (this.rs.next()) {
/* 3394 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3395 */         reg.setNumero(this.rs.getInt("numero"));
/* 3396 */         reg.setNumeroMostrar(this.rs.getInt("numero"));
/* 3397 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3398 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3399 */         reg.setNombreProveedor(this.rs.getString("Nombre_proveedor"));
/* 3400 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3401 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 3402 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 3403 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3406 */     } catch (Exception e) {
/* 3407 */       e.printStackTrace();
/* 3408 */       Utilidades.writeError("VSolicitudesFactory:solicitudesDePersona ", e);
/*      */     } 
/* 3410 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesCerradasPeriodo(int anno, int mes, int area, int servicio) {
/* 3425 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     try {
/* 3427 */       String s = "select s.numero,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  s.fecha_vigencia,  s.fecha_real_terminacion,  extract ('epoch' from (s.fecha_real_terminacion-s.fecha_vigencia))/(24*3600)  as tiempo from   Solicitudes_periodo  s,        Unidades_Dependencia Areap,        sis_usuarios         Personap where  s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.codigo_servicio=" + servicio + "        and s.area_proveedor =" + area + "        and to_char(s.fecha_real_terminacion,'yyyymm')='" + Utilidades.formato(anno, 4) + Utilidades.formato(mes, 2) + "'" + "        order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3444 */       boolean rtaDB = this.dat.parseSql(s);
/* 3445 */       if (!rtaDB) {
/* 3446 */         return resultados;
/*      */       }
/* 3448 */       this.rs = this.dat.getResultSet();
/* 3449 */       while (this.rs.next()) {
/* 3450 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3451 */         reg.setNumero(this.rs.getInt("numero"));
/* 3452 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3453 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3454 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3455 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 3456 */         reg.setTiempoTotal(this.rs.getDouble("tiempo"));
/* 3457 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3460 */     } catch (Exception e) {
/* 3461 */       e.printStackTrace();
/* 3462 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 3464 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> solicitudesCerradasBase(int anno, int mes, int mes2, int area, int servicio) {
/* 3480 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     try {
/* 3482 */       String s = "select s.numero,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  s.fecha_vigencia,  s.fecha_real_terminacion,  extract ('epoch' from (s.fecha_real_terminacion-s.fecha_vigencia))/(24*3600)  as tiempo from   Solicitudes_periodo  s,        Unidades_Dependencia Areap,        sis_usuarios         Personap where  s.Area_Proveedor = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.codigo_servicio=" + servicio + "        and s.area_proveedor =" + area + "        and to_char(s.fecha_real_terminacion,'yyyymm') in(";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3497 */       for (int i = mes; i <= mes2; i++) {
/* 3498 */         s = s + "'" + Utilidades.formato(anno, 4) + Utilidades.formato(i, 2) + "',";
/*      */       }
/* 3500 */       s = s + "'195001')";
/* 3501 */       s = s + "        order by s.numero";
/*      */ 
/*      */       
/* 3504 */       boolean rtaDB = this.dat.parseSql(s);
/* 3505 */       if (!rtaDB) {
/* 3506 */         return resultados;
/*      */       }
/* 3508 */       this.rs = this.dat.getResultSet();
/* 3509 */       while (this.rs.next()) {
/* 3510 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3511 */         reg.setNumero(this.rs.getInt("numero"));
/* 3512 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3513 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3514 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3515 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 3516 */         reg.setTiempoTotal(this.rs.getDouble("tiempo"));
/* 3517 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3520 */     } catch (Exception e) {
/* 3521 */       e.printStackTrace();
/* 3522 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 3524 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> detalleQuejas(int anno, int trimestre, int canal, String subcuenta, String linea) {
/* 3535 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     
/*      */     try {
/* 3538 */       String s = "select  s.Numero,  s.Empleado_Cliente,  s.Area_Proveedor,  s.Empleado_Proveedor,  s.Fecha_Vigencia,  s.Fecha_Estimada_Terminacion,  s.Fecha_Real_Terminacion,  s.Fecha_Base_Escalamientos,  Areap.Descripcion as Nombre_area_proveedora,  Personap.Apellidos || ' ' || Personap.Nombres as Nombre_proveedor,  Ser.Descripcion as Nombre_servicio,  e.Descripcion as Nombre_estado from   Solicitudes          s,        Unidades_Dependencia Areap,        sis_usuarios         Personap,        Servicios            Ser,        Estados              e,        otr_reporte_quejas_deta dq where  s.Area_Proveedor     = Areap.Codigo        and s.Empleado_Proveedor = Personap.Codigo_Empleado        and s.Codigo_Servicio    = Ser.Codigo        and s.Codigo_Estado      = e.Codigo        and dq.solicitud= s.numero        and dq.anno=" + anno + "        and dq.trimestre=" + trimestre + "        and dq.canal=" + canal + "        and dq.subcuenta='" + subcuenta + "'" + "        and dq.linea='" + linea + "'" + " order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3569 */       boolean rtaDB = this.dat.parseSql(s);
/* 3570 */       if (!rtaDB) {
/* 3571 */         return resultados;
/*      */       }
/* 3573 */       this.rs = this.dat.getResultSet();
/* 3574 */       while (this.rs.next()) {
/* 3575 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3576 */         reg.setNumero(this.rs.getInt("numero"));
/* 3577 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3578 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3579 */         reg.setNombreEstado(this.rs.getString("Nombre_estado"));
/* 3580 */         reg.setFechaVigencia(this.rs.getString("fecha_vigencia"));
/* 3581 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 3582 */         reg.setFechaRealTerminacion(this.rs.getString("fecha_real_terminacion"));
/* 3583 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3586 */     } catch (Exception e) {
/* 3587 */       e.printStackTrace();
/* 3588 */       Utilidades.writeError("cargarTodosEnCursoPedidasPor ", e);
/*      */     } 
/* 3590 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSolicitudesDTO getPadre(int numero) {
/*      */     try {
/* 3604 */       String s = "select  s.Numero,  s.Solicitud_Padre from   Solicitudes          s where  s.numero=" + numero;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3610 */       boolean rta = this.dat.parseSql(s);
/* 3611 */       if (!rta) return null; 
/* 3612 */       this.rs = this.dat.getResultSet();
/* 3613 */       if (this.rs.next())
/*      */       {
/* 3615 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3616 */         reg.setNumero(this.rs.getInt("numero"));
/*      */         
/* 3618 */         String temp = this.rs.getString("solicitud_padre");
/*      */         
/* 3620 */         if (!this.rs.wasNull()) {
/* 3621 */           reg.setSolicitudPadre(Integer.parseInt(temp));
/*      */         } else {
/*      */           
/* 3624 */           reg.setSolicitudPadre(-1);
/*      */         } 
/*      */         
/* 3627 */         return reg;
/*      */       }
/*      */     
/* 3630 */     } catch (SQLException e) {
/* 3631 */       e.printStackTrace();
/* 3632 */       Utilidades.writeError("getPadre ", e);
/*      */     } 
/*      */ 
/*      */     
/* 3636 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> pendientesAplazamiento(int empleadoCliente) {
/* 3651 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */     try {
/* 3653 */       String s = " select s.Numero,        Se.Descripcion as Nombre_Servicio,        u.Descripcion as nombre_area_proveedora,        p.Apellidos || ' ' || p.Nombres nombre_proveedor,        s.Fecha_Estimada_Terminacion,        a.Fecha,        a.Justificacion from   Aplazamientos_Solicitud a,        Solicitudes             s,        Servicios               Se,        sis_usuarios            p,        Unidades_Dependencia    u where  a.Numero_Solicitud = s.Numero        and s.Codigo_Servicio = Se.Codigo        and s.Empleado_Proveedor = p.Codigo_Empleado        and s.Area_Proveedor = u.Codigo        and a.Estado = 0        and s.Abierta = 'S'      AND s.empleado_cliente=" + empleadoCliente + " order by s.numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3676 */       boolean rtaDB = this.dat.parseSql(s);
/* 3677 */       if (!rtaDB) {
/* 3678 */         return resultados;
/*      */       }
/* 3680 */       this.rs = this.dat.getResultSet();
/* 3681 */       while (this.rs.next()) {
/* 3682 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3683 */         reg.setNumero(this.rs.getInt("numero"));
/* 3684 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3685 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3686 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/* 3687 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/* 3688 */         reg.setFechaGenerada(this.rs.getString("fecha"));
/* 3689 */         reg.setObservaciones(this.rs.getString("justificacion"));
/* 3690 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3693 */     } catch (Exception e) {
/* 3694 */       e.printStackTrace();
/* 3695 */       Utilidades.writeError("VSolicitudesFactory:solicitudesEscaladas ", e);
/*      */     } 
/* 3697 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<VSolicitudesDTO> cargarHijas(int nroSol, int numeroFlujo) {
/* 3706 */     Collection<VSolicitudesDTO> resultados = new ArrayList<VSolicitudesDTO>();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3711 */       String s = "select s.Numero,        s.Fecha_Estimada_Terminacion,        s.Area_Cliente,        s.Area_Proveedor,        s.Empleado_Cliente,        s.Empleado_Proveedor,        s.codigo_estado,        Areap.Descripcion as Nombre_Area_Proveedora,        Areac.Descripcion as Nombre_Area_Cliente,        Personac.Apellidos || ' ' || Personac.Nombres as Nombre_Cliente,        Personap.Apellidos || ' ' || Personap.Nombres as Nombre_Proveedor,        Ser.Descripcion as Nombre_Servicio,        e.Descripcion as Nombre_Estado,        e.Tipo_Estado,        'S' hija from   Solicitudes          s        left join Unidades_Dependencia Areap on(s.Area_Proveedor = Areap.Codigo)        left join sis_usuarios  Personap on(s.Empleado_Proveedor = Personap.Codigo_Empleado),        Unidades_Dependencia Areac,        sis_usuarios         Personac,        Servicios            Ser,        Estados              e where  s.Area_Cliente = Areac.Codigo        and s.Empleado_Cliente = Personac.Codigo_Empleado        and s.Codigo_Servicio = Ser.Codigo        and s.Codigo_Estado = e.Codigo        and s.Solicitud_Padre =" + nroSol + " UNION\t\t  " + " select s.Numero," + "        s.Fecha_Estimada_Terminacion," + "        s.Area_Cliente," + "        s.Area_Proveedor," + "        s.Empleado_Cliente," + "        s.Empleado_Proveedor," + "        s.codigo_estado," + "        Areap.Descripcion as Nombre_Area_Proveedora," + "        Areac.Descripcion as Nombre_Area_Cliente," + "        Personac.Apellidos || ' ' || Personac.Nombres as Nombre_Cliente," + "        Personap.Apellidos || ' ' || Personap.Nombres as Nombre_Proveedor," + "        Ser.Descripcion as Nombre_Servicio," + "        e.Descripcion as Nombre_Estado," + "        e.Tipo_Estado," + "        'N' hija" + " from   Solicitudes          s" + "        left join Unidades_Dependencia Areap on(s.Area_Proveedor = Areap.Codigo)" + "        left join sis_usuarios         Personap on(s.Empleado_Proveedor = Personap.Codigo_Empleado)," + " \t     SOLICITUDES_MACRO    m," + "        Unidades_Dependencia Areac," + "        sis_usuarios         Personac," + "        Servicios            Ser," + "        Estados              e" + " where  s.Area_Cliente = Areac.Codigo" + "        and s.Empleado_Cliente = Personac.Codigo_Empleado" + "        and s.Codigo_Servicio = Ser.Codigo" + "        and s.Codigo_Estado = e.Codigo" + "        AND COALESCE(s.SOLICITUD_PADRE,0) <> " + nroSol + " \t     AND s.NUMERO_FLUJO=m.NUMERO_MACROSERVICIO" + "        and m.NUMERO_MACROSERVICIO=" + numeroFlujo + " order  by Fecha_Estimada_Terminacion," + "           Numero";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3772 */       boolean rta = this.dat.parseSql(s);
/* 3773 */       if (!rta) {
/* 3774 */         return resultados;
/*      */       }
/* 3776 */       this.rs = this.dat.getResultSet();
/* 3777 */       while (this.rs.next()) {
/* 3778 */         VSolicitudesDTO reg = new VSolicitudesDTO();
/* 3779 */         reg.setNumero(this.rs.getInt("numero"));
/* 3780 */         reg.setFechaEstimadaTerminacion(this.rs.getString("Fecha_Estimada_Terminacion"));
/* 3781 */         reg.setNombreAreaProveedora(this.rs.getString("nombre_area_proveedora"));
/* 3782 */         reg.setNombreProveedor(this.rs.getString("nombre_proveedor"));
/* 3783 */         reg.setNombreServicio(this.rs.getString("Nombre_Servicio"));
/* 3784 */         reg.setNombreAreaCliente(this.rs.getString("nombre_area_cliente"));
/* 3785 */         reg.setNombreCliente(this.rs.getString("nombre_cliente"));
/* 3786 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 3787 */         reg.setTipoEstado(this.rs.getString("tipo_estado"));
/* 3788 */         reg.setAreaCliente(this.rs.getInt("area_cliente"));
/* 3789 */         reg.setEmpleadoCliente(this.rs.getInt("empleado_cliente"));
/* 3790 */         reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/* 3791 */         reg.setEmpleadoProveedor(this.rs.getInt("empleado_proveedor"));
/* 3792 */         reg.setCodigoEstado(this.rs.getInt("codigo_estado"));
/* 3793 */         reg.setHija(this.rs.getString("hija"));
/* 3794 */         resultados.add(reg);
/*      */       }
/*      */     
/* 3797 */     } catch (Exception e) {
/* 3798 */       e.printStackTrace();
/* 3799 */       Utilidades.writeError("cargarHijas ", e);
/*      */     } 
/* 3801 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3818 */   public RespuestaBD procesarNovedad(int solicitud, int codigoEstado, String usuario) { return this.dat.procesarNovedad(solicitud, codigoEstado, usuario); }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\VSolicitudesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */