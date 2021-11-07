defmodule Demo do


  def main do
    Parser.parse(
      "let a = 1",
      Parser.seq(
        Parser.seq(
          Parser.tag("let"),
          Parser.tag(" ")
        ), 
        Parser.seq(
          Parser.ident(),
          Parser.seq(
            Parser.tag(" "),
            Parser.seq(
              Parser.tag("="),
              Parser.seq(
                Parser.tag(" "),
                Parser.number()
              )
            )          
          )
        )
      )
    )
  end

end
