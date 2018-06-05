package com.fb.sdclibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类，提供一些常用的正则表达式
 */
public class RegexUtils {
    /**
     * 匹配全网IP的正则表达式
     */
    public static final String IP_REGEX = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";

    /**
     * 匹配手机号码的正则表达式
     * <br>支持130——139、150——153、155——159、180、183、185、186、188、189号段
     */
    public static final String PHONE_NUMBER_REGEX = "^1{1}\\d{10}$";

    /**
     * 匹配邮箱的正则表达式
     * <br>"www."可省略不写
     */
    public static final String EMAIL_REGEX = "^(www\\.)?\\w+@\\w+(\\.\\w+)+$";

    /**
     * 匹配汉子的正则表达式，个数限制全部
     */
    public static final String CHINESE_REGEX = "^[\u4e00-\u9f5a]+$";

    /**
     * 匹配汉子、汉字标点的正则表达式，个数限制为一个或多个
     */
    public static final String CONTAIN_CHINESE_REGEX = "[^\\x00-\\xff]";

    /**
     * 匹配正整数的正则表达式，个数限制为一个或多个
     */
    public static final String POSITIVE_INTEGER_REGEX = "^\\d+$";

    /**
     * 匹配身份证号的正则表达式
     */
    public static final String ID_CARD = "^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$";

    /**
     * 包含所有 emoji 的正则表达式
     */
    public static final String EMOJI_all = "(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)";
    /**
     * 匹配邮编的正则表达式
     */
    public static final String ZIP_CODE = "^\\d{6}$";

    /**
     * 匹配URL的正则表达式
     */
    public static final String URL = "^(([hH][tT]{2}[pP][sS]?)|([fF][tT][pP]))\\:\\/\\/[wW]{3}\\.[\\w-]+\\.\\w{2,4}(\\/.*)?$";
    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     * **/
    private static final String CHINA_TELECOM_PATTERN = "(^1(33|53|7[37]|8[019])\\d{8}$)|(^1700\\d{7}$)";

    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709
     * **/
    private static final String CHINA_UNICOM_PATTERN = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^170[7-9]\\d{7}$)";

    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184
     * ,187,188,147,178,1705
     *
     **/
    private static final String CHINA_MOBILE_PATTERN = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";

    /**
     * 仅手机号格式校验
     */
    private static final String PHONE_PATTERN=new StringBuilder(300).append(CHINA_MOBILE_PATTERN)
            .append("|")
            .append(CHINA_TELECOM_PATTERN)
            .append("|")
            .append(CHINA_UNICOM_PATTERN)
            .toString();

    /**
     * 匹配给定的字符串是否是一个邮箱账号，"www."可省略不写
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isEmail(String string) {
        return string.matches(EMAIL_REGEX);
    }

    /**
     * 匹配给定的字符串是否是一个手机号码，支持130——139、150——153、155——159、180、183、185、186、188、189号段
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isMobilePhoneNumber(String string) {
        return string.matches(PHONE_NUMBER_REGEX);
    }

    /**
     * 匹配给定的字符串是否是一个全网IP
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isIp(String string) {
        return string.matches(IP_REGEX);
    }
    /**
     * 匹配emoj表情
     */
    public static boolean isEmoj(String string) {
        return string.matches(EMOJI_all);
    }

    /**
     * 匹配给定的字符串是否全部由汉子组成
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isChinese(String string) {
        return string.matches(CHINESE_REGEX);
    }

    /**
     * 验证给定的字符串是否全部由正整数组成
     *
     * @param string 给定的字符串
     * @return true：是
     */
    public static boolean isPositiveInteger(String string) {
        return string.matches(POSITIVE_INTEGER_REGEX);
    }

    /**
     * 验证给定的字符串是否是身份证号
     * <br>
     * <br>身份证15位编码规则：dddddd yymmdd xx p
     * <br>dddddd：6位地区编码
     * <br>yymmdd：出生年(两位年)月日，如：910215
     * <br>xx：顺序编码，系统产生，无法确定
     * <br>p：性别，奇数为男，偶数为女
     * <br>
     * <br>
     * <br>身份证18位编码规则：dddddd yyyymmdd xxx y
     * <br>dddddd：6位地区编码
     * <br>yyyymmdd：出生年(四位年)月日，如：19910215
     * <br>xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
     * <br>y：校验码，该位数值可通过前17位计算获得
     * <br>前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
     * <br>验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
     * <br>如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
     * <br>i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
     */
    public static boolean isIdCard(String string) {
        return string.matches(ID_CARD);
    }

    /**
     * 验证给定的字符串是否是邮编
     */
    public static boolean isZipCode(String string) {
        return string.matches(ZIP_CODE);
    }

    /**
     * 验证给定的字符串是否是URL，仅支持http、https、ftp
     */
    public static boolean isURL(String string) {
        return string.matches(URL);
    }


    /**
     * 验证密码只能输入字母和数字的特殊字符,这个返回的是过滤之后的字符串
     */
    public static String checkPasWord(String pro) {
        try {
            // 只允许字母、数字和汉字
            String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(pro);
            return m.replaceAll("").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 只能输入字母和汉字 这个返回的是过滤之后的字符串
     */
    public static String checkInputPro(String pro) {
        try {
            String regEx = "[^a-zA-Z\u4E00-\u9FA5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(pro);
            return m.replaceAll("").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验证手机号码的合法性
     * @param phone 手机号码
     * @return
     */
    public static boolean isPhone(String phone){
        return phone==null?false:phone.matches(PHONE_PATTERN);
    }
}
