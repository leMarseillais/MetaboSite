/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;



/**
 *
 * @author davd
 */
public class KFile extends KDir {
    
    private byte[] data;
    private String path;
    private String ext;
    private static Map<String, String[]> authorizedMimes;

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public byte[] getData() {
        return data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static Map<String, String[]> getAuthorizedMimes() {
        if (authorizedMimes == null) {
            authorizedMimes = new HashMap<String, String[]>();
            
            // ajouter : fichiers compressés
            authorizedMimes.put("inode/directory", new String[] {"dir"});
            authorizedMimes.put("image/jpeg", new String[] {"jpg", "jpeg"});
            authorizedMimes.put("image/gif", new String[] {"gif"});
            authorizedMimes.put("image/png", new String[] {"png"});
            authorizedMimes.put("text/plain", new String[] {"txt"});
            authorizedMimes.put("application/pdf",  new String[] {"pdf"});
            authorizedMimes.put("application/x-iso9660-image", new String[] {"iso"});
            authorizedMimes.put("application/x-gzip", new String[] {"tar.gz", "gz"});
            authorizedMimes.put("image/vnd.dwg", new String[] {"dwg"});
            authorizedMimes.put("application/vnd.ms-excel", new String[] {"xls"});
            authorizedMimes.put("application/msword", new String[] {"doc"});
            authorizedMimes.put("application/zip", new String[] {"zip"});
            authorizedMimes.put("application/octet-stream", new String[] {"bin"});
        }
        return Collections.unmodifiableMap(authorizedMimes);
    }
    
    public KFile() {
    }
    
    public KFile(String path, String name, String mime, String ext, byte[] data) 
            throws Exception {
        
        super(name);
        
        if (!getAuthorizedMimes().containsKey(mime)) {
            ResourceBundle rb = Bundle.Err.getBundle();
            throw new Exception(rb.getString("unknowMime") + mime);
        }
        
        this.data = data;
        this.path = path;
        this.mime = mime;
        this.ext = ext;
    }
    
    public static boolean extExists(String ext) {
        for (Iterator<String[]> it = getAuthorizedMimes().values().iterator(); it.hasNext();) {
            String[] exts = it.next();
            for (int i = 0; i < exts.length; i++) {
                if (exts[i].equalsIgnoreCase(ext)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static String findExt(String text) {
        for (Iterator<String[]> it = getAuthorizedMimes().values().iterator(); it.hasNext();) {
            String[] exts = it.next();
            for (int i = 0; i < exts.length; i++) {
                Pattern p = Pattern.compile(exts[i]+"$", Pattern.CASE_INSENSITIVE);
                if (p.matcher(text).find()) {
                    return exts[i];
                }
            }
        }
        
        return null;
    }
    
    public static String findExt(String mime, String text) {
        String[] exts = getAuthorizedMimes().get(mime);
        for (int i = 0; i < exts.length; i++) {
            Pattern p = Pattern.compile(exts[i], Pattern.CASE_INSENSITIVE);
            if (p.matcher(text).find()) {
                return exts[i];
            }
        }
        
        return null;
    }
    
    public static boolean mimeExists(String mime) {
        if (getAuthorizedMimes().containsKey(mime)) {
            return true;
        }
        
        return false;
    }
    
    public static String getAuthorizedMime(String ext) {
        Set<String> mimeKeys = getAuthorizedMimes().keySet();
        for (Iterator<String> itKeys = mimeKeys.iterator(); itKeys.hasNext();) {
            String key = itKeys.next();
            for (String e : getAuthorizedMimes().get(key)) {
                if (e.equals(ext))
                    return key;
            }
        }
        return null;
    }
    
    public static boolean equivalents(String mime, String ext) {
        if (!mimeExists(mime) || !extExists(ext))
            return false;
        else {
            for (String extStr : getAuthorizedMimes().get(mime)) {
                if (extStr.equals(ext))
                    return true;
            }
            return false;
        }
    }
    
    @Override
    public void write(String absolutePath) 
            throws FileNotFoundException, IOException {
        File file = new File(absolutePath);
        
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.write(data);
        } finally {
            fos.close();
        }
    }
    
    public static KFile getKFile(String path) 
            throws FileNotFoundException, IOException {
        
        KFile kf = new KFile();
        
        File f = new File(path);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        
        kf.data = new byte[(int) f.length()];
        bis.read(kf.data);
        
        return kf;
    }
}
