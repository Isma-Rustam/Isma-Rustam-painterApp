package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.DialogPane;

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private TextField brushSize;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private boolean brushAction = false;

    @FXML
    private GraphicsContext brushTool;



    @FXML
    public void initialize() {
        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if (brushAction && !brushSize.getText().isEmpty()) {
                brushTool.setFill(colorPicker.getValue());
                brushTool.fillRoundRect(x, y, size, size, size, size);
            }
        });
    }

    @FXML
    public void brushAction(ActionEvent actionEvent) {
        brushAction = true;
    }

    @FXML
    public void clearAction(ActionEvent actionEvent) {
        brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    public void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Hey hey! Welcom");
        alert.setContentText(
                "This is a simple painter application, where you can draw whatever you want.\n" +
                "houses, cars, flowers, person, animals etc.\n" +
                "\n" +
                "Instruction how to draw.\n" +
                "1.First of all, set brush size (only numbers).\n" +
                "2.Choose color.\n" +
                "3.Click on brush button.\n" +
                "Otherwise you can't draw.\n");
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Style/stylesheet.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}

