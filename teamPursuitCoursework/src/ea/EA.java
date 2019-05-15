package ea;

/***
 * This is an example of an EA used to solve the problem
 *  A chromosome consists of two arrays - the pacing strategy and the transition strategy
 * This algorithm is only provided as an example of how to use the code and is very simple - it ONLY evolves the transition strategy and simply sticks with the default
 * pacing strategy
 * The default settings in the parameters file make the EA work like a hillclimber:
 * 	the population size is set to 1, and there is no crossover, just mutation
 * The pacing strategy array is never altered in this version- mutation and crossover are only
 * applied to the transition strategy array
 * It uses a simple (and not very helpful) fitness function - if a strategy results in an
 * incomplete race, the fitness is set to 1000, regardless of how much of the race is completed
 * If the race is completed, the fitness is equal to the time taken
 * The idea is to minimise the fitness value
 */


import java.util.ArrayList;
import java.util.Random;

import teamPursuit.TeamPursuit;
import teamPursuit.WomensTeamPursuit;

public class EA implements Runnable{
	
	// create a new team with the default settings
	public static TeamPursuit teamPursuit = new WomensTeamPursuit(); 
	
	private ArrayList<Individual> population = new ArrayList<Individual>();
	private ArrayList<Individual> island1 = new ArrayList<Individual>();
	private ArrayList<Individual> island2 = new ArrayList<Individual>();
	private ArrayList<Individual> island3 = new ArrayList<Individual>();
	private ArrayList<Individual> island4 = new ArrayList<Individual>();
	private ArrayList<Individual> island5 = new ArrayList<Individual>();
	private ArrayList<Individual> island6 = new ArrayList<Individual>();
	private int iteration = 0;
	private int iterationIsland = 0;
	
//	528,364,486,434,215,223,397,553,309,366,546,521,228,605,485,535,605,587,461,566,288,300,327,							
//	true,true,false,true,false,false,true,false,false,true,true,false,false,false,false,false,true,true,false,false,false,false,							
//	212.584							

	
	public EA() {
		
	}

	
	public static void main(String[] args) {
		EA ea = new EA();
		ea.run();
	}
	
	
	
