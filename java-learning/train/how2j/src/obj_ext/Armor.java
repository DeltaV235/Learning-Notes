package obj_ext;

public class Armor extends Item {
    int level;
    public static void main(String[] args) {
        Armor armor1 = new Armor();
        Armor armor2 = new Armor();
        armor1.name = "布甲";
        armor2.name = "锁子甲";
        armor1.price = 300;
        armor2.price = 500;
        armor1.level = 15;
        armor2.level = 40;
        System.out.println("\t名称\t价格\t等级");
        System.out.println("\t" + armor1.name + "\t" + armor1.price + "\t\t" + armor1.level);
        System.out.println("\t" + armor2.name + "\t" + armor2.price + "\t\t" + armor2.level);
    }
}
