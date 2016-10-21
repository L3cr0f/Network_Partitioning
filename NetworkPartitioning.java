package CNPro;

import java.util.ArrayList;
import java.util.Scanner;

public class NetworkPartitioning {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("Introduce an IP address of the network: ");
		String initialIPAddress = scan.next();
		System.out.print("\n");

		// Array with the IP address
		String ipAddress[] = ip(initialIPAddress);

		System.out.print("How many subnetworks do you want: ");
		int subnets = scan.nextInt();
		System.out.print("\n");

		ArrayList<Subnetwork> subnetwork = new ArrayList<Subnetwork>();
		ArrayList<Integer> subnetworkRealSize = new ArrayList<Integer>();

		for (int i = 0; i < subnets; i++) {
			System.out
					.print(i + 1 + ") Introduce the name of the subnetwork: ");
			String name = scan.next();
			System.out.print("\n");

			System.out.print(i + 1
					+ ") Introduce the total size of the subnetwork: ");
			int number = scan.nextInt();
			System.out.print("\n");

			// Add the real size of each subnetwork
			subnetworkRealSize.add(number);
			// Add the adecuate size of each subnetwork
			subnetwork.add(new Subnetwork(name, adecuateSize(number,
					next2Exponent(number))));
		}

		int numberOfHosts = 0;

		for (Subnetwork subnetSize : subnetwork) {

			numberOfHosts = numberOfHosts + subnetSize.getSize();
		}

		int networkSize = next2Exponent(numberOfHosts);
		networkSize = adecuateSize(numberOfHosts, networkSize);

		ArrayList<Integer> part = new ArrayList <Integer>();

		// Global mask number of the network
		String[] globalMask = new String[4];

