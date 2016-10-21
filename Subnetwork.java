package CNPro;

public class Subnetwork {

	//Subnetork size
	private int size;
	//Subnetork name
	private String name;
	//Subnetork value that says me if it has been assigned
	private boolean assigned;
	//Subnetwork mask
	private String [] netMask;
	//Subnetwork prefix
	private String [] networkPrefix;
	//Subnetwork broadcast address
	private String [] broadcastAddress;

	public Subnetwork(String name, int size) {

		this.size = size;
		this.name = name;
		this.assigned = false;
		this.networkPrefix = new String[4];
		this.broadcastAddress = new String[4];
		this.netMask = new String[4];
	}

	/**
	 * Method that returns the name of a subnetwork
	 * 
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method that returns the size of a subnetwork
	 * 
	 * @return this.size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Method that returns the assigned value of a subnetwork
	 * 
	 * @return this.assigned
	 */
	public boolean getAssigned() {
		return this.assigned;
	}

	/**
	 * Method that set the assigned value of a subnetwork
	 * 
	 */
	public void setAsigned(boolean asigned) {
		this.assigned = asigned;
	}
	
	/**
	 * Set the subnetwork mask
	 * 
	 * @param number
	 */
	public void setNetMask(int number){
		
		String netNumber = "";

		for (int i = 0; i < 32; i++) {
			if (i < number) {
				netNumber = netNumber + 1;
			} else {
				netNumber = netNumber + 0;
			}
		}

		this.netMask [0] = netNumber.substring(0, 8);
		this.netMask [1] = netNumber.substring(8, 16);
		this.netMask [2] = netNumber.substring(16, 24);
		this.netMask [3] = netNumber.substring(24, 32);
	}
	
	/**
	 * Method that returns the mask of a subnetwork
	 * 
	 * @return this.netMask
	 */
	public String [] getNetMask(){
		return this.netMask;
	}
	
	/**
	 * Method that sets the network prefix of a subnetwork
	 * The function consist on add one to the upper subnetwork broadcast address
	 * 
	 * @param brAddress
	 */
	public void setNetworkPrefixByBroadcastAddress(String [] brAddress) {
		
		boolean [] isInteger = new boolean [4];
		String [] broadcastAddress = new String [4];
		
		for(int j = 0; j < broadcastAddress.length; j++){
			broadcastAddress [j] = brAddress [j];
		}
		
		for(int i = 0; i < isInteger.length; i++){
			isInteger [i] = false;
		}
		
		int currentNumber = Integer.parseInt(broadcastAddress [3]);
		int previousNumber = 0;
		if(currentNumber != 255){
			currentNumber = currentNumber + 1;
			isInteger [3] = true;
		} else {
			currentNumber = Integer.parseInt(broadcastAddress [2]);
			previousNumber = Integer.parseInt(broadcastAddress [3]);
			if(currentNumber != 255){
				currentNumber = currentNumber + 1;
				previousNumber = 0;
				isInteger [2] = true;
			} else {
				currentNumber = Integer.parseInt(broadcastAddress [1]);
				previousNumber = Integer.parseInt(broadcastAddress [2]);
				if(currentNumber != 255){
					currentNumber = currentNumber + 1;
					previousNumber = 0;
					isInteger [1] = true;
				} else {
					currentNumber = Integer.parseInt(broadcastAddress [0]);
					previousNumber = Integer.parseInt(broadcastAddress [1]);
					currentNumber = currentNumber + 1;
					previousNumber = 0;
					isInteger [0] = true;
				}
			}
		}
		
		for(int i = 0; i < isInteger.length; i++){
			if(isInteger [i]){
				broadcastAddress [i] = Integer.toString(currentNumber);
				if(i != isInteger.length - 1){
					broadcastAddress [i + 1] = Integer.toString(previousNumber);
				}
			}
		}

		
		this.networkPrefix = broadcastAddress;
	}

