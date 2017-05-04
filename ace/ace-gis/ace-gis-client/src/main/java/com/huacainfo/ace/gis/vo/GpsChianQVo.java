/**
 * @Title: GpsChianVo.java
 * @Package org.platform.snail.portal.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package com.huacainfo.ace.gis.vo;
import com.huacainfo.ace.gis.model.GpsChian;

/**
 * @ClassName: GpsChianVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class GpsChianQVo extends GpsChian{
	    private int page;
	    private String name;
	    private String nameSuba;
	    private String nameSubb;
	    private String areaName;
		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNameSuba() {
			return nameSuba;
		}

		public void setNameSuba(String nameSuba) {
			this.nameSuba = nameSuba;
		}

		public String getNameSubb() {
			return nameSubb;
		}

		public void setNameSubb(String nameSubb) {
			this.nameSubb = nameSubb;
		}

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		
	    
}
