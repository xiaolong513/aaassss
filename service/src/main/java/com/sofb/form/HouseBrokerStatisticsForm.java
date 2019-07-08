package com.sofb.form;

import com.sofb.enums.CityEnum;
import com.sofb.enums.HouseStatisticsDataType;
import lombok.Data;

@Data
public class HouseBrokerStatisticsForm extends BasePageForm {

    /**
     * 城市
     */
    private CityEnum city;

    /**
     * 哪种数据
     */
    private HouseStatisticsDataType dataType;


}
