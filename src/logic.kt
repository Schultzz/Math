interface Fact
interface Rule
interface Query

class Person(val name: String) {
    override fun toString(): String {
        return name
    }
}

//Grandfather rule, should have a list of grandchildren. Currently it's a one-to-one
class GrandFather(val fatherXY: Father, val fatherYZ: Father) : Rule {
    override fun toString(): String {
        return "${fatherXY.father} is grandfather of ${fatherYZ.child}"
    }
}

//This list is used to store father facts
var fathers: MutableList<Father> = mutableListOf()

class Father(val father: Person, val child: Person) : Fact {

    init {
        fathers.add(this)
    }

    override fun toString(): String {
        return "${father.name} is the father of ${child.name}"
    }
}

infix fun Fact.and(other: Fact): Rule? {

    var grandFatherRelation: Rule? = null

    if (this is Father && other is Father) {
        grandFatherRelation = GrandFather(this, other)
    }
    return grandFatherRelation
}


class FactQuery() : Query {
    //Todo use overload instead of two methods.
    fun sonQuery(queryName: Person): Fact {
        fathers.forEach {
            if (it.father == queryName) {
                return it
            }
        }
        //Should not be here :)
        return Father(queryName, Person("nobody"))
    }

    fun fatherQuery(queryName: Person) : Fact {
        fathers.forEach {
            if(it.child == queryName){
                return it
            }
        }
        //Should not be here :)
        return Father(Person("God"), queryName)
    }

}

fun main(arguments: Array<String>) {

    val p0 = Person("Christian")
    val p1 = Person("Anders")
    val p2 = Person("Erik")
    val p3 = Person("Niels")

    Father(p1, p0)
    Father(p2, p1)
    Father(p3, p2)

    /*
    Find first fact
    Find second fact, from the child of fact one
    Create a Grandfather rule from the two facts
     */
    var q = FactQuery()

    var fact1 = q.sonQuery(p3)
    if (fact1 is Father) {
        var fact2 = q.sonQuery(fact1.child)

        var grandFatherRule = fact1 and fact2

        println(grandFatherRule)
    }

    var fact3 = q.fatherQuery(p0)
    if(fact3 is Father){
        var fact4 = q.fatherQuery(fact3.father)

        var grandFatherRule = fact4 and fact3

        println(grandFatherRule)
    }


}