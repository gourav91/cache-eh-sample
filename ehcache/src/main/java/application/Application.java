package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

/**
 * container of api
 * 
 * @author e01155
 *
 */
@SpringBootApplication
@ComponentScan("cache")
@EnableCaching
public class Application extends SpringBootServletInitializer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}

	
	@Bean
	public CacheManager getEhCacheManager(){
	    return  new EhCacheCacheManager(getEhCacheFactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory(){
	    EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
	    factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
	    factoryBean.setShared(true);
	    return factoryBean;
	}

}
