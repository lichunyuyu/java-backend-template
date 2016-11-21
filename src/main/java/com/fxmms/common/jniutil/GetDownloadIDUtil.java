package com.fxmms.common.jniutil;

import org.apache.http.util.Asserts;

import java.lang.reflect.Field;

/**
 * @usage JNI调用底层c算法将mac地址转化为downloadid
 */
public class GetDownloadIDUtil {
	static{
			try{
				setLibraryPath("/jni");
				System.loadLibrary("GetDownloadID");
			}catch(Exception e){
				System.err.println("Native code library failed to load.\n" + e);
				System.exit(1);
			}
		}

	public static String getDownLoadId(String mac){
		GetDownloadID test = new GetDownloadID();
		String downLoadId = test.getDownloadID(mac);
		return downLoadId;
	}


	/**
	 * Sets the java library path to the specified path
	 *
	 * @param path the new library path
	 * @throws Exception
	 */
	public static void setLibraryPath(String path) throws Exception {
		System.setProperty("java.library.path", path);
		//set sys_paths to null
		final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
		sysPathsField.setAccessible(true);
		sysPathsField.set(null, null);
	}

	public static void main(String[] args){
		//-Djava.library.path="/Users/mark/mms/src/main/java/com/fxmms/common/jniutil"
		///Users/mark/mms/src/main/java/com/fxmms/common/jniutil
		System.out.println(System.getProperty("java.library.path"));
		String mac = "CC:81:DA:86:42:E7";
		Asserts.check(mac!=null,"mac  null");
		GetDownloadID test = new GetDownloadID();
		System.out.println(test.getDownloadID(mac));
	}
}