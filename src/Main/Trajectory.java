package Main;

public class Trajectory {
	
	public static class Config {
		
        public double dt, max_velocity, max_acceleration, max_jerk;

        /**
         * Create a Trajectory Configuration
         * @param dt                    The time delta between points (in seconds)
         * @param max_velocity          The maximum velocity the body is capable of traveling at (in meters per second)
         * @param max_acceleration      The maximum acceleration to use (in meters per second per second)
         * @param max_jerk              The maximum jerk (acceleration per second) to use
         */
        public Config(double dt, double max_velocity, double max_acceleration, double max_jerk) {
            this.dt = dt;
            this.max_velocity = max_velocity;
            this.max_acceleration = max_acceleration;
            this.max_jerk = max_jerk;
        }
    }
	
	public Segment[] segments;

    public Trajectory(Segment[] segments) {
        this.segments = segments;
    }

    public Trajectory(int length) {
        this.segments = new Segment[length];
    }
    
    public Segment get(int index) {
    		return segments[index];
    }
	
    public int length() {
        return segments.length;
    }
    
	public static class Segment {
		public double dt, x, y, position, velocity, acceleration, jerk, heading;

        public Segment(double dt, double x, double y, double position, double velocity, double acceleration, double jerk, double heading) {
            this.dt = dt;
            this.x = x;
            this.y = y;
            this.position = position;
            this.velocity = velocity;
            this.acceleration = acceleration;
            this.jerk = jerk;
            this.heading = heading;
        }
	}
}
