package sample;

public class Cat {
    private String name;
    private int hp;
    private int damage;
    private double block;
    private int heal;

    Cat(String n, int h, int d, double b, int he) {
        name = n;
        hp = h;
        damage = d;
        block = b;
        heal = he;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public double getBlock() {
        return block;
    }

    public int getHeal() {
        return heal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setBlock(double block) {
        this.block = block;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