	/**
 	 * Method that sets the network prefix of a subnetwork from an IP address
 	 * 
	 * @param ipAddress
	 */
	public void setNetworkPrefix(String[] ipAddress) {

		String[] binaryIP = toBinaryString(ipAddress);

		// Prcess of getting the prefix number
		for (int i = 0; i < this.netMask.length && i < binaryIP.length; i++) {
			this.networkPrefix[i] = "";
			for (int j = 0; j < this.netMask[i].length(); j++) {
				if (j == this.netMask[i].length() - 1 || j == binaryIP[i].length() - 1) {
					
					if (this.netMask[i].substring(j).equals("1") &&
							binaryIP[i].substring(j).equals("1")) {
						this.networkPrefix[i] = this.networkPrefix[i] + 1;
					} else {
						this.networkPrefix[i] = this.networkPrefix[i] + 0;
					}
				} else {
					if (this.netMask[i].substring(j, j + 1).equals("1") &&
							binaryIP[i].substring(j, j + 1).equals("1")) {
						this.networkPrefix[i] = this.networkPrefix[i] + 1;
					} else {
						this.networkPrefix[i] = this.networkPrefix[i] + 0;
					}
				}
			}
		}

		for (int k = 0; k < this.networkPrefix.length; k++) {
			this.networkPrefix[k] = Integer.toString(Integer.parseInt(
					this.networkPrefix[k], 2));
		}
	}

	/**
	 * Method thats returns the network prefix of a subnetwork
	 * 
	 * @return this.networkPrefix
	 */
	public String[] getNetworkPrefix() {
		return this.networkPrefix;
	}

	/**
 	 * Method that sets the broadcast address of a subnetwork from its network prefix address
	 */
	public void setBroadcastAddress() {

		String [] binaryNetworkPrefix = toBinaryString(this.networkPrefix);
		
		//I deny the mask
		String [] denyBinaryNetMask = new String [4];
		for(int k = 0; k < this.netMask.length; k++){
			denyBinaryNetMask [k] = "";
		
			for(int p = 0; p < this.netMask[k].length(); p++){
				if(p != this.netMask[k].length() - 1){
					if(this.netMask[k].substring(p, p+1).equals("1")){
						denyBinaryNetMask [k] = denyBinaryNetMask [k] + 0;
					} else {
						denyBinaryNetMask [k] = denyBinaryNetMask [k] + 1;					
					}
				} else {
					if(this.netMask[k].substring(p).equals("1")){
						denyBinaryNetMask [k] = denyBinaryNetMask [k] + 0;
					} else {
						denyBinaryNetMask [k] = denyBinaryNetMask [k] + 1;					
					}
				}
			}
		}
	
		// Process of getting the prefix number
		for (int i = 0; i < denyBinaryNetMask.length && i < binaryNetworkPrefix.length; i++) {
			this.broadcastAddress[i] = "";
			for (int j = 0; j < denyBinaryNetMask[i].length(); j++) {
				if (j == denyBinaryNetMask[i].length() - 1 || j == binaryNetworkPrefix[i].length() - 1) {
					
					if (denyBinaryNetMask[i].substring(j).equals("1") ||
							binaryNetworkPrefix[i].substring(j).equals("1")) {
						this.broadcastAddress [i] = this.broadcastAddress [i] + 1;
					} else {
						this.broadcastAddress [i] = this.broadcastAddress [i] + 0;
					}
				} else {
					if (denyBinaryNetMask[i].substring(j, j + 1).equals("1") ||
							binaryNetworkPrefix[i].substring(j, j + 1).equals("1")) {
						this.broadcastAddress [i] = this.broadcastAddress [i] + 1;
					} else {
						this.broadcastAddress [i] = this.broadcastAddress [i] + 0;
					}
				}
			}
		}

		for (int k = 0; k < this.broadcastAddress.length; k++) {
			this.broadcastAddress [k] = Integer.toString(Integer.parseInt(
					this.broadcastAddress [k], 2));
		}
	}

	/**
	 * Method thats returns broadcast address of a subnetwork
	 * 
	 * @return this.broadcastAddress
	 */
	public String[] getBroadcastAddress() {
		return this.broadcastAddress;
	}
	
	/**
	 * Method that converts a character string to a binary string 
	 * 
	 * @param address
	 * @return binaryIP
	 */
	private String [] toBinaryString(String [] address){
		
		// Decimal to binary string
		String[] binaryIP = new String[4];
		for (int k = 0; k < address.length; k++) {
			
			binaryIP[k] = Integer.toBinaryString(Integer.parseInt(address[k]));

			while(binaryIP[k].length() < 8){
				binaryIP [k] = 0 + binaryIP [k];
			}
		}
		
		return binaryIP;
	}
}