	public void run() {
		initialisePopulation();
		System.out.println("finished init pop");
		
		initialiseIslands();
		iteration = 0;
		Individual individual = new Individual();
		individual.initialise_default();
		individual.evaluate(teamPursuit);
		island1.set(1, individual);
		while (iteration < Parameters.maxIterations) {
		iteration++;
		System.out.println("This is run "+ iteration);
		islandInternalEvolution();
		exchangeIslands();
		}
		ArrayList<Individual> help = new ArrayList<Individual>();
		help.addAll(island1);
		help.addAll(island2);
		help.addAll(island3);
		help.addAll(island4);
		help.addAll(island5);
		help.addAll(island6);
		population = help;
		Individual best = getBest(population);
		System.out.println("Print the best overall:");
		best.print();
		
	}

	
	
private void islandInternalEvolution() {
		
		// Run for island 1
		iterationIsland = 0;
		System.out.println("This is island 1:");
		printStatsIsland(island1);
		while (iterationIsland < Parameters.iterationsBeforeExchange) {
			iterationIsland++;
			sortPopulationAscending(island1);
			Individual parent1 = rankSelection(island1);
			Individual parent2 = rankSelection(island1);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(EA.teamPursuit);
			replace(child, island1);
			
			if (iterationIsland % Parameters.printHowOften == 0) {
				printStatsIsland(island1);
			}
		}
		Individual best = getBest(island1);
		best.print();
		
		// Run for island 2
		iterationIsland = 0;
		System.out.println("This is island 2:");
		printStatsIsland(island2);
		while (iterationIsland < Parameters.iterationsBeforeExchange) {
			
			iterationIsland++;
			sortPopulationAscending(island2);
			Individual parent1 = rankSelection(island2);
			Individual parent2 = rankSelection(island2);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(teamPursuit);
			replace(child, island2);
						
			if (iterationIsland % Parameters.printHowOften == 0) {
				printStatsIsland(island2);
			}
		}
		best = getBest(island2);
		best.print();
		
		// Run for island 3
		iterationIsland = 0;
		System.out.println("This is island 3:");
//		printStatsIsland(island3);
		while (iterationIsland < Parameters.iterationsBeforeExchange) {
			
			iterationIsland++;
			sortPopulationAscending(island3);
			Individual parent1 = rankSelection(island3);
			Individual parent2 = rankSelection(island3);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(teamPursuit);
			replace(child, island3);
						
			if (iterationIsland % Parameters.printHowOften == 0) {
				printStatsIsland(island3);
			}
		}
		best = getBest(island3);
		best.print();
		
		// Run for island 4
		iterationIsland = 0;
		System.out.println("This is island 4:");
		printStatsIsland(island4);
		while (iterationIsland < Parameters.iterationsBeforeExchange) {
			iterationIsland++;
			sortPopulationAscending(island4);
			Individual parent1 = rankSelection(island4);
			Individual parent2 = rankSelection(island4);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(teamPursuit);
			replace(child, island4);
			
			if (iterationIsland % Parameters.printHowOften == 0) {
				printStatsIsland(island4);
			}
			
		}
		best = getBest(island4);
		best.print();
		
		// Run for island 5
				iterationIsland = 0;
				System.out.println("This is island 5:");
				printStatsIsland(island5);
				while (iterationIsland < Parameters.iterationsBeforeExchange) {
					iterationIsland++;
					sortPopulationAscending(island5);
					Individual parent1 = rankSelection(island5);
					Individual parent2 = rankSelection(island5);
					Individual child = crossoverOnePoint(parent1, parent2);
					child = mutate(child);
					child.evaluate(teamPursuit);
					replace(child, island5);
					
					if (iterationIsland % Parameters.printHowOften == 0) {
						printStatsIsland(island5);
					}
					
				}
				best = getBest(island5);
				best.print();
		
		// Run for island 6
		iterationIsland = 0;
		System.out.println("This is island 6:");
		printStatsIsland(island6);
		while (iterationIsland < Parameters.iterationsBeforeExchange) {
			iterationIsland++;
			sortPopulationAscending(island6);
			Individual parent1 = rankSelection(island6);
			Individual parent2 = rankSelection(island6);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(teamPursuit);
			replace(child, island6);
			
			if (iterationIsland % Parameters.printHowOften == 0) {
				printStatsIsland(island6);
			}
			
		}
		best = getBest(island6);
		best.print();
	}
	
	private void initialiseIslands() {
		
		
		for (int i=0; i < Parameters.popSize; i++) {
			if (population.get(i).getIslandIndex() == 1) {
				island1.add(population.get(i));
			}
			if (population.get(i).getIslandIndex() == 2) {
				island2.add(population.get(i));
			}
			
			if (population.get(i).getIslandIndex() ==3 ) {
				island3.add(population.get(i));
			}
			
			if (population.get(i).getIslandIndex() == 4) {
				island4.add(population.get(i));
			}
			if (population.get(i).getIslandIndex() == 5) {
				island5.add(population.get(i));
			}
			if (population.get(i).getIslandIndex() == 6) {
				island6.add(population.get(i));
			}
		}
	}
	
	private void exchangeIslands() {
		Individual best1 = getBest(island1);
		Individual best2 = getBest(island2);
		Individual best3 = getBest(island3);
		Individual best4 = getBest(island4);	
		Individual best5 = getBest(island5);	
		Individual best6 = getBest(island6);	
		replace(best1, island2);
		replace(best2, island3);
		replace(best3, island4);
		replace(best4, island5);
		replace(best5, island6);
		replace(best6, island1);
		
	}
		
	private void printStatsIsland(ArrayList<Individual> list) {
		Individual best = getBest(list);
		Individual worst = getWorst(list);
		System.out.println("" + iterationIsland + "\t" + best + "\t" + best.result.getProportionCompleted() +  "\t" + worst
				+ "\t" + worst.result.getProportionCompleted());
	}
	private void printStats(ArrayList<Individual> list) {
		Individual best = getBest(list);
		Individual worst = getWorst(list);
		System.out.println("" + best.result.toString());
	}


	private void replace(Individual child, ArrayList<Individual> list) {
		Individual worst = new Individual();
		worst = getWorst(list);
		if (child.getFitness() < worst.getFitness()) {
			int idx = list.indexOf(worst);
			list.set(idx, child);
		}
		if (child.getFitness() == worst.getFitness() 
			&& child.result.getProportionCompleted()>worst.result.getProportionCompleted()) {
			int idx = list.indexOf(worst);
			list.set(idx, child);
		}
	}
	
