package com.example.htx.DesignPattern.Strategy;

import org.springframework.stereotype.Service;

/**
 * 中级会员折扣类
 */
@Service
public class IntermediateMemberStrategy implements MemberStrategy {


    @Override
    public boolean check(String code) {
        return  PriceCodeEnum.EIGHT.getPriceCode().equals(code);
    }

    @Override
    public double calcPrice(double booksPrice) {

        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.8;
    }
}
