package com.sofb.convertutil;

import com.alibaba.fastjson.JSON;
import com.sofb.BaseTest;
import com.sofb.common.Pagination;
import com.sofb.enums.SortEnum;
import com.sofb.form.hr.ResourceSearchForm;
import com.sofb.hr.Resource;
import com.sofb.hr.ResourceService;
import com.sofb.voconvert.MenuResourceVOConvertUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuResourceVOConvertUtilTest extends BaseTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void test_convertMenuResourceVO() {
        ResourceSearchForm form = new ResourceSearchForm();
        Pagination pagination = new Pagination();
        List<Resource> resourceList = resourceService.listByResourceForm(form, pagination, SortEnum.DESC);

        System.out.println("resourceList：" + JSON.toJSONString(MenuResourceVOConvertUtil.convertMenuResourceVO(resourceList)));
    }
}
