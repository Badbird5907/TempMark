package dev.badbird.markdown.object;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TempMarkConfig {
    private boolean cacheResults = true;
    private boolean parseNestedTemplates = false; // Disabled by default for security reasons
    public TempMarkConfig() {

    }
}
