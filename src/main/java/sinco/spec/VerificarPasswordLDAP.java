//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;
import java.io.UnsupportedEncodingException;
import sinco.business.ParametrosDTO;
import sinco.business.Utilidades;

public class VerificarPasswordLDAP {
    LDAPConnection lc = new LDAPConnection();
    private String ultimoError;

    public VerificarPasswordLDAP() {
    }

    public String getUltimoError() {
        return this.ultimoError == null ? "" : this.ultimoError;
    }

    private void decodificaErrorLdap(int resultCode) {
        String error = "";
        if (resultCode == 1) {
            error = "Error en la operacion realizada";
        } else if (resultCode == 2) {
            error = "Error de protocolo";
        } else if (resultCode == 3) {
            error = "Tiempo de espere excedido";
        } else if (resultCode == 4) {
            error = "Capacidad excedida";
        } else if (resultCode == 5) {
            error = "Comparacion es falsa";
        } else if (resultCode == 6) {
            error = "Comparacion es verdadera";
        } else if (resultCode == 7) {
            error = "Metodo de autenticacion no soportado";
        } else if (resultCode == 8) {
            error = "Se requiere autenticacion fuerte";
        } else if (resultCode == 9) {
            error = "Resultado parcial del LDAP en la consulta";
        } else if (resultCode == 16) {
            error = "No se encontraron atributos para la consulta";
        } else if (resultCode == 17) {
            error = "El atributo consultado no esta definido";
        } else if (resultCode == 18) {
            error = "Consulta no valida";
        } else if (resultCode == 19) {
            error = "Violacion de las caracteristicas del campo";
        } else if (resultCode == 20) {
            error = "No existe el valor o el atributo consultado";
        } else if (resultCode == 21) {
            error = "Sintaxis del atributo no es valida";
        } else if (resultCode == 32) {
            error = "No se encontro resultado para la consulta";
        } else if (resultCode == 33) {
            error = "Verificacion de alias no valida";
        } else if (resultCode == 34) {
            error = "Sintaxis del DN consultado no es valido";
        } else if (resultCode == 48) {
            error = "Metodo de autenticacion no es el apropiado";
        } else if (resultCode == 49) {
            error = "Clave de acceso del usuario no valida al acceder";
        } else if (resultCode == 50) {
            error = "No se tienen suficientes privilegios para realizar la operacion deseada";
        } else if (resultCode == 51) {
            error = "En este momento se encuentra ocupado";
        } else if (resultCode == 52) {
            error = "En este momento no esta disponible";
        } else if (resultCode == 53) {
            error = "Usuario administrador bloqueado o no valido";
        } else if (resultCode == 68) {
            error = "Entrada ya existente";
        } else if (resultCode == 81) {
            error = "En este momento esta caido";
        } else if (resultCode == 85) {
            error = "En este momento no responde";
        } else if (resultCode == 91) {
            error = "Error de conexion";
        } else if (resultCode == 92) {
            error = "Operacion no soportada por el Ldap";
        } else if (resultCode == 94) {
            error = "No se retornaron datos en la consulta";
        } else {
            error = "" + resultCode;
        }

        this.ultimoError = error;
    }

    private boolean conectar(String ldapHost, int ldapPort) {
        try {
            this.lc.connect(ldapHost, ldapPort);
            return true;
        } catch (LDAPException var4) {
            this.decodificaErrorLdap(var4.getResultCode());
            Utilidades.writeError("", var4);
            return false;
        }
    }

    private void desconectar() {
        try {
            this.lc.disconnect();
        } catch (LDAPException var2) {
            this.decodificaErrorLdap(var2.getResultCode());
            Utilidades.writeError("", var2);
        }

    }

