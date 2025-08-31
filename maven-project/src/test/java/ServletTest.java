import entities.Answer;
import entities.Question;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GameServlet;
import org.junit.jupiter.api.Test;
import repository.QuestionManager;
import java.io.IOException;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ServletTest {
    HttpServletRequest request = mock(HttpServletRequest.class);

    HttpServletResponse response = mock(HttpServletResponse.class);

    HttpSession session = mock(HttpSession.class);

    Question question1 = new Question(1, "Ты потерял память.\nПринять вызов НЛО?", List.of(
            new Answer("Принять вызов"),
            new Answer("Отклонить вызов", "Ты отклонил вызов. Поражение")));

    Question question2 = new Question(2, "Ты принял вызов. Поднимаешься на мостик к капитану?", List.of(
            new Answer("Подняться на мостик"),
            new Answer("Откзаться подниматься на мостик", "Ты не пошел на переговоры. Поражение.")));

    @Test

    public void doGetGameServletTestWrongAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("answerId")).thenReturn("1");
        doNothing().when(session).setAttribute(anyString(), any());
        when(session.getAttribute("question")).thenReturn(question1);
        QuestionManager questionManager = spy(new QuestionManager());
        when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        verify(session).setAttribute(eq("wrongAnswer"), any(Answer.class));
    }

    @Test

    public void doGetGameServletTestRightAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("answerId")).thenReturn("0");
        doNothing().when(session).setAttribute(anyString(), any());
        when(session.getAttribute("question")).thenReturn(question1);
        QuestionManager questionManager = spy(new QuestionManager());
        when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        verify(session).setAttribute(eq("question"), any(Question.class));
        verify(response).sendRedirect("/quest.jsp");
    }




}


