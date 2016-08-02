package cache;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

/**
 * @author e01155
 *
 */
@Component
public class HSCCacheManager {

	private static Logger log = Logger.getLogger(HSCCacheManager.class);

	@Autowired
	private CacheManager cacheManager;

	private final HashMap<String, HSCCache> cacheMap = new HashMap<>();

	/**
	 * @param name
	 * @return
	 */
	public synchronized HSCCache getCacheInstance(String name) {
		if (!cacheMap.containsKey(name))
			cacheMap.put(name, new HSCCache(cacheManager.getCache(name)));
		return cacheMap.get(name);
	}

	/**
	 * Clears the contents of all caches in the CacheManager, but without
	 * removing any caches.
	 */
	public void cleanAll() {
		cacheManager.clearAll();
	}

	/**
	 * if you will pass the null as argument this will return statistics of all
	 * caches present in the cache manager else you can get the specific cache
	 * statistics by passing the cache name
	 */
	public void getAllCacheStatistics(String name) {
		String[] cacheNames = cacheManager.getCacheNames();
		StringBuffer buffer = new StringBuffer();
		for (String cacheName : cacheNames) {
			Cache cache = cacheManager.getCache(cacheName);
			if (name == null || cacheName.equalsIgnoreCase(name)) {
				buffer.append("[" + cacheName + "]");
				StatisticsGateway stats = cache.getStatistics();
				buffer.append("{");
				buffer.append("Put Count-" + stats.cachePutCount() + " , ");
				buffer.append("Hit Count-" + stats.cacheHitCount() + " , ");
				buffer.append("Remove Count-" + stats.cacheRemoveCount() + " , ");
				buffer.append("Eviction Count-" + stats.cacheEvictedCount() + " , ");
				buffer.append("Expired Count-" + stats.cacheExpiredCount() + " , ");
				buffer.append("Size-" + stats.getSize());
				buffer.append("}");
				buffer.append("\n");
			}
		}
		log.info(" CACHE STATISTICS MAP " + "\n" + buffer.toString());
	}

	/**
	 * This will return all cache elements(keys and their values) by passing the
	 * cachename.You can disable the values by just passing the false flag
	 * 
	 */
	public void getCacheDetails(String cacheName, boolean printValue) {
		if (cacheName != null && !cacheName.isEmpty()) {
			Cache cache = cacheManager.getCache(cacheName);
			StringBuffer buffer = new StringBuffer();
			buffer.append("[" + cache.getName() + "]" + "---CACHE DETAILS\n");
			for (Object object : cache.getKeys()) {
				Element element = cache.get(object);
				buffer.append("{");
				buffer.append(element.getObjectKey());
				if (printValue)
					buffer.append(" : " + element.getObjectValue());
				buffer.append("}");
				buffer.append("\n");
			}
			log.info("\n" + buffer.toString());
		} else {
			log.info("NO CACHE DETAILS ARE FOUND FOR CACHE NAME -->" + cacheName);
		}

	}
}
