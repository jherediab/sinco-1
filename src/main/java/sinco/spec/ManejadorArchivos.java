//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sinco.spec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Arrays;

public class ManejadorArchivos {
    private int SUCCESS = 1;
    private int FAILURE = 0;

    public ManejadorArchivos() {
    }

    public int canRead(String path) {
        File myFile = new File(path);
        return myFile.canRead() ? this.SUCCESS : this.FAILURE;
    }

    public int canWrite(String path) {
        File myFile = new File(path);
        return myFile.canWrite() ? this.SUCCESS : this.FAILURE;
    }

    public int createNewFile(String path) throws IOException {
        File myFile = new File(path);
        return myFile.createNewFile() ? this.SUCCESS : this.FAILURE;
    }

    public int delete(String path) {
        File myFile = new File(path);
        return myFile.delete() ? this.SUCCESS : this.FAILURE;
    }

    public int exists(String path) {
        File myFile = new File(path);
        return myFile.exists() ? this.SUCCESS : this.FAILURE;
    }

    public int isDirectory(String path) {
        File myFile = new File(path);
        return myFile.isDirectory() ? this.SUCCESS : this.FAILURE;
    }

    public int isFile(String path) {
        File myFile = new File(path);
        return myFile.isFile() ? this.SUCCESS : this.FAILURE;
    }

    public int isHidden(String path) {
        File myFile = new File(path);
        return myFile.isHidden() ? this.SUCCESS : this.FAILURE;
    }

    public Timestamp lastModified(String path) {
        File myFile = new File(path);
        return new Timestamp(myFile.lastModified());
    }

    public long length(String path) {
        File myFile = new File(path);
        return myFile.length();
    }

    public String list(String path) {
        String list = "";
        File myFile = new File(path);
        String[] arrayList = myFile.list();
        Arrays.sort(arrayList, String.CASE_INSENSITIVE_ORDER);

        for(int i = 0; i < arrayList.length && list.length() + arrayList[i].length() + 1 <= 32767; ++i) {
            if (!list.equals("")) {
                list = list + "," + arrayList[i];
            } else {
                list = list + arrayList[i];
            }
        }

        return list;
    }

    public int mkdir(String path) {
        File myFile = new File(path);
        return myFile.mkdir() ? this.SUCCESS : this.FAILURE;
    }

    public int mkdirs(String path) {
        File myFile = new File(path);
        return myFile.mkdirs() ? this.SUCCESS : this.FAILURE;
    }

    public int renameTo(String fromPath, String toPath) {
        File myFromFile = new File(fromPath);
        File myToFile = new File(toPath);
        return myFromFile.renameTo(myToFile) ? this.SUCCESS : this.FAILURE;
    }

    public int setReadOnly(String path) {
        File myFile = new File(path);
        return myFile.setReadOnly() ? this.SUCCESS : this.FAILURE;
    }

    public int copy(String fromPath, String toPath) {
        try {
            File myFromFile = new File(fromPath);
            File myToFile = new File(toPath);
            InputStream in = new FileInputStream(myFromFile);
            OutputStream out = new FileOutputStream(myToFile);
            byte[] buf = new byte[1024];

            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
            return this.SUCCESS;
        } catch (Exception var9) {
            return this.FAILURE;
        }
    }
}
