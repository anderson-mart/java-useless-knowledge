package modules.timer;

public class StopwatchUtil {
    public static String secondsToTime(int interval) {
        return String.format("%02d:%02d", interval / 60, interval % 60) + " until fact.";
    }
}