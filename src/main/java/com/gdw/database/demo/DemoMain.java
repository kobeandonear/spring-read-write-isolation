package com.gdw.database.demo;

import com.gdw.database.demo.entity.People;
import com.gdw.database.demo.service.PeopleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 2020/5/29 - 15:47 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
public class DemoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean.xml");
        PeopleService peopleService = applicationContext.getBean(PeopleService.class);
        People people1 = new People(null,"test", 12);
        peopleService.add(people1);
        try {
            peopleService.search(people1.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        People people2 = new People(null,"test2", 11);
        try {
            peopleService.addAndSearch(people2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
