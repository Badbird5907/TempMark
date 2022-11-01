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
}
