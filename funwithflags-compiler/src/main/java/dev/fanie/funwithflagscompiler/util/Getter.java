package dev.fanie.funwithflagscompiler.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

import dev.fanie.funwithflags.FunWithFlags;

public final class Getter {
    private Getter() {
    }

    public static long getFlagIdFrom(Element flag) {
        final FunWithFlags.Flag annotation = flag.getAnnotation(FunWithFlags.Flag.class);
        return annotation.id();
    }

    public static List<? extends Element> filterAnnotatedElements(List<? extends Element> elements, Class<? extends Annotation> annotationClass) {
        final List<Element> annotatedElements = new ArrayList<>();

        for (Element element : elements) {
            if (element.getAnnotation(annotationClass) != null) {
                annotatedElements.add(element);
            }
        }

        return annotatedElements;
    }
}
