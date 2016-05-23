package driverslicense;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;


public class DriversLicenseTest {

    DriversLicense testLicense;
    MyStringUtils myStringUtils;

    String name = "Mike Jones";
    String address = "123 Atl drive";
    String eyeColor = "Brown";
    Date expectedDOB = new Date(); // 3 times
    Date expectedIssueDate = expectedDOB;
    Date expectedExpirationDate = expectedDOB;
    String licenseNum = "007";
    String issuingState = "TX";
    String trump = "Trump";
    char male = 'M';
    boolean federallyCompliantStatus = false;
    char licenseClassification = 'C';

    @Before
    public void setUp() throws Exception {
        myStringUtils = new MyStringUtils();
        testLicense = new DriversLicense();

        testLicense.setName(name);
        testLicense.setAddress(address);
        testLicense.setEyeColor(eyeColor);
        testLicense.setDateOfBirth(expectedDOB);
        testLicense.setIssueDate(expectedIssueDate);
        testLicense.setExpirationDate(expectedExpirationDate);
        testLicense.setLicenseNumber(licenseNum);
        testLicense.setIssuingState(issuingState);
        testLicense.setEndorsements(trump);
        testLicense.setSex(male);
        testLicense.setFederallyCompliant(federallyCompliantStatus);
        testLicense.setLicenseClassification(licenseClassification);
    }

    @Test
    public void testSerializeToCSV() throws Exception {

        String actualCSVResult = testLicense.serializeToCSV();

        String expectedCSVResult = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%c,%b,%c",
                name, address, eyeColor, expectedDOB.getTime(),expectedIssueDate.getTime(), expectedExpirationDate.getTime(),
                licenseNum, issuingState, trump, male, federallyCompliantStatus, licenseClassification)+'\n';

        //System.out.println(expectedDOB.getTime());

        assertEquals("Actual CSV result did not match expectations.",expectedCSVResult, actualCSVResult);



    }

    @Test
    public void testGetCSVHeader(){
        /*
        order: name, address, eyeColor, expectedDOB,expectedIssueDate, expectedExpirationDate,
                licenseNum, issuingState, trump, male, federallyCompliantStatus, licenseClassification
         */
        String expectedHeader = "NAME,ADDRESS,EYE COLOR,DATE OF BIRTH,ISSUE DATE,EXPIRATION DATE," +
                "LICENSE NUMBER,STATE,ENDORSEMENTS,SEX,FEDERAL COMPLIANCE,CLASSIFICATION";

        String actualHeader = DriversLicense.getCSVHeader();

        assertEquals(expectedHeader, actualHeader);
    }

    @Test
    public void testDeserializeCSV(){
        String serialize = testLicense.serializeToCSV();
        serialize+=testLicense.serializeToCSV();
        ArrayList<DriversLicense> deserialized = testLicense.deserializeCSV(serialize);
        String actualName = deserialized.get(0).getName();
        String expectedName = "Mike Jones";
        assertEquals("name should be Mike Jones",expectedName,actualName);
        char actualClassification = deserialized.get(0).getLicenseClassification();
        char expectedClassification = 'C';
        System.out.println(deserialized.get(0).getDateOfBirth());

        assertEquals("classification should be C",expectedClassification,actualClassification);
    }

    @Test
    public void splitStringByNewLine(){
        String test = "test\nand testing this";
        String[] result = new String[]{"test","and testing this"};
        String[] actualResult = myStringUtils.splitStringByLine(test);
        assertEquals("split string by new line",actualResult,result);

    }

    @Test
    public void testReverseCapitalize(){
        String test = "Test RevERSE";
        String afterRegex = myStringUtils.reverseCapitalize(test);
        String otherTest = "123test TEST #134 result Fasd@";
        System.out.println(myStringUtils.reverseCapitalize(otherTest));
        assertEquals("allcaps except first letter","tEST rEVERSE",afterRegex);

    }

    @Test
    public void testReverseWord(){
        String reverseMe = "testing";
        System.out.println(myStringUtils.reverseWord(reverseMe));
    }

    @Test
    public void testReverseAllWords(){
        String reverseAllOfMe = "test reverse me";
        String actualResult = myStringUtils.reverseAllWords(reverseAllOfMe);
        assertEquals("reverse each word","tset esrever em",actualResult);

    }

    @Test
    public void testTrimAndNewLine(){
        String spaceAndNewLine = "test space me";
        String actualResult = myStringUtils.removeSpaceAndNewLine(spaceAndNewLine);
        assertEquals("no spaces new line each word","test\nspace\nme",actualResult);
    }

    @Test
    public void testAllSubstrings(){
        String testMe = "abcd";
        ArrayList<String> actualResult = myStringUtils.allSubstrings(testMe);
        //assertEquals("all substrings","test",actualResult);
    }

    @Test
    public void testOptionalChallenge(){
        String[] optional = new String[]{"testinglongwordLorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic typesetting, remaining" +
                " essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing" +
                " Lorem Ipsum passages, and more recently with desktop publish","array","text"};
        System.out.println(myStringUtils.optionalChallenge(optional));
    }
}