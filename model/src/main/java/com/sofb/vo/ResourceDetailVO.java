package com.sofb.vo;

import lombok.Data;

@Data
public class ResourceDetailVO extends BaseVO {
    private Long id;

    private String resourceName;

    private String resourceType;

    private String url;

    private String permission;

    private String description;

}
