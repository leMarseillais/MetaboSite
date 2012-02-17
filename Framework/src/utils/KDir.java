/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class KDir {
    
    protected String name;
    protected String desc;
    protected String mime;

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public KDir () {
    }
    
    public KDir (String name) {
        this.mime = "inode/directory";
        this.name = name;
    }
    
    public KDir (String name, String desc) {
        this.mime = "inode/directory";
        this.name = name;
        this.desc = desc;
    }
    
    public void write(String absolutePath) 
            throws FileNotFoundException, IOException {
        File file = new File(absolutePath);
        
        file.mkdir();
    }
}
