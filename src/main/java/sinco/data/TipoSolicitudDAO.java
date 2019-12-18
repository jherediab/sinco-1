/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import sinco.business.TipoSolicitudDTO;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.TipoSolicitudDAO;
/*    */ 
/*    */ public class TipoSolicitudDAO
/*    */ {
/*    */   ResultSet rs;
/*    */   
/*    */   public void close() {
/*    */     try {
/* 15 */       this.dat.close();
/*    */     }
/* 17 */     catch (Exception e) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 22 */   DBManager dat = new DBManager();
/*    */ 
/*    */   
/* 25 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cargarTodos() {
/*    */     try {
/* 31 */       boolean rta = this.dat.parseSql("select * from tipos_solicitud");
/* 32 */       if (!rta) return false; 
/* 33 */       this.rs = this.dat.getResultSet();
/* 34 */       return true;
/*    */     }
/* 36 */     catch (Exception e) {
/* 37 */       e.printStackTrace();
/*    */       
/* 39 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public TipoSolicitudDTO leerRegistro() {
/*    */     try {
/* 45 */       TipoSolicitudDTO ts = new TipoSolicitudDTO();
/* 46 */       ts.setCodigo(Integer.parseInt(this.rs.getString("codigo")));
/* 47 */       ts.setDescripcion(this.rs.getString("descripcion"));
/* 48 */       return ts;
/*    */     }
/* 50 */     catch (SQLException e) {
/* 51 */       e.printStackTrace();
/*    */       
/* 53 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public TipoSolicitudDTO next() {
/*    */     try {
/* 59 */       if (this.rs.next()) {
/* 60 */         return leerRegistro();
/*    */       }
/*    */     }
/* 63 */     catch (SQLException e) {
/* 64 */       e.printStackTrace();
/*    */     } 
/* 66 */     return null;
/*    */   }
/*    */   
/*    */   public TipoSolicitudDTO getTipoSolicitud(int cod) {
/*    */     try {
/* 71 */       boolean rta = this.dat.parseSql("select * from tipos_solicitud where codigo=" + cod);
/* 72 */       if (!rta) return null; 
/* 73 */       this.rs = this.dat.getResultSet();
/* 74 */       if (this.rs.next()) {
/* 75 */         return leerRegistro();
/*    */       }
/*    */     }
/* 78 */     catch (SQLException e) {
/* 79 */       e.printStackTrace();
/*    */     } 
/* 81 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\TipoSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */