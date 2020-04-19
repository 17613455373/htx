package com.example.htx.DesignPattern.Strategy;

/**
 * 抽象折扣类
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     * @param booksPrice    图书的原价
     * @return    计算出打折后的价格
     */
    double calcPrice(double booksPrice);

    /**
     * 判断哪种折扣
     * @param code
     * @return
     */
    boolean check(String code);
}
