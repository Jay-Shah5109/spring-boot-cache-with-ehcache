package com.javacode.cachemechanism.apiconfig;

import com.javacode.cachemechanism.handler.APIFilter;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class APIConfig extends CachingConfigurerSupport {

    @Bean
    public APIFilter apiFilter() {
        return new APIFilter();
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration tenSecondCache = new CacheConfiguration();
        tenSecondCache.setName("ten-second-cache");
        tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
        tenSecondCache.maxEntriesLocalHeap(1000);
        tenSecondCache.setTimeToLiveSeconds(10);

        CacheConfiguration twentySecondCache = new CacheConfiguration();
        twentySecondCache.setName("twenty-second-cache");
        twentySecondCache.setMemoryStoreEvictionPolicy("LRU");
        twentySecondCache.maxEntriesLocalHeap(1000);
        twentySecondCache.setTimeToLiveSeconds(20);

        CacheConfiguration thirtySecondCache = new CacheConfiguration();
        thirtySecondCache.setName("thirty-second-cache");
        thirtySecondCache.setMemoryStoreEvictionPolicy("LRU");
        thirtySecondCache.maxEntriesLocalHeap(1000);
        thirtySecondCache.setTimeToLiveSeconds(30);

        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.addCache(tenSecondCache);
        configuration.addCache(twentySecondCache);
        configuration.addCache(thirtySecondCache);
        return net.sf.ehcache.CacheManager.newInstance(configuration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
}
