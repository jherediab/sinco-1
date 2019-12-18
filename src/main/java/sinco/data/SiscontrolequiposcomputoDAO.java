//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import sinco.business.RespuestaBD;
import sinco.business.SiscontrolequiposcomputoDTO;
import sinco.business.Utilidades;

public class SiscontrolequiposcomputoDAO {
    public SiscontrolequiposcomputoDAO() {
    }

    public SiscontrolequiposcomputoDTO leerRegistro(ResultSet rs) {
        try {
            SiscontrolequiposcomputoDTO reg = new SiscontrolequiposcomputoDTO();
            reg.setNHojaControl(rs.getInt("n_hoja_control"));
            reg.setFechaInventario(rs.getString("fecha_inventario"));
            reg.setMarca(rs.getString("marca"));
            reg.setSerial(rs.getInt("serial"));
            reg.setNInventarioPc(rs.getInt("n_inventario_pc"));
            reg.setUbicacion(rs.getString("ubicacion"));
            reg.setEstadoPc(rs.getString("estado_pc"));
            reg.setMarcaCpu(rs.getString("marca_cpu"));
            reg.setSerialCpu(rs.getInt("serial_cpu"));
            reg.setNInventarioCpu(rs.getInt("n_inventario_cpu"));
            reg.setMemoriaRam(rs.getString("memoria_ram"));
            reg.setMarcaDiscoDuro(rs.getString("marca_disco_duro"));
            reg.setSerialDiscoDuro(rs.getInt("serial_disco_duro"));
            reg.setProcesador(rs.getString("procesador"));
            reg.setMarcaBoard(rs.getString("marca_board"));
            reg.setSerialBoard(rs.getInt("serial_board"));
            reg.setMarcaTeclado(rs.getString("marca_teclado"));
            reg.setNInventarioT(rs.getInt("n_inventario_t"));
            reg.setEstadoT(rs.getString("estado_t"));
            reg.setMarcaMouse(rs.getString("marca_mouse"));
            reg.setNInventario(rs.getInt("n_inventario"));
            reg.setEstadoM(rs.getString("estado_m"));
            reg.setConectorMause(rs.getString("conector_mause"));
            reg.setConectorTeclado(rs.getString("conector_teclado"));
            reg.setMarcaUnidadCd(rs.getString("marca_unidad_cd"));
            reg.setSerialUnidadCd(rs.getInt("serial_unidad_cd"));
            reg.setMarcaFuentePoder(rs.getString("marca_fuente_poder"));
            reg.setTargetaRed(rs.getString("targeta_red"));
            reg.setSistemaOperativo(rs.getString("sistema_operativo"));
            reg.setParticionDiscoDuro(rs.getString("particion_disco_duro"));
            reg.setMicrosoftOffice(rs.getString("microsoft_office"));
            reg.setVersionOffice(rs.getString("version_office"));
            reg.setNavegadores(rs.getString("navegadores"));
            reg.setAdobeReader(rs.getString("adobe_reader"));
            reg.setVersionAdobe(rs.getString("version_adobe"));
            reg.setAntivirus(rs.getString("antivirus"));
            reg.setDeepFreeze(rs.getString("deep_freeze"));
            reg.setVersionDeepFreeze(rs.getString("version_deep_freeze"));
            reg.setOtrosSoftware(rs.getString("otros_software"));
            reg.setVersionSoftware(rs.getString("version_software"));
            reg.setTipoMatenimiento(rs.getString("tipo_matenimiento"));
            reg.setFechaRealizado(rs.getString("fecha_realizado"));
            reg.setRealizo(rs.getString("realizo"));
            reg.setRecibio(rs.getString("recibio"));
            reg.setDescripcion(rs.getString("descripcion"));
            reg.setObservaciones(rs.getString("observaciones"));
            return reg;
        } catch (Exception var3) {
            var3.printStackTrace();
            Utilidades.writeError("SiscontrolequiposcomputoDAO:leerRegistro ", var3);
            return null;
        }
    }

