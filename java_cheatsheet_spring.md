## Java Cheat Sheet: Fidelity Interview Focus

---

### ✅ 2. Java Core & OOP Concepts

#### 🔹 OOP Pillars:

* **Encapsulation:** Binding data and methods. (private fields, public getters/setters)
* **Abstraction:** Hiding internal implementation. (abstract classes/interfaces)
* **Inheritance:** Logic reuse through class hierarchy.
* **Polymorphism:** One interface, multiple implementations (overriding/overloading).

```java
abstract class Animal {
    abstract void makeSound();
}
class Dog extends Animal {
    void makeSound() { System.out.println("Woof"); }
}
```

#### 🔹 `==` vs `.equals()`:

* `==`: Reference equality
* `.equals()`: Content equality

```java
String a = new String("test");
String b = new String("test");
System.out.println(a == b);      // false
System.out.println(a.equals(b)); // true
```

#### 🔹 Exceptions:

* **Checked**: Must be declared/handled (e.g., `IOException`)
* **Unchecked**: RuntimeException subclasses (e.g., `NullPointerException`)

#### 🔹 Java Memory Model:

* **Heap**: Stores objects, shared across threads
* **Stack**: Stores method frames, thread-local
* GC reclaims unused heap objects

#### 🔹 HashMap Internals:

* Array of buckets; uses hashCode + modulo
* Collision handled via linked list / red-black tree
* Avg O(1), worst O(n)

#### 🔹 Common Design Patterns:

##### 🔸 Singleton Pattern:

Ensures a class has only one instance and provides a global access point.

```java
public class Singleton {
    private static final Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return instance;
    }
}
```

##### 🔸 Factory Pattern:

Creates objects without specifying the exact class. Helps decouple object creation from usage.

```java
interface Animal {
    void speak();
}
class Dog implements Animal {
    public void speak() { System.out.println("Woof"); }
}
class AnimalFactory {
    public static Animal getAnimal(String type) {
        if (type.equals("dog")) return new Dog();
        return null;
    }
}
```

##### 🔸 Strategy Pattern:

Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

```java
interface PaymentStrategy {
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}
class PaymentContext {
    private PaymentStrategy strategy;
    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    public void executePayment(int amount) {
        strategy.pay(amount);
    }
}
```

---

### ✅ 3. Java 8+ & Spring Boot

#### 🔹 Functional Interfaces:

* Single abstract method

```java
@FunctionalInterface
interface GreetService {
    void greet(String name);
}
```

#### 🔹 Streams API:

* Functional operations on collections, lazy evaluated

```java
List<String> names = Arrays.asList("Tom", "Jerry", "Bob");
List<String> filtered = names.stream()
                             .filter(n -> n.startsWith("J"))
                             .collect(Collectors.toList());
```

#### 🔹 Spring Boot Annotations:

* `@RestController`, `@Service`, `@Component`, `@Autowired`
* `@RequestMapping`, `@GetMapping`, etc.
* `@SpringBootApplication` (entry point)

#### 🔹 Dependency Injection:

* IoC: Spring container creates and wires beans
* Constructor, field, setter injection

#### 🔹 Spring Boot Lifecycle:

1. `main()` launches Spring Boot
2. Auto-configuration & component scanning
3. Initializes `ApplicationContext`
4. Executes `CommandLineRunner`, `ApplicationRunner`
5. Starts embedded server (Tomcat/Jetty)

---
