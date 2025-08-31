package service;
import jakarta.servlet.http.HttpSession;
import repository.UserRepository;

public class UserInit {
    public static void addUser(HttpSession session, String userName) {
        Integer userId = UserRepository.getIdForNewUser(userName);
        session.setAttribute("userId", userId);
        session.setAttribute("userName", userName);
    }
}
