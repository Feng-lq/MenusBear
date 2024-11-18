package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TodoList extends Application {

    private TextArea taskInput;
    private ListView<Task> taskList;
    private ObservableList<Task> tasks;
    private final String FILE_PATH = "tasks.txt"; // ʹ���ı��ļ���������
    private CheckBox showCompletedCheckBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todo List");

        // ����Ĭ�ϴ��ڴ�С
        primaryStage.setWidth(600); // ���ÿ��
        primaryStage.setHeight(400); // ���ø߶�

        taskInput = new TextArea();
        taskInput.setPromptText("�����µ�����");
        taskInput.setWrapText(true);
        taskInput.setMinHeight(60);

        Button addButton = new Button("���");
        addButton.setOnAction(e -> addTask());
        addButton.setDefaultButton(true);

        Button removeButton = new Button("ɾ��");
        removeButton.setOnAction(e -> removeTask());

        showCompletedCheckBox = new CheckBox("��ʾ���������");
        showCompletedCheckBox.setSelected(true);
        showCompletedCheckBox.setOnAction(e -> filterTasks());

        tasks = FXCollections.observableArrayList();
        taskList = new ListView<>(tasks);
        taskList.setCellFactory(lv -> new TaskCell()); // �Զ��嵥Ԫ����ʾ

        // ���������б�ļ����¼�
        taskList.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
                removeTask();
            }
        });

        // ����˫���༭����
        taskList.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                editTask();
            }
        });

        HBox buttonLayout = new HBox(10, addButton, removeButton, showCompletedCheckBox);
        VBox layout = new VBox(10, taskInput, buttonLayout, taskList);
        layout.setPadding(new Insets(10));

        // ��������
        loadTasks();

        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Todo_styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        // �ڹر�Ӧ��ʱ��������
        primaryStage.setOnCloseRequest(event -> saveTasks());
    }

    private void addTask() {
        String taskContent = taskInput.getText().trim(); // ȥ��ǰ��ո�
        if (!taskContent.isEmpty()) {
            Optional<Task> existingTask = tasks.stream().filter(task -> task.getContent().equals(taskContent)).findFirst();
            if (existingTask.isPresent()) {
                showAlert("�������Ѵ��ڣ������벻ͬ������");
            } else {
                Task newTask = new Task(taskContent);
                tasks.add(newTask);
                taskInput.clear();
                showAlert("�����ѳɹ���ӣ�");
            }
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ȷ��Ҫɾ����������");
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    tasks.remove(selectedIndex);
                }
            });
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = taskList.getItems().get(selectedIndex);
            taskInput.setText(selectedTask.getContent());
            taskList.getItems().remove(selectedIndex); // ɾ��ԭ������
        }
    }

    private void filterTasks() {
        // ���ݸ�ѡ��״̬��������
        if (showCompletedCheckBox.isSelected()) {
            taskList.setItems(tasks);
        } else {
            taskList.setItems(tasks.filtered(task -> !task.isCompleted()));
        }
    }

    private void loadTasks() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
                for (String line : lines) {
                    String[] parts = line.split(" \\| "); // �����������ݺ�ʱ���� '|' �ָ�
                    if (parts.length == 3) {
                        Task task = new Task(parts[0], parts[1], Boolean.parseBoolean(parts[2]));
                        tasks.add(task);
                    }
                }
            }
        } catch (IOException e) {
            showAlert("��������ʧ��: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.getContent() + " | " + task.getCreationTime() + " | " + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("��������ʧ��: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // ����������
    public static class Task {
        private String content;
        private String creationTime;
        private boolean completed; // ������״̬

        public Task(String content) {
            this.content = content;
            this.creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            this.completed = false; // Ĭ��δ���
        }

        public Task(String content, String creationTime, boolean completed) {
            this.content = content;
            this.creationTime = creationTime;
            this.completed = completed;
        }

        public String getContent() {
            return content;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void toggleCompleted() {
            completed = !completed; // �л����״̬
        }

        @Override
        public String toString() {
            return (completed ? "[x] " : "[ ] ") + content + " (����ʱ��: " + creationTime + ")"; // ������ʾδ��ɺ�����ɵ�����
        }
    }

    // �Զ��嵥Ԫ��
    private static class TaskCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.toString()); // ��ʾ��������ݺʹ���ʱ��

                // �������¼����л����״̬
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        item.toggleCompleted(); // �л����״̬
                        setText(item.toString()); // ������ʾ
                    }
                });
            }
        }
    }
}