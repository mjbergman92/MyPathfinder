package Main;

public class Pathfinder {
	public static Trajectory generate(Waypoint[] waypoints, Trajectory.Config config) {
		return CalculatePath.generateTrajectory(waypoints, config);
	}
}