	private Individual mutate(Individual child) {
		
		if (Parameters.rnd.nextDouble() > Parameters.mutationProbability) {
			return child;
		}
		// System.out.println("Mutation happened.");
		// choose how many elements to alter
		int mutationRate = 1 + Parameters.rnd.nextInt(Parameters.mutationRateMax);

		

		// mutate the transition strategy by flipping boolean value
		for (int i = 0; i < mutationRate; i++) {
			int index = Parameters.rnd.nextInt(child.transitionStrategy.length);
			child.transitionStrategy[index] = !child.transitionStrategy[index];
		}
		// mutate the pacing strategy
		
		int low = Parameters.minPower;
		int high = Parameters.maxPower;
		Random r = new Random();
		for (int i = 0; i < mutationRate; i++) {
			int newSpeed = r.nextInt(high-low) + low;
			int index = Parameters.rnd.nextInt(child.pacingStrategy.length);
			child.pacingStrategy[index] = newSpeed ;
		}

		return child;
	}

	private Individual mutateOld(Individual child) {
		if(Parameters.rnd.nextDouble() > Parameters.mutationProbability){
			return child;
		}
		
		// choose how many elements to alter
		int mutationRate = 1 + Parameters.rnd.nextInt(Parameters.mutationRateMax);
		
		// mutate the transition strategy

			//mutate the transition strategy by flipping boolean value
			for(int i = 0; i < mutationRate; i++){
				int index = Parameters.rnd.nextInt(child.transitionStrategy.length);
				child.transitionStrategy[index] = !child.transitionStrategy[index];
			}
			
		
		
		return child;
	}


	private Individual crossoverOnePoint(Individual parent1, Individual parent2) {
		if(Parameters.rnd.nextDouble() > Parameters.crossoverProbability){
			return parent1;
		}
		Individual child = new Individual();
		
		int crossoverPoint = Parameters.rnd.nextInt(parent1.transitionStrategy.length);
		
		// just copy the pacing strategy from p1 - not evolving in this version
		for(int i = 0; i < parent1.pacingStrategy.length; i++){			
			child.pacingStrategy[i] = parent1.pacingStrategy[i];
		}
		
		
		for(int i = 0; i < crossoverPoint; i++){
			child.transitionStrategy[i] = parent1.transitionStrategy[i];
		}
		for(int i = crossoverPoint; i < parent2.transitionStrategy.length; i++){
			child.transitionStrategy[i] = parent2.transitionStrategy[i];
		}
		return child;
	}
	
	private Individual crossoverTwoPoint(Individual parent1, Individual parent2) {
		if (Parameters.rnd.nextDouble() > Parameters.crossoverProbability) {
			return parent1;
		}
		Individual child1 = new Individual();
		Individual child2 = new Individual();

		int crossoverPoint1 = Parameters.rnd.nextInt(parent1.transitionStrategy.length);
		int crossoverPoint2 = Parameters.rnd.nextInt(parent1.transitionStrategy.length);

		for (int i = 0; i < crossoverPoint1; i++) {
			child1.pacingStrategy[i] = parent1.pacingStrategy[i];
		}
		for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
			child1.pacingStrategy[i] = parent2.pacingStrategy[i];
		}
		for (int i = crossoverPoint2; i < parent1.pacingStrategy.length; i++) {
			child1.pacingStrategy[i] = parent1.pacingStrategy[i];
		}

		for (int i = 0; i < crossoverPoint1; i++) {
			child1.transitionStrategy[i] = parent1.transitionStrategy[i];
		}
		
