import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FlightFinder {

	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart

                   // Your code
        ZonedDateTime pdsZDateTime= ZonedDateTime.of(year, month, dayOfMonth,
                                                preferredDepartureStartHour, 0, 0,
                                                0, ZoneId.of(departureCityTimeZone));
        ZonedDateTime pdeZDateTime= ZonedDateTime.of(year, month, dayOfMonth,
                                                preferredDepartureEndHour, 0, 0,
                                                0, ZoneId.of(departureCityTimeZone));;


        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        
        NavigableSet<Flight> departingflights = new TreeSet<>();
        
                   // Your code
                   // Tip: Methods like isAfter can be used to find flights in the specified user time interval
        departingflights= allDepartingFlights.stream().
                                            filter( f->f.getDepartureTime().isAfter(pdsZDateTime.toLocalDateTime()) ).
                                            filter( f->f.getDepartureTime().isBefore(pdeZDateTime.toLocalDateTime()) ).
                                            collect(Collectors.toCollection(TreeSet::new)) ;
                
        
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();      

                    // Your code  
        final LocalDateTime finl= departingflights.first().getArrivalTime().plusHours(2);
        System.out.println("in");
        connectingflights= allConnectingFlights.stream().
                                            filter( f->f.getDepartureCity().equals(arrivalCity) ).
                                            filter( f->f.getArrivalCity().equalsIgnoreCase(finalArrivalCity) ).
                                            filter( f->f.getDepartureTime().isAfter( finl )).
                                            collect(Collectors.toCollection(TreeSet::new));
        System.out.println("in");
                                        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}
	
	public static void main(String[] args) {
	    Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));
		
		Map<String, List<Flight>> allFlights = new HashMap<>();
		
		allFlights.put("New York", Arrays.asList(f1));
		allFlights.put("San Francisco", Arrays.asList(f2, f3, f4));
		
		List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
	}

}


public class Flight implements Comparable<Flight> {
	private int id; 
	private String flightNumber;
	private String airline;
	private String departureCity;
	private String arrivalCity;
	private LocalDateTime departureTime; // Local time at the time zone of the departure city
	private LocalDateTime arrivalTime; // Local time at the time zone of the arrival city
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDepartureCity() {

		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public LocalDateTime getDepartureTime() {
        System.out.println(id);
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Flight(int id, String flightNumber, String airline, String departureCity, String arrivalCity,
			LocalDateTime departureTime, LocalDateTime arrivalTime) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		System.out.println("add:"+departureCity + " to " + arrivalCity + " --> " + departureTime);
	}
	
	@Override
	public int compareTo(Flight flight) {
		return this.getId() - flight.getId();
	}
	
}

