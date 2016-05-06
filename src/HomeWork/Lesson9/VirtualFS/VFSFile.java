package HomeWork.Lesson9.VirtualFS;

import java.io.Serializable;
import java.util.ArrayList;

class VFSFile implements Serializable {
    private String name;
    private String fullPath;
    private ArrayList<String> data;
    private VFSDirectory parentDirectory;
    private VFSRoot root;

    VFSFile(String name, VFSDirectory parentDirectory, VFSRoot root) {
        this.name = name;
        this.data = new ArrayList<>();
        this.parentDirectory = parentDirectory;
        if (parentDirectory != null) {
            this.fullPath = parentDirectory.getFullPath();
        }
        this.root = root;
    }

    boolean rename(String newName) {
        if (parentDirectory != null) {
            if (parentDirectory.getFile(newName) != null) {
                return false;
            } else {
                this.name = newName;
                return true;
            }
        } else {
            if (root.getFile(newName) != null) {
                return false;
            } else {
                this.name = newName;
                return true;
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getFullPath() {
        if (parentDirectory != null) {
            return parentDirectory.getFullPath() + "\\" + name;
        } else {
            return name;
        }
    }

    public VFSDirectory getParentDirectory() {
        return parentDirectory;
    }

    public ArrayList<String> getData() {
        return data;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            VFSFile temp = (VFSFile) obj;
            if ( (this.name.equalsIgnoreCase(temp.getName())) && (this.data.equals(temp.getData())) ) {
                return true;
            }
        }
        return false;
    }
}
