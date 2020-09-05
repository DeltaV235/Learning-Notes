package com.wuyue.normalClass;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * 遍历指定的目录文件
 *
 * @author DeltaV235
 */
public class TraversalFileTree {
    private File file;
    private int depth;
    private FileNode fileNodeRoot;
    private long usedTime;

    public TraversalFileTree(String startPath, int depth) {
        file = new File(startPath);
        this.depth = depth;
        if (!(file.exists() && file.isDirectory())) {
            System.out.println("Please enter a correct path");
            System.exit(1);
        }
        Date startTime = new Date();
        fileNodeRoot = traversal();
        Date endTime = new Date();
        usedTime = (endTime.getTime() - startTime.getTime());
    }

    /**
     * 调用私有方法traversal(File, int)
     *
     * @return 文件树根节点
     */
    public FileNode traversal() {
        System.out.println(file);
        return traversal(file, 1);
    }

    /**
     * 遍历指定的目录和深度，并返回文件根结点 {@code com.wuyue.normalClass.TraversalFileTree.FileNode}
     *
     * @param file         遍历的位置
     * @param currentDepth 遍历的深度
     * @return 文件树根节点
     */
    private FileNode traversal(File file, int currentDepth) {
        ArrayList<FileNode> fileSonNodes = new ArrayList<>();
        File[] fileTables = file.listFiles();
        if (fileTables == null)
            return new FileNode(file, null, null, new ArrayList<>());
        String[] fileTable = new String[fileTables.length];
        String[] fileType = new String[fileTables.length];
        for (int index = 0; index < fileTables.length; index++) {
            fileTable[index] = fileTables[index].getName();
            fileType[index] = fileTables[index].isDirectory() ? "d" : "f";
            if (fileType[index].equals("d") && (currentDepth < this.depth || this.depth < 1)) {
                FileNode fileSonNode = traversal(fileTables[index], currentDepth + 1);
                fileSonNodes.add(fileSonNode);
            }
        }
        return new FileNode(file, fileTable, fileType, fileSonNodes);
    }

    /**
     * 显示遍历的结果
     *
     * @return 遍历的文件数
     */
    public int showResult() {
        return showResult(this.fileNodeRoot, 1);
    }

    private int showResult(FileNode fileNodeRoot, int currentDepth) {
        int returnCount = 0;
        String delimiter = "|-- ";
        String[] fileTable = fileNodeRoot.getFileTable();
        File file = fileNodeRoot.getFile();
        if (fileTable == null)
            return 0;
        int innerIndex = 0;
        for (int i = 0; i < fileTable.length; i++) {
            String fileName = fileTable[i];
            for (int space = 0; space < (middleIndexOfString(file.toString()) + middleIndexOfString(delimiter) * currentDepth); space++)
                System.out.print(" ");
            System.out.print(delimiter + fileName);
            if (fileNodeRoot.getFileType()[i].equals("d")) {
                System.out.print("/\n");
                if (currentDepth < this.depth || this.depth < 1) {
                    returnCount += showResult(fileNodeRoot.getFileSonNodes().get(innerIndex), currentDepth + 1);
                    innerIndex++;
                }
            } else
                System.out.println();
        }
        return fileTable.length + returnCount;
    }

    public long getUsedTime() {
        return usedTime;
    }

    /**
     * 返回字符串的中位索引值，在该类中用于确定目录的中间位置
     *
     * @param s 输入的字符串
     * @return 字符串中位位置
     */
    private int middleIndexOfString(String s) {
        return s.length() / 2;
    }

    public static void main(String[] args) {
        TraversalFileTree traversalFileTree = new TraversalFileTree(args[0], Integer.parseInt(args[1]));
        System.out.println("\n共找到" + traversalFileTree.showResult() + "个文件");
        System.out.println("\n遍历总用时：" + traversalFileTree.getUsedTime() + " ms");
    }
}

/**
 * 存储文件树的结构
 */
class FileNode {
    File file;
    private String[] fileTable;
    private String[] fileType;
    private ArrayList<FileNode> fileSonNodes;

    public FileNode(File file, String[] fileTable, String[] fileType, ArrayList<FileNode> fileSonNodes) {
        this.file = file;
        this.fileTable = fileTable;
        this.fileType = fileType;
        this.fileSonNodes = fileSonNodes;
    }

    public File getFile() {
        return file;
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
