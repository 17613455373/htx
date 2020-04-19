package com.example.htx.DesignPattern.Strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;

@Service
public class PriceImpl implements PriceMatchHandleService, ApplicationContextAware {

    /**
     * 获取MemberStrategy接口所有实现类
     */
    private volatile Collection<MemberStrategy> allImpl;

    private volatile ApplicationContext applicationContext;

    @Override
    public Double importCache(String priceCode,Integer booksPrice) {

        for (MemberStrategy e : allImpl ) {
            if(e.check(priceCode)){
                return e.calcPrice(booksPrice);
            }
        }
        return null;
    }

    /**
     * spring容器启动之后调用此方法
     * @return
     */
    @PostConstruct
    public void getAllImpl(){
        if(allImpl == null) {
            synchronized (this) {
                if(allImpl == null) {
                    allImpl = new LinkedList(this.applicationContext.getBeansOfType(MemberStrategy.class).values());
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }

}
