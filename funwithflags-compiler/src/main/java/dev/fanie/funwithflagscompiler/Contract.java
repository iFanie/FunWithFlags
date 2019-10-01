package dev.fanie.funwithflagscompiler;

import java.io.IOException;
import java.util.Map;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

public interface Contract {
    interface Model {
        void connect(RoundEnvironment roundEnvironment);

        void requestFlags();

        Element getFlagById(long id);

        Map<Long, Element> getAllFlags();

        void storeFlagWithId(Element flag, long id);

        void requestPropertiesClasses();

        void requestProperties(TypeElement propertiesClass);

        void storePropertiesImplementation(PropertiesImplementation propertiesImplementation);

        PropertiesImplementation[] getStoredPropertiesImplementations();
    }

    interface View {
        void publishPropertiesImplementation(PropertiesImplementation propertiesImplementation) throws IOException;
    }

    interface Presenter {
        void connect(Model model, View view);

        void onCompilation();

        void onFlagsReceived(Element[] flags);

        void onPropertiesClassesReceived(TypeElement[] propertiesClasses);

        void onPropertiesReceived(TypeElement propertiesClass, Element[] properties);

        void onFinalization();
    }
}
