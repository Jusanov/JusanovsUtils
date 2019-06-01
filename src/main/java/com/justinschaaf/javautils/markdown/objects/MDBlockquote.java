package com.justinschaaf.javautils.markdown.objects;

public class MDBlockquote extends MarkdownObject {

    @Override
    public boolean isElement(String line) {
        return line.startsWith(">");
    }

    @Override
    public String parse(String line) {

        String content = line.replaceFirst(">", "");

        while ((content.charAt(0) + "").equalsIgnoreCase(" ")) content = content.replaceFirst(" ", "");

        return "<blockquote>" + content + "<//blockquote>";

    }

}
