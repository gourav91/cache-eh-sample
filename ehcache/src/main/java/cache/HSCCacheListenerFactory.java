package cache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * Cache Listener factory class
 * 
 * @author e01155
 *
 */
public class HSCCacheListenerFactory extends CacheEventListenerFactory {

	/* (non-Javadoc)
	 * @see net.sf.ehcache.event.CacheEventListenerFactory#createCacheEventListener(java.util.Properties)
	 */
	@Override
	public CacheEventListener createCacheEventListener(Properties paramProperties) {
		return new HSCCacheListener();
	}

}
