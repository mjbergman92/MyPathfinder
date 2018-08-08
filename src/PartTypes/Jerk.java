package PartTypes;

import java.math.MathContext;

import Main.Trajectory;

public class Jerk {
	
	double actual_final_acceleration, distance_covered, time_needed, actual_initial_velocity, actual_final_velocity;
	
	/**
     * Create Jerk
     * @param known_initial_acceleration		The initial acceleration of the jerk (in used length units per second squared)
     * @param expected_final_acceleration		The wanted acceleration after the jerk (in used length units per second squared)
     * @param known_initial_velocity     		The initial velocity of the jerk (in used length units per second)				leave "null" if unknown
     * @param known_final_velocity			The final velocity of the jerk (in used length units per second)					leave "null" if unknown
     * @param part_remaining_distance			The distance left of the part at the beginning of the jerk (in used length units)
     * @param config							The configuration for the trajectory/path
     */
	public Jerk(double known_initial_acceleration, double expected_final_acceleration, double known_initial_velocity, double known_final_velocity, double part_remaining_distance, Trajectory.Config config) {
		double max_jerk;
		if(expected_final_acceleration - known_initial_acceleration < 0) {
			max_jerk = config.max_jerk;
		}else {
			max_jerk = -config.max_jerk;
		}
		
		double test_jerk_time = (expected_final_acceleration - known_initial_acceleration) / max_jerk;
		double test_jerk_distance = 	known_initial_velocity * test_jerk_time + (1/2) * known_initial_acceleration * Math.pow(test_jerk_time, 2) + (1/6) * max_jerk * Math.pow(test_jerk_time, 3);

		if(test_jerk_distance > part_remaining_distance) {
			//time_needed = ;
		}else {
			if(known_final_velocity == (Double)null) {
				time_needed = test_jerk_time;
				distance_covered = test_jerk_distance;
				actual_final_acceleration = expected_final_acceleration;
				actual_initial_velocity = known_initial_velocity;
				actual_final_velocity = known_initial_velocity + known_initial_acceleration * time_needed + (1/2) * config.max_jerk * Math.pow(time_needed, 2);
			}else {
				time_needed = test_jerk_time;
				distance_covered = test_jerk_distance;
				actual_final_acceleration = expected_final_acceleration;
				actual_initial_velocity = known_final_velocity -(known_initial_acceleration * time_needed + (1/2) * config.max_jerk * Math.pow(time_needed, 2));
				actual_final_velocity = known_final_velocity;
			}
		}
	}
	
	/**
	 * 
	 * @return <p>time_needed	[0] The time required for the jerk portion</>
	 * <p>distance_covered	[1] The length of the path traversed during the jerk portion</>
	 * <p>acutal_final_acceleration	[2] The acceleration after the jerk portion</>
	 * <p>actual_initial_velocity	[3] The velocity before the jerk portion</>
	 * <p>actual_final_velocity	[4] The velocity after the jerk portion</>
	 */
	public double[] getJerkValues() {
		return new double[] {
			time_needed,
			distance_covered,
			actual_final_acceleration,
			actual_initial_velocity,
			actual_final_velocity
		};
	}
}
