package com.stepan.dashboard.potential;

import com.stepan.dashboard.configuration.services.DashboardService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PotentialController {

    @FXML
    private PieChart potentialChart;

    private final PotentialData potentialData;

    private final DashboardService dashboardService;

    private boolean initialized = false;

    public PotentialController(DashboardService dashboardService) {
        this.potentialData = new PotentialData();
        this.dashboardService = dashboardService;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void init() {
        if (!initialized) {
            dashboardService.handleTweetTexts().subscribe(potentialData);
            potentialChart.setData(potentialData.getPieChartData());
            initialized = !initialized;
        }
    }
}
