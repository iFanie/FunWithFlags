package dev.fanie.funwithflagscompiler.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflags.FunWithFlags;

public final class Getter {
    private Getter() {
    }

    public static String getPackage(TypeElement typeElement) {
        return typeElement.getEnclosingElement().toString();
    }

    public static String getClassName(TypeElement typeElement) {
        return typeElement.getSimpleName().toString();
    }

    public static String getType(Element element) {
        return element.asType().toString().split("\\)")[1];
    }

    public static String getName(Element element) {
        return element.getSimpleName().toString();
    }

    public static String getStaticName(Element element) {
        final TypeElement parent = (TypeElement) element.getEnclosingElement();
        final String packageName = getPackage(parent);
        final String className = getClassName(parent);
        final String name = element.getSimpleName().toString();

        return packageName + "." + className + "." + name;
    }

    public static String[] getFlags(Element element, Map<Long, Element> flags) {
        final FunWithFlags.Property annotation = element.getAnnotation(FunWithFlags.Property.class);
        final List<String> values = new ArrayList<>();

        for (long id : annotation.ids()) {
            final Element flag = flags.get(id);

            if (flag != null) {
                values.add(getStaticName(flag));
            }
        }

        return values.toArray(new String[0]);
    }

    public static String getFallback(Element element) {
        final FunWithFlags.Property annotation = element.getAnnotation(FunWithFlags.Property.class);
        return String.valueOf(annotation.fallback());
    }

    public static long getFlagIdFrom(Element flag) {
        final FunWithFlags.Flag annotation = flag.getAnnotation(FunWithFlags.Flag.class);
        return annotation.id();
    }

    public static boolean isSingleton(TypeElement propertiesClass) {
        final FunWithFlags.Properties annotation = propertiesClass.getAnnotation(FunWithFlags.Properties.class);
        return annotation.isSingleton();
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
