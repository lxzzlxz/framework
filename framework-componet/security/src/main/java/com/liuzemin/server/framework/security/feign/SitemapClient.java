package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.model.Sitemap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "system", fallback = SitemapClientFallback.class)
public interface SitemapClient {

    @RequestMapping(value="/sitemap/v1/findUserSitemap", method=RequestMethod.GET)
    List<Sitemap> findUserSitemap();
}

@Component
class SitemapClientFallback implements SitemapClient{

    @Override
    public List<Sitemap> findUserSitemap() {

        return Collections.emptyList();
    }
}
