import java.util.ArrayDeque;
import java.util.ArrayList; 
import java.util.Deque; 
import java.util.Iterator; 
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    // This getter is used only by the junit test case. 
    public List<Passenger> getConfirmedList() {
        return confirmedList;     
    }

    /**      
     * Returns true if passenger could be added into either confirmedList or      
     * waitingList. Passenger will be added to waitingList only if confirmedList      
     * is full.      
     *      
     * Return false if both passengerList & waitingList are full      
     */     
     public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,
                                     String confirmationNumber) {
        if(confirmedList.size()>CONFIRMEDLIST_LIMIT-1){
            if(waitingList.size()>WAITINGLIST_LIMIT-1) return false;
            else{
                waitingList.add(new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber));
                System.out.println(confirmationNumber+ ":W add");
                return true;
            }
        }
        else{
            confirmedList.add(new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber));
            System.out.println(confirmationNumber+ ":C add");
            return true;
        }
    }

    /**      
     * Removes passenger with the given confirmationNumber. Passenger could be      
     * in either confirmedList or waitingList. The implementation to remove the      
     * passenger should go in removePassenger() method and removePassenger method      
     * will be tested separately by the uploaded test scripts.      
     
     * If passenger is in confirmedList, then after removing that passenger, the      
     * passenger at the front of waitingList (if not empty) must be moved into      
     * passengerList. Use poll() method (not remove()) to extract the passenger      
     * from waitingList.      
     */     
    public boolean cancel(String confirmationNumber){
        boolean bool;
        if(confirmedList.stream().anyMatch((Passenger passenger)->passenger
                                            .getConfirmationNumber().equalsIgnoreCase(confirmationNumber))
                                            ){
            bool=removePassenger(confirmedList.iterator(), confirmationNumber);
            while(confirmedList.size()<CONFIRMEDLIST_LIMIT){
                if(waitingList.size()>0) confirmedList.add(waitingList.poll());
                else break;
            }
            return bool;
        }
        else{
            
            if(confirmedList.stream().anyMatch((Passenger passenger)->passenger
                                            .getConfirmationNumber().equalsIgnoreCase(confirmationNumber))
                                            )
                       {return removePassenger(waitingList.iterator(), confirmationNumber);}
            else return false;
        }
    }

    /**      
     * Removes passenger with the given confirmation number.      
     * Returns true only if passenger was present and removed. Otherwise, return false.      
     */     
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while(iterator.hasNext()){
            if(iterator.next().getConfirmationNumber().equalsIgnoreCase(confirmationNumber)){
                System.out.println(confirmationNumber+ ": remove");

                iterator.remove();
                return true;
            }
        }
        return false;
    }

}

class Passenger {
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String travelClass;
	private String confirmationNumber;
	
	public Passenger(String firstName, String lastName, int age, String gender, String travelClass,
			String confirmationNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.travelClass = travelClass;
		this.confirmationNumber = confirmationNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}	
	public String getTravelClass() {
		return travelClass;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmationNumber == null) ? 0 : confirmationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (confirmationNumber == null) {
			if (other.confirmationNumber != null)
				return false;
		} else if (!confirmationNumber.equals(other.confirmationNumber))
			return false;
		return true;
	}	
	
}
