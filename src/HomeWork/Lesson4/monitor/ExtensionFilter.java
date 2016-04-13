package HomeWork.Lesson4.monitor;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by andrey on 13.04.16.
 */
class ExtensionFilter implements FilenameFilter {
    private String extension;
    private String type;

    public ExtensionFilter(){
        this.extension = "";
    }

    public ExtensionFilter(String extension){
        this.extension = "." + extension;
        this.type = extension;
    }

    @Override
    public String toString(){
        return type;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
