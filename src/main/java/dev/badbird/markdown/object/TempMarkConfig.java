package dev.badbird.markdown.object;

import lombok.Data;

@Data
public class TempMarkConfig {
    private boolean cacheResults = true;
    private boolean parseNestedTemplates = false; // Disabled by default for security reasons
    private boolean useNewParserForNestedTemplates = true;
    private boolean replaceGithubURL = true; // replace github.com with raw.githubusercontent.com
    private Callback urlMutator = null;
    public TempMarkConfig() {
    }

    public TempMarkConfig setCacheResults(boolean cacheResults) {
        this.cacheResults = cacheResults;
        return this;
    }

    public TempMarkConfig setParseNestedTemplates(boolean parseNestedTemplates) {
        this.parseNestedTemplates = parseNestedTemplates;
        return this;
    }

    public TempMarkConfig setUseNewParserForNestedTemplates(boolean useNewParserForNestedTemplates) {
        this.useNewParserForNestedTemplates = useNewParserForNestedTemplates;
        return this;
    }

    public TempMarkConfig setReplaceGithubURL(boolean replaceGithubURL) {
        this.replaceGithubURL = replaceGithubURL;
        return this;
    }

    public TempMarkConfig setUrlMutator(Callback urlMutator) {
        this.urlMutator = urlMutator;
        return this;
    }

    public static interface Callback {
        String mutate(String url);
    }

}
