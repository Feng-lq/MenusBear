package app;

import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Timer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;

public class TomatoTimer extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    Button chatArea;
    private Timer timer;

    int minutes;
    int seconds;
    int recMinutes = 25;

    URL soundURL = getClass().getResource("ksm.wav");
    Media sound = new Media(soundURL.toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("tomato.png")));

        // Remove window borders and make the window transparent
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        if(soundURL == null){
            System.out.println("Empty URL!!!!!");
        }

        BorderPane borderPane = new BorderPane();

        // Set a background color or image
        borderPane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-image: url('" + getClass().getResource("background.jpg") + "');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-color: #e2d3bc;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: lightgray;" +
                        "-fx-border-width: 2;"
        );

        // 这个是自定义三个窗口控制 owo
        VBox controlButtons = new VBox(10);
        controlButtons.setPadding(new Insets(10));
        controlButtons.getStyleClass().add("control-buttons");

        Button minimizeButton = new Button();
        Button maximizeButton = new Button();
        Button closeButton = new Button();

        // Load icons
        minimizeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("minimize-icon.png"))));
        maximizeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("maximize-icon.png"))));
        closeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("close-icon.png"))));


        minimizeButton.getStyleClass().add("minimize-button");
        maximizeButton.getStyleClass().add("maximize-button");
        closeButton.getStyleClass().add("close-button");

        closeButton.setOnAction(event -> primaryStage.close());
        minimizeButton.setOnAction(event -> primaryStage.setIconified(true));
        maximizeButton.setOnAction(event -> primaryStage.setMaximized(!primaryStage.isMaximized()));

        controlButtons.getChildren().addAll(closeButton, maximizeButton, minimizeButton);

        // Add drag functionality to the entire borderPane
        borderPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        borderPane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        //回复框
        chatArea = new Button();
        minutes = 25;
        chatArea.setText(String.format("%02d:%02d", minutes, seconds));
        chatArea.setFont(new Font(95));;
        chatArea.setDisable(false);
        // chatArea.setWrapText(true);
        chatArea.getStyleClass().add("chat-area");
        chatArea.setBackground(null);
        chatArea.setBorder(
            new Border(
                new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    new CornerRadii(15),
                    new BorderWidths(5)
                )
            )
        );

        Button sendButton = new Button("Start");
        sendButton.getStyleClass().add("send-button");
        sendButton.setStyle("-fx-background-color: #a57a52; -fx-text-fill: white;");
        sendButton.setPrefHeight(30);
        sendButton.setPrefWidth(90);
        sendButton.setPadding(new Insets(5));  // 设置内边距


        Button pauseButton = new Button("Pause");
        pauseButton.getStyleClass().add("send-button");
        pauseButton.setStyle("-fx-background-color: #a57a52; -fx-text-fill: white;");
        pauseButton.setPrefHeight(30);
        pauseButton.setPrefWidth(90);
        pauseButton.setPadding(new Insets(5));  // 设置内边距


        Button resetButton = new Button("Reset");
        resetButton.getStyleClass().add("send-button");
        resetButton.setStyle("-fx-background-color: #a57a52; -fx-text-fill: white;");
        resetButton.setPrefHeight(30);
        resetButton.setPrefWidth(90);
        resetButton.setPadding(new Insets(5));  // 设置内边距


        Button setButton = new Button("Set");
        setButton.getStyleClass().add("send-button");
        setButton.setStyle("-fx-background-color: #a57a52; -fx-text-fill: white;");
        setButton.setPrefHeight(30);
        setButton.setPrefWidth(90);
        setButton.setPadding(new Insets(5));  // 设置内边距


        Button[] timeButtons = new Button[5];
        timeButtons[0] = new Button("05");
        timeButtons[1] = new Button("15");
        timeButtons[2] = new Button("30");
        timeButtons[3] = new Button("45");
        timeButtons[4] = new Button("60");

        for(int i=0; i<timeButtons.length; i++){
            timeButtons[i].setStyle("-fx-background-color: #5a362b;" +
            "-fx-text-fill: white;" +
            "-fx-border-radius: 50%;" +
            "-fx-background-radius: 50%;" +
            "-fx-padding: 14px 16px;" +
            "-fx-font-size: 14px;" +
            "-fx-alignment: center;"
            );
        }

        // inputField.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.8));

        timer = new Timer(1000, new TimerListener());

        timeButtons[0].setOnAction(event -> {
            clearTime(5);
        });

        timeButtons[1].setOnAction(event -> {
            clearTime(15);
        });

        timeButtons[2].setOnAction(event -> {
            clearTime(30);
        });

        timeButtons[3].setOnAction(event -> {
            clearTime(45);
        });

        timeButtons[4].setOnAction(event -> {
            clearTime(60);
        });

        sendButton.setOnAction(event -> {
            timer.start();
            // mediaPlayer.play();
        });

        pauseButton.setOnAction(event -> {
            timer.stop();
        });

        resetButton.setOnAction(event ->{
            timer.stop();
            clearTime();
        });


        GridPane inputBox = new GridPane();
        // inputBox.setHgap(10);
        inputBox.setVgap(60);
        ColumnConstraints emptyColumn = new ColumnConstraints(50, 200, Double.MAX_VALUE);

        HBox forSetButton = new HBox();
        forSetButton.getChildren().addAll(sendButton, pauseButton, resetButton, setButton);
        forSetButton.setSpacing(15);

        HBox forQuickButton = new HBox();
        forQuickButton.getChildren().addAll(timeButtons);
        forQuickButton.setAlignment(Pos.CENTER);
        forQuickButton.setSpacing(20);

        inputBox.getColumnConstraints().add(emptyColumn);

        inputBox.add(forSetButton, 1, 1);
        inputBox.add(forQuickButton, 1, 0);

        inputBox.setPadding(new Insets(10));

        // Assemble layout
        borderPane.setRight(chatArea);
        borderPane.setBottom(inputBox);
        
        borderPane.setLeft(controlButtons);

        // 窗口的参数
        Scene scene = new Scene(borderPane, 600, 400);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void clearTime(){
        minutes = recMinutes;
        seconds = 0;
        chatArea.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void clearTime(int rec){
        recMinutes = rec;
        minutes = recMinutes;
        seconds = 0;
        chatArea.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (minutes == 0 && seconds == 0) { 
                        mediaPlayer.play();
                        timer.stop(); 
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Pomodoro Clock");
                        alert.setHeaderText(null);
                        alert.setContentText("Times Up!");
                        
                        alert.showAndWait();
                        
                    } else if (seconds == 0) {
                        minutes--;
                        seconds = 59; 
                    } else {
                        seconds--;
                    }
                    chatArea.setText(String.format("%02d:%02d", minutes, seconds));
                }
            });
            
        }
    }
}
