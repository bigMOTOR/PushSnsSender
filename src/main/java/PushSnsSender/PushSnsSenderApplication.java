package PushSnsSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by bigMOTOR on 17/06/15.
 */

@SpringBootApplication      //- this is equivalent to using @Configuration (Configuration class search Beans and etc..) - @EnableAutoConfiguration and @ComponentScan

public class PushSnsSenderApplication {

    public static void main(String[] args) {

        //transfers control to the SpringApplication class
        SpringApplication.run(PushSnsSenderApplication.class, args);

    }
    
}
