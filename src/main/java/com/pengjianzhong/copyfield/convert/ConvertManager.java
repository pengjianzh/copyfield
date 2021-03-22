package com.pengjianzhong.copyfield.convert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author pengjianzhong
 * @date 2021/1/6 14:16
 */
public class ConvertManager {

    private ConvertManager() {
    }

    /**
     * Convert实例的持有类
     *
     * @author pengjianzhong
     * @date 2021/1/12 13:42
     **/
    private static class ConvertHolder {

        private static final Map<String, Convert> CONVERT_MAP = new HashMap<>();

        static {
            init();
        }

        /**
         * 初始化所有Convert实例的持有类
         *
         * @author pengjianzhong
         * @date 2021/1/12 13:43
         **/
        private static void init() {
            Properties properties = new Properties();
            try (InputStream inputStream = new FileInputStream(
                    ConvertManager.class.getClassLoader().getResource("convert.properties").getPath())) {
                properties.load(inputStream);
                String convertNames = properties.getProperty("convert");
                if (null == convertNames || convertNames.length() == 0) {
                    return;
                }
                String[] convertNameArr = convertNames.split(",");
                for (String convertName : convertNameArr) {
                    if (convertName.length() != 0) {
                        Convert convert = (Convert) Class.forName(convertName).getConstructor().newInstance();
                        CONVERT_MAP.put(convertName, convert);
                    }
                }
            } catch (Exception e) {
                System.exit(0);
            }
        }

    }

    public static Convert getConvert(String convertName) {
        return ConvertHolder.CONVERT_MAP.get(convertName);
    }
}