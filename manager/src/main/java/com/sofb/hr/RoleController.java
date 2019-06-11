package com.sofb.hr;

import com.sofb.BaseController;
import com.sofb.common.ServerResult;
import com.sofb.common.StringUtil;
import com.sofb.convert.RoleVOConvert;
import com.sofb.enums.ServerResultCodeEnum;
import com.sofb.enums.SortEnum;
import com.sofb.form.hr.RoleResourceForm;
import com.sofb.form.hr.RoleSearchForm;
import com.sofb.vo.RoleDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role/*")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDealService roleDealService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(RoleSearchForm searchForm) {
        List<Role> roles = roleService.listByRoleForm(searchForm, searchForm.getPagination(), SortEnum.DESC);

        //数据转换
        List<RoleDetailVO> voList = RoleVOConvert.INSTANCE.c2h(roles);

        return new ServerResult().success(voList);

    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Object save(Role role) {
        if (role == null) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        if (StringUtil.isEmpty(role.getRoleName())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "用户名为空");
        }
        boolean result = roleService.saveRole(role);

        return new ServerResult().success(result);
    }


    @RequestMapping(value = "/{id}/disable", method = RequestMethod.POST)
    public Object disable(@PathVariable("id") String id) {
        if (StringUtil.isEmpty(id)) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }

        boolean result = roleDealService.removeRoleById(id);

        return new ServerResult().success(result);
    }


    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Object addRole(RoleResourceForm roleResourceForm) {
        if (roleResourceForm == null || StringUtil.isEmpty(roleResourceForm.getId()) || StringUtil.isEmpty(roleResourceForm.getResourceIds())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        boolean result = roleDealService.addResource(roleResourceForm.getId(), roleResourceForm.getResourceIds());
        return new ServerResult().success(result);
    }

}

