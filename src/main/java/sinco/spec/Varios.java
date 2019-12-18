//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import sinco.business.AreasDTO;
import sinco.business.EstadoDTO;
import sinco.business.ParametrosAplicacionDTO;
import sinco.business.ParametrosDTO;
import sinco.business.PersonasAreaDTO;
import sinco.business.RespuestaBD;
import sinco.business.SecuenciaEstadoDTO;
import sinco.business.SisCorreosDTO;
import sinco.business.SisMensajesDTO;
import sinco.business.SisUsuariosDTO;
import sinco.business.TipoCalificacionDTO;
import sinco.business.Utilidades;
import sinco.business.VSolicitudesDTO;
import sinco.data.AreasDAO;
import sinco.data.EstadoDAO;
import sinco.data.HistoriaSolicitudDAO;
import sinco.data.LibreDAO;
import sinco.data.ParametrosAplicacionDAO;
import sinco.data.PersonasAreaDAO;
import sinco.data.SecuenciaEstadoDAO;
import sinco.data.SeguridadDAO;
import sinco.data.SisCorreosDAO;
import sinco.data.SisMensajesDAO;
import sinco.data.SisUsuariosDAO;
import sinco.data.SolicitudDAO;
import sinco.data.TipoCalificacionDAO;

public class Varios {
    public Varios() {
    }

