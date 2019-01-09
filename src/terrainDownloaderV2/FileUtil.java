package terrainDownloaderV2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {
	private static final String Donloadbase = "/data/DownLoad/";

	public static boolean unGzipFile(File sourcedir, File f) {
		boolean ret = false;
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				ret = true;
			}
			if (ret == true)
				return false;
		}
		try {
			FileInputStream fin = new FileInputStream(sourcedir);
			GZIPInputStream gzin = new GZIPInputStream(fin);
			FileOutputStream fout = new FileOutputStream(f);
			int num;
			byte[] buf = new byte[1024];
			while ((num = gzin.read(buf, 0, buf.length)) != -1) {
				fout.write(buf, 0, num);
			}
			gzin.close();
			fout.close();
			fin.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.toString());
			// e.printStackTrace();
		}
		return false;
	}

	public static void unZip(File zfile) throws IOException {
		String Parent = zfile.getParent() + File.separator;
		FileInputStream fis = new FileInputStream(zfile);
		ZipInputStream zis = new ZipInputStream(fis);
		ZipEntry entry = null;
		BufferedOutputStream bos = null;
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				File filePath = new File(Parent + entry.getName());
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
			} else {
				FileOutputStream fos = new FileOutputStream(Parent + entry.getName());
				bos = new BufferedOutputStream(fos);
				byte buf[] = new byte[1024];
				int len;
				while ((len = zis.read(buf)) != -1) {
					bos.write(buf, 0, len);
				}
				zis.closeEntry();
				bos.close();
			}
		}
		zis.close();
	}

	public static int index(String path) {
		int ret = -1;
		for (int i = path.length() - 1 - 5; i > 0; i--) {
			if (path.charAt(i) == File.separatorChar) {
				return i;
			}
		}
		return ret;
	}

	public static void cutFile(File file1, File file2) {
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		byte[] bytes = new byte[1024];
		int temp = 0;
		try {
			inputStream = new FileInputStream(file1);
			fileOutputStream = new FileOutputStream(file2);
			while ((temp = inputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, temp);
				fileOutputStream.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println(e.toString());
					// e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					System.err.println(e.toString());
					// e.printStackTrace();
				}
			}
		}

	}

	public static void moveFile(String path, String File) {
		File a = new File(Donloadbase + File);
		File b = new File(path + File);
		File c = new File(path);
		if (a.exists()) {
			if (!b.exists()) {
				try {
					c.mkdirs();
					b.createNewFile();
				} catch (IOException e) {
					System.err.println(e.toString());
					// e.printStackTrace();
				}
			}
			cutFile(a, b);
			a.delete();
		}
	}
}