		boolean choose = false;
		while (!choose) {

			System.out
					.println("Choose the network partitioning:\n"
							+ "1) Normal partitioning (partitioning in the initial order)\n"
							+ "2) Decrease partitioning (partitioning in decrease order)\n"
							+ "3) Increase partitioning (partitioning in increase order)\n");

			int selection = scan.nextInt();

			if (selection == 1) {

				choose = true;

				// I stablish the size of the total network to know the network
				// prefix of the first subnet
				subnetwork.get(0).setNetMask(maskNumber(networkSize));
				subnetwork.get(0).setNetworkPrefix(ipAddress);
				globalMask = subnetwork.get(0).getNetMask();

				// I change the networkMask to know th broadcast address of the
				// first subnet
				subnetwork.get(0).setNetMask(
						maskNumber(subnetwork.get(0).getSize()));
				subnetwork.get(0).setBroadcastAddress();

				if (subnetwork.size() > 1) {
					
					// I do the partitioning to know which partitionings haven't
					// been assigned
					part = partitioning(networkSize, subnetwork);
					
					for (int k = 1; k < subnetwork.size() - 1; k++) {
						subnetwork.get(k).setNetMask(
								maskNumber(subnetwork.get(k).getSize()));
						subnetwork.get(k).setNetworkPrefixByBroadcastAddress(
								subnetwork.get(k - 1).getBroadcastAddress());
						subnetwork.get(k).setBroadcastAddress();
					}

					// I stablish the size of the total network to know the
					// broadcast address of the last subnet
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(networkSize));
					subnetwork.get(subnetwork.size() - 1)
							.setNetworkPrefixByBroadcastAddress(
									subnetwork.get(subnetwork.size() - 2)
											.getBroadcastAddress());
					subnetwork.get(subnetwork.size() - 1).setBroadcastAddress();
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(subnetwork.get(subnetwork.size() - 1)
									.getSize()));
				}
			} else if (selection == 2) {
				
				choose = true;
				
				// I sort the list in decrease
				sortInDecrease(subnetwork, 0, subnetwork.size());

				// I stablish the size of the total network to know the network
				// prefix of the first subnet
				subnetwork.get(0).setNetMask(maskNumber(networkSize));
				subnetwork.get(0).setNetworkPrefix(ipAddress);
				globalMask = subnetwork.get(0).getNetMask();

				// I change the networkMask to konw th broadcast address of the
				// first subnet
				subnetwork.get(0).setNetMask(
						maskNumber(subnetwork.get(0).getSize()));
				subnetwork.get(0).setBroadcastAddress();

				if (subnetwork.size() > 1) {

					// I do the partitioning to know which partitionings haven't
					// been assigned
					part = partitioning(networkSize, subnetwork);

					for (int k = 1; k < subnetwork.size() - 1; k++) {
						subnetwork.get(k).setNetMask(
								maskNumber(subnetwork.get(k).getSize()));
						subnetwork.get(k).setNetworkPrefixByBroadcastAddress(
								subnetwork.get(k - 1).getBroadcastAddress());
						subnetwork.get(k).setBroadcastAddress();
					}

					// I stablish the size of the total network to know the
					// broadcast address of the last subnet
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(networkSize));
					subnetwork.get(subnetwork.size() - 1)
							.setNetworkPrefixByBroadcastAddress(
									subnetwork.get(subnetwork.size() - 2)
											.getBroadcastAddress());
					subnetwork.get(subnetwork.size() - 1).setBroadcastAddress();
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(subnetwork.get(subnetwork.size() - 1)
									.getSize()));
				}
			} else if (selection == 3) {
				
				choose = true;
				
				// I sort the list in increase
				sortInIncrease(subnetwork, subnetwork.size());
				
				// I stablish the size of the total network to know the network
				// prefix of the first subnet
				subnetwork.get(0).setNetMask(maskNumber(networkSize));
				subnetwork.get(0).setNetworkPrefix(ipAddress);
				globalMask = subnetwork.get(0).getNetMask();

				// I change the networkMask to konw th broadcast address of the
				// first subnet
				subnetwork.get(0).setNetMask(
						maskNumber(subnetwork.get(0).getSize()));
				subnetwork.get(0).setBroadcastAddress();

				if (subnetwork.size() > 1) {

					// I do the partitioning to know which partitionings haven't
					// been assigned
					part = partitioning(networkSize, subnetwork);
					
					for (int k = 1; k < subnetwork.size() - 1; k++) {
						subnetwork.get(k).setNetMask(
								maskNumber(subnetwork.get(k).getSize()));
						subnetwork.get(k).setNetworkPrefixByBroadcastAddress(
								subnetwork.get(k - 1).getBroadcastAddress());
						subnetwork.get(k).setBroadcastAddress();
					}

					// I stablish the size of the total network to know the
					// broadcast address of the last subnet
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(networkSize));
					subnetwork.get(subnetwork.size() - 1)
							.setNetworkPrefixByBroadcastAddress(
									subnetwork.get(subnetwork.size() - 2)
											.getBroadcastAddress());
					subnetwork.get(subnetwork.size() - 1).setBroadcastAddress();
					subnetwork.get(subnetwork.size() - 1).setNetMask(
							maskNumber(subnetwork.get(subnetwork.size() - 1)
									.getSize()));

				}
			}
		}

		System.out
				.println("-----------------------------------------------------------------------------------------"
						+ "-----------------------------------------------------------");
		System.out
				.println("Total size of the network: "
						+ networkSize
						+ " | Network prefix: "
						+ subnetwork.get(0).getNetworkPrefix()[0]
						+ "."
						+ subnetwork.get(0).getNetworkPrefix()[1]
						+ "."
						+ subnetwork.get(0).getNetworkPrefix()[2]
						+ "."
						+ subnetwork.get(0).getNetworkPrefix()[3]
						+ " | Broadcast address: "
						+ subnetwork.get(subnetwork.size() - 1)
								.getBroadcastAddress()[0]
						+ "."
						+ subnetwork.get(subnetwork.size() - 1)
								.getBroadcastAddress()[1]
						+ "."
						+ subnetwork.get(subnetwork.size() - 1)
								.getBroadcastAddress()[2]
						+ "."
						+ subnetwork.get(subnetwork.size() - 1)
								.getBroadcastAddress()[3]
						+ " | Subnetwork mask: " + globalMask[0] + "."
						+ globalMask[1] + "." + globalMask[2] + "."
						+ globalMask[3]);
		System.out
				.println("-----------------------------------------------------------------------------------------"
						+ "-----------------------------------------------------------");
		for (int i = 0; i < subnetwork.size(); i++) {
			System.out.println(subnetwork.get(i).getName() + " --> "
					+ subnetwork.get(i).getSize() + " "
					+ subnetwork.get(i).getAssigned() + " | Network prefix: "
					+ subnetwork.get(i).getNetworkPrefix()[0] + "."
					+ subnetwork.get(i).getNetworkPrefix()[1] + "."
					+ subnetwork.get(i).getNetworkPrefix()[2] + "."
					+ subnetwork.get(i).getNetworkPrefix()[3]
					+ " | Broadcast address: "
					+ subnetwork.get(i).getBroadcastAddress()[0] + "."
					+ subnetwork.get(i).getBroadcastAddress()[1] + "."
					+ subnetwork.get(i).getBroadcastAddress()[2] + "."
					+ subnetwork.get(i).getBroadcastAddress()[3]
					+ " | Subnetwork mask: "
					+ subnetwork.get(i).getNetMask()[0] + "."
					+ subnetwork.get(i).getNetMask()[1] + "."
					+ subnetwork.get(i).getNetMask()[2] + "."
					+ subnetwork.get(i).getNetMask()[3]);
			System.out
					.println("-----------------------------------------------------------------------------------------"
							+ "-----------------------------------------------------------");
		}

		if (part.size() != 0) {
			System.out
					.println("\nThe following partitionings haven't been assigned:");

			for (int i = 0; i < part.size(); i++) {
				System.out.println(i + 1 + ") " + part.get(i));
			}
		} else {
			System.out.println("\nAll partitionings have been assigned");
		}
	}

	/**
	 * Function that says me the size of a network
	 * 
	 * @param number
	 * @return exponent2
	 */
	private static int next2Exponent(int number) {

		int exponent2 = 2;

		while (exponent2 < number) {
			exponent2 = exponent2 * 2;
		}

		return exponent2;
	}

	/**
	 * Method that says me the mask number of a network
	 * 
	 * @param networkSize
	 * @return maskNumber
	 */
	private static int maskNumber(int networkSize) {

		int counter = 0;
		int aux = networkSize;
		int maskNumber;

		while (aux != 2) {
			aux = aux / 2;
			counter = counter + 1;
		}

		maskNumber = 31 - counter;

		return maskNumber;
	}

	/**
	 * Function that says me if a networkSize is enough to allow the network
	 * growing
	 * 
	 * @param number
	 * @param networkSize
	 * @return networkSize
	 */
	private static int adecuateSize(int number, int networkSize) {

		if (networkSize - number < (int) number * 0.12) {
			networkSize = networkSize * 2;
		}

		return networkSize;
	}

	/**
	 * Method that sorts a list in a decrease order 24 - 13 - 8 - 2
	 * 
	 * @param subnetwork
	 * @param counter
	 * @param realSize
	 */
	private static void sortInDecrease(ArrayList<Subnetwork> subnetwork,
			int counter, int realSize) {

		if (counter < realSize) {

			for (int i = realSize - 1; i > 0; i--) {

				if (subnetwork.get(i).getSize() >= subnetwork.get(i - 1)
						.getSize()) {

					int size = subnetwork.get(i).getSize();
					String name = subnetwork.get(i).getName();
					subnetwork.set(i, subnetwork.get(i - 1));
					subnetwork.set(i - 1, new Subnetwork(name, size));

				}
			}

			sortInDecrease(subnetwork, counter + 1, realSize);

		}
	}

	/**
	 * Method that sorts a list in a increase order 3 - 9 - 16 - 25
	 * 
	 * @param subnetwork
	 * @param realSize
	 */
	private static void sortInIncrease(ArrayList<Subnetwork> subnetwork,
			int realSize) {

		if (realSize > 1) {

			for (int i = 0; i < realSize - 1; i++) {

				if (subnetwork.get(i).getSize() >= subnetwork.get(i + 1)
						.getSize()) {

					int size = subnetwork.get(i).getSize();
					String name = subnetwork.get(i).getName();
					subnetwork.set(i, subnetwork.get(i + 1));
					subnetwork.set(i + 1, new Subnetwork(name, size));

				}
			}

			sortInIncrease(subnetwork, realSize - 1);

		}
	}

	/**
	 * Converts an string with the IP address in an integer array 192.168.10.12
	 * --> [192] [168] [10] [12]
	 * 
	 * @param ipString
	 * @return ip
	 */
	private static String[] ip(String ipString) {

		String[] ip = new String[4];
		int i = 0;
		String ipWithoutDots = "";

		for (int k = 0; k < ipString.length(); k++) {
			if (k != ipString.length() - 1) {
				if (!ipString.substring(k, k + 1).equals(".")) {
					ipWithoutDots = ipWithoutDots
							+ ipString.substring(k, k + 1);
				} else {
					ip[i] = ipWithoutDots;
					i = i + 1;
					ipWithoutDots = "";
				}
			} else {
				ipWithoutDots = ipWithoutDots + ipString.substring(k);
				ip[i] = ipWithoutDots;
			}
		}

		return ip;
	}

	/**
	 * Method that executes the algorithm saw in class
	 * 
	 * @param networkSize
	 * @param subnetwork
	 */
	private static ArrayList<Integer> partitioning(int networkSize,
			ArrayList<Subnetwork> subnetwork) {

		int numberOfHosts = 0;
		for (Subnetwork subnet : subnetwork) {
			numberOfHosts = numberOfHosts + subnet.getSize();
		}

		ArrayList<Integer> partitionings = new ArrayList<Integer>();
		int aux = networkSize;
		int size = 1;

		while (numberOfHosts != 0) {

			if (networkSize == aux) {
				partitionings.add(networkSize);
			} else {
				size = size * 2;
				for (int i = 0; i < size; i++) {
					partitionings.add(networkSize);
				}
			}

			// I check each position of the arraylist to know where do a
			// partitioning
			for (int i = 0; i < subnetwork.size(); i++) {
				for (int j = 0; j < partitionings.size(); j++) {

					// If the partitioning is equal to subnetwork size and that
					// subnetwork is not asigned
					if (partitionings.get(j) - subnetwork.get(i).getSize() == 0
							&& !subnetwork.get(i).getAssigned()) {
						numberOfHosts = numberOfHosts
								- subnetwork.get(i).getSize();
						subnetwork.get(i).setAsigned(true);
						partitionings.remove(j);
						size = partitionings.size();

						break;
					}
				}
			}

			if (numberOfHosts != 0) {
				networkSize = networkSize / 2;
				partitionings.clear();
			}
		}

		if (partitionings.size() > 1) {
			adecuatePartitioning(partitionings);
		}

		return partitionings;
	}

	/**
	 * Stablish the correct size of the non used partitionings
	 * 
	 * @param partitionings
	 */
	private static void adecuatePartitioning(ArrayList<Integer> partitionings) {

		int summatory = 0;
		for (Integer list : partitionings) {
			summatory = summatory + list;
		}

		partitionings.clear();

		while (!powerOf2(summatory)) {

			int exponent2 = 2;
			int aux = 0;
			while (exponent2 < summatory) {
				aux = exponent2;
				exponent2 = exponent2 * 2;
			}

			if (summatory == aux) {
				partitionings.add(summatory);
			} else {
				partitionings.add(aux);
				partitionings.add(summatory - aux);
			}

			summatory = partitionings.get(partitionings.size() - 1);

			if (!powerOf2(summatory)) {
				partitionings.remove(partitionings.size() - 1);
			}
		}
	}

	/**
	 * Method that calculates if a number is power of two
	 * 
	 * @param number
	 * @return isPowerOf2
	 */
	private static boolean powerOf2(int number) {
		boolean isPowerOf2 = true;

		while (isPowerOf2 && number >= 2) {
			if (number % 2 != 0) {
				isPowerOf2 = false;
			} else {
				number = number / 2;
			}
		}

		return isPowerOf2;
	}
}
