package HomeWork.Lesson9.VirtualFS;

import java.util.ArrayList;

class ChildDirectory {
    private ChildDirectory parentDirectory;
    private RootDirectory rootDirectory;
    private ArrayList<ChildDirectory> childDirectories;
    private ArrayList<String> files;
    private String directoryName;

    ChildDirectory(RootDirectory rootDirectory, ChildDirectory parentDirectory, String directoryName) {
        this.parentDirectory = parentDirectory;
        this.rootDirectory = rootDirectory;
        this.childDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.directoryName = directoryName;
    }

    public String getFullPath() {
        if (parentDirectory == null) {
            return directoryName;
        } else {
            return parentDirectory.getFullPath() + "\\" + directoryName;
        }
    }

    public void listOnDisplay() {
        for (ChildDirectory child : childDirectories) {
            System.out.println(child.getFullPath());
        }
        for (String file : files) {
            System.out.println(getFullPath() + "\\" + file);
        }
    }

    public ArrayList<ChildDirectory> getChildDirectories() {
        return childDirectories;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public ChildDirectory getParentDirectory() {
        return parentDirectory;
    }

    @Override
    public int hashCode() {
        return childDirectories.hashCode() * files.hashCode() * directoryName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == "string".getClass()) {        // for easy folder search
            String temp = (String) obj;
            if (this.directoryName.equalsIgnoreCase(temp)) {
                return true;
            }
        }
        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            ChildDirectory temp = (ChildDirectory) obj;
            if ( (temp.getDirectoryName().equalsIgnoreCase(this.directoryName)) &&
                  temp.getChildDirectories().equals(this.childDirectories) &&
                  temp.getFiles().equals(this.files) &&
                 (temp.getParentDirectory().equals(this.getParentDirectory())) ) {
                return true;
            }
        }
        return false;
    }
}
