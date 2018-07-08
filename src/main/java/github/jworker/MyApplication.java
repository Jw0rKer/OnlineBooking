package github.jworker;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
public class MyApplication extends SpringBootServletInitializer {

  @Value("classpath*:dozer/**/*.xml")
  private Resource[] resourceList;

  @Bean(name = "org.dozer.Mapper")
  public DozerBeanMapper dozerBean() {
    DozerBeanMapper dozerBean = new DozerBeanMapper();
    for (Resource resource : resourceList) {
      try {
        dozerBean.addMapping(resource.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return dozerBean;
  }

  @Bean(name = "org.slf4j.Logger")
  @Scope("prototype")
  public Logger LogbackBean(InjectionPoint injectionPoint) {
    Logger logger = LoggerFactory.getLogger(injectionPoint.getField().getDeclaringClass());
    return logger;
  }

  @Bean
  public ConversionService conversionService() {
    return new DefaultConversionService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
  }
}
