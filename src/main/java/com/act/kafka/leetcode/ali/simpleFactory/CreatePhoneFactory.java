package com.act.kafka.leetcode.ali.simpleFactory;


public class CreatePhoneFactory {

    public Phone createPhoneFactory(String args){
        if(args.equals("android")){
            return new AndroidPhone();
        }else if (args.equals("iphone")){
            return new Iphone();
        }else {
            return null;
        }
    }
}
