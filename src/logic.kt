class Person(val name: String)

interface Rule

class Father(val father: Person, val child: Person) : Rule{
    override fun toString(): String {
        return "${father.name} is the father of ${child.name}"
    }
}

class AndOperator(val left: Rule, val right: Rule) : Rule{
    override fun toString(): String {
        return "$left and $right"
    }
}

infix fun Rule.and(other: Rule): AndOperator{
    return AndOperator(this, other)
}

fun main(args: Array<String>) {

    val p0 = Person("Christian")
    val p1 = Person("Anders")
    val p2 = Person("Erik")
    val p3 = Person("Niels")

    val f0 = Father(p1,p0)
    val f1 = Father(p2,p1)
    val f2 = Father(p3,p2)

    val f3 = f1 and f2

    val f4 = (f1 and f2) and f0

    println(f3)
    println(f4)
}
