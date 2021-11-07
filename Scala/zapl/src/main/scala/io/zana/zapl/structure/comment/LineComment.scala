package io.zana.zapl.structure.comment

import io.zana.zapl.structure.Structure
import io.zana.zapl.structure.block.BlockBody
import io.zana.zapl.structure.module.ModuleBody
import io.zana.zapl.structure.program.ProgramBody

case class LineComment(comment: String) extends Structure
  with ModuleBody
  with BlockBody
  with ProgramBody
