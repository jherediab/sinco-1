/*     */ package sinco.business;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
          import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ManejadorArchivosDTO;
/*     */ 
/*     */ public class ManejadorArchivosDTO {
/*  14 */   private int SUCCESS = 1;
/*  15 */   private int FAILURE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int canRead(String path) {
/*  23 */     File myFile = new File(path);
/*  24 */     return myFile.canRead() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int canWrite(String path) {
/*  28 */     File myFile = new File(path);
/*  29 */     return myFile.canWrite() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int createNewFile(String path) throws IOException {
/*  33 */     File myFile = new File(path);
/*  34 */     return myFile.createNewFile() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int delete(String path) {
/*  38 */     File myFile = new File(path);
/*  39 */     return myFile.delete() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int exists(String path) {
/*  43 */     File myFile = new File(path);
/*  44 */     return myFile.exists() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int isDirectory(String path) {
/*  48 */     File myFile = new File(path);
/*  49 */     return myFile.isDirectory() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int isFile(String path) {
/*  53 */     File myFile = new File(path);
/*  54 */     return myFile.isFile() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int isHidden(String path) {
/*  58 */     File myFile = new File(path);
/*  59 */     return myFile.isHidden() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public Timestamp lastModified(String path) {
/*  63 */     File myFile = new File(path);
/*  64 */     return new Timestamp(myFile.lastModified());
/*     */   }
/*     */   
/*     */   public long length(String path) {
/*  68 */     File myFile = new File(path);
/*  69 */     return myFile.length();
/*     */   }
/*     */   
/*     */   public String list(String path) {
/*  73 */     String list = "";
/*  74 */     File myFile = new File(path);
/*  75 */     String[] arrayList = myFile.list();
/*     */     
/*  77 */     Arrays.sort(arrayList, String.CASE_INSENSITIVE_ORDER);
/*  78 */     System.out.println("arrayLen=" + arrayList.length);
/*     */     
/*  80 */     for (int i = 0; i < arrayList.length; i++) {
/*     */       
/*  82 */       if (list.length() + arrayList[i].length() + 1 > 32767) {
/*     */         break;
/*     */       }
/*  85 */       if (!list.equals("")) {
/*  86 */         list = list + "," + arrayList[i];
/*     */       } else {
/*  88 */         list = list + arrayList[i];
/*     */       } 
/*  90 */     }  return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection listar(String path) {
/* 101 */     File myFile = new File(path);
/* 102 */     String[] arreglo = myFile.list();
/* 103 */     Collection resultados = new ArrayList();
/*     */     
/* 105 */     if (arreglo != null) {
/* 106 */       for (int i = 0; i < arreglo.length; i++) {
/* 107 */         resultados.add(arreglo[i]);
/*     */       }
/*     */     }
/* 110 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int mkdir(String path) {
/* 120 */     File myFile = new File(path);
/* 121 */     return myFile.mkdir() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int mkdirs(String path) {
/* 125 */     File myFile = new File(path);
/* 126 */     return myFile.mkdirs() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int renameTo(String fromPath, String toPath) {
/* 130 */     File myFromFile = new File(fromPath);
/* 131 */     File myToFile = new File(toPath);
/* 132 */     return myFromFile.renameTo(myToFile) ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */   
/*     */   public int setReadOnly(String path) {
/* 136 */     File myFile = new File(path);
/* 137 */     return myFile.setReadOnly() ? this.SUCCESS : this.FAILURE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int copy(String fromPath, String toPath) {
/*     */     try {
/* 148 */       File myFromFile = new File(fromPath);
/* 149 */       File myToFile = new File(toPath);
/*     */       
/* 151 */       InputStream in = new FileInputStream(myFromFile);
/* 152 */       OutputStream out = new FileOutputStream(myToFile);
/*     */       
/* 154 */       byte[] buf = new byte[1024];
/*     */       int len;
/* 156 */       while ((len = in.read(buf)) > 0) {
/* 157 */         out.write(buf, 0, len);
/*     */       }
/* 159 */       in.close();
/* 160 */       out.close();
/* 161 */       return this.SUCCESS;
/*     */     }
/* 163 */     catch (Exception ex) {
/* 164 */       return this.FAILURE;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ManejadorArchivosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */