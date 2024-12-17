import java.util.*;

//This took me so long I'm so sorry if it's overcomplicated...

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> hallOfFame = new ArrayList<>();
    private static final String[] FANTASY_LANDS = {
        "Eldoria", "Mysthaven", "Celestria", "Drakenfall", "Avaloria" //Potential background nations for each character
    };

    //THESE WILL MAKE MORE SENSE FURTHER DOWN THE CODE:
    private static final int FAME_THRESHOLD = 100; //Reputation threshold for the protagonist to be added to the hall of fame arraylist
    private static final int LOVE_THRESHOLD = 75; //Love meter threshold for the protagonist to get a happy ending
    private static final int HATE_THRESHOLD = 80; //Hate threshold for the villain to notice the protagonist and become relevant
    private static final int JEALOUSY_THRESHOLD = 70; //Jealousy threshold for the rival to sabotage the protagonist
    private static final int RIVAL_CHALLENGE_THRESHOLD = 50; //Reputation threshold for the rival to challenge the protagonist
    
    private static Protagonist hero;
    private static LoveInterest interest;
    private static Rival rival;
    private static Villain villain;
    private static int lastKnownReputation = 0;
    
    public static void main(String[] args) {
        System.out.println("On your way home from school, a giant vortex opens in the ground below you and sucks you into a land you've never seen before…");
        System.out.println("You've just been transported to another world! What's your name, traveler?");
        System.out.print("> ");
        String userName = scanner.nextLine();
        //User or manager of the system, like an omniscient being that oversees the affairs of the world
        
        System.out.println("Welcome, " + userName + "! This world may seem unfamiliar, but it's clear you have a suspicious hand in the events of this land...");
        
        initializeCharacters();
        runGameLoop();
    }
    
    private static void initializeCharacters() {
        Random rand = new Random();
        String heroOrigin = FANTASY_LANDS[rand.nextInt(FANTASY_LANDS.length)];
        hero = new Protagonist(100, 50, heroOrigin);
        interest = new LoveInterest(80, 30, FANTASY_LANDS[rand.nextInt(FANTASY_LANDS.length)]);
        interest.setName("Love Interest"); 
        rival = new Rival(90, 45, FANTASY_LANDS[rand.nextInt(FANTASY_LANDS.length)]);
        villain = new Villain(120, 60, heroOrigin); // Same origin as hero to enable redemption possibility
        villain.checkRedeemable(hero); // Check if villain can be redeemed based on shared background
    }
    
    private static void runGameLoop() {
        boolean running = true;
        while(running) {
            if(hero.getReputation() >= FAME_THRESHOLD && !hallOfFame.contains(hero.getName())) {
                hallOfFame.add(hero.getName());
                villainAppears();
                //ARRAYLIST USAGE: Protagonist can gain reputation by saving civilians or through manual changes by the user
                //once this exceeds 100, the protagonist is added to the Hall of Fame arraylist and the villain will appear and become editable in the world state menu
            }
            
            //PROTAGONIST & VILLAIN INTERACTION: Whenever the protagonists reputation increases while they are in the hall of fame, the villain will ambush them in response
            //there is a 50% chance the villain takes damage and a 50% chance the protagonist takes damage at half of the opposing party's strength
            if(hero.getReputation() > lastKnownReputation) {
                int reputationGain = hero.getReputation() - lastKnownReputation;
                rival.setJealousy(rival.getJealousy() + reputationGain/2); //Rivals jealousy increases by half of the protagonist's reputation gain
                
                if(hallOfFame.contains(hero.getName())) {
                    villain.ambushHero(hero, hallOfFame);
                    villain.checkForCapture(hero, interest, hallOfFame);
                }
                lastKnownReputation = hero.getReputation();
            }
            
            //PROTAGONIST & RIVAL INTERACTION: Whenever the protagonist's reputation exceeds the Rival's challenge threshold (50), the rival will challenge them in a duel
            //Winner depends on who has higher base strength (parent class attribute), and the rivals respect will increase if the protagonist wins
            if(hero.getReputation() >= RIVAL_CHALLENGE_THRESHOLD) {
                System.out.println("\nYour growing reputation has caught your rival's attention!");
                rival.challenge(hero);
            }
            
            displayMenu();
            int choice = getMenuChoice();
            
            //Thank you to the asian man on YouTube who taught me how to do this, idk if this is the easiest way to do choices but I tried my best
            switch(choice) {
                case 1:
                    handleWorldState();
                    break;
                case 2:
                    visitVillage();
                    break;
                case 3:
                    running = false;
                    System.out.println("You fade away from this mystical realm...");
                    break;
            }
            
            checkGameConditions();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. World State and Character Management");
        System.out.println("2. Visit Village");
        System.out.println("3. Leave this Realm");
        System.out.print("Choose your action: ");
    }
    
    private static int getMenuChoice() {
        return scanner.nextInt();
    }
    
    private static void handleWorldState() {
        System.out.println("\n=== WORLD STATE ===");
        System.out.println(hero);
        System.out.println(interest);
        System.out.println(rival);
        if(hallOfFame.contains(hero.getName())) {
            System.out.println(villain);
        }
        
        System.out.println("\nWould you like to alter any statistics? (1: Yes, 2: No)");
        if(scanner.nextInt() == 1) {
            alterStatistics();
        }
    }
    //Below this is a bunch of code for editing stats, a character has editable base stats from the parent class and stats unqiue to each child class
    private static void alterStatistics() {
        System.out.println("Which character? (1: Hero, 2: Love Interest, 3: Rival, 4: Villain)");
        int charChoice = scanner.nextInt();
        
        System.out.println("Which type of stat?");
        System.out.println("1: Base Stats (Health/Strength/Background)");
        System.out.println("2: Special Stats");
        int statType = scanner.nextInt();
        
        if(statType == 1) {
            System.out.println("Which stat? (1: Health, 2: Strength, 3: Background)");
            int statChoice = scanner.nextInt();
            
            if(statChoice == 3) {
                System.out.println("Enter new background:");
                scanner.nextLine(); // Clear buffer
                String newBackground = scanner.nextLine();
                switch(charChoice) {
                    case 1: hero.setBackground(newBackground); break;
                    case 2: interest.setBackground(newBackground); break;
                    case 3: rival.setBackground(newBackground); break;
                    case 4: villain.setBackground(newBackground); break;
                }
            } else {
                System.out.println("Enter amount to increase by:");
                int amount = scanner.nextInt();
                switch(charChoice) {
                    case 1:
                        if(statChoice == 1) hero.setHealth(hero.getHealth() + amount);
                        else hero.setStrength(hero.getStrength() + amount);
                        break;
                    case 2:
                        if(statChoice == 1) interest.setHealth(interest.getHealth() + amount);
                        else interest.setStrength(interest.getStrength() + amount);
                        break;
                    case 3:
                        if(statChoice == 1) rival.setHealth(rival.getHealth() + amount);
                        else rival.setStrength(rival.getStrength() + amount);
                        break;
                    case 4:
                        if(statChoice == 1) villain.setHealth(villain.getHealth() + amount);
                        else villain.setStrength(villain.getStrength() + amount);
                        break;
                }
            }
        } else {
            switch(charChoice) {
                case 1:
                    System.out.println("Which special stat? (1: Reputation, 2: Love Meter, 3: Name)");
                    int heroStat = scanner.nextInt();
                    if(heroStat == 3) {
                        System.out.println("Enter new name:");
                        scanner.nextLine(); // Clear buffer
                        hero.setName(scanner.nextLine());
                    } else {
                        System.out.println("Enter amount to increase by:");
                        int heroAmount = scanner.nextInt();
                        if(heroStat == 1) hero.setReputation(hero.getReputation() + heroAmount);
                        else hero.setLoveMeter(hero.getLoveMeter() + heroAmount);
                    }
                    break;
                case 2:
                    System.out.println("Which special stat? (1: Visuals, 2: Distress, 3: Name)");
                    int interestStat = scanner.nextInt();
                    if(interestStat == 3) {
                        System.out.println("Enter new name:");
                        scanner.nextLine(); // Clear buffer
                        interest.setName(scanner.nextLine());
                    } else {
                        System.out.println("Enter amount to increase by:");
                        int interestAmount = scanner.nextInt();
                        if(interestStat == 1) interest.setVisuals(interest.getVisuals() + interestAmount);
                        else interest.setDistress(interest.getDistress() + interestAmount);
                    }
                    break;
                case 3:
                    System.out.println("Which special stat? (1: Jealousy, 2: Respect)");
                    int rivalStat = scanner.nextInt();
                    System.out.println("Enter amount to increase by:");
                    int rivalAmount = scanner.nextInt();
                    if(rivalStat == 1) rival.setJealousy(rival.getJealousy() + rivalAmount);
                    else rival.setRespect(rival.getRespect() + rivalAmount);
                    break;
                case 4:
                    System.out.println("Which special stat? (1: Hate, 2: Toggle Redeemed Status, 3: Name)");
                    int villainStat = scanner.nextInt();
                    if(villainStat == 3) {
                        System.out.println("Enter new name:");
                        scanner.nextLine(); // Clear buffer
                        villain.setName(scanner.nextLine());
                    } else if(villainStat == 1) {
                        System.out.println("Enter amount to increase hate by:");
                        int villainAmount = scanner.nextInt();
                        villain.setHate(villain.getHate() + villainAmount);
                    } else {
                        villain.setRedeemed(!villain.isRedeemed());
                        System.out.println("Toggled villain's redeemed status to: " + villain.isRedeemed());
                    }
                    break;
            }
        }
        System.out.println("The winds of change sweep through the realm...");
    }
    
    private static void visitVillage() {
        System.out.println("\nGoblins are attacking the village!");
        System.out.println("1. The hero rushes to help");
        System.out.println("2. The hero gathers men first");
        System.out.println("3. Return to menu");
        
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                hero.saveCivilians(5);
                System.out.println("The hero managed to save 5 civilians!");
                if(hero.getReputation() > lastKnownReputation && hallOfFame.contains(hero.getName())) {
                    villain.ambushHero(hero, hallOfFame);
                    villain.checkForCapture(hero, interest, hallOfFame);
                    lastKnownReputation = hero.getReputation();
                    //PROTAGONIST & VILLAIN INTERACTION: Ambush (explained earlier), and capture the love interest if the heros reputation since the 
                    //last capture has grown by at least 30 points
                }
                break;
            case 2:
                hero.boostMorale();
                System.out.println("Enter number of civilians for the hero to save:");
                int amount = scanner.nextInt();
                hero.saveCivilians(amount);
                if(hero.getReputation() > lastKnownReputation && hallOfFame.contains(hero.getName())) {
                    villain.ambushHero(hero, hallOfFame);
                    villain.checkForCapture(hero, interest, hallOfFame);
                    lastKnownReputation = hero.getReputation();
                }
                break;
            case 3:
                System.out.println("Returning to menu...");
                break;
        }
    }

    //VILLAIN & LOVE INTEREST INTERACTION: The villain will capture the love interest when the protagonist first joins the hall of fame arraylist
    //The love interest will cry to the protagonist the first time she is captured, increasing the protagonist's love meter
    private static void villainAppears() {
        villain.targetProtagonist();
        villain.capture(interest);
        interest.cryTo(hero);
        

        //VILLAIN & PROTAGONIST INTERACTION: If the protagonist's hp falls below 30 (from ambushes, duels from Rival, etc), the protagonist will attempt to
        //reason with the villain. If the nation they are from is the same, then the villain will rejoice and IF the protagonist's love meter exceeds the love threshold,
        //a happy ending will occur. If the villain is not redeemable, then the protagonist will die and the game will end. 
        if(hero.getHealth() < 30) {
            if(hero.reason(villain)) {
                System.out.println("The villain has been redeemed! A new era of peace begins...");
                if(hero.getLoveMeter() >= LOVE_THRESHOLD) {
                    System.out.println("Smiling upon the villain's newfound self, they live happily ever after.");
                    System.exit(0);
                }
            } else {
                System.out.println("The core entity of this world has fallen... All is lost.");
                System.exit(0);
            }
        }
    }
    
    //A villain's hate can be manually changed through the user's menu to kickstart the Villain's targeting of the protagonist before they are added to the 
    //Hall of Fame arraylist.
    private static void checkGameConditions() {
        if(villain.getHate() >= HATE_THRESHOLD) {
            villain.targetProtagonist();
        }
    
        //RIVAL & PROTAGONIST INTERACTION: If the rival's jealousy exceeds the threshold, the rival will sabotage the protagonist's reputation
        //this can remove them from the Hall of Fame arraylist, where they can be added back by increasing their reputation above 100 again
        if(rival.getJealousy() >= JEALOUSY_THRESHOLD) {
            rival.sabotage(hero);
            if(hero.getReputation() < FAME_THRESHOLD) {
                hallOfFame.remove(hero.getName());
                System.out.println("The villain has lost interest in the protagonist...");
            }
        }

        //PROTAGONIST & LOVE INTERACTION: If the protagonist's love meter exceeds the love threshold, the protagonist will live happily ever after
        //if the villain is redeemable, they will live happily ever after with the love interest, otherwise they will live happily ever after with the love interest
        //despite the ongoing threats from the villain
        if(hero.getLoveMeter() >= LOVE_THRESHOLD) {
            if(villain.isRedeemed()) {
                System.out.println("Smiling upon the villain's newfound self, they live happily ever after.");
            } else {
                System.out.println("Despite the ongoing threats, " + hero.getName() + " and " + interest.getName() + 
                                 " live happily ever after, even with " + villain.getName() + " on their tail.");
            }
            System.exit(0);
        }
    }
}
