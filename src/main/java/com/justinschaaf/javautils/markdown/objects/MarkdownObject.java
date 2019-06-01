package com.justinschaaf.javautils.markdown.objects;

public abstract class MarkdownObject {

    public abstract boolean isElement(String line);

    public abstract String parse(String line);

}
