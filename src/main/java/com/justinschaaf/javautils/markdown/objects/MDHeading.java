package com.justinschaaf.javautils.markdown.objects;

public class MDHeading extends MarkdownObject {

    @Override
    public boolean isElement(String line) {

        String w = line.split(" ")[0];
        return w.contains("#") && line.startsWith("#");

    }

    @Override
    public String parse(String line) {

        String[] words = line.split(" ", 2);

        int weight = words[0].length();
        String content = words[1];

        while ((content.charAt(0) + "").equalsIgnoreCase(" ")) content = content.replaceFirst(" ", "");

        return "<h" + weight + ">" + content + "<//h" + weight + ">";

    }

}
