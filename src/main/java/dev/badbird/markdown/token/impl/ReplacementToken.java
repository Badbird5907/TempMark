package dev.badbird.markdown.token.impl;

import dev.badbird.markdown.MarkdownParser;
import dev.badbird.markdown.object.Import;
import dev.badbird.markdown.object.ParseError;
import dev.badbird.markdown.object.TempMarkConfig;
import dev.badbird.markdown.token.Token;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReplacementToken extends Token {
    private final MarkdownParser parser;
    @Override
    public String getReplacement(String line, String inside, MarkdownParser parser) throws ParseError {
        if (MarkdownParser.RESERVED_WORDS.contains(inside)) {
            throw new ParseError(inside + " is reserved!");
        }
        Import importObj = parser.IMPORT_REGISTRY.get(inside);
        if (importObj == null) {
            throw new ParseError("Import with name '" + inside + "' does not exist!");
        }
        String s = importObj.getContents(parser);
        if (parser.getConfig().isParseNestedTemplates()) {
            MarkdownParser parse;
            if (parser.getConfig().isUseNewParserForNestedTemplates()) {
                parse = new MarkdownParser();
            } else parse = parser;
            s = parse.parse(s);
        }
        return s;
    }
}
