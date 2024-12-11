
public class TestPlotSaltSmooth {

	public static void main(String[] args) {
		
		PlotSaltSmooth test = new PlotSaltSmooth();
		
		test.calculateFunction(-100, 100);
		
		test.saltData(5000);
		
		test.smoothData(5);
		
		test.generateGraphs("JFreeChart Initial Graph.jpeg", "JFreeChart Salted Graph.jpeg", "JFreeChart Smoothed Graph.jpeg");
		
	}
	
}
