package io.zana.zapl.structure.function

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.identifier.Identifier
import io.zana.zapl.structure.module.ModuleBody
import io.zana.zapl.structure.program.ProgramBody
import io.zana.zapl.structure.statics.Static

case class Function(name: Identifier, params: List[Parameter], static: Static,
                    body: FunctionBody)
  extends Structure
    with ModuleBody
    with ProgramBody
