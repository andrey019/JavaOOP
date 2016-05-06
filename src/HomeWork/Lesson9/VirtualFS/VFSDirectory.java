package HomeWork.Lesson9.VirtualFS;

import java.io.Serializable;
import java.util.ArrayList;

class VFSDirectory implements Serializable {
    private VFSDirectory parentDirectory;
    private VFSRoot root;
    private ArrayList<VFSDirectory> childDirectories;
    private ArrayList<VFSFile> files;
    private String name;

    VFSDirectory(VFSRoot root, VFSDirectory parentDirectory, String name) {
        this.parentDirectory = parentDirectory;
        this.root = root;
        this.childDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.name = name;
    }

    boolean rename(String newName) {
        if (parentDirectory != null) {
            if (parentDirectory.getChildDirectory(newName) != null) {
                return false;
            } else {
                this.name = newName;
                return true;
            }
        } else {
            if (root.getChildDirectory(newName) != null) {
                return false;
            } else {
                this.name = newName;
                return true;
            }
        }
    }

    void searchFiles(String name, ArrayList<VFSFile> foundFiles) {
        if ( (childDirectories != null) && (!childDirectories.isEmpty()) ) {
            for (VFSDirectory child : childDirectories) {
                child.searchFiles(name, foundFiles);
            }
        }
        if ( (files != null) && (!files.isEmpty()) ) {
            for (VFSFile file : files) {
                if (file.getName().equalsIgnoreCase(name)) {
                    foundFiles.add(file);
                }
            }
        }
    }

    public String getFullPath() {
        if (parentDirectory == null) {
            return name;
        } else {
            return parentDirectory.getFullPath() + "\\" + name;
        }
    }

    VFSFile getFile(String name) {
        for (VFSFile file : files) {
            if (file.getName().equalsIgnoreCase(name)) {
                return file;
            }
        }
        return null;
    }

    VFSDirectory getChildDirectory(String name) {
        for (VFSDirectory child : childDirectories) {
            if (child.getName().equalsIgnoreCase(name)) {
                return child;
            }
        }
        return null;
    }

    public ArrayList<VFSDirectory> getChildDirectories() {
        return childDirectories;
    }

    public ArrayList<VFSFile> getFiles() {
        return files;
    }

    public String getName() {
        return name;
    }

    public VFSDirectory getParentDirectory() {
        return parentDirectory;
    }

    @Override
    public int hashCode() {
        return childDirectories.hashCode() * files.hashCode() * name.hashCode();
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
            VFSDirectory temp = (VFSDirectory) obj;
            if ( (temp.getName().equalsIgnoreCase(this.name)) &&
                    temp.getChildDirectories().equals(this.childDirectories) &&
                    temp.getFiles().equals(this.files) &&
                    (temp.getParentDirectory().equals(this.getParentDirectory())) ) {
                return true;
            }
        }
        return false;
    }
}
