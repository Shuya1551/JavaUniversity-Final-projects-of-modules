import static org.mockito.Mockito.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import controller.InitServlet;

import java.io.IOException;

public class InitServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    ServletConfig servletConfig;

    @Mock
    ServletContext servletContext;

    @Mock
    RequestDispatcher requestDispatcher;

    InitServlet initServlet;

    @Before
    public void setUp() throws ServletException {
        MockitoAnnotations.openMocks(this);
        initServlet = new InitServlet();
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/index.jsp")).thenReturn(requestDispatcher);
        initServlet.init(servletConfig);  // Инициализируем сервлет с помощью mock ServletConfig
        when(request.getSession(true)).thenReturn(session);
    }

    @Test
    public void testDoGet() throws IOException, ServletException {
        initServlet.doGet(request, response);
        verify(request).getSession(true);
        verify(servletContext).getRequestDispatcher("/index.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
