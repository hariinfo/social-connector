package com.stepan.dashboard.potential;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class PotentialData extends BaseSubscriber<String> {

    private final PieChart.Data incliningPotential = new PieChart.Data("Inclining", 0);
    private final PieChart.Data neutralPotential = new PieChart.Data("Neutral", 0);
    private final PieChart.Data decliningPotential = new PieChart.Data("Declining", 0);
    private final ObservableList<PieChart.Data> pieChartData = observableArrayList(incliningPotential, neutralPotential, decliningPotential);

    @Override
    protected void hookOnNext(String value) {
        super.hookOnNext(value);
        if (value.contains("a")) {
            incremenentPart(incliningPotential);
        }
    }

    private void incremenentPart(PieChart.Data part) {
        part.setPieValue(part.getPieValue() + 1);
    }

    public ObservableList<PieChart.Data> getPieChartData() {
        return pieChartData;
    }
}
