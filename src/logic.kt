class Person(val name: String)

interface Rule
interface Fact

class Father(val father: Person, val child: Person) : Fact {

    companion object {
        val fathers: MutableList<Father> = arrayListOf()
    }

    override fun toString(): String {
        return "${father.name} is the father of ${child.name}"
    }
}

class GrandFather(val grandFather: Person, val grandChild : Person) : Fact {

    companion object {
        val grandfathers: MutableList<GrandFather> = arrayListOf()
    }

    override fun toString() : String {
        return "${grandFather.name} is the grandfather of ${grandChild.name}"
    }
}

class AndOperator(val left: Fact, val right: Fact) : Fact {

    override fun toString(): String {
        return "$left and $right"
    }
}

infix fun Fact.and(other: Fact): AndOperator {
    return AndOperator(this, other)
}

fun main(args: Array<String>) {

    val p0 = Person("Christian")
    val p1 = Person("Anders")
    val p2 = Person("Dorte")
    val p3 = Person("Erik")
    val p4 = Person("Niels")
    val p5 = Person("God")

    Father.fathers.add(Father(p1, p0))
    Father.fathers.add(Father(p3, p1))
    Father.fathers.add(Father(p3, p2))
    Father.fathers.add(Father(p4, p3))

    //God
    Father.fathers.add(Father(p5,p0))
    Father.fathers.add(Father(p5,p1))
    Father.fathers.add(Father(p5,p2))
    Father.fathers.add(Father(p5,p3))
    Father.fathers.add(Father(p5,p4))

    var f0 = Father.fathers.get(1) and Father.fathers.get(2)

    var god = Father.fathers.get(4) and Father.fathers.get(5) and Father.fathers.get(6) and Father.fathers.get(7) and Father.fathers.get(8)

    GrandFather.grandfathers.add(GrandFather(p4, p2))
    GrandFather.grandfathers.add(GrandFather(p4, p1))
    GrandFather.grandfathers.add(GrandFather(p3, p0))

    var g0 = GrandFather.grandfathers.get(0) and GrandFather.grandfathers.get(1)

    println(f0)
    println(g0)
    println(god)



}


