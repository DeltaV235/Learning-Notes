# Strategy Pattern

#DesignPattern #Java 

## 介绍

**策略模式**定义了一系列算法，将每一个算法封装起来，并使它们可以互相替换。策略模式让算法独立于使用它的客户而独立变化。
**主要解决**在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。

## Code

```java
public interface Strategy {
    public int doOperation(int num1, int num2);
}
```

```java
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
```

```java
public class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
```

```java
public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
```

```java
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
```

```java
public class StrategyPatternDemo {
    public static void main(String[] args) {
        // 在客户端使用时才决定使用那种算法
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
```

**Output**

```
10 + 5 = 15
10 - 5 = 5
10 * 5 = 50
```

**解释**

- `Strategy` 接口定义了一个算法族，这些算法都实现了 `doOperation` 方法。
- `OperationAdd`, `OperationSubtract`, `OperationMultiply` 分别实现了 `Strategy` 接口，提供了算法的具体实现。
- `Context` 是使用了某种算法的类，通过 `executeStrategy` 方法调用具体的算法。

## 优势

- 算法可以自由切换。
- 避免使用多重条件判断。
- 扩展性良好。
- 降低了算法类的职责，使各个算法可以独立变化并互相替换。而且使得增加新的算法变的很容易，降低了对原有系统的侵入，最终使得程序可扩展可维护性增强。

## 劣势

- 策略类数量增多，每一个策略都是一个类，复用性小。
- 所有设计模式的通病，类增多了，程序从局部来看变的更复杂了。

## 适用场景

- 多个类只有在算法或行为上稍有不同的场景。
- 需要封装算法的场景。
- 函数的复杂度越来越高，如果不使用策略模式，代码会变得很复杂。

## Reference

[tutorial video](https://www.bilibili.com/video/BV1Cz421D7Qa/?spm_id_from=333.788&vd_source=f812625f00cdd1b06ca2f4281718b552)
[tutorial](https://www.runoob.com/design-pattern/strategy-pattern.html)
[秒懂设计模式之策略模式（Strategy Pattern）](https://zhuanlan.zhihu.com/p/64584526)
