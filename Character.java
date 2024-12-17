public class Character {
    private int health;
    private int strength;
    private String background;
    
    public Character(int health, int strength, String background) {
        this.health = health;
        this.strength = strength;
        this.background = background;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    public String getBackground() {
        return background;
    }
    
    public void setBackground(String background) {
        this.background = background;
    }
    
    public void editStats(int health, int strength, String background) {
        this.health = health;
        this.strength = strength;
        this.background = background;
    }
    
    @Override
    public String toString() {
        return "Health: " + health + "\n" +
               "Strength: " + strength + "\n" +
               "Origin: " + background;
    }
    
    public String toStringHealth() {
        return "Health: " + health;
    }
    
    public String toStringStrength() {
        return "Strength: " + strength;
    }
    
    public String toStringBackground() {
        return "Origin: " + background;
    }
}
