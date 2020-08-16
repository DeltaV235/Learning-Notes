public abstract class Animal {
    protected int legs;
    protected String name;

    protected Animal(int legs) {
        this.legs = legs;
    }

    public void walk() {
        System.out.println("There are walking!!!\nThere have " + this.legs + "legs");
    }

    public abstract void eat();
}
