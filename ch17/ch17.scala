//-----------------------------
//Sequences : Lined u in order
//-----------------------------
//List, Array
//List prepend and reverse or ListBuufer if append is needed with no stackoverflow
val buf = new scala.collection.mutable.ListBuffer[Int]
buf += 1
//ArrayBuffer 
//Strings (via StringOps)
//------------------------
//Sets Maps : uniqueness
//Sorted : use SortedMap and SortedSet implems
//The order is determined by Ordered trait
//------------------------
val text = "See Spot Arun. Run, Spot. Run!"
val wordsArray = text.split("[ !,.]+")
val words = scala.collection.mutable.Set.empty[String]
for( word <- wordsArray) {
    words += word
}
//sorted Set
val orderedSet = scala.collection.immutable.TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
//ordered Map
val orderedKeysMap = scala.collection.immutable.TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')