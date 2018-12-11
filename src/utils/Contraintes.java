package utils;

public class Contraintes extends Utils {
	
	protected String type;
	protected int length;
	protected int minInt = 0;
	protected int maxInt = 9;
	protected boolean plusMoinsOuEgal = false;
	
	public Contraintes(String contrainte) {
		
		type = contrainte.split("_")[0];
		length = Integer.parseInt(contrainte.split("_")[1]);
		
		if (type.equals("int") && !contrainte.split("_")[2].equals("stop")) {
			minInt = Integer.parseInt(contrainte.split("_")[2]);
			maxInt = Integer.parseInt(contrainte.split("_")[3]);
		}
		
		if (type.equals("string")) {
			if (contrainte.split("_")[2].equals("+-=")) {
				plusMoinsOuEgal  = true;
			}
		}
	}
	
	protected boolean testPlayerIn(String playerIn) {
		
		String[] tab = playerIn.split("");
		
		if (playerIn.length() != length) {
			System.out.print("Nombre incorrect de charactère. ");
			return false;
		}
		
		
		if (type.equals("int")) {
			if (stringIsInt(playerIn)) {
				for(String str : tab) {
					if(Integer.parseInt(str) < minInt) {
						System.out.println("Chiffre incorrect");
						return false;
					}
					if(Integer.parseInt(str) > maxInt) {
						System.out.println("Chiffre incorrect");
						return false;
					}
				}
			}
			else {
				System.out.println("Ce n'est pas un chiffre ou un nombre");
				return false;
			}
		}
		
		if (type.equals("string")) {
			if (plusMoinsOuEgal) {
				for(String chr : tab) {
					if (!chr.equals("+") && !chr.equals("-") && !chr.equals("=")) {
						System.out.println("Veuillez répondre que des '+', des '-' ou des '='");
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
