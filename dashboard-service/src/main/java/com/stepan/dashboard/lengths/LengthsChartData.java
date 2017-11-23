package com.stepan.dashboard.lengths;

import javafx.scene.chart.XYChart;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.IntStream.range;

@Component
public class LengthsChartData extends BaseSubscriber<String> {

    private final XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
    private final Map<Integer, Integer> lengthToDataPosition = new HashMap<>();

    public LengthsChartData() {
        range(0, 10).forEach(this::zeroInitializer);
    }

    private void zeroInitializer(int length) {
        dataSeries.getData().add(new XYChart.Data<>(length, 0));
        lengthToDataPosition.put(length, dataSeries.getData().size() -1);
    }

    @Override
    protected void hookOnNext(String value) {
        Integer dataIndex = lengthToDataPosition.get(value.length());
        XYChart.Data<Number, Number> lengthBar = dataSeries.getData().get(dataIndex);
        lengthBar.setYValue(lengthBar.getYValue().intValue() + 1);
        super.hookOnNext(value);
    }

    public XYChart.Series<Number, Number> getDataSeries() {
        return dataSeries;
    }
}
