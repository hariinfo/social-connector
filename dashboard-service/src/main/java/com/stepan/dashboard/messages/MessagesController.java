package com.stepan.dashboard.messages;

import com.stepan.dashboard.configuration.services.DashboardService;
import com.stepan.dashboard.model.TwitterData;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagesController {

    @FXML
    private TableView<TwitterData> tweetsTable;

    private boolean initialized = false;

    private final DashboardService dashboardService;

    private final MessagesData messagesData;

    @Autowired
    public MessagesController(DashboardService dashboardService, MessagesData messagesData) {
        this.dashboardService = dashboardService;
        this.messagesData = messagesData;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void init() {
        if (!initialized) {
            dashboardService.handleTweetData().subscribe(messagesData);
            this.setData(messagesData);
            initialized = !initialized;
        }
    }

    private void setData(MessagesData data) {
        this.tweetsTable.setItems(data.getMessages());
    }
}