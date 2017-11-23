package com.stepan.dashboard.lengths;

import com.stepan.dashboard.configuration.services.DashboardService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LengthsController implements Initializable {

    @FXML
    public LineChart<Number, Number> testChart;

//        @FXML
//    private NumberAxis yAxis = new NumberAxis();

//    @FXML
//    private CategoryAxis xAxis = new CategoryAxis();

//    @FXML
//    private BarChart<String, Number> averageLength = new BarChart<>(xAxis, yAxis);

    private final DashboardService dashboardService;

    private final LengthsChartData lengthsChartData;

    @Autowired
    public LengthsController(DashboardService dashboardService, LengthsChartData lengthsChartData) {
        this.dashboardService = dashboardService;
        this.lengthsChartData = lengthsChartData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean initialized = false;

    @Scheduled(initialDelay = 1000, fixedRate = 1000000)
    public void init() {
        if (!initialized) {
            dashboardService.handleTweetTexts().subscribe(lengthsChartData);
            testChart.getData().add(lengthsChartData.getDataSeries());
            initialized = !initialized;
        }
    }
}
