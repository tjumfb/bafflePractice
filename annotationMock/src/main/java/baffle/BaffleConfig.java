package baffle;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BaffleConfig {
    @Bean(name = "baffle")
    public Logging baffle(){
        return new Logging();
    }
    @Bean(name = "configProperties")
    public PropertiesFactoryBean configProperties(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("mvc-config.xml");
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(ctx.getResource("classpath:baffle.properties"));
        return propertiesFactoryBean;
    }
}
