package com.micro.utils.lang3;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static String PERCENTAGE_PATTERN = "###0.00%";

    public static String[] deleteDuplicat(String[] strings) {
        Set<String> as = new HashSet<String>();
        as.addAll(Arrays.asList(strings));
        return as.toArray(new String[as.size()]);
    }
    
    public static String removeLetter(String stringInfo ){

 	   Pattern p = Pattern.compile("([a-z><=\"]+)");//增加对应的标�?
        Matcher m=p.matcher(stringInfo);

        String first=m.replaceAll(""); //把英文标点符号替换成空，即去掉英文标点符�?
        p=Pattern.compile(" {2,}");//去除多余空格

        m=p.matcher(first);

        String second=m.replaceAll(" ");

        return second;

    }
    public static String convertArrayToString(String[] a){
    	String value="";
    	if(a!=null){
    	if(a.length==1){
    		value=a[0]+"-"+"";
    	}
    	if(a.length>1){
    	for(int i=0;i<a.length;i++){
    		if(i!=(a.length)-1){
    			value=value+a[i]+"-";
    		}
    		else{
    			value=value+a[i];
    		}
    	}}}
    	return value;
    }

    public static String encodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "utf8");
        } catch (UnsupportedEncodingException ex) {
        }
        return s;
    }

    public static String decodeUTF8(String s) {
        try {
            return URLDecoder.decode(s, "utf8");
        } catch (UnsupportedEncodingException ex) {
        }
        return s;
    }

    public static String trim(String text) {
        return text == null ? null : text.trim();
    }

    public static boolean hasText(String text) {
        return text != null && text.trim().length() != 0;
    }

    public static boolean equals(String text1, String text2) {
        return text1 == null && text2 == null || text1 != null && text1.equals(text2);
    }

    public static boolean lengthMoreThan(String text, int length) {
        return text != null && text.length() >= length;
    }

    public static boolean lengthBetween(String text, int minLen, int maxLen) {
        return text != null && text.length() >= minLen && text.length() <= maxLen;
    }

    public static String paddingLeft(String text, char paddingChar, int length) {
//      return org.apache.commons.lang.StringUtils.
        int index = 0;
        if (StringUtils.hasText(text)) {
            index = text.length();
        }
        StringBuilder stringBuilder = new StringBuilder(text == null ? "" : text);
        while (index < length) {
            stringBuilder.insert(0, paddingChar);
            index++;
        }
        return stringBuilder.toString();
    }

    public static boolean contains(String text, String target) {
        if (text == null && target != null) {
            return false;
        }
        if (text == null) {
            return true;
        }
        return target == null || text.contains(target);
    }

    public static boolean isEmpty(String text) {
        return !hasText(text);
    }

    public static String truncate(String text, int maxLength) {
        if (text == null) {
            return null;
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength);
    }

    public static String[] split(String string, String delimit) {
        if (StringUtils.isEmpty(string)) {
            return new String[0];
        }
        String[] strings = string.split(delimit);
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        return strings;

    }

    public static String toString(List<String> entries, String delimit, boolean withQuote) {
        if (entries.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String entry : entries) {
            if (withQuote) {
                entry = entry.replaceAll("'", "''");
                builder.append('\'');
            }
            builder.append(entry);
            if (withQuote) {
                builder.append('\'');
            }
            builder.append(delimit);
        }
        return builder.substring(0, builder.length() - delimit.length());
    }

    public static boolean isNumber(String input) {
        return (isFloat(input) || isDouble(input) || isInteger(input) || isLong(input));
    }

    public static boolean isLong(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (NumberFormatException exe) {
            return false;
        }
    }

    public static boolean isFloat(String input) {
        try {
            float f = Float.parseFloat(input);
            if (Float.isNaN(f) || f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        try {
            double d = Double.parseDouble(input);
            return !(Double.isNaN(d) || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY);
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    public static String formatPerenctage(double percentage) {
        if (percentage == 0) {
            return "0.00%";
        }
        return formatDicimal(percentage, PERCENTAGE_PATTERN);
    }

    public static String formatDicimal(double percentage, String pattern) {
        DecimalFormat f = new DecimalFormat(pattern);
        return f.format(percentage);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return f.format(date);
    }

    public static String formatDicimalWithFractionDigits(double percentage, int minimumFractionDigits, int maximumFractionDigits) {
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(maximumFractionDigits);
        f.setMinimumFractionDigits(minimumFractionDigits);
        return f.format(percentage);
    }

    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a == null) {
            return "";
        }
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }

    public static List<String> splitToList(String input, String delimit) {
        return CollectionUtils.makeList(split(input, delimit));
    }

    public static String htmlEncode(String text) {
        if (text == null) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // encode standard ASCII characters into HTML entities where needed
            if (c < '\200') {
                switch (c) {
                    case '"':
                        builder.append("&quot;");
                        break;
                    case '&':
                        builder.append("&amp;");
                        break;
                    case '<':
                        builder.append("&lt;");
                        break;
                    case '>':
                        builder.append("&gt;");
                        break;
                    case '\n':
                        builder.append("<br>");
                        break;
                    default:
                        builder.append(c);
                }
            } else if (c < '\377') {    // encode 'ugly' characters (ie Word "curvy" quotes etc)
                String hexChars = "0123456789ABCDEF";
                int a = c % 16;
                int b = (c - a) / 16;
                String hex = "" + hexChars.charAt(b) + hexChars.charAt(a);
                builder.append("&#x").append(hex).append(";");
            } else {       //add other characters back in - to handle charactersets other than ascii
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static int getNonLatinCharLength(String text) {
        if (isEmpty(text)) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        String tempStr;
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            tempStr = String.valueOf(text.charAt(i));
            if (tempStr.getBytes().length >= 2) {
                sb.append(tempStr);
                count++;
            }
        }
        return count;
    }

    public static String cleanScript(String content){
    	if(isEmpty(content))
    		return content;
    	content = content.replaceAll("<\\s*script[^>]*((/>)|(>[^>]*/script\\s*>))", "");
    	return content;
    }

    public static String getEmailDomain(String email){
        if(!hasText(email) || email.indexOf("@") == -1)
            return "";
        return email.substring(email.indexOf("@")+1);
    }
    /**
     * 从后开始替换字符串函数
     * 
     * @param String strSource - 源字符串 
     * @param String strFrom要替换的子串
     *  @param String strTo替换为的字符串
     * @return boolean
     */
    public static String replace(String strSource, String strFrom, String strTo) {
        if (strFrom == null || strFrom.equals(""))
            return strSource;

        String strDest = "";
        int intPos;
        int intSourceLen = strSource.length();

        if ((intPos = strSource.lastIndexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;

            intPos = intPos + 1;
            strSource = strSource.substring(intPos, intSourceLen);
        }

        strDest = strDest + strSource;

        return strDest;
    }
    /**
     * 替换所有指定字符串函数
     * 
     * @param String strSource - 源字符串 
     * @param String strFrom要替换的子串
     *  @param String strTo替换为的字符串
     * @return boolean
     */
    public static String replaceAll(String strSource, String strFrom, String strTo) {
        if (strFrom == null || strFrom.equals(""))
            return strSource;

        String strDest = "";
        int statrPos = 0;
        int endPos;
        int strToLen = strTo.length();
        
        while ((endPos = strSource.indexOf(strFrom,statrPos)) != -1) {
            strDest = strDest+strSource.substring(statrPos, endPos);
            strDest = strDest + strTo;
            statrPos = endPos + 1;
            System.out.println("strDest::"+strDest);
        }     
        
        if (statrPos != 0) {
            strDest = strDest+strSource.substring(statrPos, strSource.length());
            return strDest;
        }
               
        return strSource;
    }
    
    /**
     * 字符串中包含字符出现的个数
     * @param args
     */
    public static int strCount(String sourceStr, String findStr){
    	int findCount = 0;
    	int startPosition =0;
    	while(startPosition < sourceStr.length()){
    		int searchStrPosition= sourceStr.indexOf(findStr, startPosition);
    		if(searchStrPosition !=-1){
    			findCount ++;
    			startPosition =searchStrPosition+findStr.length();
    		}else{
    			break;
    		}
    	}
    	return findCount;
    }
    
    /**
     * 字符串sourceStr中包含相同字符findStr，指定第num次出现的位置
     * @param args
     */
    public static int strCountSame(String sourceStr,String findStr,int num){
    	if(strCount(sourceStr, findStr)<num)return -1;
    	int findCount = 0;
    	int startPosition =0;
    	int findNum = 0;
    	int numPosition = -1;//第num个字符 出现的位置
    	
    	while(startPosition < sourceStr.length()){
    		int searchStrPosition= sourceStr.indexOf(findStr, startPosition);
    		if(searchStrPosition !=-1){
    			findCount ++;
    			findNum ++;
    			startPosition =searchStrPosition+findStr.length();
    			if(findNum == num){
    				numPosition = searchStrPosition;
    				break;
    			}
    		}else{
    			break;
    		}
    	}
    	return numPosition;
    	
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }


    public static boolean isMobileNum(String mobiles) {
        String telRegex = "[1][3456789]\\d{9}";
        // "[1]"代表第1位为数字1，"[3456789]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (isEmpty(mobiles)) {
            return false;
        } else
            return mobiles.matches(telRegex);
    }

    public static void main(String[] args) {
//    	String str = "3.4.0129.mt788.2";
//    	int num = StringUtils.strCountSame(str, ".",3);
//    	String strPilt = str.substring(0, num);
//        System.out.println(strPilt);
        String ucode = "\\u61\\u6e\\u6e\\u61";
        System.out.println(unicode2String(ucode));

    }
}
