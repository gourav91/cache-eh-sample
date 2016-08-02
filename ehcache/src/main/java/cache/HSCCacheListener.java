package cache;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * 
 * Logging class of Caching
 * 
 * @author e01155
 *
 */
@Component
public class HSCCacheListener implements CacheEventListener {

	private static Logger log = Logger.getLogger(HSCCacheListener.class);

	public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
		log.info("["+cache.getName()+"]" + "---CACHE ELEMENT REMOVED---->" + element.getObjectKey());
	}

	public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
		log.info("["+cache.getName()+"]" + "---CACHE ELEMENT PUT---->" + element.getObjectKey());
	}

	public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
		log.info(cache.getName() + "---CACHE ELEMENT UPDATED---->" + element.getObjectKey());
	}

	@Override
	public void notifyElementExpired(Ehcache cache, Element element) {
		log.info("["+cache.getName()+"]" + "---CACHE ELEMENT EXPIRED---->" + element.getObjectKey());
	}

	@Override
	public void notifyElementEvicted(Ehcache cache, Element element) {
		log.info("["+cache.getName()+"]" + "---CACHE ELEMENT EVICTED---->" + element.getObjectKey());
	}

	public void notifyRemoveAll(Ehcache cache) {
		log.info("["+cache.getName()+"]" +  "---CACHE ALL ELEMENTS REMOVED---->");
	}

	public void dispose() {
		log.info("---CACHE DISPOSED--->");
	}

	public Object clone() {
		throw new UnsupportedOperationException("NOT SUPPORTED YET.");
	}

}
