package engine;

import javafx.scene.control.Spinner;
import modules.notification.Notification;
import modules.notification.Notifier;
import engine.rest.RequestHandler;
import modules.timer.StopwatchUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import stages.MainStage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private boolean timerActive = false;
    private Timer timer = new Timer();
    private RequestHandler requestHandler = new RequestHandler();
    private StopwatchUtil stopwatchUtil = new StopwatchUtil();


    @FXML
    Spinner<Integer> timeSpinner;

    @FXML
    Text timerLabel;

    @FXML
    Button toggleButton;

    public void setTimerLabel(String text) {
        timerLabel.setText(text);
    }

    public static void start() {
        MainStage.run();
    }


    public class TimerConfig extends TimerTask {
        private int time;

        TimerConfig(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            if (time == 0) {
                this.cancel();
                timerActive = false;
                clearTimerLabel();
                try {
                    showNewFact();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            Platform.runLater(() ->
                timerLabel.setText(stopwatchUtil.secondsToTime(time)));
            time--;
        }
    }

    private void startTimer(int time) {
        if (!timerActive) {

            final int scheduleTime = time * 60;
            Platform.runLater(() ->
                timerLabel.setText(stopwatchUtil.secondsToTime(scheduleTime)));

            timer.scheduleAtFixedRate(new TimerConfig(scheduleTime), 1000, 1000);
            timerActive = true;
            Platform.runLater(() -> toggleButton.setText("Stop"));
        }
    }

    private void showNewFact() throws IOException {
        String fact = requestHandler.getNewFact();
        Notification notification = new Notification("Hey, I bet you didn't know:", fact);
        Notifier.sendNotification(notification);
    }

    private void clearTimerLabel() {
        Platform.runLater(() ->
            timerLabel.setText(""));
    }

    @FXML
    private void toggleButtonHandler(ActionEvent event) {
        if (timerActive) {
            timerActive = false;
            timer.cancel();
            timer = new Timer();
            Platform.runLater(() ->
                toggleButton.setText("Start"));

            clearTimerLabel();

        } else {
            int time = Integer.parseInt(timeSpinner.getValue().toString());
            startTimer(time);
        }
    }

    @FXML
    private void closeButtonHandler(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
