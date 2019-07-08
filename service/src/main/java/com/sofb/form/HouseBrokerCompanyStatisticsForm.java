package com.sofb.form;

import com.sofb.enums.CityEnum;
import com.sofb.enums.SourceEnum;
import lombok.Data;

@Data
public class HouseBrokerCompanyStatisticsForm extends BasePageForm {
    /**
     * 城市
     */
    private CityEnum city;

    /**
     * 平台
     */
    private SourceEnum source;


}
