package cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author e01155
 *
 */
public class HSCCache {

	private Cache cache;

	public HSCCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * add element into the cache
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * removes element from cache on the basis of key
	 * 
	 * @param key
	 */
	public void remove(Object key) {
		cache.remove(key);
	}

	/**
	 * remove all elements of cache
	 */
	public void clearCache() {
		cache.removeAll();
	}

	/**
	 * add element into the cache
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Object key, Object value, int seconds) {
		Element element = new Element(key, value);
		element.setTimeToLive(seconds);
		cache.put(element);
	}

	/**
	 * get element from cache based on the key
	 * 
	 * @param key
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return (T) element.getObjectValue();

	}

	/**
	 * get element from cache based on the key
	 * 
	 * @param key
	 * @param type
	 * @return
	 * @return
	 */
	public Element get(Object key) {
		Element element = cache.get(key);
		return element;
	}

}
