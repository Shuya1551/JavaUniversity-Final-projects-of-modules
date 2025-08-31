import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GameServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

public class GameServletTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    GameServlet gameServlet;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameServlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost() throws IOException {
        when(request.getParameter("firstName")).thenReturn("Oleg Gutsev");

        gameServlet.doPost(request, response);

        verify(session).setAttribute("questId", "quest1");
        verify(response).setContentType("text/html;charset=utf-8");
        verify(session).setAttribute("userName", "Oleg Gutsev");
    }
}

