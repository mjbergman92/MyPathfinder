package PartTypes;

import Main.Trajectory;

public class Acceleration {
	
	double accelSeconds, accelDistance, final_velocity;
	
	/**
     * Create Acceleration
     * @param start_velocity	The velocity before the constant acceleration (in used length units per second)
     * @param end_velocity	The velocity before the constant acceleration (in used length units per second)		leave "null" if unknown
     * @param distance_to_be_covered		The distance covered by the acceleration (in used length units)		leave "null" if unknown
     * @param config			The configuration for the trajectory/path
     */
	public Acceleration(double start_velocity, double end_velocity, double distance_to_be_covered, Trajectory.Config config) {
		double max_acceleration;
		if(end_velocity > start_velocity) {
			max_acceleration = config.max_acceleration;
		}else {
			max_acceleration = -config.max_acceleration;
		}
		
		if(end_velocity == (Double)null) {
			accelDistance = distance_to_be_covered;
			final_velocity = (((accelDistance - (1/2) * config.max_acceleration) / start_velocity) * max_acceleration) + start_velocity;
			accelSeconds = (final_velocity - start_velocity) / max_acceleration;
		}
		
		if(distance_to_be_covered == (Double)null) {
			accelSeconds = (end_velocity - start_velocity) / max_acceleration;
			accelDistance = start_velocity * accelSeconds + (1/2) * config.max_acceleration;
			final_velocity = end_velocity;
		}
	}
	/**
	 * 
	 * @return<p>accelSeconds [0] The time (in seconds) needed to accelerate</>
	 * <p>accelDistance [1] The distance covered (in used length units) during this constant acceleration</>
	 */
	public double[] getAccelerationValues() {
		return new double[] {
				accelSeconds,
				accelDistance,
				final_velocity
		};
	}
}
