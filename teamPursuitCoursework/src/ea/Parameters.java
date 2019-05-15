package ea;

import java.util.Random;

public class Parameters {

	public static Random rnd = new Random(System.currentTimeMillis());
	
	/**
	 * used as a seed
	 * 
	 */	
	static final boolean [] DEFAULT_WOMENS_TRANSITION_STRATEGY = {true,true,false,true,false,false,true,false,false,true,true,false,false,false,false,false,true,true,false,false,false,false,};
	public static final int [] DEFAULT_WOMENS_PACING_STRATEGY = {528,364,486,434,215,223,397,553,309,366,546,521,228,605,485,535,605,587,461,566,288,300,327};
	
	
	public static int tournamentSize = 2;
	public static int rankSelectionParameter = 10;
	public static int membersPerIsland = 20;
	public static int popSize = membersPerIsland * 6;
	// Used when initializing the pacing strategy
	public static int minPower = 205;
	public static int maxPower = 660;
	public static int mutationRateMax = 6;//out of length
	public static double mutationProbability = 0.6;
	public static double crossoverProbability = 1;
	// How many internal runs? After each run an exchange between the islands takes place.
	public static int maxIterations = 5;
	// Run on island internal
	public static int iterationsBeforeExchange = 25;
	public static int printHowOften = 5;

	
	
}
