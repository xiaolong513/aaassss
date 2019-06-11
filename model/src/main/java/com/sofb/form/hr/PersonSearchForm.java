package com.sofb.form.hr;

import com.sofb.form.BasePageForm;
import lombok.Data;

@Data
public class PersonSearchForm extends BasePageForm {
    private String userName;

    private String phone;
}
