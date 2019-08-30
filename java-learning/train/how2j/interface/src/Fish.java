public class Fish extends Animal implements Pet {
    private String name;

    Fish() {
        super(0);
    }

    @Override
    public void walk() {
        System.out.println("Fish cant walk!!");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("Fish is playing with you!!");
    }

    @Override
    public void eat() {
        System.out.println("Fish eat small fish!!");
    }
}
