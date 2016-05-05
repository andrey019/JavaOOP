package HomeWork.Lesson9.VirtualFS;

import java.io.Serializable;
import java.util.ArrayList;

class VFSRoot implements Serializable {
    private ArrayList<VFSDirectory> childDirectories;
    private ArrayList<VFSFile> files;
    private VFSDirectory currentPosition;

    VFSRoot() {
        this.childDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
        this.currentPosition = null;
    }

    boolean addFile(String name) {
        if (currentPosition == null) {
            if (!files.contains(name)) {
                files.add(new VFSFile(name, null));
                return true;
            } else {
                return false;
            }
        } else {
            if (!currentPosition.getFiles().contains(name)) {
                currentPosition.getFiles().add(new VFSFile(name, currentPosition));
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
                childDirectories.add(new VFSDirectory(this, null, name));
                return true;
            } else {
                return false;
            }
        } else {
            if (!currentPosition.getChildDirectories().contains(name)) {
                currentPosition.getChildDirectories().add(new VFSDirectory(this, null, name));
                return true;
            } else {
                return false;
            }
        }
    }

    boolean deleteDirectory(String name) {
        VFSDirectory targetDirectory = getChildDirectory(name);
        if (targetDirectory == null) {
            return false;
        } else {
            if (currentPosition == null) {
                childDirectories.remove(targetDirectory);
                return true;
            } else {
                currentPosition.getChildDirectories().remove(targetDirectory);
                return true;
            }
        }
    }

    private VFSDirectory getChildDirectory(String name) {
        if (currentPosition == null) {
            for (VFSDirectory child : childDirectories) {
                if (child.getName().equalsIgnoreCase(name)) {
                    return child;
                }
            }
        } else {
            for (VFSDirectory child : currentPosition.getChildDirectories()) {
                if (child.getName().equalsIgnoreCase(name)) {
                    return child;
                }
            }
        }
        return null;
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

    ArrayList<VFSFile> searchFiles(String name) {
        ArrayList<VFSFile> foundFiles = new ArrayList<>();
        currentPosition.searchFiles(name, foundFiles);
        return foundFiles;
    }

    void getFullPath() {
        currentPosition.getFullPath();
    }

    public VFSDirectory getCurrentPosition() {
        return currentPosition;
    }

    public ArrayList<VFSDirectory> getChildDirectories() {
        return childDirectories;
    }

    public void setCurrentPosition(VFSDirectory currentPosition) {
        this.currentPosition = currentPosition;
    }

    public ArrayList<VFSFile> getFiles() {
        return files;
    }
}
