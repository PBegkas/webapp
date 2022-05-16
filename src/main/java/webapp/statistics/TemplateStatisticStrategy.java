package webapp.statistics;

import webapp.entity.Course;

public abstract class TemplateStatisticStrategy implements StatisticStrategy {
	
	
	public abstract double calcDescriptiveStats();
	
	
	public double calculateStatistic(Course theCourse) {
		double value = 0;
		// dont know whats supposed to be calculated here since detailed stats are below
		
		return value;
	}
	
	
	//min, max, mean, standard deviation, 	variance, percentiles, skewness, kurtosis, 	median
	
}
