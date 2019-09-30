package dev.fanie.funwithflagscompiler.util;

import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

public final class Mapper {
    private Mapper() {
    }

    public static Element[] getElementArrayFrom(Set<? extends Element> elementsSet) {
        final Element[] elementsArray = new Element[elementsSet.size()];

        int index = 0;
        for (Element element : elementsSet) {
            elementsArray[index] = element;
            index++;
        }

        return elementsArray;
    }

    public static Element[] getElementArrayFrom(List<? extends Element> elementsSet) {
        final Element[] elementsArray = new Element[elementsSet.size()];

        int index = 0;
        for (Element element : elementsSet) {
            elementsArray[index] = element;
            index++;
        }

        return elementsArray;
    }

    public static TypeElement[] getTypeElementArrayFrom(Set<TypeElement> typeElementsSet) {
        final TypeElement[] typeElementsArray = new TypeElement[typeElementsSet.size()];

        int index = 0;
        for (TypeElement typeElement : typeElementsSet) {
            typeElementsArray[index] = typeElement;
            index++;
        }

        return typeElementsArray;
    }

    public static PropertiesImplementation[] getPropertiesImplementationsArrayFrom(List<PropertiesImplementation> propertiesImplementationsList) {
        return propertiesImplementationsList.toArray(new PropertiesImplementation[0]);
    }

    public static PropertiesImplementation getPropertiesImplementationFrom(TypeElement propertiesClass, Element[] properties) {
        return new PropertiesImplementation();
    }
}
