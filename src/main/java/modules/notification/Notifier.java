package modules.notification;

public class Notifier {
    public static void sendNotification(Notification notification) {
        try {
            new ProcessBuilder("notify-send", "-t", "1000", notification.getTitle(), notification.getMessage()).start().waitFor();
        } catch (Exception ignored) {
        }
    }
}
