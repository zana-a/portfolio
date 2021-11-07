package io.zana.zapl.parser.program

import io.zana.zapl.parser.Parsable
import io.zana.zapl.parser.base.Base._
import io.zana.zapl.parser.call.Call
import io.zana.zapl.parser.comment.LineComment
import io.zana.zapl.parser.control.Control
import io.zana.zapl.parser.function.Function
import io.zana.zapl.parser.module.Module
import io.zana.zapl.parser.variable.{Assign, Variable}
import io.zana.zapl.structure.program.{Program => Structure}

object Program extends Parsable[Structure] {

  override def apply: Parser[Structure] = phrase(rep(
    LineComment.apply
      | Module.apply
      | Call.apply
      | Function.apply
      | Variable.apply
      | Assign.apply
      | Control.apply
  )) ^^ (statements => Structure(statements))
}
