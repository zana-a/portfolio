# Elxir Parser Demo

A basic parser using the parser combinator technique.

## Running

Open up iex with this mix project

```sh
$ iex -S mix
```

Either Call the `Parser` module for plying around with the combinators or you
can call the `Demo` module's `main()` function and it will parse a simple string
that represents a ZAPL variable binding (eg. `let a = 1`).
