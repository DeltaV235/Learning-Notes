package deltav.linkedlist;

public class SinglyLinkedListDemo {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        HeroNode hero1 = new HeroNode(1, "test1", "test2");
        HeroNode hero2 = new HeroNode(2, "test2", "test2");
        HeroNode hero3 = new HeroNode(3, "test3", "test3");
        HeroNode hero4 = new HeroNode(4, "test4", "test4");
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);

        linkedList.showList();
    }
}

class SinglyLinkedList {
    private final HeroNode head = new HeroNode(0, null, null);

    public void add(HeroNode newHeroNode) {
        HeroNode temp = head;
        while (null != temp.next) {
            temp = temp.next;
        }
        temp.next = newHeroNode;
    }

    public void showList() {
        HeroNode temp = head;
        if (null == head.next) {
            System.out.println("LinkedList is empty!");
        }
        while (null != temp.next) {
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class HeroNode {
    public Integer no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
