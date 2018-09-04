package hossomi.kss.junitmockito.samples.mockito;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        System.out.println(service.speak("Hossomi"));
    }

    @Test
    public void shouldMockThrow() {
        doThrow(new RuntimeException("devops"))
                .when(service)
                .speak(any());

        System.out.println(service.speak("Hossomi"));
    }

    @Test
    public void shouldMockAnswer() {
        doAnswer(i -> "Bye " + i.getArgument(0))
                .when(service)
                .speak(any());

        System.out.println(service.speak("Hossomi"));
    }

    @Test
    public void shouldNotMockWrongType() {
        doReturn(10)
                .when(service)
                .speak(any());

        System.out.println(service.speak("Hossomi"));
    }

    @Test
    public void shouldNotMockCheckedException() {
        doThrow(new Exception("devops"))
                .when(service)
                .speak(any());

        System.out.println(service.speak("Hossomi"));
    }

    @Test
    public void shouldMockHossomi() {
        doReturn("Hello world!")
                .when(service)
                .speak(eq("Hossomi"));

        System.out.println(service.speak("Hossomi"));
        System.out.println(service.speak("Jeff"));
    }

    @Test
    public void shouldMockLongNames() {
        doReturn("Hello you!")
                .when(service)
                .speak(argThat(name -> name.length() > 10));

        System.out.println(service.speak("Venkatanarasimharajuvaripeta"));
        System.out.println(service.speak("Hossomi"));
    }

}
