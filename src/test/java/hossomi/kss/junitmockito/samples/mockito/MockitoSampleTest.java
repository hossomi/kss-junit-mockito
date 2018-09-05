package hossomi.kss.junitmockito.samples.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
public class MockitoSampleTest {

    public static class Service {
        public String speak(String name) {
            return "Hello " + name;
        }
    }

    private Service service = mock(Service.class);

    @Test
    public void shouldMockReturn() {
        doReturn("Bye world!")
                .when(service)
                .speak(any());

        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldMockThrow() {
        doThrow(new RuntimeException("devops"))
                .when(service)
                .speak(any());

        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldMockAnswer() {
        doAnswer(i -> "Bye " + i.getArgument(0))
                .when(service)
                .speak(any());

        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldNotMockWrongType() {
        doReturn(10)
                .when(service)
                .speak(any());

        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldNotMockCheckedException() {
        doThrow(new Exception("devops"))
                .when(service)
                .speak(any());

        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldMockHossomi() {
        doReturn("Hello world!")
                .when(service)
                .speak(eq("Hossomi"));

        log.info("{}", service.speak("Hossomi"));
        log.info("{}", service.speak("Jeff"));
    }

    @Test
    public void shouldMockLongNames() {
        doReturn("Hello you!")
                .when(service)
                .speak(argThat(name -> name.length() > 10));

        log.info("{}", service.speak("Venkatanarasimharajuvaripeta"));
        log.info("{}", service.speak("Hossomi"));
    }

    @Test
    public void shouldVerify() {
        doReturn("Hello world!")
                .when(service)
                .speak(eq("Hossomi"));

        log.info(service.speak("Hossomi"));
        log.info(service.speak("Jeff"));

        verify(service).speak(eq("Hossomi"));
        verify(service, times(2)).speak(any());
        verify(service, never()).speak(eq("Marcelo"));
    }

}
