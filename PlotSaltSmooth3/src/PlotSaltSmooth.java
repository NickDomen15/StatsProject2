import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.apache.commons.statistics.descriptive.Mean;
import java.io.File;
import java.util.Random;

/**
 * Program that plots a function, salts the data, and smooths the data
 * Uses JFreeChart library to output graphs
 * @author Nick Domenico
 */
public class PlotSaltSmooth {

	// instance variables
	private XYDataset graphPoints;
	private XYDataset saltedData;
	private XYDataset smoothedData;
	
	Random rand = new Random();
	
	
	/**
	 * Adds the x and y values of the function 2x^2 + 4x + 3 to an 
	 * XYSeries, then adds the series to the XYDataset, graphPoints
	 * 
	 * @param lowerRange
	 * @param upperRange
	 */
	public void calculateFunction(int lowerRange, int upperRange) {
		
		XYSeriesCollection dataSet = new XYSeriesCollection();
		XYSeries series = new XYSeries("2x^2 + 4x + 3");
		
		for (int i = lowerRange; i < upperRange; i++) {
			series.add(i, 2 * Math.pow(i, 2) + 4 * i + 3);
		}
		
		dataSet.addSeries(series);
		graphPoints = dataSet;
		
	}
	
	
	/**
	 * Iterates over the graphPoints XYDataset and randomly adds
	 * or subtracts a random value from 0-saltValueRange from each
	 * y value, then adds the salted y values to an XYSeries, and
	 * then to the XYDataset, saltedData
	 * 
	 * @param saltValueRange
	 */
	public void saltData(int saltValueRange) {
		
		XYSeriesCollection dataSet = new XYSeriesCollection();
		XYSeries series = new XYSeries("2x^2 + 4x + 3 (salted)");
		
		int initialGraphSize = graphPoints.getItemCount(0);
		
		for (int i = 0; i < initialGraphSize; i++) {
			
			int operation = rand.nextInt(1);
			int saltValue = rand.nextInt(saltValueRange);
			
			if (operation == 0) {
				series.add(graphPoints.getXValue(0, i), graphPoints.getYValue(0, i) - saltValue);
			}
			
			else if (operation == 1){
				series.add(graphPoints.getXValue(0, i), graphPoints.getYValue(0, i) + saltValue);
			}
			
		}
		
		dataSet.addSeries(series);
		saltedData = dataSet;
		
	}
	
	
	/**
	 * Iterates over the saltedData XYDataset and, for each y value, 
	 * averages the values of windowValue points to the left and
	 * right of the point, then adds the (x, y) value with the new
	 * average y to an XYSeries, then adds that series to the 
	 * XYDataset, smoothedData
	 * 
	 * @param windowValue
	 */
	public void smoothData(int windowValue) {
		
		XYSeriesCollection dataSet = new XYSeriesCollection();
		XYSeries series = new XYSeries("2x^2 + 4x + 3 (smoothed)");
		
		int initialGraphSize = saltedData.getItemCount(0);
		
		for (int i = 0; i < initialGraphSize; i++) {
			
			double smoothedValue = 0;
			int numberOfPoints = 0;
			double windowTotal = 0;
			
			if ((i - windowValue) < 0) {
				for (int j = 0; j < i + windowValue; j++) {
					windowTotal += saltedData.getYValue(0, j);
					numberOfPoints++;
				}
			}
			
			else if ((i + windowValue) > initialGraphSize) {
				for (int j = i - windowValue; j < initialGraphSize; j++) {
					windowTotal += saltedData.getYValue(0, j);
					numberOfPoints++;
				}
			}
			else {
				for (int j = i - windowValue; j < i + windowValue; j++) {
					windowTotal += saltedData.getYValue(0, j);
					numberOfPoints++;
				}
			}
			
			smoothedValue = windowTotal / numberOfPoints;

			series.add(saltedData.getXValue(0, i), smoothedValue);
			
		}
		
		dataSet.addSeries(series);
		smoothedData = dataSet;
		
	}
	
	
	/**
	 * Creates the 3 graphs for the original function, 
	 * the salted function, and the smoothed function, 
	 * and outputs them as JPEG files
	 * 
	 * @param file1Name
	 * @param file2Name
	 * @param file3Name
	 */
	public void generateGraphs(String file1Name, String file2Name, String file3Name) {
		
		JFreeChart chart1 = ChartFactory.createXYLineChart("Initial Function", "x", "y", graphPoints);
		JFreeChart chart2 = ChartFactory.createXYLineChart("Salted Function", "x", "y", saltedData);
		JFreeChart chart3 = ChartFactory.createXYLineChart("Smoothed Function", "x", "y", smoothedData);
		
		try {
			
			File file1 = new File(file1Name);
			File file2 = new File(file2Name);
			File file3 = new File(file3Name);
			
			if (!file1.exists()) {
				file1.createNewFile();
			}
		
			if (!file2.exists()) {
				file2.createNewFile();
			}
			
			if (!file3.exists()) {
				file3.createNewFile();
			}
			
			ChartUtilities.saveChartAsJPEG(file1, chart1, 1000, 600);
			ChartUtilities.saveChartAsJPEG(file2, chart2, 1000, 600);
			ChartUtilities.saveChartAsJPEG(file3, chart3, 1000, 600);
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
