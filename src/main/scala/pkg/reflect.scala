package pkg

import org.reflections._
import org.reflections.util._
import org.reflections.scanners._
import scala.collection.JavaConversions._

object Class1 {
  @MyAnnotation("arg")
  def foo() = println("Class1::foo()")

  @MyAnnotation("arg")
  def bar() = println("Class1::bar()")
}

object Reflect {
  def main(args: Array[String]) {
    val reflections = new Reflections(new ConfigurationBuilder()
      .setUrls(ClasspathHelper.forPackage("pkg"))
      .setScanners(new MethodAnnotationsScanner())
    )
    val methods: scala.collection.mutable.Set[java.lang.reflect.Method] = reflections.getMethodsAnnotatedWith(classOf[MyAnnotation])
    println(s"Found ${methods.size} methods")
    for (method <- methods) {
      // println("----------------------")
      val annotation: MyAnnotation = method.getAnnotation(classOf[MyAnnotation]);
      // println(s"Method '$method' has annotation: ${annotation}")
      // println(s"  value is: ${annotation.value}")
      val declaringClass = method.getDeclaringClass()
      // println(s"  parent object/class is: ${declaringClass}")
      try {
        method.invoke(declaringClass)
      }
      catch {
        case e: IllegalArgumentException =>
      }
    }
  }
}

