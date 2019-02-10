package com.justinschaaf.yaosja.markdown;

import com.justinschaaf.yaosja.markdown.objects.MDHeading;
import com.justinschaaf.yaosja.markdown.objects.MDHorizontalRule;
import com.justinschaaf.yaosja.markdown.objects.MDParagraph;
import com.justinschaaf.yaosja.markdown.objects.MarkdownObject;

import java.util.ArrayList;

public class MarkdownObjects {

    public static ArrayList<MarkdownObject> getMarkdownObjects() {

        ArrayList<MarkdownObject> objects = new ArrayList<MarkdownObject>();

        objects.add(new MDHeading());
        objects.add(new MDHorizontalRule());

        // Add MDParagraph last because it always returns true
        objects.add(new MDParagraph());

        return objects;

    }

}
