package service;
import lombok.Getter;
@Getter
public class Counter {
    private int countWin;
    private int countLose;
    public void winCount() {
        countWin++;
    }
    public void loseCount() {
        countLose++;
    }
}
