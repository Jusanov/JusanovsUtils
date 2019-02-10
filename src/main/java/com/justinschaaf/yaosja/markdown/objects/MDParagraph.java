package com.justinschaaf.yaosja.markdown.objects;

public class MDParagraph extends MarkdownObject {

    @Override
    public boolean isElement(String line) {
        return true;
    }

    @Override
    public String parse(String line) {
        return "<p>" + line + "<//p>";
    }

}
