package com.huacainfo.ace.woc.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by HuaCai008 on 2018/4/12.
 */
public class VehicleDataUtil {
    private static final String[] PROVINCE = new String[]{"湘", "鄂", "粤", "赣", "浙", "渝", "黔", "川"};

    private static final String[] ABC = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private static final String[] NUMBERS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final String[] CAR_COLOR = new String[]{"红色", "白色", "黑色", "银灰色", "宝蓝色", "玛瑙绿色"};

    private static final String[] CAR_BRAND = new String[]{"福田FOTON", "江淮汽车JAC", "东风汽车DFM", "解放", "中国重汽CNHTC", "江铃汽车JMC",
            "ISUZU五十铃", "金杯汽车", "长安汽车", "跃进", "五菱", "力帆LIFAN", "陕汽",
            "凯马KAMA", "唐骏T·KNG", "北京汽车", "红岩", "大运DAYUN", "斯堪尼亚SCANLA", "南骏汽车CNJ", "奥驰", "时代汽车"};

    private static final String[] AXLE = new String[]{"2", "3", "4", "6"};

    private static Map<String, Map<String, Object>> AXLE_CONFIG = new HashMap<>();

    static {
        Map<String, Object> micro = new HashMap<>();
        micro.put("axleCount", 2);
        micro.put("vehicleType", "微型货车（GA≤1．8吨）");
        micro.put("totalMass", new BigDecimal(3900));
        micro.put("unladedMass", new BigDecimal(2160));
        micro.put("approvedMass", new BigDecimal(1740));
        micro.put("tractionMass", new BigDecimal(1780));

        Map<String, Object> light = new HashMap<>();
        light.put("axleCount", 3);
        light.put("vehicleType", "轻型货车（1．8吨＜GA≤6吨）");
        light.put("totalMass", new BigDecimal(7760));
        light.put("unladedMass", new BigDecimal(2160));
        light.put("approvedMass", new BigDecimal(5600));
        light.put("tractionMass", new BigDecimal(3180));


        Map<String, Object> middle = new HashMap<>();
        middle.put("axleCount", 4);
        middle.put("vehicleType", "中型货车（6．0吨＜GA≤14吨）");
        middle.put("totalMass", new BigDecimal(16000));
        middle.put("unladedMass", new BigDecimal(16000));
        middle.put("approvedMass", new BigDecimal(12600));
        middle.put("tractionMass", new BigDecimal(4180));

        Map<String, Object> bigger = new HashMap<>();
        bigger.put("axleCount", 6);
        bigger.put("vehicleType", "重型货车（GA＞14吨）");
        bigger.put("totalMass", new BigDecimal(31200));
        bigger.put("unladedMass", new BigDecimal(6160));
        bigger.put("approvedMass", new BigDecimal(22600));
        bigger.put("tractionMass", new BigDecimal(5180));

        AXLE_CONFIG.put("2", micro);
        AXLE_CONFIG.put("3", light);
        AXLE_CONFIG.put("4", middle);
        AXLE_CONFIG.put("6", bigger);
    }


    public static String getRandomData(String[] pool) {
        Random r = new Random();

        return pool[r.nextInt(pool.length)];
    }

    public static String getRandomCarColor() {
        return getRandomData(CAR_COLOR);
    }

    public static String getRandomCarBrand() {
        return getRandomData(CAR_BRAND);
    }

    public static Map<String, Object> getRandomAxle() {
        return AXLE_CONFIG.get(getRandomData(AXLE));
    }

    /**
     * 随机产生车牌号码
     *
     * @return 车牌号
     */
    public static String getRandomPlateNo() {
        Random r = new Random();
        StringBuilder plateNum = new StringBuilder();
        plateNum.append(PROVINCE[r.nextInt(PROVINCE.length)])//湘
                .append(ABC[r.nextInt(ABC.length)]);//J

        int count = 0;
        String tempPlateNum = "";
        while (count < 5) {
            boolean isNumber = r.nextBoolean();
            if (true == isNumber) {
                tempPlateNum += NUMBERS[r.nextInt(NUMBERS.length)];

            } else {
                tempPlateNum += ABC[r.nextInt(ABC.length)];
            }
            count++;
        }

        return plateNum.append(tempPlateNum).toString();

    }
}
