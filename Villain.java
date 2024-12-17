public class Villain extends Character {
    private boolean redeemable;
    private boolean redeemed;
    private int hate;
    private String name;
    private int lastCaptureReputation = 0;
    
    public Villain(int health, int strength, String background) {
        super(health, strength, background);
        this.hate = 0;
        this.name = "Lord Darkness";
        this.redeemable = false; // Initialize as not redeemable by default
    }
    
    public void checkRedeemable(Protagonist protagonist) {
        // Villain is only redeemable if they share the same background/origin
        if (this.getBackground().equals(protagonist.getBackground())) {
            this.redeemable = true;
        } else {
            this.redeemable = false;
        }
    }
    
    public void ambushHero(Protagonist p, java.util.ArrayList<String> hallOfFame) {
        if(!hallOfFame.contains(p.getName())) {
            return;
        }
        
        System.out.println("\nThe villain emerges from the shadows to ambush " + p.getName() + "!");
        java.util.Random rand = new java.util.Random();
        boolean villainStrikes = rand.nextBoolean();
        
        if(villainStrikes) {
            int damage = this.getStrength() / 2;
            p.setHealth(p.getHealth() - damage);
            System.out.println(this.name + " strikes " + p.getName() + " for " + damage + " damage!");
        } else {
            int damage = p.getStrength() / 2;
            this.setHealth(this.getHealth() - damage);
            System.out.println(p.getName() + " counters and strikes " + this.name + " for " + damage + " damage!");
        }
    }
    
    public void capture(LoveInterest l) {
        System.out.println("The villain captures " + l.getName() + " in his dark fortress!");
        l.setHealth(l.getHealth() - 25);
        l.setDistress(l.getDistress() + 30);
        System.out.println("Love Interest's health decreased by 25");
        System.out.println("Love Interest's distress increased by 30");
    }
    
    public void checkForCapture(Protagonist p, LoveInterest l, java.util.ArrayList<String> hallOfFame) {
        if(!hallOfFame.contains(p.getName())) {
            return;
        }
        
        if(p.getReputation() >= (lastCaptureReputation + 30)) {
            capture(l);
            lastCaptureReputation = p.getReputation();
        }
    }
    
    public void targetProtagonist() {
        System.out.println("\nLord Darkness emerges from the shadows...");
        System.out.println("'Your growing influence threatens my dominion over these lands!'");
    }
    
    public boolean isRedeemable() {
        return redeemable;
    }
    
    public void setRedeemable(boolean redeemable) {
        this.redeemable = redeemable;
    }
    
    public boolean isRedeemed() {
        return redeemed;
    }
    
    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }
    
    public int getHate() {
        return hate;
    }
    
    public void setHate(int hate) {
        this.hate = hate;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Villain Status:\n" +
               super.toString() + "\n" +
               "Redeemable: " + redeemable + "\n" +
               "Redeemed: " + redeemed + "\n" +
               "Hate Level: " + hate;
    }
    
    public String toStringRedeemable() {
        return "Villain Redeemable: " + redeemable;
    }
    
    public String toStringRedeemed() {
        return "Villain Redeemed: " + redeemed;
    }
    
    public String toStringHate() {
        return "Villain Hate Level: " + hate;
    }
}
