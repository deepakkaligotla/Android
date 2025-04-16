import java.time.LocalDate
import java.time.Period

fun main() {
    bigLine("Class & Constructors")
    val p1 = Point()
    p1.setX(20)
    val p2 = Point(10,10)
    println("p1 - ${p1.hashCode()}, p2 - ${p2.hashCode()}")
    print("p1.equals(p2) - ${p1.equals(p2)}")
    emptyLine()
    bigLine("Primary Constructor class example")
    val p3 = Person("Deepak", LocalDate.of(1993, 5, 21))
    println(p3.toString())
    bigLine("Primary & Constructor class example")
    val e1 = Employee()
    print(e1.toString())
    emptyLine()
    bigLine("Backing Fields")
    val s1 = Student(201, 95.86f, "Deepak", LocalDate.of(1993, 5, 21))
    s1.city = "Bangalore"
    s1.display()
    emptyLine()
    bigLine("Inheritence")
    val c = Circle(12, 12, 90)
    c.draw()
}

fun bigLine(name: String) {
    println("----------------------$name-------------------------")
}

fun smallLine(name: String) {
    println("-----------$name------------")
}

fun emptyLine() {
    println()
}

//2 types of constructors in Kotlin, constructor itself is a keyword
//  * Primary -
//  * Secondary - using constructor keyword
class Point {
    private var x: Int
    private var y: Int

    //Secondary Constructor
    constructor() {
        this.x = 10
        this.y = 10
    }

    //Secondary Constructor
    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun display() { print("x = $x, y = $y") }

    fun getX(): Int { return this.x }
    fun getY(): Int { return this.y }
    fun setX(x: Int) { this.x= x }
    fun setY(y: Int) { this.y = y }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Point
        if (x != other.x) return false
        if (y != other.y) return false
        return true
    }

    override fun hashCode(): Int { return super.hashCode() }
    override fun toString(): String { return "Point(x=$x, y=$y)" }
}

open class Person( //By Default class is final, make it Open to inherit
    var name: String = "Deepak",
    var dob: LocalDate = LocalDate.now(),
    var age: Int? = null
) {
    init {
        println("Person class init called")
        age = Period.between(dob, LocalDate.now()).years
    }

    override fun toString(): String {
        return "Name: $name, DOB: $dob, Age: $age"
    }
}

class Employee(
    private var empId: Int,
    private var salary: Double
) {
    constructor() : this(101, 121000.00) {
        println("secondary constructor called")
        this.empId = empId
        this.salary = salary
    }

    override fun toString(): String {
        return "Employee ID: $empId, Name: $salary"
    }
}

//Backing field
class Student(private var rollNumber: Int, private var marks: Float, name: String, dob: LocalDate): Person() {
     var city: String? = null
        get() = field  //Backing Field
        set(value) {
            field = value //Backing Field
        }

    constructor(): this(101, 89.95f, "Deepak", LocalDate.now()) {
        this.rollNumber = rollNumber
        this.marks = marks
        this.city = "Hyderabad"
        this.name = name
        this.dob = dob
    }

    fun display() {
        print("Name= $name, Age= $age, DOB= $dob, Roll Number= $rollNumber, Name= $marks, City= $city")
    }
}

//Private, Protected, Public, Internal
open class Shape { //By Default "public final" so to make it inheritable "open"
    var x: Int = 0
        get() = field
        set(value) {
            field = value
        }
    var y: Int = 0
        get() = field
        set(value) {
            field = value
        }

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    open fun draw() {
        println("Draw method of Shape class called")
    }
}

class Circle(x: Int, y: Int, private var radius: Int): Shape(x, y) {
    override fun draw() {
        super<Shape>.draw()
        print("Draw method of Circle class called, ${super.x}, ${super.y}, $radius")
    }
}