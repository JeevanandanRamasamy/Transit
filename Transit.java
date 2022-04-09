import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		TNode walk = new TNode(), stop = new TNode(), station = new TNode(), station0 = station;
		station.down = stop;
		stop.down = walk;
		for (int i = 0, j = 0, k = 0; i < locations.length; i++) {
			walk.next = new TNode(locations[i]);
			walk = walk.next;
			if (j < busStops.length && locations[i] == busStops[j]) {
				stop.next = new TNode(busStops[j]);
				stop.next.down = walk;
				stop = stop.next;
				if (k < trainStations.length && busStops[j] == trainStations[k]) {
					station.next = new TNode(trainStations[k]);
					station.next.down = stop;
					station = station.next;
					k++;
				}
				j++;
			}
		}
		return station0;
	}
	
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {
		for (TNode tStation = trainZero; tStation.next != null; tStation = tStation.next) {
			if (tStation.next.location == station) {
				tStation.next = tStation.next.next;
				return;
			}
		}
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {
		for (TNode loc = trainZero.down.down, bStop = trainZero.down; loc != null; loc = loc.next) {
			if (bStop.next != null && bStop.next.location == loc.location)
				bStop = bStop.next;
			if (loc.location == busStop && bStop.location != busStop) {
				TNode newStop = new TNode(busStop, bStop.next, loc);
				bStop.next = newStop;
				return;
			}
		}
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		ArrayList<TNode> path = new ArrayList<TNode>();
		TNode loc = trainZero;
		while (loc != null) {
			path.add(loc);
			if (loc.next == null || loc.next.location > destination || loc.location == destination) {
				if (loc.down != null)
					loc = loc.down;
				else return path;
			}
			else loc = loc.next;
		}
		return path;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		TNode newWalk = new TNode(0), newStop = new TNode(0, null, newWalk), newStation = new TNode(0, null, newStop), station0 = newStation;
		newStop.down = newWalk;
		newStation.down = newStop;
		for (TNode station = trainZero, stop = trainZero.down, walk = trainZero.down.down; walk.next != null; walk = walk.next) {
			newWalk.next = new TNode(walk.next.location);
			if (stop.next != null && walk.next.location == stop.next.location) {
				newStop.next = new TNode(stop.next.location, null, newWalk.next);
				if (station.next != null && stop.next.location == station.next.location) {
					newStation.next = new TNode(station.next.location, null, newStop.next);
					newStation = newStation.next;
					station = station.next;
				}
				newStop = newStop.next;
				stop = stop.next;
			}
			newWalk = newWalk.next;
		}
		return station0;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		TNode stop = trainZero.down, walk = trainZero.down.down, scooter = new TNode();
		stop.down = scooter;
		scooter.down = walk;
		int i = 0;
		while (i < scooterStops.length) {
			walk = walk.next;
			if (scooterStops[i] == walk.location) {
				scooter.next = new TNode(scooterStops[i], null, walk);
				scooter = scooter.next;
				i++;
			}
			if (stop.next != null && stop.next.location == scooter.location) {
				stop.next.down = scooter;
				stop = stop.next;
			}
		}
	}
}