package dev.fanie.funwithflagscompiler.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;
import dev.fanie.funwithflagscompiler.entity.Property;

import static dev.fanie.funwithflagscompiler.util.Getter.getClassName;
import static dev.fanie.funwithflagscompiler.util.Getter.getFallback;
import static dev.fanie.funwithflagscompiler.util.Getter.getFlags;
import static dev.fanie.funwithflagscompiler.util.Getter.getName;
import static dev.fanie.funwithflagscompiler.util.Getter.getPackage;
import static dev.fanie.funwithflagscompiler.util.Getter.getType;
import static dev.fanie.funwithflagscompiler.util.Getter.isSingleton;

public final class Mapper {
    private Mapper() {
    }

    private static final String IMPLEMENTATION_SUFFIX = "Impl";

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

    public static Property getPropertyFrom(Element property, Map<Long, Element> flags) {
        return new Property(
                getType(property),
                getName(property),
                getFlags(property, flags),
                getFallback(property)
        );
    }

    public static Property[] getPropertyArrayFrom(Element[] properties, Map<Long, Element> flags) {
        final Property[] propertiesArray = new Property[properties.length];

        int index = 0;
        for (Element property : properties) {
            propertiesArray[index] = getPropertyFrom(property, flags);
            index++;
        }

        return propertiesArray;
    }

    public static PropertiesImplementation getPropertiesImplementationFrom(TypeElement propertiesClass, Element[] properties, Map<Long, Element> flags) {
        return new PropertiesImplementation(
                getPackage(propertiesClass),
                getClassName(propertiesClass) + IMPLEMENTATION_SUFFIX,
                getClassName(propertiesClass),
                isSingleton(propertiesClass),
                getPropertyArrayFrom(properties, flags)
        );
    }
}
