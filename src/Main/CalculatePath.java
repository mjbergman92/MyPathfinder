package Main;

import PartTypes.Acceleration;
import PartTypes.Jerk;

public class CalculatePath {
	
	private static Waypoint[] points;
	private static Trajectory.Segment[] segments;
	private static Trajectory.Config config;
	private static int part, waypoint;
	private static double xChange, yChange, slope, distance, acceleration, velocity;
	private static double[] Jerk1, Accel, Jerk2, Constant, Jerk3, Decel, Jerk4;
	private static int seg = 1;
	private static double time;
	private static double[] segTimes;
	private static int portion;
	
	public static Trajectory generateTrajectory(Waypoint[] waypoints, Trajectory.Config configuration) {
		part = 1;
		points = waypoints;
		config = configuration;
		int numOfPoints = waypoints.length;
		for(int i = 1; i <= numOfPoints; i++) {
			calculateBetweenWaypoints(i);
		}
		return new Trajectory(segments);
	}
	
	private static void calculateBetweenWaypoints(int point) {
		waypoint = point;

		yChange = points[waypoint+1].y - points[waypoint].y;
		xChange = points[waypoint+1].x - points[waypoint].x;
		slope = yChange/xChange; //for every x toward the next point, y changes how much, might not be needed in the end
		
		if(points[waypoint].angle == points[waypoint + 1].angle 
				&& (Math.tan(slope) == points[waypoint].angle || points[waypoint].x == points[waypoint + 1].x)) {
			calculatePart(true);
			part++;
		}else {
			calculatePart(true);
			part++;
			calculatePart(false);
			part++;
			calculatePart(true);
			part++;
		}
	}
	
	private static void calculatePart(boolean straight) {
		if(straight) {
			double distance = Math.pow(Math.pow(xChange, 2) + Math.pow(yChange, 2), .5);
			
			int i = 0;
			while(segments[i] != null) {
				i++;
			}
			
			double remainingDistance = distance;
			Jerk accelJerkUp = new Jerk(segments[i].acceleration, config.max_acceleration, segments[i].velocity, (Double) null, remainingDistance, config);
			remainingDistance = distance - accelJerkUp.getJerkValues()[1];
			Jerk accelJerkDown;
			accelJerkDown = new Jerk(config.max_acceleration, 0, (Double) null, config.max_velocity, remainingDistance, config);
			remainingDistance = remainingDistance - accelJerkDown.getJerkValues()[1];
			Acceleration acceleration = new Acceleration(accelJerkUp.getJerkValues()[4], accelJerkDown.getJerkValues()[3],config);
			double totalAccelDist = accelJerkUp.getJerkValues()[1] + acceleration.getAccelerationValues()[1] + accelJerkDown.getJerkValues()[1];
			if(totalAccelDist > distance) {
				if(accelJerkUp.getJerkValues()[2] == config.max_acceleration) {
					acceleration = new Acceleration(totalAccelDist, totalAccelDist, config);
				}else {
					
				}
			}else {
				
				
				
			}
			
			
			if(points[waypoint + 2] == null) {
				if(distance > remainingDistance) {
					AccelSeconds = (segments[i].velocity - difVelocity) / config.max_acceleration;
					AccelDist = remainingDistance;
				}else if(distance == remainingDistance){
					AccelSeconds = 0;
					AccelDist = 0;
				}else {
					JerkSeconds = 0;
					JerkDist = 0;
					AccelSeconds = (segments[i].velocity - 0) / config.max_acceleration;
					AccelDist = distance;
				}
				//segTime[portion] = 
			}else {
				if() {
					
				}else if(){
					
				}else {
					
				}
			}
		
		
		}else { //curve which is part of circle and two clothoids
			
		}
	}
}
