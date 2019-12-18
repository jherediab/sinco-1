/*     */ package sinco.business;
/*     */ 
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.StringTokenizer;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ 
/*     */ 
/*     */ public class FechaDTO
/*     */ {
/*     */   private long lJulianDay;
/*     */   private int anno;
/*     */   private int mes;
/*     */   private int dia;
/*     */   private int hora;
/*     */   private int minutos;
/*     */   private int segundos;
/*     */   
/*     */   public FechaDTO() {
/*  20 */     GregorianCalendar gc = new GregorianCalendar();
/*  21 */     this.lJulianDay = YmdToJd(gc.get(1), gc.get(2) + 1, gc.get(5));
/*  22 */     JdToYmd();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FechaDTO(int iDay, int iMonth, int iYear) {
/*  28 */     this.lJulianDay = YmdToJd(iYear, iMonth, iDay);
/*  29 */     JdToYmd();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fechaHora(String miFecha) {
/*  35 */     StringTokenizer st = new StringTokenizer(miFecha, " ");
/*     */     
/*  37 */     int iYear = 0;
/*  38 */     int iMonth = 0;
/*  39 */     int iDay = 0;
/*     */     
/*     */     try {
/*  42 */       String laFecha = st.nextToken();
/*  43 */       String laHora = st.nextToken();
/*     */       
/*  45 */       StringTokenizer stF = new StringTokenizer(laFecha, "-");
/*     */       
/*  47 */       st = new StringTokenizer(laHora, ".");
/*  48 */       laHora = st.nextToken();
/*     */       
/*  50 */       iYear = Integer.parseInt(stF.nextToken());
/*  51 */       iMonth = Integer.parseInt(stF.nextToken());
/*  52 */       iDay = Integer.parseInt(stF.nextToken());
/*     */       
/*  54 */       StringTokenizer stH = new StringTokenizer(laHora, ":");
/*     */       
/*  56 */       this.hora = Integer.parseInt(stH.nextToken());
/*  57 */       this.minutos = Integer.parseInt(stH.nextToken());
/*  58 */       this.segundos = Integer.parseInt(stH.nextToken());
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       System.out.println("error=" + e.getMessage());
/*     */     } 
/*     */     
/*  64 */     this.lJulianDay = YmdToJd(iYear, iMonth, iDay);
/*  65 */     JdToYmd();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FechaDTO(String miFecha) {
/*  76 */     if (miFecha.length() > 10) {
/*  77 */       fechaHora(miFecha);
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     StringTokenizer st = new StringTokenizer(miFecha, "-");
/*     */     try {
/*  83 */       int iYear = Integer.parseInt(st.nextToken());
/*  84 */       int iMonth = Integer.parseInt(st.nextToken());
/*  85 */       int iDay = Integer.parseInt(st.nextToken());
/*     */       
/*  87 */       this.lJulianDay = YmdToJd(iYear, iMonth, iDay);
/*  88 */       JdToYmd();
/*     */     }
/*  90 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long YmdToJd(int iYear, int iMonth, int iDay) {
/*  99 */     int year = iYear, month = iMonth, day = iDay;
/*     */ 
/*     */     
/* 102 */     if (year < 0)
/* 103 */       year++; 
/* 104 */     double year_corr = (year > 0) ? 0.0D : 0.75D;
/* 105 */     if (month <= 2) {
/*     */       
/* 107 */       year--;
/* 108 */       month += 12;
/*     */     } 
/* 110 */     int b = 0;
/* 111 */     if (year * 10000.0D + month * 100.0D + day >= 1.5821015E7D) {
/*     */       
/* 113 */       int a = year / 100;
/* 114 */       b = 2 - a + a / 4;
/*     */     } 
/* 116 */     return (long)(365.25D * year - year_corr) + (long)(30.6001D * (month + 1)) + day + 1720995L + b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void JdToYmd() {
/* 125 */     long a, z = this.lJulianDay;
/* 126 */     if (z < 2299161L) {
/* 127 */       a = z;
/*     */     } else {
/*     */       
/* 130 */       long alpha = (long)((z - 1867216.25D) / 36524.25D);
/* 131 */       a = z + 1L + alpha - alpha / 4L;
/*     */     } 
/* 133 */     long b = a + 1524L;
/* 134 */     long c = (long)((b - 122.1D) / 365.25D);
/* 135 */     long d = (long)(365.25D * c);
/* 136 */     long e = (long)((b - d) / 30.6001D);
/* 137 */     this.dia = (int)(b - d - (long)(30.6001D * e));
/* 138 */     this.mes = (int)((e < 13.5D) ? (e - 1L) : (e - 13L));
/* 139 */     this.anno = (int)((this.mes > 2.5D) ? (c - 4716L) : (c - 4715L));
/* 140 */     if (this.anno <= 0) {
/* 141 */       this.anno--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fechaMasDias(long dias) {
/* 148 */     this.lJulianDay += dias;
/* 149 */     JdToYmd();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 154 */   public long getJuliano() { return this.lJulianDay; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int getAnno() { return this.anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getMes() { return this.mes; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public int getDia() { return this.dia; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public int getHora() { return this.hora; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public int getMinutos() { return this.minutos; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public int getSegundos() { return this.segundos; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String getFecha() { return "" + Utilidades.formato(this.anno, 4) + "-" + Utilidades.formato(this.mes, 2) + "-" + Utilidades.formato(this.dia, 2); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 194 */     String sMes = "";
/* 195 */     switch (this.mes) {
/*     */       case 1:
/* 197 */         sMes = "Enero";
/*     */         break;
/*     */       case 2:
/* 200 */         sMes = "Febrero";
/*     */         break;
/*     */       case 3:
/* 203 */         sMes = "Marzo";
/*     */         break;
/*     */       case 4:
/* 206 */         sMes = "Abril";
/*     */         break;
/*     */       case 5:
/* 209 */         sMes = "Mayo";
/*     */         break;
/*     */       case 6:
/* 212 */         sMes = "Junio";
/*     */         break;
/*     */       case 7:
/* 215 */         sMes = "Julio";
/*     */         break;
/*     */       case 8:
/* 218 */         sMes = "Agosto";
/*     */         break;
/*     */       case 9:
/* 221 */         sMes = "Septiembre";
/*     */         break;
/*     */       case 10:
/* 224 */         sMes = "Octubre";
/*     */         break;
/*     */       case 11:
/* 227 */         sMes = "Noviembre";
/*     */         break;
/*     */       case 12:
/* 230 */         sMes = "Diciembre";
/*     */         break;
/*     */     } 
/*     */     
/* 234 */     return "" + this.dia + " de " + sMes + " de " + this.anno;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHora(int h, int m, int s) {
/* 239 */     this.hora = h;
/* 240 */     this.minutos = m;
/* 241 */     this.segundos = s;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 246 */   public String getFechaHora() { return "" + Utilidades.formato(this.anno, 4) + "-" + Utilidades.formato(this.mes, 2) + "-" + Utilidades.formato(this.dia, 2) + " " + Utilidades.formato(this.hora, 2) + ":" + Utilidades.formato(this.minutos, 2) + ":" + Utilidades.formato(this.segundos, 2); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrimerDiaMes() {
/* 251 */     this.lJulianDay = YmdToJd(this.anno, this.mes, 1);
/* 252 */     JdToYmd();
/* 253 */     return getFecha();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFechaD2() {
/*     */     try {
/* 263 */       return "" + Utilidades.formato(this.dia, 2) + "-" + Utilidades.formato(this.mes, 2) + "-" + Utilidades.formato(this.anno, 4);
/*     */     }
/* 265 */     catch (Exception e) {
/*     */ 
/*     */       
/* 268 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nombreMes() {
/* 279 */     String sMes = "";
/* 280 */     switch (this.mes) {
/*     */       case 1:
/* 282 */         sMes = "Enero";
/*     */         break;
/*     */       case 2:
/* 285 */         sMes = "Febrero";
/*     */         break;
/*     */       case 3:
/* 288 */         sMes = "Marzo";
/*     */         break;
/*     */       case 4:
/* 291 */         sMes = "Abril";
/*     */         break;
/*     */       case 5:
/* 294 */         sMes = "Mayo";
/*     */         break;
/*     */       case 6:
/* 297 */         sMes = "Junio";
/*     */         break;
/*     */       case 7:
/* 300 */         sMes = "Julio";
/*     */         break;
/*     */       case 8:
/* 303 */         sMes = "Agosto";
/*     */         break;
/*     */       case 9:
/* 306 */         sMes = "Septiembre";
/*     */         break;
/*     */       case 10:
/* 309 */         sMes = "Octubre";
/*     */         break;
/*     */       case 11:
/* 312 */         sMes = "Noviembre";
/*     */         break;
/*     */       case 12:
/* 315 */         sMes = "Diciembre";
/*     */         break;
/*     */     } 
/*     */     
/* 319 */     return sMes + " de " + this.anno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public String getFechaSinHoraPegada() { return "" + Utilidades.formato(this.anno, 4) + Utilidades.formato(this.mes, 2) + Utilidades.formato(this.dia, 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public String getFechaHoraD4() { return "" + Utilidades.formato(this.dia, 2) + "/" + Utilidades.formato(this.mes, 2) + "/" + Utilidades.formato(this.anno, 4) + " " + Utilidades.formato(this.hora, 2) + ":" + Utilidades.formato(this.minutos, 2) + ":" + Utilidades.formato(this.segundos, 2); }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\FechaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */