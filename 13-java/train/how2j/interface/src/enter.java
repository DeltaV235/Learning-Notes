public class enter {
    public static void main(String[] args) {
        Cat cat =  new Cat("petty");
        Spider spider = new Spider(8);
        Fish fish = new Fish();
        System.out.println(cat.getName());
        spider.eat();
        fish.setName("emmmmm");
        System.out.println(fish.getName());
    }
}
