package dev.badbird.markdown.object;

import dev.badbird.markdown.util.WebUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Import {
    private String url, name;

    public String getContents() {
        return WebUtil.getURLContents(url);
    }
}
