package com.dicoding.kotlinOOP
import kotlin.reflect.KProperty

//Importing Package
/*import packagename.ClassName
import packagename.functionName
import packagename.propertyName*/

import kotlin.random.Random
import kotlin.math.sqrt as akar
import com.dicoding.utils.*

fun main() {
    val kucing = animal("Kucing", 0.2, 1, true)
    println("Name: ${kucing.name}, Weight: ${kucing.weight}, Age: ${kucing.age}, is Mammal: ${kucing.isMammal}")
    kucing.eat()
    kucing.sleep()

    //Delegation
    val animal = animal("Kucing", 3.5, 2, true)
    animal.name = "Dicoding Miaw"
    println("Nama Hewan: ${animal.name}")

    val person = Person()
    person.name = "Dimas"
    println("Nama Orang: ${person.name}")

    val binatang = binatang()
    binatang.name = "Hewan"
    binatang.weight = 5.5
    binatang.age = 3
    println("Nama: ${binatang.name}, Weight: ${binatang.weight}, Age: ${binatang.age}")

    //encapsulation
    val car = car("", 0, "")
    car.setCar("BMW", 2004, "M3 GTR")
    println("${car.getBrand()}, ${car.getYear()}, ${car.getModel()}")

    //overloading
    val lion = Lion("Singa")
    lion.eat("Wet Food", 2.5)

    //inheritances
    val dicodingCat = Cat("Dicoding Miaw", 3.2, 2, true, "Brown", 4)
    dicodingCat.playWithHuman()
    dicodingCat.eat()
    dicodingCat.sleep()

    val ikan = fish("Tuna", 5.0, 1, true, "Blue FIsh", 2)
    ikan.swim()
    ikan.eat()
    ikan.sleep()

    //Abstract & Interfaces
    val camel = Camel()
    camel.walk()

    //Extensions
    10.printInt()
    println(10.plusThree())
    println(10.slice)
    val result = 5 sum 3
    println(result)
    println(null.slice)

    //Import & Package
    val someInt = Random(0).nextInt(1,10)
    println(someInt)
    println(akar(16.0))
    sayHello()

    //Exception Handling
    //try-catch
    //finally (optional)
    val someNullValue: String? = null
    lateinit var someMustNotNullValue: String

    try {
        someMustNotNullValue = someNullValue!!
    } catch (e: Exception) {
        someMustNotNullValue = "Nilai String Null"
    } finally {
        println(someMustNotNullValue)
    }

    val someStringValue: String? = "12.0"
    var someIntValue: Int = 0

    try {
        someIntValue = someStringValue!!.toInt()
    } catch (e: NullPointerException) {
        someIntValue = 0
    } catch (e: NumberFormatException) {
        someIntValue = -1
    } finally {
        when(someIntValue){
            0 -> println("Catch block NullPointerException terpanggil !")
            -1 -> println("Catch block NumberFormatException terpanggil !")
            else -> println(someIntValue)
        }
    }
}

class animal(name: String, weight: Double, age: Int) {
    var name: String
    val weight: Double
    val age: Int
    var isMammal: Boolean

    fun eat(){
        println("$name eating!")
    }

    fun sleep(){
        println("$name sleeping!")
    }

    //init block
    init {
        this.weight = if(weight < 0.5) 0.1 else weight
        this.age = if(age < 2) 0  else age
        this.name = name
        this.isMammal = false
    }

    //secondary constructor
    constructor(name: String, weight: Double, age: Int, isMammal: Boolean) : this(name, weight, age) {
        this.isMammal = isMammal
    }
}

class Person {
    var name: Any by DelegateClass()
}

class binatang {
    var name: Any by DelegateClass()
    var weight: Any by DelegateClass()
    var age: Any by DelegateClass()
}

//Property Delegation
class DelegateClass {
    private var value: Any = "Default"

    operator fun getValue(classRef: Any?, property: KProperty<*>) : Any {
        //println("Fungsi ini sama seperti getter untuk properti ${property.name} pada class $classRef")
        return value
    }

    operator fun setValue(classRef: Any?, property: KProperty<*>, newValue: Any){
        //println("Fungsi ini sama seperti setter untuk properti ${property.name} pada class $classRef")
        //println("Nilai ${property.name} dari: $value akan berubah menjadi $newValue")
        value = newValue
    }
}

//private practice
//setter and getter (encapsulation)
class car (private var brand: String, private var year: Int, private var model: String){

    fun getBrand() : String{
        return brand
    }

    fun getYear() : Int{
        return year
    }

    fun getModel() : String{
        return model
    }

    fun setCar(brand: String, year: Int, model: String) {
        this.brand = brand
        this.year = year
        this.model = model
    }
}

//overloading (polymorphism)
class Lion(private var name: String) {
    fun eat() {
        println("$name makan!")
    }

    fun eat(typeFood: String) {
        println("$name memakan $typeFood!")
    }

    fun eat(typeFood: String, quantity: Double) {
        println("$name memakan $typeFood sebanyak $quantity grams!")
    }

    fun sleep() {
        println("$name tidur!")
    }
}

//inheritances
open class Animl(val name: String, val weight: Double, val age: Int, val isCarnivore: Boolean){

    open fun eat(){
        println("$name sedang makan!")
    }

    open fun sleep(){
        println("$name sedang tidur!")
    }
}

class Cat(name: String, weight: Double, age: Int, isCarnivore: Boolean, val furColor: String, val numberOfFeet: Int)
    : Animl(name, weight, age, isCarnivore) {

    fun playWithHuman() {
        println("$name bermain bersama Manusia !")
    }

    override fun eat(){
        println("$name sedang memakan ikan !")
    }

    override fun sleep() {
        println("$name sedang tidur di bantal !")
    }
}

class fish(name: String, weight: Double, age: Int, isCarnivore: Boolean, val scaleColor: String, val numberOfFin: Int)
    : Animl(name, weight, age, isCarnivore) {

    fun swim() {
        println("Ikan $name sedang Berenang !")
    }

    override fun eat() {
        super.eat()
    }

    override fun sleep() {
        super.sleep()
    }
}

//Abstract and Interface
abstract class anima {
    //default value not allowed
    abstract val age: Int //must be overridden

    //default value allowed
    open val isEating = true //optional to be overridden
    val isRespire = true //can not be overridden
}

interface IWalk {
    //default value not allowed
    val numberOfLeg: Int //must be overridden
    fun walk() //must be overridden
}

interface IDrink {
}

class Camel : anima(), IWalk, IDrink {
    override val age: Int = 7   // this property must exist, try to remove it
    override val isEating = true // this property optional, try to remove it
    override val numberOfLeg = 2 // this property must exist, try to remove it
    override fun walk() { // this method  must exist, try to remove it
        println("Camel can walk")
    }
}

//Extensions
//Extension Func
fun Int.printInt(){
    println("value $this")
}

fun Int.plusThree() : Int{
    return this + 3
}

//infix func
infix fun Int.sum(value: Int): Int {
    return this + value
}

class MyHero {
    infix fun addHero(s: String) { /*...*/ }

    fun build() {
        this addHero "Superman"   // Correct
        addHero("Spidermen")       // Correct
        //  addHero "Ironman"        // Incorrect: the receiver must be specified
    }
}

//Extension Prop
val Int.slice: Int
    get() = this / 2

//Nullable Receiver
//val Int?.slice: Int
//    get() = if (this == null) 0 else this.div(2)

val Int?.slice: Int
    get() = this?.div(2) ?: 0