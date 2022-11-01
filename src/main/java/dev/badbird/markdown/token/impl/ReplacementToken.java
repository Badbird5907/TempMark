package dev.badbird.markdown.token.impl;

import dev.badbird.markdown.MarkdownParser;
import dev.badbird.markdown.object.Import;
import dev.badbird.markdown.object.ParseError;
import dev.badbird.markdown.token.Token;

public class ReplacementToken extends Token {
    @Override
    public String getReplacement(String line, String inside, MarkdownParser parser) throws ParseError {
        if (MarkdownParser.RESERVED_WORDS.contains(inside)) {
            throw new ParseError(inside + " is reserved!");
        }
        Import importObj = parser.IMPORT_REGISTRY.get(inside);
        if (importObj == null) {
            throw new ParseError("Import with name '" + inside + "' does not exist!");
        }
        return importObj.getContents();
    }
}
