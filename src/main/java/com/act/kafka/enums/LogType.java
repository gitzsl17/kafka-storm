package com.act.kafka.enums;

public enum LogType implements LongEnum, DescribeType {

    Insert(1, "插入"),
    Delete(2, "删除"),
    Update(3, "更新"),
    Select(4, "查找"),

    Loggin(5, "登录"),
    Logout(6, "退出"),

    None(98, "无"),//不记录日志
    Other(99, "其他"),;
    private final long type;
    private final String desc;

    private LogType(long type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String getDescribe() {
        return desc;
    }

    @Override
    public Long getLongType() {
        return this.type;
    }
}
