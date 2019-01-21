package com.huacainfo.ace.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Arvin
 * @Date: 2019/1/19 10:38
 * @Description:
 */
public class LatLonUtil {

    private static final double PI = 3.14159265;
    private static final double EARTH_RADIUS = 6378137;
    private static final double RAD = Math.PI / 180.0;
    private static Logger logger = LoggerFactory.getLogger(LatLonUtil.class);


    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     *
     * @param radius 单位米
     * @return minLat[0], minLng[1], maxLat[2], maxLng[3]
     */
    public static double[] getAround(double lat, double lon, int radius) {

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double radiusMile = radius;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * radiusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * radiusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;

        return new double[]{minLat, minLng, maxLat, maxLng};
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1 经度1
     * @param lat1 纬度1
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return 两点间距离，单位为米
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        Double lat1 = 34.264648;
        Double lon1 = 108.952736;

        int radius = 50;
        //[34.25566276027792,108.94186385411045,34.27363323972208,108.96360814588955]
        getAround(lat1, lon1, radius);

        //911717.0   34.264648,108.952736,39.904549,116.407288
        double dis = getDistance(108.952736, 34.264648, 116.407288, 39.904549);
        System.out.println(dis);
    }

    /**
     * 坐标转换，腾讯地图转换成百度地图坐标
     *
     * @param lat 腾讯纬度
     * @param lon 腾讯经度
     * @return 返回结果：经度,纬度
     */

    public static double[] map_tx2bd(double lat, double lon) {
        double bd_lat;
        double bd_lon;
        double x_pi = 3.14159265358979324;
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        bd_lon = z * Math.cos(theta) + 0.0065;
        bd_lat = z * Math.sin(theta) + 0.006;
        LatLonUtil.logger.info("tx:{tx_lat} {tx_lon}=>bd:{bd_lat} {bd_lon}",lat,lon,bd_lat,bd_lon);

        return new double[]{bd_lat, bd_lon};
    }


    /**
     * 坐标转换，百度地图坐标转换成腾讯地图坐标
     *
     * @param lat 百度坐标纬度
     * @param lon 百度坐标经度
     * @return 返回结果：纬度,经度
     */
    public static double[] map_bd2tx(double lat, double lon) {
        double tx_lat;
        double tx_lon;
        double x_pi = 3.14159265358979324;
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        tx_lon = z * Math.cos(theta);
        tx_lat = z * Math.sin(theta);
        return new double[]{tx_lat, tx_lon};
    }


}