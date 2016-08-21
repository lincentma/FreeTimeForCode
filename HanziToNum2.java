import java.util.List;
import java.util.Map;

/**
 * 中文数字转换为阿拉伯数字
 * 
 * @author maling
 * 
 */

public class HanziToNum2 {
	private static long result = 0;
	// HashMap
	private static Map<String, Long> unitMap = new java.util.HashMap<String, Long>();
	private static Map<String, Long> numMap = new java.util.HashMap<String, Long>();

	// 字符串分离
	private static String stryi = new String();
	private static String stryiwan = new String();
	private static String stryione = new String();
	private static String strwan = new String();
	private static String strone = new String();

	public static void ChangeChnString(String chnStr) {
		// unit
		unitMap.put("十", 10L);
		unitMap.put("百", 100L);
		unitMap.put("千", 1000L);
		unitMap.put("万", 10000L);
		unitMap.put("亿", 100000000L);
		// num
		numMap.put("零", 0L);
		numMap.put("一", 1L);
		numMap.put("二", 2L);
		numMap.put("三", 3L);
		numMap.put("四", 4L);
		numMap.put("五", 5L);
		numMap.put("六", 6L);
		numMap.put("七", 7L);
		numMap.put("八", 8L);
		numMap.put("九", 9L);

		// 去零
		for (int i = 0; i < chnStr.length(); i++) {
			if ('零' == (chnStr.charAt(i))) {
				chnStr = chnStr.substring(0, i) + chnStr.substring(i + 1);
			}
		}
		// 分切成三部分
		int index = 0;
		boolean yiflag = true;
		boolean yiwanflag = true; //亿字节中存在万
		boolean wanflag = true;
		for (int i = 0; i < chnStr.length(); i++) {
			if ('亿' == (chnStr.charAt(i))) {
				// 存在亿前面也有小节的情况
				stryi = chnStr.substring(0, i);
				if (chnStr.indexOf('亿' + "") > chnStr.indexOf('万' + "")) {
					stryi = chnStr.substring(0, i);
					for (int j = 0; j < stryi.length(); j++) {
						if ('万' == (stryi.charAt(j))) {
							yiwanflag = false;
							stryiwan = stryi.substring(0, j);
							stryione = stryi.substring(j + 1);
						}
					}
				}
				if(yiwanflag){//亿字节中没有万，直接赋值
					stryione = stryi;
				}
				index = i + 1;
				yiflag = false;// 分节完毕
				strone = chnStr.substring(i + 1);

			}
			if ('万' == (chnStr.charAt(i)) && chnStr.indexOf('亿' + "") < chnStr.indexOf('万' + "")) {
				strwan = chnStr.substring(index, i);
				strone = chnStr.substring(i + 1);
				wanflag = false;// 分节完毕
			}
		}
		if (yiflag && wanflag) {// 没有处理
			strone = chnStr;
		}
	}

	// 字符串转换为数字
	public static long chnStrToNum(String str) {
		long strreuslt = 0;
		long value1 = 0;
		long value2 = 0;
		long value3 = 0;
		if (str.isEmpty()) {
			return 0;
		}
		for (int i = 0; i < str.length(); i++) {
			char bit = str.charAt(i);
			// 数字
			if (numMap.containsKey(bit + "")) {
				value1 = numMap.get(bit + "");
				if (i == str.length() - 1) {
					strreuslt += value1;
				}

			}
			// 单位
			else if (unitMap.containsKey(bit + "")) {
				value2 = unitMap.get(bit + "");
				if (value1 == 0 && value2 == 10L) {
					value3 = 1 * value2;
				} else {
					value3 = value1 * value2;
					// 清零避免重复读取
					value1 = 0;
				}
				strreuslt += value3;
			}
		}
		return strreuslt;
	}

	// 组合数字
	public static long ComputeResult(String chnStr) {
		ChangeChnString(chnStr);
		long stryiwanresult = chnStrToNum(stryiwan);
		long stryioneresult = chnStrToNum(stryione);
		long strwanresult = chnStrToNum(strwan);
		long stroneresult = chnStrToNum(strone);
		result = (stryiwanresult + stryioneresult) * 100000000 + strwanresult * 10000 + stroneresult;
		// 重置
		stryi = "";
		strwan = "";
		strone = "";
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> chnList = new java.util.ArrayList<String>();
		chnList.add("一");
		chnList.add("一十五");
		chnList.add("十五");
		chnList.add("二十");
		chnList.add("二十三");
		chnList.add("一百");
		chnList.add("一百零一");
		chnList.add("一百一十");
		chnList.add("一百一十一");
		chnList.add("一千");
		chnList.add("一千零一");
		chnList.add("一千零三十一");
		chnList.add("一万零一");
		chnList.add("一万零二十一");
		chnList.add("一万零三百二十一");
		chnList.add("一万一千三百二十一");
		chnList.add("三千零十五万");
		chnList.add("三千零一十五万");
		chnList.add("三千五百六十八万零一百零一");
		chnList.add("五十亿三千零七十五万零六百二十二");
		chnList.add("十三亿三千零十五万零三百一十二");
		chnList.add("三千零七十八亿三千零十五万零三百一十二");
		chnList.add("一千二百五十八亿");
		chnList.add("一千二百五十八万亿零三千三百二十一");

		for (String chnStr : chnList) {
			System.out.println(chnStr);
			System.out.println(ComputeResult(chnStr));
		}

	}

}
