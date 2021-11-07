defmodule Parser do

  def seq(p1, p2) do
    fn s ->
      {status, {parsed, remaining}} = p1.(s)

      if status == :ok do
        {status_p2, {parsed_p2, remaining_p2}} = p2.(remaining)

        if status_p2 == :ok do
          {:ok, {parsed <> parsed_p2, remaining_p2}}
        else
          {:err, {parsed, remaining}}
        end
      else
        {:err, {"", s}}
      end
    end
  end

  def alt(p1, p2) do
    fn s ->
      res_p1 = p1.(s)

      if res_p1 |> elem(0) == :ok do
        res_p1
      else
        if p2.(s) |> elem(0) == :ok do
          p2.(s)
        else
          {:err, {"", s}}
        end
      end
    end
  end

  defp many0(f, res) do
    {status, {parsed, rem}} = res

    if status == :ok do
      {inner_status, {inner_parsed, inner_rem}} = f.(rem)
      if inner_status == :ok do
        many0(f, {:ok, {parsed <> inner_parsed, inner_rem}})
      else
        {:ok, {parsed, rem}}
      end
    else
      {:ok, {"", rem}}
    end
  end

  def many0(f) do
    fn s ->
      res = f.(s)
      {status, _} = res

      if status == :ok do
        many0(f, res)
      else
        {:ok, {"", s}}
      end
    end
  end

  def tag(n) do
    fn s ->
      if s |> String.starts_with?(n) do
        {:ok, s |> String.split_at(String.length(n))}
      else
        {:err, {"", n}}
      end
    end
  end

  def number do
    Parser.alt(
      Parser.alt(
        Parser.alt(
          Parser.alt(
            Parser.tag("0"),
            Parser.tag("1")
          ),
          Parser.alt(
            Parser.tag("2"),
            Parser.tag("3")
          )
        ),
        Parser.alt(
          Parser.tag("4"),
          Parser.tag("5")
        )
      ),
      Parser.alt(
        Parser.alt(
          Parser.tag("6"),
          Parser.tag("7")
        ),
        Parser.alt(
          Parser.tag("8"),
          Parser.tag("9")
        )
      )
    )
  end

  def upper_alpha do
    Parser.alt(
      Parser.alt(
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("A"), Parser.tag("B")),
            Parser.alt(Parser.tag("C"), Parser.tag("D"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("E"), Parser.tag("F")),
            Parser.alt(Parser.tag("G"), Parser.tag("H"))
          )
        ),
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("I"), Parser.tag("J")),
            Parser.alt(Parser.tag("K"), Parser.tag("L"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("M"), Parser.tag("N")),
            Parser.alt(Parser.tag("O"), Parser.tag("P"))
          )
        )
      ),
      Parser.alt(
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("Q"), Parser.tag("R")),
            Parser.alt(Parser.tag("S"), Parser.tag("T"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("U"), Parser.tag("V")),
            Parser.alt(Parser.tag("W"), Parser.tag("X"))
          )
        ),
        Parser.alt(
          Parser.tag("Y"), Parser.tag("Z")
        )
      )
    )
  end

  def lower_alpha do
    Parser.alt(
      Parser.alt(
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("a"), Parser.tag("b")),
            Parser.alt(Parser.tag("c"), Parser.tag("d"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("e"), Parser.tag("f")),
            Parser.alt(Parser.tag("g"), Parser.tag("h"))
          )
        ),
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("i"), Parser.tag("j")),
            Parser.alt(Parser.tag("k"), Parser.tag("l"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("m"), Parser.tag("n")),
            Parser.alt(Parser.tag("o"), Parser.tag("p"))
          )
        )
      ),
      Parser.alt(
        Parser.alt(
          Parser.alt(
            Parser.alt(Parser.tag("q"), Parser.tag("r")),
            Parser.alt(Parser.tag("s"), Parser.tag("t"))
          ),
          Parser.alt(
            Parser.alt(Parser.tag("u"), Parser.tag("v")),
            Parser.alt(Parser.tag("w"), Parser.tag("x"))
          )
        ),
        Parser.alt(
          Parser.tag("y"), Parser.tag("z")
        )
      )
    )
  end

  def alpha do
    Parser.alt(
      lower_alpha(),
      upper_alpha()
    )
  end

  def ident do
    Parser.seq(
      Parser.alpha(),
      Parser.many0(
        Parser.alt(
          Parser.alt(
            Parser.alpha(),
            Parser.tag("_")
          ),
          Parser.number()
        )
      )
    )
  end

  def parse(s, f) do
    f.(s)
  end
end


