package pl.maku.tech.PcapAnalizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Maciek
 * Date: 22.06.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class LineChart {

    private String filePath = "";

    public LineChart(String filePath) {
        this.filePath = filePath;

    }


    public void createLineChart(Map<String, Integer> inputMap, String chartName) throws IOException {

        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        for(String key : inputMap.keySet()) {

            line_chart_dataset.addValue(inputMap.get(key),"Pakiety",key);

        }


    /* Step -2:Define the JFreeChart object to create line chart */
        JFreeChart lineChartObject= ChartFactory.createLineChart(chartName,
                        "Czas",
                        "Liczba Pakietow",
                        line_chart_dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false);



        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategoryMargin(0.2);
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        lineChartObject.getCategoryPlot().setDomainAxis(xAxis);

    /* Step -3 : Write line chart to a file */
        int width=1000; /* Width of the image */
        int height=400; /* Height of the image */
        File lineChart=new File(filePath+"\\"+chartName+".png");
        ChartUtilities.saveChartAsPNG(lineChart, lineChartObject, width, height);


    }


}
