object FileMatcher {
    private def filesHere = (new java.io.File(".")).listFiles

    def filesMatching(query: String, matcher:(String, String) => Boolean) = {
        for( file <- filesHere; if matcher(file.getName, query)) yield file
    }

    def filesEnding(query : String): Array[java.io.File] = {
        filesMatching(query, _.endsWith(_))
    }
   
    def filesContaining(query : String): Array[java.io.File] = {
        filesMatching(query, _.contains(_))
    }
    
    def filesRegex(query : String): Array[java.io.File] = {
        filesMatching(query, _.matches(_))
    }

}

