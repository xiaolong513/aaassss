package com.sofb.voconvert;

import com.sofb.common.CollectionUtil;
import com.sofb.common.StringUtil;
import com.sofb.enums.ResourceTypeEnum;
import com.sofb.hr.Resource;
import com.sofb.vo.MenuResourceVO;

import java.util.*;
import java.util.stream.Collectors;

public class MenuResourceVOConvertUtil {
    /**
     * 初始化最上级菜单是 /
     *
     * @param resourceList
     * @return
     */
    public static List<MenuResourceVO> convertMenuResourceVO(List<Resource> resourceList) {
        List<MenuResourceVO> voList = new ArrayList<>();
        if (CollectionUtil.isEmpty(resourceList)) {
            return voList;
        }

        //key = parentId
        Map<Long, List<Resource>> resourceMapAll = resourceList.stream().collect(Collectors.groupingBy(Resource::getParentId));

        //递归查找子菜单
        List<Resource> resourcesMap = resourceMapAll.get(1L);
        Collections.sort(resourcesMap, Comparator.comparingInt(Resource::getPriority).thenComparing(Resource::getCreateDate));
        for (Resource resource : resourcesMap) {
            List<Resource> subResource = resourceMapAll.get(resource.getId());
            if (resource.getResourceType() == ResourceTypeEnum.BUTTON) {
                continue;
            }
            MenuResourceVO menuResourceVO = new MenuResourceVO();
            menuResourceVO.setName(resource.getResourceName());
            menuResourceVO.setUrl(resource.getUrl());
            addSubMenuResources(menuResourceVO, subResource, resourceMapAll);
            voList.add(menuResourceVO);
        }

        return voList;
    }

    private static void addSubMenuResources(MenuResourceVO menuResourceVO, List<Resource> subResource, Map<Long, List<Resource>> resourceMapAll) {
        if (CollectionUtil.isEmpty(subResource) || StringUtil.isEmpty(menuResourceVO.getName()) || StringUtil.isEmpty(menuResourceVO.getUrl())) {
            return;
        }
        List<MenuResourceVO> voList = new ArrayList<>();
        for (Resource resource : subResource) {
            List<Resource> childSubResource = resourceMapAll.get(resource.getId());
            if (CollectionUtil.isEmpty(childSubResource) && resource.getResourceType() == ResourceTypeEnum.MENU) {
                continue;
            }
            if (!CollectionUtil.isEmpty(childSubResource)) {
                Collections.sort(childSubResource, Comparator.comparingInt(Resource::getPriority).thenComparing(Resource::getCreateDate));
            }
            if (resource.getResourceType() == ResourceTypeEnum.BUTTON) {
                menuResourceVO.addPersmission(convertPersmission(resource.getPermission()));
            } else {
                MenuResourceVO childResourceVO = new MenuResourceVO();
                childResourceVO.setName(resource.getResourceName());
                childResourceVO.setUrl(resource.getUrl());
                addSubMenuResources(childResourceVO, childSubResource, resourceMapAll);
                voList.add(childResourceVO);
            }
        }
        menuResourceVO.setChildMenuResources(voList);
    }

    private static String convertPersmission(String persmission) {
        if (StringUtil.isEmpty(persmission)) {
            return "";
        }
        String reg1 = ".*:(.*)";
        return StringUtil.getMatchesGroup1Text(reg1, persmission, "");
    }

    public static void main(String[] args) {
        System.out.println("convertPersmission:    " + convertPersmission("user:delete"));
    }


}