    public Collection<SiscontrolequiposcomputoDTO> cargarTodos() {
        Collection<SiscontrolequiposcomputoDTO> resultados = new ArrayList();
        DBManager dat = new DBManager();

        try {
            String s = "select t.n_hoja_control,t.fecha_inventario,t.marca,t.serial,t.n_inventario_pc,t.ubicacion,t.estado_pc,t.marca_cpu,t.serial_cpu,t.n_inventario_cpu,t.memoria_ram,t.marca_disco_duro,t.serial_disco_duro,t.procesador,t.marca_board,t.serial_board,t.marca_teclado,t.n_inventario_t,t.estado_t,t.marca_mouse,t.n_inventario,t.estado_m,t.conector_mause,t.conector_teclado,t.marca_unidad_cd,t.serial_unidad_cd,t.marca_fuente_poder,t.targeta_red,t.sistema_operativo,t.particion_disco_duro,t.microsoft_office,t.version_office,t.navegadores,t.adobe_reader,t.version_adobe,t.antivirus,t.deep_freeze,t.version_deep_freeze,t.otros_software,t.version_software,t.tipo_matenimiento,t.fecha_realizado,t.realizo,t.recibio,t.descripcion,t.observaciones from control_equipos_computo t  where 1=1";
            s = s + " order by 1";
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
            Utilidades.writeError("SiscontrolequiposcomputoDAO:cargarTodos ", var10);
        } finally {
            dat.close();
        }

        return resultados;
    }

    public SiscontrolequiposcomputoDTO cargarRegistro(int nHojaControl) {
        DBManager dat = new DBManager();

        try {
            String s = "select t.n_hoja_control,t.fecha_inventario,t.marca,t.serial,t.n_inventario_pc,t.ubicacion,t.estado_pc,t.marca_cpu,t.serial_cpu,t.n_inventario_cpu,t.memoria_ram,t.marca_disco_duro,t.serial_disco_duro,t.procesador,t.marca_board,t.serial_board,t.marca_teclado,t.n_inventario_t,t.estado_t,t.marca_mouse,t.n_inventario,t.estado_m,t.conector_mause,t.conector_teclado,t.marca_unidad_cd,t.serial_unidad_cd,t.marca_fuente_poder,t.targeta_red,t.sistema_operativo,t.particion_disco_duro,t.microsoft_office,t.version_office,t.navegadores,t.adobe_reader,t.version_adobe,t.antivirus,t.deep_freeze,t.version_deep_freeze,t.otros_software,t.version_software,t.tipo_matenimiento,t.fecha_realizado,t.realizo,t.recibio,t.descripcion,t.observaciones from control_equipos_computo t  where  t.n_hoja_control=" + nHojaControl + "";
            boolean rtaDB = dat.parseSql(s);
            SiscontrolequiposcomputoDTO var6;
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
            Utilidades.writeError("SiscontrolequiposcomputoDAO:cargarSiscontrolequiposcomputo", var10);
        } finally {
            dat.close();
        }

        return null;
    }

    public synchronized int siguienteRegistro() {
        int inumero = 1;
        String s = "select max(n_hoja_control) from control_equipos_computo ";
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
            Utilidades.writeError("SiscontrolequiposcomputoDAO:siguienteRegistro ", var10);
            return 0;
        } finally {
            dat.close();
        }

