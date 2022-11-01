package dev.badbird.markdown.token.impl;

import dev.badbird.markdown.MarkdownParser;
import dev.badbird.markdown.object.GenericParseException;
import dev.badbird.markdown.object.Import;
import dev.badbird.markdown.object.ParseError;
import dev.badbird.markdown.object.ReplaceWithNothing;
import dev.badbird.markdown.token.Token;

public class ImportToken extends Token {
    @Override
    public String getReplacement(String line, String inside, MarkdownParser parser) throws GenericParseException {
        String lowerInside = inside.toLowerCase();
        if (lowerInside.startsWith("import")) {
            String url = inside.substring(6, inside.indexOf(" as ")).trim();
            String name = inside.substring(inside.indexOf(" as ") + 4).trim();
            if (url.isEmpty() || name.isEmpty()) {
                throw new ParseError("Invalid import syntax! Expected 'import <url> as <name>'");
            }
            if (MarkdownParser.RESERVED_WORDS.contains(name)) {
                throw new ParseError("'" + name + "' is a reserved keyword!");
            }
            Import importObj = new Import(url, name);
            if (parser.IMPORT_REGISTRY.containsKey(name)) {
                throw new ParseError("Import with name '" + name + "' already exists!");
            }
            //System.out.println("Importing '" + url + "' as '" + name + "'");
            parser.IMPORT_REGISTRY.put(name, importObj);
            throw new ReplaceWithNothing();
        }
        return null;
    }
}
