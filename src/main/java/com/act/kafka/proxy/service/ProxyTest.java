package com.act.kafka.proxy.service;

import com.act.kafka.proxy.service.cglibProxy.CglibProxy;
import com.act.kafka.proxy.service.impl.BookFacadeImpl;
import com.act.kafka.proxy.service.impl.BookFacadeImpl2;
import com.act.kafka.proxy.service.jdkProxy.JdkProxy;

/**
 * @ClassName ProxyTest
 * @Description TODO
 * @Autor Administrator
 * @Date 2019/4/9 11:10
 * @Version 1.0
 **/
public class ProxyTest {
    public static void main(String[] args) {
        BookFacadeImpl bookFacadeImpl = new BookFacadeImpl();
        JdkProxy jdkProxy = new JdkProxy();
        BookFacade bookFacade = (BookFacade) jdkProxy.bind(bookFacadeImpl);
        bookFacade.addBook();

        BookFacadeImpl2 bookFacadeImpl2 = new BookFacadeImpl2();
        CglibProxy cglibProxy = new CglibProxy();
        BookFacadeImpl2 bookFacade2 = (BookFacadeImpl2) cglibProxy.getInstance(bookFacadeImpl2);
        bookFacade2.addBook();

    }
}
