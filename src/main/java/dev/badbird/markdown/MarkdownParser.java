package dev.badbird.markdown;

import dev.badbird.markdown.object.GenericParseException;
import dev.badbird.markdown.object.Import;
import dev.badbird.markdown.object.ParseError;
import dev.badbird.markdown.object.ReplaceWithNothing;
import dev.badbird.markdown.token.Token;
import dev.badbird.markdown.token.impl.ImportToken;
import dev.badbird.markdown.token.impl.ReplacementToken;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class MarkdownParser {
    /*
    Our custom markdown looks like this:
    {{import https://url.com/thing.md as thing}}

    # Hi
    Lorem ipsum blah blah
    {{thing}}
    That is a thing

    {\{thing}}
    This shouldn't be replaced as it's escaped.
     */
    public final Map<String, Import> IMPORT_REGISTRY = new HashMap<>();

    // match {{everything}}
    private static final Pattern PATTERN = Pattern.compile("\\{\\{.*}}");

    private final Token[] TOKENS = {
            new ImportToken(), new ReplacementToken()
    };

    public static final List<String> RESERVED_WORDS = Arrays.asList("import", "as");

    public String parse(String in) {
        String[] lines = in.split("\n");
        List<String> newLines = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            // Use newLines
            if (PATTERN.matcher(line).find()) {
                System.err.println("Line matches! " + line);
                String inside = line.substring(2, line.length() - 3);
                if (inside.startsWith("\\")) {
                    newLines.add(line.substring(1));
                    continue;
                }
                for (Token token : TOKENS) {
                    String replacement = null;
                    try {
                        replacement = token.getReplacement(line, inside, this);
                    } catch (ParseError e) {
                        replacement = "Error on line " + (i + 1) + ": " + e.getMessage();
                        System.err.println(replacement);
                    } catch (GenericParseException e) {
                        if (e instanceof ReplaceWithNothing) {
                            break;
                        }
                        e.printStackTrace();
                    }
                    if (replacement != null) {
                        newLines.addAll(Arrays.asList(replacement.split("\n")));
                        break;
                    }
                }
            } else {
                System.err.println("Line doesn't match! " + line);
                newLines.add(line);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String line : newLines) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("test.md");
        String contents = new String(Files.readAllBytes(file.toPath()));
        MarkdownParser parser = new MarkdownParser();
        String parsed = parser.parse(contents);
        System.out.println(parsed);
    }
}
