package org.demo.base.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * 说明：生成UUID(32位不重复的字符串)
 * 作者：BONY
 *
 */
public class UuidUtil {

	public static String get32UUID() {
//		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		String uuid = SnowflakeIdWorker.getUUID();
		return uuid;
	}
//
//	public static void main(String[] args) {
//		System.out.println(get32UUID());
//	} 
	/**
	 * 1ms一个  1s1000个，线程安全
	 * @param orderLx
	 * @return
	 */
	public static String getUuidByLx(String lx) {
		return lx + FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
	}
}
