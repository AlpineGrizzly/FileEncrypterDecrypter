package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class mainTest {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		/*
		 * This is the main part of the main method that will keep asking for a menu option until the user
		 * quits the program.
		 */
		while(true) {
			/***
			 * Here I have newFile, with the method findFile. This will make sure the file the user is asking to be 
			 * encrypted or decrypted exists.
			 */
		File newFile = findFile();
		//Here is key with the getKey method, which will ask for a key from the user, but only accept it if it
		//is an integer value, otherwise it will keep asking for a value til one suffices.
		int key = getKey();
		//This is the getMenu method that will ask the user for a choice, being either 1, 2, or 3.
		//And will only return a value when one of these 3 choices are picked
		int pick = getMenuOption();
		if(pick == 3) {
			//If the user picks the 3rd option, quit, break the loop and end the program.
			System.out.println("Quitting....");
			break;
		} else {
			//Otherwise, if they pick 1. This is the encrypt method that will take the file the user has chosen,
			//as well as the key the user has chosen and encrypted a file with given key.
		switch(pick) {
		case 1: 
				encrypt(newFile, key);
			break;
			//If the user picks two, the program will take the file, and the key and decrypt a file
		case 2: decrypt(newFile, key);
			break;
		
					}
				}
			}
		}
	

	
	
//getKey method
public static int getKey() {
	Scanner Finder = new Scanner(System.in);
	
	//found will represent whether or not the user has picked and option
	//that is allowed.
	boolean found = false;
	//key will represent the key once it has been found
	int key = 0;
	System.out.print("What is the key : ");
	//do-while loop, if the user input is an integer, than the key has been found and return it to
	//the main method.
	do {
		try {
			key = Finder.nextInt();
			found = true;
			
	//Otherwise, their will be an exception, so catch the exception and ask for another value.
	} catch(Exception e) {
		
		System.out.print("Please enter a valid number: ");
	}
		//Clear the next line for the next answer
		Finder.nextLine();
	} while(!found);
	//Keep running the program till found is true;
	
	return key;
	//return the key
}

//findFile method will ensure that the file the user is inputting is valid and exists
	public static File findFile() {
		Scanner input1 = new Scanner(System.in);

		while(true) {
			//Take the string value and see if it a valid file
		System.out.print("What is the name of the text file? : ");
		String newfile = input1.next();
		File newFile = new File(newfile);
		//If the file doesnt exists, keep asking for one until it does exists.
		while(newFile.exists() == false) {
			System.out.print("That file does not exist, try again : ");
			newfile = input1.next();
			newFile = new File(newfile);
		}
		
		return newFile;
		//Return the file information to the main method
		}	
	}
	
	
