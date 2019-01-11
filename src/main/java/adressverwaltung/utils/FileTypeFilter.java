/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressverwaltung.utils;

import adressverwaltung.enums.FileTypeEnum;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Christof Weickhardt
 */
public class FileTypeFilter extends FileFilter{

    FileTypeEnum fileType;

    public FileTypeFilter(FileTypeEnum fileType) {
        this.fileType = fileType;
    }
    
    @Override
    public boolean accept(File f) {
        return FilenameUtils.getExtension(f.getName()).equalsIgnoreCase(fileType.getExtension());
    }

    @Override
    public String getDescription() {
        return fileType.getDescription()+" file (*"+getExtension()+")";
    }
    
    public String getExtension(){
        return fileType.getExtension();
    }
    
    public FileTypeEnum getFileType(){
        return fileType;
    }
    
}
