package com.example.htx.DesignPattern.Strategy;

import org.springframework.stereotype.Service;

/**
 * 高级会员折扣类
 */
@Service
public class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public boolean check(String code) {

        return  PriceCodeEnum.SEVEN.getPriceCode().equals(code);
    }

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.7;
    }

}
