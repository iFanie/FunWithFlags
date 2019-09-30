package dev.fanie.funwithflagscompiler.component;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflagscompiler.Contract;
import dev.fanie.funwithflagscompiler.DuplicateFlagIdException;
import dev.fanie.funwithflagscompiler.entity.PropertiesImplementation;

import static dev.fanie.funwithflagscompiler.Fakes.getFlag;
import static dev.fanie.funwithflagscompiler.Fakes.getFlags;
import static dev.fanie.funwithflagscompiler.Fakes.getProperties;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesClass;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesClasses;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesImplementations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterTest {
    private Presenter mSubject;

    private final Contract.Model mockModel = mock(Contract.Model.class);
    private final Contract.View mockView = mock(Contract.View.class);

    @Before
    public void setup() {
        mSubject = new Presenter();
        mSubject.connect(mockModel, mockView);
    }

    @Test
    public void whenCompiling_thenFlagsAreRequested() {
        mSubject.onCompilation();

        verify(mockModel).requestFlags();
    }

    @Test(expected = DuplicateFlagIdException.class)
    public void givenDuplicateFlagIdsExist_whenFlagsAreReceived_thenDuplicateIdExceptionIsThrown() {
        when(mockModel.getFlagById(anyLong())).thenReturn(getFlag());

        mSubject.onFlagsReceived(getFlags());
    }

    @Test
    public void givenDuplicateFlagIdsDoNotExist_whenFlagsAreReceived_thenEachFlagIsStored() {
        final Element[] flags = getFlags();
        when(mockModel.getFlagById(anyLong())).thenReturn(null);

        mSubject.onFlagsReceived(flags);

        verify(mockModel, times(flags.length)).storeFlagWithId(any(Element.class), anyLong());
    }

    @Test
    public void whenCompiling_thenPropertiesClassesAreRequested() {
        mSubject.onCompilation();

        verify(mockModel).requestPropertiesClasses();
    }

    @Test
    public void whenPropertyClassesAreReceived_thenPropertiesAreRequestedForEachClass() {
        final TypeElement[] propertiesClasses = getPropertiesClasses();
        mSubject.onPropertiesClassesReceived(propertiesClasses);

        verify(mockModel, times(propertiesClasses.length)).requestProperties(any(TypeElement.class));
    }

    @Test
    public void whenClassPropertiesAreReceived_thenPropertiesClassImplementationIsStored() {
        final TypeElement propertyClass = getPropertiesClass();
        final Element[] properties = getProperties();
        mSubject.onPropertiesReceived(propertyClass, properties);

        verify(mockModel).storePropertiesImplementation(any(PropertiesImplementation.class));
    }

    @Test
    public void whenFinalized_thenStoredPropertiesImplementationsAreRetrieved() {
        when(mockModel.getStoredPropertiesImplementations()).thenReturn(getPropertiesImplementations());

        mSubject.onFinalization();

        verify(mockModel).getStoredPropertiesImplementations();
    }

    @Test
    public void whenFinalized_thenEachPropertiesImplementationIsPublished() throws IOException {
        final PropertiesImplementation[] propertiesImplementations = getPropertiesImplementations();
        when(mockModel.getStoredPropertiesImplementations()).thenReturn(propertiesImplementations);

        mSubject.onFinalization();

        verify(mockView, times(propertiesImplementations.length)).publishPropertiesImplementation(any(PropertiesImplementation.class));
    }
}
