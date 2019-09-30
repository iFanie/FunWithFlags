package dev.fanie.funwithflagscompiler;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;

import dev.fanie.funwithflags.FunWithFlags;
import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

public final class Fakes {
    private Fakes() {
    }

    public static Element getFlag() {
        return buildElement();
    }

    public static Set<? extends Element> getFlagsSet() {
        final int size = 20;
        final Set<Element> flags = new HashSet<>();

        for (int index = 0; index < size; index++) {
            flags.add(buildElement());
        }

        return flags;
    }

    public static Element[] getFlags() {
        final int size = 20;
        final Element[] flags = new Element[size];

        for (int index = 0; index < size; index++) {
            flags[index] = buildElement();
        }

        return flags;
    }

    public static TypeElement getPropertiesClass() {
        return buildTypeElement();
    }

    public static Set<? extends Element> getPropertiesClassesSet() {
        final int size = 3;
        final Set<Element> propertiesClasses = new HashSet<>();

        for (int index = 0; index < size; index++) {
            propertiesClasses.add(buildTypeElement());
        }

        return propertiesClasses;
    }

    public static TypeElement[] getPropertiesClasses() {
        final int size = 3;
        final TypeElement[] propertiesClasses = new TypeElement[size];

        for (int index = 0; index < size; index++) {
            propertiesClasses[index] = buildTypeElement();
        }

        return propertiesClasses;
    }

    public static Element[] getProperties() {
        final int size = 10;
        final Element[] properties = new Element[size];

        for (int index = 0; index < size; index++) {
            properties[index] = buildElement();
        }

        return properties;
    }

    public static Element[] getEnclosedProperties() {
        final int size = 10;
        final Element[] properties = new Element[size];

        for (int index = 0; index < size; index++) {
            properties[index] = buildPropertyElement();
        }

        return properties;
    }

    public static PropertiesImplementation[] getPropertiesImplementations() {
        final int size = 5;
        final PropertiesImplementation[] propertiesImplementations = new PropertiesImplementation[size];

        for (int index = 0; index < size; index++) {
            propertiesImplementations[index] = buildPropertiesImplementation();
        }

        return propertiesImplementations;
    }

    private static TypeElement buildTypeElement() {
        return new TypeElement() {
            @Override
            public List<? extends Element> getEnclosedElements() {
                final int size = 10;
                final List<Element> elements = new ArrayList<>();

                for (int index = 0; index < size; index++) {
                    elements.add(buildPropertyElement());
                }

                return elements;
            }

            @Override
            public NestingKind getNestingKind() {
                return null;
            }

            @Override
            public Name getQualifiedName() {
                return null;
            }

            @Override
            public Name getSimpleName() {
                return null;
            }

            @Override
            public TypeMirror getSuperclass() {
                return null;
            }

            @Override
            public List<? extends TypeMirror> getInterfaces() {
                return null;
            }

            @Override
            public List<? extends TypeParameterElement> getTypeParameters() {
                return null;
            }

            @Override
            public Element getEnclosingElement() {
                return null;
            }

            @Override
            public TypeMirror asType() {
                return null;
            }

            @Override
            public ElementKind getKind() {
                return ElementKind.CLASS;
            }

            @Override
            public Set<Modifier> getModifiers() {
                return null;
            }

            @Override
            public List<? extends AnnotationMirror> getAnnotationMirrors() {
                return null;
            }

            @Override
            public <A extends Annotation> A getAnnotation(Class<A> aClass) {
                return null;
            }

            @Override
            public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
                return null;
            }

            @Override
            public <A extends Annotation> A[] getAnnotationsByType(Class<A> aClass) {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof TypeElement;
            }
        };
    }

    private static Element buildElement() {
        return new Element() {
            @Override
            public TypeMirror asType() {
                return null;
            }

            @Override
            public ElementKind getKind() {
                return null;
            }

            @Override
            public Set<Modifier> getModifiers() {
                return null;
            }

            @Override
            public Name getSimpleName() {
                return new Name() {
                    @Override
                    public boolean contentEquals(CharSequence charSequence) {
                        return false;
                    }

                    @Override
                    public int length() {
                        return 0;
                    }

                    @Override
                    public char charAt(int i) {
                        return 0;
                    }

                    @Override
                    public CharSequence subSequence(int i, int i1) {
                        return null;
                    }

                    @Override
                    @SuppressWarnings("all")
                    public String toString() {
                        return "abc.dfg.FakeElement";
                    }
                };
            }

            @Override
            public Element getEnclosingElement() {
                return buildElement();
            }

            @Override
            public List<? extends Element> getEnclosedElements() {
                return null;
            }

            @Override
            public List<? extends AnnotationMirror> getAnnotationMirrors() {
                return null;
            }

            @Override
            @SuppressWarnings("unchecked")
            public <A extends Annotation> A getAnnotation(Class<A> aClass) {
                return (A) new FunWithFlags.Flag() {
                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return null;
                    }

                    @Override
                    public long id() {
                        return 10;
                    }
                };
            }

            @Override
            public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
                return null;
            }

            @Override
            public <A extends Annotation> A[] getAnnotationsByType(Class<A> aClass) {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof Element;
            }
        };
    }

    private static Element buildPropertyElement() {
        return new Element() {
            @Override
            public TypeMirror asType() {
                return null;
            }

            @Override
            public ElementKind getKind() {
                return null;
            }

            @Override
            public Set<Modifier> getModifiers() {
                return null;
            }

            @Override
            public Name getSimpleName() {
                return null;
            }

            @Override
            public Element getEnclosingElement() {
                return null;
            }

            @Override
            public List<? extends Element> getEnclosedElements() {
                return null;
            }

            @Override
            public List<? extends AnnotationMirror> getAnnotationMirrors() {
                return null;
            }

            @Override
            @SuppressWarnings("unchecked")
            public <A extends Annotation> A getAnnotation(Class<A> aClass) {
                return (A) new FunWithFlags.Property() {

                    @Override
                    public Class<? extends Annotation> annotationType() {
                        return FunWithFlags.Property.class;
                    }

                    @Override
                    public long[] ids() {
                        return new long[0];
                    }

                    @Override
                    public boolean fallback() {
                        return false;
                    }
                };
            }

            @Override
            public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
                return null;
            }

            @Override
            public <A extends Annotation> A[] getAnnotationsByType(Class<A> aClass) {
                return null;
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof Element;
            }
        };
    }

    private static PropertiesImplementation buildPropertiesImplementation() {
        return new PropertiesImplementation();
    }
}
