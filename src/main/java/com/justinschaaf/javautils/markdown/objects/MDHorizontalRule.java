package com.justinschaaf.javautils.markdown.objects;

public class MDHorizontalRule extends MarkdownObject {

    @Override
    public boolean isElement(String line) {

        String w = line.split(" ")[0];
        return (w.equalsIgnoreCase("---") || w.equalsIgnoreCase("***")) && line.trim().length() == 1;

    }

    @Override
    public String parse(String line) {
        return "<hr>";
    }

}
