package com.sunlands.intl.yingshi.ui.my.bean;


import com.contrarywind.interfaces.IPickerViewData;
import com.sunlands.intl.yingshi.bean.IPickerViewCode;

import java.util.List;

/**
 * Created by W on 2017/4/13.
 */

public class JsonBean implements IPickerViewData,IPickerViewCode {


    /**
     * name : 北京
     * region_id : 2
     * city : [{"name":"北京","region_id":"52","area":[{"name":"东城区","region_id":"500"},{"name":"西城区","region_id":"501"},{"name":"海淀区","region_id":"502"},{"name":"朝阳区","region_id":"503"},{"name":"崇文区","region_id":"504"},{"name":"宣武区","region_id":"505"},{"name":"丰台区","region_id":"506"},{"name":"石景山区","region_id":"507"},{"name":"房山区","region_id":"508"},{"name":"门头沟区","region_id":"509"},{"name":"通州区","region_id":"510"},{"name":"顺义区","region_id":"511"},{"name":"昌平区","region_id":"512"},{"name":"怀柔区","region_id":"513"},{"name":"平谷区","region_id":"514"},{"name":"大兴区","region_id":"515"},{"name":"密云县","region_id":"516"},{"name":"延庆县","region_id":"517"}]}]
     */

    private String name;
    private String region_id;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {

        return name;
    }

    @Override
    public String getPickerViewCode() {

        return region_id;
    }

    public static class CityBean {
        /**
         * name : 北京
         * region_id : 52
         * area : [{"name":"东城区","region_id":"500"},{"name":"西城区","region_id":"501"},{"name":"海淀区","region_id":"502"},{"name":"朝阳区","region_id":"503"},{"name":"崇文区","region_id":"504"},{"name":"宣武区","region_id":"505"},{"name":"丰台区","region_id":"506"},{"name":"石景山区","region_id":"507"},{"name":"房山区","region_id":"508"},{"name":"门头沟区","region_id":"509"},{"name":"通州区","region_id":"510"},{"name":"顺义区","region_id":"511"},{"name":"昌平区","region_id":"512"},{"name":"怀柔区","region_id":"513"},{"name":"平谷区","region_id":"514"},{"name":"大兴区","region_id":"515"},{"name":"密云县","region_id":"516"},{"name":"延庆县","region_id":"517"}]
         */

        private String name;
        private String region_id;
        private List<AreaBean> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public static class AreaBean {
            /**
             * name : 东城区
             * region_id : 500
             */
            private String name;
            private String region_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }
        }
    }
}
