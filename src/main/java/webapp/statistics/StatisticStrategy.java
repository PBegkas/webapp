package webapp.statistics;

import webapp.entity.Course;

public interface StatisticStrategy {
	
	public double calculateStatistic(Course theCourse);

}
