package com.example.htx.DesignPattern.Strategy;

public enum PriceCodeEnum {
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine");


    /**
     * 会员折扣
     */
    private String priceCode;


    PriceCodeEnum(String priceCode) {
        this.priceCode = priceCode;
    }

    public static PriceCodeEnum getByCode(String code) {
        for (PriceCodeEnum item : PriceCodeEnum.values()) {
            if (item.getPriceCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

}
