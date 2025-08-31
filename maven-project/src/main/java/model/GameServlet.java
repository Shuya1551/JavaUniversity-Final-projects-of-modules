package model;
import entities.Answer;
import entities.Question;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.QuestionManager;
import service.Counter;
import service.UserInit;
import java.io.IOException;

@WebServlet(name = "model.GameServlet", value = "/gameServlet")
public class GameServlet extends HttpServlet {
    Counter counter = new Counter();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession currentSession = req.getSession();
        QuestionManager questionManager = new QuestionManager();
        Question currentQuestion = getCurrentQuestion(req);
        Question nextQuestion = null;
        if (currentQuestion == null) {
            nextQuestion = questionManager.getById(1);
        } else {
            Answer currentAnswer = getCurrentAnswer(currentQuestion, req);
            if (currentAnswer != null && !currentAnswer.isWrongAnswer()) {
                nextQuestion = questionManager.getById(currentQuestion.getId() + 1);
            } else {
                currentSession.setAttribute("wrongAnswer", currentAnswer);
                counter.loseCount();
            }
        }
        currentSession.setAttribute("question", nextQuestion);
        String ipaddress = req.getRemoteAddr();
        currentSession.setAttribute("ipaddress", ipaddress);
        currentSession.setAttribute("loseCounter", counter.getCountLose());
        Answer currentAnswer = getCurrentAnswer(currentQuestion, req);
        if (currentAnswer != null) {
            if (currentAnswer.getText().equals("Рассказать правду")) {
                counter.winCount();
            }
        }
        currentSession.setAttribute("winCounter", counter.getCountWin());
        resp.sendRedirect("/quest.jsp");
    }

    private Question getCurrentQuestion(HttpServletRequest req) {
        try {
            return (Question) req.getSession().getAttribute("question");
        } catch (Exception e) {
            return null;
        }
    }

    private Answer getCurrentAnswer(Question currentQuestion, HttpServletRequest req) {
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            return currentQuestion.getAnswerList().get(answerId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("firstName");
        req.getSession().setAttribute("questId", "quest1");
        req.setAttribute("questId", "quest1");
        resp.setContentType("text/html;charset=utf-8");
        UserInit.addUser(req.getSession(), userName);
        doGet(req, resp);
    }
}