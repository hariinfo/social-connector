package com.stepan.dashboard;

import com.stepan.dashboard.lengths.LengthsController;
import com.stepan.dashboard.messages.MessagesController;
import com.stepan.dashboard.potential.PotentialController;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class DashboardController {

    @FXML
    private MessagesController messagesController;

    @FXML
    private LengthsController lengthsController;

    @FXML
    private PotentialController potentialController;
}
