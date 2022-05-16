package webapp.statistics;

import webapp.entity.Course;

public abstract class TemplateStatisticStrategy implements StatisticStrategy {
	
	public double calculateStatistic(Course theCourse) {
		double value = 0;
		// dont know whats supposed to be calculated here since detailed stats are below
		
		return value;
	}
	
	
	//min, max, mean, standard deviation, 	variance, percentiles, skewness, kurtosis, 	median
	
	public abstract double calculateMin();
	
	public abstract double calculateMax();
	
	public abstract double calculateMean();
	
	public abstract double calculateStdDeviation();

	public abstract double calculateVariance();

	public abstract double calculatePercentiles();

	public abstract double calculateSkewness();
	
	public abstract double calculateMedian();

	public abstract double calculateKurtosis();



	
	

}
