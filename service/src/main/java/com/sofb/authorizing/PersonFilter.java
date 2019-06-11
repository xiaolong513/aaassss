package com.sofb.authorizing;

import com.sofb.common.SessionPerson;
import com.sofb.hr.PersonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class PersonFilter extends PathMatchingFilter {

    @Autowired
    private PersonService personService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SessionPerson.set(personService.getByUserName(username));
        return true;
    }
}