        return var6;
    }

    public RespuestaBD eliminarRegistro(int nHojaControl) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "delete from control_equipos_computo where  n_hoja_control=" + nHojaControl + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var8) {
            var8.printStackTrace();
            Utilidades.writeError("SiscontrolequiposcomputoDAO:eliminarRegistro ", var8);
            rta.setMensaje(var8.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }

    public RespuestaBD crearRegistro(int nHojaControl, String fechaInventario, String marca, int serial, int nInventarioPc, String ubicacion, String estadoPc, String marcaCpu, int serialCpu, int nInventarioCpu, String memoriaRam, String marcaDiscoDuro, int serialDiscoDuro, String procesador, String marcaBoard, int serialBoard, String marcaTeclado, int nInventarioT, String estadoT, String marcaMouse, int nInventario, String estadoM, String conectorMause, String conectorTeclado, String marcaUnidadCd, int serialUnidadCd, String marcaFuentePoder, String targetaRed, String sistemaOperativo, String particionDiscoDuro, String microsoftOffice, String versionOffice, String navegadores, String adobeReader, String versionAdobe, String antivirus, String deepFreeze, String versionDeepFreeze, String otrosSoftware, String versionSoftware, String tipoMatenimiento, String fechaRealizado, String realizo, String recibio, String descripcion, String observaciones) {
        RespuestaBD rta = new RespuestaBD();
        int elSiguiente = this.siguienteRegistro();
        if (elSiguiente == 0) {
            rta.setMensaje("Generando secuencia");
            return rta;
        } else {
            DBManager dat = new DBManager();

            try {
                String s = "insert into control_equipos_computo(n_hoja_control,fecha_inventario,marca,serial,n_inventario_pc,ubicacion,estado_pc,marca_cpu,serial_cpu,n_inventario_cpu,memoria_ram,marca_disco_duro,serial_disco_duro,procesador,marca_board,serial_board,marca_teclado,n_inventario_t,estado_t,marca_mouse,n_inventario,estado_m,conector_mause,conector_teclado,marca_unidad_cd,serial_unidad_cd,marca_fuente_poder,targeta_red,sistema_operativo,particion_disco_duro,microsoft_office,version_office,navegadores,adobe_reader,version_adobe,antivirus,deep_freeze,version_deep_freeze,otros_software,version_software,tipo_matenimiento,fecha_realizado,realizo,recibio,descripcion,observaciones) values (" + elSiguiente + "," + "" + (fechaInventario.length() > 0 ? Utilidades.formatoFecha2(fechaInventario) : "null") + "," + "'" + marca + "'," + "" + serial + "," + "" + nInventarioPc + "," + "'" + ubicacion + "'," + "'" + estadoPc + "'," + "'" + marcaCpu + "'," + "" + serialCpu + "," + "" + nInventarioCpu + "," + "'" + memoriaRam + "'," + "'" + marcaDiscoDuro + "'," + "" + serialDiscoDuro + "," + "'" + procesador + "'," + "'" + marcaBoard + "'," + "" + serialBoard + "," + "'" + marcaTeclado + "'," + "" + nInventarioT + "," + "'" + estadoT + "'," + "'" + marcaMouse + "'," + "" + nInventario + "," + "'" + estadoM + "'," + "'" + conectorMause + "'," + "'" + conectorTeclado + "'," + "'" + marcaUnidadCd + "'," + "" + serialUnidadCd + "," + "'" + marcaFuentePoder + "'," + "'" + targetaRed + "'," + "'" + sistemaOperativo + "'," + "'" + particionDiscoDuro + "'," + "'" + microsoftOffice + "'," + "'" + versionOffice + "'," + "'" + navegadores + "'," + "'" + adobeReader + "'," + "'" + versionAdobe + "'," + "'" + antivirus + "'," + "'" + deepFreeze + "'," + "'" + versionDeepFreeze + "'," + "'" + otrosSoftware + "'," + "'" + versionSoftware + "'," + "'" + tipoMatenimiento + "'," + "" + (fechaRealizado.length() > 0 ? Utilidades.formatoFecha2(fechaRealizado) : "null") + "," + "'" + realizo + "'," + "'" + recibio + "'," + "'" + descripcion + "'," + "'" + observaciones + "'" + ")";
                rta = dat.executeUpdate2(s);
                rta.setSecuencia(elSiguiente);
            } catch (Exception var54) {
                var54.printStackTrace();
                Utilidades.writeError("%SiscontrolequiposcomputoDAO:crearRegistro ", var54);
                rta.setMensaje(var54.getMessage());
            } finally {
                dat.close();
            }

            return rta;
        }
    }

    public RespuestaBD modificarRegistro(int nHojaControl, String fechaInventario, String marca, int serial, int nInventarioPc, String ubicacion, String estadoPc, String marcaCpu, int serialCpu, int nInventarioCpu, String memoriaRam, String marcaDiscoDuro, int serialDiscoDuro, String procesador, String marcaBoard, int serialBoard, String marcaTeclado, int nInventarioT, String estadoT, String marcaMouse, int nInventario, String estadoM, String conectorMause, String conectorTeclado, String marcaUnidadCd, int serialUnidadCd, String marcaFuentePoder, String targetaRed, String sistemaOperativo, String particionDiscoDuro, String microsoftOffice, String versionOffice, String navegadores, String adobeReader, String versionAdobe, String antivirus, String deepFreeze, String versionDeepFreeze, String otrosSoftware, String versionSoftware, String tipoMatenimiento, String fechaRealizado, String realizo, String recibio, String descripcion, String observaciones) {
        RespuestaBD rta = new RespuestaBD();
        DBManager dat = new DBManager();

        try {
            String s = "update control_equipos_computo set  fecha_inventario=" + (fechaInventario.length() > 0 ? Utilidades.formatoFecha2(fechaInventario) : "null") + "," + " marca='" + marca + "'," + " serial=" + serial + "," + " n_inventario_pc=" + nInventarioPc + "," + " ubicacion='" + ubicacion + "'," + " estado_pc='" + estadoPc + "'," + " marca_cpu='" + marcaCpu + "'," + " serial_cpu=" + serialCpu + "," + " n_inventario_cpu=" + nInventarioCpu + "," + " memoria_ram='" + memoriaRam + "'," + " marca_disco_duro='" + marcaDiscoDuro + "'," + " serial_disco_duro=" + serialDiscoDuro + "," + " procesador='" + procesador + "'," + " marca_board='" + marcaBoard + "'," + " serial_board=" + serialBoard + "," + " marca_teclado='" + marcaTeclado + "'," + " n_inventario_t=" + nInventarioT + "," + " estado_t='" + estadoT + "'," + " marca_mouse='" + marcaMouse + "'," + " n_inventario=" + nInventario + "," + " estado_m='" + estadoM + "'," + " conector_mause='" + conectorMause + "'," + " conector_teclado='" + conectorTeclado + "'," + " marca_unidad_cd='" + marcaUnidadCd + "'," + " serial_unidad_cd=" + serialUnidadCd + "," + " marca_fuente_poder='" + marcaFuentePoder + "'," + " targeta_red='" + targetaRed + "'," + " sistema_operativo='" + sistemaOperativo + "'," + " particion_disco_duro='" + particionDiscoDuro + "'," + " microsoft_office='" + microsoftOffice + "'," + " version_office='" + versionOffice + "'," + " navegadores='" + navegadores + "'," + " adobe_reader='" + adobeReader + "'," + " version_adobe='" + versionAdobe + "'," + " antivirus='" + antivirus + "'," + " deep_freeze='" + deepFreeze + "'," + " version_deep_freeze='" + versionDeepFreeze + "'," + " otros_software='" + otrosSoftware + "'," + " version_software='" + versionSoftware + "'," + " tipo_matenimiento='" + tipoMatenimiento + "'," + " fecha_realizado=" + (fechaRealizado.length() > 0 ? Utilidades.formatoFecha2(fechaRealizado) : "null") + "," + " realizo='" + realizo + "'," + " recibio='" + recibio + "'," + " descripcion='" + descripcion + "'," + " observaciones='" + observaciones + "'" + " where" + " n_hoja_control=" + nHojaControl + "";
            rta = dat.executeUpdate2(s);
        } catch (Exception var53) {
            var53.printStackTrace();
            Utilidades.writeError("SiscontrolequiposcomputoDAO:modificarRegistro ", var53);
            rta.setMensaje(var53.getMessage());
        } finally {
            dat.close();
        }

        return rta;
    }
}
