package cache;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * register jax-rs classes
 * 
 * 
 * @author e01155
 *
 */
@Configuration
public class JersyContext extends ResourceConfig {
	public JersyContext() {
		register(CacheTest.class);
	}
}