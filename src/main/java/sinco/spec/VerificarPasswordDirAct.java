//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import java.util.Hashtable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import sinco.business.ParametrosDTO;
import sinco.business.Utilidades;

public class VerificarPasswordDirAct {
    private String ultimoError;
    private DirContext ctx;
    private String urlLdap = "";

    public VerificarPasswordDirAct() {
    }

    public String getUltimoError() {
        return this.ultimoError == null ? "" : this.ultimoError;
    }

    private boolean conectar(String loginAdminDN, String passwordAdmin) {
        try {
            Hashtable<String, Object> env = new Hashtable();
            env.put("java.naming.security.authentication", "simple");
            env.put("java.naming.security.principal", loginAdminDN);
            env.put("java.naming.security.credentials", passwordAdmin);
            env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
            env.put("java.naming.provider.url", this.urlLdap);
            env.put("java.naming.ldap.attributes.binary", "objectSID");
            this.ctx = new InitialDirContext(env);
            return true;
        } catch (NamingException var4) {
            this.ultimoError = var4.getMessage();
        } catch (Exception var5) {
            this.ultimoError = var5.getMessage();
        }

        return false;
    }

    private void desconectar() {
        try {
            this.ctx.close();
        } catch (NamingException var2) {
            this.ultimoError = var2.getMessage();
            Utilidades.writeError("", var2);
        }

    }

    private String obtenerDn(String searchBase, String accountName) {
        try {
            String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(2);
            NamingEnumeration<SearchResult> results = this.ctx.search(searchBase, searchFilter, searchControls);
            if (results.hasMoreElements()) {
                SearchResult searchResult = (SearchResult)results.nextElement();
                if (results.hasMoreElements()) {
                    Utilidades.writeError("Matched multiple groups for the group with SID: " + accountName);
                    return null;
                }

                return searchResult.getNameInNamespace();
            }
        } catch (NamingException var7) {
            this.ultimoError = var7.getMessage();
            Utilidades.writeError("", var7);
        }

        return null;
    }

    private boolean verificarPassword(String objectDN, String testPassword) {
        try {
            Hashtable<String, Object> env = new Hashtable();
            env.put("java.naming.security.authentication", "simple");
            env.put("java.naming.security.principal", objectDN);
            env.put("java.naming.security.credentials", testPassword);
            env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
            env.put("java.naming.provider.url", this.urlLdap);
            DirContext ctxx = new InitialDirContext(env);
            ctxx.close();
            return true;
        } catch (NamingException var5) {
            this.ultimoError = var5.getMessage();
            Utilidades.writeError("Password no valido " + objectDN + " " + var5.getMessage());
            return false;
        }
    }

    public String validarPassword(String ldapHost, int ldapPort, String loginAdminDN, String passwordAdmin, String ldapSearchBase, String prefijoDn, String user, String testPassword) {
        this.ultimoError = null;
        this.urlLdap = ldapHost + ":" + ldapPort;
        boolean rta = this.conectar(loginAdminDN, passwordAdmin);
        if (!rta) {
            Utilidades.writeError("Error Conectando directorio activo: " + this.ultimoError);
            return "N";
        } else {
            if (ParametrosDTO.getInt("traceo") == 1) {
                Utilidades.writeError("raiz: " + ldapSearchBase + " Filtro: " + user);
            }

            String dn = this.obtenerDn(ldapSearchBase, user);
            if (!rta) {
                Utilidades.writeError("Error obteniendo dn: " + this.ultimoError);
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
                    this.desconectar();
                    rta = this.verificarPassword(dn, testPassword);
                    if (ParametrosDTO.getInt("traceo") == 1) {
                        Utilidades.writeError("verificar Password ldap=" + rta);
                    }

                    return rta ? "S" : "N";
                }
            }
        }
    }
}
