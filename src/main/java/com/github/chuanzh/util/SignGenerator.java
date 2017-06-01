package com.github.chuanzh.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SignGenerator {

	public static String getSign(Map<String, String> map, String appSecret) {
		Map<String, String> treeMap = new TreeMap(map);
		StringBuffer sb = new StringBuffer();
		sb.append(appSecret);
		Collection<String> values = treeMap.values();
		for (String value : values) {
			sb.append(value);
		}
		sb.append(appSecret);
		String str = sb.toString();

		return FuncEncrypt.getMD5String(str).toUpperCase();
	}

	public static String getOldSign(Map<String, String> paramValues, List<String> ignoreParamNames, String appSecret)
			throws IOException {
		return FuncEncrypt.sign(paramValues, ignoreParamNames, appSecret);
	}

	public static String getOldSign(Map<String, String> map, Map<String, String> filterParam, String appSecret)
			throws IOException {
		String method = (String) map.get("method");
		String filterParamStr = (String) filterParam.get(method);

		List<String> fpList = null;
		if ((filterParamStr != null) && (!"".equals(filterParamStr))) {
			fpList = Arrays.asList(filterParamStr.split(","));
		}
		return FuncEncrypt.sign(map, fpList, appSecret);
	}
}
