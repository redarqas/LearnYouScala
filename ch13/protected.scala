package p {
  class A {
    protected def a() {println("a")}
  }

  class SubA extends A {
    def sub() {a()}
  }

  class B {
     //def b() {(new A).a()} : a is not accessible, differs from java 
  }
}