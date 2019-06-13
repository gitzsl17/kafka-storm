package com.act.kafka.proxy.service.impl;

import com.act.kafka.proxy.service.BookFacade;

/**
 * @ClassName BookFacade
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/9 11:00
 * @Version 1.0
 **/
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("新增图书方法111");
    }
}
