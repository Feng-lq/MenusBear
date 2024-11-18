package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class OpenSoftwareHandler implements EventHandler<ActionEvent> {

    private String softwareName;

    public OpenSoftwareHandler(String softwareName) {
        this.softwareName = softwareName;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            if ("todo".equalsIgnoreCase(softwareName)) {
                // �� TodoList Ӧ��
                TodoList todoList = new TodoList();
                Stage stage = new Stage();
                todoList.start(stage);
            } else if ("chat".equalsIgnoreCase(softwareName)) {
                // �� Chat Ӧ��
                Chat chat = new Chat();
                Stage stage = new Stage();
                chat.start(stage);
            } else if ("tomato".equalsIgnoreCase(softwareName)) {
                // �� Tomato Ӧ��
                TomatoTimer tomatoTimer = new TomatoTimer(true, 30);
                Stage stage = new Stage();
                tomatoTimer.start();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}