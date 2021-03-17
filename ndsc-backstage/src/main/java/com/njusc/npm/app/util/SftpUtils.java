package com.njusc.npm.app.util;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class SftpUtils {
    public static ResourceBundle config = ResourceBundle.getBundle("weChat/weChatFtp");
    private ChannelSftp sftp;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * 私钥
     */
    private String privateKeyPath;
    /**
     * SFTP 服务器地址IP地址
     */
    private String host;
    /**
     * SFTP 端口
     */
    private int port;

    private String passphrase;

    /**
     * 构造基于密码认证的sftp对象
     */
    public SftpUtils(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        login();
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SftpUtils(String username, String host, int port, String privateKeyPath,String passphrase) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKeyPath = privateKeyPath;
        this.passphrase=passphrase;
        login();
    }

    public SftpUtils() {
        this.username = config.getString("sftp.username");
        this.host = config.getString("sftp.host");
        this.port = Integer.parseInt(config.getString("sftp.port"));
        this.passphrase=config.getString("sftp.passphrase");
        this.privateKeyPath=SftpUtils.class.getResource("/weChat/wechat_ftp_rsa").getPath();
        login();
    }


    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKeyPath != null) {
                try {
                    jsch.addIdentity(privateKeyPath,passphrase);// 设置私钥
                } catch (JSchException e) {
                    e.printStackTrace();
                }
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }


    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     */
    public boolean upload(String directory, String sftpFileName, InputStream input){
        boolean b=false;
    	try {

            mkdirs(directory);//判断目录是否存在，不存在则创建含子目录
            sftp.put(input, sftpFileName,ChannelSftp.OVERWRITE);  //上传文件
            b=true;
        } catch (SftpException e) {
        	e.printStackTrace();
           b=false;
        }
    	return b;
    }
    
    private boolean isExistDir(String directory) {
    	SftpATTRS sftpATTRS;
    	boolean b=false;
		try {
			sftpATTRS = sftp.lstat(directory);
			b=sftpATTRS.isDir();
		} catch (SftpException e) {
			b=false;
		}
    	return b;
    }

    /**SFTP不支持生成嵌套目录，所以只能一级一级生成
     * @param dirpath
     * @return
     */
    private boolean mkdirs(String dirpath) {
        try {
            if (isExistDir(dirpath)) {
                sftp.cd(dirpath);
                return true;
            } else {
                String pathArry[] = dirpath.split("\\/");
                StringBuffer filePath = new StringBuffer("/");
                for (String path : pathArry) {
                    if ("".equals(path)) {
                        continue;
                    }
                    filePath.append(path + "/");
                    if (isExistDir(filePath.toString())) {
                        sftp.cd(filePath.toString());
                    } else {//目录不存在则创建新目录
                        sftp.mkdir(filePath.toString());
                        sftp.cd(filePath.toString());
                    }
                }
                sftp.cd(dirpath);
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 下载文件。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) {
        System.out.println("download:" + directory + " downloadFile:" + downloadFile + " saveFile:" + saveFile);
        
        File file = null;
        try {
        	
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (SftpException e) {
            e.printStackTrace();
            if (file != null) {
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (file != null) {
                file.delete();
            }
        }

    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
    	
    	if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        byte[] fileData = IOUtils.toByteArray(is);

        return fileData;
    }


    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException {
    
    	if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        sftp.rm(deleteFile);
    }


    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    public boolean isExistsFile(String directory, final String fileName) {

        final List<String> findFilelist = new ArrayList();
        ChannelSftp.LsEntrySelector selector = new ChannelSftp.LsEntrySelector() {
            @Override
            public int select(ChannelSftp.LsEntry lsEntry) {
                if (lsEntry.getFilename().equals(fileName)) {
                    findFilelist.add(fileName);
                }
                return 0;
            }
        };

        try {
            sftp.ls(directory, selector);
        } catch (SftpException e) {
            e.printStackTrace();
        }

        if (findFilelist.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {

        try {
            SftpUtils sftp=new SftpUtils();
            sftp.upload("/2020/03",System.currentTimeMillis()+"aaaaaa.png",new FileInputStream("f:/1.png"));
           // sftp.download("/2020/03", "1584604842068aaaaaa.png", "f:/2.png");
            sftp.logout();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行结束。。。。。。");
    }
}
