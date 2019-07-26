package com.fb.sdclibrary.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * 通过UI的jPath获取属性对应的值
 */

public class GsonParserUtils {
    /**
     * 通过jPath切割
     */
    public static String parseTable(Object object, String[] split) {
        Gson gson = new Gson();
        String info_json = gson.toJson(object);
        if (split != null) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(info_json);
                return parseTable(jsonObject, split);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过jPath切割
     */
    public static String parseTable(String data, String[] split) {
        if (split != null && data != null) {
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(data);
                return parseTable(jsonObject, split);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 通过jPath切割
     */
    public static String parseTable(JSONObject jsonObject, String[] split) {
        if (split != null) {
            for (int j = 0; j < split.length; j++) {
                Object opt = jsonObject.opt(split[j]);
                if (opt instanceof String) {
                    return ((String) opt);
                } else if (opt instanceof Long) {
                    return String.valueOf(opt);
                } else if (opt instanceof Double) {
                    return getData(String.valueOf(opt));
                } else if (opt instanceof JSONObject) {
                    jsonObject = (JSONObject) opt;
                } else if (opt instanceof Float) {
                    return String.valueOf(opt);
                } else if (opt instanceof Boolean) {
                    return String.valueOf(opt);
                } else if (opt instanceof Integer) {
                    return String.valueOf(opt);
                } else if (opt instanceof JSONArray) {
//                    JSONArray array = (JSONArray) opt;
//                    String[] temp = new String[split.length - j - 1];
//                    System.arraycopy(split, j + 1, temp, 0, temp.length);
                    return opt.toString();
                }
                if (j == split.length - 1 && opt != null) {
                    return opt.toString();
                }
            }
        }
        return null;
    }

    public static <T> T parseJson(T t, Object data) {
        if (t instanceof List) {
            List list = (List) t;
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                parseJson(o, data);
            }
        } else if (t instanceof String) {
            return t;
        } else {
            try {
                Object value = getValue(t, "data");
                parseJson(value, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Object value = getValue(t, "jpath");
                Object o = parseJson(value, data);
                if (o instanceof String) {
                    String parseValue = parseTable(data, ((String) o).split("\\."));
                    setValue(t, "content", TextUtils.isEmpty(parseValue) ? "--" : parseValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    private static String parseTable(JSONArray jsonArray, String[] split) {
        String str = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            Object o = jsonArray.opt(i);
            str += parseTable(o.toString(), split);
            if (i != jsonArray.length() - 1) {
                str += ",";
            }
        }
        return str;
    }

    private static String getData(String str) {
        String plainString = new BigDecimal(String.valueOf(str)).toPlainString();
        String[] split = plainString.split("\\.");
        if (split.length > 1) {
            String data = split[1];
            if (data.matches("^[0]*$")) {
                return split[0];
            }
        }
        return plainString;
    }

    private static Object getValue(Object o, String fileName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = o.getClass();
        Field data = aClass.getDeclaredField(fileName);
        data.setAccessible(true);
        return data.get(o);
    }

    private static void setValue(Object o, String fileName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = o.getClass();
        Field data = aClass.getDeclaredField(fileName);
        data.setAccessible(true);
        data.set(o, value);
    }


}
