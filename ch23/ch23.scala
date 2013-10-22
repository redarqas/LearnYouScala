case class Person(name: String, isMale: Boolean, children: Person*)
val jamal = Person("Jamal", true) 
val souad = Person("Souad", false)
val laftouma = Person("LaFtouma", false, jamal, souad)
val persons = List(jamal, souad, laftouma)

val r = persons.filter(p => !p.isMale).flatMap(p => p.children.map(c => (p.name, c.name)))

val r2 = for( p <- persons;
              n = p.name;  
              if !p.isMale; 
              c <- p.children;
              f = c.name) yield (n, f)


 

