package HomeWork.Lesson9.VirtualFS;

import java.io.Serializable;
import java.util.ArrayList;

class VFSDirectory implements Serializable {
    private VFSDirectory parentDirectory;
    private VFSRoot rootDirectory;
    private ArrayList<VFSDirectory> childDirectories;
    private ArrayList<VFSFile> files;
    private String name;

    VFSDirectory(VFSRoot rootDirectory, VFSDirectory parentDirectory, String name) {
        this.parentDirectory = parentDirectory;
        this.rootDirectory = rootDirectory;
        this.childDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.name = name;
    }

    public String getFullPath() {
        if (parentDirectory == null) {
            return name;
        } else {
            return parentDirectory.getFullPath() + "\\" + name;
        }
    }

    public void listOnDisplay() {
        for (VFSDirectory child : childDirectories) {
            System.out.println(child.getFullPath());
        }
        for (VFSFile file : files) {
            System.out.println(getFullPath() + "\\" + file.getName());
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

    VFSDirectory getDirectory(String name) {
        for (VFSDirectory child : childDirectories) {
            if (child.getName().equalsIgnoreCase(name)) {
                return child;
            }
        }
        return null;
    }

    void searchFiles(String name, ArrayList<VFSFile> foundFiles) {
        for (VFSDirectory child : childDirectories) {
            child.searchFiles(name, foundFiles);
        }
        for (VFSFile file : files) {
            if (file.getName().equalsIgnoreCase(name)) {
                foundFiles.add(file);
            }
        }
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