    private String obtenerDn(String searchBase, String searchFilter) {
        int searchScope = 2;
        boolean attributeOnly = false;
        String[] attrs = new String[]{"1.1"};

        try {
            LDAPSearchResults searchResults = this.lc.search(searchBase, searchScope, searchFilter, attrs, attributeOnly);
            String dnUsuario = "";

            while(searchResults.hasMore()) {
                LDAPEntry nextEntry = null;

                try {
                    nextEntry = searchResults.next();
                } catch (LDAPException var10) {
                    this.decodificaErrorLdap(var10.getResultCode());
                    continue;
                }

                dnUsuario = nextEntry.getDN();
            }

            return dnUsuario;
        } catch (LDAPException var11) {
            Utilidades.writeError("", var11);
            this.decodificaErrorLdap(var11.getResultCode());
            return null;
        }
    }

    private boolean verificarPassword(String objectDN, String testPassword) {
        try {
            this.lc.bind(3, objectDN, testPassword.getBytes("UTF8"));
            return true;
        } catch (LDAPException var4) {
            this.decodificaErrorLdap(var4.getResultCode());
            if (var4.getResultCode() == 32) {
                Utilidades.writeError("Error: No such entry", var4);
            } else if (var4.getResultCode() == 16) {
                Utilidades.writeError("Error: No such attribute ", var4);
            } else {
                Utilidades.writeError("Error:  ", var4);
            }
        } catch (UnsupportedEncodingException var5) {
            Utilidades.writeError("Error:  ", var5);
        }

        return false;
    }

    public String verificarUsuarioLdap(String ldapHost, int ldapPort, String objectDN, String prefijoDn, String user, String testPassword) {
        boolean rta = this.conectar(ldapHost, ldapPort);
        if (!rta) {
            this.desconectar();
            return "N";
        } else {
            String dn = prefijoDn + user + "," + objectDN;
            if (ParametrosDTO.getInt("traceo") == 1) {
                Utilidades.writeError("" + dn);
            }

            rta = this.verificarPassword(dn, testPassword);
            if (ParametrosDTO.getInt("traceo") == 1) {
                Utilidades.writeError("verificar Password ldap=" + rta);
            }

            this.desconectar();
            return rta ? "S" : "N";
        }
    }

    private boolean conectar(String ldapHost, int ldapPort, String loginDN, String password) {
        byte ldapVersion = 3;

        try {
            this.lc.connect(ldapHost, ldapPort);
            this.lc.bind(ldapVersion, loginDN, password.getBytes("UTF8"));
            return true;
        } catch (LDAPException var7) {
            Utilidades.writeError("Error:  ", var7);
            this.decodificaErrorLdap(var7.getResultCode());
        } catch (UnsupportedEncodingException var8) {
            Utilidades.writeError("Error:  ", var8);
        }

        return false;
    }

    public String validarPassword(String ldapHost, int ldapPort, String loginAdminDN, String passwordAdmin, String objectDN, String prefijoDn, String user, String testPassword) {
        this.ultimoError = null;
        boolean rta = this.conectar(ldapHost, ldapPort, loginAdminDN, passwordAdmin);
        if (!rta) {
            Utilidades.writeError("Error ldap: " + this.ultimoError);
            this.desconectar();
            return "N";
        } else {
            if (ParametrosDTO.getInt("traceo") == 1) {
                Utilidades.writeError("raiz: " + objectDN + " Filtro: " + prefijoDn + user);
            }

            String dn = this.obtenerDn(objectDN, prefijoDn + user);
            if (!rta) {
                Utilidades.writeError("Error ldap obtenerDn: " + this.ultimoError);
                this.desconectar();
                return "N";
            } else {
                if (ParametrosDTO.getInt("traceo") == 1) {
                    Utilidades.writeError("dn= " + dn);
                }

                if (dn == null) {
                    this.desconectar();
                    return "N";
                } else if (dn.equals("")) {
                    this.desconectar();
                    return "N";
                } else {
                    rta = this.verificarPassword(dn, testPassword);
                    this.desconectar();
                    if (ParametrosDTO.getInt("traceo") == 1) {
                        Utilidades.writeError("verificar Password ldap=" + rta);
                    }

                    return rta ? "S" : "N";
                }
            }
        }
    }
}
