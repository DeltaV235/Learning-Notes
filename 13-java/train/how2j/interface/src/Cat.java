public class Cat extends Animal implements Pet {
    Cat(String name) {
        super(4);
        this.name = name;
    }

    Cat() {
        this("");
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("The cat is playing with you!!");
    }

    @Override
    public void eat() {
        System.out.println("The cat eat fish!!");
    }
}
