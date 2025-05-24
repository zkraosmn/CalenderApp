package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import model.Event;
import util.FileUtil;
import main.CalendarApp;
import java.time.LocalDate;

public class DashboardController {
    @FXML private TextField eventTitle;
    @FXML private TextArea eventDescription;
    @FXML private DatePicker datePicker;
    @FXML private ListView<String> eventListView;
    private ObservableList<String> eventItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        eventItems.clear();
        for (Event e : FileUtil.loadEvents()) {
            eventItems.add(e.toDisplay());
        }
        eventListView.setItems(eventItems);
    }

    @FXML
    protected void onAddEvent(ActionEvent event) {
        String title = eventTitle.getText();
        String desc = eventDescription.getText();
        LocalDate date = datePicker.getValue();
        if (title.isEmpty() || desc.isEmpty() || date == null) return;

        Event e = new Event(title, desc, date);
        FileUtil.saveEvent(e);
        eventItems.add(e.toDisplay());
        eventTitle.clear();
        eventDescription.clear();
    }

    @FXML
    protected void onDeleteEvent(ActionEvent event) {
        String selected = eventListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            FileUtil.deleteEvent(selected);
            eventItems.remove(selected);
        }
    }

    @FXML
    protected void onLogout(ActionEvent event) {
        try {
            CalendarApp.changeScene("login.fxml", 400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// User.java
package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
