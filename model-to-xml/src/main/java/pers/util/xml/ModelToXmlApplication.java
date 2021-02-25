package pers.util.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 描述：ModelToXmlApplication
 *
 * @author 归墟
 * @date 2021/2/25 20:14
 */
@SpringBootApplication
public class ModelToXmlApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ModelToXmlApplication.class, args);
    }
}