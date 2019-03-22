package com.huacainfo.ace.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: Arvin
 * @Date: 2019/1/19 10:38
 * @Description:
 */
public class MapKit {

    private static Logger logger = LoggerFactory.getLogger(MapKit.class);

    private static final double PI = 3.14159265;
    private static final double EARTH_RADIUS = 6378137;
    private static final double RAD = Math.PI / 180.0;

    private MapKit() {

    }


    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     *
     * @param radius 单位米
     * @return minLat[0], minLng[1], maxLat[2], maxLng[3]
     */
    public static double[] getAround(double lat, double lng, int radius) {

        Double latitude = lat;
        Double longitude = lng;

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
     * @param p1 经度1,纬度1
     * @param p2 经度2,纬度2
     * @return 两点间距离，单位为米
     */
    public static double getDistance(Point p1, Point p2) {
        double radLat1 = p1.getLat() * RAD;
        double radLat2 = p2.getLat() * RAD;
        double a = radLat1 - radLat2;
        double b = (p1.getLng() - p2.getLng()) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args) {
        Point p1 = new Point(29.047335, 111.598359);
        Point p2 = new Point(29.015656, 111.729777);
        System.out.println(getDistance(p1, p2));
    }

    /**
     * 坐标转换，腾讯地图转换成百度地图坐标
     *
     * @param lat 腾讯纬度
     * @param lng 腾讯经度
     * @return 返回结果：经度,纬度
     */

    public static double[] map_tx2bd(double lat, double lng) {
        double bd_lat;
        double bd_lon;
        double x_pi = 3.14159265358979324;
        double x = lng, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        bd_lon = z * Math.cos(theta) + 0.0065;
        bd_lat = z * Math.sin(theta) + 0.006;
        MapKit.logger.info("tx:{} {}=>bd:{} {}", lat, lng, bd_lat, bd_lon);

        return new double[]{bd_lat, bd_lon};
    }


    /**
     * 坐标转换，百度地图坐标转换成腾讯地图坐标
     *
     * @param lat 百度坐标纬度
     * @param lng 百度坐标经度
     * @return 返回结果：纬度,经度
     */
    public static double[] map_bd2tx(double lat, double lng) {
        double tx_lat;
        double tx_lon;
        double x_pi = 3.14159265358979324;
        double x = lng - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        tx_lon = z * Math.cos(theta);
        tx_lat = z * Math.sin(theta);
        return new double[]{tx_lat, tx_lon};
    }


    public static class Point {
        private double lat;//纬度
        private double lng;//经度

        public Point(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}