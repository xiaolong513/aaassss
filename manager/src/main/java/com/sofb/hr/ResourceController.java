package com.sofb.hr;

import com.sofb.BaseController;
import com.sofb.common.ServerResult;
import com.sofb.common.StringUtil;
import com.sofb.convert.ResourceVOConvert;
import com.sofb.enums.ResourceTypeEnum;
import com.sofb.enums.ServerResultCodeEnum;
import com.sofb.enums.SortEnum;
import com.sofb.form.hr.ResourceSearchForm;
import com.sofb.vo.ResourceDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource/*")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(ResourceSearchForm searchForm) {
        List<Resource> roles = resourceService.listByResourceForm(searchForm, searchForm.getPagination(), SortEnum.DESC);

        //数据转换
        List<ResourceDetailVO> voList = ResourceVOConvert.INSTANCE.c2h(roles);

        return new ServerResult().success(searchForm.getPagination().setItems(voList));

    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Object save(Resource resource) {
        if (resource == null) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        if (StringUtil.isEmpty(resource.getResourceName())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "资源名称为空");
        }
        if (StringUtil.isEmpty(resource.getResourceType())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "资源类型为空");
        }
        if (StringUtil.isEmpty(resource.getUrl()) && resource.getResourceType() == ResourceTypeEnum.MENU) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "资源链接为空");
        }
        if (StringUtil.isEmpty(resource.getParentId())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "父链接为空");
        }
        boolean result = resourceService.saveResource(resource);

        return new ServerResult().success(result);
    }


    @RequestMapping(value = "/{id}/disable", method = RequestMethod.POST)
    public Object disable(@PathVariable("id") Long id) {
        if (StringUtil.isEmpty(id)) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }

        boolean result = resourceService.removeById(id);

        return new ServerResult().success(result);
    }


}

