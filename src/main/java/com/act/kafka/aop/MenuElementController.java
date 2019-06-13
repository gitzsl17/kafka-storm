package com.act.kafka.aop;

import com.act.kafka.enums.LogType;


@ControllerLog(format = "系统菜单元素接口")
public class MenuElementController {

    @ControllerLog(format = "根据用户查询菜单元素", type = LogType.Select)
    public void getMenuElementByUser() {
        System.out.println("111");
    }

}
