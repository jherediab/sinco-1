//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import com.lutris.appserver.server.user.User;
import java.io.Serializable;

public class UserDO implements User, Serializable {
    private String username;

    public UserDO(String username) {
        this.username = username;
    }

    public String getName() {
        return this.username;
    }
}