//getMenuOption, this will get the menu option from the user in order to direct whether or not 
	//they want to encrypt or decrypt a file or quit.
	public static int getMenuOption() throws Exception{
		Scanner input = new Scanner(System.in);
		
		//Success will represent whether or not a valid choice has been made
		boolean success = false;
		//choice is going to the value of the choice the user has selected.
		int choice = 0;
		
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("3. Quit");
		System.out.print("What would you like to do?");
		
		//while a valid choice has not been made, keep asking the user for a value until the value also is either 1, 2, or 3
		
		do {
			try {
				choice = input.nextInt();
				if(choice >= 1 && choice <= 3) {
				success = true;
				} else {
				System.out.print("Please enter a valid choice: ");
				}
		} catch(Exception e) {
			
			System.out.print("Please enter a valid choice: ");
		}
			input.nextLine();
		} while(!success);
		//return the choice to the main method
		return choice;
		
		}
	
	
	public static void encrypt(File inputFile, int key) throws Exception {
		//Declared reader, to read the input file.
		//char encrypt will take each character in the file as a case and change it accordingly in the code
		//toBeEnc, will be the message that will be encrypted
		//encMessage, will be the encrypted message that will be printed to the new .enc file
		Scanner reader = new Scanner(inputFile);
		char encrypt;
		String toBeEnc = "";
		String encMessage = "";
		
		//If the key is greater than or less than 26 or -26, take the modulus to instead make it more workable
		//within the 26 characters available.
		if(key > 26 || key < -26) {
			key = key % 26;
			}
			
		//Check to see if the input file has a .txt extension, else dont encrypt
		if(inputFile.getName().contains(".txt")) {
			
			//Replace the .txt with the .enc in the file name
		String encFile = (inputFile.getName()).replace(".txt", ".enc");
		//create new encrypted file
		File encryptedFile = new File(encFile);
		
		//Read the file and put the files text into a string, toBeEnc.
		while(reader.hasNext()) {
			toBeEnc += reader.next() + " ";
		}
		
		
		//For the length of the string, replace each character
		for(int i = 0; i < toBeEnc.length(); i++) {
			encrypt = toBeEnc.charAt(i);
			
			//If its within the bounds the alphabet, lowercase, add the key shift to the character, then cast to get that
			//character at i in the loop.
			if(encrypt >= 'a' && encrypt <= 'z') {
				encrypt = (char)(encrypt + key);
				//If the character exceeds z, start at a and go from there.
			if(encrypt > 'z') {
				encrypt = (char)(encrypt - 'z' + 'a' - 1);
				}
			
			//each character will be added to the string, encMessage, making the encrypted message.
			encMessage += encrypt;
			
			//Now the cases for uppercase letters
			//If its within the bounds of the alphabet, uppercase, add the key shift to the character, then cast to 
			//get that character at i in the loop.
			} else if (encrypt >= 'A' && encrypt <= 'Z') {
				encrypt = (char)(encrypt + key);
				
				//If the character is shifted past z, start again at a and move from there. 
				if(encrypt > 'Z') {
					encrypt = (char)(encrypt - 'Z' + 'A' - 1);
				}
				
				encMessage += encrypt;
				
			} else {
				//If the character is not a letter, just add to message, such as number or unreadable symbols. 
				encMessage += encrypt;
			}
			//Close the reader, to stop reading the document
			reader.close();
			 
		}

		
		//Write  the encrypted file to the encrypted file with the .enc extension. 
		PrintWriter pw = new PrintWriter(encryptedFile);
			pw.print(encMessage);
			pw.close();
			
			//Verify the document has been encrypted
			System.out.println(inputFile + " has been encrypted");
			System.out.println();

		} else {
			//If the file was unable to be encrypted, print it wasnt able to be done.
			System.out.println("File cannot be encrypted");
			System.out.println();
		}
	

	}
	
	//decrypt method
	public static void decrypt(File inputFile, int key) throws Exception{
		Scanner reader = new Scanner(inputFile);
		//decrypt - this will represent the characters in the file
		//decMessage - this will the string that will have the decrypted message
		//toBeDec - the message that needs to be decrypted
		char decrypt;
		String decMessage = "";
		String tobeDec = "";
		
		//If the key is greater than 26 or less than -26, find the modulus to simplify 
		if(key > 26 || key < -26) {
		key = key % 26;
		}
		
		//If the input file's name has an .enc extension, continue the program, other wise dont decrypt.
		if(inputFile.getName().contains(".enc")) {
		//Replace .enc to .txt
		String decFile = (inputFile.getName()).replace(".enc", ".txt");
		
		//Create new file, which will hold the decrypted message
		File decryptedFile = new File(decFile);
		
		//Reader the decrypted text and add to the tobeDec string.
		while(reader.hasNext()) {
			tobeDec += reader.next() + " ";
		}
		//For loop that will take each char in the encrypted message and move it key amount of spaces for the 
		//duration of length of the string.
		for(int i = 0; i < tobeDec.length(); i++) {
			decrypt = tobeDec.charAt(i);
			
			//Since in the encryption, the key would move the char's forward so many spaces, the decryption will do the opposite,
			//hints why the char will be subtracted by the key.
			if(decrypt >= 'a' && decrypt <= 'z') {
				decrypt = (char)(decrypt - key) ;
			if(decrypt < 'a') {
				decrypt = (char)(decrypt + 'z' - 'a' + 1);
				}
			//Add each character to the decrypted message
			decMessage += decrypt;
			
			} else if (decrypt >= 'A' && decrypt <= 'Z') {
				decrypt = (char)(decrypt - key);
				
				if(decrypt < 'A') {
					decrypt = (char)(decrypt + 'Z' - 'A' + 1);
				}
				
				decMessage += decrypt;
			} else {
				decMessage += decrypt;
			}
			 
		}

		//print the decrypted message to the new file.
		PrintWriter pw = new PrintWriter(decryptedFile);
			pw.print(decMessage);
			pw.close();
			System.out.println(inputFile + " has been decrypted");
			System.out.println();
		} else {
			System.out.println("File cannot be decrypted");
			System.out.println();
		}
		
		reader.close();
	

	}
}

