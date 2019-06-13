package com.sofb.hr;

import com.sofb.BaseController;
import com.sofb.common.ServerResult;
import com.sofb.common.StringUtil;
import com.sofb.enums.ServerResultCodeEnum;
import com.sofb.enums.SortEnum;
import com.sofb.form.hr.PersonRoleForm;
import com.sofb.form.hr.PersonSearchForm;
import com.sofb.vo.PersonDetailVO;
import com.sofb.voconvert.PersonVOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/person/*")
public class PersonController extends BaseController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonDealService personDealService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(PersonSearchForm form) {
        List<Person> personList = personService.listByPersonForm(form, form.getPagination(), SortEnum.DESC);

        //数据转换
        List<PersonDetailVO> voList = PersonVOConvertUtil.p2v(personList);

        return new ServerResult().success(form.getPagination().setItems(voList));
    }

    @RequestMapping(value = "createPerson", method = RequestMethod.POST)
    //@Transactional
    public Object createPerson(@ModelAttribute Person person) {
        if (person == null) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        if (StringUtil.isEmpty(person.getUserName())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "用户名为空");
        }

        if (StringUtil.isEmpty(person.getPhone())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "手机号为空");
        }

        if (StringUtil.isEmpty(person.getNumber())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "工号为空");
        }

        boolean result;
        try {
            result = personService.savePerson(person);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResult().error(ServerResultCodeEnum.C0001, e.getMessage());
        }

        return new ServerResult().success(result);
    }

    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
    public Object changePassword(@PathVariable("id") String id, String newPassword) {
        if (StringUtil.isEmpty(id)) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }

        if (StringUtil.isEmpty(newPassword)) {
            return new ServerResult().error(ServerResultCodeEnum.C0008, "新密码不能为空");
        }
        boolean result = personService.changePassword(id, newPassword);
        return new ServerResult().success(result);
    }

    @RequestMapping(value = "/{id}/disable", method = RequestMethod.POST)
    public Object disable(@PathVariable("id") String id) {
        if (StringUtil.isEmpty(id)) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        boolean result = personDealService.removePersonById(id);
        return new ServerResult().success(result);
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Object addRole(@ModelAttribute PersonRoleForm personRoleForm) {
        if (personRoleForm == null || StringUtil.isEmpty(personRoleForm.getId()) || StringUtil.isEmpty(personRoleForm.getRoleIds())) {
            return new ServerResult().error(ServerResultCodeEnum.C0008);
        }
        boolean result = personDealService.addRole(personRoleForm.getId(), personRoleForm.getRoleIds());
        return new ServerResult().success(result);
    }
}
