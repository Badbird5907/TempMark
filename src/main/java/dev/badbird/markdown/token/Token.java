package dev.badbird.markdown.token;

import dev.badbird.markdown.MarkdownParser;
import dev.badbird.markdown.object.GenericParseException;

public abstract class Token {
    public abstract String getReplacement(String line, String inside, MarkdownParser parser) throws GenericParseException;
}
