//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Utilidades {
    public static final boolean miFecha = false;
    public static final int MAXIMOINTENTOS = 5;
    public static final int POSTGRES = 1;
    public static final int SQLSERVER97 = 2;
    public static final int SQLSERVER2000 = 4;
    public static final int ORACLE = 5;
    public static final int SYBASE = 3;

    public Utilidades() {
    }

    public static double round(double Valor) {
        return (double)Math.round(Valor);
    }

    public static double round2(double Valor) {
        return round(Valor * 100.0D) / 100.0D;
    }

    public static int porcentajeEntre(int esperado, int verdadero) {
        try {
            float por = (float)(verdadero / esperado * 100);
            int porcentaje = (int)por;
            return porcentaje;
        } catch (Exception var4) {
            return 0;
        }
    }

    public static String formatDouble(double valor) {
        DecimalFormat df = new DecimalFormat("##0");
        return df.format(valor);
    }

    public static String formatDouble2(double valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("##0.00", simbolos);
        return df.format(valor);
    }

    public static int numeroMes(String sMes) {
        int mes = 0;
        if (sMes.equals("JAN")) {
            mes = 1;
        } else if (sMes.equals("FEB")) {
            mes = 2;
        } else if (sMes.equals("MAR")) {
            mes = 3;
        } else if (sMes.equals("APR")) {
            mes = 4;
        } else if (sMes.equals("MAY")) {
            mes = 5;
        } else if (sMes.equals("JUN")) {
            mes = 6;
        } else if (sMes.equals("JUL")) {
            mes = 7;
        } else if (sMes.equals("AUG")) {
            mes = 8;
        } else if (sMes.equals("SEP")) {
            mes = 9;
        } else if (sMes.equals("OCT")) {
            mes = 10;
        } else if (sMes.equals("NOV")) {
            mes = 11;
        } else if (sMes.equals("DEC")) {
            mes = 12;
        }

        return mes;
    }

    public static int diasMes(int ano, int mes) {
        if (mes != 1 && mes != 3 && mes != 5 && mes != 7 && mes != 8 && mes != 10 && mes != 12) {
            if (mes != 4 && mes != 6 && mes != 9 && mes != 11) {
                return ano % 4 == 0 ? 29 : 28;
            } else {
                return 30;
            }
        } else {
            return 31;
        }
    }

    public static String nombreMes(int mes) {
        String salida = "";
        switch(mes) {
        case 1:
            salida = "Enero";
            break;
        case 2:
            salida = "Febrero";
            break;
        case 3:
            salida = "Marzo";
            break;
        case 4:
            salida = "Abril";
            break;
        case 5:
            salida = "Mayo";
            break;
        case 6:
            salida = "Junio";
            break;
        case 7:
            salida = "Julio";
            break;
        case 8:
            salida = "Agosto";
            break;
        case 9:
            salida = "Septiembre";
            break;
        case 10:
            salida = "Octubre";
            break;
        case 11:
            salida = "Noviembre";
            break;
        case 12:
            salida = "Diciembre";
        }

        return salida;
    }

    public static String GCtoString(GregorianCalendar gc) {
        String salida = "" + formato(gc.get(1), 4) + "-" + formato(gc.get(2) + 1, 2) + "-" + formato(gc.get(5), 2);
        String hora = "" + formato(gc.get(11), 2) + ":" + formato(gc.get(12), 2) + ":" + formato(gc.get(13), 2);
        return salida + " " + hora;
    }

    public static String GCtoStringOnlyDate(GregorianCalendar gc) {
        String salida = "" + formato(gc.get(1), 4) + "-" + formato(gc.get(2) + 1, 2) + "-" + formato(gc.get(5), 2);
        return salida;
    }

    public static String GCtoAhora(GregorianCalendar gc) {
        int dias = gc.get(5);
        int meses = gc.get(2) + 1;
        int anos = gc.get(1);
        String fecha = "" + formato(anos, 4) + "-" + formato(meses, 2) + "-" + formato(dias, 2);
        String hora = "" + formato(gc.get(11), 2) + ":" + formato(gc.get(12), 2) + ":" + formato(gc.get(13), 2);
        String salida = fecha + " " + hora;
        return salida;
    }

    public static String GCtoStringSF(GregorianCalendar gc) {
        String dias = "" + gc.get(5);
        String meses = "" + (gc.get(2) + 1);
        String anos = "" + gc.get(1);
        if (dias.length() == 1) {
            dias = "0" + dias;
        }

        if (meses.length() == 1) {
            meses = "0" + meses;
        }

        String fecha = "" + anos + "-" + meses + "-" + dias;
        return fecha;
    }

    public static GregorianCalendar StringtoGC(String miFecha) {
        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        StringTokenizer st = new StringTokenizer(miFecha, " ");
        String laFecha = "";

        try {
            laFecha = st.nextToken();
            st.nextToken();
        } catch (Exception var9) {
            laFecha = miFecha;
        }

        StringTokenizer stF = new StringTokenizer(laFecha, "-");

       // int iYear;
       // int iMonth;
       // int iDay;
        try {
            iYear = Integer.parseInt(stF.nextToken());
            iMonth = Integer.parseInt(stF.nextToken()) - 1;
            iDay = Integer.parseInt(stF.nextToken());
        } catch (Exception var8) {
            return null;
        }

        return new GregorianCalendar(iYear, iMonth, iDay);
    }

    public static String fechaActual() {
        GregorianCalendar gc = new GregorianCalendar();
        return GCtoString(gc);
    }

    public static String fechaActualDate() {
        GregorianCalendar gc = new GregorianCalendar();
        return GCtoStringOnlyDate(gc);
    }

    public static String ahora() {
        GregorianCalendar gc = new GregorianCalendar();
        return GCtoAhora(gc);
    }

    public static void sendMail(String host, String from, String to, String subject, String mensaje) {
        from = from.replace(' ', '.');
        to = to.replace(' ', '.');
        writeInfo("\nGmail. host=" + host + "\nfrom=" + from + "\nto=" + to + "\nsubject=" + subject + "\nmensaje=" + mensaje + "\n\n");
        if (ParametrosDTO.getInt("pruebas") != 1) {
            final String username = ParametrosDTO.getString("UsuarioGmailEnviaCorreo");
            final String password = ParametrosDTO.getString("ClaveGmailEnviaCorreo");
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(mensaje);
                message.setSentDate(new Date());
                Transport.send(message);
            } catch (MessagingException var10) {
                ParametrosDTO.put("causaError", var10.getMessage());
                writeError("\n--Exception handling in msgsendsample.java", var10);
                var10.printStackTrace();
            } catch (Exception var11) {
                ParametrosDTO.put("causaError", var11.getMessage());
                writeError("\n--Exception handling in msgsendsample.java", var11);
                var11.printStackTrace();
            }

        }
    }

    public static void sendMail(String host, String from, String to, String subject, String mensaje, File attachment) {
        from = from.replace(' ', '.');
        to = to.replace(' ', '.');
        writeInfo("\nhost=" + host + "\nfrom=" + from + "\nto=" + to + "\nsubject=" + subject + "\nmensaje=" + mensaje + "\n\n");
        if (ParametrosDTO.getInt("pruebas") != 1) {
            final String username = ParametrosDTO.getString("UsuarioGmailEnviaCorreo");
            final String password = ParametrosDTO.getString("ClaveGmailEnviaCorreo");
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(from));
                msg.setRecipients(RecipientType.TO, InternetAddress.parse(to));
                msg.setSubject(subject);
                MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setText(mensaje);
                MimeBodyPart mbp2 = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(attachment);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());
                Multipart mp = new MimeMultipart();
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
                msg.setContent(mp);
                msg.setSentDate(new Date());
                Transport.send(msg);
            } catch (MessagingException var15) {
                System.out.println("\n--Exception handling sending msgsendsample.java");
                writeError("\n--Exception handling in msgsendsample.java", var15);
                var15.printStackTrace();
                System.out.println();
            }

        }
    }

    public static String fechaMasHoras(String fecha, int cuantos) {
        FechaDTO fechaHora = new FechaDTO(fecha);
        GregorianCalendar gc = new GregorianCalendar(fechaHora.getAnno(), fechaHora.getMes() - 1, fechaHora.getDia(), fechaHora.getHora(), fechaHora.getMinutos(), fechaHora.getSegundos());
        gc.add(10, cuantos);
        String newFecha = "" + formato(gc.get(1), 4) + "-" + formato(gc.get(2) + 1, 2) + "-" + formato(gc.get(5), 2);
        newFecha = newFecha + " " + formato(gc.get(11), 2) + ":" + formato(gc.get(12), 2) + ":" + formato(gc.get(13), 2);
        return newFecha;
    }

    public static String fechaMas(String fecha, int cuantos, String unidad) {
        FechaDTO fechaHora = new FechaDTO(fecha);
        GregorianCalendar gc = new GregorianCalendar(fechaHora.getAnno(), fechaHora.getMes() - 1, fechaHora.getDia(), fechaHora.getHora(), fechaHora.getMinutos(), fechaHora.getSegundos());
        if (unidad.equals("hh")) {
            gc.add(10, cuantos);
        } else if (unidad.equals("mi")) {
            gc.add(12, cuantos);
        } else if (unidad.equals("ss")) {
            gc.add(13, cuantos);
        } else if (unidad.equals("mm")) {
            gc.add(2, cuantos);
        }

        String newFecha = "" + formato(gc.get(1), 4) + "-" + formato(gc.get(2) + 1, 2) + "-" + formato(gc.get(5), 2);
        newFecha = newFecha + " " + formato(gc.get(11), 2) + ":" + formato(gc.get(12), 2) + ":" + formato(gc.get(13), 2);
        return newFecha;
    }

    public static String darFormatoFecha(String s) {
        if (s.trim().equals("")) {
            return s;
        } else {
            try {
                return s.substring(0, 10);
            } catch (Exception var2) {
                return s;
            }
        }
    }

    public static String darFormatoFechaHora(String s) {
        if (s.trim().equals("")) {
            return s;
        } else {
            try {
                return s.substring(0, 16);
            } catch (Exception var2) {
                return s;
            }
        }
    }

    public static int authPOP(String server, String user, String password) {
        try {
            if (ParametrosDTO.getInt("pruebas") == 1) {
                return 1;
            } else {
                Socket sock = new Socket(server, 110);
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                OutputStream pw = sock.getOutputStream();
                pw.flush();
                br.readLine();
                String s = "user " + user + "\r\n";
                pw.write(s.getBytes(), 0, s.length());
                br.readLine();
                s = "pass " + password + "\r\n";
                pw.write(s.getBytes(), 0, s.length());
                String answerpass = br.readLine();
                s = "QUIT\r\n";
                pw.write(s.getBytes(), 0, s.length());
                sock.close();
                return answerpass.startsWith("+OK") ? 1 : 0;
            }
        } catch (BindException var8) {
            var8.printStackTrace();
            writeError("e1 Error authPOP = " + user + " " + var8.getMessage());
            ParametrosDTO.put("mensajeError", var8.getMessage());
            ParametrosDTO.put("error", "1");
            return -1;
        } catch (ConnectException var9) {
            var9.printStackTrace();
            writeError("e2 Error authPOP = " + user + " " + var9.getMessage());
            ParametrosDTO.put("mensajeError", var9.getMessage());
            ParametrosDTO.put("error", "2");
            return -2;
        } catch (MalformedURLException var10) {
            var10.printStackTrace();
            writeError("e3 Error authPOP = " + user + " " + var10.getMessage());
            ParametrosDTO.put("mensajeError", var10.getMessage());
            ParametrosDTO.put("error", "3");
            return -3;
        } catch (NoRouteToHostException var11) {
            var11.printStackTrace();
            writeError("e4 Error authPOP = " + user + " " + var11.getMessage());
            ParametrosDTO.put("mensajeError", var11.getMessage());
            ParametrosDTO.put("error", "4");
            return -4;
        } catch (ProtocolException var12) {
            var12.printStackTrace();
            writeError("e5 Error authPOP = " + user + " " + var12.getMessage());
            ParametrosDTO.put("mensajeError", var12.getMessage());
            ParametrosDTO.put("error", "5");
            return -5;
        } catch (SocketException var13) {
            var13.printStackTrace();
            writeError("e6 Error authPOP = " + user + " " + var13.getMessage());
            ParametrosDTO.put("mensajeError", var13.getMessage());
            ParametrosDTO.put("error", "6");
            return -6;
        } catch (UnknownHostException var14) {
            var14.printStackTrace();
            writeError("e7 Error authPOP = " + user + " " + var14.getMessage());
            ParametrosDTO.put("mensajeError", var14.getMessage());
            ParametrosDTO.put("error", "7");
            return -7;
        } catch (UnknownServiceException var15) {
            var15.printStackTrace();
            writeError("e8 Error authPOP = " + user + " " + var15.getMessage());
            ParametrosDTO.put("mensajeError", var15.getMessage());
            ParametrosDTO.put("error", "8");
            return -8;
        } catch (Exception var16) {
            var16.printStackTrace();
            writeError("Error authPOP = " + user + " ", var16);
            ParametrosDTO.put("mensajeError", var16.getMessage());
            ParametrosDTO.put("error", "2");
            return -9;
        }
    }

    public static boolean validarFecha(int anno, int mes, int dia) {
        if (anno < 0) {
            return false;
        } else if (mes > 0 && mes <= 12) {
            if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && dia > 31) {
                return false;
            } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                return false;
            } else if (mes == 2 && anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0) && dia > 29) {
                return false;
            } else {
                return mes != 2 || anno % 4 == 0 && (anno % 100 != 0 || anno % 400 == 0) || dia <= 28;
            }
        } else {
            return false;
        }
    }

    public static boolean validarFormatoFecha(String fecha) {
        StringTokenizer st = new StringTokenizer(fecha, "-");

        try {
            int ano = Integer.parseInt(st.nextToken());
            int mes = Integer.parseInt(st.nextToken());
            int dia = Integer.parseInt(st.nextToken());
            return validarFecha(ano, mes, dia);
        } catch (Exception var6) {
            return false;
        }
    }

    public static String miles(double valor, int decimales) {
        String tmp = "";
        if (decimales > 0) {
            tmp = ".";

            for(int i = 0; i < decimales; ++i) {
                tmp = tmp + "0";
            }
        }

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat(",##0" + tmp, simbolos);
        return df.format(valor);
    }

    public static String miles2(double valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat(",##0.00", simbolos);
        return df.format((double)Math.round(valor * 100.0D) / 100.0D);
    }

    public static void writeError(String s) {
        GregorianCalendar gc = new GregorianCalendar();
        String miLog = "LOG-" + GCtoStringSF(gc) + ".txt";

        try {
            FileOutputStream file = new FileOutputStream(ParametrosDTO.getString("logs") + miLog, true);
            String hora = "" + gc.get(11) + ":" + gc.get(12) + ":" + gc.get(13) + "==> ";
            file.write((hora + s + "\r\n\r\n").getBytes());
            file.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void writeError(String msg, Throwable excepcion) {
        GregorianCalendar gc = new GregorianCalendar();
        String miLog = "LOG-" + GCtoStringSF(gc) + ".txt";

        try {
            FileOutputStream appendedFile = new FileOutputStream(ParametrosDTO.getString("logs") + miLog, true);
            String hora = "" + gc.get(11) + ":" + gc.get(12) + ":" + gc.get(13) + "==> ";
            StackTraceElement[] elementosRastreo = excepcion.getStackTrace();
            appendedFile.write((hora + " " + msg + " " + excepcion.getMessage() + "\r\n\r\n").getBytes());

            for(int i = 0; i < elementosRastreo.length; ++i) {
                StackTraceElement elementoActual = elementosRastreo[i];
                String s = elementoActual.getClassName() + "." + elementoActual.getFileName() + "." + elementoActual.getMethodName() + " (" + elementoActual.getLineNumber() + ")\n";
                appendedFile.write(s.getBytes());
            }

            appendedFile.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public static void writeInfo(String s) {
        new GregorianCalendar();
        String miLog = "log.txt";

        try {
            FileOutputStream file = new FileOutputStream(ParametrosDTO.getString("logs") + miLog, true);
            file.write((s + "\n").getBytes());
            file.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void grabarLog(String s) {
        try {
            FileOutputStream file = new FileOutputStream(ParametrosDTO.getString("logs") + "log.txt", true);
            file.write((s + "\n").getBytes());
            file.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static String formato(int valor, int decimales) {
        String tmp = "";

        for(int i = 0; i < decimales; ++i) {
            tmp = tmp + "0";
        }

        DecimalFormat df = new DecimalFormat(tmp);
        return df.format((long)valor);
    }

    public static int getAnnoActual() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(1);
    }

    public static int getMesActual() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(2) + 1;
    }

    public static int getDiaActual() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(5);
    }

    public static String ddmmaaaaa(String fecha) {
        StringTokenizer st = new StringTokenizer(fecha, "-");

        try {
            String ano = st.nextToken();
            String mes = st.nextToken();
            String dia = st.nextToken();
            String total = dia + "/" + mes + "/" + ano;
            return total;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String inicioMesCurso() {
        return "" + formato(getAnnoActual(), 4) + "-" + formato(getMesActual(), 2) + "-01";
    }

    public static String siguienteDia(String fecha) {
        FechaDTO miFecha = new FechaDTO(fecha);
        miFecha.fechaMasDias(1L);
        return miFecha.getFecha();
    }

    public static String analizarEdad(double edad) {
        String salida = "";

        try {
            double dias = Math.floor(edad);
            double resto = edad - dias;
            if (dias > 0.0D) {
                salida = "" + (int)dias + " Dia(s)";
            }

            double horas = Math.floor(resto * 24.0D);
            resto = resto * 24.0D - horas;
            if (horas > 0.0D) {
                salida = salida + " " + (int)horas + " Hora(s)";
            }

            double minutos = Math.floor(resto * 60.0D);
            resto = resto * 60.0D - minutos;
            if (minutos > 0.0D) {
                salida = salida + " " + (int)minutos + " Min(s)";
            }

            double segundos = Math.floor(resto * 60.0D);
            if (segundos > 0.0D) {
                salida = salida + " " + (int)segundos + " Sg(s)";
            }
        } catch (Exception var13) {
        }

        return salida;
    }

    public static float indiceSatisfaccion(String ia, String ig) {
        float po = 0.0F;
        float pc = 0.0F;
        if (ia.equals("E")) {
            po = ParametrosDTO.getFloat("porcentajeE");
        } else if (ia.equals("B")) {
            po = ParametrosDTO.getFloat("porcentajeB");
        } else if (ia.equals("R")) {
            po = ParametrosDTO.getFloat("porcentajeR");
        }

        if (ig.equals("E")) {
            pc = ParametrosDTO.getFloat("porcentajeE");
        } else if (ig.equals("B")) {
            pc = ParametrosDTO.getFloat("porcentajeB");
        } else if (ig.equals("R")) {
            pc = ParametrosDTO.getFloat("porcentajeR");
        }

        return 100.0F * (po + pc) / 2.0F;
    }

    public static String fechaLarga(String miFecha) {
        StringTokenizer st = new StringTokenizer(miFecha, "-");
        int iYear = 0;
        int iMonth = 0;
        boolean var4 = false;

      //  int iYear;
       // int iMonth;
        int iDay;
        try {
            iYear = Integer.parseInt(st.nextToken());
            iMonth = Integer.parseInt(st.nextToken());
            iDay = Integer.parseInt(st.nextToken());
        } catch (Exception var6) {
            return "";
        }

        return "" + nombreMes(iMonth) + " " + iDay + " del " + iYear;
    }

    public static float porcentajeSatisfaccion(String ia) {
        if (ia.equals("E")) {
            return ParametrosDTO.getFloat("porcentajeE") * 100.0F;
        } else if (ia.equals("B")) {
            return ParametrosDTO.getFloat("porcentajeB") * 100.0F;
        } else {
            return ia.equals("R") ? ParametrosDTO.getFloat("porcentajeR") * 100.0F : 0.0F;
        }
    }

    public static String tipoAuditor(String tipo) {
        if (tipo.equals("M")) {
            return "Master";
        } else if (tipo.equals("S")) {
            return "Senior";
        } else {
            return tipo.equals("J") ? "Junior" : "";
        }
    }

    public static float calcularIndice(int ce, int cb, int cr) {
        int total = ce + cb + cr;
        return total == 0 ? 0.0F : 100.0F * (ParametrosDTO.getFloat("porcentajeE") * (float)ce + ParametrosDTO.getFloat("porcentajeB") * (float)cb + ParametrosDTO.getFloat("porcentajeR") * (float)cr) / (float)total;
    }

    public static String formatoFecha(String fecha) {
        String miFecha = "";
        if (ParametrosDTO.getInt("tipoBaseDatos") != 4 && ParametrosDTO.getInt("tipoBaseDatos") != 2) {
            if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
                miFecha = "TO_DATE('" + fecha + "','YYYY-MM-DD HH24:MI:SS')";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
                miFecha = "to_timestamp('" + fecha + "','YYYY-MM-DD HH24:MI:SS')";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 3) {
                miFecha = "'" + fecha + "'";
            } else {
                miFecha = "'" + fecha + "'";
            }
        } else {
            miFecha = "CONVERT(datetime,'" + fecha + "',120)";
        }

        return miFecha;
    }

    public static String formatoFecha2(String fecha) {
        String miFecha = "";
        if (ParametrosDTO.getInt("tipoBaseDatos") != 4 && ParametrosDTO.getInt("tipoBaseDatos") != 2) {
            if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
                miFecha = "TO_DATE('" + darFormatoFecha(fecha) + "','YYYY-MM-DD')";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
                miFecha = "TO_DATE('" + darFormatoFecha(fecha) + "','YYYY-MM-DD')";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 3) {
                miFecha = "'" + darFormatoFecha(fecha) + "'";
            } else {
                miFecha = "'" + darFormatoFecha(fecha) + "'";
            }
        } else {
            miFecha = "CONVERT(datetime,'" + darFormatoFecha(fecha) + "',111)";
        }

        return miFecha;
    }

    public static String getFechaBD() {
        String miFecha = "";
        if (ParametrosDTO.getInt("tipoBaseDatos") != 4 && ParametrosDTO.getInt("tipoBaseDatos") != 2) {
            if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
                miFecha = "sysdate";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
                miFecha = "now()";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 3) {
                miFecha = "getdate()";
            }
        } else {
            miFecha = "getdate()";
        }

        return miFecha;
    }

    public static int restaFechas(String vieja, String nueva) {
        FechaDTO fechaV = new FechaDTO(vieja);
        FechaDTO fechaN = new FechaDTO(nueva);
        return (int)(fechaN.getJuliano() - fechaV.getJuliano());
    }

    public static boolean esSecuenciaHija(String sHija, String sPadre1) {
        try {
            if (sPadre1 != null && sHija != null && sHija.indexOf(sPadre1) >= 0) {
                return true;
            }
        } catch (Exception var3) {
        }

        return false;
    }

    public static String diasDiffDB() {
        String miFecha = "";
        if (ParametrosDTO.getInt("tipoBaseDatos") != 4 && ParametrosDTO.getInt("tipoBaseDatos") != 2) {
            if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
                miFecha = "sysdate";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
                miFecha = "now()";
            } else if (ParametrosDTO.getInt("tipoBaseDatos") == 3) {
                miFecha = "getdate()";
            }
        } else {
            miFecha = "getdate()";
        }

        return miFecha;
    }

    public static String getFullString(String cadena, String prefijo, int longitudMaxima) {
        StringBuffer res = new StringBuffer();
        res.append(cadena + " ");

        for(int i = 0; i < longitudMaxima - cadena.trim().length(); ++i) {
            res.append(prefijo);
        }

        return res.toString();
    }

    public static String verificarDirectorioDestino(String ruta, int anno, int mes) {
        ManejadorArchivosDTO m = new ManejadorArchivosDTO();
        if (m.exists(ruta + "/" + anno) == 0) {
            m.mkdir(ruta + "/" + anno);
        }

        if (m.exists(ruta + "/" + anno + "/" + nombreMes(mes)) == 0) {
            m.mkdir(ruta + "/" + anno + "/" + nombreMes(mes));
        }

        return ruta + "/" + anno + "/" + nombreMes(mes);
    }

    public static String[] arregloCadena(String cadena, int len) {
        Pattern p = Pattern.compile(" ");
        String[] arr = p.split(cadena);
        Vector<String> rv = new Vector();
        String bloque = "";

        for(int i = 0; i < arr.length; ++i) {
            if (bloque.length() + arr[i].length() > len) {
                rv.add(bloque);
                bloque = "";
            }

            bloque = bloque + arr[i] + " ";
        }

        if (bloque.length() > 0) {
            rv.add(bloque);
        }

        String[] strings = new String[rv.size()];
        int i = 0;

        String str;
        for(Iterator i$ = rv.iterator(); i$.hasNext(); strings[i++] = str) {
            str = (String)i$.next();
        }

        return strings;
    }

    public static String miles(double valor) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat(",##0.00", simbolos);
        return df.format(valor);
    }

    public static String formatoNumero2(double valor) {
        DecimalFormat df = new DecimalFormat("##################");
        return df.format(valor);
    }

    public static String removerAcentos(String str) {
        String fixedFileName = str.replace('#', '_');
        fixedFileName = fixedFileName.replace('&', '_');
        fixedFileName = fixedFileName.replace('á', 'a');
        fixedFileName = fixedFileName.replace('é', 'e');
        fixedFileName = fixedFileName.replace('í', 'i');
        fixedFileName = fixedFileName.replace('ó', 'o');
        fixedFileName = fixedFileName.replace('ú', 'u');
        fixedFileName = fixedFileName.replace('ñ', 'n');
        fixedFileName = fixedFileName.replace('Ñ', 'N');
        fixedFileName = fixedFileName.replace('Á', 'A');
        fixedFileName = fixedFileName.replace('É', 'E');
        fixedFileName = fixedFileName.replace('Í', 'I');
        fixedFileName = fixedFileName.replace('Ó', 'O');
        fixedFileName = fixedFileName.replace('Ú', 'U');
        fixedFileName = fixedFileName.replace("-", "_");
        fixedFileName = fixedFileName.replace(" ", "");
        fixedFileName = fixedFileName.replace("%", "");
        fixedFileName = fixedFileName.replace("$", "");
        fixedFileName = fixedFileName.replace("'", "");
        fixedFileName = fixedFileName.replace("/", "");
        fixedFileName = fixedFileName.replace("\\", "");
        fixedFileName = fixedFileName.replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "_");
        fixedFileName = fixedFileName.replaceAll("[^\\x00-\\x7F]", "_");
        return fixedFileName;
    }
}
