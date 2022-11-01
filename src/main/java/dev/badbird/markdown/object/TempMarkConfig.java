package dev.badbird.markdown.object;

import lombok.Data;

@Data
public class TempMarkConfig {
    private boolean cacheResults = true;
    private boolean parseNestedTemplates = false; // Disabled by default for security reasons
    private boolean useNewParserForNestedTemplates = true;
    private boolean replaceGithubURL = true; // replace github.com with raw.githubusercontent.com
    public TempMarkConfig() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean cacheResults;
        private boolean parseNestedTemplates;
        private boolean useNewParserForNestedTemplates;
        private boolean replaceGithubURL;

        Builder() {
        }

        public Builder cacheResults(boolean cacheResults) {
            this.cacheResults = cacheResults;
            return this;
        }

        public Builder parseNestedTemplates(boolean parseNestedTemplates) {
            this.parseNestedTemplates = parseNestedTemplates;
            return this;
        }

        public Builder useNewParserForNestedTemplates(boolean useNewParserForNestedTemplates) {
            this.useNewParserForNestedTemplates = useNewParserForNestedTemplates;
            return this;
        }

        public Builder replaceGithubURL(boolean replaceGithubURL) {
            this.replaceGithubURL = replaceGithubURL;
            return this;
        }

        public TempMarkConfig build() {
            TempMarkConfig tempMarkConfig = new TempMarkConfig();
            tempMarkConfig.setCacheResults(cacheResults);
            tempMarkConfig.setParseNestedTemplates(parseNestedTemplates);
            tempMarkConfig.setUseNewParserForNestedTemplates(useNewParserForNestedTemplates);
            tempMarkConfig.setReplaceGithubURL(replaceGithubURL);
            return tempMarkConfig;
        }
    }
}
