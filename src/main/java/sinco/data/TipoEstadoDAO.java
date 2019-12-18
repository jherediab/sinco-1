/*    */ package sinco.data;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import sinco.business.TipoEstadoDTO;
/*    */ import sinco.data.DBManager;
/*    */ import sinco.data.TipoEstadoDAO;
/*    */ 
/*    */ public class TipoEstadoDAO
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
/*    */   public boolean cargarTodos() {
/*    */     try {
/* 30 */       boolean rta = this.dat.parseSql("select * from tipos_estado");
/* 31 */       if (!rta) return false; 
/* 32 */       this.rs = this.dat.getResultSet();
/* 33 */       return true;
/*    */     }
/* 35 */     catch (Exception e) {
/* 36 */       e.printStackTrace();
/*    */       
/* 38 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public TipoEstadoDTO leerRegistro() {
/*    */     try {
/* 44 */       TipoEstadoDTO tipoestado = new TipoEstadoDTO();
/* 45 */       tipoestado.setCodigo(this.rs.getString("codigo"));
/* 46 */       tipoestado.setDescripcion(this.rs.getString("descripcion"));
/* 47 */       return tipoestado;
/*    */     }
/* 49 */     catch (SQLException e) {
/* 50 */       e.printStackTrace();
/*    */       
/* 52 */       return null;
/*    */     } 
/*    */   }
/*    */   public TipoEstadoDTO next() {
/*    */     try {
/* 57 */       if (this.rs.next()) {
/* 58 */         return leerRegistro();
/*    */       }
/*    */     }
/* 61 */     catch (SQLException e) {
/* 62 */       e.printStackTrace();
/*    */     } 
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public TipoEstadoDTO getTipoEstado(String codigo) {
/*    */     try {
/* 69 */       boolean rta = this.dat.parseSql("select * from tipos_estado where codigo='" + codigo + "'");
/* 70 */       if (!rta) return null; 
/* 71 */       this.rs = this.dat.getResultSet();
/* 72 */       if (this.rs.next()) {
/* 73 */         return leerRegistro();
/*    */       }
/*    */     }
/* 76 */     catch (SQLException e) {
/* 77 */       e.printStackTrace();
/*    */     } 
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\TipoEstadoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */