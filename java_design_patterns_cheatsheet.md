## Java Design Patterns Cheat Sheet

---

### ✅ Creational Patterns

#### 🔹 Singleton
- Ensures only one instance exists
- Global access point
```java
public class Singleton {
    private static final Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return instance;
    }
}
```

#### 🔹 Factory Method
- Creates objects without specifying exact class
```java
interface Animal { void speak(); }
class Dog implements Animal { public void speak() { System.out.println("Woof"); } }
class Cat implements Animal { public void speak() { System.out.println("Meow"); } }
class AnimalFactory {
    static Animal create(String type) {
        return switch (type) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException();
        };
    }
}
```

#### 🔹 Builder
- Step-by-step object construction (used with immutables)
```java
class User {
    private final String name;
    private final int age;
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }
    static class Builder {
        private String name;
        private int age;
        Builder name(String name) { this.name = name; return this; }
        Builder age(int age) { this.age = age; return this; }
        User build() { return new User(this); }
    }
}
```

---

### ✅ Behavioral Patterns

#### 🔹 Strategy
- Define a family of algorithms, encapsulate them
```java
interface PaymentStrategy { void pay(int amount); }
class CreditCard implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using Credit Card"); }
}
class PayPal implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using PayPal"); }
}
class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void pay(int amount) { strategy.pay(amount); }
}
```

#### 🔹 Observer
- Notify dependent objects automatically
```java
interface Observer { void update(String message); }
class EmailObserver implements Observer {
    public void update(String message) { System.out.println("Email: " + message); }
}
class Publisher {
    List<Observer> observers = new ArrayList<>();
    void subscribe(Observer o) { observers.add(o); }
    void notifyAllObservers(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
```

---

### ✅ Structural Patterns

#### 🔹 Adapter
- Converts interface of one class into another
```java
interface MediaPlayer { void play(String filename); }
class MP3Player implements MediaPlayer {
    public void play(String filename) { System.out.println("Playing MP3: " + filename); }
}
class VLCPlayer {
    void playVLC(String filename) { System.out.println("Playing VLC: " + filename); }
}
class VLCAdapter implements MediaPlayer {
    private VLCPlayer vlc = new VLCPlayer();
    public void play(String filename) { vlc.playVLC(filename); }
}
```

#### 🔹 Decorator
- Add behavior dynamically to objects
```java
interface Coffee { String getDescription(); int cost(); }
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public int cost() { return 5; }
}
class MilkDecorator implements Coffee {
    private Coffee coffee;
    MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() { return coffee.getDescription() + ", Milk"; }
    public int cost() { return coffee.cost() + 2; }
}
```

---

Let me know if you want visual diagrams or example interview questions for these patterns.

