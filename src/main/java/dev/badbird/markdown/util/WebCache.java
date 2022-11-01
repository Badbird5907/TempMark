package dev.badbird.markdown.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.badbird.markdown.MarkdownParser;
import lombok.SneakyThrows;

import java.net.URL;
import java.net.URLStreamHandler;

public class WebCache {
    private final MarkdownParser markdownParser;

    private static CacheLoader<URL, String> cacheLoader;
    private static LoadingCache<URL, String> cache;

    public WebCache(MarkdownParser markdownParser) {
        this.markdownParser = markdownParser;
        if (markdownParser.getConfig().isCacheResults()) {
            cacheLoader = new CacheLoader<URL, String>() {
                @Override
                public String load(URL key) {
                    return WebUtil.getURLContents(key);
                }
            };
            cache = CacheBuilder.newBuilder().build(cacheLoader);
        }
    }

    @SneakyThrows
    public String getResults(String urlStr) {
        URL url = new URL(urlStr);
        if (markdownParser.getConfig().isCacheResults()) {
            return cache.getUnchecked(url);
        }
        return WebUtil.getURLContents(url);
    }
}
