package io.zana.zapl.test.parser

import org.junit.Assert.assertEquals

object Tester {

  import io.zana.zapl.parser.base.Base._

  def apply(parser: Parser[Any], input: String, expected: Any): Unit = {
    parse(parser, input) match {
      case Success(s, _) => assertEquals(expected, s)
      case Failure(s, _) => assert(assertion = false, s)
      case Error(s, _) => assert(assertion = false, s)
    }
  }
}
