package com.njusc.npm.utils.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class FileUploadTool {
	 private static String encoding = System.getProperty("file.encoding");
	 private static Properties p=new Properties();
		
		static{
			try {
				p.load(FileUploadTool.class.getClassLoader().getResourceAsStream("ftp.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	/**
	 * @param yearMon 年月（201408）
	 * @param filename 上传到服务器上的文件名称（要保证不重复）
	 * @param input 上传文件输入流
	 * @return
	 */
	public static String uploadFile(String year,String month, String filename, InputStream input) {
	    boolean success = false;  
	    String path="/"+year+"/"+month+"/";
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
//	        ftp.connect(p.getProperty("ftp.host"), Integer.parseInt(p.getProperty("ftp.post")));//连接FTP服务器  
	        ftp.connect(p.getProperty("ftp.host"),  Integer.parseInt(p.getProperty("ftp.post")));
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(p.getProperty("ftp.user"),  p.getProperty("ftp.password"));//登录  
	        ftp.setControlEncoding(encoding);

	        // 检验是否连接成功
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return null;  
	        }  
//	        ftp.changeWorkingDirectory(path);  
//	        ftp.storeFile(filename, input);           
	        // 转移工作目录至指定目录下
	       
	        boolean change = ftp.changeWorkingDirectory(path);
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        ftp.enterLocalPassiveMode();
	        if (!change) {
	        	if(!ftp.makeDirectory(path)){
	        		System.out.println("文件夹不存在且创建失败!");
	        	}else{
	        		ftp.changeWorkingDirectory(path);
		        	success = ftp.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
		        	//System.out.println("返回值："+success);
		        	if (success) {
		        		//System.out.println("文件上传成功!");
		        	}
	        	}
	        }else{
	        	success = ftp.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
	        	//System.out.println("返回值："+success);
	        	if (success) {
	        		//System.out.println("文件上传成功!");
	        	}
	        }

	        input.close();  
	        ftp.logout();  
	        
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return path+"/"+filename;  
	}
	
	 /**
	  * @param path
	  * @return function:读取指定目录下的文件名
	  * @throws IOException
	  */
//	 public List<String> getFileList(String path) {
//	  List<String> fileLists = new ArrayList<String>();
//	  // 获得指定目录下所有文件名
//	  FTPFile[] ftpFiles = null;
//	  try {
//	   ftpFiles = ftpClient.listFiles(path);
//	  } catch (IOException e) {
//	   e.printStackTrace();
//	  }
//	  for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
//	   FTPFile file = ftpFiles[i];
//	   if (file.isFile()) {
//	    fileLists.add(file.getName());
//	   }
//	  }
//	  return fileLists;
//	 }

	 /**
	  * 从服务器上读取指定的文件
	  * @param path 文件所在FTP文件路径
	  * @param fileName  读取的文件名称
	  */
	public static InputStream readFile(String Url) throws ParseException {
		InputStream ins = null;
		
		FTPClient ftp = new FTPClient();
		String path="";
		String fileName="";
		try {
			path= Url.substring(0,Url.lastIndexOf('/'));
			fileName= Url.substring(Url.lastIndexOf('/')+1);
			int reply;
			ftp.connect(p.getProperty("ftp.host"), Integer.parseInt(p
					.getProperty("ftp.post")));// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(p.getProperty("ftp.user"), p.getProperty("ftp.password"));// 登录
			ftp.setControlEncoding(encoding);

			// 检验是否连接成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			boolean change = ftp.changeWorkingDirectory(path);
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        ftp.enterLocalPassiveMode();
			if (change) {
		       // 从服务器上读取指定的文件
				ins = ftp.retrieveFileStream(new String(fileName.getBytes(encoding),"iso-8859-1"));
		    }
			
//			if (ins != null) {
//				ins.close();
//			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
//			ftp.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }
		return ins;  
		
	}
	
	/**
	  * 从服务器上读取指定的文件
	  * @param path 文件所在FTP文件路径
	  * @param fileName  response的输出量
	  */
	public static void downLoadFtpFile(String Url,OutputStream outs) throws ParseException {
		InputStream ins = null;
		
		FTPClient ftp = new FTPClient();
		String path="";
		String fileName="";
		try {
			path= Url.substring(0,Url.lastIndexOf('/'));
			fileName= Url.substring(Url.lastIndexOf('/')+1);
			int reply;
			ftp.connect(p.getProperty("ftp.host"), Integer.parseInt(p
					.getProperty("ftp.post")));// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(p.getProperty("ftp.user"), p.getProperty("ftp.password"));// 登录
			ftp.setControlEncoding(encoding);

			// 检验是否连接成功
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			boolean change = ftp.changeWorkingDirectory(path);
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        ftp.enterLocalPassiveMode();
			if (change) {
		       // 从服务器上读取指定的文件
				ins = ftp.retrieveFileStream(new String(fileName.getBytes(encoding),"iso-8859-1"));
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				// 开始向网络传输文件流
				while ((bytesRead = ins.read(buffer, 0, buffer.length)) > 0) {
					outs.write(buffer, 0, bytesRead);
				}
				outs.flush();// 这里一定要调用flush()方法
				ins.close();
				outs.close();
		    }
			
//			if (ins != null) {
//				ins.close();
//			}
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
//			ftp.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }
	}
	
	
	public static void main(String[] args) {
//		File file1 = new File("d://1.txt");
//		File file2 = new File("d://2.txt");
//		OutputStream os = null;
//		try {
//			//将文件上传至FTP服务器
//			// FileInputStream in = new FileInputStream(file1);
//			// uploadFile("/320201/201408/320201A14071002", "测试.txt", in);
//			//	
//			
//			//从FTP服务器上获取文件流
//			InputStream is = readFile("/320201/201408/320201A14071002/测试.txt");
//			os = new FileOutputStream(file2);
//			byte buffer[] = new byte[4 * 1024];
//			while ((is.read(buffer)) != -1) {
//				os.write(buffer);
//			}
//			os.flush();
//			if (is != null) {
//				is.close();
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		} finally {
//			try {
//				os.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("/3208/201408/3202828282/text.doc".substring(0,"/3208/201408/3202828282/text.doc".lastIndexOf("/")));
	}
	
	/**
	 * @param userid 用户id
	 * @param yearMon 年月（201408）
	 * @param filename 上传到服务器上的文件名称（要保证不重复）
	 * @param input 上传文件输入流
	 * @return
	 */
	public static String uploadOneFile(String userid,String yearMon, String filename, InputStream input) {  
	    boolean success = false;  
	    String path="/"+userid+"/"+yearMon;
	    FTPClient ftp = new FTPClient();  
	    String rtnPath = "";
	    try {  
	        int reply;  
//	        ftp.connect(p.getProperty("ftp.host"), Integer.parseInt(p.getProperty("ftp.post")));//连接FTP服务器  
	        ftp.connect(p.getProperty("ftp.host"),  Integer.parseInt(p.getProperty("ftp.post")));
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(p.getProperty("ftp.user"),  p.getProperty("ftp.password"));//登录  
	        ftp.setControlEncoding(encoding);

	        // 检验是否连接成功
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return null;  
	        }  
//	        ftp.changeWorkingDirectory(path);  
//	        ftp.storeFile(filename, input);           
	        // 转移工作目录至指定目录下
	       
	        boolean change = ftp.changeWorkingDirectory(path);
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        ftp.enterLocalPassiveMode();
	        if (!change) {
	        	if(!ftp.makeDirectory(path)){
	        		System.out.println("文件夹不存在且创建失败!");
	        	}else{
	        		ftp.changeWorkingDirectory(path);
		        	success = ftp.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
		        	//System.out.println("返回值："+success);
		        	if (success) {
		        		//System.out.println("文件上传成功!");
		        		rtnPath = path+"/"+filename;  
		        	}
	        	}
	        }else{
	        	success = ftp.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
	        	//System.out.println("返回值："+success);
	        	if (success) {
	        		//System.out.println("文件上传成功!");
	        		rtnPath = path+"/"+filename;  
	        	}
	        }

	        input.close();  
	        ftp.logout();  
	        
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return rtnPath;  
	}
}
