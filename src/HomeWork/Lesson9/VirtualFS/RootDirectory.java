package HomeWork.Lesson9.VirtualFS;

import java.io.Serializable;
import java.util.ArrayList;

class RootDirectory implements Serializable {
    private ArrayList<ChildDirectory> childDirectories;
    private ArrayList<String> files;
    private ChildDirectory currentPosition;

    public RootDirectory() {
        this.childDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.currentPosition = null;
    }

    boolean addFile(String name) {
        if (currentPosition == null) {
            if (!files.contains(name)) {
                files.add(name);
                return true;
            } else {
                return false;
            }
        } else {
            if (!currentPosition.getFiles().contains(name)) {
                currentPosition.getFiles().add(name);
                return true;
            } else {
                return false;
            }
        }
    }

    boolean deleteFile(String name) {
        if (currentPosition == null) {
            return files.remove(name);
        } else {
            return currentPosition.getFiles().remove(name);
        }
    }

    boolean addDirectory(String name) {
        if (currentPosition == null) {
            if (!childDirectories.contains(name)) {
                childDirectories.add(new ChildDirectory(this, null, name));
                return true;
            } else {
                return false;
            }
        } else {
            if (!currentPosition.getChildDirectories().contains(name)) {
                currentPosition.getChildDirectories().add(new ChildDirectory(this, null, name));
                return true;
            } else {
                return false;
            }
        }
    }

    boolean deleteDirectory(String name) {
        if (currentPosition == null) {
            return childDirectories.remove(name);
        } else {
            return currentPosition.getChildDirectories().remove(name);
        }
    }

    void getFullPath() {
        currentPosition.getFullPath();
    }


}
