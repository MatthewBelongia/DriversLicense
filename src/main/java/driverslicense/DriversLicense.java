package driverslicense;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriversLicense {

    private String name, address, licenseNumber, endorsements, issuingState, eyeColor;

    private int height;
    private double weight;
    private char licenseClassification, sex;
    private boolean organDonor, federallyCompliant;
    private Date dateOfBirth, issueDate, expirationDate;

    //private Restriction[] restrictions;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getLicenseClassification() {
        return licenseClassification;
    }

    public void setLicenseClassification(char licenseClassification) {
        this.licenseClassification = licenseClassification;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public boolean isOrganDonor() {
        return organDonor;
    }

    public void setOrganDonor(boolean organDonor) {
        this.organDonor = organDonor;
    }

    public boolean isFederallyCompliant() {
        return federallyCompliant;
    }

    public void setFederallyCompliant(boolean federallyCompliant) {
        this.federallyCompliant = federallyCompliant;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

//    public Restriction[] getRestrictions() {
//        return restrictions;
//    }
//
//    public void setRestrictions(Restriction[] restrictions) {
//        this.restrictions = restrictions;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(String endorsements) {
        this.endorsements = endorsements;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * (include description of field order here)
     * @return
     */
    public String serializeToCSV(){

        StringBuilder csvBuilder = new StringBuilder();

        csvBuilder.append(name).append(',')
                .append(address).append(',')
                .append(eyeColor).append(',')
                .append(dateOfBirth.getTime()).append(',')
                .append(issueDate.getTime()).append(',')
                .append(expirationDate.getTime()).append(',')
                .append(licenseNumber).append(',')
                .append(issuingState).append(',')
                .append(endorsements).append(',')
                .append(sex).append(',')
                .append(federallyCompliant).append(',')
                .append(licenseClassification).append('\n');

        return csvBuilder.toString();
    }

    public static String getCSVHeader(){
        return "NAME,ADDRESS,EYE COLOR,DATE OF BIRTH,ISSUE DATE,EXPIRATION DATE," +
                "LICENSE NUMBER,STATE,ENDORSEMENTS,SEX,FEDERAL COMPLIANCE,CLASSIFICATION";
    }

    static ArrayList<DriversLicense> deserializeCSV(String serialMessage){
        ArrayList<DriversLicense> listOfLicenses = new ArrayList<>();
        DriversLicense newLicense = new DriversLicense();
        String[] splitMessage = serialMessage.split(",|\\n");
        for(int i =0;i<splitMessage.length;i++){
            switch(i%12){
                case 0: newLicense.setName(splitMessage[i]);
                    break;
                case 1: newLicense.setAddress(splitMessage[i]);
                    break;
                case 2: newLicense.setEyeColor(splitMessage[i]);
                    break;
                case 3: newLicense.setDateOfBirth(new Date(Long.valueOf(splitMessage[i]).longValue()));
                    break;
                case 4: newLicense.setIssueDate(new Date(Long.valueOf(splitMessage[i]).longValue()));
                    break;
                case 5: newLicense.setExpirationDate(new Date(Long.valueOf(splitMessage[i]).longValue()));
                    break;
                case 6: newLicense.setLicenseNumber(splitMessage[i]);
                    break;
                case 7: newLicense.setIssuingState(splitMessage[i]);
                    break;
                case 8: newLicense.setEndorsements(splitMessage[i]);
                    break;
                case 9: newLicense.setSex(splitMessage[i].charAt(0));
                    break;
                case 10: newLicense.setFederallyCompliant(Boolean.parseBoolean(splitMessage[i]));
                    break;
                case 11: newLicense.setLicenseClassification(splitMessage[i].charAt(0));
                    listOfLicenses.add(newLicense);
                    break;
                default:
                    System.out.println("mayday mayday");


            }
        }return listOfLicenses;
    }


}
