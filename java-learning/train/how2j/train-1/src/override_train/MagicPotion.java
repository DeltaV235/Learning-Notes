package override_train;

import obj_ext.Item;

public class MagicPotion extends Item {
    public void effect() {
        System.out.println("蓝瓶使用后，可以回魔法");
    }

    public void finalize() {
        System.out.println("GC occur!");
    }

    public static void main(String[] args) {
        MagicPotion mg;
//        test gc
        while (true)
            mg = new MagicPotion();
//        mg.effect();
//        System.out.println(mg.toString());
    }
}
