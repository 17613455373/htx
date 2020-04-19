package com.example.htx.DesignPattern.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 策略模式
 */
@RestController
@RequestMapping("/strategy")
public class MainController {

    @Autowired
    PriceMatchHandleService priceMatchHandleService;

    /**
     *
     * @param priceCode 折扣信息
     * @param booksPrice 价格
     * @return
     */
    @RequestMapping("/get")
    public  Double get(String priceCode,Integer booksPrice){
        Double d = priceMatchHandleService.importCache(priceCode,booksPrice);
        return d;
    }
}
