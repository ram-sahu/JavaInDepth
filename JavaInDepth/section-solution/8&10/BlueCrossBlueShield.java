public class User {

    private long id;
    // add rest of the variables
    private int age;
    private String firstname, lastname, gender, email;
    private boolean insured,smoking;
    private HealthInsurancePlan insurancePlan;

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSmoking() {
        return smoking;
    }
    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // add rest of the getters & setters
    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }
    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public boolean isInsured() {
        return insured;
    }
    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }
     public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}

public class Patient extends User{
    private long patientId;
    
    public long getPatientId() {
        return patientId;
    }
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}

abstract class HealthInsurancePlan {
    // Code for 'coverage' field goes here
    private double coverage;

    public double getCoverage() {
        return coverage;
    }
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
    private InsuranceBrand offeredBy;

	public InsuranceBrand getOfferedBy() {
		return offeredBy;
	}
	public void setOfferedBy(InsuranceBrand offeredBy) {
		this.offeredBy = offeredBy;
	}
    public abstract double computeMonthlyPremium(double salary,int age,boolean smoking);    
}

public interface InsuranceBrand{
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan,int age,boolean smoking);
}

public class  BlueCrossBlueShield implements InsuranceBrand{
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
    }
    
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan,int age,boolean smoking){
        double premium=0;
        if(insurancePlan instanceof PlatinumPlan){
            if(age>55) premium+=200;
            if(smoking) premium+=100;
        }
        else if(insurancePlan instanceof GoldPlan){
            if(age>55) premium+=150;
            if(smoking) premium+=90;
        }
        else if(insurancePlan instanceof SilverPlan){
            if(age>55) premium+=100;
            if(smoking) premium+=80;
        }
        else if(insurancePlan instanceof BronzePlan){
            if(age>55) premium+=50;
            if(smoking) premium+=70;
        }
       return premium; 
    }
}

public class PlatinumPlan extends HealthInsurancePlan{
    PlatinumPlan(){
        this.setCoverage(0.08);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking){
        return 0.08 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
public class  GoldPlan extends HealthInsurancePlan{
    GoldPlan(){
        this.setCoverage(0.07);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking){
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
public class SilverPlan extends HealthInsurancePlan{
    SilverPlan(){
        this.setCoverage(0.06);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking){
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
public class BronzePlan extends HealthInsurancePlan{
    BronzePlan(){
        this.setCoverage(0.05);
    }
    public double computeMonthlyPremium(double salary,int age,boolean smoking){
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}

public class Billing{
   
    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];
        
        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();
        //if(patientInsurancePlan!=null) patient.setInsured(true);
        // your logic  
        if(patient.isInsured()){
            payments[0]=amount*patientInsurancePlan.getCoverage();
            amount-=payments[0];
        }
        else payments[0]=0;
        
        if(patientInsurancePlan instanceof PlatinumPlan){
            if(amount>49) amount-=50;
            else amount=0;
        }
        else if(patientInsurancePlan instanceof GoldPlan){
            if(amount>39) amount-=40;
            else amount=0;
        }
        else if(patientInsurancePlan instanceof SilverPlan){
            if(amount>29) amount-=30;
            else amount=0;
        }
        else if(patientInsurancePlan instanceof BronzePlan){
            if(amount>24) amount-=25;
            else amount=0;
        }
        else {
            if(amount>19) amount-=20;
            else amount=0;
        }
        payments[1]=amount;
        return payments;
    }

}