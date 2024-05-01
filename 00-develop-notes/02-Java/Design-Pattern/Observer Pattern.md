# Observer Pattern

https://www.runoob.com/design-pattern/observer-pattern.html

**Class Diagram**

```mermaid
classDiagram
direction BT
class Billboard {
  + display(int) void
  + update() void
}
class Monitor {
  + update() void
  + display(int) void
}
class Observer~T~ {
  # T subject
  ~ update() void
}
class Stock {
  - int price
  + main(String[]) void
  + setPrice(int) void
  + getPrice() int
}
class Subject {
  - List~Observer~Subject~~ observerList
  + addObserver(Observer~Subject~) void
  + notifyObservers() void
  + removeObserver(Observer~Subject~) void
}

Billboard  -->  Observer~T~ 
Monitor  -->  Observer~T~ 
Observer~T~  ..>  Subject 
Stock  -->  Subject 
```

Observer 类是一个抽象的观察者类，其有一个引用指向 Subject 类，即被观察者。当 Subject 的值被更新（在此为 price 的值），将调用 notifyObservers 方法，通知所有的观察者，调用其 update 方法。
