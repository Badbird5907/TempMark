package dev.badbird.markdown.object;

import lombok.Data;

@Data
public class TempMarkConfig {
    private boolean cacheResults = true;
    private boolean parseNestedTemplates = false; // Disabled by default for security reasons
    private boolean useNewParserForNestedTemplates = true;
    public TempMarkConfig() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean cacheResults;
        private boolean parseNestedTemplates;
        private boolean useNewParserForNestedTemplates;

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

        public TempMarkConfig build() {
            TempMarkConfig tempMarkConfig = new TempMarkConfig();
            tempMarkConfig.setCacheResults(cacheResults);
            tempMarkConfig.setParseNestedTemplates(parseNestedTemplates);
            tempMarkConfig.setUseNewParserForNestedTemplates(useNewParserForNestedTemplates);
            return tempMarkConfig;
        }

        public String toString() {
            return "TempMarkConfig.TempMarkConfigBuilder(cacheResults=" + this.cacheResults + ", parseNestedTemplates=" + this.parseNestedTemplates + ", useNewParserForNestedTemplates=" + this.useNewParserForNestedTemplates + ")";
        }
    }
}
