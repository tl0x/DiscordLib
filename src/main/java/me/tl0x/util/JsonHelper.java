package me.tl0x.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class JsonHelper {

    public final HashMap<String, Object> map;

    public JsonHelper(HashMap data) {
        if (data == null) {
            this.map = new HashMap<>();
        } else {
            this.map = data;
        }
    }

    public void put(String key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
    }

    public static JsonHelper fromString(String JsonString) {
        HashMap<String, Object> retMap = new Gson().fromJson(JsonString, new TypeToken<HashMap<String, Object>>() {}.getType());

        return new JsonHelper(retMap);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        builder.append("{");

        int i = 0;
        for (Map.Entry<String, Object> entry : entrySet) {
            Object val = entry.getValue();
            builder.append(quote(entry.getKey())).append(":");

            if (val instanceof String) {
                builder.append(quote(String.valueOf(val)));
            } else if (val instanceof Integer) {
                builder.append(Integer.valueOf(String.valueOf(val)));
            } else if (val instanceof Boolean) {
                builder.append("\"" + val.toString() + "\"");
            } else if (val instanceof JsonHelper) {
                builder.append(val.toString());
            }  else if (val instanceof Double) {
                builder.append(quote(Double.toString((Double) val)));
            } else if (val == null) {
                builder.append("\"null\"");
            } else if (Objects.requireNonNull(val.getClass().isArray())) {
                builder.append("[");
                int len = Array.getLength(val);
                for (int j = 0; j < len; j++) {
                    builder.append(Array.get(val, j).toString()).append(j != len - 1 ? "," : "");
                }
                builder.append("]");
            } else {
                builder.append("");
            }

            builder.append(++i == entrySet.size() ? "}" : ",");
        }

        return builder.toString();
    }

    private String quote(String string) {
        return "\"" + string + "\"";
    }
}