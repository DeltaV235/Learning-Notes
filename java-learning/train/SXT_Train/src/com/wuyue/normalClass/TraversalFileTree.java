package com.wuyue.normalClass;

import java.io.File;
import java.util.ArrayList;

public class TraversalFileTree {
    private File file;
    private String startPath;
    private int depth;

    public TraversalFileTree(String startPath, int depth) {
        file = new File(startPath);
        this.depth = depth;
        if (!(file.exists() && file.isDirectory())) {
            System.out.println("Please enter a correct path");
            System.exit(1);
        }
    }

    public int traversal() {
        System.out.println(file);
        return traversal(file, 1);
    }

    private int traversal(File file, int currentDepth) {
        int returnCount = 0;
        String delimiter = "|-- ";
        String[] fileTable = file.list();
        if (fileTable == null)
            return 0;
        for (String fileName : fileTable) {
            for (int space = 0; space < (middleIndexOfString(file.toString()) + middleIndexOfString(delimiter) * currentDepth); space++)
                System.out.print(" ");
            System.out.print(delimiter + fileName);
            File tempFile = new File(file + "/" + fileName);
            if (tempFile.isDirectory()) {
                System.out.print("/\n");
                if (currentDepth < this.depth || this.depth < 1)
                    returnCount += traversal(tempFile, currentDepth + 1);
            } else
                System.out.println();
        }
        return fileTable.length + returnCount;
    }

    private int middleIndexOfString(String s) {
        return s.length() / 2;
    }

    public static void main(String[] args) {
        TraversalFileTree traversalFileTree = new TraversalFileTree(args[0], Integer.parseInt(args[1]));
        System.out.println("共找到" + traversalFileTree.traversal() + "个文件");
    }
}

class FileNode {
    private String[] fileTable;
    private String[] fileType;
    private ArrayList<FileNode> fileSonNodes;

    public FileNode(String[] fileTable, String[] fileType, ArrayList<FileNode> fileSonNodes) {
        this.fileTable = fileTable;
        this.fileType = fileType;
        this.fileSonNodes = fileSonNodes;
    }

    public String[] getFileTable() {
        return fileTable;
    }

    public String[] getFileType() {
        return fileType;
    }

    public ArrayList<FileNode> getFileSonNodes() {
        return fileSonNodes;
    }
}
