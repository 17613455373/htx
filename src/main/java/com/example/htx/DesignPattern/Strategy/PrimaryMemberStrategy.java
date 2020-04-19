package com.example.htx.DesignPattern.Strategy;

import org.springframework.stereotype.Service;

/**
 * 初级会员折扣类
 */
@Service
public class PrimaryMemberStrategy implements MemberStrategy {


    @Override
    public boolean check(String code) {

        return  PriceCodeEnum.NINE.getPriceCode().equals(code);
    }

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于初级会员的没有折扣");
        return booksPrice*0.9;
    }
}
