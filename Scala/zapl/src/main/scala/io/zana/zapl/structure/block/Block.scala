package io.zana.zapl.structure.block

import io.zana.zapl.structure.function.FunctionBody

case class Block(body: List[BlockBody]) extends FunctionBody
