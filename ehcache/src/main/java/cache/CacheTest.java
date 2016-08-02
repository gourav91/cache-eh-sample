package cache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.hsc.mm.ott.global.vo.catalog.GenericRespVO;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * rest services
 * 
 * @author e01155
 *
 */
@Path("/cache")
public class CacheTest {

	@Autowired
	CacheManager cacheManager;

	@GET
	@Path("home")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Element getHome() {
		Cache cache = cacheManager.getCache("vodcache");
		return cache.get("home");
	}

	@GET
	@Path("epg")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Element getEPG(@QueryParam("tid") Long titleID) {
		Cache cache = cacheManager.getCache("epgcache");
		StringBuffer buffer = new StringBuffer();
		buffer.append("[" + cache.getName() + "]" + "---CACHE DETAILS\n");
		for (Object object : cache.getKeys()) {
			Element element = cache.get(object);
			buffer.append("{");
			buffer.append(element.getObjectKey());
			buffer.append(" : " + element.getObjectValue());
			buffer.append("}");
			buffer.append("\n");
		}
		System.out.println("\n" + buffer.toString());
		return cache.get("epg_1017");
	}

	@GET
	@Path("update")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Element Test() {
		Cache cache = cacheManager.getCache("epgcache");
		Element element = new Element("epg_1017", new GenericRespVO("dsa", "das", "ads", "sda", "asd", "sda", null));
		//cache.remove("epg_1017");
		cache.put(element);
		return cache.get("epg_1017");
	}

}
