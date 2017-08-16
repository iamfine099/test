import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeJava2 {

	public static void main(String[] args) {

		
		String path = "E:\\CODE\\ilikebusWorkSpace\\bus-v13\\"
				+ "ilikebus-api-bus\\src\\com";
		path = "E:\\CODE\\ilikebusWorkSpace\\bus-v13\\"
				+ "ilikebus-api-cart\\src";
		path = "E:\\CODE\\ilikebusWorkSpace\\ilikebus-api-fs\\src";
		path = "E:\\CODE\\ilikebusWorkSpace\\bus-v13\\"
				+ "ilikebus-api-tickets\\src";
		path = "E:\\CODE\\ilikebusWorkSpace\\bus-v13\\ilikebus-web\\src";
		path = "E:\\CODE\\ilikebusWorkSpace\\ilikebus-nydcexpress\\src";
		
		
		File baseDir = new File(path);
		filterFiles(baseDir);
	}

	private static void filterFiles(File dir) {

		if (!dir.isDirectory()) {
			if (dir.getName().contains(".java") ) {
				System.out.println(dir.getAbsolutePath());
				changeFile(dir);
			}
		} else {
			File[] fs = dir.listFiles();
			for (int i = 0; i < fs.length; i++) {
				filterFiles(fs[i]);
			}
		}
	}

	private static void changeFile(File file) {

		write(file, read4New(file)); // 读取修改文件
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String read4New(File file) {

		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();

		try {
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(new FileReader(file));

			
			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				
				String temp = line;
				
				if(line.contains("com.ilikebus.api_tickets.domain.dao.DaoFactory")) {
					continue;
				}
				
				//  此处根据实际需要修改某些行的内容
				if (line.contains("public ") && !line.contains("public static") 
						&& !line.contains("public class") 
						&& file.getName().contains("Dao.java")) {
					temp = temp.replaceFirst("public ", "public static ");
				}
				
				if (line.contains("sqlClient.")) {
					temp = temp.replaceFirst("sqlClient.", "getSqlMapClient().");
				}
				
				if (line.contains("businesSqlClient.")) {
					temp = temp.replaceFirst("businesSqlClient.", "getSqlMapClient().");
				}
				
				if (line.contains("DaoFactory.get")) {
					temp = temp.replaceFirst("DaoFactory.get", "");
				}
				if (line.contains("Dao()")) {
					temp = temp.replaceFirst("Dao\\(\\)", "Dao");
				}
				
/*				if (line.contains("com.eastern.bizsupport")) {
					buf.append(line.replaceFirst("com.eastern.bizsupport", "com.eastern.bizsupport.payment"));
				}
				// 如果不用修改, 则按原来的内容回写
				else {
					buf.append(line);
				}*/
				buf.append(temp);
				buf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}

		return buf.toString();
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 */
	public static void write(File file, String content) {

		BufferedWriter bw = null;

		try {
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(file));
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e) {
					bw = null;
				}
			}
		}
	}
}
