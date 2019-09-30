package dev.fanie.funwithflagscompiler.component;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import dev.fanie.funwithflags.FunWithFlags;
import dev.fanie.funwithflagscompiler.Contract;

import static dev.fanie.funwithflagscompiler.Fakes.getEnclosedProperties;
import static dev.fanie.funwithflagscompiler.Fakes.getFlags;
import static dev.fanie.funwithflagscompiler.Fakes.getFlagsSet;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesClass;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesClasses;
import static dev.fanie.funwithflagscompiler.Fakes.getPropertiesClassesSet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModelTest {
    private Model mSubject;

    private final Contract.Presenter mockPresenter = mock(Contract.Presenter.class);
    private final RoundEnvironment mockRoundEnvironment = mock(RoundEnvironment.class);

    @Before
    public void setup() {
        mSubject = new Model(mockPresenter);
        mSubject.connect(mockRoundEnvironment);
    }

    @Test
    public void whenFlagsAreRequested_thenRoundEnvironmentIsQueried() {
        prepareEnvironmentForFlags();
        mSubject.requestFlags();

        verify(mockRoundEnvironment).getElementsAnnotatedWith(FunWithFlags.Flag.class);
    }

    @Test
    public void whenFlagsAreRequested_thenFlagsAreReturned() {
        prepareEnvironmentForFlags();
        mSubject.requestFlags();

        verify(mockPresenter).onFlagsReceived(getFlags());
    }

    private void prepareEnvironmentForFlags() {
        when(mockRoundEnvironment.getElementsAnnotatedWith(FunWithFlags.Flag.class)).then(new Answer<Set<? extends Element>>() {
            @Override
            public Set<? extends Element> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return getFlagsSet();
            }
        });
    }

    @Test
    public void whenPropertyClassesAreRequested_thenRoundEnvironmentIsQueried() {
        prepareEnvironmentForPropertiesClasses();
        mSubject.requestPropertiesClasses();

        verify(mockRoundEnvironment).getElementsAnnotatedWith(FunWithFlags.Properties.class);
    }

    @Test
    public void whenPropertyClassesAreRequested_thenPropertyClassesAreReturned() {
        prepareEnvironmentForPropertiesClasses();
        mSubject.requestPropertiesClasses();

        verify(mockPresenter).onPropertiesClassesReceived(getPropertiesClasses());
    }

    private void prepareEnvironmentForPropertiesClasses() {
        when(mockRoundEnvironment.getElementsAnnotatedWith(FunWithFlags.Properties.class)).then(new Answer<Set<? extends Element>>() {
            @Override
            public Set<? extends Element> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return getPropertiesClassesSet();
            }
        });
    }

    @Test
    public void whenPropertiesAreRequested_thenPropertiesAreReturned() {
        final TypeElement propertiesClass = getPropertiesClass();
        mSubject.requestProperties(propertiesClass);

        verify(mockPresenter).onPropertiesReceived(propertiesClass, getEnclosedProperties());
    }
}
