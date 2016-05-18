package HomeWork.Lesson12.Task1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class ServerStatus {
    private File serverListFile;
    private File statusReportFile;
    private HashMap<String, String> serverStatusMap;

    ServerStatus(String serverListLocation, String statusReportLocation) throws IOException {
        this.serverListFile = new File(serverListLocation);
        this.statusReportFile = new File(statusReportLocation);
        if (!serverListFile.exists()) {
            throw new IOException();
        }
        if (!statusReportFile.exists()) {
            statusReportFile.createNewFile();
        }
        this.serverStatusMap = new HashMap<>();
    }

    private boolean getServerList() {
        try {
            RandomAccessFile servers = new RandomAccessFile(serverListFile, "r");
            while (servers.getFilePointer() != servers.length()) {
                serverStatusMap.put(servers.readLine(), null);
            }
            servers.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void getStatus() {
        Socket socket;
        for (String key : serverStatusMap.keySet()) {
            try {
                socket = new Socket(key, 80);
                serverStatusMap.put(key, "good");
            } catch (IOException e) {
                serverStatusMap.put(key, "bad");
            }
        }
    }

    private boolean writeStatusToFile() {
        try {
            PrintWriter statusFile = new PrintWriter(statusReportFile);
            for (Map.Entry<String, String> entry : serverStatusMap.entrySet()) {
                statusFile.write(entry.getKey() + " - " + entry.getValue() + "\r\n");
            }
            statusFile.flush();
            statusFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void start() {
        System.out.println("Working...");
        if (getServerList()) {
            getStatus();
            if (writeStatusToFile()) {
                System.out.println("Successful!");
            } else {
                System.out.println("Failed to write status file!");
            }
        } else {
            System.out.println("Something's wrong with server list file!");
        }
    }

    public static void main(String[] args) {
        try {
            ServerStatus serverStatus = new ServerStatus("c:\\test\\serverList.txt" , "c:\\test\\serverStatus.txt");
            serverStatus.start();
        } catch (IOException e) {
            System.out.println("Server list file not found!");
        }
    }
}
