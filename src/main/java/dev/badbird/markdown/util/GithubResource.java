package dev.badbird.markdown.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubResource {
    private static final String GITHUB_RAW_BASE = "https://raw.githubusercontent.com/%USER%/%REPO%/%BRANCH%/%PATH%";
    private static final String GITHUB_BASE = "https://github.com/%USER%/%REPO%/%BRANCH%/%PATH%";
    private String owner;
    private String repo;
    private String branch;
    private String dir;
    private String file;

    public String getEffectiveURL(boolean raw) {
        return (raw ? GITHUB_RAW_BASE : GITHUB_BASE)
                .replace("%USER%", owner)
                .replace("%REPO%", repo)
                .replace("%BRANCH%", branch)
                .replace("%PATH%", getPath());
    }

    public String getDir() {
        if (dir.startsWith("/")) dir = dir.substring(1);
        if (dir.endsWith("/")) dir = dir.substring(0, dir.length() - 1);
        return dir;
    }

    public String getPath() {
        return getDir() + "/" + file;
    }

    public String getFullURLForDir() {
        return GITHUB_BASE
                .replace("%USER%", owner)
                .replace("%REPO%", repo)
                .replace("%BRANCH%", branch)
                .replace("%PATH%", getDir());
    }

    public String getFileContents() {
        return WebUtil.getURLContents(getEffectiveURL(true));
    }

    public static GithubResource fromURL(String githubURL) {
        if (githubURL.contains("raw.githubusercontent.com")) {
            String[] split = githubURL.split("/");
            String owner = split[3];
            String repo = split[4];
            String branch = split[5];
            String dir = "";
            for (int i = 6; i < split.length - 1; i++) {
                dir += "/" + split[i];
            }
            String file = split[split.length - 1];
            return new GithubResource(owner, repo, branch, dir, file);
        }
        //https://github.com/Badbird5907/blog/blob/master/content/test/Test.md
        String[] split = githubURL.split("/");
        String owner = split[3];
        String repo = split[4];
        String branch = split[6];
        String dir = "";
        for (int i = 7; i < split.length - 1; i++) {
            dir += "/" + split[i];
        }
        String file = split[split.length - 1];
        return new GithubResource(owner, repo, branch, dir, file);
    }
}
