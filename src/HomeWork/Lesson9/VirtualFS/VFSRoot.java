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
            if (getFile(name) == null) {
                files.add(new VFSFile(name, null, this));
                return true;
            } else {
                return false;
            }
        } else {
            if (currentPosition.getFile(name) == null) {
                currentPosition.getFiles().add(new VFSFile(name, currentPosition, null));
                return true;
            } else {
                return false;
            }
        }
    }

    boolean deleteFile(String name) {
        if (currentPosition == null) {
            VFSFile file = getFile(name);
            if (file != null) {
                return files.remove(file);
            } else {
                return false;
            }
        } else {
            VFSFile file = currentPosition.getFile(name);
            if (file != null) {
                return currentPosition.getFiles().remove(file);
            } else {
                return false;
            }
        }
    }

    boolean addDirectory(String name) {
        if (currentPosition == null) {
            if (getChildDirectory(name) == null) {
                childDirectories.add(new VFSDirectory(this, null, name));
                return true;
            } else {
                return false;
            }
        } else {
            if (currentPosition.getChildDirectory(name) == null) {
                currentPosition.getChildDirectories().add(new VFSDirectory(null, currentPosition, name));
                return true;
            } else {
                return false;
            }
        }
    }

    boolean deleteDirectory(String name) {
        if (currentPosition == null) {
            VFSDirectory directory = getChildDirectory(name);
            if (directory != null) {
                return childDirectories.remove(directory);
            } else {
                return false;
            }
        } else {
            VFSDirectory directory = currentPosition.getChildDirectory(name);
            if (directory != null) {
                return currentPosition.getChildDirectories().remove(directory);
            } else {
                return false;
            }
        }
    }

    ArrayList<VFSFile> searchFiles(String name) {
        ArrayList<VFSFile> foundFiles = new ArrayList<>();
        if (currentPosition != null) {
            currentPosition.searchFiles(name, foundFiles);
        } else {
            for (VFSDirectory child : childDirectories) {
                child.searchFiles(name, foundFiles);
            }
            for (VFSFile file : files) {
                if (file.getName().equalsIgnoreCase(name)) {
                    foundFiles.add(file);
                }
            }
        }
        return foundFiles;
    }

    VFSDirectory getChildDirectory(String name) {
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