    public void EnviarSisCorreos(int numeroSolicitud, String tipoCorreo, String usuario) {
        SisCorreosDAO rsCorreos = new SisCorreosDAO();
        if (rsCorreos.getEstadoConexion()) {
            Collection<SisCorreosDTO> arr = rsCorreos.cargarPendientes(numeroSolicitud, tipoCorreo);
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                SisCorreosDTO reg = (SisCorreosDTO)iterator.next();
                Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), reg.getOrigen(), reg.getDestino(), reg.getAsunto(), reg.getMensaje());
                rsCorreos.actualizarEstado(reg.getConsecutivo(), "E", usuario);
            }

            rsCorreos.close();
        }
    }

    public String getNivelSuperior(int area, int niveles) {
        String retorno = "";

        try {
            AreasDAO af = new AreasDAO();
            int areaAnterior = area;
            retorno = retorno + area;

            for(int i = 0; i < niveles; ++i) {
                AreasDTO regArea = af.getArea(areaAnterior);
                if (regArea != null && regArea.getNivelSuperior() != 0) {
                    areaAnterior = regArea.getNivelSuperior();
                    retorno = retorno + "," + regArea.getNivelSuperior();
                }
            }

            af.close();
            return retorno;
        } catch (Exception var8) {
            return "" + area;
        }
    }

    public void EnviarSisCorreos(int numeroSolicitud, String usuario) {
        SisCorreosDAO rsCorreos = new SisCorreosDAO();
        if (rsCorreos.getEstadoConexion()) {
            Collection<SisCorreosDTO> arr = rsCorreos.cargarPendientes(numeroSolicitud);
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                SisCorreosDTO reg = (SisCorreosDTO)iterator.next();
                Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), reg.getOrigen(), reg.getDestino(), reg.getAsunto(), reg.getMensaje());
                rsCorreos.actualizarEstado(reg.getConsecutivo(), "E", usuario);
            }

            rsCorreos.close();
        }
    }

    public AreasDTO getObjNivelSuperior(int nivelsuperior) {
        if (nivelsuperior == -1) {
            return null;
        } else {
            AreasDAO af = new AreasDAO();
            AreasDTO are = af.getArea(nivelsuperior);
            af.close();
            return are;
        }
    }

    public int getSuperior(int area, int codigo) {
        PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
        PersonasAreaDTO regPa = rsPersonasArea.cargarJefe(area);
        rsPersonasArea.close();
        if (regPa == null) {
            return -1;
        } else {
            int pr = regPa.getCodigoEmpleado();
            if (pr != codigo) {
                return regPa.getCodigoEmpleado();
            } else {
                AreasDAO af = new AreasDAO();
                AreasDTO ar = af.getArea(area);
                af.close();
                int arsup = ar.getNivelSuperior();
                if (arsup != -1 && arsup != 0) {
                    AreasDTO regAreaS = this.getObjNivelSuperior(ar.getNivelSuperior());
                    if (regAreaS == null) {
                        return -1;
                    } else {
                        PersonasAreaDAO rsPersonasArea2 = new PersonasAreaDAO();
                        PersonasAreaDTO regPa2 = rsPersonasArea2.cargarJefe(regAreaS.getCodigo());
                        rsPersonasArea2.close();
                        return regPa2 != null ? regPa2.getCodigoEmpleado() : -1;
                    }
                } else {
                    return -1;
                }
            }
        }
    }

    public int getPrimerActivo(int area, int codigo, String estado) {
        if (estado.equals("A")) {
            return codigo;
        } else {
            int supe = this.getSuperior(area, codigo);
            if (supe == -1) {
                return -1;
            } else {
                SisUsuariosDAO pf = new SisUsuariosDAO();
                SisUsuariosDTO p = pf.cargarRegistro(supe);
                return this.getPrimerActivo(p.getArea(), p.getCodigoEmpleado(), p.getEstado());
            }
        }
    }

    public int getPrimerActivoConCorreo(int area, int codigo, String estado) {
        if (estado.equals("A")) {
            return codigo;
        } else {
            int supe = this.getSuperior(area, codigo);
            if (supe == -1) {
                return -1;
            } else {
                SisUsuariosDAO pf = new SisUsuariosDAO();
                SisUsuariosDTO p = pf.cargarRegistro(supe);
                return this.getPrimerActivoConCorreo(p.getArea(), p.getCodigoEmpleado(), p.getEstado());
            }
        }
    }

    public SisUsuariosDTO leerPersona(int codigo) {
        SisUsuariosDAO pf = new SisUsuariosDAO();
        SisUsuariosDTO p = pf.cargarRegistro(codigo);
        return p;
    }

    public SisUsuariosDTO getJefeProveedorObj(int empleadoProveedor) {
        SisUsuariosDTO p = this.leerPersona(empleadoProveedor);
        int sup = this.getSuperior(p.getArea(), p.getCodigoEmpleado());
        return sup == -1 ? null : this.leerPersona(sup);
    }

    public RespuestaBD actualizarEstadoObj(int navegante, int viejo, int nuevo, String observacion, VSolicitudesDTO regSol, boolean enviarmensaje, String usuario, int devuelta, int nuevoResponsable, int diasSumar, String codigoConfiabilidad) {
        SecuenciaEstadoDAO sefa = new SecuenciaEstadoDAO();
        SecuenciaEstadoDTO secu = sefa.getSecuenciaEstado(nuevo, viejo);
        sefa.close();
        if (secu == null) {
            return new RespuestaBD();
        } else {
            EstadoDAO ef = new EstadoDAO();
            EstadoDTO e = ef.getEstado(nuevo);
            ef.close();
            String codigoOportunidad = "";
            String fechaEscalamiento = "";
            int diasesperados;
            if (devuelta == 1) {
                diasesperados = diasSumar + (int)Math.round((double)ParametrosDTO.getInt("porcentaje.tiempo.reenvio.solicitud") * (double)regSol.getDuracion() / 100.0D);
                if (diasesperados > 0) {
                    fechaEscalamiento = Utilidades2.fechaTerminacion(Utilidades.ahora(), diasesperados, regSol.getUnidadMedida());
                }

                if (nuevoResponsable > 0) {
                    regSol.setEmpleadoProveedor(nuevoResponsable);
                }
            } else if (e.getOportunidad()) {
                regSol.setFechaOportunidad(Utilidades.fechaActual());
                if (regSol.getNivelEscalamiento() == 0) {
                    diasesperados = Utilidades2.diferenciaDias(regSol.getFechaVigencia(), regSol.getFechaEstimadaTerminacion());
                    int diasreales = Utilidades2.diferenciaDias(regSol.getFechaVigencia(), regSol.getFechaOportunidad());
                    regSol.setFechaEstimadaTerminacion(Utilidades.GCtoString(Utilidades.StringtoGC(regSol.getFechaEstimadaTerminacion())));
                    if (regSol.getFechaOportunidad().compareTo(regSol.getFechaEstimadaTerminacion()) < 0) {
                        diasreales = 0;
                    }

                    int porcentajeoportunidad = Utilidades.porcentajeEntre(diasesperados, diasreales);
                    if (porcentajeoportunidad > 999) {
                        porcentajeoportunidad = 999;
                    }

                    TipoCalificacionDAO tcf = new TipoCalificacionDAO();
                    tcf.cargarPorPorcentaje(porcentajeoportunidad);
                    TipoCalificacionDTO tipoc = tcf.next();
                    tcf.close();
                    codigoOportunidad = "E";
                    if (tipoc != null) {
                        codigoOportunidad = "" + tipoc.getCodigo();
                    }
                } else {
                    codigoOportunidad = "R";
                }
            }

            SolicitudDAO sf = new SolicitudDAO();
            RespuestaBD rtadb = sf.actualizarEstado(regSol.getNumero(), nuevo, e.getTipoEstado(), e.getCierra(), e.getOportunidad(), codigoOportunidad, regSol.getNivelEscalamiento(), devuelta, codigoConfiabilidad, diasSumar, regSol.getDuracion(), regSol.getUnidadMedida(), nuevoResponsable, fechaEscalamiento, observacion, usuario);
            sf.close();
            if (rtadb.getCerrarSolicitud().equals("N")) {
                return rtadb;
            } else {
                HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
                hsf.crearHistoria(regSol.getNumero(), nuevo, regSol.getCodigoEstado(), observacion, usuario);
                hsf.close();
                if (!enviarmensaje) {
                    return rtadb;
                } else {
                    int rol = e.getRolGeneraMail();
                    if (rol > 0) {
                        SisUsuariosDTO recipiente;
                        if (rol == 2) {
                            recipiente = this.leerPersona(regSol.getEmpleadoCliente());
                        } else {
                            if (rol != 3 && rol != 5) {
                                return rtadb;
                            }

                            recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
                        }

                        String url = "";
                        String pag1 = "VSPorAt.po";
                        String pag2 = "VSEnCurso.po";
                        if (e.getTipoEstado().trim().equals("PRV")) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag1 + "?solicitud=" + regSol.getNumero();
                        }

                        if (e.getTipoEstado().trim().equals("DV") || e.getTipoEstado().equals("CAL") || e.getTipoEstado().equals("VER") || e.getTipoEstado().equals("VED")) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                        }

                        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                        }

                        SisUsuariosDAO perf = new SisUsuariosDAO();
                        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
                        String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
                        String from = regNavegante.getEmail();
                        String to = recipiente.getEmail();
                        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
                    }

                    this.EnviarSisCorreos(regSol.getNumero(), usuario);
                    return rtadb;
                }
            }
        }
    }

    public RespuestaBD actualizarEstadoObj(int navegante, int viejo, int nuevo, String observacion, File ds, VSolicitudesDTO regSol, boolean enviarmensaje, String usuario) {
        SecuenciaEstadoDAO sefa = new SecuenciaEstadoDAO();
        SecuenciaEstadoDTO secu = sefa.getSecuenciaEstado(nuevo, viejo);
        sefa.close();
        if (secu == null) {
            return new RespuestaBD();
        } else {
            EstadoDAO ef = new EstadoDAO();
            EstadoDTO e = ef.getEstado(nuevo);
            ef.close();
            String codigoOportunidad = "";
            if (e.getOportunidad()) {
                regSol.setFechaOportunidad(Utilidades.fechaActual());
                if (regSol.getNivelEscalamiento() == 0) {
                    int diasesperados = Utilidades2.diferenciaDias(regSol.getFechaVigencia(), regSol.getFechaEstimadaTerminacion());
                    int diasreales = Utilidades2.diferenciaDias(regSol.getFechaVigencia(), regSol.getFechaOportunidad());
                    regSol.setFechaEstimadaTerminacion(Utilidades.GCtoString(Utilidades.StringtoGC(regSol.getFechaEstimadaTerminacion())));
                    if (regSol.getFechaOportunidad().compareTo(regSol.getFechaEstimadaTerminacion()) < 0) {
                        diasreales = 0;
                    }

                    int porcentajeoportunidad = Utilidades.porcentajeEntre(diasesperados, diasreales);
                    TipoCalificacionDAO tcf = new TipoCalificacionDAO();
                    tcf.cargarPorPorcentaje(porcentajeoportunidad);
                    TipoCalificacionDTO tipoc = tcf.next();
                    tcf.close();
                    if (tipoc != null) {
                        codigoOportunidad = "" + tipoc.getCodigo();
                    } else {
                        codigoOportunidad = "E";
                    }
                } else {
                    codigoOportunidad = "R";
                }
            }

            SolicitudDAO sf = new SolicitudDAO();
            RespuestaBD rtadb = sf.actualizarEstado(regSol.getNumero(), nuevo, e.getTipoEstado(), e.getCierra(), e.getOportunidad(), codigoOportunidad, regSol.getNivelEscalamiento(), observacion, usuario);
            sf.close();
            if (rtadb.getCerrarSolicitud().equals("N")) {
                return rtadb;
            } else {
                HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
                hsf.crearHistoria(regSol.getNumero(), nuevo, regSol.getCodigoEstado(), observacion, usuario);
                hsf.close();
                if (!enviarmensaje) {
                    return rtadb;
                } else {
                    int rol = e.getRolGeneraMail();
                    if (rol > 0) {
                        SisUsuariosDTO recipiente;
                        if (rol == 2) {
                            recipiente = this.leerPersona(regSol.getEmpleadoCliente());
                        } else {
                            if (rol != 3 && rol != 5) {
                                return rtadb;
                            }

                            recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
                        }

                        String url = "";
                        String pag1 = "VSPorAt.po";
                        String pag2 = "VSEnCurso.po";
                        if (e.getTipoEstado().trim().equals("PRV")) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag1 + "?solicitud=" + regSol.getNumero();
                        }

                        if (e.getTipoEstado().trim().equals("DV") || e.getTipoEstado().equals("CAL") || e.getTipoEstado().equals("VER") || e.getTipoEstado().equals("VER")) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                        }

                        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
                            url = "\n" + ParametrosDTO.getString("url.sistema");
                            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                        }

                        SisUsuariosDAO perf = new SisUsuariosDAO();
                        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
                        String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
                        String from = regNavegante.getEmail();
                        String to = recipiente.getEmail();
                        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje, ds);
                    }

                    return rtadb;
                }
            }
        }
    }

    public void enviarSolicitud(int navegante, int viejo, int nuevo, String observacion, VSolicitudesDTO regSol, boolean enviarmensaje, String fechaVigencia, String fechaterminacion, int horasContacto, int duracion, String unidadMedida, String usuario) {
        SecuenciaEstadoDAO sefa = new SecuenciaEstadoDAO();
        SecuenciaEstadoDTO secu = sefa.getSecuenciaEstado(nuevo, viejo);
        sefa.close();
        if (secu != null) {
            String fechaContacto = "";
            if (horasContacto > 0) {
                fechaContacto = Utilidades2.fechaTerminacion(fechaVigencia, horasContacto, "HO");
            }

            EstadoDAO ef = new EstadoDAO();
            EstadoDTO e = ef.getEstado(nuevo);
            ef.close();
            SolicitudDAO sf = new SolicitudDAO();
            sf.enviarSolicitud(regSol.getNumero(), nuevo, fechaVigencia, fechaterminacion, duracion, unidadMedida, observacion, horasContacto, fechaContacto, usuario);
            sf.close();
            HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
            hsf.crearHistoria(regSol.getNumero(), nuevo, regSol.getCodigoEstado(), observacion, usuario);
            hsf.close();
            if (enviarmensaje) {
                int rol = e.getRolGeneraMail();
                if (rol > 0) {
                    SisUsuariosDTO recipiente;
                    if (rol == 2) {
                        recipiente = this.leerPersona(regSol.getEmpleadoCliente());
                    } else {
                        if (rol != 3 && rol != 5) {
                            return;
                        }

                        recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
                    }

                    String url = "";
                    String pag1 = "VSPorAt.po";
                    String pag2 = "VSEnCurso.po";
                    if (e.getTipoEstado().trim().equals("PRV")) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag1 + "?solicitud=" + regSol.getNumero();
                    }

                    if (e.getTipoEstado().trim().equals("DV") || e.getTipoEstado().equals("CAL") || e.getTipoEstado().equals("VER") || e.getTipoEstado().equals("VED")) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                    }

                    if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                    }

                    SisUsuariosDAO perf = new SisUsuariosDAO();
                    SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
                    String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
                    String from = regNavegante.getEmail();
                    String to = recipiente.getEmail();
                    Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
                }

            }
        }
    }

    public void enviarSolicitud(int navegante, int viejo, int nuevo, String observacion, File ds, VSolicitudesDTO regSol, boolean enviarmensaje, String fechaVigencia, String fechaterminacion, int horasContacto, int duracion, String unidadMedida, String usuario) {
        SecuenciaEstadoDAO sefa = new SecuenciaEstadoDAO();
        SecuenciaEstadoDTO secu = sefa.getSecuenciaEstado(nuevo, viejo);
        sefa.close();
        if (secu != null) {
            String fechaContacto = "";
            if (horasContacto > 0) {
                fechaContacto = Utilidades2.fechaTerminacion(fechaVigencia, horasContacto, "HO");
            }

            EstadoDAO ef = new EstadoDAO();
            EstadoDTO e = ef.getEstado(nuevo);
            ef.close();
            SolicitudDAO sf = new SolicitudDAO();
            sf.enviarSolicitud(regSol.getNumero(), nuevo, fechaVigencia, fechaterminacion, duracion, unidadMedida, observacion, horasContacto, fechaContacto, usuario);
            sf.close();
            HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
            hsf.crearHistoria(regSol.getNumero(), nuevo, regSol.getCodigoEstado(), observacion, usuario);
            hsf.close();
            if (enviarmensaje) {
                int rol = e.getRolGeneraMail();
                if (rol > 0) {
                    SisUsuariosDTO recipiente;
                    if (rol == 2) {
                        recipiente = this.leerPersona(regSol.getEmpleadoCliente());
                    } else {
                        if (rol != 3 && rol != 5) {
                            return;
                        }

                        recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
                    }

                    String url = "";
                    String pag1 = "VSPorAt.po";
                    String pag2 = "VSEnCurso.po";
                    if (e.getTipoEstado().trim().equals("PRV")) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag1 + "?solicitud=" + regSol.getNumero();
                    }

                    if (e.getTipoEstado().trim().equals("DV") || e.getTipoEstado().equals("CAL") || e.getTipoEstado().equals("VER") || e.getTipoEstado().equals("VER")) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                    }

                    if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
                        url = "\n" + ParametrosDTO.getString("url.sistema");
                        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=" + pag2 + "?solicitud=" + regSol.getNumero();
                    }

                    SisUsuariosDAO perf = new SisUsuariosDAO();
                    SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
                    String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
                    String from = regNavegante.getEmail();
                    String to = recipiente.getEmail();
                    Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje, ds);
                }

            }
        }
    }

    public void enviarCorreoSolicitud(int numero, int navegante, int empProveedor, VSolicitudesDTO regSol) {
        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO regCliente = perf.cargarRegistro(navegante);
        SisUsuariosDTO regProveedor = perf.cargarRegistro(empProveedor);
        String url = ParametrosDTO.getString("url.sistema") + "LU.po?l=" + regProveedor.getCodigoEmpleado() + "&p=" + regProveedor.getPassword() + "&t=m&h=VSPorAt.po?solicitud=" + numero;
        String mensaje = this.formatMensaje("LlegoSolicitud", "" + numero, regSol.getNombreServicio(), regSol.getNombreEstado(), url);
        String from = regCliente.getEmail();
        String to = regProveedor.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
    }

    public void nuevoProveedor(int navegante, String observacion, VSolicitudesDTO regSol, String usuario) {
        HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
        hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), observacion, usuario);
        hsf.close();
        EstadoDAO ef = new EstadoDAO();
        EstadoDTO e = ef.getEstado(regSol.getCodigoEstado());
        ef.close();
        SisUsuariosDTO recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
        String url = "\n" + ParametrosDTO.getString("url.sistema");
        if (e.getTipoEstado().trim().equals("PRV") || e.getTipoEstado().trim().equals("ESC")) {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSPorAt.po?solicitud=" + regSol.getNumero();
        }

        if (e.getTipoEstado().trim().equals("DV") || e.getTipoEstado().equals("CAL")) {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
        }

        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null && regSol.getCodigoConfiabilidad().equals("R")) {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSPorPlan.po?solicitud=" + regSol.getNumero();
        }

        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null && !regSol.getCodigoConfiabilidad().equals("R")) {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
        }

        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
        String mensaje = this.formatMensaje("RedireccionoSolicitado", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
    }

    public void viejoProveedor(int numero, int codigo, SisUsuariosDTO regNavegante, SisUsuariosDTO recipiente, String nombreServicio) {
        String url = ParametrosDTO.getString("url.sistema") + "LU.po?l=" + codigo + "&p=123&t=m&h=VSEnCurso.po?solicitud=" + numero;
        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        String mensaje = this.formatMensaje("CambioProveedorSolicitud", "" + numero, nombreServicio, url);
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, nombreServicio, mensaje);
    }

    public void recordarCalificacion(int tipo, int elNavegante, VSolicitudesDTO regSol, String usuario) {
        SisUsuariosDAO pf = new SisUsuariosDAO();
        SisUsuariosDTO regNavegante = pf.cargarRegistro(elNavegante);
        HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
        if (tipo == 1) {
            hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), regNavegante.getNombre() + " Recordo Calificacion al Cliente", usuario);
        } else {
            hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), regNavegante.getNombre() + " Recordo Atencion al Proveedor", usuario);
        }

        hsf.close();
        EstadoDAO ef = new EstadoDAO();
        EstadoDTO e = ef.getEstado(regSol.getCodigoEstado());
        ef.close();
        SisUsuariosDTO recipiente;
        if (tipo == 1) {
            recipiente = this.leerPersona(regSol.getEmpleadoCliente());
        } else {
            recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
        }

        String url = "";
        url = "\n" + ParametrosDTO.getString("url.sistema");
        String mensaje = "";
        if (tipo == 1) {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
            mensaje = this.formatMensaje("RecordarCalificacionSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
        } else {
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSPorAt.po?solicitud=" + regSol.getNumero();
            mensaje = this.formatMensaje("RecordarAtencionSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), regNavegante.getNombre(), url);
        }

        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
    }

    public void notificacionProveedorAnterior(VSolicitudesDTO regSol, String from) {
        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO recipiente = perf.cargarRegistro(regSol.getProveedorAnterior());
        if (recipiente != null) {
            String url = "\n" + ParametrosDTO.getString("url.sistema") + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&";
            url = url + "h=VSEnCurso.po?solicitud=" + regSol.getNumero() + "&lectura=1";
            String mensaje = this.formatMensaje("NotificarProveedorAnterior", "" + regSol.getNumero(), regSol.getNombreServicio(), regSol.getNombreProveedor(), regSol.getNombreEstado(), url);
            String to = recipiente.getEmail();
            Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
        }
    }

    public boolean actualizarPlaneacion(VSolicitudesDTO regSol, int anterior, int nuevo, String usuario) {
        LibreDAO rsLibre = new LibreDAO();
        int maximo = rsLibre.maxivoValor("detalle_auditoria", "consecutivo", "codigo_empleado=" + nuevo + " and ciclo='" + regSol.getCiclo() + "'");
        String s = "update detalle_auditoria  set codigo_empleado=" + nuevo + ",";
        s = s + "consecutivo=" + maximo + ",";
        s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
        s = s + "usuario_modificacion='" + usuario + "'";
        s = s + " where codigo_empleado=" + anterior + " and ciclo='" + regSol.getCiclo() + "' and solicitud=" + regSol.getNumero();
        boolean rta = rsLibre.executeUpdate(s);
        rsLibre.close();
        return rta;
    }

    public boolean cerrarSolicitudSinCalificar(int navegante, VSolicitudesDTO regSol, int codigoEstado, String usuario) {
        String s = "update solicitudes set abierta='N',codigo_estado=" + codigoEstado + ",";
        s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
        s = s + "usuario_modificacion='" + usuario + "'";
        s = s + " where numero=" + regSol.getNumero();
        LibreDAO rsLibre = new LibreDAO();
        boolean rta = rsLibre.executeUpdate(s);
        rsLibre.close();
        EstadoDAO ef = new EstadoDAO();
        EstadoDTO e = ef.getEstado(codigoEstado);
        ef.close();
        HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
        hsf.crearHistoria(regSol.getNumero(), codigoEstado, regSol.getCodigoEstado(), "", usuario);
        hsf.close();
        SisUsuariosDTO recipiente = this.leerPersona(regSol.getEmpleadoCliente());
        String url = "";
        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
            url = "\n" + ParametrosDTO.getString("url.sistema");
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
        }

        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
        String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
        return rta;
    }

    public boolean cerrarSolicitudSinCalificar(int navegante, VSolicitudesDTO regSol, int codigoEstado, File ds, String usuario) {
        String s = "update solicitudes set abierta='N',codigo_estado=" + codigoEstado + ",";
        s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
        s = s + "usuario_modificacion='" + usuario + "'";
        s = s + " where numero=" + regSol.getNumero();
        LibreDAO rsLibre = new LibreDAO();
        boolean rta = rsLibre.executeUpdate(s);
        rsLibre.close();
        EstadoDAO ef = new EstadoDAO();
        EstadoDTO e = ef.getEstado(codigoEstado);
        ef.close();
        HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
        hsf.crearHistoria(regSol.getNumero(), codigoEstado, regSol.getCodigoEstado(), "", usuario);
        hsf.close();
        SisUsuariosDTO recipiente = this.leerPersona(regSol.getEmpleadoCliente());
        String url = "";
        if (e.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad() != null) {
            url = "\n" + ParametrosDTO.getString("url.sistema");
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
        }

        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
        String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), e.getDescripcion(), url);
        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje, ds);
        return rta;
    }

    public boolean mensajeArchivo(int navegante, VSolicitudesDTO regSol, File ds) {
        SisUsuariosDTO recipiente;
        if (navegante == regSol.getEmpleadoCliente()) {
            recipiente = this.leerPersona(regSol.getEmpleadoProveedor());
        } else {
            recipiente = this.leerPersona(regSol.getEmpleadoCliente());
        }

        String url = "\n" + ParametrosDTO.getString("url.sistema");
        url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
        SisUsuariosDAO perf = new SisUsuariosDAO();
        SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
        String mensaje = this.formatMensaje("LlegoSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), regSol.getNombreEstado(), url);
        String from = regNavegante.getEmail();
        String to = recipiente.getEmail();
        Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje, ds);
        return true;
    }

    public String formatMensaje(String codigo) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes != null ? regMensajes.getDescripcion() : "";
    }

    public String formatMensaje(String codigo, String p1) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1);
    }

    public String formatMensaje(String codigo, String p1, String p2) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1, p2);
    }

    public String formatMensaje(String codigo, String p1, String p2, String p3) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1, p2, p3);
    }

    public String formatMensaje(String codigo, String p1, String p2, String p3, String p4) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1, p2, p3, p4);
    }

    public String formatMensaje(String codigo, String p1, String p2, String p3, String p4, String p5) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1, p2, p3, p4, p5);
    }

    public String formatMensaje(String codigo, String p1, String p2, String p3, String p4, String p5, String p6) {
        SisMensajesDAO rsMensajes = new SisMensajesDAO();
        SisMensajesDTO regMensajes = rsMensajes.cargarRegistro(codigo);
        rsMensajes.close();
        return regMensajes == null ? "El mensaje no existe. " + codigo : regMensajes.format(p1, p2, p3, p4, p5, p6);
    }

    public boolean cargarParametros() {
        ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
        if (!rs.getEstadoConexion()) {
            return false;
        } else {
            Collection<ParametrosAplicacionDTO> arr = rs.cargarTodos("", "");
            rs.close();
            Iterator iterator = arr.iterator();

            while(iterator.hasNext()) {
                ParametrosAplicacionDTO reg = (ParametrosAplicacionDTO)iterator.next();
                switch(Integer.parseInt(reg.getControl())) {
                case 1:
                    ParametrosDTO.put(reg.getCodigo(), "" + reg.getEntero());
                    break;
                case 2:
                    ParametrosDTO.put(reg.getCodigo(), "" + reg.getReal());
                    break;
                case 3:
                    ParametrosDTO.put(reg.getCodigo(), "" + reg.getCaracter());
                    break;
                case 4:
                    ParametrosDTO.put(reg.getCodigo(), "" + reg.getFecha());
                    break;
                case 5:
                    ParametrosDTO.put(reg.getCodigo(), "" + reg.getDescripcion());
                }
            }

            if (ParametrosDTO.getString("encoding").equals("")) {
                ParametrosDTO.put("encoding", "UTF-8");
            }

            TipoCalificacionDAO tcfac = new TipoCalificacionDAO();
            TipoCalificacionDTO tCAL = tcfac.getTipoCalificacion('E');
            if (tCAL != null) {
                ParametrosDTO.put("porcentajeE", "" + tCAL.getPorcentaje());
            }

            tCAL = tcfac.getTipoCalificacion('B');
            if (tCAL != null) {
                ParametrosDTO.put("porcentajeB", "" + tCAL.getPorcentaje());
            }

            tCAL = tcfac.getTipoCalificacion('R');
            if (tCAL != null) {
                ParametrosDTO.put("porcentajeR", "" + tCAL.getPorcentaje());
            }

            tcfac.close();
            EstadoDAO estadof = new EstadoDAO();
            EstadoDTO estado = estadof.getEstado("ESC");
            if (estado != null) {
                ParametrosDTO.put("ESC", "" + estado.getCodigo());
            }

            estado = estadof.getEstado("PRV");
            if (estado != null) {
                ParametrosDTO.put("PRV", "" + estado.getCodigo());
            }

            estadof.close();
            return true;
        }
    }

    public boolean cambiarCliente(int navegante, VSolicitudesDTO regSol, String usuario) {
        PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
        PersonasAreaDTO cliente = rsPersonasArea.cargarPersona(ParametrosDTO.getInt("Cliente_Quejas"));
        rsPersonasArea.close();
        if (cliente == null) {
            return true;
        } else {
            SisUsuariosDAO perf = new SisUsuariosDAO();
            SisUsuariosDTO regNavegante = perf.cargarRegistro(navegante);
            String s = "update solicitudes set empleado_cliente=" + cliente.getCodigoEmpleado() + "," + "area_cliente=" + cliente.getCodigoArea() + "," + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "usuario_modificacion='" + usuario + "'" + " where numero=" + regSol.getNumero();
            LibreDAO rsLibre = new LibreDAO();
            boolean rta = rsLibre.executeUpdate(s);
            rsLibre.close();
            HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
            hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), "Cambio Funcioario Cliente", usuario);
            hsf.close();
            SisUsuariosDTO recipiente = this.leerPersona(cliente.getCodigoEmpleado());
            String url = "";
            url = "\n" + ParametrosDTO.getString("url.sistema");
            url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&h=VSEnCurso.po?solicitud=" + regSol.getNumero();
            String mensaje = this.formatMensaje("ClienteSolicitud", "" + regSol.getNumero(), regSol.getNombreServicio(), url);
            String from = regNavegante.getEmail();
            String to = recipiente.getEmail();
            Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
            return rta;
        }
    }

    public boolean tienePermiso(int miGrupo, String permiso) {
        SeguridadDAO rsSeguridad = new SeguridadDAO();
        if (!rsSeguridad.getEstadoConexion()) {
            return false;
        } else {
            boolean bPermiso = rsSeguridad.tieneLlave(miGrupo, permiso);
            rsSeguridad.close();
            return bPermiso;
        }
    }

    public int getPrimerActivo(int area, int codigo, String estado, String enviarCorreo) {
        if (estado.equals("A") && enviarCorreo.equals("S")) {
            return codigo;
        } else {
            int supe = this.getSuperior(area, codigo);
            if (supe != -1 && supe != 0) {
                SisUsuariosDAO pf = new SisUsuariosDAO();
                SisUsuariosDTO p = pf.cargarRegistro(supe);
                return this.getPrimerActivo(p.getArea(), p.getCodigoEmpleado(), p.getEstado());
            } else {
                return -1;
            }
        }
    }

    public SisUsuariosDTO getJefeArea(int area) {
        AreasDAO af = new AreasDAO();
        AreasDTO regArea = af.getArea(area);
        af.close();
        return this.leerPersona(regArea.getPersonaResponsable());
    }
}