		for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
			child1.transitionStrategy[i] = parent2.transitionStrategy[i];
		}
		for (int i = crossoverPoint2; i < parent1.transitionStrategy.length; i++) {
			child1.transitionStrategy[i] = parent1.transitionStrategy[i];
		}
		
		//Create 2nd child

		for (int i = 0; i < crossoverPoint1; i++) {
			child2.pacingStrategy[i] = parent2.pacingStrategy[i];
		}
		for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
			child2.pacingStrategy[i] = parent1.pacingStrategy[i];
		}
		for (int i = crossoverPoint2; i < parent1.pacingStrategy.length; i++) {
			child2.pacingStrategy[i] = parent2.pacingStrategy[i];
		}

		for (int i = 0; i < crossoverPoint1; i++) {
			child2.transitionStrategy[i] = parent2.transitionStrategy[i];
		}
		
		for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
			child2.transitionStrategy[i] = parent1.transitionStrategy[i];
		}
		for (int i = crossoverPoint2; i < parent1.transitionStrategy.length; i++) {
			child2.transitionStrategy[i] = parent2.transitionStrategy[i];
		}
		child1.evaluate(teamPursuit);
		child2.evaluate(teamPursuit);
		
		if (child1.getFitness() <= child2.getFitness()) {
			return child1;
		}
		else {
			return child2;	
		}
		
	}

	
	/**
	 * Returns a COPY of the individual selected using tournament selection
	 * @return
	 */
	
	
	
	private Individual tournamentSelection(ArrayList<Individual> list) {
		ArrayList<Individual> candidates = new ArrayList<Individual>();
		for (int i = 0; i < Parameters.tournamentSize; i++) {
			candidates.add(list.get(Parameters.rnd.nextInt(list.size())));
		}
		return getBest(candidates).copy();
	}
	
	private Individual rankSelection(ArrayList<Individual> list) {
		ArrayList<Individual> candidates = new ArrayList<Individual>();
		Random r = new Random();
		// 1 of the 10 best 
		for (int i = 0; i < Parameters.tournamentSize; i++) {
			int randomNumber = r.nextInt(Parameters.rankSelectionParameter - 1);
			candidates.add(list.get(randomNumber));
		}
		return getBest(candidates).copy();
	}
	
	// sort the population the individual in the first position has the best fitness
	private void sortPopulationAscending(ArrayList<Individual> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(i).getFitness() > list.get(j).getFitness()) {
					Individual help = new Individual();
					help = list.get(j);
					list.set(j, list.get(i));
					list.set(i, help);
				}
			}
		}
	}
	
	private void initialisePopulation() {
		while (population.size() < Parameters.popSize) {
			Individual individual = new Individual();
			individual.initialise();
			individual.evaluate(teamPursuit);
			population.add(individual);

		}
		int island = 1;
		int inkrement = 0;
		for (int i= 0; i<Parameters.popSize;i++) {
			
			Individual replacement = population.get(i);
			if (inkrement % Parameters.membersPerIsland == 0 && inkrement != 0) {
				island++;
			}
			inkrement++;
			replacement.setIslandIndex(island);
			population.set(i, replacement);
		}
	}
	
//	private void initialisePopulationOld() {
//		while(population.size() < Parameters.popSize){
//			Individual individual = new Individual();
//			individual.initialise();			
//			individual.evaluate(teamPursuit);
//			population.add(individual);
//							
//		}		
//	}	

	private Individual getBest(ArrayList<Individual> aPopulation) {
		double bestFitness = Double.MAX_VALUE;
		Individual best = null;
		for(Individual individual : aPopulation){
			if(individual.getFitness() < bestFitness || best == null){
				best = individual;
				bestFitness = best.getFitness();
			}
		}
		return best;
	}

	private Individual getWorst(ArrayList<Individual> list) {
		double worstFitness = 0;
		Individual worst = null;
		for(Individual individual : list){
			if(individual.getFitness() > worstFitness || worst == null){
				worst = individual;
				worstFitness = worst.getFitness();
			}
		}
		return worst;
	}
	
	private void printPopulation(ArrayList<Individual> list) {
		for(Individual individual : list){
			System.out.println(individual);
		}
	}
	
	
	// Old methods / achieve
	
	public void runOld() {
		initialisePopulation();	
		System.out.println("finished init pop");
		iteration = 0;
		while(iteration < Parameters.maxIterations){
			iteration++;
			Individual parent1 = rankSelection(population);
			Individual parent2 = rankSelection(population);
			Individual child = crossoverOnePoint(parent1, parent2);
			child = mutate(child);
			child.evaluate(teamPursuit);
			replace(child, population);
			printStats(population);
		}						
		Individual best = getBest(population);
		best.print();
		
	}
	
	private void printPopulationOld() {
		for(Individual individual : population){
			System.out.println(individual);
		}
	}

	
	
	private void printStatsOld() {		
		System.out.println("" + iteration + "\t" + getBest(population) + "\t" + getWorst(population));		
	}
}
