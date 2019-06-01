package com.justinschaaf.javautils.markdown;

import com.justinschaaf.javautils.markdown.objects.MarkdownObject;

import java.util.ArrayList;

public class Markdown {

    public String parse(String text) {

        ArrayList<MarkdownObject> objects = MarkdownObjects.getMarkdownObjects();

        String parsed = "";

        int aLevel = 0; // Asterisks
        int uLevel = 0; // Underlines

        String[] lines = text.split("\n");

        for (String line : lines) {

            String[] words = line.split(" ");
            String pL = "";

            for (MarkdownObject obj : objects) {
                if (obj.isElement(line)) {
                    pL = obj.parse(line);
                    break;
                }
            }

            parsed += pL + "\n";

        }

        return parsed;

    }

}
