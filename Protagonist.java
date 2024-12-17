public class Protagonist extends Character {
    private int reputation;
    private int loveMeter;
    private String name;
    
    public Protagonist(int health, int strength, String background) {
        super(health, strength, background);
        this.reputation = 0;
        this.loveMeter = 0;
        this.name = "Protagonist";
    }
    
    public void saveCivilians(int civilians) {
        int reputationGain = civilians * 2;
        this.reputation += reputationGain;
        System.out.println("\nThe people cheer as the protagonist saves " + civilians + " civilians!");
        System.out.println("Word of the heroic deed spreads through the land...");
        System.out.println("Reputation increased by " + reputationGain);
    }
    
    public void boostMorale() {
        int healthBoost = 20;
        int strengthBoost = 15;
        this.setHealth(this.getHealth() + healthBoost);
        this.setStrength(this.getStrength() + strengthBoost);
        System.out.println("\nThe protagonist rallies the troops with an inspiring speech!");
        System.out.println("'For glory and honor, we fight as one!'");
        System.out.println("Health increased by " + healthBoost);
        System.out.println("Strength increased by " + strengthBoost);
    }
    
    public boolean reason(Villain v) {
        System.out.println("\nBleeding and weakened, the protagonist reaches out to " + v.getName() + "...");
        System.out.println("'We both hail from " + this.getBackground() + ". Can't you see we're not so different?'");
        
        if(v.isRedeemable() && this.getBackground().equals(v.getBackground())) {
            System.out.println("A flicker of recognition passes through " + v.getName() + "'s eyes...");
            v.setRedeemed(true);
            v.setHate(0);
            return true;
        }
        
        System.out.println(v.getName() + " remains unmoved by the protagonist's words...");
        return false;
    }
    
    public int getReputation() {
        return reputation;
    }
    
    public void setReputation(int reputation) {
        if(reputation < 0) {
            this.reputation = 0;
        } else {
            this.reputation = reputation;
        }
    }
    
    public int getLoveMeter() {
        return loveMeter;
    }
    
    public void setLoveMeter(int loveMeter) {
        if(loveMeter < 0) {
            this.loveMeter = 0;
        } else {
            this.loveMeter = loveMeter;
        }
    }
    
    public void increaseLoveMeterFromVisuals(int visualsIncrease) {
        int loveIncrease = visualsIncrease / 2; // Love meter increases by half the visuals increase
        this.loveMeter += loveIncrease;
        System.out.println("The protagonist's heart skips a beat...");
        System.out.println("Love meter increased by " + loveIncrease);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = "Protagonist";
    }
    
    public void editStats(int health, int strength, String background, int reputation, int loveMeter) {
        super.setHealth(health);
        super.setStrength(strength);
        super.setBackground(background);
        setReputation(reputation);
        setLoveMeter(loveMeter);
    }
    
    @Override
    public String toString() {
        return "Hero Status:\n" +
               super.toString() + "\n" +
               "Name: " + name + "\n" +
               "Reputation: " + reputation + "\n" +
               "Love Meter: " + loveMeter;
    }
    
    public String toStringHealth() {
        return "Hero Health: " + getHealth();
    }
    
    public String toStringStrength() {
        return "Hero Strength: " + getStrength();
    }
    
    public String toStringReputation() {
        return "Hero Reputation: " + reputation;
    }
    
    public String toStringLoveMeter() {
        return "Hero Love Meter: " + loveMeter;
    }
    
    public String toStringBackground() {
        return "Hero Origin: " + getBackground();
    }
}
