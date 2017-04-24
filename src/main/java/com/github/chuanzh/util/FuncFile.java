package com.github.chuanzh.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

public class FuncFile {
	private static Logger logger = Logger.getLogger(FuncFile.class.getName());

	public static void insertFile(String path, InputStream is)
			throws IOException {
		File file = new File(path);
		OutputStream out = null;
		try {
			try {
				out = openOutputStream(file);
				int ch;
				while ((ch = is.read()) != -1) {
					out.write(ch);
				}
			} catch (IOException e) {
				throw e;
			}

		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 插入文件，如果文件已存在，覆盖原有的。默认UTF-8
	 * 
	 * @param path 文件路径
	 * @param text 内容
	 * @throws IOException 异常
	 */
	public static void insertFile(String path, String text) throws IOException {
		insertFile(path, text, "UTF-8");
	}

	public static void insertFile(String path, String text, String encode)
			throws IOException {
		File file = new File(path);
		OutputStream out = null;
		try {
			try {
				out = openOutputStream(file);
				out.write(text.getBytes(encode));
			} catch (IOException e) {
				throw e;
			}

		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 向文件夹中添加文件.并写入类容。如果文件已经存在，则替换原有文件
	 * @param dirPath 文件夹路径
	 * @param fileName 文件名
	 * @param data 字节流
	 * @throws IOException 异常
	 */
	public static void insertFile(String dirPath, String fileName, byte[] data)
			throws IOException {
		if (dirPath != null && fileName != null && data != null) {

			String path;
			if ((!dirPath.endsWith("/")) || (!dirPath.endsWith("\\"))) {
				path = dirPath + File.separator + fileName;
			} else {
				path = dirPath + fileName;
			}

			File file = new File(path);
			OutputStream out = null;
			try {
				try {
					out = openOutputStream(file);
					out.write(data);
				} catch (IOException e) {
					throw e;
				}

			} finally {
				IOUtils.closeQuietly(out);
			}
		}
	}

	public static void appendFile(String filePath, String content) {
		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, true);
			writer.write(content);
		} catch (IOException e) {
			FuncStatic.errorTrace(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(FuncStatic.errorTrace(e));
				}
			}
		}
	}

	private static FileOutputStream openOutputStream(File file)
			throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file
						+ "' exists but is a directory");
			}
			if (file.canWrite() == false) {
				throw new IOException("File '" + file
						+ "' cannot be written to");
			}
		} else {
			File parent = file.getParentFile();
			if (parent != null && parent.exists() == false) {
				if (parent.mkdirs() == false) {
					throw new IOException("File '" + file
							+ "' could not be created");
				}
			}
		}
		return new FileOutputStream(file);
	}

	/**
	 * 向文件夹中添加文件.并写入类容。如果文件已经存在，则替换原有文件
	 * @param dirPath 文件夹路径
	 * @param fileName 文件名
	 * @param is 文件流
	 * @throws Exception 异常
	 */
	public static void insertFile(String dirPath, String fileName,
			InputStream is) throws Exception {
		if (dirPath != null && fileName != null && is != null) {
			try {
				String path;
				if ((!dirPath.endsWith("/")) || (!dirPath.endsWith("\\"))) {
					path = dirPath + File.separator + fileName;
				} else {
					path = dirPath + fileName;
				}
				File targetFile = new File(path);
				// 新建文件输出流并对它进行缓冲
				FileOutputStream output;

				output = new FileOutputStream(targetFile);

				BufferedOutputStream outBuff = new BufferedOutputStream(output);

				// 缓冲数组
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = is.read(b)) != -1) {
					outBuff.write(b, 0, len);
				}

				// 刷新此缓冲的输出流
				outBuff.flush();
				outBuff.close();
				output.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public static void createDir(String path) {
		if (!FuncFile.isExist(path)) {
			File f = new File(path);
			f.mkdirs();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path 路径
	 * @throws IOException 异常
	 */
	public static void delFile(String path) throws IOException {
		try {
			File file = new File(path);
			if (file.isDirectory()) {
				delDir(file);

			} else {
				if (!file.exists()) {
					throw new FileNotFoundException("File does not exist: "
							+ file);
				}
				if (!file.delete()) {
					String message = "Unable to delete file: " + file;
					throw new IOException(message);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 删除目录
	 * @param path 路径
	 * @throws IOException 异常
	 */
	public static void delDir(String path) throws IOException {
		File file = new File(path);
		delDir(file);
	}

	public static void delDir(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		cleanDirectory(directory);
		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			throw new IOException(message);
		}
	}

	private static void cleanDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			delFile(file.getPath());
		}

		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile 源路径
	 * @param targetFile 新路径
	 * @throws IOException 异常
	 */
	public static void copyFile(String sourceFile, String targetFile)
			throws IOException {
		File f1 = new File(sourceFile);
		if (f1.exists()) {
			File f2 = new File(targetFile);
			copyFile(f1, f2);
		}

	}

	/**
	 * 复制文件
	 * 
	 * @param srcFile 源路径
	 * @param destFile 新路径
	 * @throws IOException 异常
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		copyFile(srcFile, destFile, true);
	}

	private static void copyFile(File srcFile, File destFile,
			boolean preserveFileDate) throws IOException {
		if (srcFile == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destFile == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (srcFile.exists() == false) {
			throw new FileNotFoundException("Source '" + srcFile
					+ "' does not exist");
		}
		if (srcFile.isDirectory()) {
			throw new IOException("Source '" + srcFile
					+ "' exists but is a directory");
		}
		if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
			throw new IOException("Source '" + srcFile + "' and destination '"
					+ destFile + "' are the same");
		}
		if (destFile.getParentFile() != null
				&& destFile.getParentFile().exists() == false) {
			if (destFile.getParentFile().mkdirs() == false) {
				throw new IOException("Destination '" + destFile
						+ "' directory cannot be created");
			}
		}
		if (destFile.exists() && destFile.canWrite() == false) {
			throw new IOException("Destination '" + destFile
					+ "' exists but is read-only");
		}
		doCopyFile(srcFile, destFile, preserveFileDate);
	}

	private static void doCopyFile(File srcFile, File destFile,
			boolean preserveFileDate) throws IOException {
		if (destFile.exists() && destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile
					+ "' exists but is a directory");
		}

		FileInputStream input = new FileInputStream(srcFile);
		try {
			FileOutputStream output = new FileOutputStream(destFile);
			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(output);
			}
		} finally {
			IOUtils.closeQuietly(input);
		}

		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '"
					+ srcFile + "' to '" + destFile + "'");
		}
		if (preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param sourceDir 源路径
	 * @param targetDir 新路径
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) {
		File src = new File(sourceDir);
		File tar = new File(targetDir);
		try {
			copyDirectory(src, tar);
		} catch (IOException e) {
			logger.error(FuncStatic.errorTrace(e));
		}
	}

	public static void copyDirectory(File srcDir, File destDir)
			throws IOException {
		copyDirectory(srcDir, destDir, true);
	}

	public static void copyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) throws IOException {
		if (srcDir == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destDir == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (srcDir.exists() == false) {
			throw new FileNotFoundException("Source '" + srcDir
					+ "' does not exist");
		}
		if (srcDir.isDirectory() == false) {
			throw new IOException("Source '" + srcDir
					+ "' exists but is not a directory");
		}
		if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
			throw new IOException("Source '" + srcDir + "' and destination '"
					+ destDir + "' are the same");
		}
		doCopyDirectory(srcDir, destDir, preserveFileDate);
	}

	private static void doCopyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) throws IOException {
		if (destDir.exists()) {
			if (destDir.isDirectory() == false) {
				throw new IOException("Destination '" + destDir
						+ "' exists but is not a directory");
			}
		} else {
			if (destDir.mkdirs() == false) {
				throw new IOException("Destination '" + destDir
						+ "' directory cannot be created");
			}
			if (preserveFileDate) {
				destDir.setLastModified(srcDir.lastModified());
			}
		}
		if (destDir.canWrite() == false) {
			throw new IOException("Destination '" + destDir
					+ "' cannot be written to");
		}
		// recurse
		File[] files = srcDir.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + srcDir);
		}
		for (int i = 0; i < files.length; i++) {
			File copiedFile = new File(destDir, files[i].getName());
			if (files[i].isDirectory()) {
				doCopyDirectory(files[i], copiedFile, preserveFileDate);
			} else {
				doCopyFile(files[i], copiedFile, preserveFileDate);
			}
		}
	}

	/**
	 * 查找目录名中所有文件(包括子目录)
	 * @param path 目录名
	 * @param regx 文件名匹配的表达式(注，不需要匹配文件路径)
	 * @return 文件集合
	 * @throws Exception 异常
	 */
	public static List<String> searchFile(String path, String regx)
			throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			Pattern patten = Pattern.compile(regx);
			list = search(path, list, patten);
			return list;
		} catch (Exception e) {
			throw e;
		}

	}

	private static List<String> search(String path, List<String> list,
			Pattern patten) throws Exception {
		try {
			File file = new File(path);
			if (!file.isDirectory()) {
				if (file.exists()) {
					Matcher ma = patten.matcher(file.getName());
					if (ma.find()) {
						list.add(file.getPath());
					}
				}
			} else {
				String[] filelist = file.list();
				if (filelist != null && filelist.length > 0) {
					for (int i = 0; i < filelist.length; i++) {
						File fi = new File(path + "/" + filelist[i]);
						if (!fi.isDirectory()) {
							Matcher ma = patten.matcher(fi.getName());
							if (ma.find()) {
								list.add(fi.getPath());
							}
						} else {
							search(path + "/" + filelist[i], list, patten);
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	public static String readResourceText(String resourceName, String encode)
			throws Exception {
		InputStream is = null;
		if (encode == null) {
			encode = "UTF-8";
		}
		try {
			is = FuncFile.class.getResourceAsStream("/" + resourceName);
			String txt = FuncFile.readText(is, encode);
			return txt;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * 读取文件内容(默认UTF-8)
	 * @param filePath 文件路径
	 * @return 内容
	 * @throws Exception 异常
	 */
	public static String readText(String filePath) throws Exception {
		return readText(filePath, "UTF-8");
	}

	public static String readText(InputStream fis, String encode)
			throws Exception {
		try {
			InputStreamReader is = new InputStreamReader(fis, encode);
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(is);
			String line = reader.readLine(); // 读取第一行
			while (line != null) { // 如果 line 为空说明读完了
				buffer.append(line); // 将读到的内容添加到 buffer 中
				buffer.append("\n"); // 添加换行符
				line = reader.readLine(); // 读取下一行
			}

			reader.close();
			is.close();
			return buffer.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	public static String readText(InputStream fis) throws Exception {
		return readText(fis, "UTF-8");
	}

	public static String readTextNode(String content, String node) {
		Pattern p = Pattern.compile(FuncStatic.format(
				"<!--{0} start-->([\\s\\S]*?)<!--{0} end-->", node));
		Matcher m = p.matcher(content);
		if (m.find()) {
			return m.group(1);
		} else {
			logger.info("节点:" + node + " 没有找到！");
			return "";
		}
	}

	/**
	 * 读取文件内容
	 * @param filePath 文件路径
	 * @param encode 编码
	 * @return 内容
	 * @throws Exception 异常
	 */
	public static String readText(String filePath, String encode)
			throws Exception {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		String content = readText(fis, encode);
		fis.close();
		return content;
	}

	public static boolean isExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	public static boolean isExist(String dirPath, String filePath) {
		if (!dirPath.endsWith("/"))
			dirPath = dirPath + "/";
		File file = new File(dirPath + filePath);
		return file.exists();
	}

	public static Set<Class<?>> getClasses(Package pack) throws Exception {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack.getName();
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class
													.forName(packageName + '.'
															+ className));
										} catch (ClassNotFoundException e) {
											logger.error("添加用户自定义视图类错误 找不到此类的.class文件");
											throw e;
										}
									}
								}
							}
						}
					} catch (IOException e) {
						logger.error("在扫描用户定义视图时从jar包获取文件出错");
						throw e;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}

		return classes;
	}

	/**
	 * 获取某个包路径下的类集合(包括子目录)
	 * 
	 * @param path 路径
	 * @return 类集合
	 */
	public static Set<Class<?>> getClasses(String path)   {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(path.replace('.', '/'));
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findAndAddClassesInPackageByFile(path, filePath, recursive,
							classes);
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return classes;
	}

	private static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes)
			 {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));

				} catch (ClassNotFoundException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	public static String readFileToBase64(String path) throws IOException {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(path);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			throw e;
		}
		// 对字节数组Base64编码
		if (data != null) {
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		} else {
			return null;
		}
	}

	public static void changeFileNameInDirectory(File file, String newName) {
		String c = file.getParent();
		File mm = new File(c + "/" + newName);
		file.renameTo(mm); // 改名
	}
}
