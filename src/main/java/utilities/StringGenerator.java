package utilities;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator {

    private final static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final static char[] NUMBERS = "0123456789".toCharArray();
    private final static char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^$*.[]{}()?-\"!@#%&/\\,><':;|_~`".toCharArray();
	private final static List<String> loanPurpose = new ArrayList<>(Arrays.asList("Pay off Credit Cards",
			"Debt Consolidation", "Business", "Home Improvement", "Large Purchase", "Other"));
    private final static List<String> usStates = new ArrayList<>(Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT",
			"DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
			"MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
			"TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"));
    private final static Random rand = new SecureRandom();

    /**
     * generate email address that conforms to template
     * @return - valid e-mail address
     */
    public String generateEmail() {
    	return "candidate+" + generateZipCode() + "@upgrade-challenge.com";
    }
    
    /** 
     * Generate password that conforms to password rules of
     * 1 lowercase letter
     * 1 capital letter
     * 1 number
     * @param length - password length
     * @return - password string
     */
    public String generatePassword(int length) {
        assert length >= 3;
        char[] password = new char[length];

        // Ensure we conform to password rules
        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];

        // populate rest of the password with random chars
        for (int i = 3; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        // Shuffle characters
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }

        return new String(password);
    }

    /**
     * Generate name string - 10 characters with
     * first letter capitalized
     * @return string representing name/street etc
     */
    public String generateName() {
        char[] name = new char[10];

        name[0] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        
        for (int i = 1; i < 10; i++) {
        	name[i] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        }
        
        return new String(name);
    }
    
    /**
     * Generates an addrress - 2 numbers with a space
     * and some random letters ending in Street
     * @return Random valid address
     */
    public String generateAddress() {
    	char[] street = new char[13];
    	
    	street[0] = NUMBERS[rand.nextInt(NUMBERS.length)];
    	street[1] = NUMBERS[rand.nextInt(NUMBERS.length)];
    	street[2] = ' ';
    	street[3] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
    	
    	for (int i = 4; i < 13; i++) {
    		street[i] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        }
    	return new String(street).concat(" Street");
    	
    }
    
    /**
     * Generate random number - length of 5 to be used as ZIP code
     * @return random 5 digit number
     */
    public String generateZipCode() {
    	char[] zipCode = new char[5];
    	for (int i = 0; i < 5; i++) {
    		zipCode[i] = NUMBERS[rand.nextInt(NUMBERS.length)];
        }
    	return new String(zipCode);
    }
    
    /**
     * Generate a random date between Jan 1st 1930 and Jan 1st 2000
     * @return random date 
     */
    public String generateRandomDate() {
        long startEpochDay = LocalDate.of(1930, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.of(2000, 1, 1).toEpochDay();
        
        long randomEpochDay = ThreadLocalRandom.current().longs(startEpochDay, endEpochDay).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }
    
    /**
     * Returns a valid 2 character US State value
     * @return 2 character value representing US sttate
     */
    public String getRandomUsState() {
    	return usStates.get(rand.nextInt(usStates.size()));
    }
    
    /**
     * Returns one of several available choices when choosing a loan
     * purpose from the presented menu
     * @return loan purpose string
     */
    public String getLoanPurpose() {
    	return loanPurpose.get(rand.nextInt(loanPurpose.size()));
    } 

    /**
     * Generate income based on a passed in base amount. Will add
     * between 1-10 thousand dollars to the passed in amount
     * @param baseAmount - amount to start with when calculating income
     * @return string representing income with randomly added amount
     */
    public String generateIncome(int baseAmount) {
		return Integer.toString(baseAmount  + (new Random().nextInt((10 - 1) + 1) + 1) * 1000);
    }
   
    /**
     * Generate a random UUID
     * @return UUID String
     */
    public String generateUUID() {
    	return UUID.randomUUID().toString();
    }
}
